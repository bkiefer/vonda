package de.dfki.mlt.rudimant.compiler;

import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import de.dfki.mlt.rudimant.common.Position;
import de.dfki.mlt.rudimant.compiler.tree.RudiTree;

public class TokenHandler {

  private LinkedList<Token> tokens;
  private LinkedList<Token> commentTokens;

  // in this list, keep all comments that belong to methods or rules that
  // are not evaluated into the process method
  private LinkedList<Token> savedComments;

  public TokenHandler(LinkedList<Token> tokz, LinkedList<Token> ctokz) {
    tokens = tokz;
    commentTokens = ctokz;
    savedComments = new LinkedList<>();
  }

  private static String removeJavaBrackets(String c){
    // Deal with java code comments
    if (c.startsWith("/*@")) {
      c = c.substring(3, c.length() - 3);
    }
    return c;
  }

  /** Print commments that should be positioned before any generated code */
  public void initialComments(Position firstPos, Writer out)
      throws IOException {
    Iterator<Token> it = commentTokens.iterator();
    while (it.hasNext()) {
      Token curr = it.next();
      if (curr.getStart().getCharpos() < firstPos.getCharpos()
          && curr.getText().startsWith("/*@")) {
        out.append(removeJavaBrackets(curr.getText()));
        it.remove();
      } else break;
    }
  }


  /** Save comments that belong to a postponed part of the code onto another
   *  list (all before pos)
   */
  public void saveCommentsForLater(Position pos) {
    while (!commentTokens.isEmpty()
        && commentTokens.get(0).getStart().getCharpos() < pos.getCharpos()) {
      savedComments.addFirst(commentTokens.get(0));
      commentTokens.remove();
    }
  }

  /** put the saved comments back onto the original list for processing */
  public void pourBackSavedComments() {
    while (!savedComments.isEmpty()) {
      commentTokens.addFirst(savedComments.getFirst());
      savedComments.removeFirst();
    }
  }

  /** check if there are comment tokens which should be put into the compiled
   *  Java output file
   * @param firstPos
   * @param out
   * @throws IOException
   */
  public void checkComments(Position firstPos, Writer out) throws IOException {
    Iterator<Token> it = commentTokens.iterator();
    Token next;
    boolean content = false;
    while (it.hasNext()
        && (next = it.next()).getStart().getCharpos() < firstPos.getCharpos()) {
      it.remove();
      String comment = next.getText();
      comment = removeJavaBrackets(comment);
      if (!comment.trim().isEmpty()) {
        out.append(comment);
        content = true;
      }
    }
    if (content) out.append('\n');
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
