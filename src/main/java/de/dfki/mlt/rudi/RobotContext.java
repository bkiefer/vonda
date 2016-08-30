/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi;

import java.util.List;


/**
 *
 * @author anna
 */
public interface RobotContext {

  public int getCurrentBool();


  /**
   * method that adds a String to the logging block that will be printed before
   * boolean expressions (only if -log switch was used)
   * @param toLog the String to be logged
   */
  public void doLog(String toLog);

  /**
   * resets the log memory of the context
   * @return a String consisting of every log that toLog received until here
   */
  public String getLog();

  /**
   * method invoked by main before inserting the class name
   * @return a string to be printed to the beginning of the file
   */
  public String beforeClassName();

  /**
   * method invoked by AGrammarFile.toString() after the first comment (what should
   * be the class declaration) is parsed
   * @return a string to be printed directly underneath "public class xy"
   */
  public String afterClassName();

  /**
   * method invoked before the file is closed; if there is anything to be written
   * behind the rules, put it here
   * @return a string to be printed after the parse tree has been walked
   */
  public String atEndOfFile();

}
