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

package de.dfki.mlt.rudimant.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BasicInfo {

  /** To generate successive unique rule ids */
  protected static int ruleId;

  protected BasicInfo _parent;
  protected int _line;
  protected String _label;
  protected List<BasicInfo> _children = new ArrayList<>();

  public BasicInfo() { }

  public BasicInfo(String name, int line, BasicInfo parent) {
    _parent = parent;
    if (parent != null) parent._children.add(this);
    _label = name;
    _line = line;
  }

  public BasicInfo getParent() {
    return _parent;
  }

  public void setParent(BasicInfo parent) {
    this._parent = parent;
  }

  public int getLine() {
    return _line;
  }

  public void setLine(int line) {
    this._line = line;
  }

  public String getLabel() {
    return _label;
  }

  public void setLabel(String label) {
    this._label = label;
  }

  public List<BasicInfo> getChildren() {
    return _children;
  }

  public void setChildren(List<BasicInfo> children) {
    this._children = children;
  }

  @Override
  public String toString() { return _label; }

  @Override
  public boolean equals(Object other) {

    if (this == other)
        return true;

    if (other == null)
        return false;

    if (getClass() != other.getClass())
        return false;

    BasicInfo o = (BasicInfo) other;
    return (this._line == o._line
            && this._label.equals(o._label)
            && this._children.equals(o._children)
            );
  }

  @Override
  public int hashCode() {
    int hash = 3;
    hash = 73 * hash + Objects.hashCode(this._parent);
    hash = 73 * hash + this._line;
    hash = 73 * hash + Objects.hashCode(this._label);
    return hash;
  }

  /** Return the number of registered rules */
  public static int getRuleNumber() {
    return ruleId;
  }
}
