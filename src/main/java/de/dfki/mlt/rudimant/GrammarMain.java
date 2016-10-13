/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant;

import static de.dfki.mlt.rudimant.Constants.CFG_LOG;
import static de.dfki.mlt.rudimant.Constants.CFG_ONTOLOGY_FILE;
import static de.dfki.mlt.rudimant.Constants.CFG_OUTPUT_DIRECTORY;
import static de.dfki.mlt.rudimant.Constants.CFG_SERVER_HOST;
import static de.dfki.mlt.rudimant.Constants.CFG_SERVER_PORT;
import static de.dfki.mlt.rudimant.Constants.CFG_TYPE_CHECK;
import static de.dfki.mlt.rudimant.Constants.CFG_TYPE_ERROR_FATAL;
import static de.dfki.mlt.rudimant.Constants.CFG_WRAPPER_CLASS;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.thrift.TException;
import org.apache.thrift.transport.TTransportException;
import org.yaml.snakeyaml.Yaml;

import com.google.googlejavaformat.java.FormatterException;

import de.dfki.lt.hfc.WrongFormatException;
import de.dfki.lt.hfc.db.client.HfcDbClient;
import de.dfki.lt.hfc.db.rdfProxy.RdfProxy;
import joptsimple.OptionException;
import joptsimple.OptionParser;
import joptsimple.OptionSet;

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

  private Map<String, Object> configs;

  private RudimantCompiler rc;
  // rdf functionality

  private RdfProxy startClient()
      throws IOException, WrongFormatException, TException {
    HfcDbClient client = new HfcDbClient();
    client.init((String) configs.get(CFG_SERVER_HOST), (int) configs.get(CFG_SERVER_PORT));
    /*
    String ontoFileName = (String) configs.get(CFG_ONTOLOGY_FILE);
    if (ontoFileName == null) {
      throw new IOException("Ontology file is missing.");
    }
    client.readConfig(new File(ontoFileName));
    */
    return new RdfProxy(client._client);
  }

  public void initCompiler() throws IOException, WrongFormatException, TException {
    rc = new RudimantCompiler((String)configs.get(CFG_WRAPPER_CLASS), startClient());
    rc.setLog((boolean)(configs.get(CFG_LOG) == null ? false : configs.get(CFG_LOG)));
    rc.setThrowExceptions((boolean)configs.get(CFG_TYPE_ERROR_FATAL));
    rc.setTypeCheck((boolean)configs.get(CFG_TYPE_CHECK));
  }

  public void process(List files, File outputDirectory)
      throws IOException, FormatterException, WrongFormatException, TException {
    if (! outputDirectory.exists()) {
      if (! outputDirectory.mkdirs()) {
        throw new IOException("Output directory could not be created: "
            + outputDirectory.getAbsolutePath());
      }
    } else {
      if (! outputDirectory.isDirectory()) {
        throw new IOException("Output directory is not a directory: "
            + outputDirectory.getAbsolutePath());
      }
    }
    for (Object file : files) {
      rc.process(new File((String) file), outputDirectory);
    }
  }

  /** For unit tests */
  public void setConfig(Map<String, Object> conf) {
    configs = conf;
  }

  public Map<String, Object> defaultConfig() {
    configs = new LinkedHashMap<String, Object>();
    configs.put(CFG_SERVER_HOST, "localhost");
    configs.put(CFG_SERVER_PORT, 9000);
    configs.put(CFG_LOG, false);
    configs.put(CFG_TYPE_ERROR_FATAL, true);
    configs.put(CFG_TYPE_CHECK, true);
    return configs;
  }

  /**
   *
   * @param args: the file that should be parsed without ending (in args[0])
   *             + the complete path of the configfile
   * @throws Exception
   */
  public static void main(String[] args) throws IOException, TTransportException, FileNotFoundException, WrongFormatException, TException, FormatterException {
    // BasicConfigurator.resetConfiguration();
    // BasicConfigurator.configure();

    OptionParser parser = new OptionParser("hledr:w:c:p:o:");
    parser.accepts("help");
    OptionSet options = null;

    GrammarMain main = new GrammarMain();
    Map<String, Object> configs;
    int port = 9000;
    File outputDirectory = null;
    List files = null;

    try {
      options = parser.parse(args);
      files = options.nonOptionArguments();

      if (files.isEmpty()) {
        usage("Input file is missing");
      }

      outputDirectory = new File((String)files.get(0)).getParentFile();

      if (options.has("help") || options.has("h")) {
        System.out.println(help);
        System.exit(0);
      }
      if (options.has("c")) {
        Yaml yaml = new Yaml();
        configs = (Map<String, Object>)
            yaml.load(new FileReader((String)options.valueOf("c")));
      } else {
        configs = main.defaultConfig();
      }

      if (options.has("o")) {
        configs.put(CFG_OUTPUT_DIRECTORY, (String) options.valueOf("o"));
      }
      if (options.has("l")) {
        configs.put(CFG_LOG, true);
      }
      if (options.has("e")) {
        configs.put(CFG_TYPE_ERROR_FATAL, false);
      }
      if (options.has("d")) {
        configs.put(CFG_TYPE_CHECK, false);
      }
      if (options.has("w")) {
        configs.put(CFG_WRAPPER_CLASS, (String)options.valueOf("w"));
      }
      if (options.has("r")) {
        configs.put(CFG_ONTOLOGY_FILE, (String)options.valueOf("r"));
      }
      if (options.has("p")) {
        port = Integer.parseInt((String)options.valueOf("p"));
      } else {
        if (configs.containsKey(CFG_SERVER_PORT)) {
          Object p = configs.get(CFG_SERVER_PORT);
          if (p instanceof Integer) {
            port = (Integer)p;
          } else if (p instanceof String) {
            port = Integer.parseInt((String) p);
          }
        }
      }
      configs.put(CFG_SERVER_PORT, port);
      main.setConfig(configs);
    } catch (OptionException ex) {
      usage("Error parsing options: " + ex.getLocalizedMessage());
      System.exit(1);
    } catch (NumberFormatException nex) {
      usage("Argument of -p (port) must be a number");
      System.exit(1);
    }
    main.initCompiler();
    main.process(files, outputDirectory);
  }

  private static void usage(String message) {
    System.out.println(message);
    System.out.println("Please use this tool as follows: "
        + "java rudimant <directory_to_be_searched/> <output_directory/>? OPTIONS\n"
        + "For help see rumdimant -help\n");
    System.exit(-1);
  }

}
