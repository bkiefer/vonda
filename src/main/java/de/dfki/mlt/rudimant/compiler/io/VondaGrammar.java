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
    { yyval = _statements;};
  break;
    

  case 8:
  if (yyn == 8)
    /* "VondaGrammar.y":128  */ /* lalr1.java:489  */
    { yyval = "public"; };
  break;
    

  case 9:
  if (yyn == 9)
    /* "VondaGrammar.y":129  */ /* lalr1.java:489  */
    { yyval = "protected"; };
  break;
    

  case 10:
  if (yyn == 10)
    /* "VondaGrammar.y":130  */ /* lalr1.java:489  */
    { yyval = "private"; };
  break;
    

  case 11:
  if (yyn == 11)
    /* "VondaGrammar.y":134  */ /* lalr1.java:489  */
    {
    new Import((( String )(yystack.valueAt (4-(2)))), ((List<String>)(yystack.valueAt (4-(3)))).toArray(new String[((List<String>)(yystack.valueAt (4-(3)))).size()]));
  };
  break;
    

  case 12:
  if (yyn == 12)
    /* "VondaGrammar.y":140  */ /* lalr1.java:489  */
    { yyval = new ArrayList<String>(); };
  break;
    

  case 13:
  if (yyn == 13)
    /* "VondaGrammar.y":141  */ /* lalr1.java:489  */
    { yyval = ((List<String>)(yystack.valueAt (3-(3)))); ((List<String>)(yystack.valueAt (3-(3)))).add((( String )(yystack.valueAt (3-(2))))); };
  break;
    

  case 14:
  if (yyn == 14)
    /* "VondaGrammar.y":145  */ /* lalr1.java:489  */
    { yyval = ((StatAbstractBlock)(yystack.valueAt (1-(1)))); };
  break;
    

  case 15:
  if (yyn == 15)
    /* "VondaGrammar.y":146  */ /* lalr1.java:489  */
    { yyval = new StatExpression(((RTExpression)(yystack.valueAt (2-(1))))); };
  break;
    

  case 16:
  if (yyn == 16)
    /* "VondaGrammar.y":147  */ /* lalr1.java:489  */
    { yyval = ((StatGrammarRule)(yystack.valueAt (1-(1)))); };
  break;
    

  case 17:
  if (yyn == 17)
    /* "VondaGrammar.y":148  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 18:
  if (yyn == 18)
    /* "VondaGrammar.y":149  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 19:
  if (yyn == 19)
    /* "VondaGrammar.y":150  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 20:
  if (yyn == 20)
    /* "VondaGrammar.y":151  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 21:
  if (yyn == 21)
    /* "VondaGrammar.y":152  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 22:
  if (yyn == 22)
    /* "VondaGrammar.y":153  */ /* lalr1.java:489  */
    { yyval = ((StatIf)(yystack.valueAt (1-(1)))); };
  break;
    

  case 23:
  if (yyn == 23)
    /* "VondaGrammar.y":154  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 24:
  if (yyn == 24)
    /* "VondaGrammar.y":155  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 25:
  if (yyn == 25)
    /* "VondaGrammar.y":156  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 26:
  if (yyn == 26)
    /* "VondaGrammar.y":160  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 27:
  if (yyn == 27)
    /* "VondaGrammar.y":161  */ /* lalr1.java:489  */
    { yyval = ((StatVarDef)(yystack.valueAt (1-(1)))); };
  break;
    

  case 28:
  if (yyn == 28)
    /* "VondaGrammar.y":167  */ /* lalr1.java:489  */
    { yyval = new StatAbstractBlock(((LinkedList<RTStatement>)(yystack.valueAt (3-(2)))), true); };
  break;
    

  case 29:
  if (yyn == 29)
    /* "VondaGrammar.y":168  */ /* lalr1.java:489  */
    { yyval = new StatAbstractBlock(new ArrayList<RTStatement>(), true); };
  break;
    

  case 30:
  if (yyn == 30)
    /* "VondaGrammar.y":170  */ /* lalr1.java:489  */
    { yyval = new LinkedList<RTStatement>(){{ add(((RTStatement)(yystack.valueAt (1-(1))))); }}; };
  break;
    

  case 31:
  if (yyn == 31)
    /* "VondaGrammar.y":171  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RTStatement>)(yystack.valueAt (2-(2)))); ((LinkedList<RTStatement>)(yystack.valueAt (2-(2)))).addFirst(((RTStatement)(yystack.valueAt (2-(1))))); };
  break;
    

  case 32:
  if (yyn == 32)
    /* "VondaGrammar.y":175  */ /* lalr1.java:489  */
    { yyval = new StatGrammarRule((( String )(yystack.valueAt (3-(1)))), ((StatIf)(yystack.valueAt (3-(3))))); };
  break;
    

  case 33:
  if (yyn == 33)
    /* "VondaGrammar.y":179  */ /* lalr1.java:489  */
    { yyval = new StatReturn("return"); };
  break;
    

  case 34:
  if (yyn == 34)
    /* "VondaGrammar.y":180  */ /* lalr1.java:489  */
    { yyval = new StatReturn(((RTExpression)(yystack.valueAt (3-(2))))); };
  break;
    

  case 35:
  if (yyn == 35)
    /* "VondaGrammar.y":181  */ /* lalr1.java:489  */
    { yyval = new StatReturn("break"); };
  break;
    

  case 36:
  if (yyn == 36)
    /* "VondaGrammar.y":182  */ /* lalr1.java:489  */
    { yyval = new StatReturn("break", (( String )(yystack.valueAt (3-(2))))); };
  break;
    

  case 37:
  if (yyn == 37)
    /* "VondaGrammar.y":183  */ /* lalr1.java:489  */
    { yyval = new StatReturn("cancel"); };
  break;
    

  case 38:
  if (yyn == 38)
    /* "VondaGrammar.y":184  */ /* lalr1.java:489  */
    { yyval = new StatReturn("cancel_all"); };
  break;
    

  case 39:
  if (yyn == 39)
    /* "VondaGrammar.y":185  */ /* lalr1.java:489  */
    { yyval = new StatReturn("continue"); };
  break;
    

  case 40:
  if (yyn == 40)
    /* "VondaGrammar.y":189  */ /* lalr1.java:489  */
    { yyval = new StatIf(((RTExpression)(yystack.valueAt (5-(3)))), ((RTStatement)(yystack.valueAt (5-(5)))), null); };
  break;
    

  case 41:
  if (yyn == 41)
    /* "VondaGrammar.y":190  */ /* lalr1.java:489  */
    {
    yyval = new StatIf(((RTExpression)(yystack.valueAt (7-(3)))), ((RTStatement)(yystack.valueAt (7-(5)))), ((RTStatement)(yystack.valueAt (7-(7)))));
  };
  break;
    

  case 42:
  if (yyn == 42)
    /* "VondaGrammar.y":196  */ /* lalr1.java:489  */
    { yyval = new StatWhile(((RTExpression)(yystack.valueAt (5-(3)))), ((RTStatement)(yystack.valueAt (5-(5)))), true); };
  break;
    

  case 43:
  if (yyn == 43)
    /* "VondaGrammar.y":197  */ /* lalr1.java:489  */
    {
    yyval = new StatWhile(((RTExpression)(yystack.valueAt (6-(5)))), ((RTStatement)(yystack.valueAt (6-(2)))), false);
  };
  break;
    

  case 44:
  if (yyn == 44)
    /* "VondaGrammar.y":203  */ /* lalr1.java:489  */
    {
    yyval = new StatFor1(((StatVarDef)(yystack.valueAt (8-(3)))), ((RTExpression)(yystack.valueAt (8-(4)))), ((RTExpression)(yystack.valueAt (8-(6)))), ((RTStatement)(yystack.valueAt (8-(8))))); };
  break;
    

  case 45:
  if (yyn == 45)
    /* "VondaGrammar.y":205  */ /* lalr1.java:489  */
    {
    yyval = new StatFor1(((StatVarDef)(yystack.valueAt (7-(3)))), null, ((RTExpression)(yystack.valueAt (7-(5)))), ((RTStatement)(yystack.valueAt (7-(7))))); };
  break;
    

  case 46:
  if (yyn == 46)
    /* "VondaGrammar.y":207  */ /* lalr1.java:489  */
    {
    yyval = new StatFor1(((StatVarDef)(yystack.valueAt (7-(3)))), ((RTExpression)(yystack.valueAt (7-(4)))), null, ((RTStatement)(yystack.valueAt (7-(7))))); };
  break;
    

  case 47:
  if (yyn == 47)
    /* "VondaGrammar.y":209  */ /* lalr1.java:489  */
    {
    yyval = new StatFor1(((StatVarDef)(yystack.valueAt (6-(3)))), null, null, ((RTStatement)(yystack.valueAt (6-(6))))); };
  break;
    

  case 48:
  if (yyn == 48)
    /* "VondaGrammar.y":211  */ /* lalr1.java:489  */
    {
    yyval = new StatFor1(null, ((RTExpression)(yystack.valueAt (8-(4)))), ((RTExpression)(yystack.valueAt (8-(6)))), ((RTStatement)(yystack.valueAt (8-(8))))); };
  break;
    

  case 49:
  if (yyn == 49)
    /* "VondaGrammar.y":213  */ /* lalr1.java:489  */
    {
    yyval = new StatFor2(new ExpVariable((( String )(yystack.valueAt (7-(3))))), ((RTExpression)(yystack.valueAt (7-(5)))), ((RTStatement)(yystack.valueAt (7-(7)))));
  };
  break;
    

  case 50:
  if (yyn == 50)
    /* "VondaGrammar.y":216  */ /* lalr1.java:489  */
    {
    yyval = new StatFor2(((Type)(yystack.valueAt (8-(3)))), new ExpVariable((( String )(yystack.valueAt (8-(4))))), ((RTExpression)(yystack.valueAt (8-(6)))), ((RTStatement)(yystack.valueAt (8-(8)))));
  };
  break;
    

  case 51:
  if (yyn == 51)
    /* "VondaGrammar.y":224  */ /* lalr1.java:489  */
    { yyval = new StatPropose(((RTExpression)(yystack.valueAt (5-(3)))), ((StatAbstractBlock)(yystack.valueAt (5-(5))))); };
  break;
    

  case 52:
  if (yyn == 52)
    /* "VondaGrammar.y":228  */ /* lalr1.java:489  */
    {
    yyval = new StatTimeout(null, ((RTExpression)(yystack.valueAt (5-(3)))), ((StatAbstractBlock)(yystack.valueAt (5-(5)))));
  };
  break;
    

  case 53:
  if (yyn == 53)
    /* "VondaGrammar.y":234  */ /* lalr1.java:489  */
    {
    yyval = new StatTimeout(((RTExpression)(yystack.valueAt (7-(3)))), ((RTExpression)(yystack.valueAt (7-(5)))), ((StatAbstractBlock)(yystack.valueAt (7-(7)))));
  };
  break;
    

  case 54:
  if (yyn == 54)
    /* "VondaGrammar.y":240  */ /* lalr1.java:489  */
    {
    yyval = new StatSwitch(((RTExpression)(yystack.valueAt (7-(3)))), ((StatAbstractBlock)(yystack.valueAt (7-(6)))));
  };
  break;
    

  case 55:
  if (yyn == 55)
    /* "VondaGrammar.y":246  */ /* lalr1.java:489  */
    {
    List<RTStatement> elts = ((LinkedList<RTStatement>)(yystack.valueAt (2-(1))));
    elts.addAll(((LinkedList<RTStatement>)(yystack.valueAt (2-(2)))));
    yyval = new StatAbstractBlock(elts, false);
  };
  break;
    

  case 56:
  if (yyn == 56)
    /* "VondaGrammar.y":254  */ /* lalr1.java:489  */
    { };
  break;
    

  case 57:
  if (yyn == 57)
    /* "VondaGrammar.y":255  */ /* lalr1.java:489  */
    { yyval = new ArrayList<RTExpression>(); };
  break;
    

  case 58:
  if (yyn == 58)
    /* "VondaGrammar.y":259  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RTStatement>)(yystack.valueAt (2-(2)))); ((LinkedList<RTStatement>)(yystack.valueAt (2-(2)))).addFirst(((RTStatement)(yystack.valueAt (2-(1))))); };
  break;
    

  case 59:
  if (yyn == 59)
    /* "VondaGrammar.y":260  */ /* lalr1.java:489  */
    { yyval = new LinkedList<RTStatement>(){{ add(((RTStatement)(yystack.valueAt (1-(1))))); }}; };
  break;
    

  case 60:
  if (yyn == 60)
    /* "VondaGrammar.y":264  */ /* lalr1.java:489  */
    {
    yyval = new ExpSingleValue("case " + (( String )(yystack.valueAt (3-(2)))) + ":", "label").ensureStatement();
  };
  break;
    

  case 61:
  if (yyn == 61)
    /* "VondaGrammar.y":267  */ /* lalr1.java:489  */
    {
    yyval = new ExpSingleValue("case " + (( String )(yystack.valueAt (3-(2)))) + ":", "label").ensureStatement();
  };
  break;
    

  case 62:
  if (yyn == 62)
    /* "VondaGrammar.y":270  */ /* lalr1.java:489  */
    {
    yyval = new ExpSingleValue("case " + (( String )(yystack.valueAt (3-(2)))) + ":", "label").ensureStatement();
  };
  break;
    

  case 63:
  if (yyn == 63)
    /* "VondaGrammar.y":273  */ /* lalr1.java:489  */
    {
    yyval = new ExpSingleValue("default:", "label").ensureStatement();
  };
  break;
    

  case 64:
  if (yyn == 64)
    /* "VondaGrammar.y":279  */ /* lalr1.java:489  */
    {
    yyval = new StatVarDef(true, null, (( String )(yystack.valueAt (4-(2)))), ((RTExpression)(yystack.valueAt (4-(3)))));
  };
  break;
    

  case 65:
  if (yyn == 65)
    /* "VondaGrammar.y":282  */ /* lalr1.java:489  */
    {
    yyval = new StatVarDef(false, ((Type)(yystack.valueAt (4-(1)))), (( String )(yystack.valueAt (4-(2)))), ((RTExpression)(yystack.valueAt (4-(3)))));
  };
  break;
    

  case 66:
  if (yyn == 66)
    /* "VondaGrammar.y":285  */ /* lalr1.java:489  */
    {
    yyval = new StatVarDef(true, ((Type)(yystack.valueAt (5-(2)))), (( String )(yystack.valueAt (5-(3)))), ((RTExpression)(yystack.valueAt (5-(4)))));
  };
  break;
    

  case 67:
  if (yyn == 67)
    /* "VondaGrammar.y":291  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (2-(2)))); };
  break;
    

  case 68:
  if (yyn == 68)
    /* "VondaGrammar.y":292  */ /* lalr1.java:489  */
    { yyval = new ExpListLiteral(new LinkedList<RTExpression>()); };
  break;
    

  case 69:
  if (yyn == 69)
    /* "VondaGrammar.y":293  */ /* lalr1.java:489  */
    { yyval = new ExpListLiteral(((LinkedList<RTExpression>)(yystack.valueAt (4-(3))))); };
  break;
    

  case 70:
  if (yyn == 70)
    /* "VondaGrammar.y":297  */ /* lalr1.java:489  */
    { yyval = new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (1-(1))))); }}; };
  break;
    

  case 71:
  if (yyn == 71)
    /* "VondaGrammar.y":298  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))); ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))).addFirst(((RTExpression)(yystack.valueAt (3-(1))))); };
  break;
    

  case 72:
  if (yyn == 72)
    /* "VondaGrammar.y":302  */ /* lalr1.java:489  */
    {
    yyval = new StatMethodDeclaration("public", ((Type)(yystack.valueAt (6-(2)))), ((Type)(yystack.valueAt (6-(1)))), (( String )(yystack.valueAt (6-(3)))), null, ((StatAbstractBlock)(yystack.valueAt (6-(6)))));
  };
  break;
    

  case 73:
  if (yyn == 73)
    /* "VondaGrammar.y":305  */ /* lalr1.java:489  */
    {
    yyval = new StatMethodDeclaration("public", ((Type)(yystack.valueAt (6-(2)))), ((Type)(yystack.valueAt (6-(1)))), (( String )(yystack.valueAt (6-(3)))), ((LinkedList)(yystack.valueAt (6-(5)))), ((StatAbstractBlock)(yystack.valueAt (6-(6)))));
  };
  break;
    

  case 74:
  if (yyn == 74)
    /* "VondaGrammar.y":308  */ /* lalr1.java:489  */
    {
    yyval = new StatMethodDeclaration("public", null, ((Type)(yystack.valueAt (5-(1)))), (( String )(yystack.valueAt (5-(2)))), null, ((StatAbstractBlock)(yystack.valueAt (5-(5)))));
  };
  break;
    

  case 75:
  if (yyn == 75)
    /* "VondaGrammar.y":311  */ /* lalr1.java:489  */
    {
    yyval = new StatMethodDeclaration("public", null, ((Type)(yystack.valueAt (5-(1)))), (( String )(yystack.valueAt (5-(2)))), ((LinkedList)(yystack.valueAt (5-(4)))), ((StatAbstractBlock)(yystack.valueAt (5-(5)))));
  };
  break;
    

  case 76:
  if (yyn == 76)
    /* "VondaGrammar.y":314  */ /* lalr1.java:489  */
    {
    yyval = new StatMethodDeclaration("public", ((Type)(yystack.valueAt (5-(1)))), null, (( String )(yystack.valueAt (5-(2)))), null, ((StatAbstractBlock)(yystack.valueAt (5-(5)))));
  };
  break;
    

  case 77:
  if (yyn == 77)
    /* "VondaGrammar.y":317  */ /* lalr1.java:489  */
    {
    yyval = new StatMethodDeclaration("public", ((Type)(yystack.valueAt (5-(1)))), null, (( String )(yystack.valueAt (5-(2)))), ((LinkedList)(yystack.valueAt (5-(4)))), ((StatAbstractBlock)(yystack.valueAt (5-(5)))));
  };
  break;
    

  case 78:
  if (yyn == 78)
    /* "VondaGrammar.y":320  */ /* lalr1.java:489  */
    {
    yyval = new StatMethodDeclaration("public", null, null, (( String )(yystack.valueAt (4-(1)))), null, ((StatAbstractBlock)(yystack.valueAt (4-(4)))));
  };
  break;
    

  case 79:
  if (yyn == 79)
    /* "VondaGrammar.y":323  */ /* lalr1.java:489  */
    {
    yyval = new StatMethodDeclaration("public", null, null, (( String )(yystack.valueAt (4-(1)))), ((LinkedList)(yystack.valueAt (4-(3)))), ((StatAbstractBlock)(yystack.valueAt (4-(4)))));
  };
  break;
    

  case 80:
  if (yyn == 80)
    /* "VondaGrammar.y":328  */ /* lalr1.java:489  */
    { yyval = ((Type)(yystack.valueAt (4-(2)))); };
  break;
    

  case 81:
  if (yyn == 81)
    /* "VondaGrammar.y":332  */ /* lalr1.java:489  */
    { yyval = ((StatAbstractBlock)(yystack.valueAt (1-(1)))); };
  break;
    

  case 82:
  if (yyn == 82)
    /* "VondaGrammar.y":333  */ /* lalr1.java:489  */
    { yyval = null; };
  break;
    

  case 83:
  if (yyn == 83)
    /* "VondaGrammar.y":337  */ /* lalr1.java:489  */
    { yyval = new LinkedList(){{ add((( String )(yystack.valueAt (2-(1))))); }}; };
  break;
    

  case 84:
  if (yyn == 84)
    /* "VondaGrammar.y":338  */ /* lalr1.java:489  */
    { yyval = new LinkedList(){{ add(((Type)(yystack.valueAt (3-(1))))); add((( String )(yystack.valueAt (3-(2))))); }}; };
  break;
    

  case 85:
  if (yyn == 85)
    /* "VondaGrammar.y":339  */ /* lalr1.java:489  */
    { yyval = ((LinkedList)(yystack.valueAt (3-(3)))); ((LinkedList)(yystack.valueAt (3-(3)))).addFirst((( String )(yystack.valueAt (3-(1))))); };
  break;
    

  case 86:
  if (yyn == 86)
    /* "VondaGrammar.y":340  */ /* lalr1.java:489  */
    {
    yyval = ((LinkedList)(yystack.valueAt (4-(4)))); ((LinkedList)(yystack.valueAt (4-(4)))).addFirst((( String )(yystack.valueAt (4-(2))))); ((LinkedList)(yystack.valueAt (4-(4)))).addFirst(((Type)(yystack.valueAt (4-(1)))));
  };
  break;
    

  case 87:
  if (yyn == 87)
    /* "VondaGrammar.y":348  */ /* lalr1.java:489  */
    {
    yyval = new StatSetOperation(new ExpVariable((( String )(yystack.valueAt (4-(1))))), true, ((RTExpression)(yystack.valueAt (4-(3)))));
  };
  break;
    

  case 88:
  if (yyn == 88)
    /* "VondaGrammar.y":351  */ /* lalr1.java:489  */
    {
    yyval = new StatSetOperation(new ExpVariable((( String )(yystack.valueAt (4-(1))))), false, ((RTExpression)(yystack.valueAt (4-(3)))));
  };
  break;
    

  case 89:
  if (yyn == 89)
    /* "VondaGrammar.y":354  */ /* lalr1.java:489  */
    { yyval = new StatSetOperation(((RTExpression)(yystack.valueAt (4-(1)))), true, ((RTExpression)(yystack.valueAt (4-(3))))); };
  break;
    

  case 90:
  if (yyn == 90)
    /* "VondaGrammar.y":355  */ /* lalr1.java:489  */
    { yyval = new StatSetOperation(((RTExpression)(yystack.valueAt (4-(1)))), false, ((RTExpression)(yystack.valueAt (4-(3))))); };
  break;
    

  case 91:
  if (yyn == 91)
    /* "VondaGrammar.y":356  */ /* lalr1.java:489  */
    { yyval = new StatSetOperation(((RTExpression)(yystack.valueAt (4-(1)))), true, ((RTExpression)(yystack.valueAt (4-(3))))); };
  break;
    

  case 92:
  if (yyn == 92)
    /* "VondaGrammar.y":357  */ /* lalr1.java:489  */
    { yyval = new StatSetOperation(((RTExpression)(yystack.valueAt (4-(1)))), false, ((RTExpression)(yystack.valueAt (4-(3))))); };
  break;
    

  case 93:
  if (yyn == 93)
    /* "VondaGrammar.y":364  */ /* lalr1.java:489  */
    {
    yyval = new ExpFuncCall((( String )(yystack.valueAt (3-(1)))), new LinkedList<RTExpression>(), false);
  };
  break;
    

  case 94:
  if (yyn == 94)
    /* "VondaGrammar.y":367  */ /* lalr1.java:489  */
    {
    yyval = new ExpFuncCall((( String )(yystack.valueAt (4-(1)))), ((LinkedList<RTExpression>)(yystack.valueAt (4-(3)))), false);
  };
  break;
    

  case 95:
  if (yyn == 95)
    /* "VondaGrammar.y":373  */ /* lalr1.java:489  */
    { yyval = new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (1-(1))))); }}; };
  break;
    

  case 96:
  if (yyn == 96)
    /* "VondaGrammar.y":374  */ /* lalr1.java:489  */
    { yyval = new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (1-(1))))); }}; };
  break;
    

  case 97:
  if (yyn == 97)
    /* "VondaGrammar.y":375  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))); ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))).addFirst(((RTExpression)(yystack.valueAt (3-(1))))); };
  break;
    

  case 98:
  if (yyn == 98)
    /* "VondaGrammar.y":376  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))); ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))).addFirst(((RTExpression)(yystack.valueAt (3-(1))))); };
  break;
    

  case 99:
  if (yyn == 99)
    /* "VondaGrammar.y":380  */ /* lalr1.java:489  */
    { yyval = new Type("Array", new Type((( String )(yystack.valueAt (3-(1)))))); };
  break;
    

  case 100:
  if (yyn == 100)
    /* "VondaGrammar.y":381  */ /* lalr1.java:489  */
    { yyval = new Type((( String )(yystack.valueAt (1-(1))))); };
  break;
    

  case 101:
  if (yyn == 101)
    /* "VondaGrammar.y":382  */ /* lalr1.java:489  */
    { yyval = ((Type)(yystack.valueAt (1-(1)))); };
  break;
    

  case 102:
  if (yyn == 102)
    /* "VondaGrammar.y":386  */ /* lalr1.java:489  */
    { yyval = new Type((( String )(yystack.valueAt (4-(1)))), ((LinkedList<Type>)(yystack.valueAt (4-(3)))).toArray(new Type[((LinkedList<Type>)(yystack.valueAt (4-(3)))).size()])); };
  break;
    

  case 103:
  if (yyn == 103)
    /* "VondaGrammar.y":390  */ /* lalr1.java:489  */
    { yyval = new LinkedList<Type>(){{ add(((Type)(yystack.valueAt (1-(1))))); }}; };
  break;
    

  case 104:
  if (yyn == 104)
    /* "VondaGrammar.y":391  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<Type>)(yystack.valueAt (3-(3)))); ((LinkedList<Type>)(yystack.valueAt (3-(3)))).addFirst(((Type)(yystack.valueAt (3-(1))))); };
  break;
    

  case 105:
  if (yyn == 105)
    /* "VondaGrammar.y":395  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 106:
  if (yyn == 106)
    /* "VondaGrammar.y":396  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 107:
  if (yyn == 107)
    /* "VondaGrammar.y":397  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 108:
  if (yyn == 108)
    /* "VondaGrammar.y":398  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 109:
  if (yyn == 109)
    /* "VondaGrammar.y":402  */ /* lalr1.java:489  */
    {
    yyval = new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "||");
  };
  break;
    

  case 110:
  if (yyn == 110)
    /* "VondaGrammar.y":405  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 111:
  if (yyn == 111)
    /* "VondaGrammar.y":409  */ /* lalr1.java:489  */
    {
    yyval = new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "&&");
  };
  break;
    

  case 112:
  if (yyn == 112)
    /* "VondaGrammar.y":412  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 113:
  if (yyn == 113)
    /* "VondaGrammar.y":416  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 114:
  if (yyn == 114)
    /* "VondaGrammar.y":417  */ /* lalr1.java:489  */
    {
    yyval = new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "|");
  };
  break;
    

  case 115:
  if (yyn == 115)
    /* "VondaGrammar.y":423  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 116:
  if (yyn == 116)
    /* "VondaGrammar.y":424  */ /* lalr1.java:489  */
    {
    yyval = new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "^");
  };
  break;
    

  case 117:
  if (yyn == 117)
    /* "VondaGrammar.y":430  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 118:
  if (yyn == 118)
    /* "VondaGrammar.y":431  */ /* lalr1.java:489  */
    {
    yyval = new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "&");
  };
  break;
    

  case 119:
  if (yyn == 119)
    /* "VondaGrammar.y":437  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 120:
  if (yyn == 120)
    /* "VondaGrammar.y":438  */ /* lalr1.java:489  */
    {
    yyval = new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "==");
  };
  break;
    

  case 121:
  if (yyn == 121)
    /* "VondaGrammar.y":441  */ /* lalr1.java:489  */
    {
    yyval = new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "!=");
  };
  break;
    

  case 122:
  if (yyn == 122)
    /* "VondaGrammar.y":447  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 123:
  if (yyn == 123)
    /* "VondaGrammar.y":448  */ /* lalr1.java:489  */
    {
    yyval = new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "<");
  };
  break;
    

  case 124:
  if (yyn == 124)
    /* "VondaGrammar.y":451  */ /* lalr1.java:489  */
    {
    yyval = new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), ">");
  };
  break;
    

  case 125:
  if (yyn == 125)
    /* "VondaGrammar.y":454  */ /* lalr1.java:489  */
    {
    yyval = new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), ">=");
  };
  break;
    

  case 126:
  if (yyn == 126)
    /* "VondaGrammar.y":457  */ /* lalr1.java:489  */
    {
    yyval = new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "<=");
  };
  break;
    

  case 127:
  if (yyn == 127)
    /* "VondaGrammar.y":463  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 128:
  if (yyn == 128)
    /* "VondaGrammar.y":464  */ /* lalr1.java:489  */
    {
    yyval = new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "+");
  };
  break;
    

  case 129:
  if (yyn == 129)
    /* "VondaGrammar.y":467  */ /* lalr1.java:489  */
    {
    yyval = new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "-");
  };
  break;
    

  case 130:
  if (yyn == 130)
    /* "VondaGrammar.y":473  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 131:
  if (yyn == 131)
    /* "VondaGrammar.y":474  */ /* lalr1.java:489  */
    {
    yyval = new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "*");
  };
  break;
    

  case 132:
  if (yyn == 132)
    /* "VondaGrammar.y":477  */ /* lalr1.java:489  */
    {
    yyval = new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "/");
  };
  break;
    

  case 133:
  if (yyn == 133)
    /* "VondaGrammar.y":480  */ /* lalr1.java:489  */
    {
    yyval = new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "%");
  };
  break;
    

  case 134:
  if (yyn == 134)
    /* "VondaGrammar.y":486  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 135:
  if (yyn == 135)
    /* "VondaGrammar.y":487  */ /* lalr1.java:489  */
    { new ExpCast(((Type)(yystack.valueAt (4-(2)))), ((RTExpression)(yystack.valueAt (4-(4))))); };
  break;
    

  case 136:
  if (yyn == 136)
    /* "VondaGrammar.y":492  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 137:
  if (yyn == 137)
    /* "VondaGrammar.y":493  */ /* lalr1.java:489  */
    { yyval = new ExpBoolean(((RTExpression)(yystack.valueAt (2-(2)))), null, "!"); };
  break;
    

  case 138:
  if (yyn == 138)
    /* "VondaGrammar.y":494  */ /* lalr1.java:489  */
    { yyval = new ExpArithmetic(((RTExpression)(yystack.valueAt (2-(2)))), null, "~"); };
  break;
    

  case 139:
  if (yyn == 139)
    /* "VondaGrammar.y":498  */ /* lalr1.java:489  */
    {
    yyval = new ExpArithmetic(((RTExpression)(yystack.valueAt (2-(2)))), new ExpSingleValue("1", "int"), "+");
  };
  break;
    

  case 140:
  if (yyn == 140)
    /* "VondaGrammar.y":501  */ /* lalr1.java:489  */
    {
    yyval = new ExpArithmetic(((RTExpression)(yystack.valueAt (2-(2)))), new ExpSingleValue("1", "int"), "-");
  };
  break;
    

  case 141:
  if (yyn == 141)
    /* "VondaGrammar.y":504  */ /* lalr1.java:489  */
    { yyval = new ExpArithmetic(((RTExpression)(yystack.valueAt (2-(2)))), null, "+"); };
  break;
    

  case 142:
  if (yyn == 142)
    /* "VondaGrammar.y":505  */ /* lalr1.java:489  */
    { yyval = new ExpArithmetic(((RTExpression)(yystack.valueAt (2-(2)))), null, "-"); };
  break;
    

  case 143:
  if (yyn == 143)
    /* "VondaGrammar.y":506  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 144:
  if (yyn == 144)
    /* "VondaGrammar.y":510  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 145:
  if (yyn == 145)
    /* "VondaGrammar.y":516  */ /* lalr1.java:489  */
    { yyval = new ExpSingleValue("null", "null"); };
  break;
    

  case 146:
  if (yyn == 146)
    /* "VondaGrammar.y":517  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 147:
  if (yyn == 147)
    /* "VondaGrammar.y":518  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 148:
  if (yyn == 148)
    /* "VondaGrammar.y":522  */ /* lalr1.java:489  */
    { yyval = new ExpVariable((( String )(yystack.valueAt (1-(1))))); };
  break;
    

  case 149:
  if (yyn == 149)
    /* "VondaGrammar.y":523  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 150:
  if (yyn == 150)
    /* "VondaGrammar.y":527  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (3-(2)))); };
  break;
    

  case 151:
  if (yyn == 151)
    /* "VondaGrammar.y":528  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 152:
  if (yyn == 152)
    /* "VondaGrammar.y":532  */ /* lalr1.java:489  */
    { yyval = ((ExpSingleValue)(yystack.valueAt (1-(1)))); };
  break;
    

  case 153:
  if (yyn == 153)
    /* "VondaGrammar.y":533  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 154:
  if (yyn == 154)
    /* "VondaGrammar.y":534  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 155:
  if (yyn == 155)
    /* "VondaGrammar.y":535  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 156:
  if (yyn == 156)
    /* "VondaGrammar.y":539  */ /* lalr1.java:489  */
    { yyval = new ExpSingleValue((( String )(yystack.valueAt (1-(1)))), "String"); };
  break;
    

  case 157:
  if (yyn == 157)
    /* "VondaGrammar.y":540  */ /* lalr1.java:489  */
    { yyval = new ExpSingleValue((( String )(yystack.valueAt (1-(1)))), "int"); };
  break;
    

  case 158:
  if (yyn == 158)
    /* "VondaGrammar.y":541  */ /* lalr1.java:489  */
    { yyval = (( ExpSingleValue )(yystack.valueAt (1-(1)))); };
  break;
    

  case 159:
  if (yyn == 159)
    /* "VondaGrammar.y":545  */ /* lalr1.java:489  */
    { yyval = new ExpArrayAccess(new ExpVariable((( String )(yystack.valueAt (4-(1))))), ((RTExpression)(yystack.valueAt (4-(3))))); };
  break;
    

  case 160:
  if (yyn == 160)
    /* "VondaGrammar.y":546  */ /* lalr1.java:489  */
    { yyval = new ExpArrayAccess(((RTExpression)(yystack.valueAt (4-(1)))), ((RTExpression)(yystack.valueAt (4-(3))))); };
  break;
    

  case 161:
  if (yyn == 161)
    /* "VondaGrammar.y":550  */ /* lalr1.java:489  */
    { yyval = new ExpConditional(((RTExpression)(yystack.valueAt (5-(1)))), ((RTExpression)(yystack.valueAt (5-(3)))), ((RTExpression)(yystack.valueAt (5-(5))))); };
  break;
    

  case 162:
  if (yyn == 162)
    /* "VondaGrammar.y":578  */ /* lalr1.java:489  */
    { yyval = new ExpAssignment(new ExpVariable((( String )(yystack.valueAt (2-(1))))), ((RTExpression)(yystack.valueAt (2-(2))))); };
  break;
    

  case 163:
  if (yyn == 163)
    /* "VondaGrammar.y":579  */ /* lalr1.java:489  */
    { yyval = new ExpAssignment(((RTExpression)(yystack.valueAt (2-(1)))), ((RTExpression)(yystack.valueAt (2-(2))))); };
  break;
    

  case 164:
  if (yyn == 164)
    /* "VondaGrammar.y":580  */ /* lalr1.java:489  */
    { yyval = new ExpAssignment(((RTExpression)(yystack.valueAt (2-(1)))), ((RTExpression)(yystack.valueAt (2-(2))))); };
  break;
    

  case 165:
  if (yyn == 165)
    /* "VondaGrammar.y":584  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RTExpression>)(yystack.valueAt (2-(2)))); ((LinkedList<RTExpression>)(yystack.valueAt (2-(2)))).addFirst(((RTExpression)(yystack.valueAt (2-(1))))); };
  break;
    

  case 166:
  if (yyn == 166)
    /* "VondaGrammar.y":588  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))); ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))).addFirst(((RTExpression)(yystack.valueAt (3-(2))))); };
  break;
    

  case 167:
  if (yyn == 167)
    /* "VondaGrammar.y":589  */ /* lalr1.java:489  */
    { yyval = new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (2-(2))))); }}; };
  break;
    

  case 168:
  if (yyn == 168)
    /* "VondaGrammar.y":593  */ /* lalr1.java:489  */
    { yyval = (( String )(yystack.valueAt (1-(1)))); };
  break;
    

  case 169:
  if (yyn == 169)
    /* "VondaGrammar.y":594  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 170:
  if (yyn == 170)
    /* "VondaGrammar.y":595  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (3-(2)))); };
  break;
    

  case 171:
  if (yyn == 171)
    /* "VondaGrammar.y":599  */ /* lalr1.java:489  */
    { yyval = new ExpNew(new Type((( String )(yystack.valueAt (2-(2)))))); };
  break;
    

  case 172:
  if (yyn == 172)
    /* "VondaGrammar.y":600  */ /* lalr1.java:489  */
    { yyval = new ExpNew(new Type((( String )(yystack.valueAt (4-(2))))), new LinkedList<>()); };
  break;
    

  case 173:
  if (yyn == 173)
    /* "VondaGrammar.y":601  */ /* lalr1.java:489  */
    {
    yyval = new ExpNew(new Type((( String )(yystack.valueAt (5-(2))))), ((LinkedList<RTExpression>)(yystack.valueAt (5-(4)))));
  };
  break;
    

  case 174:
  if (yyn == 174)
    /* "VondaGrammar.y":604  */ /* lalr1.java:489  */
    {
    yyval = new ExpNew(((Type)(yystack.valueAt (4-(2)))), new LinkedList<>());
  };
  break;
    

  case 175:
  if (yyn == 175)
    /* "VondaGrammar.y":607  */ /* lalr1.java:489  */
    {
    yyval = new ExpNew(((Type)(yystack.valueAt (5-(2)))), ((LinkedList<RTExpression>)(yystack.valueAt (5-(4)))));
  };
  break;
    

  case 176:
  if (yyn == 176)
    /* "VondaGrammar.y":610  */ /* lalr1.java:489  */
    {
    yyval = new ExpNew(new Type("Array", new Type((( String )(yystack.valueAt (5-(2)))))),
                    new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (5-(4))))); }});
  };
  break;
    

  case 177:
  if (yyn == 177)
    /* "VondaGrammar.y":617  */ /* lalr1.java:489  */
    {
    yyval = new ExpLambda(((LinkedList)(yystack.valueAt (4-(2)))), new StatExpression(((RTExpression)(yystack.valueAt (4-(4))))));
  };
  break;
    

  case 178:
  if (yyn == 178)
    /* "VondaGrammar.y":620  */ /* lalr1.java:489  */
    {
    yyval = new ExpLambda(((LinkedList)(yystack.valueAt (4-(2)))), ((StatAbstractBlock)(yystack.valueAt (4-(4)))));
  };
  break;
    

  case 179:
  if (yyn == 179)
    /* "VondaGrammar.y":623  */ /* lalr1.java:489  */
    {
    yyval = new ExpLambda(new LinkedList<>(), new StatExpression(((RTExpression)(yystack.valueAt (4-(4))))));
  };
  break;
    

  case 180:
  if (yyn == 180)
    /* "VondaGrammar.y":626  */ /* lalr1.java:489  */
    { yyval = new ExpLambda(new LinkedList<>(), ((StatAbstractBlock)(yystack.valueAt (4-(4))))); };
  break;
    

  case 181:
  if (yyn == 181)
    /* "VondaGrammar.y":631  */ /* lalr1.java:489  */
    {
    yyval = new ExpDialogueAct(((RTExpression)(yystack.valueAt (5-(2)))), ((RTExpression)(yystack.valueAt (5-(4)))), new LinkedList<RTExpression>());
  };
  break;
    

  case 182:
  if (yyn == 182)
    /* "VondaGrammar.y":634  */ /* lalr1.java:489  */
    {
    yyval = new ExpDialogueAct(((RTExpression)(yystack.valueAt (6-(2)))), ((RTExpression)(yystack.valueAt (6-(4)))), ((LinkedList<RTExpression>)(yystack.valueAt (6-(5)))));
  };
  break;
    

  case 183:
  if (yyn == 183)
    /* "VondaGrammar.y":640  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (2-(2)))); };
  break;
    

  case 184:
  if (yyn == 184)
    /* "VondaGrammar.y":641  */ /* lalr1.java:489  */
    { yyval = (( String )(yystack.valueAt (1-(1)))); };
  break;
    

  case 185:
  if (yyn == 185)
    /* "VondaGrammar.y":642  */ /* lalr1.java:489  */
    { yyval = (( String )(yystack.valueAt (1-(1)))); };
  break;
    

  case 186:
  if (yyn == 186)
    /* "VondaGrammar.y":643  */ /* lalr1.java:489  */
    { yyval = (( String )(yystack.valueAt (1-(1)))); };
  break;
    

  case 187:
  if (yyn == 187)
    /* "VondaGrammar.y":647  */ /* lalr1.java:489  */
    {
    yyval = ((LinkedList<RTExpression>)(yystack.valueAt (5-(5)))); ((LinkedList<RTExpression>)(yystack.valueAt (5-(5)))).addFirst(((RTExpression)(yystack.valueAt (5-(4))))); ((LinkedList<RTExpression>)(yystack.valueAt (5-(5)))).addFirst(((RTExpression)(yystack.valueAt (5-(2)))));
  };
  break;
    

  case 188:
  if (yyn == 188)
    /* "VondaGrammar.y":650  */ /* lalr1.java:489  */
    { new LinkedList<RTExpression>(); };
  break;
    


