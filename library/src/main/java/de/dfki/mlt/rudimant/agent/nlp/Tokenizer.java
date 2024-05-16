package de.dfki.mlt.rudimant.agent.nlp;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Tokenizer extends NLProcessor {
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
   Tokenizer tok = _tokenizers.get(currentLang);
   if (tok == null) {
     tok = LanguageServices.createNLProcessor(configDir, currentLang, langConfig);
     if (tok != null) {
       _tokenizers.put(currentLang, tok);
     }
   }
   return tok;
 }

  // TODO: eventually change String[] to Token[] to be able to pass on additional
  // information
  public abstract String[] tokenize(String input);

  @SuppressWarnings("rawtypes")
  public abstract boolean init(File configDir, String language, Map config);
}
