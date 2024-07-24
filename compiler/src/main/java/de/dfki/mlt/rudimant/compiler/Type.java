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

package de.dfki.mlt.rudimant.compiler;

import java.util.*;

import de.dfki.lt.hfc.db.rdfProxy.RdfClass;
import de.dfki.lt.hfc.db.rdfProxy.RdfProxy;

/** A class to represent the types/classes used in the code, the relation
 *  between Java and RDF types, the handling of complex (parameterised) types,
 *  etc.
 */
public class Type {
  private static RdfProxy PROXY;
  private static final Map<String, String> JAVA_CLASSES = new HashMap<>();

  static final Map<String, Long> typeCodes = new HashMap<>();
  static final Map<Long, String> code2type = new HashMap<>();

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
  // the bit that is set exclusively for container types
  static final long CONTAINER_BIT = 0b1000l;
  // does *not* include container types!
  static final long POD_MASK = 0b111111110000l;

  /** This represents the "assignable" hierarchy, with the exception of
   *  interchangeability of POD with container type, which must be checked
   *  manually
   *
  static final Map<String, Long> assignCodes = new HashMap<>();
  static final Map<Long, String> assigncode2type = new HashMap<>();
  static {
    assignCodes.put("double",           0b11111101000l);
    assignCodes.put("Double",           0b11111100000l);
    assignCodes.put("float",            0b11111001000l);
    assignCodes.put("Float",            0b11111000000l);
    assignCodes.put("long",             0b11110001000l);
    assignCodes.put("Long",             0b11110000000l);
    assignCodes.put("int",              0b11100001000l);
    assignCodes.put("Integer",          0b11100000000l);
    assignCodes.put("short",            0b11000001000l);
    assignCodes.put("Short",            0b11000000000l);
    assignCodes.put("byte",             0b10000001000l);
    assignCodes.put("Byte",             0b10000000000l);
    assignCodes.put("char",             0b11000011000l);
    assignCodes.put("Character",        0b11000010000l);
    assignCodes.put("boolean",         0b100000001000l);
    assignCodes.put("Boolean",         0b100000000000l);
    for (Map.Entry<String, Long> entry : assignCodes.entrySet()) {
      assigncode2type.put(entry.getValue(), entry.getKey());
    }
  }*/

  // private static final long CONTAINER_MASK = ~ CONTAINER_BIT;

  // This represents a correct hierarchy for type unification for expressions.
  // the type unification will return the result type of the expression.
  // This hierarchy does not work for assignments or function calls (parameter
  // substitution).
  static {
    typeCodes.put("Object",                  0b1111l);
    typeCodes.put("String",                     0b1l);
    typeCodes.put("Rdf",                       0b10l);
    typeCodes.put("DialogueAct",              0b100l);
    typeCodes.put("double",           0b10000000000l);
    typeCodes.put("Double",           0b10000001000l);
    typeCodes.put("float",            0b11000000000l);
    typeCodes.put("Float",            0b11000001000l);
    typeCodes.put("long",             0b11100000000l);
    typeCodes.put("Long",             0b11100001000l);
    typeCodes.put("int",              0b11110000000l);
    typeCodes.put("Integer",          0b11110001000l);
    typeCodes.put("short",            0b11111000000l);
    typeCodes.put("Short",            0b11111001000l);
    typeCodes.put("byte",             0b11111100000l);
    typeCodes.put("Byte",             0b11111101000l);
    typeCodes.put("char",             0b11110010000l);
    typeCodes.put("Character",        0b11110011000l);
    typeCodes.put("boolean",         0b100000000000l);
    typeCodes.put("Boolean",         0b100000001000l);
    typeCodes.put("null",           0b1000000000000l);
    typeCodes.put("void",          0b10000000000000l);
    for (Map.Entry<String, Long> entry : typeCodes.entrySet()) {
      code2type.put(entry.getValue(), entry.getKey());
    }
  }

  public static void setProxy(RdfProxy proxy) {
    PROXY = proxy;
  }

