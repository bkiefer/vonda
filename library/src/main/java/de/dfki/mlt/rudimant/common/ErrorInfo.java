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

public class ErrorInfo {
  public static enum ErrorType { ERROR, WARNING, PARSE_ERROR };

  private Location _location;

  private String _message;

  private ErrorType _type;

  public ErrorInfo(){}

  public ErrorInfo(String m, Location l, ErrorType t) {
    _message = m;
    _location = l;
    _type = t;
  }

  public Location getLocation() {
    return _location;
  }

  public void setLocation(Location location) {
    this._location = location;
  }

  public String getMessage() {
    return _message;
  }

  public void setMessage(String message) {
    this._message = message;
  }

  public ErrorType getType() { return _type; }

  public void setType(ErrorType t) { _type = t; }

}
