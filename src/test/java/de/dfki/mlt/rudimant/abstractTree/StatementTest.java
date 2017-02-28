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
 * @author sophie
 */
public class StatementTest {
  @BeforeClass
  public static void setUpClass() {
    setUpNonEmpty();
  }

  @Test
  public void StatementTest1() {
    String in = "Object foo; while (foo.slot == 1){foo.slot = 2;}";
    String r = getGeneration(in);
    assertEquals("while ((foo.slot == 1)){ foo.slot = 2; }", r);
  }

  @Test
  public void StatementTest2(){
    String in = "List<String> slots = { Hobby, Color }; for (String slot : slots){}";
    String r = getGeneration(in);
    assertEquals("List<String> slots = new ArrayList<>();slots.add(\"Hobby\"); slots.add(\"Color\"); for (String slot: slots) {}", r);
  }
  
  
  @Test
  public void StatementTest3(){
    String in = "Object foo; if (foo.slot == 1){foo.slot = 2;} else {foo.slot = 1;}";
    String r = getGeneration(in);
    assertEquals("if ((foo.slot == 1)) { foo.slot = 2; }else { foo.slot = 1; }", r);
  }
  
  @Test
  public void StatementTest4(){
    String in = "Object foo; do {foo.slot = 2;} while (foo.slot == 1)";
    String r = getGeneration(in);
    assertEquals("do{ foo.slot = 2; }while ((foo.slot == 1));", r);
  }
  
  // not supported yet; not sure whether this is syntactically correct
//  @Test
//  public void StatementTest5(){
//    String in = "propose(\"hello\"){};";
//    String r = getGeneration(in);
//    assertEquals("", r);
//  }
 
// @Test
// public void StatementTest6(){
//   String in = "int i; boolean truth; switch(truth){case (truth): i=2;  case(truth==false): i=1;}";
//   String r = getGeneration(in);
//   assertEquals("switch (truth){case(truth):; i = 2; case(truth==false):; i = 1; }", r);
// }
 // @Test
 //  public void StatementTestx(){
 //    String in = "Object foo; for (int i = 1; i <= 2; i++){foo.slot = 1;}";
 //    String r = getGeneration(in);
 //    assertEquals("for (int i = 1; i <= 2; i++){foo.slot = 1;}", r);
//  }
}
