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


import org.junit.*;

import de.dfki.mlt.rudimant.compiler.TypeException;

public class MethodDeclarationTest {

  @BeforeClass
  public static void setUpClass() {
    setUpEmpty();
  }

  @Test
  public void test() {
    String methdecl = " void foo() { i = 1; }";
    String s = generate(methdecl);
    String expected = "public void foo() { int i = 1; }";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void testParType() {
    String methdecl = " void foo(List<Child> cs) { Child c = cs.get(0); }";
    String s = generate(methdecl);
    String expected = "public void foo(List<Rdf> cs)"
        + " { Rdf c = cs.get(0); }";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void testMethodOverload(){
    String methdecl1 = " void foo() { i = 1; }\n";
    String methdecl2 = "String foo(int a) { i = 1; }\n";
    String usage = "void bar() { if(true){foo();} }";
    String s = generate(methdecl1 + methdecl2 + usage);
    String expected = "public void foo() { int i = 1; } "
        + "public String foo(int a) { int i = 1; } "
        + "public void bar() { if (true) { foo(); }";
    assertEquals(expected, getForMarked(s, expected));
  }

// .,

  @Test
  public void testMethodMultipleParameters(){
    String methdecl = " void foo(int i, String s) { i = 1; }";
    String s = generate(methdecl);
    String expected = "public void foo(int i, String s) { i = 1; }";
    assertEquals(expected, getForMarked(s, expected));
  }


  @Test
  public void testLocalParameters(){
    String methdecl = "int s; void foo(String s) { s = \"a\"; }";
    String s = generate(methdecl);
    String expected = "public int s;/**/public void foo(String s) { s = \"a\"; }";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void testCallUpon1(){
    String methdecl = "#List<T> T gett(int a); List<String> l;"
    		+ "x = l.gett(0);";
    String s = generate(methdecl);
    String expected = "public List<String> l;public String x;/**/x = l.gett(0);";
    assertEquals(expected, getForMarked(s, expected));
    assertTrue(s.contains("String x;"));
    assertTrue(s.contains("List<String> l;"));
  }

  @Test(expected=TypeException.class)
  public void testFunctionRedeclaration() throws Throwable {
    String methdecl = "#String long length();";
    String s = getTypeError(methdecl);
  }
}
