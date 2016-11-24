package de.dfki.mlt.rudimant.abstractTree;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import de.dfki.lt.loot.Pair;
import de.dfki.lt.loot.gui.adapters.MapAdapterIterator;
import de.dfki.lt.loot.gui.adapters.ModelAdapter;
import de.dfki.lt.loot.gui.adapters.ModelAdapterFactory;

public class TreeModelAdapter extends ModelAdapter {

  public static void init() {
    ModelAdapterFactory.registerAdapter(RudiTree.class, TreeModelAdapter.class);
  }

  /*
  private class EdgeAdapterIterator implements MapAdapterIterator {
    short[] _excludedFeatures;
    Pair<Object, Object> _current;
    private Iterator<? extends DagEdge> _iterator;

    // precondition: _iterator != null
    private void advance() {
      _current = null;
      while (_current == null && _iterator.hasNext()) {
        DagEdge edge = _iterator.next();
        if (_excludedFeatures == null ||
            Arrays.binarySearch(_excludedFeatures, edge.getFeature()) == -1)
          _current = new Pair<Object, Object>(edge, edge.getValue());
      }
    }

    public EdgeAdapterIterator(RudiTree node) { this(node, null); }

    public EdgeAdapterIterator(RudiTree node, String[] excludedFeatures) {
      _current = null;
      _iterator = node.getEdgeIterator();
      if (_iterator != null) {
        if (excludedFeatures != null) {
          _excludedFeatures = new short[excludedFeatures.length];
          int i = 0;
          for (String feat : excludedFeatures) {
            _excludedFeatures[i] = RudiTree.getGrammar().getFeatureId(feat);
          }
          Arrays.sort(_excludedFeatures);
        }
        advance();
      }
    }

    public boolean hasNext() { return _current != null; }

    public Pair<Object, Object> next() {
      Pair<Object, Object> result = _current;
      advance();
      return result;
    }
  }
  */

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
      return ModelAdapter.TREE;
    }
    return ModelAdapter.NONE;
  }

  public String getString(Object model) {
    throw new UnsupportedOperationException("calling getString on " + model);
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
    return new MapMarker((RudiTree) model);
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

  /*
  @Override
  public MapAdapterIterator mapIterator(Object model) {
    if (model instanceof MapMarker) {
      final String[] exclude = { "ARGS" };
      return new EdgeAdapterIterator(((MapMarker) model)._root, exclude);
    }
    return new EdgeAdapterIterator((RudiTree) model);
  }*/
}
