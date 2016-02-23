/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Versuch2;

import Versuch1.RobotContext;
import Versuch2.abstractTree.*;
import Versuch2.abstractTree.expressions.*;
import Versuch2.abstractTree.statements.*;
import Versuch2.abstractTree.leaves.*;
import de.dfki.mlt.rudimant.io.RobotGrammarParser;
import de.dfki.mlt.rudimant.io.RobotGrammarVisitor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

/**
 *
 * @author anna
 */
public class ParseTreeVisitor implements RobotGrammarVisitor<AbstractTree>{
  
  // this memory will know the types of variables
  private HashMap<String, AbstractType> memory;
  
  // the object that nows about context that we cannot see
  private RobotContext context;
  
  public ParseTreeVisitor(RobotContext context){
    this.memory = new HashMap<String, AbstractType>();
    this.context = context;
  }

  @Override
  public AbstractTree visitGrammar_file(RobotGrammarParser.Grammar_fileContext ctx) {
    ArrayList<AbstractTree> rules = new ArrayList<AbstractTree>();
    for (int i = 0; i < ctx.getChildCount(); i++){
      rules.add(this.visit(ctx.getChild(i)));
    }
    return new AGrammarFile(rules);
  }

  @Override
  public AbstractTree visitLabel(RobotGrammarParser.LabelContext ctx) {
    // this method should never be reached as label is a part of grammar rule and
    // is not invoked there
    return null;
  }

  @Override
  public AbstractTree visitGrammar_rule(RobotGrammarParser.Grammar_ruleContext ctx) {
    // label comment if_statement
    return new AGrammarRule(ctx.getChild(0).getText(),
            (ACommentBlock)this.visit(ctx.getChild(1)),
            (AIfStatement)this.visit(ctx.getChild(2)));
  }

  @Override
  public AbstractTree visitStatement_block(RobotGrammarParser.Statement_blockContext ctx) {
    // comment LBRACE comment ( statement )* RBRACE
    List<AbstractStatement> statblock = new ArrayList<AbstractStatement> ();
      statblock.add((AbstractStatement)this.visit(ctx.getChild(0)));
    for (int i = 2; i < ctx.getChildCount() -1; i++){
      statblock.add((AbstractStatement)this.visit(ctx.getChild(i)));
    }
    return new AbstractBlock(statblock, true);
  }

  @Override
  public AbstractTree visitStatement(RobotGrammarParser.StatementContext ctx) {
    // childcount can be one to infinity since we allow comments
    // -> solution: use an abstract block without braces as return container
    List<AbstractStatement> statblock = new ArrayList<AbstractStatement> ();
    for (int i = 0; i < ctx.getChildCount(); i++){
      statblock.add((AbstractStatement)this.visit(ctx.getChild(i)));
    }
    return new AbstractBlock(statblock, false);
  }

  @Override
  public AbstractTree visitLoop_statement_block(RobotGrammarParser.Loop_statement_blockContext ctx) {
    // comment LBRACE comment ( statement )* RBRACE
    List<AbstractStatement> statblock = new ArrayList<AbstractStatement> ();
      statblock.add((AbstractStatement)this.visit(ctx.getChild(0)));
    for (int i = 2; i < ctx.getChildCount() -1; i++){
      statblock.add((AbstractStatement)this.visit(ctx.getChild(i)));
    }
    return new AbstractBlock(statblock, true);
  }

  @Override
  public AbstractTree visitLoop_statement(RobotGrammarParser.Loop_statementContext ctx) {
    // childcount can be one to infinity since we allow comments
    // -> solution: use an abstract block without braces as return container
    List<AbstractStatement> statblock = new ArrayList<AbstractStatement> ();
    for (int i = 0; i < ctx.getChildCount(); i++){
      statblock.add((AbstractStatement)this.visit(ctx.getChild(i)));
    }
    return new AbstractBlock(statblock, false);
  }

  @Override
  public AbstractTree visitFunction_call(RobotGrammarParser.Function_callContext ctx) {
    // as we do not declare funccalls in grammar files, this must be a function access
    return new AFunctAccess(context.getFunctionAccessType(ctx.getText()), ctx.getText());
  }

  @Override
  public AbstractTree visitArithmetic(RobotGrammarParser.ArithmeticContext ctx) {
    AArithmeticExp arit = new AArithmeticExp(
            (AbstractExpression)this.visit(ctx.getChild(ctx.getChildCount() - 1)),
            null, null, false);
    for (int i = ctx.getChildCount() - 2; i >= 0; i--){
      if(i % 2 == 1){
        arit = new AArithmeticExp(
                new AArithmeticExp(
                        (AbstractExpression)this.visit(ctx.getChild(i--)),
                              null, null, false),
                        arit, ctx.getChild(i).getText(), false);
      }
    }
    return arit;
  }

