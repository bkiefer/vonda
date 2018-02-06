package de.dfki.mlt.rudimant.agent;

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

  private DialogueAct(DagNode da) {
    this();
    _dag = da;
  }

  public DialogueAct(String da, String prop, String ... args) {
    this();
    _dag = DagNode.parseLfString(da + "(" + prop + ")");
    for (int i = 0; i < args.length; i+=2) {
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

  public boolean equals(DialogueAct arg) {
    return _dag.equals(arg._dag);
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
  public void setValue(String slot, String value) {
    DagEdge edge = _dag.getEdge(DagNode.getFeatureId(slot));
    DagNode newVal = new DagNode(DagNode.PROP_FEAT_ID, new DagNode(value));
    if (edge == null) {
      _dag.addEdge(DagNode.getFeatureId(slot), newVal);
    } else {
      edge.setValue(newVal);
    }
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

  private static String propertyToSlot(String uri) {
    return uri.substring(uri.indexOf(":") + 1, uri.lastIndexOf(">"));
  }

  // TODO finish and test
  public static DialogueAct fromRdf(String uri, RdfProxy proxy) {

    Rdf da = proxy.getRdf(uri);
    if (! da.getClazz().isSubclassOf(proxy.getClass("<dial:DialogueAct>"))) {
      logger.error("URI does not point to a DialogueAct: {}", uri);
    }
    Table propsValues =
        proxy.selectQuery("select ?p ?o where {} ?p ?o ?_", uri).getTable();
    // String rawDA = da.getClass().getSimpleName();
    String rawDA = propertyToSlot(da.getClazz().toString());
    rawDA += "(" + propertyToSlot(((Rdf)da.getSingleValue("<dial:frame>")).getClazz().toString());

    // first is prop, second is value
    for (List<String> propVal: propsValues.getRows()) {
      String prop = propVal.get(0);
      if (! "<dial:frame>".equals(prop))
        rawDA += ", " + propertyToSlot(prop) + "=" + da.getValue(prop);
    }

    Rdf frame = (Rdf) da.getSingleValue("<dial:frame>");
    propsValues =
        proxy.selectQuery("select ?p ?o where {} ?p ?o ?_", frame.getURI()).getTable();
    
    // TODO similar for loop like above, for all props & values
    for (List<String> propVal: propsValues.getRows()) {
      String prop = propVal.get(0);
      rawDA += ", " + propertyToSlot(prop) + "=" + da.getValue(prop);
    }
    System.out.println(rawDA);
    return new DialogueAct(rawDA + ")");
  }

  // TODO
  public void toRdf(RdfProxy proxy) {
    RdfClass diaClass = proxy.getClass(getDialogueActType());
    if (diaClass == null) {
      logger.error("No Subclass of DialougeAct: {}", getDialogueActType());
      diaClass = proxy.getClass("<dial:DialogueAct>");
    }
    Rdf da = diaClass.getNewInstance(DIAL_NS);
    RdfClass frameClass = proxy.getClass(getProposition());
    if (frameClass == null) {
      logger.error("No Subclass of Framme: {}", getProposition());
      frameClass = proxy.getClass("<sem:Frame>");
    }
    Rdf frame = frameClass.getNewInstance(DIAL_NS);
    da.setValue("<dial:frame>", frame);

    Iterator<DagEdge> dit = _dag.getEdgeIterator();
    while (dit.hasNext()) {
      DagEdge d = dit.next();
      if (d.getFeature() != DagNode.PROP_FEAT_ID
          && d.getFeature() != DagNode.TYPE_FEAT_ID) {
        String prop = diaClass.fetchProperty(d.getName());
        if (prop != null) {
          da.setValue(prop, d.getValue().getTypeName());
        } else {
          prop = frameClass.fetchProperty(d.getName());
          if (prop == null) {
            prop = "<sem:" + d.getName() + ">";
          }
          frame.setValue(prop, d.getValue().getTypeName());
        }
      }
    }
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

}

