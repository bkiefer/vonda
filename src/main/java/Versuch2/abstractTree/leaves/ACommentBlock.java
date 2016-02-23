/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Versuch2.abstractTree.leaves;

import Versuch2.abstractTree.AbstractStatement;
import Versuch2.abstractTree.AbstractTree;
import java.util.List;

/**
 *
 * @author anna
 */
public class ACommentBlock implements AbstractTree, AbstractStatement{
  
  List<AComment> comments;

  public ACommentBlock(List<AComment> comments) {
    this.comments = comments;
  }
  
  @Override
  public String toString(){
    String ret = "";
    for (AComment c : comments){
      ret += c.toString() + "\n";
    }
    return ret + "\n";
  }

  @Override
  public void testType() {
  }
  
}
