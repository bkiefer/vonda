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

  public static Type getNoType(){
	return new Type(null);
  }
  
  /** Indicates if this type should be compared with == or equals */
  public boolean isPODType() {
	if (_name == null) return true;
    String name = RdfClass.xsdToJavaPod(_name);
    Long code = typeCodes.get(name);
    return code != null && (code & 0x11111111000l) != 0;
  }

  public boolean isRdfType() {
    return (_name != null && get_name().charAt(0) == '<');
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

  /** Return the "inner" type of a complex type expression */
  public Type getInnerType() {
    int left = _name.indexOf('<');
    if (left < 0) return new Type("Object"); // or return this??
    int right = _name.lastIndexOf('>');
    return new Type(_name.substring(left + 1, right));
  }

  public Type getOuterType() {
    int i = _name.indexOf('<');
    if (i < 0) return this;
    return new Type(_name.substring(0, i));
  }

  /**
   * returns a correct java type for xsd types
   * @param something
   * @return
   */
  public Type convertXsdType(){
    String ret = RdfClass.xsdToJavaPod(get_name());
    if (ret != null) return new Type(ret);
    return this;
  }

  /**
   * returns a correct java type for whatever input
   * @param something
   * @return
   */
  public Type convertRdfType(){
	if (_name == null) return this;
    Type something = this.convertXsdType();
    if(!something.isRdfType()) {
      String name = something.get_name();
      if(name.contains("<")){
        // might be sth like List<Child>
        String som = name.substring(0, name.indexOf("<") + 1);
        Type s = new Type(name.substring(name.indexOf("<")
                + 1, name.lastIndexOf(">")));
        som += s.convertRdfType().get_name() + ">";
        something.set_name(som);
      }
      return something;
    }
    String d = (DIALOGUE_ACT_TYPE.equals(something.get_name())) ? "DialogueAct" : "Rdf";
    return new Type(d);
  }

  public Type checkRdf() {
    // if is necessary, because otherwise, Object as the static type in a
    // declaration gets changed to RdfType by
    // VGenerationVisitor.visitNode(ExpAssignment node)
    if (new Type("Object").equals(this)
        || new Type("String").equals(this)){ // what about Integer, int, etc.??
      return this;
    }
    String n = get_name();
    if(!n.startsWith("<") && n.contains("<")){
      // might be sth like List<Child>
      n = n.substring(0, n.indexOf("<") + 1);
      Type t = new Type(get_name().substring(get_name().indexOf("<") + 1, get_name().lastIndexOf(">")));
      n += t.checkRdf().get_name() + ">";
    }
    RdfClass clazz = PROXY.fetchClass(n);
    if (clazz != null) {
      n = clazz.toString(); // the URI of the class
    }
    return new Type(n);
  }


  // **********************************************************************
  // End of static fields and methods
  // **********************************************************************

  private String _name;

  /** The parameters of a parameterised type, null if a simple type */
  private List<Type> _parameterTypes;

  public Type(String typeName) {
    set_name(typeName);
  }

  public String get_name() {
	return _name;
  }

  public void set_name(String _name) {
	this._name = _name;
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
  
  @Override
  public String toString(){
	if (_name == null) return "Object /* (unknown) */";
	return _name;
  }
  
  @Override
  public boolean equals(Object o){
	if (!(o instanceof Type)) return false;
	return _name != null && _name.equals(((Type)o).get_name());
  }

}
