package de.dfki.mlt.rudimant.agent.nlg;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dfki.mlt.rudimant.agent.DialogueAct;

/* *************************************************************
 * A factory and superclass for all interpreters
 * ************************************************************ */
public abstract class Interpreter {

  protected static Logger logger = LoggerFactory.getLogger(Interpreter.class);

  private static Map<String, Interpreter> _interpreters
  = new HashMap<String, Interpreter>();

  /** Factory method to get a language analyser for the given config.
  *
  * @param configDir   the directory where the config file is (for relative paths)
  * @param currentLang which language should be treated
  * @param langConfig  the configuration to create a new analyser
  * @return a new Interpreter for the given language, or null in case
  *         of failure.
  * @throws FileNotFoundException
  */
  @SuppressWarnings("rawtypes")
  public static Interpreter getInterpreter(File configDir, String currentLang,
      Map<String, Object> langConfig) {
    Interpreter singleton = _interpreters.get(currentLang);
    if (singleton == null) {
      String parserClassName = (String)langConfig.get("class");
      try {
        Class parserClass = Class.forName(parserClassName);
        singleton = (Interpreter)parserClass.newInstance();
        if (singleton != null
            && singleton.init(configDir, currentLang, langConfig))
          _interpreters.put(currentLang, singleton);
      }
      catch (ClassNotFoundException | InstantiationException |
          IllegalAccessException | IllegalArgumentException |
          SecurityException ex) {
        logger.error("No valid interpreter class for: {}", parserClassName);
        singleton = null;
      }
    }
    return singleton;
  }

  @SuppressWarnings("rawtypes")
  public abstract boolean init(File configDir, String language, Map config);

  public abstract DialogueAct analyse(String text);

}
