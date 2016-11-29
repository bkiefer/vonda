/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import de.dfki.mlt.rudimant.abstractTree.*;
import de.dfki.mlt.rudimant.io.RobotGrammarLexer;
import de.dfki.mlt.rudimant.io.RobotGrammarParser;
import de.dfki.mlt.rudimant.io.RobotGrammarParser.Da_tokenContext;
import de.dfki.mlt.rudimant.io.RobotGrammarParser.Switch_blockContext;
import de.dfki.mlt.rudimant.io.RobotGrammarParser.Switch_groupContext;
import de.dfki.mlt.rudimant.io.RobotGrammarParser.Switch_labelContext;
import de.dfki.mlt.rudimant.io.RobotGrammarParser.Switch_statementContext;
import de.dfki.mlt.rudimant.io.RobotGrammarVisitor;

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

  /**
   * constructor; the visitor needs to know in which rule/file we're in
   *
   * @param masterFile the name of the current rule (filename), please not the
   * name of the resulting java file!!!!
   * @param client
   */
  public ParseTreeVisitor(String masterFile, List<Token> commentTokens) {
    currentClass = masterFile;
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
    return new GrammarFile(rules).setPosition(ctx);
  }

  @Override
  public RudiTree visitImports(RobotGrammarParser.ImportsContext ctx) {
    // IMPORT VARIABLE SEMICOLON
    String file = ctx.getChild(1).getText();
    return new StatImport(file).setPosition(ctx);
  }

  @Override
  public RudiTree visitMethod_declaration(RobotGrammarParser.Method_declarationContext ctx) {
    // (PUBLIC | PROTECTED | PRIVATE)? (DEC_VAR | VARIABLE) VARIABLE LPAR
    // ((VARIABLE | DEC_VAR) VARIABLE (COMMA (VARIABLE | DEC_VAR) VARIABLE)*) RPAR statement_block

    int hasVisibilitySpec = ctx.getChild(3).getText().equals("(") ? 1 : 0;

    ArrayList<String> parameters = new ArrayList<>();
    ArrayList<String> partypes = new ArrayList<>();
    // get all the parameters of the function
    for (int i = 3 + hasVisibilitySpec; i < ctx.getChildCount() - 2;) {
      partypes.add(ctx.getChild(i).getText());
      parameters.add(ctx.getChild(++i).getText());
      i += 2;
    }
    return new StatMethodDeclaration(
        (hasVisibilitySpec == 0 ? "" : ctx.getChild(0).getText()),
        ctx.getChild(0 + hasVisibilitySpec).getText(),
        ctx.getChild(1 + hasVisibilitySpec).getText(),
        parameters, partypes,
        this.visit(ctx.getChild(ctx.getChildCount() - 1)),
        currentClass).setPosition(ctx);
  }

  @Override
  public RudiTree visitGrammar_rule(RobotGrammarParser.Grammar_ruleContext ctx) {
    //  label if_statement
    String ruleName = ctx.getChild(0).getText();
    boolean toplevel = false;
    if (curDepth == 0) {
      // then this is a toplevel rule
      currentTRule = ruleName;
      toplevel = true;
    }
    curDepth++;
    return new GrammarRule(ruleName, (StatIf) this.visit(ctx.getChild(2)), toplevel).setPosition(ctx);
  }

  @Override
  public RudiTree visitStatement_block(RobotGrammarParser.Statement_blockContext ctx) {
    // '{' (statement)* '}'
    List<RudiTree> statblock = new ArrayList<RudiTree>();
    for (int i = 1; i < ctx.getChildCount() - 1; i++) {
      statblock.add(this.visit(ctx.getChild(i)));
    }
    return new StatAbstractBlock(statblock, true).setPosition(ctx);
  }

  @Override
  public RudiTree visitStatement(RobotGrammarParser.StatementContext ctx) {
    return this.visit(ctx.getChild(0)).setPosition(ctx);
  }

  @Override
  public RudiTree visitFunction_call(RobotGrammarParser.Function_callContext ctx) {
    // VARIABLE LPAR exp? (COMMA exp)* RPAR
    ArrayList<RTExpression> expList = new ArrayList<RTExpression>();
    for (int i = 2; i < ctx.getChildCount() - 1;) {
      expList.add((RTExpression) this.visit(ctx.getChild(i)));
      i += 2;   // skip comma
    }
    return new UFuncCall(ctx.getText(), ctx.getChild(0).getText(),
        expList).setPosition(ctx);
  }

  private RudiTree arithExp(ParserRuleContext ctx) {
    RudiTree result;
    if (ctx.getChildCount() == 1) {
      result = this.visit(ctx.getChild(0));
    } else { // 3 children
      result = new ExpArithmetic(ctx.getText(),
          (RTExpression) this.visit(ctx.getChild(0)),
          (RTExpression) this.visit(ctx.getChild(2)),
          ctx.getChild(1).getText());
    }
    return result.setPosition(ctx);
  }

  @Override
  public RudiTree visitArithmetic(RobotGrammarParser.ArithmeticContext ctx) {
    // term ('-'|'+') boolean_exp | term
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
      return this.visit(ctx.getChild(0)).setPosition(ctx);
    } // MINUS arithmetic
    else if (ctx.getChildCount() == 2) {
      return new ExpArithmetic(ctx.getText(),
              (RTExpression) this.visit(ctx.getChild(1)), null, "-").setPosition(ctx);
    } // LPAR arithmetic RPAR
    else {
      // we write a lot of nice parenthesis anyways
      return this.visit(ctx.getChild(1)).setPosition(ctx);
    }
  }

  @Override
  public RudiTree visitNumber(RobotGrammarParser.NumberContext ctx) {
    return this.visit(ctx.getChild(0)).setPosition(ctx);
  }

  @Override
  public RudiTree visitReturn_statement(RobotGrammarParser.Return_statementContext ctx) {
    // RETURN exp? SEMICOLON;
    if (ctx.getChildCount() == 2) {
      return new StatReturn().setPosition(ctx);
    } else {
      return new StatReturn(this.visit(ctx.getChild(1)), ctx.getChild(1).getText()).setPosition(ctx);
    }
  }

  // testpush
  @Override
  public RudiTree visitWhile_statement(RobotGrammarParser.While_statementContext ctx) {
    // WHILE LPAR boolean_exp RPAR loop_statement_block
    if (ctx.getChildCount() == 5) {
      return new StatWhile((RTExpression) this.visit(ctx.getChild(2)),
              (StatAbstractBlock) this.visit(ctx.getChild(4)), currentClass).setPosition(ctx);
    } // DO loop_statement_block WHILE LPAR boolean_exp RPAR
    else {
      return new StatDoWhile((RTExpression) this.visit(ctx.getChild(4)),
              (StatAbstractBlock) this.visit(ctx.getChild(1)), currentClass).setPosition(ctx);
    }
  }

  @Override
  public RudiTree visitExp(RobotGrammarParser.ExpContext ctx) {
    return (RTExpression) this.visit(ctx.getChild(0)).setPosition(ctx);
  }

  @Override
  public RudiTree visitLiteral_or_graph_exp(RobotGrammarParser.Literal_or_graph_expContext ctx) {
    // HASH da_token LPAR ( da_token ( COMMA ( da_token ASSIGN da_token) )* ) RPAR
    ArrayList<RTExpression> expList = new ArrayList<RTExpression>();

    for (int i = 5; i < ctx.getChildCount() - 1;) {
      expList.add((RTExpression) this.visit(ctx.getChild(i)));
      i += 2;   // because we aren't interested in commas
    }
    return new ExpDialogueAct(ctx.getText(),
        (RTExpression)this.visit(ctx.getChild(1)),
        (RTExpression)this.visit(ctx.getChild(3)),
        expList).setPosition(ctx);
  }

  @Override
  public RudiTree visitSimple_exp(RobotGrammarParser.Simple_expContext ctx) {
    RudiTree result;
    switch (ctx.getChildCount()) {
    case 2: // NOT simple_exp
      result = new ExpBoolean(ctx.getChild(1).getText(),
          (RTExpression) this.visit(ctx.getChild(1)), null, "!"); break;
    case 3: // '(' exp ')'
      result = this.visit(ctx.getChild(1)); break;
    case 1: // other expression
      result = this.visit(ctx.getChild(0)); break;
    default: throw new UnsupportedOperationException("How's that possible");
    }
    return result.setPosition(ctx);
  }

  private RudiTree boolExp(ParserRuleContext ctx) {
    RudiTree result;
    if (ctx.getChildCount() == 1) {
      result = this.visit(ctx.getChild(0));
    } else {
      result = new ExpBoolean(ctx.getText(),
          (RTExpression) this.visit(ctx.getChild(0)),
          (RTExpression) this.visit(ctx.getChild(2)),
          ctx.getChild(1).getText()         // operator
          );
    }
    return result.setPosition(ctx);
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
      RudiTree left = this.visit(ctx.getChild(0));
      return new ExpAssignment(ctx.getText(), left,
          (RTExpression) this.visit(ctx.getChild(2)), currentClass).setPosition(ctx);
    } else {  // declaration
      String declaredType = ctx.getChild(0).getText();
      if (declaredType.equals("var")) {
        declaredType = null;
      }
      return new ExpAssignment(ctx.getText(), declaredType, this.visit(ctx.getChild(1)),
          (RTExpression) this.visit(ctx.getChild(3)), currentClass).setPosition(ctx);
    }
  }

  @Override
  public RudiTree visitPropose_statement(RobotGrammarParser.Propose_statementContext ctx) {
    // PROPOSE LPAR propose_arg RPAR statement_block
    return new StatPropose((RTExpression) this.visit(ctx.getChild(2)),
            (StatAbstractBlock) this.visit(ctx.getChild(4))).setPosition(ctx);
  }

  @Override
  public RudiTree visitSwitch_statement(Switch_statementContext ctx) {
    // SWITCH '(' exp ')' '{' switch_block '}'
    return new StatSwitch((RTExpression)visit(ctx.getChild(2)),
        (StatAbstractBlock) visit(ctx.getChild(5))).setPosition(ctx);
  }

  @Override
  public RudiTree visitSwitch_block(Switch_blockContext ctx) {
    // switch_group* switch_label*
    List<RudiTree> elements = new ArrayList<>();
    for (ParseTree t: ctx.children) {
      elements.add(visit(t));
    }
    return new StatAbstractBlock(elements, true).setPosition(ctx);
  }

  @Override
  public RudiTree visitSwitch_group(Switch_groupContext ctx) {
    // switch_label switch_label* statement
    List<RudiTree> elements = new ArrayList<>();
    for (ParseTree t: ctx.children) {
      elements.add(visit(t));
    }
    return new StatAbstractBlock(elements, false).setPosition(ctx);
  }

  @Override
  public RudiTree visitSwitch_label(Switch_labelContext ctx) {
    // CASE string_expression ':' | CASE VARIABLE ':' | DEFAULT ':'
    return new USingleValue(ctx.getText(), "label").setPosition(ctx);
  }

  @Override
  public RudiTree visitIf_statement(RobotGrammarParser.If_statementContext ctx) {
    // IF LPAR boolean_exp RPAR statement (ELSE statement)?
    if (ctx.getChildCount() == 5) {   // no else
      return new StatIf(ctx.getChild(2).getText(), (RTExpression) this.visit(ctx.getChild(2)),
              (RTStatement) this.visit(ctx.getChild(4)), null).setPosition(ctx);
    }
    // if there is an else
    return new StatIf(ctx.getChild(2).getText(), (RTExpression) this.visit(ctx.getChild(2)),
            (RTStatement) this.visit(ctx.getChild(4)),
            (RTStatement) this.visit(ctx.getChild(6))).setPosition(ctx);
  }

  @Override
  public RudiTree visitIf_exp(RobotGrammarParser.If_expContext ctx) {
    // boolean_exp QUESTION exp COLON exp
    return new ExpIf(ctx.getText(), (RTExpression) this.visit(ctx.getChild(0)),
            (RTExpression) this.visit(ctx.getChild(2)),
            (RTExpression) this.visit(ctx.getChild(4))).setPosition(ctx);
  }

  @Override
  public RudiTree visitFor_statement(RobotGrammarParser.For_statementContext ctx) {
    ctx.VARIABLE(0);

    if (ctx.getChild(3).getText().equals(":")) {
      // FOR '(' VARIABLE ':' exp ')' loop_statement_block
      // TODO: or should we check here that the type of the variable in assignment
      // is the type the iterable in exp returns? How?
      RTExpression exp = (RTExpression) this.visit(ctx.getChild(4));
      UVariable var = new UVariable(ctx.getChild(2).getText(), currentClass);
      var.setPosition(ctx.VARIABLE(0));
      return new StatFor2(var, exp,
          (RTStatement) this.visit(ctx.getChild(6)), currentClass).setPosition(ctx);
    } else if (ctx.getChild(4).getText().equals(":")) {
      // with type specification
      UVariable var = new UVariable(ctx.getChild(2).getText(),
          ctx.getChild(3).getText(),
          currentClass);
      var.setPosition(ctx.VARIABLE(0));
      // FOR '(' (DEC_VAR | type_spec) VARIABLE ':' exp ')' loop_statement_block
      return new StatFor2(ctx.getChild(2).getText(), var,
              (RTExpression) this.visit(ctx.getChild(5)),
              (RTStatement) this.visit(ctx.getChild(7)), currentClass).setPosition(ctx);
    } else if (ctx.getChild(2).equals(";") || ctx.getChild(3).equals(";")) {
      // statement looks like "FOR LPAR assignment SEMICOLON exp SEMICOLON exp RPAR loop_statement_block"
      // all expressions are optional!
      int i = 2;
      RudiTree[] forExps = { null, null, null };
      for (int j = 0; j < 3; ++j) {
        if (! ctx.getChild(i).equals(";")) {
          forExps[j] = this.visit(ctx.getChild(i));
          ++i;
        }
        ++i;
      }
      RTStatement block = (RTStatement) this.visit(ctx.getChild(i));

      return new StatFor1((ExpAssignment)forExps[0], (ExpBoolean)forExps[1],
          (RTExpression)forExps[2], block, currentClass).setPosition(ctx);
    } else {
      // statement looks like "FOR LPAR LPAR VARIABLE ( COMMA VARIABLE )+ RPAR COLON exp RPAR loop_statement_block"
      // TODO: implement For3Stat; exp will return some Object[]
      List<String> vars = new ArrayList<String>();
      RudiTree exp = this.visit(ctx.getChild(ctx.getChildCount() - 3));
      for (int i = 3; i < ctx.getChildCount() - 5; i += 2) {
        vars.add(ctx.getChild(i).getText());
      }
      return new StatFor3(vars, exp,
              (RTStatement) this.visit(ctx.getChild(ctx.getChildCount() - 1)),
              currentClass).setPosition(ctx);
    }
  }

  @Override
  public RudiTree visitFunccall_on_object(RobotGrammarParser.Funccall_on_objectContext ctx) {
    // variable DOT function_call
    return new ExpFuncOnObject(ctx.getText(),
        (RTExpression) this.visit(ctx.getChild(0)),
        (RTExpression) this.visit(ctx.getChild(2))).setPosition(ctx);
  }

  @Override
  public RudiTree visitField_access(RobotGrammarParser.Field_accessContext ctx) {
    ArrayList<String> representation = new ArrayList<>();
    ArrayList<RudiTree> parts = new ArrayList<>();
    for (int i = 0; i < ctx.getChildCount(); i += 2) {
      representation.add(ctx.getChild(i).getText());
      parts.add(this.visit(ctx.getChild(i)));
    }
    return new UFieldAccess(parts, representation).setPosition(ctx);
  }

  @Override
  public RudiTree visitLambda_exp(RobotGrammarParser.Lambda_expContext ctx) {
    return new ExpLambda(ctx.getText()).setPosition(ctx);
  }

  @Override
  public RudiTree visitSet_operation(RobotGrammarParser.Set_operationContext ctx) {
    // (VARIABLE | field_access) (ADD | REMOVE) number
    if (ctx.getChild(1).getText().startsWith("-")) {      // remove
      return new StatSetOperation(this.visit(ctx.getChild(0)), false, this.visit(ctx.getChild(2))).setPosition(ctx);
    } else {       // add
      return new StatSetOperation(this.visit(ctx.getChild(0)), true, this.visit(ctx.getChild(2))).setPosition(ctx);
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
    return new StatMethodDeclaration(null, type, funcname, parnames, partypes,
        null, currentClass).setPosition(ctx);
  }

  @Override
  public RudiTree visitVar_def(RobotGrammarParser.Var_defContext ctx) {
    // type_spec VARIABLE SEMICOLON
    // type_spec is either a boring normal type or a type of form Rdf<Child>
    String type = ctx.getChild(0).getText();
    return new StatVarDef(type, ctx.getChild(1).getText(), currentClass).setPosition(ctx);
  }

  @Override
  public RudiTree visitChildren(RuleNode rn) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public RudiTree visitTerminal(TerminalNode tn) {
    return visitTerminalInner(tn).setPosition(tn);
  }

  public RudiTree visitTerminalInner(TerminalNode tn) {
    switch (tn.getSymbol().getType()) {
      case RobotGrammarLexer.NULL:   // token is NULL
        return new USingleValue("null", "Object");
      case RobotGrammarLexer.TRUE:   // token is TRUE
      case RobotGrammarLexer.FALSE:  // token is FALSE
        return new USingleValue(tn.getText(), "boolean");
      case RobotGrammarLexer.CHARACTER:  // token is character
        return new USingleValue(tn.getText(), "char");
      case RobotGrammarLexer.STRING:  // token is String
        return new USingleValue(tn.getText(), "String");
      case RobotGrammarLexer.INT:  // token is int
        return new USingleValue(tn.getText(), "int");
      case RobotGrammarLexer.FLOAT:  // token is float
        return new USingleValue(tn.getText(), "float");
      // An annotation is sth. like @Override (yes, it is a String as long as the
      // representation of Strings has to explicitly have a " to be put in "")
      case RobotGrammarLexer.ANNOTATION:  // token is an annotation
        return new USingleValue(tn.getText() + "\n", "annotation");
      case RobotGrammarLexer.WILDCARD:  //token is wildcard
        return new UWildcard();
      case RobotGrammarLexer.VARIABLE:  // token is variable
        return new UVariable(tn.getText(), currentClass);
      case RobotGrammarLexer.BREAK:
      case RobotGrammarLexer.CONTINUE:
        return new USingleValue(tn.getText(), "break/continue");
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
    // (VARIABLE SMALLER VARIABLE GREATER)? variable ASSIGN LBRACE (VARIABLE (COMMA VARIABLE)*)? SEMICOLON
    if (ctx.getChild(1).getText().equals("=")) {
      // get all the elements to be added to the list
      ArrayList<RTExpression> elements = new ArrayList<>();
      for (int i = 3; i <= ctx.getChildCount() - 2;) {
        elements.add((RTExpression) this.visit(ctx.getChild(i)));
        i += 2;
      }
      return new StatListCreation(ctx.getChild(0).getText(), elements, currentClass).setPosition(ctx);
    } else {
      // get all the elements to be added to the list
      ArrayList<RTExpression> elements = new ArrayList<>();
      for (int i = 7; i <= ctx.getChildCount() - 2;) {
        elements.add((RTExpression) this.visit(ctx.getChild(i)));
        i += 2;
      }
      return new StatListCreation(ctx.getChild(4).getText(), elements,
              currentClass, ctx.getChild(0).getText() + "<"
              + ctx.getChild(2).getText() + ">").setPosition(ctx);
    }
  }

  @Override
  public RudiTree visitVariable(RobotGrammarParser.VariableContext ctx) {
    // VARIABLE | field_access
    return new UVariable(ctx.getText(), currentClass).setPosition(ctx);
  }

  @Override
  public RudiTree visitDa_token(Da_tokenContext ctx) {
    return this.visit(ctx.getChild(ctx.getChildCount() - 1)).setPosition(ctx);
  }

  @Override
  public RudiTree visit(ParseTree pt) {
    return pt.accept(this);
  }
}
