package de.dfki.mlt.rudimant.compiler;

import de.dfki.mlt.rudimant.common.Position;

public class Token {
  private String s;
  private Position start, end;

  public Token(String s, Position start, Position  end) {
    this.s = s;
    this.start = start;
    this.end = end;
  }

  public String getText() { return this.s; }

  public Position getStart() { return start; }

  public Position getEnd() { return end; }

  @Override
  public String toString() {
    return '<' + s + '>' + '[' + start + '|' + end + ']';
  }

  public String content(){
    String c = getText();
    // Deal with java code comments
    return (c.startsWith("/*@")) ? c.substring(3, c.length() - 3) : c;
  }
}