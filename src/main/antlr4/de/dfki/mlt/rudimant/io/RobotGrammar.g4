/*
 * Grammar dedicated to create a parser and a lexer for dialogue rules in
 * DFKI robot language
 */

grammar RobotGrammar;

/*
 * PARSER
 */

/// start rule
// the part "LBRACE statement RBRACE" is ridiciulous but currently solves a parsing exception...
grammar_file
  : imports*
    (comment
    (grammar_rule | method_declaration | statement 
    | LBRACE statement RBRACE | ANNOTATION | imports))*
    comment
  ;

imports
  : IMPORT VARIABLE SEMICOLON
  ;

method_declaration
  : (PUBLIC | PROTECTED | PRIVATE)? (DEC_VAR | type_spec) VARIABLE LPAR
    ((type_spec | DEC_VAR) VARIABLE (COMMA (type_spec | DEC_VAR) VARIABLE)*)?
    RPAR statement_block;

grammar_rule
  : comment label comment if_statement
  ;

label
  : VARIABLE COLON
  ;


statement
  : (statement_block
    | (comment
    (exp SEMICOLON
    | list_creation
    |  grammar_rule
    | set_operation SEMICOLON
    | return_statement
    | propose_statement
    | timeout_statement
    | if_statement
    | while_statement
    | for_statement
    | var_def
    | fun_def
    //| SEMICOLON   <- we don't really need it, and it's a pain to find in parser
    ))) comment
 ;

return_statement: RETURN exp? SEMICOLON;

comment
  : (MULTI_L_COMMENT | ONE_L_COMMENT | JAVA_CODE)*
  ;

if_statement
  : IF LPAR boolean_exp RPAR (statement) (ELSE (statement))?
  ;

if_exp
  : boolean_exp QUESTION exp COLON exp
  ;

while_statement
  : WHILE LPAR boolean_exp RPAR loop_statement_block
  | DO loop_statement_block WHILE LPAR boolean_exp RPAR
  ;

for_statement
  : FOR LPAR assignment SEMICOLON exp SEMICOLON exp? RPAR loop_statement_block
  | FOR LPAR (DEC_VAR | type_spec)? VARIABLE COLON exp RPAR loop_statement_block
  | FOR LPAR LPAR VARIABLE ( COMMA VARIABLE )+ RPAR COLON exp RPAR loop_statement_block
  ;

statement_block
  : comment LBRACE comment statement* RBRACE
  ;

loop_statement_block
  : comment LBRACE comment (statement | ((CONTINUE | BREAK) SEMICOLON))* RBRACE
  ;

loop_propose_statement
  : PROPOSE LPAR propose_arg RPAR loop_statement_block/*loop_propose_block*/
  ;

/*loop_propose_block
  : comment LBRACE loop_statement+ RBRACE
  ;*/

loop_if_statement
  : IF LPAR boolean_exp RPAR (statement | (CONTINUE | BREAK) SEMICOLON)
    ( ELSE (statement | (CONTINUE | BREAK) SEMICOLON) )?
  ;

function_call
  : VARIABLE LPAR ( exp ( COMMA exp )* )? RPAR
  ;

funccall_on_object
  : (variable | function_call | field_access | STRING | LPAR exp RPAR) DOT function_call
  ;

field_access
  : (VARIABLE | function_call)
    ( ( DOT (VARIABLE | LPAR function_call RPAR | function_call )) )+
  ;

type_spec 
    : VARIABLE LBRACK RBRACK
    | VARIABLE SMALLER type_spec GREATER
    | VARIABLE
    ;

variable 
  : VARIABLE LBRACK ((VARIABLE | INT) | arithmetic) RBRACK
  | VARIABLE 
  ;

exp
  : comment
  (LPAR exp RPAR
  | variable
  | field_access
  | funccall_on_object
  | assignment
  | if_exp
  | arithmetic
  | function_call
  | literal_or_graph_exp
  | ( STRING
    | WILDCARD
    | FALSE
    | TRUE
    | NULL
    )
  | boolean_exp
  | string_expression
  )
  comment
  ;

