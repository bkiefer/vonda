/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.compiler.tree;

import static de.dfki.mlt.rudimant.compiler.Utils.*;
import static de.dfki.mlt.rudimant.compiler.GenerationConstants.*;
import static de.dfki.mlt.rudimant.compiler.tree.io.RobotGrammarParser.*;

import java.io.*;
import java.util.*;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dfki.mlt.rudimant.compiler.*;
import de.dfki.mlt.rudimant.compiler.tree.io.RobotGrammarLexer;
import de.dfki.mlt.rudimant.compiler.tree.io.RobotGrammarParser;

/**
 * class that represents a top-level file that was handed over to GrammarMain (=
 * the root of the whole tree)
 *
 * @author Anna Welker
 */
public class GrammarFile extends RudiTree implements RTBlockNode {

  public static final Logger logger = LoggerFactory.getLogger(RudimantCompiler.class);

  // imports* (comment grammar_rule | method_declaration | statement )* comment
  List<RudiTree> rules;
  static Writer out;

  public LinkedList<Token> tokens;


  // **********************************************************************
  // static methods
  // **********************************************************************

  private static GrammarFile parseInput(final String realName, InputStream in)
      throws IOException {
    // initialise the lexer with given input file
    RobotGrammarLexer lexer = new RobotGrammarLexer(new ANTLRInputStream(in));

    List<Integer> toCollect = Arrays.asList(
        new Integer[] { JAVA_CODE, ONE_L_COMMENT, MULTI_L_COMMENT, NLWS });
    CollectorTokenSource collector = new CollectorTokenSource(lexer, toCollect);

    // initialise the parser
    RobotGrammarParser parser =
        new RobotGrammarParser(new CommonTokenStream(collector));
    final boolean[] errorOccured = { false };
    parser.addErrorListener(new BaseErrorListener() {
      @Override
      public void syntaxError(Recognizer<?, ?> recognizer,
          Object offendingSymbol, int line, int charPositionInLine, String msg,
          RecognitionException e) {
        errorOccured[0] = true;
        logger.error("{}.rudi:{}:{}: {}", realName, line, charPositionInLine, msg);
      }
    });

    // create a parse tree; grammar_file is the start rule
    ParseTree tree = parser.grammar_file();
    if (errorOccured[0])
      return null;

    // initialise the visitor that will do all the work
    ParseTreeVisitor visitor = new ParseTreeVisitor(realName);

    // create the abstract syntax tree
    RudiTree myTree = visitor.visit(tree);
    if (! (myTree instanceof GrammarFile))
      return null;
    GrammarFile result = (GrammarFile)myTree;
    result.tokens = collector.getCollectedTokens();
    return (GrammarFile)myTree;
  }


  /** Prerequisite: mem.enterClass(<className>) has been called, and
   *  mem.leaveClass(<className>) is called afterwards. Parse the content of
   *  in and write the generated output to output
   * @param in     the stream containing rudi input
   * @param output the writer to output generated java code
   * @return
   * @throws IOException
   */
  public static GrammarFile parseAndTypecheck(RudimantCompiler rudi,
      InputStream in, String inputRealName) throws IOException {
    GrammarFile gf = parseInput(inputRealName, in);
    logger.info("Done parsing ");
    if (gf == null) return null;

    // do the type checking, which also adds function and variable definitions
    // to Mem
    gf.startTypeInference(rudi, rudi.typeErrorsFatal());
    logger.info("Done type checking");
    return gf;
  }

  public void generate(RudimantCompiler rudi, Writer output) throws IOException {
    // generate the output
    startGeneration(rudi, output);
    logger.info("Done generating code");
  }

  /** Constructor, only to be used by the parser */
  public GrammarFile(List<RudiTree> rules) {
    this.rules = rules;
  }

  // As expected, this works equally well.
  private void startTypeInference(RudimantCompiler rudi, boolean errorsFatal) {
    Mem mem = rudi.getMem();
    VisitorType ttv = new VisitorType(mem, errorsFatal);

    for (RudiTree t : rules) {
      if (t instanceof Import) {
        Import node = (Import)t;
        try {
          rudi.processImport(node.name, node.path, node.location);
        } catch (IOException fex) {
          ttv.typeError(fex.getMessage(), node);
        }
      } else if (t instanceof RTStatement) {
        ((RTStatement)t).visit(ttv);
      } else if (t instanceof RTExpression) {
        ((RTExpression)t).visit(ttv);
      }
    }
  }

