package de.dfki.chatcat.util;

public class Date {
  public static String timeOfDay() {
    java.util.Date d = new java.util.Date();
    int h = d.getHours();
    if (h < 11) return "morning";
    else if (h < 13) return "midday";
    else if (h < 19) return "afternoon";
    return "evening";
  }
}
