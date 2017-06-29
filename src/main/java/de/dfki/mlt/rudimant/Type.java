package de.dfki.mlt.rudimant;

import static de.dfki.mlt.rudimant.Constants.DIALOGUE_ACT_TYPE;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.dfki.lt.hfc.db.rdfProxy.RdfClass;
import de.dfki.lt.hfc.db.rdfProxy.RdfProxy;

/** A class to represent the types/classes used in the code, the relation
 *  between Java and RDF types, the handling of complex (parameterised) types,
 *  etc.
 */
public class Type {
  private static RdfProxy PROXY;

  static Map<String, Long> typeCodes = new HashMap<>();

  static final long JAVA_TYPE = 0x10;
  static {
    typeCodes.put("Object",                  0x111l);
    typeCodes.put("String",                    0x1l);
    typeCodes.put("Rdf",                      0x10l);
    typeCodes.put("double",           0x1000000000l);
    typeCodes.put("Double",           0x1000000100l);
    typeCodes.put("float",            0x1100000000l);
    typeCodes.put("Float",            0x1100000100l);
    typeCodes.put("long",             0x1110000000l);
    typeCodes.put("Long",             0x1110000100l);
    typeCodes.put("int",              0x1111000000l);
    typeCodes.put("Integer",          0x1111000100l);
    typeCodes.put("short",            0x1111100000l);
    typeCodes.put("Short",            0x1111100100l);
    typeCodes.put("byte",             0x1111110000l);
    typeCodes.put("Byte",             0x1111110100l);
    typeCodes.put("char",             0x1111001000l);
    typeCodes.put("Character",        0x1111001100l);
    typeCodes.put("boolean",         0x10000000000l);
    typeCodes.put("Boolean",         0x10000000100l);
    typeCodes.put("null",           0x100000000000l);
  }

  public static void setProxy(RdfProxy proxy) { PROXY = proxy; }

  /** Indicates if this type should be compared with == or equals */
  public static boolean isPODType(String name) {
    name = RdfClass.xsdToJavaPod(name);
    Long code = typeCodes.get(name);
    return code != null && (code & 0x11111111000l) != 0;
  }

  public static boolean isRdfType(String type) {
    return (type != null && type.charAt(0) == '<');
  }

  public static boolean isComplexType(String type) {
    if (type == null) return false;
    return (type.startsWith("Map")
        || type.startsWith("Set")
        || type.startsWith("List")
        || type.startsWith("RdfSet")
        || type.startsWith("RdfList")
        || ((type.endsWith(">") && ! isRdfType(type))));
  }

  /** Return the "inner" type of a complex type expression */
  public static String getInnerType(String type) {
    int left = type.indexOf('<');
    if (left < 0) return "Object";
    int right = type.lastIndexOf('>');
    return type.substring(left + 1, right);
  }

  public static String getOuterType(String type) {
    int i = type.indexOf('<');
    if (i < 0) return type;
    return type.substring(0, i);
  }

  /**
   * returns a correct java type for xsd types
   * @param something
   * @return
   */
  public static String convertXsdType(String something){
    String ret = RdfClass.xsdToJavaPod(something);
    if (ret != null) return ret;
    return something;
  }

  /**
   * returns a correct java type for whatever input
   * @param something
   * @return
   */
  public static String convertRdfType(String something){
    something = convertXsdType(something);
    if(!isRdfType(something)) {
      if(something.contains("<")){
        // might be sth like List<Child>
        something = something.substring(0, something.indexOf("<") + 1)
                + convertRdfType(something.substring(something.indexOf("<")
                        + 1, something.lastIndexOf(">")))
                + ">";
      }
      return something;
    }
    return (DIALOGUE_ACT_TYPE.equals(something)) ? "DialogueAct" : "Rdf";
  }

  public static String checkRdf(String type) {
    // if is necessary, because otherwise, Object as the static type in a
    // declaration gets changed to RdfType by
    // VGenerationVisitor.visitNode(ExpAssignment node)
    if ("Object".equals(type) || null == type
        || "String".equals(type)){ // what about Integer, int, etc.??
      return type;
    }
    if(!type.startsWith("<") && type.contains("<")){
      // might be sth like List<Child>
      type = type.substring(0, type.indexOf("<") + 1)
              + checkRdf(type.substring(type.indexOf("<")
                      + 1, type.lastIndexOf(">")))
              + ">";
    }
    RdfClass clazz = PROXY.fetchClass(type);
    if (clazz != null) {
      type = clazz.toString(); // the URI of the class
    }
    return type;
  }


  // **********************************************************************
  // End of static fields and methods
  // **********************************************************************

  private String _name;

  /** The parameters of a parameterised type, null if a simple type */
  private List<Type> _parameterTypes;

  public Type(String typeName) {
    _name = typeName;
  }

  /** Return the more specific of the two types, if it exists, null otherwise */
  public static String unifyTypes(String left, String right) {
    if (left == null || "Object".equals(left)) return right;
    if (right == null || "Object".equals(right)) return left;
    left = convertXsdType(left);
    right = convertXsdType(right);
    // check if these are RDF types and are in a type relation.
    if (isRdfType(left) || isRdfType(right)) {
      if (isRdfType(left) && isRdfType(right))
        return PROXY.fetchMostSpecific(left, right);
      if ("Rdf".equals(left)) return right;
      if ("Rdf".equals(right)) return left;
      return null;
    }

    // this should return the more specific of the two, or null if they are
    // incompatible
    Long leftCode = typeCodes.get(left);
    if (leftCode == null) leftCode = JAVA_TYPE;
    Long rightCode = typeCodes.get(right);
    if (rightCode == null) rightCode = JAVA_TYPE;

    long common = leftCode & rightCode;
    if (common == leftCode) {
      return left;
    }
    if (common == rightCode) {
      return right;
    }
    return null;
  }

}
