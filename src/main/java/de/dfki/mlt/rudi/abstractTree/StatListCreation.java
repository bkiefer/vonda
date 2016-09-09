/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

import java.util.ArrayList;

/**
 *
 * @author Anna Welker, anna.welker@dfki.de
 */
public class StatListCreation implements RudiTree, RTStatement{

  //RTExpression left;
  ArrayList<RTExpression> objects;
  String origin;
  String variableName;
  String listType;

  public StatListCreation (String variableName, ArrayList<RTExpression> objects,
          String origin){
    this.objects = objects;
    this.origin = origin;
    this.variableName = variableName;
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }

}
