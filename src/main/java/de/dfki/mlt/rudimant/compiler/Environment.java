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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/** represents a variable/function scope, e.g., in a file / block
 *
 * @author Anna Welker
 */
public class Environment {

  private Environment _parent;

  private Map<String, Type> variableToType;
  private Map<String, String> variableOrigin;
  private HashMap<String, Set<Function>> functions;
  private HashMap<String, Map<Type, Type>> fields;

  public static Environment getEnvironment(Environment parent) {
    // by copying the existing environment, we avoid searching through all
    // lower environments at the cost of bigger space consumption
    // Unfortunately, this is wrong since we can not distinguish between
    // locally redefining a variable (error) and creating a new binding in an
    // embedded environment (ok)
    //return parent == null ? new Environment() : parent.deepCopy();
    // the current solution might be improved by keeping a separate
    // Environment where the definitions of all parents are kept, separate from
    // the local definitions, and local defs are copied there when a new Env
    // is created (could reuse deepCopy)
    Environment result = new Environment();
    result._parent = parent;
    return result;
  }

  private Environment() {
    variableToType = new HashMap<>();
    variableOrigin = new HashMap<>();
    functions = new HashMap<>();
    fields = new HashMap<>();
  }

  /*
  private Environment deepCopy() {
    Environment newEnv = new Environment();
    newEnv.variableOrigin.putAll(variableOrigin);
    newEnv.variableToType.putAll(variableToType);
    newEnv.functions.putAll(functions);
    newEnv._parent = this;
    return newEnv;
  }
  */

  private <T> T seekEnvironments(java.util.function.Function<Environment, T> f) {
    Environment env = this;
    T res;
    while (env != null) {
      if ((res = f.apply(env)) != null) return res;
      env = env._parent;
    }
    return null;
  }

  /** Set the type t and origin o (from which file) for variable v */
  public void put(String var, Type type, String origin) {
    variableToType.put(var, type);
    variableOrigin.put(var, origin);
  }

  /** Return true if a variable with this name was defined in this local scope */
  public boolean isVarLocallyDefined(String k) {
    return variableToType.containsKey(k);
  }

  /** Return the type of the variable with name k, if defined in some accessible
   *  scope, null otherwise.
   */
  public Type getType(String k) {
    return seekEnvironments((e) -> e.variableToType.get(k));
  }

  /**
   * May return null, either because variable does not exist or is not from the
   * top level, in which case the origin is not of our concern
   */
  public String getOrigin(String k) {
    Environment env = seekEnvironments(
        (e) -> e.variableToType.containsKey(k) ? e : null);
    return env == null ? null : env.variableOrigin.get(k);
  }

  /** Return true if there is already a function defined with this name and type */
  public boolean functionDefined(String funcname, Type functype) {
    Function f = getFunction(funcname, functype);
    return f != null
        && !f.getType().getReturnedType().equals(functype.getReturnedType());
  }

  /**
   * Add a function/method declaration, optionally with return and parameter
   * types. If the types are not known, it's assumed they are null.
   *
   * @param funcname The name of the function
   * @param functype the return type of the function, or null
   * @param partypes the parameter types of the function's parameters
   * @param origin first element class, second rule origin
   */
  public void addFunction(String funcname, Type functype, String origin) {
    if (! functions.containsKey(funcname)) {
      functions.put(funcname, new HashSet<Function>());
    }
    functions.get(funcname).add(new Function(funcname, origin, functype));
  }

  /** if defined in this environment, return the function with this name and
   *  compatible type (excluding return type), null otherwise.
   *
   * @param funcname the name of the function
   * @return the Function or null
   */
  private Function getFunctionLocally(String funcname, Type actualParameterTypes) {
    if (functions.containsKey(funcname)) {
      for (Function f : functions.get(funcname)) {
        if (f.signatureMatches(actualParameterTypes)) {
          return f;
        }
      }
    }
    return null;
  }

  /** if defined in an accessible scope return the function with this name and
   *  compatible type (excluding return type), null otherwise.
   *
   * @param funcname the name of the function
   * @return the Function or null
   */
  public Function getFunction(String funcname, Type actualParameterTypes) {
    return seekEnvironments(
        (env) -> env.getFunctionLocally(funcname, actualParameterTypes));
  }

  /** Add a field declaration. The type given must be a field type, containing
   *  the class the type is defined and the content ("return") type.
   */
  public boolean addField(String fieldname, Type type) {
    if (! fields.containsKey(fieldname)) {
      fields.put(fieldname, new HashMap<>());
    }
    Type retType = fields.get(fieldname).get(type.getClassOf());
    if (retType != null) {
      if (! retType.equals(type.getReturnedType()))
        // Illegal field redefinition
        return false;
    } else {
      fields.get(fieldname).put(type.getClassOf(), type.getReturnedType());
    }
    return true;
  }

  private Type getFieldTypeLocally(String fieldname, Type calledUpon) {
    Map<Type, Type> classToField = fields.get(fieldname);
    if (classToField != null) {
      for (Map.Entry<Type, Type> entry : classToField.entrySet()) {
        if (entry.getKey().signatureMatches(calledUpon))
          return entry.getValue();
      }
    }
    return null;
  }

  /** Find the type of this field, if defined in an accessible scope
   *
   * @param fieldname the name of the field
   * @return the Type or null
   */
  public Type getFieldType(String fieldname, Type calledUpon) {
    return seekEnvironments(
        (env) -> env.getFieldTypeLocally(fieldname, calledUpon));
  }

  /** Environment is a stack, return the parent of this environment */
  public Environment getParent() {
    return _parent;
  }
}
