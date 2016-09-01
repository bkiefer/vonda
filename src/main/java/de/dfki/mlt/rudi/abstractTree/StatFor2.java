/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

import de.dfki.mlt.rudi.Mem;
import de.dfki.mlt.rudi.abstractTree.UVariable;
import java.io.IOException;
import java.io.Writer;
import de.dfki.mlt.rudi.abstractTree.RudiTree;
import de.dfki.mlt.rudi.abstractTree.RTStatement;
import de.dfki.mlt.rudi.abstractTree.RTExpression;

/**
 * FOR LPAR VARIABLE COLON exp RPAR loop_statement_block
 * a 'modern' for statement
 *
 * @author Anna Welker
 */
public class StatFor2 implements RTStatement, RudiTree {

  String varType;
  UVariable var;
  RudiTree exp;
  StatAbstractBlock statblock;
  String position;

  public StatFor2(UVariable var, RudiTree exp, StatAbstractBlock statblock,
          String position) {
    this.var = var;
    this.exp = exp;
    this.statblock = statblock;
    this.varType = null;
    this.position = position;
  }

  public StatFor2(String varType, UVariable var, RudiTree exp,
          StatAbstractBlock statblock, String position) {
    this.var = var;
    this.exp = exp;
    this.statblock = statblock;
    if (varType.equals("var")) {
      this.varType = null;
    } else {
      this.varType = varType;
    }
    this.position = position;
  }

  @Override
  public void testType() {
    // somehow test return type of exp & variable?!
    // currently we will always assume that type of var is object
  }

  @Override
  public void generate(Writer out) throws IOException {
    // TODO: or should we check here that the type of the variable in assignment
    // is the type the iterable in exp returns? How?
    if (varType == null) {
      varType = ((RTExpression) exp).getType();
    }
    Mem.addElement(var.toString(), varType, position);
    out.append("for (" + varType + " ");
    var.generate(out);
    out.append(": ");
    exp.generate(out);
    out.append(") ");
    statblock.generate(out);
  }

  @Override
  public void returnManaging() {
    statblock.returnManaging();
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }
}