simple_exp
  : comment
  (LPAR exp RPAR
  | variable
  | arithmetic
  | function_call
  | literal_or_graph_exp
  | field_access
  | funccall_on_object
  | assignment
  | ( STRING
    | WILDCARD
    | FALSE
    | TRUE
    | NULL
    )
  | NOT boolean_exp
  )
  comment
  ;

simple_b_exp
  : simple_exp boolean_op2 exp
  | simple_exp
  ;

boolean_exp
  : simple_b_exp boolean_op1 boolean_exp
  | simple_b_exp boolean_op1 exp
  | simple_b_exp
  //| NOT simple_b_exp
  ;

boolean_op1
  : AND
  | OR
  ;

boolean_op2
  : EQUAL
  | NOT_EQUAL
  | SMALLER_EQUAL
  | SMALLER
  | GREATER_EQUAL
  | GREATER
  ;

lambda_exp: LPAR (DEC_VAR? VARIABLE (COMMA DEC_VAR? VARIABLE)*)? RPAR ARROW exp;

timeout_statement: TIMEOUT LPAR STRING COMMA INT RPAR statement_block;

propose_statement
  : PROPOSE LPAR propose_arg RPAR statement_block
  ;

/*
propose_block
  : comment LBRACE statement+ RBRACE
  ;*/

string_expression
  : simple_exp
    ( PLUS exp)*
  //| ( ( STRING | variable )
  //  | field_access | string_expression
  //  )
  //  ( PLUS
  //    ( ( STRING | variable )
  //    | field_access | string_expression
  //    )
  //  )*
  ;

propose_arg
  : string_expression
  ;

literal_or_graph_exp
  :  HASH da_token LPAR ( da_token 
                             ( COMMA
                               ( da_token ASSIGN da_token)
                             )*
                           )
     RPAR
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
    ASSIGN exp
  ;

var_def
  : type_spec variable SEMICOLON
  ;

fun_def
  : type_spec variable LPAR
    ( type_spec VARIABLE (COMMA type_spec VARIABLE)* )? RPAR SEMICOLON
  ;

list_creation
  : (VARIABLE SMALLER type_spec GREATER)? variable ASSIGN LBRACE ((variable | STRING | INT | FLOAT)
                            (COMMA (variable | STRING | INT | FLOAT))*)? 
    RBRACE SEMICOLON
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

arithmetic_operator
  : arithmetic_dot_operator
  | arithmetic_lin_operator
  ;

arithmetic_dot_operator
  : DIV
  | MUL
  | MOD
  ;

arithmetic_lin_operator
  : MINUS
  | PLUS
  ;

/// either a number or a term containing at least one operator
arithmetic
  : term ( arithmetic_lin_operator term )*
  ;

term
  : factor ( arithmetic_dot_operator factor )*
  ;

factor
  : number
  | LPAR arithmetic RPAR
  | MINUS arithmetic
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
TIMEOUT: 'timeout';
VARIABLE_MARKER: '^';

/// comments (starting with /* or //):
JAVA_CODE: '/*@'.*?'@*/';//-> channel(HIDDEN);
ONE_L_COMMENT: '//'.*?'\n' ;//-> channel(HIDDEN);
MULTI_L_COMMENT: '/*'.*?'*/' ;//-> channel(HIDDEN);

/// whitespace
WS: ( ' ' | '\t' | '\r' | '\n' ) -> channel(HIDDEN);

/// identifiers (starting with "java letter"):
VARIABLE: ('A'..'Z'|'a'..'z'|'_')('0'..'9'|'A'..'Z'|'a'..'z'|'_'|'$')*;

/// numeric literal (starting with - or number):
INT: ('-')?('1'..'9')?('0'..'9')+;
FLOAT: ('-')?('0'('.'('0'..'9')+) | ('1'..'9')('0'..'9')*('.'('0'..'9')+));
