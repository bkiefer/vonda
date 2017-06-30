package de.dfki.mlt.rudimant;

import static de.dfki.mlt.rudimant.Constants.DIALOGUE_ACT_TYPE;

import java.util.ArrayList;
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

  public Type(String typeSpec) {
    if (typeSpec == null) {
      _name = null;
      return;
    }
    int i;
    String nameSpec = typeSpec;
    if ((i = typeSpec.indexOf('<')) > 0) {
      nameSpec = typeSpec.substring(0, i).trim();
      String[] partypes = typeSpec.substring(i+1, typeSpec.lastIndexOf('>')).split(" *, *");
      _parameterTypes = new ArrayList<>();
      for (String par : partypes)
        _parameterTypes.add(new Type(par));
    }
    _name = nameSpec;
    if (typeCodes.containsKey(nameSpec)) return;
    if (nameSpec.charAt(0) == '<') {
      // xsd or RDF type
      _class = PROXY.getClass(nameSpec);
    } else {
      // try to get an appropriate RDF class
      _class = PROXY.fetchClass(nameSpec);
    }
  }

  public String get_name() {
    return _name;
  }

  /** Indicates if this type should be compared with == or equals */
  public boolean isPODType() {
    if (_name == null) return true;
    String name = RdfClass.xsdToJavaPod(_name);
    Long code = typeCodes.get(name);
    return code != null && (code & 0x11111111000l) != 0;
  }

  public boolean isRdfType() {
    return _class != null || _name != null && _name.charAt(0) == '<';
  }

  public Type getInnerType() {
    if (_parameterTypes != null && _parameterTypes.size() == 1)
      return _parameterTypes.get(0);
    return null;
  }

  public boolean isComplexType() {
    if (_name == null) return false;
    return (_name.startsWith("Map")
        || _name.startsWith("Set")
        || _name.startsWith("List")
        || _name.startsWith("RdfSet")
        || _name.startsWith("RdfList")
        || ((_name.endsWith(">") && ! isRdfType())));
  }

  /**
   * returns a correct java type for xsd types
   * @param something
   * @return
   */
  private Type convertXsdType(){
    String ret = RdfClass.xsdToJavaPod(get_name());
    if (ret != null) return new Type(ret);
    return this;
  }

  public boolean isDialogueAct() {
    return DIALOGUE_ACT_TYPE.equals(_name);
  }

  public boolean isString() {
    return "String".equals(_name);
  }

  public boolean isBool() {
    return "boolean".equals(_name.toLowerCase());
  }

  public boolean isUnspecified() {
    return _name == null;
  }

  /** Return the more specific of the two types, if it exists, null otherwise */
  public Type unifyTypes(Type right) {
    if (this.equals(getNoType()) || new Type("Object").equals(this)) return right;
    if (right == null) return getNoType();
    if (right.equals(getNoType()) || new Type("Object").equals(right)) return this;
    Type left = convertXsdType();
    Type r = right.convertXsdType();
    // check if these are RDF types and are in a type relation.
    if (left.isRdfType() || r.isRdfType()) {
      if (left.isRdfType() && r.isRdfType())
        return new Type(PROXY.fetchMostSpecific(left.get_name(), r.get_name()));
      if ("Rdf".equals(left.get_name())) return right;
      if ("Rdf".equals(r.get_name())) return this;
      return getNoType();
    }

    // this should return the more specific of the two, or null if they are
    // incompatible
    Long leftCode = typeCodes.get(left.get_name());
    if (leftCode == null) leftCode = JAVA_TYPE;
    Long rightCode = typeCodes.get(r.get_name());
    if (rightCode == null) rightCode = JAVA_TYPE;

    long common = leftCode & rightCode;
    if (common == leftCode) {
      return this;
    }
    if (common == rightCode) {
      return right;
    }
    // TODO: if these are complex types like List<sth>, they can be unified, too!!!
    if(left.get_name().equals(r.get_name())) return this;
    return getNoType();
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
    if (isRdfType())
      sb.append(isDialogueAct() ? "DialogueAct" : "Rdf");
    else
      sb.append(_name);

    if (_parameterTypes != null) {
      sb.append('<');
      boolean first = true;
      for (Type pType : _parameterTypes) {
        if (! first) sb.append(", ");
        pType.toString(sb);
        first = false;
      }
      sb.append('>');
    }
  }

  @Override
  public boolean equals(Object o){
    if (!(o instanceof Type)) return false;
    return _name != null && _name.equals(((Type)o).get_name());
  }

}
