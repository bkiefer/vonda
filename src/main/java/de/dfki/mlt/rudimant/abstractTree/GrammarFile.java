/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

import java.util.List;

import de.dfki.mlt.rudimant.GrammarMain;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Objects;

/**
 * class that represents a top-level file that was handed over to GrammarMain (=
 * the root of the whole tree)
 *
 * @author Anna Welker
 */
public class GrammarFile extends RudiTree {

  // imports* (comment grammar_rule | method_declaration | statement )* comment
  List<RudiTree> rules;
  static Writer out;
  String classname;

  public GrammarFile(List<RudiTree> rules) {
    this.rules = rules;
  }

  public void setClassName(String name) {
    this.classname = name;
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }

  @Override
  public int hashCode() {
    int hash = 5;
    hash = 41 * hash + Objects.hashCode(this.rules);
    hash = 41 * hash + Objects.hashCode(this.classname);
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
    final GrammarFile other = (GrammarFile) obj;
    if (!Objects.equals(this.classname, other.classname)) {
      return false;
    }
    if (!Objects.equals(this.rules, other.rules)) {
      return false;
    }
    return true;
  }

}
