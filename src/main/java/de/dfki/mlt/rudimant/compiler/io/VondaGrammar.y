/* -*- Mode: Java -*- */

%code imports {

import java.io.Reader;
import java.util.*;
import de.dfki.mlt.rudimant.compiler.Type;
import de.dfki.mlt.rudimant.compiler.Position;
import de.dfki.mlt.rudimant.compiler.tree.*;

@SuppressWarnings({"serial", "unchecked", "fallthrough", "unused"})
}

%language "Java"

%type <String> visibility_spec
%type <LinkedList<RudiTree>> grammar_file
%type <LinkedList<RTExpression>> nonempty_exp_list nonempty_args_list
%type <LinkedList<RTExpression>> field_access_rest da_args
%type <RTExpression> exp assgn_exp lambda_exp ConditionalExpression assignment
%type <RTExpression> ConditionalOrExpression ConditionalAndExpression
%type <RTExpression> dialogueact_exp InclusiveOrExpression
%type <RTExpression> ExclusiveOrExpression AndExpression EqualityExpression
%type <RTExpression> RelationalExpression AdditiveExpression
%type <RTExpression> MultiplicativeExpression CastExpression
%type <RTExpression> LogicalUnaryExpression UnaryExpression PostfixExpression
%type <RTExpression> PrimaryExpression NotJustName ComplexPrimary new_exp
%type <RTExpression> ComplexPrimaryNoParenthesis field_access ArrayAccess
%type <RTExpression> function_call simple_nofa_exp da_token
%type <StatVarDef> var_def
%type <RTStatement> statement_no_def statement blk_statement set_operation
%type <RTStatement> return_statement propose_statement timeout_statement
%type <RTStatement> timeout_behaviour_statement while_statement for_statement
%type <RTStatement>  label_statement switch_statement
%type <StatAbstractBlock> block opt_block
%type <StatIf> if_statement
%type <Import> imports
%type <StatMethodDeclaration> method_declaration
%type <List<String>> path
%type <LinkedList<RTStatement>> statements
%type <StatGrammarRule> grammar_rule
%type <Type> type_spec class_spec
%type <LinkedList<Type>> type_spec_list
%type <ExpSingleValue> Literal
%type <LinkedList> args_list


%locations

%define package "de.dfki.mlt.rudimant.compiler.io"

%define public

%define parser_class_name {VondaGrammar}

%define parse.error verbose

%code {
  private List<RudiTree> _statements = new LinkedList<>();

  private GrammarFile _result = new GrammarFile(_statements);

  public GrammarFile getResult() { return _result; }

  public <T extends RudiTree> T setPos(T rt, Location l) {
    rt.setPos(l, this);
    return rt;
  }

  public <T extends RudiTree> T setPos(T rt, Location start, Location end) {
    rt.setPos(start, end, this);
    return rt;
  }

  public String getFullText(Position start, Position end) {
    return ((VondaLexer)this.yylexer).getFullText(start, end);
  }

  // if the left part is a variable, this must be transformed to StatVarDef
  private RudiTree getAssignmentStat(RTExpression assign) {
    assert(assign instanceof ExpAssignment);
    if (((ExpAssignment)assign).leftIsVariable()) {
      return new StatVarDef(false, new Type(null), assign);
    }
    return new StatExpression(assign);
  }

  // generic method
  private ExpAssignment createPlusMinus(RTExpression variable, String plusOrMinus,
                                         Location loc) {
    ExpSingleValue es = setPos(new ExpSingleValue("1", "int"), loc);
    ExpArithmetic ar = setPos(new ExpArithmetic(variable, es, plusOrMinus), loc);
    ExpAssignment ass = setPos(new ExpAssignment(variable, ar), loc);
    return ass;
  }
}

// keywords
%token BREAK
%token CANCEL
%token CANCEL_ALL
%token CASE
%token CONTINUE
%token DEFAULT
%token DO
%token ELSE
%token FINAL
%token FOR
%token IF
%token IMPORT
%token NEW
%token NULL
%token PRIVATE
%token PROPOSE
%token PROTECTED
%token PUBLIC
%token RETURN
%token SWITCH
%token TIMEOUT
%token TIMEOUT_BEHAVIOUR
%token WHILE

