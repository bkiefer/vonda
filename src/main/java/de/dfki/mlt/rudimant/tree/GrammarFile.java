/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.tree;

import static de.dfki.mlt.rudimant.Utils.*;

import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import de.dfki.mlt.rudimant.Mem;
import de.dfki.mlt.rudimant.RudimantCompiler;
import de.dfki.mlt.rudimant.Type;

/**
 * class that represents a top-level file that was handed over to GrammarMain (=
 * the root of the whole tree)
 *
 * @author Anna Welker
 */
public class GrammarFile extends RudiTree {

  // imports* (comment grammar_rule | method_declaration | statement )* comment
  List<RudiTree> rules;
  static Writer out;

  public GrammarFile(List<RudiTree> rules) {
    this.rules = rules;
  }

  public boolean containsDefinition(RudiTree t) {
    if (t instanceof StatVarDef
        || (t instanceof StatMethodDeclaration
            && ((StatMethodDeclaration)t).block == null)) return true;
    if (t instanceof StatExpression) {
      RTExpression ex = ((StatExpression) t).expression;
      return (ex instanceof ExpAssignment) && ((ExpAssignment) ex).declaration;
    }
    return false;
  }

  private void visitTypeInference(RudiTree t, VTestTypeVisitor ttv) {
    if (t instanceof RTStatement) {
      ((RTStatement)t).visit(ttv);
    } else if (t instanceof RTExpression) {
      ((RTExpression)t).visit(ttv);
    }
  }

  public void startTypeInference(RudimantCompiler rudi) {
    VTestTypeVisitor ttv = new VTestTypeVisitor(rudi);
    Mem mem = rudi.getMem();
    List<RudiTree> nonDefs = new ArrayList<>(rules.size());
    // learn about all definitions before visiting the other statements!!
    // TODO: WHY? IF WE REQUIRE THE DEFINITION ALWAYS PRECEDING THE USE, THEN
    // WHY IS THIS NECESSARY?
    for (RudiTree t : rules) {
      if(containsDefinition(t)) {
        visitTypeInference(t, ttv);
      } else {
        nonDefs.add(t);
      }
    }
    for (RudiTree t : nonDefs) {
      if (t instanceof Import) {
        Import node = (Import)t;
        String conargs = "";
        mem.addImport(node.name, conargs);
        rudi.processImport(node.content);
      }
      visitTypeInference(t, ttv);
    }
  }


  public void writeRuleList(RudimantCompiler out, VGenerationVisitor gv){
    Mem mem = out.getMem();
    List<RTStatement> later = new ArrayList<>();
    // do all assignments on toplevel here, those are class attributes
    for(RudiTree r : rules){
      if(r instanceof StatExpression &&
          ((StatExpression)r).expression instanceof ExpAssignment){
        ExpAssignment ass = (ExpAssignment)((StatExpression)r).expression;
        if(ass.declaration) {
          out.append(Type.convertRdfType(ass.type)).append(" ")
             .append(ass.left.fullexp).append(";\n");
          ass.declaration = false;
        }
      }
    }
    // create the process method
    out.append("  public boolean process(){\n");
    // initialize me according to the super class init
    //out.append("// this.init();\n");
    // use all methods created from rules in this file
    for(RudiTree r : rules){
      // rules, method declarations and imports are a special case
      if (r instanceof StatGrammarRule){
        out.append(((StatGrammarRule)r).label + "();\n");
        later.add((StatGrammarRule)r);
      } else if (r instanceof StatMethodDeclaration){
        later.add((StatMethodDeclaration)r);
      } else if (r instanceof Import){
        out.append("if (new " + capitalize(((Import)r).name) + "(");
        Set<String> ncs = mem.getNeededClasses();
        if (ncs != null) {
          int i = 0;
          for (String c : ncs) {
            if (c.equals(mem.getClassName())
                || capitalize(c).equals(mem.getClassName())) {
              c = "this";
            }
            if (i != 0) out.append(", ");
            out.append(lowerCaseFirst(c));
            i++;
          }
        }
        out.append(").process()) return true;\n");
      } else {
        gv.visitNode((RTStatement)r);
      }
    }
    out.append("return false; \n}\n");
    // now, add everything that we did not want in the process method
    for(RTStatement t : later){
      gv.visitNode(t);
    }
  }


