package de.dfki.mlt.rudimant;

import java.util.List;

import org.apache.commons.lang.StringUtils;

class Function {

  private String _name;
  private String _origin;
  private Type _returnType;
  private List<Type> _parameterTypes;
  private Type _calledUpon;

  public Function(String name, String origin, Type returnType,
      List<Type> parameterTypes, Type calledUpon) {
    _name = name;
    _origin = origin;
    _returnType = returnType;
    _parameterTypes = parameterTypes;
    _calledUpon = calledUpon;
  }

  public boolean canCallUpon(Type calledOn, Mem mem) {
    // TODO: this is probably not accurate enough; find a more
    // sophisticated way to do this
    return _calledUpon != null &&
        (_calledUpon.get_name().contains("<T>") || _calledUpon.equals("T")) ||
        _calledUpon.unifyTypes(calledOn) != null;
  }

  public String getName() {
    return _name;
  }

  public Type getCalledUpon() {
    return _calledUpon;
  }

  /** TODO BK I changed this function in a way that seemed sensible to me,
   *  but earnestly, i don't understand what it was supposed to do in the first
   *  place. So, if my changes are not correct, PLEASE EXPLAIN THE EXACT
   *  INTENTIONS, MAYBE ADD EXAMPLES, OR WHATEVER.
   */
  public Type getReturnType0(String calledUpon) {
    // TODO: extensively test this magic
    int from = _returnType.get_name().indexOf('<');
    if (from >= 0) {
      // extract the parameter type
      int to = _returnType.get_name().lastIndexOf('>');
      String ret = calledUpon.substring(from + 1, to);
      if(!_returnType.get_name().equals("T")){
        // don't miss anything that is wrapped around <T>
        ret = _returnType.get_name().substring(0, from)
            + ret
            + _returnType.get_name().substring(to + 1);
      }
      return new Type(ret);
    }
    return _returnType;
  }

  public Type getReturnType(Type calledUpon) {
    // TODO: extensively test this magic
    if(calledUpon != null &&
    	_returnType.get_name().contains("<T>") ||
        _returnType.get_name().equals("T")){
      int from = StringUtils.indexOfDifference(_calledUpon.get_name(), calledUpon.get_name());
      int to = calledUpon.get_name().length() - StringUtils.indexOfDifference(
          StringUtils.reverse(_calledUpon.get_name()), StringUtils.reverse(calledUpon.get_name()));
      String ret = calledUpon.get_name().substring(from, to);
      if(!_returnType.get_name().equals("T")){
       // don't miss anything that is wrapped around <T>
        int before = _returnType.get_name().indexOf("<T>");
        int after = before + 3;
        ret = _returnType.get_name().substring(0, before)
            + ret
            + _returnType.get_name().substring(after);
      }
      return new Type(ret);
    }
    return _returnType;
  }


  public String getOrigin(){
    return _origin;
  }

  public boolean isName(String name) {
    return _name.equals(name);
  }

  public boolean isReturnType(Type type, Mem mem) {
    return _returnType.equals(type)
        || _returnType.unifyTypes(type) != null;
  }

  public boolean areParametertypes(List<Type> partypes, Mem mem) {
    if (_parameterTypes.size() != partypes.size()) {
      return false;
    }
    for (int i = 0; i < _parameterTypes.size(); i++) {
      if (!(_parameterTypes.get(i).equals(partypes.get(i))
          || _parameterTypes.get(i).unifyTypes(partypes.get(i)) != null)) {
        return false;
      }
    }
    return true;
  }

  @Override
  public int hashCode() {
    return _parameterTypes.hashCode();
  }

  @Override
  public boolean equals(Object fun) {
    if (!(fun instanceof Function)) {
      return false;
    }
    return _name.equals(((Function) fun)._name)
        && _returnType.equals(((Function) fun)._returnType)
        && _parameterTypes.equals(((Function) fun)._parameterTypes);
  }
}