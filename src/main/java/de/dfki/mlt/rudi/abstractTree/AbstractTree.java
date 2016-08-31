/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

import java.io.IOException;
import java.io.Writer;

/**
 * all classes that can be created by the ParseTreeVisitor should implement this
 * interface, for it is the return type of the visit methods
 * @author Anna Welker
 */
public interface AbstractTree {

  /**
   * method that can print the tree to code
   * @return code representing this treenode
   */
  public void generate(Writer out) throws IOException;

  /**
   * method for type testing
   */
  public void testType();
}
