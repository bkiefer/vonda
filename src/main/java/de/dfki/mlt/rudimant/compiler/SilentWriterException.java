package de.dfki.mlt.rudimant.compiler;

public class SilentWriterException extends RuntimeException {

  private static final long serialVersionUID = 2015869475359840193L;

  public SilentWriterException(Exception ex) {
    super(ex);
  }
}
