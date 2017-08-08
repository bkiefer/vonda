package de.dfki.mlt.rudimant;

import static de.dfki.mlt.rudimant.Constants.DIALOGUE_ACT_TYPE;

import java.util.*;

import de.dfki.lt.hfc.db.rdfProxy.RdfClass;
import de.dfki.lt.hfc.db.rdfProxy.RdfProxy;

/** A class to represent the types/classes used in the code, the relation
 *  between Java and RDF types, the handling of complex (parameterised) types,
 *  etc.
 */
public class Type {
  private static RdfProxy PROXY;
  private static RdfClass DIALACT_CLASS;

  static Map<String, Long> typeCodes = new HashMap<>();
  static Map<Long, String> code2type = new HashMap<>();

  private static final Map<String, String> xsd2java = new HashMap<>();

  static {
    final String[][] xsd2javatypes = {
      {"<xsd:int>", "Integer"},
      {"<xsd:string>", "String"},
      {"<xsd:boolean>", "Boolean"},
      {"<xsd:double>", "Double"},
      {"<xsd:float>", "Float"},
      {"<xsd:long>", "Long"},
      {"<xsd:integer>", "Long"},
      {"<xsd:byte>", "Byte"},
      {"<xsd:short>", "Short"},
      {"<xsd:dateTime>", "Date"},
      {"<xsd:date>", "XsdDate"},
    };
    for (String[] pair : xsd2javatypes) {
      xsd2java.put(pair[0], pair[1]);
    }
  }

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
    for (Map.Entry<String, Long> entry : typeCodes.entrySet()) {
      code2type.put(entry.getValue(), entry.getKey());
    }
  }

  public static void setProxy(RdfProxy proxy) {
    PROXY = proxy;
    DIALACT_CLASS = proxy.getClass(DIALOGUE_ACT_TYPE);
  }

  public static Type getNoType(){
    return new Type();
  }

  // **********************************************************************
  // Fields and constructors
  // **********************************************************************

  private String _name;

  /** The parameters of a parameterised type, null if a simple type */
  private List<Type> _parameterTypes;

  private RdfClass _class;

  private Type() {
    _name = null;
  }

  private void rdfIfy() {
    if (typeCodes.containsKey(_name)) return;
    if (_name.charAt(0) == '<') {
      // xsd or RDF type
      _class = PROXY.getClass(_name);
    } else {
      // try to get an appropriate RDF class
      _class = PROXY.fetchClass(_name);
    }
  }

  private void splitIfy(String typeSpec) {
    int i = 0;
    if ((i = typeSpec.indexOf('<')) > 0) {
      String nameSpec = typeSpec.substring(0, i).trim();
      String[] partypes = typeSpec.substring(i+1, typeSpec.lastIndexOf('>')).split(" *, *");
      _parameterTypes = new ArrayList<>();
      for (String par : partypes)
        _parameterTypes.add(new Type(par));
      _name = nameSpec;
    } else {
      _name = typeSpec;
    }
  }


  /** This is only to be used to create complex types for lambda expressions */
  public Type(Type ... parameterTypes) {
    _name = "Function";
    _parameterTypes = new ArrayList<>(parameterTypes.length);
    _parameterTypes.addAll(Arrays.asList(parameterTypes));
  }

  public Type(String typeSpec) {
    if (typeSpec == null) {
      _name = null;
      return;
    }
    splitIfy(typeSpec);
    rdfIfy();
  }

  public String get_name() {
    return _name;
  }

  private String xsdToJavaPodWrapper() {
    String ret = xsd2java.get(_name);
    return (ret != null) ? ret : _name;
  }

  private String xsdToJavaPod() {
    String ret = xsd2java.get(_name);
    Long code = typeCodes.get(ret);
    if (code == null) return _name;
    ret = code2type.get(code & ~ 0x100);
    return (ret != null) ? ret : _name;
  }

  /** Indicates if this type should be compared with == or equals */
  public boolean isPODType() {
    if (_name == null) return false; // if we're ignorant, it is Object
    if (isNull()) return true;
    String name = xsdToJavaPodWrapper();
    Long code = typeCodes.get(name);
    return code != null && (code & 0x11111111000l) != 0
        && (code & 0x100) == 0; // containers may be null!
  }

  /** Return true if this is a Java wrapper class for some number */
  public boolean isNumber() {
     // if we're ignorant, it is Object, and null is not a number
    if (_name == null) return false;
    String name = xsdToJavaPodWrapper();
    Long code = typeCodes.get(name);
    return code != null && (code & 0x11111111000l) != 0
        && (code & 0x100) != 0;
  }

  public boolean isNull() { return "null".equals(_name); }

  public boolean isRdfType() { return _class != null || "Rdf".equals(_name); }

  public boolean isXsdType() {
    return _name != null && _name.charAt(0) == '<';
  }

  public boolean isCollection() {
    if (_name == null) return false;
    return (_name.endsWith("Map")
        || _name.endsWith("Set")
        || _name.endsWith("List")
        || _name.startsWith("RdfSet")
        || _name.startsWith("RdfList"));
  }

  public boolean isDialogueAct() {
    return _class != null && DIALACT_CLASS.equals(_class);
  }

  public boolean isString() {
    return "String".equals(_name) || "<xsd:string>".equals(_name);
  }

  public boolean isBool() {
    return "boolean".equals(_name) || "Boolean".equals(_name)
        || "<xsd:boolean>".equals(_name);
  }

  public boolean isUnspecified() {
    return _name == null;
  }


  public RdfClass getRdfClass() { return _class; }

  public Type getInnerType() {
    if (_parameterTypes != null && _parameterTypes.size() == 1)
      return _parameterTypes.get(0);
    return null;
  }

  /** Return the more specific of the two types, if it exists, null otherwise */
  public Type unifyTypes(Type right) {
    if (_name == null || _name.equals("Object")) return right;
    if (right == null || right._name == null
        || right._name.equals("Object") || this.equals(right))
      return this;

    // check if these are (real) RDF types and are in a type relation.
    if (_class != null || right._class != null) {
      if (_class != null && right._class != null) {
        String result = PROXY.fetchMostSpecific(
            _class.toString(), right._class.toString());
        if (result == null) return null; // incompatible types
        return new Type(result);
      }
      if ("Rdf".equals(_name)) return right;
      if ("Rdf".equals(right._name)) return this;
      return null;
    }

    String l = xsdToJavaPod();
    String r = right.xsdToJavaPod();
    // this should return the more specific of the two, or null if they are
    // incompatible
    Long leftCode = typeCodes.get(l);
    if (leftCode == null) leftCode = JAVA_TYPE;
    Long rightCode = typeCodes.get(r);
    if (rightCode == null) rightCode = JAVA_TYPE;

    long common = leftCode & rightCode;
    if (common == leftCode) {
      return this;
    }
    if (common == rightCode) {
      return right;
    }

    return null;
  }

  /** returns a correct java type for use in generated code */
  @Override
  public String toString() {
    if (_name == null) return "Object /* (unknown) */";
    StringBuffer out = new StringBuffer();
    toString(out);
    return out.toString();
  }

  private void toString(StringBuffer sb) {
    if (isDialogueAct())
      sb.append("DialogueAct");
    else if (isRdfType())
      sb.append("Rdf");
    else if (isXsdType())
      sb.append(xsdToJavaPodWrapper());
    else
      sb.append(_name);

    if (_parameterTypes != null) {
      sb.append('<');
      boolean first = true;
      for (Type pType : _parameterTypes) {
        if (! first) sb.append(", ");
        if (pType != null) pType.toString(sb);
        else sb.append("null");
        first = false;
      }
      sb.append('>');
    }
  }

  @Override
  public boolean equals(Object o){
    if (!(o instanceof Type)) return false;
    Type t = (Type)o;
    if (_class != null) {
      return _class == t._class;
    }
    if (isPODType()) {
      String l = xsdToJavaPodWrapper();
      String r = t.xsdToJavaPodWrapper();
      return l.equals(r);
    }
    // TODO: check the parameter type list (recursively)
    return _name != null && _name.equals(t._name);
  }

  /** Return true if the right type has to be casted to this type, e.g.,
   *  <xsd:long> or long to int. The types are assumed to be compatible, so
   *  casting is either not necessary or possible.
   */
  public boolean needsCast(Type right) {
    Type res = unifyTypes(right);
    return res.equals(this);
  }

  /** Only for the visualisation */
  public String getRep() {
    if (_class != null) return _class.toString();
    if (isRdfType()) return _name;
    return toString();
  }
}