  @Override
  public AbstractTree visitTerm(RobotGrammarParser.TermContext ctx) {
    // dasselbe in Grün
    AArithmeticExp arit = new AArithmeticExp(
            (AbstractExpression)this.visit(ctx.getChild(ctx.getChildCount() - 1)),
            null, null, false);
    for (int i = ctx.getChildCount() - 2; i >= 0; i--){
      if(i % 2 == 1){
        arit = new AArithmeticExp(
                new AArithmeticExp(
                        (AbstractExpression)this.visit(ctx.getChild(i--)),
                              null, null, false),
                        arit, ctx.getChild(i).getText(), false);
      }
    }
    return arit;
  }

  @Override
  public AbstractTree visitFactor(RobotGrammarParser.FactorContext ctx) {
    // number
    if(ctx.getChildCount() == 1){
      return new ANumber(ctx.getText());
    }
    // MINUS arithmetic
    else if (ctx.getChildCount() == 2){
      return new AArithmeticExp(
              (AbstractExpression)this.visit(ctx.getChild(1)), null, null, true);
    }
    // LPAR arithmetic RPAR
    else{
      // we write a lot of nice parenthesis anyways
      return this.visit(ctx.getChild(1));
    }
  }

  @Override
  public AbstractTree visitArithmetic_operator(RobotGrammarParser.Arithmetic_operatorContext ctx) {
    // we should never get here since operators are directly used as Strings in arithmetics
    throw new UnsupportedOperationException("This method shouldn't be used");
  }

  @Override
  public AbstractTree visitArithmetic_lin_operator(RobotGrammarParser.Arithmetic_lin_operatorContext ctx) {
    // we should never get here since operators are directly used as Strings in arithmetics
    throw new UnsupportedOperationException("This method shouldn't be used");
  }

  @Override
  public AbstractTree visitArithmetic_dot_operator(RobotGrammarParser.Arithmetic_dot_operatorContext ctx) {
    // we should never get here since operators are directly used as Strings in arithmetics
    throw new UnsupportedOperationException("This method shouldn't be used");
  }

  @Override
  public AbstractTree visitNumber(RobotGrammarParser.NumberContext ctx) {
    return new ANumber(ctx.getChild(0).getText());
  }

  @Override
  public AbstractTree visitWhile_statement(RobotGrammarParser.While_statementContext ctx) {
    // WHILE LPAR boolean_exp RPAR loop_statement_block
    if(ctx.getChildCount() == 5){
      return new AWhileStat((ABooleanExp) this.visit(ctx.getChild(2)),
              (AbstractBlock) this.visit(ctx.getChild(4)));
    }
    // DO loop_statement_block WHILE LPAR boolean_exp RPAR
    else{
      return new ADoWhileStat((ABooleanExp) this.visit(ctx.getChild(4)),
              (AbstractBlock) this.visit(ctx.getChild(1)));
    }
  }

  @Override
  public AbstractTree visitExp(RobotGrammarParser.ExpContext ctx) {
    // comment exp comment
    if(ctx.getChildCount() == 4){ // exp of kind comment NOT boolean_exp comment
      return new AnAbstractExp((ACommentBlock)this.visit(ctx.getChild(0)),
              new ABooleanExp((AbstractExpression)this.visit(ctx.getChild(2)), null, null, true),
              (ACommentBlock)this.visit(ctx.getChild(3)));
    }
    if(ctx.getChildCount() == 5){ // exp of kind comment LPAR exp RPAR comment
    return new AnAbstractExp((ACommentBlock)this.visit(ctx.getChild(0)),
            (AbstractTree)this.visit(ctx.getChild(2)),
            (ACommentBlock)this.visit(ctx.getChild(4)));
    }
    return new AnAbstractExp((ACommentBlock)this.visit(ctx.getChild(0)),
            (AbstractTree)this.visit(ctx.getChild(1)),
            (ACommentBlock)this.visit(ctx.getChild(2)));
  }

  @Override
  public AbstractTree visitLiteral_or_graph_exp(RobotGrammarParser.Literal_or_graph_expContext ctx) {
    // LITERAL_OR_GRAPH LPAR ( exp (COMMA exp)*)? RPAR
    ArrayList<AbstractExpression> expList = new ArrayList<AbstractExpression> ();
    for (int i = 2; i < ctx.getChildCount() - 1;){
      expList.add((AbstractExpression)this.visit(ctx.getChild(i)));
      i += 2;   // because we aren't interested in commas
    }
    return new ALiteralOrGraphExp(ctx.getChild(0).getText(), expList);
  }

