/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant;

import org.junit.Test;

import de.dfki.mlt.rudimant.GrammarMain;

/**
 *
 * @author Anna Welker
 */
public class PrintTest {

  @Test
  public void Test() throws Exception {
    // enter here the file whose compilation you'd like to debug
    String[] strings = new String[]{
      "-c", "src/test/resources/tests.yml",
      "src/test/resources/Test.rudi",
      //"../herbea/poc/src/main/rudi/Kaefer.rudi"
    };
    GrammarMain.main(strings);
  }
}
