/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import de.dfki.mlt.rudimant.io.RobotGrammarLexer;
import de.dfki.mlt.rudimant.io.RobotGrammarParser;
import de.dfki.mlt.rudimant.io.RobotGrammarParser.Da_tokenContext;
import de.dfki.mlt.rudimant.io.RobotGrammarParser.Switch_blockContext;
import de.dfki.mlt.rudimant.io.RobotGrammarParser.Switch_groupContext;
import de.dfki.mlt.rudimant.io.RobotGrammarParser.Switch_labelContext;
import de.dfki.mlt.rudimant.io.RobotGrammarParser.Switch_statementContext;
import de.dfki.mlt.rudimant.io.RobotGrammarParser.Timeout_statementContext;
import de.dfki.mlt.rudimant.tree.*;
import de.dfki.mlt.rudimant.io.RobotGrammarVisitor;

/**
 *
 * @author anna
 */
public class ParseTreeVisitor implements RobotGrammarVisitor<RudiTree> {

  // What's the class we're currently working on
  private String currentClass;

  /**
   * constructor; the visitor needs to know in which rule/file we're in
   *
   * @param masterFile the name of the current rule (filename), please not the
   * name of the resulting java file!!!!
   * @param client
   */
  public ParseTreeVisitor(String masterFile) {
    currentClass = masterFile;
  }

  @Override
  public RudiTree visitGrammar_file(RobotGrammarParser.Grammar_fileContext ctx) {
    // create an environment at the upper level
    // imports* (comment (grammar_rule | ...))* comment
    ArrayList<RudiTree> rules = new ArrayList<RudiTree>();
    for (int i = 0; i < ctx.getChildCount(); i++) {
      RudiTree t = visit(ctx.getChild(i));
      if (t instanceof RTExpression) t = t.ensureStatement();
      rules.add(t);
    }
    return new GrammarFile(rules).setPosition(ctx, currentClass);
  }

  @Override
  public RudiTree visitImports(RobotGrammarParser.ImportsContext ctx) {
    // IMPORT VARIABLE SEMICOLON
    String file = ctx.getChild(1).getText();
    return new Import(file).setPosition(ctx, currentClass);
  }

  @Override
  public RudiTree visitMethod_declaration(RobotGrammarParser.Method_declarationContext ctx) {
    // ('[' type_spec ']' '.')?
    // (PUBLIC | PROTECTED | PRIVATE)? (DEC_VAR | VARIABLE) VARIABLE LPAR
    // ((VARIABLE | DEC_VAR) VARIABLE (COMMA (VARIABLE | DEC_VAR) VARIABLE)*) RPAR statement_block
    String callSpec = null;
    int callS = 0;
    if(ctx.getChild(0).getText().equals("[")){
      callSpec = ctx.getChild(1).getText();
      callS = 4;
    }
    int hasVisibilitySpec = ctx.getChild(3 + callS).getText().equals("(") ? 1 : 0;

    ArrayList<String> parameters = new ArrayList<>();
    ArrayList<String> partypes = new ArrayList<>();
    // get all the parameters of the function
    for (int i = 3 + hasVisibilitySpec + callS; i < ctx.getChildCount() - 2;) {
      partypes.add(ctx.getChild(i).getText());
      parameters.add(ctx.getChild(++i).getText());
      i += 2;
    }
    RTStatement block = null;
    if (!ctx.getChild(ctx.getChildCount() - 1).getText().equals(";")) {
      block = visit(ctx.getChild(ctx.getChildCount() - 1)).ensureStatement();
    }
    return new StatMethodDeclaration(
        (hasVisibilitySpec == 0 ? "" : ctx.getChild(callS).getText()),
        ctx.getChild(0 + hasVisibilitySpec + callS).getText(),
        callSpec,
        ctx.getChild(1 + hasVisibilitySpec + callS).getText(),
        parameters, partypes, block).setPosition(ctx, currentClass);
  }

  @Override
  public RudiTree visitGrammar_rule(RobotGrammarParser.Grammar_ruleContext ctx) {
    //  label if_statement
    String ruleName = ctx.getChild(0).getText();
    return new StatGrammarRule(ruleName,
        (StatIf) visit(ctx.getChild(2)))
        .setPosition(ctx, currentClass);
  }

