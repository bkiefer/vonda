/*
 * The Creative Commons CC-BY-NC 4.0 License
 *
 * http://creativecommons.org/licenses/by-nc/4.0/legalcode
 *
 * Creative Commons (CC) by DFKI GmbH
 *  - Bernd Kiefer <kiefer@dfki.de>
 *  - Anna Welker <anna.welker@dfki.de>
 *  - Christophe Biwer <christophe.biwer@dfki.de>
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE.
 */

package de.dfki.mlt.rudimant.compiler.tree;

import static de.dfki.mlt.rudimant.compiler.tree.TestHelper.*;
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
	    String expected = "if (exists(lastDA()) && lastDA().hasSlot(\"name\"))";
	    assertEquals(expected, getForMarked(s, expected));
	  }

	  @Test
	  public void testFieldAccess2() {
	    String in = "if(! c.user.personality.nonchalance ){}";
	    String s = generate(in);
	    //System.out.println(s);
	    String expected = "if (!(c != null && c.user != null && c.user.personality != null"
	    		+ " && c.user.personality.nonchalance != null))";
	    assertEquals(expected, getForMarked(s, expected));
	  }

	  @Test
	  public void testFieldAccess3() {
	    String in = "Child c; yesterday = \"Lukas\"; if(c.surname.equals(yesterday)){}";
	    String s = generate(in);
	    String expected = "Rdf c;String yesterday = \"Lukas\";if (c != null &&"
	        + " exists(c.getValue(\"<dom:surname>\")) &&"
	    		+ " c.getValue(\"<dom:surname>\").equals(yesterday))";
	    assertEquals(expected, getForMarked(s, expected));
	  }

    @Test
    public void testFieldAccess3a() {
      String in = "Child c; yesterday = \"Lukas\"; if(c.forename.equals(yesterday)){}";
      String s = generate(in);
      String expected = "Rdf c;String yesterday = \"Lukas\";if (c != null &&"
          + " exists(c.getString(\"<dom:forename>\")) &&"
          + " c.getString(\"<dom:forename>\").equals(yesterday))";
      assertEquals(expected, getForMarked(s, expected));
    }

    @Test
    public void testFieldAccess4() {
      String in = "Child c; timeout(\"bla\" + c.hasFather.toString(), 1000) {}";
      String s = generate(in);
      String expected = "Rdf c;newTimeout(\"bla\"+c.getRdf(\"<dom:hasFather>\").toString(),"
          + "1000,new Proposal() {public void run() { } });";
      assertEquals(expected, getForMarked(s, expected));
    }

    @Test
    public void testFieldAccess5() {
      String in = "Child c; propose(\"bla\" + c.hasFather.toString()) {}";
      String s = generate(in);
      String expected = "Rdf c;propose(\"bla\"+c.getRdf(\"<dom:hasFather>\").toString(),"
          + "new Proposal() {public void run() { } });";
      assertEquals(expected, getForMarked(s, expected));
    }

    @Test
    public void testFieldAccess6() {
      String in = "Child c; property = \"name\"; l = isa(String, c.{property});";
      String s = generate(in);
      String expected = "Rdf c;String property = \"name\";String l = ((String)c.getObject(property)); }";
      assertEquals(expected, getForMarked(s, expected));
    }

    @Test
    public void testFieldAccess6a() {
      String in = "Child c; property = \"name\"; l = (isa(List<String>, c.{property})).get(0);";
      String s = generate(in);
      String expected = "Rdf c;String property = \"name\";String l = ((List<String>)c.getObject(property)).get(0); }";
      assertEquals(expected, getForMarked(s, expected));
    }

    @Test
    public void testFieldAccess7() {
      // clear the value of this property
      String in = "Child c; c.forename = null;";
      String s = generate(in);
      String expected = "Rdf c;c.clearValue(\"<dom:forename>\");";
      assertEquals(expected, getForMarked(s, expected));
    }

    @Test
    public void testFieldAccess8() {
      String in = "Child c; a: if(c.forename){}";
      String s = generate(in);
      String expected = "Rdf c;// Rule a boolean[] __x1 = new boolean[2];"
          + " __x1[0] = (__x1[1] = c != null && exists(c.getString(\"<dom:forename>\")));";
      assertEquals(expected, getForMarked(s, expected));
    }

    @Test
    public void testFieldAccess9() {
      String in = "Child c; s=\"<dom:forename>\"; a: if(c.{s}){}";
      String s = generate(in);
      String expected = "Rdf c;String s = \"<dom:forename>\";// Rule a boolean[] __x1 = new boolean[2];"
          + " __x1[0] = (__x1[1] = c != null && c.getObject(s) != null);";

      assertEquals(expected, getForMarked(s, expected));
    }

    @Test
    public void testFieldAccess10() {
      // TODO: Make it clear in the doc that we only support *direct*
      // existence support for RDF access chains, so that is all we offer, for
      // arbitrary long chains:
      String in = "Child c; if(c.forename && c.forename == \"foo\"){}";
      String s = generate(in);
      //System.out.println(s);
      String expected = "Rdf c;if (c != null && exists(c.getString(\"<dom:forename>\"))"
          + " && c.getString(\"<dom:forename>\").equals(\"foo\")) { }";
      assertEquals(expected, getForMarked(s, expected));
    }

    @Test
    public void testFieldAccess11() {
      String in = "Map<String, String> m;" +
          "if (m.containsKey(\"foo\")) {s = m.get(\"foo\");}";
      String s = generate(in);
      //System.out.println(s);
      String expected = "Map<String, String> m;if (exists(m) && m.containsKey(\"foo\")) { String s = m.get(\"foo\"); } }";
      assertEquals(expected, getForMarked(s, expected));
    }

    @Test
    public void testFieldAccess12() {
      // TODO: Make it clear in the doc that we only support *direct*
      // existence support for RDF access chains, so that is all we offer, for
      // arbitrary long chains:
      String in = "Child c; s = c.hasFather.forename;";
      String s = generate(in);
      //System.out.println(s);
      String expected = "Rdf c;String s = c.getRdf(\"<dom:hasFather>\").getString(\"<dom:forename>\");";
      assertEquals(expected, getForMarked(s, expected));
    }

    @Test
    public void testArrayFieldAccess() {
      String in = "String[] m; l = m.length;";
      String s = generate(in);
      //System.out.println(s);
      String expected = "String[] m;int l = m.length;";
      assertEquals(expected, getForMarked(s, expected));
    }

}
