package de.dfki.mlt.rudimant.agent;

import de.dfki.lt.hfc.db.rdfProxy.Rdf;
import de.dfki.lt.hfc.db.rdfProxy.RdfClass;
import de.dfki.lt.hfc.db.rdfProxy.RdfProxy;

/**
 *
 * @author chbu02, Bernd Kiefer
 */
public class DataComparator {

  protected DataComparator(){};

  /** The RDF access object */
  public RdfProxy _proxy;

  /********************************************************
   * overloaded boolean operator methods
   ********************************************************/

//  public boolean isEqual(){}
//  public boolean isSmaller(){}
//  public boolean isGreater(){}
//  public boolean isSmallerEqual(){}
//  public boolean isGreaterEqual(){}

  public static boolean exists(Object s) {
    if (s == null) return false;
    if (s instanceof String) return ! ((String)s).isEmpty();
    if (s instanceof Boolean) return (Boolean)s;
    return true;
  }

  public static boolean isEqual(DialogueAct left, DialogueAct right){
    if (left == null || right == null)
      return (left == null && right == null);
    return (left.isSubsumedBy(right) && right.isSubsumedBy(left));
  }
  public static boolean isSmaller(DialogueAct left, DialogueAct right){
    return (isSmallerEqual(left, right) && !isSmallerEqual(right, left));
  }
  public static boolean isGreater(DialogueAct left, DialogueAct right){
    return (isGreaterEqual(left, right) && !isGreaterEqual(right, left));
  }
  public static boolean isSmallerEqual(DialogueAct left, DialogueAct right){
    return left.isSubsumedBy(right);
  }
  public static boolean isGreaterEqual(DialogueAct left, DialogueAct right){
    return isSmallerEqual(right, left);
  }

  public static boolean isEqual(String left, DialogueAct right){
    if (left == null || right == null)
      return (left == null && right == null);
    return isEqual(new DialogueAct(left), right);
  }

  public static boolean isSmaller(String left, DialogueAct right){
    return isSmaller(new DialogueAct(left), right);
  }

  public static boolean isGreater(String left, DialogueAct right){
    return isGreater(new DialogueAct(left), right);
  }
  public static boolean isSmallerEqual(String left, DialogueAct right){
    return isSmallerEqual(new DialogueAct(left), right);
  }
  public static boolean isGreaterEqual(String left, DialogueAct right){
    return isGreaterEqual(new DialogueAct(left), right);
  }

  public static boolean isEqual(DialogueAct left, String right){
    if (left == null || right == null)
      return (left == null && right == null);
    return isEqual(left, new DialogueAct(right));
  }
  public static boolean isSmaller(DialogueAct left, String right){
    return isSmaller(left, new DialogueAct(right));
  }
  public static boolean isGreater(DialogueAct left, String right){
    return isGreater(left, new DialogueAct(right));
  }
  public static boolean isSmallerEqual(DialogueAct left, String right){
    return isSmallerEqual(left, new DialogueAct(right));
  }
  public static boolean isGreaterEqual(DialogueAct left, String right){
    return isGreaterEqual(left, new DialogueAct(right));
  }

  public static boolean isEqual(Rdf left, Rdf right){
    if (left == null || right == null) return left == right;
    return isEqual(left.getClazz(), right.getClazz());
  }
  public static boolean isSmaller(Rdf left, Rdf right){
    return (isSmallerEqual(left, right) && !isSmallerEqual(right, left));
  }
  public static boolean isGreater(Rdf left, Rdf right){
    return (isGreaterEqual(left, right) && !isGreaterEqual(right, left));
  }
  public static boolean isSmallerEqual(Rdf left, Rdf right){
    return isSmallerEqual(left.getClazz(), right.getClazz());
  }

  public static boolean isGreaterEqual(Rdf left, Rdf right){
    return isSmallerEqual(right, left);
  }

  public boolean isEqual(String left, Rdf right){
    return isEqual(_proxy.getRdfClass(left), right.getClazz());
  }

  public boolean isSmaller(String left, Rdf right){
    return isSmaller(_proxy.getRdfClass(left), right.getClazz());
  }
  public boolean isGreater(String left, Rdf right){
    return isGreater(_proxy.getRdfClass(left), right.getClazz());
  }

  public boolean isSmallerEqual(String left, Rdf right){
    return isSmallerEqual(_proxy.getRdfClass(left), right.getClazz());
  }
  public boolean isGreaterEqual(String left, Rdf right){
    return isGreaterEqual(_proxy.getRdfClass(left), right.getClazz());
  }