  @Override
  public RudiTree visitStatement_block(RobotGrammarParser.Statement_blockContext ctx) {
    // '{' (statement)* '}'
    List<RTStatement> statblock = new ArrayList<>();
    for (int i = 1; i < ctx.getChildCount() - 1; i++) {
      statblock.add(visit(ctx.getChild(i)).ensureStatement());
    }
    return new StatAbstractBlock(statblock, true).setPosition(ctx, currentClass);
  }

  @Override
  public RudiTree visitStatement(RobotGrammarParser.StatementContext ctx) {
    return visit(ctx.getChild(0)).setPosition(ctx, currentClass);
  }

  @Override
  public RudiTree visitFunction_call(RobotGrammarParser.Function_callContext ctx) {
    // VARIABLE LPAR exp? (COMMA exp)* RPAR
    ArrayList<RTExpression> expList = new ArrayList<RTExpression>();
    for (int i = 2; i < ctx.getChildCount() - 1;) {
      expList.add((RTExpression) visit(ctx.getChild(i)));
      i += 2;   // skip comma
    }
    return new ExpFuncCall(ctx.getChild(0).getText(),
        expList, false).setPosition(ctx, currentClass);
  }

  private RudiTree arithExp(ParserRuleContext ctx) {
    RudiTree result;
    if (ctx.getChildCount() == 1) {
      result = visit(ctx.getChild(0));
    } else if (ctx.getChildCount() == 2) {
      // it had a ++ or --
      RTExpression left = (RTExpression) visit(ctx.getChild(0));
      RTExpression right = (RTExpression) new ExpArithmetic(left,
          (RTExpression) new ExpSingleValue("1", "int")
          .setPosition(ctx, currentClass),
          ctx.getChild(1).getText().equals("++")? "+" : "-")
          .setPosition(ctx, currentClass);
      result = new ExpAssignment(left, right);
    } else { // 3 children
      result = new ExpArithmetic(
          (RTExpression) visit(ctx.getChild(0)),
          (RTExpression) visit(ctx.getChild(2)),
          ctx.getChild(1).getText());
    }
    return result.setPosition(ctx, currentClass);
  }

  @Override
  public RudiTree visitArithmetic(RobotGrammarParser.ArithmeticContext ctx) {
    // term ('-'|'+') boolean_exp | term ('++'|'--') | term
    return arithExp(ctx);
  }

  @Override
  public RudiTree visitTerm(RobotGrammarParser.TermContext ctx) {
    // factor ('*'|'/'|'%') term | factor
    return arithExp(ctx);
  }

  @Override
  public RudiTree visitString_expression(RobotGrammarParser.String_expressionContext ctx) {
    // (simple_exp|if_exp) '+' exp | (simple_exp|if_exp)
    return arithExp(ctx);
  }

  @Override
  public RudiTree visitFactor(RobotGrammarParser.FactorContext ctx) {
    // number (at least parsed as number)
    if (ctx.getChildCount() == 1) {
      return visit(ctx.getChild(0)).setPosition(ctx, currentClass);
    } // MINUS arithmetic
    else if (ctx.getChildCount() == 2) {
      return new ExpArithmetic((RTExpression) visit(ctx.getChild(1)),
          null, "-").setPosition(ctx, currentClass);
    } // LPAR arithmetic RPAR
    else {
      // we write a lot of nice parenthesis anyways
      return visit(ctx.getChild(1)).setPosition(ctx, currentClass);
    }
  }

  @Override
  public RudiTree visitNumber(RobotGrammarParser.NumberContext ctx) {
    return visit(ctx.getChild(0)).setPosition(ctx, currentClass);
  }

  @Override
  public RudiTree visitReturn_statement(RobotGrammarParser.Return_statementContext ctx) {
    // RETURN exp? SEMICOLON;
    StatReturn res;
    String cmd = ctx.getChild(0).getText();
    if (ctx.getChildCount() == 2) {
      res = new StatReturn(cmd);
    } else {
      if (cmd.equals("return"))
        res = new StatReturn((RTExpression)visit(ctx.getChild(1)));
      else
        res = new StatReturn(cmd, ctx.getChild(1).getText());
    }
    res.setPosition(ctx, currentClass);
    return res;
  }

  // TODO: IN PRINCIPLE POSSIBLE WITHOUT CURLY BRACES
  @Override
  public RudiTree visitWhile_statement(RobotGrammarParser.While_statementContext ctx) {
    // WHILE LPAR boolean_exp RPAR loop_statement_block
    if (ctx.getChildCount() == 5) {
      return new StatWhile((RTExpression) visit(ctx.getChild(2)),
          (StatAbstractBlock) visit(ctx.getChild(4)), true)
          .setPosition(ctx, currentClass);
    } // DO loop_statement_block WHILE LPAR boolean_exp RPAR
    else {
      return new StatWhile((RTExpression) visit(ctx.getChild(4)),
          (StatAbstractBlock) visit(ctx.getChild(1)), false)
          .setPosition(ctx, currentClass);
    }
  }

