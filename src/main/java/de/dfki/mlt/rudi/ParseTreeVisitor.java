/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudi;

import de.dfki.mlt.rudi.abstractTree.*;
import de.dfki.lt.hfc.db.HfcDbService;
import de.dfki.mlt.rudimant.io.RobotGrammarParser;
import de.dfki.mlt.rudimant.io.RobotGrammarVisitor;
import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

/**
 *
 * @author anna
 */
public class ParseTreeVisitor implements RobotGrammarVisitor<RudiTree> {

  // a container to always remember in which rule we are
  // as this was created for another design, use currentClass instead
  // private String currentRule;
  private String currentClass;
  // same for the current toplevel rule
  private String currentTRule;
  private int curDepth;
  private HfcDbService.Client _client;

  /**
   * constructor; the visitor needs to know in which rule/file we're in
   *
   * @param masterFile the name of the current rule (filename), please not the
   * name of the resulting java file!!!!
   * @param client
   */
  public ParseTreeVisitor(String masterFile, HfcDbService.Client client) {
    //this.memory = new HashMap<String, AbstractType>();
    currentClass = masterFile;
    _client = client;
  }

  @Override
  public RudiTree visitGrammar_file(RobotGrammarParser.Grammar_fileContext ctx) {
    // create an environment at the upper level
    // imports* (comment (grammar_rule | ...))* comment
    currentTRule = currentClass;
    ArrayList<RudiTree> rules = new ArrayList<RudiTree>();
    for (int i = 0; i < ctx.getChildCount(); i++) {
      curDepth = 0;
      rules.add(this.visit(ctx.getChild(i)));
    }
    return new GrammarFile(rules);
  }

  @Override
  public RudiTree visitImports(RobotGrammarParser.ImportsContext ctx) {
    //System.out.println("Rudi was here");
    // IMPORT VARIABLE SEMICOLON
    String file = ctx.getChild(1).getText();
    return new StatImport(file);
  }

  @Override
  public RudiTree visitMethod_declaration(RobotGrammarParser.Method_declarationContext ctx) {
    // (PUBLIC | PROTECTED | PRIVATE)? (DEC_VAR | VARIABLE) VARIABLE LPAR
    // ((VARIABLE | DEC_VAR) VARIABLE (COMMA (VARIABLE | DEC_VAR) VARIABLE)*) RPAR statement_block

    if (ctx.getChild(3).getText().equals("(")) { // we have public, protected or private modifier
      ArrayList<String> parameters = new ArrayList<>();
      ArrayList<String> partypes = new ArrayList<>();
      // get all the parameters of the function
      for (int i = 4; i < ctx.getChildCount() - 2;) {
        partypes.add(ctx.getChild(i).getText());
        partypes.add(ctx.getChild(++i).getText());
        i += 2;
      }
      return new StatMethodDeclaration(ctx.getChild(0).getText(), ctx.getChild(1).getText(),
              ctx.getChild(2).getText(), parameters, partypes,
              this.visit(ctx.getChild(ctx.getChildCount() - 1)),
              currentClass);
    }
    ArrayList<String> parameters = new ArrayList<>();
    ArrayList<String> partypes = new ArrayList<>();
    // get all the parameters of the function
    for (int i = 3; i < ctx.getChildCount() - 2;) {
      partypes.add(ctx.getChild(i).getText());
      partypes.add(ctx.getChild(++i).getText());
      i += 2;
    }
    return new StatMethodDeclaration(ctx.getChild(0).getText(), ctx.getChild(1).getText(),
            ctx.getChild(2).getText(), parameters, partypes,
            this.visit(ctx.getChild(ctx.getChildCount() - 1)),
            currentClass);
  }

  @Override
  public RudiTree visitLabel(RobotGrammarParser.LabelContext ctx) {
    // this method should never be reached as label is a part of grammar rule and
    // is not invoked there
    throw new UnsupportedOperationException("This method shouldn't be used");
  }

