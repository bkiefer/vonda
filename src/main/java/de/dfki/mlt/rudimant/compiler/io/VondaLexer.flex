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

import static de.dfki.mlt.rudimant.common.ErrorInfo.ErrorType.*;

import java.util.*;

import de.dfki.mlt.rudimant.common.Location;
import de.dfki.mlt.rudimant.common.Position;
import de.dfki.mlt.rudimant.compiler.Mem;
import de.dfki.mlt.rudimant.compiler.Token;
import de.dfki.mlt.rudimant.compiler.tree.ExpSingleValue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
%%

%public
%class VondaLexer
%implements VondaGrammar.Lexer
%function yylex_internal

%unicode

%char
%line
%column

%byaccj


%{
  private static final Logger logger = LoggerFactory.getLogger(VondaLexer.class);

  private String origin;

  private Mem mem;

  private Object yylval;
  private StringBuffer string = new StringBuffer();

  private LinkedList<Token> commentTokens = new LinkedList<>();

  private LinkedList<Token> tokens = new LinkedList<>();

  private int charLiteral(String charval) {
    yylval = new ExpSingleValue(charval, "char");
    return token(VondaGrammar.Lexer.OTHER_LITERAL);
  }

  private int intLiteral(String intval) {
    yylval = new ExpSingleValue(intval, "int");
    return token(VondaGrammar.Lexer.INT);
  }

  private int floatLiteral(String floatval) {
    yylval = new ExpSingleValue(floatval, "float");
    return token(VondaGrammar.Lexer.OTHER_LITERAL);
  }

  private int booleanLiteral(String boolval) {
    yylval = new ExpSingleValue(boolval, "boolean");
    return token(VondaGrammar.Lexer.BOOL_LITERAL);
  }

  /**
   * Method to retrieve the beginning position of the last scanned token.
   * @return the position at which the last scanned token starts.
   */
  public Position getStartPos() {
    return new Position(yyline, yycolumn, yychar, origin);
  }

  /**
   * Method to retrieve the ending position of the last scanned token.
   * @return the first position beyond the last scanned token.
   */
  public Position getEndPos() {
    int len = yylength();
    return new Position(yyline, yycolumn + len, yychar + len, origin);
  }

  /**
   * Method to retrieve the semantic value of the last scanned token.
   * @return the semantic value of the last scanned token.
   */
  public Object getLVal() {
    Object result = yylval;
    yylval = null;
    return result;
  }

  /**
   * Entry point for the scanner.  Returns the token identifier corresponding
   * to the next token and prepares to return the semantic value
   * and beginning/ending positions of the token.
   *
   * This is a wrapper around the internal yylex method to collect tokens such
   * as comments, whitespace, etc. to use them later on in the compiler's
   * output. Also, other necessary functionality can be put her (extracting
   * the full input text?)
   *
   * @return the token identifier corresponding to the next token.
   */
  public int yylex() throws java.io.IOException {
    int result = yylex_internal();
    return result;
  }

  /**
   * Entry point for error reporting.  Emits an error
   * referring to the given location in a user-defined way.
   *
   * @param loc The location of the element to which the
   *                error message is related
   * @param msg The string for the error message.
   */
  public void yyerror (VondaGrammar.Location loc, String msg) {
    logger.error("{}: {}", loc, msg);
    mem.registerError(msg, new Location(loc.begin, loc.end), PARSE_ERROR);
  }

  public void setOrigin(String s) { origin = s; }

  public void setMem(Mem m) { mem = m; }

  /** Return the collected tokens */
  public LinkedList<Token> getTokens() { return tokens; }

  /** Return the collected comment tokens */
  public LinkedList<Token> getCommentTokens() { return commentTokens; }

  /** Add a non-comment and non-whitespace token */
  public int token(int token) {
    tokens.add(new Token(yytext(), getStartPos(), getEndPos()));
    return token;
  }

  /** Add a comment or whitespace token */
  public void addComment(String comment) {
    commentTokens.add(new Token(comment, getStartPos(), getEndPos()));
  }
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
  "break"                        { return token(VondaGrammar.Lexer.BREAK); }
  "cancel"                       { return token(VondaGrammar.Lexer.CANCEL); }
  "cancel_all"                   { return token(VondaGrammar.Lexer.CANCEL_ALL); }
  "case"                         { return token(VondaGrammar.Lexer.CASE); }
  "continue"                     { return token(VondaGrammar.Lexer.CONTINUE); }
  "default"                      { return token(VondaGrammar.Lexer.DEFAULT); }
  "do"                           { return token(VondaGrammar.Lexer.DO); }
  "else"                         { return token(VondaGrammar.Lexer.ELSE); }
  "final"                        { return token(VondaGrammar.Lexer.FINAL); }
  "for"                          { return token(VondaGrammar.Lexer.FOR); }
  "if"                           { return token(VondaGrammar.Lexer.IF); }
  "import"                       { return token(VondaGrammar.Lexer.IMPORT); }
  "new"                          { return token(VondaGrammar.Lexer.NEW); }
  "null"                         { return token(VondaGrammar.Lexer.NULL); }
  "private"                      { return token(VondaGrammar.Lexer.PRIVATE); }
  "propose"                      { return token(VondaGrammar.Lexer.PROPOSE); }
  "protected"                    { return token(VondaGrammar.Lexer.PROTECTED); }
  "public"                       { return token(VondaGrammar.Lexer.PUBLIC); }
  "return"                       { return token(VondaGrammar.Lexer.RETURN); }
  "switch"                       { return token(VondaGrammar.Lexer.SWITCH); }
  "timeout"                      { return token(VondaGrammar.Lexer.TIMEOUT); }
  "behaviour_timeout"            { return token(VondaGrammar.Lexer.TIMEOUT_BEHAVIOUR); }
  "while"                        { return token(VondaGrammar.Lexer.WHILE); }

  /* boolean literals */
  "true"                         |
  "false"                        { return booleanLiteral(yytext()); }

  /* null literal */
  "null"                         { return token(VondaGrammar.Lexer.NULL); }


  /* separators */
  "(" |
  ")" |
  "{" |
  "}" |
  "[" |
  "]" |
  ";" |
  "," |
  "."                            { return token((int)yycharat(0)); }

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
  "%"                            { return token((int)yycharat(0)); }

  "->"                           { return token(VondaGrammar.Lexer.ARROW); }
  "=="                           { return token(VondaGrammar.Lexer.EQEQ); }
  "<="                           { return token(VondaGrammar.Lexer.LTEQ); }
  ">="                           { return token(VondaGrammar.Lexer.GTEQ); }
  "!="                           { return token(VondaGrammar.Lexer.NOTEQ); }
  "&&"                           { return token(VondaGrammar.Lexer.ANDAND); }
  "||"                           { return token(VondaGrammar.Lexer.OROR); }
  "++"                           { return token(VondaGrammar.Lexer.PLUSPLUS); }
  "--"                           { return token(VondaGrammar.Lexer.MINUSMINUS); }

  "+="                           { return token(VondaGrammar.Lexer.PLUSEQ); }
  "-="                           { return token(VondaGrammar.Lexer.MINUSEQ); }

/* NOTUSED
  "<<"                           { return token(VondaGrammar.Lexer.LSHIFT); }
  ">>"                           { return token(VondaGrammar.Lexer.RSHIFT); }
  ">>>"                          { return token(VondaGrammar.Lexer.URSHIFT); }
  "*="                           { return token(VondaGrammar.Lexer.MULTEQ); }
  "/="                           { return token(VondaGrammar.Lexer.DIVEQ); }
  "&="                           { return token(VondaGrammar.Lexer.ANDEQ); }
  "|="                           { return token(VondaGrammar.Lexer.OREQ); }
  "^="                           { return token(VondaGrammar.Lexer.XOREQ); }
  "%="                           { return token(VondaGrammar.Lexer.MODEQ); }
  "<<="                          { return token(VondaGrammar.Lexer.LSHIFTEQ); }
  ">>="                          { return token(VondaGrammar.Lexer.RSHIFTEQ); }
  ">>>="                         { return token(VondaGrammar.Lexer.URSHIFTEQ); }
  */

  /* string literal */
  \"                             { yybegin(STRING); string.setLength(0); }

  /* character literal */
  \'                             { yybegin(CHARLITERAL); }

  /* numeric literals */

  /* This is matched together with the minus, because the number is too big to
     be represented by a positive integer. */
  "-2147483648"                  { return intLiteral(yytext()); }

  {DecIntegerLiteral}            { return intLiteral(yytext()); }
  {DecLongLiteral}               { return intLiteral(yytext()); }

  {HexIntegerLiteral}            { return intLiteral(yytext()); }
  {HexLongLiteral}               { return intLiteral(yytext()); }

  {OctIntegerLiteral}            { return intLiteral(yytext()); }
  {OctLongLiteral}               { return intLiteral(yytext()); }

  {FloatLiteral}                 { return floatLiteral(yytext()); }
  {DoubleLiteral}                { return floatLiteral(yytext()); }
  {DoubleLiteral}[dD]            { return floatLiteral(yytext()); }

  /* comments */
  {Comment}                      { addComment(yytext()); }

  /* whitespace */
  {WhiteSpace}                   { addComment(yytext()); }

  /* identifiers */
  {Identifier}                   {
  yylval = yytext();
  return token(VondaGrammar.Lexer.VARIABLE);
                                 }
}

