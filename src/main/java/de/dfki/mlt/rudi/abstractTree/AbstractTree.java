/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

import java.io.Writer;

/**
 *
 * @author anna
 */
public interface AbstractTree {

  /**
   * method that can print the tree to code
   * @return code representing this treenode
   */
  public void generate(Writer out);

  /**
   * method for type testing
   */
  public void testType();
}
