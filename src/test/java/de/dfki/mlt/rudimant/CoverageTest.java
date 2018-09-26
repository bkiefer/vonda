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

package de.dfki.mlt.rudimant;
import static de.dfki.mlt.rudimant.compiler.tree.TestUtilities.RESOURCE_DIR;
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.yaml.snakeyaml.Yaml;

import de.dfki.mlt.rudimant.compiler.CompilerMain;
import de.dfki.mlt.rudimant.compiler.RudimantCompiler;

import static de.dfki.mlt.rudimant.compiler.Constants.*;


public class CoverageTest {
  public int startCompiler(File dir) throws IOException, InterruptedException {
    // java -jar ../../tecs_server/tecs-server-2.0.0.jar -c -p PORT
    Process compile = Runtime.getRuntime().exec(
        new String[]{"sh", "-c", "./javcomp"},
        new String[]{}, dir );
    return compile.waitFor();
  }

  @Test
  public void testCoverageAndJava() throws Exception {
    Path confFile = Paths.get(RESOURCE_DIR, "miniproj", "config.yml");
    File confDir = confFile.toFile().getParentFile();
    Yaml yaml = new Yaml();
    @SuppressWarnings("unchecked")
    Map<String, Object> configs =
        (Map<String, Object>) yaml.load(new FileReader(confFile.toFile()));
    configs.put(CFG_VISUALISE, true);
    RudimantCompiler rc = new RudimantCompiler(confDir, configs);
    assertFalse(CompilerMain.process(rc, new File(confDir, "AllYouCanDo.rudi")));
    assertEquals(0, startCompiler(confDir));
  }
}
