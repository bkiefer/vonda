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
  
  public String getCurrentRule();
  public int getCurrentBool();
  public void setCurrentRule(String rule);
  
  
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
  
  /**
   * method to check whether a variable exists somewhere in the context
   * @param variable the variable
   * @return true if variable is global
   */
  public boolean isGlobalVariable(String variable);
  
  /**
   * method that finds the type of a variable or returns null if there is no such variable
   * @param variable the variable
   * @return the type of this variable
   */
  public String getVariableType(String variable);
  
  /**
   * method that finds the type of a field or returns null if there is no such field
   * @param access the access
   * @return the type of this field
   */
  public String getFieldAccessType(String access);
  
  /**
   * method that finds the type of a function or returns null if there is no such function
   * @param access the access
   * @return the type of this function
   */
  public String getFunctionAccessType(String access);
  
  ////////////////////////////////////////////////////////////////////////////
  /////////////////// following methods still needed? ////////////////////////
  ////////////////////////////////////////////////////////////////////////////
  
  
  /**
   * method that can say whether a field is existing or not
   * @param access the access
   * @return true if this access is valid
   */
  public boolean existsFieldAccess(String access);
  
  /**
   * method that handles variables without field access not declared in the input file
   * (but may be declared somewhere else)
   * i.e. if you declared the variable in this.afterClassName(), just return its name
   * function isn't used atm
   * !!! Attention !!!
   * atm this method will also change function names (which are variables + () )
   * @param variable the variable
   * @return i.e. an existing field access or error if there is no such variable defined
   */
  public String getFullVariableName(String variable);
  
}