%token ARROW
%token ANDAND
%token OROR
%token EQEQ
%token NOTEQ
%token GTEQ
%token LTEQ
%token MINUSEQ
%token PLUSEQ
%token MINUSMINUS
%token PLUSPLUS

// real tokens
%token < ExpSingleValue > STRING
%token < String > WILDCARD
%token < ExpSingleValue > INT
%token < ExpSingleValue > BOOL_LITERAL
%token < String > VARIABLE
%token < ExpSingleValue > OTHER_LITERAL

%%

// start rule
grammar_file
  : visibility_spec method_declaration grammar_file {
    $$ = $3; $2.setVisibility($1); $3.addFirst($2);
  }
  | method_declaration grammar_file { $$ = $2; $2.addFirst($1); }
  | statement_no_def grammar_file { $$ = $2; $2.addFirst($1); }
  // | ANNOTATION grammar_file { $$ = $2; $2.add($1); }
  | imports grammar_file { $$ = $2; $2.addFirst($1); }
  | visibility_spec var_def grammar_file  {
    $$ = $3; $3.addFirst(setPos(new StatFieldDef($1, $2), @1, @2));
  }
  | var_def grammar_file  {
    $$ = $2; $2.addFirst(setPos(new StatFieldDef(null, $1), @1));
  }
  | %empty { $$ = _statements;}
  ;

visibility_spec
  : PUBLIC { $$ = "public"; }
  | PROTECTED { $$ = "protected"; }
  | PRIVATE  { $$ = "private"; }
  ;

imports
  : IMPORT path ';' {
    List<String> path = $2;
    String name = path.remove(path.size() - 1);
    $$ = setPos(new Import(name, path.toArray(new String[path.size()])), @$);
  }
  ;

path
  : VARIABLE { $$ = new ArrayList<String>(){{ add($1); }}; }
  | path '.' VARIABLE { $$ = $1; $1.add($3); }
  ;

statement_no_def
  : block { $$ = $1; }
  | assignment ';' { $$ = setPos(getAssignmentStat($1), @$); }
  | field_access ';' { $$ = setPos(new StatExpression($1), @$); }
  | PLUSPLUS UnaryExpression ';' {
    $$ = setPos(new StatExpression(createPlusMinus($2, "+", @$)), @$);
  }
  | MINUSMINUS UnaryExpression ';' {
    $$ = setPos(new StatExpression(createPlusMinus($2, "+", @$)), @$);
  }
  | function_call ';' { $$ = setPos(new StatExpression($1), @$); }
  | grammar_rule { $$ = $1; }
  | set_operation { $$ = $1; }
  | return_statement { $$ = $1; }
  | propose_statement { $$ = $1; }
  | timeout_statement { $$ = $1; }
  | timeout_behaviour_statement { $$ = $1; }
  | if_statement { $$ = $1; }
  | while_statement { $$ = $1; }
  | for_statement { $$ = $1; }
  | switch_statement { $$ = $1; }
  | label_statement { $$ = $1; }
  ;

statement
  : statement_no_def { $$ = $1; }
  | var_def { $$ = $1; }
  ;

blk_statement
  : statement { $$ = $1; }
  ;

////////// STATEMENTS ///////////////////

block
  : '{' statements '}' { $$ = setPos(new StatAbstractBlock($2, true), @$); }
  | '{' '}' {
    $$ = setPos(new StatAbstractBlock(new ArrayList<RTStatement>(), true), @$);
  }

statements: blk_statement { $$ = new LinkedList<RTStatement>(){{ add($1); }}; }
  | blk_statement statements { $$ = $2; $2.addFirst($1); }
  ;

grammar_rule
  : VARIABLE ':' if_statement { $$ = setPos(new StatGrammarRule($1, $3), @$); }
  ;