  @Override
  public RudiTree visitGrammar_rule(RobotGrammarParser.Grammar_ruleContext ctx) {
    // label comment if_statement
    String ruleName = ctx.getChild(0).getText().substring(0, ctx.getChild(0).getText().length() - 1);
    boolean toplevel = false;
    if (curDepth == 0) {
      // then this is a toplevel rule
      currentTRule = ruleName;
      toplevel = true;
    }
    curDepth++;
    return new GrammarRule(ruleName,
            (UCommentBlock) this.visit(ctx.getChild(1)),
            (StatIf) this.visit(ctx.getChild(2)), toplevel);
  }

  @Override
  public RudiTree visitStatement_block(RobotGrammarParser.Statement_blockContext ctx) {
    // comment LBRACE comment (statement  | grammar_rule)* RBRACE
    List<RudiTree> statblock = new ArrayList<RudiTree>();
    statblock.add(this.visit(ctx.getChild(0)));
    for (int i = 2; i < ctx.getChildCount() - 1; i++) {
      statblock.add(this.visit(ctx.getChild(i)));
    }
    return new StatAbstractBlock(statblock, true);
  }

  @Override
  public RudiTree visitStatement(RobotGrammarParser.StatementContext ctx) {
    // (some_statement | exp SEMICOLON) comment
    List<RudiTree> statblock = new ArrayList<RudiTree>();
    statblock.add(this.visit(ctx.getChild(0)));
    statblock.add(this.visit(ctx.getChild(ctx.getChildCount() - 1)));
    return new StatAbstractBlock(statblock, false);
  }

  @Override
  public RudiTree visitLoop_statement_block(RobotGrammarParser.Loop_statement_blockContext ctx) {
    // comment LBRACE comment ( statement  | grammar_rule)* RBRACE
    // when entering a statement block, we need to create a new local environment
    List<RudiTree> statblock = new ArrayList<RudiTree>();
    statblock.add(this.visit(ctx.getChild(0)));
    for (int i = 2; i < ctx.getChildCount() - 1; i++) {
      statblock.add(this.visit(ctx.getChild(i)));
    }
    return new StatAbstractBlock(statblock, true);
  }

  @Override
  public RudiTree visitFunction_call(RobotGrammarParser.Function_callContext ctx) {
    // VARIABLE LPAR exp? (COMMA exp)* RPAR
    ArrayList<RTExpression> expList = new ArrayList<RTExpression>();
    for (int i = 2; i < ctx.getChildCount() - 1;) {
      expList.add((RTExpression) this.visit(ctx.getChild(i)));
      i += 2;   // because we aren't interested in commas
    }
    //throw new UnsupportedOperationException("Yay, I found a function: ");
    return new UFuncCall(ctx.getChild(0).getText(), expList);
  }

  @Override
  public RudiTree visitArithmetic(RobotGrammarParser.ArithmeticContext ctx) {
    // term ( arithmetic_lin_operator term )*
    if (ctx.getChildCount() == 1) {
      return this.visit(ctx.getChild(0));
    }
    ExpArithmetic arit = new ExpArithmetic(
            (RTExpression) this.visit(ctx.getChild(ctx.getChildCount() - 1)),
            null, null, false);
    for (int i = ctx.getChildCount() - 2; i >= 0; i--) {
      if (i % 2 == 1) {
        arit = new ExpArithmetic(
                new ExpArithmetic(
                        (RTExpression) this.visit(ctx.getChild(i - 1)),
                        null, null, false),
                arit, ctx.getChild(i).getText(), false);
      }
    }
    return arit;
  }

  @Override
  public RudiTree visitTerm(RobotGrammarParser.TermContext ctx) {
    // term ( arithmetic_lin_operator term )*
    if (ctx.getChildCount() == 1) {
      return this.visit(ctx.getChild(0));
    }
    ExpArithmetic arit = new ExpArithmetic(
            (RTExpression) this.visit(ctx.getChild(ctx.getChildCount() - 1)),
            null, null, false);
    for (int i = ctx.getChildCount() - 2; i >= 0; i--) {
      if (i % 2 == 1) {
        arit = new ExpArithmetic(
                new ExpArithmetic(
                        (RTExpression) this.visit(ctx.getChild(i - 1)),
                        null, null, false),
                arit, ctx.getChild(i).getText(), false);
      }
    }
    return arit;
  }

