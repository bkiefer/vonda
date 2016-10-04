/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant;

import com.google.googlejavaformat.java.FormatterException;
import de.dfki.lt.hfc.WrongFormatException;
import de.dfki.lt.hfc.db.HfcDbService;
import de.dfki.lt.hfc.db.client.HfcDbClient;
import de.dfki.lt.hfc.db.rdfProxy.RdfProxy;
import de.dfki.lt.hfc.db.server.HfcDbServer;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import joptsimple.OptionException;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import org.yaml.snakeyaml.Yaml;

import org.apache.thrift.TException;
import org.apache.thrift.transport.TTransportException;

/**
 * the main class of rudimant
 *
 * @author Anna Welker
 */
public class GrammarMain {

  private static String help = "Hello, this is rudimant.\n"
          + "Currently, the following flags are available:\n"
          + "-l\tTranscribe file in logmode. Text will be added so that "
          + "the outcome of\n"
          + "\tall boolean expressions is being logged as "
          + "soon as they are evaluated.\n"
          + "-e\tDo not crash if there are .rudi files that cannot be translated\n"
          + "-d\tDo not try to do type checking while translating\n"
          + "\n\nPlease use this tool as follows:\n"
          + "java rudimant <directory_to_be_searched/> <paht_of_config_file/> OPTIONS\n"
          + "-o=DIRECTORY\tSpecify DIRECTORY as the output directory";

  private static LinkedHashMap<String, Object> configs;

  public static Yaml yaml = new Yaml();

  /**
   *
   * @param args: the file that should be parsed without ending (in args[0]) +
   * the complete path of the configfile
   * @throws Exception
   */
  public static void main(String[] args) throws IOException, TTransportException, FileNotFoundException, WrongFormatException, TException, FormatterException {
    // BasicConfigurator.resetConfiguration();
    // BasicConfigurator.configure();

    if (!args[1].contains("config")) {
      throw new NoConfigException("There was no config file specified; aborting");
    }
//    System.out.println((new File(args[1])).exists());
    configs = (LinkedHashMap<String, Object>) yaml.load(new FileInputStream(args[1]));

    serverConfigs();
//    startServer();
    startClient();

    OptionParser parser = new OptionParser("hledo:");
    parser.accepts("help");
    OptionSet options = null;

    try {
      options = parser.parse(args);
    } catch (OptionException ex) {
      usage("Error parsing options: " + ex.getLocalizedMessage());
    } catch (NumberFormatException nex) {
      usage("Argument of -p (port) must be a number");
    }

    List things = options.nonOptionArguments();

    if (things.isEmpty()) {
      usage("");
    }

    File outputDirectory = null;
    File dir = new File((String) things.get(0));

    if (options.has("help") || options.has("h")) {
      System.out.println(help);
      System.exit(0);
    }
    RudimantCompiler rc = new RudimantCompiler((String) configs.get("wrapperClass"), new RdfProxy(_client));
    if (options.has("l")) {
      rc.setLog(true);
    }
    if (options.has("e")) {
      rc.setThrowExceptions(false);
    }
    if (options.has("d")) {
      rc.setTypeCheck(false);
    }
    if (options.has("o")) {
      outputDirectory = new File((String) options.valueOf("o"));
    }

    //System.out.println("test");
    if (outputDirectory != null) {
      rc.process(dir, outputDirectory);
      return;
    } else {
      if (configs.get("outputDirectory") != null) {
        rc.process(dir, new File((String) configs.get("outputDirectory")));
        return;
      }
      rc.process(dir);
      return;
    }
//    shutdownServer();
  }

  // rdf functionality
  private static String RESOURCE_DIR;

//  private static HfcDbServer server;
  private static HfcDbClient client;
  private static HfcDbService.Client _client;

  // alternative PORTS
  private static int SERVER_PORT;
  private static int WEBSERVER_PORT;

//  /**
//   *
//   * @throws TTransportException
//   * @throws FileNotFoundException
//   * @throws IOException
//   * @throws WrongFormatException
//   */
//  private static void startServer() throws TTransportException, FileNotFoundException, IOException, WrongFormatException {
//    if (server == null) {
//      File config = new File(RESOURCE_DIR + "ontos/pal.ini");
//      server = new HfcDbServer(SERVER_PORT);
//      server.readConfig(config);
//      server.runServer();
//      server.runHttpService(WEBSERVER_PORT);
//    }
//  }
  private static void startClient()
          throws IOException, WrongFormatException, TException {
    if (client == null) {
      client = new HfcDbClient();
      client.init("localhost", SERVER_PORT);
      client.readConfig(new File(RESOURCE_DIR + "rifca/rifca.ini"));
      client.readConfig(new File(RESOURCE_DIR + "ontos/pal.ini"));
      _client = client._client;
    }
  }

//  private static void shutdownServer() {
//    server.shutdown();
//  }
  private static void serverConfigs() {
    RESOURCE_DIR = (String) configs.get("resourceDir");
    SERVER_PORT = (int) configs.get("serverPort");
    WEBSERVER_PORT = (int) configs.get("webserverPort");
  }

  private static void usage(String message) {
    System.out.println(message);
    System.out.println("Please use this tool as follows: java rudimant <directory_to_be_searched/> <output_directory/>? OPTIONS\n"
            + "For help see rumdimant -help\n");
    System.exit(-1);
  }

}
