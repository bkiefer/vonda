/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree.statements;

import de.dfki.mlt.rudi.Mem;
import de.dfki.mlt.rudi.abstractTree.AbstractStatement;
import de.dfki.mlt.rudi.abstractTree.AbstractTree;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

/**
 * type_spec VARIABLE LPAR
    ( type_spec VARIABLE (COMMA type_spec VARIABLE)* )? RPAR SEMICOLON
 * @author anna
 */
public class AFunDef implements AbstractStatement, AbstractTree{

  private String funcname;
  private String type;
  private ArrayList<String> parameters;
  private ArrayList<String> parameterTypes;
  private String position;

  public AFunDef(String type, String funcname, ArrayList<String> parameters,
          ArrayList<String> parameterTypes, String position){
    this.type = type;
    this.funcname = funcname;
    this.position = position;
    this.parameters = parameters;
    this.parameterTypes = parameterTypes;
  }

  @Override
  public void generate(Writer out) throws IOException {
    Mem.addFunction(funcname, type, parameterTypes, position);
  }

  @Override
  public void testType() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

}
