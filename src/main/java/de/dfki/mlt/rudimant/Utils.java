package de.dfki.mlt.rudimant;

public class Utils {
  public static String capitalize(String s) {
    return s.substring(0,1).toUpperCase() + s.substring(1);
  }

  public static String lowerCaseFirst(String s) {
    return s.substring(0, 1).toLowerCase() + s.substring(1);
  }
}
