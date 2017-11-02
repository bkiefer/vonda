/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.compiler;

import java.io.IOException;

/**
 * to catch an ioexception, recatch and throw it without catching other
 * runnables
 *
 * @author Anna Welker, anna.welker@dfki.de
 */
public class InOutException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  InOutException(IOException ex) {
    super(ex);
  }

}
