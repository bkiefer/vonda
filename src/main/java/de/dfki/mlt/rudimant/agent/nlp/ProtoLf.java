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

package de.dfki.mlt.rudimant.agent.nlp;

import de.dfki.lt.tr.dialogue.cplan.DagEdge;
import de.dfki.lt.tr.dialogue.cplan.DagNode;

/** A data structure to represent incomplete (proto) logical forms which will
 *  have to be transformed into fully specified logical forms that can be
 *  passed on to, e.g., natural language generation.
 */
public class ProtoLf {

  private String _stringRep;
  private DagNode _dag;

  /** Initialize a PLF using a string representation
   */
  public ProtoLf(String stringRep) {
    _dag = null;
    _stringRep = stringRep;
  }

  /** Initialize a PLF using a string representation
   */
  public ProtoLf(DagNode dag) {
    _dag = dag;
    _stringRep = null;
  }

  /** Return the string representation of this PLF
   */
  @Override
  public String toString() {
    return _stringRep == null ? _dag.toString() : _stringRep;
  }

  public DagNode getDag() {
    return _dag;
  }

  private static int getTypeId(DagNode dag) {
    DagEdge edge = dag.getEdge(DagNode.TYPE_FEAT_ID);
    if (edge == null) return DagNode.BOTTOM_ID;
    return edge.getValue().getType();
  }

  private static String getType(DagNode dag) {
    return DagNode.getTypeName(getTypeId(dag));
  }

  public int getTypeId() { return getTypeId(_dag); }

  public String getType() { return getType(_dag); }

  private static int getPropId(DagNode dag) {
    DagEdge edge = dag.getEdge(DagNode.PROP_FEAT_ID);
    if (edge == null) return DagNode.BOTTOM_ID;
    return edge.getValue().getType();
  }

  private static String getProp(DagNode dag) {
    return DagNode.getTypeName(getPropId(dag));
  }

  public int getPropId() { return getPropId(_dag); }

  public String getProp() { return getProp(_dag); }

  /** return the atomic string value under the given edge, if it exists,
   *  null otherwise
   * @param edgeName
   * @return the atomic string value under the given edge, if it exists,
   *  null otherwise
   */
  public String getEdgeValue(String edgeName) {
    DagEdge edge = _dag.getEdge(DagNode.getFeatureId(edgeName));
    if (edge == null) return null;
    return getProp(edge.getValue());
  }

}