return_statement
  : RETURN ';' { $$ = setPos(new StatReturn("return"), @$); }
  | RETURN exp ';' { $$ = setPos(new StatReturn($2), @$); }
  | BREAK ';' { $$ = setPos(new StatReturn("break"), @$); }
  | BREAK VARIABLE ';' { $$ = setPos(new StatReturn("break", $2), @$); }
  | CANCEL ';' { $$ = setPos(new StatReturn("cancel"), @$); }
  | CANCEL_ALL ';' { $$ = setPos(new StatReturn("cancel_all"), @$); }
  | CONTINUE ';' { $$ = setPos(new StatReturn("continue"), @$); }
  ;

if_statement
  : IF '(' exp ')' statement { $$ = setPos(new StatIf($3, $5, null), @$); }
  | IF '(' exp ')' statement ELSE statement {
    $$ = setPos(new StatIf($3, $5, $7), @$);
  }
  ;

while_statement
  : WHILE '(' exp ')' statement { $$ = setPos(new StatWhile($3, $5, true), @$); }
  | DO statement WHILE '(' exp ')' {
    $$ = setPos(new StatWhile($5, $2, false), @$);
  }
  ;

for_statement
  : FOR '(' var_def exp ';' exp ')' statement {
    $$ = setPos(new StatFor1($3, $4, $6, $8), @$); }
  | FOR '(' var_def     ';' exp ')' statement {
    $$ = setPos(new StatFor1($3, null, $5, $7), @$); }
  | FOR '(' var_def exp ';'     ')' statement {
    $$ = setPos(new StatFor1($3, $4, null, $7), @$); }
  | FOR '(' var_def     ';'     ')' statement {
    $$ = setPos(new StatFor1($3, null, null, $6), @$); }
  | FOR '('     ';' exp ';' exp ')' statement {
    $$ = setPos(new StatFor1(null, $4, $6, $8), @$); }
  | FOR '(' VARIABLE ':' exp ')' statement {
    ExpVariable var = setPos(new ExpVariable($3), @3);
    $$ = setPos(new StatFor2(var, $5, $7), @$);
  }
  | FOR '(' type_spec VARIABLE ':' exp ')' statement {
    ExpVariable var = setPos(new ExpVariable($4), @4);
    $$ = setPos(new StatFor2($3, var, $6, $8), @$);
  }
  // for loop with destructuring into a tuple
  //| FOR '(' '(' VARIABLE ( ',' VARIABLE )+ ')' ':' exp ')' statement {}
  ;

propose_statement
  : PROPOSE '(' exp ')' block { $$ = setPos(new StatPropose($3, $5), @$); }
  ;

timeout_behaviour_statement
  : TIMEOUT_BEHAVIOUR '(' exp ')' block {
    $$ = setPos(new StatTimeout(null, $3, $5), @$);
  }
  ;

timeout_statement
  : TIMEOUT '(' exp ',' exp ')' block {
    $$ = setPos(new StatTimeout($3, $5, $7), @$);
  }
  ;

switch_statement
  : SWITCH '(' exp ')' block {
    $$ = setPos(new StatSwitch($3, $5), @$);
  }
  ;

/*
switch_block
  : switch_labels switch_cont {
    List<RTStatement> elts = $1;
    elts.addAll($2);
    $$ = setPos(new StatAbstractBlock(elts, false), @$);
  }
  ;

switch_cont
  : statements switch_labels { }
  | %empty { $$ = new ArrayList<RTExpression>(); }
  ;

switch_labels
  : switch_label switch_labels { $$ = $2; $2.addFirst($1); }
  | switch_label { $$ = new LinkedList<RTStatement>(){{ add($1); }}; }
  ;
*/

