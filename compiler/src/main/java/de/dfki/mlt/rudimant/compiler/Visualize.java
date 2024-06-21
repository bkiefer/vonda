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

package de.dfki.mlt.rudimant.compiler;

import de.dfki.lt.loot.gui.DrawingPanel;
import de.dfki.lt.loot.gui.MainFrame;
import de.dfki.lt.loot.gui.Style;
import de.dfki.lt.loot.gui.layouts.CompactLayout;
import de.dfki.mlt.rudimant.compiler.tree.RudiTree;
import de.dfki.mlt.rudimant.compiler.tree.TreeModelAdapter;


public class Visualize {

  /*
  public static class RudiFileHandler implements ObjectHandler {
    @Override
    public boolean process(File f, InputStream in, MainFrame mf)
        throws IOException {
      String inputRealName = f.getName().replace(RULE_FILE_EXTENSION, "");

      // create the abstract syntax tree
      GrammarFile gf = null;

      // do the type checking
      // create the abstract syntax tree
      gf = parseAndTypecheck(in);

      // show tree
      show(gf, inputRealName, mf);
      return true;
    }
  }
  */

  public static void show(RudiTree root, String realName, MainFrame mf) {
    CompactLayout cl = new CompactLayout();
    cl.setTreeHorizontal(true);
    cl.setTreeLevelDistance(8);
    cl.setTreeNodeDistance(3);
    cl.setTreeEdgesStraight(false);

    DrawingPanel dp = new DrawingPanel(root, cl, new TreeModelAdapter());
    mf.setContentArea(dp);
    mf.setTitle(realName);
  }

  public static void show(RudiTree root, String realName) {
    MainFrame mf = new MainFrame("foo");
    //mf.addFileAssociation(new RudiFileHandler(), "rudi");
    show(root, realName, mf);
  }

  public static void init() {
    Style.increaseDefaultFontSize(1.5);
  }

  /**
   * @param args: the file that should be parsed without ending (in args[0])
   * @throws FileNotFoundException
   * @throws WrongFormatException
   * @throws Exception
   *
  public static void main(String[] args) {

    File inputFile = new File(args[0]);

    try {
      if (args.length > 1) { readConfig(args[1]); }

      Visualize.init();
      MainFrame root = new MainFrame("foo");
      root.addFileAssociation(new RudiFileHandler(), "rudi");
      new RudiFileHandler().process(inputFile, new FileInputStream(inputFile),
          root);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }*/
}
