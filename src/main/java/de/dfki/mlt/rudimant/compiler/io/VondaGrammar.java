/* A Bison parser, made by GNU Bison 3.0.4.  */

/* Skeleton implementation for Bison LALR(1) parsers in Java

   Copyright (C) 2007-2015 Free Software Foundation, Inc.

   This program is free software: you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation, either version 3 of the License, or
   (at your option) any later version.

   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with this program.  If not, see <http://www.gnu.org/licenses/>.  */

/* As a special exception, you may create a larger work that contains
   part or all of the Bison parser skeleton and distribute that work
   under terms of your choice, so long as that work isn't itself a
   parser generator using the skeleton or a modified version thereof
   as a parser skeleton.  Alternatively, if you modify or redistribute
   the parser skeleton itself, you may (at your option) remove this
   special exception, which will cause the skeleton and the resulting
   Bison output files to be licensed under the GNU General Public
   License without this special exception.

   This special exception was added by the Free Software Foundation in
   version 2.2 of Bison.  */

package de.dfki.mlt.rudimant.compiler.io;
/* First part of user declarations.  */

/* "VondaGrammar.java":37  */ /* lalr1.java:91  */

/* "VondaGrammar.java":39  */ /* lalr1.java:92  */
/* "%code imports" blocks.  */
/* "VondaGrammar.y":3  */ /* lalr1.java:93  */


import java.io.Reader;
import java.util.*;
import de.dfki.mlt.rudimant.compiler.Type;
import de.dfki.mlt.rudimant.compiler.Position;
import de.dfki.mlt.rudimant.compiler.tree.*;

@SuppressWarnings({"serial", "unchecked", "fallthrough", "unused"})

/* "VondaGrammar.java":52  */ /* lalr1.java:93  */

/**
 * A Bison parser, automatically generated from <tt>VondaGrammar.y</tt>.
 *
 * @author LALR (1) parser skeleton written by Paolo Bonzini.
 */
public class VondaGrammar
{
    /** Version number for the Bison executable that generated this parser.  */
  public static final String bisonVersion = "3.0.4";

  /** Name of the skeleton that generated this parser.  */
  public static final String bisonSkeleton = "lalr1.java";


  /**
   * True if verbose error messages are enabled.
   */
  private boolean yyErrorVerbose = true;

  /**
   * Return whether verbose error messages are enabled.
   */
  public final boolean getErrorVerbose() { return yyErrorVerbose; }

  /**
   * Set the verbosity of error messages.
   * @param verbose True to request verbose error messages.
   */
  public final void setErrorVerbose(boolean verbose)
  { yyErrorVerbose = verbose; }



  /**
   * A class defining a pair of positions.  Positions, defined by the
   * <code>Position</code> class, denote a point in the input.
   * Locations represent a part of the input through the beginning
   * and ending positions.
   */
  public class Location {
    /**
     * The first, inclusive, position in the range.
     */
    public Position begin;

    /**
     * The first position beyond the range.
     */
    public Position end;

    /**
     * Create a <code>Location</code> denoting an empty range located at
     * a given point.
     * @param loc The position at which the range is anchored.
     */
    public Location (Position loc) {
      this.begin = this.end = loc;
    }

    /**
     * Create a <code>Location</code> from the endpoints of the range.
     * @param begin The first position included in the range.
     * @param end   The first position beyond the range.
     */
    public Location (Position begin, Position end) {
      this.begin = begin;
      this.end = end;
    }

    /**
     * Print a representation of the location.  For this to be correct,
     * <code>Position</code> should override the <code>equals</code>
     * method.
     */
    public String toString () {
      if (begin.equals (end))
        return begin.toString ();
      else
        return begin.toString () + "-" + end.toString ();
    }
  }



  
  private Location yylloc (YYStack rhs, int n)
  {
    if (n > 0)
      return new Location (rhs.locationAt (n-1).begin, rhs.locationAt (0).end);
    else
      return new Location (rhs.locationAt (0).end);
  }

  /**
   * Communication interface between the scanner and the Bison-generated
   * parser <tt>VondaGrammar</tt>.
   */
  public interface Lexer {
    /** Token returned by the scanner to signal the end of its input.  */
    public static final int EOF = 0;

/* Tokens.  */
    /** Token number,to be returned by the scanner.  */
    static final int BREAK = 258;
    /** Token number,to be returned by the scanner.  */
    static final int CANCEL = 259;
    /** Token number,to be returned by the scanner.  */
    static final int CANCEL_ALL = 260;
    /** Token number,to be returned by the scanner.  */
    static final int CASE = 261;
    /** Token number,to be returned by the scanner.  */
    static final int CONTINUE = 262;
    /** Token number,to be returned by the scanner.  */
    static final int DEFAULT = 263;
    /** Token number,to be returned by the scanner.  */
    static final int DO = 264;
    /** Token number,to be returned by the scanner.  */
    static final int ELSE = 265;
    /** Token number,to be returned by the scanner.  */
    static final int FINAL = 266;
    /** Token number,to be returned by the scanner.  */
    static final int FOR = 267;
    /** Token number,to be returned by the scanner.  */
    static final int IF = 268;
    /** Token number,to be returned by the scanner.  */
    static final int IMPORT = 269;
    /** Token number,to be returned by the scanner.  */
    static final int NEW = 270;
    /** Token number,to be returned by the scanner.  */
    static final int NULL = 271;
    /** Token number,to be returned by the scanner.  */
    static final int PRIVATE = 272;
    /** Token number,to be returned by the scanner.  */
    static final int PROPOSE = 273;
    /** Token number,to be returned by the scanner.  */
    static final int PROTECTED = 274;
    /** Token number,to be returned by the scanner.  */
    static final int PUBLIC = 275;
    /** Token number,to be returned by the scanner.  */
    static final int RETURN = 276;
    /** Token number,to be returned by the scanner.  */
    static final int SWITCH = 277;
    /** Token number,to be returned by the scanner.  */
    static final int TIMEOUT = 278;
    /** Token number,to be returned by the scanner.  */
    static final int TIMEOUT_BEHAVIOUR = 279;
    /** Token number,to be returned by the scanner.  */
    static final int WHILE = 280;
    /** Token number,to be returned by the scanner.  */
    static final int ARROW = 281;
    /** Token number,to be returned by the scanner.  */
    static final int ANDAND = 282;
    /** Token number,to be returned by the scanner.  */
    static final int OROR = 283;
    /** Token number,to be returned by the scanner.  */
    static final int EQEQ = 284;
    /** Token number,to be returned by the scanner.  */
    static final int NOTEQ = 285;
    /** Token number,to be returned by the scanner.  */
    static final int GTEQ = 286;
    /** Token number,to be returned by the scanner.  */
    static final int LTEQ = 287;
    /** Token number,to be returned by the scanner.  */
    static final int MINUSEQ = 288;
    /** Token number,to be returned by the scanner.  */
    static final int PLUSEQ = 289;
    /** Token number,to be returned by the scanner.  */
    static final int MINUSMINUS = 290;
    /** Token number,to be returned by the scanner.  */
    static final int PLUSPLUS = 291;
    /** Token number,to be returned by the scanner.  */
    static final int STRING = 292;
    /** Token number,to be returned by the scanner.  */
    static final int WILDCARD = 293;
    /** Token number,to be returned by the scanner.  */
    static final int INT = 294;
    /** Token number,to be returned by the scanner.  */
    static final int VARIABLE = 295;
    /** Token number,to be returned by the scanner.  */
    static final int OTHER_LITERAL = 296;


    /**
     * Method to retrieve the beginning position of the last scanned token.
     * @return the position at which the last scanned token starts.
     */
    Position getStartPos ();

    /**
     * Method to retrieve the ending position of the last scanned token.
     * @return the first position beyond the last scanned token.
     */
    Position getEndPos ();

    /**
     * Method to retrieve the semantic value of the last scanned token.
     * @return the semantic value of the last scanned token.
     */
    Object getLVal ();

    /**
     * Entry point for the scanner.  Returns the token identifier corresponding
     * to the next token and prepares to return the semantic value
     * and beginning/ending positions of the token.
     * @return the token identifier corresponding to the next token.
     */
    int yylex () throws java.io.IOException;

    /**
     * Entry point for error reporting.  Emits an error
     * referring to the given location in a user-defined way.
     *
     * @param loc The location of the element to which the
     *                error message is related
     * @param msg The string for the error message.
     */
     void yyerror (Location loc, String msg);
  }

  /**
   * The object doing lexical analysis for us.
   */
  private Lexer yylexer;
  
  



  /**
   * Instantiates the Bison-generated parser.
   * @param yylexer The scanner that will supply tokens to the parser.
   */
  public VondaGrammar (Lexer yylexer) 
  {
    
    this.yylexer = yylexer;
    
  }

  private java.io.PrintStream yyDebugStream = System.err;

  /**
   * Return the <tt>PrintStream</tt> on which the debugging output is
   * printed.
   */
  public final java.io.PrintStream getDebugStream () { return yyDebugStream; }

  /**
   * Set the <tt>PrintStream</tt> on which the debug output is printed.
   * @param s The stream that is used for debugging output.
   */
  public final void setDebugStream(java.io.PrintStream s) { yyDebugStream = s; }

  private int yydebug = 0;

  /**
   * Answer the verbosity of the debugging output; 0 means that all kinds of
   * output from the parser are suppressed.
   */
  public final int getDebugLevel() { return yydebug; }

  /**
   * Set the verbosity of the debugging output; 0 means that all kinds of
   * output from the parser are suppressed.
   * @param level The verbosity level for debugging output.
   */
  public final void setDebugLevel(int level) { yydebug = level; }

  /**
   * Print an error message via the lexer.
   * Use a <code>null</code> location.
   * @param msg The error message.
   */
  public final void yyerror (String msg)
  {
    yylexer.yyerror ((Location)null, msg);
  }

  /**
   * Print an error message via the lexer.
   * @param loc The location associated with the message.
   * @param msg The error message.
   */
  public final void yyerror (Location loc, String msg)
  {
    yylexer.yyerror (loc, msg);
  }

  /**
   * Print an error message via the lexer.
   * @param pos The position associated with the message.
   * @param msg The error message.
   */
  public final void yyerror (Position pos, String msg)
  {
    yylexer.yyerror (new Location (pos), msg);
  }

  protected final void yycdebug (String s) {
    if (yydebug > 0)
      yyDebugStream.println (s);
  }

  private final class YYStack {
    private int[] stateStack = new int[16];
    private Location[] locStack = new Location[16];
    private Object[] valueStack = new Object[16];

    public int size = 16;
    public int height = -1;

    public final void push (int state, Object value                            , Location loc) {
      height++;
      if (size == height)
        {
          int[] newStateStack = new int[size * 2];
          System.arraycopy (stateStack, 0, newStateStack, 0, height);
          stateStack = newStateStack;
          
          Location[] newLocStack = new Location[size * 2];
          System.arraycopy (locStack, 0, newLocStack, 0, height);
          locStack = newLocStack;

          Object[] newValueStack = new Object[size * 2];
          System.arraycopy (valueStack, 0, newValueStack, 0, height);
          valueStack = newValueStack;

          size *= 2;
        }

      stateStack[height] = state;
      locStack[height] = loc;
      valueStack[height] = value;
    }

    public final void pop () {
      pop (1);
    }

    public final void pop (int num) {
      // Avoid memory leaks... garbage collection is a white lie!
      if (num > 0) {
        java.util.Arrays.fill (valueStack, height - num + 1, height + 1, null);
        java.util.Arrays.fill (locStack, height - num + 1, height + 1, null);
      }
      height -= num;
    }

    public final int stateAt (int i) {
      return stateStack[height - i];
    }

    public final Location locationAt (int i) {
      return locStack[height - i];
    }

    public final Object valueAt (int i) {
      return valueStack[height - i];
    }

    // Print the state stack on the debug stream.
    public void print (java.io.PrintStream out)
    {
      out.print ("Stack now");

      for (int i = 0; i <= height; i++)
        {
          out.print (' ');
          out.print (stateStack[i]);
        }
      out.println ();
    }
  }

  /**
   * Returned by a Bison action in order to stop the parsing process and
   * return success (<tt>true</tt>).
   */
  public static final int YYACCEPT = 0;

  /**
   * Returned by a Bison action in order to stop the parsing process and
   * return failure (<tt>false</tt>).
   */
  public static final int YYABORT = 1;



  /**
   * Returned by a Bison action in order to start error recovery without
   * printing an error message.
   */
  public static final int YYERROR = 2;

  /**
   * Internal return codes that are not supported for user semantic
   * actions.
   */
  private static final int YYERRLAB = 3;
  private static final int YYNEWSTATE = 4;
  private static final int YYDEFAULT = 5;
  private static final int YYREDUCE = 6;
  private static final int YYERRLAB1 = 7;
  private static final int YYRETURN = 8;


  private int yyerrstatus_ = 0;


  /**
   * Return whether error recovery is being done.  In this state, the parser
   * reads token until it reaches a known state, and then restarts normal
   * operation.
   */
  public final boolean recovering ()
  {
    return yyerrstatus_ == 0;
  }

  /** Compute post-reduction state.
   * @param yystate   the current state
   * @param yysym     the nonterminal to push on the stack
   */
  private int yy_lr_goto_state_ (int yystate, int yysym)
  {
    int yyr = yypgoto_[yysym - yyntokens_] + yystate;
    if (0 <= yyr && yyr <= yylast_ && yycheck_[yyr] == yystate)
      return yytable_[yyr];
    else
      return yydefgoto_[yysym - yyntokens_];
  }

