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
import static de.dfki.mlt.rudimant.common.Constants.*;
import static de.dfki.mlt.rudimant.compiler.tree.TestUtilities.RESOURCE_DIR;
import static org.junit.Assert.*;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import de.dfki.mlt.rudimant.compiler.RudimantCompiler;


public class CoverageTest {
  Logger log = LoggerFactory.getLogger(CoverageTest.class);

  static boolean headless = false;

  @BeforeClass
  public static void testHeadless() {
    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    try {
      @SuppressWarnings("unused")
      GraphicsDevice de = ge.getDefaultScreenDevice();
    } catch (HeadlessException hex) {
      headless = true;
    }
  }

  public int startCompiler(File dir) throws IOException, InterruptedException {
    Process compile = new ProcessBuilder().command("sh", "-c", "./javcomp")
        .directory(dir)
        .redirectOutput(new File("target/comp.log")).redirectErrorStream(true)
        .start();
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
    configs.put(CFG_VISUALISE, !headless);
    configs.put(CFG_CONFIG_DIRECTORY, confDir);
    assertFalse(RudimantCompiler.process(confDir, configs).hasFailed());
    // skip compiler test for Windows
    Assume.assumeFalse(System.getProperty("os.name").toLowerCase().startsWith("win"));
    assertEquals(0, startCompiler(confDir));
  }
}
