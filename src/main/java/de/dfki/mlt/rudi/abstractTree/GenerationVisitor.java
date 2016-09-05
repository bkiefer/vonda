/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

import de.dfki.mlt.rudi.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * this visitor generates the java code
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
  public void visitNode(RudiTree node) {
    node.visit(this);
  }

  @Override
  public void visitNode(ExpAbstractWrapper node) {
    node.commentbefore.visit(this);
    node.exp.visit(this);
    node.commentafter.visit(this);
  }

  @Override
  public void visitNode(ExpArithmetic node) {
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
  public void visitNode(ExpAssignment node) {
    //System.out.println(((UVariable) left).toString());
    if (node.declaration) {
      out.append(node.actualType + " ");
    }
    // visit also the left side, it could be using another class's variable!
    System.out.println("Generating assignment");
    node.left.visit(this);
    out.append(" = ");
    node.right.visit(this);
    //out.append(";");
  }

  @Override
  public void visitNode(ExpBoolean node) {
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
  public void visitNode(ExpDialogueAct node) {
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
      {
        if (mem.existsVariable(parts[1])) {
          out.append(", " + parts[0] + " = \" + " + parts[1] + " + \"");
        } else {
          out.append(", " + parts[0] + " = " + parts[1]);
        }
      }
    }
    out.append(")\")");
  }

  @Override
  public void visitNode(ExpIf node) {
    node.boolexp.visit(this);
    out.append(" ? ");
    node.thenexp.visit(this);
    out.append(" : ");
    node.elseexp.visit(this);
  }

  @Override
  public void visitNode(ExpLambda node) {
    out.append(node.exp);
  }

  @Override
  public void visitNode(GrammarFile node) {
    //let the return guy do its work
    //returnManaging();
    //boolean foundClassName = false;
    //mem.enterNextEnvironment();
    out.append("public class " + node.classname + "{\n"
            + "\tprivate int returnTo = 0;\n");
    // initialize all return markers
    for (String k : out.rm.getMarkers()) {
      out.append("int return_" + k + " = " + out.rm.getMarker(k) + ";\n");
    }
    // initialize all class attributes before the main process method
    for (RudiTree r : node.rules) {
      if (r instanceof StatAbstractBlock) {
        for (RudiTree e : ((StatAbstractBlock) r).statblock) {
          if (e instanceof ExpAbstractWrapper && ((ExpAbstractWrapper) e).exp instanceof ExpAbstractWrapper
                  && ((ExpAbstractWrapper) ((ExpAbstractWrapper) e).exp).exp instanceof ExpAssignment) {
            // then it is a class attribute and we want it to be defined outside
            // of the process method
            if (((ExpAssignment) ((ExpAbstractWrapper) ((ExpAbstractWrapper) e).exp).exp).declaration) {
              ((ExpAbstractWrapper) ((ExpAbstractWrapper) e).exp).exp.visit(this);
              out.append(";");
            }
          }
        }
      }
    }
    out.append("\tpublic void process(){\n");
    // use all methods created from rules in this file
    for (String toplevel : mem.getTopLevelRules()) {
      out.append(toplevel + "();");
    }
    out.append("}\n");
    // Now, only get those statements that are not assignments of class attributes
    for (RudiTree r : node.rules) {
      if (r instanceof StatAbstractBlock) {
        for (RudiTree e : ((StatAbstractBlock) r).statblock) {
          if (e instanceof ExpAbstractWrapper && ((ExpAbstractWrapper) e).exp instanceof ExpAbstractWrapper
                  && ((ExpAbstractWrapper) ((ExpAbstractWrapper) e).exp).exp instanceof ExpAssignment) {
            if (((ExpAssignment) ((ExpAbstractWrapper) ((ExpAbstractWrapper) e).exp).exp).declaration) {
              continue;
            }
          }
          e.visit(this);

        }
      } else {
        r.visit(this);
      }
    }
    out.append("}\n");
    out.flush();
    // mem.leaveEnvironment();
    mem.goBackToBeginning();
  }

  @Override
  public void visitNode(GrammarRule node) {
    if (mem.isTopLevel(node.label)) {
      // this is a toplevel rule and will be converted to a method
      out.append("public void " + node.label + "() {\n");
      mem.enterNextEnvironment();
      node.comment.visit(this);
      node.ifstat.visit(this);
      mem.leaveEnvironment();
    } else {
      // this is a sublevel rule and will get an if to determine whether it should be executed
      out.append("if ((returnTo | (");
      int i = 0;
      for (String r : out.rm.shouldAddReturnto(node.label)) {
        if (i != 0) {
          out.append(" | return_" + r);
        } else {
          out.append("return_" + r);
        }
        i++;
      }
      out.append(")) == 0) {\n");
      node.comment.visit(this);
      node.ifstat.visit(this);
    }
    out.append("}\n");
  }

  @Override
  public void visitNode(StatAbstractBlock node) {
    if (node.braces) {
      // when entering a statement block, we need to create a new local environment
      mem.enterNextEnvironment();
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
  public void visitNode(StatDoWhile node) {
    out.append("do");
    node.statblock.visit(this);
    out.append("while (");
    node.condition.visit(this);
    out.append(");");
  }

  @Override
  public void visitNode(StatFor1 node) {
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
  public void visitNode(StatFor2 node) {
    // TODO: or should we check here that the type of the variable in assignment
    // is the type the iterable in exp returns? How?
    if (node.varType == null) {
      node.varType = ((RTExpression) node.exp).getType();
    }
    out.append("for (" + node.varType + " ");
    node.var.visit(this);
    out.append(": ");
    node.exp.visit(this);
    out.append(") ");
    node.statblock.visit(this);
  }

  @Override
  public void visitNode(StatFor3 node) {
    out.append("for (Object[] o : ");
    node.exp.visit(this);
    out.append(") {");
    int count = 0;
    for (String s : node.variables) {
      out.append("\nObject " + s + " = o[" + count++ + "]");
    }
    node.statblock.visit(this);
    out.append("}");
  }

  @Override
  public void visitNode(StatFunDef node) {
    // no generation here
  }

  @Override
  public void visitNode(StatIf node) {
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
  public void visitNode(StatImport node) {
    // TODO: memory creation should be handled in testTypeVisitor...
    System.out.println("Processing import " + node.text);
    out.append(node.text + ".process()");
    try {
      RudimantCompiler.getEmbedded(out).process(node.text);
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }

  @Override
  public void visitNode(StatMethodDeclaration node) {
    mem.enterNextEnvironment();
    String ret = node.visibility + " " + node.return_type + " " + node.name + "(";
    if (!node.parameters.isEmpty()) {
      for (int i = 0; i < node.parameters.size(); i++) {
        if (i == 1) {
          ret += node.partypes.get(i) + " " + node.parameters.get(i);
        } else {
          ret += ", " + node.partypes.get(i) + " " + node.parameters.get(i);
        }
      }
    }
    ret += ")";
    out.append(ret + "\n");
    node.block.visit(this);
    mem.leaveEnvironment();
  }

  @Override
  public void visitNode(StatPropose node) {
    out.append("propose(");
    node.arg.visit(this);
    out.append(", new Proposal() {public void run()\n");
    node.block.visit(this);
    out.append("});");
  }

  @Override
  public void visitNode(StatReturn node) {
    if (out.rm.isExistingRule(node.lit)) {
      out.append("returnTo = returnTo | return_" + node.lit + ";\n");
      return;

    } else if (node.toRet == null) {
      out.append("return;\n");
      return;
    }
    out.append("return ");
    node.toRet.visit(this);
    out.append(";\n");
  }

  @Override
  public void visitNode(StatSetOperation node) {
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
  public void visitNode(StatTimeout node) {
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
  public void visitNode(StatVarDef node) {
    // no generation here
  }

  @Override
  public void visitNode(StatWhile node) {
    out.append("while (");
    node.condition.visit(this);
    out.append(")");
    node.statblock.visit(this);
  }

  @Override
  public void visitNode(UCharacter node) {
    out.append("\'" + node.content + "\'");
  }

  @Override
  public void visitNode(UComment node) {
    out.append(node.comment);
  }

  @Override
  public void visitNode(UCommentBlock node) {
    for (UComment c : node.comments) {
      c.visit(this);
      out.append("\n");
    }
  }

  @Override
  public void visitNode(UFieldAccess node) {
    // TODO: tell me how the client is named!!!
    out.append(node.representation.get(0));
    for (int i = 1; i < node.representation.size(); i++) {
      out.append(".getValue(" + node.representation.get(i) + ", client)");
    }
  }

  @Override
  public void visitNode(UFuncCall node) {
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
  public void visitNode(UNull node) {
    out.append("null");
  }

  @Override
  public void visitNode(UNumber node) {
    out.append(node.value);
  }

  @Override
  public void visitNode(UString node) {
    out.append(node.content);
  }

  @Override
  public void visitNode(UVariable node) {
    // if the variable is not in the memory,
    if (!node.origin.equals(mem.getVariableOrigin(node.representation))
            && !(mem.getVariableOrigin(node.representation) == null)) {
      out.append(mem.getVariableOrigin(node.representation) + "." + node.representation);
      return;
    }
    out.append(node.representation);
  }

  @Override
  public void visitNode(UWildcard node) {
    out.append("this.wildcard");   // wildcard is a local variable in resulting class
  }

  @Override
  public void visitNode(UnaryBoolean node) {
    out.append(node.content);
  }
}