  private int yyaction (int yyn, YYStack yystack, int yylen) 
  {
    Object yyval;
    Location yyloc = yylloc (yystack, yylen);

    /* If YYLEN is nonzero, implement the default value of the action:
       '$$ = $1'.  Otherwise, use the top of the stack.

       Otherwise, the following line sets YYVAL to garbage.
       This behavior is undocumented and Bison
       users should not rely upon it.  */
    if (yylen > 0)
      yyval = yystack.valueAt (yylen - 1);
    else
      yyval = yystack.valueAt (0);

    yy_reduce_print (yyn, yystack);

    switch (yyn)
      {
          case 2:
  if (yyn == 2)
    /* "VondaGrammar.y":114  */ /* lalr1.java:489  */
    {
    yyval = ((LinkedList<RudiTree>)(yystack.valueAt (3-(3)))); ((StatMethodDeclaration)(yystack.valueAt (3-(2)))).setVisibility(((String)(yystack.valueAt (3-(1))))); ((LinkedList<RudiTree>)(yystack.valueAt (3-(3)))).addFirst(((StatMethodDeclaration)(yystack.valueAt (3-(2)))));
  };
  break;
    

  case 3:
  if (yyn == 3)
    /* "VondaGrammar.y":117  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))); ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))).addFirst(((StatMethodDeclaration)(yystack.valueAt (2-(1))))); };
  break;
    

  case 4:
  if (yyn == 4)
    /* "VondaGrammar.y":118  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))); ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))).addFirst(((RTStatement)(yystack.valueAt (2-(1))))); };
  break;
    

  case 5:
  if (yyn == 5)
    /* "VondaGrammar.y":120  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))); ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))).addFirst(((Import)(yystack.valueAt (2-(1))))); };
  break;
    

  case 6:
  if (yyn == 6)
    /* "VondaGrammar.y":121  */ /* lalr1.java:489  */
    {
    yyval = ((LinkedList<RudiTree>)(yystack.valueAt (3-(3)))); ((LinkedList<RudiTree>)(yystack.valueAt (3-(3)))).addFirst(new StatFieldDef(((String)(yystack.valueAt (3-(1)))), ((StatVarDef)(yystack.valueAt (3-(2))))));
  };
  break;
    

  case 7:
  if (yyn == 7)
    /* "VondaGrammar.y":124  */ /* lalr1.java:489  */
    {
    yyval = ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))); ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))).addFirst(new StatFieldDef(null, ((StatVarDef)(yystack.valueAt (2-(1))))));
  };
  break;
    

  case 8:
  if (yyn == 8)
    /* "VondaGrammar.y":126  */ /* lalr1.java:489  */
    { yyval = _statements;};
  break;
    

  case 9:
  if (yyn == 9)
    /* "VondaGrammar.y":130  */ /* lalr1.java:489  */
    { yyval = "public"; };
  break;
    

  case 10:
  if (yyn == 10)
    /* "VondaGrammar.y":131  */ /* lalr1.java:489  */
    { yyval = "protected"; };
  break;
    

  case 11:
  if (yyn == 11)
    /* "VondaGrammar.y":132  */ /* lalr1.java:489  */
    { yyval = "private"; };
  break;
    

  case 12:
  if (yyn == 12)
    /* "VondaGrammar.y":136  */ /* lalr1.java:489  */
    {
    new Import((( String )(yystack.valueAt (4-(2)))), ((List<String>)(yystack.valueAt (4-(3)))).toArray(new String[((List<String>)(yystack.valueAt (4-(3)))).size()]));
  };
  break;
    

  case 13:
  if (yyn == 13)
    /* "VondaGrammar.y":142  */ /* lalr1.java:489  */
    { yyval = new ArrayList<String>(); };
  break;
    

  case 14:
  if (yyn == 14)
    /* "VondaGrammar.y":143  */ /* lalr1.java:489  */
    { yyval = ((List<String>)(yystack.valueAt (3-(3)))); ((List<String>)(yystack.valueAt (3-(3)))).add((( String )(yystack.valueAt (3-(2))))); };
  break;
    

  case 15:
  if (yyn == 15)
    /* "VondaGrammar.y":147  */ /* lalr1.java:489  */
    { yyval = ((StatAbstractBlock)(yystack.valueAt (1-(1)))); };
  break;
    

  case 16:
  if (yyn == 16)
    /* "VondaGrammar.y":148  */ /* lalr1.java:489  */
    { yyval = new StatExpression(((RTExpression)(yystack.valueAt (2-(1))))); };
  break;
    

  case 17:
  if (yyn == 17)
    /* "VondaGrammar.y":149  */ /* lalr1.java:489  */
    { yyval = ((StatGrammarRule)(yystack.valueAt (1-(1)))); };
  break;
    

  case 18:
  if (yyn == 18)
    /* "VondaGrammar.y":150  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 19:
  if (yyn == 19)
    /* "VondaGrammar.y":151  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 20:
  if (yyn == 20)
    /* "VondaGrammar.y":152  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 21:
  if (yyn == 21)
    /* "VondaGrammar.y":153  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 22:
  if (yyn == 22)
    /* "VondaGrammar.y":154  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 23:
  if (yyn == 23)
    /* "VondaGrammar.y":155  */ /* lalr1.java:489  */
    { yyval = ((StatIf)(yystack.valueAt (1-(1)))); };
  break;
    

  case 24:
  if (yyn == 24)
    /* "VondaGrammar.y":156  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 25:
  if (yyn == 25)
    /* "VondaGrammar.y":157  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 26:
  if (yyn == 26)
    /* "VondaGrammar.y":158  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 27:
  if (yyn == 27)
    /* "VondaGrammar.y":162  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 28:
  if (yyn == 28)
    /* "VondaGrammar.y":163  */ /* lalr1.java:489  */
    { yyval = ((StatVarDef)(yystack.valueAt (1-(1)))); };
  break;
    

  case 29:
  if (yyn == 29)
    /* "VondaGrammar.y":169  */ /* lalr1.java:489  */
    { yyval = new StatAbstractBlock(((LinkedList<RTStatement>)(yystack.valueAt (3-(2)))), true); };
  break;
    

  case 30:
  if (yyn == 30)
    /* "VondaGrammar.y":170  */ /* lalr1.java:489  */
    { yyval = new StatAbstractBlock(new ArrayList<RTStatement>(), true); };
  break;
    

  case 31:
  if (yyn == 31)
    /* "VondaGrammar.y":172  */ /* lalr1.java:489  */
    { yyval = new LinkedList<RTStatement>(){{ add(((RTStatement)(yystack.valueAt (1-(1))))); }}; };
  break;
    

  case 32:
  if (yyn == 32)
    /* "VondaGrammar.y":173  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RTStatement>)(yystack.valueAt (2-(2)))); ((LinkedList<RTStatement>)(yystack.valueAt (2-(2)))).addFirst(((RTStatement)(yystack.valueAt (2-(1))))); };
  break;
    

  case 33:
  if (yyn == 33)
    /* "VondaGrammar.y":177  */ /* lalr1.java:489  */
    { yyval = new StatGrammarRule((( String )(yystack.valueAt (3-(1)))), ((StatIf)(yystack.valueAt (3-(3))))); };
  break;
    

  case 34:
  if (yyn == 34)
    /* "VondaGrammar.y":181  */ /* lalr1.java:489  */
    { yyval = new StatReturn("return"); };
  break;
    

  case 35:
  if (yyn == 35)
    /* "VondaGrammar.y":182  */ /* lalr1.java:489  */
    { yyval = new StatReturn(((RTExpression)(yystack.valueAt (3-(2))))); };
  break;
    

  case 36:
  if (yyn == 36)
    /* "VondaGrammar.y":183  */ /* lalr1.java:489  */
    { yyval = new StatReturn("break"); };
  break;
    

  case 37:
  if (yyn == 37)
    /* "VondaGrammar.y":184  */ /* lalr1.java:489  */
    { yyval = new StatReturn("break", (( String )(yystack.valueAt (3-(2))))); };
  break;
    

  case 38:
  if (yyn == 38)
    /* "VondaGrammar.y":185  */ /* lalr1.java:489  */
    { yyval = new StatReturn("cancel"); };
  break;
    

  case 39:
  if (yyn == 39)
    /* "VondaGrammar.y":186  */ /* lalr1.java:489  */
    { yyval = new StatReturn("cancel_all"); };
  break;
    

  case 40:
  if (yyn == 40)
    /* "VondaGrammar.y":187  */ /* lalr1.java:489  */
    { yyval = new StatReturn("continue"); };
  break;
    

  case 41:
  if (yyn == 41)
    /* "VondaGrammar.y":191  */ /* lalr1.java:489  */
    { yyval = new StatIf(((RTExpression)(yystack.valueAt (5-(3)))), ((RTStatement)(yystack.valueAt (5-(5)))), null); };
  break;
    

  case 42:
  if (yyn == 42)
    /* "VondaGrammar.y":192  */ /* lalr1.java:489  */
    {
    yyval = new StatIf(((RTExpression)(yystack.valueAt (7-(3)))), ((RTStatement)(yystack.valueAt (7-(5)))), ((RTStatement)(yystack.valueAt (7-(7)))));
  };
  break;
    

  case 43:
  if (yyn == 43)
    /* "VondaGrammar.y":198  */ /* lalr1.java:489  */
    { yyval = new StatWhile(((RTExpression)(yystack.valueAt (5-(3)))), ((RTStatement)(yystack.valueAt (5-(5)))), true); };
  break;
    

  case 44:
  if (yyn == 44)
    /* "VondaGrammar.y":199  */ /* lalr1.java:489  */
    {
    yyval = new StatWhile(((RTExpression)(yystack.valueAt (6-(5)))), ((RTStatement)(yystack.valueAt (6-(2)))), false);
  };
  break;
    

  case 45:
  if (yyn == 45)
    /* "VondaGrammar.y":205  */ /* lalr1.java:489  */
    {
    yyval = new StatFor1(((StatVarDef)(yystack.valueAt (8-(3)))), ((RTExpression)(yystack.valueAt (8-(4)))), ((RTExpression)(yystack.valueAt (8-(6)))), ((RTStatement)(yystack.valueAt (8-(8))))); };
  break;
    

  case 46:
  if (yyn == 46)
    /* "VondaGrammar.y":207  */ /* lalr1.java:489  */
    {
    yyval = new StatFor1(((StatVarDef)(yystack.valueAt (7-(3)))), null, ((RTExpression)(yystack.valueAt (7-(5)))), ((RTStatement)(yystack.valueAt (7-(7))))); };
  break;
    

  case 47:
  if (yyn == 47)
    /* "VondaGrammar.y":209  */ /* lalr1.java:489  */
    {
    yyval = new StatFor1(((StatVarDef)(yystack.valueAt (7-(3)))), ((RTExpression)(yystack.valueAt (7-(4)))), null, ((RTStatement)(yystack.valueAt (7-(7))))); };
  break;
    

  case 48:
  if (yyn == 48)
    /* "VondaGrammar.y":211  */ /* lalr1.java:489  */
    {
    yyval = new StatFor1(((StatVarDef)(yystack.valueAt (6-(3)))), null, null, ((RTStatement)(yystack.valueAt (6-(6))))); };
  break;
    

  case 49:
  if (yyn == 49)
    /* "VondaGrammar.y":213  */ /* lalr1.java:489  */
    {
    yyval = new StatFor1(null, ((RTExpression)(yystack.valueAt (8-(4)))), ((RTExpression)(yystack.valueAt (8-(6)))), ((RTStatement)(yystack.valueAt (8-(8))))); };
  break;
    

  case 50:
  if (yyn == 50)
    /* "VondaGrammar.y":215  */ /* lalr1.java:489  */
    {
    yyval = new StatFor2(new ExpVariable((( String )(yystack.valueAt (7-(3))))), ((RTExpression)(yystack.valueAt (7-(5)))), ((RTStatement)(yystack.valueAt (7-(7)))));
  };
  break;
    

  case 51:
  if (yyn == 51)
    /* "VondaGrammar.y":218  */ /* lalr1.java:489  */
    {
    yyval = new StatFor2(((Type)(yystack.valueAt (8-(3)))), new ExpVariable((( String )(yystack.valueAt (8-(4))))), ((RTExpression)(yystack.valueAt (8-(6)))), ((RTStatement)(yystack.valueAt (8-(8)))));
  };
  break;
    

  case 52:
  if (yyn == 52)
    /* "VondaGrammar.y":226  */ /* lalr1.java:489  */
    { yyval = new StatPropose(((RTExpression)(yystack.valueAt (5-(3)))), ((StatAbstractBlock)(yystack.valueAt (5-(5))))); };
  break;
    

  case 53:
  if (yyn == 53)
    /* "VondaGrammar.y":230  */ /* lalr1.java:489  */
    {
    yyval = new StatTimeout(null, ((RTExpression)(yystack.valueAt (5-(3)))), ((StatAbstractBlock)(yystack.valueAt (5-(5)))));
  };
  break;
    

  case 54:
  if (yyn == 54)
    /* "VondaGrammar.y":236  */ /* lalr1.java:489  */
    {
    yyval = new StatTimeout(((RTExpression)(yystack.valueAt (7-(3)))), ((RTExpression)(yystack.valueAt (7-(5)))), ((StatAbstractBlock)(yystack.valueAt (7-(7)))));
  };
  break;
    

  case 55:
  if (yyn == 55)
    /* "VondaGrammar.y":242  */ /* lalr1.java:489  */
    {
    yyval = new StatSwitch(((RTExpression)(yystack.valueAt (7-(3)))), ((StatAbstractBlock)(yystack.valueAt (7-(6)))));
  };
  break;
    

  case 56:
  if (yyn == 56)
    /* "VondaGrammar.y":248  */ /* lalr1.java:489  */
    {
    List<RTStatement> elts = ((LinkedList<RTStatement>)(yystack.valueAt (2-(1))));
    elts.addAll(((LinkedList<RTStatement>)(yystack.valueAt (2-(2)))));
    yyval = new StatAbstractBlock(elts, false);
  };
  break;
    

  case 57:
  if (yyn == 57)
    /* "VondaGrammar.y":256  */ /* lalr1.java:489  */
    { };
  break;
    

  case 58:
  if (yyn == 58)
    /* "VondaGrammar.y":257  */ /* lalr1.java:489  */
    { yyval = new ArrayList<RTExpression>(); };
  break;
    

  case 59:
  if (yyn == 59)
    /* "VondaGrammar.y":261  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RTStatement>)(yystack.valueAt (2-(2)))); ((LinkedList<RTStatement>)(yystack.valueAt (2-(2)))).addFirst(((RTStatement)(yystack.valueAt (2-(1))))); };
  break;
    

  case 60:
  if (yyn == 60)
    /* "VondaGrammar.y":262  */ /* lalr1.java:489  */
    { yyval = new LinkedList<RTStatement>(){{ add(((RTStatement)(yystack.valueAt (1-(1))))); }}; };
  break;
    

  case 61:
  if (yyn == 61)
    /* "VondaGrammar.y":266  */ /* lalr1.java:489  */
    {
    yyval = new ExpSingleValue("case " + (( ExpSingleValue )(yystack.valueAt (3-(2)))) + ":", "label").ensureStatement();
  };
  break;
    

  case 62:
  if (yyn == 62)
    /* "VondaGrammar.y":269  */ /* lalr1.java:489  */
    {
    yyval = new ExpSingleValue("case " + (( ExpSingleValue )(yystack.valueAt (3-(2)))) + ":", "label").ensureStatement();
  };
  break;
    

  case 63:
  if (yyn == 63)
    /* "VondaGrammar.y":272  */ /* lalr1.java:489  */
    {
    yyval = new ExpSingleValue("case " + (( String )(yystack.valueAt (3-(2)))) + ":", "label").ensureStatement();
  };
  break;
    

  case 64:
  if (yyn == 64)
    /* "VondaGrammar.y":275  */ /* lalr1.java:489  */
    {
    yyval = new ExpSingleValue("default:", "label").ensureStatement();
  };
  break;
    

  case 65:
  if (yyn == 65)
    /* "VondaGrammar.y":281  */ /* lalr1.java:489  */
    {
    yyval = new StatVarDef(true, null, (( String )(yystack.valueAt (4-(2)))), ((RTExpression)(yystack.valueAt (4-(3)))));
  };
  break;
    

  case 66:
  if (yyn == 66)
    /* "VondaGrammar.y":284  */ /* lalr1.java:489  */
    {
    yyval = new StatVarDef(false, ((Type)(yystack.valueAt (4-(1)))), (( String )(yystack.valueAt (4-(2)))), ((RTExpression)(yystack.valueAt (4-(3)))));
  };
  break;
    

  case 67:
  if (yyn == 67)
    /* "VondaGrammar.y":287  */ /* lalr1.java:489  */
    {
    yyval = new StatVarDef(true, ((Type)(yystack.valueAt (5-(2)))), (( String )(yystack.valueAt (5-(3)))), ((RTExpression)(yystack.valueAt (5-(4)))));
  };
  break;
    

  case 68:
  if (yyn == 68)
    /* "VondaGrammar.y":290  */ /* lalr1.java:489  */
    {
    yyval = new StatVarDef(true, null, (( String )(yystack.valueAt (3-(2)))), null);
  };
  break;
    

  case 69:
  if (yyn == 69)
    /* "VondaGrammar.y":293  */ /* lalr1.java:489  */
    {
    yyval = new StatVarDef(false, ((Type)(yystack.valueAt (3-(1)))), (( String )(yystack.valueAt (3-(2)))), null);
  };
  break;
    

  case 70:
  if (yyn == 70)
    /* "VondaGrammar.y":296  */ /* lalr1.java:489  */
    {
    yyval = new StatVarDef(true, ((Type)(yystack.valueAt (4-(2)))), (( String )(yystack.valueAt (4-(3)))), null);
  };
  break;
    

  case 71:
  if (yyn == 71)
    /* "VondaGrammar.y":302  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (2-(2)))); };
  break;
    

  case 72:
  if (yyn == 72)
    /* "VondaGrammar.y":303  */ /* lalr1.java:489  */
    { yyval = new ExpListLiteral(new LinkedList<RTExpression>()); };
  break;
    

  case 73:
  if (yyn == 73)
    /* "VondaGrammar.y":304  */ /* lalr1.java:489  */
    { yyval = new ExpListLiteral(((LinkedList<RTExpression>)(yystack.valueAt (4-(3))))); };
  break;
    

  case 74:
  if (yyn == 74)
    /* "VondaGrammar.y":308  */ /* lalr1.java:489  */
    { yyval = new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (1-(1))))); }}; };
  break;
    

  case 75:
  if (yyn == 75)
    /* "VondaGrammar.y":309  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))); ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))).addFirst(((RTExpression)(yystack.valueAt (3-(1))))); };
  break;
    

  case 76:
  if (yyn == 76)
    /* "VondaGrammar.y":313  */ /* lalr1.java:489  */
    {
    yyval = new StatMethodDeclaration("public", ((Type)(yystack.valueAt (6-(2)))), ((Type)(yystack.valueAt (6-(1)))), (( String )(yystack.valueAt (6-(3)))), null, ((StatAbstractBlock)(yystack.valueAt (6-(6)))));
  };
  break;
    

  case 77:
  if (yyn == 77)
    /* "VondaGrammar.y":316  */ /* lalr1.java:489  */
    {
    yyval = new StatMethodDeclaration("public", ((Type)(yystack.valueAt (6-(2)))), ((Type)(yystack.valueAt (6-(1)))), (( String )(yystack.valueAt (6-(3)))), ((LinkedList)(yystack.valueAt (6-(5)))), ((StatAbstractBlock)(yystack.valueAt (6-(6)))));
  };
  break;
    

  case 78:
  if (yyn == 78)
    /* "VondaGrammar.y":319  */ /* lalr1.java:489  */
    {
    yyval = new StatMethodDeclaration("public", null, ((Type)(yystack.valueAt (5-(1)))), (( String )(yystack.valueAt (5-(2)))), null, ((StatAbstractBlock)(yystack.valueAt (5-(5)))));
  };
  break;
    

  case 79:
  if (yyn == 79)
    /* "VondaGrammar.y":322  */ /* lalr1.java:489  */
    {
    yyval = new StatMethodDeclaration("public", null, ((Type)(yystack.valueAt (5-(1)))), (( String )(yystack.valueAt (5-(2)))), ((LinkedList)(yystack.valueAt (5-(4)))), ((StatAbstractBlock)(yystack.valueAt (5-(5)))));
  };
  break;
    

  case 80:
  if (yyn == 80)
    /* "VondaGrammar.y":325  */ /* lalr1.java:489  */
    {
    yyval = new StatMethodDeclaration("public", ((Type)(yystack.valueAt (5-(1)))), null, (( String )(yystack.valueAt (5-(2)))), null, ((StatAbstractBlock)(yystack.valueAt (5-(5)))));
  };
  break;
    

  case 81:
  if (yyn == 81)
    /* "VondaGrammar.y":328  */ /* lalr1.java:489  */
    {
    yyval = new StatMethodDeclaration("public", ((Type)(yystack.valueAt (5-(1)))), null, (( String )(yystack.valueAt (5-(2)))), ((LinkedList)(yystack.valueAt (5-(4)))), ((StatAbstractBlock)(yystack.valueAt (5-(5)))));
  };
  break;
    

  case 82:
  if (yyn == 82)
    /* "VondaGrammar.y":331  */ /* lalr1.java:489  */
    {
    yyval = new StatMethodDeclaration("public", null, null, (( String )(yystack.valueAt (4-(1)))), null, ((StatAbstractBlock)(yystack.valueAt (4-(4)))));
  };
  break;
    

  case 83:
  if (yyn == 83)
    /* "VondaGrammar.y":334  */ /* lalr1.java:489  */
    {
    yyval = new StatMethodDeclaration("public", null, null, (( String )(yystack.valueAt (4-(1)))), ((LinkedList)(yystack.valueAt (4-(3)))), ((StatAbstractBlock)(yystack.valueAt (4-(4)))));
  };
  break;
    

  case 84:
  if (yyn == 84)
    /* "VondaGrammar.y":339  */ /* lalr1.java:489  */
    { yyval = ((Type)(yystack.valueAt (4-(2)))); };
  break;
    

  case 85:
  if (yyn == 85)
    /* "VondaGrammar.y":343  */ /* lalr1.java:489  */
    { yyval = ((StatAbstractBlock)(yystack.valueAt (1-(1)))); };
  break;
    

  case 86:
  if (yyn == 86)
    /* "VondaGrammar.y":344  */ /* lalr1.java:489  */
    { yyval = null; };
  break;
    

  case 87:
  if (yyn == 87)
    /* "VondaGrammar.y":348  */ /* lalr1.java:489  */
    { yyval = new LinkedList(){{ add((( String )(yystack.valueAt (2-(1))))); }}; };
  break;
    

  case 88:
  if (yyn == 88)
    /* "VondaGrammar.y":349  */ /* lalr1.java:489  */
    { yyval = new LinkedList(){{ add(((Type)(yystack.valueAt (3-(1))))); add((( String )(yystack.valueAt (3-(2))))); }}; };
  break;
    

  case 89:
  if (yyn == 89)
    /* "VondaGrammar.y":350  */ /* lalr1.java:489  */
    { yyval = ((LinkedList)(yystack.valueAt (3-(3)))); ((LinkedList)(yystack.valueAt (3-(3)))).addFirst((( String )(yystack.valueAt (3-(1))))); };
  break;
    

  case 90:
  if (yyn == 90)
    /* "VondaGrammar.y":351  */ /* lalr1.java:489  */
    {
    yyval = ((LinkedList)(yystack.valueAt (4-(4)))); ((LinkedList)(yystack.valueAt (4-(4)))).addFirst((( String )(yystack.valueAt (4-(2))))); ((LinkedList)(yystack.valueAt (4-(4)))).addFirst(((Type)(yystack.valueAt (4-(1)))));
  };
  break;
    

  case 91:
  if (yyn == 91)
    /* "VondaGrammar.y":359  */ /* lalr1.java:489  */
    {
    yyval = new StatSetOperation(new ExpVariable((( String )(yystack.valueAt (4-(1))))), true, ((RTExpression)(yystack.valueAt (4-(3)))));
  };
  break;
    

  case 92:
  if (yyn == 92)
    /* "VondaGrammar.y":362  */ /* lalr1.java:489  */
    {
    yyval = new StatSetOperation(new ExpVariable((( String )(yystack.valueAt (4-(1))))), false, ((RTExpression)(yystack.valueAt (4-(3)))));
  };
  break;
    

  case 93:
  if (yyn == 93)
    /* "VondaGrammar.y":365  */ /* lalr1.java:489  */
    { yyval = new StatSetOperation(((RTExpression)(yystack.valueAt (4-(1)))), true, ((RTExpression)(yystack.valueAt (4-(3))))); };
  break;
    

  case 94:
  if (yyn == 94)
    /* "VondaGrammar.y":366  */ /* lalr1.java:489  */
    { yyval = new StatSetOperation(((RTExpression)(yystack.valueAt (4-(1)))), false, ((RTExpression)(yystack.valueAt (4-(3))))); };
  break;
    

  case 95:
  if (yyn == 95)
    /* "VondaGrammar.y":367  */ /* lalr1.java:489  */
    { yyval = new StatSetOperation(((RTExpression)(yystack.valueAt (4-(1)))), true, ((RTExpression)(yystack.valueAt (4-(3))))); };
  break;
    

  case 96:
  if (yyn == 96)
    /* "VondaGrammar.y":368  */ /* lalr1.java:489  */
    { yyval = new StatSetOperation(((RTExpression)(yystack.valueAt (4-(1)))), false, ((RTExpression)(yystack.valueAt (4-(3))))); };
  break;
    

  case 97:
  if (yyn == 97)
    /* "VondaGrammar.y":375  */ /* lalr1.java:489  */
    {
    yyval = new ExpFuncCall((( String )(yystack.valueAt (3-(1)))), new LinkedList<RTExpression>(), false);
  };
  break;
    

  case 98:
  if (yyn == 98)
    /* "VondaGrammar.y":378  */ /* lalr1.java:489  */
    {
    yyval = new ExpFuncCall((( String )(yystack.valueAt (4-(1)))), ((LinkedList<RTExpression>)(yystack.valueAt (4-(3)))), false);
  };
  break;
    

  case 99:
  if (yyn == 99)
    /* "VondaGrammar.y":384  */ /* lalr1.java:489  */
    { yyval = new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (1-(1))))); }}; };
  break;
    

  case 100:
  if (yyn == 100)
    /* "VondaGrammar.y":385  */ /* lalr1.java:489  */
    { yyval = new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (1-(1))))); }}; };
  break;
    

  case 101:
  if (yyn == 101)
    /* "VondaGrammar.y":386  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))); ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))).addFirst(((RTExpression)(yystack.valueAt (3-(1))))); };
  break;
    

  case 102:
  if (yyn == 102)
    /* "VondaGrammar.y":387  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))); ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))).addFirst(((RTExpression)(yystack.valueAt (3-(1))))); };
  break;
    

  case 103:
  if (yyn == 103)
    /* "VondaGrammar.y":391  */ /* lalr1.java:489  */
    { yyval = new Type("Array", new Type((( String )(yystack.valueAt (3-(1)))))); };
  break;
    

  case 104:
  if (yyn == 104)
    /* "VondaGrammar.y":392  */ /* lalr1.java:489  */
    { yyval = new Type((( String )(yystack.valueAt (1-(1))))); };
  break;
    

  case 105:
  if (yyn == 105)
    /* "VondaGrammar.y":393  */ /* lalr1.java:489  */
    { yyval = ((Type)(yystack.valueAt (1-(1)))); };
  break;
    

  case 106:
  if (yyn == 106)
    /* "VondaGrammar.y":397  */ /* lalr1.java:489  */
    { yyval = new Type((( String )(yystack.valueAt (4-(1)))), ((LinkedList<Type>)(yystack.valueAt (4-(3)))).toArray(new Type[((LinkedList<Type>)(yystack.valueAt (4-(3)))).size()])); };
  break;
    

  case 107:
  if (yyn == 107)
    /* "VondaGrammar.y":401  */ /* lalr1.java:489  */
    { yyval = new LinkedList<Type>(){{ add(((Type)(yystack.valueAt (1-(1))))); }}; };
  break;
    

  case 108:
  if (yyn == 108)
    /* "VondaGrammar.y":402  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<Type>)(yystack.valueAt (3-(3)))); ((LinkedList<Type>)(yystack.valueAt (3-(3)))).addFirst(((Type)(yystack.valueAt (3-(1))))); };
  break;
    

  case 109:
  if (yyn == 109)
    /* "VondaGrammar.y":406  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 110:
  if (yyn == 110)
    /* "VondaGrammar.y":407  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 111:
  if (yyn == 111)
    /* "VondaGrammar.y":408  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 112:
  if (yyn == 112)
    /* "VondaGrammar.y":409  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 113:
  if (yyn == 113)
    /* "VondaGrammar.y":413  */ /* lalr1.java:489  */
    {
    yyval = new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "||");
  };
  break;
    

  case 114:
  if (yyn == 114)
    /* "VondaGrammar.y":416  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 115:
  if (yyn == 115)
    /* "VondaGrammar.y":420  */ /* lalr1.java:489  */
    {
    yyval = new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "&&");
  };
  break;
    

  case 116:
  if (yyn == 116)
    /* "VondaGrammar.y":423  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 117:
  if (yyn == 117)
    /* "VondaGrammar.y":427  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 118:
  if (yyn == 118)
    /* "VondaGrammar.y":428  */ /* lalr1.java:489  */
    {
    yyval = new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "|");
  };
  break;
    

  case 119:
  if (yyn == 119)
    /* "VondaGrammar.y":434  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 120:
  if (yyn == 120)
    /* "VondaGrammar.y":435  */ /* lalr1.java:489  */
    {
    yyval = new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "^");
  };
  break;
    

  case 121:
  if (yyn == 121)
    /* "VondaGrammar.y":441  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 122:
  if (yyn == 122)
    /* "VondaGrammar.y":442  */ /* lalr1.java:489  */
    {
    yyval = new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "&");
  };
  break;
    

  case 123:
  if (yyn == 123)
    /* "VondaGrammar.y":448  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 124:
  if (yyn == 124)
    /* "VondaGrammar.y":449  */ /* lalr1.java:489  */
    {
    yyval = new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "==");
  };
  break;
    

  case 125:
  if (yyn == 125)
    /* "VondaGrammar.y":452  */ /* lalr1.java:489  */
    {
    yyval = new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "!=");
  };
  break;
    

  case 126:
  if (yyn == 126)
    /* "VondaGrammar.y":458  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 127:
  if (yyn == 127)
    /* "VondaGrammar.y":459  */ /* lalr1.java:489  */
    {
    yyval = new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "<");
  };
  break;
    

  case 128:
  if (yyn == 128)
    /* "VondaGrammar.y":462  */ /* lalr1.java:489  */
    {
    yyval = new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), ">");
  };
  break;
    

  case 129:
  if (yyn == 129)
    /* "VondaGrammar.y":465  */ /* lalr1.java:489  */
    {
    yyval = new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), ">=");
  };
  break;
    

  case 130:
  if (yyn == 130)
    /* "VondaGrammar.y":468  */ /* lalr1.java:489  */
    {
    yyval = new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "<=");
  };
  break;
    

  case 131:
  if (yyn == 131)
    /* "VondaGrammar.y":474  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 132:
  if (yyn == 132)
    /* "VondaGrammar.y":475  */ /* lalr1.java:489  */
    {
    yyval = new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "+");
  };
  break;
    

  case 133:
  if (yyn == 133)
    /* "VondaGrammar.y":478  */ /* lalr1.java:489  */
    {
    yyval = new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "-");
  };
  break;
    

  case 134:
  if (yyn == 134)
    /* "VondaGrammar.y":484  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 135:
  if (yyn == 135)
    /* "VondaGrammar.y":485  */ /* lalr1.java:489  */
    {
    yyval = new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "*");
  };
  break;
    

  case 136:
  if (yyn == 136)
    /* "VondaGrammar.y":488  */ /* lalr1.java:489  */
    {
    yyval = new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "/");
  };
  break;
    

  case 137:
  if (yyn == 137)
    /* "VondaGrammar.y":491  */ /* lalr1.java:489  */
    {
    yyval = new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "%");
  };
  break;
    

  case 138:
  if (yyn == 138)
    /* "VondaGrammar.y":497  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 139:
  if (yyn == 139)
    /* "VondaGrammar.y":498  */ /* lalr1.java:489  */
    { new ExpCast(((Type)(yystack.valueAt (4-(2)))), ((RTExpression)(yystack.valueAt (4-(4))))); };
  break;
    

  case 140:
  if (yyn == 140)
    /* "VondaGrammar.y":503  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 141:
  if (yyn == 141)
    /* "VondaGrammar.y":504  */ /* lalr1.java:489  */
    { yyval = new ExpBoolean(((RTExpression)(yystack.valueAt (2-(2)))), null, "!"); };
  break;
    

  case 142:
  if (yyn == 142)
    /* "VondaGrammar.y":505  */ /* lalr1.java:489  */
    { yyval = new ExpArithmetic(((RTExpression)(yystack.valueAt (2-(2)))), null, "~"); };
  break;
    

  case 143:
  if (yyn == 143)
    /* "VondaGrammar.y":509  */ /* lalr1.java:489  */
    {
    yyval = new ExpArithmetic(((RTExpression)(yystack.valueAt (2-(2)))), new ExpSingleValue("1", "int"), "+");
  };
  break;
    

  case 144:
  if (yyn == 144)
    /* "VondaGrammar.y":512  */ /* lalr1.java:489  */
    {
    yyval = new ExpArithmetic(((RTExpression)(yystack.valueAt (2-(2)))), new ExpSingleValue("1", "int"), "-");
  };
  break;
    

  case 145:
  if (yyn == 145)
    /* "VondaGrammar.y":515  */ /* lalr1.java:489  */
    { yyval = new ExpArithmetic(((RTExpression)(yystack.valueAt (2-(2)))), null, "+"); };
  break;
    

  case 146:
  if (yyn == 146)
    /* "VondaGrammar.y":516  */ /* lalr1.java:489  */
    { yyval = new ExpArithmetic(((RTExpression)(yystack.valueAt (2-(2)))), null, "-"); };
  break;
    

  case 147:
  if (yyn == 147)
    /* "VondaGrammar.y":517  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 148:
  if (yyn == 148)
    /* "VondaGrammar.y":521  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 149:
  if (yyn == 149)
    /* "VondaGrammar.y":527  */ /* lalr1.java:489  */
    { yyval = new ExpSingleValue("null", "null"); };
  break;
    

  case 150:
  if (yyn == 150)
    /* "VondaGrammar.y":528  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 151:
  if (yyn == 151)
    /* "VondaGrammar.y":529  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 152:
  if (yyn == 152)
    /* "VondaGrammar.y":533  */ /* lalr1.java:489  */
    { yyval = new ExpVariable((( String )(yystack.valueAt (1-(1))))); };
  break;
    

  case 153:
  if (yyn == 153)
    /* "VondaGrammar.y":534  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 154:
  if (yyn == 154)
    /* "VondaGrammar.y":538  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (3-(2)))); };
  break;
    

  case 155:
  if (yyn == 155)
    /* "VondaGrammar.y":539  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 156:
  if (yyn == 156)
    /* "VondaGrammar.y":543  */ /* lalr1.java:489  */
    { yyval = ((ExpSingleValue)(yystack.valueAt (1-(1)))); };
  break;
    

  case 157:
  if (yyn == 157)
    /* "VondaGrammar.y":544  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 158:
  if (yyn == 158)
    /* "VondaGrammar.y":545  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 159:
  if (yyn == 159)
    /* "VondaGrammar.y":546  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 160:
  if (yyn == 160)
    /* "VondaGrammar.y":550  */ /* lalr1.java:489  */
    { yyval = (( ExpSingleValue )(yystack.valueAt (1-(1)))); };
  break;
    

  case 161:
  if (yyn == 161)
    /* "VondaGrammar.y":551  */ /* lalr1.java:489  */
    { yyval = (( ExpSingleValue )(yystack.valueAt (1-(1)))); };
  break;
    

  case 162:
  if (yyn == 162)
    /* "VondaGrammar.y":552  */ /* lalr1.java:489  */
    { yyval = (( ExpSingleValue )(yystack.valueAt (1-(1)))); };
  break;
    

  case 163:
  if (yyn == 163)
    /* "VondaGrammar.y":556  */ /* lalr1.java:489  */
    { yyval = new ExpArrayAccess(new ExpVariable((( String )(yystack.valueAt (4-(1))))), ((RTExpression)(yystack.valueAt (4-(3))))); };
  break;
    

  case 164:
  if (yyn == 164)
    /* "VondaGrammar.y":557  */ /* lalr1.java:489  */
    { yyval = new ExpArrayAccess(((RTExpression)(yystack.valueAt (4-(1)))), ((RTExpression)(yystack.valueAt (4-(3))))); };
  break;
    

  case 165:
  if (yyn == 165)
    /* "VondaGrammar.y":561  */ /* lalr1.java:489  */
    { yyval = new ExpConditional(((RTExpression)(yystack.valueAt (5-(1)))), ((RTExpression)(yystack.valueAt (5-(3)))), ((RTExpression)(yystack.valueAt (5-(5))))); };
  break;
    

  case 166:
  if (yyn == 166)
    /* "VondaGrammar.y":589  */ /* lalr1.java:489  */
    { yyval = new ExpAssignment(new ExpVariable((( String )(yystack.valueAt (2-(1))))), ((RTExpression)(yystack.valueAt (2-(2))))); };
  break;
    

  case 167:
  if (yyn == 167)
    /* "VondaGrammar.y":590  */ /* lalr1.java:489  */
    { yyval = new ExpAssignment(((RTExpression)(yystack.valueAt (2-(1)))), ((RTExpression)(yystack.valueAt (2-(2))))); };
  break;
    

  case 168:
  if (yyn == 168)
    /* "VondaGrammar.y":591  */ /* lalr1.java:489  */
    { yyval = new ExpAssignment(((RTExpression)(yystack.valueAt (2-(1)))), ((RTExpression)(yystack.valueAt (2-(2))))); };
  break;
    

  case 169:
  if (yyn == 169)
    /* "VondaGrammar.y":595  */ /* lalr1.java:489  */
    {
    yyval = new ExpFieldAccess(((LinkedList<RTExpression>)(yystack.valueAt (2-(2)))), new ArrayList<>()); ((LinkedList<RTExpression>)(yystack.valueAt (2-(2)))).addFirst(((RTExpression)(yystack.valueAt (2-(1)))));
  };
  break;
    

  case 170:
  if (yyn == 170)
    /* "VondaGrammar.y":601  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))); ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))).addFirst(((RTExpression)(yystack.valueAt (3-(2))))); };
  break;
    

  case 171:
  if (yyn == 171)
    /* "VondaGrammar.y":602  */ /* lalr1.java:489  */
    { yyval = new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (2-(2))))); }}; };
  break;
    

  case 172:
  if (yyn == 172)
    /* "VondaGrammar.y":606  */ /* lalr1.java:489  */
    { yyval = new ExpVariable((( String )(yystack.valueAt (1-(1))))); };
  break;
    

  case 173:
  if (yyn == 173)
    /* "VondaGrammar.y":607  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 174:
  if (yyn == 174)
    /* "VondaGrammar.y":608  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (3-(2)))); };
  break;
    

  case 175:
  if (yyn == 175)
    /* "VondaGrammar.y":612  */ /* lalr1.java:489  */
    { yyval = new ExpNew(new Type((( String )(yystack.valueAt (2-(2)))))); };
  break;
    

  case 176:
  if (yyn == 176)
    /* "VondaGrammar.y":613  */ /* lalr1.java:489  */
    { yyval = new ExpNew(new Type((( String )(yystack.valueAt (4-(2))))), new LinkedList<>()); };
  break;
    

  case 177:
  if (yyn == 177)
    /* "VondaGrammar.y":614  */ /* lalr1.java:489  */
    {
    yyval = new ExpNew(new Type((( String )(yystack.valueAt (5-(2))))), ((LinkedList<RTExpression>)(yystack.valueAt (5-(4)))));
  };
  break;
    

  case 178:
  if (yyn == 178)
    /* "VondaGrammar.y":617  */ /* lalr1.java:489  */
    {
    yyval = new ExpNew(((Type)(yystack.valueAt (4-(2)))), new LinkedList<>());
  };
  break;
    

  case 179:
  if (yyn == 179)
    /* "VondaGrammar.y":620  */ /* lalr1.java:489  */
    {
    yyval = new ExpNew(((Type)(yystack.valueAt (5-(2)))), ((LinkedList<RTExpression>)(yystack.valueAt (5-(4)))));
  };
  break;
    

  case 180:
  if (yyn == 180)
    /* "VondaGrammar.y":623  */ /* lalr1.java:489  */
    {
    yyval = new ExpNew(new Type("Array", new Type((( String )(yystack.valueAt (5-(2)))))),
                    new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (5-(4))))); }});
  };
  break;
    

  case 181:
  if (yyn == 181)
    /* "VondaGrammar.y":630  */ /* lalr1.java:489  */
    {
    yyval = new ExpLambda(((LinkedList)(yystack.valueAt (4-(2)))), new StatExpression(((RTExpression)(yystack.valueAt (4-(4))))));
  };
  break;
    

  case 182:
  if (yyn == 182)
    /* "VondaGrammar.y":633  */ /* lalr1.java:489  */
    {
    yyval = new ExpLambda(((LinkedList)(yystack.valueAt (4-(2)))), ((StatAbstractBlock)(yystack.valueAt (4-(4)))));
  };
  break;
    

  case 183:
  if (yyn == 183)
    /* "VondaGrammar.y":636  */ /* lalr1.java:489  */
    {
    yyval = new ExpLambda(new LinkedList<>(), new StatExpression(((RTExpression)(yystack.valueAt (4-(4))))));
  };
  break;
    

  case 184:
  if (yyn == 184)
    /* "VondaGrammar.y":639  */ /* lalr1.java:489  */
    { yyval = new ExpLambda(new LinkedList<>(), ((StatAbstractBlock)(yystack.valueAt (4-(4))))); };
  break;
    

  case 185:
  if (yyn == 185)
    /* "VondaGrammar.y":644  */ /* lalr1.java:489  */
    {
    yyval = new ExpDialogueAct(((RTExpression)(yystack.valueAt (5-(2)))), ((RTExpression)(yystack.valueAt (5-(4)))), new LinkedList<RTExpression>());
  };
  break;
    

  case 186:
  if (yyn == 186)
    /* "VondaGrammar.y":647  */ /* lalr1.java:489  */
    {
    yyval = new ExpDialogueAct(((RTExpression)(yystack.valueAt (6-(2)))), ((RTExpression)(yystack.valueAt (6-(4)))), ((LinkedList<RTExpression>)(yystack.valueAt (6-(5)))));
  };
  break;
    

  case 187:
  if (yyn == 187)
    /* "VondaGrammar.y":653  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (2-(2)))); };
  break;
    

  case 188:
  if (yyn == 188)
    /* "VondaGrammar.y":654  */ /* lalr1.java:489  */
    { yyval = (( String )(yystack.valueAt (1-(1)))); };
  break;
    

  case 189:
  if (yyn == 189)
    /* "VondaGrammar.y":655  */ /* lalr1.java:489  */
    { yyval = (( ExpSingleValue )(yystack.valueAt (1-(1)))); };
  break;
    

  case 190:
  if (yyn == 190)
    /* "VondaGrammar.y":656  */ /* lalr1.java:489  */
    { yyval = (( String )(yystack.valueAt (1-(1)))); };
  break;
    

  case 191:
  if (yyn == 191)
    /* "VondaGrammar.y":660  */ /* lalr1.java:489  */
    {
    yyval = ((LinkedList<RTExpression>)(yystack.valueAt (5-(5)))); ((LinkedList<RTExpression>)(yystack.valueAt (5-(5)))).addFirst(((RTExpression)(yystack.valueAt (5-(4))))); ((LinkedList<RTExpression>)(yystack.valueAt (5-(5)))).addFirst(((RTExpression)(yystack.valueAt (5-(2)))));
  };
  break;
    

  case 192:
  if (yyn == 192)
    /* "VondaGrammar.y":663  */ /* lalr1.java:489  */
    { new LinkedList<RTExpression>(); };
  break;
    