  @Override
  public RudiTree visitExp(RobotGrammarParser.ExpContext ctx) {
    if (ctx.getChildCount() == 1) {
      return (RTExpression) visit(ctx.getChild(0));
    } else {
      RTExpression ret =
          (RTExpression) visit(ctx.getChild(3));
      return new ExpCast(ctx.getChild(1).getText(),
          ret).setPosition(ctx, currentClass);
    }
  }

  @Override
  public RudiTree visitLiteral_or_graph_exp(RobotGrammarParser.Literal_or_graph_expContext ctx) {
    // HASH da_token LPAR ( da_token ( COMMA ( da_token ASSIGN da_token) )* ) RPAR
    ArrayList<RTExpression> expList = new ArrayList<RTExpression>();

    for (int i = 5; i < ctx.getChildCount() - 1;) {
      expList.add((RTExpression) visit(ctx.getChild(i)));
      i += 2;   // because we aren't interested in commas
    }
    return new ExpDialogueAct(
        (RTExpression) visit(ctx.getChild(1)),
        (RTExpression) visit(ctx.getChild(3)),
        expList).setPosition(ctx, currentClass);
  }

  @Override
  public RudiTree visitSimple_exp(RobotGrammarParser.Simple_expContext ctx) {
    // '('exp')' | variable | function_call | field_access | STRING | FALSE | TRUE | NULL
    RudiTree result;
    switch (ctx.getChildCount()) {
    case 3: // '(' exp ')'
      result = visit(ctx.getChild(1));
      break;
    case 1: // other expression
      result = visit(ctx.getChild(0));
      break;
    default:
      throw new UnsupportedOperationException("How's that possible");
    }
    return result.setPosition(ctx, currentClass);
  }

  @Override
  public RudiTree visitComplex_exp(RobotGrammarParser.Complex_expContext ctx) {
    //  arithmetic | literal_or_graph_exp | assignment
    if (ctx.getChildCount() != 1)
      throw new UnsupportedOperationException("How's that possible");
    return visit(ctx.getChild(0)).setPosition(ctx, currentClass);
  }

  @Override
  public RudiTree visitNew_exp(RobotGrammarParser.New_expContext ctx) {
    // NEW VARIABLE | NEW function_call
    String toCreate = null;
    RTExpression construct = null;
    if (ctx.getChild(1).getText().contains("(")) {
      construct = (RTExpression) visit(ctx.getChild(1));
    } else {
      toCreate = ctx.getChild(1).getText();
    }
    return new ExpNew(toCreate, construct)
        .setPosition(ctx, currentClass);
  }

  private RudiTree boolExp(ParserRuleContext ctx) {
    RudiTree result = null;
    switch (ctx.getChildCount()) {
    case 1:
      result = visit(ctx.getChild(0));
      break;
    case 2: // NOT simple_exp
      result = new ExpBoolean(
          (RTExpression) visit(ctx.getChild(1)), null, "!");
      break;
    case 3: // comparison operator
      result = new ExpBoolean(
          (RTExpression) visit(ctx.getChild(0)),
          (RTExpression) visit(ctx.getChild(2)),
          ctx.getChild(1).getText() // operator
          );
      break;
    default:
      throw new UnsupportedOperationException("How's that possible");
    }
    return result.setPosition(ctx, currentClass);
  }

  @Override
  public RudiTree visitSimple_b_exp(RobotGrammarParser.Simple_b_expContext ctx) {
    // simple_exp | simple_exp ('==' | '!=' | '<=' | '<' | '>=' | '>') exp
    return boolExp(ctx);
  }

  @Override
  public RudiTree visitBoolean_exp(RobotGrammarParser.Boolean_expContext ctx) {
    // bool_and_exp | boolean_exp '||' bool_and_exp
    return boolExp(ctx);
  }

  @Override
  public RudiTree visitBool_and_exp(RobotGrammarParser.Bool_and_expContext ctx) {
    //  simple_b_exp | bool_and_exp '&&' simple_b_exp
    return boolExp(ctx);
  }

