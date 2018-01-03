package de.dfki.mlt.rudimant.compiler;

import static de.dfki.mlt.rudimant.compiler.Constants.DIALOGUE_ACT_TYPE;

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

  static Map<String, Long> assignCodes = new HashMap<>();
  static Map<Long, String> assigncode2type = new HashMap<>();

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
      {"<xsd:dateTimeStamp>", "Long"},
    };
    for (String[] pair : xsd2javatypes) {
      xsd2java.put(pair[0], pair[1]);
    }
  }

  /*
  1. Types that are automatically converted in assignments, expressions or
     function calls (as parameters)
     byte -> short -> int -> long -> float -> double

     PODs and their corresponding container types can be used interchangeably,
     but *not*, e.g., Long l = (int)i, this needs a cast to the corresponding
     POD(!) type.

     The other way around works: double d = F; where F is Float

     Also, the above conversion for the PODs does not work for the container
     types, Double D = F; is illegal, but D = D + F works

   2. For Java and RDF type: if they are in a subclass relation, the more
      specific can be assigned/used as parameter for the more general
   */

  static final long JAVA_TYPE = 0x10;

  /** This represents the "assignable" hierarchy, with the exception of
   *  interchangeability of POD with container type, which must be checked
   *  manually
   */
  static {
    assignCodes.put("double",           0b1111110100l);
    assignCodes.put("Double",           0b1111110000l);
    assignCodes.put("float",            0b1111100100l);
    assignCodes.put("Float",            0b1111100000l);
    assignCodes.put("long",             0b1111000100l);
    assignCodes.put("Long",             0b1111000000l);
    assignCodes.put("int",              0b1110000100l);
    assignCodes.put("Integer",          0b1110000000l);
    assignCodes.put("short",            0b1100000100l);
    assignCodes.put("Short",            0b1100000000l);
    assignCodes.put("byte",             0b1000000100l);
    assignCodes.put("Byte",             0b1000000000l);
    assignCodes.put("char",             0b1100001100l);
    assignCodes.put("Character",        0b1100001000l);
    assignCodes.put("boolean",         0b10000000100l);
    assignCodes.put("Boolean",         0b10000000000l);
    for (Map.Entry<String, Long> entry : assignCodes.entrySet()) {
      assigncode2type.put(entry.getValue(), entry.getKey());
    }
  }

  private static final long CONTAINER_MASK = ~ 0b100l;

  // This represents a correct hierarchy for type unification for expressions.
  // the type unification will return the result type of the expression.
  // This hierarchy does not work for assignments or function calls (parameter
  // substitution).
  static {
    typeCodes.put("Object",                  0b111l);
    typeCodes.put("String",                    0b1l);
    typeCodes.put("Rdf",                      0b10l);
    typeCodes.put("double",           0b1000000000l);
    typeCodes.put("Double",           0b1000000100l);
    typeCodes.put("float",            0b1100000000l);
    typeCodes.put("Float",            0b1100000100l);
    typeCodes.put("long",             0b1110000000l);
    typeCodes.put("Long",             0b1110000100l);
    typeCodes.put("int",              0b1111000000l);
    typeCodes.put("Integer",          0b1111000100l);
    typeCodes.put("short",            0b1111100000l);
    typeCodes.put("Short",            0b1111100100l);
    typeCodes.put("byte",             0b1111110000l);
    typeCodes.put("Byte",             0b1111110100l);
    typeCodes.put("char",             0b1111001000l);
    typeCodes.put("Character",        0b1111001100l);
    typeCodes.put("boolean",         0b10000000000l);
    typeCodes.put("Boolean",         0b10000000100l);
    typeCodes.put("null",           0b100000000000l);
    typeCodes.put("void",          0b1000000000000l);
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

  /** The RDF class of this type, if any */
  private RdfClass _class;

  /** When true, the type appears to be Object in the Java code, but we know
   *  it's something more specific (mostly because we're treating an RDF
   *  collection of things.
   */
  private boolean _castRequired = false;

  public boolean castRequired() { return _castRequired;};

  private Type() { }

  private void rdfIfy() {
    // this line was added only for testing purposes
    if (PROXY == null) return;
    
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
        _parameterTypes.add(new Type("_".equals(par) ? null : par));
      _name = nameSpec;
    } else if ((i = typeSpec.indexOf('[')) > 0) {
      assert(typeSpec.substring(i+1).trim().equals("]"));
      _name = "Array";
      _parameterTypes = new ArrayList<Type>(1);
      _parameterTypes.add(new Type(typeSpec.substring(0, i).trim()));
    } else {
      _name = typeSpec;
    }
  }


  /** This is only to be used to create complex types for lambda expressions */
  public static Type getComplexType(String name, Type ... parameterTypes) {
    Type result = new Type(name);
    result._parameterTypes = new ArrayList<>(parameterTypes.length);
    result._parameterTypes.addAll(Arrays.asList(parameterTypes));
    return result;
  }

  /** This is only to be used to create complex types for lambda expressions */
  public static Type getRdfComplexType(String name, Type ... parameterTypes) {
    Type result = getComplexType(name, parameterTypes);
    for(Type p : parameterTypes) {
      p._castRequired = true;
    }
    return result;
  }

  public Type(String typeSpec) {
    if (typeSpec == null) {
      _name = null;
      return;
    }
    splitIfy(typeSpec);
    rdfIfy();
  }

  public Type(String outer, Type ... inner) {
    _name = outer;
    _parameterTypes = Arrays.asList(inner);
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
    ret = code2type.get(code & ~ 0b100);
    return (ret != null) ? ret : _name;
  }

  /** Indicates if this type should be compared with == or equals */
  public boolean isPODType() {
    if (_name == null) return false; // if we're ignorant, it is Object
    if (isNull()) return true;
    String name = xsdToJavaPodWrapper();
    Long code = typeCodes.get(name);
    return code != null && (code & 0b11111111000l) != 0
        && (code & 0b100) == 0; // containers may be null!
  }

  /** Return true if this is a Java wrapper class for some number */
  public boolean isNumber() {
     // if we're ignorant, it is Object, and null is not a number
    if (_name == null) return false;
    String name = xsdToJavaPodWrapper();
    Long code = typeCodes.get(name);
    return code != null && (code & 0b11111111000l) != 0
        && (code & 0b100) != 0;
  }

  public boolean isNull() { return "null".equals(_name); }

  public boolean isRdfType() { return _class != null || "Rdf".equals(_name); }

  public boolean isXsdType() {
    return _name != null && _name.charAt(0) == '<';
  }

  private int getCollectionCode() {
    if (_name == null) return 0;
    if (_name.endsWith("Map")) return 0x1000b;
    else if (_name.endsWith("Array")) return 0x0100b;
    else if (_name.endsWith("Set")) return 0x010b;
    else if (_name.endsWith("List")) return 0x001b;
    else if (_name.startsWith("Collection")) return 0x1111b;
    return 0;
  }

  public boolean isCollection() {
    return getCollectionCode() != 0;
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

  public boolean isArray() {
    return "Array".equals(_name);
  }

  public boolean isVoid() {
    return "void".equals(_name);
  }

  public boolean isUnspecified() {
    return _name == null;
  }
  
  public boolean isUnaryGeneric() {
    return !isUnspecified() && _name.matches("[A-Z]");
  }
  
  public boolean containsGeneric() {
    if (_parameterTypes == null)
      return isUnaryGeneric();
    for (Type p : _parameterTypes) {
      if (p.containsGeneric())
        return true;
    }
    return false;
  }

  public RdfClass getRdfClass() { return _class; }

  public Type getObjectRdf() {
    Type ret = new Type("Object");
    ret._class = _class;
    //Type ret = this;
    return ret;
  }

  public Type getInnerType() {
    if (_parameterTypes != null && _parameterTypes.size() == 1)
      return _parameterTypes.get(0);
    return null;
  }

  public List<Type> getParameterTypes() {
    return _parameterTypes != null? _parameterTypes: new ArrayList<>();
  }

  public void setInnerType(Type inner) {
    _parameterTypes = new ArrayList<>(1);
    _parameterTypes.add(inner);
  }

  public void setParameterTypes(List<Type> params) {
    _parameterTypes = params;
  }

  public Type unifyTypes(Type right) {
    if (isUnspecified() || isNull() || _name.equals("Object")) return right;
    if (right == null || right.isNull() || right.isUnspecified()
        || this.equals(right))
      return this;
    // TODO: this is not for types with more than one parameter type. Can it be
    // extended?
    int leftCollCode = getCollectionCode();
    int rightCollCode = right.getCollectionCode();
    if ((leftCollCode | rightCollCode) != 0) {
      Type inner = getInnerType().unifyBasicTypes(right.getInnerType());
      if (inner == null || ((leftCollCode & rightCollCode) == 0))
        return null;
      String outer = (leftCollCode & rightCollCode) == leftCollCode ?
          this._name : right._name;
      Type result = new Type();
      result._name = outer;
      result.setInnerType(inner);
      return result;
    }
    return unifyBasicTypes(right);
  }

  /** Return the more specific of the two types, if it exists, null otherwise */
  public Type unifyBasicTypes(Type right) {
    if (isUnspecified() || isNull() || _name.equals("Object")) return right;
    if (right == null || right.isNull() || right.isUnspecified()
        || this.equals(right))
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

  /** Return true if actualType can be used as function argument when this is the
   *  parameter type, which is the same as if this was the type of the left
   *  hand side of an assignment and actualType on the right hand side.
   *
   *  - if this is null or Object, return true;
   *  - if this an RDF type, actualType must be an RDF type that is subClassOf
   *    this rdf type
   *  - if this is a POD type, get the assign codes for both types, mask the
   *    Container type bit, and perform a bitwise and. The result must be
   *    equal to the assign code of this.
   */
  public boolean isPossibleArgumentType(Type actualType) {
    if (actualType == null)
      return false;
    if (_name == null || actualType._name == null
        || _name.equals("Object") || equals(actualType))
      return true;

    // check if these are (real) RDF types and are in a type relation.
    if (_class != null || actualType._class != null) {
      if (_class != null && actualType._class != null) {
        String result = PROXY.fetchMostSpecific(
            _class.toString(), actualType._class.toString());
        // not incompatible, and actualType is more specific?
        return result != null && result.equals(actualType._class.toString());
      }
      return ("Rdf".equals(_name)); // the most unspecific RDF type
    }

    String l = xsdToJavaPodWrapper();
    String r = actualType.xsdToJavaPodWrapper();
    // this should return the more specific of the two, or null if they are
    // incompatible
    Long leftCode = assignCodes.get(l);
    if (leftCode == null) leftCode = JAVA_TYPE;
    Long rightCode = assignCodes.get(r);
    if (rightCode == null) rightCode = JAVA_TYPE;
    if ((leftCode & CONTAINER_MASK) == (rightCode & CONTAINER_MASK)) return true;


    long result = leftCode | rightCode;
    return result == leftCode;
  }

  /** returns a correct java type for use in generated code */
  @Override
  public String toString() {
    return toDebugString(new StringBuffer());
  }

  /** returns a correct java type for use in generated code */
  public String toJava() {
    if (_name == null) return "Object /* (unknown) */";
    StringBuffer out = new StringBuffer();
    toString(out);
    return out.toString();
  }

  /** This returns a string that can be used to create a collection for this
   *  type, which should be a collection
   */
  public String toConcreteCollection() {
    if (! isCollection()) return toJava();
    if (_name.startsWith("Rdf")) return _name;
    String collType = null;
    if (_name.endsWith("Map")) collType = "LinkedHashMap";
    else if (_name.startsWith("Array")) collType = "Array";
    else if (_name.endsWith("Set")) collType = "LinkedHashSet";
    else if (_name.endsWith("List")) collType = "ArrayList";
    else collType = _name;
    StringBuffer out = new StringBuffer();
    out.append(collType);
    if (_parameterTypes != null && ! _parameterTypes.isEmpty())
      out.append("<>");
    //paramTypes(out);
    return out.toString();
  }

  private void paramTypesDebug(StringBuffer sb) {
    if (_parameterTypes != null) {
      sb.append('<');
      boolean first = true;
      for (Type pType : _parameterTypes) {
        if (! first) sb.append(", ");
        if (pType == null)  // for visualization
          sb.append("null");
        else if (pType.isRdfType())
          sb.append("Object[").append(_class.toString()).append("]");
        else
          pType.toDebugString(sb);
        first = false;
      }
      sb.append('>');
    }
  }

  private String toDebugString(StringBuffer sb) {
    if (_class != null) sb.append(_class.toString() + "[" + toJava() + "]");
    else if (isRdfType())
      sb.append(_name + "[" + toJava() + "]");
    else if (isDialogueAct())
      sb.append("DialogueAct");
    else if (isXsdType())
      sb.append(xsdToJavaPodWrapper());
    else
      sb.append(_name);
    paramTypesDebug(sb);
    return sb.toString();
  }

  private void toString(StringBuffer sb) {
    if (isArray()) {
      sb.append(_parameterTypes.get(0).toJava()).append("[]");
    } else {
      if (isDialogueAct())
        sb.append("DialogueAct");
      else if (isRdfType())
        sb.append("Rdf");
      else if (isXsdType())
        sb.append(xsdToJavaPodWrapper());
      else
        sb.append(_name);
      paramTypes(sb);
    }
  }

  private void paramTypes(StringBuffer sb) {
    if (_parameterTypes != null) {
      sb.append('<');
      boolean first = true;
      for (Type pType : _parameterTypes) {
        if (! first) sb.append(", ");
        if (pType == null)  // for visualization
          sb.append("null");
        // if this exact parameter type can only be known by Java
        // through casting, we need to put Object here
        else if (pType._castRequired)
          sb.append("Object");
        else if (pType.isRdfType())
          sb.append(pType.toJava());
        else
          pType.toString(sb);
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
   *
   *  TODO: SEEMS THIS WORKS, BUT IS IT CORRECT? WHAT ABOUT ASSIGNMENTS
   */
  public boolean needsCast(Type right) {
    Type res = unifyTypes(right);
    return (! res.equals(this) || right._castRequired);
  }

  /** Only for the visualisation */
  public String getRep() {
    if (_class != null) return _class.toString();
    if (isRdfType()) return _name;
    return toJava();
  }
  
  public static Type findTypeForGeneric(Type concrete, Type withGeneric) {
    if (withGeneric == null || concrete == null
        || concrete.getParameterTypes().size() != withGeneric.getParameterTypes().size()) return null;
    // TODO: might want to extend this to other place holders than T
    if (withGeneric.isUnaryGeneric()) return concrete;
    if (withGeneric.getParameterTypes() != null) {
      Type found = null;
      for (int i = 0; i < withGeneric.getParameterTypes().size(); i++) {
        found = findTypeForGeneric(concrete.getParameterTypes().get(i),
            withGeneric.getParameterTypes().get(i));
        if (found != null) return found;
      }
    }
    return null;
  }
  
  public static Type createTypeFromGeneric(Type concrete, Type withGeneric) {
    if (withGeneric.isUnaryGeneric()) return concrete;
    // TODO: assuming here that there cannot be generics like T<String>
    String resultType = withGeneric.get_name();
    List<Type> inner = new ArrayList<>();
    for (int i = 0; i < withGeneric.getParameterTypes().size(); i++) {
      inner.add(createTypeFromGeneric(concrete, withGeneric.getParameterTypes().get(i)));
    }
    Type result = new Type(resultType);
    result.setParameterTypes(inner);
    return result;
  }
}
