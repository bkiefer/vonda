/*
 * Grammar dedicated to create a parser and a lexer for dialogue rules in
 * DFKI robot language
 *
 * see also:
 * http://stackoverflow.com/questions/15050137/once-grammar-is-complete-whats-the-best-way-to-walk-an-antlr-v4-tree
 */

grammar RobotGrammar;

/*
 * PARSER
 */

/// start rule
grammar_file
  : ( method_declaration
      | statement
      | ANNOTATION
      | imports
      | var_spec
    )*
  ;

var_spec
  : (PUBLIC | PROTECTED | PRIVATE) var_def
  ;

imports
  : IMPORT VARIABLE ( '.' VARIABLE )* ';'
  ;

statement
  : statement_block
  | var_def
  | exp ';'
  | list_creation
  | grammar_rule
  | set_operation
  | return_statement
  | propose_statement
  | timeout_statement
  | if_statement
  | while_statement
  | for_statement
  | switch_statement
  ;

////////// STATEMENTS ///////////////////

statement_block
  : '{' statement* '}'
  ;

grammar_rule
  // label: if_statement
  : VARIABLE ':' if_statement
  ;

return_statement
  : (RETURN exp? | BREAK VARIABLE? | CANCEL | CANCEL_ALL | CONTINUE) ';' ;

if_statement
  : IF '(' boolean_exp ')' (statement) (ELSE (statement))?
  ;

if_exp
  : boolean_exp '?' exp ':' exp
  ;

while_statement
  : WHILE '(' boolean_exp ')' statement
  | DO statement WHILE '(' boolean_exp ')'
  ;

for_statement
  : FOR '(' (var_def | ';') exp? ';' exp? ')' statement
  | FOR '(' (DEC_VAR | type_spec)? VARIABLE ':' exp ')' statement
  // for loop with destructuring into a tuple
  | FOR '(' '(' VARIABLE ( ',' VARIABLE )+ ')' ':' exp ')' statement
  ;

propose_statement
  : (PROPOSE|TIMEOUT_BEHAVIOUR) '(' string_expression ')' statement_block
  ;

timeout_statement
  : TIMEOUT '(' string_expression ',' arithmetic ')' statement_block
  ;

switch_statement
  : SWITCH '(' exp ')' '{' switch_block '}'
  ;

switch_block
  : switch_group* switch_label*
  ;

switch_group
  : switch_label+ statement+
  ;

switch_label
  : CASE string_expression ':'
  | CASE VARIABLE ':'
  | DEFAULT ':'
  ;

var_def
  : FINAL?
    type_spec?
    variable
    ( '=' exp )? ';'
  ;

method_declaration
  : ('[' type_spec ']' '.')?
    (PUBLIC | PROTECTED | PRIVATE)?
    (DEC_VAR | type_spec) VARIABLE
    '('
       ((type_spec | DEC_VAR) VARIABLE (',' (type_spec | DEC_VAR) VARIABLE)*)?
    ')'
    (statement_block |';')
  ;

list_creation
  : type_spec?
    variable '=' '{' ((variable | STRING | INT | FLOAT)
                      (',' (variable | STRING | INT | FLOAT))*)?
                 '}' ';'
  ;

// add sth to a collection
set_operation
  : (variable | field_access) ('+=' | '-=') exp ';'
  ;


////////////////// EXPRESSIONS ////////////////////////////////

function_call
  : VARIABLE '(' ( exp ( ',' exp )* )? ')'
  ;

field_access
  : (variable | function_call | STRING | '(' exp ')')
    ( '.' simple_exp)+
  ;

type_spec
    : VARIABLE '[' ']'
    | VARIABLE '<' type_spec (',' type_spec)* '>'
    | VARIABLE
    ;

variable
  : VARIABLE '[' arithmetic ']'
  | VARIABLE
  ;

exp
  : '(' type_spec ')' exp
  | complex_exp
  | if_exp
  | string_expression
  | boolean_exp
  | new_exp
  | lambda_exp
  ;

complex_exp
  : arithmetic
  | literal_or_graph_exp
  | assignment
  | simple_exp
  ;

// TODO: is that all 'what you can assign to' (an lvalue), which can be:
// a variable, an array element, an rdf slot (did i forget sth?)
assignment
  : variable '=' exp
  | field_access '=' exp
  ;

simple_exp
  : '(' exp ')'
  | variable
  | function_call
  | field_access
  | STRING
  | FALSE
  | TRUE
  | NULL
  ;

boolean_exp
  : bool_and_exp '||' boolean_exp
  | bool_and_exp
  ;

bool_and_exp
  : negatable_b_exp '&&' bool_and_exp
  | negatable_b_exp
  ;

negatable_b_exp
  : '!' simple_b_exp
  | simple_b_exp
  ;

simple_b_exp
  : complex_exp // will be compiled to '!= null' or '!= 0' or 'has()' ...
  | complex_exp ('==' | '!=' | '<=' | '<' | '>=' | '>') exp
  ;

new_exp
  : NEW VARIABLE
  | NEW spec_func_call
  ;

spec_func_call
  : type_spec '(' ( exp ( ',' exp )* )? ')'
  ;

lambda_exp: '(' VARIABLE (',' VARIABLE)* ')' '->' (exp|statement_block);

string_expression : (complex_exp|if_exp) '+' exp | (complex_exp|if_exp) ;

literal_or_graph_exp
  : '#' da_token '(' ( da_token ( ',' ( da_token '=' da_token) )* ) ')'
  ;

da_token
  : '^' exp
  | VARIABLE
  | STRING
  | WILDCARD
  ;

// either a number or a term containing at least one operator
arithmetic
  : term ('-'|'+'|'|') arithmetic
  | term ('++'|'--')
  | term
  ;

term
  : factor ('*'|'/'|'%'|'&') term
  | factor
  ;

factor
  : number
  | '(' arithmetic ')'
  | '-' arithmetic
  ;

number
  : ( '++ | ' )?
    ( ( INT | FLOAT | VARIABLE )
      | field_access
      | function_call
    )
  ;


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