  @Override
  public RudiTree visitAssignment(RobotGrammarParser.AssignmentContext ctx) {
    // ((DEC_VAR | VARIABLE)? VARIABLE | field_access) ASSIGN exp
    if (ctx.getChildCount() == 3) { // no declaration
      RudiTree left = visit(ctx.getChild(0));
      return new ExpAssignment((RTExpression) left,
          (RTExpression) visit(ctx.getChild(2))).setPosition(ctx, currentClass);
    } else {  // declaration
      String declaredType = ctx.getChild(0).getText();
      if (declaredType.equals("var")) {
        declaredType = null;
      }
      return new ExpAssignment(declaredType, (RTExpression) visit(ctx.getChild(1)),
          (RTExpression) visit(ctx.getChild(3))).setPosition(ctx, currentClass);
    }
  }

  @Override
  public RudiTree visitPropose_statement(RobotGrammarParser.Propose_statementContext ctx) {
    // PROPOSE LPAR propose_arg RPAR statement_block
    if (((TerminalNode)ctx.getChild(0)).getSymbol().getType() == RobotGrammarLexer.PROPOSE)
      return new StatPropose((RTExpression) visit(ctx.getChild(2)),
          (StatAbstractBlock) visit(ctx.getChild(4))).setPosition(ctx, currentClass);
    return new StatTimeout(null, (RTExpression) visit(ctx.getChild(2)),
        (StatAbstractBlock) visit(ctx.getChild(4))).setPosition(ctx, currentClass);
  }

  @Override
  public RudiTree visitTimeout_statement(Timeout_statementContext ctx) {
    // TIMEOUT '(' string_expression ',' arithmetic ')' statement_block
    return new StatTimeout((RTExpression) visit(ctx.getChild(2)),
        (RTExpression) visit(ctx.getChild(4)),
        (StatAbstractBlock) visit(ctx.getChild(6))).setPosition(ctx, currentClass);
  }

  @Override
  public RudiTree visitSwitch_statement(Switch_statementContext ctx) {
    // SWITCH '(' exp ')' '{' switch_block '}'
    return new StatSwitch((RTExpression) visit(ctx.getChild(2)),
        (StatAbstractBlock) visit(ctx.getChild(5))).setPosition(ctx, currentClass);
  }

  @Override
  public RudiTree visitSwitch_block(Switch_blockContext ctx) {
    // switch_group* switch_label*
    List<RTStatement> elements = new ArrayList<>();
    for (ParseTree t : ctx.children) {
      elements.add(visit(t).ensureStatement());
    }
    return new StatAbstractBlock(elements, true).setPosition(ctx, currentClass);
  }

  @Override
  public RudiTree visitSwitch_group(Switch_groupContext ctx) {
    // switch_label switch_label* statement
    List<RTStatement> elements = new ArrayList<>();
    for (ParseTree t : ctx.children) {
      elements.add(visit(t).ensureStatement());
    }
    return new StatAbstractBlock(elements, false).setPosition(ctx, currentClass);
  }

  @Override
  public RudiTree visitSwitch_label(Switch_labelContext ctx) {
    // CASE string_expression ':' | CASE VARIABLE ':' | DEFAULT ':'
    return new ExpSingleValue(ctx.getText(), "label").setPosition(ctx, currentClass);
  }

  @Override
  public RudiTree visitIf_statement(RobotGrammarParser.If_statementContext ctx) {
    // IF LPAR boolean_exp RPAR statement (ELSE statement)?
    if (ctx.getChildCount() == 5) {   // no else
      return new StatIf((RTExpression) visit(ctx.getChild(2)),
          visit(ctx.getChild(4)).ensureStatement(), null).setPosition(ctx, currentClass);
    }
    // if there is an else
    return new StatIf((RTExpression) visit(ctx.getChild(2)),
        visit(ctx.getChild(4)).ensureStatement(),
        visit(ctx.getChild(6)).ensureStatement()).setPosition(ctx, currentClass);
  }

  @Override
  public RudiTree visitIf_exp(RobotGrammarParser.If_expContext ctx) {
    // boolean_exp QUESTION exp COLON exp
    return new ExpConditional((RTExpression) visit(ctx.getChild(0)),
        (RTExpression) visit(ctx.getChild(2)),
        (RTExpression) visit(ctx.getChild(4))).setPosition(ctx, currentClass);
  }

