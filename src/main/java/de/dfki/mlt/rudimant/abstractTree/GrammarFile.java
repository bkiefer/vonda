/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

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
  String classname;

  public GrammarFile(List<RudiTree> rules) {
    this.rules = rules;
  }

  public void setClassName(String name) {
    this.classname = name;
  }

  public void startTypeInference(RudimantCompiler rudi) {
    VTestTypeVisitor ttv = new VTestTypeVisitor(rudi);
    Mem mem = rudi.getMem();
    mem.enterEnvironment();
    String oldname = mem.getClassName();
    String oldrule = mem.getCurrentRule();
    String oldTrule = mem.getCurrentTopRule();
    if(rudi.getClassName() != null){
      setClassName(rudi.getClassName());
      mem.enterClass(rudi.getClassName());
    }
    List<RudiTree> nonDefs = new ArrayList<>(rules.size());
    // learn about all definitions before visiting the other statements!!
    for (RudiTree t : rules) {
      if(t instanceof StatVarDef
         || (t instanceof StatMethodDeclaration
             && ((StatMethodDeclaration)t).block == null)
         || (t instanceof ExpAssignment && ((ExpAssignment) t).declaration)){
        t.visit(ttv);
      } else {
        nonDefs.add(t);
      }
    }
    for (RudiTree t : nonDefs) {
      if(t instanceof GrammarRule){
        mem.ontop = true;
      }
      if (t instanceof Import) {
        Import node = (Import)t;
        String conargs = "";
        mem.addImport(node.name, conargs);
        rudi.processImport(node.content);
      }
      t.visit(ttv);
      // if t will later on be put into a stub function in GenerationVisitor,
      // we should add its predicted name to the mem so the method is called in
      // the correct position between the imports
      // TODO: CHECK IF THIS IS REALLY STILL NECESSARY, esp. because of the
      // number appended to the function name.
      if(!(t instanceof StatAbstractBlock
           || t instanceof Import
           || t instanceof StatMethodDeclaration
           || t instanceof GrammarRule)){
        String fname = rudi.getClassName() + rules.indexOf(t);
        // add the function to our mem as if it was a rule, so it is called in
        // the process function
        mem.ontop = true;
        mem.addRule(fname);
      }
    }
    if (mem.getToplevelCalls(rudi.getClassName()) != null) {
      for (String s : mem.getToplevelCalls(rudi.getClassName())) {
        if(s.contains("(")){
          s = s.substring(s.indexOf("=") + 6, s.indexOf("("));
        }
        if (mem.getNeededClasses(s) != null) {
          for (String n : mem.getNeededClasses(s)) {
            mem.needsClass(rudi.getClassName(), n);
          }
        }
      }
    }
    // do not leave the environment, we are still in it! (but remember to leave
    // it once the generation is done)
    mem.leaveClass(oldname, oldrule, oldTrule);
  }


  public void writeRuleList(RudimantCompiler out, VGenerationVisitor gv){
    Mem mem = out.getMem();
    List<RudiTree> later = new ArrayList<>();
    // do all assignments on toplevel here, those are class attributes
    for(RudiTree r : rules){
      if(r instanceof ExpAssignment){
        if(((ExpAssignment)r).declaration){
          out.append(Type.convertRdfType(((ExpAssignment)r).type) + " "
                  + ((ExpAssignment)r).left.fullexp + ";\n");
          ((ExpAssignment)r).declaration = false;
        }
      }
    }
    // create the process method
    out.append("\tpublic void process(");
    out.append("){\n");
    // initialize me according to the super class init
    out.append("// this.init();\n");
    // use all methods created from rules in this file
    for(RudiTree r : rules){
      // rules, method declarations and imports are a special case
      if (r instanceof GrammarRule){
        out.append(((GrammarRule)r).label + "();\n");
        later.add(r);
      } else if (r instanceof StatMethodDeclaration){
        later.add(r);
      } else if (r instanceof Import){
        String impor = ((Import)r).name;
        String importn = impor.substring(0, 1).toUpperCase() + impor.substring(1);
        out.append("new " + importn + "(");
        List<String> ncs = mem.getNeededClasses(impor);
        if (ncs != null) {
          int i = 0;
          for (String c : ncs) {
            if (c.equals(out.getClassName())
                    || (c.substring(0, 1).toUpperCase()
                            + c.substring(1)).equals(out.getClassName())) {
              c = "this";
            }
            if (i == 0) {
              out.append(c.substring(0, 1).toLowerCase()
                      + c.substring(1));
            } else {
              out.append(", " + c.substring(0, 1).toLowerCase()
                      + c.substring(1));
            }
            i++;
          }
        }
        out.append(").process();\n");
      } else {
        gv.visitNode(r);
        if(!(r instanceof RTStatement)){
          out.append(";\n");
        }
      }
    }
    out.append("}\n");
    // now, add everything that we did not want in the process method
    for(RudiTree t : later){
      gv.visitNode(t);
    }
  }


  public void startGeneration(RudimantCompiler out, VGenerationVisitor gv) {
    Mem mem = out.getMem();
    String oldname = mem.getClassName();
    String oldrule = mem.getCurrentRule();
    String oldTrule = mem.getCurrentTopRule();
    mem.enterClass(out.getClassName());

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
    // Let's import our supersuper class
    out.append("import ");
    if (out.getParent() != null) {
      out.append(pkg + out.getParent().getClassName());
    } else {
      out.append(out.getWrapperClass());
    }
    out.append(";\n");

    // we also need all imports that might be hidden in /*@ in the rudi
    // so, look for it in the comment before the first element we've got
    // TODO: THIS SHOULD BE HANDLED EXPLICITELY, BECAUSE IT'S A COMPLETE
    // EXCEPTION: COMMENTS BEFORE ANY CODE, OR AM I WRONG?
    rules.get(0).printImportifJava(gv);
    // maybe we need to import the class that imported us to use its variables
    String ext = "";
    if(classname.toLowerCase()
            .equals(mem.getToplevelInstance().toLowerCase())){
      ext = " extends " + out.getWrapperClass();
    }
    out.append("public class " + classname
            + ext
            + "{\n");

    // create variable fields for all those classes whose concrete instances we
    // will need
    for (String n : mem.getNeededClasses(classname)) {
      if(n.equals(out.getClassName())) continue;
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
    for (String n : mem.getNeededClasses(out.getClassName())) {
      if(n.equals(out.getClassName())) continue;
      String name = n.substring(0, 1).toLowerCase() + n.substring(1);
      if (i == 0) {
        args += n.substring(0, 1).toUpperCase() + n.substring(1) + " "
                + name;
      } else {
        args += ", " + n.substring(0, 1).toUpperCase() + n.substring(1) + " "
                + name;
      }
      declare += "this." + name + " = " + name + ";\n";
      i++;
    }
    out.append("public " + out.getClassName() + "(" + args + ") {\n"
            + "super(" + conargs + ");\n" + declare + "}\n");

    // finally, the main processing method that will call all rules and imports
    // declared in this file
    writeRuleList(out, gv);

    out.append("}\n");
    mem.leaveClass(oldname, oldrule, oldTrule);
    mem.leaveEnvironment();
  }

  @Override
  public Iterable<? extends RudiTree> getDtrs() {
    return rules;
  }
}
