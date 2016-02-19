/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Versuch1;

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
  public Writer writer;
  
  // a Visitor that only implements part of RobotGrammarVisitor (everything that
  // could possibly be a child of exp) and will be used for typechecking
  private RGVisitorNotWrite typefinder;
  
  // the object that nows about context that we cannot see
  private RobotContext context;
  
  
  /**
   * method only for test purposes, to close writer at a breakpoint if there is no child i
   * @param ctx
   * @param i 
   */
  private void testClose(ParseTree ctx, int i){
    if(this.visit(ctx.getChild(i)) == null){
      try {
        writer.close();
      } catch (IOException ex) {
        Logger.getLogger(RGVisitor.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }
  
  /**
   * method that will print given text to the output file
   * @param text
   * @return true if successfull
   */
  private boolean writeToFile(String text){
    try {
      writer.write(text);
    } catch (IOException ex) {
      Logger.getLogger(RGVisitor.class.getName()).log(Level.SEVERE, null, ex);
    }
    return true;
  }
  
  public RGVisitor(Writer writer, RobotContext context){
    this.writer = writer;
    this.indent = "    ";
    this.memory = new HashMap<String, Type>();
    this.typefinder = new RGVisitorNotWrite(this, context);
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
  public Type visitGrammar_file(RobotGrammarParser.Grammar_fileContext ctx) {
    this.visitChildren(ctx);
    return null;
  }

  @Override
  public Type visitGrammar_rule(RobotGrammarParser.Grammar_ruleContext ctx) {
    writeToFile("\n\n");
    visit(ctx.getChild(0));  // this is the label
    writeToFile(indent);
    visit(ctx.getChild(1)); // this is the if
    return null;
  }

  @Override
  public Type visitLabel(RobotGrammarParser.LabelContext ctx) {
    // label looks like VARIABLE COLON
    String label = ctx.getChild(0).getText();
    writeToFile(indent + "execute(\"" + label + "\");\n");
    return null;
  }

  @Override
  public Type visitStatement_block(RobotGrammarParser.Statement_blockContext ctx) {
    visitChildren(ctx);
    return null;
  }

  @Override
  public Type visitStatement(RobotGrammarParser.StatementContext ctx) {
    writeToFile(indent);
    visitChildren(ctx);
    return null;
  }

  @Override
  public Type visitFunction_call(RobotGrammarParser.Function_callContext ctx) {
    ArrayList<Type> types = new ArrayList<Type>();
    // LPAR is second child
    for(int i = 2; i < ctx.getChildCount(); i++){
      types.add(typefinder.visit(ctx.getChild(i)));
    }
    assert(context.testFunctionArguments(types));
    visitChildren(ctx);
    return context.getFunctionReturn(ctx.getChild(1).getText());
  }

  @Override
  public Type visitArithmetic(RobotGrammarParser.ArithmeticContext ctx) {
    // Attention: concatenation of two Strings is parsed as arithmetic operation!
    boolean minus = false;
    boolean number = false;
    boolean string = false;
    for (int i = 0; i < ctx.getChildCount(); i++){
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
    visitChildren(ctx);
    if(string){
      return Type.STRING;
    }
    return Type.FLOAT;
  }

  @Override
  public Type visitTerm(RobotGrammarParser.TermContext ctx) {
    for (int i = 0; i < ctx.getChildCount(); i++){
      if(ctx.getChildCount() > 1){
        if(!(ctx.getChild(i).getText().equals("*") || ctx.getChild(i).getText().equals("/"))){
          assert(typefinder.visit(ctx.getChild(i)).equals(Type.FLOAT)
                  || typefinder.visit(ctx.getChild(i)).equals(Type.INT));
        }
      }
    }
    visitChildren(ctx);
    return Type.FLOAT;
  }

  @Override
  public Type visitFactor(RobotGrammarParser.FactorContext ctx) {
    visitChildren(ctx);
    return null;
  }

  @Override
  public Type visitArithmetic_operator(RobotGrammarParser.Arithmetic_operatorContext ctx) {
    visitChildren(ctx);
    return null;
  }

  @Override
  public Type visitArithmetic_lin_operator(RobotGrammarParser.Arithmetic_lin_operatorContext ctx) {
    visitChildren(ctx);
    return null;
  }

  @Override
  public Type visitArithmetic_dot_operator(RobotGrammarParser.Arithmetic_dot_operatorContext ctx) {
    visitChildren(ctx);
    return null;
  }

  @Override
  public Type visitNumber(RobotGrammarParser.NumberContext ctx) {
    Type type;
    if(ctx.getChildCount() == 2){ // there is a ++ or --
      type = typefinder.visit(ctx.getChild(1));
    }
    else{
      type = typefinder.visit(ctx.getChild(0));
    }
    //assert(type.equals(Type.FLOAT) || type.equals(Type.INT));
    visitChildren(ctx);
    return type;
  }

  @Override
  public Type visitExp(RobotGrammarParser.ExpContext ctx) {
    visitChildren(ctx);
    if(ctx.getChildCount() > 1){
      return typefinder.visit(ctx.getChild(1));
    }
    return typefinder.visit(ctx.getChild(0));
  }

  @Override
  public Type visitBoolean_exp(RobotGrammarParser.Boolean_expContext ctx) {
    if(ctx.getChildCount() == 1){ // it could be anything
      visitChildren(ctx);
      return typefinder.visit(ctx.getChild(0));
    }
    else{ // there is a boolean op -> it really is a boolean expression
      // TODO: can we do "blabla" < 3? "blabla" < "haha"?
      visitChildren(ctx);
      return Type.BOOL;
    }
  }

  @Override
  public Type visitSimple_b_exp(RobotGrammarParser.Simple_b_expContext ctx) {
    visitChildren(ctx);
    if(ctx.getChildCount() > 1){
      return typefinder.visit(ctx.getChild(1));
    }
    return typefinder.visit(ctx.getChild(0));
  }

  @Override
  public Type visitBoolean_op(RobotGrammarParser.Boolean_opContext ctx) {
    visitChildren(ctx);
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
      Type type = typefinder.visit(ctx.getChild(3));
      if(memory.containsKey(ctx.getChild(1).getText())){
        if((memory.get(ctx.getChild(1).getText())).equals(type)){
          // TODO: define a nice exception
          throw new UnsupportedOperationException("Variable " + ctx.getChild(1).getText() + " is already defined");
        }
      }
      writeToFile(type + " ");
      this.visitChildren(ctx);
      return type;
    }
  }

  @Override
  public Type visitPropose_statement(RobotGrammarParser.Propose_statementContext ctx) {
    // looks like PROPOSE LPAR propose_arg RPAR loop_propose_block
    writeToFile(indent + "propose(");
    visit(ctx.getChild(2));
    writeToFile(", new Proposal() {\n" + indent + "    public void run()\n");
    indent += "      ";
    visit(ctx.getChild(4));
    indent = indent.substring(0, indent.length() - 7);
    writeToFile(indent + "});\n");
    return null;
  }

  @Override
  public Type visitLoop_propose_statement(RobotGrammarParser.Loop_propose_statementContext ctx) {
    // looks like PROPOSE LPAR propose_arg RPAR loop_propose_block
    writeToFile(indent + "propose(");
    visit(ctx.getChild(2));
    writeToFile(", new Proposal() {\n" + indent + "    public void run()\n");
    indent += "      ";
    visit(ctx.getChild(4));
    indent = indent.substring(0, indent.length() - 7);
    writeToFile(indent + "}\n" + indent + "});\n");
    return null;
  }

  @Override
  public Type visitPropose_arg(RobotGrammarParser.Propose_argContext ctx) {
    visitChildren(ctx);
    return Type.STRING;
  }

  @Override
  public Type visitPropose_block(RobotGrammarParser.Propose_blockContext ctx) {
    visitChildren(ctx);
    return null;
  }

  @Override
  public Type visitLoop_propose_block(RobotGrammarParser.Loop_propose_blockContext ctx) {
    visitChildren(ctx);
    return null;
  }

  @Override
  public Type visitString_expression(RobotGrammarParser.String_expressionContext ctx) {
    for(int i = 0; i < ctx.getChildCount(); i++){
      if(ctx.getChild(i).getText().equals("+")){
        continue;
      }
      assert(typefinder.visit(ctx.getChild(i)).equals(Type.STRING));
    }
    visitChildren(ctx);
    return Type.STRING;
  }

  @Override
  public Type visitLiteral_or_graph_exp(RobotGrammarParser.Literal_or_graph_expContext ctx) {
    // form is LITERAL_OR_GRAPH LPAR ( exp (COMMA exp)*)? RPAR
    writeToFile(indent + "new DialogueAct(");
    // TODO: please specify all variables that should NOT be packed in the String we give
    //      to DialogueAct (atm: every field access)
    String da = ctx.getChild(0).getText();
    da = da.substring(1, da.length());
    String what_da_gets = "\"" + da + "(";
    // now go through all arguments and look for things that we must not convert to string
    for (int i = 2; i < ctx.getChildCount() - 1; i++){  // last child is RPAR
      if(ctx.getChild(i).getText().equals(",")){
        what_da_gets += ", ";
          continue;
      }
      // Beobachtung: parser parst immer exp -> boolean_exp -> simple_b_exp -> andere
      int l = 0;
      ParseTree p = ctx.getChild(i);
      while (l < 3){
        p = p.getChild(0);
        l++;
      }
      if (p.getClass().equals(RobotGrammarParser.AssignmentContext.class)){
        // in this case the right part could be a wildcard...
        if(p.getChild(2).getText().equals("_")){
          what_da_gets += "_";
          continue;
        }
        // ... or a field access or sth else
        // parsing goes exp -> boolean_exp -> simple_b_exp -> arithmetic -> term -> factor -> number -> field_access / other
        l = 0;
        ParseTree t = p.getChild(2);
        while (l < 7){
          t = t.getChild(0);
          l++;
        }
        if (t.getClass().equals(RobotGrammarParser.Field_accessContext.class)){
          // then we have found a field access
          what_da_gets += p.getChild(0).getText() + " =\" + " + t.getText() + " + \"";
          continue;
        }
      }
      // if there is no field access, just add the parameter as string
      what_da_gets += ctx.getChild(i).getText();
    }
    writeToFile(what_da_gets + ")\")");
    return null;
  }

  @Override
  public Type visitIf_statement(RobotGrammarParser.If_statementContext ctx) {
    visitChildren(ctx);
    return null;
  }

  @Override
  public Type visitLoop_if_statement(RobotGrammarParser.Loop_if_statementContext ctx) {
    visitChildren(ctx);
    return null;
  }

  @Override
  public Type visitWhile_statement(RobotGrammarParser.While_statementContext ctx) {
    visitChildren(ctx);
    return null;
  }

  @Override
  public Type visitFor_statement(RobotGrammarParser.For_statementContext ctx) {
    writeToFile("for(");
    if (ctx.getChildCount() == 7){
      // statement looks like "FOR LPAR VARIABLE COLON exp RPAR loop_statement_block"
      Type vartype = typefinder.visit(ctx.getChild(4));
      writeToFile(vartype.toString());
      for (int i = 2; i < ctx.getChildCount(); i++){
        visit(ctx.getChild(i));
      }
      // TODO: or should we check here that the type of the variable in assignment
      // is the type the iterable in exp returns? How?
    }
    else if(ctx.getChildCount() == 8 || ctx.getChildCount() == 9){
      // statement looks like "FOR LPAR assignment SEMICOLON exp SEMICOLON exp? RPAR loop_statement_block"
      visitChildren(ctx);
      // TODO: or should we check here that the type of the variable in assignment
      // is the type the iterable in exp returns? How?
    }
    else{
      // statement looks like "FOR LPAR LPAR VARIABLE ( COMMA VARIABLE )+ RPAR COLON exp RPAR loop_statement_block"
      // TODO: we need sth that remembers what container exp uses to give back tuples
      //      so we can create a nested loop
    }
    return null;
  }

  @Override
  public Type visitLoop_statement(RobotGrammarParser.Loop_statementContext ctx) {
    writeToFile(indent);
    visitChildren(ctx);
    return null;
  }

  @Override
  public Type visitLoop_statement_block(RobotGrammarParser.Loop_statement_blockContext ctx) {
    visitChildren(ctx);
    return null;
  }

  @Override
  public Type visitField_access(RobotGrammarParser.Field_accessContext ctx) {
    assert(context.existsFieldAccess(ctx.getText())): "I don't know this field: " + ctx.getText();
    writeToFile(ctx.getText() + " ");     // visitChildren would be dangerous because children are lexed as VARIABLE
    return context.getFieldType(ctx.getText());
  }

  @Override
  public Type visitField_access_vfunc(RobotGrammarParser.Field_access_vfuncContext ctx) {
    assert(context.existsFieldAccess(ctx.getText())): "I don't know this field: " + ctx.getText();
    writeToFile(ctx.getText());     // visitChildren would be dangerous because children are lexed as VARIABLE
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
    // working on making them visible, should be possible as showed here:
    // http://meri-stuff.blogspot.de/2012/09/tackling-comments-in-antlr-compiler.html
    for(int n = 0; n < ctx.getChildCount(); n++){
        writeToFile(indent);
        this.visit(ctx.getChild(n));
    }
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
    if(tn.getSymbol().getType() == 46 || tn.getSymbol().getType() == 43){
      // token is whitespace or DEC_VAR
      return null;
    }
    if(tn.getSymbol().getType() == 47){ // token is variable
      if(memory.containsKey(tn.getText())){
        writeToFile(tn.getSymbol().getText() + " ");
          return memory.get(tn.getText());
      }
      else{
        writeToFile(context.getFullVariableName(tn.getText()));
        return context.getFieldType(tn.getText());
      }
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
      case 27:  // token is <=
        // TODO: how to determine whether this is normal boolean or magic?
      case 40:  //token is wildcard
        // ?????????????????
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
