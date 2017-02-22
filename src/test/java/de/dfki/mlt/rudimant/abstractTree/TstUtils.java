package de.dfki.mlt.rudimant.abstractTree;

import static de.dfki.mlt.rudimant.Visualize.*;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.StringWriter;
import java.util.Iterator;

public class TstUtils {
  public static final String RESOURCE_DIR = "src/test/resources/";

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

  static int prefix = 678, suffix = 5;

  public static String getGeneration(String in) {
    StringWriter out = new StringWriter();
    parseAndTypecheck(in, out);
    StringBuffer sb = out.getBuffer();
    return normalizeSpaces(sb.subSequence(prefix, sb.length() - suffix).toString());
  }

  public static RudiTree getNodeOfInterest(GrammarFile rt) {
    return getNodeOfInterest(rt, 0);
  }

  public static void setUpNonEmpty() {
    try {
      setUp(RESOURCE_DIR + "dipal/dipal.yml", "label: if(true) {", "}");
    }
    catch (FileNotFoundException fex) {
      throw new RuntimeException(fex);
    }
  }


  public static void setUpEmpty() {
    try {
      setUp(RESOURCE_DIR + "dipal/dipal.yml", "", "");
    }
    catch (FileNotFoundException fex) {
      throw new RuntimeException(fex);
    }
  }

}
