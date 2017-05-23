/*
 * To change license header, choose License Headers in Project Properties.
 * To change template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

import de.dfki.mlt.rudimant.Location;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.TerminalNode;

/**
 * all classes that can be created by the ParseTreeVisitor should implement this
 * interface, for it is the return type of the visit methods
 *
 * @author Anna Welker
 */
public abstract class RudiTree {

  /**
   * positions contains the start and stop positions of a Token according to its
   * ParserRuleContext. [0] = start of TokenIndex, [1] = stop of TokenIndex
   */
  public int[] positions;

  /** contains the origin file and the line Rudi Tree started on */
  public Location location;

  /** The input string that is covered by node */
  public String fullexp;

  public void visitWithComments(VGenerationVisitor v) {
    int firstPos = positions[0];
    v.out.append(checkComments(v, firstPos));
    visitVoidV(v);
    int endPos = positions[1];
    v.out.append(checkComments(v, endPos));
  }

  public <T extends RudiTree> T fixFields(T b) {
    b.positions = positions;
    b.fullexp = fullexp;
    return b;
  }

  public RTStatement ensureStatement() {
    if (this instanceof RTExpression) {
      return fixFields(new StatExpression((RTExpression)this));
    }
    return (RTStatement)this;
  }

  boolean lookingForImport = false;

  /**
   * print the comment before and forget about it if -and only if- it is an
   * import in java escapes
   */
  public void printImportifJava(VGenerationVisitor v) {
    lookingForImport = true;
    v.out.append(checkComments(v, positions[0]));
    lookingForImport = false;
  }

  /**
   * the visitMethod for the visitor that allows to return Strings ! only to be
   * used by expressions !
   */
  public abstract String visitStringV(RTStringVisitor v);

  /**
   * the visitMethod for the visitor that allows to return Strings ! for
   * everything except expressions, they should write to out !
   */
  public abstract void visitVoidV(VGenerationVisitor v);

  protected String checkComments(VGenerationVisitor v, int firstPos) {
    String allcomments = "";
    while (!v.collectedTokens.isEmpty() && v.collectedTokens.get(0).getTokenIndex() < firstPos) {
      String comment = v.collectedTokens.get(0).getText();
      // Deal with java code
      if (comment.startsWith("/*@")) {
        comment = comment.substring(3, comment.length() - 3);
        if (lookingForImport) {
          if (!comment.contains("import")) {
            return allcomments;
          }
        }
      }
      if (!comment.trim().isEmpty()) {
        allcomments += comment;
      }
      v.collectedTokens.remove();
    }
    return allcomments;
  }

  /**
   * setPosition is used to store the start and stop position of a Token given
   * its ParserRuleContext.
   *
   * @param context The ParserRuleContext.
   * @return RudiTree
   */
  public RudiTree setPosition(ParserRuleContext context, String originClass) {
    positions = new int[]{
      context.getStart().getTokenIndex(),
      context.getStop().getTokenIndex()
    };
    location = new Location(originClass, context.getStart().getLine());
    fullexp = context.getText();
    return this;
  }

  /**
   * setPosition is used to store the start and stop position of a Token given
   * its ParserRuleContext.
   *
   * @param tn The TerminalNode
   * @return RudiTree
   */
  public RudiTree setPosition(TerminalNode tn, String originClass) {
    positions = new int[]{
      tn.getSymbol().getTokenIndex(),
      tn.getSymbol().getTokenIndex()
    };
    location = new Location(originClass, tn.getSymbol().getTokenIndex());
    fullexp = tn.getText();
    return this;
  }

  public abstract Iterable<? extends RudiTree> getDtrs();

  public Location getLocation() {
    return location;
  }
}
