/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Versuch2.abstractTree.statements;

import Versuch2.abstractTree.AbstractStatement;
import Versuch2.abstractTree.AbstractTree;

/**
 *
 * @author anna
 */
public class ATimeoutStat implements AbstractStatement, AbstractTree{
  private long time;
  private String name;
  private AbstractTree statblock;

  public ATimeoutStat(String name, long time, AbstractTree statblock) {
    this.time = time;
    this.name = name;
    this.statblock = statblock;
  }
  
  @Override
  public String toString(){
    // TODO: soll statblock actionPerformed überschreiben? dann kann nicht die Timeouts-Klasse
    // benutzt werden
    return "timeouts.newTimeout(name, time);\n";
  }

  @Override
  public void testType() {
    //nothing to test here
  }
  
}
