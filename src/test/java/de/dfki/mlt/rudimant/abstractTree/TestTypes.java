/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

import static de.dfki.mlt.rudimant.Visualize.*;
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
    setUpEmpty();
  }

  @Test
  public void testType1(){
    String in = "DialogueAct reply = myLastDA().copy();";
    String r = generate(in);
    String expected = "DialogueAct reply = (DialogueAct) myLastDA().copy();";
    assertEquals(expected, getForEmptyAssign(r, expected));
  }

  @Test
  public void testType2(){
    String in = "Rdf turn = getCurrentTurn(activity);";
    String r = generate(in);
    String expected = "Rdf turn = getCurrentTurn(activity);";
    assertEquals(expected, getForEmptyAssign(r, expected));
  }

  @Test
  public void testType3(){
    String in = "propose(\"continue_quiz\") {\n" +
              "      Rdf turn = getCurrentTurn(activity);}";
    String r = generate(in);
    String expected = "propose(\"continue_quiz\",new Proposal() {"
            + "public void run() {"
            + "Rdf turn = getCurrentTurn(activity); }});";
    assertEquals(expected, getForEmptyAssign(r, expected));
  }

  @Test
  public void testType4(){
    String in = "int correct = q.getWhichCorrect();";
    String r = generate(in);
    String expected = "int correct = (int) q.getWhichCorrect();";
    assertEquals(expected, getForEmptyAssign(r, expected));
  }

  @Test
  public void testReturnSetType() {
    String in = "Child c; docs = c.isTreatedBy;";
    String r = generate(in);
    String expected = "Set<Object> docs = ((Set<Object>)c.getValue(\"<dom:isTreatedBy>\"));";
    assertEquals(expected, getForEmptyAssign(r, expected));
  }

  @Test
  public void testLambdaExp() {
    String in = "Set<Child> cs; cs.contains((c) -> c.foreName.equals(\"John\"));";
    String r = generate(in);
    String expected = "cs.contains((c) -> "
            + "((Set<Object>)c.getValue(\"foreName\")).equals(\"John\"));";
    assertEquals(expected, getForEmptyAssign(r, expected));
  }

}
