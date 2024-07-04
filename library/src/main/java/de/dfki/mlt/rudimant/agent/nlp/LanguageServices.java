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

package de.dfki.mlt.rudimant.agent.nlp;

import static de.dfki.mlt.rudimant.common.Configs.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dfki.lt.tr.dialogue.cplan.DagNode;

/**
 * A factory to create NLU and NLG components for the Agent
 */
public class LanguageServices {
  public static final Logger logger = LoggerFactory.getLogger(LanguageServices.class);

  /**
   * The language generation engine
   */
  protected Generator _generator;

  protected Interpreter _interpreter;

  /** Factory method to get a language analyser or generator for the given config.
   *
   * @param configDir   the directory where the config file is (for relative paths)
   * @param currentLang which language should be treated
   * @param langConfig  the configuration to create a new processor
   * @return a new processor (Interpreter/Generator) for the given language, or
   *         null in case of failure.
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public static <T extends NLProcessor> T
  createNLProcessor(File configDir, String currentLang,
      Map<String, Object> langConfig) {
    String className = (String)langConfig.get(CFG_CLASS);
    T proc = null;
    try {
      Class clazz = Class.forName(className);
      proc = (T) clazz.getConstructor().newInstance();
      if (proc != null && ! proc.init(configDir, currentLang, langConfig))
        return null;
    }
    catch (ClassNotFoundException | InstantiationException |
        IllegalAccessException | IllegalArgumentException |
        SecurityException | InvocationTargetException | NoSuchMethodException ex) {
      logger.error("No valid interpreter/generator class for: {}", className);
    }
    return proc;
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  public void loadGrammar(File configDir, String language, Map configs)
      throws IOException {

    Map nlgConfig = (Map)configs.get(CFG_NLG_KEY);
    Map nluConfig = (Map)configs.get(CFG_NLU_KEY);

    if (nlgConfig  != null && nlgConfig.containsKey(language)) {
      _generator = createNLProcessor(configDir, language,
          (Map<String, Object>)nlgConfig.get(language));
    }

    if (nluConfig != null && nluConfig.containsKey(language)) {
      _interpreter = (Interpreter) createNLProcessor(configDir, language,
          (Map)nluConfig.get(language));
    }
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

  public Generator getGenerator() {
    return _generator;
  }

  public Interpreter getInterpreter() {
    return _interpreter;
  }

  public Pair<String, String> generate(DagNode saEvent) {
    Pair<String, String> toSay = null;
    if (_generator != null) {
      //logger.info("raw:" + saEvent);
      toSay = _generator.generate(saEvent);
      //logger.info("text: " + toSay);
    }
    return toSay;
  }

  public DialogueAct interpret(String text) {
    if (_interpreter != null) {
      return _interpreter.analyse(text);
    }
    return null;
  }
}
