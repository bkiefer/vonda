/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

import de.dfki.mlt.rudi.GrammarMain;
import de.dfki.mlt.rudi.Mem;
import de.dfki.mlt.rudi.abstractTree.leaves.AFieldAccess;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.thrift.TException;

/**
 *
 * @author Anna Welker, anna.welker@dfki.de
 */
public class GenerationVisitor implements RudiVisitor{

  @Override
  public void visitNode(RudiTree node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(ExpAbstractWrapper node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(ExpArithmetic node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(ExpAssignment node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(ExpBoolean node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(ExpDialogueAct node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(ExpIf node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(ExpLambda node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(GrammarFile node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(GrammarRule node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(StatAbstractBlock node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void visitNode(StatDoWhile node) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    if (!asked) {
      try {
        this.type = askChristophe(out.getMem());
      } catch (TException ex) {
        Logger.getLogger(AFieldAccess.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    // TODO: tell me how the client is named!!!
    out.append(representation.get(0));
    for (int i = 1; i < representation.size(); i++) {
      out.append(".getValue(" + representation.get(i) + ", client)");
    }
  }

  @Override
  public void visitNode(UFuncCall node) {
    if(GrammarMain.checkTypes()){
      testType();
    }
    out.append(this.representation + "(");
    for(int i = 0; i < this.exps.size(); i++){
      this.exps.get(i).generate(out);
      if(i != this.exps.size() -1){
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
    out.append(this.value);
  }

  @Override
  public void visitNode(UString node) {
    out.append(this.content);
  }

  @Override
  public void visitNode(UVariable node) {
    if(!origin.equals(Mem.getVariableOrigin(representation)))
      origin += ".";
    out.append(origin + representation);
  }

  @Override
  public void visitNode(UWildcard node) {
    out.append("this.wildcard");   // wildcard is a local variable in resulting class
  }

  @Override
  public void visitNode(UnaryBoolean node) {
    out.append(this.content);
  }
}