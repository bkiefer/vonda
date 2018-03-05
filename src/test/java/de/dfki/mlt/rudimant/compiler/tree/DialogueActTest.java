package de.dfki.mlt.rudimant.compiler.tree;

import static de.dfki.mlt.rudimant.compiler.Visualize.*;
import static de.dfki.mlt.rudimant.compiler.tree.TestUtilities.*;
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import de.dfki.lt.hfc.WrongFormatException;
import de.dfki.lt.hfc.db.QueryResult;
import de.dfki.lt.hfc.db.rdfProxy.Rdf;
import de.dfki.lt.hfc.db.rdfProxy.RdfProxy;
import de.dfki.lt.hfc.db.server.HfcDbApiHandler;
import de.dfki.lt.hfc.db.server.HfcDbServer;
import de.dfki.lt.tr.dialogue.cplan.DagNode;
import de.dfki.mlt.rudimant.agent.DiaHierarchy;
import de.dfki.mlt.rudimant.agent.DialogueAct;
import de.dfki.mlt.rudimant.compiler.tree.ExpDialogueAct;
import de.dfki.mlt.rudimant.compiler.tree.ExpFuncCall;
import de.dfki.mlt.rudimant.compiler.tree.RudiTree;

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
    String in = "inf = \"Inform\"; emitDA(#^inf(Answer, what=solution));";
    String r = generate(in);
    String expected = "String inf = \"Inform\";"
        + "emitDA(new DialogueAct(inf, \"Answer\", \"what\", \"solution\"));";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testDA2() {
    String in = "emitDA(#Inform(Answering, what=^random(10)));";
    String r = generate(in);
    String expected = "emitDA(new DialogueAct(\"Inform\", \"Answering\", \"what\", Integer.toString(random(10))));";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testToStringDA() {
    // Test set string var with POD type
    String in = "DialogueAct c = #Confirm(Correct, value=^10);";
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
    Rdf sender = _proxy.getClass("<dom:Child>").getNewInstance("rifca:");
    DialogueAct da = new DialogueAct("@raw:ReturnGreeting(Meeting"
        + " ^ <Time>afternoon ^ <addressee>\"<rifca:Child_8>\""
        + " ^ <sender>\"" + sender.getURI() + "\")");
    Rdf rep = DialogueAct.toRdf(da, _proxy);
    QueryResult res =
        _proxy.selectQuery("select ?p ?q where {} ?p ?q ?_", rep.getURI());
    assertEquals(2, res.getTable().getRows().size());
  }

  @Test
  public void testToRdf1() {
    // use information about entry time to make sure we do not only find an old entry
    long now = System.currentTimeMillis();
    Rdf sender = _proxy.getClass("<dom:Child>").getNewInstance("rifca:");
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

}
