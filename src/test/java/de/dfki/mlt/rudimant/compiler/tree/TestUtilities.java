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

import static de.dfki.mlt.rudimant.compiler.GenerationConstants.*;
import static de.dfki.mlt.rudimant.compiler.Visualize.*;
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.Map;

import de.dfki.lt.hfc.WrongFormatException;
import de.dfki.lt.hfc.db.rdfProxy.RdfProxy;
import de.dfki.lt.hfc.db.server.HfcDbApiHandler;

public class TestUtilities {
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
  
  public static RdfProxy startClient(File configDir, Map<String, Object> configs)
      throws IOException, WrongFormatException {
    String ontoFileName = (String) configs.get("ontologyFile");
    if (ontoFileName == null) {
      throw new IOException("Ontology file is missing.");
    }
    HfcDbApiHandler handler = new HfcDbApiHandler();
    handler.readConfig(new File(configDir, ontoFileName));
    RdfProxy proxy = new RdfProxy(handler);
    handler.registerStreamingClient(proxy);
    return proxy;
  }

  static int prefix = 252, suffix = 55;

  public static String normalizeSpaces(String in) {
    return in.replaceAll("[ \n\r\t]+", " ");
  }

  public static String getTypeError(String in) throws Throwable {
    StringWriter out = new StringWriter();
    parseAndTypecheckWithError(in, out);
    StringBuffer sb = out.getBuffer();
    return sb.toString();
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
      setUp(TESTCONF, "// hello test\n", "");
    }
    catch (FileNotFoundException fex) {
      throw new RuntimeException(fex);
    }
  }

  public static String getForMarked(String s, String exp) {
    s = s.replace(PROCESS_PREFIX, "/**/").replace(PROCESS_SUFFIX, "");
    s = normalizeSpaces(s).trim();
    exp = normalizeSpaces(exp).trim();
    int start = s.indexOf("// hello test") + 13;
    int end = Math.min(start + exp.length() + 1, s.length());
    //s.lastIndexOf("// end of test");
    return normalizeSpaces(s.substring(start, end).trim());
  }

  public static String getForMarked(String s, String exp, String marker) {
    s = s.replace(PROCESS_PREFIX, "/**/").replace(PROCESS_SUFFIX, "");
    s = normalizeSpaces(s).trim();
    int b = s.indexOf("/**/") + 4;
    int e = s.indexOf(marker);
    s = s.substring(0, b) + s.substring(e);
    exp = normalizeSpaces(exp).trim();
    int start = s.indexOf("// hello test") + 13;
    int end = Math.min(start + exp.length() + 1, s.length());
    //s.lastIndexOf("// end of test");
    return normalizeSpaces(s.substring(start, end).trim());
  }
}
