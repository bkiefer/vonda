/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

import java.util.Arrays;

import de.dfki.mlt.rudimant.RudimantCompiler;

/**
 * this represents an access to the ontology (will result in an rdf object in
 * output)
 *
 * @author Anna Welker
 */
public class UPropertyAccess extends RTExpLeaf {

  UVariable label;
  boolean propertyVariable = false;
  String rangeType;
  boolean functional;


  public UPropertyAccess(UVariable l, boolean var, String rt,
      boolean func) {
    // an access will always return sth of type Object, so to not get null
    // I'll set the type of this to Object by default
    type = "Object";
    label = l;
    propertyVariable = var;
    rangeType = rt;
    functional = func;
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }

  public Iterable<? extends RudiTree> getDtrs() {
    RudiTree[] dtrs = { label };
    return Arrays.asList(dtrs);
  }

  void getPropertyName(RudimantCompiler out) {
    if(!propertyVariable) out.append('"');
    out.append(label.content);
    if(!propertyVariable) out.append('"');
  }

}
