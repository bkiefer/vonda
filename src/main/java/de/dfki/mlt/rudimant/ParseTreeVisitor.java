/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant;

import java.util.ArrayList;
import java.util.List;

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
    //this.memory = new HashMap<String, AbstractType>();
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
//      System.out.println("============================================\n" +
//              ctx.getChild(i).getText());
//      System.out.println("next one ");
    }
    return new GrammarFile(rules);
  }

  @Override
  public RudiTree visitImports(RobotGrammarParser.ImportsContext ctx) {
    // IMPORT VARIABLE SEMICOLON
    String file = ctx.getChild(1).getText();
    return new StatImport(file);
  }

  @Override
  public RudiTree visitMethod_declaration(RobotGrammarParser.Method_declarationContext ctx) {
    // (PUBLIC | PROTECTED | PRIVATE)? (DEC_VAR | VARIABLE) VARIABLE LPAR
    // ((VARIABLE | DEC_VAR) VARIABLE (COMMA (VARIABLE | DEC_VAR) VARIABLE)*) RPAR statement_block

    int hasVis = ctx.getChild(3).getText().equals("(") ? 1 : 0;

    ArrayList<String> parameters = new ArrayList<>();
    ArrayList<String> partypes = new ArrayList<>();
    // get all the parameters of the function
    for (int i = 3 + hasVis; i < ctx.getChildCount() - 2;) {
      partypes.add(ctx.getChild(i).getText());
      parameters.add(ctx.getChild(++i).getText());
      i += 2;
    }
    return new StatMethodDeclaration(
        (hasVis == 0 ? "" : ctx.getChild(0).getText()),
        ctx.getChild(0 + hasVis).getText(),
        ctx.getChild(1 + hasVis).getText(),
        parameters, partypes,
        this.visit(ctx.getChild(ctx.getChildCount() - 1)),
        currentClass);
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
    return new GrammarRule(ruleName, (StatIf) this.visit(ctx.getChild(2)), toplevel);
  }

  @Override
  public RudiTree visitStatement_block(RobotGrammarParser.Statement_blockContext ctx) {
    // '{' (statement)* '}'
    List<RudiTree> statblock = new ArrayList<RudiTree>();
    // statblock.add(this.visit(ctx.getChild(0))); // comment
    for (int i = 1; i < ctx.getChildCount() - 1; i++) {
      statblock.add(this.visit(ctx.getChild(i)));
    }
    return new StatAbstractBlock(statblock, true);
  }

  @Override
  public RudiTree visitStatement(RobotGrammarParser.StatementContext ctx) {
    // (statement_block | (some_statement | exp SEMICOLON)))
    List<RudiTree> statblock = new ArrayList<RudiTree>();
    statblock.add(this.visit(ctx.getChild(0)));
    // statblock.add(this.visit(ctx.getChild(1))); //COMMENT
    if (ctx.getChildCount() > 2) {
      statblock.add(this.visit(ctx.getChild(ctx.getChildCount() - 1)));
    }
    return new StatAbstractBlock(statblock, false);
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
    return (RTExpression)
        this.visit(ctx.getChild(ctx.getChildCount() == 3 ? 1 : 0));
  }

  @Override
  public RudiTree visitLiteral_or_graph_exp(RobotGrammarParser.Literal_or_graph_expContext ctx) {
    // HASH da_token LPAR ( da_token ( COMMA ( da_token ASSIGN da_token) )* ) RPAR
    ArrayList<RTExpression> expList = new ArrayList<RTExpression>();

    for (int i = 5; i < ctx.getChildCount() - 1;) {
      expList.add((RTExpression) this.visit(ctx.getChild(i)));
      i += 2;   // because we aren't interested in commas
    }
    return new ExpDialogueAct((RTExpression)this.visit(ctx.getChild(1)),
        (RTExpression)this.visit(ctx.getChild(3)),
        expList);
  }

  @Override
  public RudiTree visitSimple_exp(RobotGrammarParser.Simple_expContext ctx) {
    if (ctx.getChildCount() == 2) {
      // NOT simple_exp
      return new ExpBoolean(ctx.getChild(1).getText(),
          (RTExpression) this.visit(ctx.getChild(1)), null, "!", true);
    } else if (ctx.getChildCount() == 4) {
      // NOT LPAR boolean_exp RPAR
      return new ExpBoolean(ctx.getChild(2).getText(),
          (RTExpression) this.visit(ctx.getChild(2)), null, "!", true);
    }
    if (ctx.getChildCount() == 3) {
      // '(' exp ')'
      return this.visit(ctx.getChild(1));
    }
    // other expression
    return this.visit(ctx.getChild(0));
  }

  @Override
  public RudiTree visitSimple_b_exp(RobotGrammarParser.Simple_b_expContext ctx) {
    // simple_exp | simple_exp ('==' | '!=' | '<=' | '<' | '>=' | '>') exp

    if (ctx.getText().equals("true") || ctx.getText().equals("false")) {
      return this.visit(ctx.getChild(0));
    }

    return new ExpBoolean(ctx.getText(),
        (RTExpression) this.visit(ctx.getChild(0)),
        (RTExpression) (ctx.getChildCount() == 1 ? null : this.visit(ctx.getChild(2))),
        // comparison operator
        (ctx.getChildCount() == 1 ? null : ctx.getChild(1).getText()),
        false);
  }

  @Override
  public RudiTree visitBoolean_exp(RobotGrammarParser.Boolean_expContext ctx) {
    // bool_and_exp | boolean_exp '||' bool_and_exp

    if (ctx.getChildCount() == 1) {
      return this.visit(ctx.getChild(0));
    }

    return new ExpBoolean(ctx.getText(),
        (RTExpression) this.visit(ctx.getChild(0)),
        (RTExpression) this.visit(ctx.getChild(2)),
        ctx.getChild(1).getText(), true);
  }

  @Override
  public RudiTree visitBool_and_exp(RobotGrammarParser.Bool_and_expContext ctx) {
	  //  simple_b_exp | bool_and_exp '&&' simple_b_exp

    if (ctx.getChildCount() == 1) {
		  return visit(ctx.getChild(0));
	  }
	  // boolean_exp '&&' bool_and_exp
	  return new ExpBoolean(ctx.getText(),
	      (RTExpression) this.visit(ctx.getChild(0)),
	      (RTExpression) this.visit(ctx.getChild(2)),
	      ctx.getChild(1).getText(), true);
  }


  @Override
  public RudiTree visitAssignment(RobotGrammarParser.AssignmentContext ctx) {
    // ((DEC_VAR | VARIABLE)? VARIABLE | field_access) ASSIGN exp
    if (ctx.getChildCount() == 3) { // no declaration
      RudiTree left = this.visit(ctx.getChild(0));
      return new ExpAssignment(left,
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
  public RudiTree visitSwitch_statement(Switch_statementContext ctx) {
    // SWITCH '(' exp ')' '{' switch_block '}'
    return new StatSwitch((RTExpression)visit(ctx.getChild(2)),
        (StatAbstractBlock) visit(ctx.getChild(5)));
  }

  @Override
  public RudiTree visitSwitch_block(Switch_blockContext ctx) {
    // switch_group* switch_label*
    List<RudiTree> elements = new ArrayList<>();
    for (ParseTree t: ctx.children) {
      elements.add(visit(t));
    }
    return new StatAbstractBlock(elements, true);
  }

  @Override
  public RudiTree visitSwitch_group(Switch_groupContext ctx) {
    // switch_label switch_label* statement
    List<RudiTree> elements = new ArrayList<>();
    for (ParseTree t: ctx.children) {
      elements.add(visit(t));
    }
    return new StatAbstractBlock(elements, false);
  }

  @Override
  public RudiTree visitSwitch_label(Switch_labelContext ctx) {
    // CASE string_expression ':' | CASE VARIABLE ':' | DEFAULT ':'
    return new USingleValue(ctx.getText(), "label");
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
  public RudiTree visitIf_exp(RobotGrammarParser.If_expContext ctx) {
    // boolean_exp QUESTION exp COLON exp
    return new ExpIf(ctx.getText(), (RTExpression) this.visit(ctx.getChild(0)),
            (RTExpression) this.visit(ctx.getChild(2)),
            (RTExpression) this.visit(ctx.getChild(4)));
  }

  @Override
  public RudiTree visitFor_statement(RobotGrammarParser.For_statementContext ctx) {
    if (ctx.getChild(3).getText().equals(":")) {
      // FOR '(' VARIABLE ':' exp ')' loop_statement_block
      // TODO: or should we check here that the type of the variable in assignment
      // is the type the iterable in exp returns? How?
      RTExpression exp = (RTExpression) this.visit(ctx.getChild(4));
      return new StatFor2(new UVariable(ctx.getChild(2).getText(), currentClass,
              currentTRule), exp,
              (StatAbstractBlock) this.visit(ctx.getChild(6)), currentClass);
    } else if (ctx.getChild(4).getText().equals(":")) {
      // FOR '(' (DEC_VAR | type_spec) VARIABLE ':' exp ')' loop_statement_block
      return new StatFor2(ctx.getChild(2).getText(),
              new UVariable(ctx.getChild(3).getText(), currentClass, currentTRule),
              (RTExpression) this.visit(ctx.getChild(5)),
              (StatAbstractBlock) this.visit(ctx.getChild(7)), currentClass);
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
      StatAbstractBlock block = (StatAbstractBlock) this.visit(ctx.getChild(i));

      return new StatFor1((ExpAssignment)forExps[0], (ExpBoolean)forExps[1],
          (RTExpression)forExps[2], block, currentClass);
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
    // variable DOT function_call
    //throw new UnsupportedOperationException("Yay, I found a function: ");
    return new ExpFuncOnObject((RTExpression) this.visit(ctx.getChild(0)),
            (RTExpression) this.visit(ctx.getChild(2)), ctx.getText().replace("^", ""));
  }

  @Override
  public RudiTree visitField_access(RobotGrammarParser.Field_accessContext ctx) {
    ArrayList<String> representation = new ArrayList<>();
    ArrayList<RudiTree> parts = new ArrayList<>();
    for (int i = 0; i < ctx.getChildCount(); i += 2) {
      representation.add(ctx.getChild(i).getText());
      parts.add(this.visit(ctx.getChild(i)));
    }
    return new UFieldAccess(parts, representation);
  }

  @Override
  public RudiTree visitLambda_exp(RobotGrammarParser.Lambda_expContext ctx) {
    return new ExpLambda(ctx.getText());
  }

  /*
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
  */

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
    switch (tn.getSymbol().getType()) {
      case RobotGrammarLexer.NULL:   // token is NULL
        return new USingleValue("null", "Object");
      case RobotGrammarLexer.TRUE:   // token is TRUE
        return new USingleValue(tn.getText(), "boolean");
      case RobotGrammarLexer.FALSE:  // token is FALSE
        return new USingleValue(tn.getText(), "boolean");
      case RobotGrammarLexer.CHARACTER:  // token is character
        return new USingleValue(tn.getText(), "char");
      case RobotGrammarLexer.STRING:  // token is String
        return new USingleValue(tn.getText(), "String");
      // An annotation is sth. like @Override (yes, it is a String as long as the
      // representation of Strings has to explicitly have a " to be put in "")
      case RobotGrammarLexer.ANNOTATION:  // token is an annotation
        return new USingleValue(tn.getText() + "\n", "annotation");
      case RobotGrammarLexer.WILDCARD:  //token is wildcard
        return new UWildcard();
      case RobotGrammarLexer.VARIABLE:  // token is variable
        String t = tn.getText();
        // t = t.replace("^", "");
        //System.out.println(currentClass);
        return new UVariable(t, currentClass, currentTRule);
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
      case RobotGrammarLexer.INT:  // token is int
        return new USingleValue(tn.getText(), "int");
      case RobotGrammarLexer.FLOAT:  // token is float
        return new USingleValue(tn.getText(), "float");
      case RobotGrammarLexer.BREAK:
      case RobotGrammarLexer.CONTINUE:
        return new USingleValue(tn.getText(), "break/continue");
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
    // (VARIABLE SMALLER VARIABLE GREATER)? variable ASSIGN LBRACE (VARIABLE (COMMA VARIABLE)*)? SEMICOLON
    if (ctx.getChild(1).getText().equals("=")) {
// get all the elements to be added to the list
      ArrayList<RTExpression> elements = new ArrayList<>();
      for (int i = 3; i <= ctx.getChildCount() - 2;) {
        elements.add((RTExpression) this.visit(ctx.getChild(i)));
        i += 2;
      }
      return new StatListCreation(ctx.getChild(0).getText(), elements, currentClass);
    } else {
// get all the elements to be added to the list
      ArrayList<RTExpression> elements = new ArrayList<>();
      for (int i = 7; i <= ctx.getChildCount() - 2;) {
        elements.add((RTExpression) this.visit(ctx.getChild(i)));
        i += 2;
      }
      return new StatListCreation(ctx.getChild(4).getText(), elements,
              currentClass, ctx.getChild(0).getText() + "<"
              + ctx.getChild(2).getText() + ">");
    }
  }

  @Override
  public RudiTree visitVariable(RobotGrammarParser.VariableContext ctx) {
    // VARIABLE | field_access
    return new UVariable(ctx.getText(), currentClass, currentTRule);
  }

  @Override
  public RudiTree visitDa_token(Da_tokenContext ctx) {
    return this.visit(ctx.getChild(ctx.getChildCount() - 1));
  }

}
