/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

import java.io.IOException;
import java.io.Writer;
import de.dfki.mlt.rudi.abstractTree.RudiTree;
import de.dfki.mlt.rudi.abstractTree.RTExpression;

/**
 * this is an if expression, i.e., something like a ? b : c
 *
 * @author Anna Welker
 */
public class ExpIf implements RudiTree, RTExpression{

  private RTExpression boolexp;
  private RTExpression thenexp;
  private RTExpression elseexp;

  public ExpIf(RTExpression bool, RTExpression thenexp, RTExpression elseexp){
    this.boolexp = bool;
    this.thenexp = thenexp;
    this.elseexp = elseexp;
  }

  @Override
  public void testType() {
    ((RudiTree)boolexp).testType();
    ((RudiTree)thenexp).testType();
    ((RudiTree)elseexp).testType();
    assert(boolexp.getType().equals("boolean"));
    assert(thenexp.getType().equals(elseexp.getType()));
  }

  @Override
  public String getType() {
    return thenexp.getType();
  }

  @Override
  public void generate(Writer out) throws IOException{
    this.boolexp.generate(out);
    out.append(" ? ");
    this.thenexp.generate(out);
    out.append(" : ");
    this.elseexp.generate(out);
  }

  @Override
  public void returnManaging() {
    // nothing to do
  }
}