label_statement
  : CASE STRING ':'   {
    ExpSingleValue val =
      new ExpSingleValue("case " + $2.toString() + ":", "label");
    RTStatement lbl = val.ensureStatement();
    setPos(val, @$); setPos(lbl, @$);
    $$ = lbl;
  }
  | CASE INT ':'      {
    ExpSingleValue val =
      new ExpSingleValue("case " + $2.toString() + ":", "label");
    RTStatement lbl = val.ensureStatement();
    setPos(val, @$); setPos(lbl, @$);
    $$ = lbl;
  }
  | CASE BOOL_LITERAL ':'      {
    ExpSingleValue val =
      new ExpSingleValue("case " + $2.toString() + ":", "label");
    RTStatement lbl = val.ensureStatement();
    setPos(val, @$); setPos(lbl, @$);
    $$ = lbl;
  }
  | CASE VARIABLE ':' {
    ExpSingleValue val =
      new ExpSingleValue("case " + $2 + ":", "label");
    RTStatement lbl = val.ensureStatement();
    setPos(val, @$); setPos(lbl, @$);
    $$ = lbl;
  }
  | DEFAULT ':'       {
    ExpSingleValue val = new ExpSingleValue("default:", "label");
    RTStatement lbl = val.ensureStatement();
    setPos(val, @$); setPos(lbl, @$);
    $$ = lbl;
  }
  ;

var_def
  : FINAL VARIABLE assgn_exp ';' {
    $$ = setPos(new StatVarDef(true, new Type(null), $2, $3), @$);
  }
  | type_spec VARIABLE assgn_exp ';' {
    $$ = setPos(new StatVarDef(false, $1, $2, $3), @$);
  }
  | FINAL type_spec VARIABLE assgn_exp ';' {
    $$ = setPos(new StatVarDef(true, $2, $3, $4), @$);
  }
  | VARIABLE assgn_exp ';' {
    $$ = setPos(new StatVarDef(false, new Type(null), $1, $2), @$);
  }
  | FINAL VARIABLE ';' {
    $$ = setPos(new StatVarDef(true, new Type(null), $2, null), @$);
  }
  | type_spec VARIABLE ';' {
    $$ = setPos(new StatVarDef(false, $1, $2, null), @$);
  }
  | FINAL type_spec VARIABLE ';' {
    $$ = setPos(new StatVarDef(true, $2, $3, null), @$);
  }
  ;

assgn_exp
  : '=' exp { $$ = $2; }
  | '=' '{' '}' {
    $$ = setPos(new ExpListLiteral(new LinkedList<RTExpression>()), @2, @3);
  }
  | '=' '{' nonempty_exp_list '}' {
    $$ = setPos(new ExpListLiteral($3), @2, @4);
  }
  ;

nonempty_exp_list
  : exp { $$ = new LinkedList<RTExpression>(){{ add($1); }}; }
  | exp ',' nonempty_exp_list { $$ = $3; $3.addFirst($1); }
  ;

method_declaration
  : class_spec type_spec VARIABLE '(' ')' opt_block {
    $$ = setPos(new StatMethodDeclaration("public", $2, $1, $3, null, $6), @$);
  }
  | class_spec type_spec VARIABLE '(' args_list ')' opt_block {
    $$ = setPos(new StatMethodDeclaration("public", $2, $1, $3, $5, $7), @$);
  }
  | class_spec           VARIABLE '(' ')' opt_block {
    $$ = setPos(new StatMethodDeclaration("public", null, $1, $2, null, $5), @$);
  }
  | class_spec           VARIABLE '(' args_list ')' opt_block {
    $$ = setPos(new StatMethodDeclaration("public", null, $1, $2, $4, $6), @$);
  }
  |            type_spec VARIABLE '(' ')' opt_block {
    $$ = setPos(new StatMethodDeclaration("public", $1, null, $2, null, $5), @$);
  }
  |            type_spec VARIABLE '(' args_list ')' opt_block {
    $$ = setPos(new StatMethodDeclaration("public", $1, null, $2, $4, $6), @$);
  }
  |                      VARIABLE '(' ')' block {
    $$ = setPos(new StatMethodDeclaration("public", null, null, $1, null, $4), @$);
  }
  |                      VARIABLE '(' args_list ')' block {
    $$ = setPos(new StatMethodDeclaration("public", null, null, $1, $3, $5), @$);
  }
  ;

