/*
 * To change license header, choose License Headers in Project Properties.
 * To change template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

import java.util.Collections;

/**
 * represents an import statement; each import statement will cause the imported
 * .rudi file to be parsed and translated. While parsing the imported file,
 * rudimant will remember the variables from the original file, but the
 * namespace of the imported file will not be visible from the original file
 * thereafter.
 *
 * @author Anna Welker
 */
public class Import extends RudiTree {

  // IMPORT VARIABLE SEMICOLON
  String content;
  String name;

  public Import(String text) {
    content = text;
    int lastDotPos = text.lastIndexOf(".");
    name = (lastDotPos >= 0 ? text.substring(lastDotPos) : text);
  }

  public String toString() { return "import " + content; }

  public Iterable<? extends RudiTree> getDtrs() {
    return Collections.emptyList();
  }

  @Override
  public String visitStringV(RTStringVisitor v) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void visitVoidV(VGenerationVisitor v) {
    // TODO Auto-generated method stub

  }
}
