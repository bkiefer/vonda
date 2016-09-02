/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

import de.dfki.mlt.rudi.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.thrift.TException;

/**
 *
 * @author Anna Welker, anna.welker@dfki.de
 */
public class GenerationVisitor implements RudiVisitor {

  private RudimantCompiler out;
  private Mem mem;

  public GenerationVisitor(RudimantCompiler out) {
    this.out = out;
    this.mem = out.getMem();
  }

  @Override
  public void visitNode(RudiTree  node) {
    node.visit(this);
  }

  @Override
  public void visitNode(ExpAbstractWrapper  node) {
    node.commentbefore.visit(this);
    node.exp.visit(this);
    node.commentafter.visit(this);
  }

  @Override
  public void visitNode(ExpArithmetic  node) {
    String ret = "";
    if (node.minus) {
      out.append("-");
    }
    if (node.right != null) {
      out.append("(");
      node.left.visit(this);
      out.append(node.operator);
      node.right.visit(this);
      out.append(")");
      return;
    }
    node.left.visit(this);
  }

  @Override
  public void visitNode(ExpAssignment  node) {
    //System.out.println(((UVariable) left).toString());
    /*if (out.checkTypes()) {
      if (node.actualType != null) {
        testTypeDecl(node.actualType, out);
      } else {
        testType(out);
      }
    }*/
    if (node.declaration) {
      mem.addElement(((UVariable) node.left).toString(), node.actualType, node.position);
      out.append(node.actualType + " " + ((UVariable) node.left).toString() + " = ");
      node.right.visit(this);
      return;
    }
    out.append(((UVariable) node.left).toString() + " = ");
    node.right.visit(this);
  }

  @Override
  public void visitNode(ExpBoolean  node) {
    if (node.type == null) {
      node.getType();
    }
    String ret = "";
    if (node.not) {
      out.append("!");
    }
    if (node.isSubsumed) {
      out.append("isSubsumed(");
      node.left.visit(this);
      out.append(", ");
      node.right.visit(this);
      out.append(")");
    } else if (node.doesSubsume) {
      out.append("isSubsumed(");
      node.right.visit(this);
      out.append(", ");
      node.left.visit(this);
      out.append(")");
    }
    if (node.right != null) {
      out.append("(");
      node.left.visit(this);
      out.append(node.operator);
      node.right.visit(this);
      out.append(")");
      //out.context.doLog(
      //        "\"" + ret.replace('"', ' ') +  " _ resulted to \" + " + ret);
      return;
    }
    node.left.visit(this);
    //out.context.doLog("\"" + ret.replace('"', ' ') +  " resulted to \" + ("
    //        + ret + ")");
  }

  @Override
  public void visitNode(ExpDialogueAct  node) {
    out.append("new DialogueAct(\"" + node.litGraph + "(");
    String[] parameters = node.rest.split(",");
    // the first argument will never need to be more than a String
    out.append(parameters[0]);
    for (int i = 1; i < parameters.length; i++) {
      String[] parts = parameters[i].split("=");
      if (parts.length == 1) {
        // then this argument is a variable that is passed and should be found somewhere
        if (mem.existsVariable(parts[0])) {
          out.append(", \" + " + parts[0] + " + \"");
        } else {
          // TODO: or throw an error here?
          out.append(", " + parts[0]);
        }
      } else // this argument is of kind x = y, look if y is a variable we know
      if (mem.existsVariable(parts[1])) {
        out.append(", " + parts[0] + " = \" + " + parts[1] + " + \"");
      } else {
        out.append(", " + parts[0] + " = " + parts[1]);
      }
    }
    out.append(")\")");
  }

  @Override
  public void visitNode(ExpIf  node) {
    node.boolexp.visit(this);
    out.append(" ? ");
    node.thenexp.visit(this);
    out.append(" : ");
    node.elseexp.visit(this);
  }

  @Override
  public void visitNode(ExpLambda  node) {
    out.append(node.exp);
  }