  @Override
  public AbstractTree visitSimple_b_exp(RobotGrammarParser.Simple_b_expContext ctx) {
    // comment exp comment
    return new AnAbstractExp((ACommentBlock)this.visit(ctx.getChild(0)),
            (AbstractTree)this.visit(ctx.getChild(1)),
            (ACommentBlock)this.visit(ctx.getChild(2)));
  }

  @Override
  public AbstractTree visitBoolean_exp(RobotGrammarParser.Boolean_expContext ctx) {
    ABooleanExp arit = new ABooleanExp(
            (AbstractExpression)this.visit(ctx.getChild(ctx.getChildCount() - 1)),
            null, null, false);
    for (int i = ctx.getChildCount() - 2; i >= 0; i--){
      if(i % 2 == 1){
        arit = new ABooleanExp(
                new ABooleanExp(
                        (AbstractExpression)this.visit(ctx.getChild(i--)),
                              null, null, false),
                        arit, ctx.getChild(i).getText(), false);
      }
    }
    return arit;
  }

  @Override
  public AbstractTree visitBoolean_op(RobotGrammarParser.Boolean_opContext ctx) {
    // opereators are directly passed in boolean_exp
    throw new UnsupportedOperationException("This method shouldn't be used");
  }

  @Override
  public AbstractTree visitAssignment(RobotGrammarParser.AssignmentContext ctx) {
    // TODO: save variable in memory!!!!!!!!!!!!
    if(ctx.getChildCount() == 3){ // no declaration
      return new AAssignment(this.visit(ctx.getChild(0)), 
              (AbstractExpression)this.visit(ctx.getChild(2)), false);
    }
    else {  // declaration
      return new AAssignment(this.visit(ctx.getChild(0)), 
              (AbstractExpression)this.visit(ctx.getChild(2)), true);
    }
  }

  @Override
  public AbstractTree visitPropose_arg(RobotGrammarParser.Propose_argContext ctx) {
    return this.visit(ctx.getChild(0));
  }

  @Override
  public AbstractTree visitString_expression(RobotGrammarParser.String_expressionContext ctx) {
    // TODO: stimmt das??
    AArithmeticExp arit = new AArithmeticExp(
            (AbstractExpression)this.visit(ctx.getChild(ctx.getChildCount() - 1)),
            null, null, false);
    for (int i = ctx.getChildCount() - 2; i >= 0; i--){
      if(i % 2 == 1){
        arit = new AArithmeticExp(
                new AArithmeticExp(
                        (AbstractExpression)this.visit(ctx.getChild(i--)),
                              null, null, false),
                        arit, ctx.getChild(i).getText(), false);
      }
    }
    return arit;
  }

  @Override
  public AbstractTree visitPropose_statement(RobotGrammarParser.Propose_statementContext ctx) {
    // PROPOSE LPAR propose_arg RPAR statement_block
    return new AProposeStat((AbstractExpression)this.visit(ctx.getChild(2)),
            (AbstractBlock)this.visit(ctx.getChild(4)));
  }

  @Override
  public AbstractTree visitLoop_propose_statement(RobotGrammarParser.Loop_propose_statementContext ctx) {
    // PROPOSE LPAR propose_arg RPAR statement_block
    return new AProposeStat((AbstractExpression)this.visit(ctx.getChild(2)),
            (AbstractBlock)this.visit(ctx.getChild(4)));
  }

  @Override
  public AbstractTree visitIf_statement(RobotGrammarParser.If_statementContext ctx) {
    // IF LPAR boolean_exp RPAR statement (ELSE statement)?
    if(ctx.getChildCount() == 5){   // no else
      return new AIfStatement((ABooleanExp)this.visit(ctx.getChild(2)),
              (AbstractBlock) this.visit(ctx.getChild(4)), null);
    }
    // if there is an else
    return new AIfStatement((ABooleanExp)this.visit(ctx.getChild(2)),
            (AbstractBlock) this.visit(ctx.getChild(4)),
            (AbstractBlock) this.visit(ctx.getChild(6)));
  }