  @Override
  public RudiTree visitFactor(RobotGrammarParser.FactorContext ctx) {
    // number (at least parsed as number)
    if (ctx.getChildCount() == 1) {
      return this.visit(ctx.getChild(0));
    } // MINUS arithmetic
    else if (ctx.getChildCount() == 2) {
      return new ExpArithmetic(
              (RTExpression) this.visit(ctx.getChild(1)), null, null, true);
    } // LPAR arithmetic RPAR
    else {
      // we write a lot of nice parenthesis anyways
      return this.visit(ctx.getChild(1));
    }
  }

  @Override
  public RudiTree visitArithmetic_operator(RobotGrammarParser.Arithmetic_operatorContext ctx) {
    // we should never get here since operators are directly used as Strings in arithmetics
    throw new UnsupportedOperationException("This method shouldn't be used");
  }

  @Override
  public RudiTree visitArithmetic_lin_operator(RobotGrammarParser.Arithmetic_lin_operatorContext ctx) {
    // we should never get here since operators are directly used as Strings in arithmetics
    throw new UnsupportedOperationException("This method shouldn't be used");
  }

  @Override
  public RudiTree visitArithmetic_dot_operator(RobotGrammarParser.Arithmetic_dot_operatorContext ctx) {
    // we should never get here since operators are directly used as Strings in arithmetics
    throw new UnsupportedOperationException("This method shouldn't be used");
  }

  @Override
  public RudiTree visitNumber(RobotGrammarParser.NumberContext ctx) {
    return this.visit(ctx.getChild(0));
  }

  @Override
  public RudiTree visitReturn_statement(RobotGrammarParser.Return_statementContext ctx) {
    // RETURN exp? SEMICOLON;
    if (ctx.getChildCount() == 2) {
      return new StatReturn();
    } else {
      return new StatReturn(this.visit(ctx.getChild(1)), ctx.getChild(1).getText());
    }
  }

  // testpush
  @Override
  public RudiTree visitWhile_statement(RobotGrammarParser.While_statementContext ctx) {
    // WHILE LPAR boolean_exp RPAR loop_statement_block
    if (ctx.getChildCount() == 5) {
      return new StatWhile((RTExpression) this.visit(ctx.getChild(2)),
              (StatAbstractBlock) this.visit(ctx.getChild(4)), currentClass);
    } // DO loop_statement_block WHILE LPAR boolean_exp RPAR
    else {
      return new StatDoWhile((RTExpression) this.visit(ctx.getChild(4)),
              (StatAbstractBlock) this.visit(ctx.getChild(1)), currentClass);
    }
  }

  @Override
  public RudiTree visitExp(RobotGrammarParser.ExpContext ctx) {
    // comment exp comment
//    if (ctx.getChildCount() == 4) { // exp of kind comment NOT boolean_exp comment
//      return new ExpAbstractWrapper((UCommentBlock) this.visit(ctx.getChild(0)),
//              new ExpBoolean(ctx.getChild(2).getText(), (RTExpression) this.visit(ctx.getChild(2)), null, null, true, true),
//              (UCommentBlock) this.visit(ctx.getChild(3)));
//    }
    if (ctx.getChildCount() == 5) { // exp of kind comment LPAR exp RPAR comment
      return new ExpAbstractWrapper((UCommentBlock) this.visit(ctx.getChild(0)),
              this.visit(ctx.getChild(2)),
              (UCommentBlock) this.visit(ctx.getChild(4)));
    }
    return new ExpAbstractWrapper((UCommentBlock) this.visit(ctx.getChild(0)),
            this.visit(ctx.getChild(1)),
            (UCommentBlock) this.visit(ctx.getChild(2)));
  }