  // in this list, keep all comments that belong to methods or rules that
  // are not evaluated into the process method
  LinkedList<Token> saveComments = new LinkedList<>();

  /** Save comments that belong to a postponed part of the code onto another
   *  list (all before pos)
   */
  private void saveCommentsForLater(VisitorGeneration gv, int pos) {
    while (!gv.collectedTokens.isEmpty()
        && gv.collectedTokens.get(0).getTokenIndex() < pos) {
      saveComments.addFirst(gv.collectedTokens.get(0));
      gv.collectedTokens.remove();
    }
  }

  /** put the saved comments back onto the original list for processing */
  private void pourBackSavedComments(VisitorGeneration gv) {
    while (!saveComments.isEmpty()) {
      gv.collectedTokens.addFirst(saveComments.getFirst());
      saveComments.removeFirst();
    }
  }

  /** Now produce code for all rules and statements in a file */
  private void writeRuleList(Writer out, Mem mem, VisitorGeneration gv)
      throws IOException{
    List<RTStatement> later = new ArrayList<>();
    // do all VarDefs *DEFINITIONS* on toplevel here, those are class attributes
    for(RudiTree r : rules) {
      StatFieldDef fd = null;
      if (r instanceof StatFieldDef) {
        // Only generates definition;
        fd = (StatFieldDef)r;
      } else if (r instanceof StatVarDef) {
        fd = new StatFieldDef("public", (StatVarDef)r);
        r.fixFields(fd);
      }
      if (fd != null) {
        if (fd.varDef.toAssign != null) {
          gv.visitNode(fd); // save comment for assignment
        } else {
          fd.visitWithComments(gv);
        }
      }
    }

    // create the process method
    out.append(PROCESS_PREFIX);
    // use all methods created from rules in this file
    for(RudiTree r : rules) {
      if (r instanceof StatMethodDeclaration) {
        // retain method declarations for later and move all appropriate
        // comments to a laterComments list
        later.add((StatMethodDeclaration)r);
        if (((StatMethodDeclaration)r).block != null)
          saveCommentsForLater(gv, r.positions[1]);
      } else if (r instanceof StatVarDef) {
        StatVarDef vd = (StatVarDef)r;
        if (vd.toAssign == null) continue;
        vd.isDefinition = false;
        vd.visitWithComments(gv);
      } else if (r instanceof StatFieldDef) {
        StatVarDef vd = ((StatFieldDef)r).varDef;
        if (vd.toAssign == null) continue;
        vd.isDefinition = false;
        vd.visitWithComments(gv);
      } else if (r instanceof StatGrammarRule || r instanceof Import) {
        // rules and imports are called as functions and may return a non-zero
        // value. If the value is 1
        if (r instanceof StatGrammarRule){
          out.append("res = ");
          out.append(((StatGrammarRule)r).label).append("();");
          later.add((StatGrammarRule)r);
          // move all appropriate comments to a laterComments list
          saveCommentsForLater(gv, r.positions[1]);
          out.append(" if (res != 0)");
        } else {
          Import imp = (Import)r;
          out.append(r.checkComments(gv, r.positions[0]));
          out.append("res = ");
          // use fully qualified name
          String rootpkg = getPackageName(mem.getTopLevelPackageSpec());
          String pkg = getPackageName(mem.getPackageSpec());
          out.append("new ");
          if (! rootpkg.isEmpty()) out.append(rootpkg).append('.');
          if (! pkg.isEmpty()) out.append(pkg).append('.');
          out.append(getQualifiedName(imp.path, imp.name)).append("(");
          Set<String> ncs = mem.getNeededClasses();
          if (ncs != null) {
            boolean notfirst = false;
            for (String c : ncs) {
              if (c.equals(mem.getClassName())) {
                c = "this";
              }
              if (notfirst) out.append(", ");
              else notfirst = true;
              out.append(lowerCaseFirst(c));
            }
          }
          out.append(").process();");
          out.append(" if (res < 0)");
        }
        out.append(" return (res - 1);\n");
      } else if (r instanceof RTStatement) {
        ((RTStatement)r).visitWithComments(gv);
      }
    }
    out.append(PROCESS_SUFFIX);

    // replace the comments list by the laterComments list
    pourBackSavedComments(gv);
    // now, add everything that we did not want in the process method
    for(RTStatement t : later){
      gv.visitNode(t);
    }
  }


