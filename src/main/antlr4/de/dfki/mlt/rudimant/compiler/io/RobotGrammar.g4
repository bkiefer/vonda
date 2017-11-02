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
  : ( method_declaration
      | statement
      | ANNOTATION
      | imports
    )*
  ;

imports
  : IMPORT VARIABLE ';'
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
  : VARIABLE COLON if_statement
  ;

// TODO: what about return label; ??
return_statement
  : (RETURN exp? | BREAK VARIABLE? | CANCEL | CANCEL_ALL | CONTINUE) ';' ;

if_statement
  : IF '(' boolean_exp ')' (statement) (ELSE (statement))?
  ;

if_exp
  : boolean_exp QUESTION exp COLON exp
  ;

while_statement
  : WHILE '(' boolean_exp ')' statement
  | DO statement WHILE '(' boolean_exp ')'
  ;

for_statement
  : FOR '(' assignment? ';' exp? ';' exp? ')' statement
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

method_declaration
  : ('[' type_spec ']' '.')?
    (PUBLIC | PROTECTED | PRIVATE)? (DEC_VAR | type_spec) VARIABLE '('
    ((type_spec | DEC_VAR) VARIABLE (',' (type_spec | DEC_VAR) VARIABLE)*)?
    ')' (statement_block |';')
  ;

list_creation
  : type_spec?
    variable '=' '{' ((variable | STRING | INT | FLOAT)
                      (',' (variable | STRING | INT | FLOAT))*)?
                 '}' ';'
  ;

// TODO: WHAT'S THIS?
set_operation
  : (variable | field_access) (ADD | REMOVE) exp ';'
  ;


////////////////// EXPRESSIONS ////////////////////////////////

function_call
  : VARIABLE '(' ( exp ( ',' exp )* )? ')'
  ;

// TODO: the next two have to be merged into a more generic one. What to do
// about field access with a '.' can only be decided in the type visitor, when
// the type of the object to apply to is known
/*funccall_on_object
  : (variable | function_call | field_access | STRING | '(' exp ')')
   '.' function_call
  ;*/

// TODO: allowing exp after . is too much, should we split exp into exp and
// complex exp??
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
  :
  //| funccall_on_object
    arithmetic
  | literal_or_graph_exp
  | assignment
  | simple_exp
  ;

simple_exp
  : 
  '(' exp ')'
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
  : NOT simple_b_exp
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

//lambda_exp: '(' DEC_VAR? VARIABLE (',' DEC_VAR? VARIABLE)* ')'
//    '->' (exp|statement_block);
lambda_exp: '(' VARIABLE (',' VARIABLE)* ')' '->' (exp|statement_block);

string_expression : (complex_exp|if_exp) '+' exp | (complex_exp|if_exp) ;

literal_or_graph_exp
  : HASH da_token '(' ( da_token ( ',' ( da_token '=' da_token) )* ) ')'
  ;

da_token
  : VARIABLE_MARKER exp
  | VARIABLE
  | STRING
  | WILDCARD
  ;

// TODO: is that all 'what you can assign to' (an lvalue), which can be:
// a variable, an array element, an rdf slot (did i forget sth?)
assignment
  : FINAL type_spec? variable '=' exp
  | type_spec? variable '=' exp
  | field_access '=' exp
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
  : ( INCREMENT | DECREMENT )?
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

/// character literal (starting with ' ):
CHARACTER: '\''.'\'';

/// string (starting with " ):
STRING: '\"'(~('\\'|'"')|'\\"')*'\"';

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
TIMEOUT: 'timeout';
TIMEOUT_BEHAVIOUR: 'behaviour_timeout';
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
