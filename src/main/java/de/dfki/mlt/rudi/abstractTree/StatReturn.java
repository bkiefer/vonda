/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

import de.dfki.mlt.rudi.ReturnManagement;
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

  RudiTree toRet;
  String lit;

  public StatReturn() {
    this.toRet = null;
  }

  public StatReturn(RudiTree exp, String lit) {
    if (ReturnManagement.isExistingRule(lit)) {
      ReturnManagement.foundReturnTo(lit);
    } else {
      this.toRet = exp;
      this.lit = lit;
    }
  }
  
  @Override
  public void testType() {
    // test return type??
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }

}
