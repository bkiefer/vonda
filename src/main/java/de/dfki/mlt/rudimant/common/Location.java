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

import de.dfki.mlt.rudimant.compiler.Position;

/**
 *
 * @author Anna Welker, anna.welker@dfki.de
 */
public class Location {
  private String originClass;
  private Position begin, end;

  public Location(String origin, Position b, Position e) {
    originClass = origin;
    begin = b;
    end = e;
  }

  public Location() {}

  public String getOriginClass() {
    return originClass;
  }

  public int getLineNumber() {
    return begin.line;
  }

  public int getCharPosition() {
    return begin.charpos;
  }

  public Position getBegin() { return begin; }

  public Position getEnd() { return end; }

  @Override
  public String toString() {
    if (begin.charpos != 0)
      return originClass + ".rudi:" + begin.line + ":" + begin.charpos + ":";
    else
      return originClass + ".rudi:" + begin.line + ":";
  }
}