  @Override
  public RudiTree visitLiteral_or_graph_exp(RobotGrammarParser.Literal_or_graph_expContext ctx) {
    // LITERAL_OR_GRAPH LPAR ( exp (COMMA exp)*)? RPAR
    ArrayList<RTExpression> expList = new ArrayList<RTExpression>();
    for (int i = 2; i < ctx.getChildCount() - 1;) {
      expList.add((RTExpression) this.visit(ctx.getChild(i)));
      i += 2;   // because we aren't interested in commas
    }
//    this.in_graph = false;
//    return new ExpDialogueAct(ctx.getChild(0).getText(), expList);
    String rest = "";
    for (int i = 2; i < ctx.getChildCount() - 1; i++) {  // we don't need the parenthesis
      rest += ctx.getChild(i).getText();
    }
    return new ExpDialogueAct(ctx.getChild(0).getText(), rest, expList);
  }

  @Override
  public RudiTree visitSimple_exp(RobotGrammarParser.Simple_expContext ctx) {
    // comment exp comment
    if (ctx.getChildCount() == 4) { // exp of kind comment NOT boolean_exp comment
//      System.out.println("simple exp");
      return new ExpAbstractWrapper((UCommentBlock) this.visit(ctx.getChild(0)),
              new ExpBoolean(ctx.getChild(2).getText(), (RTExpression) this.visit(ctx.getChild(2)), null, null, true, true),
              (UCommentBlock) this.visit(ctx.getChild(3)));
    }
    if (ctx.getChildCount() == 5) { // exp of kind comment LPAR exp RPAR comment
      return new ExpAbstractWrapper((UCommentBlock) this.visit(ctx.getChild(0)),
              this.visit(ctx.getChild(2)),
              (UCommentBlock) this.visit(ctx.getChild(4)));
    }
    return new ExpAbstractWrapper((UCommentBlock) this.visit(ctx.getChild(0)),
            this.visit(ctx.getChild(1)),
            (UCommentBlock) this.visit(ctx.getChild(2)));
  }

  @Override
  public RudiTree visitSimple_b_exp(RobotGrammarParser.Simple_b_expContext ctx) {
    // simple_exp (boolean_op2 exp)?
    if (ctx.getChildCount() == 1) {
      if (ctx.getText().equals("true") || ctx.getText().equals("false")) {
        return new UnaryBoolean(ctx.getText());
      }
      if (ctx.getChild(0) instanceof RobotGrammarParser.Simple_b_expContext) {
        return this.visit(ctx.getChild(0));
      }
//      System.out.println("simple b exp 1");
      return new ExpBoolean(ctx.getText(), (RTExpression) this.visit(ctx.getChild(0)),
              null, null, false, false);
    } else {
//      System.out.println("simple b exp 2");
      return new ExpBoolean(ctx.getText(), (RTExpression) this.visit(ctx.getChild(0)),
              (RTExpression) this.visit(ctx.getChild(2)),
              ctx.getChild(1).getText(), false, false);
    }
  }

