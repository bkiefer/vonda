package de.dfki.mlt.rudimant.agent.nlp;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Tokenizer  {
  protected static Logger logger = LoggerFactory.getLogger(Interpreter.class);

  /** This maps from languages to interpreters */
  private static Map<String, Tokenizer> _tokenizers
  = new HashMap<String, Tokenizer>();

  /** Factory method to get a tokenizer for the given config.
  *
  * @param configDir   the directory where the config file is (for relative paths)
  * @param currentLang which language should be treated
  * @param langConfig  the configuration to create a new analyser
  * @return a new Tokenizer for the given language, or null in case
  *         of failure.
  */
 public static Tokenizer getTokenizer(File configDir, String currentLang,
     Map<String, Object> langConfig) {
   Tokenizer singleton = _tokenizers.get(currentLang);
   if (singleton == null) {
     singleton = createTokenizer(configDir, currentLang, langConfig);
     if (singleton != null) {
       _tokenizers.put(currentLang, singleton);
     }
   }
   return singleton;
 }

 /** Factory method to get a language analyser for the given config.
  *
  * @param configDir   the directory where the config file is (for relative paths)
  * @param currentLang which language should be treated
  * @param langConfig  the configuration to create a new analyser
  * @return a new Tokenizer for the given language, or null in case
  *         of failure.
  */
 @SuppressWarnings({ "unchecked", "rawtypes" })
 public static Tokenizer createTokenizer(File configDir, String currentLang,
     Map<String, Object> langConfig) {
   String parserClassName = (String)langConfig.get("class");
   Tokenizer inter = null;
   try {
     Class parserClass = Class.forName(parserClassName);
     inter = (Tokenizer)parserClass.getConstructor().newInstance();
     if (inter != null && ! inter.init(configDir, currentLang, langConfig))
       return null;
   }
   catch (ClassNotFoundException | InstantiationException |
       IllegalAccessException | IllegalArgumentException |
       SecurityException | InvocationTargetException | NoSuchMethodException ex) {
     logger.error("No valid interpreter class for: {}", parserClassName);
   }
   return inter;
 }

  // TODO: eventually change String[] to Token[] to be able to pass on additional
  // information
  public abstract String[] tokenize(String input);

  @SuppressWarnings("rawtypes")
  public abstract boolean init(File configDir, String language, Map config);
}
