package de.dfki.mlt.agent;

import static pal.Constants.TECSDB_SERVER_IP_PROP;
import static pal.Constants.TECSDB_SERVER_PORT_PROP;

import java.io.IOException;
import java.net.UnknownHostException;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import pal.Utils;

/**
 * Hello world!
 *
 */
public class App {

  /** Generate a unique id for the speech acts */
  public static String generateId() {
    return Long.toString(System.currentTimeMillis()
        + (int) (Math.random() * 64));
  }


  /** This triggers the reasoner to produce a reaction after some event or
   *  information came from somewhere.
   *
   * What about things that time out? We could possibly use sleep() functions
   * for that
   *
   *
   * private void triggerReactionReasoner(String lastPercept) {};
   *
  public static SpeechActEvent createSAEvent(String speechAct, String proposition,
      String from, String to, String ... arguments) {
    if ((arguments.length & 1) != 0) {
      throw new IllegalArgumentException("Odd number in key/value pair spec"
          + "for creating speech act");
    }
    Map<String, String> args = new HashMap<>();
    for (int i = 0; i < arguments.length; i += 2) {
      args.put(arguments[i], arguments[i+1]);
    }

    return new SpeechActEvent(speechAct, proposition,
        generateId(), from, to, args);
  }
  */


  /** event types that match:
   * request --> confirm
   * offer --> acceptOffer, declineOffer
   * TODO: WHAT ELSE ????
   */
  static boolean referringSpeechactType(String type, String lastType) {
    return (((lastType.equals("Request") || lastType.equals("YNQuestion"))
             && type.endsWith("onfirm"))
            || (lastType.equals("Offer") && type.endsWith("Offer")
                && type.length() > lastType.length())
            || (lastType.equals("WHQuestion") && type.equals("Inform"))) ;
  }

  private static String[] necessaryProperties = {
      // TECSSERVER_IP_PROP, TECSSERVER_PORT_PROP,
      TECSDB_SERVER_IP_PROP, TECSDB_SERVER_PORT_PROP,
  };

  public static void main( String[] args )
      throws InterruptedException, UnknownHostException, IOException {
    //BasicConfigurator.configure();
    Utils.loadEnvironment(necessaryProperties);
    
    String QUIZ_CONFIG_FILE_NAME_PROP = "config.quiz";
    String LANGUAGE_PROP = "gmg.language";

    if (System.getProperty(QUIZ_CONFIG_FILE_NAME_PROP) == null) {
      System.setProperty(QUIZ_CONFIG_FILE_NAME_PROP, "share/quiz.properties");
    }
    String [] necprop = {};
    Utils.readGlobalProperties(QUIZ_CONFIG_FILE_NAME_PROP, necprop);
    
    String tecsDBserverIP = System.getProperty(TECSDB_SERVER_IP_PROP);
    int tecsDBserverPort = Integer.parseInt(System.getProperty(TECSDB_SERVER_PORT_PROP));
    String language = pal.Utils.getProperty(LANGUAGE_PROP, "language ");
    
    final String grammarDir = "grammars/cplanner/";
    // AsrTts.logger.setLevel(Level.INFO);
    Logger.getLogger("ps.PSClient").setLevel(Level.ERROR);
    String [][] generationGrammars = {
        { "dut", "dutch/allrules" },
        { "eng", "english/allrules" },
        { "ita", "italianLF/allrules_lf" },
    };
    for (String[] gram : generationGrammars) {
      System.setProperty("generation.project." + gram[0],
          grammarDir + gram[1]);
    }

    System.setProperty("mapper.project",
        grammarDir + "mapper/allrules-integrated");

    String ip = args[0];
    int port = 0;
    try {
      port = Integer.parseInt(args[1]);
    }
    catch (NumberFormatException ex) {
      System.out.println("port must be a number");
      System.exit(1);
    }

    Agent myAgent = new NaoAgent(ip, port);
    myAgent.init(tecsDBserverIP, tecsDBserverPort, language);

    myAgent.startListening();

  }
}
