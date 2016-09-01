/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

import de.dfki.mlt.rudi.returnManagement;
import java.io.IOException;
import java.io.Writer;
import de.dfki.mlt.rudi.abstractTree.RudiTree;
import de.dfki.mlt.rudi.abstractTree.RTStatement;

/**
 * a return statement; returns can be used with labels to return to the
 * corresponding point in execution
 *
 * @author Anna Welker
 */
public class StatReturn implements RTStatement, RudiTree {

  private RudiTree toRet;
  private String lit;

  public StatReturn() {
    this.toRet = null;
  }

  public StatReturn(RudiTree exp, String lit) {
    if (returnManagement.isExistingRule(lit)) {
      returnManagement.foundReturnTo(lit);
    } else {
      this.toRet = exp;
      this.lit = lit;
    }
  }

  @Override
  public void generate(Writer out) throws IOException {
    if (this.toRet == null) {
      out.append("return;\n");
    }
    out.append("return ");
    this.toRet.generate(out);
    out.append(";\n");
  }

  @Override
  public void testType() {
    // test return type??
  }

  @Override
  public void returnManaging() {
    if (returnManagement.isExistingRule(lit)) {
      returnManagement.foundReturnTo(lit);
      this.toRet = null;
    }
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }

}
