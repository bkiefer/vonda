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
import java.util.Iterator;
import java.util.List;
import java.util.Set;

//import org.antlr.v4.runtime.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dfki.mlt.rudimant.common.Position;
import de.dfki.mlt.rudimant.compiler.*;
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
  private List<RudiTree> rules;
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


  /** Now produce code for all rules and statements in a file */
  private void writeRuleList(Mem mem, Writer out)
      throws IOException {
    VisitorGeneration gv = new VisitorGeneration(out, mem, _th);
    mem.enterEnvironment(this);
    List<RTStatement> later = new ArrayList<>();
    // do all VarDefs *DEFINITIONS* on toplevel here, those are class attributes
    for(RudiTree r : rules) {
      StatFieldDef fd = null;
      if (r instanceof StatFieldDef) {
        // Only generates definition;
        fd = (StatFieldDef)r;
      } else if (r instanceof StatVarDef) {
        if (((StatVarDef)r).isDefinition) {
          fd = new StatFieldDef("public", (StatVarDef)r);
          r.fixFields(fd);
        }
      }
      if (fd != null) {
        if (fd.varDef.toAssign != null && fd.varDef.isDefinition) {
          gv.visit(fd); // save comment for assignment
        } else if (fd.varDef.isDefinition) {
          // TODO: confirm: if this is no definition, we do not want it here, right?
          gv.gen(fd);
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
          _th.saveCommentsForLater(r.getLocation().getEnd());
      } else if (r instanceof StatVarDef) {
        StatVarDef vd = (StatVarDef)r;
        if (vd.toAssign == null) continue;
        vd.isDefinition = false;
        gv.gen(vd);
      } else if (r instanceof StatFieldDef) {
        StatVarDef vd = ((StatFieldDef)r).varDef;
        if (vd.toAssign == null) continue;
        vd.isDefinition = false;
        gv.gen(vd);
      } else if (r instanceof StatGrammarRule || r instanceof Include) {
        // rules and includes are called as functions and may return a non-zero
        // value. If the value is 1
        if (r instanceof StatGrammarRule){
          out.append("res = ");
          out.append(((StatGrammarRule)r).label).append("();");
          later.add((StatGrammarRule)r);
          // move all appropriate comments to a laterComments list
          _th.saveCommentsForLater(r.getLocation().getEnd());
          out.append(" if (res != 0)");
        } else {
          Include imp = (Include)r;
          _th.checkComments(r.getLocation().getBegin(), out);
          out.append("res = ");
          // use fully qualified name
          String rootpkg = getPackageName(mem.getTopLevelPackageSpec());
          String pkg = getPackageName(mem.getPackageSpec());
          out.append("new ");
          if (! rootpkg.isEmpty()) out.append(rootpkg).append('.');
          if (! pkg.isEmpty()) out.append(pkg).append('.');
          out.append(getQualifiedName(imp.path, imp.name)).append("(");
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
          out.append(").process();");
          out.append(" if (res < 0)");
        }
        out.append(" return (res - 1);\n");
      } else if (r instanceof RTStatement) {
        gv.gen(r);
      }
    }
    out.append(PROCESS_SUFFIX);

    // replace the comments list by the laterComments list
    _th.pourBackSavedComments();
    // now, add everything that we did not want in the process method
    for(RTStatement t : later){
      gv.visit(t);
    }
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

    out.append("import java.util.*;\n\n" +
        "import de.dfki.mlt.rudimant.agent.DialogueAct;\n" +
        "import de.dfki.lt.hfc.db.rdfProxy.*;\n" +
        "import de.dfki.lt.hfc.types.*;\n");
    // Let's import our supersuper class, if we're the top-level class
    if (! mem.isNotToplevelClass()) {
      out.append("import ").append(mem.getWrapperClass()).append(";\n");
    }

    // handle the imports from the .rudi file
    for(RudiTree r : rules) {
      // TODO: MAYBE NOT NECESSARY WHEN USING QUALIFIED NAMES IN PROCESS ??
      if (r instanceof Import) {
        Import i = (Import)r;
        if (i.path.size() > 0) {
          out.append("import ");
          if (i.staticImport) {
            out.append("static ");
          }
          out.append(i.path.get(0));
          for (int j = 1; j < i.path.size(); ++j) {
            out.append('.').append(i.path.get(j));
          }
          out.append(";\n");
        }
      }
    }
    /* done by neededclasses
     * else {
      // import the top level class
      out.append("import ");
      if (! rootpkg.isEmpty()) out.append(rootpkg).append('.');
      out.append(mem.getTopLevelClass()).append(";\n");
    }
    */
    // import the included classes
    for(RudiTree r : rules) {
      // TODO: MAYBE NOT NECESSARY WHEN USING QUALIFIED NAMES IN PROCESS ??
      if (r instanceof Include) {
        Include i = (Include)r;
        if (i.path.length > 0) {
          out.append("import ");
          if (! rootpkg.isEmpty()) out.append(rootpkg).append('.');
          if (! pkg.isEmpty()) out.append(pkg).append('.');
          out.append(getQualifiedName(i.path, i.name)).append(";\n");
        }
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

    // Now, print all initial comments (preceding the first element) before the
    // class starts, for, e.g., imports
    Position firstPos = rules.isEmpty()
        ? new Position(0,0,Integer.MAX_VALUE,"")
            : rules.get(0).getLocation().getBegin();
    _th.initialComments(firstPos, out);

    // maybe we need to import the class that imported us to use its variables
    out.append("\n\npublic class ").append(mem.getClassName());
    // check if this should extend the wrapper class
    if (! mem.isNotToplevelClass()) {
      out.append(" extends ").append(mem.getWrapperClass());
    }
    out.append("{\n");

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
    out.append("\n}\n");

    // ************************************************************
    // generate the main processing method that will call all rules and imports
    // declared in this file
    // ************************************************************
    try {
      writeRuleList(mem, out);
    } catch (WriterException wex) {
      throw (IOException)wex.getCause();
    }
    // ************************************************************
    // at the very end of the file, there might still be unprinted comments
    // ************************************************************
    _th.checkComments(new Position(0, 0, Integer.MAX_VALUE, ""), out);
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