class_spec: '[' type_spec ']' '.' { $$ = $2; }
  ;

opt_block
  : block { $$ = $1; }
  | ';' { $$ = null; }
  ;

args_list
  : VARIABLE { $$ = new LinkedList(){{ add($1); }}; }
  | type_spec VARIABLE { $$ = new LinkedList(){{ add($1); add($2); }}; }
  | VARIABLE ',' args_list { $$ = $3; $3.addFirst($1); }
  | type_spec VARIABLE ',' args_list {
    $$ = $4; $4.addFirst($2); $4.addFirst($1);
  }
  ;


// add sth to a collection
set_operation
  : VARIABLE PLUSEQ exp ';' {
    ExpVariable var = setPos(new ExpVariable($1), @1);
    $$ = setPos(new StatSetOperation(var, true, $3), @$);
  }
  | VARIABLE MINUSEQ exp ';' {
    ExpVariable var = setPos(new ExpVariable($1), @1);
    $$ = setPos(new StatSetOperation(var, false, $3), @$);
  }
  | ArrayAccess PLUSEQ exp ';' {
    $$ = setPos(new StatSetOperation($1, true, $3), @$);
  }
  | ArrayAccess MINUSEQ exp ';' {
    $$ = setPos(new StatSetOperation($1, false, $3), @$);
  }
  | field_access PLUSEQ exp ';' {
    $$ = setPos(new StatSetOperation($1, true, $3), @$);
  }
  | field_access MINUSEQ exp ';' {
    $$ = setPos(new StatSetOperation($1, false, $3), @$);
  }
  ;


////////////////// EXPRESSIONS ////////////////////////////////

function_call
  : VARIABLE '(' ')' {
    $$ = setPos(new ExpFuncCall($1, new LinkedList<RTExpression>(), false), @$);
  }
  | VARIABLE '(' nonempty_args_list ')'  {
    $$ = setPos(new ExpFuncCall($1, $3, false), @$);
  }
  ;

nonempty_args_list
  : exp { $$ = new LinkedList<RTExpression>(){{ add($1); }}; }
  | lambda_exp { $$ = new LinkedList<RTExpression>(){{ add($1); }}; }
  | exp ',' nonempty_args_list { $$ = $3; $3.addFirst($1); }
  | lambda_exp ',' nonempty_args_list { $$ = $3; $3.addFirst($1); }
  ;

type_spec
  : VARIABLE '[' ']' { $$ = new Type("Array", new Type($1)); }
  | VARIABLE { $$ = new Type($1); }
  | VARIABLE '<' type_spec_list '>' {
    $$ = new Type($1, $3.toArray(new Type[$3.size()]));
  }
  ;

type_spec_list
  : type_spec { $$ = new LinkedList<Type>(){{ add($1); }}; }
  | type_spec ',' type_spec_list { $$ = $3; $3.addFirst($1); }
  ;

exp
  : ConditionalExpression { $$ = $1; }
  | assignment { $$ = $1; }
  | ConditionalOrExpression { $$ = $1; }
//| dialogueact_exp { $$ = $1; }
  ;

ConditionalOrExpression
  : ConditionalOrExpression OROR ConditionalAndExpression {
    $$ = setPos(new ExpBoolean($1, $3, "||"), @$);
  }
  | ConditionalAndExpression { $$ = $1; }
  ;

ConditionalAndExpression
  : ConditionalAndExpression ANDAND InclusiveOrExpression {
    $$ = setPos(new ExpBoolean($1, $3, "&&"), @$);
  }
  | InclusiveOrExpression { $$ = $1; }
  ;

InclusiveOrExpression
  : ExclusiveOrExpression { $$ = $1; }
  | InclusiveOrExpression '|' ExclusiveOrExpression {
    $$ = setPos(new ExpArithmetic($1, $3, "|"), @$);
  }
  ;

