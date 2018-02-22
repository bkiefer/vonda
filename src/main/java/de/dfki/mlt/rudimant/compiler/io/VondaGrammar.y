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
%type <RTExpression> exp assgn_exp lambda_exp if_exp assignment
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
%type <RTStatement> statement blk_statement set_operation
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
%token < String > VARIABLE
%token < ExpSingleValue > OTHER_LITERAL

%%

// start rule
grammar_file
  : visibility_spec method_declaration grammar_file {
    $$ = $3; $2.setVisibility($1); $3.addFirst($2);
  }
  | method_declaration grammar_file { $$ = $2; $2.addFirst($1); }
  | statement grammar_file { $$ = $2; $2.addFirst($1); }
  // | ANNOTATION grammar_file { $$ = $2; $2.add($1); }
  | imports grammar_file { $$ = $2; $2.addFirst($1); }
  | visibility_spec var_def grammar_file  {
    $$ = $3; $3.addFirst(new StatFieldDef($1, $2).setPos(@1, @2));
  }
  | var_def grammar_file  {
    $$ = $2; $2.addFirst(new StatFieldDef(null, $1).setPos(@1));
  }
  | %empty { $$ = _statements;}
  ;

visibility_spec
  : PUBLIC { $$ = "public"; }
  | PROTECTED { $$ = "protected"; }
  | PRIVATE  { $$ = "private"; }
  ;

imports
  : IMPORT VARIABLE path ';' {
    new Import($2, $3.toArray(new String[$3.size()]));
  }
  ;

path
  : %empty { $$ = new ArrayList<String>(); }
  | '.' VARIABLE path { $$ = $3; $3.add($2); }
  ;

statement
  : block { $$ = $1; }
  | assignment ';' { $$ = new StatExpression($1).setPos(@$); }
  | function_call ';' { $$ = new StatExpression($1).setPos(@$); }
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

blk_statement
  : statement { $$ = $1; }
  | var_def { $$ = $1; }
  ;

////////// STATEMENTS ///////////////////

block
  : '{' statements '}' { $$ = new StatAbstractBlock($2, true).setPos(@$); }
  | '{' '}' {
    $$ = new StatAbstractBlock(new ArrayList<RTStatement>(), true).setPos(@$);
  }

statements: blk_statement { $$ = new LinkedList<RTStatement>(){{ add($1); }}; }
  | blk_statement statements { $$ = $2; $2.addFirst($1); }
  ;

grammar_rule
  : VARIABLE ':' if_statement { $$ = new StatGrammarRule($1, $3).setPos(@$); }
  ;

return_statement
  : RETURN ';' { $$ = new StatReturn("return").setPos(@$); }
  | RETURN exp ';' { $$ = new StatReturn($2).setPos(@$); }
  | BREAK ';' { $$ = new StatReturn("break").setPos(@$); }
  | BREAK VARIABLE ';' { $$ = new StatReturn("break", $2).setPos(@$); }
  | CANCEL ';' { $$ = new StatReturn("cancel").setPos(@$); }
  | CANCEL_ALL ';' { $$ = new StatReturn("cancel_all").setPos(@$); }
  | CONTINUE ';' { $$ = new StatReturn("continue").setPos(@$); }
  ;

if_statement
  : IF '(' exp ')' statement { $$ = new StatIf($3, $5, null).setPos(@$); }
  | IF '(' exp ')' statement ELSE statement {
    $$ = new StatIf($3, $5, $7).setPos(@$);
  }
  ;

while_statement
  : WHILE '(' exp ')' statement { $$ = new StatWhile($3, $5, true).setPos(@$); }
  | DO statement WHILE '(' exp ')' {
    $$ = new StatWhile($5, $2, false).setPos(@$);
  }
  ;

