/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree.statements;

import de.dfki.mlt.rudi.abstractTree.AbstractStatement;
import de.dfki.mlt.rudi.abstractTree.AbstractTree;
import de.dfki.mlt.rudi.returnManagement;
import java.io.IOException;
import java.io.Writer;

/**
 * a return statement; returns can be used with labels to return to the
 * corresponding point in execution
 *
 * @author Anna Welker
 */
public class AReturnStat implements AbstractStatement, AbstractTree {

  private AbstractTree toRet;
  private String lit;

  public AReturnStat() {
    this.toRet = null;
  }

  public AReturnStat(AbstractTree exp, String lit) {
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

}
