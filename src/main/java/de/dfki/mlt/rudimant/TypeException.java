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
public class TypeException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public TypeException(String ex) {
    super(ex);
  }
}