for_statement
  : FOR '(' var_def exp ';' exp ')' statement {
    $$ = new StatFor1($3, $4, $6, $8).setPos(@$); }
  | FOR '(' var_def     ';' exp ')' statement {
    $$ = new StatFor1($3, null, $5, $7).setPos(@$); }
  | FOR '(' var_def exp ';'     ')' statement {
    $$ = new StatFor1($3, $4, null, $7).setPos(@$); }
  | FOR '(' var_def     ';'     ')' statement {
    $$ = new StatFor1($3, null, null, $6).setPos(@$); }
  | FOR '('     ';' exp ';' exp ')' statement {
    $$ = new StatFor1(null, $4, $6, $8).setPos(@$); }
  | FOR '(' VARIABLE ':' exp ')' statement {
    ExpVariable var = new ExpVariable($3); var.setPos(@3);
    $$ = new StatFor2(var, $5, $7).setPos(@$);
  }
  | FOR '(' type_spec VARIABLE ':' exp ')' statement {
    ExpVariable var = new ExpVariable($4); var.setPos(@4);
    $$ = new StatFor2($3, var, $6, $8).setPos(@$);
  }
  // for loop with destructuring into a tuple
  //| FOR '(' '(' VARIABLE ( ',' VARIABLE )+ ')' ':' exp ')' statement {}
  ;

propose_statement
  : PROPOSE '(' exp ')' block { $$ = new StatPropose($3, $5).setPos(@$); }
  ;

timeout_behaviour_statement
  : TIMEOUT_BEHAVIOUR '(' exp ')' block {
    $$ = new StatTimeout(null, $3, $5).setPos(@$);
  }
  ;

timeout_statement
  : TIMEOUT '(' exp ',' exp ')' block {
    $$ = new StatTimeout($3, $5, $7).setPos(@$);
  }
  ;

switch_statement
  : SWITCH '(' exp ')' block {
    $$ = new StatSwitch($3, $5).setPos(@$);
  }
  ;

