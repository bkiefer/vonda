/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

import static de.dfki.mlt.rudimant.Visualize.*;
import static visitortests.SeriousTest.RESOURCE_DIR;
import static org.junit.Assert.*;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;

import org.junit.BeforeClass;
import org.junit.Test;

import de.dfki.lt.hfc.WrongFormatException;
import static de.dfki.mlt.rudimant.abstractTree.TestCast.getGeneration;

/**
 *
 * @author sophie
 */
public class StatementTest {
    static int prefix = 678, suffix = 5;
    static String header = "label: if(true) {";
    static String footer = "}";
      @BeforeClass
  
  public static void setUpClass() throws FileNotFoundException {
    setUp(RESOURCE_DIR + "dipal/dipal.yml", header, footer);
  }
  
  @Test
  public void StatementTest1() throws IOException, WrongFormatException {
    String in = "Object foo; while (foo.slot == 1){foo.slot = 2;}";
    String r = getGeneration(in);
    assertEquals(
        "while ((foo.slot == 1)){ foo.slot = 2; }",
        r);
  }

  // why does this not work?
//  @Test
//  public void StatementTest2() throws IOException, WrongFormatException {
//    String in = "Object foo; for (int i = 1; i <= 2; i++){foo.slot = 1;}";
//    String r = getGeneration(in);
//    assertEquals("for (int i = 1; i <= 2; i++){foo.slot = 1;}", r);
//  }
}
