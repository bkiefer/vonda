package de.dfki.mlt.agent;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import de.dfki.lt.hfc.*;
import de.dfki.lt.hfc.types.*;

public class RdfStore {

  public static final Logger logger = Logger.getLogger("RdfStore");

  /** The RDF storage and reasoner */
  private ForwardChainer fc;

  public RdfStore(String rootDir) throws FileNotFoundException, WrongFormatException, IOException {
    initForwardChainer(rootDir);
    // startQueryConsole();
  }

  public void shutdown() {
    fc.shutdownNoExit();
  }

  private void initForwardChainer(String rootDir) throws FileNotFoundException, WrongFormatException, IOException {
    fc = new ForwardChainer(
        1,                                // noOfCores
        true,                             // verbose
        true,                             // rdfCheck
        true,                             // eqReduction
        3,                                // minNoOfArgs
        5,                                // maxNoOfArgs
        100000,                           // noOfAtoms
        500000,
        rootDir + "ontologies/default.eqred.nt",
        rootDir + "ontologies/default.time.long.quintuple.eqred.rdl",
        rootDir + "ontologies/default.ns");
    // upload the HySociaTea n-triple sub-ontologies and instance files
    String[] ntFiles = {
        "upper.nt", "domain.nt", "dialframe.nt", "hysociatea.nt"
    };

    for (String s : ntFiles) {
      fc.uploadTuples(rootDir + "ontologies/" + s);
    }

    // always call this _before_ the closure is computed the first time
    // as a side effect, this call will then time-stamp triples
    fc.enableTupleDeletion();

    //
    fc.computeClosure();

    /*
    Query q = new Query(fc);
    try {
      BindingTable bt = q.query("SELECT * WHERE ?s ?p ?o");
      System.out.println(bt.toString());
    }
    catch (QueryParseException ex) {}
    */
  }

  int[] varsToPos(BindingTable bt, String ... vars) {
    int[] posList = new int[vars.length];
    int i = 0;
    for (String var : vars) {
      posList[i++] = bt.obtainPosition(var);
    }
    return posList;
  }

  public String[] projectRows(int[] row, int[] posList) {
    String[] result = new String[posList.length];
    for (int i = 0; i < posList.length; ++i) {
      result[i] = fc.tupleStore.getObject(row[posList[i]]);
    }
    return result;
  }

  public Object[] projectObjRows(int[] row, int[] posList) {
    Object[] result = new Object[posList.length];
    for (int i = 0; i < posList.length; ++i) {
      // String objString = fc.tupleStore.getObject(row[posList[i]]);
      AnyType any = fc.tupleStore.getJavaObject(row[posList[i]]);
      Object obj = null;
      if (any instanceof XsdLong) {
        obj = (Long)((XsdLong)any).value;
      } else if (any instanceof XsdInt) {
        obj = (Integer)((XsdInt)any).value;
      } else if (any instanceof XsdFloat) {
        obj = (Float)((XsdFloat)any).value;
      } else {
        // use String for the rest
        obj = fc.tupleStore.getObject(row[posList[i]]);
      }
      result[i] = obj;
    }
    return result;
  }

  /** Query the RDF storage and return the result with the highest time stamp.
   *  The time must be in the first slot.
   */
  public String[] queryOne(String query, String ... vars) {
    String[] result = null;
    Query q = new Query(fc.tupleStore);
    try {
      BindingTable bt = q.query(query);
      if (null == bt || bt.table.size() == 0) return null;
      int[] resultPosList = varsToPos(bt, vars);

      int timeSlot = bt.obtainPosition("?time");
      if (null == bt || bt.table.size() == 0) return null;
      int[] maxTime = bt.table.iterator().next();
      for (int[] row : bt.table) {
        if (maxTime[0] < row[0]) {
          maxTime = row;
        }
      }
      result = projectRows(maxTime, resultPosList);
    }
    catch (QueryParseException ex) {
      logger.error("Wrong query syntax for " + query + " " + ex);
    }
    return result;
  }

