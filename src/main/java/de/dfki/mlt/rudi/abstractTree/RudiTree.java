/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

/**
 * all classes that can be created by the ParseTreeVisitor should implement this
 * interface, for it is the return type of the visit methods
 * @author Anna Welker
 */
public interface RudiTree {


  /**
   * method for type testing
   */
  public void testType();

  /**
   * visitor method
   */
  public void visit(RudiVisitor v) throws Exception;
}
