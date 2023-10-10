package de.dfki.mlt.rudimant.compiler.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import de.dfki.mlt.rudimant.compiler.Mem;
import de.dfki.mlt.rudimant.compiler.TokenHandler;
import de.dfki.mlt.rudimant.compiler.tree.GrammarFile;

public class BisonParser {

  public static boolean DEBUG_GRAMMAR = false;

  public static GrammarFile parse(String realName, InputStream in, Mem mem)
      throws IOException {
    VondaLexer lexer = new VondaLexer(new InputStreamReader(in, "UTF-8"));
    lexer.setOrigin(realName);
    lexer.setMem(mem);
    VondaGrammar grammar = new VondaGrammar(lexer);
    if (DEBUG_GRAMMAR) {
      grammar.setDebugLevel(99); grammar.setErrorVerbose(true);
    }
    try {
      if (! grammar.parse()) return null;
    } catch (RuntimeException ex) {
      grammar.yyerror(lexer.getStartPos(), ex.getMessage());
      return null;
    }
    return new GrammarFile(grammar.getResult(),
        new TokenHandler(lexer.getTokens(), lexer.getCommentTokens()));
  }
}
