/*
 * Grammar dedicated to create a parser and a lexer for dialogue rules in
 * DFKI robot language
 */

grammar RobotGrammar;

/*
 * PARSER
 */

/// start rule
// the part "'{' statement '}'" is ridiciulous but currently solves a parsing exception...
grammar_file
  : // TODO: THIS IS REDUNDANT. IT'S ALREADY CONTAINED IN WHAT FOLLOWS
    imports*
    (
     // TODO: THIS IS REDUNDANT. IT'S ALREADY CONTAINED IN STATEMENT
     grammar_rule
     | method_declaration
     | statement
     // TODO: THIS IS REDUNDANT. IT'S ALREADY CONTAINED IN STATEMENT
     | '{' statement '}'
     | ANNOTATION
     | imports
    )*
  ;

imports
  : IMPORT VARIABLE ';'
  ;

method_declaration
  : (PUBLIC | PROTECTED | PRIVATE)? (DEC_VAR | type_spec) VARIABLE '('
    ((type_spec | DEC_VAR) VARIABLE (',' (type_spec | DEC_VAR) VARIABLE)*)?
    ')' statement_block
  ;

statement
  : statement_block
  | exp ';'
  | list_creation
  | grammar_rule
  | set_operation
  | return_statement
  | propose_statement
  | if_statement
  | while_statement
  | for_statement
  | switch_statement
  | var_def
  | fun_def
  | CONTINUE ';'
  | BREAK ';'
  ;
////////// STATEMENTS ///////////////////

statement_block
  : '{' statement* '}'
  ;

grammar_rule
  // label: if_statement
  : VARIABLE COLON if_statement
  ;

return_statement: RETURN exp? ';';

if_statement
  : IF '(' boolean_exp ')' (statement) (ELSE (statement))?
  ;

if_exp
  : boolean_exp QUESTION exp COLON exp
  ;

while_statement
  : WHILE '(' boolean_exp ')' statement_block
  | DO statement_block WHILE '(' boolean_exp ')'
  ;

for_statement
  : FOR '(' assignment? ';' exp? ';' exp? ')' statement_block
  | FOR '(' (DEC_VAR | type_spec)? VARIABLE ':' exp ')' statement_block
  // WHAT'S THIS???
  | FOR '(' '(' VARIABLE ( ',' VARIABLE )+ ')' ':' exp ')' statement_block
  ;

propose_statement
  : PROPOSE '(' string_expression ')' statement_block
  ;

switch_statement
	:	SWITCH '(' exp ')' '{' switch_block '}'
	;

switch_block
	:	switch_group* switch_label*
	;

switch_group
	:	switch_label+ statement+
	;

switch_label
	:	CASE string_expression ':'
	|	CASE VARIABLE ':'
	|	DEFAULT ':'
	;

var_def
  : type_spec variable ';'
  ;

fun_def
  : type_spec variable '('
    ( type_spec VARIABLE (',' type_spec VARIABLE)* )? ')' ';'
  ;

list_creation
  : (VARIABLE '<' type_spec '>')?
    variable '=' '{' ((variable | STRING | INT | FLOAT)
                      (',' (variable | STRING | INT | FLOAT))*)?
                 '}' ';'
  ;

// TODO: WHAT'S THIS?
set_operation
  : (variable | field_access) (ADD | REMOVE) number ';'
  ;


////////////////// EXPRESSIONS ////////////////////////////////

function_call
  : VARIABLE '(' ( exp ( ',' exp )* )? ')'
  ;

funccall_on_object
  : (variable | function_call | field_access | STRING | '(' exp ')') '.' function_call
  ;

field_access
  : (VARIABLE | function_call)
    ( ( '.' (VARIABLE | '(' function_call ')' | function_call )) )+
  ;

type_spec
    : VARIABLE '[' ']'
    | VARIABLE '<' type_spec '>'
    | VARIABLE
    ;

variable
  : VARIABLE '[' ((VARIABLE | INT) | arithmetic) ']'
  | VARIABLE
  ;

exp
  : simple_exp
  | if_exp
  | string_expression
  | boolean_exp
  ;

simple_exp
  :
  '(' exp ')'
  | funccall_on_object
  | variable
  | arithmetic
  | function_call
  | literal_or_graph_exp
  | field_access
  | assignment
  | ( STRING
    | WILDCARD
    | FALSE
    | TRUE
    | NULL
    )
  | NOT exp
  ;