/* "VondaGrammar.java":1942  */ /* lalr1.java:489  */
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

  private static final short yypact_ninf_ = -357;
  private static final short yytable_ninf_ = -101;

  /* YYPACT[STATE-NUM] -- Index in YYTABLE of the portion describing
   STATE-NUM.  */
  private static final short yypact_[] = yypact_init();
  private static final short[] yypact_init()
  {
    return new short[]
    {
     523,   253,    85,    94,   104,   780,    13,   137,   197,   219,
    -357,  -357,   215,  -357,  -357,    65,   228,   297,   322,   328,
     208,   208,  -357,  -357,   182,  -357,   652,  1129,   310,  1188,
    1188,   208,   208,   135,   380,    -5,   523,   523,  -357,  -357,
    -357,  -357,  -357,  -357,  -357,  -357,  -357,  -357,   523,   361,
    -357,  -357,   364,  -357,   -14,   300,   396,   372,   379,   382,
     -11,    18,    30,   335,  -357,  -357,  -357,  -357,  -357,   398,
     393,  -357,  -357,   180,  -357,  -357,   186,  -357,  -357,   403,
    -357,  -357,  -357,  -357,    87,   421,    16,  1142,   404,   226,
     401,  1142,   331,  -357,    -8,   402,   402,  1142,  1142,  1142,
    1142,    52,  1142,  -357,  -357,  -357,  -357,  1142,  1142,   438,
     268,   564,   793,   310,  -357,   414,   296,  -357,  -357,   716,
     411,  -357,   417,   326,   410,   -26,   306,   416,  -357,  -357,
    -357,  -357,  -357,  -357,  -357,  1142,   413,  -357,   267,   523,
     523,   422,  -357,  -357,  -357,   288,   423,   418,  -357,  1142,
    1188,  1188,  1188,  1188,  1188,  1188,  1188,  1188,  1188,  1188,
    1188,  1188,  1188,  1188,  1188,  1188,   252,  -357,  1142,  1142,
    1142,  -357,  1142,  1142,  -357,  -357,   826,  1142,   419,   302,
    1142,   860,   424,    -4,   428,   427,   893,  1142,   927,    46,
    -357,    48,    25,    70,    82,     1,    17,  -357,   238,   961,
     429,   429,   426,   431,    83,   412,   995,   407,  -357,   118,
     430,   435,   371,   436,  -357,  -357,   402,  1188,  -357,   425,
     433,   407,   135,   134,  -357,  -357,    75,   242,   434,   254,
     -32,   396,   372,   379,   382,   -11,    18,    18,    30,    30,
      30,    30,   335,   335,  -357,  -357,  -357,   437,  1142,  -357,
     398,   127,    27,    28,    33,    43,  -357,  1142,  1142,    44,
    1028,    45,   200,   780,   404,  -357,  -357,   444,   169,   174,
    -357,   445,   429,   439,  1142,   429,   780,  -357,  -357,  -357,
     440,   449,   452,   258,  -357,  -357,  -357,     6,  1175,  1175,
    -357,   451,  -357,   310,  -357,   455,   402,   458,  -357,  -357,
     145,   303,   429,   341,   341,   273,   341,   341,  1142,   123,
    -357,  -357,  -357,  -357,  -357,  -357,   173,   177,  1142,   780,
     192,  1062,  1142,   472,  -357,  -357,  1142,  -357,  -357,  -357,
     432,   193,  -357,  -357,  -357,  1096,  1096,  -357,   440,  -357,
    -357,  -357,  -357,  -357,   459,  -357,  -357,   135,   454,  -357,
    -357,  -357,  -357,   341,   341,  -357,  -357,   407,  -357,  -357,
     780,   203,  -357,   780,   780,   204,   205,   780,  -357,   285,
     457,   462,   716,   432,   429,  -357,   407,  -357,   407,  -357,
    -357,   460,  -357,  -357,  -357,  -357,   780,  -357,  -357,   780,
     780,  -357,   463,   465,   466,  -357,  -357,   432,  -357,  -357,
    -357,   135,  -357,  -357,  -357,  -357,  -357,  -357,   716,   467,
    -357,  -357
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
       7,     0,     0,     0,     0,     0,     0,     0,     0,     0,
     145,    10,     0,     9,     8,     0,     0,     0,     0,     0,
       0,     0,   156,   157,   148,   158,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     7,     7,    14,    16,
      18,    22,    23,    24,    19,    21,    20,    25,     7,     0,
      17,   155,     0,   101,     0,   107,   110,   112,   113,   115,
     117,   119,   122,   127,   130,   143,   134,   136,   144,   146,
     147,   151,   152,   153,   105,   106,   154,   149,   108,     0,
      35,    37,    38,    39,   148,     0,     0,     0,    12,   171,
       0,     0,   148,    33,     0,   153,   154,     0,     0,     0,
       0,   148,     0,   140,   153,   154,   139,     0,     0,     0,
       0,     0,     0,     0,   162,     0,   148,    29,    26,    30,
       0,    27,     0,   148,     0,     0,   100,     0,   141,   142,
     137,   138,   185,   186,   184,     0,     0,     1,   100,     7,
       7,     0,     5,     4,     3,   100,     0,     0,    15,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,   165,     0,     0,
       0,   164,     0,     0,   163,    36,     0,     0,     0,   100,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
      34,     0,     0,     0,     0,     0,     0,    32,   148,     0,
      93,     0,     0,     0,    95,    96,     0,    67,    99,     0,
     103,     0,   100,     0,    31,    28,     0,     0,   150,     0,
       0,   183,     0,     0,     6,     2,     0,     0,     0,     0,
       0,   109,   111,   114,   116,   118,   120,   121,   125,   126,
     123,   124,   128,   129,   131,   132,   133,   168,     0,   169,
     167,     0,     0,     0,     0,     0,    93,     0,     0,     0,
       0,     0,     0,     0,    12,    11,   172,     0,    70,     0,
     174,     0,     0,     0,     0,     0,     0,    88,    87,    83,
       0,     0,     0,     0,    78,    79,    94,     0,     0,     0,
      68,     0,   159,     0,   102,     0,     0,     0,   135,    80,
       0,   100,     0,     0,     0,     0,     0,     0,     0,     0,
     166,   160,    90,    89,    92,    91,     0,     0,     0,     0,
       0,     0,     0,    40,    13,   173,     0,   176,   175,    51,
       0,     0,    52,    42,    85,     0,     0,    84,     0,    97,
      98,    69,   104,    64,     0,    65,   181,     0,     0,    82,
      81,    74,    75,     0,     0,    76,    77,   161,   170,    43,
       0,     0,    47,     0,     0,     0,     0,     0,    71,     0,
       0,     0,    57,    59,     0,   180,   179,   178,   177,    86,
      66,     0,   182,    72,    73,    49,     0,    45,    46,     0,
       0,    41,     0,     0,     0,    63,    54,     0,    55,    58,
      53,     0,    48,    44,    50,    60,    61,    62,    57,   188,
      56,   187
    };
  }