/*
switch_block
  : switch_labels switch_cont {
    List<RTStatement> elts = $1;
    elts.addAll($2);
    $$ = new StatAbstractBlock(elts, false).setPos(@$);
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
    val.setPos(@$); lbl.setPos(@$);
    $$ = lbl;
  }
  | CASE INT ':'      {
    ExpSingleValue val =
      new ExpSingleValue("case " + $2.toString() + ":", "label");
    RTStatement lbl = val.ensureStatement();
    val.setPos(@$); lbl.setPos(@$);
    $$ = lbl;
  }
  | CASE VARIABLE ':' {
    ExpSingleValue val =
      new ExpSingleValue("case " + $2 + ":", "label");
    RTStatement lbl = val.ensureStatement();
    val.setPos(@$); lbl.setPos(@$);
    $$ = lbl;
  }
  | DEFAULT ':'       {
    ExpSingleValue val = new ExpSingleValue("default:", "label");
    RTStatement lbl = val.ensureStatement();
    val.setPos(@$); lbl.setPos(@$);
    $$ = lbl;
  }
  ;

var_def
  : FINAL VARIABLE assgn_exp ';' {
    $$ = new StatVarDef(true, new Type(null), $2, $3).setPos(@$);
  }
  | type_spec VARIABLE assgn_exp ';' {
    $$ = new StatVarDef(false, $1, $2, $3).setPos(@$);
  }
  | FINAL type_spec VARIABLE assgn_exp ';' {
    $$ = new StatVarDef(true, $2, $3, $4).setPos(@$);
  }
  | FINAL VARIABLE ';' {
    $$ = new StatVarDef(true, new Type(null), $2, null).setPos(@$);
  }
  | type_spec VARIABLE ';' {
    $$ = new StatVarDef(false, $1, $2, null).setPos(@$);
  }
  | FINAL type_spec VARIABLE ';' {
    $$ = new StatVarDef(true, $2, $3, null).setPos(@$);
  }
  ;

assgn_exp
  : '=' exp { $$ = $2; }
  | '=' '{' '}' {
    $$ = new ExpListLiteral(new LinkedList<RTExpression>()).setPos(@2, @3);
  }
  | '=' '{' nonempty_exp_list '}' {
    $$ = new ExpListLiteral($3).setPos(@2, @4);
  }
  ;

nonempty_exp_list
  : exp { $$ = new LinkedList<RTExpression>(){{ add($1); }}; }
  | exp ',' nonempty_exp_list { $$ = $3; $3.addFirst($1); }
  ;

method_declaration
  : class_spec type_spec VARIABLE '(' ')' opt_block {
    $$ = new StatMethodDeclaration("public", $2, $1, $3, null, $6).setPos(@$);
  }
  | class_spec type_spec VARIABLE '(' args_list opt_block {
    $$ = new StatMethodDeclaration("public", $2, $1, $3, $5, $6).setPos(@$);
  }
  | class_spec           VARIABLE '(' ')' opt_block {
    $$ = new StatMethodDeclaration("public", null, $1, $2, null, $5).setPos(@$);
  }
  | class_spec           VARIABLE '(' args_list opt_block {
    $$ = new StatMethodDeclaration("public", null, $1, $2, $4, $5).setPos(@$);
  }
  |            type_spec VARIABLE '(' ')' opt_block {
    $$ = new StatMethodDeclaration("public", $1, null, $2, null, $5).setPos(@$);
  }
  |            type_spec VARIABLE '(' args_list opt_block {
    $$ = new StatMethodDeclaration("public", $1, null, $2, $4, $5).setPos(@$);
  }
  |                      VARIABLE '(' ')' block {
    $$ = new StatMethodDeclaration("public", null, null, $1, null, $4).setPos(@$);
  }
  |                      VARIABLE '(' args_list block {
    $$ = new StatMethodDeclaration("public", null, null, $1, $3, $4).setPos(@$);
  }
  ;

class_spec: '[' type_spec '.' ']' { $$ = $2; }
  ;

opt_block
  : block { $$ = $1; }
  | ';' { $$ = null; }
  ;

args_list
  : VARIABLE ')' { $$ = new LinkedList(){{ add($1); }}; }
  | type_spec VARIABLE ')' { $$ = new LinkedList(){{ add($1); add($2); }}; }
  | VARIABLE ',' args_list { $$ = $3; $3.addFirst($1); }
  | type_spec VARIABLE ',' args_list {
    $$ = $4; $4.addFirst($2); $4.addFirst($1);
  }
  ;


// add sth to a collection
set_operation
  : VARIABLE PLUSEQ exp ';' {
    ExpVariable var = new ExpVariable($1); var.setPos(@1);
    $$ = new StatSetOperation(var, true, $3).setPos(@$);
  }
  | VARIABLE MINUSEQ exp ';' {
    ExpVariable var = new ExpVariable($1); var.setPos(@1);
    $$ = new StatSetOperation(var, false, $3).setPos(@$);
  }
  | ArrayAccess PLUSEQ exp ';' {
    $$ = new StatSetOperation($1, true, $3).setPos(@$);
  }
  | ArrayAccess MINUSEQ exp ';' {
    $$ = new StatSetOperation($1, false, $3).setPos(@$);
  }
  | field_access PLUSEQ exp ';' {
    $$ = new StatSetOperation($1, true, $3).setPos(@$);
  }
  | field_access MINUSEQ exp ';' {
    $$ = new StatSetOperation($1, false, $3).setPos(@$);
  }
  ;


////////////////// EXPRESSIONS ////////////////////////////////

function_call
  : VARIABLE '(' ')' {
    $$ = new ExpFuncCall($1, new LinkedList<RTExpression>(), false).setPos(@$);
  }
  | VARIABLE '(' nonempty_args_list ')'  {
    $$ = new ExpFuncCall($1, $3, false).setPos(@$);
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
  : if_exp { $$ = $1; }
  | assignment { $$ = $1; }
  | ConditionalOrExpression { $$ = $1; }
//| dialogueact_exp { $$ = $1; }
  ;

ConditionalOrExpression
  : ConditionalOrExpression OROR ConditionalAndExpression {
    $$ = new ExpBoolean($1, $3, "||").setPos(@$);
  }
  | ConditionalAndExpression { $$ = $1; }
  ;

ConditionalAndExpression
  : ConditionalAndExpression ANDAND InclusiveOrExpression {
    $$ = new ExpBoolean($1, $3, "&&").setPos(@$);
  }
  | InclusiveOrExpression { $$ = $1; }
  ;

InclusiveOrExpression
  : ExclusiveOrExpression { $$ = $1; }
  | InclusiveOrExpression '|' ExclusiveOrExpression {
    $$ = new ExpArithmetic($1, $3, "|").setPos(@$);
  }
  ;

ExclusiveOrExpression
  : AndExpression { $$ = $1; }
  | ExclusiveOrExpression '^' AndExpression {
    $$ = new ExpArithmetic($1, $3, "^").setPos(@$);
  }
  ;

AndExpression
  : EqualityExpression { $$ = $1; }
  | AndExpression '&' EqualityExpression {
    $$ = new ExpArithmetic($1, $3, "&").setPos(@$);
  }
  ;

EqualityExpression
  : RelationalExpression { $$ = $1; }
  | EqualityExpression EQEQ RelationalExpression {
    $$ = new ExpBoolean($1, $3, "==").setPos(@$);
  }
  | EqualityExpression NOTEQ RelationalExpression {
    $$ = new ExpBoolean($1, $3, "!=").setPos(@$);
  }
  ;

RelationalExpression
  : AdditiveExpression { $$ = $1; }
  | RelationalExpression '<' AdditiveExpression {
    $$ = new ExpBoolean($1, $3, "<").setPos(@$);
  }
  | RelationalExpression '>' AdditiveExpression {
    $$ = new ExpBoolean($1, $3, ">").setPos(@$);
  }
  | RelationalExpression GTEQ AdditiveExpression {
    $$ = new ExpBoolean($1, $3, ">=").setPos(@$);
  }
  | RelationalExpression LTEQ AdditiveExpression {
    $$ = new ExpBoolean($1, $3, "<=").setPos(@$);
  }
  ;

AdditiveExpression
  : MultiplicativeExpression { $$ = $1; }
  | AdditiveExpression '+' MultiplicativeExpression {
    $$ = new ExpArithmetic($1, $3, "+").setPos(@$);
  }
  | AdditiveExpression '-' MultiplicativeExpression {
    $$ = new ExpArithmetic($1, $3, "-").setPos(@$);
  }
  ;

MultiplicativeExpression
  : CastExpression { $$ = $1; }
  | MultiplicativeExpression '*' CastExpression {
    $$ = new ExpArithmetic($1, $3, "*").setPos(@$);
  }
  | MultiplicativeExpression '/' CastExpression {
    $$ = new ExpArithmetic($1, $3, "/").setPos(@$);
  }
  | MultiplicativeExpression '%' CastExpression {
    $$ = new ExpArithmetic($1, $3, "%").setPos(@$);
  }
  ;

UnaryExpression
  : PLUSPLUS UnaryExpression {
    ExpSingleValue es = new ExpSingleValue("1", "int"); es.setPos(@$);
    $$ = new ExpArithmetic($2, es, "+").setPos(@$);
  }
  | MINUSMINUS UnaryExpression {
    ExpSingleValue es = new ExpSingleValue("1", "int"); es.setPos(@$);
    $$ = new ExpArithmetic($2, es, "-").setPos(@$);
  }
  | '+' CastExpression { $$ = new ExpArithmetic($2, null, "+").setPos(@$); }
  | '-' CastExpression { $$ = new ExpArithmetic($2, null, "-").setPos(@$); }
  | LogicalUnaryExpression { $$ = $1; }
;

CastExpression
  : UnaryExpression { $$ = $1; }
  | '(' type_spec ')' CastExpression { $$ = new ExpCast($2, $4).setPos(@$); }
  ;
//  | '(' exp ')' LogicalUnaryExpression { new ExpCast($2, $4); }

LogicalUnaryExpression
  : PostfixExpression { $$ = $1; }
  | '!' UnaryExpression { $$ = new ExpBoolean($2, null, "!").setPos(@$); }
  | '~' UnaryExpression { $$ = new ExpArithmetic($2, null, "~").setPos(@$); }
  ;

PostfixExpression
  : PrimaryExpression { $$ = $1; }
//  | PostfixExpression "++"
//  | PostfixExpression "--"
  ;

PrimaryExpression
  : NULL { $$ = new ExpSingleValue("null", "null").setPos(@$); }
  | NotJustName { $$ = $1; }
  | ComplexPrimary { $$ = $1; }
  ;

NotJustName
  : VARIABLE { $$ = new ExpVariable($1).setPos(@$); }
  | new_exp { $$ = $1; }
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
  : STRING { $$ = $1; $1.setPos(@$); }
  | INT { $$ = $1; $1.setPos(@$); }
  | OTHER_LITERAL { $$ = $1; $1.setPos(@$); }
  ;

ArrayAccess
  : VARIABLE '[' exp ']' {
    ExpVariable var = new ExpVariable($1); var.setPos(@1);
    $$ = new ExpArrayAccess(var, $3).setPos(@$);
  }
  | ComplexPrimary '[' exp ']' { $$ = new ExpArrayAccess($1, $3).setPos(@$); }
  ;

if_exp
  : exp '?' exp ':' exp { $$ = new ExpConditional($1, $3, $5).setPos(@$); }
  ;

/*
: '(' type_spec ')' exp {}
//| complex_exp {}
  | if_exp {}
//  | string_expression {}
  | boolean_exp {}
  | new_exp {}
  | lambda_exp {}
//  | nonbool_exp // missing here: in boolean_exp, but incorrecty treated there
;


nonbool_exp
  : arithmetic {}
  | dialogueact_exp {}
  | assignment {}
  | STRING {}
  | '(' exp ')' {}
  | NULL {}
  ;
*/

