/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

import static de.dfki.mlt.rudimant.Constants.*;

/**
 * a special kind of the RudiTree is an expression; expressions can have types
 *
 * @author Anna Welker
 */
public abstract class RTExpression extends RudiTree {

  protected String type;
  protected boolean isRdfType;

  public String getType() {
    return type;
  }

  public void setType(String to) {
    this.type = to;
  }

  // Return true if this is represents an RDF type or a DialogueAct
  // TODO: maybe has to be split up.
  public boolean isRdfType() {
    return isRdfType;
  }

  public void setRdfType() {
    isRdfType = true;
  }

  public boolean isComplexType() {
    return ("String".equals(type)
        || type.startsWith("Map")
        || type.startsWith("Set")
        || type.startsWith("RdfSet")
        || type.startsWith("RdfList")
        || (type.endsWith(">") && ! isRdfType()));
  }

  public void propagateType(String upperType) {
  }

}