/* YYPGOTO[NTERM-NUM].  */
  private static final short yypgoto_[] = yypgoto_init();
  private static final short[] yypgoto_init()
  {
    return new short[]
    {
    -357,   -25,  -357,  -357,   244,    -2,  -357,    96,   -22,  -357,
    -357,   405,  -357,  -357,  -357,  -357,  -357,  -357,  -357,   107,
    -356,  -357,   -33,   -31,  -181,   483,  -357,  -291,  -189,  -357,
     353,    49,   141,   511,   229,   318,  -357,   373,   370,   377,
     378,   397,   257,   234,   281,   -21,  -357,   280,  -357,  -357,
    -357,  -357,  -357,  -357,     0,  -357,  -357,    47,   275,  -357,
    -357,  -357,  -357,  -221,   124
    };
  }

/* YYDEFGOTO[NTERM-NUM].  */
  private static final short yydefgoto_[] = yydefgoto_init();
  private static final short[] yydefgoto_init()
  {
    return new short[]
    {
      -1,    34,    35,    36,   185,    37,   119,    38,   397,    39,
      40,    41,    42,    43,    44,    45,    46,    47,   371,   398,
     372,   373,   121,   114,   267,    48,    49,   351,   201,    50,
      51,   202,   203,    53,   211,    54,    55,    56,    57,    58,
      59,    60,    61,    62,    63,    64,    65,    66,    67,    68,
      69,    70,    71,    72,    95,    74,    75,    96,   167,   250,
      77,   205,    78,   136,   348
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
      73,   300,   139,    85,   120,    73,   115,   271,   128,   129,
     282,   142,   143,   352,   308,   355,   356,   399,   155,   156,
     104,   104,   218,   144,   118,   291,    73,   115,   148,   104,
     104,   104,   104,   149,   190,   138,    73,    73,   304,   149,
     307,   408,   171,   277,   263,   174,    28,    76,    73,   157,
     158,   149,    76,   181,   337,   338,   179,   149,   180,   278,
      86,   149,   383,   384,   171,   174,   149,   105,   105,   312,
     313,   159,   160,    76,   274,   314,   105,   105,   105,   105,
       9,    10,   149,    76,    76,   315,   318,   321,   161,   162,
     149,   334,   149,   149,   272,    76,   273,   214,   149,   176,
      20,    21,    22,   177,    23,    92,    25,    93,   149,   149,
     149,   149,    27,   149,   224,   225,   354,   118,   275,    73,
     107,   108,   229,    29,    30,   111,   381,    81,    31,    32,
     276,    33,   288,   109,   176,   149,    82,   111,   177,    73,
      73,    52,   244,   245,   246,   368,    83,   149,   149,   379,
     104,   104,   104,   104,   104,   104,   104,   104,   104,   104,
     104,   104,   104,   104,   104,   104,    76,   122,   124,   127,
     292,   358,   132,   133,   301,   134,   141,    52,    52,   311,
     409,   295,   302,   149,    87,   297,    76,    76,   149,    52,
     146,   135,   149,   346,   347,   297,   298,   105,   105,   105,
     105,   105,   105,   105,   105,   105,   105,   105,   105,   105,
     105,   105,   105,   169,   170,   107,   108,   104,   326,   172,
     173,   359,  -100,     9,    10,   360,   327,   182,   109,   110,
     111,   297,   111,   112,   149,   113,   111,    88,   149,   149,
     363,   374,   149,    20,    21,    22,   322,    23,   101,    25,
     111,   386,   389,   390,   210,   102,   213,   149,   149,    89,
     122,   323,    91,    73,   105,   344,    29,    30,   149,   149,
     149,    31,    32,   186,   333,    97,    73,   187,  -100,   113,
      52,    52,   301,     9,    10,   176,   279,   280,   111,   112,
     303,   113,   247,    79,   301,    80,   284,   285,   287,   248,
     103,   106,   306,    20,    21,    22,   217,    23,   198,    25,
      76,   130,   131,   301,   223,   199,   200,   362,   219,    73,
     113,   353,   392,    76,   393,   394,    29,    30,   150,   107,
     108,    31,    32,    94,    33,   227,  -100,   339,   340,   219,
     283,   113,   109,   176,    98,   125,   111,   112,   258,   113,
     126,   279,   280,   219,   219,   113,   113,   219,   385,   113,
      73,   387,   388,    73,    73,   391,    76,    73,   329,    99,
     118,   332,    73,   176,  -100,   100,   111,   112,   176,   113,
     137,   111,   177,   349,   402,    26,    73,   403,   404,    73,
      73,   238,   239,   240,   241,   163,   164,   165,   284,   350,
     350,   145,   350,   350,   147,   183,   118,    76,    73,   189,
      76,    76,   236,   237,    76,   191,   192,   193,   194,    76,
     125,   111,   219,   151,   113,   195,   196,   152,   204,   207,
     209,   375,   377,    76,   210,   153,    76,    76,   369,   154,
     370,   166,   242,   243,   168,   175,   178,   184,   188,   350,
     350,     7,   111,   221,   212,    76,   215,   216,   217,   220,
     222,   289,   226,   228,   262,   229,   257,   230,   264,   265,
     400,   287,   149,    26,   286,   335,   296,   208,   336,   293,
     301,   305,   367,   330,   176,   299,   251,   252,   253,   294,
     254,   255,   325,   328,   204,   209,   341,   343,   259,   261,
     345,   380,   382,   395,   268,   269,   268,   396,   324,   405,
     401,   406,   407,   122,   197,   410,   347,   125,   140,   249,
      90,   232,   342,   231,   268,   310,     1,     2,     3,   233,
       4,   234,     5,   411,     0,     6,     7,     8,     9,    10,
      11,    12,    13,    14,    15,    16,    17,    18,    19,   122,
       0,   235,     0,     0,     0,     0,     0,     0,    20,    21,
      22,     0,    23,    24,    25,     0,   309,    26,     0,     0,
      27,     0,     0,     0,    28,   316,   317,     0,   320,     9,
      10,    29,    30,     0,     0,     0,    31,    32,     0,    33,
       0,     0,   331,     0,     0,     0,     0,     0,     0,    20,
      21,    22,     0,    23,    92,    25,   204,   204,   206,     0,
       0,    27,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,    29,    30,     0,     0,   357,    31,    32,     0,
      33,     0,     0,     0,     0,     0,   361,     0,     0,   365,
     366,     0,     0,     0,   268,     0,     0,     0,     0,     0,
       0,     0,     0,   376,   378,     1,     2,     3,     0,     4,
       0,     5,     0,   115,     6,     7,     0,     9,    10,     0,
      12,     0,     0,    15,    16,    17,    18,    19,     0,     0,
       0,     0,     0,     0,     0,     0,     0,    20,    21,    22,
       0,    23,   116,    25,     0,     0,    26,   117,     0,    27,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
      29,    30,     0,     0,     0,    31,    32,     0,    33,     1,
       2,     3,     0,     4,     0,     5,     0,   115,     6,     7,
       0,     9,    10,     0,    12,     0,     0,    15,    16,    17,
      18,    19,     0,     0,     0,     0,     0,     0,     0,     0,
       0,    20,    21,    22,     0,    23,   116,    25,     0,     0,
      26,     0,     0,    27,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,    29,    30,     0,     0,     0,    31,
      32,     0,    33,     1,     2,     3,     0,     4,     0,     5,
       0,     0,     6,     7,     0,     9,    10,     0,    12,     0,
       0,    15,    16,    17,    18,    19,     0,     0,     9,    10,
       0,     0,     0,     0,     0,    20,    21,    22,     0,    23,
      84,    25,     0,     0,    26,     0,     0,    27,    20,    21,
      22,     0,    23,    92,    25,     0,     0,     0,    29,    30,
      27,     9,    10,    31,    32,   208,    33,     0,     0,     0,
       0,    29,    30,     0,     0,     0,    31,    32,     0,    33,
       0,    20,    21,    22,     0,    23,    92,    25,     0,     0,
       0,     0,     0,   199,   256,     9,    10,     0,     0,     0,
       0,     0,     0,     0,    29,    30,     0,     0,     0,    31,
      32,     0,    33,     0,     0,    20,    21,    22,     0,    23,
      92,    25,   260,     0,     0,     0,     0,    27,     9,    10,
       0,     0,     0,     0,     0,     0,     0,     0,    29,    30,
       0,     0,     0,    31,    32,     0,    33,     0,    20,    21,
      22,     0,    23,    92,    25,     0,     0,     0,     0,     0,
      27,   266,     9,    10,     0,     0,     0,     0,     0,     0,
       0,    29,    30,     0,     0,     0,    31,    32,     0,    33,
       0,     0,    20,    21,    22,     0,    23,    92,    25,     0,
       0,     0,     0,     0,    27,   270,     9,    10,     0,     0,
       0,     0,     0,     0,     0,    29,    30,     0,     0,     0,
      31,    32,     0,    33,     0,     0,    20,    21,    22,     0,
      23,   198,    25,     0,     0,     0,     0,     0,    27,   281,
       9,    10,     0,     0,     0,     0,     0,     0,     0,    29,
      30,     0,     0,     0,    31,    32,     0,    33,     0,     0,
      20,    21,    22,     0,    23,    92,    25,     0,     0,     0,
     290,     0,    27,     9,    10,     0,     0,     0,     0,     0,
       0,     0,     0,    29,    30,     0,     0,     0,    31,    32,
       0,    33,     0,    20,    21,    22,     0,    23,    92,    25,
       0,     0,     0,     0,     0,    27,   319,     9,    10,     0,
       0,     0,     0,     0,     0,     0,    29,    30,     0,     0,
       0,    31,    32,     0,    33,     0,     0,    20,    21,    22,
       0,    23,    92,    25,     0,     0,     0,     0,     0,    27,
     364,     9,    10,     0,     0,     0,     0,     0,     0,     0,
      29,    30,     0,     0,     0,    31,    32,     0,    33,     0,
       0,    20,    21,    22,     0,    23,    92,    25,     0,     0,
      26,     0,     0,    27,     9,    10,     0,     0,     0,     0,
       0,     0,     0,     0,    29,    30,     0,     9,    10,    31,
      32,     0,    33,     0,    20,    21,    22,     0,    23,   123,
      25,     0,     0,     0,     0,     0,    27,    20,    21,    22,
       0,    23,    92,    25,     0,     0,     0,    29,    30,    27,
       9,    10,    31,    32,     0,    33,     0,     0,     0,     0,
      29,    30,     0,     9,    10,    31,    32,     0,    33,     0,
      20,    21,    22,     0,    23,    92,    25,     0,     0,     0,
       0,     0,   199,    20,    21,    22,     0,    23,   101,    25,
       0,     0,     0,    29,    30,    27,     0,     0,    31,    32,
       0,    33,     0,     0,     0,     0,    29,    30,     0,     0,
       0,    31,    32
    };
  }