<STRING> {
  \"                             {
  yybegin(YYINITIAL);
  yylval = new ExpSingleValue(string.toString(), "String");
  return token(VondaGrammar.Lexer.STRING);
                                 }

  {StringCharacter}+             |

  /* escape sequences */
  "\\b"                          |
  "\\t"                          |
  "\\n"                          |
  "\\f"                          |
  "\\r"                          |
  "\\\""                         |
  "\\'"                          |
  "\\\\"                         |
  \\[0-3]?{OctDigit}?{OctDigit}  { string.append( yytext() ); }

  /* error cases */
  \\.                            { throw new RuntimeException("Illegal escape sequence \""+yytext()+"\""); }
  {LineTerminator}               { throw new RuntimeException("Unterminated string at end of line"); }
}

<CHARLITERAL> {
  {SingleCharacter}\'            |

  /* escape sequences */
  "\\b"\'                        |
  "\\t"\'                        |
  "\\n"\'                        |
  "\\f"\'                        |
  "\\r"\'                        |
  "\\\""\'                       |
  "\\'"\'                        |
  "\\\\"\'                       |
  \\[0-3]?{OctDigit}?{OctDigit}\' { yybegin(YYINITIAL); return charLiteral(yytext()); }

  /* error cases */
  \\.                            { throw new RuntimeException("Illegal escape sequence \""+yytext()+"\""); }
  {LineTerminator}               { throw new RuntimeException("Unterminated character literal at end of line"); }
}

/* error fallback */
[^]                              { throw new RuntimeException("Illegal character \""+yytext()+
                                                              "\" at line "+yyline+", column "+yycolumn); }
<<EOF>>                          { return VondaGrammar.Lexer.EOF; }