  @Override
  public RudiTree visitBoolean_exp(RobotGrammarParser.Boolean_expContext ctx) {
    //  simple_b_exp boolean_op1 boolean_exp |  simple_b_exp
    if (ctx.getChildCount() == 1) {
      if (ctx.getText().equals("true") || ctx.getText().equals("false")) {
        return new UnaryBoolean(ctx.getText());
      }
      if (ctx.getChild(0) instanceof RobotGrammarParser.Simple_b_expContext) {
        return this.visit(ctx.getChild(0));
      }
//      System.out.println("b exp 1");
      return new ExpBoolean(ctx.getText(), (RTExpression) this.visit(ctx.getChild(0)),
              null, null, false, false);
//    } else if (ctx.getChildCount() == 2) {
//      return new ExpBoolean(ctx.getText(), (RTExpression) this.visit(ctx.getChild(1)),
//              null, null, true, false);
    } else if (ctx.getChildCount() == 3) {
//      System.out.println("b exp 3");
      ExpBoolean arit = new ExpBoolean(ctx.getChild(2).getText(),
              (RTExpression) this.visit(ctx.getChild(2)),
              null, null, false, false);
      arit = new ExpBoolean(ctx.getText(),
              //              new ExpBoolean(ctx.getChild(0).getText(),
              (RTExpression) this.visit(ctx.getChild(0)),
              //                      null, null, false),
              arit, ctx.getChild(1).getText(), false, true);
      return arit;
//    } else if (ctx.getChildCount() == 4) {
//      ExpBoolean arit = new ExpBoolean(ctx.getChild(3).getText(),
//              (RTExpression) this.visit(ctx.getChild(3)),
//              null, null, false, false);
//      arit = new ExpBoolean(ctx.getText(),
//              //              new ExpBoolean(ctx.getChild(1).getText(),
//              (RTExpression) this.visit(ctx.getChild(1)),
//              //                      null, null, true),
//              arit, ctx.getChild(2).getText(), false, true);
//      return arit;
    }
    return null;
  }

  @Override
  public RudiTree visitBoolean_op1(RobotGrammarParser.Boolean_op1Context ctx) {
    // operators are directly passed in boolean_exp
    throw new UnsupportedOperationException("This method shouldn't be used ");
  }

  @Override
  public RudiTree visitBoolean_op2(RobotGrammarParser.Boolean_op2Context ctx) {
    // operators are directly passed in boolean_exp
    throw new UnsupportedOperationException("This method shouldn't be used ");
  }

  @Override
  public RudiTree visitAssignment(RobotGrammarParser.AssignmentContext ctx) {
    // ((DEC_VAR | VARIABLE)? VARIABLE | field_access) ASSIGN exp
    if (ctx.getChildCount() == 3) { // no declaration
      RudiTree left = this.visit(ctx.getChild(0));
      return new ExpAssignment(this.visit(ctx.getChild(0)),
              (RTExpression) this.visit(ctx.getChild(2)), false, currentClass);
    } else {  // declaration
      if (ctx.getChild(0).getText().equals("var")) {
        RudiTree right = this.visit(ctx.getChild(3));
        return new ExpAssignment(this.visit(ctx.getChild(1)),
                (RTExpression) right, true, currentClass);
      }
      // now, this could be a special variable
      String type = ctx.getChild(0).getText();
      return new ExpAssignment(type, this.visit(ctx.getChild(1)),
              (RTExpression) this.visit(ctx.getChild(3)), true, currentClass);
    }
  }

  @Override
  public RudiTree visitPropose_arg(RobotGrammarParser.Propose_argContext ctx) {
    return this.visit(ctx.getChild(0));
  }

  @Override
  public RudiTree visitString_expression(RobotGrammarParser.String_expressionContext ctx) {
    // someString (PLUS someString)*
    // TODO: stimmt das??
    ExpArithmetic arit = new ExpArithmetic(
            (RTExpression) this.visit(ctx.getChild(ctx.getChildCount() - 1)),
            null, null, false);
    for (int i = ctx.getChildCount() - 3; i >= 0; i--) {
      if (i % 2 == 1) {
        arit = new ExpArithmetic(
                new ExpArithmetic(
                        (RTExpression) this.visit(ctx.getChild(i - 1)),
                        null, null, false),
                arit, ctx.getChild(i).getText(), false);
      }
    }
    return arit;
  }

  @Override
  public RudiTree visitPropose_statement(RobotGrammarParser.Propose_statementContext ctx) {
    // PROPOSE LPAR propose_arg RPAR statement_block
    return new StatPropose((RTExpression) this.visit(ctx.getChild(2)),
            (StatAbstractBlock) this.visit(ctx.getChild(4)));
  }

