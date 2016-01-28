/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

grammar DialogueCombinedGrammar;

/*
 * PARSER
 */

/// to parse multiple rules in one (test purposes atm)
grammar_file
  : grammar_rule*
  ;

grammar_rule
  : ( comment )* label if_statement
  ;

label
  : VARIABLE COLON
  ;

/// comments sind hidden
statement
  : //( comment )*
    ( exp SEMICOLON
    | propose_statement
    | if_statement
    | while_statement
    | for_statement
    | statement_block
    | SEMICOLON
    )
    //( comment )*
 ;

comment
  : MULTI_L_COMMENT
  | ONE_L_COMMENT
  ;

/// erlaubt die Schreibweise ohne {} bei einem einzigen Statement
if_statement
  : IF LPAR boolean_exp RPAR statement (ELSE statement)?
  ;

while_statement
  : WHILE LPAR boolean_exp RPAR loop_statement_block
  | DO loop_statement_block WHILE LPAR boolean_exp RPAR
  ;

/// for ((arg, val) : exp)
/// wir gehen provisorisch von exp aus, könnte aber auch boolean_exp (line 45???)
for_statement
  : FOR LPAR assignment SEMICOLON exp SEMICOLON exp RPAR loop_statement_block
  | FOR LPAR VARIABLE COLON exp RPAR loop_statement_block
  | FOR LPAR LPAR VARIABLE ( COMMA VARIABLE )+ RPAR COLON exp RPAR loop_statement_block  // ist exp richtig?
  ;

statement_block
  : LBRACE ( statement )* RBRACE
  ;

loop_statement_block
  : LBRACE ( loop_statement )* RBRACE
  ;


/// loop_statement = alle statements, nur dass in jedem Nachkömmling break oder continue aufgerufen werden können
loop_statement
  : ( comment )*
    ( exp SEMICOLON
    | (CONTINUE | BREAK) SEMICOLON
    | loop_propose_statement // macht es Sinn, im propose abbrechen zu können??
    | loop_if_statement
    | while_statement
    | for_statement
    | loop_statement_block
    | SEMICOLON
    )
    ( comment )*
  ;

loop_propose_statement
  : PROPOSE LPAR propose_arg RPAR loop_propose_block
  ;

loop_propose_block
  : LBRACE loop_statement+ RBRACE
  ;

loop_if_statement
  : IF LPAR boolean_exp RPAR loop_statement ( ELSE loop_statement )?
  ;

function_call
  : ( VARIABLE | field_access_vfunc ) LPAR ( exp ( COMMA exp )* )? RPAR
  ;

/// hack to allow things like func(x).time but also g.func(x).time or (g.func(x)).getY() (avoiding left recursion)
field_access_vfunc
  : ( VARIABLE
    | LPAR function_call RPAR
    )
    ( DOT ( VARIABLE
          | LPAR? function_call RPAR?
          )
    )+
    function_call?
  ;

field_access
  : ( VARIABLE
    | function_call
    )
    ( ( DOT VARIABLE
      | LPAR? function_call RPAR?
      )
    )+
    function_call?
  ;

exp
  : LPAR exp RPAR
  | exp_braceless
  ;

exp_braceless
  : boolean_exp
  | field_access
  | assignment
  | arithmetic
  | function_call
  | literal_or_graph_exp
  | ( STRING
    | WILDCARD
    | VARIABLE
    | FALSE
    | TRUE
    | NULL
    )
  ;

simple_b_exp
  : LPAR boolean_exp RPAR
  | simple_b_exp_braceless
  | NOT boolean_exp
  ;

simple_b_exp_braceless
  : arithmetic
  | function_call
  | field_access
  | assignment
  | ( STRING
    | WILDCARD
    | VARIABLE
    | FALSE
    | TRUE
    | NULL
    )
  ;

boolean_exp
  : simple_b_exp ( boolean_op exp )*
  ;

boolean_op
  : NOT
  | EQUAL
  | AND
  | OR
  | NOT_EQUAL
  | SMALLER_EQUAL
  | SMALLER
  | GREATER_EQUAL
  | GREATER
  ;

/// Vgl rulesproto Zeilen 145 und 150: propose ohne () erlauben??
propose_statement
  : PROPOSE LPAR propose_arg RPAR propose_block
  ;

propose_block
  : LBRACE statement+ RBRACE
  ;

string_expression
  : ( ( STRING | VARIABLE )
    | field_access
    )
    ( PLUS
      ( ( STRING | VARIABLE )
      | field_access
      )
    )*
  ;

propose_arg
  : string_expression
  ;

/// is the first one always a variable and should this be forced here?
literal_or_graph_exp
  :  LITERAL_OR_GRAPH LPAR ( ( exp | mysterious_binding_exp )
                             ( COMMA
                               ( exp | mysterious_binding_exp )
                             )*
                           )?
     RPAR
  ;

assignment
  : ( ( DEC_VAR )? VARIABLE
      | field_access
    )
    ASSIGN exp
  ;

/// dient dem Abfangen von Rechnungen
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

/// entweder eine einfache Zahl oder Rechnung mit min 1 Operator
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
mysterious_binding_exp
  : VARIABLE ASSIGN QUESTION VARIABLE
  ;


/*
 * LEXER
 */

/// keywords + true, false, null:
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

/// character literal (starting with ' ):
CHARACTER: '\''.'\'';

/// string (starting with " ):
STRING: '\"'.*?'\"';

/// assignments
ASSIGN: '=';

/// separators:
LPAR: '(';
RPAR: ')';
LBRACE: '{';
RBRACE: '}';
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

/// additional operators:
QUESTION: '?';
COLON: ':';

/// special for dialogue grammar:
WILDCARD: '_';
LITERAL_OR_GRAPH: '@'( '0'..'9'|'A'..'z'|'_' )+;
PROPOSE: 'propose';
DEC_VAR: 'var';

/// comments (starting with /* or //):
ONE_L_COMMENT: '//'.*?'\n' -> channel(HIDDEN);  // required so you can add comments at every time
MULTI_L_COMMENT: '/*'.*?'*/' -> channel(HIDDEN);

/// whitespace
WS: ( ' ' | '\t' | '\r' | '\n' ) -> channel(HIDDEN);

/// identifiers (starting with "java letter"):
VARIABLE: ('A'..'z'|'_')('0'..'9'|'A'..'z'|'_'|'$')*;

/// numeric literal (starting with number):
INT: ('-')?('1'..'9')?('0'..'9')+;
FLOAT: ('-')?('0'('.'('0'..'9')+) | ('1'..'9')('0'..'9')*('.'('0'..'9')+));

/// SPARQL queries: müssen wohl oder übel später iwann so formuliert werden, dass die Anfrage funktioniert -> gibt es ein besonderes Schema, das immer erfolgreich umschreibt, oder nicht? wenn nein, muss jede Art von Anfrage vorbereitet werden, d.h., der Benutzer der Grammatik kann ohne Weiteres keine nicht vorgesehenen Dinge abfragen