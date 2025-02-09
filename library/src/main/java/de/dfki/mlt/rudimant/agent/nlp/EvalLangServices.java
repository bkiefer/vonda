package de.dfki.mlt.rudimant.agent.nlp;

import static de.dfki.mlt.rudimant.common.Configs.CFG_CONFIG_DIRECTORY;
import static de.dfki.mlt.rudimant.common.Configs.readConfig;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EvalLangServices {
  private static Logger logger = LoggerFactory.getLogger(EvalLangServices.class);

  LanguageServices ls;

  int tokens = 0;
  int recall = 0;
  int correct_intent = 0;
  int correct_frame = 0;
  int no_frame = 0;
  int correct_theme = 0;
  int no_theme = 0;

  public static final class Example {
    String text;
    String dialogueAct;
    String frame;
    String theme;
  }

  public static Example makeExample(String text, String[] intent) {
    Example res = new Example();
    res.text = text;
    res.dialogueAct = intent[0];
    if (intent.length > 1) {
      res.frame = intent[1];
      if (intent.length > 2) {
        res.theme = intent[2];
      }
    }
    return res;
  }

  public void loadServices(Map<String, Object> config) {
    logger.info("Loading grammar from {}", config.get("grammar"));
    File configDir = (File)config.get(CFG_CONFIG_DIRECTORY);
    String language = (String)config.get("language");
    if (language == null) {
      language = "en_EN";
    }
    ls = new LanguageServices();
    try {
      ls.loadGrammar(configDir, language, config);
    } catch (Exception ex) {
      logger.error("grammar loading failed: {}", ex.getMessage());
      throw new RuntimeException();
    }
  }

  public DialogueAct parse(String input) {
    return ls.interpret(input);
  }

  public Pair<String, String> generate(DialogueAct input) {
    return ls.generate(input.getDag());
  }

  private void evaluateCorpusFile(PrintWriter results, File corpusFile,
      CorpusReader ce) throws IOException {
    Iterable<Example> examples = ce.readCorpus(corpusFile);

    /*
    String corpName = corpusFile.getName();
    int dot;
    if ((dot = corpName.indexOf('.')) > 0) {
      corpName = corpName.substring(0, dot);
    }
    */
    for (Example ex : examples) {
      String input = ex.text;
      ++tokens;
      // remove role annotations
      // send it through the SRGS parser
      DialogueAct result = parse(input);
      if (result != null && ! Interpreter.NO_RESULT.equals(result)) {
        String intent = "";
        String theme = "";
        String frame = "";
        ++recall;
        // check if it matches complex_intent
        if (result.getDialogueActType().equals(ex.dialogueAct)) {
          ++correct_intent;
        } else {
          intent = "{" + ex.dialogueAct+ "|" + result.getDialogueActType() + "}";
        }
        // check if it matches the frame
        if (result.getProposition() != null) {
          if (result.getProposition().equals(ex.frame)) {
            ++correct_frame;
          } else if ((result.getProposition().equals("<rdf:top>")
              || result.getProposition().equals("top"))
              && ex.frame == null) {
            ++correct_frame;
            ++no_frame;
          } else { 
            frame = " [" + ex.frame + "|" + result.getProposition() +  "]";
          }
        } else if (ex.frame == null) {
          ++correct_frame;
          ++no_frame;
        } else {
          frame = " [" + ex.frame + "|-]";
        }

        // check if it matches the theme
        if (result.hasSlot("theme")) {
          if (result.getValue("theme").equals(ex.theme)) {
            ++correct_theme;
          } else {
            theme = " <" + ex.theme + "|" + result.getValue("theme") +  ">";
          }
        } else if (ex.theme == null) {
          ++correct_theme;
          ++no_theme;
        } else {
          theme = " <" + ex.theme + "|->";
        }

        results.format("%s : %s  %s%s%s%n", input, result, intent, frame, theme);
      } else {
        results.format("%s : NO RESULT%n", input);
      }
    }
  }

  /** Factory method to get a corpus reader for the given config.
   *
   * @param configDir   the directory where the config file is (for relative paths)
   * @param currentLang which language should be treated
   * @param langConfig  the configuration to create a new processor
   * @return a new processor (Interpreter/Generator) for the given language, or
   *         null in case of failure.
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public static <T extends CorpusReader> T getReader(String className) {
    T proc = null;
    try {
      Class clazz = Class.forName(className);
      proc = (T) clazz.getConstructor().newInstance();
    }
    catch (ClassNotFoundException | InstantiationException |
        IllegalAccessException | IllegalArgumentException |
        SecurityException | InvocationTargetException | NoSuchMethodException ex) {
      logger.error("No valid reader class for: {}", className);
    }
    return proc;
  }

  void evaluateCorpora(File configFile) throws IOException {
    Map<String, Object> configs = readConfig(configFile);
    loadServices(configs);

    File configDir = (File)configs.get(CFG_CONFIG_DIRECTORY);
    if (configs.containsKey("corpora")) {
      @SuppressWarnings("unchecked")
      Map<String, Object> corpora = (Map<String, Object>)configs.get("corpora");
      String readerClazz = (String)corpora.get("reader");
      // Instantiate
      CorpusReader cr = getReader(readerClazz);
      cr.init(corpora);
      evaluate(configDir, cr);
    }
  }


  void evaluate(File configDir, CorpusReader evaluator)
      throws IOException {
    String prefix = evaluator.getPrefix();
    try (PrintWriter pw = new PrintWriter(new File(prefix + "results.txt"))) {
      for (String corpName : evaluator.getCorpora()) {
        File corpus = new File(corpName);
        if (! corpus.isAbsolute()) {
          corpus = new File(configDir, corpName);
        }
        evaluateCorpusFile(pw, corpus, evaluator);
      }
    }
    try (PrintWriter eval = new PrintWriter(new File(prefix + "eval.txt"))) {
      eval.format("tokens: %d, recall: %d (%f%%)%n", tokens, recall,
          recall * 100.0 / tokens);
      if (recall > 0) {
        eval.format("correct intent: %d (%f%%)%n", correct_intent,
            correct_intent * 100.0 / recall);
      }
      if (recall - no_frame > 0) {
        eval.format("correct frame: %d (%f%%)%n", correct_frame,
            (correct_frame - no_frame) * 100.0 / (recall - no_frame));
      }
      if (recall - no_theme > 0) {
        eval.format("correct theme: %d (%f%%)%n", correct_theme,
            (correct_theme - no_theme) * 100.0 / (recall - no_theme));
      }
    }
  }


  public static void main(String[] args) throws IOException {
    EvalLangServices e = new EvalLangServices();
    if (args.length == 0) {
      System.out.println("Usage: eval_nlu configfile.yml");
      System.out.println("       the configfile.yml needs a \"corpora:\" property "
          + "to specify the test corpus and corpus reader");
      System.exit(1);
    }
    e.evaluateCorpora(new File(args[0]));
  }
}
