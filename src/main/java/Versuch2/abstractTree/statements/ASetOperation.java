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
 * @author mawo01
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
    public String toString(){
        if(add){
            return this.left + ".add(" + this.right + ");";
        }
        else{
            return this.left + ".remove(" + this.right + ");";
        }
    }
    
    @Override
    public void testType() {
        // TODO: test somehow
    }
    
}