  @Override
  public void visitNode(GrammarFile  node) {
    //let the return guy do its work
    //returnManaging();
    //boolean foundClassName = false;
    mem.addAndEnterNewEnvironment(mem.getCurrentDepth() + 1);
    out.append("public class " + node.classname + "{\n"
            + "  public void process(){");
    for (RudiTree r : node.rules) {
      // Don't forget to insert text after class name as specified in context
      // Edit: no one wants to use this anyway
      /*if (r instanceof ACommentBlock && foundClassName == false) {
        if (((ACommentBlock) r).containsClassName()) {
          ((ACommentBlock) r).setPrintClassName();
          foundClassName = true;
        }
      }*/
      r.visit(this);
    }
    out.append("  }\n}");
    out.flush();
    mem.leaveEnvironment();
  }

  @Override
  public void visitNode(GrammarRule node) {
    String returnType = "void";
    if (ReturnManagement.shouldAddReturnto(node.label)) {
      returnType = "String";
    }
    out = new BufferedWriter(new OutputStreamWriter(
            new FileOutputStream(out.getOutputDirectory() + node.classname + ".java")));
    mem.addAndEnterNewEnvironment(mem.getCurrentDepth() + 1);
    out.append("public class " + node.classname + "{\n"
            + "  public " + returnType + "process(){");
    node.comment.visit(this);
    node.ifstat.visit(this);
    out.append("\n\t return;\n  }\n}");
    mem.leaveEnvironment();
  }

  @Override
  public void visitNode(StatAbstractBlock  node) {
    String stats = "";
    if (node.braces) {
      // when entering a statement block, we need to create a new local environment
      node.environmentPosition = mem.addAndEnterNewEnvironment(mem.getCurrentDepth() + 1);
      out.append("{");
    }
    for (RudiTree stat : node.statblock) {
      if (stat instanceof RTExpression) {
        stat.visit(this);
        out.append(";\n");
        break;
      }
      stat.visit(this);
    }
    if (node.braces) {
      out.append("}");
      mem.leaveEnvironment();
    }
  }

  @Override
  public void visitNode(StatDoWhile  node) {
    out.append("do");
    node.statblock.visit(this);
    out.append("while (");
    node.condition.visit(this);
    out.append(");");
  }

  @Override
  public void visitNode(StatFor1  node) {
    // the assignment will add the variable to the memory!!
    out.append("for ( ");
    node.assignment.visit(this);
    out.append("; ");
    node.condition.visit(this);
    out.append(";");
    if (node.arithmetic != null) {
      node.arithmetic.visit(this);
    }
    out.append(");");
    node.statblock.visit(this);
  }

  @Override
  public void visitNode(StatFor2  node) {
    // TODO: or should we check here that the type of the variable in assignment
    // is the type the iterable in exp returns? How?
    if (node.varType == null) {
      node.varType = ((RTExpression) node.exp).getType();
    }
    mem.addElement(node.var.toString(), node.varType, node.position);
    out.append("for (" + node.varType + " ");
    node.var.visit(this);
    out.append(": ");
    node.exp.visit(this);
    out.append(") ");
    node.statblock.visit(this);
  }

  @Override
  public void visitNode(StatFor3  node) {
    out.append("for (Object[] o : ");
    node.exp.visit(this);
    out.append(") {");
    int count = 0;
    for (String s : node.variables) {
      mem.addElement(s, "Object", node.position);
      out.append("\nObject " + s + " = o[" + count++ + "]");
    }
    node.statblock.visit(this);
    out.append("}");
  }

  @Override
  public void visitNode(StatFunDef  node) {
    mem.addFunction(node.funcname, node.type, node.parameterTypes, node.position);
  }

  @Override
  public void visitNode(StatIf  node) {
    if (node.statblockElse != null) {
      out.append("if (");
      node.condition.visit(this);
      out.append(") ");
      node.statblockIf.visit(this);
      out.append("else");
      node.statblockElse.visit(this);
    } else {
      out.append("if (");
      node.condition.visit(this);
      out.append(") ");
      node.statblockIf.visit(this);
    }
  }

  @Override
  public void visitNode(StatImport  node) {
    System.out.println("Processing import " + node.text);
    mem.addAndEnterNewEnvironment(mem.getCurrentDepth() + 1);
    out.append(node.text + ".process()");
    out.processFile(new File(out.getInputDirectory() + node.text + ".rudi"));
    mem.leaveEnvironment();
  }

