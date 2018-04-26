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

/**
 *
 * @author Anna Welker, anna.welker@dfki.de
 */
public class Location {
  private Position begin, end;

  public Location(Position b, Position e) {
    begin = b;
    end = e;
  }

  public Location() {}

  public String getOriginClass() {
    return begin.getOrigin();
  }

  public int getLineNumber() {
    return begin.getLine();
  }

  public int getCharPosition() {
    return begin.getCharpos();
  }

  public Position getBegin() { return begin; }
  public void setBegin(Position begin) { this.begin = begin; }

  public Position getEnd() { return end; }
  public void setEnd(Position end) { this.end = end; }


  @Override
  public String toString() {
    return getOriginClass() + ".rudi:" + (begin.getLine()+1) +
        ((begin.getColumn() != 0) ? ":" + begin.getColumn() + ":" : ":");
  }
}
