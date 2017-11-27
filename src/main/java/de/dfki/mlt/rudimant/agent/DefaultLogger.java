package de.dfki.mlt.rudimant.agent;

import de.dfki.mlt.rudimant.common.LogPrinter;
import de.dfki.mlt.rudimant.common.RuleInfo;

public class DefaultLogger implements LogPrinter {

  protected void print(String s) {
    System.out.print(s);
  }

  protected void printTerm(String term, boolean value) {
    print("[" + value + ": " + term + "]");
  }

  protected void printResult(boolean value) {
    print(Boolean.toString(value).toUpperCase() + ": ");
  }

  private void printRec(RuleInfo r, boolean[] result, int[] pos) {
    int elt = r.getExpression(pos[0]);
    ++pos[0]; // move to next element
    if (elt < 0) {
      if (RuleInfo.isNot(elt)) { // unary not
        print(RuleInfo.getOp(elt));
        printRec(r, result, pos);
      } else { // binary operator
        print("(");
        printRec(r, result, pos);
        print(RuleInfo.getOp(elt));
        printRec(r, result, pos);
        print(")");
      }
    } else {
      // print term
      printTerm(r.getBaseTerm(elt), result[elt + 1]);
    }
  }

  @Override
  public void printLog(RuleInfo ruleId, boolean[] result) {
    int[] pos = { 0 };
    printResult(result[0]); printRec(ruleId, result, pos); print("\n");
  }
}
