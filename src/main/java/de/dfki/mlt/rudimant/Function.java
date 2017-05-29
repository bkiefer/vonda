package de.dfki.mlt.rudimant;

import java.util.List;

import org.apache.commons.lang.StringUtils;

class Function {

  private String _name;
  private String _origin;
  private String _returnType;
  private List<String> _parameterTypes;
  private String _calledUpon;

  public Function(String name, String origin, String returnType,
      List<String> parameterTypes, String calledUpon) {
    _name = name;
    _origin = origin;
    _returnType = returnType;
    _parameterTypes = parameterTypes;
    _calledUpon = calledUpon;
  }

  public boolean canCallUpon(String calledOn, Mem mem) {
    // TODO: this is probably not accurate enough; find a more
    // sophisticated way to do this
    return _calledUpon != null &&
        (_calledUpon.contains("<T>") || _calledUpon.equals("T")) ||
        Type.unifyTypes(_calledUpon, calledOn) != null;
  }

  public String getName() {
    return _name;
  }

  public String getCalledUpon() {
    return _calledUpon;
  }

  /** TODO BK I changed this function in a way that seemed sensible to me,
   *  but earnestly, i don't understand what it was supposed to do in the first
   *  place. So, if my changes are not correct, PLEASE EXPLAIN THE EXACT
   *  INTENTIONS, MAYBE ADD EXAMPLES, OR WHATEVER.
   */
  public String getReturnType0(String calledUpon) {
    // TODO: extensively test this magic
    int from = _returnType.indexOf('<');
    if (from >= 0) {
      // extract the parameter type
      int to = _returnType.lastIndexOf('>');
      String ret = calledUpon.substring(from + 1, to);
      if(!_returnType.equals("T")){
        // don't miss anything that is wrapped around <T>
        ret = _returnType.substring(0, from)
            + ret
            + _returnType.substring(to + 1);
      }
      return ret;
    }
    return _returnType;
  }

  public String getReturnType(String calledUpon) {
    // TODO: extensively test this magic
    if(calledUpon != null &&
    	_returnType.contains("<T>") ||
        _returnType.equals("T")){
      int from = StringUtils.indexOfDifference(_calledUpon, calledUpon);
      int to = calledUpon.length() - StringUtils.indexOfDifference(
          StringUtils.reverse(_calledUpon), StringUtils.reverse(calledUpon));
      String ret = calledUpon.substring(from, to);
      if(!_returnType.equals("T")){
       // don't miss anything that is wrapped around <T>
        int before = _returnType.indexOf("<T>");
        int after = before + 3;
        ret = _returnType.substring(0, before)
            + ret
            + _returnType.substring(after);
      }
      return ret;
    }
    return _returnType;
  }


  public String getOrigin(){
    return _origin;
  }

  public boolean isName(String name) {
    return _name.equals(name);
  }

  public boolean isReturnType(String type, Mem mem) {
    return _returnType.equals(type)
        || Type.unifyTypes(_returnType, type) != null;
  }

  public boolean areParametertypes(List<String> partypes, Mem mem) {
    if (_parameterTypes.size() != partypes.size()) {
      return false;
    }
    for (int i = 0; i < _parameterTypes.size(); i++) {
      if (!(_parameterTypes.get(i).equals(partypes.get(i))
          || Type.unifyTypes(_parameterTypes.get(i), partypes.get(i)) != null)) {
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