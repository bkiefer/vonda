/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

import static de.dfki.mlt.rudimant.Visualize.generate;
import static de.dfki.mlt.rudimant.Visualize.parseAndTypecheck;
import static de.dfki.mlt.rudimant.abstractTree.TstUtils.*;
import static org.junit.Assert.*;

import org.junit.*;

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
    String expected = "while ((foo.slot == 1)){ foo.slot = 2; }";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void StatementTest2(){
    String in = " List<String> slots = { Hobby, Color };"
            + " for (String slot : slots){}";
    String r = generate(in);
    String expected = "List<String> slots = new ArrayList<>();slots.add(\"Hobby\"); "
        + "slots.add(\"Color\"); "
        + "for (Object slot_outer : slots) { String slot = (String)slot_outer; {}}";
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
    String expected = "if ((foo.slot == 1)) { foo.slot = 2; } else { foo.slot = 1; }";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void StatementTest4(){
    String in = "Object foo;  "
            + "do {foo.slot = 2;} while (foo.slot == 1)";
    String r = generate(in);
    String expected = "do{ foo.slot = 2; }while ((foo.slot == 1));";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void StatementTest5(){
    String in = "int i;  i = 1; return i;";
    String r = generate(in);
    String expected = "i = 1; return i;";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void StatementTest5Iterator(){
    String in = "int i;  i = 1; return i;";
    RudiTree dtr = getNodeOfInterest(parseAndTypecheck(in), 2);
    assertTrue(dtr instanceof StatReturn);
    assertTrue(((StatReturn)dtr).getDtrs().iterator().next()
        instanceof ExpUVariable);
  }

 @Test
 public void StatementTest6(){
   String in = "int i; boolean truth; "
           + "switch(truth){case (truth): i=2;  case(truth==false): i=1;}";
    String r = generate(in);
    String expected = "switch (truth){case(truth):; "
            + "i = 2; case(truth==false):; i = 1; }";
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
    String expected = "for ( int i = 1; (i <= 2); i = (i+1)){ foo.slot = 1; }";
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