  @Override
  public RudiTree visitLoop_propose_statement(RobotGrammarParser.Loop_propose_statementContext ctx) {
    // PROPOSE LPAR propose_arg RPAR statement_block
    return new StatPropose((RTExpression) this.visit(ctx.getChild(2)),
            (StatAbstractBlock) this.visit(ctx.getChild(4)));
  }

  @Override
  public RudiTree visitTimeout_statement(RobotGrammarParser.Timeout_statementContext ctx) {
    // TIMEOUT LPAR STRING COMMA INT RPAR statement_block
    if (ctx.getChildCount() == 7) {   // timeout with statblock
      return new StatTimeout(ctx.getChild(2).getText(), Long.parseLong(ctx.getChild(4).getText()),
              this.visit(ctx.getChild(6)));
    } else {   // no statblock
      return new StatTimeout(ctx.getChild(2).getText(), Long.parseLong(ctx.getChild(4).getText()),
              null);
    }
  }

  @Override
  public RudiTree visitIf_statement(RobotGrammarParser.If_statementContext ctx) {
    // IF LPAR boolean_exp RPAR statement (ELSE statement)?
    if (ctx.getChildCount() == 5) {   // no else
      return new StatIf(ctx.getChild(2).getText(), (RTExpression) this.visit(ctx.getChild(2)),
              (StatAbstractBlock) this.visit(ctx.getChild(4)), null);
    }
    // if there is an else
    return new StatIf(ctx.getChild(2).getText(), (RTExpression) this.visit(ctx.getChild(2)),
            (StatAbstractBlock) this.visit(ctx.getChild(4)),
            (StatAbstractBlock) this.visit(ctx.getChild(6)));
  }

  @Override
  public RudiTree visitLoop_if_statement(RobotGrammarParser.Loop_if_statementContext ctx) {
    // IF LPAR boolean_exp RPAR statement (ELSE statement)?
    if (ctx.getChildCount() == 5) {   // no else
      return new StatIf(ctx.getChild(2).getText(), (RTExpression) this.visit(ctx.getChild(2)),
              (StatAbstractBlock) this.visit(ctx.getChild(4)), null);
    }
    // if there is an else
    return new StatIf(ctx.getChild(2).getText(), (RTExpression) this.visit(ctx.getChild(2)),
            (StatAbstractBlock) this.visit(ctx.getChild(4)),
            (StatAbstractBlock) this.visit(ctx.getChild(6)));
  }

  @Override
  public RudiTree visitIf_exp(RobotGrammarParser.If_expContext ctx) {
    // boolean_exp QUESTION exp COLON exp
    return new ExpIf(ctx.getText(), (RTExpression) this.visit(ctx.getChild(0)),
            (RTExpression) this.visit(ctx.getChild(2)),
            (RTExpression) this.visit(ctx.getChild(4)));
  }

