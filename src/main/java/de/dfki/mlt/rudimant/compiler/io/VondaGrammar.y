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
%type <RTStatement> switch_statement switch_label
%type <StatAbstractBlock> block opt_block switch_block
%type <StatIf> if_statement
%type <Import> imports
%type <StatMethodDeclaration> method_declaration
%type <List<String>> path
%type <LinkedList<RTStatement>> statements switch_labels switch_cont
%type <StatGrammarRule> grammar_rule
%type <Type> type_spec parameterized_type class_spec
%type <LinkedList<Type>> type_spec_list
%type <ExpSingleValue> Literal
%type <LinkedList> args_list


%locations

%define package "de.dfki.mlt.rudimant.compiler.io"

%define public

%define parser_class_name {VondaGrammar}

%define parse.error verbose

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
%token < String > STRING
%token < String > WILDCARD
%token < String > INT
%token < String > VARIABLE
%token < ExpSingleValue > OTHER_LITERAL

%%

// start rule
grammar_file
  : visibility_spec method_declaration grammar_file {
    $$ = $2; $2.setVisibility($1); $3.add($2);
  }
  | method_declaration grammar_file { $$ = $2; $2.add($1); }
  | statement grammar_file { $$ = $2; $2.add($1); }
  // | ANNOTATION grammar_file { $$ = $2; $2.add($1); }
  | imports grammar_file { $$ = $2; $2.add($1); }
  | visibility_spec var_def grammar_file  {
    $$ = $2; $3.add(new StatFieldDef($1, $2));
  }
  | %empty { $$ = new ArrayList<RudiTree>();}
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
  | exp ';' { $$ = new StatExpression($1); }
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
  ;

blk_statement
  : statement { $$ = $1; }
  | var_def { $$ = $1; }
  ;

////////// STATEMENTS ///////////////////

block
  : '{' statements '}' { $$ = new StatAbstractBlock($2, true); }
  | '{' '}' { $$ = new StatAbstractBlock(new ArrayList<RTStatement>(), true); }

statements: blk_statement { $$ = new LinkedList<RTStatement>(){{ add($1); }}; }
  | blk_statement statements { $$ = $2; $2.addFirst($1); }
  ;

grammar_rule
  : VARIABLE ':' if_statement { $$ = new StatGrammarRule($1, $3); }
  ;

return_statement
  : RETURN ';' { $$ = new StatReturn("return"); }
  | RETURN exp ';' { $$ = new StatReturn($2); }
  | BREAK ';' { $$ = new StatReturn("break"); }
  | BREAK VARIABLE ';' { $$ = new StatReturn("break", $2); }
  | CANCEL ';' { $$ = new StatReturn("cancel"); }
  | CANCEL_ALL ';' { $$ = new StatReturn("cancel_all"); }
  | CONTINUE ';' { $$ = new StatReturn("continue"); }
  ;

if_statement
  : IF '(' exp ')' statement { $$ = new StatIf($3, $5, null); }
  | IF '(' exp ')' statement ELSE statement {
    $$ = new StatIf($3, $5, $7);
  }
  ;

while_statement
  : WHILE '(' exp ')' statement { $$ = new StatWhile($3, $5, true); }
  | DO statement WHILE '(' exp ')' {
    $$ = new StatWhile($5, $2, false);
  }
  ;

for_statement
  : FOR '(' var_def exp ';' exp ')' statement {
    $$ = new StatFor1($3, $4, $6, $8); }
  | FOR '(' var_def     ';' exp ')' statement {
    $$ = new StatFor1($3, null, $5, $7); }
  | FOR '(' var_def exp ';'     ')' statement {
    $$ = new StatFor1($3, $4, null, $7); }
  | FOR '(' var_def     ';'     ')' statement {
    $$ = new StatFor1($3, null, null, $6); }
  | FOR '('     ';' exp ';' exp ')' statement {
    $$ = new StatFor1(null, $4, $6, $8); }
  | FOR '(' VARIABLE ':' exp ')' statement {
    $$ = new StatFor2(new ExpVariable($3), $5, $7);
  }
  | FOR '(' type_spec VARIABLE ':' exp ')' statement {
    $$ = new StatFor2($3, new ExpVariable($4), $6, $8);
  }
  // for loop with destructuring into a tuple
  //| FOR '(' '(' VARIABLE ( ',' VARIABLE )+ ')' ':' exp ')' statement {}
  ;

