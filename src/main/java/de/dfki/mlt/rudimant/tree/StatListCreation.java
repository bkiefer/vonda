/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.tree;

import java.util.ArrayList;

/**
 *
 * @author Anna Welker, anna.welker@dfki.de
 */
public class StatListCreation extends RTStatement {

  ArrayList<RTExpression> objects;
  String origin;
  String variableName;
  String listType;

  public StatListCreation(String varName, ArrayList<RTExpression> objs,
          String orig) {
    objects = objs;
    origin = orig;
    variableName = varName;
  }

  public StatListCreation(String varName, ArrayList<RTExpression> objs,
          String orig, String lType) {
    objects = objs;
    origin = orig;
    variableName = varName;
    listType = lType;
  }

  @Override
  public void visit(RTStatementVisitor v) {
    v.visitNode(this);
  }

  @Override
  public String visitStringV(RTStringVisitor v){
    throw new UnsupportedOperationException("Nodes bigger than expressions must not return Strings but write to out!");
  }

  @Override
  public void visitVoidV(VGenerationVisitor v) {
    v.visitNode(this);
  }

  public Iterable<? extends RudiTree> getDtrs() {
    return objects;
  }
}
