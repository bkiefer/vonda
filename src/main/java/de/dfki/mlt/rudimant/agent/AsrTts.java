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

package de.dfki.mlt.rudimant.agent;

import static de.dfki.mlt.rudimant.common.Constants.*;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dfki.lt.tr.dialogue.cplan.DagNode;
import de.dfki.mlt.rudimant.agent.nlp.*;

public class AsrTts {
  public static final Logger logger = LoggerFactory.getLogger(AsrTts.class);

  /**
   * Connection to ASR & TTS used in HySociaTea
   */
  /**
   * The language generation engine
   */
  private LanguageGenerator _generator;

  private Interpreter _interpreter;

  @SuppressWarnings({ "rawtypes", "unchecked" })
  public void loadGrammar(File configDir, String language, Agent agent, Map configs)
      throws IOException {

    Map nlgConfig = (Map)configs.get(CFG_NLG_KEY);
    Map nluConfig = (Map)configs.get(CFG_NLU_KEY);

    if (nlgConfig  != null && nlgConfig.containsKey(language)) {
      //language = "english";
      _generator = LanguageGenerator.getGenerator(configDir, language,
          (Map<String, Object>)nlgConfig.get(language));
      //_generator.registerAccess("general", new InfoStateAccess(agent));
      // see below
    }

    if (nluConfig != null && nluConfig.containsKey(language)) {
      _interpreter = Interpreter.getInterpreter(configDir, language,
          (Map)nluConfig.get(language));
    }
  }

  public void registerInfoStateAccess(String what, BaseInfoStateAccess acc) {
    _generator.registerAccess(what, acc);
  }

  private static final Pattern toEscape = Pattern.compile("[^0-9a-zA-Z_-]");

  @SuppressWarnings("unused")
  private static String stringify(String in) {
    if (in.isEmpty()) return in;
    if ('"' == in.charAt(0) && '"' == in.charAt(in.length() - 1))
      in = in.substring(1, in.length() - 1);
    if (toEscape.matcher(in).find()) {
      if (in.charAt(0) == '"') in = '\\' + in;
      in = in.replaceAll("([^\\\\])\\\"", "\\1\\\\\"");
      in = '"' + in + '"';
    }
    return in;
  }

  public Pair<String, String> generate(DagNode saEvent) {
    //logger.info("raw:" + saEvent);
    Pair<String, String> toSay = _generator.getSurfaceFormExtendedLf(saEvent);
    //logger.info("text: " + toSay);
    return toSay;
  }

  public DialogueAct interpret(String text) {
    if (_interpreter != null) {
      return _interpreter.analyse(text);
    }
    return null;
  }
}
