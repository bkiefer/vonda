/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

import static de.dfki.mlt.rudimant.Visualize.normalizeSpaces;
import static de.dfki.mlt.rudimant.Visualize.parseAndTypecheck;
import static de.dfki.mlt.rudimant.Visualize.setUp;
import java.io.FileNotFoundException;
import java.io.StringWriter;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;
import static de.dfki.mlt.rudimant.abstractTree.TstUtils.RESOURCE_DIR;

/**
 *
 * @author anna
 */
public class TestTypes {

  static int prefix = 678, suffix = 5;

  public static String getGeneration(String in) {
    StringWriter out = new StringWriter();
    parseAndTypecheck(in, out);
    StringBuffer sb = out.getBuffer();
    return normalizeSpaces(sb.subSequence(prefix, sb.length() - suffix).toString());
  }

  static String header = "label: if(true) {";
  static String footer = "}";

  @BeforeClass
  public static void setUpClass() throws FileNotFoundException {
    setUp(RESOURCE_DIR + "dipal/dipal.yml", header, footer);
  }

  @Test
  public void testType1(){
    String in = "DialogueAct reply = myLastDA().copy();";
    String r = getGeneration(in);
    assertEquals("DialogueAct reply = (DialogueAct) myLastDA().copy(); ", r);
  }

  @Test
  public void testType2(){
    String in = "Rdf turn = getCurrentTurn(activity);";
    String r = getGeneration(in);
    assertEquals("Rdf turn = getCurrentTurn(activity); ", r);
  }

//  @Test
//  public void testType3(){
//    String in = "propose(\"continue_quiz\") {\n" +
//              "      Rdf turn = getCurrentTurn(activity);}";
//    String r = getGeneration(in);
//    System.out.println(r);
//    assertEquals("propose(\"continue_quiz\", new Proposal() {"
//            + "public void run() {"
//            + "Rdf turn = getCurrentTurn(activity) ; }});"
//            , r);
//  }

  @Test
  public void testType4(){
    String in = "int correct = q.getWhichCorrect();";
    String r = getGeneration(in);
    assertEquals("int correct = (int) q.getWhichCorrect(); ", r);
  }
}
