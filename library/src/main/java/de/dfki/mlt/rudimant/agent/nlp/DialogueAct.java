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

  public static final String DIALACT_RDFCLASS = "<dial:DialogueAct>";
  public static final String FRAME_RDFCLASS = "<dial:Frame>";
  public static final String PAIR_RDFCLASS = "<dial:Pair>";

  public static final String FRAME_PROPERTY = "<dial:frame>";
  public static final String SENDER_PROPERTY = "<dial:sender>";
  public static final String ADRESSEE_PROPERTY = "<dial:addressee>";
  public static final String FROMTIME_PROPERTY = "<dial:fromTime>";
  public static final String TOTIME_PROPERTY = "<dial:toTime>";

  public static final String NONRDFPROPS_PROPERTY = "<dial:repr>";

  public static final String ARGS_PROPERTY = "<dial:hasArgs>";
  public static final String FIRST_PROPERTY = "<dial:first>";
  public static final String SECOND_PROPERTY = "<dial:second>";

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

  /** RDF --> dag: add RDF compatible feature/value pairs to the given dag structure */
  private static Set<Object> addRdfArguments(StringBuilder sb, Rdf rdf, RdfProxy proxy) {
    Set<Object> result = null;
    Table propsValues = proxy.selectQuery(
        "select ?p ?o where {} ?p ?o ?_", rdf.getURI()).getTable();
    for (List<String> propVal: propsValues.getRows()) {
      String prop = propVal.get(0);
      if (! FRAME_PROPERTY.equals(prop) && !"<rdf:type>".equals(prop)) {
        if (ARGS_PROPERTY.equals(prop)) {
          result = rdf.getValue(prop);
        } else if (! NONRDFPROPS_PROPERTY.equals(prop)) {
          Set<Object> valSet = rdf.getValue(prop);
          assert(valSet.size() == 1);
          Object val = valSet.iterator().next();
          sb.append(" ^ <" + uriToName(prop) + ">\"" + val.toString() +'"');
        }
      }
    }
    return result == null ? Collections.emptySet() : result;
  }

  /** RDF --> dag: Add initial Intent and Frame */
  private static Rdf addIntentAndFrame(StringBuilder sb, String uri, RdfProxy proxy) {
    Rdf da = proxy.getRdf(uri);
    Rdf frame = (Rdf) da.getSingleValue(FRAME_PROPERTY);
    if (! da.getClazz().isSubclassOf(proxy.getClass(DIALACT_RDFCLASS))) {
      logger.error("URI does not point to a DialogueAct: {}", uri);
    }
    sb.append("@raw:").append(uriToName(da.getClazz().toString()));
    String frameName = (String)frame.getSingleValue(Rdf.RDFS_LABEL);
    if (frameName == null)
      frameName = uriToName(frame.getClazz().toString());
    sb.append("(").append(frameName);
    return da;
  }

  /** Read a DialogueAct from the database, with uri being the root node.
   *  (RDF --> dag)
   *
   * Although maybe that's the "right way" to do it, it seems overkill. For the
   * moment, we go for the simpler way.
   *
   * As for toRdf, this will only work on restricted shallow representations.
   */
  public static DialogueAct fromRdfProper(String uri, RdfProxy proxy) {
    StringBuilder sb = new StringBuilder();
    Rdf da = addIntentAndFrame(sb, uri, proxy);
    Set<Object> nonRdfProps = addRdfArguments(sb, da, proxy);
    for(Object rdfPair: nonRdfProps) {
      Rdf pair = (Rdf)rdfPair;
      sb.append(" ^ <")
      .append(pair.getString(FIRST_PROPERTY)).append(">\"")
      .append(pair.getString(SECOND_PROPERTY)).append('"');
    }
    //extractArguments(rawDA, frame, proxy);
    sb.append(')');
    return new DialogueAct(sb.toString());
  }

  /** dag --> RDF: put Intent and Frame into a new RDF and return it */
  private Rdf storeIntentAndFrame(RdfProxy proxy) {
    RdfClass diaClass = proxy.getRdfClass(getDialogueActType());
    if (diaClass == null) {
      logger.error("No Subclass of DialougeAct: {}", getDialogueActType());
      diaClass = proxy.getClass(DIALACT_RDFCLASS);
    }
    Rdf rdfDialAct = diaClass.getNewInstance(DIAL_NS);
    RdfClass frameClass = proxy.getRdfClass(getProposition());
    boolean syntheticFrame = frameClass == null;
    if (syntheticFrame) {
      logger.warn("No Subclass of Frame: {}", getProposition());
      frameClass = proxy.getClass(FRAME_RDFCLASS);
    }
    Rdf frame = frameClass.getNewInstance(DIAL_NS);
    if (syntheticFrame)
      frame.setValue(Rdf.RDFS_LABEL, getProposition());
    rdfDialAct.setValue(FRAME_PROPERTY, frame);
    return rdfDialAct;
  }

  /** dag --> RDF: put RDF compatible properties into instance */
  private Set<String> storeRdfProperies(Rdf rdfDialAct, RdfProxy proxy) {
    Set<String> result = new HashSet<>();
    RdfClass diaClass = proxy.getRdfClass(getDialogueActType());
    for (Map.Entry<String, String> entry : getValues().entrySet()) {
      String name = entry.getKey();
      String prop = diaClass.fetchProperty(name);
      if (prop != null) {
        String val = entry.getValue();
        rdfDialAct.setValue(prop, val);
      } else {
        result.add(name);
      }
    }
    return result;
  }

  /** Write a DialogueAct to the database. (dag --> RDF)
   *
   * Although maybe that's the "right way" to do it, it seems overkill. For the
   * moment, we go for the simpler way.
   *
   * This implementation is not fully general, it will only write shallow
   * structures without coreferences correctly.
   */
  public Rdf toRdfProper(RdfProxy proxy) {
    Rdf rdfDialAct = storeIntentAndFrame(proxy);
    Set<String> props = storeRdfProperies(rdfDialAct, proxy);
    for (String prop : props) {
      RdfClass pairClass = proxy.getClass(PAIR_RDFCLASS);
      Rdf pair = pairClass.getNewInstance(DIAL_NS);
      pair.setValue(FIRST_PROPERTY, prop);
      pair.setValue(SECOND_PROPERTY, getValue(prop));
      rdfDialAct.add(ARGS_PROPERTY, pair);
    }
    return rdfDialAct;
  }

  /** RDF --> dag: use the repr feature for non-RDF properties */
  public static DialogueAct fromRdf(String uri, RdfProxy proxy) {
    StringBuilder sb = new StringBuilder();
    Rdf da = addIntentAndFrame(sb, uri, proxy);
    addRdfArguments(sb, da, proxy);
    String rep = (String)da.getSingleValue(NONRDFPROPS_PROPERTY);
    sb.append(rep).append(')');
    return new DialogueAct(sb.toString());
  }

  /** dag --> RDF: use repr feature for non-RDF properties */
  public Rdf toRdf(RdfProxy proxy) {
    RdfClass dialClass = proxy.getClass(DIALACT_RDFCLASS);
    Rdf dial = storeIntentAndFrame(proxy);
    Set<String> props = storeRdfProperies(dial, proxy);
    StringBuilder sb = new StringBuilder();
    for (String name : props) {
      String val = this.getValue(name);
      String prop = dialClass.fetchProperty(name);
      if (prop != null) {
        dial.setValue(prop, val);
      } else {
        sb.append(" ^ <").append(name).append(">\"").append(val).append('"');
      }
    }
    dial.setValue(NONRDFPROPS_PROPERTY, sb.toString());
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
