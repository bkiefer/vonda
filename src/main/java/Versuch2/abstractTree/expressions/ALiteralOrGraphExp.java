/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Versuch2.abstractTree.expressions;

import Versuch2.GrammarMain;
import Versuch2.Mem;
import Versuch2.abstractTree.AbstractExpression;
import Versuch2.abstractTree.AbstractTree;
import Versuch2.abstractTree.AbstractType;

/**
 *
 * @author anna
 */
public class ALiteralOrGraphExp  implements AbstractTree, AbstractExpression{

  // comment LITERAL_OR_GRAPH LPAR ( exp (COMMA exp)*)? RPAR comment
  
  private String litGraph;
  //private List<AbstractExpression> exps;
  private String rest;

  public ALiteralOrGraphExp(String litGraph, String rest){ // List<AbstractExpression> exps) {
    this.litGraph = litGraph.substring(1, litGraph.length());
    //this.exps = exps;
    this.rest = rest;
  }
  
  @Override
  public void testType() {
    // no type testing here
  }
  
  @Override
  public String toString(){
    String ret = "new DialogueAct(\"" + litGraph + "(";
    String rightParams = "";
    String[] parameters = this.rest.split(",");
    // the first argument will never need to be more than a String
    rightParams += parameters[0];
    for (int i = 1; i < parameters.length; i++){
      String[] parts = parameters[i].split("=");
      if(parts.length == 1){
        // then this argument is a variable that is passed and should be found somewhere
        if(Mem.existsVariable(parts[0])){
          rightParams += ", \" + " + parts[0] + " + \"";
        }
        else if(GrammarMain.context.isGlobalVariable(parts[0])){
          rightParams += ", \" + " + parts[0] + " + \"";          
        }
        else{
          // TODO: or throw an error here?
          rightParams += ", " + parts[0];
        }
      }
      else{
        // this argument is of kind x = y, look if y is a variable we know
        if(Mem.existsVariable(parts[1])){
          rightParams += ", " + parts[0] + " = \" + " + parts[1] + " + \"";
        }
        else if(GrammarMain.context.isGlobalVariable(parts[1])){
          rightParams += ", " + parts[0] + " = \" + " + parts[1] + " + \"";          
        }
        else{
          rightParams += ", " + parts[0] + " = " + parts[1];
        }
      }
    }
    return ret + rightParams + ")\")";
  }

  @Override
  public AbstractType getType() {
    return AbstractType.MAGIC;
  }
  
}
