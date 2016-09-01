/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

import de.dfki.mlt.rudi.GrammarMain;
import de.dfki.mlt.rudi.Mem;
import de.dfki.mlt.rudi.ReturnManagement;
import de.dfki.mlt.rudi.abstractTree.leaves.AFieldAccess;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.thrift.TException;

/**
 *
 * @author Anna Welker, anna.welker@dfki.de
 */
public class GenerationVisitor implements RudiVisitor {

  @Override
  public void visitNode(RudiTree node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(ExpAbstractWrapper node) {
    node.commentbefore.generate(out);
    node.exp.generate(out);
    node.commentafter.generate(out);
  }

  @Override
  public void visitNode(ExpArithmetic node) {
    String ret = "";
    if (node.minus) {
      out.append("-");
    }
    if (node.right != null) {
      out.append("(");
      node.left.generate(out);
      out.append(this.operator);
      node.right.generate(out);
      out.append(")");
      return;
    }
    node.left.generate(out);
  }

  @Override
  public void visitNode(ExpAssignment node) {
    //System.out.println(((UVariable) left).toString());
    if (GrammarMain.checkTypes()) {
      if (node.actualType != null) {
        testTypeDecl(node.actualType, out);
      } else {
        testType(out);
      }
    }
    if (node..declaration) {
      Mem.addElement(((UVariable) node.left).toString(), node.actualType, node.position);
      out.append(node..actualType + " " + ((UVariable) node.left).toString() + " = ");
      node..right.generate(out);
      return;
    }
    out.append(((UVariable) node.left).toString() + " = ");
    node..right.generate(out);
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
      node.left.generate(out);
      out.append(", ");
      node.right.generate(out);
      out.append(")");
    } else if (node.doesSubsume) {
      out.append("isSubsumed(");
      node.right.generate(out);
      out.append(", ");
      node.left.generate(out);
      out.append(")");
    }
    if (node.right != null) {
      out.append("(");
      node.left.generate(out);
      out.append(node.operator);
      node.right.generate(out);
      out.append(")");
      //GrammarMain.context.doLog(
      //        "\"" + ret.replace('"', ' ') +  " _ resulted to \" + " + ret);
      return;
    }
    node.left.generate(out);
    //GrammarMain.context.doLog("\"" + ret.replace('"', ' ') +  " resulted to \" + ("
    //        + ret + ")");
  }

  @Override
  public void visitNode(ExpDialogueAct node) {
    out.append("new DialogueAct(\"" + litGraph + "(");
    String[] parameters = this.rest.split(",");
    // the first argument will never need to be more than a String
    out.append(parameters[0]);
    for (int i = 1; i < parameters.length; i++){
      String[] parts = parameters[i].split("=");
      if(parts.length == 1){
        // then this argument is a variable that is passed and should be found somewhere
        if(Mem.existsVariable(parts[0])){
          out.append(", \" + " + parts[0] + " + \"");
        }
        else{
          // TODO: or throw an error here?
          out.append(", " + parts[0]);
        }
      }
      else{
        // this argument is of kind x = y, look if y is a variable we know
        if(Mem.existsVariable(parts[1])){
          out.append(", " + parts[0] + " = \" + " + parts[1] + " + \"");
        }
        else{
          out.append(", " + parts[0] + " = " + parts[1]);
        }
      }
    }
    out.append(")\")");
  }

  @Override
  public void visitNode(ExpIf node) {
    this.boolexp.generate(out);
    out.append(" ? ");
    this.thenexp.generate(out);
    out.append(" : ");
    this.elseexp.generate(out);
  }

  @Override
  public void visitNode(ExpLambda node) {
    out.append(exp);
  }

  @Override
  public void visitNode(GrammarFile node) {
    //let the return guy do its work
    returnManaging();
    //boolean foundClassName = false;
    Mem.addAndEnterNewEnvironment(Mem.getCurrentDepth() + 1);
    out.append("public class " + classname + "{\n"
            + "  public void process(){");
    for (RudiTree r : rules) {
      // Don't forget to insert text after class name as specified in context
      // Edit: no one wants to use this anyway
      /*if (r instanceof ACommentBlock && foundClassName == false) {
        if (((ACommentBlock) r).containsClassName()) {
          ((ACommentBlock) r).setPrintClassName();
          foundClassName = true;
        }
      }*/
      r.generate(out);
    }
    out.append("  }\n}");
    out.close();
    Mem.leaveEnvironment();
  }

  @Override
  public void visitNode(GrammarRule node) {
    String returnType = "void";
    if(ReturnManagement.shouldAddReturnto(label)){
      returnType = "String";
    }
    out = new BufferedWriter(new OutputStreamWriter(
            new FileOutputStream(GrammarMain.getOutputDirectory() + classname + ".java")));
    Mem.addAndEnterNewEnvironment(Mem.getCurrentDepth() + 1);
    out.append("public class " + classname + "{\n"
            + "  public " + returnType + "process(){");
    comment.generate(out);
    ifstat.generate(out);
    out.append("\n\t return;\n  }\n}");
    Mem.leaveEnvironment();
  }

  @Override
  public void visitNode(StatAbstractBlock node) {
        String stats = "";
    if (braces) {
      // when entering a statement block, we need to create a new local environment
      this.environmentPosition = Mem.addAndEnterNewEnvironment(Mem.getCurrentDepth() + 1);
      out.append("{");
    }
    for (RudiTree stat : statblock) {
      if (stat instanceof RTExpression) {
        stat.generate(out);
        out.append(";\n");
        break;
      }
      stat.generate(out);
    }
    if (braces) {
      out.append("}");
      Mem.leaveEnvironment();
    }
  }

  @Override
  public void visitNode(StatDoWhile node) {
    out.append("do");
    statblock.generate(out);
    out.append("while (");
    condition.generate(out);
    out.append(");");
  }

  @Override
  public void visitNode(StatFor1 node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(StatFor2 node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(StatFor3 node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(StatFunDef node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(StatIf node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(StatImport node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(StatMethodDeclaration node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(StatPropose node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(StatReturn node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(StatSetOperation node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(StatTimeout node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(StatVarDef node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(StatWhile node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
      c.generate(out);
      out.append("\n");
    }
  }

  @Override
  public void visitNode(UFieldAccess node) {
    if (!node.asked) {
      try {
        node.type = node.askChristophe(out.getMem());
      } catch (TException ex) {
        Logger.getLogger(AFieldAccess.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    // TODO: tell me how the client is named!!!
    out.append(node.representation.get(0));
    for (int i = 1; i < node.representation.size(); i++) {
      out.append(".getValue(" + node.representation.get(i) + ", client)");
    }
  }

  @Override
  public void visitNode(UFuncCall node) {
    if (GrammarMain.checkTypes()) {
      node.testType();
    }
    out.append(this.representation + "(");
    for (int i = 0; i < node.exps.size(); i++) {
      node.exps.get(i).generate(out);
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
    if (!node.origin.equals(Mem.getVariableOrigin(node.representation))) {
      node.origin += ".";
      out.append(node.origin + node.representation);
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
