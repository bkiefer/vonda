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
public class StatFieldDef extends RTStatement {

  String visibility;
  StatVarDef varDef;

  public StatFieldDef(String visibility, StatVarDef varDef) {
    this.visibility = visibility;
    this.varDef = varDef;
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }

  public String toString() {
    return (visibility != null ? visibility + " " : "") + varDef;
  }

  @SuppressWarnings("serial")
  public Iterable<? extends RudiTree> getDtrs() {
    return new ArrayList<RudiTree>(){{ add(varDef); }};
  }
}
