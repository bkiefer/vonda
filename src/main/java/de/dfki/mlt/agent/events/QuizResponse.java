/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.agent.events;

import de.dfki.mlt.agent.Event;

/**
 *
 * @author pal
 */
public class QuizResponse implements Event {

  public String getType() { return null; }

  public Object getSource() { return null; }
  
  public String getResponse(){
    return null;
  }
  
  public boolean isCorrect(){
    return false;
  }
  
  public String getCategory(){
    return null;
  }
}
