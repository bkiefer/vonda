package de.dfki.mlt.rudimant;

import java.util.List;

class Function {

  private String name;
  private String origin;
  private String returnType;
  private List<String> parameterTypes;
  String calledUpon;

  public Function(String name, String origin, String returnType,
      List<String> parameterTypes, String calledUpon) {
    this.name = name;
    this.origin = origin;
    this.returnType = returnType;
    this.parameterTypes = parameterTypes;
    this.calledUpon = calledUpon;
  }

  public boolean canCallUpon(String calledOn, Mem mem) {
    // TODO: this is probably not accurate enough; find a more
    // sophisticated way to do this
  return this.calledUpon != null &&
		  (this.calledUpon.contains("<T>") ||
				  this.calledUpon.equals("T")) ||
		  mem.unifyTypes(this.calledUpon, calledOn) != null;
}

public String getName() {
  return this.name;
}

/** TODO BK I changed this function in a way that seemed sensible to me,
 *  but earnestly, i don't understand what it was supposed to do in the first
 *  place. So, if my changes are not correct, PLEASE EXPLAIN THE EXACT
 *  INTENTIONS, MAYBE ADD EXAMPLES, OR WHATEVER.
 */
public String getReturnType(String calledUpon) {
  // TODO: extensively test this magic
  int from = this.returnType.indexOf('<');
  if (from >= 0) {
    // extract the parameter type
    int to = this.returnType.lastIndexOf('>');
    String ret = calledUpon.substring(from + 1, to);
    if(!this.returnType.equals("T")){
  	  // don't miss anything that is wrapped around <T>
  	  ret = this.returnType.substring(0, from)
  			  + ret
  			  + this.returnType.substring(to + 1);
  	}
    return ret;
  }
  return this.returnType;
}

public String getOrigin(){
  return this.origin;
}

public boolean isName(String name) {
  return this.name.equals(name);
}

public boolean isReturnType(String type, Mem mem) {
  return this.returnType.equals(type)
      || mem.unifyTypes(this.returnType, type) != null;
}

public boolean areParametertypes(List<String> partypes, Mem mem) {
  if (this.parameterTypes.size() != partypes.size()) {
    return false;
  }
  for (int i = 0; i < this.parameterTypes.size(); i++) {
    if (!(this.parameterTypes.get(i).equals(partypes.get(i))
        || mem.unifyTypes(this.parameterTypes.get(i), partypes.get(i)) != null)) {
      return false;
    }
  }
  return true;
}

@Override
public int hashCode() {
  return parameterTypes.hashCode();
}

@Override
public boolean equals(Object fun) {
  if (!(fun instanceof Function)) {
    return false;
  }
  return this.name.equals(((Function) fun).name)
      && this.returnType.equals(((Function) fun).returnType)
      && this.parameterTypes.equals(((Function) fun).parameterTypes);
}
}