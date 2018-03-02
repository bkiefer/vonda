package de.dfki.mlt.rudimant.compiler;

public class Position implements Comparable<Position> {

  public int line;
  public int column;
  public int charpos;
  public String msg;

  public Position(int line, int column, int charpos, String msg) {
    this.line = line;
    this.column = column;
    this.charpos = charpos;
    this.msg = msg;
  }

  @Override
  public boolean equals(Object o) {
    if (! (o instanceof Position)) return false;
    Position p2 = (Position) o;
    return line == p2.line && column == p2.column && msg.equals(p2.msg);
  }

  @Override
  public int hashCode() {
    return line << 10 + column;
  }

  @Override
  public String toString() {
    return msg + ":" + line + ":" + column;
  }

  @Override
  public int compareTo(Position o) {
    if (charpos >= 0) return charpos - o.charpos;
    int res = line - o.line;
    if (res != 0) return res;
    return column - o.column;
  }
}
