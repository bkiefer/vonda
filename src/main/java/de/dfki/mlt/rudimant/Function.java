package de.dfki.mlt.rudimant;

import java.util.List;

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

  public boolean canCallUpon(Type calledOn) {
    // TODO: this is probably not accurate enough; find a more
    // sophisticated way to do this
    // It seems what is returned by unifyTypes should be a subclass of
    // _calledUpon
    return _calledUpon != null &&
        _calledUpon.unifyTypes(calledOn) != null;
  }

  public String getName() {
    return _name;
  }

  public Type getCalledUpon() {
    return _calledUpon;
  }

  public Type getReturnType(Type calledUpon) {
    String rn = _returnType.get_name();
    if(calledUpon != null &&
        // TODO: THIS SEEMS TO BE A CRUDE HOAX
        (rn.contains("<T>") || rn.equals("T"))){
      Type ret = calledUpon.getInnerType();
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

  // TODO: AGAIN: IS THIS ENOUGH? NOT SUBCLASS?
  public boolean isReturnType(Type type) {
    return _returnType.equals(type)
        || _returnType.unifyTypes(type) != null;
  }

  public boolean areParametertypes(List<Type> actualTypes) {
    if (_parameterTypes.size() != actualTypes.size()) {
      return false;
    }
    for (int i = 0; i < _parameterTypes.size(); i++) {
      if (! _parameterTypes.get(i).isPossibleArgumentType(actualTypes.get(i))) {
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

  @Override
  public String toString() {
    StringBuffer sb = new StringBuffer();
    sb.append(_returnType.getRep()).append(' ').append(_name).append('(');
    boolean notfirst = false;
    for (Type pType : _parameterTypes) {
      if (notfirst) sb.append(", ");
      notfirst = true;
      sb.append(pType.getRep());
    }
    sb.append(')');
    return sb.toString();
  }
}