/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree;

import java.io.IOException;
import java.io.Writer;
import de.dfki.mlt.rudi.abstractTree.RudiTree;
import de.dfki.mlt.rudi.abstractTree.RTStatement;

/**
 * class representing a timeout
 *
 * @author Anna Welker
 */
public class StatTimeout implements RTStatement, RudiTree {

    private long time;
    private String name;
    private RudiTree statblock;

    public StatTimeout(String name, long time, RudiTree statblock) {
        this.time = time;
        this.name = name;
        this.statblock = statblock;
    }

    @Override
    public void generate(Writer out) throws IOException {
        // TODO: test this
        if(this.statblock == null){
            out.append("newTimeout(" + name + "," + time + ");\n");
        }
        out.append("MyTimer t = newSpecialTimeout(" + name + "," + time + ");"
                + "t.timer = new Timer(timeToFire, new ActionListener(){\n"
                + "@Override\n public void actionPerformed(ActionEvent e)" + statblock + "});"
                + "\n" + "t.started = System.currentTimeMillis();\n"
                + " t.timer.start();\n");
    }

    @Override
    public void testType() {
        //nothing to test here
    }

    @Override
  public void returnManaging() {
    // nothing to do
  }
}
