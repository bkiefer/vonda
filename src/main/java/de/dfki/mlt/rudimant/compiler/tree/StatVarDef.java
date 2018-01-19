/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.compiler.tree;

import java.util.ArrayList;
import java.util.Collections;

import de.dfki.mlt.rudimant.compiler.Type;

/**
 * type_spec VARIABLE SEMICOLON = only to get the type of this variable into
 * memory
 *
 * @author Anna Welker
 */
public class StatVarDef extends RTStatement {

  String variable;
  Type type;
  RTExpression toAssign;
  boolean varIsFinal;
  boolean isDefinition;

  public StatVarDef(boolean isFinal, Type type, String variable,
      RTExpression assign) {
    this.varIsFinal = isFinal;
    this.isDefinition = isFinal || !type.isUnspecified();
    this.type = type;
    this.variable = variable;
    this.toAssign = assign;
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }

  public String toString() {
    return (varIsFinal ? "final " : "")
        + type + " " + variable
        + (toAssign != null ? ("= " + toAssign.toString()) : "") + ";"; }

  @Override
  @SuppressWarnings("serial")
  public Iterable<? extends RudiTree> getDtrs() {
    return new ArrayList<RudiTree>(){{ add(toAssign); }};
  }
}
