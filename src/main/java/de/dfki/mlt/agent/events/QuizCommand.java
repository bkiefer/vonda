package de.dfki.mlt.agent.events;

import de.dfki.mlt.agent.Event;

public class QuizCommand implements Event {
  
  public QuizCommand(int id, String q, String b){}
  public QuizCommand(){}

  public String getType() { return null; }

  public Object getSource() { return null; }

  public String getAsker(){
    return null;
  }
  
  public String getCommand(){
    return null;
  }
  
  public void setId(int id){}
  
  public void setCommand(String s){}
  
  public void setAsker(String s){}
  
}
