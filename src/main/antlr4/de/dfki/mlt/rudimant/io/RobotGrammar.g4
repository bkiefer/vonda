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
  : imports*
    (//comment
    (grammar_rule | method_declaration | statement
    | '{' statement '}' | ANNOTATION | imports))*
    //comment
  ;

imports
  : IMPORT VARIABLE ';'
  ;

method_declaration
  : (PUBLIC | PROTECTED | PRIVATE)? (DEC_VAR | type_spec) VARIABLE '('
    ((type_spec | DEC_VAR) VARIABLE (',' (type_spec | DEC_VAR) VARIABLE)*)?
    ')' statement_block;

grammar_rule
  // : comment label comment if_statement
  : VARIABLE COLON if_statement
  ;

/*
label
  : VARIABLE COLON
  ;
*/

statement
  : (statement_block
    | (// comment
    (exp ';'
    | list_creation
    |  grammar_rule
    | set_operation ';'
    | return_statement
    | propose_statement
    //| timeout_statement
    | if_statement
    | while_statement
    | for_statement
    | var_def
    | fun_def
    //| ';'   <- we don't really need it, and it's a pain to find in parser
    ))) // comment
 ;

return_statement: RETURN exp? ';';

/* comment
  : (MULTI_L_COMMENT | ONE_L_COMMENT | JAVA_CODE)*
  ;*/

if_statement
  : IF '(' boolean_exp ')' (statement) (ELSE (statement))?
  ;

if_exp
  : boolean_exp QUESTION exp COLON exp
  ;

while_statement
  : WHILE '(' boolean_exp ')' loop_statement_block
  | DO loop_statement_block WHILE '(' boolean_exp ')'
  ;

for_statement
  : FOR '(' assignment ';' exp ';' exp? ')' loop_statement_block
  | FOR '(' (DEC_VAR | type_spec)? VARIABLE COLON exp ')' loop_statement_block
  | FOR '(' '(' VARIABLE ( ',' VARIABLE )+ ')' COLON exp ')' loop_statement_block
  ;

statement_block
  // : comment '{' comment statement* '}'
  : '{' statement* '}'
  ;

loop_statement_block
  //: comment '{' comment (statement | ((CONTINUE | BREAK) ';'))* '}'
  : '{' (statement | ((CONTINUE | BREAK) ';'))* '}'
  ;

// TODO: I THINK THIS ENTAILS THE NORMAL propose_statement
loop_propose_statement
  : PROPOSE '(' string_expression ')' loop_statement_block/*loop_propose_block*/
  ;

/*loop_propose_block
  : comment '{' loop_statement+ '}'
  ;*/

loop_if_statement
  : IF '(' boolean_exp ')' (statement | (CONTINUE | BREAK) ';')
    ( ELSE (statement | (CONTINUE | BREAK) ';') )?
  ;

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
  : // comment
  ('(' exp ')' //sexp
  | funccall_on_object //sexp
  | variable //sexp
  | assignment //sexp
  | if_exp
  | arithmetic //sexp
  | function_call //sexp
  | literal_or_graph_exp //sexp
  | ( STRING // all sexp
    | WILDCARD
    | FALSE
    | TRUE
    | NULL
    )
  | boolean_exp
  | string_expression
  | field_access // sexp
  )
  // comment
  ;

simple_exp
  : // comment
  ('(' exp ')'
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
  | NOT boolean_exp
  )
  // comment
  ;

boolean_exp
  : bool_and_exp
  | boolean_exp '||' bool_and_exp
  // TODO: CHECK WHY THIS IS IN, SEEMS WRONG TO ME
//  | simple_b_exp boolean_op1 exp
  //| NOT simple_b_exp
  ;

bool_and_exp
  : simple_b_exp
  | bool_and_exp '&&' simple_b_exp
	;

simple_b_exp
  : simple_exp
  | simple_exp ('==' | '!=' | '<=' | '<' | '>=' | '>') exp
  ;

// TODO: IS THIS STILL USED?
lambda_exp: '(' (DEC_VAR? VARIABLE (',' DEC_VAR? VARIABLE)*)? ')' ARROW exp;

//timeout_statement: TIMEOUT '(' STRING ',' INT ')' statement_block;

// TODO: seems obsolete (see above))
propose_statement
  : PROPOSE '(' string_expression ')' statement_block
  ;

/*
propose_block
  : comment '{' statement+ '}'
  ;*/

string_expression : simple_exp ( '+' exp )* ;

/*
propose_arg
  : string_expression
  ;
*/

literal_or_graph_exp
  :  HASH da_token '(' ( da_token ( ',' ( da_token '=' da_token) )* ) ')'
  ;

da_token
    : VARIABLE_MARKER exp
    | VARIABLE
    | STRING
    ;

assignment
  : ( ( DEC_VAR | type_spec)? variable
      | field_access
    )
    '=' exp
  ;

var_def
  : type_spec variable ';'
  ;

fun_def
  : type_spec variable '('
    ( type_spec VARIABLE (',' type_spec VARIABLE)* )? ')' ';'
  ;

list_creation
  : (VARIABLE '<' type_spec '>')? variable '=' '{' ((variable | STRING | INT | FLOAT)
                            (',' (variable | STRING | INT | FLOAT))*)?
    '}' ';'
  ;

set_operation
  : (variable | field_access) (ADD | REMOVE) number
  ;

number
  : ( INCREMENT | DECREMENT )?
    ( ( INT | FLOAT | VARIABLE )
      | field_access
      | function_call
    )
  ;

/*
arithmetic_operator
  : arithmetic_dot_operator
  | arithmetic_lin_operator
  ;

arithmetic_dot_operator
  : '/'
  | '*'
  | '%'
  ;

arithmetic_lin_operator
  : '-'
  | '+'
  ;
*/

/// either a number or a term containing at least one operator
arithmetic
  : term ( ('-'|'+') term )*
  ;

term
  : factor ( ('*'|'/'|'%') factor )*
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
AND: '&'('&')?;
OR: '|'('|')?;
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

/// identifiers (starting with "java letter"):
VARIABLE: ('A'..'Z'|'a'..'z'|'_')('0'..'9'|'A'..'Z'|'a'..'z'|'_'|'$')*;

/// numeric literal (starting with - or number):
INT: ('-')?('1'..'9')?('0'..'9')+;
FLOAT: ('-')?('0'('.'('0'..'9')+) | ('1'..'9')('0'..'9')*('.'('0'..'9')+));
