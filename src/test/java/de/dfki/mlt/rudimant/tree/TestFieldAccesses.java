package de.dfki.mlt.rudimant.tree;

import static de.dfki.mlt.rudimant.Visualize.*;
import static de.dfki.mlt.rudimant.tree.TstUtils.*;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestFieldAccesses {

	  static String header = "boolean firstEncounter(); label: if(true) {"
	          + "// hello test\n";
	  static String footer = "}";

	  @BeforeClass
	  public static void setUpClass() throws FileNotFoundException {
	    setUp(RESOURCE_DIR + "tests.yml", header, footer);
	  }

	  @Test
	  public void testFieldAccess1() {
	    String in = "if (lastDA().name){}";
	    String s = generate(in);
	    String expected = "if (((lastDA() != null) && hasSlot(lastDA(), \"name\"))";
	    assertEquals(expected, getForMarked(s, expected));
	  }

	  @Test
	  public void testFieldAccess2() {
	    String in = "if(! c.user.personality.nonchalance ){}";
	    String s = generate(in);
	    String expected = "if (!((((c != null) && (c.user != null)) && (c.user.personality != null))"
	    		+ " && (c.user.personality.nonchalance != null)))";
	    assertEquals(expected, getForMarked(s, expected));
	  }
	  
	  @Test
	  public void testFieldAccess3() {
	    String in = "Child c; yesterday = \"Lukas\"; if(c.name.equals(yesterday)){}";
	    String s = generate(in);
	    String expected = "String yesterday = \"Lukas\"; if ((((c != null) &&"
	    		+ " (((Set<Object>)c.getValue(\"<upper:name>\")) != null)) &&"
	    		+ " ((Set<Object>)c.getValue(\"<upper:name>\")).equals(yesterday))";
	    assertEquals(expected, getForMarked(s, expected));
	  }
	  
}
