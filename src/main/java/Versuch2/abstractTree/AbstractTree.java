/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Versuch2.abstractTree;

/**
 *
 * @author anna
 */
public interface AbstractTree {
  
  /**
   * method that can print the tree to code
   * @return code representing this treenode
   */
  @Override
  public String toString();
  
  /**
   * method for type testing
   */
  public void testType();
}