propose_statement
  : PROPOSE '(' exp ')' block { $$ = new StatPropose($3, $5); }
  ;

timeout_behaviour_statement
  : TIMEOUT_BEHAVIOUR '(' exp ')' block {
    $$ = new StatTimeout(null, $3, $5);
  }
  ;

timeout_statement
  : TIMEOUT '(' exp ',' exp ')' block {
    $$ = new StatTimeout($3, $5, $7);
  }
  ;

switch_statement
  : SWITCH '(' exp ')' '{' switch_block '}' {
    $$ = new StatSwitch($3, $6);
  }
  ;

switch_block
  : switch_labels switch_cont {
    List<RTStatement> elts = $1;
    elts.addAll($2);
    $$ = new StatAbstractBlock(elts, false);
  }
  ;

switch_cont
  : statements switch_labels switch_cont { }
  | %empty { $$ = new ArrayList<RTExpression>(); }
  ;

switch_labels
  : switch_label switch_labels { $$ = $2; $2.addFirst($1); }
  | switch_label { $$ = new LinkedList<RTStatement>(){{ add($1); }}; }
  ;

switch_label
  : CASE STRING ':'   {
    $$ = new ExpSingleValue("case " + $2 + ":", "label").ensureStatement();
  }
  | CASE INT ':'      {
    $$ = new ExpSingleValue("case " + $2 + ":", "label").ensureStatement();
  }
  | CASE VARIABLE ':' {
    $$ = new ExpSingleValue("case " + $2 + ":", "label").ensureStatement();
  }
  | DEFAULT ':'       {
    $$ = new ExpSingleValue("default:", "label").ensureStatement();
  }
  ;

var_def
  : FINAL VARIABLE assgn_exp ';' {
    $$ = new StatVarDef(true, null, $2, $3);
  }
  | type_spec VARIABLE assgn_exp ';' {
    $$ = new StatVarDef(false, $1, $2, $3);
  }
  | FINAL type_spec VARIABLE assgn_exp ';' {
    $$ = new StatVarDef(true, $2, $3, $4);
  }
  ;

assgn_exp
  : '=' exp { $$ = $2; }
  | '=' '{' '}' { $$ = new ExpListLiteral(new LinkedList<RTExpression>()); }
  | '=' '{' nonempty_exp_list '}' { $$ = new ExpListLiteral($3); }
  ;

nonempty_exp_list
  : exp { $$ = new LinkedList<RTExpression>(){{ add($1); }}; }
  | exp ',' nonempty_exp_list { $$ = $3; $3.addFirst($1); }
  ;

method_declaration
  : class_spec type_spec VARIABLE '(' ')' opt_block {
    $$ = new StatMethodDeclaration("public", $2, $1, $3, null, $6);
  }
  | class_spec type_spec VARIABLE '(' args_list opt_block {
    $$ = new StatMethodDeclaration("public", $2, $1, $3, $5, $6);
  }
  | class_spec           VARIABLE '(' ')' opt_block {
    $$ = new StatMethodDeclaration("public", null, $1, $2, null, $5);
  }
  | class_spec           VARIABLE '(' args_list opt_block {
    $$ = new StatMethodDeclaration("public", null, $1, $2, $4, $5);
  }
  |            type_spec VARIABLE '(' ')' opt_block {
    $$ = new StatMethodDeclaration("public", $1, null, $2, null, $5);
  }
  |            type_spec VARIABLE '(' args_list opt_block {
    $$ = new StatMethodDeclaration("public", $1, null, $2, $4, $5);
  }
  |                      VARIABLE '(' ')' block {
    $$ = new StatMethodDeclaration("public", null, null, $1, null, $4);
  }
  |                      VARIABLE '(' args_list block {
    $$ = new StatMethodDeclaration("public", null, null, $1, $3, $4);
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
    $$ = new StatSetOperation(new ExpVariable($1), true, $3);
  }
  | VARIABLE MINUSEQ exp ';' {
    $$ = new StatSetOperation(new ExpVariable($1), false, $3);
  }
  | ArrayAccess PLUSEQ exp ';' { $$ = new StatSetOperation($1, true, $3); }
  | ArrayAccess MINUSEQ exp ';' { $$ = new StatSetOperation($1, false, $3); }
  | field_access PLUSEQ exp ';' { $$ = new StatSetOperation($1, true, $3); }
  | field_access MINUSEQ exp ';' { $$ = new StatSetOperation($1, false, $3); }
  ;


