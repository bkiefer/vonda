/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

/**
 *
 * @author anna
 */
public abstract class AbstractLeaf implements AbstractExpression {

  /**
   * the leaf's type
   */
  String type = "Object";

  public abstract String getType();
}
