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

import static de.dfki.mlt.rudimant.common.Constants.CFG_NLU_CONVERTER;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dfki.lt.tr.dialogue.cplan.DagNode;
import de.dfki.lt.tr.dialogue.cplan.UtterancePlanner;

/* *************************************************************
 * A factory and superclass for all interpreters
 * ************************************************************ */
public abstract class Interpreter extends NLProcessor {

  protected static Logger logger = LoggerFactory.getLogger(Interpreter.class);

  protected UtterancePlanner daConverter = null;

  protected String name = "NLU";

  @Override
  @SuppressWarnings("rawtypes")
  public boolean init(File configDir, String language, Map config) {
    super.init(configDir, language, config);
    String converterName = (String) config.get(CFG_NLU_CONVERTER);
    if (converterName != null) {
      // load converter
      try {
        daConverter = new UtterancePlanner();
        daConverter.readProjectFile(new File(configDir, converterName));
        daConverter.setLanguage(CPlannerNlg.langToTag(language));
      } catch (IOException ex) {
        logger.error("Could not read converter project {} because of {}",
            new File(configDir, converterName), ex.toString());
        return false;
      }
    }
    return true;
  }

  private DagNode array2dag(JSONArray arr) {
    short firstid = DagNode.getFeatureId("first");
    short restid = DagNode.getFeatureId("rest");
    DagNode value = new DagNode();
    value.setNominal();
    DagNode curr = value;
    if (arr.length() > 0) {
      Object last = arr.get(arr.length() - 1);
      for (Object elt : arr) {
        curr.addEdge(firstid, objDispatch(elt));
        if (elt != last) {
          DagNode rest = new DagNode().setNominal();
          curr.addEdge(restid, rest);
          curr = rest;
        }
      }
    }
    return value;
  }

  private DagNode atom2dag(String atom) {
    return new DagNode(DagNode.PROP_FEAT_ID, new DagNode(atom));
  }

  private DagNode objDispatch(Object o) {
    DagNode value = null;
    if (o == null) {
      value = atom2dag("null");
    } else if (o instanceof JSONObject) {
      value = json2dag((JSONObject) o);
    } else if (o instanceof JSONArray) {
      value = array2dag((JSONArray)o);
    } else if (o instanceof String) {
      value = atom2dag((String)o);
    } else if (o instanceof Integer) {
      value = atom2dag(Integer.toString((Integer)o));
    } else if (o instanceof Double) {
      value = atom2dag(Double.toString((Double)o));
    } else if (o instanceof BigDecimal) {
      value = atom2dag(((BigDecimal)o).toPlainString());
    }
    return value;
  }

  public DagNode json2dag(JSONObject obj) {
    DagNode res = new DagNode();
    res.setNominal();
    for (String key : obj.keySet()) {
      short feat = DagNode.getFeatureId(key);
      DagNode value = objDispatch(obj.isNull(key) ? null : obj.get(key));
      res.addEdge(feat, value);
    }
    return res;
  }

  protected DialogueAct convert(JSONObject object) {
    DagNode in = json2dag(object);
    logger.debug("Incoming {} JSON: {}", name, object);
    DagNode node = in;
    logger.debug("Incoming {} result: {}", name, node);
    if (daConverter != null) {
      node = daConverter.process(in);
      logger.debug("Converted {} result: {}", name, node);
    }
    node.addEdge(DagNode.ID_FEAT_ID, new DagNode("raw"));
    return (node == null) ? null : new DialogueAct(node);
  }

  public abstract DialogueAct analyse(String text);
}
