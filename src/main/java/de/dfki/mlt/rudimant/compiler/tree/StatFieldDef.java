/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.compiler.tree;

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
  public void visit(RTStatementVisitor v) {
    v.visitNode(this);
  }

  @Override
  public String visitStringV(RTStringVisitor v){
    throw new UnsupportedOperationException("Nodes bigger than expressions must not return Strings but write to out!");
  }

  @Override
  public void visitVoidV(VisitorGeneration v) {
    v.visitNode(this);
  }

  public String toString() {
    return (visibility != null ? visibility + " " : "") + varDef;
  }

  public Iterable<? extends RudiTree> getDtrs() {
    return Collections.emptyList();
  }
}
