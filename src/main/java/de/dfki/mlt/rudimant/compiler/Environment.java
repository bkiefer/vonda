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

  public static Environment getEnvironment(Environment parent) {
    // by copying the existing environment, we avoid searching through all
    // lower environments at the cost of bigger space consumption
    return parent == null ? new Environment() : parent.deepCopy();
  }

  private Environment() {
    variableToType = new HashMap<>();
    variableOrigin = new HashMap<>();
    functions = new HashMap<>();
  }

  private Environment deepCopy() {
    Environment newEnv = new Environment();
    newEnv.variableOrigin.putAll(variableOrigin);
    newEnv.variableToType.putAll(variableToType);
    newEnv.functions.putAll(functions);
    newEnv._parent = this;
    return newEnv;
  }

  /** Set the type t and origin o (from which file) for variable v */
  public void put(String var, Type type, String origin) {
    variableToType.put(var, type);
    variableOrigin.put(var, origin);
  }

  public boolean isVarDefined(String k) {
    return variableToType.containsKey(k);
  }

  public Type getType(String k) {
    return variableToType.get(k);
  }

  /**
   * May return null, either because variable does not exist or is not from the
   * top level, in which case the origin is not of our concern
   */
  public String getOrigin(String k) {
    return variableOrigin.get(k);
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
    // test whether we already have an entry for this method
    if (functions.keySet().contains(funcname)) {
      for (Function f : functions.get(funcname)) {
        if (f.signatureMatches(functype)) {
          /* TODO: ADAPT
          // in this case we have an obvious error
          if (!f.isReturnType(functype)) {
            // TODO: add a description about where we are in the input file
            logger.warn("redeclaring function " + funcname
                    + " with new return type - was: " + f.getReturnType()
                    + " is: " + functype);
          }*/
          return;
        }
      }
      // else: just add the method
    } else {
      // if we did not know of this method until now, create a new entry for it
      functions.put(funcname, new HashSet<Function>());
    }
    functions.get(funcname).add(new Function(funcname, origin, functype));
  }

  /** returns null if there is no such function, the function otherwise
  *
  * @param funcname the name of the function
  * @return its return type or null
  */
  public Function getFunction(String funcname, Type actualParameterTypes) {
    if (!functions.containsKey(funcname)) return null;
    for (Function f : functions.get(funcname)) {
      if (f.signatureMatches(actualParameterTypes)) {
        return f;
      }
    }
    return null;
  }

  /** Environment is a stack, return the parent of this environment */
  public Environment getParent() {
    return _parent;
  }
}
