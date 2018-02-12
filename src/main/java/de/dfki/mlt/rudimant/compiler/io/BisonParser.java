package de.dfki.mlt.rudimant.compiler.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import org.antlr.v4.runtime.Token;

import de.dfki.mlt.rudimant.compiler.tree.GrammarFile;

public class BisonParser {

  public static GrammarFile parse(String realName, InputStream in,
      List<Token> tokens) throws IOException {
    // TODO: lexer needs wrapping to collect the relevant comment tokens etc.
    VondaLexer lexer = new VondaLexer(new InputStreamReader(in, "UTF-8"));
    VondaGrammar grammar = new VondaGrammar(lexer);
    if (! grammar.parse()) return null;
    GrammarFile result = grammar.getResult();
    result.tokens = new LinkedList();
    return result;
  }
}