private static final short yycheck_[] = yycheck_init();
  private static final short[] yycheck_init()
  {
    return new short[]
    {
       0,   222,    35,     5,    26,     5,    11,   188,    29,    30,
     199,    36,    37,   304,    46,   306,   307,   373,    29,    30,
      20,    21,    48,    48,    26,   206,    26,    11,    42,    29,
      30,    31,    32,    65,    42,    40,    36,    37,   227,    65,
     229,   397,    73,    42,    48,    76,    51,     0,    48,    31,
      32,    65,     5,    86,    48,    49,    40,    65,    42,    42,
      47,    65,   353,   354,    95,    96,    65,    20,    21,    42,
      42,    53,    54,    26,    49,    42,    29,    30,    31,    32,
      15,    16,    65,    36,    37,    42,    42,    42,    58,    59,
      65,   280,    65,    65,    48,    48,    48,   119,    65,    47,
      35,    36,    37,    51,    39,    40,    41,    42,    65,    65,
      65,    65,    47,    65,   139,   140,   305,   119,    48,   119,
      33,    34,    47,    58,    59,    50,   347,    42,    63,    64,
      48,    66,    49,    46,    47,    65,    42,    50,    51,   139,
     140,     0,   163,   164,   165,   326,    42,    65,    65,   338,
     150,   151,   152,   153,   154,   155,   156,   157,   158,   159,
     160,   161,   162,   163,   164,   165,   119,    26,    27,    28,
      52,    48,    37,    38,    40,    40,    35,    36,    37,    52,
     401,   212,    48,    65,    47,   216,   139,   140,    65,    48,
      49,    56,    65,    48,    49,   226,   217,   150,   151,   152,
     153,   154,   155,   156,   157,   158,   159,   160,   161,   162,
     163,   164,   165,    33,    34,    33,    34,   217,    49,    33,
      34,    48,    40,    15,    16,    48,    52,    86,    46,    47,
      50,   262,    50,    51,    65,    53,    50,    40,    65,    65,
      48,    48,    65,    35,    36,    37,    46,    39,    40,    41,
      50,    48,    48,    48,   113,    47,   115,    65,    65,    40,
     119,   263,    47,   263,   217,   296,    58,    59,    65,    65,
      65,    63,    64,    47,   276,    47,   276,    51,    40,    53,
     139,   140,    40,    15,    16,    47,    48,    49,    50,    51,
      48,    53,    40,    40,    40,    42,   200,   201,    40,    47,
      20,    21,    48,    35,    36,    37,    48,    39,    40,    41,
     263,    31,    32,    40,    47,    47,    48,   319,    51,   319,
      53,    48,    37,   276,    39,    40,    58,    59,    28,    33,
      34,    63,    64,    15,    66,    47,    40,   288,   289,    51,
     199,    53,    46,    47,    47,    27,    50,    51,    46,    53,
      40,    48,    49,    51,    51,    53,    53,    51,   360,    53,
     360,   363,   364,   363,   364,   367,   319,   367,   272,    47,
     372,   275,   372,    47,    48,    47,    50,    51,    47,    53,
       0,    50,    51,    42,   386,    44,   386,   389,   390,   389,
     390,   157,   158,   159,   160,    60,    61,    62,   302,   303,
     304,    40,   306,   307,    40,    87,   408,   360,   408,    91,
     363,   364,   155,   156,   367,    97,    98,    99,   100,   372,
     102,    50,    51,    27,    53,   107,   108,    55,   110,   111,
     112,   335,   336,   386,   293,    56,   389,   390,     6,    57,
       8,    43,   161,   162,    51,    42,    25,    43,    47,   353,
     354,    13,    50,   135,    40,   408,    45,    40,    48,    43,
      47,    49,    40,    40,    40,    47,    47,   149,    40,    42,
     374,    40,    65,    44,    48,    26,    40,    52,    26,    49,
      40,    47,    10,    44,    47,    52,   168,   169,   170,    54,
     172,   173,    48,    48,   176,   177,    45,    42,   180,   181,
      42,    42,    48,    46,   186,   187,   188,    45,   264,    46,
      50,    46,    46,   372,   109,   408,    49,   199,    35,   166,
       9,   151,   293,   150,   206,   250,     3,     4,     5,   152,
       7,   153,     9,   409,    -1,    12,    13,    14,    15,    16,
      17,    18,    19,    20,    21,    22,    23,    24,    25,   408,
      -1,   154,    -1,    -1,    -1,    -1,    -1,    -1,    35,    36,
      37,    -1,    39,    40,    41,    -1,   248,    44,    -1,    -1,
      47,    -1,    -1,    -1,    51,   257,   258,    -1,   260,    15,
      16,    58,    59,    -1,    -1,    -1,    63,    64,    -1,    66,
      -1,    -1,   274,    -1,    -1,    -1,    -1,    -1,    -1,    35,
      36,    37,    -1,    39,    40,    41,   288,   289,    44,    -1,
      -1,    47,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    58,    59,    -1,    -1,   308,    63,    64,    -1,
      66,    -1,    -1,    -1,    -1,    -1,   318,    -1,    -1,   321,
     322,    -1,    -1,    -1,   326,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,   335,   336,     3,     4,     5,    -1,     7,
      -1,     9,    -1,    11,    12,    13,    -1,    15,    16,    -1,
      18,    -1,    -1,    21,    22,    23,    24,    25,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    35,    36,    37,
      -1,    39,    40,    41,    -1,    -1,    44,    45,    -1,    47,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      58,    59,    -1,    -1,    -1,    63,    64,    -1,    66,     3,
       4,     5,    -1,     7,    -1,     9,    -1,    11,    12,    13,
      -1,    15,    16,    -1,    18,    -1,    -1,    21,    22,    23,
      24,    25,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    35,    36,    37,    -1,    39,    40,    41,    -1,    -1,
      44,    -1,    -1,    47,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    58,    59,    -1,    -1,    -1,    63,
      64,    -1,    66,     3,     4,     5,    -1,     7,    -1,     9,
      -1,    -1,    12,    13,    -1,    15,    16,    -1,    18,    -1,
      -1,    21,    22,    23,    24,    25,    -1,    -1,    15,    16,
      -1,    -1,    -1,    -1,    -1,    35,    36,    37,    -1,    39,
      40,    41,    -1,    -1,    44,    -1,    -1,    47,    35,    36,
      37,    -1,    39,    40,    41,    -1,    -1,    -1,    58,    59,
      47,    15,    16,    63,    64,    52,    66,    -1,    -1,    -1,
      -1,    58,    59,    -1,    -1,    -1,    63,    64,    -1,    66,
      -1,    35,    36,    37,    -1,    39,    40,    41,    -1,    -1,
      -1,    -1,    -1,    47,    48,    15,    16,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    58,    59,    -1,    -1,    -1,    63,
      64,    -1,    66,    -1,    -1,    35,    36,    37,    -1,    39,
      40,    41,    42,    -1,    -1,    -1,    -1,    47,    15,    16,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    58,    59,
      -1,    -1,    -1,    63,    64,    -1,    66,    -1,    35,    36,
      37,    -1,    39,    40,    41,    -1,    -1,    -1,    -1,    -1,
      47,    48,    15,    16,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    58,    59,    -1,    -1,    -1,    63,    64,    -1,    66,
      -1,    -1,    35,    36,    37,    -1,    39,    40,    41,    -1,
      -1,    -1,    -1,    -1,    47,    48,    15,    16,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    58,    59,    -1,    -1,    -1,
      63,    64,    -1,    66,    -1,    -1,    35,    36,    37,    -1,
      39,    40,    41,    -1,    -1,    -1,    -1,    -1,    47,    48,
      15,    16,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    58,
      59,    -1,    -1,    -1,    63,    64,    -1,    66,    -1,    -1,
      35,    36,    37,    -1,    39,    40,    41,    -1,    -1,    -1,
      45,    -1,    47,    15,    16,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    58,    59,    -1,    -1,    -1,    63,    64,
      -1,    66,    -1,    35,    36,    37,    -1,    39,    40,    41,
      -1,    -1,    -1,    -1,    -1,    47,    48,    15,    16,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    58,    59,    -1,    -1,
      -1,    63,    64,    -1,    66,    -1,    -1,    35,    36,    37,
      -1,    39,    40,    41,    -1,    -1,    -1,    -1,    -1,    47,
      48,    15,    16,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      58,    59,    -1,    -1,    -1,    63,    64,    -1,    66,    -1,
      -1,    35,    36,    37,    -1,    39,    40,    41,    -1,    -1,
      44,    -1,    -1,    47,    15,    16,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    58,    59,    -1,    15,    16,    63,
      64,    -1,    66,    -1,    35,    36,    37,    -1,    39,    40,
      41,    -1,    -1,    -1,    -1,    -1,    47,    35,    36,    37,
      -1,    39,    40,    41,    -1,    -1,    -1,    58,    59,    47,
      15,    16,    63,    64,    -1,    66,    -1,    -1,    -1,    -1,
      58,    59,    -1,    15,    16,    63,    64,    -1,    66,    -1,
      35,    36,    37,    -1,    39,    40,    41,    -1,    -1,    -1,
      -1,    -1,    47,    35,    36,    37,    -1,    39,    40,    41,
      -1,    -1,    -1,    58,    59,    47,    -1,    -1,    63,    64,
      -1,    66,    -1,    -1,    -1,    -1,    58,    59,    -1,    -1,
      -1,    63,    64
    };
  }

