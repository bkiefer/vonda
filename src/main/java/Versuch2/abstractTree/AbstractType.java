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
public enum AbstractType {
  STRING, FLOAT, INT, BOOL, CHAR, OBJECT;
  
  @Override
  public String toString(){
    switch(this.ordinal()){
      case 0: return "String";
      case 1: return "float";
      case 2: return "int";
      case 3: return "boolean";
      case 4: return "char";
      case 5: return "Object";
    }
    return null;
  }
  
}
