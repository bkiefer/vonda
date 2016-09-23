package de.dfki.mlt.agent.rdf;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.thrift.TApplicationException;
import org.apache.thrift.TException;

import de.dfki.mlt.agent.Agent;
import de.dfki.lt.hfc.WrongFormatException;
import de.dfki.lt.hfc.db.QueryResult;
import de.dfki.lt.hfc.types.XsdAnySimpleType;
import de.dfki.lt.hfc.types.XsdBoolean;
import de.dfki.lt.hfc.types.XsdInt;
import de.dfki.lt.hfc.types.XsdString;


// for the time-sensitive predicates, make a version that takes a boolean[]
// and sets its first value to true if the information is new.

// alternatively, we could do this actively: if a piece of information has
// been used, mark it as used, or clear the container!

// all methods that change values in this and other RDF proxy classes
// must signal that some data has changed!

public class RdfProxy {
  static String[] subsNames = {
      "Activity", "Child", "Closing", "Color", "DialogueAct", "Hobby",
      "Introduction", "Preference", "Quiz", "Session", "Turn" };
  static String pkg = "de.dfki.dialogue.rdf.";
  @SuppressWarnings("rawtypes")
  static Class[] subs;

  protected final static
  Map<Class<? extends RdfProxy>, Map<String, String>> validKeys =
      new HashMap<Class<? extends RdfProxy>, Map<String, String>>();

  protected final static Map<String, String> namespaceMap =
      new HashMap<String, String>();

  protected final static Map<String, Class<? extends RdfProxy>> rdfTypes =
      new HashMap<String, Class<? extends RdfProxy>>();


  protected static void addValidKeys(Class<? extends RdfProxy> rdfClazz,
      String className, String[][] keyMap) {
    Map<String, String> localKeys = validKeys.get(rdfClazz);
    rdfTypes.put(className, rdfClazz);
    if (null == localKeys) {
      localKeys = new HashMap<String, String>();
      validKeys.put(rdfClazz, localKeys);
    }
    for(String[] pair : keyMap) {
      localKeys.put(pair[0], pair[1]);
    }
    @SuppressWarnings("rawtypes")
    Class clazz = rdfClazz;
    while (((clazz = clazz.getSuperclass()) != null) &&
        clazz != Object.class) {
      Map<String, String> superKeys = validKeys.get(clazz);
      localKeys.putAll(superKeys);
    }
  }

  static {
    String[][] valid = {
        { "id", "String" }, // the URI
    };
    addValidKeys(RdfProxy.class, "RdfProxy", valid);
    try {
      subs = new Class[subsNames.length];
      int i = 0;
      for (String c : subsNames)
        subs[i++] = Class.forName(pkg + c);
    } catch (ClassNotFoundException ex) {
      throw new RuntimeException(ex);
    }
  }

  protected Agent _agent;

  /** Fetch the value for the given uri and predicate, complete, with angle
   * brackets
   * @param uri
   * @param predicate
   * @return
   * @throws TException
   */
  protected String getValue(String uri, String predicate) throws TException {
    String value = null;
    try {
      value = _agent._client.getValue(uri, predicate);
      if ("<dom:no_value>".equals(value)) {
        value = null;
      }
    } catch (TApplicationException ex2) {
      // no value
    }
    return value;
  }


  /** Retrieve a universal fact (no time stamp) */
  protected String getUniversal(String uri, String predicate) throws TException {
    String value = null;
    try {
      // no value with time, try triple
      QueryResult result = _agent._client.selectQuery(
          "select ?o where " + uri + " " + predicate + " ?o");
      if (result.getTable().getRowsSize() > 0) {
        value = result.getTable().getRows().get(0).get(0);
      }
    } catch (TApplicationException ex2) {
        // no value
    }
    return value;
  }

  /** Get the name label of an rdf object */
  protected String getLabel(String language) {
    try {
      QueryResult result = _agent._client.selectQuery(
          "select ?o where " + get("id") + " <dom:name> ?o");
      if (result.getTable().getRowsSize() == 0) return get("id");
      for (List<String> row : result.getTable().getRows()) {
      // no value with time, try triple
        XsdString s = (XsdString) XsdString.getXsdObject(row.get(0));
        if (s.languageTag != null && language.startsWith(s.languageTag)) {
          return s.value;
        }
      }
    }
    catch (WrongFormatException e) {
      _agent.logger.error("Wrong format atom from db: {}", e);
    }
    catch (TException e ) {
      _agent.logger.error("db problem: {}", e);
    }
    return null;
  }

  /** TODO: untested */
  protected RdfProxy findInstance(String type, String label, String language) {
    try {
      QueryResult result = _agent._client.selectQuery(
          "select ?i where ?i <rdf:type> "+ type
          + " & ?i <dom:name> \"" + label + "\"@" + language.substring(0, 2));
      if (result.getTable().getRowsSize() == 0) return null;
      return getProxy(result.getTable().getRows().get(0).get(0));
    }
    catch (TException e) {
      _agent.logger.error("db problem: {}", e);
      return null;
    }
  }

  protected String getWithOrWithoutTime(String uri, String predicate) throws TException {
    String value = getValue(uri, predicate);
    if (value == null) {
      value = getUniversal(uri, predicate);
    }
    return value;
  }

