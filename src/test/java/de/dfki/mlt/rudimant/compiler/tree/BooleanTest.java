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

import static de.dfki.mlt.rudimant.compiler.Visualize.*;
import static de.dfki.mlt.rudimant.compiler.tree.TestUtilities.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author max
 */
public class BooleanTest {

  /*
  /// boolean operators:
NOT: '!';
EQUAL: '==';
AND1: '&'; TODO deprecated?
OR1: '|'; TODO deprecated?
AND2: '&&';
OR2: '||';
NOT_EQUAL: '!=';
SMALLER_EQUAL: '<=';
SMALLER: '<';
GREATER_EQUAL: '>=';
GREATER: '>';

   */

  @BeforeClass
  public static void setUpClass() {
    setUpNonEmpty();
  }

  @Test
  public void testBoolean() {
    String booleanExp = "4 < 5;";

    RudiTree dtr = getNodeOfInterest(parseAndTypecheck(booleanExp));
    assertTrue(dtr instanceof ExpBoolean);
  }

  @Test
  public void testBoolean2() {
    String booleanExp = "4 == 5;";

    RudiTree dtr = getNodeOfInterest(parseAndTypecheck(booleanExp));
    assertTrue(dtr instanceof ExpBoolean);
  }

  @Test
  public void testBoolean3() {
    String booleanExp = "false;";
    RudiTree dtr = getNodeOfInterest(parseAndTypecheck(booleanExp));

    assertTrue(dtr instanceof ExpSingleValue);
    assertEquals("false should be of type Boolean", "boolean",
        (((RTExpression) dtr).getType().toJava()));
  }

  @Test
  public void testBoolean4() {
    String booleanExp = "!(5>5);";

    RudiTree dtr = getNodeOfInterest(parseAndTypecheck(booleanExp));

    assertTrue(dtr instanceof ExpBoolean);
  }

  @Test
  public void testBoolean5() {
    String booleanExp = "(false && false || true);";

    RudiTree dtr = getNodeOfInterest(parseAndTypecheck(booleanExp));

    assertTrue(dtr instanceof ExpBoolean);
  }

  @Test
  public void testBoolean7() {
    String booleanExp = "(var1 != var2);";

    RudiTree dtr = getNodeOfInterest(parseAndTypecheck(booleanExp));

    assertTrue(dtr instanceof ExpBoolean);
  }

  @Test
  public void testBoolean8() {
    String booleanExp = "(var1 >= var2 && var1 <= var2);";

    RudiTree dtr = getNodeOfInterest(parseAndTypecheck(booleanExp));

    assertTrue(dtr instanceof ExpBoolean);
  }

  @Test
  public void testNegationScope() {
    String booleanExp = "(! var1 == var2 && var1 <= var2 && var2 == var1);";

    RudiTree dtr = getNodeOfInterest(parseAndTypecheck(booleanExp));
    //Visualize.show(dtr, "foo");
    List<RudiTree> dtrs = new ArrayList<>();
    for (RudiTree d : dtr.getDtrs()) { dtrs.add(d); }
    assertEquals("!", ((ExpBoolean)dtrs.get(0)).operator) ;
    assertEquals("&&", ((ExpBoolean)dtrs.get(1)).operator) ;

  }

}
