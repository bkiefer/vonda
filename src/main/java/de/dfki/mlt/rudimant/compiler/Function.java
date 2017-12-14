package de.dfki.mlt.rudimant.compiler;

import java.util.ArrayList;
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
  /*
  private Type createTypeFromGeneric(Type concrete, Type withGeneric) {
    if (withGeneric == null) return null;
    // TODO: might want to extend this to other place holders than T
    String resultType = "T".equals(withGeneric.get_name())? concrete.get_name() : withGeneric.get_name();
    if (withGeneric.getParameterTypes() != null && withGeneric.getParameterTypes().size() > 1) {
      List<Type> inner = new ArrayList<>();
      for (int i = 0; i < withGeneric.getParameterTypes().size(); i++) {
        inner.add(createTypeFromGeneric(concrete.getParameterTypes().get(i),
            withGeneric.getParameterTypes().get(i)));
      }
      return Type.getComplexType(resultType, (Type[]) inner.toArray());
    } else {
      Type result = new Type(resultType);
      if (withGeneric.getParameterTypes() != null && withGeneric.getParameterTypes().size() == 1) {
        result.setInnerType(createTypeFromGeneric(concrete.getParameterTypes().get(0),
            withGeneric.getParameterTypes().get(0)));
      }
      return result;
    }
  }*/
  
  private Type findTypeForGeneric(Type concrete, Type withGeneric) {
    if (withGeneric == null) return null;
    // TODO: might want to extend this to other place holders than T
    if ("T".equals(withGeneric.get_name())) return concrete;
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
  
  private Type createTypeFromGeneric(Type concrete, Type withGeneric) {
    if (withGeneric.toJava().equals("T")) return concrete;
    // TODO: can this really happen?
    String resultType = "T".equals(withGeneric.get_name())? concrete.toJava() : withGeneric.get_name();
    List<Type> inner = new ArrayList<>();
    for (int i = 0; i < withGeneric.getParameterTypes().size(); i++) {
      inner.add(createTypeFromGeneric(concrete, withGeneric.getParameterTypes().get(i)));
    }
    Type result = new Type(resultType);
    result.setParameterTypes(inner);
    return result;
  }

  public Type getReturnType(Type calledUpon, List<Type> partypes) {
    String rn = _returnType.toJava();
    // TODO: THIS SEEMS TO BE A CRUDE HOAX
    if (rn.contains("<T>") || rn.equals("T")){
      Type tType = null;
      if (calledUpon != null && canCallUpon(calledUpon)) {
        tType = findTypeForGeneric(calledUpon, _calledUpon);
      } else {
        for (int i = 0; i < _parameterTypes.size(); i++) {
          if (_parameterTypes.get(i).toJava().contains("<T>")
              || _parameterTypes.get(i).toJava().equals("T")) {
            tType = findTypeForGeneric(partypes.get(i), _parameterTypes.get(i));
            break;
          }
        }
      }
      if (tType != null) {
        return createTypeFromGeneric(tType, _returnType);
      }
      /* if (calledUpon != null) {
        return calledUpon.getInnerType();
      } */
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