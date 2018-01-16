/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.compiler.tree;

import static de.dfki.mlt.rudimant.compiler.Visualize.*;
import static de.dfki.mlt.rudimant.compiler.tree.TestUtilities.*;
import static org.junit.Assert.*;

import org.junit.*;

import de.dfki.mlt.rudimant.compiler.tree.ExpVariable;
import de.dfki.mlt.rudimant.compiler.tree.RudiTree;
import de.dfki.mlt.rudimant.compiler.tree.StatReturn;

/**
 *
 * @author sophie
 */
public class StatementTest {
  @BeforeClass
  public static void setUpClass() {
    setUpNonEmpty();
  }

  @Test
  public void StatementTest1() {
    String in = "Object foo;"
            + "while (foo.slot == 1){foo.slot = 2;}";
    String r = generate(in);
    String expected = "Object foo;while (foo.slot == 1){ foo.slot = 2; }";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void StatementTest2(){
    String in = " List<String> slots = { \"Hobby\", \"Color\" };"
            + " for (String slot : slots){}";
    String r = generate(in);
    String expected = "List<String> slots = new ArrayList<>();slots.add(\"Hobby\"); "
        + "slots.add(\"Color\"); "
        + "for (Object slot_outer : slots) { String slot = (String)slot_outer; { } }";
    assertEquals(expected, getForMarked(r, expected));
    // TODO: make this work again, it was nicer
//    assertEquals("List<String> slots = new ArrayList<>();slots.add(\"Hobby\"); "
//        + "slots.add(\"Color\"); "
//        + "for (String slot_outer : slots) { String slot = (String)slot_outer; {}}", r);
  }


  @Test
  public void StatementTest3(){
    String in = "Object foo;  if (foo.slot == 1){"
            + "foo.slot = 2;} else {foo.slot = 1;}";
    String r = generate(in);
    String expected = "Object foo;if (foo.slot == 1) { foo.slot = 2; } else { foo.slot = 1; }";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void StatementTest4(){
    String in = "Object foo;  "
            + "do {foo.slot = 2;} while (foo.slot == 1)";
    String r = generate(in);
    String expected = "Object foo;do{ foo.slot = 2; } while (foo.slot == 1);";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void StatementTest5(){
    // explanation: the statement "int i;" is supposed to tell rudimant that
    // the variable i already exists somewhere
    String in = "int i;  i = 1; return i;";
    String r = generate(in);
    String expected = "int i;i = 1;return i;";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void StatementTest5Iterator(){
    String in = "int i;  i = 1; return i;";
    RudiTree dtr = getNodeOfInterest(parseAndTypecheck(in), 2);
    assertTrue(dtr instanceof StatReturn);
    assertTrue(((StatReturn)dtr).getDtrs().iterator().next()
        instanceof ExpVariable);
  }

 @Test
 public void StatementTest6(){
   // TODO: the empty statement after case is not wrong, but it is kind of funny
   String in = "int i; boolean truth; "
           + "switch(truth){case (truth): i=2;  case(truth==false): i=1;}";
    String r = generate(in);
    String expected = "int i;boolean truth;switch (truth){ case (truth):; "
            + "i = 2;case (truth==false):; i = 1; }";
    assertEquals(expected, getForMarked(r, expected));
 }

 @Test
 public void StatementTest7(){
   String in = "List<String> a = {};";
    String r = generate(in);
    String expected = "List<String> a = new ArrayList<>();";
    assertEquals(expected, getForMarked(r, expected));
 }

 @Test
  public void StatementTestx(){
    String in = "Object foo;  "
            + "for (int i = 1; i <= 2; i = i+1){foo.slot = 1;}";
   String r = generate(in);
   String expected = "Object foo;for ( int i = 1;i <= 2;i = (i+1)){ foo.slot = 1; }";
   assertEquals(expected, getForMarked(r, expected));
 }

 @Test
  public void TimeoutTest(){
   String in = "time = 2; timeout(\"label\", time) { i = 4; }";
   String r = generate(in);
   String expected = "int time = 2;"
       + "newTimeout(\"label\",time,new Proposal() {public void run() { int i = 4; } });";
   assertEquals(expected, getForMarked(r, expected));
 }

 @Test
 public void IfTest1(){
   String in = "String s = \"bla\"; if (s == null) {}";
   String r = generate(in);
   String expected = "String s = \"bla\";if (s == null) {";
   assertEquals(expected, getForMarked(r, expected));
 }

 @Test
 public void IfTest2(){
   String in = "String s = \"bla\"; if (s != null) {}";
   String r = generate(in);
   String expected = "String s = \"bla\";if (s != null) {";
   assertEquals(expected, getForMarked(r, expected));
 }

 @Test
 public void IfTest3(){
   String in = "String s = \"bla\"; if (s) {}";
   String r = generate(in);
   String expected = "String s = \"bla\";if (exists(s)) {";
   assertEquals(expected, getForMarked(r, expected));
 }

 @Test
 public void SwitchTest(){
   String in = "switch(5 % 3) { case 0: return true; case 1: return false;}";
   String r = generate(in);
   String expected = "switch ((5%3)){ case 0:; return true; case 1:; return false;";
   assertEquals(expected, getForMarked(r, expected));
 }

 @Test
 public void AssignConditionTest(){
   String in = "Child user; Agent agent; Robot I_ROBOT; agent = (agent == user) ? (Agent)I_ROBOT : (Agent)user;";
   String r = generate(in);
   String expected = "Rdf user;Rdf agent;Rdf I_ROBOT;agent = ((agent.equals(user)) ? ((Rdf)I_ROBOT) : ((Rdf)user));";
   assertEquals(expected, getForMarked(r, expected));
 }

 @Test
 public void StatementTest8(){
   String in = " Child user; n = user.name; List<String> names = { n };";
   String r = generate(in);
   String expected = "Rdf user;Set<Object> n = ((Set<Object>)user.getValue(\"<upper:name>\"));"
       + "List<String> names = new ArrayList<>();names.add(n);";
   assertEquals(expected, getForMarked(r, expected));
 }

//   @Test
//   public void StatementTestxy(){
//     String in = "Object foo; for (int i = 1; i <= 2; ++i){foo.slot = 1;}";
//     String r = getGeneration(in);
//     assertEquals("for (int i = 1; i <= 2; ++i) {foo.slot =1;}", r);
//  }
//
//  @Test
//  public void StatementTestxx(){
//    String in = "Object foo; for (int i = 1; i <= 2; i++){foo.slot = 1;}";
//    String r = getGeneration(in);
//    assertEquals("", r);
//  }
}