  /** Get a new RdfProxy object from the given URI
   * @throws TException
   */
  public RdfProxy getProxy(String uri) throws TException {
    // get type
    String rdfType = getWithOrWithoutTime(uri, "<rdf:type>");
    // get class for type, if it exists
    Class<? extends RdfProxy> cl = rdfTypes.get(rdfType);
    if (cl == null) { return null; }
    try {
      RdfProxy o;
      Constructor<? extends RdfProxy> constr;
      try {
        constr = cl.getConstructor(Agent.class, String.class);
        o = constr.newInstance(this._agent, uri);
      } catch (NoSuchMethodException ex) {
        constr = cl.getConstructor(Agent.class);
        o = constr.newInstance(this._agent);
        o.values.put("id", uri);
      }
      return o;
    } catch (NoSuchMethodException|SecurityException|InstantiationException
        |IllegalAccessException|IllegalArgumentException
        |InvocationTargetException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
  }

  public Object getObject(String s) throws TException {
    Object o = null;
    if (s.charAt(0) == '<') {
      // a URI, has to be turned into the appropriate RdfProxy, if
      // possible
      o = getProxy(s);
      if (o == null) {
        o = s;
      }
    } else {
      try {
        o = XsdAnySimpleType.getXsdObject(s).toJava();
      } catch (WrongFormatException e) {
        // TODO Auto-generated catch block
        _agent.logger.error("Unrecognized Value from DB: {}", s);
      }
    }
    return o;
  }

  private static String getNewUri(Agent agent, String type) {
    int lastDot = type.lastIndexOf('.');
    type = type.substring(lastDot + 1);
    try {
      // TODO: THE NAMESPACE OF THE PROXY OBJECT TO BE CREATED IS NOT NECESSARY
      // THE NAMESPACE OF THE TYPE
      String id = agent._client.getNewId(aboxNamespace(), getUriString(type));
      return id;
    }
    catch (TException ex) {
      agent.logger.error("Database error: {}", ex);
    }
    return null;
  }

  private void init(Agent agent, String uri) {
    _agent = agent;
    values.put("id", uri);
    _agent.newData();
  }

  protected RdfProxy(Agent agent) {
    init(agent, getNewUri(agent, this.getClass().getName()));
  }

  protected RdfProxy(Agent agent, String uri) {
    init(agent, uri);
  }

  // mimic RDF
  public Map<String, Object> values = new HashMap<>();

  void checkKey(String key) {
    Map<String, String> localKeys = validKeys.get(this.getClass());
    if (localKeys == null || !localKeys.containsKey(key)) {
      _agent.logger.error(
          "Agent using wrong key {} for class {}", key, this.getClass());
      throw new RuntimeException(
          "Agent using wrong key" + key + " for class " + this.getClass());
    }
    prefetch(key);
  }

  static protected String defaultNamespace() { return "dom:"; }

  static String aboxNamespace() { return "rifca:"; }

  protected static String getNamespace(String what) {
    String ns = namespaceMap.get(what);
    return (ns == null) ? defaultNamespace() : ns;
  }


  private static String getUriString(String key) {
    return '<' + getNamespace(key) + key + '>';
  }

  private String getUri() {
    // return getUriString((String)values.get("id"));
    return (String)values.get("id");
  }

  private void prefetch(String key) {
    if (values.containsKey(key)) return;
    Object o = null;
    try {
      String s = getValue(getUri(), getUriString(key));
      if (s != null) {
        o = getObject(s);
      }
    }
    catch (TException ex) {
      _agent.logger.error("Database error: {}", ex);
    }
    values.put(key, o);
  }

  static String valToString(Object value) {
    String valString = null;
    if (value instanceof RdfProxy) {
      valString = (String)((RdfProxy)value).get("id");
    } else {
      if (value instanceof Boolean) {
        valString = new XsdBoolean((Boolean)value).toString(true);
      } else if (value instanceof Integer) {
        valString = new XsdInt((Integer)value).toString(true);
      } else {
        // currently buggy
        valString = // new XsdString(value.toString()).toString(true);
            '"' + value.toString() + "\"^^<xsd:string>";
      }
    }
    return valString;
  }

  private void setValue(String key, Object value) {
    try {
      _agent._client.setValue(getUri(), getUriString(key), valToString(value));
    }
    catch (TException ex) {
      _agent.logger.error("Database error: {}", ex);
    }
  }

  public <T> T get(String key) {
    checkKey(key);
    return (T)values.get(key);
  }

  public boolean has(String key) {
    checkKey(key);
    return values.containsKey(key) && values.get(key) != null;
  }

  public void put(String key, Object value) {
    checkKey(key);
    values.put(key, value);
    // write to DB
    setValue(key, value);
    _agent.newData();
  }

  /** Assumes that the key points to an RDF list, to which object is added */
  @SuppressWarnings({"unchecked", "rawtypes"})
  public void addToList(String key, Object value) {
    checkKey(key);
    RdfList list = (RdfList)get(key);
    if (list == null) {
      list = new RdfList(_agent);
      put(key, list);
    }
    list.add(value);
  }

  /*
  public void remove(String key) {
    checkKey(key);
    values.remove(key);
    _agent.newData();
  }
  */

  public boolean oneOf(String key, Object ... value) {
    checkKey(key);
    return Arrays.asList(value).indexOf(get(key)) >= 0;
  }

  public boolean isEqual(String key, Object value) {
    checkKey(key);
    Object o = get(key);
    return (o == null) ? value == null : o.equals(value);
  }

}