/* YYSTOS[STATE-NUM] -- The (internal number of the) accessing
   symbol of state STATE-NUM.  */
  private static final short yystos_[] = yystos_init();
  private static final short[] yystos_init()
  {
    return new short[]
    {
       0,     3,     4,     5,     7,     9,    12,    13,    14,    15,
      16,    17,    18,    19,    20,    21,    22,    23,    24,    25,
      35,    36,    37,    39,    40,    41,    44,    47,    51,    58,
      59,    63,    64,    66,    68,    69,    70,    72,    74,    76,
      77,    78,    79,    80,    81,    82,    83,    84,    92,    93,
      96,    97,    99,   100,   102,   103,   104,   105,   106,   107,
     108,   109,   110,   111,   112,   113,   114,   115,   116,   117,
     118,   119,   120,   121,   122,   123,   124,   127,   129,    40,
      42,    42,    42,    42,    40,    72,    47,    47,    40,    40,
     100,    47,    40,    42,   102,   121,   124,    47,    47,    47,
      47,    40,    47,   114,   121,   124,   114,    33,    34,    46,
      47,    50,    51,    53,    90,    11,    40,    45,    72,    73,
      75,    89,    99,    40,    99,   102,    40,    99,   112,   112,
     114,   114,    37,    38,    40,    56,   130,     0,    40,    89,
      92,    99,    68,    68,    68,    40,    99,    40,    42,    65,
      28,    27,    55,    56,    57,    29,    30,    31,    32,    53,
      54,    58,    59,    60,    61,    62,    43,   125,    51,    33,
      34,    90,    33,    34,    90,    42,    47,    51,    25,    40,
      42,    89,    99,   102,    43,    71,    47,    51,    47,   102,
      42,   102,   102,   102,   102,   102,   102,    78,    40,    47,
      48,    95,    98,    99,   102,   128,    44,   102,    52,   102,
      99,   101,    40,    99,    75,    45,    40,    48,    48,    51,
      43,   102,    47,    47,    68,    68,    40,    47,    40,    47,
     102,   104,   105,   106,   107,   108,   109,   109,   110,   110,
     110,   110,   111,   111,   112,   112,   112,    40,    47,    97,
     126,   102,   102,   102,   102,   102,    48,    47,    46,   102,
      42,   102,    40,    48,    40,    42,    48,    91,   102,   102,
      48,    91,    48,    48,    49,    48,    48,    42,    42,    48,
      49,    48,    95,    99,    74,    74,    48,    40,    49,    49,
      45,    91,    52,    49,    54,    90,    40,    90,   112,    52,
     130,    40,    48,    48,    95,    47,    48,    95,    46,   102,
     125,    52,    42,    42,    42,    42,   102,   102,    42,    48,
     102,    42,    46,    72,    71,    48,    49,    52,    48,    74,
      44,   102,    74,    72,    95,    26,    26,    48,    49,    98,
      98,    45,   101,    42,    90,    42,    48,    49,   131,    42,
      74,    94,    94,    48,    95,    94,    94,   102,    48,    48,
      48,   102,    72,    48,    48,   102,   102,    10,    91,     6,
       8,    85,    87,    88,    48,    74,   102,    74,   102,    95,
      42,   130,    48,    94,    94,    72,    48,    72,    72,    48,
      48,    72,    37,    39,    40,    46,    45,    75,    86,    87,
      74,    50,    72,    72,    72,    46,    46,    46,    87,   130,
      86,   131
    };
  }