/* "VondaGrammar.java":1980  */ /* lalr1.java:489  */
        default: break;
      }

    yy_symbol_print ("-> $$ =", yyr1_[yyn], yyval, yyloc);

    yystack.pop (yylen);
    yylen = 0;

    /* Shift the result of the reduction.  */
    int yystate = yy_lr_goto_state_ (yystack.stateAt (0), yyr1_[yyn]);
    yystack.push (yystate, yyval, yyloc);
    return YYNEWSTATE;
  }


  /* Return YYSTR after stripping away unnecessary quotes and
     backslashes, so that it's suitable for yyerror.  The heuristic is
     that double-quoting is unnecessary unless the string contains an
     apostrophe, a comma, or backslash (other than backslash-backslash).
     YYSTR is taken from yytname.  */
  private final String yytnamerr_ (String yystr)
  {
    if (yystr.charAt (0) == '"')
      {
        StringBuffer yyr = new StringBuffer ();
        strip_quotes: for (int i = 1; i < yystr.length (); i++)
          switch (yystr.charAt (i))
            {
            case '\'':
            case ',':
              break strip_quotes;

            case '\\':
              if (yystr.charAt(++i) != '\\')
                break strip_quotes;
              /* Fall through.  */
            default:
              yyr.append (yystr.charAt (i));
              break;

            case '"':
              return yyr.toString ();
            }
      }
    else if (yystr.equals ("$end"))
      return "end of input";

    return yystr;
  }


  /*--------------------------------.
  | Print this symbol on YYOUTPUT.  |
  `--------------------------------*/

  private void yy_symbol_print (String s, int yytype,
                                 Object yyvaluep                                 , Object yylocationp)
  {
    if (yydebug > 0)
    yycdebug (s + (yytype < yyntokens_ ? " token " : " nterm ")
              + yytname_[yytype] + " ("
              + yylocationp + ": "
              + (yyvaluep == null ? "(null)" : yyvaluep.toString ()) + ")");
  }


  /**
   * Parse input from the scanner that was specified at object construction
   * time.  Return whether the end of the input was reached successfully.
   *
   * @return <tt>true</tt> if the parsing succeeds.  Note that this does not
   *          imply that there were no syntax errors.
   */
   public boolean parse () throws java.io.IOException

  {
    /* @$.  */
    Location yyloc;


    /* Lookahead and lookahead in internal form.  */
    int yychar = yyempty_;
    int yytoken = 0;

    /* State.  */
    int yyn = 0;
    int yylen = 0;
    int yystate = 0;
    YYStack yystack = new YYStack ();
    int label = YYNEWSTATE;

    /* Error handling.  */
    int yynerrs_ = 0;
    /* The location where the error started.  */
    Location yyerrloc = null;

    /* Location. */
    Location yylloc = new Location (null, null);

    /* Semantic value of the lookahead.  */
    Object yylval = null;

    yycdebug ("Starting parse\n");
    yyerrstatus_ = 0;

    /* Initialize the stack.  */
    yystack.push (yystate, yylval , yylloc);



    for (;;)
      switch (label)
      {
        /* New state.  Unlike in the C/C++ skeletons, the state is already
           pushed when we come here.  */
      case YYNEWSTATE:
        yycdebug ("Entering state " + yystate + "\n");
        if (yydebug > 0)
          yystack.print (yyDebugStream);

        /* Accept?  */
        if (yystate == yyfinal_)
          return true;

        /* Take a decision.  First try without lookahead.  */
        yyn = yypact_[yystate];
        if (yy_pact_value_is_default_ (yyn))
          {
            label = YYDEFAULT;
            break;
          }

        /* Read a lookahead token.  */
        if (yychar == yyempty_)
          {


            yycdebug ("Reading a token: ");
            yychar = yylexer.yylex ();
            yylval = yylexer.getLVal ();
            yylloc = new Location (yylexer.getStartPos (),
                            yylexer.getEndPos ());

          }

        /* Convert token to internal form.  */
        if (yychar <= Lexer.EOF)
          {
            yychar = yytoken = Lexer.EOF;
            yycdebug ("Now at end of input.\n");
          }
        else
          {
            yytoken = yytranslate_ (yychar);
            yy_symbol_print ("Next token is", yytoken,
                             yylval, yylloc);
          }

        /* If the proper action on seeing token YYTOKEN is to reduce or to
           detect an error, take that action.  */
        yyn += yytoken;
        if (yyn < 0 || yylast_ < yyn || yycheck_[yyn] != yytoken)
          label = YYDEFAULT;

        /* <= 0 means reduce or error.  */
        else if ((yyn = yytable_[yyn]) <= 0)
          {
            if (yy_table_value_is_error_ (yyn))
              label = YYERRLAB;
            else
              {
                yyn = -yyn;
                label = YYREDUCE;
              }
          }

        else
          {
            /* Shift the lookahead token.  */
            yy_symbol_print ("Shifting", yytoken,
                             yylval, yylloc);

            /* Discard the token being shifted.  */
            yychar = yyempty_;

            /* Count tokens shifted since error; after three, turn off error
               status.  */
            if (yyerrstatus_ > 0)
              --yyerrstatus_;

            yystate = yyn;
            yystack.push (yystate, yylval, yylloc);
            label = YYNEWSTATE;
          }
        break;

      /*-----------------------------------------------------------.
      | yydefault -- do the default action for the current state.  |
      `-----------------------------------------------------------*/
      case YYDEFAULT:
        yyn = yydefact_[yystate];
        if (yyn == 0)
          label = YYERRLAB;
        else
          label = YYREDUCE;
        break;

      /*-----------------------------.
      | yyreduce -- Do a reduction.  |
      `-----------------------------*/
      case YYREDUCE:
        yylen = yyr2_[yyn];
        label = yyaction (yyn, yystack, yylen);
        yystate = yystack.stateAt (0);
        break;

      /*------------------------------------.
      | yyerrlab -- here on detecting error |
      `------------------------------------*/
      case YYERRLAB:
        /* If not already recovering from an error, report this error.  */
        if (yyerrstatus_ == 0)
          {
            ++yynerrs_;
            if (yychar == yyempty_)
              yytoken = yyempty_;
            yyerror (yylloc, yysyntax_error (yystate, yytoken));
          }

        yyerrloc = yylloc;
        if (yyerrstatus_ == 3)
          {
        /* If just tried and failed to reuse lookahead token after an
         error, discard it.  */

        if (yychar <= Lexer.EOF)
          {
          /* Return failure if at end of input.  */
          if (yychar == Lexer.EOF)
            return false;
          }
        else
            yychar = yyempty_;
          }

        /* Else will try to reuse lookahead token after shifting the error
           token.  */
        label = YYERRLAB1;
        break;

      /*-------------------------------------------------.
      | errorlab -- error raised explicitly by YYERROR.  |
      `-------------------------------------------------*/
      case YYERROR:

        yyerrloc = yystack.locationAt (yylen - 1);
        /* Do not reclaim the symbols of the rule which action triggered
           this YYERROR.  */
        yystack.pop (yylen);
        yylen = 0;
        yystate = yystack.stateAt (0);
        label = YYERRLAB1;
        break;

      /*-------------------------------------------------------------.
      | yyerrlab1 -- common code for both syntax error and YYERROR.  |
      `-------------------------------------------------------------*/
      case YYERRLAB1:
        yyerrstatus_ = 3;       /* Each real token shifted decrements this.  */

        for (;;)
          {
            yyn = yypact_[yystate];
            if (!yy_pact_value_is_default_ (yyn))
              {
                yyn += yyterror_;
                if (0 <= yyn && yyn <= yylast_ && yycheck_[yyn] == yyterror_)
                  {
                    yyn = yytable_[yyn];
                    if (0 < yyn)
                      break;
                  }
              }

            /* Pop the current state because it cannot handle the
             * error token.  */
            if (yystack.height == 0)
              return false;

            yyerrloc = yystack.locationAt (0);
            yystack.pop ();
            yystate = yystack.stateAt (0);
            if (yydebug > 0)
              yystack.print (yyDebugStream);
          }

        if (label == YYABORT)
            /* Leave the switch.  */
            break;


        /* Muck with the stack to setup for yylloc.  */
        yystack.push (0, null, yylloc);
        yystack.push (0, null, yyerrloc);
        yyloc = yylloc (yystack, 2);
        yystack.pop (2);

        /* Shift the error token.  */
        yy_symbol_print ("Shifting", yystos_[yyn],
                         yylval, yyloc);

        yystate = yyn;
        yystack.push (yyn, yylval, yyloc);
        label = YYNEWSTATE;
        break;

        /* Accept.  */
      case YYACCEPT:
        return true;

        /* Abort.  */
      case YYABORT:
        return false;
      }
}




  // Generate an error message.
  private String yysyntax_error (int yystate, int tok)
  {
    if (yyErrorVerbose)
      {
        /* There are many possibilities here to consider:
           - If this state is a consistent state with a default action,
             then the only way this function was invoked is if the
             default action is an error action.  In that case, don't
             check for expected tokens because there are none.
           - The only way there can be no lookahead present (in tok) is
             if this state is a consistent state with a default action.
             Thus, detecting the absence of a lookahead is sufficient to
             determine that there is no unexpected or expected token to
             report.  In that case, just report a simple "syntax error".
           - Don't assume there isn't a lookahead just because this
             state is a consistent state with a default action.  There
             might have been a previous inconsistent state, consistent
             state with a non-default action, or user semantic action
             that manipulated yychar.  (However, yychar is currently out
             of scope during semantic actions.)
           - Of course, the expected token list depends on states to
             have correct lookahead information, and it depends on the
             parser not to perform extra reductions after fetching a
             lookahead from the scanner and before detecting a syntax
             error.  Thus, state merging (from LALR or IELR) and default
             reductions corrupt the expected token list.  However, the
             list is correct for canonical LR with one exception: it
             will still contain any token that will not be accepted due
             to an error action in a later state.
        */
        if (tok != yyempty_)
          {
            /* FIXME: This method of building the message is not compatible
               with internationalization.  */
            StringBuffer res =
              new StringBuffer ("syntax error, unexpected ");
            res.append (yytnamerr_ (yytname_[tok]));
            int yyn = yypact_[yystate];
            if (!yy_pact_value_is_default_ (yyn))
              {
                /* Start YYX at -YYN if negative to avoid negative
                   indexes in YYCHECK.  In other words, skip the first
                   -YYN actions for this state because they are default
                   actions.  */
                int yyxbegin = yyn < 0 ? -yyn : 0;
                /* Stay within bounds of both yycheck and yytname.  */
                int yychecklim = yylast_ - yyn + 1;
                int yyxend = yychecklim < yyntokens_ ? yychecklim : yyntokens_;
                int count = 0;
                for (int x = yyxbegin; x < yyxend; ++x)
                  if (yycheck_[x + yyn] == x && x != yyterror_
                      && !yy_table_value_is_error_ (yytable_[x + yyn]))
                    ++count;
                if (count < 5)
                  {
                    count = 0;
                    for (int x = yyxbegin; x < yyxend; ++x)
                      if (yycheck_[x + yyn] == x && x != yyterror_
                          && !yy_table_value_is_error_ (yytable_[x + yyn]))
                        {
                          res.append (count++ == 0 ? ", expecting " : " or ");
                          res.append (yytnamerr_ (yytname_[x]));
                        }
                  }
              }
            return res.toString ();
          }
      }

    return "syntax error";
  }

  /**
   * Whether the given <code>yypact_</code> value indicates a defaulted state.
   * @param yyvalue   the value to check
   */
  private static boolean yy_pact_value_is_default_ (int yyvalue)
  {
    return yyvalue == yypact_ninf_;
  }

  /**
   * Whether the given <code>yytable_</code>
   * value indicates a syntax error.
   * @param yyvalue the value to check
   */
  private static boolean yy_table_value_is_error_ (int yyvalue)
  {
    return yyvalue == yytable_ninf_;
  }

  private static final short yypact_ninf_ = -301;
  private static final short yytable_ninf_ = -105;

  /* YYPACT[STATE-NUM] -- Index in YYTABLE of the portion describing
   STATE-NUM.  */
  private static final short yypact_[] = yypact_init();
  private static final short[] yypact_init()
  {
    return new short[]
    {
     579,    61,   -19,    -8,    19,   764,    72,    74,    88,   110,
     145,  -301,  -301,   225,  -301,  -301,   367,   238,   265,   325,
     330,   252,   252,  -301,  -301,   210,  -301,   478,  1146,   250,
    1205,  1205,   252,   252,   -21,   317,     3,   579,   579,  -301,
    -301,  -301,  -301,  -301,  -301,  -301,  -301,  -301,  -301,   579,
     579,   290,  -301,  -301,   341,  -301,   -29,   356,   363,   331,
     349,   353,   276,    20,   321,   273,  -301,  -301,  -301,  -301,
    -301,   379,   378,  -301,  -301,    73,  -301,  -301,   139,  -301,
    -301,   390,  -301,  -301,  -301,  -301,    86,   411,   227,   398,
       4,  1159,   396,     9,   395,  1159,   208,  -301,   -18,   393,
     393,  1159,  1159,  1159,  1159,   294,  1159,  -301,  -301,  -301,
    -301,  1159,  1159,   431,   613,   777,   810,   250,  -301,   274,
    -301,  -301,   700,   400,  -301,   406,   296,   399,    28,   178,
     405,  -301,  -301,  -301,  -301,  -301,  -301,  -301,  1159,   402,
    -301,   179,   579,   579,  -301,  -301,  -301,  -301,   301,   412,
     -22,  -301,  1159,  1205,  1205,  1205,  1205,  1205,  1205,  1205,
    1205,  1205,  1205,  1205,  1205,  1205,  1205,  1205,  1205,   104,
    -301,  1159,  1159,  1159,  -301,  1159,  1159,  -301,  -301,   843,
    1159,   404,  -301,   401,   413,   185,   228,  1159,   877,   416,
      46,   417,   422,   910,  1159,   944,    60,  -301,   121,    65,
     127,   130,    17,    23,  -301,   289,   978,   414,   414,   418,
     427,   175,   419,  1012,   407,  -301,   122,   420,   423,  -301,
    -301,   212,  1205,  -301,   424,   407,   -21,   254,  -301,  -301,
     255,   432,  -301,   256,   436,    44,   363,   331,   349,   353,
     276,    20,    20,   321,   321,   321,   321,   273,   273,  -301,
    -301,  -301,   433,  1159,  -301,   379,   181,    26,    35,    45,
      50,  -301,  1159,  -301,  -301,   442,  1159,    53,  1045,    62,
      99,   764,   396,  -301,  -301,   438,   176,   201,  -301,   440,
     414,   448,  1159,   414,   764,  -301,  -301,  -301,   455,   472,
     479,   261,  -301,  -301,  -301,   352,  1192,  1192,  -301,   459,
    -301,   250,  -301,  -301,  -301,   369,   307,   414,   309,   309,
     283,   309,   309,  -301,  1159,   131,  -301,  -301,  -301,  -301,
    -301,  -301,   132,  -301,   133,  1159,   764,   134,  1079,  1159,
     496,  -301,  -301,  1159,  -301,  -301,  -301,   357,   135,  -301,
    -301,  -301,  1113,  1113,  -301,   455,  -301,  -301,  -301,  -301,
    -301,   -21,   460,  -301,  -301,  -301,  -301,   309,   309,  -301,
    -301,   407,  -301,  -301,   764,   169,  -301,   764,   764,   171,
     173,   764,  -301,   322,   461,   464,   700,   357,   414,  -301,
     407,  -301,   407,  -301,   462,  -301,  -301,  -301,  -301,   764,
    -301,  -301,   764,   764,  -301,   470,   474,   475,  -301,  -301,
     357,  -301,  -301,  -301,   -21,  -301,  -301,  -301,  -301,  -301,
    -301,   700,   477,  -301,  -301
    };
  }

