package de.dfki.mlt.rudimant.agent.nlp;

import static de.dfki.mlt.rudimant.common.Constants.*;

import java.io.File;
import java.util.Map;
import java.util.regex.Pattern;

public class TrivialTokenizer extends Tokenizer {

  // TODO: MAYBE THIS SHOULD BE CONFIGURABLE
  private static Pattern PUNCTUATION = Pattern.compile("[.?!;,]");

  protected boolean toLower = false;

  protected boolean removePunctuation= false;

  @Override
  public String[] tokenize(String text) {
    text = preprocess(text);
    String[] tokens = text.split(" +");
    return tokens;
  }

  @SuppressWarnings("rawtypes")
  @Override
  public boolean init(File configDir, String language, Map config) {
    if (config.containsKey(CFG_TOK_TOLOWER)) {
      toLower = (boolean) config.get(CFG_TOK_TOLOWER);
    }
    if (config.containsKey(CFG_TOK_REMOVE_PUNCTUATION)) {
      removePunctuation = (boolean) config.get(CFG_TOK_REMOVE_PUNCTUATION);
    }
    return true;
  }

  public String preprocess(String input) {
    if (toLower) {
      // TODO: THIS SHOULD USE THE ICU4J FUNCTIONALITY
      input = input.toLowerCase();
    }
    if (removePunctuation) {
      input = PUNCTUATION.matcher(input).replaceAll("");
    }
    return input;
  }

  public void setToLower(boolean value) {
    toLower = value;
  }

  public void setRemovePunctuation(boolean value) {
    removePunctuation = value;
  }
}
