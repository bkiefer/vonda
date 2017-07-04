/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import de.dfki.mlt.rudimant.Environment;
import de.dfki.mlt.rudimant.Type;

/**
 * class that allows the implementation of methods in a .rudi file; we are not
 * sure whether this should still be possible
 *
 * @author Anna Welker
 */
public class StatMethodDeclaration extends RTStatement {

  String visibility;
  Type return_type;
  String name;
  ArrayList<String> parameters;
  ArrayList<Type> partypes;
  RTStatement block;
  // the type this method should be called upon; null if the method
  // is rudi-defined!
  Type calledUpon;

  private Environment _localBindings;

  public StatMethodDeclaration(String vis, String ret_type,
		  String calledUpn, String nm, ArrayList<String> parms,
		  ArrayList<String> parmTypes, RTStatement blk) {
    visibility = vis;
    return_type = new Type(ret_type);
    name = nm;
    parameters = parms;
    partypes = new ArrayList<Type>();
    for (String p : parmTypes){
      partypes.add(new Type(p));
    }
    block = blk;
    calledUpon = new Type(calledUpn);
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
  public void visitVoidV(VisitorGeneration v) {
    v.visitNode(this);
  }

  public Iterable<? extends RudiTree> getDtrs() {
    if (block == null) return Collections.emptyList();
    RudiTree[] dtrs = { block };
    return Arrays.asList(dtrs);
  }

  public void setBindings(Environment local) {
    _localBindings = local;
  }

  public Environment getBindings() { return _localBindings; }
}