  public void startGeneration(RudimantCompiler out, VGenerationVisitor gv) {
    Mem mem = out.getMem();
    // tell the file in which package it lies
    String pkg = out.getPackageName();
    if (pkg == null) {
      pkg = "";
    } else {
      out.append("package " + pkg + ";\n");
      pkg += ".";
    }
    out.append("import java.util.*;\n\n");
    out.append("import de.dfki.mlt.rudimant.agent.DialogueAct;\n");
    out.append("import de.dfki.lt.hfc.db.rdfProxy.*;\n");
    out.append("import de.dfki.lt.hfc.types.*;\n");
    // Let's import our supersuper class, TODO: maybe obsolete except for the
    // wrapper class?
    if (out.getParent() == null) {
      out.append("import ");
      out.append(out.getWrapperClass());
      out.append(";\n");
    }

    // we also need all imports that might be hidden in /*@ in the rudi
    // so, look for it in the comment before the first element we've got
    // TODO: THIS SHOULD BE HANDLED EXPLICITELY, BECAUSE IT'S A COMPLETE
    // EXCEPTION: COMMENTS BEFORE ANY CODE, OR AM I WRONG?
    rules.get(0).printImportifJava(gv);
    // maybe we need to import the class that imported us to use its variables
    out.append("public class " + mem.getClassName());
    // check if this should extend the wrapper class
    if (out.getParent() == null) {
      out.append(" extends ").append(out.getWrapperClass());
    }
    out.append("{\n");

    // create variable fields for all those classes whose concrete instances we
    // will need
    for (String n : mem.getNeededClasses()) {
      if(n.equals(mem.getClassName())) continue;
      out.append("private final ");
      out.append(n.substring(0, 1).toUpperCase() + n.substring(1) + " "
              + n.substring(0, 1).toLowerCase() + n.substring(1));
      out.append(";\n");
    }
    // now, we should add a constructor, including constructor parameters if
    // specified in configs
    // also, to use them for imports, declare those parameters class attributes
    String conargs = "";
    String declare = "";
    String args = out.getConstructorArgs();
    if (null != args && !args.isEmpty()) {
      int i = 0;
      for (String a : args.split(",")) {
        if (i > 0) {
          conargs += ", ";
        }
        String s = a.trim().split(" ")[1];
        out.append("private final " + a + ";\n");
        declare += "this." + s + " = " + s + ";\n";
        conargs += a.trim().split(" ")[1];
        i++;
      }
    } else {
      args = "";
    }
    // get all those classes the toplevel rules need
    int i = 0;
    for (String n : mem.getNeededClasses()) {
      if(n.equals(mem.getClassName())) continue;
      String name = lowerCaseFirst(n);
      if (i != 0) args += ", ";
      args += capitalize(n) + " " + name;
      declare += "this." + name + " = " + name + ";\n";
      i++;
    }
    out.append("public " + mem.getClassName() + "(" + args + ") {\n"
        + "super(" + conargs + ");\n" + declare + "}\n");

    // finally, the main processing method that will call all rules and imports
    // declared in this file
    writeRuleList(out, gv);

    out.append("}\n");
  }

  @Override
  public Iterable<? extends RudiTree> getDtrs() {
    return rules;
  }

  @Override
  public String visitStringV(RTStringVisitor v) {
    throw new UnsupportedOperationException("not supported");
  }

  @Override
  public void visitVoidV(VGenerationVisitor v) {
    throw new UnsupportedOperationException("not supported");
  }

}
