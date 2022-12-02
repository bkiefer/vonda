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

package de.dfki.mlt.rudimant.compiler.tree;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import de.dfki.lt.loot.Pair;
import de.dfki.lt.loot.gui.adapters.MapAdapterIterator;
import de.dfki.lt.loot.gui.adapters.ModelAdapter;
import de.dfki.lt.loot.gui.adapters.ModelAdapterFactory;
import de.dfki.mlt.rudimant.compiler.Type;

public class TreeModelAdapter extends ModelAdapter {

  public static void init() {
    ModelAdapterFactory.registerAdapter(RudiTree.class, TreeModelAdapter.class);
  }

  private class EdgeAdapterIterator implements MapAdapterIterator {
    Map<String, String> _values;

    Iterator<String> _it;

    public EdgeAdapterIterator(RudiTree node) {
      if (node instanceof RTExpression) {
        _values = new HashMap<>();
        RTExpression exp = (RTExpression)node;
        _values.put("type", exp.type.getRep());
      }
      if (_values == null) {
        _values = Collections.emptyMap();
      }
      _it = _values.keySet().iterator();
    }


    public boolean hasNext() { return _it.hasNext(); }

    public Pair<Object, Object> next() {
      String key = _it.next();
      return new Pair<Object, Object>(key, _values.get(key));
    }
  }

  private class MapMarker {
    RudiTree _root;
    MapMarker(RudiTree root) { _root = root; }
  }

  @Override
  public int facets(Object model) {
    if (model instanceof MapMarker) {
      return ModelAdapter.MAP;
    }
    if (model instanceof RudiTree) {
      RudiTree node = (RudiTree) model;
      /*
      if (node.getNewType() == RudiTree.getGrammar().getConsType())
        return ModelAdapter.MAP | ModelAdapter.CONS;
      if (node.getNewType() == RudiTree.getGrammar().getNullType())
        return ModelAdapter.MAP | ModelAdapter.NULL;
      if (node.getNthArg(0) != null)
        return ModelAdapter.MAP | ModelAdapter.TREE;
      return ModelAdapter.MAP;
       */
      return ModelAdapter.SYMBOL | ModelAdapter.TREE;
    }
    if (model == null || model instanceof String) {
      return ModelAdapter.ATOM;
    }
    return ModelAdapter.NONE;
  }

  public String getString(Object model) {
    String result;
    if (model instanceof RTBinaryExp) {
      result = ((RTBinaryExp)model).operator;
    } else if (model instanceof StatReturn) {
      result = ((StatReturn)model).toString();
    } else if (model instanceof StatGrammarRule) {
      result = ((StatGrammarRule)model).label + ":";
    } else if (model instanceof StatIf) {
      result = "if";
    } else if (model instanceof ExpDialogueAct) {
      result = "DialAct";
    } else if (model instanceof ExpAssignment) {
      result = ":=";
    } else if (model instanceof ExpConditional) {
      result = "_ ? _ : _";
    } else if (model instanceof StatPropose) {
      result = "propose";
    } else if (model instanceof ExpFieldAccess) {
      result = "FieldAcc";
    } else if (model instanceof ExpLambda) {
      result = " -> ";
    } else if (model instanceof StatMethodDeclaration) {
      StatMethodDeclaration md = (StatMethodDeclaration)model;
      Iterator<Type> partypes = md.function_type.getParameterTypes();
      Type calledUpon = md.function_type.getClassOf();

      result = "meth " + md.function_type.getReturnedType().getRep() + " " +
          (calledUpon == null ? "" : calledUpon.getRep() + ". ")
          + md.name + '(';
      for (int j = 0; j < md.parameters.size(); ++j) {
        if (j != 0) result += ", ";
        result += partypes.next().getRep() + " " + md.parameters.get(j);
      }
    } else if (model instanceof StatAbstractBlock) {
      if (((StatAbstractBlock)model).braces)
        result = "{ _ }";
      else
        result = "block";
    } else if (model instanceof ExpFuncCall) {
      result = ((ExpFuncCall)model).content + "( )";
    } else {
      if (null == model) {
        result = "<null>";
      } else {
        result = model.toString();
      }
    }
    if (model instanceof RTExpression) {
      RTExpression exp = (RTExpression)model;
      if (exp.type != null)
        result += "\n[" + exp.type.getRep();
    }
    return result;
  }

  @Override
  public String getAttribute(Object model, String name) {
    RudiTree node;
    if (model instanceof MapMarker) {
      node = ((MapMarker) model)._root;
    } else if (model instanceof RudiTree) {
      node = (RudiTree) model;
    } else {
      return null;
    }
    return null;
  }

  @Override
  public Object getNodeContent(Object model) {
    //return new MapMarker((RudiTree) model);
    return model;
  }

  @SuppressWarnings("rawtypes")
  @Override
  public Iterable getTreeDaughters(Object model) {
    // if (!(model instanceof TreeMarker)) return null;
    RudiTree tree = (RudiTree) model;
    return tree.getDtrs();
  }


  //@Override
  //public boolean isNull(Object model) { return false; }

  /**/
  @Override
  public MapAdapterIterator mapIterator(Object model) {
    if (model instanceof MapMarker) {
      return new EdgeAdapterIterator(((MapMarker)model)._root);
    }
    return new EdgeAdapterIterator((RudiTree) model);
  }/**/
}