/* YYDEFACT[STATE-NUM] -- Default reduction number in state STATE-NUM.
   Performed when YYTABLE does not specify something else to do.  Zero
   means the default is an error.  */
  private static final short yydefact_[] = yydefact_init();
  private static final short[] yydefact_init()
  {
    return new short[]
    {
       8,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,   149,    11,     0,    10,     9,     0,     0,     0,     0,
       0,     0,     0,   160,   161,   152,   162,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     8,     8,    15,
      17,    19,    23,    24,    25,    20,    22,    21,    26,     8,
       8,     0,    18,   159,     0,   105,     0,   111,   114,   116,
     117,   119,   121,   123,   126,   131,   134,   147,   138,   140,
     148,   150,   151,   155,   156,   157,   109,   110,   158,   153,
     112,     0,    36,    38,    39,    40,   152,     0,   104,     0,
       0,     0,    13,   175,     0,     0,   152,    34,     0,   157,
     158,     0,     0,     0,     0,   152,     0,   144,   157,   158,
     143,     0,     0,     0,     0,     0,     0,     0,   166,   152,
      30,    27,    31,     0,    28,     0,   152,     0,     0,   104,
       0,   145,   146,   141,   142,   189,   190,   188,     0,     0,
       1,   104,     8,     8,     5,     4,     7,     3,   104,     0,
       0,    16,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
     169,     0,     0,     0,   168,     0,     0,   167,    37,     0,
       0,     0,    68,     0,     0,     0,   104,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,    35,     0,     0,
       0,     0,     0,     0,    33,   152,     0,    97,     0,     0,
       0,    99,   100,     0,    71,   103,     0,   107,     0,    32,
      29,     0,     0,   154,     0,   187,     0,     0,     6,     2,
       0,     0,    69,     0,     0,     0,   113,   115,   118,   120,
     122,   124,   125,   129,   130,   127,   128,   132,   133,   135,
     136,   137,   172,     0,   173,   171,     0,     0,     0,     0,
       0,    97,     0,    65,    70,     0,     0,     0,     0,     0,
       0,     0,    13,    12,   176,     0,    74,     0,   178,     0,
       0,     0,     0,     0,     0,    92,    91,    87,     0,     0,
       0,     0,    82,    83,    98,     0,     0,     0,    72,     0,
     163,     0,   106,   139,    84,     0,   104,     0,     0,     0,
       0,     0,     0,    66,     0,     0,   170,   164,    94,    93,
      96,    95,     0,    67,     0,     0,     0,     0,     0,     0,
      41,    14,   177,     0,   180,   179,    52,     0,     0,    53,
      43,    89,     0,     0,    88,     0,   101,   102,    73,   108,
     185,     0,     0,    86,    85,    78,    79,     0,     0,    80,
      81,   165,   174,    44,     0,     0,    48,     0,     0,     0,
       0,     0,    75,     0,     0,     0,    58,    60,     0,   184,
     183,   182,   181,    90,     0,   186,    76,    77,    50,     0,
      46,    47,     0,     0,    42,     0,     0,     0,    64,    55,
       0,    56,    59,    54,     0,    49,    45,    51,    61,    62,
      63,    58,   192,    57,   191
    };
  }