  @Override
  public RudiTree visitFor_statement(RobotGrammarParser.For_statementContext ctx) {
    if (ctx.getChild(3).getText().equals(":")) {
      // FOR '(' VARIABLE ':' exp ')' loop_statement_block
      // TODO: or should we check here that the type of the variable in assignment
      // is the type the iterable in exp returns? How?
      RTExpression exp = (RTExpression) visit(ctx.getChild(4));
      ExpVariable var = new ExpVariable(ctx.getChild(2).getText());
      var.setPosition(ctx.VARIABLE(0), currentClass);
      return new StatFor2(var, exp, visit(ctx.getChild(6)).ensureStatement()).setPosition(ctx, currentClass);
    } else if (ctx.getChild(4).getText().equals(":")) {
      // with type specification
      ExpVariable var = new ExpVariable(ctx.getChild(2).getText(),
          ctx.getChild(3).getText());
      var.setPosition(ctx.VARIABLE(0), currentClass);
      // FOR '(' (DEC_VAR | type_spec) VARIABLE ':' exp ')' loop_statement_block
      return new StatFor2(ctx.getChild(2).getText(), var,
          (RTExpression) visit(ctx.getChild(5)),
          visit(ctx.getChild(7)).ensureStatement()).setPosition(ctx, currentClass);
    } else if (ctx.getChild(3).getText().equals(";") ||
        ctx.getChild(5).getText().equals(";")) {
      // statement looks like "FOR LPAR assignment SEMICOLON exp SEMICOLON exp RPAR loop_statement_block"
      // all expressions are optional!
      int i = 2;
      RudiTree[] forExps = {null, null, null};
      for (int j = 0; j < 3; ++j) {
        if (!ctx.getChild(i).equals(";")) {
          forExps[j] = visit(ctx.getChild(i));
          ++i;
        }
        ++i;
      }
      RTStatement block = visit(ctx.getChild(i)).ensureStatement();

      return new StatFor1((ExpAssignment) forExps[0], (ExpBoolean) forExps[1],
          (RTExpression) forExps[2], block).setPosition(ctx, currentClass);
    } else {
      // statement looks like "FOR LPAR LPAR VARIABLE ( COMMA VARIABLE )+ RPAR COLON exp RPAR loop_statement_block"
      // TODO: implement For3Stat; exp will return some Object[]
      List<String> vars = new ArrayList<String>();
      RudiTree exp = visit(ctx.getChild(ctx.getChildCount() - 3));
      for (int i = 3; i < ctx.getChildCount() - 5; i += 2) {
        vars.add(ctx.getChild(i).getText());
      }
      return new StatFor3(vars, (RTExpression)exp,
          (RTStatement) visit(ctx.getChild(ctx.getChildCount() - 1))).setPosition(ctx, currentClass);
    }
  }

  @Override
  public RudiTree visitField_access(RobotGrammarParser.Field_accessContext ctx) {
    ArrayList<String> representation = new ArrayList<>();
    ArrayList<RTExpression> parts = new ArrayList<>();
    int i = 0;
    if (ctx.getChild(0).getText().equals("(")) {
      representation.add(ctx.getChild(1).getText());
      parts.add((RTExpression) visit(ctx.getChild(1)));
      i = 4;
    }
    for (;i < ctx.getChildCount(); i += 2) {
      representation.add(ctx.getChild(i).getText());
      parts.add((RTExpression) visit(ctx.getChild(i)));
    }
    return new ExpFieldAccess(parts, representation)
        .setPosition(ctx, currentClass);
  }

  @Override
  public RudiTree visitLambda_exp(RobotGrammarParser.Lambda_expContext ctx) {
    // '(' VARIABLE (',' VARIABLE)* ')' '->' (exp|statement_block);
    List<String> args = new ArrayList<>();
    for(int i = 1; i < ctx.getChildCount() - 3; i += 2) {
      args.add(ctx.getChild(i).getText());
    }
    return new ExpLambda(args, visit(ctx.getChild(ctx.getChildCount() - 1)))
        .setPosition(ctx, currentClass);
  }

  @Override
  public RudiTree visitSet_operation(RobotGrammarParser.Set_operationContext ctx) {
    // (VARIABLE | field_access) (ADD | REMOVE) number
    return new StatSetOperation(
        (RTExpression)visit(ctx.getChild(0)),
        ctx.getChild(1).getText().startsWith("+"),
        (RTExpression)visit(ctx.getChild(2)))
        .setPosition(ctx, currentClass);
  }

