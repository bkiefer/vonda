/* -*- Mode: Java -*- */

%code imports {

import java.io.Reader;
import java.util.*;
import de.dfki.mlt.rudimant.compiler.Type;
import de.dfki.mlt.rudimant.common.Position;
import de.dfki.mlt.rudimant.compiler.tree.*;

@SuppressWarnings({"serial", "unchecked", "fallthrough", "unused"})
}

%language "Java"

%type <String> visibility_spec
%type <LinkedList<RudiTree>> grammar_file root
%type <RTStatement> declarations
%type <LinkedList<RTExpression>> nonempty_exp_list nonempty_args_list
%type <LinkedList<RTExpression>> field_access_rest da_args
%type <RTExpression> exp assgn_exp lambda_exp ConditionalExpression assignment
%type <RTExpression> ConditionalOrExpression ConditionalAndExpression
%type <RTExpression> dialogueact_exp InclusiveOrExpression
%type <RTExpression> ExclusiveOrExpression AndExpression EqualityExpression
%type <RTExpression> RelationalExpression AdditiveExpression
%type <RTExpression> MultiplicativeExpression CastExpression
%type <RTExpression> LogicalUnaryExpression UnaryExpression PostfixExpression
%type <RTExpression> PrimaryExpression ComplexPrimary new_exp
%type <RTExpression> ComplexPrimaryNoParenthesis field_access ArrayAccess
%type <RTExpression> function_call simple_nofa_exp da_token
%type <StatVarDef> var_def var_decl
%type <StatFieldDef> field_def
%type <RTStatement> statement_no_def statement blk_statement set_operation
%type <RTStatement> return_statement propose_statement timeout_statement
%type <RTStatement> while_statement for_statement
%type <RTStatement> label_statement switch_statement
%type <StatAbstractBlock> block opt_block
%type <StatIf> if_statement
%type <Import> imports
%type <Include> includes
%type <StatMethodDeclaration> method_declaration
%type <List<String>> path
%type <LinkedList<RTStatement>> statements
%type <StatGrammarRule> grammar_rule
%type <Type> type_spec
%type <LinkedList<Type>> type_spec_list
%type <ExpLiteral> Literal
%type <LinkedList> args_list opt_args_list


%locations

%define api.package "de.dfki.mlt.rudimant.compiler.io"

%define api.parser.public

%define api.parser.class {VondaGrammar}

%define parse.error verbose

