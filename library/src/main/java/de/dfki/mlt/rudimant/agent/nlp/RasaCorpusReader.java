package de.dfki.mlt.rudimant.agent.nlp;

import static de.dfki.mlt.rudimant.agent.nlp.EvalLangServices.makeExample;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.yaml.snakeyaml.Yaml;

import de.dfki.mlt.rudimant.agent.nlp.EvalLangServices.Example;

public class RasaCorpusReader implements CorpusReader {

  List<String> corpora;

  private static final Pattern ANNOT_REGEX = Pattern.compile(
      "\\[([^]]*)\\]\\([^)]*\\)");

  private static String removeAnnotations(String input) {
    Matcher m = ANNOT_REGEX.matcher(input);
    String result = m.replaceAll("$1");
    return result.replaceAll("\"", "");
  }

  private static List<String> getExamples(String examplesString) {
    String[] exlist = examplesString.split("\n");
    List<String> result = new ArrayList<>();
    for (String line : exlist) {
      line = line.trim();
      if (line.isEmpty())
        continue;
      if (line.charAt(0) == '-') {
        line = line.substring(1).trim();
      }
      result.add(removeAnnotations(line));
    }
    return result;
  }


  @Override
  @SuppressWarnings("unchecked")
  public List<Example> readCorpus(File f) throws IOException {
    Yaml yaml = new Yaml();
    Map<String, Object> rasaCorpus = yaml.load(new FileReader(f));
    List<LinkedHashMap<String, String>> sections =
        (List<LinkedHashMap<String, String>>) rasaCorpus.get("nlu");
    List<Example> result = new ArrayList<>();
    for (Map<String, String> e : sections) {
      String intent = e.get("intent");
      String[] complex_intent = intent.split("_");
      String exString = e.get("examples");
      for (String t : getExamples(exString)) {
        result.add(makeExample(t, complex_intent));
      }
    }
    return result;
  }


  @Override
  @SuppressWarnings("unchecked")
  public void init(Map<String, Object> config) {
    corpora = (List<String>)config.get("files");
  }

  @Override
  public String getPrefix() {
    return "rasa";
  }

  @Override
  public Iterable<String> getCorpora() {
    return corpora;
  }

}
