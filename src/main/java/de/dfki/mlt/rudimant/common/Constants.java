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

/** A set of constants only relevant at run-time and compilation */
public class Constants {
  /** The location of the ontology configuration file, if any */
  public static final String CFG_ONTOLOGY_FILE = "ontologyFile";

  public static final String CFG_INPUT_FILE = "inputFile";
  public static final String CFG_OUTPUT_DIRECTORY = "outputDirectory";
  public static final String CFG_WRAPPER_CLASS = "wrapperClass";
  public static final String CFG_TYPE_ERROR_FATAL = "failOnError";
  public static final String CFG_PACKAGE = "rootPackage";
  public static final String CFG_NAME_TO_URI = "nameToURI";
  public static final String CFG_NAME_TO_CLASS = "nameToClass";
  public static final String CFG_VISUALISE = "visualise";
  public static final String CFG_LOGGING = "logging";

  /** the port for the debugger to connect to the running system */
  public static final String CFG_DEBUG_PORT = "debugPort";
  /** NLG option section */
  public static final String CFG_NLG_KEY = "NLG";
  /** NLG mapper config */
  public static final String CFG_MAPPER_PROJECT = "mapperProject";
  /** NLG main config */
  public static final String CFG_GENERATION_PROJECT = "generationProject";
  /** NLG Flag if numbers should be translated to text by ICU or not */
  public static final String CFG_TRANSLATE_NUMBERS = "translateNumbers";

  /** NLU option section */
  public static final String CFG_NLU_KEY = "NLU";

  public static final String RULE_FILE_EXTENSION = ".rudi";
  public static final String RULE_LOCATION_FILE = "RuleLoc.yml";

  /* used to mark the rule logging state of a rule */
  public static final int STATE_NEVER = 0;
  public static final int STATE_IF_TRUE = 0b1;
  public static final int STATE_IF_FALSE = 0b10;
  public static final int STATE_ALWAYS = STATE_IF_TRUE | STATE_IF_FALSE;
  public static final int STATE_PARTLY = 9;
  public static final int STATE_RULELESS = 10;
}
