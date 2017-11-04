package de.dfki.mlt.rudimant.agent.nlg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.ibm.icu.text.RuleBasedNumberFormat;

import de.dfki.lt.tr.dialogue.cplan.*;
import de.dfki.lt.tr.dialogue.cplan.BatchTest.BatchType;
import de.dfki.lt.tr.dialogue.cplan.functions.FunctionFactory;

public class CPlannerNlg {

  UtterancePlanner _planner;

  Logger logger = Logger.getLogger(CPlannerNlg.class);

  public RuleBasedNumberFormat numberFormatter;

  /**
   * Constructor: get a new natural language generation component with built-in
   * content planner.
   *
   * @param pluginDirectory a File object that must point to a directory. Jar
   * files in this directory will be loaded, Function implementations in these
   * files will be registered for use in the grammar of the content planner.
   * Will throw an IllegalArgumentException if it does not point to a directory
   * @param projectFile a File object that points to a content planner project
   * file, containing all information on how to load the grammar for the content
   * planner. This may also contain a setting that loads additional plug-ins.
   * @param language a valid three character language identifier to initialize a
   * number to text converter
   */
  public CPlannerNlg(File pluginDirectory, File projectFile, String language)
          throws FileNotFoundException, IOException {
    _planner = new UtterancePlanner() {
      /** Load all things contained in the configuration in the right way */
      protected void load() {
        // initHierachy();
        /** First load the plugins, then the rules */
        loadPlugins();
        loadRules();
      }
    };

    _planner.readProjectFile(projectFile);

    if (null != pluginDirectory) {
      FunctionFactory.registerPlugins(pluginDirectory, _planner);
    }
    numberFormatter = new RuleBasedNumberFormat(new Locale(language),
            RuleBasedNumberFormat.SPELLOUT);
  }

  /** Constructor: get a new natural language generation component with built-in
   *  content planner.
   *  @param projectFile a File object that points to a content planner project
   *         file, containing all information on how to load the grammar for
   *         the content planner. This may also contain a setting that loads
   *         additional plug-ins.
   *  @param language a valid three character language identifier to initialize
   *         a number to text converter
   */
  public CPlannerNlg(File projectFile, String language)
  throws FileNotFoundException, IOException {
    this(null, projectFile, language);
  }

  private static final Pattern punctRegex =
    Pattern.compile("\\s*(?:[;:,.?]\\s*)*([;:,.?])");

  /** Replace multiple punctuation characters, possibly separated by
   *  whitespace, by only the rightmost in the sequence
   */
  public static String fixPunctuation(String text) {
    return punctRegex.matcher(text).replaceAll("$1");
  }

  private static final Pattern numberRegex =
      Pattern.compile("(\\A|\\s)(\\d+)(\\Z|\\s|\\.(?:\\D|\\Z)|[;:,?])");

  private static String numbersToText(String in, RuleBasedNumberFormat formatter) {
    Matcher m = numberRegex.matcher(in);
    StringBuffer sb = new StringBuffer();
    while (m.find()) {
      String numberString = formatter.format(Integer.parseInt(m.group(2)));
      m.appendReplacement(sb, m.group(1) + numberString + m.group(3));
    }
    m.appendTail(sb);
    String result = sb.toString();
    return result.replaceAll("\u00ad", "");
  }

  /** Replace numbers in input string by text using icu4j functionality
   *  @param in the string containing numbers to convert
   *  @param language a valid three character language identifier to initialize
   *         a number to text converter
   */
  public static String numbersToText(String in, String language) {
    return numbersToText(in, new RuleBasedNumberFormat(new Locale(language),
        RuleBasedNumberFormat.SPELLOUT));
  }

  /** Replace numbers in input string by text using icu4j functionality in the
   *  language of this interface
   */
  public String numbersToText(String in) {
    return numbersToText(in, numberFormatter);
  }

  List<DagNode> emptyRealizations = new ArrayList<DagNode>();

  public ProtoLf toDag(String stringRepresentation) {
    DagNode input = DagNode.parseLfString(stringRepresentation);
    if (input == null ||
        ! DagNode.parseLfString(input.toString()).equals(input)) {
      logger.error("String representation differs from parsed : ["
          + stringRepresentation + "] [" + input + "]");
    }
    return new ProtoLf(input);
  }

  public void logGenerator() {
    _planner.setTracing(new LoggingTracer(RuleTracer.ALL));
  }

  public String realise(ProtoLf plf) {
    logger.trace("Input to LF parsing: " + plf);
    DagNode input = plf.getDag();
    if (input == null) {
      input = DagNode.parseLfString(plf.toString());
      if (input == null ||
          ! DagNode.parseLfString(input.toString()).equals(input)) {
        logger.error("String representation differs from parsed : ["
            + plf + "] [" + input + "]");
      }
    }
    logger.trace("Input to content planning: " + input);
    DagNode cplanOutput = _planner.process(input);
    // handle canned text output.
    logger.trace("Output of content planning: " + cplanOutput);
    DagEdge edge = cplanOutput.getEdge(DagNode.TYPE_FEAT_ID);
    String result = "";
    if (edge != null && edge.getValue().getTypeName().equals("canned")){
      edge = cplanOutput.getEdge(DagNode.getFeatureId("string"))
        .getValue().getEdge(DagNode.PROP_FEAT_ID);
      result = edge.getValue().getTypeName();
      result = numbersToText(fixPunctuation(result)).trim();
      if ("<empty>".equals(result)) {
        result = "";
      } else if (result == null || result.isEmpty()) {
        if (!emptyRealizations.contains(input)) {
          emptyRealizations.add(input);
          logger.warn("empty realization (canned): i[" + plf + "] o["
                  + cplanOutput + "]");
        }
      } else {
        logger.trace("Canned text: " + result);
      }
    } else {
      // TODO: CHECK IF TO REACTIVATE
      // result = _planner.realize(cplanOutput).trim();
      if (result == null || result.isEmpty()) {
        if (! emptyRealizations.contains(input)) {
          emptyRealizations.add(input);
        }
        logger.warn("empty realization: i[" + plf + "] o[" +
            cplanOutput + "]");
      } else {
        logger.trace("Output of realization proper: " + result);
      }
    }
    return result;
  }

  /*
  public BatchTest batchProcess(File file, BatchType generation)
          throws IOException {
    return _planner.batchProcess(file, generation);
  }

  public CcgUtterancePlanner getPlanner() {
    return _planner;
  }
   */
}
