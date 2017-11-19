/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.compiler.tree;

import static de.dfki.mlt.rudimant.compiler.Visualize.*;
import static de.dfki.mlt.rudimant.compiler.tree.TstUtils.*;
import static org.junit.Assert.*;

import org.junit.*;

/**
 *
 * @author anna
 */
public class TestComparison {

  @BeforeClass
  public static void setUpClass() {
    setUpNonEmpty();
  }

  @Test
  public void testGenerationComparisonPODPOD() {
    String in = "int i; long j; if (i == j) return true;";
    String r = generate(in);
    String expected = "int i;long j;if ((i == j)) return true;";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testGenerationComparisonPODContainer() {
    String in = "int i; Long j; if (i == j) return true;";
    String r = generate(in);
    String expected = "int i;Long j;if ((i == j)) return true;";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testGenerationComparisonStringString() {
    String in = "String i; String j; if (i <= j) return true;";
    String r = generate(in);
    String expected = "String i;String j;if ((i.compareTo(j) <= 0)) return true;";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testGenerationComparisonStringDA() {
    // maybe check all variants of comparison operators in the end
    String in = "String i; if (i <= #Accept(Return)) return true;";
    String r = generate(in);
    String expected = "String i;if ((new DialogueAct(i).subsumes(new DialogueAct(\"Accept\", \"Return\")))) return true;";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testGenerationComparisonStringRdf() {
    String in = "String i; Child j; if (i <= j) return true;";
    String r = generate(in);
    String expected = "String i;Rdf j;if ((i.getClazz().isSubclassOf(j.getClazz()))) return true;";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testGenerationComparisonStringRdfClass() {
    String in = "String j; if (Child <= j) return true;";
    String r = generate(in);
    String expected = "String j;if ((getRdfClass(\"Child\").isSubclassOf(j.getClazz()))) return true;";
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
    String expected = "Rdf a;Rdf b;if ((a.equals(b))) return true;";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testGenerationComparisonRdfRdfClass() {
    String in = "Child j; if (Child <= j) return true;";
    String r = generate(in);
    String expected = "Rdf j;if ((getRdfClass(\"Child\").isSubclassOf(j.getClazz()))) return true;";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testGenerationComparisonDADA() {
    // maybe check all variants of comparison operators in the end
    String in = "DialogueAct d; if (d >= #Accept(Return)) return true;";
    String r = generate(in);
    String expected = "DialogueAct d;if ((d.isSubsumedBy(new DialogueAct(\"Accept\", \"Return\")))) return true;";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testGenerationRdfExists() {
    // other operators than == don't make sense here.
    String in = "Child a; if (a.hasTreatment) return true;";
    String r = generate(in);
    String expected = "Rdf a;if ((a != null &&"
        + " ((Rdf)a.getSingleValue(\"<dom:hasTreatment>\")) != null)) return true;";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testGenerationIntegerExists() {
    // other operators than == don't make sense here.
    String in = "final Child a; if (a.age) return true;";
    String r = generate(in);
    String expected = "final Rdf a;if ((a != null && "
        + "exists(((Integer)a.getSingleValue(\"<dom:age>\"))))) return true;";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testGenerationXsdDateExists() {
    // other operators than == don't make sense here.
    String in = "Child a; if (a.birthdate) return true;";
    String r = generate(in);
    String expected = "Rdf a;if ((a != null && ((XsdDate)a.getSingleValue(\"<dom:birthdate>\")) != null)) return true;";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testGenerationStringExists() {
    // other operators than == don't make sense here.
    String in = "final Activity a; if (a.status) return true;";
    String r = generate(in);
    String expected = "final Rdf a;if ((a != null && exists(((String)a.getSingleValue(\"<dom:status>\"))))) return true;";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testGenerationNotEqual() {
    // other operators than == don't make sense here.
    String in = "Activity a; if (a.status != \"foo\") return true;";
    String r = generate(in);
    String expected = "Rdf a;if ((! (((String)a.getSingleValue(\"<dom:status>\")).equals(\"foo\")))) return true;";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testGenerationNotEqual2() {
    // other operators than == don't make sense here.
    String in = "final Child c; if (c.hasBrother != null) return true;";
    String r = generate(in);
    String expected = "final Rdf c;if ((((Set<Object>)c.getValue(\"<dom:hasBrother>\")) != null)) return true;";
    assertEquals(expected, getForMarked(r, expected));
  }}
