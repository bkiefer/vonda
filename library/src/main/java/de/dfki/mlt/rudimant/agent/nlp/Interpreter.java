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

import static de.dfki.mlt.rudimant.common.Configs.CFG_NLU_CONVERTER;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Iterator;
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

  public static DialogueAct NO_RESULT = null;

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
    NO_RESULT = new DialogueAct("OutOfDomain(top)");
    return true;
  }

  protected DialogueAct noResult() {
    return NO_RESULT.copy();
  }

  public static short firstid = -1, restid = -1;

  private DagNode array2dag(Iterable<Object> arr) {
    if (firstid < 0) {
      firstid = DagNode.getFeatureId("first");
      restid = DagNode.getFeatureId("rest");
    }
    DagNode value = new DagNode();
    value.setNominal();
    DagNode curr = value;
    Iterator<Object> it = arr.iterator();
    while (it.hasNext()) {
      Object elt = it.next();
      //for (Object elt : arr) {
      curr.addEdge(firstid, objDispatch(elt));
      if (it.hasNext()) {
        DagNode rest = new DagNode().setNominal();
        curr.addEdge(restid, rest);
        curr = rest;
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
      value = array2dag((JSONArray) o);
    } else if (o instanceof Map) {
      value = map2dag((Map) o);
    } else if (o instanceof Iterable) {
      value = array2dag((Iterable) o);
    } else if (o instanceof String) {
      value = atom2dag((String)o);
    } else if (o instanceof Number) {
      value = atom2dag(String.valueOf(o));
    }
    return value;
  }

  public DagNode map2dag(Map<String, Object> obj) {
    DagNode res = new DagNode();
    res.setNominal();
    for (String key : obj.keySet()) {
      short feat = DagNode.getFeatureId(key);
      DagNode value = objDispatch(obj.get(key));
      res.addEdge(feat, value);
    }
    return res;
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