/* YYPGOTO[NTERM-NUM].  */
  private static final short yypgoto_[] = yypgoto_init();
  private static final short[] yypgoto_init()
  {
    return new short[]
    {
    -301,    34,  -301,  -301,   257,     2,  -301,   -60,   -23,  -301,
    -301,   397,  -301,  -301,  -301,  -301,  -301,  -301,  -301,   113,
    -272,  -301,   -26,   -33,  -187,   491,  -301,  -300,  -117,  -301,
     364,   124,    12,   518,   239,   359,  -301,   392,   389,   394,
     403,   391,   269,   236,   270,   -28,  -301,   243,  -301,  -301,
    -301,  -301,  -301,  -301,     0,  -301,  -301,    48,   295,  -301,
    -301,  -301,  -301,  -220,   143
    };
  }

/* YYDEFGOTO[NTERM-NUM].  */
  private static final short yydefgoto_[] = yydefgoto_init();
  private static final short[] yydefgoto_init()
  {
    return new short[]
    {
      -1,    35,    36,    37,   192,    38,   122,    39,   400,    40,
      41,    42,    43,    44,    45,    46,    47,    48,   375,   401,
     376,   377,    49,   118,   275,    50,    51,   355,   208,    52,
      53,   209,    54,    55,   218,    56,    57,    58,    59,    60,
      61,    62,    63,    64,    65,    66,    67,    68,    69,    70,
      71,    72,    73,    74,    99,    76,    77,   100,   170,   255,
      79,   212,    80,   139,   352
    };
  }

