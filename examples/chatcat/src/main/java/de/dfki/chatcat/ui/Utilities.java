/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.chatcat.ui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Christophe Biwer, christophe.biwer@dfki.de
 */
public class Utilities {

  public static String slice_start(String s, int startIndex) {
    if (startIndex < 0) {
      startIndex = s.length() + startIndex;
    }
    return s.substring(startIndex);
  }

  public static String slice_end(String s, int endIndex) {
    if (endIndex < 0) {
      endIndex = s.length() + endIndex;
    }
    return s.substring(0, endIndex);
  }

  public static String slice_range(String s, int startIndex, int endIndex) {
    if (startIndex < 0) {
      startIndex = s.length() + startIndex;
    }
    if (endIndex < 0) {
      endIndex = s.length() + endIndex;
    }
    return s.substring(startIndex, endIndex);
  }

  public static String getTimeStamp() {
    DateFormat dateFormat = new SimpleDateFormat("[yyyy.MM.dd HH:mm:ss]");
    Calendar cal = Calendar.getInstance();
    String timestamp = dateFormat.format(cal.getTime());
    return timestamp;
  }
}
