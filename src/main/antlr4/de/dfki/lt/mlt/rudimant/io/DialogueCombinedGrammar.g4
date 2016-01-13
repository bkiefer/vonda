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
		| literal_or_graph_statement
		| if_statement
		| while_statement
		| for_statement
		| statement_block
                //| arithmetic
		| SEMICOLON
            ) (comment)*
         ;

comment: MULTI_L_COMMENT | ONE_L_COMMENT;

if_statement:	IF LPAR boolean_exp RPAR statement (ELSE statement)?;	// erlaubt die Schreibweise ohne {} bei einem einzigen Statement

while_statement:	WHILE LPAR boolean_exp RPAR while_statement_block
			| DO while_statement_block WHILE LPAR boolean_exp RPAR;

for_statement:	FOR LPAR assignment SEMICOLON exp SEMICOLON exp RPAR statement_block
		| FOR LPAR VARIABLE COLON exp RPAR statement_block
                | FOR LPAR LPAR VARIABLE ( COMMA VARIABLE )+ RPAR COLON exp RPAR statement_block;	// ist exp richtig?
                   // for ((arg, val) : exp)
                  // wir gehen provisorisch von exp aus, könnte aber auch boolean_exp (line 45???)

statement_block:	LBRACE (statement)* RBRACE;

while_statement_block:	LBRACE (w_statement)* RBRACE;

w_statement:	statement | (CONTINUE | BREAK) SEMICOLON;

function_call: (VARIABLE | passender_name) LPAR (exp (COMMA exp)*)? (COMMA usw)? RPAR;

passender_name: VARIABLE (DOT VARIABLE)+;

usw: DOT DOT DOT;

exp: LPAR exp RPAR
     | exp_braceless
   ;

exp_braceless:	boolean_exp
                | passender_name
                | assignment
                | arithmetic
                | function_call
                | literal_or_graph_exp  // ist das hier valide oder erlaubt es zu viel?
                | 
                (
                    STRING
                  | WILDCARD
                  | VARIABLE
                  | INT
                  | FLOAT
                )
              ;

simple_exp: LPAR simple_exp RPAR
            | simple_exp_braceless
          ;

simple_exp_braceless:	assignment  // hack to avoid left recursion
                        | arithmetic
                        | function_call
                        | literal_or_graph_exp  // ist das hier valide oder erlaubt es zu viel?
                        | passender_name
                        | 
                        (
                            STRING
                          | WILDCARD	
                          | VARIABLE
                          | INT
                          | FLOAT
                        )
                  ;

boolean_exp:	simple_exp (boolean_op exp)+
                | TRUE (boolean_op exp)*
                | FALSE (boolean_op exp)*
                | NOT boolean_exp
                ;

boolean_op:	NOT | EQUAL | AND | OR | NOT_EQUAL | SMALLER_EQUAL | SMALLER | GREATER_EQUAL | GREATER;

// Vgl rulesproto Zeilen 145 und 150: propose ohne () erlauben??
propose_statement: 	PROPOSE LPAR propose_arg RPAR propose_block;

propose_block:	LBRACE statement+ RBRACE; 

propose_arg: passender_name  // string expression 
            |
            (
                VARIABLE
               | STRING
            )
            ;

literal_or_graph_exp:	LITERAL_OR_GRAPH LPAR (exp (COMMA exp)* (COMMA usw)?)? RPAR;

literal_or_graph_statement:	literal_or_graph_exp SEMICOLON;  // beginnt immer mit @

assignment:	((DEC_VAR)? VARIABLE | passender_name) ASSIGN exp;

// dient dem Abfangen von Rechnungen
number:         INT | FLOAT;
arithmetic_operator:        MINUS | PLUS | DIV | MUL | MOD;
arithmetic:     number (arithmetic_operator number)+;





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
	MINUS: '-';
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