ExclusiveOrExpression
  : AndExpression { $$ = $1; }
  | ExclusiveOrExpression '^' AndExpression {
    $$ = setPos(new ExpArithmetic($1, $3, "^"), @$);
  }
  ;

AndExpression
  : EqualityExpression { $$ = $1; }
  | AndExpression '&' EqualityExpression {
    $$ = setPos(new ExpArithmetic($1, $3, "&"), @$);
  }
  ;

EqualityExpression
  : RelationalExpression { $$ = $1; }
  | EqualityExpression EQEQ RelationalExpression {
    $$ = setPos(new ExpBoolean($1, $3, "=="), @$);
  }
  | EqualityExpression NOTEQ RelationalExpression {
    $$ = setPos(new ExpBoolean($1, $3, "!="), @$);
  }
  ;

RelationalExpression
  : AdditiveExpression { $$ = $1; }
  | RelationalExpression '<' AdditiveExpression {
    $$ = setPos(new ExpBoolean($1, $3, "<"), @$);
  }
  | RelationalExpression '>' AdditiveExpression {
    $$ = setPos(new ExpBoolean($1, $3, ">"), @$);
  }
  | RelationalExpression GTEQ AdditiveExpression {
    $$ = setPos(new ExpBoolean($1, $3, ">="), @$);
  }
  | RelationalExpression LTEQ AdditiveExpression {
    $$ = setPos(new ExpBoolean($1, $3, "<="), @$);
  }
  ;

AdditiveExpression
  : MultiplicativeExpression { $$ = $1; }
  | AdditiveExpression '+' MultiplicativeExpression {
    $$ = setPos(new ExpArithmetic($1, $3, "+"), @$);
  }
  | AdditiveExpression '-' MultiplicativeExpression {
    $$ = setPos(new ExpArithmetic($1, $3, "-"), @$);
  }
  ;

MultiplicativeExpression
  : CastExpression { $$ = $1; }
  | MultiplicativeExpression '*' CastExpression {
    $$ = setPos(new ExpArithmetic($1, $3, "*"), @$);
  }
  | MultiplicativeExpression '/' CastExpression {
    $$ = setPos(new ExpArithmetic($1, $3, "/"), @$);
  }
  | MultiplicativeExpression '%' CastExpression {
    $$ = setPos(new ExpArithmetic($1, $3, "%"), @$);
  }
  ;

UnaryExpression
  : PLUSPLUS UnaryExpression {
    $$ = createPlusMinus($2, "+", @$);
  }
  | MINUSMINUS UnaryExpression {
    $$ = createPlusMinus($2, "-", @$);
  }
  | '+' CastExpression { $$ = setPos(new ExpArithmetic($2, null, "+"), @$); }
  | '-' CastExpression { $$ = setPos(new ExpArithmetic($2, null, "-"), @$); }
  | LogicalUnaryExpression { $$ = $1; }
;

CastExpression
  : UnaryExpression { $$ = $1; }
  | '(' type_spec ')' CastExpression { $$ = setPos(new ExpCast($2, $4), @$); }
  ;
//  | '(' exp ')' LogicalUnaryExpression { new ExpCast($2, $4); }

LogicalUnaryExpression
  : PostfixExpression { $$ = $1; }
  | '!' UnaryExpression { $$ = setPos(new ExpBoolean($2, null, "!"), @$); }
  | '~' UnaryExpression { $$ = setPos(new ExpArithmetic($2, null, "~"), @$); }
  ;

PostfixExpression
  : PrimaryExpression { $$ = $1; }
  | PostfixExpression PLUSPLUS {
    $$ = createPlusMinus($1, "+", @$);
  }
  | PostfixExpression MINUSMINUS {
    $$ = createPlusMinus($1, "-", @$);
  }
  ;

PrimaryExpression
  : NULL { $$ = setPos(new ExpSingleValue("null", "null"), @$); }
  | NotJustName { $$ = $1; }
  | ComplexPrimary { $$ = $1; }
  ;

