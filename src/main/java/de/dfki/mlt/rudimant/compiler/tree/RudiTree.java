/*
 * To change license header, choose License Headers in Project Properties.
 * To change template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.compiler.tree;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;
//import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;

import de.dfki.mlt.rudimant.common.Location;
import de.dfki.mlt.rudimant.compiler.Position;
import de.dfki.mlt.rudimant.compiler.io.VondaGrammar;
import de.dfki.mlt.rudimant.compiler.io.VondaLexer.Token;

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
  public Position[] positions;

  /** contains the origin file and the line Rudi Tree started on */
  public Location location;

  /** The input string that is covered by node */
  public String fullexp;

  public void visitWithComments(VisitorGeneration v) {
    Position firstPos = positions[0];
    v.out.append(checkComments(v, firstPos));
    visit(v);
    // TODO: as endpos is where this node ends, we will never get to print anything
    //       here, will we?
    Position endPos = positions[1];
    v.out.append(checkComments(v, endPos));
  }

  /** visitor method, for double dispatch */
  public abstract void visit(RudiVisitor v);

  /**
   * set positions and fullexp of to those of this object
   * @param Ruditree b
   * @return
   */
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

  public static String removeJavaBrackets(String c){
    // Deal with java code
    if (c.startsWith("/*@")) {
      c = c.substring(3, c.length() - 3);
    }
    return c;
  }

  private List<Token> getTokensOfInterest(VisitorGeneration v, Position firstPos) {
    List<Token> tokens = new ArrayList<>();
    while (!v.collectedTokens.isEmpty() && v.collectedTokens.get(0).start.charpos < firstPos.charpos) {
      tokens.add(v.collectedTokens.get(0));
      v.collectedTokens.remove();
    }
    return tokens;
  }

  protected String checkComments(VisitorGeneration v, Position firstPos) {
    List<Token> ts = getTokensOfInterest(v, firstPos);
    String allcomments = "";
    for (int i = 0; i < ts.size(); i++) {
      String comment = ts.get(i).getText();
      comment = removeJavaBrackets(comment);
      allcomments += comment;
    }
    allcomments = allcomments.trim();
    if(!allcomments.isEmpty()) allcomments += "\n";
    return allcomments;
  }

  /**
   * setPosition is used to store the start and stop position of a Token given
   * its ParserRuleContext.
   *
   * @param context The ParserRuleContext.
   * @return RudiTree
   *
  public RudiTree setPosition(ParserRuleContext context, String originClass) {
    positions = new Position[]{
      context.getStart().getTokenIndex(),
      context.getStop().getTokenIndex()
    };
    location = new Location(originClass, context.getStart().getLine());
    fullexp = context.getText();
    return this;
  }*/

  /**
   * setPosition is used to store the start and stop position of a Token given
   * its ParserRuleContext.
   *
   * @param context The ParserRuleContext.
   * @return RudiTree
   */
  public RudiTree setPos(VondaGrammar.Location loc, VondaGrammar gram) {
    String originClass = ""; // TODO: GET IT FROM SOMEWHERE
    positions = new Position[]{ // TODO: does not match the old functionality
        loc.begin,
        loc.end
    };
    location = new Location(originClass, loc.begin.line);
    fullexp = gram.getFullText(loc.begin, loc.end); // TODO
    return this;
  }

  /**
   * setPosition is used to store the start and stop position of a Token given
   * its ParserRuleContext.
   *
   * @param context The ParserRuleContext.
   * @return RudiTree
   */
  public RudiTree setPos(VondaGrammar.Location start, VondaGrammar.Location end,
      VondaGrammar gram) {
    String originClass = ""; // TODO: SEE ABOVE
    positions = new Position[]{
        start.begin,
        end.end
    };
    location = new Location(originClass, start.begin.line);
    fullexp = gram.getFullText(start.begin, end.end);
    return this;
  }

  /**
   * setPosition is used to store the start and stop position of a Token given
   * its ParserRuleContext.
   *
   * @param tn The TerminalNode
   * @return RudiTree
   *
  public RudiTree setPosition(TerminalNode tn, String originClass) {
    positions = new int[]{
      tn.getSymbol().getTokenIndex(),
      tn.getSymbol().getTokenIndex()
    };
    location = new Location(originClass, tn.getSymbol().getTokenIndex());
    fullexp = tn.getText();
    return this;
  }*/

  public abstract Iterable<? extends RudiTree> getDtrs();

  public Location getLocation() {
    return location;
  }
}
