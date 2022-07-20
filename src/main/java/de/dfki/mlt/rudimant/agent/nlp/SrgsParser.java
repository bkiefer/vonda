package de.dfki.mlt.rudimant.agent.nlp;

import static de.dfki.mlt.rudimant.common.Constants.*;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.json.JSONObject;
import org.jvoicexml.processor.srgs.ChartGrammarChecker;
import org.jvoicexml.processor.srgs.ChartGrammarChecker.ChartNode;
import org.jvoicexml.processor.srgs.JVoiceXmlGrammarManager;
import org.jvoicexml.processor.srgs.SemanticsInterpreter;
import org.jvoicexml.processor.srgs.grammar.Grammar;
import org.jvoicexml.processor.srgs.grammar.GrammarException;

import de.dfki.mlt.rudimant.agent.DialogueAct;

// TODO: TURN INTO A VONDA MODULE/PLUGIN
public class SrgsParser extends Interpreter {
  JVoiceXmlGrammarManager manager;
  Grammar grammar;
  ChartGrammarChecker checker;

  @SuppressWarnings("rawtypes")
  @Override
  public boolean init(File configDir, String language, Map config) {
    String grammarName = (String) config.get(CFG_NLU_GRAMMAR);
    if (grammarName == null)
      return false;
    try {
      manager = new JVoiceXmlGrammarManager();
      grammar = manager.loadGrammar(new File(configDir, grammarName).toURI());
      checker = new ChartGrammarChecker(manager);
    } catch (IOException | GrammarException ex) {
      logger.error("Could not read grammar file {} because of {}",
          new File(configDir, grammarName), ex.toString());
      return false;
    }
    return super.init(configDir, language, config);
  }

  @Override
  public DialogueAct analyse(String text) {
    text = preprocess(text);
    String[] tokens = text.split(" +");
    ChartNode validRule = null;
    DialogueAct result = null;
    try {
      // TODO: give diagnostics if no validRule is returned
      validRule = checker.parse(grammar, tokens);
    } catch (GrammarException ex) {
      logger.error(ex.toString());
    }
    if (validRule != null) {
      JSONObject object = SemanticsInterpreter.interpret(checker, validRule);
      result = convert(object);
    }
    logger.error("srgs: {}", (result == null ? "null" : result.toString()));
    return result;
  }
}
