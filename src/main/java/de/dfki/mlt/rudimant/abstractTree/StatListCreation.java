/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

import java.util.ArrayList;

/**
 *
 * @author Anna Welker, anna.welker@dfki.de
 */
public class StatListCreation extends RTStatement {

  //RTExpression left;
  ArrayList<RTExpression> objects;
  String origin;
  String variableName;
  String listType;

  public StatListCreation(String variableName, ArrayList<RTExpression> objects,
          String origin) {
    this.objects = objects;
    this.origin = origin;
    this.variableName = variableName;
  }

  public StatListCreation(String variableName, ArrayList<RTExpression> objects,
          String origin, String listType) {
    this.objects = objects;
    this.origin = origin;
    this.variableName = variableName;
    this.listType = listType;
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }
  
  @Override
  public String visitStringV(VGenerationVisitor v){
    throw new UnsupportedOperationException("Nodes bigger than expressions must not return Strings but write to out!");
  }

  @Override
  public void visitVoidV(VGenerationVisitor v) {
    v.visitNode(this);
  }
  
  @Override
  public void visitCondPart(VRuleConditionVisitor v){
    v.visitNode(this);
  }

  public Iterable<? extends RudiTree> getDtrs() {
    return objects;
  }
}