/* YYR1[YYN] -- Symbol number of symbol that rule YYN derives.  */
  private static final short yyr1_[] = yyr1_init();
  private static final short[] yyr1_init()
  {
    return new short[]
    {
       0,    67,    68,    68,    68,    68,    68,    68,    69,    69,
      69,    70,    71,    71,    72,    72,    72,    72,    72,    72,
      72,    72,    72,    72,    72,    72,    73,    73,    74,    74,
      75,    75,    76,    77,    77,    77,    77,    77,    77,    77,
      78,    78,    79,    79,    80,    80,    80,    80,    80,    80,
      80,    81,    82,    83,    84,    85,    86,    86,    87,    87,
      88,    88,    88,    88,    89,    89,    89,    90,    90,    90,
      91,    91,    92,    92,    92,    92,    92,    92,    92,    92,
      93,    94,    94,    95,    95,    95,    95,    96,    96,    96,
      96,    96,    96,    97,    97,    98,    98,    98,    98,    99,
      99,    99,   100,   101,   101,   102,   102,   102,   102,   103,
     103,   104,   104,   105,   105,   106,   106,   107,   107,   108,
     108,   108,   109,   109,   109,   109,   109,   110,   110,   110,
     111,   111,   111,   111,   112,   112,   113,   113,   113,   114,
     114,   114,   114,   114,   115,   116,   116,   116,   117,   117,
     118,   118,   119,   119,   119,   119,   120,   120,   120,   121,
     121,   122,   123,   123,   123,   124,   125,   125,   126,   126,
     126,   127,   127,   127,   127,   127,   127,   128,   128,   128,
     128,   129,   129,   130,   130,   130,   130,   131,   131
    };
  }

