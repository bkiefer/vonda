/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

import java.util.Objects;

/**
 * represents a single grammar rule located in the .rudi file; each rule
 * will be transformed in a separate .java class (i.e., a separate .java file)
 * @author Anna Welker
 */
public class GrammarRule implements RudiTree{

  // label comment if_statement

  String label;
  UCommentBlock comment;
  StatIf ifstat;
  // remember whether you are toplevel
  boolean toplevel;

  public GrammarRule(String label, UCommentBlock comment,StatIf ifstat,
          boolean toplevel) {
    this.label = label;
    this.ifstat = ifstat;
    this.comment = comment;
    this.toplevel = toplevel;
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }

  @Override
  public int hashCode() {
    int hash = 5;
    hash = 37 * hash + Objects.hashCode(this.label);
    hash = 37 * hash + Objects.hashCode(this.comment);
    hash = 37 * hash + Objects.hashCode(this.ifstat);
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
    final GrammarRule other = (GrammarRule) obj;
    if (!Objects.equals(this.label, other.label)) {
      return false;
    }
    if (!Objects.equals(this.comment, other.comment)) {
      return false;
    }
    if (!Objects.equals(this.ifstat, other.ifstat)) {
      return false;
    }
    return true;
  }

  
}
