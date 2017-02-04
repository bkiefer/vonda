package de.dfki.mlt.rudimant.agent;

import java.io.IOException;
import java.util.regex.Pattern;

import de.dfki.mlt.rudimant.agent.nlg.InfoStateAccess;
import de.dfki.mlt.rudimant.agent.nlg.LanguageGenerator;
import de.dfki.mlt.rudimant.agent.nlg.Pair;
import de.dfki.lt.tr.dialogue.cplan.DagNode;

public class AsrTts {
  // public static final Logger logger = Logger.getLogger("asrlogger");

  /**
   * Connection to ASR & TTS used in HySociaTea
   */
  /**
   * The language generation engine
   */
  private LanguageGenerator _generator;

  public void loadGrammar(String language, Agent agent, String confFile)
          throws IOException {

    //language = "english";
    _generator = LanguageGenerator.getGenerator(language, confFile);
    _generator.registerAccess("general", new InfoStateAccess(agent));
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

  /**
   * Transform the semantic representation of the best hypothesis into a speech
   * act event and return it.
   *
   * @param utt
   * @return the speech act event corresponding to the semantic content in the
   * best hypothesis
   *
   * public static SpeechActEvent utterance2SpeechActEvent(UserTextFeedback utt)
   * { // TODO: DO PARSING OF TEXT MESSAGE Map<String, String> tokens = new
   * HashMap<>(); SpeechActEvent sa = new SpeechActEvent(); sa.arguments = new
   * HashMap<String, String>(); for (Map.Entry<String, String> keyVal :
   * tokens.entrySet()) { switch (keyVal.getKey()) { case "speechact":
   * sa.speechact = keyVal.getValue(); break; case "proposition": sa.proposition
   * = keyVal.getValue(); break; case "sender": sa.sender = keyVal.getValue();
   * break; case "addressee": sa.addressee = keyVal.getValue(); break; case
   * "id": sa.id = App.generateId(); break; default:
   * sa.arguments.put(keyVal.getKey(), keyVal.getValue()); break; } } if
   * (!sa.isSetId()) { sa.setId(App.generateId()); } return sa; }
   */
}
