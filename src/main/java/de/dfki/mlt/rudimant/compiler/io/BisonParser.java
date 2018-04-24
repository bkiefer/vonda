package de.dfki.mlt.rudimant.compiler.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

import de.dfki.mlt.rudimant.compiler.io.VondaLexer.Token;
import de.dfki.mlt.rudimant.compiler.tree.GrammarFile;

public class BisonParser {

  public static GrammarFile parse(String realName, InputStream in)
      throws IOException {
    VondaLexer lexer = new VondaLexer(new InputStreamReader(in, "UTF-8"));
    VondaGrammar grammar = new VondaGrammar(lexer);
    //grammar.setDebugLevel(99); grammar.setErrorVerbose(true);
    if (! grammar.parse()) return null;
    GrammarFile result = grammar.getResult();
    result.tokens = lexer.getCollectedTokens();
    //result.tokens = new LinkedList();
    return result;
  }
}
