/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * class that allows the implementation of methods in a .rudi file; we are not
 * sure whether this should still be possible
 *
 * @author Anna Welker
 */
public class StatMethodDeclaration extends RudiTree {

  String visibility;
  String return_type;
  String name;
  ArrayList<String> parameters;
  ArrayList<String> partypes;
  RudiTree block;
  String position;

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
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }

  public Iterable<? extends RudiTree> getDtrs() {
    RudiTree[] dtrs = { block };
    return Arrays.asList(dtrs);
  }
}