NotJustName
  : VARIABLE { $$ = setPos(new ExpVariable($1), @$); }
  | new_exp { $$ = $1; }
  | '(' '(' type_spec ')' CastExpression ')' { $$ = setPos(new ExpCast($3, $5), @$); }
  ;

ComplexPrimary
  : '(' exp ')' { $$ = $2; }
  | ComplexPrimaryNoParenthesis { $$ = $1; }
  ;

ComplexPrimaryNoParenthesis
  : Literal { $$ = $1; }
  | ArrayAccess { $$ = $1; }
  | field_access { $$ = $1; }
  | function_call { $$ = $1; }
  | dialogueact_exp { $$ = $1; }
  ;

Literal
  : STRING { $$ = $1; setPos($1, @$); }
  | INT { $$ = $1; setPos($1, @$); }
  | OTHER_LITERAL { $$ = $1; setPos($1, @$); }
  | BOOL_LITERAL { $$ = $1; setPos($1, @$); }
  ;

ArrayAccess
  : VARIABLE '[' exp ']' {
    ExpVariable var = setPos(new ExpVariable($1), @1);
    $$ = setPos(new ExpArrayAccess(var, $3), @$);
  }
  | ComplexPrimary '[' exp ']' { $$ = setPos(new ExpArrayAccess($1, $3), @$); }
  ;

ConditionalExpression
  : ConditionalOrExpression '?' exp ':' exp { $$ = setPos(new ExpConditional($1, $3, $5), @$); }
  ;

// TODO: is that all 'what you can assign to' (an lvalue), which can be:
// a variable, an array element, an rdf slot (did i forget sth?)
assignment
  : VARIABLE assgn_exp {
    ExpVariable var = setPos(new ExpVariable($1), @1);
    $$ = setPos(new ExpAssignment(var, $2), @$);
  }
  | field_access assgn_exp { $$ = setPos(new ExpAssignment($1, $2), @$); }
  | ArrayAccess assgn_exp { $$ = setPos(new ExpAssignment($1, $2), @$); }
  ;

field_access
  : NotJustName field_access_rest {
    //List<String> repr = new ArrayList<>($2.size());
    //for (int i = 0; i < $2.size(); ++i) repr.add("");
    //$$ = setPos(new ExpFieldAccess($2, repr), @$); $2.addFirst($1);
    $$ = setPos(new ExpFieldAccess($2), @$); $2.addFirst($1);
  }
  | function_call field_access_rest { $$ = setPos(new ExpFieldAccess($2), @$); $2.addFirst($1); }
//  | CastExpression field_access_rest { $$ = setPos(new ExpFieldAccess($2), @$); $2.addFirst($1); }
  ;

field_access_rest
  : '.' simple_nofa_exp field_access_rest { $$ = $3; $3.addFirst($2); }
  | '.' simple_nofa_exp { $$ = new LinkedList<RTExpression>(){{ add($2); }}; }
  ;

simple_nofa_exp
  : VARIABLE { $$ = setPos(new ExpVariable($1), @$); }
  | function_call { $$ = $1; }
  | '(' exp ')' { $$ = $2; }
  ;

new_exp
  : NEW VARIABLE { $$ = setPos(new ExpNew(new Type($2)), @$); }
  | NEW VARIABLE '(' ')' {
    $$ = setPos(new ExpNew(new Type($2), new LinkedList<>()), @$);
  }
  | NEW VARIABLE '(' nonempty_exp_list ')' {
    $$ = setPos(new ExpNew(new Type($2), $4), @$);
  }
  | NEW VARIABLE '[' exp ']' {
    $$ = setPos(new ExpNew(new Type("Array", new Type($2)),
                    new LinkedList<RTExpression>(){{ add($4); }}), @$);
  }
  | NEW VARIABLE '[' ']' '(' exp ')'{
    $$ = setPos(new ExpNew(new Type("Array", new Type($2)),
                    new LinkedList<RTExpression>(){{ add($6); }}), @$);
  }
  | NEW VARIABLE '<' type_spec_list '>' '(' ')' {
    $$ = setPos(new ExpNew(new Type($2, $4.toArray(new Type[$4.size()])),
                    new LinkedList<>()), @$);
  }
  | NEW VARIABLE '<' type_spec_list '>' '(' nonempty_exp_list ')' {
    $$ = setPos(new ExpNew(new Type($2, $4.toArray(new Type[$4.size()])),
                    $7), @$);
  }
  ;