  public List<String[]> queryTwo(String query, int uniqueRow, String ... vars) {
    Query q = new Query(fc.tupleStore);
    try {
      BindingTable bt = q.query(query);
      if (null == bt || bt.table.size() == 0) return null;
      int[] resultPosList = varsToPos(bt, vars);

      HashMap<Integer, int[]> maxRows = new HashMap<>();
      for (int[] row : bt.table) {
        int[] maxTime = maxRows.get(row[uniqueRow]);

        if (null == maxTime || maxTime[0] < row[0]) {
          maxRows.put(row[uniqueRow], row);
        }
      }

      List<String[]> result = new ArrayList<>(maxRows.size());
      for(int[] maxTime : maxRows.values()) {
        result.add(projectRows(maxTime, resultPosList));
      }
      return result;
    }
    catch (QueryParseException ex) {
      logger.error("Wrong query syntax for " + query + " " + ex);
    }
    return null;
  }


  public List<Object[]> query(String query, String ... vars) {
    Query q = new Query(fc.tupleStore);
    try {
      BindingTable bt = q.query(query);
      if (null == bt || bt.table.size() == 0) return null;
      int[] resultPosList = varsToPos(bt, vars);

      List<Object[]> resultList = new ArrayList<Object[]>(bt.table.size());
      for (int[] row : bt.table) {
        resultList.add(projectObjRows(row, resultPosList));
      }
      return resultList;
    }
    catch (QueryParseException ex) {
      logger.error("Wrong query syntax " + query + " " + ex);
    }
    return null;
  }



 public static String long2Rdf(long l) {
    return '"' + Long.toString(l) + "\"^^<xsd:long>";
  }

  public static String float2Rdf(double d) {
    return '"' + Double.toString(d) + "\"^^<xsd:float>";
  }

  public static String string2RdfSting(String s) {
    return '"' + s + "\"^^<xsd:string>";
  }

  public static String rdfString2String(String s) {
    return s.substring(1, s.length() - "\"^^<xsd:string>".length());
  }

  public static String toUri(String s) {
    // logger.info("toURI :" + s);
    return "<dafn:" + s + '>';
  }

  public static String uri2String(String s) {
    int colPos = s.indexOf(':');
    if (colPos >= 0) {
      s = s.substring(colPos+1, s.length() - 1);
    } else {
      s = s.substring(1, s.length() - 1);
    }
    return s;
  }



  public void addTriple(long time, String ... strings) {
    String[] tuple = new String[strings.length + 2];
    for (int i = 0; i < strings.length; ++i) {
      if (strings[i].charAt(0) != '<' && strings[i].charAt(0) != '"')
        logger.error("Wrong RDF symbol");
      tuple[i] = strings[i];
    }
    tuple[strings.length] = long2Rdf(time);
    tuple[strings.length + 1] = long2Rdf(0);

    fc.tupleStore.addTuple(tuple);

    StringBuilder sb = new StringBuilder();
    sb.append("[").append(tuple[0]);
    for (int i = 1; i < tuple.length; ++i) {
      sb.append(", ").append(tuple[i]);
    }
    sb.append("]");
    logger.info(sb.toString());
  }


  private static final Pattern varPattern = Pattern.compile("\\?[a-z][a-z]*");

  String[] extractResultVariables(String query) {
    List<String> l = new ArrayList<>();
    Matcher m = varPattern.matcher(query);
    while(m.find()) {
      l.add(m.group(0));
    }
    return l.toArray(new String[l.size()]);
  }

  /** For interactive debugging *
  private void startQueryConsole() {
    QueryWindow qw = new QueryWindow();
    qw.registerListener(new QueryWindowListener() {

      @Override
      public void onQuery(String query) {
        Query q = new Query(fc);
        try {
          BindingTable bt = q.query(query);
          System.out.println((bt == null) ? "Empty Result" : bt.toString());
         }
        catch (QueryParseException ex) {
          System.out.println("Wrong query: " + query + " " + ex);
        }
        /*
        String[] vars = extractResultVariables(query);
        List<Object[]> t = query(query, vars);
        for (String s : vars) {
          System.out.print(s + " ");
        }
        table2string(t)
        *
     }
    });
  }*/