/* YYTABLE[YYPACT[STATE-NUM]] -- What to do in state STATE-NUM.  If
   positive, shift that token.  If negative, reduce the rule whose
   number is the opposite.  If YYTABLE_NINF, syntax error.  */
  private static final short yytable_[] = yytable_init();
  private static final short[] yytable_init()
  {
    return new short[]
    {
      75,   124,   131,   132,   123,    75,   305,    87,   279,   356,
     142,   359,   360,   151,     6,     6,   135,   136,    89,   137,
     232,   108,   108,    83,   197,   233,   299,    75,   115,   121,
     108,   108,   108,   108,    84,   138,   152,    75,    75,   125,
     127,   130,   174,   141,   186,   177,   187,   152,    78,    75,
      75,   160,   161,    78,    29,   184,   193,   386,   387,   285,
     194,    85,   117,   149,   188,   286,   174,   177,   318,   109,
     109,   144,   145,   162,   163,    78,   223,   319,   109,   109,
     109,   109,   152,   146,   147,    78,    78,   320,   152,   290,
     314,   152,   321,   152,   271,   325,   124,    78,    78,   219,
     152,    81,   189,    82,   328,   402,   172,   173,   280,   152,
     152,   152,    88,   309,   282,   152,   312,   234,   152,   111,
     112,    90,    75,   115,   121,   152,   210,   152,   411,   217,
     152,   384,   113,   179,   125,    91,   115,   180,   249,   250,
     251,   232,    75,    75,   252,   329,   372,   292,   293,   115,
      92,   253,   265,   108,   108,   108,   108,   108,   108,   108,
     108,   108,   108,   108,   108,   108,   108,   108,   108,   281,
      78,   341,   175,   176,   300,   283,   228,   229,   284,   362,
     363,   364,   367,   378,   412,    93,   152,   152,   234,   115,
      78,    78,   152,   358,   303,   152,   152,   152,   152,   152,
     152,   109,   109,   109,   109,   109,   109,   109,   109,   109,
     109,   109,   109,   109,   109,   109,   109,   389,   291,   392,
     336,   393,   108,   339,   296,   333,   227,   264,   383,   183,
     183,   117,   117,   317,   152,   115,   152,   234,   152,   210,
     152,   152,   210,   111,   112,   210,   152,   292,   354,   354,
    -104,   354,   354,   334,   232,   179,   113,   114,   115,   180,
     115,   116,   115,   117,   107,   110,   152,    10,    11,   182,
     109,    75,    95,   330,   266,   133,   134,   115,   183,   183,
     117,   117,   379,   381,    75,   101,   340,    21,    22,    23,
     129,    24,   105,    26,   306,   306,   306,   354,   354,   106,
     210,   295,   307,   308,   311,   158,   159,   111,   112,   222,
      30,    31,   102,   217,  -104,    32,    33,   140,   403,    78,
     113,   179,   210,   306,   115,   116,    75,   117,   366,  -104,
     148,   357,    78,   166,   167,   168,   179,   287,   288,   115,
     116,   179,   117,   179,  -104,   180,   115,   116,   230,   117,
     124,   353,   183,    27,   117,   287,   288,   210,   183,   395,
     117,   396,   397,   373,    75,   374,   388,    75,    75,   390,
     391,    75,   103,   394,    78,    98,    75,   104,   121,   164,
     165,   150,    10,    11,   153,   124,   155,   128,   125,    75,
     154,   405,    75,    75,   406,   407,   243,   244,   245,   246,
     344,   345,    21,    22,    23,   156,    24,    96,    26,    97,
     157,    75,    78,   121,    28,    78,    78,   350,   351,    78,
     346,   347,   169,   125,    78,    30,    31,   241,   242,   171,
      32,    33,   178,    34,   247,   248,   181,    78,   185,   191,
      78,    78,   195,   115,     8,   220,   221,   222,   224,   226,
     190,   262,   231,   215,   196,   263,   270,   272,    27,    78,
     198,   199,   200,   201,   273,   128,   294,   295,   297,   301,
     202,   203,   152,   211,   214,   216,   304,   302,   313,   310,
     179,     1,     2,     3,   323,     4,   332,     5,   335,     6,
       7,     8,   337,    10,    11,   306,    13,   225,   342,    16,
      17,    18,    19,    20,   348,   343,   371,   398,   385,   399,
     204,   235,   404,    21,    22,    23,   408,    24,   119,    26,
     409,   410,    27,   120,   413,    28,   351,   143,    94,   331,
     256,   257,   258,   254,   259,   260,    30,    31,   211,   216,
     349,    32,    33,   237,    34,   236,   267,   269,   240,   238,
     316,     0,   276,   277,   276,   414,     0,     0,     0,   239,
       0,     0,     0,     0,     0,   128,     0,     0,     0,     0,
       0,     0,   276,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     1,     2,     3,     0,     4,     0,     5,     0,
       6,     7,     8,     9,    10,    11,    12,    13,    14,    15,
      16,    17,    18,    19,    20,     0,     0,     0,     0,     0,
       0,     0,   315,     0,    21,    22,    23,     0,    24,    25,
      26,   322,     0,    27,     0,   324,    28,   327,    10,    11,
      29,     0,     0,     0,     0,     0,     0,    30,    31,     0,
       0,   338,    32,    33,     0,    34,     0,     0,    21,    22,
      23,     0,    24,   205,    26,   211,   211,     0,     0,     0,
     206,   207,     0,     0,     0,     0,     0,     0,     0,     0,
       0,    30,    31,   361,     0,     0,    32,    33,     0,    34,
       0,     0,     0,     0,   365,     0,     0,   369,   370,     0,
       0,     0,   276,     0,     0,     0,     0,     0,     0,     0,
       0,   380,   382,     1,     2,     3,     0,     4,     0,     5,
       0,     6,     7,     8,     0,    10,    11,     0,    13,     0,
       0,    16,    17,    18,    19,    20,     0,     0,     0,     0,
       0,     0,     0,     0,     0,    21,    22,    23,     0,    24,
     119,    26,     0,     0,    27,     0,     0,    28,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,    30,    31,
       0,     0,     0,    32,    33,     0,    34,     1,     2,     3,
       0,     4,     0,     5,     0,     0,     7,     8,     0,    10,
      11,     0,    13,     0,     0,    16,    17,    18,    19,    20,
       0,     0,    10,    11,     0,     0,     0,     0,     0,    21,
      22,    23,     0,    24,    86,    26,     0,     0,    27,     0,
       0,    28,    21,    22,    23,     0,    24,    96,    26,     0,
       0,   213,    30,    31,    28,    10,    11,    32,    33,     0,
      34,     0,     0,     0,     0,    30,    31,     0,     0,     0,
      32,    33,     0,    34,     0,    21,    22,    23,     0,    24,
      96,    26,     0,     0,     0,     0,     0,    28,    10,    11,
       0,     0,   215,     0,     0,     0,     0,     0,    30,    31,
       0,     0,     0,    32,    33,     0,    34,     0,    21,    22,
      23,     0,    24,    96,    26,     0,     0,     0,     0,     0,
     206,   261,    10,    11,     0,     0,     0,     0,     0,     0,
       0,    30,    31,     0,     0,     0,    32,    33,     0,    34,
       0,     0,    21,    22,    23,     0,    24,    96,    26,   268,
       0,     0,     0,     0,    28,    10,    11,     0,     0,     0,
       0,     0,     0,     0,     0,    30,    31,     0,     0,     0,
      32,    33,     0,    34,     0,    21,    22,    23,     0,    24,
      96,    26,     0,     0,     0,     0,     0,    28,   274,    10,
      11,     0,     0,     0,     0,     0,     0,     0,    30,    31,
       0,     0,     0,    32,    33,     0,    34,     0,     0,    21,
      22,    23,     0,    24,    96,    26,     0,     0,     0,     0,
       0,    28,   278,    10,    11,     0,     0,     0,     0,     0,
       0,     0,    30,    31,     0,     0,     0,    32,    33,     0,
      34,     0,     0,    21,    22,    23,     0,    24,   205,    26,
       0,     0,     0,     0,     0,    28,   289,    10,    11,     0,
       0,     0,     0,     0,     0,     0,    30,    31,     0,     0,
       0,    32,    33,     0,    34,     0,     0,    21,    22,    23,
       0,    24,    96,    26,     0,     0,     0,   298,     0,    28,
      10,    11,     0,     0,     0,     0,     0,     0,     0,     0,
      30,    31,     0,     0,     0,    32,    33,     0,    34,     0,
      21,    22,    23,     0,    24,    96,    26,     0,     0,     0,
       0,     0,    28,   326,    10,    11,     0,     0,     0,     0,
       0,     0,     0,    30,    31,     0,     0,     0,    32,    33,
       0,    34,     0,     0,    21,    22,    23,     0,    24,    96,
      26,     0,     0,     0,     0,     0,    28,   368,    10,    11,
       0,     0,     0,     0,     0,     0,     0,    30,    31,     0,
       0,     0,    32,    33,     0,    34,     0,     0,    21,    22,
      23,     0,    24,    96,    26,     0,     0,    27,     0,     0,
      28,    10,    11,     0,     0,     0,     0,     0,     0,     0,
       0,    30,    31,     0,    10,    11,    32,    33,     0,    34,
       0,    21,    22,    23,     0,    24,   126,    26,     0,     0,
       0,     0,     0,    28,    21,    22,    23,     0,    24,    96,
      26,     0,     0,     0,    30,    31,    28,    10,    11,    32,
      33,     0,    34,     0,     0,     0,     0,    30,    31,     0,
      10,    11,    32,    33,     0,    34,     0,    21,    22,    23,
       0,    24,    96,    26,     0,     0,     0,     0,     0,   206,
      21,    22,    23,     0,    24,   105,    26,     0,     0,     0,
      30,    31,    28,     0,     0,    32,    33,     0,    34,     0,
       0,     0,     0,    30,    31,     0,     0,     0,    32,    33
    };
  }

