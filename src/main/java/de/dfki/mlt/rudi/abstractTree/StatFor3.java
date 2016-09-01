/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

import de.dfki.mlt.rudi.Mem;
import java.util.List;

import java.io.IOException;
import java.io.Writer;
import de.dfki.mlt.rudi.abstractTree.RudiTree;
import de.dfki.mlt.rudi.abstractTree.RTStatement;

/**
 * FOR LPAR LPAR VARIABLE ( COMMA VARIABLE )+ RPAR COLON exp RPAR
 * loop_statement_block
 * = a special for statement allowing tuples of arbitrary size
 *
 * @author Anna Welker
 */
public class StatFor3 implements RTStatement, RudiTree {

  private List<String> variables;
  private RudiTree exp;
  private StatAbstractBlock block;
  private String position;

  public StatFor3(List<String> variables, RudiTree exp,
          StatAbstractBlock block, String position) {
    this.variables = variables;
    this.exp = exp;
    this.block = block;
    this.position = position;
  }

  @Override
  public void testType() {
    // no types for statements
  }

  @Override
  public void generate(Writer out) throws IOException {
    out.append("for (Object[] o : ");
    this.exp.generate(out);
    out.append(") {");
    int count = 0;
    for (String s : this.variables) {
      Mem.addElement(s, "Object", position);
      out.append("\nObject " + s + " = o[" + count++ + "]");
    }
    block.generate(out);
    out.append("}");
  }

  @Override
  public void returnManaging() {
    block.returnManaging();
  }
}