  @Override
  public RudiTree visitType_spec(RobotGrammarParser.Type_specContext ctx) {
    // VARIABLE | VARIABLE SMALLER VARIABLE GREATER
    throw new UnsupportedOperationException("This method should not be used.");
  }

  @Override
  public RudiTree visitVar_def(RobotGrammarParser.Var_defContext ctx) {
    // type_spec VARIABLE SEMICOLON
    // type_spec is either a boring normal type or a type of form Rdf<Child>
    String type = ctx.getChild(0).getText();
    return new StatVarDef(type, ctx.getChild(1).getText())
        .setPosition(ctx, currentClass);
  }

  @Override
  public RudiTree visitChildren(RuleNode rn) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public RudiTree visitTerminal(TerminalNode tn) {
    return visitTerminalInner(tn).setPosition(tn, currentClass);
  }

  public RudiTree visitTerminalInner(TerminalNode tn) {
    switch (tn.getSymbol().getType()) {
    case RobotGrammarLexer.NULL:   // token is NULL
      return new ExpSingleValue("null", "Object");
    case RobotGrammarLexer.TRUE:   // token is TRUE
    case RobotGrammarLexer.FALSE:  // token is FALSE
      return new ExpSingleValue(tn.getText(), "boolean");
    case RobotGrammarLexer.CHARACTER:  // token is character
      return new ExpSingleValue(tn.getText(), "char");
    case RobotGrammarLexer.STRING:  // token is String
      return new ExpSingleValue(tn.getText(), "String");
    case RobotGrammarLexer.INT:  // token is int
      return new ExpSingleValue(tn.getText(), "int");
    case RobotGrammarLexer.FLOAT:  // token is float
      return new ExpSingleValue(tn.getText(), "float");
      // An annotation is sth. like @Override (yes, it is a String as long as the
      // representation of Strings has to explicitly have a " to be put in "")
    case RobotGrammarLexer.ANNOTATION:  // token is an annotation
      return new ExpSingleValue(tn.getText() + "\n", "annotation");
    case RobotGrammarLexer.VARIABLE:  // token is variable
      return new ExpVariable(tn.getText());
    case RobotGrammarLexer.BREAK:
    case RobotGrammarLexer.CONTINUE:
      return new ExpSingleValue(tn.getText(), "break/continue");
    }
    throw new UnsupportedOperationException("The terminal node for " + tn.getText()
    + ", tree type: " + tn.getSymbol().getType() + " should never be used");
  }

  @Override
  public RudiTree visitErrorNode(ErrorNode en) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public RudiTree visitList_creation(RobotGrammarParser.List_creationContext ctx) {
    // type_spec? variable ASSIGN LBRACE (VARIABLE (COMMA VARIABLE)*)? SEMICOLON
    int start = 4;
    if (ctx.getChild(1).getText().equals("=")) {
      start = 3;
    }
    // get all the elements to be added to the list
    ArrayList<RTExpression> elements = new ArrayList<>();
    for (int i = start; i < ctx.getChildCount() - 2;) {
      elements.add((RTExpression) visit(ctx.getChild(i)));
      i += 2;
    }
    if(start == 3){
      return new StatListCreation(ctx.getChild(0).getText(), elements)
          .setPosition(ctx, currentClass);
    } else {
      return new StatListCreation(ctx.getChild(1).getText(), elements,
          ctx.getChild(0).getText())
          .setPosition(ctx, currentClass);
    }
  }

  @Override
  public RudiTree visitVariable(RobotGrammarParser.VariableContext ctx) {
    // VARIABLE | field_access
    return new ExpVariable(ctx.getText()).setPosition(ctx, currentClass);
  }

  @Override
  public RudiTree visitDa_token(Da_tokenContext ctx) {
    return visit(ctx.getChild(ctx.getChildCount() - 1)).setPosition(ctx, currentClass);
  }

  @Override
  public RudiTree visit(ParseTree pt) {
    return pt == null ? null : pt.accept(this);
  }

  @Override
  public RudiTree visitSpec_func_call(RobotGrammarParser.Spec_func_callContext ctx) {
    // type_spec LPAR exp? (COMMA exp)* RPAR
    ArrayList<RTExpression> expList = new ArrayList<RTExpression>();
    for (int i = 2; i < ctx.getChildCount() - 1;) {
      expList.add((RTExpression) visit(ctx.getChild(i)));
      i += 2;   // skip comma
    }
    return new ExpFuncCall(ctx.getChild(0).getText(),
        expList, true).setPosition(ctx, currentClass);
  }
}
