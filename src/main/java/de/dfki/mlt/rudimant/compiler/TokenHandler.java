package de.dfki.mlt.rudimant.compiler;

import java.util.LinkedList;
import java.util.List;

import de.dfki.mlt.rudimant.common.Position;
import de.dfki.mlt.rudimant.compiler.tree.GrammarFile;
import de.dfki.mlt.rudimant.compiler.tree.RudiTree;

public class TokenHandler {

  private LinkedList<Token> tokens;
  private LinkedList<Token> commentTokens;

  public TokenHandler(LinkedList<Token> tokz, LinkedList<Token> ctokz) {
    tokens = tokz;
    commentTokens = ctokz;
  }

  /** Attach comment tokens to the RudiTree item immediately after the token.
   *
   *  This may result in multiple attachments if the RudiTree items are moved
   *  or start at the exact same position.
   *
   * This code assumes that preorder traversal also reflects the order in the
   * file.
   */
  private void attachRec(RudiTree r, List<Token> comments) {
    r.comments = new LinkedList<>();
    Token t;
    while(! comments.isEmpty()
        && (t = comments.get(0)).getEnd().getCharpos()
        <= r.getLocation().getBegin().getCharpos()) {
      if (! t.getText().isBlank()) {
        r.comments.add(t);
      }
      comments.remove(0);
    }
    for (RudiTree dtr : r.getDtrs()) {
      attachRec(dtr, comments);
    }
  }

  /** The top level needs to be treated slightly differently from the rest */
  public void attachComments(GrammarFile gf) {
    List<Token> comments = new LinkedList<>();
    comments.addAll(commentTokens);
    for(RudiTree r : gf.getDtrs()) {
      attachRec(r, comments);
    }
    // What's left is assigned to the GrammarFile
    gf.comments = comments;
  }

  /** find the position of the token that starts at or immediately after pos */
  private int indexOf(List<Token> tokens, Position start) {
    for(int i = 0; i < tokens.size(); ++i) {
      if (tokens.get(i).getStart().compareTo(start) >= 0) return i;
    }
    return -1;
  }

  /** Reconstruct the string in the original source file for some node */
  public String getFullText(RudiTree node) {
    Position start = node.getLocation().getBegin();
    Position end = node.getLocation().getEnd();
    StringBuffer sb = new StringBuffer();
    // find the positions of the first token starting at start
    int comm = indexOf(commentTokens, start);
    int cont = indexOf(tokens, start);
    while ((comm != -1 && comm < commentTokens.size()
          && commentTokens.get(comm).getEnd().compareTo(end) <= 0) ||
           (cont != -1 && cont < tokens.size()
              && tokens.get(cont).getEnd().compareTo(end) <= 0)) {
      // find which token is next, append it and increase the appropriate index
      if (comm != -1 && comm < commentTokens.size()
          && commentTokens.get(comm).getStart().compareTo(tokens.get(cont).getStart()) <= 0){
        sb.append(commentTokens.get(comm).getText());
        ++comm;
      } else {
        sb.append(tokens.get(cont).getText());
        ++cont;
      }
    }
    return sb.toString();
  }
}
