package de.dfki.mlt.rudimant.abstractTree;

import static de.dfki.mlt.rudimant.Visualize.*;
import static org.junit.Assert.*;
import static de.dfki.mlt.rudimant.abstractTree.TstUtils.*;

import org.junit.*;

public class TestMethodDeclaration {

  @BeforeClass
  public static void setUpClass() {
    setUpEmpty();
  }

  @Test
  public void test() {
    String methdecl = "void foo() { i = 1; }";

    String s = generate(methdecl);
    s = normalizeSpaces(s);
    String expected = "void foo() {int i = 1; }} ";
    assertEquals(expected, s.substring(s.length() - expected.length()));
  }

}
