/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * class that allows the implementation of methods in a .rudi file; we are not
 * sure whether this should still be possible
 *
 * @author Anna Welker
 */
public class StatMethodDeclaration extends RTStatement {

  String visibility;
  String return_type;
  String name;
  ArrayList<String> parameters;
  ArrayList<String> partypes;
  RTStatement block;
  // the type this method should be called upon; null if the method
  // is rudi-defined!
  String calledUpon;

  public StatMethodDeclaration(String vis, String ret_type,
		  String calledUpn, String nm, ArrayList<String> parms,
		  ArrayList<String> parmTypes, RTStatement blk) {
    visibility = vis;
    return_type = ret_type;
    name = nm;
    parameters = parms;
    partypes = parmTypes;
    block = blk;
    calledUpon = calledUpn;
  }

  @Override
  public void visit(RTStatementVisitor v) {
    v.visitNode(this);
  }

  @Override
  public String visitStringV(RTStringVisitor v){
    throw new UnsupportedOperationException("Nodes bigger than expressions must not return Strings but write to out!");
  }

  @Override
  public void visitVoidV(VGenerationVisitor v) {
    v.visitNode(this);
  }

  public Iterable<? extends RudiTree> getDtrs() {
    if (block == null) return Collections.emptyList();
    RudiTree[] dtrs = { block };
    return Arrays.asList(dtrs);
  }
}
