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

package de.dfki.mlt.rudimant.agent.nlp;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dfki.lt.hfc.db.Table;
import de.dfki.lt.hfc.db.rdfProxy.Rdf;
import de.dfki.lt.hfc.db.rdfProxy.RdfClass;
import de.dfki.lt.hfc.db.rdfProxy.RdfProxy;
import de.dfki.lt.tr.dialogue.cplan.DagEdge;
import de.dfki.lt.tr.dialogue.cplan.DagNode;

public class DialogueAct {

  public static String DIAL_NS = "dial:";

  private static Logger logger = LoggerFactory.getLogger(DialogueAct.class);

  public long timeStamp;

  private DagNode _dag;

  public DialogueAct() {
    timeStamp = System.currentTimeMillis();
  }

  public DialogueAct(DagNode da) {
    this();
    _dag = da;
  }

  // Only for logging
  private String catAll(String ... args) {
    StringBuilder sbb = new StringBuilder();
    if (args.length > 0)
      sbb.append(args[0]).append('(');
    if (args.length > 1)
      sbb.append(args[1]);
    for (int i = 2; i < args.length; ++i) sbb.append("|").append(args[i]);
    sbb.append(')');
    return sbb.toString();
  }

  public DialogueAct(String ... args) {
    this();
    if (args.length < 2) {
      throw new IllegalArgumentException("type or proposition missing: " + catAll(args));
    }
    if ((args.length & 1) != 0) {
      throw new IllegalArgumentException("Odd number of arguments: " + catAll(args));
    }
    _dag = DagNode.parseLfString(args[0] + "(" + args[1] + ")");
    for (int i = 2; i < args.length; i+=2) {
      setValue(args[i], args[i+1]);
    }
  }

  public DialogueAct(String s) {
    this();
    _dag = DagNode.parseLfString(s);
    if (_dag == null) logger.error("Wrong syntax creating DialogueAct: {}", s);
  }

  public DagNode getDag() {
    return _dag;
  }

  @Override
  public String toString() {
    return _dag.toString(true);
  }

  /** this is more specific than arg (this >= arg) */
  public boolean isSubsumedBy(DialogueAct moreGeneral) {
    return _dag.isSubsumedBy(moreGeneral._dag);
  }

  /** this is more specific than arg (this >= arg) */
  public boolean isStrictlySubsumedBy(DialogueAct moreGeneral) {
    return _dag.isSubsumedBy(moreGeneral._dag) && ! equals(moreGeneral);
  }

  /** this is more general than arg (this <= arg) */
  public boolean subsumes(DialogueAct moreSpecific) {
    return _dag.subsumes(moreSpecific._dag);
  }

  /** this is more general than arg (this <= arg) */
  public boolean strictlySubsumes(DialogueAct moreSpecific) {
    return _dag.subsumes(moreSpecific._dag) && ! equals(moreSpecific);
  }

  @Override
  public boolean equals(Object arg) {
    if (! (arg instanceof DialogueAct))
      return false;
    DialogueAct da2 = (DialogueAct)arg;
    return _dag.equals(da2._dag);
  }

  /** Return true if the given slot (argument) is available */
  public boolean hasSlot(String slot) {
    return _dag.getEdge(DagNode.getFeatureId(slot)) != null;
  }

  /** Return the argument for key slot */
  public String getValue(String slot) {
    DagEdge e = _dag.getEdge(DagNode.getFeatureId(slot));
    if (e == null) {
      return null;
    }
    DagNode node = e.getValue();
    if (node.getType() == DagNode.TOP_ID) {
      e = node.getEdge(DagNode.PROP_FEAT_ID);
      if (e == null) {
        return DagNode.TOP_TYPE;
      }
      return e.getValue().getTypeName();
    }
    return node.getTypeName();
  }

  /** Set the argument for key slot */
  public String setValue(String slot, String value) {
    DagEdge edge = _dag.getEdge(DagNode.getFeatureId(slot));
    DagNode newVal = new DagNode(DagNode.PROP_FEAT_ID, new DagNode(value));
    if (edge == null) {
      _dag.addEdge(DagNode.getFeatureId(slot), newVal);
    } else {
      edge.setValue(newVal);
    }
    return value;
  }

  /** Get the timestamp of when this DA has been created */
  public long getTimeStamp() {
    return timeStamp;
  }

  /** Return the dialogue act */
  public String getDialogueActType() {
    DagNode d = _dag.getValue(DagNode.TYPE_FEAT_ID);
    return (d == null) ? null : d.getTypeName();
  }

  /** Return the dialogue act */
  public void setDialogueActType(String daType) {
    DagEdge e = _dag.getEdge(DagNode.TYPE_FEAT_ID);
    if (e == null) {
      _dag.addEdge(DagNode.TYPE_FEAT_ID, new DagNode(daType));
    } else {
      e.getValue().setType(DagNode.getTypeId(daType));
    }
  }

  /** Return the proposition */
  public String getProposition() {
    DagNode prop = _dag.getValue(DagNode.PROP_FEAT_ID);
    return (prop == null) ? null : prop.getTypeName();
  }

  /** Set the proposition of a dialogue act*/
  public void setProposition(String prop) {
    DagEdge p = _dag.getEdge(DagNode.PROP_FEAT_ID);
    if (p == null) {
      _dag.addEdge(DagNode.PROP_FEAT_ID, new DagNode(prop));
    } else {
      p.getValue().setType(DagNode.getTypeId(prop));
    }
  }

  public DialogueAct copy() {
    return new DialogueAct(_dag.copySafely());
  }

