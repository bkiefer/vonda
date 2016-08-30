/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree.statements;

import de.dfki.mlt.rudi.Mem;
import java.util.List;

import de.dfki.mlt.rudi.abstractTree.AbstractStatement;
import de.dfki.mlt.rudi.abstractTree.AbstractTree;
import java.io.IOException;
import java.io.Writer;

/**
 * FOR LPAR LPAR VARIABLE ( COMMA VARIABLE )+ RPAR COLON exp RPAR
 * loop_statement_block
 *
 * @author anna
 */
public class AFor3Stat implements AbstractStatement, AbstractTree {

  private List<String> variables;
  private AbstractTree exp;
  private AbstractBlock block;
  private String position;

  public AFor3Stat(List<String> variables, AbstractTree exp,
          AbstractBlock block, String position) {
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
}
