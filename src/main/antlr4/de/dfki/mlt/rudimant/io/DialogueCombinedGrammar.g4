/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

grammar DialogueCombinedGrammar;

// PARSER
// -------------------------------

grammar_rule:           (comment)*
                label
                if_statement
    ;

label:          VARIABLE COLON;

statement:	(comment)*
             (exp SEMICOLON
		| propose_statement
		| if_statement
		| while_statement
		| for_statement
		| statement_block
		| SEMICOLON
            ) (comment)*
         ;

comment: MULTI_L_COMMENT | ONE_L_COMMENT;

if_statement:	IF LPAR boolean_exp RPAR statement (ELSE statement)?;	// erlaubt die Schreibweise ohne {} bei einem einzigen Statement

while_statement:	WHILE LPAR boolean_exp RPAR loop_statement_block
			| DO loop_statement_block WHILE LPAR boolean_exp RPAR;

for_statement:	FOR LPAR assignment SEMICOLON exp SEMICOLON exp RPAR loop_statement_block
		| FOR LPAR VARIABLE COLON exp RPAR loop_statement_block
                | FOR LPAR LPAR VARIABLE ( COMMA VARIABLE )+ RPAR COLON exp RPAR loop_statement_block;	// ist exp richtig?
                   // for ((arg, val) : exp)
                  // wir gehen provisorisch von exp aus, könnte aber auch boolean_exp (line 45???)

statement_block:	LBRACE (statement)* RBRACE;

loop_statement_block:	LBRACE (loop_statement)* RBRACE;

// loop_statement = alle statements, nur dass in jedem Nachkömmling break oder continue aufgerufen werden können

loop_statement:	(comment)*
                (exp SEMICOLON
                    | (CONTINUE | BREAK) SEMICOLON
                    | loop_propose_statement        // macht es Sinn, im propose abbrechen zu können??
                    | loop_if_statement
                    | while_statement
                    | for_statement
                    | loop_statement_block
                    | SEMICOLON
                ) (comment)*;

loop_propose_statement: PROPOSE LPAR propose_arg RPAR loop_propose_block;

loop_propose_block: LBRACE loop_statement+ RBRACE;

loop_if_statement: IF LPAR boolean_exp RPAR loop_statement (ELSE loop_statement)?;

function_call: (VARIABLE | passender_name) LPAR (exp (COMMA exp)*)? RPAR;

passender_name: VARIABLE (DOT VARIABLE)+;

exp: LPAR exp RPAR
     | exp_braceless
   ;

exp_braceless:	boolean_exp
                | passender_name
                | assignment
                | arithmetic
                | function_call
                | literal_or_graph_exp
                | 
                (
                    STRING
                  | WILDCARD
                  | VARIABLE
                  | NULL
                )
              ;

simple_b_exp: LPAR simple_b_exp RPAR
            | simple_b_exp_braceless
          ;

simple_b_exp_braceless:	arithmetic
                        | function_call
                        | passender_name
                        | literal_or_graph_exp
                        | 
                        (
                            STRING
                          | WILDCARD	
                          | VARIABLE
                          | FALSE
                          | TRUE
                          | NULL
                        )
                  ;

boolean_exp:	LPAR boolean_exp RPAR (boolean_op exp)*
                | simple_b_exp (boolean_op exp)*
                | NOT boolean_exp
                ;

boolean_op:	NOT | EQUAL | AND | OR | NOT_EQUAL | SMALLER_EQUAL | SMALLER | GREATER_EQUAL | GREATER;

// Vgl rulesproto Zeilen 145 und 150: propose ohne () erlauben??
propose_statement: 	PROPOSE LPAR propose_arg RPAR propose_block;

propose_block:	LBRACE statement+ RBRACE; 

string_expression: ((STRING | VARIABLE) | passender_name) (PLUS ((STRING | VARIABLE) | passender_name))*;

propose_arg: string_expression;

literal_or_graph_exp:	LITERAL_OR_GRAPH LPAR (exp (COMMA exp)*)? RPAR;

assignment:	((DEC_VAR)? VARIABLE | passender_name) ASSIGN exp;

// dient dem Abfangen von Rechnungen
number:         (INCREMENT | DECREMENT)? ((INT | FLOAT | VARIABLE) | passender_name);
arithmetic_operator:        MINUS | PLUS | DIV | MUL | MOD;
arithmetic:     number (arithmetic_operator number)*;





// LEXER
// -------------------------------------


// keywords + true, false, null:
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


// character literal (starting with ' ):
	CHARACTER: '\''.'\'';		

// string (starting with " ):
	STRING: '\"'.*?'\"';

// assignments
	ASSIGN: '=';

// separators:
	LPAR: '(';
	RPAR: ')';
	LBRACE: '{';
	RBRACE: '}';
	SEMICOLON: ';';
	COMMA: ',';
	DOT: '.';

// boolean operators:
        NOT: '!';
	EQUAL: '==';
	AND: '&'('&')?;
	OR: '|'('|')?;
	NOT_EQUAL: '!=';
	SMALLER_EQUAL: '<=';
	SMALLER: '<';			
	GREATER_EQUAL: '>=';		
	GREATER: '>';			
	
// mathematical operators:
        PLUS: '+';
        INCREMENT: '++';
	MINUS: '-';
        DECREMENT: '--';
	DIV: '/';
	MUL: '*';
        MOD: '%';
    
// additional operators:
        QUESTION: '?';
	COLON: ':';
        


//	special for dialogue grammar:
	LOCAL_VAR: '_'('0'..'9'|'A'..'z'|'_')+;
	WILDCARD: '_';
	LITERAL_OR_GRAPH: '@'('0'..'9'|'A'..'z'|'_')+;
	PROPOSE: 'propose';
        DEC_VAR: 'var';
	
    
// comments (starting with /* or //):
	ONE_L_COMMENT: '//'.*?'\n' -> channel(HIDDEN);	// required so you can add comments at every time	
	MULTI_L_COMMENT: '/*'.*?'*/' -> channel(HIDDEN);		

// whitespace 
        WS: ( ' ' | '\t' | '\r' | '\n' ) -> channel(HIDDEN);    
    
// identifiers (starting with "java letter"):
	VARIABLE: ('A'..'z')('0'..'9'|'A'..'z'|'_'|'$')*;

// numeric literal (starting with number):
	INT: ('-')?('1'..'9')?('0'..'9')+;
	FLOAT: ('-')?('0'('.'('0'..'9')+) | ('1'..'9')('0'..'9')*('.'('0'..'9')+));

// SPARQL queries: müssen wohl oder übel später iwann so formuliert werden, dass die Anfrage funktioniert -> gibt es ein besonderes Schema, das immer erfolgreich umschreibt, oder nicht? wenn nein, muss jede Art von Anfrage vorbereitet werden, d.h., der Benutzer der Grammatik kann ohne Weiteres keine nicht vorgesehenen Dinge abfragen