private static final short yycheck_[] = yycheck_init();
  private static final short[] yycheck_init()
  {
    return new short[]
    {
       0,    27,    30,    31,    27,     5,   226,     5,   195,   309,
      36,   311,   312,    42,    11,    11,    37,    38,     6,    40,
      42,    21,    22,    42,    42,    47,   213,    27,    50,    27,
      30,    31,    32,    33,    42,    56,    65,    37,    38,    27,
      28,    29,    75,    40,    40,    78,    42,    65,     0,    49,
      50,    31,    32,     5,    51,    88,    47,   357,   358,    42,
      51,    42,    53,    51,    90,    42,    99,   100,    42,    21,
      22,    37,    38,    53,    54,    27,    48,    42,    30,    31,
      32,    33,    65,    49,    50,    37,    38,    42,    65,   206,
      46,    65,    42,    65,    48,    42,   122,    49,    50,   122,
      65,    40,    90,    42,    42,   377,    33,    34,    48,    65,
      65,    65,    40,   230,    49,    65,   233,   150,    65,    33,
      34,    47,   122,    50,   122,    65,   114,    65,   400,   117,
      65,   351,    46,    47,   122,    47,    50,    51,   166,   167,
     168,    42,   142,   143,    40,    46,   333,   207,   208,    50,
      40,    47,   185,   153,   154,   155,   156,   157,   158,   159,
     160,   161,   162,   163,   164,   165,   166,   167,   168,    48,
     122,   288,    33,    34,    52,    48,   142,   143,    48,    48,
      48,    48,    48,    48,   404,    40,    65,    65,   221,    50,
     142,   143,    65,   310,   222,    65,    65,    65,    65,    65,
      65,   153,   154,   155,   156,   157,   158,   159,   160,   161,
     162,   163,   164,   165,   166,   167,   168,    48,   206,    48,
     280,    48,   222,   283,    49,    49,    47,    42,   345,    51,
      51,    53,    53,    52,    65,    50,    65,   270,    65,   227,
      65,    65,   230,    33,    34,   233,    65,   307,   308,   309,
      40,   311,   312,    52,    42,    47,    46,    47,    50,    51,
      50,    51,    50,    53,    21,    22,    65,    15,    16,    42,
     222,   271,    47,   271,    46,    32,    33,    50,    51,    51,
      53,    53,   342,   343,   284,    47,   284,    35,    36,    37,
      40,    39,    40,    41,    40,    40,    40,   357,   358,    47,
     288,    40,    48,    48,    48,    29,    30,    33,    34,    48,
      58,    59,    47,   301,    40,    63,    64,     0,   378,   271,
      46,    47,   310,    40,    50,    51,   326,    53,   326,    40,
      40,    48,   284,    60,    61,    62,    47,    48,    49,    50,
      51,    47,    53,    47,    48,    51,    50,    51,    47,    53,
     376,    42,    51,    44,    53,    48,    49,   345,    51,    37,
      53,    39,    40,     6,   364,     8,   364,   367,   368,   367,
     368,   371,    47,   371,   326,    16,   376,    47,   376,    58,
      59,    40,    15,    16,    28,   411,    55,    28,   376,   389,
      27,   389,   392,   393,   392,   393,   160,   161,   162,   163,
      48,    49,    35,    36,    37,    56,    39,    40,    41,    42,
      57,   411,   364,   411,    47,   367,   368,    48,    49,   371,
     296,   297,    43,   411,   376,    58,    59,   158,   159,    51,
      63,    64,    42,    66,   164,   165,    25,   389,    40,    43,
     392,   393,    47,    50,    13,    45,    40,    48,    43,    47,
      91,    47,    40,    52,    95,    42,    40,    40,    44,   411,
     101,   102,   103,   104,    42,   106,    48,    40,    49,    49,
     111,   112,    65,   114,   115,   116,    52,    54,    42,    47,
      47,     3,     4,     5,    42,     7,    48,     9,    48,    11,
      12,    13,    44,    15,    16,    40,    18,   138,    26,    21,
      22,    23,    24,    25,    45,    26,    10,    46,    48,    45,
     113,   152,    50,    35,    36,    37,    46,    39,    40,    41,
      46,    46,    44,    45,   411,    47,    49,    36,    10,   272,
     171,   172,   173,   169,   175,   176,    58,    59,   179,   180,
     301,    63,    64,   154,    66,   153,   187,   188,   157,   155,
     255,    -1,   193,   194,   195,   412,    -1,    -1,    -1,   156,
      -1,    -1,    -1,    -1,    -1,   206,    -1,    -1,    -1,    -1,
      -1,    -1,   213,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,     3,     4,     5,    -1,     7,    -1,     9,    -1,
      11,    12,    13,    14,    15,    16,    17,    18,    19,    20,
      21,    22,    23,    24,    25,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,   253,    -1,    35,    36,    37,    -1,    39,    40,
      41,   262,    -1,    44,    -1,   266,    47,   268,    15,    16,
      51,    -1,    -1,    -1,    -1,    -1,    -1,    58,    59,    -1,
      -1,   282,    63,    64,    -1,    66,    -1,    -1,    35,    36,
      37,    -1,    39,    40,    41,   296,   297,    -1,    -1,    -1,
      47,    48,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    58,    59,   314,    -1,    -1,    63,    64,    -1,    66,
      -1,    -1,    -1,    -1,   325,    -1,    -1,   328,   329,    -1,
      -1,    -1,   333,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,   342,   343,     3,     4,     5,    -1,     7,    -1,     9,
      -1,    11,    12,    13,    -1,    15,    16,    -1,    18,    -1,
      -1,    21,    22,    23,    24,    25,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    35,    36,    37,    -1,    39,
      40,    41,    -1,    -1,    44,    -1,    -1,    47,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    58,    59,
      -1,    -1,    -1,    63,    64,    -1,    66,     3,     4,     5,
      -1,     7,    -1,     9,    -1,    -1,    12,    13,    -1,    15,
      16,    -1,    18,    -1,    -1,    21,    22,    23,    24,    25,
      -1,    -1,    15,    16,    -1,    -1,    -1,    -1,    -1,    35,
      36,    37,    -1,    39,    40,    41,    -1,    -1,    44,    -1,
      -1,    47,    35,    36,    37,    -1,    39,    40,    41,    -1,
      -1,    44,    58,    59,    47,    15,    16,    63,    64,    -1,
      66,    -1,    -1,    -1,    -1,    58,    59,    -1,    -1,    -1,
      63,    64,    -1,    66,    -1,    35,    36,    37,    -1,    39,
      40,    41,    -1,    -1,    -1,    -1,    -1,    47,    15,    16,
      -1,    -1,    52,    -1,    -1,    -1,    -1,    -1,    58,    59,
      -1,    -1,    -1,    63,    64,    -1,    66,    -1,    35,    36,
      37,    -1,    39,    40,    41,    -1,    -1,    -1,    -1,    -1,
      47,    48,    15,    16,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    58,    59,    -1,    -1,    -1,    63,    64,    -1,    66,
      -1,    -1,    35,    36,    37,    -1,    39,    40,    41,    42,
      -1,    -1,    -1,    -1,    47,    15,    16,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    58,    59,    -1,    -1,    -1,
      63,    64,    -1,    66,    -1,    35,    36,    37,    -1,    39,
      40,    41,    -1,    -1,    -1,    -1,    -1,    47,    48,    15,
      16,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    58,    59,
      -1,    -1,    -1,    63,    64,    -1,    66,    -1,    -1,    35,
      36,    37,    -1,    39,    40,    41,    -1,    -1,    -1,    -1,
      -1,    47,    48,    15,    16,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    58,    59,    -1,    -1,    -1,    63,    64,    -1,
      66,    -1,    -1,    35,    36,    37,    -1,    39,    40,    41,
      -1,    -1,    -1,    -1,    -1,    47,    48,    15,    16,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    58,    59,    -1,    -1,
      -1,    63,    64,    -1,    66,    -1,    -1,    35,    36,    37,
      -1,    39,    40,    41,    -1,    -1,    -1,    45,    -1,    47,
      15,    16,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      58,    59,    -1,    -1,    -1,    63,    64,    -1,    66,    -1,
      35,    36,    37,    -1,    39,    40,    41,    -1,    -1,    -1,
      -1,    -1,    47,    48,    15,    16,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    58,    59,    -1,    -1,    -1,    63,    64,
      -1,    66,    -1,    -1,    35,    36,    37,    -1,    39,    40,
      41,    -1,    -1,    -1,    -1,    -1,    47,    48,    15,    16,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    58,    59,    -1,
      -1,    -1,    63,    64,    -1,    66,    -1,    -1,    35,    36,
      37,    -1,    39,    40,    41,    -1,    -1,    44,    -1,    -1,
      47,    15,    16,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    58,    59,    -1,    15,    16,    63,    64,    -1,    66,
      -1,    35,    36,    37,    -1,    39,    40,    41,    -1,    -1,
      -1,    -1,    -1,    47,    35,    36,    37,    -1,    39,    40,
      41,    -1,    -1,    -1,    58,    59,    47,    15,    16,    63,
      64,    -1,    66,    -1,    -1,    -1,    -1,    58,    59,    -1,
      15,    16,    63,    64,    -1,    66,    -1,    35,    36,    37,
      -1,    39,    40,    41,    -1,    -1,    -1,    -1,    -1,    47,
      35,    36,    37,    -1,    39,    40,    41,    -1,    -1,    -1,
      58,    59,    47,    -1,    -1,    63,    64,    -1,    66,    -1,
      -1,    -1,    -1,    58,    59,    -1,    -1,    -1,    63,    64
    };
  }

