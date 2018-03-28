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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import de.dfki.mlt.rudimant.compiler.Environment;
import de.dfki.mlt.rudimant.compiler.Type;

/**
 * class that allows the implementation of methods in a .rudi file; we are not
 * sure whether this should still be possible
 *
 * @author Anna Welker
 */
public class StatMethodDeclaration extends RTStatement implements RTBlockNode {

  String visibility;
  Type return_type;
  String name;
  List<String> parameters;
  List<Type> partypes;
  RTStatement block;
  // the type this method should be called upon; null if the method
  // is rudi-defined!
  Type calledUpon;

  public StatMethodDeclaration(String vis, Type ret_type,
      Type calledUpn, String nm, List parmsAndTypes,
      RTStatement blk) {
    visibility = vis;
    return_type = ret_type == null ? Type.getNoType() : ret_type;
    name = nm;
    parameters = new ArrayList<>();
    partypes = new ArrayList<>();
    if (parmsAndTypes != null)
        for (int i = 0; i < parmsAndTypes.size();) {
            partypes.add((Type)parmsAndTypes.get(i++));
            parameters.add((String)parmsAndTypes.get(i++));
        }
    block = blk;
    calledUpon = calledUpn == null ? Type.getNoType() : calledUpn;
  }

  public StatMethodDeclaration(String vis, String ret_type,
		  String calledUpn, String nm, List<String> parms,
		  List<String> parmTypes, RTStatement blk) {
    visibility = vis;
    return_type = new Type(ret_type);
    name = nm;
    parameters = parms;
    partypes = new ArrayList<Type>();
    for (String p : parmTypes){
      partypes.add(new Type(p));
    }
    block = blk;
    calledUpon = new Type(calledUpn);
  }

  public void setVisibility(String vis) {
    visibility = vis;
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }

  public Iterable<? extends RudiTree> getDtrs() {
    if (block == null) return Collections.emptyList();
    RudiTree[] dtrs = { block };
    return Arrays.asList(dtrs);
  }

  // ==== IMPLEMENTATION OF RTBLOCKNODE =====================================

  private Environment _localBindings;

  public Environment getParentBindings() { return _localBindings.getParent(); }

  public Environment enterEnvironment(Environment parent) {
    return _localBindings != null ? _localBindings
        : (_localBindings = Environment.getEnvironment(parent));
  }
}
