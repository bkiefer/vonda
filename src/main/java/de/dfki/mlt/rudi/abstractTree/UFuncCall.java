/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

import de.dfki.mlt.rudi.GrammarMain;
import de.dfki.mlt.rudi.Mem;
import java.util.List;

import de.dfki.mlt.rudi.abstractTree.*;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

/**
 * this is a call to a function; as field accesses are considered to be retrieving
 * sth from the ontology, we define that there will never be functions used in
 * a .rudi file that would need to be accessed via another class
 *
 * @author Anna Welker
 */
public class UFuncCall extends RTLeaf{

  private String type;
  private String representation;
  private List<RTExpression> exps;

  public UFuncCall(String representation, List<RTExpression> exps) {
    this.representation = representation;
    this.exps = exps;
  }

  @Override
  public void testType() {
    // test whether the given parameters are of the correct type
    ArrayList<String> partypes = new ArrayList<String>();
    for (RTExpression e : exps){
      partypes.add(e.getType());
    }
    Mem.existsFunction(representation, partypes);
  }

  @Override
  public void generate(Writer out) throws IOException{
    if(GrammarMain.checkTypes()){
      testType();
    }
    out.append(this.representation + "(");
    for(int i = 0; i < this.exps.size(); i++){
      this.exps.get(i).generate(out);
      if(i != this.exps.size() -1){
        out.append(", ");
      }
    }
    out.append(")");
  }

  @Override
  public String getType() {
    if(this.type == null){
      type = Mem.getFunctionRetType(representation);
    }
    return this.type;
  }

  @Override
  public void returnManaging() {
    // nothing to do
  }
}
