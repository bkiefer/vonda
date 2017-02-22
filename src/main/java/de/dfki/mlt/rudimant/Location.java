/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant;

/**
 *
 * @author Anna Welker, anna.welker@dfki.de
 */
public class Location {
  String originClass;
  int lineNumber;

  public Location(String originClass, int lineNumber) {
    this.originClass = originClass;
    this.lineNumber = lineNumber;
  }

  public String getOriginClass() {
    return originClass;
  }

  public int getLineNumber() {
    return lineNumber;
  }

  public String toString() {
    return originClass + ".rudi:" + lineNumber + ":";
  }
}
