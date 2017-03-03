/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

import static de.dfki.mlt.rudimant.abstractTree.TstUtils.*;
import static org.junit.Assert.*;

import org.junit.*;

/**
 *
 * @author anna
 */
public class TestTypes {

  @BeforeClass
  public static void setUpClass() {
    setUpNonEmpty();
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

  @Test
  public void testType3(){
    String in = "propose(\"continue_quiz\") {\n" +
              "      Rdf turn = getCurrentTurn(activity);}";
    String r = getGeneration(in);
    System.out.println(r);
    assertEquals("propose(\"continue_quiz\", new Proposal() {"
            + "public void run() {"
            + "Rdf turn = getCurrentTurn(activity); }});"
            , r);
  }

  @Test
  public void testType4(){
    String in = "int correct = q.getWhichCorrect();";
    String r = getGeneration(in);
    assertEquals("int correct = (int) q.getWhichCorrect(); ", r);
  }
}
