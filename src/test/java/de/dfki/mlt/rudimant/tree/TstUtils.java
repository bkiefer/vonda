package de.dfki.mlt.rudimant.tree;

import static de.dfki.mlt.rudimant.Visualize.*;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.StringWriter;
import java.util.Iterator;

import de.dfki.mlt.rudimant.tree.*;

public class TstUtils {
  public static final String RESOURCE_DIR = "src/test/resources/";
  public static final String TESTCONF = RESOURCE_DIR + "tests.yml";


  public static RudiTree getNodeOfInterest(GrammarFile gf, int n) {
    assertNotNull(gf);
    StatGrammarRule dtr = (StatGrammarRule) gf.getDtrs().iterator().next();
    StatIf _if = (StatIf) dtr.getDtrs().iterator().next();
    StatAbstractBlock blk = (StatAbstractBlock) ((StatIf) _if).statblockIf;
    Iterator<? extends RudiTree> it = blk.getDtrs().iterator();
    for (int i = 0; i < n; i++){
      it.next();
    }
    RudiTree r = it.next();
    if (r instanceof StatExpression) return ((StatExpression)r).expression;
    return r;
  }

  static int prefix = 252, suffix = 55;

  public static String getGeneration(String in) {
    StringWriter out = new StringWriter();
    parseAndTypecheck(in, out);
    StringBuffer sb = out.getBuffer();
    return normalizeSpaces(sb.subSequence(prefix + 34, sb.length() - suffix).toString());
  }

  public static String getTypeError(String in) throws Throwable {
    StringWriter out = new StringWriter();
    parseAndTypecheckWithError(in, out);
    StringBuffer sb = out.getBuffer();
    return normalizeSpaces(sb.subSequence(prefix + 34, sb.length() - suffix).toString());
  }

  public static RudiTree getNodeOfInterest(GrammarFile rt) {
    return getNodeOfInterest(rt, 0);
  }

  public static void setUpNonEmpty() {
    try {
      setUp(TESTCONF, "label: if(true) {// hello test\n", "}");
    }
    catch (FileNotFoundException fex) {
      throw new RuntimeException(fex);
    }
  }


  public static void setUpEmpty() {
    try {
      setUp(TESTCONF, "int asdf;// hello test\n", "");
    }
    catch (FileNotFoundException fex) {
      throw new RuntimeException(fex);
    }
  }

  public static String getForMarked(String s, String exp) {
    s = normalizeSpaces(s);
    exp = normalizeSpaces(exp);
    //int end = s.lastIndexOf("}") - 2;
    int start = s.indexOf("// hello test") + 14;
    return normalizeSpaces(s.substring(start, start + exp.length()).trim());
  }
}
