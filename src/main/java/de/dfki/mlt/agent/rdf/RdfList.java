package de.dfki.mlt.agent.rdf;

import java.util.Iterator;
import java.util.LinkedList;

import org.apache.thrift.TException;

import de.dfki.mlt.agent.Agent;

// for the time-sensitive predicates, make a version that takes a boolean[]
// and sets its first value to true if the information is new.

// alternatively, we could do this actively: if a piece of information has
// been used, mark it as used, or clear the container!

// all methods that change values in this and other RDF proxy classes
// must signal that some data has changed!

public class RdfList<T> extends RdfProxy implements Iterable<T> {
  static {
    String[][] valid = { };
    addValidKeys(RdfList.class, "<rdf:List>", valid);
  }

  LinkedList<T> _impl = new LinkedList<>();

  String headUri, tailUri;

  private String newCons() throws TException {
    return _agent._client.getNewId(RdfProxy.aboxNamespace(), "<rdf:List>");
  }

  public RdfList(Agent agent) {
    super(agent);
    try {
      headUri = tailUri = newCons();
      values.put("id", headUri);
    }
    catch (TException ex) {
      _agent.logger.error("Database error: {}", ex);
    }
  }


  public RdfList(Agent agent, String uri) {
    super(agent);
    headUri = uri;
    String current = uri, rest = uri;
    try {
      do {
        current = rest;
        String value = getValue(current, "<rdf:first>");
        _impl.add((T)getObject(value));
        rest = getValue(current, "<rdf:rest>");
      } while (rest != null);
    } catch (TException ex) {
      _agent.logger.error("Database error: {}", ex);
    }
    tailUri = current;
  }


  public boolean isEmpty() {
    return _impl.isEmpty();
    // an empty rdf list in this representation only has the URI of type List
    // but no first element
  }

  public int length() { return _impl.size(); }
  
  public T peekLast() { return _impl.peekLast(); }

  public T peekFirst() { return _impl.peekFirst(); }
  
  public T get(int index) { return _impl.get(index); }

  public Iterator<T> iterator() { return _impl.iterator(); }

  /** Add to the end of the list */
  void add(T value) {
    try {
      if (! _impl.isEmpty()) {
        String newCons = newCons();
        _agent._client.setValue(tailUri, "<rdf:rest>", newCons);
        tailUri = newCons;
      }
      // add element: put it into the first slot of the (possibly new) tail
      _agent._client.setValue(tailUri, "<rdf:first>",
          RdfProxy.valToString(value));
    }
    catch (TException ex) {
      _agent.logger.error("Database error: {}", ex);
    }
    _impl.add(value);

    _agent.newData();
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(get("id").toString()).append('[');
    Iterator<T> it = _impl.iterator();
    if (it.hasNext()) sb.append(it.next().toString());
    while (it.hasNext()) {
      sb.append(", ").append(it.next().toString());
    }
    sb.append(']');
    return sb.toString();
  }
  
  /** push to the front of the list *
  void push(T value) {
    _impl.push(value);
    _agent.newData();
  }
  */
}
