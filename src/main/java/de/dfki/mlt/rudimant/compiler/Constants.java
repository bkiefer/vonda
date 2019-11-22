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

package de.dfki.mlt.rudimant.compiler;

import static de.dfki.mlt.rudimant.common.Constants.*;

public interface Constants {

  // Definitions for methods and variables in Agent.java
  public static final String AGENT_DEFS = "Agent" + RULE_FILE_EXTENSION;

  public static final String CFG_OUTPUT_DIRECTORY = "outputDirectory";
  public static final String CFG_WRAPPER_CLASS = "wrapperClass";
  public static final String CFG_ONTOLOGY_FILE = "ontologyFile";
  public static final String CFG_TYPE_ERROR_FATAL = "failOnError";
  public static final String CFG_PACKAGE = "rootPackage";
  public static final String CFG_NAME_TO_URI = "nameToURI";
  public static final String CFG_NAME_TO_CLASS = "nameToClass";
  public static final String CFG_VISUALISE = "visualise";
  public static final String CFG_LOGGING = "logging";

  public static final String DIALOGUE_ACT_TYPE = "<dial:DialogueAct>";

  public static final String CANCEL_LOCAL = "1";
  public static final String CANCEL_GLOBAL = "-1";

  public static final String CFG_RULE_LOCATION_FILE = "ruleLocationFile";
  public static final String CFG_RULE_LOCATION_SUFFIX = "RuleLocation.yml";

  public static final String UNCRUST_CFG_OLD = "/uncrustify.cfg";
  public static final String UNCRUST_CFG_NEW = "/uncrust0.66.cfg";
}

