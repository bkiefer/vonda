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
import java.util.HashMap;

/**
 * class that allows the implementation of methods in a .rudi file; we are not
 * sure whether this should still be possible
 * @author Anna Welker
 */
public class StatMethodDeclaration implements RudiTree {

  private String visibility;
  private String return_type;
  private String name;
  private ArrayList<String> parameters;
  private ArrayList<String> partypes;
  private RudiTree block;
  private String position;

  public StatMethodDeclaration(String visibility, String return_type, String name,
          ArrayList<String> parameters, ArrayList<String> partypes,
          RudiTree block, String position) {
    this.visibility = visibility;
    this.return_type = return_type;
    this.name = name;
    this.parameters = parameters;
    this.partypes = partypes;
    this.block = block;
    this.position = position;
    Mem.addFunction(name, return_type, partypes, position);
  }

  @Override
  public void generate(Writer out) throws IOException {
    Mem.addAndEnterNewEnvironment(Mem.getCurrentDepth());
    String ret = visibility + " " + return_type + " " + name + "(";
    if (!parameters.isEmpty()) {
      for (int i = 0; i < parameters.size(); i++) {
        if (i == 1) {
          ret += partypes.get(i) + " " + parameters.get(i);
        } else {
          ret += ", " + partypes.get(i) + " " + parameters.get(i);
        }
        // add parameters to environment
        Mem.addElement(parameters.get(i), partypes.get(i), position);
      }
    }
    ret += ")";
    out.append(ret + "\n");
    block.generate(out);
    Mem.leaveEnvironment();
  }

  @Override
  public void testType() {
    // ...
  }

  @Override
  public void returnManaging() {
    block.returnManaging();
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }
}
