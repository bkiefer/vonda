package de.dfki.mlt.rudimant;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static org.junit.Assert.*;

import org.junit.Test;

import de.dfki.mlt.rudimant.agent.nlg.CPlannerNlg;

/**
 *
 * @author Bernd Kiefer
 */
public class NumberTranslateTest {

  @Test
  public void Test() throws Exception {
    String[] langs = { "it", "nl_NL", "en", "de" };
    String[][] results = {
        { "zero", "uno", "due", "tre", "quattro" },
        { "nul", "een", "twee", "drie", "vier" },
        { "zero", "one", "two", "three", "four" },
        { "null", "eins", "zwei", "drei", "vier" } };
    int l = 0;
    for (String lang : langs) {
      for (int i = 0; i < 5; ++i) {
        String res = CPlannerNlg.numbersToText(Integer.toString(i), lang);
        assertEquals(results[l][i], res);
      }
      ++l;
    }

  }
}