/* YYSTOS[STATE-NUM] -- The (internal number of the) accessing
   symbol of state STATE-NUM.  */
  private static final short yystos_[] = yystos_init();
  private static final short[] yystos_init()
  {
    return new short[]
    {
       0,     3,     4,     5,     7,     9,    11,    12,    13,    14,
      15,    16,    17,    18,    19,    20,    21,    22,    23,    24,
      25,    35,    36,    37,    39,    40,    41,    44,    47,    51,
      58,    59,    63,    64,    66,    68,    69,    70,    72,    74,
      76,    77,    78,    79,    80,    81,    82,    83,    84,    89,
      92,    93,    96,    97,    99,   100,   102,   103,   104,   105,
     106,   107,   108,   109,   110,   111,   112,   113,   114,   115,
     116,   117,   118,   119,   120,   121,   122,   123,   124,   127,
     129,    40,    42,    42,    42,    42,    40,    72,    40,    99,
      47,    47,    40,    40,   100,    47,    40,    42,   102,   121,
     124,    47,    47,    47,    47,    40,    47,   114,   121,   124,
     114,    33,    34,    46,    47,    50,    51,    53,    90,    40,
      45,    72,    73,    75,    89,    99,    40,    99,   102,    40,
      99,   112,   112,   114,   114,    37,    38,    40,    56,   130,
       0,    40,    89,    92,    68,    68,    68,    68,    40,    99,
      40,    42,    65,    28,    27,    55,    56,    57,    29,    30,
      31,    32,    53,    54,    58,    59,    60,    61,    62,    43,
     125,    51,    33,    34,    90,    33,    34,    90,    42,    47,
      51,    25,    42,    51,    90,    40,    40,    42,    89,    99,
     102,    43,    71,    47,    51,    47,   102,    42,   102,   102,
     102,   102,   102,   102,    78,    40,    47,    48,    95,    98,
      99,   102,   128,    44,   102,    52,   102,    99,   101,    75,
      45,    40,    48,    48,    43,   102,    47,    47,    68,    68,
      47,    40,    42,    47,    90,   102,   104,   105,   106,   107,
     108,   109,   109,   110,   110,   110,   110,   111,   111,   112,
     112,   112,    40,    47,    97,   126,   102,   102,   102,   102,
     102,    48,    47,    42,    42,    90,    46,   102,    42,   102,
      40,    48,    40,    42,    48,    91,   102,   102,    48,    91,
      48,    48,    49,    48,    48,    42,    42,    48,    49,    48,
      95,    99,    74,    74,    48,    40,    49,    49,    45,    91,
      52,    49,    54,   112,    52,   130,    40,    48,    48,    95,
      47,    48,    95,    42,    46,   102,   125,    52,    42,    42,
      42,    42,   102,    42,   102,    42,    48,   102,    42,    46,
      72,    71,    48,    49,    52,    48,    74,    44,   102,    74,
      72,    95,    26,    26,    48,    49,    98,    98,    45,   101,
      48,    49,   131,    42,    74,    94,    94,    48,    95,    94,
      94,   102,    48,    48,    48,   102,    72,    48,    48,   102,
     102,    10,    91,     6,     8,    85,    87,    88,    48,    74,
     102,    74,   102,    95,   130,    48,    94,    94,    72,    48,
      72,    72,    48,    48,    72,    37,    39,    40,    46,    45,
      75,    86,    87,    74,    50,    72,    72,    72,    46,    46,
      46,    87,   130,    86,   131
    };
  }

/* YYR1[YYN] -- Symbol number of symbol that rule YYN derives.  */
  private static final short yyr1_[] = yyr1_init();
  private static final short[] yyr1_init()
  {
    return new short[]
    {
       0,    67,    68,    68,    68,    68,    68,    68,    68,    69,
      69,    69,    70,    71,    71,    72,    72,    72,    72,    72,
      72,    72,    72,    72,    72,    72,    72,    73,    73,    74,
      74,    75,    75,    76,    77,    77,    77,    77,    77,    77,
      77,    78,    78,    79,    79,    80,    80,    80,    80,    80,
      80,    80,    81,    82,    83,    84,    85,    86,    86,    87,
      87,    88,    88,    88,    88,    89,    89,    89,    89,    89,
      89,    90,    90,    90,    91,    91,    92,    92,    92,    92,
      92,    92,    92,    92,    93,    94,    94,    95,    95,    95,
      95,    96,    96,    96,    96,    96,    96,    97,    97,    98,
      98,    98,    98,    99,    99,    99,   100,   101,   101,   102,
     102,   102,   102,   103,   103,   104,   104,   105,   105,   106,
     106,   107,   107,   108,   108,   108,   109,   109,   109,   109,
     109,   110,   110,   110,   111,   111,   111,   111,   112,   112,
     113,   113,   113,   114,   114,   114,   114,   114,   115,   116,
     116,   116,   117,   117,   118,   118,   119,   119,   119,   119,
     120,   120,   120,   121,   121,   122,   123,   123,   123,   124,
     125,   125,   126,   126,   126,   127,   127,   127,   127,   127,
     127,   128,   128,   128,   128,   129,   129,   130,   130,   130,
     130,   131,   131
    };
  }

/* YYR2[YYN] -- Number of symbols on the right hand side of rule YYN.  */
  private static final byte yyr2_[] = yyr2_init();
  private static final byte[] yyr2_init()
  {
    return new byte[]
    {
       0,     2,     3,     2,     2,     2,     3,     2,     0,     1,
       1,     1,     4,     0,     3,     1,     2,     1,     1,     1,
       1,     1,     1,     1,     1,     1,     1,     1,     1,     3,
       2,     1,     2,     3,     2,     3,     2,     3,     2,     2,
       2,     5,     7,     5,     6,     8,     7,     7,     6,     8,
       7,     8,     5,     5,     7,     7,     2,     3,     0,     2,
       1,     3,     3,     3,     2,     4,     4,     5,     3,     3,
       4,     2,     3,     4,     1,     3,     6,     6,     5,     5,
       5,     5,     4,     4,     4,     1,     1,     2,     3,     3,
       4,     4,     4,     4,     4,     4,     4,     3,     4,     1,
       1,     3,     3,     3,     1,     1,     4,     1,     3,     1,
       1,     1,     1,     3,     1,     3,     1,     1,     3,     1,
       3,     1,     3,     1,     3,     3,     1,     3,     3,     3,
       3,     1,     3,     3,     1,     3,     3,     3,     1,     4,
       1,     2,     2,     2,     2,     2,     2,     1,     1,     1,
       1,     1,     1,     1,     3,     1,     1,     1,     1,     1,
       1,     1,     1,     4,     4,     5,     2,     2,     2,     2,
       3,     2,     1,     1,     3,     2,     4,     5,     4,     5,
       5,     4,     4,     4,     4,     5,     6,     2,     1,     1,
       1,     5,     0
    };
  }

  /* YYTOKEN_NUMBER[YYLEX-NUM] -- Internal symbol number corresponding
      to YYLEX-NUM.  */
  private static final short yytoken_number_[] = yytoken_number_init();
  private static final short[] yytoken_number_init()
  {
    return new short[]
    {
       0,   256,   257,   258,   259,   260,   261,   262,   263,   264,
     265,   266,   267,   268,   269,   270,   271,   272,   273,   274,
     275,   276,   277,   278,   279,   280,   281,   282,   283,   284,
     285,   286,   287,   288,   289,   290,   291,   292,   293,   294,
     295,   296,    59,    46,   123,   125,    58,    40,    41,    44,
      61,    91,    93,    60,    62,   124,    94,    38,    43,    45,
      42,    47,    37,    33,   126,    63,    35
    };
  }

  /* YYTNAME[SYMBOL-NUM] -- String name of the symbol SYMBOL-NUM.
     First, the terminals, then, starting at \a yyntokens_, nonterminals.  */
  private static final String yytname_[] = yytname_init();
  private static final String[] yytname_init()
  {
    return new String[]
    {
  "$end", "error", "$undefined", "BREAK", "CANCEL", "CANCEL_ALL", "CASE",
  "CONTINUE", "DEFAULT", "DO", "ELSE", "FINAL", "FOR", "IF", "IMPORT",
  "NEW", "NULL", "PRIVATE", "PROPOSE", "PROTECTED", "PUBLIC", "RETURN",
  "SWITCH", "TIMEOUT", "TIMEOUT_BEHAVIOUR", "WHILE", "ARROW", "ANDAND",
  "OROR", "EQEQ", "NOTEQ", "GTEQ", "LTEQ", "MINUSEQ", "PLUSEQ",
  "MINUSMINUS", "PLUSPLUS", "STRING", "WILDCARD", "INT", "VARIABLE",
  "OTHER_LITERAL", "';'", "'.'", "'{'", "'}'", "':'", "'('", "')'", "','",
  "'='", "'['", "']'", "'<'", "'>'", "'|'", "'^'", "'&'", "'+'", "'-'",
  "'*'", "'/'", "'%'", "'!'", "'~'", "'?'", "'#'", "$accept",
  "grammar_file", "visibility_spec", "imports", "path", "statement",
  "blk_statement", "block", "statements", "grammar_rule",
  "return_statement", "if_statement", "while_statement", "for_statement",
  "propose_statement", "timeout_behaviour_statement", "timeout_statement",
  "switch_statement", "switch_block", "switch_cont", "switch_labels",
  "switch_label", "var_def", "assgn_exp", "nonempty_exp_list",
  "method_declaration", "class_spec", "opt_block", "args_list",
  "set_operation", "function_call", "nonempty_args_list", "type_spec",
  "parameterized_type", "type_spec_list", "exp", "ConditionalOrExpression",
  "ConditionalAndExpression", "InclusiveOrExpression",
  "ExclusiveOrExpression", "AndExpression", "EqualityExpression",
  "RelationalExpression", "AdditiveExpression", "MultiplicativeExpression",
  "CastExpression", "LogicalUnaryExpression", "UnaryExpression",
  "PostfixExpression", "PrimaryExpression", "NotJustName",
  "ComplexPrimary", "ComplexPrimaryNoParenthesis", "Literal",
  "ArrayAccess", "if_exp", "assignment", "field_access",
  "field_access_rest", "simple_nofa_exp", "new_exp", "lambda_exp",
  "dialogueact_exp", "da_token", "da_args", null
    };
  }

  /* YYRLINE[YYN] -- Source line where rule number YYN was defined.  */
  private static final short yyrline_[] = yyrline_init();
  private static final short[] yyrline_init()
  {
    return new short[]
    {
       0,   114,   114,   117,   118,   120,   121,   124,   126,   130,
     131,   132,   136,   142,   143,   147,   148,   149,   150,   151,
     152,   153,   154,   155,   156,   157,   158,   162,   163,   169,
     170,   172,   173,   177,   181,   182,   183,   184,   185,   186,
     187,   191,   192,   198,   199,   205,   207,   209,   211,   213,
     215,   218,   226,   230,   236,   242,   248,   256,   257,   261,
     262,   266,   269,   272,   275,   281,   284,   287,   290,   293,
     296,   302,   303,   304,   308,   309,   313,   316,   319,   322,
     325,   328,   331,   334,   339,   343,   344,   348,   349,   350,
     351,   359,   362,   365,   366,   367,   368,   375,   378,   384,
     385,   386,   387,   391,   392,   393,   397,   401,   402,   406,
     407,   408,   409,   413,   416,   420,   423,   427,   428,   434,
     435,   441,   442,   448,   449,   452,   458,   459,   462,   465,
     468,   474,   475,   478,   484,   485,   488,   491,   497,   498,
     503,   504,   505,   509,   512,   515,   516,   517,   521,   527,
     528,   529,   533,   534,   538,   539,   543,   544,   545,   546,
     550,   551,   552,   556,   557,   561,   589,   590,   591,   595,
     601,   602,   606,   607,   608,   612,   613,   614,   617,   620,
     623,   630,   633,   636,   639,   644,   647,   653,   654,   655,
     656,   660,   663
    };
  }


  // Report on the debug stream that the rule yyrule is going to be reduced.
  private void yy_reduce_print (int yyrule, YYStack yystack)
  {
    if (yydebug == 0)
      return;

    int yylno = yyrline_[yyrule];
    int yynrhs = yyr2_[yyrule];
    /* Print the symbols being reduced, and their result.  */
    yycdebug ("Reducing stack by rule " + (yyrule - 1)
              + " (line " + yylno + "), ");

    /* The symbols being reduced.  */
    for (int yyi = 0; yyi < yynrhs; yyi++)
      yy_symbol_print ("   $" + (yyi + 1) + " =",
                       yystos_[yystack.stateAt(yynrhs - (yyi + 1))],
                       ((yystack.valueAt (yynrhs-(yyi + 1)))),
                       yystack.locationAt (yynrhs-(yyi + 1)));
  }

  /* YYTRANSLATE(YYLEX) -- Bison symbol number corresponding to YYLEX.  */
  private static final byte yytranslate_table_[] = yytranslate_table_init();
  private static final byte[] yytranslate_table_init()
  {
    return new byte[]
    {
       0,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,    63,     2,    66,     2,    62,    57,     2,
      47,    48,    60,    58,    49,    59,    43,    61,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,    46,    42,
      53,    50,    54,    65,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,    51,     2,    52,    56,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,    44,    55,    45,    64,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     1,     2,     3,     4,
       5,     6,     7,     8,     9,    10,    11,    12,    13,    14,
      15,    16,    17,    18,    19,    20,    21,    22,    23,    24,
      25,    26,    27,    28,    29,    30,    31,    32,    33,    34,
      35,    36,    37,    38,    39,    40,    41
    };
  }

  private static final byte yytranslate_ (int t)
  {
    if (t >= 0 && t <= yyuser_token_number_max_)
      return yytranslate_table_[t];
    else
      return yyundef_token_;
  }

  private static final int yylast_ = 1269;
  private static final int yynnts_ = 65;
  private static final int yyempty_ = -2;
  private static final int yyfinal_ = 140;
  private static final int yyterror_ = 1;
  private static final int yyerrcode_ = 256;
  private static final int yyntokens_ = 67;

  private static final int yyuser_token_number_max_ = 296;
  private static final int yyundef_token_ = 2;

/* User implementation code.  */
/* Unqualified %code blocks.  */
/* "VondaGrammar.y":58  */ /* lalr1.java:1066  */

  private List<RudiTree> _statements = new LinkedList<>();

  private GrammarFile _result = new GrammarFile(_statements);

  public GrammarFile getResult() { return _result; }

/* "VondaGrammar.java":3097  */ /* lalr1.java:1066  */

}

