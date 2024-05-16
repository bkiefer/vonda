package de.dfki.mlt.rudimant.agent.nlp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

public class EvalSrgs {
  private static Logger logger = LoggerFactory.getLogger(EvalSrgs.class);

  PrintWriter hits;
  PrintWriter misses;

  SrgsParser srgs = new SrgsParser();

  private static class Section {
    private Section(String i, String e) {
      intent = i;
      examples = getExamples(e);
    }

    String intent;

    List<String> examples;

    private List<String> getExamples(String examplesString) {
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

    @SuppressWarnings("unchecked")
    public static List<Section> readCorpus(File f) throws FileNotFoundException {
      Yaml yaml = new Yaml();
      Map<String, Object> rasaCorpus = yaml.load(new FileReader(f));
      List<LinkedHashMap<String, String>> sections =
          (List<LinkedHashMap<String, String>>) rasaCorpus.get("nlu");
      List<Section> result = new ArrayList<>();
      for (Map<String, String> e : sections) {
        String intent = e.get("intent");
        String exString = e.get("examples");
        result.add(new Section(intent, exString));
      }
      return result;
    }
  }

  public void loadSrgs(File configDir, Map<String, Object> config) {
    logger.info("Loading grammar from {}", config.get("grammar"));
    String language = (String)config.get("language");
    if (language == null) {
      language = "en_EN";
    }
    if (!srgs.init(configDir, language, config)) {
      logger.error("SRGS grammar loading failed");
      throw new RuntimeException();
    }
  }

  private DialogueAct parse(String input) {
    return srgs.analyse(input);
  }

  private static final Pattern ANNOT_REGEX = Pattern.compile(
      "\\[([^]]*)\\]\\([^)]*\\)");

  private static String removeAnnotations(String input) {
    Matcher m = ANNOT_REGEX.matcher(input);
    String result = m.replaceAll("$1");
    return result.replaceAll("\"", "");
  }

  @SuppressWarnings("unchecked")
  void evaluate(File configFile) throws FileNotFoundException {
    File configDir = configFile.getAbsoluteFile().getParentFile();
    Yaml yaml = new Yaml();
    Map<String, Object> configs =
        (Map<String, Object>) yaml.load(new FileReader(configFile));
    loadSrgs(configDir, configs);
    List<String> corpora = (List<String>)configs.get("corpora");
    for (String corpName : corpora) {
      File corpus = new File(corpName);
      if (! corpus.isAbsolute()) {
        corpus = new File(configDir, corpName);
      }
      parseCorpus(configDir, corpus);
    }
  }

  void parseCorpus(File rootDir, File corpusFile) throws FileNotFoundException {

    // list of Map<String, Object>
    // intent: string
    // examples: list of strings with annotations
    List<Section> corpus = Section.readCorpus(corpusFile);
    int tokens = 0;
    int recall = 0;
    int precision_int = 0;
    int precision_frame = 0;
    int precision_theme = 0;
    String corpName = corpusFile.getName();
    int dot;
    if ((dot = corpName.indexOf('.')) > 0) {
      corpName = corpName.substring(0, dot);
    }
    try (PrintWriter hits = new PrintWriter(new File(rootDir, corpName + "_hits.txt"));
        PrintWriter misses = new PrintWriter(new File(rootDir, corpName + "_misses.txt"))) {
      for (Section section : corpus) {
        String[] complex_intent = section.intent.split("_");
        List<String> examples = section.examples;
        for (String input : examples) {
          ++tokens;
          // remove role annotations
          // send it through the SRGS parser
          DialogueAct result = parse(input);
          if (result != null) {
            String intent = "";
            String theme = "";
            String frame = "";
            ++recall;
            // check if it matches complex_intent
            if (result.getDialogueActType().equals(complex_intent[0])) {
              ++precision_int;
            } else {
              intent = complex_intent[0];
            }
            // check if it matches the frame
            if (result.getProposition() != null && complex_intent.length > 1) {
              if (result.getProposition().equals(complex_intent[1])) {
                ++precision_frame;
              } else {
                frame = " [" + complex_intent[1] + "]";
              }
            } else {
              if (complex_intent.length == 1) {
                ++precision_frame;
              } else {
                theme = "NO_FRAME";
              }
            }
            // check if it matches the theme
            if (result.hasSlot("theme") && complex_intent.length > 2) {
              if (result.getValue("theme").equals(complex_intent[2])) {
                ++precision_theme;
              } else {
                theme = " <" + complex_intent[2] + ">";
              }
            } else {
              if (complex_intent.length == 2) {
                ++precision_theme;
              } else {
                theme = "NO_THEME";
              }
            }
            hits.format("%s : %s  %s%s%s\n", input, result, intent, frame, theme);
          } else {
            misses.format("%s\n", input);
          }
        }
      }
    }
    System.out.println(String.format("recall: %d (%f%%)", recall,
        recall * 100.0 / tokens));
    System.out.println(String.format("prec int: %d (%f%%)", precision_int,
        precision_int * 100.0 / recall));
    System.out.println(String.format("prec frame: %d (%f%%)", precision_frame,
        precision_frame * 100.0 / recall));
    System.out.println(String.format("prec theme: %d (%f%%)", precision_theme,
        precision_theme * 100.0 / recall));
  }

  public static void main(String[] args) throws FileNotFoundException {
    EvalSrgs e = new EvalSrgs();
    if (args.length == 0) {
      System.out.println("Usage: eval_nlu configfile.yml");
      System.out.println("       the configfile.yml needs a \"corpus\" property "
          + "to specify the test corpus");
      System.exit(1);
    }
    e.evaluate(new File(args[0]));
  }
}
