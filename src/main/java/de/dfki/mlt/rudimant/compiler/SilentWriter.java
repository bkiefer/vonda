package de.dfki.mlt.rudimant.compiler;

import java.io.IOException;
import java.io.Writer;

public class SilentWriter {
  private Writer _out;

  public SilentWriter(Writer toDelegate) {
    _out = toDelegate;
  }

  public SilentWriter append(char c) {
    try {
      _out.append(c);
    } catch (IOException ex) {
      throw new SilentWriterException(ex);
    }
    return this;
  }

  public SilentWriter append(CharSequence c) {
    try {
      _out.append(c);
    } catch (IOException ex) {
      throw new SilentWriterException(ex);
    }
    return this;
  }

  public void flush() {
    try {
      _out.flush();
    } catch (IOException ex) {
      throw new SilentWriterException(ex);
    }
  }

  public void close() {
    try {
      _out.close();
    } catch (IOException ex) {
      throw new SilentWriterException(ex);
    }
  }
}