%code {
  private List<RudiTree> _statements = new LinkedList<>();

  //private GrammarFile _result = new GrammarFile(_statements);

  public List<RudiTree> getResult() { return _statements; }

  public static <T extends RudiTree> T setPos(T rt, Location l) {
    return setPos(rt, l, l) ;
  }

  public static <T extends RudiTree> T setPos(T rt, Location start, Location end) {
    rt.location = new de.dfki.mlt.rudimant.common.Location(start.begin, end.end);
    return rt;
  }

  // if the left part is a variable, this must be transformed to StatVarDef
  private RudiTree getAssignmentStat(RTExpression assign) {
    assert(assign instanceof ExpAssignment);
    if (((ExpAssignment)assign).leftIsVariable()) {
      return new StatVarDef(false, Type.getNoType(), (ExpAssignment)assign);
    }
    return new StatExpression(assign);
  }

  // generic method
  private ExpArithmetic createPlusMinus(RTExpression variable,
                                        String plusOrMinus,
                                        Location loc) {
    return setPos(new ExpArithmetic(variable, null, plusOrMinus), loc);
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
%token STATIC
%token INCLUDE
%token NEW
%token NULL
%token PRIVATE
%token PROPOSE
%token PROTECTED
%token PUBLIC
%token RETURN
%token SWITCH
%token TIMEOUT
%token WHILE
%token ISA

%token ELLIPSIS

%token LAMBDA
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
%token < ExpLiteral > STRING
%token < String > WILDCARD
%token < ExpLiteral > INT
%token < ExpLiteral > BOOL_LITERAL
%token < String > IDENTIFIER
%token < ExpLiteral > OTHER_LITERAL

%%

// start rule
root
  : imports root { $$ = $2; $2.addFirst($1); }
  | grammar_file { $$ = $1;}
  ;

imports
  : IMPORT path ';' { $$ = setPos(new Import($2), @$); }
  | IMPORT path '.' '*' ';' {
    $2.add("*");
    $$ = setPos(new Import($2), @$);
  }
  | IMPORT STATIC path ';' { $$ = setPos(new Import($3, true), @$); }
  | IMPORT STATIC path '.' '*' ';' {
    $3.add("*");
    $$ = setPos(new Import($3, true), @$);
  };

declarations
  : visibility_spec method_declaration { $$ = $2; $2.setVisibility($1); }
  | method_declaration { $$ = $1; }
  | visibility_spec var_def  { $$ = setPos(new StatFieldDef($1, $2), @1, @2); }
  | var_def  { $$ = setPos(new StatFieldDef(null, $1), @1); }
  | field_def { $$ = $1; }
  ;

grammar_file
  : declarations grammar_file { $$ = $2; $2.addFirst($1); }
  | function_call grammar_file { $$ = $2; $2.addFirst($1); }
  | statement_no_def grammar_file { $$ = $2; $2.addFirst($1); }
  // | ANNOTATION grammar_file { $$ = $2; $2.add($1); }
  | includes grammar_file { $$ = $2; $2.addFirst($1); }
  | %empty { $$ = _statements; }
  ;

visibility_spec
  : PUBLIC { $$ = "public"; }
  | PROTECTED { $$ = "protected"; }
  | PRIVATE  { $$ = "private"; }
  ;

includes
  : INCLUDE path ';' {
    List<String> path = $2;
    String name = path.remove(path.size() - 1);
    $$ = setPos(new Include(name, path.toArray(new String[path.size()])), @$);
  }
  ;

path
  : IDENTIFIER { $$ = new ArrayList<String>(){{ add($1); }}; }
  | path '.' IDENTIFIER { $$ = $1; $1.add($3); }
  ;

statement_no_def
  : block { $$ = $1; }
  | assignment ';' { $$ = setPos(getAssignmentStat($1), @$); }
  | field_access ';' { $$ = setPos(new StatExpression($1), @$); }
  | PLUSPLUS IDENTIFIER ';' {
    ExpIdentifier var = setPos(new ExpIdentifier($2), @2);
    $$ = setPos(new StatExpression(createPlusMinus(var, "++", @$)), @$);
  }
  | MINUSMINUS IDENTIFIER ';' {
    ExpIdentifier var = setPos(new ExpIdentifier($2), @2);
    $$ = setPos(new StatExpression(createPlusMinus(var, "--", @$)), @$);
  }
  | IDENTIFIER PLUSPLUS ';' {
    ExpIdentifier var = setPos(new ExpIdentifier($1), @1);
    $$ = setPos(new StatExpression(createPlusMinus(var, "+++", @$)), @$);
  }
  | IDENTIFIER MINUSMINUS ';' {
    ExpIdentifier var = setPos(new ExpIdentifier($1), @1);
    $$ = setPos(new StatExpression(createPlusMinus(var, "---", @$)), @$);
  }
  | PLUSPLUS field_access ';' {
    $$ = setPos(new StatExpression(createPlusMinus($2, "++", @$)), @$);
  }
  | MINUSMINUS field_access ';' {
    $$ = setPos(new StatExpression(createPlusMinus($2, "--", @$)), @$);
  }
  | field_access PLUSPLUS ';' {
    $$ = setPos(new StatExpression(createPlusMinus($1, "+++", @$)), @$);
  }
  | field_access MINUSMINUS ';' {
    $$ = setPos(new StatExpression(createPlusMinus($1, "---", @$)), @$);
  }
  | function_call ';' { $$ = setPos(new StatExpression($1), @$); }
  | grammar_rule { $$ = $1; }
  | set_operation { $$ = $1; }
  | return_statement { $$ = $1; }
  | propose_statement { $$ = $1; }
  | timeout_statement { $$ = $1; }
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
  : IDENTIFIER ':' if_statement { $$ = setPos(new StatGrammarRule($1, $3), @$); }
  ;

return_statement
  : RETURN ';' { $$ = setPos(new StatReturn("return"), @$); }
  | RETURN exp ';' { $$ = setPos(new StatReturn($2), @$); }
  | BREAK ';' { $$ = setPos(new StatReturn("break"), @$); }
  | BREAK IDENTIFIER ';' { $$ = setPos(new StatReturn("break", $2), @$); }
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
  : FOR '(' var_decl exp ';' exp ')' statement {
    $$ = setPos(new StatFor1($3, $4, $6, $8), @$); }
  | FOR '(' var_decl     ';' exp ')' statement {
    $$ = setPos(new StatFor1($3, null, $5, $7), @$); }
  | FOR '(' var_decl exp ';'     ')' statement {
    $$ = setPos(new StatFor1($3, $4, null, $7), @$); }
  | FOR '(' var_decl     ';'     ')' statement {
    $$ = setPos(new StatFor1($3, null, null, $6), @$); }
  | FOR '('     ';' exp ';' exp ')' statement {
    $$ = setPos(new StatFor1(null, $4, $6, $8), @$); }
  | FOR '(' IDENTIFIER ':' exp ')' statement {
    ExpIdentifier var = setPos(new ExpIdentifier($3), @3);
    $$ = setPos(new StatFor2(var, $5, $7), @$);
  }
  | FOR '(' type_spec IDENTIFIER ':' exp ')' statement {
    ExpIdentifier var = setPos(new ExpIdentifier($4), @4);
    $$ = setPos(new StatFor2($3, var, $6, $8), @$);
  }
  // for loop with destructuring into a tuple
  //| FOR '(' '(' IDENTIFIER ( ',' IDENTIFIER )+ ')' ':' exp ')' statement {}
  ;

var_decl
  : IDENTIFIER assgn_exp ';' {
    ExpIdentifier var = setPos(new ExpIdentifier($1), @1);
    ExpAssignment ass = setPos(new ExpAssignment(var, $2), @1, @2);
    $$ = setPos(new StatVarDef(false, Type.getNoType(), ass), @$);
  }
  | type_spec IDENTIFIER assgn_exp ';' {
    ExpIdentifier var = setPos(new ExpIdentifier($2), @2);
    ExpAssignment ass = setPos(new ExpAssignment(var, $3), @2, @3);
    $$ = setPos(new StatVarDef(false, $1, ass), @$);
  }
  | FINAL IDENTIFIER assgn_exp ';' {
    ExpIdentifier var = setPos(new ExpIdentifier($2), @2);
    ExpAssignment ass = setPos(new ExpAssignment(var, $3), @2, @3);
    $$ = setPos(new StatVarDef(true, Type.getNoType(), ass), @$);
  }
  | FINAL type_spec IDENTIFIER assgn_exp ';' {
    ExpIdentifier var = setPos(new ExpIdentifier($3), @3);
    ExpAssignment ass = setPos(new ExpAssignment(var, $4), @3, @4);
    $$ = setPos(new StatVarDef(true, $2, ass), @$);
  }
  ;

propose_statement
  : PROPOSE '(' exp ')' block { $$ = setPos(new StatPropose($3, $5), @$); }
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


label_statement
  : CASE STRING ':'   {
    ExpLiteral val =
      new ExpLiteral("case \"" + $2.toString() + "\":", "label");
    RTStatement lbl = val.ensureStatement();
    setPos(val, @$); setPos(lbl, @$);
    $$ = lbl;
  }
  | CASE INT ':'      {
    ExpLiteral val =
      new ExpLiteral("case " + $2.toString() + ":", "label");
    RTStatement lbl = val.ensureStatement();
    setPos(val, @$); setPos(lbl, @$);
    $$ = lbl;
  }
  | CASE BOOL_LITERAL ':'      {
    ExpLiteral val =
      new ExpLiteral("case " + $2.toString() + ":", "label");
    RTStatement lbl = val.ensureStatement();
    setPos(val, @$); setPos(lbl, @$);
    $$ = lbl;
  }
  | CASE IDENTIFIER ':' {
    ExpLiteral val =
      new ExpLiteral("case " + $2 + ":", "label");
    RTStatement lbl = val.ensureStatement();
    setPos(val, @$); setPos(lbl, @$);
    $$ = lbl;
  }
  | DEFAULT ':'       {
    ExpLiteral val = new ExpLiteral("default:", "label");
    RTStatement lbl = val.ensureStatement();
    setPos(val, @$); setPos(lbl, @$);
    $$ = lbl;
  }
  ;

var_def
  : FINAL IDENTIFIER assgn_exp ';' {
    ExpIdentifier var = setPos(new ExpIdentifier($2), @2);
    ExpAssignment ass = setPos(new ExpAssignment(var, $3), @2, @3);
    $$ = setPos(new StatVarDef(true, Type.getNoType(), ass), @$);
  }
  | type_spec IDENTIFIER assgn_exp ';' {
    ExpIdentifier var = setPos(new ExpIdentifier($2), @2);
    ExpAssignment ass = setPos(new ExpAssignment(var, $3), @2, @3);
    $$ = setPos(new StatVarDef(false, $1, ass), @$);
  }
  | FINAL type_spec IDENTIFIER assgn_exp ';' {
    ExpIdentifier var = setPos(new ExpIdentifier($3), @3);
    ExpAssignment ass = setPos(new ExpAssignment(var, $4), @3, @4);
    $$ = setPos(new StatVarDef(true, $2, ass), @$);
    }
  | FINAL IDENTIFIER ';' {
    $$ = setPos(new StatVarDef(true, Type.getNoType(), $2), @$);
  }
  | type_spec IDENTIFIER ';' {
    $$ = setPos(new StatVarDef(false, $1, $2), @$);
  }
  | FINAL type_spec IDENTIFIER ';' {
    $$ = setPos(new StatVarDef(true, $2, $3), @$);
  }
  ;

field_def
  : '#' type_spec type_spec IDENTIFIER ';' {
    $$ = setPos(new StatFieldDef(null,
           setPos(new StatVarDef(false, $3, $4), @$), $2), @$);
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

/* For method declarations, the return type spec is OBLIGATORY */
method_declaration
  : '#' type_spec type_spec IDENTIFIER '(' opt_args_list ')' opt_block {
    $$ = setPos(new StatMethodDeclaration("public", $3, $2, $4, $6, $8), @$);
  }
  | type_spec IDENTIFIER '(' opt_args_list ')' opt_block {
    $$ = setPos(new StatMethodDeclaration("public", $1, null, $2, $4, $6), @$);
  }
  ;

opt_block
  : block { $$ = $1; }
  | ';' { $$ = null; }
  ;

opt_args_list
  : args_list { $$ = $1; }
  | %empty { $$ = new LinkedList(); }
  ;

args_list
  : IDENTIFIER { $$ = new LinkedList(){{ add(Type.getNoType()); add($1); }}; }
  | type_spec IDENTIFIER { $$ = new LinkedList(){{ add($1); add($2); }}; }
  | type_spec ELLIPSIS IDENTIFIER {
    // TODO: make optional sequence type from $1, this does not suffice
    // or adapt the type checking code
    Type optt = new Type("Array", new ArrayList<Type>(){{ add($1); }});
    $$ = new LinkedList(){{ add(optt); add($3); }};
  }
  | IDENTIFIER ',' args_list {
    $$ = $3;
    $3.addFirst($1); $3.addFirst(Type.getNoType());
  }
  | type_spec IDENTIFIER ',' args_list {
    $$ = $4; $4.addFirst($2); $4.addFirst($1);
  }
  ;


// add sth to a collection
set_operation
  : IDENTIFIER PLUSEQ exp ';' {
    ExpIdentifier var = setPos(new ExpIdentifier($1), @1);
    $$ = setPos(new StatSetOperation(var, true, $3), @$);
  }
  | IDENTIFIER MINUSEQ exp ';' {
    ExpIdentifier var = setPos(new ExpIdentifier($1), @1);
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
  : IDENTIFIER '(' ')' {
    $$ = setPos(new ExpFuncCall($1, new LinkedList<RTExpression>(), false), @$);
  }
  | IDENTIFIER '(' nonempty_args_list ')'  {
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
  : IDENTIFIER '[' ']' {
    $$ = new Type("Array",
                  new ArrayList<Type>(){{ add(new Type($1)); }});
  }
  | IDENTIFIER { $$ = new Type($1); }
  | IDENTIFIER '<' type_spec_list '>' { $$ = new Type($1, $3); }
  ;

type_spec_list
  : type_spec { $$ = new LinkedList<Type>(){{ add($1); }}; }
  | type_spec ',' type_spec_list { $$ = $3; $3.addFirst($1); }
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

CastExpression
  : UnaryExpression { $$ = $1; }
  | ISA '(' type_spec ',' ConditionalOrExpression ')' { $$ = setPos(new ExpCast($3, $5), @$); }
  ;

UnaryExpression
  : PLUSPLUS UnaryExpression {
    $$ = createPlusMinus($2, "++", @$);
  }
  | MINUSMINUS UnaryExpression {
    $$ = createPlusMinus($2, "--", @$);
  }
  | '+' CastExpression { $$ = setPos(new ExpArithmetic($2, null, "+"), @$); }
  | '-' CastExpression { $$ = setPos(new ExpArithmetic($2, null, "-"), @$); }
  | LogicalUnaryExpression { $$ = $1; }
;

LogicalUnaryExpression
  : PostfixExpression { $$ = $1; }
  | '!' UnaryExpression { $$ = setPos(new ExpBoolean($2, null, "!"), @$); }
  | '~' UnaryExpression { $$ = setPos(new ExpArithmetic($2, null, "~"), @$); }
  ;

PostfixExpression
  : PrimaryExpression { $$ = $1; }
  | PostfixExpression PLUSPLUS {
    $$ = createPlusMinus($1, "+++", @$);
  }
  | PostfixExpression MINUSMINUS {
    $$ = createPlusMinus($1, "---", @$);
  }
  ;

PrimaryExpression
  : NULL { $$ = setPos(new ExpLiteral("null", "null"), @$); }
  | IDENTIFIER { $$ = setPos(new ExpIdentifier($1), @$); }
  | field_access { $$ = $1; }
  | ComplexPrimary { $$ = $1; }
  ;

ComplexPrimary
  : '(' exp ')' { $$ = $2; $2.generateParens(); }
  | ComplexPrimaryNoParenthesis { $$ = $1; }
  ;

ComplexPrimaryNoParenthesis
  : Literal { $$ = $1; }
  | ArrayAccess { $$ = $1; }
  | function_call { $$ = $1; }
  | dialogueact_exp { $$ = $1; }
  ;

Literal
  : STRING { $$ = setPos($1, @$); }
  | INT { $$ = setPos($1, @$); }
  | OTHER_LITERAL { $$ = setPos($1, @$); }
  | BOOL_LITERAL { $$ = setPos($1, @$); }
  ;

ArrayAccess
  : IDENTIFIER '[' exp ']' {
    ExpIdentifier var = setPos(new ExpIdentifier($1), @1);
    $$ = setPos(new ExpArrayAccess(var, $3), @$);
  }
  | ComplexPrimary '[' exp ']' { $$ = setPos(new ExpArrayAccess($1, $3), @$); }
  ;

field_access
  : IDENTIFIER field_access_rest {
    ExpIdentifier var = setPos(new ExpIdentifier($1), @1);
    $2.addFirst(var);
    $$ = setPos(new ExpFieldAccess($2), @$);
  }
  | ISA '(' type_spec ',' ConditionalOrExpression ')' field_access_rest {
    ExpCast c = setPos(new ExpCast($3, $5), @3, @5);
    $7.addFirst(c);
    $$ = setPos(new ExpFieldAccess($7), @$);
  }
  | ComplexPrimary field_access_rest {
    $2.addFirst($1);
    $$ = setPos(new ExpFieldAccess($2), @$);
  }
  ;

field_access_rest
   : '.' simple_nofa_exp field_access_rest { $$ = $3; $3.addFirst($2); }
   | '.' simple_nofa_exp {
     List<RTExpression> l = new LinkedList<RTExpression>();
     l.add($2);
     $$ = l;
   }
   ;

simple_nofa_exp
  : IDENTIFIER { $$ = setPos(new ExpIdentifier($1), @$); }
  | '{' exp '}' { $$ = $2; }
  | function_call { $$ = $1; }
  ;

ConditionalExpression
  : ConditionalOrExpression '?' exp ':' exp { $$ = setPos(new ExpConditional($1, $3, $5), @$); }
  | ConditionalOrExpression { $$ = $1; }
  ;

// TODO: is that all 'what you can assign to' (an lvalue), which can be:
// a variable, an array element, an rdf slot (did i forget sth?)
assignment
  : field_access assgn_exp { $$ = setPos(new ExpAssignment($1, $2), @$); }
  | ArrayAccess assgn_exp { $$ = setPos(new ExpAssignment($1, $2), @$); }
  | IDENTIFIER assgn_exp {
    ExpIdentifier var = setPos(new ExpIdentifier($1), @1);
    ExpAssignment ass = setPos(new ExpAssignment(var, $2), @$);
    $$ = ass;
  }
  ;

new_exp
  : NEW IDENTIFIER { $$ = setPos(new ExpNew(new Type($2)), @$); }
  | NEW IDENTIFIER '(' ')' {
    $$ = setPos(new ExpNew(new Type($2), new LinkedList<>()), @$);
  }
  | NEW IDENTIFIER '(' nonempty_exp_list ')' {
    $$ = setPos(new ExpNew(new Type($2), $4), @$);
  }
  | NEW IDENTIFIER '[' exp ']' {
    List<Type> sub = new ArrayList<Type>() {{ add(new Type($2)); }};
    $$ = setPos(new ExpNew(new Type("Array", sub),
                    new LinkedList<RTExpression>(){{ add($4); }}), @$);
  }
  | NEW IDENTIFIER '[' ']' '(' exp ')'{
    List<Type> sub = new ArrayList<Type>() {{ add(new Type($2)); }};
    $$ = setPos(new ExpNew(new Type("Array", sub),
                    new LinkedList<RTExpression>(){{ add($6); }}), @$);
  }
  | NEW IDENTIFIER '<' type_spec_list '>' '(' ')' {
    $$ = setPos(new ExpNew(new Type($2, $4),
                    new LinkedList<>()), @$);
  }
  | NEW IDENTIFIER '<' type_spec_list '>' '(' nonempty_exp_list ')' {
    $$ = setPos(new ExpNew(new Type($2, $4),
                    $7), @$);
  }
  ;

lambda_exp
  : LAMBDA '(' opt_args_list ')' exp {
    $$ = setPos(new ExpLambda($3, $5), @$);
  }
  | LAMBDA '(' opt_args_list ')' block {
    $$ = setPos(new ExpLambda($3, $5), @$);
  }
  ;


dialogueact_exp
  : '#' da_token '(' da_token da_args ')' {
    $$ = setPos(new ExpDialogueAct($2, $4, $5), @$);
  }
  ;

da_token
  : '{' exp '}' { $$ = $2; }
  // Note: if not explicitly marked, variables are not treated as variabls in DAs
  //  | IDENTIFIER { $$ = setPos(new ExpIdentifier($1), @$); }
  | IDENTIFIER { $$ = setPos(new ExpLiteral($1, "String"), @$); }
  | STRING { $$ = setPos($1, @$); }
  | WILDCARD { $$ = setPos(new ExpLiteral($1, "String"), @$); }
  ;

da_args
  : ',' da_token '=' da_token da_args  {
    $$ = $5; $5.addFirst($4); $5.addFirst($2);
  }
  | %empty { $$ = new LinkedList<RTExpression>(); }
  ;

exp
  : ConditionalExpression { $$ = $1; }
  | assignment { $$ = $1; }
  | new_exp { $$ = $1; }
//| dialogueact_exp { $$ = $1; }
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
IDENTIFIER: ('A'..'Z'|'a'..'z'|'_')('0'..'9'|'A'..'Z'|'a'..'z'|'_'|'$')*;

/// numeric literal (starting with - or number):
INT: ('-')?('1'..'9')?('0'..'9')+;
FLOAT: ('-')?('0'('.'('0'..'9')+) | ('1'..'9')('0'..'9')*('.'('0'..'9')+));
*/
