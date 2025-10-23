/*
 * The Creative Commons CC-BY-NC 4.0 License
 *
 * http://creativecommons.org/licenses/by-nc/4.0/legalcode
 *
 * Creative Commons (CC) by DFKI GmbH
 *  - Bernd Kiefer <kiefer@dfki.de>
 *  - Anna Welker <anna.welker@dfki.de>
 *  - Christophe Biwer <christophe.biwer@dfki.de>
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE.
 */

package de.dfki.mlt.rudimant.compiler.tree;

import static de.dfki.mlt.rudimant.compiler.GenerationConstants.*;
import static de.dfki.mlt.rudimant.compiler.Utils.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

//import org.antlr.v4.runtime.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dfki.mlt.rudimant.compiler.ClassEnv;
import de.dfki.mlt.rudimant.compiler.Environment;
import de.dfki.mlt.rudimant.compiler.Mem;
import de.dfki.mlt.rudimant.compiler.RudimantCompiler;
import de.dfki.mlt.rudimant.compiler.Token;
import de.dfki.mlt.rudimant.compiler.TokenHandler;
import de.dfki.mlt.rudimant.compiler.WriterException;
import de.dfki.mlt.rudimant.compiler.io.BisonParser;

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
  private TokenHandler _th;

  // **********************************************************************
  // static methods
  // **********************************************************************

  private static GrammarFile parseInput(final String realName, InputStream in,
                                        Mem mem)
      throws IOException {
    return BisonParser.parse(realName, in, mem);
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
    GrammarFile gf = parseInput(inputRealName, in, rudi.getMem());
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
  public GrammarFile(List<RudiTree> rules, TokenHandler th) {
    this.rules = rules;
    this._th = th;
  }

  // As expected, this works equally well.
  private void startTypeInference(RudimantCompiler rudi, boolean errorsFatal) {
    Mem mem = rudi.getMem();
    VisitorType ttv = new VisitorType(mem, errorsFatal, _th);

    for (RudiTree t : rules) {
      if (t instanceof Include) {
        Include node = (Include)t;
        try {
          rudi.processInclude(node.name, node.path, node.location);
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

  /** Create an object that represents a rule file */
  private void createIncludedRuleObject(Mem mem, Writer out, Include inc)
      throws IOException {
    String rootpkg = getPackageName(mem.getTopLevelPackageSpec());
    String pkg = getPackageName(mem.getPackageSpec());
    out.append("new ");
    if (! rootpkg.isEmpty()) out.append(rootpkg).append('.');
    if (! pkg.isEmpty()) out.append(pkg).append('.');
    out.append(getQualifiedName(inc.path, inc.name)).append("(");
    Set<ClassEnv> ncs = mem.getNeededClasses();
    if (ncs != null) {
      boolean notfirst = false;
      for (ClassEnv clz : ncs) {
        String c = clz.getName();
        if (c.equals(mem.getClassName())) {
          c = "this";
        }
        if (notfirst) out.append(", ");
        else notfirst = true;
        out.append(lowerCaseFirst(c));
      }
    }
    out.append(")");
  }

  /** Now produce code for all rules and statements in a file
   *
   *  Do not use the visit() method directly, use gen()!!!
   */
  private void writeRuleList(Mem mem, Writer out, boolean persistentVars)
      throws IOException {
    VisitorGeneration gv = new VisitorGeneration(out, mem);
    mem.enterEnvironment(this);

    // All things that have to land on the toplevel of the class are treated
    // now
    //
    // do all VarDefs *DEFINITIONS* on toplevel here, those are class attributes
    // Method definitions have to move anyway, so this does not hurt more
    for(RudiTree r : rules) {
      if (r instanceof StatFieldDef) {
        // Only generates definition;
        gv.gen(r);
      } else if (r instanceof StatVarDef && ((StatVarDef)r).isDefinition) {
        StatFieldDef fd = r.fixFields(new StatFieldDef("public", (StatVarDef)r));
        fd.comments = Collections.emptyList();
          gv.gen(fd);
      }
      // do all method defs in advance, could also be appended, does not matter
      if (r instanceof StatMethodDeclaration) {
        gv.gen(r);
      }
    }

    // create the process method
    out.append(PROCESS_PREFIX);
    // use all methods created from rules in this file
    for(RudiTree r : rules) {
      if (r instanceof StatVarDef) {
        StatVarDef vd = (StatVarDef)r;
        if (vd.toAssign == null) continue;
        vd.isDefinition = false;
        gv.gen(vd);
      } else if (r instanceof StatFieldDef) {
        StatVarDef vd = ((StatFieldDef)r).varDef;
        if (vd.toAssign == null) continue;
        vd.isDefinition = false;
        gv.gen(vd);
      } else if (r instanceof Include) {
        // includes are called as functions and may return a non-zero
        // If the value is 1, skip all the subsequent rules
        Include inc = (Include)r;
        out.append("res = ");
        if (persistentVars) {
          out.append(lowerCaseFirst(inc.name));
        } else {
          createIncludedRuleObject(mem, out, (Include)r);
        }
        out.append(".process();");
        out.append(" if (res < 0) return res;");
      } else if ((r instanceof StatGrammarRule )
          || (r instanceof RTStatement
              && !(r instanceof StatMethodDeclaration))) {
        gv.gen(r);
      }
    }
    out.append(PROCESS_SUFFIX);
    mem.leaveEnvironment(this);
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
    // standard imports
    out.append("import java.util.*;\n\n" +
        "import de.dfki.mlt.rudimant.agent.nlp.DialogueAct;\n" +
        "import de.dfki.lt.hfc.db.rdfProxy.*;\n" +
        "import de.dfki.lt.hfc.types.*;\n");
    // Let's import our supersuper class, if we're the top-level class
    if (! mem.isNotToplevelClass()) {
      out.append("import ").append(mem.getWrapperClass()).append(";\n");
    }

    /** add the comment tokens to the right RudiTree items */
    _th.attachComments(this);

    List<Include> includes = new ArrayList<>();
    // import the included classes
    for(RudiTree r : rules) {
      // these have to appear first, the syntax allows nothing else
      if (r instanceof Import) {
        // print all comments before this point (there's nothing before this!
        ((Import)r).gen(out);
      } else if (r instanceof Include) {
        Include i = (Include)r;
        includes.add(i);
        // TODO: not necessary when using qualified names in creating included
        // classes/rule files in process
        /*
        // no import needed for classes in the same package (path length zero)
        if (i.path.length > 0) {
          out.append("import ");
          if (! rootpkg.isEmpty()) out.append(rootpkg).append('.');
          if (! pkg.isEmpty()) out.append(pkg).append('.');
          out.append(getQualifiedName(i.path, i.name)).append(";")
             .append(System.lineSeparator());
        }
        */
      }
    }

    String thisClassName = mem.getClassName();
    Set<ClassEnv> neededClasses = mem.getNeededClasses();
    for (ClassEnv clz : neededClasses) {
      String className = clz.getName();
      if(className.equals(thisClassName)) continue;
      out.append("import ")
         .append(rootpkg).append('.')
         .append(getQualifiedName(clz.packageSpec(), clz.getName()))
         .append(";\n");
    }

    // maybe we need to import the class that imported us to use its variables
    out.append("\n\npublic class ").append(mem.getClassName());
    // check if this should extend the wrapper class
    if (! mem.isNotToplevelClass()) {
      /* No wrapper class anymore? Decision: it's optional, but possible. If
       * there is no wrapper class, getWrapperClass will return Agent
       */
      out.append(" extends ").append(mem.getWrapperClass());
      //.append(AGENT_CLASS).append(' ');
    }
    out.append("{").append(System.lineSeparator());

    List<String> fields = new ArrayList<>();
    // ************************************************************
    // create fields for all classes we need instances of
    // ************************************************************
    for (ClassEnv clz : neededClasses) {
      String className = clz.getName();
      if(className.equals(thisClassName)) continue;
      String name = lowerCaseFirst(className);
      fields.add(name);
      out.append("private final ");
      out.append(className).append(' ').append(name).append(";\n");
    }

    // ************************************************************
    // persistent var mode: create fields for all included rule file objects
    // ************************************************************
    if (rudi.persistentVars()) {
      for(Include i : includes) {
        out.append("private final ");
        if (! rootpkg.isEmpty()) out.append(rootpkg).append('.');
        if (! pkg.isEmpty()) out.append(pkg).append('.');
        out.append(getQualifiedName(i.path, i.name)).append(" ")
           .append(lowerCaseFirst(i.name))
           .append(";").append(System.lineSeparator());
      }
    }

    // ************************************************************
    // add a constructor for this class
    // ************************************************************

    // Add arguments for all needed class instances
    boolean notfirst = false;
    out.append("\n\npublic ").append(thisClassName).append('(');
    Iterator<String> names = fields.iterator();
    for (ClassEnv clz : neededClasses) {
      String className = clz.getName();
      if(className.equals(thisClassName)) continue;
      if (notfirst)
        out.append(", ");
      out.append(clz.getName()).append(' ').append(names.next());
      notfirst = true;
    }
    out.append(") {\n super();\n");
    for (String name : fields) {
      out.append("this.").append(name).append(" = ").append(name).append(";\n");
    }
    // create objects for all included rule files
    if (rudi.persistentVars()) {
      for(Include i : includes) {
        out.append(lowerCaseFirst(i.name)).append(" = ");
        createIncludedRuleObject(mem, out, i);
        out.append(";").append(System.lineSeparator());
      }
    }
    out.append("\n}\n");

    // ************************************************************
    // generate the main processing method that will call all rules and imports
    // declared in this file
    // ************************************************************
    try {
      writeRuleList(mem, out, rudi.persistentVars());
    } catch (WriterException wex) {
      throw (IOException)wex.getCause();
    }

    // ************************************************************
    // at the very end of the file, there might still be unprinted comments
    // ************************************************************
    for (Token t : this.comments) {
      out.append(t.content());
    }

    out.append("}\n");
  }

  @Override
  public Iterable<? extends RudiTree> getDtrs() {
    return rules;
  }

  @Override
  public void visit(RudiVisitor v) {
    throw new UnsupportedOperationException("visit is special");
  };

  // ==== IMPLEMENTATION OF RTBLOCKNODE =====================================

  private Environment _localBindings;

  @Override
  public Environment getParentBindings() { return _localBindings.getParent(); }

  @Override
  public Environment enterEnvironment(Environment parent) {
    return _localBindings != null ? _localBindings
        : (_localBindings = Environment.getEnvironment(parent));
  }
}
