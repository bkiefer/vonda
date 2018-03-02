package de.dfki.mlt.rudimant.compiler.tree;

import static de.dfki.mlt.rudimant.compiler.Visualize.*;
import static de.dfki.mlt.rudimant.compiler.tree.TestUtilities.*;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.BeforeClass;
import org.junit.Test;

public class FieldAccessesTest {

	  static String header = "boolean firstEncounter(); label: if(true) {"
	          + "// hello test\n";
	  static String footer = "// end of test\n}";

	  @BeforeClass
	  public static void setUpClass() throws FileNotFoundException {
	    setUp(RESOURCE_DIR + "tests.yml", header, footer);
	  }

	  @Test
	  public void testFieldAccess1() {
	    String in = "if (lastDA().name){}";
	    String s = generate(in);
	    String expected = "if (lastDA() != null && lastDA().hasSlot(\"name\"))";
	    assertEquals(expected, getForMarked(s, expected));
	  }

	  @Test
	  public void testFieldAccess2() {
	    String in = "if(! c.user.personality.nonchalance ){}";
	    String s = generate(in);
	    System.out.println(s);
	    String expected = "if (!(c != null && c.user != null && c.user.personality != null"
	    		+ " && c.user.personality.nonchalance != null))";
	    assertEquals(expected, getForMarked(s, expected));
	  }

	  @Test
	  public void testFieldAccess3() {
	    String in = "Child c; yesterday = \"Lukas\"; if(c.name.equals(yesterday)){}";
	    String s = generate(in);
	    String expected = "Rdf c;String yesterday = \"Lukas\";if (c != null &&"
	        + " exists(((Set<Object>)c.getValue(\"<upper:name>\"))) &&"
	    		+ " ((Set<Object>)c.getValue(\"<upper:name>\")).equals(yesterday))";
	    assertEquals(expected, getForMarked(s, expected));
	  }

    @Test
    public void testFieldAccess4() {
      String in = "Child c; timeout(\"bla\" + c.hasFather.toString(), 1000) {}";
      String s = generate(in);
      String expected = "Rdf c;newTimeout((\"bla\"+((Rdf)c.getSingleValue(\"<dom:hasFather>\")).toString()),"
          + "1000,new Proposal() {public void run() { } });";
      assertEquals(expected, getForMarked(s, expected));
    }

    @Test
    public void testFieldAccess5() {
      String in = "Child c; propose(\"bla\" + c.hasFather.toString()) {}";
      String s = generate(in);
      String expected = "Rdf c;propose((\"bla\"+((Rdf)c.getSingleValue(\"<dom:hasFather>\")).toString()),"
          + "new Proposal() {public void run() { } });";
      assertEquals(expected, getForMarked(s, expected));
    }

    @Test
    public void testFieldAccess6() {
      String in = "Child c; property = \"name\"; l = c.property;";
      String s = generate(in);
      String expected = "Rdf c;String property = \"name\";Set<Object> l = ((Set<Object>)c.getValue(property)); }";
      assertEquals(expected, getForMarked(s, expected));
    }

    @Test
    public void testFieldAccess7() {
      // clear the value of this property
      String in = "Child c; c.name = null;";
      String s = generate(in);
      String expected = "Rdf c;c.clearValue(\"<upper:name>\");";
      assertEquals(expected, getForMarked(s, expected));
    }

    @Test
    public void testFieldAccess8() {
      String in = "Child c; a: if(c.name){}";
      String s = generate(in);
      String expected = "Rdf c;// Rule a boolean[] __x1 = new boolean[2];"
          + " __x1[0] = (__x1[1] = c != null && exists(((Set<Object>)c.getValue(\"<upper:name>\"))));";
      assertEquals(expected, getForMarked(s, expected));
    }

    @Test
    public void testFieldAccess9() {
      String in = "Child c; s=\"<upper:name>\"; a: if(c.s){}";
      String s = generate(in);
      String expected = "Rdf c;String s = \"<upper:name>\";// Rule a boolean[] __x1 = new boolean[2];"
          + " __x1[0] = (__x1[1] = c != null && exists(((Set<Object>)c.getValue(s))));";

      assertEquals(expected, getForMarked(s, expected));
    }
}
