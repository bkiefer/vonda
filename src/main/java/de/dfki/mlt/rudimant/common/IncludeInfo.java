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
import java.io.InputStream;
import java.io.Writer;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.inspector.TagInspector;

public class IncludeInfo extends BasicInfo {
  protected List<ErrorInfo> _errors = new ArrayList<>();
  protected String[] relativePath;

  public static void saveInfo(IncludeInfo info, Writer w) {
    DumperOptions options = new DumperOptions();
    options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
    Yaml yaml = new Yaml(options);
    yaml.dump(info, w);
  }

  public static IncludeInfo loadInfo(InputStream stream) {
    // load the rule infos for logging
    LoaderOptions opt = new LoaderOptions();
    opt.setMaxAliasesForCollections(1000);
    TagInspector taginspector =
        tag -> (tag.getClassName().equals(IncludeInfo.class.getName())
            || tag.getClassName().equals(RuleInfo.class.getName()));
    opt.setTagInspector(taginspector);
    IncludeInfo root = (IncludeInfo) new Yaml(new Constructor(IncludeInfo.class, opt))
        .load(stream);
    return root;
  }

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