  private void startGeneration(RudimantCompiler rudi, Writer out)
      throws IOException  {
    Mem mem = rudi.getMem();
    // tell the file in which package it lies
    String rootpkg = getPackageName(mem.getTopLevelPackageSpec());
    String pkg = getPackageName(mem.getPackageSpec());
    if (!rootpkg.isEmpty() || !pkg.isEmpty())
      out.append("package " + rootpkg
          + (pkg.isEmpty() ? "" : "." + pkg) + ";\n");

    out.append("import java.util.*;\n\n" +
        "import de.dfki.mlt.rudimant.agent.DialogueAct;\n" +
        "import de.dfki.lt.hfc.db.rdfProxy.*;\n" +
        "import de.dfki.lt.hfc.types.*;\n");
    // Let's import our supersuper class, if we're the top-level class
    if (! mem.isNotToplevelClass()) {
      out.append("import ").append(mem.getWrapperClass()).append(";\n");
    } else {
      // import the top level class
      out.append("import ");
      if (! rootpkg.isEmpty()) out.append(rootpkg).append('.');
      out.append(mem.getTopLevelClass()).append(";\n");
    }
    // import the included classes
    for(RudiTree r : rules) {
      // TODO: MAYBE NOT NECESSARY WHEN USING QUALIFIED NAMES IN PROCESS ??
      if (r instanceof Import) {
        Import i = (Import)r;
        if (i.path.length > 0) {
          out.append("import ");
          if (! rootpkg.isEmpty()) out.append(rootpkg).append('.');
          if (! pkg.isEmpty()) out.append(pkg).append('.');
          out.append(getQualifiedName(i.path, i.name)).append(";\n");
        }
      }
    }

    // Now, print all initial comments (preceding the first element) before the
    // class starts, for, e.g., imports
    int firstPos = rules.get(0).positions[0];
    Iterator<Token> it = tokens.iterator();
    while (it.hasNext()) {
      Token curr = it.next();
      if (curr.getTokenIndex() < firstPos && curr.getText().startsWith("/*@")) {
        out.append(RudiTree.removeJavaBrackets(curr.getText()));
        it.remove();
      } else break;
    }

    // maybe we need to import the class that imported us to use its variables
    out.append("public class ").append(mem.getClassName());
    // check if this should extend the wrapper class
    if (! mem.isNotToplevelClass()) {
      out.append(" extends ").append(mem.getWrapperClass());
    }
    out.append("{\n");

    String thisClassName = mem.getClassName();
    List<String> fields = new ArrayList<>();
    // ************************************************************
    // create fields for all classes we need instances of
    // ************************************************************
    for (String neededClass : mem.getNeededClasses()) {
      if(neededClass.equals(thisClassName)) continue;
      String name = lowerCaseFirst(neededClass);
      fields.add(name);
      out.append("private final ");
      out.append(neededClass).append(' ').append(name).append(";\n");
    }

    // ************************************************************
    // add a constructor for this class
    // ************************************************************

    // Add arguments for all needed class instances
    boolean notfirst = false;
    out.append("\n\npublic ").append(thisClassName).append('(');
    Iterator<String> names = fields.iterator();
    for (String needed : mem.getNeededClasses()) {
      if(needed.equals(thisClassName)) continue;
      if (notfirst)
        out.append(", ");
      out.append(needed).append(' ').append(names.next());
      notfirst = true;
    }
    out.append(") {\n super();\n");
    for (String name : fields) {
      out.append("this.").append(name).append(" = ").append(name).append(";\n");
    }
    out.append("\n}\n");

    // ************************************************************
    // generate the main processing method that will call all rules and imports
    // declared in this file
    // ************************************************************
    VisitorGeneration gv =
        new VisitorGeneration(out, mem, rudi.logRudi(), tokens);
    mem.enterEnvironment(this);
    writeRuleList(out, mem, gv);
    mem.leaveEnvironment(this);

    // ************************************************************
    // at the very end of the file, there might still be unprinted comments
    // ************************************************************
    for (Token comment : gv.collectedTokens) {
      out.append(RudiTree.removeJavaBrackets(comment.getText())).append("\n");
    }
    out.append("}\n");
  }

  @Override
  public Iterable<? extends RudiTree> getDtrs() {
    return rules;
  }

  public void visit(RudiVisitor v) {
    throw new UnsupportedOperationException("visit is special");
  };

  // ==== IMPLEMENTATION OF RTBLOCKNODE =====================================

  private Environment _localBindings, _parentBindings;

  public void setBindings(Environment parent, Environment local) {
    _parentBindings = parent; _localBindings = local;
  }

  public Environment getBindings() { return _localBindings; }

  public Environment getParentBindings() { return _parentBindings; }
}
