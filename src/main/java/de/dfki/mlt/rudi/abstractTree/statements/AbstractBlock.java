/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree.statements;

import java.util.List;

import de.dfki.mlt.rudi.Mem;
import de.dfki.mlt.rudi.abstractTree.AbstractExpression;
import de.dfki.mlt.rudi.abstractTree.AbstractStatement;
import de.dfki.mlt.rudi.abstractTree.AbstractTree;
import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author anna
 */
public class AbstractBlock implements AbstractStatement, AbstractTree{

  private List<AbstractTree> statblock;
  private final boolean braces;
  private int environmentPosition;

  public AbstractBlock(List<AbstractTree> statblock, boolean braces, int environmentPosition) {
    this.statblock = statblock;
    this.braces = braces;
    this.environmentPosition = environmentPosition;
  }

  public AbstractBlock(List<AbstractTree> statblock, boolean braces){
    this.statblock = statblock;
    this.braces = braces;
    if(braces){
      throw new UnsupportedOperationException("Attention, you didn't create a new environment when entering a statement block!!");
    }
  }

  @Override
  public void testType() {
  }

  @Override
  public void generate(Writer out) throws IOException{
    String stats = "";
    if(braces){
      Mem.goToEnvironmentNumber(environmentPosition);
      out.append("{");
    }
    for (AbstractTree stat : statblock){
      if(stat instanceof AbstractExpression){
        stat.generate(out);
        out.append(";\n");
        break;
      }
      stat.generate(out);
    }
    if(braces){
      out.append("}");
    }
  }

}
