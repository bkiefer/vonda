package de.dfki.mlt.rudimant.agent.nlp;

import java.io.File;
import java.util.Map;

public abstract class NLProcessor {

  protected String language;

  @SuppressWarnings("rawtypes")
  public boolean init(File configDir, String language, Map config) {
    this.language = language;
    return true;
  }
}
