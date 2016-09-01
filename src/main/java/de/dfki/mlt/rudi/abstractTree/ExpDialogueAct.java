/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

import de.dfki.mlt.rudi.GrammarMain;
import de.dfki.mlt.rudi.Mem;
import java.io.IOException;
import java.io.Writer;
import de.dfki.mlt.rudi.abstractTree.RudiTree;
import de.dfki.mlt.rudi.abstractTree.RTExpression;

/**
 * this class represents dialogue acts
 *
 * @author Anna Welker
 */
public class ExpDialogueAct implements RudiTree, RTExpression{

  // comment LITERAL_OR_GRAPH LPAR ( exp (COMMA exp)*)? RPAR comment

  private String litGraph;
  //private List<AbstractExpression> exps;
  private String rest;

  public ExpDialogueAct(String litGraph, String rest){ // List<AbstractExpression> exps) {
    this.litGraph = litGraph.substring(1, litGraph.length());
    //this.exps = exps;
    this.rest = rest;
  }

  @Override
  public void testType() {
    // no type testing here
  }

  @Override
  public void generate(Writer out) throws IOException{
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
  public String getType() {
    return "magic";
  }

  @Override
  public void returnManaging() {
    // nothing to do
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }
}