  public boolean isEqual(Rdf left, String right){
    return isEqual(left.getClazz(), _proxy.getRdfClass(right));
  }
  public boolean isSmaller(Rdf left, String right){
    return isSmaller(left.getClazz(), _proxy.getRdfClass(right));
  }
  public boolean isGreater(Rdf left, String right){
    return isGreater(left.getClazz(), _proxy.getRdfClass(right));
  }
  public boolean isSmallerEqual(Rdf left, String right){
    return isSmallerEqual(left.getClazz(), _proxy.getRdfClass(right));
  }
  public boolean isGreaterEqual(Rdf left, String right){
    return isGreaterEqual(left.getClazz(), _proxy.getRdfClass(right));
  }

  public static boolean isEqual(RdfClass left, RdfClass right){
    return (left.isSubclassOf(right) && right.isSubclassOf(left));
  }
  public static boolean isSmaller(RdfClass left, RdfClass right){
    return (isSmallerEqual(left, right) && !isSmallerEqual(right, left));
  }
  public static boolean isGreater(RdfClass left, RdfClass right){
    return (isGreaterEqual(left, right) && !isGreaterEqual(right, left));
  }
  public static boolean isSmallerEqual(RdfClass left, RdfClass right){
    return (left.isSubclassOf(right));
  }
  public static boolean isGreaterEqual(RdfClass left, RdfClass right){
    return isSmallerEqual(right, left);
  }

  public boolean isEqual(String left, RdfClass right){
    return isEqual(_proxy.getRdfClass(left), right);
  }
  public boolean isSmaller(String left, RdfClass right){
    return isSmaller(_proxy.getRdfClass(left), right);
  }
  public boolean isGreater(String left, RdfClass right){
    return isGreater(_proxy.getRdfClass(left), right);
  }
  public boolean isSmallerEqual(String left, RdfClass right){
    return isSmallerEqual(_proxy.getRdfClass(left), right);
  }
  public boolean isGreaterEqual(String left, RdfClass right){
    return isGreaterEqual(_proxy.getRdfClass(left), right);
  }

  public boolean isEqual(RdfClass left, String right){
    return isEqual(left, _proxy.getRdfClass(right));
  }
  public boolean isSmaller(RdfClass left, String right){
    return isSmaller(left, _proxy.getRdfClass(right));
  }
  public boolean isGreater(RdfClass left, String right){
    return isGreater(left, _proxy.getRdfClass(right));
  }
  public boolean isSmallerEqual(RdfClass left, String right){
    return isSmallerEqual(left, _proxy.getRdfClass(right));
  }
  public boolean isGreaterEqual(RdfClass left, String right){
    return isGreaterEqual(left, _proxy.getRdfClass(right));
  }

  public boolean isEqual(String left, String right){
    //try to find out whether left and right are DialogueActs or Rdf objects
    RdfClass leftRdf = _proxy.getRdfClass(left);
    RdfClass rightRdf = _proxy.getRdfClass(right);
    if(leftRdf == null || rightRdf == null){
      return isEqual(new DialogueAct(left), new DialogueAct(right));
    }
    return isEqual(leftRdf, rightRdf);
  }

  public boolean isSmaller(String left, String right){
    //try to find out whether left and right are DialogueActs or Rdf objects
    RdfClass leftRdf = _proxy.getRdfClass(left);
    RdfClass rightRdf = _proxy.getRdfClass(right);
    if(leftRdf == null || rightRdf == null){
      return isSmaller(new DialogueAct(left), new DialogueAct(right));
    }
    return isSmaller(leftRdf, rightRdf);
  }
  public boolean isGreater(String left, String right){
    //try to find out whether left and right are DialogueActs or Rdf objects
    RdfClass leftRdf = _proxy.getRdfClass(left);
    RdfClass rightRdf = _proxy.getRdfClass(right);
    if(leftRdf == null || rightRdf == null){
      return isGreater(new DialogueAct(left), new DialogueAct(right));
    }
    return isGreater(leftRdf, rightRdf);
  }
  public boolean isSmallerEqual(String left, String right){
    //try to find out whether left and right are DialogueActs or Rdf objects
    RdfClass leftRdf = _proxy.getRdfClass(left);
    RdfClass rightRdf = _proxy.getRdfClass(right);
    if(leftRdf == null || rightRdf == null){
      return isSmallerEqual(new DialogueAct(left), new DialogueAct(right));
    }
    return isSmallerEqual(leftRdf, rightRdf);
  }

  public boolean isGreaterEqual(String left, String right){
    RdfClass leftRdf = _proxy.getRdfClass(left);
    RdfClass rightRdf = _proxy.getRdfClass(right);
    if(leftRdf == null || rightRdf == null){
      return isGreaterEqual(new DialogueAct(left), new DialogueAct(right));
    }
    return isGreaterEqual(leftRdf, rightRdf);
  }

