package de.dfki.mlt.rudimant.agent;

public class ColorLogger extends DefaultLogger {

  private static final String RED="\033[31m";
  private static final String GREEN="\033[32m";
  private static final String GRAY="\033[37m";
  private static final String RES="\033[m";

  private void printInColor(String s, String color) {
    print(color + s + RES);
  }

  @Override
  protected void printTerm(String term, boolean value, boolean shortCut) {
    printInColor(term, shortCut ? GRAY : value ? GREEN : RED);
  }

  @Override
  protected void printResult(String label, boolean value) {
    printInColor(label + ": ", value ? GREEN : RED);
  }

}
