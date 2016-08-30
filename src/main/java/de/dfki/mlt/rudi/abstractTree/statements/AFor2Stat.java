/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree.statements;

import de.dfki.mlt.rudi.abstractTree.AbstractStatement;
import de.dfki.mlt.rudi.abstractTree.AbstractTree;
import de.dfki.mlt.rudi.abstractTree.leaves.AVariable;
import java.io.IOException;
import java.io.Writer;

/**
 * FOR LPAR VARIABLE COLON exp RPAR loop_statement_block
 *
 * @author anna
 */
public class AFor2Stat implements AbstractStatement, AbstractTree {

  private String varType;
  private AVariable var;
  private AbstractTree exp;
  private AbstractBlock statblock;

  public AFor2Stat(AVariable var, AbstractTree exp, AbstractBlock statblock) {
    this.var = var;
    this.exp = exp;
    this.statblock = statblock;
    this.varType = "Object";
  }

  public AFor2Stat(String varType, AVariable var, AbstractTree exp, AbstractBlock statblock) {
    this.var = var;
    this.exp = exp;
    this.statblock = statblock;
    if (varType.equals("var")) {
      this.varType = "Object";
    } else {
      this.varType = varType;
    }
  }

  @Override
  public void testType() {
    // somehow test return type of exp & variable?!
    // currently we will always assume that type of var is object
  }

  @Override
  public void generate(Writer out) throws IOException {
    out.append("for (" + varType + " ");
    var.generate(out);
    out.append(": ");
    exp.generate(out);
    out.append(") ");
    statblock.generate(out);
  }
}