////////////////// EXPRESSIONS ////////////////////////////////

function_call
  : VARIABLE '(' ')' {
    $$ = new ExpFuncCall($1, new LinkedList<RTExpression>(), false);
  }
  | VARIABLE '(' nonempty_args_list ')'  {
    $$ = new ExpFuncCall($1, $3, false);
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
  | parameterized_type { $$ = $1; }
  ;

parameterized_type
  : VARIABLE '<' type_spec_list '>' { $$ = new Type($1, $3.toArray(new Type[$3.size()])); }
  ;

type_spec_list
  : type_spec { $$ = new LinkedList<Type>(){{ add($1); }}; }
  | type_spec ',' type_spec_list { $$ = $3; $3.addFirst($1); }
  ;

exp
  : if_exp { $$ = $1; }
  | assignment { $$ = $1; }
  | ConditionalOrExpression { $$ = $1; }
  | dialogueact_exp { $$ = $1; }
  ;

ConditionalOrExpression
  : ConditionalOrExpression OROR ConditionalAndExpression {
    $$ = new ExpBoolean($1, $3, "||");
  }
  | ConditionalAndExpression { $$ = $1; }
  ;

ConditionalAndExpression
  : ConditionalAndExpression ANDAND InclusiveOrExpression {
    $$ = new ExpBoolean($1, $3, "&&");
  }
  | InclusiveOrExpression { $$ = $1; }
  ;

InclusiveOrExpression
  : ExclusiveOrExpression { $$ = $1; }
  | InclusiveOrExpression '|' ExclusiveOrExpression {
    $$ = new ExpArithmetic($1, $3, "|");
  }
  ;

ExclusiveOrExpression
  : AndExpression { $$ = $1; }
  | ExclusiveOrExpression '^' AndExpression {
    $$ = new ExpArithmetic($1, $3, "^");
  }
  ;

AndExpression
  : EqualityExpression { $$ = $1; }
  | AndExpression '&' EqualityExpression {
    $$ = new ExpArithmetic($1, $3, "&");
  }
  ;

EqualityExpression
  : RelationalExpression { $$ = $1; }
  | EqualityExpression EQEQ RelationalExpression {
    $$ = new ExpBoolean($1, $3, "==");
  }
  | EqualityExpression NOTEQ RelationalExpression {
    $$ = new ExpBoolean($1, $3, "!=");
  }
  ;

RelationalExpression
  : AdditiveExpression { $$ = $1; }
  | RelationalExpression '<' AdditiveExpression {
    $$ = new ExpBoolean($1, $3, "<");
  }
  | RelationalExpression '>' AdditiveExpression {
    $$ = new ExpBoolean($1, $3, ">");
  }
  | RelationalExpression GTEQ AdditiveExpression {
    $$ = new ExpBoolean($1, $3, ">=");
  }
  | RelationalExpression LTEQ AdditiveExpression {
    $$ = new ExpBoolean($1, $3, "<=");
  }
  ;

AdditiveExpression
  : MultiplicativeExpression { $$ = $1; }
  | AdditiveExpression '+' MultiplicativeExpression {
    $$ = new ExpArithmetic($1, $3, "+");
  }
  | AdditiveExpression '-' MultiplicativeExpression {
    $$ = new ExpArithmetic($1, $3, "-");
  }
  ;

MultiplicativeExpression
  : CastExpression { $$ = $1; }
  | MultiplicativeExpression '*' CastExpression {
    $$ = new ExpArithmetic($1, $3, "*");
  }
  | MultiplicativeExpression '/' CastExpression {
    $$ = new ExpArithmetic($1, $3, "/");
  }
  | MultiplicativeExpression '%' CastExpression {
    $$ = new ExpArithmetic($1, $3, "%");
  }
  ;

CastExpression
  : UnaryExpression { $$ = $1; }
  | '(' type_spec ')' CastExpression { new ExpCast($2, $4); }
  ;
//  | '(' exp ')' LogicalUnaryExpression { new ExpCast($2, $4); }

LogicalUnaryExpression
  : PostfixExpression { $$ = $1; }
  | '!' UnaryExpression { $$ = new ExpBoolean($2, null, "!"); }
  | '~' UnaryExpression { $$ = new ExpArithmetic($2, null, "~"); }
  ;

UnaryExpression
  : PLUSPLUS UnaryExpression {
    $$ = new ExpArithmetic($2, new ExpSingleValue("1", "int"), "+");
  }
  | MINUSMINUS UnaryExpression {
    $$ = new ExpArithmetic($2, new ExpSingleValue("1", "int"), "-");
  }
  | '+' CastExpression { $$ = new ExpArithmetic($2, null, "+"); }
  | '-' CastExpression { $$ = new ExpArithmetic($2, null, "-"); }
  | LogicalUnaryExpression { $$ = $1; }
  ;

PostfixExpression
  : PrimaryExpression { $$ = $1; }
//  | PostfixExpression "++"
//  | PostfixExpression "--"
  ;

PrimaryExpression
  : NULL { $$ = new ExpSingleValue("null", "null"); }
  | NotJustName { $$ = $1; }
  | ComplexPrimary { $$ = $1; }
  ;

NotJustName
  : VARIABLE { $$ = new ExpVariable($1); }
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
  ;

Literal
  : STRING { $$ = new ExpSingleValue($1, "String"); }
  | INT { $$ = new ExpSingleValue($1, "int"); }
  | OTHER_LITERAL { $$ = $1; }
  ;

ArrayAccess
  : VARIABLE '[' exp ']' { $$ = new ExpArrayAccess(new ExpVariable($1), $3); }
  | ComplexPrimary '[' exp ']' { $$ = new ExpArrayAccess($1, $3); }
  ;

if_exp
  : exp '?' exp ':' exp { $$ = new ExpConditional($1, $3, $5); }
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
  : VARIABLE assgn_exp { $$ = new ExpAssignment(new ExpVariable($1), $2); }
  | field_access assgn_exp { $$ = new ExpAssignment($1, $2); }
  | ArrayAccess assgn_exp { $$ = new ExpAssignment($1, $2); }
  ;

field_access
  : NotJustName field_access_rest { $$ = $2; $2.addFirst($1); }
  ;

field_access_rest
  : '.' simple_nofa_exp field_access_rest { $$ = $3; $3.addFirst($2); }
  | '.' simple_nofa_exp { $$ = new LinkedList<RTExpression>(){{ add($2); }}; }
  ;

simple_nofa_exp
  : VARIABLE { $$ = $1; }
  | function_call { $$ = $1; }
  | '(' exp ')' { $$ = $2; }
  ;

new_exp
  : NEW VARIABLE { $$ = new ExpNew(new Type($2)); }
  | NEW VARIABLE '(' ')' { $$ = new ExpNew(new Type($2), new LinkedList<>()); }
  | NEW VARIABLE '(' nonempty_exp_list ')' {
    $$ = new ExpNew(new Type($2), $4);
  }
  | NEW parameterized_type '(' ')' {
    $$ = new ExpNew($2, new LinkedList<>());
  }
  | NEW parameterized_type '(' nonempty_exp_list')' {
    $$ = new ExpNew($2, $4);
  }
  | NEW VARIABLE '[' exp ']' {
    $$ = new ExpNew(new Type("Array", new Type($2)),
                    new LinkedList<RTExpression>(){{ add($4); }});
  }
  ;

lambda_exp
  : '(' args_list ARROW exp {
    $$ = new ExpLambda($2, new StatExpression($4));
  }
  | '(' args_list ARROW block {
    $$ = new ExpLambda($2, $4);
  }
  | '(' ')' ARROW exp {
    $$ = new ExpLambda(new LinkedList<>(), new StatExpression($4));
  }
  | '(' ')' ARROW block { $$ = new ExpLambda(new LinkedList<>(), $4); }
  ;


dialogueact_exp
  : '#' da_token '(' da_token ')' {
    $$ = new ExpDialogueAct($2, $4, new LinkedList<RTExpression>());
  }
  | '#' da_token '(' da_token da_args ')' {
    $$ = new ExpDialogueAct($2, $4, $5);
  }
  ;

da_token
  : '^' exp { $$ = $2; }
  | VARIABLE { $$ = $1; }
  | STRING { $$ = $1; }
  | WILDCARD { $$ = $1; }
  ;

da_args
  : ',' da_token '=' da_token da_args  {
    $$ = $5; $5.addFirst($4); $5.addFirst($2);
  }
  | %empty { new LinkedList<RTExpression>(); }
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
