/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

/**
 * represents an import statement; each import statement will cause the imported
 * .rudi file to be parsed and translated. While parsing the imported file,
 * rudimant will remember the variables from the original file, but the
 * namespace of the imported file will not be visible from the original file
 * thereafter.
 *
 * @author Anna Welker
 */
public class StatImport extends RTLeaf {

  // IMPORT VARIABLE SEMICOLON
  String name;

  public StatImport(String text) {
    this.content = text;
    if (text.contains(".")) {
      name = text.substring(text.lastIndexOf("."));
    } else {
      name = text;
    }
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }
  
  @Override
  public String visitStringV(RTStringVisitor v){
    return v.visitNode(this);
  }

  public String toString() { return "import " + content; }
}
