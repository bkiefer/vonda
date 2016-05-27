/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree.statements;

import de.dfki.mlt.rudi.abstractTree.AbstractStatement;
import de.dfki.mlt.rudi.abstractTree.AbstractTree;

/**
 *
 * @author anna
 */
public class ATimeoutStat implements AbstractStatement, AbstractTree {

    private long time;
    private String name;
    private AbstractTree statblock;

    public ATimeoutStat(String name, long time, AbstractTree statblock) {
        this.time = time;
        this.name = name;
        this.statblock = statblock;
    }

    @Override
    public String toString() {
        // TODO: test this
        if(this.statblock == null){
            return "newTimeout(" + name + "," + time + ");\n";
        }
        return "MyTimer t = newSpecialTimeout(" + name + "," + time + ");"
                + "t.timer = new Timer(timeToFire, new ActionListener(){\n"
                + "@Override\n public void actionPerformed(ActionEvent e)" + statblock + "});"
                + "\n" + "t.started = System.currentTimeMillis();\n"
                + " t.timer.start();\n";
    }

    @Override
    public void testType() {
        //nothing to test here
    }

}
