package de.dfki.mlt.rudimant.compiler.tree;

import static de.dfki.mlt.rudimant.compiler.Visualize.generate;
import static de.dfki.mlt.rudimant.compiler.tree.TestUtilities.*;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class VarDeclarationTest {

  @BeforeClass
  public static void setUpClass() {
    setUpNonEmpty();
  }

  @Test
  public void testPOD() {
    String decl = " int i; if (true) { i = i + 1; }";
    String s = generate(decl);
    String expected = "int i;if (true) { i = (i+1); }";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void testRdf() {
    String decl = " Child i; if (true) { i = new Child; }";
    String s = generate(decl);
    String expected_dom = "Rdf i;if (true) { i = _proxy.getClass(\"<dom:Child>\").getNewInstance(DEFNS); }";
    String marked = getForMarked(s, expected_dom);
    marked = marked.replaceAll("<edu:", "<dom:");
    assertEquals(expected_dom, marked);
  }

}
