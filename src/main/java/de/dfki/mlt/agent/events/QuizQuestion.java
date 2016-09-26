/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.agent.events;

import de.dfki.mlt.agent.Event;
import java.util.Set;

/**
 *
 * @author pal
 */
public class QuizQuestion implements Event {

  public String getType() { return null; }

  public Object getSource() { return null; }
  
  public int getWhichCorrect(){
    return 0;
  }
  
  public String getQuestionText(){
    return null;
  }
  
  public String getAsker(){
    return null;
  }
  
  public Set<String> getAnswers(){
    return null;
  }
}
