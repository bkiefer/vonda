package de.dfki.chatcat;

import static de.dfki.chatcat.Constants.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.thrift.transport.TTransportException;
import org.yaml.snakeyaml.Yaml;

import de.dfki.chatcat.ui.GUI;
import de.dfki.chatcat.ui.Reaction;
import de.dfki.lt.hfc.WrongFormatException;
import joptsimple.OptionException;
import joptsimple.OptionParser;
import joptsimple.OptionSet;

/**
 * The main class to start the ReCo / Socrates test environment
 */
public class Main {
  public static Map<String, Object> configs;
  public static File confDir;

  private static void interactive(final StubClient client) {
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        final GUI qw = new GUI();
        qw.initializeComponents();
        // connect to client
        qw._react = new Reaction(client, qw._chat, qw._statusbar);
        qw._react.execute();
      }
    });
  }

  final static Object [][] defaults = {
      { CFG_VISUALISE, false , "v" },
      { CFG_ONTOLOGY_FILE, "src/main/resources/ontology/chatcat.ini", "o" },
  };

  public static Map<String, Object> defaultConfig() {
    configs = new LinkedHashMap<String, Object>();
    for (Object[] pair : defaults) {
      configs.put((String)pair[0], pair[1]);
    }
    return configs;
  }

  static Object getDefault(String key) {
    Object result = null;
    for (Object[] pair : defaults) {
      if (key.equals(pair[0])) {
        result = pair[1];
        break;
      }
    }
    return result;
  }

  static void setDefault(String key, Map<String, Object> configs) {
    if (! configs.containsKey(key)) {
      configs.put(key, getDefault(key));
    }
  }

  @SuppressWarnings("unchecked")
  public static Map<String, Object> readConfig(String confname)
      throws FileNotFoundException {
    Yaml yaml = new Yaml();
    File confFile = new File(confname);
    confDir = confFile.getParentFile();
    return (Map<String, Object>) yaml.load(new FileReader(confFile));
  }

  public static void main(String[] args)
      throws TTransportException, IOException, WrongFormatException, InterruptedException {
    //BasicConfigurator.configure();

    OptionParser parser = new OptionParser("c:");
    // parser.accepts("help");
    OptionSet options = null;

    //List files = null;
    confDir = new File(".");
    String confName = "config.yml";

    try {
      options = parser.parse(args);
      //files = options.nonOptionArguments();
      if (options.has("c")) {
        confName = (String)options.valueOf("c");
      }
      configs = (confName != null) ? readConfig(confName) : defaultConfig();
    } catch (OptionException ex) {
      usage("Error parsing options: " + ex.getMessage());
    }

    StubClient stub = new StubClient();
    stub.init(confDir, configs);
    stub.startListening();

    interactive(stub);
  }

  private static void usage(String message) {
    System.out.println(message);
    System.out.println("[-c confFile]");
  }
}
