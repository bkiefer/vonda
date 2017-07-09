package de.dfki.mlt.rudimant.tree;

import static de.dfki.mlt.rudimant.Visualize.*;
import static de.dfki.mlt.rudimant.tree.TstUtils.*;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestFieldAccesses {

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
	    String expected = "if ((lastDA() != null && lastDA().hasSlot(\"name\")))";
	    assertEquals(expected, getForMarked(s, expected));
	  }

	  @Test
	  public void testFieldAccess2() {
	    String in = "if(! c.user.personality.nonchalance ){}";
	    String s = generate(in);
	    String expected = "if (!((((c != null && c.user != null) && c.user.personality != null)"
	    		+ " && c.user.personality.nonchalance != null)))";
	    assertEquals(expected, getForMarked(s, expected));
	  }

	  @Test
	  public void testFieldAccess3() {
	    String in = "Child c; yesterday = \"Lukas\"; if(c.name.equals(yesterday)){}";
	    String s = generate(in);
	    String expected = "String yesterday = \"Lukas\"; if (((c != null &&"
	        + " exists(((Set<Object>)c.getValue(\"<upper:name>\")))) &&"
	    		+ " ((Set<Object>)c.getValue(\"<upper:name>\")).equals(yesterday)))";
	    assertEquals(expected, getForMarked(s, expected));
	  }

    @Test
    public void testFieldAccess4() {
      String in = "Clazz c; timeout(\"bla\" + c.a.toString(), 1000) {}";
      String s = generate(in);
      String expected = "newTimeout((\"bla\"+((Rdf)c.getSingleValue(\"<dom:a>\")).toString()),"
          + "1000,new Proposal() {public void run() { }});";
      assertEquals(expected, getForMarked(s, expected));
    }

    @Test
    public void testFieldAccess5() {
      String in = "Clazz c; propose(\"bla\" + c.a.toString()) {}";
      String s = generate(in);
      String expected = "propose((\"bla\"+((Rdf)c.getSingleValue(\"<dom:a>\")).toString()),"
          + "new Proposal() {public void run() { }});";
      assertEquals(expected, getForMarked(s, expected));
    }
}
