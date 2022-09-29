package de.dfki.chatcat;

import static de.dfki.chatcat.Constants.CFG_SRGS_GRAMMAR;
import static de.dfki.chatcat.Constants.DA_SLOT;
import static de.dfki.chatcat.Constants.PROP_SLOT;

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
import de.dfki.mlt.rudimant.agent.nlg.Interpreter;

public class SrgsParser extends Interpreter {
  JVoiceXmlGrammarManager manager;
  Grammar ruleGrammar;
  AbstractParser checker;

  @SuppressWarnings("rawtypes")
  @Override
  public boolean init(File configDir, String language, Map config) {
    String grammarName = (String)config.get(CFG_SRGS_GRAMMAR);
    if (grammarName == null) return false;
    try {
      manager = new JVoiceXmlGrammarManager();
      ruleGrammar = manager.loadGrammar(new File(configDir, grammarName).toURI());
      checker = AbstractParser.getParser(manager);
    } catch (IOException | GrammarException ex){
      logger.error("Could not read grammar file {} because of {}",
          new File(configDir, grammarName), ex.toString());
      return false;
    };
    return true;
  }

  @Override
  public DialogueAct analyse(String text) {
    String[] tokens = text.split(" +");
    try {
      //TODO: Find out why no validRule is returned
      ChartNode validRule = checker.parse(ruleGrammar, tokens);
      if (validRule != null) {
        JSONObject object = SemanticsInterpreter.interpret(checker, validRule);
        String da = object.getString(DA_SLOT);
        if (da == null) return null;
        String prop = object.getString(PROP_SLOT);
        if (prop == null) return null;
        StringBuilder sb = new StringBuilder();
        sb.append(da).append('(').append(prop);
        for (String key : object.keySet()) {
          if (! key.equals(DA_SLOT) && !key.equals(PROP_SLOT)) {
            sb.append(", ").append(key)
              .append("=\"").append(object.get(key)).append('"');
          }
        }
        sb.append(')');
        return new DialogueAct(sb.toString());
      }
    }
    catch (GrammarException ex) {
      logger.error(ex.toString());
    }
    return null;
  }

}
