package de.dfki.mlt.agent.nlg;

public class Pair<KEYTYPE, VALUETYPE> {
  public KEYTYPE first;
  public VALUETYPE second;

  public Pair(KEYTYPE theFirst, VALUETYPE theSecond) {
    first = theFirst;
    second = theSecond;
  }
  
  public String toString() {
    return "<"+first+"|"+second+">";
  }
}
