package de.dfki.mlt.rudimant.agent.nlp;

import static de.dfki.mlt.rudimant.common.Configs.*;
import static com.ibm.icu.text.CaseMap.toLower;

import java.io.File;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

public class TrivialTokenizer extends Tokenizer {

  private Pattern punctuationRegex;

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
    String punctRegex = "[.?!;,]";
    if (config.containsKey(CFG_TOK_PUNCTUATION_REGEX)) {
      punctRegex = (String)config.get(CFG_TOK_PUNCTUATION_REGEX);
    }
    punctuationRegex = Pattern.compile(punctRegex);
    return true;
  }

  public String preprocess(String input) {
    if (toLower) {
      input = toLower().apply(Locale.getDefault(), input);
    }
    if (removePunctuation) {
      input = punctuationRegex.matcher(input).replaceAll("");
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
