package de.dfki.mlt.rudimant.agent;

import org.apache.thrift.TBase;

@SuppressWarnings("rawtypes")
public class MessageContainer {

  private TBase message;
  private String toWhom = ".*";

  public MessageContainer(final TBase event) {
    message = event;
  }

  public MessageContainer(final String whom, final TBase event) {
    message = event;
    toWhom = whom;
  }

  public TBase getMessage() {
    return message;
  }

  public String getToWhom() {
    return toWhom;
  }

  public String toString() {
    return '<' + toWhom + '|' + message + '>';
  }

}
