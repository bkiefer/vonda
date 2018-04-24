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
  private String originClass;
  private int lineNumber;
  private int charPosition;

  public Location(String origin, int lineNo) {
    originClass = origin;
    lineNumber = lineNo;
    charPosition = 0;
  }

  public Location(String origin, int lineNo, int charPos) {
    originClass = origin;
    lineNumber = lineNo;
    charPosition = charPos;
  }

  public Location() {}

  public String getOriginClass() {
    return originClass;
  }

  public void setOriginClass(String oc) {
    originClass = oc;
  }

  public int getLineNumber() {
    return lineNumber;
  }

  public void setLineNumber(int ln) {
    lineNumber = ln;
  }

  public int getCharPosition() {
    return charPosition;
  }

  public void setCharPosition(int cp) {
    charPosition = cp;
  }

  @Override
  public String toString() {
    if (charPosition != 0)
      return originClass + ".rudi:" + lineNumber + ":" + charPosition + ":";
    else
      return originClass + ".rudi:" + lineNumber + ":";
  }
}
