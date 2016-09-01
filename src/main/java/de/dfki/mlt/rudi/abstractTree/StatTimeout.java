/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

import java.io.IOException;
import java.io.Writer;

/**
 * class representing a timeout
 *
 * @author Anna Welker
 */
public class StatTimeout implements RTStatement, RudiTree {

    long time;
    String name;
    RudiTree statblock;

    public StatTimeout(String name, long time, RudiTree statblock) {
        this.time = time;
        this.name = name;
        this.statblock = statblock;
    }

    @Override
    public void testType() {
        //nothing to test here
    }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }
}
