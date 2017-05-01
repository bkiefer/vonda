package de.dfki.mlt.rudimant.abstractTree;

import static de.dfki.mlt.rudimant.Visualize.*;
import static de.dfki.mlt.rudimant.abstractTree.TstUtils.*;
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
/*
	  @Test
	  public void testFieldAccess1() {
	    String in = "";
	    String s = generate(in);
	    String expected = "";
	    assertEquals(expected, getForMarked(s, expected));
	  }*/

	  @Test
	  public void testFieldAccess2() {
		/* This is not crashing, so it seems Java ignores the parenthesis when choosing the order
		  ArrayList<ArrayList<ArrayList<String>>> a = new ArrayList<>();
		  ArrayList<ArrayList<String>> b = new ArrayList<>();
		  a.add(0, b);
		  b.add(0, null);
		  if(((a.get(0) != null) && a.get(0).get(0) != null) && a.get(0).get(0).get(0) != null){}
		*/
	    String in = "if(! c.user.personality.nonchalance ){}";
	    String s = generate(in);
	    String expected = "if (!((((c != null) && (c.user != null)) && (c.user.personality != null))"
	    		+ " && (c.user.personality.nonchalance != null)))";
	    assertEquals(expected, getForMarked(s, expected));
	  }

}