  public static void table2string(List<Object[]> t) {
    System.out.println();
    for (Object[] row : t) {
      for (Object elt : row) {
        System.out.print(elt + " ");
      }
      System.out.println();
    }
  }

  /*
  String addRdfSpeechact(SpeechActEvent saEvent) {
    long now = new Date().getTime();
    String newSa = "<speechact_" + saEvent.getId() + '>';
    String newFrame = "<frame_" + saEvent.getId() + '>';

    addTriple(now, newSa, "<rdf:type>", toUri(saEvent.getSpeechact()));
    addTriple(now, newSa, "<dafn:Frame>", newFrame);
    String[] propositions = saEvent.getProposition().split("\\+");
    for (String type : propositions) {
      addTriple(now, newFrame, "<rdf:type>", toUri(type));
    }
    for (Map.Entry<String, String> arg : saEvent.arguments.entrySet()) {
      switch (arg.getKey()) {
      case "addressee":
      case "follows":
      case "refersTo:":
      case "sender":
        addTriple(now, newSa, toUri(arg.getKey()), string2RdfSting(arg.getValue()));
        break;
      default:
        addTriple(now, newFrame, toUri(arg.getKey()), string2RdfSting(arg.getValue()));
        break;
      }
    }
    return newSa;
  }
  */

  /** Extract the current worker location from the RDF store and return it */
  String getWorkerLocation() {
    // TODO: DO IT !!!!
    return "<dafn:workbench>";
  }

  /** get the storage location that contains content
   *
   * @param content the content to look for
   * @return where the content is stored
   */
  String getLocationContainingContent(String content) {
    // map the item to bring to the position in the storage
    String[] fromRow = queryOne("SELECT ?time ?storage "
        + "WHERE ?storage <contains> \"" + content + "\"^^<xsd:string> ?time ?e",
        "?storage");
    return fromRow == null ? null : fromRow[0];
  }

  /** get the content in the specified storage location (URI)
   *
   * @param content the content to look for
   * @return where the content is stored
   */
  String getContentInLocation(String locationUri) {
    // map the item to bring to the position in the storage
    String[] fromRow = queryOne("SELECT ?time ?content "
        + "WHERE " + locationUri + " <contains> ?content ?time ?e",
        "?content");
    return fromRow == null ? null : fromRow[0];
  }

 /** Look for an empty space on the table
   *  TODO: could this also be done with LMax aggregate ??
   * @param location
   * @return an empty slot in location or null
   */
  String getEmptySlotInLocation(String location) {
    List<String[]> toLocationRow =
        queryTwo("SELECT ?time ?storage ?content "
            + "WHERE " + location + " <hasSlot> ?storage ?st ?et & "
            + "?storage <contains> ?content ?time ?etime", 0,
            "?storage", "?content");
    String toLocation = null;
    // grab the one whose content is empty, if available
    for (String[] row : toLocationRow) {
      if ("<EMPTY>".equals(row[1])) {
        toLocation = row[0];
        break;
      }
    }
    return toLocation;
  }

  public static void main(String[] args) throws FileNotFoundException, WrongFormatException, IOException {
    String s = "SELECT ?p ?q WHERE ?p <rdf:type> ?q";
    RdfStore r = new RdfStore(Agent.RESOURCE_DIR);
    List<Object[]> t = r.query(s, "?p", "?q");

    for (Object[] row : t) {
      for (Object o : row) System.out.print(o + " ");
      System.out.println();
    }
    System.exit(0);
  }

  String getPreferredBoxToTake(String worker) {
    List<Object[]> table =
        query("SELECT ?time ?l WHERE " + worker + " <uses> ?l ?time ?e",
            "?time", "?l");
    if (table == null || table.size() == 0) return null;
    List<String> lastLocations = new ArrayList<>(3);
    Collections.sort(table, new Comparator<Object[]>() {
      @Override
      public int compare(Object[] o1, Object[] o2) {
        return (int)((Long)o1[0] - (Long)o2[0]);
      }
    });
    for(Object[] row : table) {
      String loc = (String)row[1];
      if (! lastLocations.contains(loc)) {
        lastLocations.add(loc);
        if (lastLocations.size() >= 3) break;
      }
    }
    return lastLocations.get(0);
  }

}
