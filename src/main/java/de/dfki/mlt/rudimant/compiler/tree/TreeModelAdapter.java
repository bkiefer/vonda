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
import java.util.Iterator;
import java.util.Map;

import de.dfki.lt.loot.Pair;
import de.dfki.lt.loot.gui.adapters.MapAdapterIterator;
import de.dfki.lt.loot.gui.adapters.ModelAdapter;
import de.dfki.lt.loot.gui.adapters.ModelAdapterFactory;

public class TreeModelAdapter extends ModelAdapter {

  public static void init() {
    ModelAdapterFactory.registerAdapter(RudiTree.class, TreeModelAdapter.class);
  }

  private class EdgeAdapterIterator implements MapAdapterIterator {
    Map<String, String> _values;

    Iterator<String> _it;

    public EdgeAdapterIterator(RudiTree node) {
      /* ??
      if (node instanceof RTExpression) {
        _values = new HashMap<>();
        RTExpression exp = (RTExpression)node;
        _values.put("type", exp.type.getRep());
      }
      */
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
      return ModelAdapter.SYMBOL | ModelAdapter.TREE;
    }
    if (model == null || model instanceof String) {
      return ModelAdapter.ATOM;
    }
    return ModelAdapter.NONE;
  }

  TreeViewVisitor tvv = new TreeViewVisitor();

  public String getString(Object model) {
    if (null == model) {
      return "<null>";
    }
    String result = "";
    if (model instanceof Include || model instanceof Import) {
      result = model.toString();
    } else if (model instanceof RudiTree) {
      ((RudiTree)model).visit(tvv);
      result = tvv.result;
    }
    if (result.isBlank()) {
      result = model.toString();
    }
    if (model instanceof RTExpression) {
      RTExpression exp = (RTExpression)model;
      if (exp.type != null)
        result += "[" + exp.type.getRep() + "]";
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