  public boolean isEqual(Object left, Object right){
    if (left instanceof String) {
      if (right instanceof String) {
        return isEqual((String) left, (String) right);
      } else if (right instanceof Rdf) {
        return isEqual((String) left, (Rdf) right);
      } else if (right instanceof RdfClass) {
        return isEqual((String) left, (RdfClass) right);
      } else { // instanceof DialogueAct
        return isEqual((String) left, (DialogueAct) right);
      }
    } else if (left instanceof Rdf) {
      if (right instanceof String) {
        return isEqual((Rdf) left, (String) right);
      } else if (right instanceof Rdf) {
        return isEqual((Rdf) left, (Rdf) right);
      } else if (right instanceof RdfClass) {
        return isEqual((Rdf) left, (RdfClass) right);
      } else { // instanceof DialogueAct
        return isEqual((Rdf) left, (DialogueAct) right);
      }
    } else if (left instanceof RdfClass) {
      if (right instanceof String) {
        return isEqual((RdfClass) left, (String) right);
      } else if (right instanceof Rdf) {
        return isEqual((RdfClass) left, (Rdf) right);
      } else if (right instanceof RdfClass) {
        return isEqual((RdfClass) left, (RdfClass) right);
      } else { // instanceof DialogueAct
        return isEqual((RdfClass) left, (DialogueAct) right);
      }
    } else { // instanceof DialogueAct
      if (right instanceof String) {
        return isEqual((DialogueAct) left, (String) right);
      } else if (right instanceof Rdf) {
        return isEqual((DialogueAct) left, (Rdf) right);
      } else if (right instanceof RdfClass) {
        return isEqual((DialogueAct) left, (RdfClass) right);
      } else { // instanceof DialogueAct
        return isEqual((DialogueAct) left, (DialogueAct) right);
      }
    }
  }

  public boolean isSmaller(Object left, Object right){
    if (left instanceof String) {
      if (right instanceof String) {
        return isSmaller((String) left, (String) right);
      } else if (right instanceof Rdf) {
        return isSmaller((String) left, (Rdf) right);
      } else if (right instanceof RdfClass) {
        return isSmaller((String) left, (RdfClass) right);
      } else { // instanceof DialogueAct
        return isSmaller((String) left, (DialogueAct) right);
      }
    } else if (left instanceof Rdf) {
      if (right instanceof String) {
        return isSmaller((Rdf) left, (String) right);
      } else if (right instanceof Rdf) {
        return isSmaller((Rdf) left, (Rdf) right);
      } else if (right instanceof RdfClass) {
        return isSmaller((Rdf) left, (RdfClass) right);
      } else { // instanceof DialogueAct
        return isSmaller((Rdf) left, (DialogueAct) right);
      }
    } else if (left instanceof RdfClass) {
      if (right instanceof String) {
        return isSmaller((RdfClass) left, (String) right);
      } else if (right instanceof Rdf) {
        return isSmaller((RdfClass) left, (Rdf) right);
      } else if (right instanceof RdfClass) {
        return isSmaller((RdfClass) left, (RdfClass) right);
      } else { // instanceof DialogueAct
        return isSmaller((RdfClass) left, (DialogueAct) right);
      }
    } else { // instanceof DialogueAct
      if (right instanceof String) {
        return isSmaller((DialogueAct) left, (String) right);
      } else if (right instanceof Rdf) {
        return isSmaller((DialogueAct) left, (Rdf) right);
      } else if (right instanceof RdfClass) {
        return isSmaller((DialogueAct) left, (RdfClass) right);
      } else { // instanceof DialogueAct
        return isSmaller((DialogueAct) left, (DialogueAct) right);
      }
    }
  }

  public boolean isGreater(Object left, Object right){
    if (left instanceof String) {
      if (right instanceof String) {
        return isGreater((String) left, (String) right);
      } else if (right instanceof Rdf) {
        return isGreater((String) left, (Rdf) right);
      } else if (right instanceof RdfClass) {
        return isGreater((String) left, (RdfClass) right);
      } else { // instanceof DialogueAct
        return isGreater((String) left, (DialogueAct) right);
      }
    } else if (left instanceof Rdf) {
      if (right instanceof String) {
        return isGreater((Rdf) left, (String) right);
      } else if (right instanceof Rdf) {
        return isGreater((Rdf) left, (Rdf) right);
      } else if (right instanceof RdfClass) {
        return isGreater((Rdf) left, (RdfClass) right);
      } else { // instanceof DialogueAct
        return isGreater((Rdf) left, (DialogueAct) right);
      }
    } else if (left instanceof RdfClass) {
      if (right instanceof String) {
        return isGreater((RdfClass) left, (String) right);
      } else if (right instanceof Rdf) {
        return isGreater((RdfClass) left, (Rdf) right);
      } else if (right instanceof RdfClass) {
        return isGreater((RdfClass) left, (RdfClass) right);
      } else { // instanceof DialogueAct
        return isGreater((RdfClass) left, (DialogueAct) right);
      }
    } else { // instanceof DialogueAct
      if (right instanceof String) {
        return isGreater((DialogueAct) left, (String) right);
      } else if (right instanceof Rdf) {
        return isGreater((DialogueAct) left, (Rdf) right);
      } else if (right instanceof RdfClass) {
        return isGreater((DialogueAct) left, (RdfClass) right);
      } else { // instanceof DialogueAct
        return isGreater((DialogueAct) left, (DialogueAct) right);
      }
    }
  }

