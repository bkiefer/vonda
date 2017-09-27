package de.dfki.mlt.rudimant.agent;

import static de.dfki.mlt.rudimant.agent.nlg.ConfConstants.*;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dfki.lt.tr.dialogue.cplan.DagNode;
import de.dfki.mlt.rudimant.agent.nlg.*;

public class AsrTts {
  public static final Logger logger = LoggerFactory.getLogger(AsrTts.class);

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
      //_generator.registerAccess("general", new InfoStateAccess(agent));
      // see below
    }

    if (nluConfig != null && nluConfig.containsKey(language)) {
      _interpreter = Interpreter.getInterpreter(configDir, language,
          (Map)nluConfig.get(language));
    }
  }

  public void registerInfoStateAccess(String what, BaseInfoStateAccess acc) {
    _generator.registerAccess(what, acc);
  }

  private static final Pattern toEscape = Pattern.compile("[^0-9a-zA-Z_-]");

  private static String stringify(String in) {
    if (in.isEmpty()) return in;
    if ('"' == in.charAt(0) && '"' == in.charAt(in.length() - 1))
      in = in.substring(1, in.length() - 1);
    if (toEscape.matcher(in).find()) {
      if (in.charAt(0) == '"') in = '\\' + in;
      in = in.replaceAll("([^\\\\])\\\"", "\\1\\\\\"");
      in = '"' + in + '"';
    }
    return in;
  }

  public static String toRawSpeechAct(String dialogueAct, String proposition,
      String ... args) {
    StringBuilder sb = new StringBuilder();
    sb.append(dialogueAct) //.toLowerCase())
      .append('(').append(proposition); // .toLowerCase());
    int len = args.length;
    if ((len & 1) != 0) {
      StringBuilder sbb = new StringBuilder();
      sbb.append(sb);
      for (String s : args) sbb.append("|").append(s);
      sbb.append(')');
      // length is odd! Illegal!
      logger.error("Odd number of arguments for constructing raw speechact! {}",
          sbb.toString());
      len -= 1;
    }
    for(int i = 0; i < args.length; i += 2) {
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
