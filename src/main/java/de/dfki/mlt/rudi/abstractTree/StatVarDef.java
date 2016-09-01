/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

import de.dfki.mlt.rudi.Mem;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import de.dfki.mlt.rudi.abstractTree.RudiTree;
import de.dfki.mlt.rudi.abstractTree.RTStatement;

/**
 * type_spec VARIABLE SEMICOLON
 * = only to get the type of this variable into memory
 *
 * @author Anna Welker
 */
public class StatVarDef implements RTStatement, RudiTree{

  private String variable;
  private String type;
  private String position;

  public StatVarDef(String type, String variable, String position){
    this.type = type;
    this.variable = variable;
    this.position = position;
  }

  @Override
  public void generate(Writer out) throws IOException {
    Mem.addElement(variable, type, position);
  }

  @Override
  public void testType() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void returnManaging() {
    // nothing to do
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }
}
