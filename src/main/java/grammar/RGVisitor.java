/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grammar;

import de.dfki.mlt.rudimant.io.RobotGrammarParser;
import de.dfki.mlt.rudimant.io.RobotGrammarVisitor;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
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
public class RGVisitor implements RobotGrammarVisitor<Type> {
    
  private String indent;
  
  // this memory will know the types of variables
  private HashMap<String, Type> memory;
  
  // the output stream to write to
  private Writer writer;
  
  // a Visitor that only implements part of RobotGrammarVisitor (everything that
  // could possibly be a child of exp) and will be used for typechecking
  private RGVisitorNotWrite typefinder;
  
  // the object that nows about context that we cannot see
  private RobotContext context;
    
  private boolean writeToFile(String text){
    try {
      writer.write(indent + text);
    } catch (IOException ex) {
      Logger.getLogger(RGVisitor.class.getName()).log(Level.SEVERE, null, ex);
    }
    return true;
  }
  
  public RGVisitor(Writer writer, RobotContext context){
    this.writer = writer;
    this.indent = "";
    this.memory = new HashMap<String, Type>();
    this.typefinder = new RGVisitorNotWrite(this);
    this.context = context;
  }
  
  /**
   * this method allows other classes to look into type memory for type checking
   * @param toCheck
   * @return type of toCheck or null if not in memory
   */
  public Type checkMemory(String toCheck){
    if(memory.containsKey(toCheck))
      return memory.get(toCheck);
    return null;
  }

  @Override
  public Type visitStatement_block(RobotGrammarParser.Statement_blockContext ctx) {
    visitChildren(ctx);
    return null;
  }

