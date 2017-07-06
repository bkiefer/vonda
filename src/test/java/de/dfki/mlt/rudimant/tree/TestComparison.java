/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.tree;

import static de.dfki.mlt.rudimant.Visualize.*;
import static de.dfki.mlt.rudimant.tree.TstUtils.*;
import static org.junit.Assert.*;

import org.junit.*;

/**
 *
 * @author anna
 */
public class TestComparison {

  @BeforeClass
  public static void setUpClass() {
    setUpEmpty();
  }

  @Test
  public void testGenerationComparisonPODPOD() {
    String in = "int i; long j; if (i == j) return true;";
    String r = generate(in);
    String expected = "if ((i == j)) return true;";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testGenerationComparisonPODContainer() {
    String in = "int i; Long j; if (i == j) return true;";
    String r = generate(in);
    String expected = "if ((i == j)) return true;";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testGenerationComparisonStringString() {
    String in = "String i; String j; if (i <= j) return true;";
    String r = generate(in);
    String expected = "if ((i.compareTo(j) <= 0)) return true;";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testGenerationComparisonStringDA() {
    // maybe check all variants of comparison operators in the end
    String in = "String i; if (i <= #Accept(Return)) return true;";
    String r = generate(in);
    String expected = "if ((new DialogueAct(i).subsumes(new DialogueAct(\"Accept\", \"Return\")))) return true;";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testGenerationComparisonStringRdf() {
    String in = "String i; Child j; if (i <= j) return true;";
    String r = generate(in);
    String expected = "if ((i.getClazz().isSubclassOf(j.getClazz()))) return true;";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testGenerationComparisonStringRdfClass() {
    String in = "String j; if (Child <= j) return true;";
    String r = generate(in);
    String expected = "if ((getRdfClass(\"Child\").isSubclassOf(j.getClazz()))) return true;";
    assertEquals(expected, getForMarked(r, expected));
  }

  /** Hmm, not sure if this is possible
  @Test
  public void testGenerationComparisonRdfClassRdfClass() {
    String in = "if (Child >= Object) return true;";
    String r = generate(in);
    String expected = "if ((getRdfClass(\"Child\").isSubclassOf(getRdfClass(\"Object\")))) return true;";
    assertEquals(expected, getForMarked(r, expected));
  }
  */

  @Test
  public void testGenerationComparisonRdfRdf() {
    // other operators than == don't make sense here.
    String in = "Child a; Child b; if (a == b) return true;";
    String r = generate(in);
    String expected = "if (a.equals(b)) return true;";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testGenerationComparisonRdfRdfClass() {
    String in = "Child j; if (Child <= j) return true;";
    String r = generate(in);
    String expected = "if ((getRdfClass(\"Child\").isSubclassOf(j.getClazz()))) return true;";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testGenerationComparisonDADA() {
    // maybe check all variants of comparison operators in the end
    String in = "DialogueAct d; if (d >= #Accept(Return)) return true;";
    String r = generate(in);
    String expected = "if ((d.isSubsumedBy(new DialogueAct(\"Accept\", \"Return\")))) return true;";
    assertEquals(expected, getForMarked(r, expected));
  }
}
