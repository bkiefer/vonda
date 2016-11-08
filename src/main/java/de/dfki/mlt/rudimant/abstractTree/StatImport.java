/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

import java.util.Objects;

/**
 * represents an import statement; each import statement will cause the imported
 * .rudi file to be parsed and translated. While parsing the imported file,
 * rudimant will remember the variables from the original file, but the
 * namespace of the imported file will not be visible from the original file
 * thereafter.
 *
 * @author Anna Welker
 */
public class StatImport extends RudiTree {

  // IMPORT VARIABLE SEMICOLON
  String text;
  String name;

  public StatImport(String text) {
    this.text = text;
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
  public int hashCode() {
    int hash = 7;
    hash = 19 * hash + Objects.hashCode(this.text);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final StatImport other = (StatImport) obj;
    if (!Objects.equals(this.text, other.text)) {
      return false;
    }
    return true;
  }

}
