/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

import de.dfki.mlt.rudi.abstractTree.leaves.ACommentBlock;
import de.dfki.mlt.rudi.abstractTree.statements.AIfStatement;
import java.io.Writer;

/**
 *
 * @author anna
 */
public class AGrammarRule implements AbstractTree{

  // label comment if_statement

  private String label;
  private ACommentBlock comment;
  private AIfStatement ifstat;

  public AGrammarRule(String label, ACommentBlock comment,AIfStatement ifstat) {
    this.label = label;
    this.ifstat = ifstat;
    this.comment = comment;
  }

  @Override
  public void generate(Writer out){
    return "execute(\"" + label + "\");\n" + comment + ifstat + "\n";
  }

  @Override
  public void testType() {
    this.ifstat.testType();
  }

}
