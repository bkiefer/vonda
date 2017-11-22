package de.dfki.mlt.rudimant.common;

import java.util.ArrayList;
import java.util.List;

public class RuleInfo extends BasicInfo {
  private int _id;

  private int _state = 0;

  private List<Integer> _expr;

  private List<String> _baseTerms;

  private static final String[] opId2Op = { "&&", "||", "!" };

  // to simplify tests
  public static void resetIdGenerator() {
    ruleId = 0;
  }

  public RuleInfo() {}

  public RuleInfo(String name, int line, BasicInfo parent) {
    super(name, line, parent);
    _id = ruleId++;
    _expr = new ArrayList<>();
    _baseTerms = new ArrayList<>();
  }

  public void setId(int id) {
    _id = id;
    ruleId = Math.max(ruleId, id + 1);
  }

  public int getId() { return _id; }

  public void setState(int state) {
    _state = state;
  }

  public int getState() { return _state; }

  public List<Integer> getExpression() {
    return _expr;
  }

  public void setExpression(List<Integer> _expr) {
    this._expr = _expr;
  }

  public List<String> getBaseterms() {
    return _baseTerms;
  }

  public void setBaseterms(List<String> _baseTerms) {
    this._baseTerms = _baseTerms;
  }

  public void addOp(String op) {
    int opId = -99;
    switch (op.charAt(0)) {
    case '&': opId = -1; break;
    case '|': opId = -2; break;
    case '!': opId = -3; break;
    default:
      throw new IllegalArgumentException("Unknown boolean operator " + op);
    }
    _expr.add(opId);
  }

  public String opId2Op(int opId) {
    return opId2Op[(-opId + 1)];
  }

  /** 1. add the String of the node to the base term list in the rule info
   *  2. add the number of the arg to the infix representation of the expr
   *     in the rule info
   */
  public void addBaseTerm(String term) {
    _baseTerms.add(term);
    _expr.add(_baseTerms.size());
  }

  public String resultVarName() {
    return "__x" + _id;
  }

  public int noBaseTerms() {
    return _baseTerms.size();
  }
}
