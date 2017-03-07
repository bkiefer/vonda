package de.dfki.mlt.rudimant.abstractTree;

import static de.dfki.mlt.rudimant.Visualize.*;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.StringWriter;
import java.util.Iterator;

public class TstUtils {
  public static final String RESOURCE_DIR = "src/test/resources/";
  public static final String TESTCONF = RESOURCE_DIR + "tests.yml";


  public static RudiTree getNodeOfInterest(GrammarFile gf, int n) {
    assertNotNull(gf);
    GrammarRule dtr = (GrammarRule) gf.getDtrs().iterator().next();
    StatIf _if = (StatIf) dtr.getDtrs().iterator().next();
    StatAbstractBlock blk = (StatAbstractBlock) ((StatIf) _if).statblockIf;
    Iterator<? extends RudiTree> it = blk.getDtrs().iterator();
    for (int i = 0; i < n; i++){
      it.next();
    }
    return it.next();
  }

  static int prefix = 286, suffix = 55;

  public static String getGeneration(String in) {
    StringWriter out = new StringWriter();
    parseAndTypecheck(in, out);
    StringBuffer sb = out.getBuffer();
    return normalizeSpaces(sb.subSequence(prefix, sb.length() - suffix).toString());
  }

  public static String getTypeError(String in) throws Throwable {
    StringWriter out = new StringWriter();
    parseAndTypecheckWithError(in, out);
    StringBuffer sb = out.getBuffer();
    return normalizeSpaces(sb.subSequence(prefix, sb.length() - suffix).toString());
  }

  public static RudiTree getNodeOfInterest(GrammarFile rt) {
    return getNodeOfInterest(rt, 0);
  }

  public static void setUpNonEmpty() {
    try {
      setUp(TESTCONF, "label: if(true) {", "}");
    }
    catch (FileNotFoundException fex) {
      throw new RuntimeException(fex);
    }
  }


  public static void setUpEmpty() {
    try {
      setUp(TESTCONF, "", "");
    }
    catch (FileNotFoundException fex) {
      throw new RuntimeException(fex);
    }
  }


  /** Extract the right string if there is no global if-rule, and in case it's
   *  not only a variable declaration (then, there is also the constructor)
   */
  public static String getForEmpty(String s, String exp) {
    s = normalizeSpaces(s);
    exp = normalizeSpaces(exp);
    int end = s.lastIndexOf("public void process()") - 1  ;
    int start = 246; //end - exp.length();
    return normalizeSpaces(s.substring(start, end));
  }

  public static String getGenerationEmpty(String in, String exp) {
    String s = generate(in);
    return getForEmpty(s, exp);
  }

  /** Extract the right string if there is no global if-rule, and in case it's
   *  not only a variable declaration (then, there is also the constructor)
   */
  public static String getForEmptyAssign(String s, String exp) {
    s = normalizeSpaces(s);
    exp = normalizeSpaces(exp);
    int end = s.lastIndexOf("public null()");
    return normalizeSpaces(s.substring(end - exp.length(), end));
  }

}