// TODO: is that all 'what you can assign to' (an lvalue), which can be:
// a variable, an array element, an rdf slot (did i forget sth?)
assignment
  : VARIABLE assgn_exp {
    ExpVariable var = new ExpVariable($1); var.setPos(@1);
    $$ = new ExpAssignment(var, $2).setPos(@$);
  }
  | field_access assgn_exp { $$ = new ExpAssignment($1, $2).setPos(@$); }
  | ArrayAccess assgn_exp { $$ = new ExpAssignment($1, $2).setPos(@$); }
  ;

field_access
  : NotJustName field_access_rest {
    List<String> repr = new ArrayList<>($2.size());
    for (int i = 0; i < $2.size(); ++i) repr.add("");
    $$ = new ExpFieldAccess($2, repr).setPos(@$); $2.addFirst($1);
  }
  ;

field_access_rest
  : '.' simple_nofa_exp field_access_rest { $$ = $3; $3.addFirst($2); }
  | '.' simple_nofa_exp { $$ = new LinkedList<RTExpression>(){{ add($2); }}; }
  ;

simple_nofa_exp
  : VARIABLE { $$ = new ExpVariable($1).setPos(@$); }
  | function_call { $$ = $1; }
  | '(' exp ')' { $$ = $2; }
  ;

new_exp
  : NEW VARIABLE { $$ = new ExpNew(new Type($2)).setPos(@$); }
  | NEW VARIABLE '(' ')' {
    $$ = new ExpNew(new Type($2), new LinkedList<>()).setPos(@$);
  }
  | NEW VARIABLE '(' nonempty_exp_list ')' {
    $$ = new ExpNew(new Type($2), $4).setPos(@$);
  }
  | NEW type_spec '(' ')' {
    $$ = new ExpNew($2, new LinkedList<>()).setPos(@$);
  }
  | NEW type_spec '(' nonempty_exp_list')' {
    $$ = new ExpNew($2, $4).setPos(@$);
  }
  | NEW VARIABLE '[' exp ']' {
    $$ = new ExpNew(new Type("Array", new Type($2)),
                    new LinkedList<RTExpression>(){{ add($4); }}).setPos(@$);
  }
  ;

lambda_exp
  : '(' args_list ARROW exp {
    $$ = new ExpLambda($2, $4).setPos(@$);
  }
  | '(' args_list ARROW block {
    $$ = new ExpLambda($2, $4).setPos(@$);
  }
  | '(' ')' ARROW exp {
    $$ = new ExpLambda(new LinkedList<>(), $4).setPos(@$);
  }
  | '(' ')' ARROW block {
    $$ = new ExpLambda(new LinkedList<>(), $4).setPos(@$);
  }
  ;


dialogueact_exp
  : '#' da_token '(' da_token ')' {
    $$ = new ExpDialogueAct($2, $4, new LinkedList<RTExpression>()).setPos(@$);
  }
  | '#' da_token '(' da_token da_args ')' {
    $$ = new ExpDialogueAct($2, $4, $5).setPos(@$);
  }
  ;

da_token
  : '{' exp '}' { $$ = $2; }
  | VARIABLE { $$ = new ExpVariable($1).setPos(@$); }
  | STRING { $$ = $1; }
  | WILDCARD { $$ = new ExpSingleValue($1, "String").setPos(@$); }
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