/* YYR2[YYN] -- Number of symbols on the right hand side of rule YYN.  */
  private static final byte yyr2_[] = yyr2_init();
  private static final byte[] yyr2_init()
  {
    return new byte[]
    {
       0,     2,     3,     2,     2,     2,     3,     0,     1,     1,
       1,     4,     0,     3,     1,     2,     1,     1,     1,     1,
       1,     1,     1,     1,     1,     1,     1,     1,     3,     2,
       1,     2,     3,     2,     3,     2,     3,     2,     2,     2,
       5,     7,     5,     6,     8,     7,     7,     6,     8,     7,
       8,     5,     5,     7,     7,     2,     3,     0,     2,     1,
       3,     3,     3,     2,     4,     4,     5,     2,     3,     4,
       1,     3,     6,     6,     5,     5,     5,     5,     4,     4,
       4,     1,     1,     2,     3,     3,     4,     4,     4,     4,
       4,     4,     4,     3,     4,     1,     1,     3,     3,     3,
       1,     1,     4,     1,     3,     1,     1,     1,     1,     3,
       1,     3,     1,     1,     3,     1,     3,     1,     3,     1,
       3,     3,     1,     3,     3,     3,     3,     1,     3,     3,
       1,     3,     3,     3,     1,     4,     1,     2,     2,     2,
       2,     2,     2,     1,     1,     1,     1,     1,     1,     1,
       3,     1,     1,     1,     1,     1,     1,     1,     1,     4,
       4,     5,     2,     2,     2,     2,     3,     2,     1,     1,
       3,     2,     4,     5,     4,     5,     5,     4,     4,     4,
       4,     5,     6,     2,     1,     1,     1,     5,     0
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
       0,   114,   114,   117,   118,   120,   121,   124,   128,   129,
     130,   134,   140,   141,   145,   146,   147,   148,   149,   150,
     151,   152,   153,   154,   155,   156,   160,   161,   167,   168,
     170,   171,   175,   179,   180,   181,   182,   183,   184,   185,
     189,   190,   196,   197,   203,   205,   207,   209,   211,   213,
     216,   224,   228,   234,   240,   246,   254,   255,   259,   260,
     264,   267,   270,   273,   279,   282,   285,   291,   292,   293,
     297,   298,   302,   305,   308,   311,   314,   317,   320,   323,
     328,   332,   333,   337,   338,   339,   340,   348,   351,   354,
     355,   356,   357,   364,   367,   373,   374,   375,   376,   380,
     381,   382,   386,   390,   391,   395,   396,   397,   398,   402,
     405,   409,   412,   416,   417,   423,   424,   430,   431,   437,
     438,   441,   447,   448,   451,   454,   457,   463,   464,   467,
     473,   474,   477,   480,   486,   487,   492,   493,   494,   498,
     501,   504,   505,   506,   510,   516,   517,   518,   522,   523,
     527,   528,   532,   533,   534,   535,   539,   540,   541,   545,
     546,   550,   578,   579,   580,   584,   588,   589,   593,   594,
     595,   599,   600,   601,   604,   607,   610,   617,   620,   623,
     626,   631,   634,   640,   641,   642,   643,   647,   650
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

  private static final int yylast_ = 1252;
  private static final int yynnts_ = 65;
  private static final int yyempty_ = -2;
  private static final int yyfinal_ = 137;
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

/* "VondaGrammar.java":3054  */ /* lalr1.java:1066  */

}

