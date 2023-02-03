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

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class IncludeInfo extends BasicInfo {
  protected List<ErrorInfo> _errors = new ArrayList<>();
  protected String[] relativePath;

  public IncludeInfo() { }

  public IncludeInfo(String name, String[] pathSpec, int line, BasicInfo parent) {
    super(name, line, parent);
    setRelativePath(pathSpec);
  }

  public List<ErrorInfo> getErrors() {
    return _errors;
  }

  public void setErrors(List<ErrorInfo> errors) {
    this._errors = errors;
  }

  public Path getFilePath() {
    File f = new File(".");
    for (String sub : relativePath) f = new File(f, sub);
    f = new File(f, _label + ".rudi");
    return f.toPath();
  }

  public String[] getRelativePath() {
    return relativePath;
  }

  public void setRelativePath(String[] relativePath) {
    this.relativePath = relativePath;
  }

}
