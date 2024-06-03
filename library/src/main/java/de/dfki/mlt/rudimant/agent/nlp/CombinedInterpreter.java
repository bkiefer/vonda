package de.dfki.mlt.rudimant.agent.nlp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This class currently implements a sequence of Interpreters to be asked for
 * results. The first that returns a result wins.
 *
 * TODO
 * This class could be extended in several ways (together with Interpreter)
 * 1. Confindence threshold: only consider results with a minimal confidence
 * 2. Alternative parallel Interpreters / ensemble: the highest conf wins
 * To implement this, DialogueAct might have to be extended, or a special slot
 * reserved to store the confidence of the Interpreter result.
 *
 */
public class CombinedInterpreter extends Interpreter {

  List<Interpreter> instances = new ArrayList<>();

  @SuppressWarnings({ "unchecked", "rawtypes" })
  @Override
  public boolean init(File configDir, String language, Map config) {
    List<Map> interpreterConfigs = (List<Map>) config.get("instances");
    for (Map conf : interpreterConfigs) {
      Interpreter i = (Interpreter) LanguageServices.createNLProcessor(
          configDir, language, conf);
      if (i != null) {
        instances.add(i);
      }
    }
    return true;
  }

  @Override
  public DialogueAct analyse(String text) {
    logger.info(text);

    for (Interpreter i : instances) {
      DialogueAct da = i.analyse(text);
      if (da != null) {
        return da;
      }
    }
    return noResult();
  }

}
