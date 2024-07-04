package de.dfki.mlt.rudimant.agent.nlp;

import static de.dfki.mlt.rudimant.common.Configs.*;
import static org.jvoicexml.processor.BestTreeFinder.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.jvoicexml.processor.AbstractParser;
import org.jvoicexml.processor.ChartNode;
import org.jvoicexml.processor.Configuration;
import org.jvoicexml.processor.JVoiceXmlGrammarManager;
import org.jvoicexml.processor.SemanticsInterpreter;
import org.jvoicexml.processor.Traversable;
import org.jvoicexml.processor.grammar.Grammar;
import org.jvoicexml.processor.srgs.GrammarException;

public class SrgsParser extends Interpreter {
  protected JVoiceXmlGrammarManager manager;
  protected Grammar grammar;
  protected AbstractParser checker;
  protected Tokenizer tokenizer = null;

  @SuppressWarnings({ "rawtypes", "unchecked" })
  @Override
  public boolean init(File configDir, String language, Map config) {
    name = "SRGS_" + language;
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
    synchronized (checker) {
      String[] tokens = tokenizer.tokenize(text);
      Traversable validRule = null;
      DialogueAct result = null;
      try {
        // TODO: give diagnostics if no validRule is returned
        validRule = checker.parse(grammar, tokens);
      } catch (GrammarException ex) {
        logger.error(ex.toString());
      }
      if (validRule != null) {
        List<ChartNode> all = checker.returnAllResults().collect(Collectors.toList());
        Configuration best = findBestTree(all);
        if (! best.isDefault()) {
          logger.debug("Best tree is not first tree");
          validRule = best;
        }

        JSONObject object = SemanticsInterpreter.interpret(checker, validRule);
        result = convert(object);
      }
      if (result != null) {
        logger.info("{} result for {}: {}", name, text, result.toString());
      } else {
        logger.info("No {} result for {}", name, text);
      }
      return result;
    }
  }
}