lambda_exp
  : '(' args_list ')' ARROW exp {
    $$ = setPos(new ExpLambda($2, $5), @$);
  }
  | '(' args_list ')' ARROW block {
    $$ = setPos(new ExpLambda($2, $5), @$);
  }
  | '(' ')' ARROW exp {
    $$ = setPos(new ExpLambda(new LinkedList<>(), $4), @$);
  }
  | '(' ')' ARROW block {
    $$ = setPos(new ExpLambda(new LinkedList<>(), $4), @$);
  }
  ;


dialogueact_exp
  : '#' da_token '(' da_token ')' {
    $$ = setPos(new ExpDialogueAct($2, $4, new LinkedList<RTExpression>()), @$);
  }
  | '#' da_token '(' da_token da_args ')' {
    $$ = setPos(new ExpDialogueAct($2, $4, $5), @$);
  }
  ;

da_token
  : '{' exp '}' { $$ = $2; }
  | VARIABLE { $$ = setPos(new ExpVariable($1), @$); }
  | STRING { $$ = $1; }
  | WILDCARD { $$ = setPos(new ExpSingleValue($1, "String"), @$); }
  ;

da_args
  : ',' da_token '=' da_token da_args  {
    $$ = $5; $5.addFirst($4); $5.addFirst($2);
  }
  | %empty { $$ = new LinkedList<RTExpression>(); }
  ;



/*
 * LEXER
 */
/*
/// keywords + true, false, null:
IMPORT: 'import';
IF: 'if';
ELSE: 'else';
WHILE: 'while';
DO: 'do';
SWITCH: 'switch';
CASE: 'case';
DEFAULT: 'default';
CONTINUE: 'continue';
BREAK: 'break';
CANCEL: 'cancel';
CANCEL_ALL: 'cancel_all';
FOR: 'for';
NULL: 'null';
TRUE: 'true';
FALSE: 'false';
RETURN: 'return';
PUBLIC: 'public';
PROTECTED: 'protected';
PRIVATE: 'private';
NEW: 'new';
FINAL: 'final';
DEC_VAR: 'var';

/// character literal (starting with ' ):
CHARACTER: '\''.'\'';

/// string (starting with " ):
STRING: '\"'(~('\\'|'"')|'\\"')*'\"';

// TODO: WHAT IS THIS GOOD FOR ?
ANNOTATION: '@'('0'..'9'|'A'..'z'|'_'|'('|')')+;

/// special for dialogue grammar:
WILDCARD: '_';
PROPOSE: 'propose';
TIMEOUT: 'timeout';
TIMEOUT_BEHAVIOUR: 'behaviour_timeout';
*/
/// comments (starting with /* or //):
//JAVA_CODE: '/*@'.*?'@*/' -> channel(HIDDEN);
//ONE_L_COMMENT: '//'.*?'\n' -> channel(HIDDEN);
//MULTI_L_COMMENT: '/*'.*?'*/' -> channel(HIDDEN);
/*
/// whitespace
WS: [ \t\u000C]+ -> channel(HIDDEN);
NLWS: [\n\r]+ -> channel(HIDDEN);

// LETTER: ('A'..'Z'|'a'..'z');
/// identifiers (starting with "java letter"):
VARIABLE: ('A'..'Z'|'a'..'z'|'_')('0'..'9'|'A'..'Z'|'a'..'z'|'_'|'$')*;

/// numeric literal (starting with - or number):
INT: ('-')?('1'..'9')?('0'..'9')+;
FLOAT: ('-')?('0'('.'('0'..'9')+) | ('1'..'9')('0'..'9')*('.'('0'..'9')+));
*/
