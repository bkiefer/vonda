/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi;

import java.io.File;
import java.io.IOException;

/**
 * the main class of rudimant
 *
 * @author Anna Welker
 */
public class GrammarMain {

  private static String help = "Hello, this is rudimant.\n"
          + "Currently, the following flags are available:\n"
          + "-log\tTranscribe file in logmode. Text will be added so that "
          + "the outcome of\n"
          + "\tall boolean expressions is being logged as "
          + "soon as they are evaluated.\n"
          + "-e\tDo not crash if there are .rudi files that cannot be translated\n"
          + "-nt\tDo not try to do type checking while translating\n"
          + "\n\nPlease use this tool as follows:\n"
          + "java rudimant <directory_to_be_searched/> [output_directory/] (flags)\n";

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

    int i = 0;
    if (args.length == 0 || args[0].equals("-help")) {
      System.out.println(help);
      System.exit(0);
    }
    if (args.length == 0 || args[0].startsWith("-")) {
      System.out.println("Please use this tool as follows: java rudimant <directory_to_be_searched/> <output_directory/>? (-log)\n"
              + "For help see rumdimant -help\n");
      System.exit(-1);
    }
    String inputDirectory = args[0];
    RudimantCompiler rc = new RudimantCompiler();

    for (String arg : args) {
      i++;
      if (i <= 1) {
        continue;
      }
      switch (arg) {
        case "-log":
          rc.setLog(true);
          break;
        case "-e":
          rc.setThrowExceptions(false);
          break;
        case "-nt":
          rc.setTypeCheck(false);
          break;
        default:
          if (arg.startsWith("-")) {
            System.out.println(help);
          }
      }
    }

    File dir = new File(inputDirectory);
    File outputDir;

    if(!args[1].startsWith("-")){
      outputDir = new File(args[1]);
      if(outputDir.isDirectory()){
        rc.process(dir, outputDir);
      }
    }

    rc.process(dir);

  }

}