  public boolean isSmallerEqual(Object left, Object right){
    if (left instanceof String) {
      if (right instanceof String) {
        return isSmallerEqual((String) left, (String) right);
      } else if (right instanceof Rdf) {
        return isSmallerEqual((String) left, (Rdf) right);
      } else if (right instanceof RdfClass) {
        return isSmallerEqual((String) left, (RdfClass) right);
      } else { // instanceof DialogueAct
        return isSmallerEqual((String) left, (DialogueAct) right);
      }
    } else if (left instanceof Rdf) {
      if (right instanceof String) {
        return isSmallerEqual((Rdf) left, (String) right);
      } else if (right instanceof Rdf) {
        return isSmallerEqual((Rdf) left, (Rdf) right);
      } else if (right instanceof RdfClass) {
        return isSmallerEqual((Rdf) left, (RdfClass) right);
      } else { // instanceof DialogueAct
        return isSmallerEqual((Rdf) left, (DialogueAct) right);
      }
    } else if (left instanceof RdfClass) {
      if (right instanceof String) {
        return isSmallerEqual((RdfClass) left, (String) right);
      } else if (right instanceof Rdf) {
        return isSmallerEqual((RdfClass) left, (Rdf) right);
      } else if (right instanceof RdfClass) {
        return isSmallerEqual((RdfClass) left, (RdfClass) right);
      } else { // instanceof DialogueAct
        return isSmallerEqual((RdfClass) left, (DialogueAct) right);
      }
    } else { // instanceof DialogueAct
      if (right instanceof String) {
        return isSmallerEqual((DialogueAct) left, (String) right);
      } else if (right instanceof Rdf) {
        return isSmallerEqual((DialogueAct) left, (Rdf) right);
      } else if (right instanceof RdfClass) {
        return isSmallerEqual((DialogueAct) left, (RdfClass) right);
      } else { // instanceof DialogueAct
        return isSmallerEqual((DialogueAct) left, (DialogueAct) right);
      }
    }
  }

  public boolean isGreaterEqual(Object left, Object right){
    if (left instanceof String) {
      if (right instanceof String) {
        return isSmallerEqual((String) left, (String) right);
      } else if (right instanceof Rdf) {
        return isSmallerEqual((String) left, (Rdf) right);
      } else if (right instanceof RdfClass) {
        return isSmallerEqual((String) left, (RdfClass) right);
      } else { // instanceof DialogueAct
        return isSmallerEqual((String) left, (DialogueAct) right);
      }
    } else if (left instanceof Rdf) {
      if (right instanceof String) {
        return isSmallerEqual((Rdf) left, (String) right);
      } else if (right instanceof Rdf) {
        return isSmallerEqual((Rdf) left, (Rdf) right);
      } else if (right instanceof RdfClass) {
        return isSmallerEqual((Rdf) left, (RdfClass) right);
      } else { // instanceof DialogueAct
        return isSmallerEqual((Rdf) left, (DialogueAct) right);
      }
    } else if (left instanceof RdfClass) {
      if (right instanceof String) {
        return isSmallerEqual((RdfClass) left, (String) right);
      } else if (right instanceof Rdf) {
        return isSmallerEqual((RdfClass) left, (Rdf) right);
      } else if (right instanceof RdfClass) {
        return isSmallerEqual((RdfClass) left, (RdfClass) right);
      } else { // instanceof DialogueAct
        return isSmallerEqual((RdfClass) left, (DialogueAct) right);
      }
    } else { // instanceof DialogueAct
      if (right instanceof String) {
        return isSmallerEqual((DialogueAct) left, (String) right);
      } else if (right instanceof Rdf) {
        return isSmallerEqual((DialogueAct) left, (Rdf) right);
      } else if (right instanceof RdfClass) {
        return isSmallerEqual((DialogueAct) left, (RdfClass) right);
      } else { // instanceof DialogueAct
        return isSmallerEqual((DialogueAct) left, (DialogueAct) right);
      }
    }
  }
}