  @Override
  public AbstractTree visitLoop_if_statement(RobotGrammarParser.Loop_if_statementContext ctx) {
    // IF LPAR boolean_exp RPAR statement (ELSE statement)?
    if(ctx.getChildCount() == 5){   // no else
      return new AIfStatement((ABooleanExp)this.visit(ctx.getChild(2)),
              (AbstractBlock) this.visit(ctx.getChild(4)), null);
    }
    // if there is an else
    return new AIfStatement((ABooleanExp)this.visit(ctx.getChild(2)),
            (AbstractBlock) this.visit(ctx.getChild(4)),
            (AbstractBlock) this.visit(ctx.getChild(6)));
  }

  @Override
  public AbstractTree visitFor_statement(RobotGrammarParser.For_statementContext ctx) {
    if (ctx.getChildCount() == 7){
      // statement looks like "FOR LPAR VARIABLE COLON exp RPAR loop_statement_block"
      // TODO: or should we check here that the type of the variable in assignment
      // is the type the iterable in exp returns? How?
      return new AFor2Stat(new ALocalVar(ctx.getChild(2).getText()), this.visit(ctx.getChild(4)),
              (AbstractBlock)this.visit(ctx.getChild(6)));
    }
    else if(ctx.getChildCount() == 8){
      // statement looks like "FOR LPAR assignment SEMICOLON exp SEMICOLON RPAR loop_statement_block"
      return new AFor1Stat((AAssignment)this.visit(ctx.getChild(2)),
              (ABooleanExp)this.visit(ctx.getChild(4)),
              null, (AbstractBlock)this.visit(ctx.getChild(7)));
    }
    else if(ctx.getChildCount() == 9){
      // statement looks like "FOR LPAR assignment SEMICOLON exp SEMICOLON exp RPAR loop_statement_block"
      return new AFor1Stat((AAssignment)this.visit(ctx.getChild(2)),
              (ABooleanExp)this.visit(ctx.getChild(4)),
              (AbstractExpression)this.visit(ctx.getChild(6)),
              (AbstractBlock)this.visit(ctx.getChild(8)));
    }
    else{
      // statement looks like "FOR LPAR LPAR VARIABLE ( COMMA VARIABLE )+ RPAR COLON exp RPAR loop_statement_block"
      // TODO: implement For3Stat; exp will return some Object[]
      return new AFor3Stat();
    }
  }

  @Override
  public AbstractTree visitField_access(RobotGrammarParser.Field_accessContext ctx) {
    return new AFieldAccess(context.getFieldAccessType(ctx.getText()), ctx.getText());
  }

  @Override
  public AbstractTree visitField_access_vfunc(RobotGrammarParser.Field_access_vfuncContext ctx) {
    return new AFieldAccess(context.getFieldAccessType(ctx.getText()), ctx.getText());
  }

  @Override
  public AbstractTree visitLambda_exp(RobotGrammarParser.Lambda_expContext ctx) {
    return new ALambdaExp(ctx.getText());
  }

  @Override
  public AbstractTree visitComment(RobotGrammarParser.CommentContext ctx) {
    ArrayList<AComment> comments = new ArrayList<AComment>();
    for(int i = 0; i < ctx.getChildCount(); i++){
      comments.add(new AComment(ctx.getText()));
    }
    return new ACommentBlock(comments);
  }

  @Override
  public AbstractTree visit(ParseTree pt) {
    return pt.accept(this);
  }

  @Override
  public AbstractTree visitChildren(RuleNode rn) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public AbstractTree visitTerminal(TerminalNode tn) {
    switch(tn.getSymbol().getType()){
      case 9:   // token is TRUE
        return new AUnaryBoolean(tn.getText());
      case 10:  // token is FALSE
        return new AUnaryBoolean(tn.getText());
      case 11:  // token is character
        return new ACharacter(tn.getText());
      case 12:  // token is String
        return new AString(tn.getText());
      case 27:  // token is <=
        // TODO: how to determine whether this is normal boolean or magic?
      case 40:  //token is wildcard
        return new AWildcard();
      case 47:  // token is variable
        if (memory.containsKey(tn.getText())){
          return new ALocalVar(memory.get(tn.getText()), tn.getText());
        }
        else if (context.isGlobalVariable(tn.getText())){
          return new AGlobalVar(context.getVariableType(tn.getText()), tn.getText());
        }
        else{
          // TODO: find a nice exception
          throw new UnsupportedOperationException("This variable isn't declared "
                  + "anywhere: " + tn.getText());
        }
      case 48:  // token is int
        return new ANumber(tn.getText());
      case 49:  // token is float
        return new ANumber(tn.getText());
    }
    return null;
  }

  @Override
  public AbstractTree visitErrorNode(ErrorNode en) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }
  
}
