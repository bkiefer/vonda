/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi;

import java.io.File;
import java.io.IOException;
import java.util.List;
import joptsimple.OptionException;
import joptsimple.OptionParser;
import joptsimple.OptionSet;

import org.apache.log4j.BasicConfigurator;

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
          + "java rudimant <directory_to_be_searched/> [output_directory/] OPTIONS\n"
          + "-o=DIRECTORY\tSpecify DIRECTORY as the output directory";

  // RDF functionality
  private static final String RESOURCE_DIR = "../hfc-database/src/test/resources/";

  // alternative PORTS
  private static final int SERVER_PORT = 8996;
  private static final int WEBSERVER_PORT = 8995;

  /**
   *
   * @param args: the file that should be parsed without ending (in args[0])
   * @throws Exception
   */
  public static void main(String[] args) throws IOException {
    // BasicConfigurator.resetConfiguration();
    // BasicConfigurator.configure();

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
    RudimantCompiler rc = new RudimantCompiler();
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
    if(outputDirectory != null){
        rc.process(dir, outputDirectory);
    }

    rc.process(dir);

  }

  private static void usage(String message) {
    System.out.println(message);
    System.out.println("Please use this tool as follows: java rudimant <directory_to_be_searched/> <output_directory/>? OPTIONS\n"
            + "For help see rumdimant -help\n");
    System.exit(-1);
  }

}