  public static void setJavaClasses(Map<String, String> resolved) {
    JAVA_CLASSES.putAll(resolved);
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
   *
   *  This makes a lot of sense, since there is no other way to signal that the
   *  "real" type (RDF or XSD) can not be seen by the Java compiler.
   */
  private boolean _castRequired = false;

  public boolean castRequired() { return _castRequired; }

  public void setCastRequired() {
    if (isRdfType() || isXsdType())_castRequired = true;
    setCastRequiredInner();
  }

  public void setCastRequiredInner() {
    if (_parameterTypes != null) {
      for (Type p : _parameterTypes) if (p !=null) p.setCastRequired();
    }
  }

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

  // =========================================================================
  // Factory methods
  // =========================================================================

  /** This is only to be used to create complex types for function definitions */
  public static Type getFunctionType(Type retType,
      Type methodOfType, List<Type> paramTypes) {
    List<Type> parameterTypes = new LinkedList<>();
    if (retType == null) retType = getNoType();
    parameterTypes.add(retType);
    if (methodOfType != null)
      parameterTypes.add(methodOfType);
    if (paramTypes != null)
      parameterTypes.addAll(paramTypes);
    return new Type(methodOfType == null ? "Function" : "Method", parameterTypes);
  }

  public static Type getFieldType(Type calledUpon, Type type) {
    @SuppressWarnings("serial")
    List<Type> parTypes = new ArrayList<Type>(2){{ add(type); add(calledUpon); }};
    Type t = new Type("Field", parTypes);
    return t;
  }

  private Type renameTypeVars(int[] i, Map<String, String> map) {
    String name = _name;
    if (isTypeVariable()) {
      if (map.containsKey(name)) name = map.get(name);
      else {
        name = "" + (char)('A' + i[0]++);
        map.put(_name, name);
      }
    }
    Type result;
    if (_parameterTypes == null) {
      result = new Type(name);
    } else {
      List<Type> params = new ArrayList<>();
      for (Type t : _parameterTypes) {
        params.add(t.renameTypeVars(i, map));
      }
      result = new Type(name, params);
    }
    if (_castRequired) result.setCastRequired();
    return result;
  }

  /** This is only to be used to create complex types for function expressions.
   *  It makes sure the type variables of all inner types do not overlap by
   *  renaming them if necessary.
   */
  public static Type getFunctionCallType(Type retType,
      Type methodOfType, List<Type> paramTypes) {
    int[] i = { 0 };
    List<Type> parameterTypes = new LinkedList<>();
    if (retType == null) parameterTypes.add(getNoType());
    else parameterTypes.add(retType.renameTypeVars(i, new HashMap<>()));
    if (methodOfType != null)
      parameterTypes.add(methodOfType.renameTypeVars(i, new HashMap<>()));
    for(Type p : paramTypes) {
      parameterTypes.add(p.renameTypeVars(i, new HashMap<>()));
    }
    return new Type(methodOfType == null ? "Function" : "Method", parameterTypes);
  }

  /** This is only to be used to create complex types for lambda expressions */
  public static Type getRdfComplexType(String name, Type parameterType) {
    List<Type> parameterTypes = new LinkedList<>();
    parameterTypes.add(parameterType);
    Type result = new Type(name, parameterTypes);
    for(Type p : parameterTypes) {
      p._castRequired = true;
    }
    return result;
  }

  public Type(String name) {
    _name = name;
    if (_name == null) return;
    if (! JAVA_CLASSES.containsKey(_name)) {
      rdfIfy();
    }
  }

  public Type(String outer, List<Type> inner) {
    this(outer);
    _parameterTypes = inner;
  }

  /** Create a duplicate (shallow copy), where castRequired is false */
  public Type copyNoCast() {
    Type result = new Type();
    result._castRequired = false;
    result._class = _class;
    result._name = _name;
    result._parameterTypes = _parameterTypes;
    return result;
  }

  public String get_name() {
    return _name;
  }

  private void getTypeVarsRec(Set<String> typeVars) {
    if (_name == null) return;
    if (isTypeVariable())
      typeVars.add(_name);

    if (_parameterTypes == null) return;
    for (Type t : _parameterTypes)
      if (t != null) { t.getTypeVarsRec(typeVars); }
  }

  private Set<String> getTypeVars() {
    Set<String> result = new HashSet<>();
    getTypeVarsRec(result);
    return result;
  }


  private String xsdToJavaPodWrapper() {
    String ret = xsd2java.get(_name);
    return (ret != null) ? ret : _name;
  }

  private String xsdToJavaPod() {
    String ret = xsd2java.get(_name);
    Long code = typeCodes.get(ret);
    if (code == null) return _name;
    ret = code2type.get(code & ~ CONTAINER_BIT);
    return (ret != null) ? ret : _name;
  }

  private String getContainerName() {
    if (isPODType()) {
      Long code = typeCodes.get(_name);
      code = code | CONTAINER_BIT;
      return code2type.get(code);
    }
    return _name;
  }

  private Long getCode() {
    if (_name == null) return null;
    String name = xsdToJavaPodWrapper();
    return typeCodes.get(name);
  }

  /** Indicates if this type should be compared with == or equals */
  public boolean isPODType() {
    if (isNull()) return true;
    Long code = getCode();
    return code != null && (code & POD_MASK) != 0
        && (code & CONTAINER_BIT) == 0; // containers may be null!
  }

  /** Return true if this is a number class, POD or container */
  public boolean isNumber() {
    // if we're ignorant, it is Object, and null is not a number
    Long code = getCode();
    return code != null && (code & POD_MASK) != 0; // it's a number
  }

  /** Return true if this is a Java wrapper class */
  public boolean isContainer() {
    // if we're ignorant, it is Object, and null is not a number
    Long code = getCode();
    return code != null && (code & CONTAINER_BIT) != 0; // It's a container type
  }

  /** Return true if this is a Java wrapper class for some number */
  public boolean isNumberContainer() {
    // if we're ignorant, it is Object, and null is not a number
    Long code = getCode();
    return isContainer() && (code & POD_MASK) != 0; // it's a number
  }

  public boolean isNull() { return "null".equals(_name); }

  public boolean isRdfType() { return _class != null || "Rdf".equals(_name); }

  public boolean isJavaConvertible() {
    return isContainer() || isRdfType() || isString() ||
        "Date".equals(toJava());
  }

  /** This returns true for all XSD and resolved RDF types */
  public boolean isXsdType() {
    return _name != null && _name.charAt(0) == '<';
  }

  private int getCollectionCode() {
    if (isUnspecified() || isTypeVariable()) return 0x1111b;
    if (_name.endsWith("Map")) return 0x1000b;
    else if (_name.endsWith("Array")) return 0x0100b;
    else if (_name.endsWith("Set")) return 0x010b;
    else if (_name.endsWith("List")) return 0x001b;
    else if (_name.startsWith("Collection")) return 0x1111b;
    return 0;
  }

  public boolean isCollection() {
    return ! (isUnspecified() || isTypeVariable())
        && getCollectionCode() != 0;
  }

  public boolean isDialogueAct() {
    return "DialogueAct".equals(_name);
  }

  public boolean isString() {
    return "String".equals(_name) || "<xsd:string>".equals(_name);
  }

  public boolean isBool() {
    return "boolean".equals(_name);
  }

  public boolean isBoolContainer() {
    return "Boolean".equals(_name) || "<xsd:boolean>".equals(_name);
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

  public boolean isTypeVariable() {
    return _name != null && _name.length() == 1
        && _name.charAt(0) >= 'A' && _name.charAt(0) <= 'Z';
  }

  public boolean isStringConvertible() {
    return isPODType() || isRdfType() || isNumber();
  }

  public boolean isField() {
    return "Field".equals(_name);
  }

  public Type getContainer() {
    String name = getContainerName();
    return (name != null) ? new Type(name) : null;
  }

  public String getStringConversionFunction() {
    assert(isStringConvertible());
    String typeName = _name;
    if (isPODType()) {
      // if it's sth like int, return Integer.toString
      Long code = typeCodes.get(_name);
      if (code == null) typeName = _name;
      typeName = code2type.get(code | CONTAINER_BIT);
      typeName += ".toString";
    } else {
      // it's a Java type
      typeName = ".toString";
    }
    return typeName;
  }

  // ######################################################################
  // Function type methods
  // ######################################################################

  public boolean isFunction() {
    return "Function".equals(_name);
  }

  public boolean isMethod() {
    return "Method".equals(_name);
  }

  public Type getReturnedType() {
    if (! (isMethod() || isFunction() || isField())) return null;
    return _parameterTypes.get(0);
  }

  public void setReturnType(Type t) {
    _parameterTypes.set(0, t);
  }

  public Type getClassOf() {
    if (! (isMethod() || isField())) return null;
    return _parameterTypes.get(1);
  }

  public Iterator<Type> getParameterTypes() {
    if (! (isMethod() || isFunction())) return null;
    Iterator<Type> result = _parameterTypes.iterator();
    result.next(); // return type
    if (isMethod()) result.next(); // class of method
    return result;
  }

  /** Return true if the given Function type filled with actual parameter types
   *  matches the signature of this type.
   */
  public boolean signatureMatches(Type actualType) {
    if (! actualType._name.equals(_name)) // both method OR function
      return false;
    List<Type> actual = actualType._parameterTypes;
    if (_parameterTypes == null || actualType._parameterTypes == null) {
      return _parameterTypes == actualType._parameterTypes;
    } else {
      if (_parameterTypes.size() != actual.size())
        return false;
    }
    for (int i = 1; i < _parameterTypes.size(); i++) {
      if (_parameterTypes.get(i).unifyTypes(actual.get(i)) == null)
        return false;
    }
    return true;
  }

  // ######################################################################
  // RDF type methods
  // ######################################################################

  public RdfClass getRdfClass() { return _class; }

  public Type getObjectRdf() {
    Type ret = new Type("Object");
    ret._class = _class;
    return ret;
  }

  public Type getInnerType() {
    if (_parameterTypes != null && _parameterTypes.size() == 1)
      return _parameterTypes.get(0);
    return null;
  }

  public void setInnerType(Type inner) {
    _parameterTypes = new ArrayList<>(1);
    _parameterTypes.add(inner);
  }

  @SuppressWarnings("serial")
  private Type checkDefaultCollection() {
    if (! isUnspecified() || getInnerType() == null) return this;
    Type i = getInnerType();
    return new Type("Array",
        new ArrayList<Type>(){{ add(i);
        // TODO: MAYBE WE WANT THIS?
          //add(i.isPODType() ? new Type(i.getContainer()) : i);
        }});
  }

  public Type unifyTypes(Type right) {
    if (isUnspecified() || isNull() || _name.equals("Object")
        || isTypeVariable()) return right.checkDefaultCollection();
    if (right == null || right.isNull() || this.equals(right)
        || ((right.isUnspecified() || right.isTypeVariable())
            && right._parameterTypes == null))
      return this.checkDefaultCollection();
    // TODO: this is not for types with more than one parameter type. Can it be
    // extended?
    int leftCollCode = getCollectionCode();
    int rightCollCode = right.getCollectionCode();
    if ((leftCollCode | rightCollCode) != 0) {
      // collection vs. simple type?
      if (leftCollCode == 0 || rightCollCode == 0
          || (_parameterTypes != null && right._parameterTypes != null
              && _parameterTypes.size() != right._parameterTypes.size()))
        return null;
      String outer = (leftCollCode & rightCollCode) == leftCollCode ?
          this._name : right._name;
      List<Type> resParamTypes = new ArrayList<Type>();
      if (_parameterTypes == null && right._parameterTypes == null) {
        return new Type(outer);
      } else if (_parameterTypes == null) {
        resParamTypes.addAll(right._parameterTypes);
      } else if (right._parameterTypes == null) {
        resParamTypes.addAll(_parameterTypes);
      } else {
        for (int i = 0; i < _parameterTypes.size(); ++i) {
          Type innerLeft = _parameterTypes.get(i);
          Type innerRight = right._parameterTypes.get(i);
          Type inner = null;
          if (innerLeft == null) {
            inner =  innerRight;
          } else if (innerRight == null) {
            inner = innerLeft;
          } else {
            inner = innerLeft.unifyTypes(innerRight);
          }
          if (inner == null)
            return null;
          resParamTypes.add(inner);
        }
      }
      if (resParamTypes.size() == 1 && outer == null) {
        outer = "Array";
      }
      if (! "Array".equals(outer) && resParamTypes.get(0).isPODType()) {
        resParamTypes.set(0, new Type(resParamTypes.get(0).getContainerName()));
      }
      return new Type(outer, resParamTypes);
    }
    // non-collection type
    Type result = unifyBasicTypes(right);
    if (result == null) return result;
    result._castRequired = (isRdfType() || isXsdType())
        && (_castRequired || right._castRequired);
    return result;
  }

  /** Return the more specific of the two types, if it exists, null otherwise */
  private Type unifyBasicTypes(Type right) {
    if (isUnspecified() || isNull() || _name.equals("Object")) return right;
    if (right == null || right.isNull() || right.isUnspecified()
        || this.equals(right))
      return this;

    // if one of those is a generic type, the real type is the more
    // specific one and they are compatible
    // (necessary because this method is also used for funccall evaluation)
    if (this.isTypeVariable()) return right;
    if (right.isTypeVariable()) return this;

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
    return toDebugString(new StringBuffer());
  }

  /** returns a correct java type for use in generated code */
  public String toJava() {
    if (_name == null) return "Object /*UNK*/";
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
    if (_name.equals("Map")) collType = "LinkedHashMap";
    else if (_name.equals("List")) collType = "ArrayList";
    else if (_name.equals("Array")) collType = "Array";
    else if (_name.equals("Set")) collType = "LinkedHashSet";
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
          sb.append("Object[").append(
              pType._class == null ? "Rdf" : pType._class.toString())
          .append(pType._castRequired ? "]*" : "]");
        else
          pType.toDebugString(sb);
        first = false;
      }
      sb.append('>');
    }
  }

