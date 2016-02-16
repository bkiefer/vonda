/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grammar;

import de.dfki.mlt.rudimant.io.RobotGrammarParser;
import de.dfki.mlt.rudimant.io.RobotGrammarVisitor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

/**
 * this visitor should contain copies of all methods of RGVisitor that are modified
 * so they do not write anything
 * 
 * @author anna
 */
public class RGVisitorNotWrite implements RobotGrammarVisitor<Type>{
  private RGVisitor parent;
  private RobotContext context;
  
  public RGVisitorNotWrite(RGVisitor parent, RobotContext context){
    this.parent = parent;
    this.context = context;
  }

  /**
   * method only for test purposes, to close writer at a breakpoint if there is no child i
   * @param ctx
   * @param i 
   */
  private void testClose(ParseTree ctx, int i){
    if(this.visit(ctx.getChild(i)) == null){
      try {
        parent.writer.close();
      } catch (IOException ex) {
        Logger.getLogger(RGVisitorNotWrite.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }

  @Override
  public Type visitGrammar_file(RobotGrammarParser.Grammar_fileContext ctx) {
    return null;
  }

  @Override
  public Type visitGrammar_rule(RobotGrammarParser.Grammar_ruleContext ctx) {
    return null;
  }

  @Override
  public Type visitLabel(RobotGrammarParser.LabelContext ctx) {
    return null;
  }
  
  @Override
  public Type visitStatement_block(RobotGrammarParser.Statement_blockContext ctx) {
    return null;
  }

  @Override
  public Type visitStatement(RobotGrammarParser.StatementContext ctx) {
    return null;
  }

  @Override
  public Type visitFunction_call(RobotGrammarParser.Function_callContext ctx) {
    ArrayList<Type> types = new ArrayList<Type>();
    // LPAR is second child
    for(int i = 2; i < ctx.getChildCount(); i++){
      types.add(this.visit(ctx.getChild(i)));
    }
    assert(context.testFunctionArguments(types));
    visitChildren(ctx);
    return context.getFunctionReturn(ctx.getChild(0).getText());
  }

  @Override
  public Type visitArithmetic(RobotGrammarParser.ArithmeticContext ctx) {
    // Attention: concatenation of two Strings is parsed as arithmetic operation!
    boolean minus = false;
    boolean number = false;
    boolean string = false;
    for (int i = 0; i < ctx.getChildCount(); i++){
      this.testClose(ctx, i);
      Type type = this.visit(ctx.getChild(i));
      if (ctx.getChild(i).getText().equals("-")){
        minus = true;
      }
      else if (type.equals(Type.STRING)){
        string = true;
      }
      else if (type.equals(Type.FLOAT)){
        number = true;
      }
      else if (type.equals(Type.INT)){
        number = true;
      }
    }
    assert(!((minus && string) || (string && number)));
    if(string){
      return Type.STRING;
    }
    return Type.FLOAT;
  }

  @Override
  public Type visitTerm(RobotGrammarParser.TermContext ctx) {
    for (int i = 0; i < ctx.getChildCount(); i++){
      if(!(ctx.getChild(i).getText().equals("*") || ctx.getChild(i).getText().equals("/"))){
        if(ctx.getChildCount() > 1){
          this.testClose(ctx, i);
          assert(this.visit(ctx.getChild(i)).equals(Type.INT)
                  || this.visit(ctx.getChild(i)).equals(Type.FLOAT));
        }
      }
    }
    return Type.FLOAT;
  }

  @Override
  public Type visitFactor(RobotGrammarParser.FactorContext ctx) {
    visitChildren(ctx);
    return null;
  }

  @Override
  public Type visitArithmetic_operator(RobotGrammarParser.Arithmetic_operatorContext ctx) {
    return null;
  }

  @Override
  public Type visitArithmetic_lin_operator(RobotGrammarParser.Arithmetic_lin_operatorContext ctx) {
    return null;
  }

  @Override
  public Type visitArithmetic_dot_operator(RobotGrammarParser.Arithmetic_dot_operatorContext ctx) {
    return null;
  }

  @Override
  public Type visitNumber(RobotGrammarParser.NumberContext ctx) {
    Type type;
    if(ctx.getChildCount() == 2){ // there is a ++ or --
      type = this.visit(ctx.getChild(1));
    }
    else{
      type = this.visit(ctx.getChild(0));
    }
    //assert(type.equals(Type.FLOAT) || type.equals(Type.INT));
    return type;
  }

  @Override
  public Type visitExp(RobotGrammarParser.ExpContext ctx) {
    visitChildren(ctx);
    if(ctx.getChildCount() > 1){
      return this.visit(ctx.getChild(1));
    }
    return this.visit(ctx.getChild(0));
  }

  @Override
  public Type visitBoolean_exp(RobotGrammarParser.Boolean_expContext ctx) {
    if(ctx.getChildCount() == 1){ // it could be anything
      return this.visit(ctx.getChild(0));
    }
    else{ // there is a boolean op -> it really is a boolean expression
      // TODO: can we do "blabla" < 3? "blabla" < "haha"?
      return Type.BOOL;
    }
  }

  @Override
  public Type visitSimple_b_exp(RobotGrammarParser.Simple_b_expContext ctx) {
    visitChildren(ctx);
    if(ctx.getChildCount() > 1){
      return this.visit(ctx.getChild(1));
    }
    return this.visit(ctx.getChild(0));
  }

  @Override
  public Type visitBoolean_op(RobotGrammarParser.Boolean_opContext ctx) {
    return null;
  }

  @Override
  public Type visitAssignment(RobotGrammarParser.AssignmentContext ctx) {
    if(ctx.getChildCount() == 3){ // form is x = 5
      Type left = visit(ctx.getChild(0));
      visit(ctx.getChild(1)); // necessary? should do nothing more than print "="
      Type right = visit(ctx.getChild(2));
      assert(left.equals(right));
      return right;
    }
    else{ // form is var x = 5
      Type type = this.visit(ctx.getChild(3));
      if(parent.checkMemory(ctx.getChild(1).getText()) != null){
        if(parent.checkMemory(ctx.getChild(1).getText()).equals(type)){
            // TODO: define a nice exception
            throw new UnsupportedOperationException("Variable " + ctx.getChild(1).getText() + " is already defined");
        }
      }
      this.visitChildren(ctx);
      return type;
    }
  }

  @Override
  public Type visitPropose_statement(RobotGrammarParser.Propose_statementContext ctx) {
    return null;
  }

  @Override
  public Type visitLoop_propose_statement(RobotGrammarParser.Loop_propose_statementContext ctx) {
    return null;
  }

  @Override
  public Type visitPropose_arg(RobotGrammarParser.Propose_argContext ctx) {
    visitChildren(ctx);
    return null;
  }

  @Override
  public Type visitPropose_block(RobotGrammarParser.Propose_blockContext ctx) {
    return null;
  }

  @Override
  public Type visitLoop_propose_block(RobotGrammarParser.Loop_propose_blockContext ctx) {
    return null;
  }

  @Override
  public Type visitString_expression(RobotGrammarParser.String_expressionContext ctx) {
    for(int i = 0; i < ctx.getChildCount(); i++){
      if(ctx.getChild(i).getText().equals("+")){
        continue;
      }
      assert(this.visit(ctx.getChild(i)).equals(Type.STRING));
    }
    return Type.STRING;
  }

  @Override
  public Type visitLiteral_or_graph_exp(RobotGrammarParser.Literal_or_graph_expContext ctx) {
    return null;
  }

  @Override
  public Type visitIf_statement(RobotGrammarParser.If_statementContext ctx) {
    return null;
  }

  @Override
  public Type visitLoop_if_statement(RobotGrammarParser.Loop_if_statementContext ctx) {
    return null;
  }

  @Override
  public Type visitWhile_statement(RobotGrammarParser.While_statementContext ctx) {
    return null;
  }

  @Override
  public Type visitFor_statement(RobotGrammarParser.For_statementContext ctx) {
    return null;
  }

  @Override
  public Type visitLoop_statement(RobotGrammarParser.Loop_statementContext ctx) {
    return null;
  }

  @Override
  public Type visitLoop_statement_block(RobotGrammarParser.Loop_statement_blockContext ctx) {
    return null;
  }

  @Override
  public Type visitField_access(RobotGrammarParser.Field_accessContext ctx) {
    assert(context.existsFieldAccess(ctx.getText())): "I don't know this field: " + ctx.getText();
    return context.getFieldType(ctx.getText());
  }

  @Override
  public Type visitField_access_vfunc(RobotGrammarParser.Field_access_vfuncContext ctx) {
    assert(context.existsFieldAccess(ctx.getText())): "I don't know this field: " + ctx.getText();
    return context.getFieldType(ctx.getText());
  }

  @Override
  public Type visitLambda_exp(RobotGrammarParser.Lambda_expContext ctx) {
    visitChildren(ctx);
    // TODO: should we do anything else here? How?
    // TODO: do we need to find out what type a lambda expression has? (do they have a type)
    return null;
  }

  @Override
  public Type visitComment(RobotGrammarParser.CommentContext ctx) {
    return null;
  }

  @Override
  public Type visit(ParseTree pt) {
    return pt.accept(this);
  }
  
  /**
   * Don't use this method in a rule where you want to do type checking! (you
   * won't get back anything useful)
   * @param rn
   * @return nothing
   */
  @Override
  public Type visitChildren(RuleNode rn) {
      // TODO: starting count at 0 or 1??
    for(int n = 0; n < rn.getChildCount(); n++){
        this.visit(rn.getChild(n));
    }
    return null;
  }
  
  @Override
  public Type visitTerminal(TerminalNode tn) {
    if(tn.getSymbol().getType() == 46){ // token is whitespace
      return null;
    }
    switch(tn.getSymbol().getType()){
      case 9:   // token is TRUE
        return Type.BOOL;
      case 10:  // token is FALSE
        return Type.BOOL;
      case 11:  // token is character
        return Type.CHAR;
      case 12:  // token is String
        return Type.STRING;
      case 41:  // token is literal_or_graph
        // ????????????????
      case 47:  // token is variable
        if(!(parent.checkMemory(tn.getText()) == null)){
          return parent.checkMemory(tn.getText());
        }
        else{
          return context.getFieldType(tn.getText());
        }
      case 48:  // token is int
        return Type.INT;
      case 49:  // token is float
        return Type.FLOAT;
    }
    return null;
  }

  @Override
  public Type visitErrorNode(ErrorNode en) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }
  
}