  @Override
  public RudiTree visitFor_statement(RobotGrammarParser.For_statementContext ctx) {
    if (ctx.getChild(3).getText().equals(":")) {
      // FOR LPAR  VARIABLE COLON exp RPAR loop_statement_block
      // TODO: or should we check here that the type of the variable in assignment
      // is the type the iterable in exp returns? How?
      RTExpression exp = (RTExpression) this.visit(ctx.getChild(4));
      return new StatFor2(new UVariable(ctx.getChild(2).getText(), currentClass,
              currentTRule), exp,
              (StatAbstractBlock) this.visit(ctx.getChild(6)), currentClass);
    } else if (ctx.getChild(4).getText().equals(":")) {
      // FOR LPAR (DEC_VAR | VARIABLE) VARIABLE COLON exp RPAR loop_statement_block
      return new StatFor2(ctx.getChild(2).getText(),
              new UVariable(ctx.getChild(3).getText(), currentClass, currentTRule),
              (RTExpression) this.visit(ctx.getChild(5)),
              (StatAbstractBlock) this.visit(ctx.getChild(7)), currentClass);
    } else if (ctx.getChildCount() == 8) {
      // statement looks like "FOR LPAR assignment SEMICOLON exp SEMICOLON RPAR loop_statement_block"
      return new StatFor1((ExpAssignment) this.visit(ctx.getChild(2)),
              (ExpBoolean) this.visit(ctx.getChild(4)),
              null, (StatAbstractBlock) this.visit(ctx.getChild(7)), currentClass);
    } else if (ctx.getChildCount() == 9) {
      // statement looks like "FOR LPAR assignment SEMICOLON exp SEMICOLON exp RPAR loop_statement_block"
      return new StatFor1((ExpAssignment) this.visit(ctx.getChild(2)),
              (ExpBoolean) this.visit(ctx.getChild(4)),
              (RTExpression) this.visit(ctx.getChild(6)),
              (StatAbstractBlock) this.visit(ctx.getChild(8)), currentClass);
    } else {
      // statement looks like "FOR LPAR LPAR VARIABLE ( COMMA VARIABLE )+ RPAR COLON exp RPAR loop_statement_block"
      // TODO: implement For3Stat; exp will return some Object[]
      List<String> vars = new ArrayList<String>();
      RudiTree exp = this.visit(ctx.getChild(ctx.getChildCount() - 3));
      for (int i = 3; i < ctx.getChildCount() - 5; i += 2) {
        vars.add(ctx.getChild(i).getText());
      }
      return new StatFor3(vars, exp,
              (StatAbstractBlock) this.visit(ctx.getChild(ctx.getChildCount() - 1)),
              currentClass);
    }
  }

  @Override
  public RudiTree visitFunccall_on_object(RobotGrammarParser.Funccall_on_objectContext ctx) {
    // VARIABLE DOT function_call
    //throw new UnsupportedOperationException("Yay, I found a function: ");
    return new ExpFuncOnObject((RTExpression) this.visit(ctx.getChild(0)),
            (RTExpression) this.visit(ctx.getChild(2)), ctx.getText());
  }

  @Override
  public RudiTree visitField_access(RobotGrammarParser.Field_accessContext ctx) {
    ArrayList<String> parts = new ArrayList<String>();
    for (int i = 0; i < ctx.getChildCount(); i += 2) {
      parts.add(ctx.getChild(i).getText());
    }
    return new UFieldAccess(parts, _client);
  }

  @Override
  public RudiTree visitLambda_exp(RobotGrammarParser.Lambda_expContext ctx) {
    return new ExpLambda(ctx.getText());
  }

  @Override
  public RudiTree visitComment(RobotGrammarParser.CommentContext ctx) {
    ArrayList<UComment> comments = new ArrayList<UComment>();
    for (int i = 0; i < ctx.getChildCount(); i++) {
      if (ctx.getChild(i).getText().startsWith("/*@")) {
        comments.add(new UComment(ctx.getChild(i).getText().substring(3, ctx.getChild(i).getText().length() - 3) + "\n"));
      } else {
        comments.add(new UComment(ctx.getChild(i).getText()));
      }
    }
    return new UCommentBlock(comments);
  }

  @Override
  public RudiTree visitSet_operation(RobotGrammarParser.Set_operationContext ctx) {
    // (VARIABLE | field_access) (ADD | REMOVE) number
    if (ctx.getChild(1).getText().startsWith("-")) {      // remove
      return new StatSetOperation(this.visit(ctx.getChild(0)), false, this.visit(ctx.getChild(2)));
    } else {       // add
      return new StatSetOperation(this.visit(ctx.getChild(0)), true, this.visit(ctx.getChild(2)));
    }
  }

  @Override
  public RudiTree visitType_spec(RobotGrammarParser.Type_specContext ctx) {
    // VARIABLE | VARIABLE SMALLER VARIABLE GREATER
    throw new UnsupportedOperationException("This method should not be used.");
  }

