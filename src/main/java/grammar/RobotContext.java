/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grammar;

import java.util.List;

/**
 *
 * @author anna
 */
public interface RobotContext {
  
  /**
   * method invoked by main before inserting the class name
   * @return a string to be printed to the beginning of the file
   */
  public String beforeClassName();
  
  /**
   * method invoked by main after inserting the class name and before
   * the parse tree is walked
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
   * method that will test whether the given arguments are of the correct type
   * @param arguments
   * @return true if types are right
   */
  public boolean testFunctionArguments(List<Type> arguments);
  
  /**
   * method that will give you the return type of a function
   * @param functionname
   * @return the return type of the function with name functionname
   */
  public Type getFunctionReturn(String functionname);
  
  /**
   * method that can say whether a field is existing or not
   * @param access
   * @return true if this access is valid
   */
  public boolean existsFieldAccess(String access);
  
  /**
   * method that finds the type of a field
   * @param access
   * @return the type of this field
   */
  public Type getFieldType(String access);
}
