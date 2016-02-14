/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grammar;

import de.dfki.mlt.rudimant.io.RobotGrammarParser;
import de.dfki.mlt.rudimant.io.RobotGrammarVisitor;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

/**
 *
 * @author anna
 */
public class RGVisitor implements RobotGrammarVisitor<String> {
    
  private String indent;
    
  private boolean writeToFile(String text){
    try {
      Main.writer.write(indent + text);
    } catch (IOException ex) {
      Logger.getLogger(RGVisitor.class.getName()).log(Level.SEVERE, null, ex);
    }
    return true;
  }

  @Override
  public String visitStatement_block(RobotGrammarParser.Statement_blockContext ctx) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public String visitGrammar_file(RobotGrammarParser.Grammar_fileContext ctx) {
    writeToFile("public class RulesQuiz extends RuleUnit {\n\n"
              + "  // GameData is an RDF class proxy in java code\n"
              + "  // TODO: The type of game must specified, can not be inferred, same for currentUser\n"
              + "  // and speech act\n"
              + "  // TODO: what about relational properties aka multi-valued features\n"
              + "  // TODO: possibly gamePlayed must be a custom method / ASK query\n\n"
              + "  GameData game;"
              + "\n"
              + "  // User is an RDF class proxy in java code"
              + "\n"
              + "  User currentUser;"
              + "\n\n  // This must be a real class"
              + "\n"
              + "  GameLogic gameLogic;"
              + "\n\n  // SpeechActs, RDF proxies"
              + "\n"
              + "  DialogueAct myLastSA, currentSA;"
              + "\n\n"
              + "  private void computeProxies() "
              + "{\n"
              + "    // TODO: ... missing definition generating this code\n"
              + "  }\n\n"
              + "  public void mainLoop() {\n"
              + "    computeProxies();");
    this.visitChildren(ctx);
    writeToFile("  }\n}");
    return null;
  }

  @Override
  public String visitLoop_propose_statement(RobotGrammarParser.Loop_propose_statementContext ctx) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public String visitFunction_call(RobotGrammarParser.Function_callContext ctx) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public String visitGrammar_rule(RobotGrammarParser.Grammar_ruleContext ctx) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public String visitArithmetic_operator(RobotGrammarParser.Arithmetic_operatorContext ctx) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public String visitArithmetic(RobotGrammarParser.ArithmeticContext ctx) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public String visitNumber(RobotGrammarParser.NumberContext ctx) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public String visitStatement(RobotGrammarParser.StatementContext ctx) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public String visitPropose_block(RobotGrammarParser.Propose_blockContext ctx) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public String visitTerm(RobotGrammarParser.TermContext ctx) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public String visitWhile_statement(RobotGrammarParser.While_statementContext ctx) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public String visitArithmetic_lin_operator(RobotGrammarParser.Arithmetic_lin_operatorContext ctx) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public String visitExp(RobotGrammarParser.ExpContext ctx) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public String visitFactor(RobotGrammarParser.FactorContext ctx) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public String visitLiteral_or_graph_exp(RobotGrammarParser.Literal_or_graph_expContext ctx) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public String visitSimple_b_exp(RobotGrammarParser.Simple_b_expContext ctx) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public String visitBoolean_exp(RobotGrammarParser.Boolean_expContext ctx) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public String visitLoop_propose_block(RobotGrammarParser.Loop_propose_blockContext ctx) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public String visitAssignment(RobotGrammarParser.AssignmentContext ctx) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public String visitBoolean_op(RobotGrammarParser.Boolean_opContext ctx) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public String visitPropose_arg(RobotGrammarParser.Propose_argContext ctx) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public String visitLabel(RobotGrammarParser.LabelContext ctx) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public String visitArithmetic_dot_operator(RobotGrammarParser.Arithmetic_dot_operatorContext ctx) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public String visitPropose_statement(RobotGrammarParser.Propose_statementContext ctx) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public String visitLoop_if_statement(RobotGrammarParser.Loop_if_statementContext ctx) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public String visitLambda_exp(RobotGrammarParser.Lambda_expContext ctx) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public String visitFor_statement(RobotGrammarParser.For_statementContext ctx) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public String visitString_expression(RobotGrammarParser.String_expressionContext ctx) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public String visitField_access(RobotGrammarParser.Field_accessContext ctx) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public String visitLoop_statement_block(RobotGrammarParser.Loop_statement_blockContext ctx) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public String visitField_access_vfunc(RobotGrammarParser.Field_access_vfuncContext ctx) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public String visitIf_statement(RobotGrammarParser.If_statementContext ctx) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public String visitLoop_statement(RobotGrammarParser.Loop_statementContext ctx) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public String visitComment(RobotGrammarParser.CommentContext ctx) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public String visit(ParseTree pt) {
    return pt.accept(this);
  }

  /**
   * Don't use this method in a rule where you want to do type checking! (you
   * won't get back anything useful)
   * @param rn
   * @return nothing
   */
  @Override
  public String visitChildren(RuleNode rn) {
      // TODO: starting count at 0 or 1??
    for(int n = 0; n < rn.getChildCount(); ++n){
        this.visit(rn.getChild(n));
    }
    return null;
  }

  @Override
  public String visitTerminal(TerminalNode tn) {
    return this.visit(tn);
  }

  // TODO: what to do?
  @Override
  public String visitErrorNode(ErrorNode en) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

}