  @Override
  public RudiTree visitFun_def(RobotGrammarParser.Fun_defContext ctx) {
    // type_spec VARIABLE LPAR ( type_spec VARIABLE (COMMA type_spec VARIABLE)* )? RPAR SEMICOLON
    // type_spec is either a boring normal type or a type of form Rdf<Child>
    String type = ctx.getChild(0).getText();
    String funcname = ctx.getChild(1).getText();
    // get all the parameters & their types
    ArrayList<String> parnames = new ArrayList<>();
    ArrayList<String> partypes = new ArrayList<>();
    int j = 3;
    while (j < ctx.getChildCount() - 2) {
      partypes.add(ctx.getChild(j++).getText());
      parnames.add(ctx.getChild(j++).getText());
      j++;
    }
    return new StatFunDef(type, funcname, parnames, partypes, currentClass);
  }

  @Override
  public RudiTree visitVar_def(RobotGrammarParser.Var_defContext ctx) {
    // type_spec VARIABLE SEMICOLON
    // type_spec is either a boring normal type or a type of form Rdf<Child>
    String type = ctx.getChild(0).getText();
    return new StatVarDef(type, ctx.getChild(1).getText(), currentClass);
  }

  @Override
  public RudiTree visit(ParseTree pt) {
    return pt.accept(this);
  }

  @Override
  public RudiTree visitChildren(RuleNode rn) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public RudiTree visitTerminal(TerminalNode tn) {
    // Attention! if you added new tokens or deleted old ones, the case numbers might have changed and
    // you get unexpected behaviour! (see Generated Sources / RobotGrammarLexer.java for right numbers)
    switch (tn.getSymbol().getType()) {
      case 9:   // token is NULL
        return new UNull();
      case 10:   // token is TRUE
        return new UnaryBoolean(tn.getText());
      case 11:  // token is FALSE
        return new UnaryBoolean(tn.getText());
      case 16:  // token is character
        return new UCharacter(tn.getText());
      case 17:  // token is String
        return new UString(tn.getText());
      case 20:  // token is an annotation
        return new UString(tn.getText() + "\n");
      case 48:  //token is wildcard
        return new UWildcard();
      case 58:  // token is variable
        String text = tn.getText();
        //System.out.println(currentClass);
        return new UVariable(text, currentClass, currentTRule);
      /*if (Mem.existsVariable(text)) {
          String origin = "";
          if (context.getCurrentRule().equals(Mem.getVariableOrigin(text))) {
            origin = "";
          } else {
            origin = context.getCurrentRule();
          }
          return new ALocalVar(Mem.getVariableType(text), text, origin);
        } else if (context.isGlobalVariable(text)) {
          return new AGlobalVar(context.getVariableType(text), text);
        } else {
          // TODO: find a nice exception
          throw new UnsupportedOperationException("This variable isn't declared "
                  + "anywhere: " + text);
        }*/
      case 59:  // token is int
        return new UNumber(tn.getText());
      case 60:  // token is float
        return new UNumber(tn.getText());
    }
    throw new UnsupportedOperationException("The terminal node for " + tn.getText()
            + ", tree type: " + tn.getSymbol().getType() + " should never be used");
  }

  @Override
  public RudiTree visitErrorNode(ErrorNode en) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public RudiTree visitList_creation(RobotGrammarParser.List_creationContext ctx) {
    // VARIABLE ASSIGN LBRACE (VARIABLE (COMMA VARIABLE)*)? SEMICOLON
// get all the elements to be added to the list
    ArrayList<RTExpression> elements = new ArrayList();
    for (int i = 3; i <= ctx.getChildCount() - 2;) {
      elements.add((RTExpression) this.visit(ctx.getChild(i)));
      i += 2;
    }
    return new StatListCreation(ctx.getChild(0).getText(), elements, currentClass);
  }

  @Override
  public RudiTree visitVariable(RobotGrammarParser.VariableContext ctx) {
    // VARIABLE | VARIABLE_MARKER field_access | VARIABLE_MARKER VARIABLE
    if (ctx.getChildCount() == 1) {
      return new UVariable(ctx.getChild(0).getText(), currentClass, currentTRule);
    } // else: there was a marker
    return new UVariable(ctx.getChild(1).getText(), currentClass, currentTRule);
  }

}