  private String toDebugString(StringBuffer sb) {
    if (_class != null)
      sb.append(_class.toString() + "[" + toJava() + (_castRequired ? "]*" : "]"));
    else if (isRdfType())
      sb.append(_name + "[" + toJava() + (_castRequired ? "]*" : "]"));
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
      else if (isTypeVariable())
        sb.append("Object");
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
    if (_name != null) {
      if (!_name.equals(t._name)) return false;
    } else {
      if (t._name != null) return false;
    }
    if (_parameterTypes != null) {
      if (t._parameterTypes != null
          && _parameterTypes.size() == t._parameterTypes.size()) {
        for (int i = 0; i < _parameterTypes.size(); ++i) {
          if (! _parameterTypes.get(i).equals(t._parameterTypes.get(i)))
            return false;
        }
      } else {
        return false;
      }
    } else if (t._parameterTypes != null) return false;
    return true;
  }

  @Override
  public int hashCode() {
    int result = (_name == null) ? 777 : _name.hashCode();
    if (_class != null) {
      result = 7 * result + _class.hashCode();
    }
    if (_parameterTypes != null) {
      for (Type t : _parameterTypes)
        if (t != null) result = 7 * result + t.hashCode();
    }
    return result;
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

  // ######################################################################
  // Resolution of complex types with type variables
  // ######################################################################

  /** A union-find data structure to store equivalence sets of type variables.
   *  It differs from the standard implementation in that a Type that is not
   *  a type variable automatically becomes a representative.
   */
  private static class Partition {
    HashMap<Type, Type> map = new HashMap<>();

    List<String> msgs;

    /** Find the representative of type t */
    Type findRep(Type t) {
      if (! t.isTypeVariable() || ! map.containsKey(t)) return t;
      Type c = map.get(t);
      if (c == t) return t;
      Type rep = findRep(c);
      map.put(c, rep); // path compression
      return rep;
    }

    /** Set the representative of type t ot r, and of all children on the way
     *  to the old representative.
     */
    void setRep(Type t, Type r) {
      Type child = map.get(t);
      if (child == null || ! child.isTypeVariable() || child == t) {
        map.put(t, r);
        return;
      }
      setRep(child, r);
    }

    // t1 is always a type variable
    boolean merge (Type t1, Type t2) {
      Type r1 = findRep(t1);
      Type r2 = findRep(t2);
      if (r1 == r2) return true;
      Type u = r1.unifyTypes(r2);
      if (u == null) {
        msgs.add("Type clash during resolution: ["
            + t1 + "]" + r1 + " <> [" + t2 + "]" + r2);
        return false;
      }
      setRep(t1, u);
      if (t2.isTypeVariable())
        setRep(t2, u);
      return true;
    }

    @Override
    public String toString() { return map.toString(); }
  }

  /** Merge the two type signatures t1 and t2, such that resolved type variables
   *  flow from one to the other.
   */
  private static void resolveTypeVarsRec(Partition p, Type t1, Type t2) {
    if (t1.isTypeVariable())
      p.merge(t1, t2);
    else if (t2.isTypeVariable())
      p.merge(t2, t1);

    List<Type> inner1 = t1._parameterTypes;
    List<Type> inner2 = t2._parameterTypes;
    if (inner1 == null || inner2 == null || inner1.size() != inner2.size()) {
      // no further resolution possible, incompatible signatures
      return;
    }
    Iterator<Type> i1 = inner1.iterator();
    Iterator<Type> i2 = inner2.iterator();
    while (i1.hasNext()) {
      resolveTypeVarsRec(p, i1.next(), i2.next());
    }
  }

  private Type replaceVarsRec(Partition p) {
    String name = _name;
    boolean castRequired = _castRequired;
    if (isTypeVariable()) {
      Type rep =p.findRep(this);
      name = rep._name;
      if (_parameterTypes == null)
        _parameterTypes = rep._parameterTypes;
      castRequired = rep._castRequired;
    }
    Type result;
    if (_parameterTypes != null) {
      List<Type> paramTypes = new ArrayList<>();
      for(Type t : _parameterTypes) {
        paramTypes.add(t.replaceVarsRec(p));
      }
      result = new Type(name, paramTypes);
    } else {
      result = new Type(name);
    }
    if (castRequired) result.setCastRequired();
    return result;
  }

  private int replaceUnspecifiedRec(int freeVar, BitSet taken) {
    if (_parameterTypes != null) {
      for(int i = 0; i < _parameterTypes.size(); ++i) {
        Type c = _parameterTypes.get(i);
        if (c == null || c.isUnspecified()) {
          while (taken.get(freeVar)) {
            ++freeVar;
          }
          taken.set(freeVar);
          _parameterTypes.set(i, new Type("" + (char)('A' + freeVar)));
        } else {
          freeVar = c.replaceUnspecifiedRec(freeVar, taken);
        }
      }
    }
    return freeVar;
  }

  /** Replace all unspecified types in funType and callType with new variables
   */
  private void replaceUnspecifiedWithVars(Type funType, Type callType) {
    BitSet b = new BitSet();
    // We must make the type variables in callType disjoint from those in
    // funType first, therefore, we rename the variables
    for(String var : funType.getTypeVars()) b.set(var.charAt(0) - 'A');
    for(String var : callType.getTypeVars()) b.set(var.charAt(0) - 'A');
    callType.replaceUnspecifiedRec(0, b);
  }


  /** Resolve as many type variables as possible to concrete types, and return
   *  a new type with type vars resolved.
   *
   * @param funType the type of the calling function
   * @param callType the type computed from the actual call parameters
   * @return a new type with resolved type variables
   */
  public static Type resolveTypeVars(Type funType, Type callType, List<String> clashes) {
    Partition p = new Partition();
    p.msgs = clashes;
    callType.replaceUnspecifiedWithVars(funType, callType);
    resolveTypeVarsRec(p, funType, callType);
    // Now p contains "resolved" types for all type vars, create the result for
    // type2 with all possible vars replaced
    return callType.replaceVarsRec(p);
  }
}
