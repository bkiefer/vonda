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

  // why does this not work?
//  @Test
//  public void StatementTest2() throws IOException, WrongFormatException {
//    String in = "Object foo; for (int i = 1; i <= 2; i++){foo.slot = 1;}";
//    String r = getGeneration(in);
//    assertEquals("for (int i = 1; i <= 2; i++){foo.slot = 1;}", r);
//  }
}