  @Override
  public Type visitGrammar_file(RobotGrammarParser.Grammar_fileContext ctx) {
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
  public Type visitLoop_propose_statement(RobotGrammarParser.Loop_propose_statementContext ctx) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Type visitFunction_call(RobotGrammarParser.Function_callContext ctx) {
    ArrayList<Type> types = new ArrayList<Type>();
    // LPAR is second child
    for(int i = 2; i < ctx.getChildCount(); ++i){
      types.add(typefinder.visit(ctx.getChild(i)));
    }
    assert(context.testFunctionArguments(types));
    return context.getFuctionReturn(ctx.getChild(1).getText());
  }

  @Override
  public Type visitGrammar_rule(RobotGrammarParser.Grammar_ruleContext ctx) {
    writeToFile("\n\n");
    visitChildren(ctx);
    return null;
  }

  @Override
  public Type visitArithmetic_operator(RobotGrammarParser.Arithmetic_operatorContext ctx) {
    visitChildren(ctx);
    return null;
  }

  @Override
  public Type visitArithmetic(RobotGrammarParser.ArithmeticContext ctx) {
    // Attention: concatenation of two Strings is parsed as arithmetic operation!
    boolean minus = false;
    boolean number = false;
    boolean string = false;
    for (int i = 0; i <= ctx.getChildCount(); ++i){
      Type type = typefinder.visit(ctx.getChild(i));
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
  public Type visitNumber(RobotGrammarParser.NumberContext ctx) {
    Type type;
    if(ctx.getChildCount() == 2){ // there is a ++ or --
      type = typefinder.visit(ctx.getChild(2));
    }
    else{
      type = typefinder.visit(ctx.getChild(1));
    }
    //assert(type.equals(Type.FLOAT) || type.equals(Type.INT));
    return type;
  }

  @Override
  public Type visitStatement(RobotGrammarParser.StatementContext ctx) {
    visitChildren(ctx);
    return null;
  }

  @Override
  public Type visitPropose_block(RobotGrammarParser.Propose_blockContext ctx) {
    visitChildren(ctx);
    return null;
  }

  @Override
  public Type visitTerm(RobotGrammarParser.TermContext ctx) {
    for (int i = 0; i < ctx.getChildCount(); ++i){
      if(!(ctx.getChild(i).getText().equals("*") || ctx.getChild(i).getText().equals("/"))){
        assert(typefinder.visit(ctx.getChild(i)).equals(Type.FLOAT)
                || typefinder.visit(ctx.getChild(i)).equals(Type.INT));
      }
    }
    return Type.FLOAT;
  }

  @Override
  public Type visitWhile_statement(RobotGrammarParser.While_statementContext ctx) {
    visitChildren(ctx);
    return null;
  }

  @Override
  public Type visitArithmetic_lin_operator(RobotGrammarParser.Arithmetic_lin_operatorContext ctx) {
    visitChildren(ctx);
    return null;
  }

  @Override
  public Type visitExp(RobotGrammarParser.ExpContext ctx) {
    visitChildren(ctx);
    if(ctx.getChildCount() > 1){
      return typefinder.visit(ctx.getChild(2));
    }
    return typefinder.visit(ctx.getChild(1));
  }

  @Override
  public Type visitFactor(RobotGrammarParser.FactorContext ctx) {
    visitChildren(ctx);
    return null;
  }

  @Override
  public Type visitLiteral_or_graph_exp(RobotGrammarParser.Literal_or_graph_expContext ctx) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Type visitSimple_b_exp(RobotGrammarParser.Simple_b_expContext ctx) {
    visitChildren(ctx);
    if(ctx.getChildCount() > 1){
      return typefinder.visit(ctx.getChild(2));
    }
    return typefinder.visit(ctx.getChild(1));
  }

  @Override
  public Type visitBoolean_exp(RobotGrammarParser.Boolean_expContext ctx) {
    if(ctx.getChildCount() == 1){ // it could be anything
      return typefinder.visit(ctx.getChild(1));
    }
    else{ // there is a boolean op -> it really is a boolean expression
      // TODO: can we do "blabla" < 3? "blabla" < "haha"?
      return Type.BOOL;
    }
  }

  @Override
  public Type visitLoop_propose_block(RobotGrammarParser.Loop_propose_blockContext ctx) {
    visitChildren(ctx);
    return null;
  }

  @Override
  public Type visitAssignment(RobotGrammarParser.AssignmentContext ctx) {
    if(ctx.getChildCount() == 3){ // form is x = 5
      Type left = visit(ctx.getChild(1));
      visit(ctx.getChild(2)); // necessary? should do nothing more than print "="
      Type right = visit(ctx.getChild(3));
      assert(left.equals(right));
      return right;
    }
    else{ // form is var x = 5
      Type type = typefinder.visit(ctx.getChild(4));
      if(memory.containsKey(ctx.getChild(2).getText())){
        if((memory.get(ctx.getChild(2).getText())).equals(type)){
          // TODO: define a nice exception
          throw new UnsupportedOperationException("Variable " + ctx.getChild(2).getText() + " is already defined");
        }
      }
      writeToFile(type + " ");
      this.visitChildren(ctx);
      return type;
    }
  }

  @Override
  public Type visitBoolean_op(RobotGrammarParser.Boolean_opContext ctx) {
    visitChildren(ctx);
    return null;
  }

  @Override
  public Type visitPropose_arg(RobotGrammarParser.Propose_argContext ctx) {
    visitChildren(ctx);
    return null;
  }

  @Override
  public Type visitLabel(RobotGrammarParser.LabelContext ctx) {
    // label looks like VARIABLE COLON
    String label = ctx.getChild(1).getText();
    writeToFile("execute(\"" + label + "\");\n");
    visitChildren(ctx);
    return null;
  }

  @Override
  public Type visitArithmetic_dot_operator(RobotGrammarParser.Arithmetic_dot_operatorContext ctx) {
    visitChildren(ctx);
    return null;
  }

  @Override
  public Type visitPropose_statement(RobotGrammarParser.Propose_statementContext ctx) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Type visitLoop_if_statement(RobotGrammarParser.Loop_if_statementContext ctx) {
    visitChildren(ctx);
    return null;
  }

  @Override
  public Type visitLambda_exp(RobotGrammarParser.Lambda_expContext ctx) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Type visitFor_statement(RobotGrammarParser.For_statementContext ctx) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Type visitString_expression(RobotGrammarParser.String_expressionContext ctx) {
    for(int i = 0; i < ctx.getChildCount(); ++i){
      if(ctx.getChild(i).getText().equals("+")){
        continue;
      }
      assert(typefinder.visit(ctx.getChild(i)).equals(Type.STRING));
    }
    return Type.STRING;
  }

  @Override
  public Type visitField_access(RobotGrammarParser.Field_accessContext ctx) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Type visitLoop_statement_block(RobotGrammarParser.Loop_statement_blockContext ctx) {
    visitChildren(ctx);
    return null;
  }

  @Override
  public Type visitField_access_vfunc(RobotGrammarParser.Field_access_vfuncContext ctx) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Type visitIf_statement(RobotGrammarParser.If_statementContext ctx) {
    visitChildren(ctx);
    return null;
  }

  @Override
  public Type visitLoop_statement(RobotGrammarParser.Loop_statementContext ctx) {
    visitChildren(ctx);
    return null;
  }

  @Override
  public Type visitComment(RobotGrammarParser.CommentContext ctx) {
    visitChildren(ctx);
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
    for(int n = 0; n < rn.getChildCount(); ++n){
        this.visit(rn.getChild(n));
    }
    return null;
  }

  @Override
  public Type visitTerminal(TerminalNode tn) {
    if(tn.getSymbol().getType() == 46 || tn.getSymbol().getType() == 43){
      // token is whitespace or DEC_VAR
      return null;
    }
    writeToFile(tn.getSymbol().getText() + " ");
    switch(tn.getSymbol().getType()){
      case 9:   // token is TRUE
        return Type.BOOL;
      case 10:  // token is FALSE
        return Type.BOOL;
      case 11:  // token is character
        return Type.CHAR;
      case 12:  // token is String
        return Type.STRING;
      case 17:  //  token is LBRACE -> block indented
        writeToFile("\n");
        indent += "  ";
        break;
      case 18:  //  token is RBRACE -> resize indentation
        writeToFile("\n");
        indent = indent.substring(0, indent.length() - 3);
        break;
      case 19:  // token is semicolon
        writeToFile("\n");
        break;
      case 40:  //token is wildcard
        // ?????????????????
      case 41:  // token is literal_or_graph
        // ????????????????
      case 47:  // token is variable
        if(memory.containsKey(tn.getText())){
          return memory.get(tn.getText());
        }
        else{
          break;
        }
      case 48:  // token is int
        return Type.INT;
      case 49:  // token is float
        return Type.FLOAT;
    }
    return null;
  }

  // TODO: what to do?
  @Override
  public Type visitErrorNode(ErrorNode en) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

}
