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

public class Position implements Comparable<Position> {

  private int line;
  private int column;
  private int charpos;
  private String origin;

  public Position(){};

  public Position(int line, int column, int charpos, String o) {
    this.line = line;
    this.column = column;
    this.charpos = charpos;
    this.origin = o;
  }

  public int getLine() {
    return line;
  }

  public void setLine(int line) {
    this.line = line;
  }

  public int getColumn() {
    return column;
  }

  public void setColumn(int column) {
    this.column = column;
  }

  public int getCharpos() {
    return charpos;
  }

  public void setCharpos(int charpos) {
    this.charpos = charpos;
  }

  public String getOrigin() {
    return origin;
  }

  public void setOrigin(String msg) {
    this.origin = msg;
  }

  @Override
  public boolean equals(Object o) {
    if (! (o instanceof Position)) return false;
    Position p2 = (Position) o;
    return line == p2.line && column == p2.column && origin.equals(p2.origin);
  }

  @Override
  public int hashCode() {
    return line << 10 + column;
  }

  @Override
  public String toString() {
    return origin + ":" + line + ":" + column;
  }

  @Override
  public int compareTo(Position o) {
    if (charpos >= 0) return charpos - o.charpos;
    int res = line - o.line;
    if (res != 0) return res;
    return column - o.column;
  }
}
