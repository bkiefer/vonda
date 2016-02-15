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
  public Type getFuctionReturn(String functionname);
}