boolean_exp
  : bool_and_exp '||' boolean_exp
  | bool_and_exp
  ;

bool_and_exp
  : simple_b_exp '&&' bool_and_exp
  | simple_b_exp
  ;

simple_b_exp
  : simple_exp // will be compiled to '!= null' or '!= 0' or 'has()' ...
  | simple_exp ('==' | '!=' | '<=' | '<' | '>=' | '>') exp
  ;

// TODO: IS THIS STILL USED?
lambda_exp: '(' (DEC_VAR? VARIABLE (',' DEC_VAR? VARIABLE)*)? ')' ARROW exp;

string_expression : (simple_exp|if_exp) '+' exp | (simple_exp|if_exp) ;

literal_or_graph_exp
  : HASH da_token '(' ( da_token ( ',' ( da_token '=' da_token) )* ) ')'
  ;

da_token
  : VARIABLE_MARKER exp
  | VARIABLE
  | STRING
  ;

assignment
  : ( (DEC_VAR | type_spec)? variable | field_access ) '=' exp
  ;

number
  : ( INCREMENT | DECREMENT )?
    ( ( INT | FLOAT | VARIABLE )
      | field_access
      | function_call
    )
  ;

// either a number or a term containing at least one operator
arithmetic
  : term ('-'|'+') arithmetic
  | term
  ;

term
  : factor ('*'|'/'|'%') term
  | factor
  ;

factor
  : number
  | '(' arithmetic ')'
  | '-' arithmetic
  ;


/// citing rulesproto: TODO: CURRENTLY NOT IN SYNTAX: SUBSUMPTION + BINDING VARIABLES
/*mysterious_binding_exp
  : VARIABLE ASSIGN QUESTION VARIABLE
  ;*/


/*
 * LEXER
 */

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
FOR: 'for';
NULL: 'null';
TRUE: 'true';
FALSE: 'false';
RETURN: 'return';
PUBLIC: 'public';
PROTECTED: 'protected';
PRIVATE: 'private';

/// character literal (starting with ' ):
CHARACTER: '\''.'\'';

/// string (starting with " ):
STRING: '\"'.*?'\"';

/// assignments
ASSIGN: '=';

// operator for lambda expressions, annotations
ARROW: '->';
// TODO: WHAT IS THIS GOOD FOR ?
ANNOTATION: '@'('0'..'9'|'A'..'z'|'_'|'('|')')+;

/// separators:
LPAR: '(';
RPAR: ')';
LBRACE: '{';
RBRACE: '}';
LBRACK: '[';
RBRACK: ']';
SEMICOLON: ';';
COMMA: ',';
DOT: '.';

/// boolean operators:
NOT: '!';
EQUAL: '==';
AND1: '&';
OR1: '|';
AND2: '&&';
OR2: '||';
NOT_EQUAL: '!=';
SMALLER_EQUAL: '<=';
SMALLER: '<';
GREATER_EQUAL: '>=';
GREATER: '>';

/// mathematical operators:
PLUS: '+';
INCREMENT: '++';
MINUS: '-';
DECREMENT: '--';
DIV: '/';
MUL: '*';
MOD: '%';
ADD: '+=';
REMOVE: '-=';

/// additional operators:
QUESTION: '?';
COLON: ':';

/// special for dialogue grammar:
WILDCARD: '_';
HASH: '#';
PROPOSE: 'propose';
DEC_VAR: 'var';
// TIMEOUT: 'timeout';
VARIABLE_MARKER: '^';

/// comments (starting with /* or //):
JAVA_CODE: '/*@'.*?'@*/' -> channel(HIDDEN);
ONE_L_COMMENT: '//'.*?'\n' -> channel(HIDDEN);
MULTI_L_COMMENT: '/*'.*?'*/' -> channel(HIDDEN);

/// whitespace
WS: [ \t\u000C]+ -> channel(HIDDEN);
NLWS: [\n\r]+ -> channel(HIDDEN);

// LETTER: ('A'..'Z'|'a'..'z');
/// identifiers (starting with "java letter"):
VARIABLE: ('A'..'Z'|'a'..'z'|'_')('0'..'9'|'A'..'Z'|'a'..'z'|'_'|'$')*;

/// numeric literal (starting with - or number):
INT: ('-')?('1'..'9')?('0'..'9')+;
FLOAT: ('-')?('0'('.'('0'..'9')+) | ('1'..'9')('0'..'9')*('.'('0'..'9')+));
