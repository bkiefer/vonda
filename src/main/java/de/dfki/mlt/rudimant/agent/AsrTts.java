package de.dfki.mlt.rudimant.agent;

import static de.dfki.mlt.rudimant.agent.nlg.ConfConstants.*;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.regex.Pattern;

import de.dfki.lt.tr.dialogue.cplan.DagNode;
import de.dfki.mlt.rudimant.agent.nlg.InfoStateAccess;
import de.dfki.mlt.rudimant.agent.nlg.Interpreter;
import de.dfki.mlt.rudimant.agent.nlg.LanguageGenerator;
import de.dfki.mlt.rudimant.agent.nlg.Pair;

public class AsrTts {
  // public static final Logger logger = Logger.getLogger("asrlogger");

  /**
   * Connection to ASR & TTS used in HySociaTea
   */
  /**
   * The language generation engine
   */
  private LanguageGenerator _generator;

  private Interpreter _interpreter;


  @SuppressWarnings({ "rawtypes", "unchecked" })
  public void loadGrammar(File configDir, String language, Agent agent, Map configs)
      throws IOException {

    Map nlgConfig = (Map)configs.get(NLG_KEY);
    Map nluConfig = (Map)configs.get(NLU_KEY);

    if (nlgConfig  != null && nlgConfig.containsKey(language)) {
      //language = "english";
      _generator = LanguageGenerator.getGenerator(configDir, language,
          (Map<String, Object>)nlgConfig.get(language));
      _generator.registerAccess("general", new InfoStateAccess(agent));
    }

    if (nluConfig != null && nluConfig.containsKey(language)) {
      _interpreter = Interpreter.getInterpreter(configDir, language,
          (Map)nluConfig.get(language));
    }
  }

  private static final Pattern toEscape = Pattern.compile("[^0-9a-zA-Z_-]");

  private static String stringify(String in) {
    if (in.isEmpty()) {
      return in;
    }
    if ('"' == in.charAt(0) && '"' == in.charAt(in.length() - 1)) {
      in = in.substring(1, in.length() - 1);
    }
    if (toEscape.matcher(in).find()) {
      if (in.charAt(0) == '"') {
        in = '\\' + in;
      }
      in = in.replaceAll("([^\\\\])\\\"", "\\1\\\\\"");
      in = '"' + in + '"';
    }
    return in;
  }

  public static String toRawSpeechAct(String dialogueAct, String proposition,
          String... args) {
    StringBuilder sb = new StringBuilder();
    sb.append(dialogueAct) //.toLowerCase())
            .append('(').append(proposition); // .toLowerCase());
    for (int i = 0; i < args.length; i += 2) {
      sb.append(", ").append(args[i]).append('=').append(stringify(args[i + 1]));
    }
    sb.append(')');
    return sb.toString();
  }

  public DagNode toDag(String rawSpeechAct) {
    return _generator.toDag(rawSpeechAct);
  }

  public Pair<String, String> generate(DagNode saEvent) {
    //logger.info("raw:" + saEvent);
    Pair<String, String> toSay = _generator.getSurfaceFormExtendedLf(saEvent);
    //logger.info("text: " + toSay);
    return toSay;
  }

  public DialogueAct interpret(String text) {
    if (_interpreter != null) {
      return _interpreter.analyse(text);
    }
    return null;
  }
}