  /** Remove the namespace and the angle brackets, leaving only the name */
  private static String uriToName(String uri) {
    return uri.substring(uri.indexOf(":") + 1, uri.lastIndexOf(">"));
  }

  /** Add all the feature/value pairs from the given dag structure */
  private static void extractArguments(StringBuilder sb, Rdf rdf, RdfProxy proxy) {
    Table propsValues =
        proxy.selectQuery("select ?p ?o where {} ?p ?o ?_", rdf.getURI()).getTable();
    // first is prop, second is value
    for (List<String> propVal: propsValues.getRows()) {

      String prop = propVal.get(0);
      if (! "<dial:frame>".equals(prop) && !"<rdf:type>".equals(prop)) {
        Set<Object> valSet = rdf.getValue(prop);
        assert(valSet.size() == 1);
        Object val = valSet.iterator().next();
        sb.append(", " + uriToName(prop) + "=\"" + val.toString() +'"');
      }
    }
  }

  /** Read a DialogueAct from the database, with uri being the root node.
   *
   * Although maybe that's the "right way" to do it, it seems overkill. For the
   * moment, we go for the simpler way.
   *
   * As for toRdf, this will only work on restricted shallow representations.
   */
  public static DialogueAct fromRdfProper(String uri, RdfProxy proxy) {

    Rdf da = proxy.getRdf(uri);
    Rdf frame = (Rdf) da.getSingleValue("<dial:frame>");
    if (! da.getClazz().isSubclassOf(proxy.getClass("<dial:DialogueAct>"))) {
      logger.error("URI does not point to a DialogueAct: {}", uri);
    }
    StringBuilder rawDA = new StringBuilder();
    rawDA.append(uriToName(da.getClazz().toString()));
    String frameName = (String)frame.getSingleValue("<sem:label>");
    if (frameName == null)
      frameName = uriToName(frame.getClazz().toString());
    rawDA.append("(").append(frameName);
    extractArguments(rawDA, da, proxy);
    extractArguments(rawDA, frame, proxy);
    rawDA.append(')');
    return new DialogueAct(rawDA.toString());
  }

  /** Write a DialogueAct to the database.
   *
   * Although maybe that's the "right way" to do it, it seems overkill. For the
   * moment, we go for the simpler way.
   *
   * This implementation is not fully general, it will only write shallow
   * structures without coreferences correctly.
   */
  public static Rdf toRdfProper(DialogueAct d, RdfProxy proxy) {
    RdfClass diaClass = proxy.getRdfClass(d.getDialogueActType());
    if (diaClass == null) {
      logger.error("No Subclass of DialougeAct: {}", d.getDialogueActType());
      diaClass = proxy.getClass("<dial:DialogueAct>");
    }
    Rdf rdfDialAct = diaClass.getNewInstance(DIAL_NS);
    RdfClass frameClass = proxy.getRdfClass(d.getProposition());
    boolean syntheticFrame = frameClass == null;
    if (syntheticFrame) {
      logger.error("No Subclass of Framme: {}", d.getProposition());
      frameClass = proxy.getClass("<sem:Frame>");
    }
    Rdf frame = frameClass.getNewInstance(DIAL_NS);
    if (syntheticFrame)
      frame.setValue("<sem:label>", d.getProposition());
    rdfDialAct.setValue("<dial:frame>", frame);
    for (Map.Entry<String, String> entry : d.getValues().entrySet()) {
      String name = entry.getKey();
      String val = entry.getValue();
      String prop = diaClass.fetchProperty(name);
      if (prop != null) {
        rdfDialAct.setValue(prop, val);
      } else {
        prop = frameClass.fetchProperty(name);
        if (prop == null) {
          prop = "<sem:" + name + ">";
        }
        frame.setValue(prop, val);
      }
    }
    return rdfDialAct;
  }

  public static DialogueAct fromRdf(String uri, RdfProxy proxy) {
    Rdf da = proxy.getRdf(uri);
    String rep = (String)da.getSingleValue("<dial:repr>");
    return new DialogueAct(rep);
  }

  public static Rdf toRdf(DialogueAct d, RdfProxy proxy) {
    String senderUri = d.getValue("sender");
    if (senderUri == null) {
      logger.warn("No sender specified for dialogue act {}", d.toString());
      return null;
    }
    Rdf sender = proxy.getRdf(senderUri);
    RdfClass dialClass = proxy.getClass("<dial:DialogueAct>");
    Rdf dial = dialClass.getNewInstance(DIAL_NS);
    dial.setValue("<dial:repr>", d.toString());
    dial.setValue("<dial:sender>", sender);
    return dial;
  }

  /** Get a set of all slots of this dialogue act */
  public Set<String> getAllSlots() {
    Set<String> slots = new HashSet<String>();
    Iterator<DagEdge> dit = _dag.getEdgeIterator();
    while (dit.hasNext()) {
      DagEdge d = dit.next();
      slots.add(d.getName());
    }
    return slots;
  }

  /** Get a set of all slots of this dialogue act */
  public Map<String, String> getValues() {
    Map<String, String> args = new LinkedHashMap<>();
    Iterator<DagEdge> dit = _dag.getEdgeIterator();
    while (dit.hasNext()) {
      DagEdge d = dit.next();
      if (d.getFeature() != DagNode.PROP_FEAT_ID
          && d.getFeature() != DagNode.TYPE_FEAT_ID
          && d.getFeature() != DagNode.ID_FEAT_ID) {
        args.put(d.getName(), getValue(d.getName()));
      }
    }
    return args;
  }

}