  @Override
  public void visitNode(StatMethodDeclaration  node) {
    mem.addAndEnterNewEnvironment(mem.getCurrentDepth());
    String ret = node.visibility + " " + node.return_type + " " + node.name + "(";
    if (!node.parameters.isEmpty()) {
      for (int i = 0; i < node.parameters.size(); i++) {
        if (i == 1) {
          ret += node.partypes.get(i) + " " + node.parameters.get(i);
        } else {
          ret += ", " + node.partypes.get(i) + " " + node.parameters.get(i);
        }
        // add parameters to environment
        mem.addElement(node.parameters.get(i), node.partypes.get(i), node.position);
      }
    }
    ret += ")";
    out.append(ret + "\n");
    node.block.visit(this);
    mem.leaveEnvironment();
  }

  @Override
  public void visitNode(StatPropose  node) {
    out.append("propose(");
    node.arg.visit(this);
    out.append(", new Proposal() {public void run()\n");
    node.block.visit(this);
    out.append("});");
  }

  @Override
  public void visitNode(StatReturn  node) {
    if (node.toRet == null) {
      out.append("return;\n");
    }
    out.append("return ");
    node.toRet.visit(this);
    out.append(";\n");
  }

  @Override
  public void visitNode(StatSetOperation  node) {
    node.left.visit(this);
    if (node.add) {
      out.append(".add(");
    } else {
      out.append(".remove(");
    }
    node.right.visit(this);
    out.append(");");
  }

  @Override
  public void visitNode(StatTimeout  node) {
    if (node.statblock == null) {
      out.append("newTimeout(" + node.name + "," + node.time + ");\n");
    }
    out.append("MyTimer t = newSpecialTimeout(" + node.name + "," + node.time + ");"
            + "t.timer = new Timer(timeToFire, new ActionListener(){\n"
            + "@Override\n public void actionPerformed(ActionEvent e)" + node.statblock + "});"
            + "\n" + "t.started = System.currentTimeMillis();\n"
            + " t.timer.start();\n");
  }

  @Override
  public void visitNode(StatVarDef  node) {
    mem.addElement(node.variable, node.type, node.position);
  }

  @Override
  public void visitNode(StatWhile  node) {
    out.append("while (");
    node.condition.visit(this);
    out.append(")");
    node.statblock.visit(this);
  }

  @Override
  public void visitNode(UCharacter  node) {
    out.append("\'" + node.content + "\'");
  }

  @Override
  public void visitNode(UComment  node) {
    out.append(node.comment);
  }

  @Override
  public void visitNode(UCommentBlock  node) {
    for (UComment c : node.comments) {
      c.visit(this);
      out.append("\n");
    }
  }

  @Override
  public void visitNode(UFieldAccess  node) {
    if (!node.asked) {
      try {
        node.type = node.askChristophe(out.getMem());
      } catch (TException ex) {
        Logger.getLogger(UFieldAccess.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    // TODO: tell me how the client is named!!!
    out.append(node.representation.get(0));
    for (int i = 1; i < node.representation.size(); i++) {
      out.append(".getValue(" + node.representation.get(i) + ", client)");
    }
  }

  @Override
  public void visitNode(UFuncCall  node) {
    out.append(node.representation + "(");
    for (int i = 0; i < node.exps.size(); i++) {
      node.exps.get(i).visit(this);
      if (i != node.exps.size() - 1) {
        out.append(", ");
      }
    }
    out.append(")");
  }

  @Override
  public void visitNode(UNull  node) {
    out.append("null");
  }

  @Override
  public void visitNode(UNumber  node) {
    out.append(node.value);
  }

  @Override
  public void visitNode(UString  node) {
    out.append(node.content);
  }

  @Override
  public void visitNode(UVariable  node) {
    if (!node.origin.equals(mem.getVariableOrigin(node.representation))) {
      node.origin += ".";
      out.append(node.origin + node.representation);
      return;
    }
    out.append(node.representation);
  }

  @Override
  public void visitNode(UWildcard  node) {
    out.append("this.wildcard");   // wildcard is a local variable in resulting class
  }

  @Override
  public void visitNode(UnaryBoolean  node) {
    out.append(node.content);
  }
}
