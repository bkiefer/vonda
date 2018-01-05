/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 1998-2015  Gerwin Klein <lsf@jflex.de>                    *
 * All rights reserved.                                                    *
 *                                                                         *
 * License: BSD                                                            *
 *                                                                         *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

/* Java 1.2 language lexer specification */

/* Use together with unicode.flex for Unicode preprocesssing */
/* and java12.cup for a Java 1.2 parser                      */

/* Note that this lexer specification is not tuned for speed.
   It is in fact quite slow on integer and floating point literals,
   because the input is read twice and the methods used to parse
   the numbers are not very fast.
   For a production quality application (e.g. a Java compiler)
   this could be optimized */

package de.dfki.mlt.rudimant.compiler.io;

%%

%public
%class VondaLexer
%implements VondaGrammar.Lexer

%unicode

%char
%line
%column

%byaccj

%{

%}

/* main character classes */
LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]

WhiteSpace = {LineTerminator} | [ \t\f]

/* comments */
Comment = {TraditionalComment} | {EndOfLineComment} |
          {DocumentationComment}

TraditionalComment = "/*" [^*] ~"*/" | "/*" "*"+ "/"
EndOfLineComment = "//" {InputCharacter}* {LineTerminator}?
DocumentationComment = "/*" "*"+ [^/*] ~"*/"

/* identifiers */
Identifier = [:jletter:][:jletterdigit:]*

/* integer literals */
DecIntegerLiteral = 0 | [1-9][0-9]*
DecLongLiteral    = {DecIntegerLiteral} [lL]

HexIntegerLiteral = 0 [xX] 0* {HexDigit} {1,8}
HexLongLiteral    = 0 [xX] 0* {HexDigit} {1,16} [lL]
HexDigit          = [0-9a-fA-F]

OctIntegerLiteral = 0+ [1-3]? {OctDigit} {1,15}
OctLongLiteral    = 0+ 1? {OctDigit} {1,21} [lL]
OctDigit          = [0-7]

/* floating point literals */
FloatLiteral  = ({FLit1}|{FLit2}|{FLit3}) {Exponent}? [fF]
DoubleLiteral = ({FLit1}|{FLit2}|{FLit3}) {Exponent}?

FLit1    = [0-9]+ \. [0-9]*
FLit2    = \. [0-9]+
FLit3    = [0-9]+
Exponent = [eE] [+-]? [0-9]+

/* string and character literals */
StringCharacter = [^\r\n\"\\]
SingleCharacter = [^\r\n\'\\]

%state STRING, CHARLITERAL

%%

<YYINITIAL> {

  /* keywords */
  "break"                        { return VondaGrammar.Lexer.BREAK; }
  "cancel"                       { return VondaGrammar.Lexer.CANCEL; }
  "cancel_all"                   { return VondaGrammar.Lexer.CANCEL_ALL; }
  "case"                         { return VondaGrammar.Lexer.CASE; }
  "continue"                     { return VondaGrammar.Lexer.CONTINUE; }
  "default"                      { return VondaGrammar.Lexer.DEFAULT; }
  "do"                           { return VondaGrammar.Lexer.DO; }
  "else"                         { return VondaGrammar.Lexer.ELSE; }
  "final"                        { return VondaGrammar.Lexer.FINAL; }
  "for"                          { return VondaGrammar.Lexer.FOR; }
  "if"                           { return VondaGrammar.Lexer.IF; }
  "import"                       { return VondaGrammar.Lexer.IMPORT; }
  "new"                          { return VondaGrammar.Lexer.NEW; }
  "null"                         { return VondaGrammar.Lexer.NULL; }
  "private"                      { return VondaGrammar.Lexer.PRIVATE; }
  "propose"                      { return VondaGrammar.Lexer.PROPOSE; }
  "protected"                    { return VondaGrammar.Lexer.PROTECTED; }
  "public"                       { return VondaGrammar.Lexer.PUBLIC; }
  "return"                       { return VondaGrammar.Lexer.RETURN; }
  "short"                        { return VondaGrammar.Lexer.SHORT; }
  "switch"                       { return VondaGrammar.Lexer.SWITCH; }
  "timeout"                      { return VondaGrammar.Lexer.TIMEOUT; }
  "timeout_behaviour"            { return VondaGrammar.Lexer.TIMEOUT_BEHAVIOUR; }
  "while"                        { return VondaGrammar.Lexer.WHILE; }

  /* boolean literals */
  "true"                         { return VondaGrammar.Lexer.BOOLEAN_LITERAL, true; }
  "false"                        { return VondaGrammar.Lexer.BOOLEAN_LITERAL, false; }

  /* null literal */
  "null"                         { return VondaGrammar.Lexer.NULL; }


  /* separators */
  "(" |
  ")" |
  "{" |
  "}" |
  "[" |
  "]" |
  ";" |
  "," |
  "."                            { return (int)yycharat(0); }

  /* operators */
  "=" |
  ">" |
  "<" |
  "!" |
  "~" |
  "?" |
  ":" |
  "+" |
  "-" |
  "*" |
  "/" |
  "&" |
  "|" |
  "^" |
  "#" |
  "%"                            { return (int)yycharat(0); }

  "->"                           { return VondaGrammar.Lexer.ARROW; }
  "=="                           { return VondaGrammar.Lexer.EQEQ; }
  "<="                           { return VondaGrammar.Lexer.LTEQ; }
  ">="                           { return VondaGrammar.Lexer.GTEQ; }
  "!="                           { return VondaGrammar.Lexer.NOTEQ; }
  "&&"                           { return VondaGrammar.Lexer.ANDAND; }
  "||"                           { return VondaGrammar.Lexer.OROR; }
  "++"                           { return VondaGrammar.Lexer.PLUSPLUS; }
  "--"                           { return VondaGrammar.Lexer.MINUSMINUS; }

  /* NOTUSED
  "<<"                           { return VondaGrammar.Lexer.LSHIFT; }
  ">>"                           { return VondaGrammar.Lexer.RSHIFT; }
  ">>>"                          { return VondaGrammar.Lexer.URSHIFT; }
  "+="                           { return VondaGrammar.Lexer.PLUSEQ; }
  "-="                           { return VondaGrammar.Lexer.MINUSEQ; }
  "*="                           { return VondaGrammar.Lexer.MULTEQ; }
  "/="                           { return VondaGrammar.Lexer.DIVEQ; }
  "&="                           { return VondaGrammar.Lexer.ANDEQ; }
  "|="                           { return VondaGrammar.Lexer.OREQ; }
  "^="                           { return VondaGrammar.Lexer.XOREQ; }
  "%="                           { return VondaGrammar.Lexer.MODEQ; }
  "<<="                          { return VondaGrammar.Lexer.LSHIFTEQ; }
  ">>="                          { return VondaGrammar.Lexer.RSHIFTEQ; }
  ">>>="                         { return VondaGrammar.Lexer.URSHIFTEQ; }
  */

  /* string literal */
  \"                             { yybegin(STRING); string.setLength(0); }

  /* character literal */
  \'                             { yybegin(CHARLITERAL); }

  /* numeric literals */

  /* This is matched together with the minus, because the number is too big to
     be represented by a positive integer. */
  "-2147483648"                  { return symbol(INTEGER_LITERAL, new Integer(Integer.MIN_VALUE)); }

  {DecIntegerLiteral}            { return symbol(INTEGER_LITERAL, new Integer(yytext())); }
  {DecLongLiteral}               { return symbol(INTEGER_LITERAL, new Long(yytext().substring(0,yylength()-1))); }

  {HexIntegerLiteral}            { return symbol(INTEGER_LITERAL, new Integer((int) parseLong(2, yylength(), 16))); }
  {HexLongLiteral}               { return symbol(INTEGER_LITERAL, new Long(parseLong(2, yylength()-1, 16))); }

  {OctIntegerLiteral}            { return symbol(INTEGER_LITERAL, new Integer((int) parseLong(0, yylength(), 8))); }
  {OctLongLiteral}               { return symbol(INTEGER_LITERAL, new Long(parseLong(0, yylength()-1, 8))); }

  {FloatLiteral}                 { return symbol(FLOATING_POINT_LITERAL, new Float(yytext().substring(0,yylength()-1))); }
  {DoubleLiteral}                { return symbol(FLOATING_POINT_LITERAL, new Double(yytext())); }
  {DoubleLiteral}[dD]            { return symbol(FLOATING_POINT_LITERAL, new Double(yytext().substring(0,yylength()-1))); }

  /* comments */
  {Comment}                      { /* ignore */ }

  /* whitespace */
  {WhiteSpace}                   { /* ignore */ }

  /* identifiers */
  {Identifier}                   { return symbol(IDENTIFIER, yytext()); }
}

<STRING> {
  \"                             { yybegin(YYINITIAL); return symbol(STRING_LITERAL, string.toString()); }

  {StringCharacter}+             { string.append( yytext() ); }

  /* escape sequences */
  "\\b"                          { string.append( '\b' ); }
  "\\t"                          { string.append( '\t' ); }
  "\\n"                          { string.append( '\n' ); }
  "\\f"                          { string.append( '\f' ); }
  "\\r"                          { string.append( '\r' ); }
  "\\\""                         { string.append( '\"' ); }
  "\\'"                          { string.append( '\'' ); }
  "\\\\"                         { string.append( '\\' ); }
  \\[0-3]?{OctDigit}?{OctDigit}  { char val = (char) Integer.parseInt(yytext().substring(1),8);
                        				   string.append( val ); }

  /* error cases */
  \\.                            { throw new RuntimeException("Illegal escape sequence \""+yytext()+"\""); }
  {LineTerminator}               { throw new RuntimeException("Unterminated string at end of line"); }
}

<CHARLITERAL> {
  {SingleCharacter}\'            { yybegin(YYINITIAL); return symbol(CHARACTER_LITERAL, yytext().charAt(0)); }

  /* escape sequences */
  "\\b"\'                        { yybegin(YYINITIAL); return symbol(CHARACTER_LITERAL, '\b');}
  "\\t"\'                        { yybegin(YYINITIAL); return symbol(CHARACTER_LITERAL, '\t');}
  "\\n"\'                        { yybegin(YYINITIAL); return symbol(CHARACTER_LITERAL, '\n');}
  "\\f"\'                        { yybegin(YYINITIAL); return symbol(CHARACTER_LITERAL, '\f');}
  "\\r"\'                        { yybegin(YYINITIAL); return symbol(CHARACTER_LITERAL, '\r');}
  "\\\""\'                       { yybegin(YYINITIAL); return symbol(CHARACTER_LITERAL, '\"');}
  "\\'"\'                        { yybegin(YYINITIAL); return symbol(CHARACTER_LITERAL, '\'');}
  "\\\\"\'                       { yybegin(YYINITIAL); return symbol(CHARACTER_LITERAL, '\\'); }
  \\[0-3]?{OctDigit}?{OctDigit}\' { yybegin(YYINITIAL);
			                              int val = Integer.parseInt(yytext().substring(1,yylength()-1),8);
			                            return symbol(CHARACTER_LITERAL, (char)val); }

  /* error cases */
  \\.                            { throw new RuntimeException("Illegal escape sequence \""+yytext()+"\""); }
  {LineTerminator}               { throw new RuntimeException("Unterminated character literal at end of line"); }
}

/* error fallback */
[^]                              { throw new RuntimeException("Illegal character \""+yytext()+
                                                              "\" at line "+yyline+", column "+yycolumn); }
<<EOF>>                          { return VondaGrammar.Lexer.EOF; }