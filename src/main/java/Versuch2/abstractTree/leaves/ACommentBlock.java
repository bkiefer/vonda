/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Versuch2.abstractTree.leaves;

import Versuch2.GrammarMain;
import Versuch2.abstractTree.AbstractStatement;
import Versuch2.abstractTree.AbstractTree;
import java.util.List;

/**
 *
 * @author anna
 */
public class ACommentBlock implements AbstractTree, AbstractStatement {

  List<AComment> comments;
  private boolean containsClassName;
  private boolean printClassName;

  public ACommentBlock(List<AComment> comments) {
    this.comments = comments;
    this.containsClassName = false;
    for (AComment c : comments) {
      if (c.containsClassName()) {
        this.containsClassName = true;
      }
    }
  }

  public void setPrintClassName() {
    this.printClassName = true;
  }

  public boolean containsClassName() {
    return this.containsClassName;
  }

  @Override
  public String toString() {
    String ret = "";
    if (this.printClassName) {
      for (AComment c : comments) {
        ret += "\n" + c;
        if(c.containsClassName()){
          ret += "\n" + GrammarMain.context.afterClassName();
        }
      }
      return ret;
    }
    for (AComment c : comments) {
      ret += "\n" + c;
    }
    return ret;
  }

  @Override
  public void testType() {
  }

}
