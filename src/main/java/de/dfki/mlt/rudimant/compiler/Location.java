/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.compiler;

/**
 *
 * @author Anna Welker, anna.welker@dfki.de
 */
public class Location {
  String originClass;
  int lineNumber;

  public Location(String origin, int lineNo) {
    originClass = origin;
    lineNumber = lineNo;
  }

  public Location() {}

  public String getOriginClass() {
    return originClass;
  }

  public void setOriginClass(String oc) {
    originClass = oc;
  }

  public int getLineNumber() {
    return lineNumber;
  }

  public void setLineNumber(int ln) {
    lineNumber = ln;
  }

  @Override
  public String toString() {
    return originClass + ".rudi:" + lineNumber + ":";
  }
}
