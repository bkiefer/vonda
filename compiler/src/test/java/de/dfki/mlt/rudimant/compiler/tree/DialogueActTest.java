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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import de.dfki.lt.hfc.WrongFormatException;
import de.dfki.lt.hfc.db.QueryResult;
import de.dfki.lt.hfc.db.rdfProxy.Rdf;
import de.dfki.lt.hfc.db.rdfProxy.RdfProxy;
import de.dfki.lt.tr.dialogue.cplan.DagNode;
import de.dfki.mlt.rudimant.agent.nlp.DiaHierarchy;
import de.dfki.mlt.rudimant.agent.nlp.DialogueAct;

/**
 *
 * @author max
 */
public class DialogueActTest {
  static RdfProxy _proxy;

  @BeforeClass
  public static void setUpClass() throws FileNotFoundException {
    setUpNonEmpty();
    // need this to set up dialogue act hierarchy
    try {
      _proxy = startClient(new File(RESOURCE_DIR), configs);
    } catch (IOException | WrongFormatException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    DagNode.init(new DiaHierarchy(_proxy));
  }

  @Test
  public void test() throws IOException {
    String in = "emitDA(#Inform(Answer, what=solution));";
    RudiTree dtr = getNodeOfInterest(parseAndTypecheck(in));
    assertTrue(dtr instanceof ExpFuncCall);
    assertTrue(((ExpFuncCall)dtr).getDtrs().iterator().next()
        instanceof ExpDialogueAct);

  }

  @Test
  public void testDA1() {
    String in = "inf = \"Inform\"; emitDA(#{inf}(Answer, what=solution));";
    String r = generate(in);
    String expected = "String inf = \"Inform\";"
        + "emitDA(new DialogueAct(inf, \"Answer\", \"what\", \"solution\"));";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testDA2() {
    String in = "emitDA(#Inform(Answering, what={random(10)}));";
    String r = generate(in);
    String expected = "emitDA(new DialogueAct(\"Inform\", \"Answering\", \"what\", Integer.toString(random(10))));";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testToStringDA() {
    // Test set string var with POD type
    String in = "DialogueAct c = #Confirm(Correct, value={10});";
    String s = generate(in);
    String expected =
        "DialogueAct c = new DialogueAct(\"Confirm\", \"Correct\", \"value\", Integer.toString(10))";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void testFromRdf() {
    Rdf da = _proxy.getClass("<dial:Accept>").getNewInstance("dial:");
    da.setValue("<dial:sender>", "someone");
    Rdf frame = _proxy.getClass("<sem:Answering>").getNewInstance("dial:");
    frame.setValue("<sem:agent>", "someone else");
    da.setValue("<dial:frame>", frame);

    DialogueAct transformed = DialogueAct.fromRdfProper(da.getURI(), _proxy);

    assertEquals("<dial:Accept>", transformed.getDialogueActType());
    assertEquals("Answering", transformed.getProposition());
    assertTrue(transformed.hasSlot("sender"));
    assertEquals("someone", transformed.getValue("sender"));
    assertTrue(transformed.hasSlot("agent"));
    assertEquals("someone else", transformed.getValue("agent"));
  }

  @Test
  public void testFromRdf2() {
    Rdf da = _proxy.getClass("<dial:Accept>").getNewInstance("dial:");
    da.setValue("<dial:sender>", "someone");
    Rdf frame = _proxy.getClass("<sem:Frame>").getNewInstance("dial:");
    frame.setValue("<sem:agent>", "someone else");
    frame.setValue("<sem:label>", "Foo");
    da.setValue("<dial:frame>", frame);

    DialogueAct transformed = DialogueAct.fromRdfProper(da.getURI(), _proxy);

    assertEquals("<dial:Accept>", transformed.getDialogueActType());
    assertEquals("Foo", transformed.getProposition());
    assertTrue(transformed.hasSlot("sender"));
    assertEquals("someone", transformed.getValue("sender"));
    assertTrue(transformed.hasSlot("agent"));
    assertEquals("someone else", transformed.getValue("agent"));
  }

  @Test
  public void testToRdf2() {
    Rdf sender = _proxy.getClass("<dom:Child>").getNewInstance("dial:");
    DialogueAct da = new DialogueAct("@raw:ReturnGreeting(Meeting"
        + " ^ <Time>afternoon ^ <addressee>\"<dial:Child_8>\""
        + " ^ <sender>\"" + sender.getURI() + "\")");
    Rdf rep = DialogueAct.toRdf(da, _proxy);
    QueryResult res =
        _proxy.selectQuery("select ?p ?q where {} ?p ?q ?_", rep.getURI());
    assertEquals(3, res.getTable().getRows().size());
  }

  @Test
  public void testToRdf1() {
    // use information about entry time to make sure we do not only find an old entry
    long now = System.currentTimeMillis();
    Rdf sender = _proxy.getClass("<dom:Child>").getNewInstance("dial:");
    _proxy.getClass("<dial:Confirm>").getNewInstance("dial:");
    DialogueAct da =
        new DialogueAct("Inform", "Answer", "what", "solution", "sender", sender.toString() );
    DialogueAct.toRdf(da, _proxy);

    String query = "select ?s ?t where ?s ?_ <dial:Inform> ?t "
        + "filter LLess ?t {} "
        + "aggregate ?inform = LGetLatest ?s ?t \"1\"^^<xsd:int>";
    QueryResult qr = _proxy.selectQuery(query, "\"" + Long.toString(now) + "\"^^<xsd:long>");
    assertTrue(RdfProxy.getValues(qr) != null && RdfProxy.getValues(qr).size() == 1);
  }

  @Test
  public void testHasSlot() throws IOException {
    String in = "slots = { \"theme1\", \"theme2\" };" +
                  "for (String slot: slots) {" +
                  "  if (lastDA().slot) { }}";
    String s = generate(in);
    String expected = "String[] slots = new String[]{\"theme1\", \"theme2\"};"
            + "for (Object slot_outer : slots) { "
            + "String slot = (String)slot_outer; { "
            + "if (exists(lastDA()) && lastDA().hasSlot(slot)) { } } }";
    assertEquals(expected, getForMarked(s, expected));

  }

  @Test
  public void testSetSlot() throws IOException {
    String in = "da = #Inform(Matter); da.theme = \"foo\";";
    String s = generate(in);
    String expected = "DialogueAct da = new DialogueAct(\"Inform\", \"Matter\");"
        + "da.setValue(\"theme\", \"foo\"); }";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void testGetSlot() throws IOException {
    String in = "da = #Inform(Matter, theme=foo);s = da.theme;";
    String s = generate(in);
    String expected = "DialogueAct da = new DialogueAct(\"Inform\", \"Matter\", \"theme\", \"foo\");"
        + "String s = da.getValue(\"theme\");";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void testGetSlot2() throws IOException {
    String in = "da = #Inform(Matter, Agent=foo);s = da.Agent;";
    String s = generate(in);
    String expected = "DialogueAct da = new DialogueAct(\"Inform\", \"Matter\", \"Agent\", \"foo\");"
        + "String s = da.getValue(\"Agent\");";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void testSlotVariables1() throws IOException {
    String in = "int foo = 0; da = #Inform(Matter, theme=foo);";
    String s = generate(in);
    String expected = "int foo = 0;DialogueAct da = new DialogueAct(\"Inform\", \"Matter\", \"theme\", \"foo\");";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void testSlotVariables2() throws IOException {
    String in = "int foo = 0; da = #Inform(Matter, theme={foo});";
    String s = generate(in);
    String expected = "int foo = 0;DialogueAct da = new DialogueAct(\"Inform\", \"Matter\", \"theme\", Integer.toString(foo));";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void testSlotVariables3() throws IOException {
    String in = "int foo = 0; da = #Inform(Matter, foo=bar);";
    String s = generate(in);
    String expected = "int foo = 0;DialogueAct da = new DialogueAct(\"Inform\", \"Matter\", \"foo\", \"bar\");";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void testSlotVariables4() throws IOException {
    String in = "int foo = 0; da = #Inform(Matter, {foo}=bar);";
    String s = generate(in);
    String expected = "int foo = 0;DialogueAct da = new DialogueAct(\"Inform\", \"Matter\", Integer.toString(foo), \"bar\");";
    assertEquals(expected, getForMarked(s, expected));
  }
}
