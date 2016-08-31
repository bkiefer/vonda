/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi.abstractTree.statements;

import de.dfki.mlt.rudi.abstractTree.AbstractStatement;
import de.dfki.mlt.rudi.abstractTree.AbstractTree;
import java.io.IOException;
import java.io.Writer;

/**
 * representation of sth like a += b where a is a set and b should be added
 * (also works with -, i.e. remove)
 *
 * @author Anna Welker
 */
public class ASetOperation implements AbstractStatement, AbstractTree{

    private AbstractTree left;
    boolean add;
    private AbstractTree right;

    public ASetOperation(AbstractTree left, boolean add, AbstractTree right) {
        this.left = left;
        this.add = add;
        this.right = right;
    }

    @Override
    public void generate(Writer out) throws IOException{
        if(add){
            this.left.generate(out);
            out.append(".add(");
            this.right.generate(out);
            out.append(");");
        }
        else{
            this.left.generate(out);
            out.append(".remove(");
            this.right.generate(out);
            out.append(");");
        }
    }

    @Override
    public void testType() {
        // TODO: test somehow
    }

}
