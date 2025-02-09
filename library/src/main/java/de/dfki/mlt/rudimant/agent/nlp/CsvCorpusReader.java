package de.dfki.mlt.rudimant.agent.nlp;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.dfki.mlt.rudimant.agent.nlp.EvalLangServices.Example;

public class CsvCorpusReader implements CorpusReader {

  private Map<String, Object> config;
  // to map input intents to other (possibly complex) intent names
  Map<String, String> intentmap = new HashMap<String, String>();
  // mandatory, only intent, text, frame and theme expected as keys
  Map<String, String> keyMap;

  String delimiter = ";";

  @Override
  @SuppressWarnings("unchecked")
  public void init(Map<String, Object> conf) {
    config = conf;
    if (config.containsKey("mappings")) {
      intentmap.putAll((Map<String, String>)config.get("mappings"));
    }
    // mandatory, only intent, text, frame and theme expected as keys
    keyMap = (Map<String, String>)config.get("keys");
  }

  @Override
  public String getPrefix() {
    return "csv";
  }

  @SuppressWarnings("unchecked")
  @Override
  public Iterable<String> getCorpora() {
    return (Collection<String>)config.get("files");
  }

  private int index(String[] set, String key) {
    for (int r = 0; r < set.length; ++r) {
      if (set[r].equals(key)) return r;
    }
    return -1;
  }

  @SuppressWarnings("unchecked")
  @Override
  public Iterable<Example> readCorpus(File corpusFile) throws IOException {
    Map<String, String> intentmap = new HashMap<String, String>();
    List<Example> result = new ArrayList<>();
    if (config.containsKey("mappings")) {
      intentmap.putAll((Map<String, String>)config.get("mappings"));
    }
    // mandatory, only intent, text, frame and theme expected as keys
    Map<String, String> keyMap = (Map<String, String>)config.get("keys");
    try (BufferedReader br = Files.newBufferedReader(corpusFile.toPath())) {
      String[] header = br.readLine().split(delimiter);
      // these are mandatory
      String intentKey = keyMap.get("intent");
      if (intentKey == null) {
        throw new IllegalArgumentException(
            "Mandatory field name for dialogue act ('intent') missing");
      }
      int intentIndex = index(header, intentKey);
      if (intentIndex < 0) {
        throw new IllegalArgumentException(
            "Mandatory field name for dialogue act ('intent') wrong: "
                + intentKey);
      }
      String textKey = keyMap.get("text");
      if (textKey == null) {
        throw new IllegalArgumentException(
            "Mandatory field name for input string ('text') missing");
      }
      int textIndex = index(header, textKey);
      if (textIndex < 0) {
        throw new IllegalArgumentException(
            "Mandatory field name for input string ('text') wrong: "
                + textKey);
      }

      // these are optional
      int frameIndex = index(header, keyMap.get("frame"));
      int themeIndex = index(header, keyMap.get("theme"));

      String line;
      while ((line = br.readLine()) != null) {
        String[] row = line.split(delimiter);
        Example res = new Example();
        String intent = row[intentIndex];
        if (intentmap.containsKey(intent)) {
          intent = intentmap.get(intent);
        }
        String[] complex_intent = intent.split("_");
        res.text = row[textIndex];
        res.dialogueAct = complex_intent[0];
        if (complex_intent.length > 1) {
          res.frame = complex_intent[1];
        } else if (frameIndex >= 0 && row[frameIndex] != null
            && ! row[frameIndex].isBlank()) {
          res.frame = row[frameIndex];
        }
        if (complex_intent.length > 2) {
          res.theme = complex_intent[2];
        } else if (themeIndex >= 0 && row[themeIndex] != null
            && ! row[themeIndex].isBlank()) {
          res.theme = row[themeIndex];
        }
        result.add(res);
      }
    }
    return result;
  }

}
