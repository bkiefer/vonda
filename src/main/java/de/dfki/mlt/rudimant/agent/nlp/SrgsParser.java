package de.dfki.mlt.rudimant.agent.nlp;

import static de.dfki.mlt.rudimant.common.Constants.CFG_NLU_GRAMMAR;
import static de.dfki.mlt.rudimant.common.Constants.CFG_NLU_TOKENIZER;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.json.JSONObject;
import org.jvoicexml.processor.AbstractParser;
import org.jvoicexml.processor.AbstractParser.ChartNode;
import org.jvoicexml.processor.JVoiceXmlGrammarManager;
import org.jvoicexml.processor.SemanticsInterpreter;
import org.jvoicexml.processor.grammar.Grammar;
import org.jvoicexml.processor.srgs.GrammarException;

import de.dfki.mlt.rudimant.agent.DialogueAct;

public class SrgsParser extends Interpreter {
  protected JVoiceXmlGrammarManager manager;
  protected Grammar grammar;
  protected AbstractParser checker;
  protected Tokenizer tokenizer = null;

  @SuppressWarnings({ "rawtypes", "unchecked" })
  @Override
  public boolean init(File configDir, String language, Map config) {
    String grammarName = (String) config.get(CFG_NLU_GRAMMAR);
    if (grammarName == null)
      return false;
    try {
      manager = new JVoiceXmlGrammarManager();
      grammar = manager.loadGrammar(new File(configDir, grammarName).toURI());
      checker = AbstractParser.getParser(manager);
    } catch (IOException | GrammarException ex) {
      logger.error("Could not read grammar file {} because of {}",
          new File(configDir, grammarName), ex.toString());
      return false;
    }
    if (config.containsKey(CFG_NLU_TOKENIZER)) {
      tokenizer = Tokenizer.getTokenizer(configDir, language,
          (Map<String, Object>)config.get(CFG_NLU_TOKENIZER));
    } else {
      tokenizer = new TrivialTokenizer();
    }
    return super.init(configDir, language, config);
  }

  @Override
  public DialogueAct analyse(String text) {
    String[] tokens = tokenizer.tokenize(text);
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
