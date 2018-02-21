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
    yyval = ((LinkedList<RudiTree>)(yystack.valueAt (3-(3)))); ((LinkedList<RudiTree>)(yystack.valueAt (3-(3)))).addFirst(new StatFieldDef(((String)(yystack.valueAt (3-(1)))), ((StatVarDef)(yystack.valueAt (3-(2))))).setPos(yystack.locationAt (3-(1)), yystack.locationAt (3-(2))));
  };
  break;
    

  case 7:
  if (yyn == 7)
    /* "VondaGrammar.y":124  */ /* lalr1.java:489  */
    {
    yyval = ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))); ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))).addFirst(new StatFieldDef(null, ((StatVarDef)(yystack.valueAt (2-(1))))).setPos(yystack.locationAt (2-(1))));
  };
  break;
    

  case 8:
  if (yyn == 8)
    /* "VondaGrammar.y":127  */ /* lalr1.java:489  */
    { yyval = _statements;};
  break;
    

  case 9:
  if (yyn == 9)
    /* "VondaGrammar.y":131  */ /* lalr1.java:489  */
    { yyval = "public"; };
  break;
    

  case 10:
  if (yyn == 10)
    /* "VondaGrammar.y":132  */ /* lalr1.java:489  */
    { yyval = "protected"; };
  break;
    

  case 11:
  if (yyn == 11)
    /* "VondaGrammar.y":133  */ /* lalr1.java:489  */
    { yyval = "private"; };
  break;
    

  case 12:
  if (yyn == 12)
    /* "VondaGrammar.y":137  */ /* lalr1.java:489  */
    {
    new Import((( String )(yystack.valueAt (4-(2)))), ((List<String>)(yystack.valueAt (4-(3)))).toArray(new String[((List<String>)(yystack.valueAt (4-(3)))).size()]));
  };
  break;
    

  case 13:
  if (yyn == 13)
    /* "VondaGrammar.y":143  */ /* lalr1.java:489  */
    { yyval = new ArrayList<String>(); };
  break;
    

  case 14:
  if (yyn == 14)
    /* "VondaGrammar.y":144  */ /* lalr1.java:489  */
    { yyval = ((List<String>)(yystack.valueAt (3-(3)))); ((List<String>)(yystack.valueAt (3-(3)))).add((( String )(yystack.valueAt (3-(2))))); };
  break;
    

  case 15:
  if (yyn == 15)
    /* "VondaGrammar.y":148  */ /* lalr1.java:489  */
    { yyval = ((StatAbstractBlock)(yystack.valueAt (1-(1)))); };
  break;
    

  case 16:
  if (yyn == 16)
    /* "VondaGrammar.y":149  */ /* lalr1.java:489  */
    { yyval = new StatExpression(((RTExpression)(yystack.valueAt (2-(1))))).setPos((yyloc)); };
  break;
    

  case 17:
  if (yyn == 17)
    /* "VondaGrammar.y":150  */ /* lalr1.java:489  */
    { yyval = new StatExpression(((RTExpression)(yystack.valueAt (2-(1))))).setPos((yyloc)); };
  break;
    

  case 18:
  if (yyn == 18)
    /* "VondaGrammar.y":151  */ /* lalr1.java:489  */
    { yyval = ((StatGrammarRule)(yystack.valueAt (1-(1)))); };
  break;
    

  case 19:
  if (yyn == 19)
    /* "VondaGrammar.y":152  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 20:
  if (yyn == 20)
    /* "VondaGrammar.y":153  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 21:
  if (yyn == 21)
    /* "VondaGrammar.y":154  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 22:
  if (yyn == 22)
    /* "VondaGrammar.y":155  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 23:
  if (yyn == 23)
    /* "VondaGrammar.y":156  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 24:
  if (yyn == 24)
    /* "VondaGrammar.y":157  */ /* lalr1.java:489  */
    { yyval = ((StatIf)(yystack.valueAt (1-(1)))); };
  break;
    

  case 25:
  if (yyn == 25)
    /* "VondaGrammar.y":158  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 26:
  if (yyn == 26)
    /* "VondaGrammar.y":159  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 27:
  if (yyn == 27)
    /* "VondaGrammar.y":160  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 28:
  if (yyn == 28)
    /* "VondaGrammar.y":161  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 29:
  if (yyn == 29)
    /* "VondaGrammar.y":165  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 30:
  if (yyn == 30)
    /* "VondaGrammar.y":166  */ /* lalr1.java:489  */
    { yyval = ((StatVarDef)(yystack.valueAt (1-(1)))); };
  break;
    

  case 31:
  if (yyn == 31)
    /* "VondaGrammar.y":172  */ /* lalr1.java:489  */
    { yyval = new StatAbstractBlock(((LinkedList<RTStatement>)(yystack.valueAt (3-(2)))), true).setPos((yyloc)); };
  break;
    

  case 32:
  if (yyn == 32)
    /* "VondaGrammar.y":173  */ /* lalr1.java:489  */
    {
    yyval = new StatAbstractBlock(new ArrayList<RTStatement>(), true).setPos((yyloc));
  };
  break;
    

  case 33:
  if (yyn == 33)
    /* "VondaGrammar.y":177  */ /* lalr1.java:489  */
    { yyval = new LinkedList<RTStatement>(){{ add(((RTStatement)(yystack.valueAt (1-(1))))); }}; };
  break;
    

  case 34:
  if (yyn == 34)
    /* "VondaGrammar.y":178  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RTStatement>)(yystack.valueAt (2-(2)))); ((LinkedList<RTStatement>)(yystack.valueAt (2-(2)))).addFirst(((RTStatement)(yystack.valueAt (2-(1))))); };
  break;
    

  case 35:
  if (yyn == 35)
    /* "VondaGrammar.y":182  */ /* lalr1.java:489  */
    { yyval = new StatGrammarRule((( String )(yystack.valueAt (3-(1)))), ((StatIf)(yystack.valueAt (3-(3))))).setPos((yyloc)); };
  break;
    

  case 36:
  if (yyn == 36)
    /* "VondaGrammar.y":186  */ /* lalr1.java:489  */
    { yyval = new StatReturn("return").setPos((yyloc)); };
  break;
    

  case 37:
  if (yyn == 37)
    /* "VondaGrammar.y":187  */ /* lalr1.java:489  */
    { yyval = new StatReturn(((RTExpression)(yystack.valueAt (3-(2))))).setPos((yyloc)); };
  break;
    

  case 38:
  if (yyn == 38)
    /* "VondaGrammar.y":188  */ /* lalr1.java:489  */
    { yyval = new StatReturn("break").setPos((yyloc)); };
  break;
    

  case 39:
  if (yyn == 39)
    /* "VondaGrammar.y":189  */ /* lalr1.java:489  */
    { yyval = new StatReturn("break", (( String )(yystack.valueAt (3-(2))))).setPos((yyloc)); };
  break;
    

  case 40:
  if (yyn == 40)
    /* "VondaGrammar.y":190  */ /* lalr1.java:489  */
    { yyval = new StatReturn("cancel").setPos((yyloc)); };
  break;
    

  case 41:
  if (yyn == 41)
    /* "VondaGrammar.y":191  */ /* lalr1.java:489  */
    { yyval = new StatReturn("cancel_all").setPos((yyloc)); };
  break;
    

  case 42:
  if (yyn == 42)
    /* "VondaGrammar.y":192  */ /* lalr1.java:489  */
    { yyval = new StatReturn("continue").setPos((yyloc)); };
  break;
    

  case 43:
  if (yyn == 43)
    /* "VondaGrammar.y":196  */ /* lalr1.java:489  */
    { yyval = new StatIf(((RTExpression)(yystack.valueAt (5-(3)))), ((RTStatement)(yystack.valueAt (5-(5)))), null).setPos((yyloc)); };
  break;
    

  case 44:
  if (yyn == 44)
    /* "VondaGrammar.y":197  */ /* lalr1.java:489  */
    {
    yyval = new StatIf(((RTExpression)(yystack.valueAt (7-(3)))), ((RTStatement)(yystack.valueAt (7-(5)))), ((RTStatement)(yystack.valueAt (7-(7))))).setPos((yyloc));
  };
  break;
    

  case 45:
  if (yyn == 45)
    /* "VondaGrammar.y":203  */ /* lalr1.java:489  */
    { yyval = new StatWhile(((RTExpression)(yystack.valueAt (5-(3)))), ((RTStatement)(yystack.valueAt (5-(5)))), true).setPos((yyloc)); };
  break;
    

  case 46:
  if (yyn == 46)
    /* "VondaGrammar.y":204  */ /* lalr1.java:489  */
    {
    yyval = new StatWhile(((RTExpression)(yystack.valueAt (6-(5)))), ((RTStatement)(yystack.valueAt (6-(2)))), false).setPos((yyloc));
  };
  break;
    

  case 47:
  if (yyn == 47)
    /* "VondaGrammar.y":210  */ /* lalr1.java:489  */
    {
    yyval = new StatFor1(((StatVarDef)(yystack.valueAt (8-(3)))), ((RTExpression)(yystack.valueAt (8-(4)))), ((RTExpression)(yystack.valueAt (8-(6)))), ((RTStatement)(yystack.valueAt (8-(8))))).setPos((yyloc)); };
  break;
    

  case 48:
  if (yyn == 48)
    /* "VondaGrammar.y":212  */ /* lalr1.java:489  */
    {
    yyval = new StatFor1(((StatVarDef)(yystack.valueAt (7-(3)))), null, ((RTExpression)(yystack.valueAt (7-(5)))), ((RTStatement)(yystack.valueAt (7-(7))))).setPos((yyloc)); };
  break;
    

  case 49:
  if (yyn == 49)
    /* "VondaGrammar.y":214  */ /* lalr1.java:489  */
    {
    yyval = new StatFor1(((StatVarDef)(yystack.valueAt (7-(3)))), ((RTExpression)(yystack.valueAt (7-(4)))), null, ((RTStatement)(yystack.valueAt (7-(7))))).setPos((yyloc)); };
  break;
    

  case 50:
  if (yyn == 50)
    /* "VondaGrammar.y":216  */ /* lalr1.java:489  */
    {
    yyval = new StatFor1(((StatVarDef)(yystack.valueAt (6-(3)))), null, null, ((RTStatement)(yystack.valueAt (6-(6))))).setPos((yyloc)); };
  break;
    

  case 51:
  if (yyn == 51)
    /* "VondaGrammar.y":218  */ /* lalr1.java:489  */
    {
    yyval = new StatFor1(null, ((RTExpression)(yystack.valueAt (8-(4)))), ((RTExpression)(yystack.valueAt (8-(6)))), ((RTStatement)(yystack.valueAt (8-(8))))).setPos((yyloc)); };
  break;
    

  case 52:
  if (yyn == 52)
    /* "VondaGrammar.y":220  */ /* lalr1.java:489  */
    {
    ExpVariable var = new ExpVariable((( String )(yystack.valueAt (7-(3))))); var.setPos(yystack.locationAt (7-(3)));
    yyval = new StatFor2(var, ((RTExpression)(yystack.valueAt (7-(5)))), ((RTStatement)(yystack.valueAt (7-(7))))).setPos((yyloc));
  };
  break;
    

  case 53:
  if (yyn == 53)
    /* "VondaGrammar.y":224  */ /* lalr1.java:489  */
    {
    ExpVariable var = new ExpVariable((( String )(yystack.valueAt (8-(4))))); var.setPos(yystack.locationAt (8-(4)));
    yyval = new StatFor2(((Type)(yystack.valueAt (8-(3)))), var, ((RTExpression)(yystack.valueAt (8-(6)))), ((RTStatement)(yystack.valueAt (8-(8))))).setPos((yyloc));
  };
  break;
    

  case 54:
  if (yyn == 54)
    /* "VondaGrammar.y":233  */ /* lalr1.java:489  */
    { yyval = new StatPropose(((RTExpression)(yystack.valueAt (5-(3)))), ((StatAbstractBlock)(yystack.valueAt (5-(5))))).setPos((yyloc)); };
  break;
    

  case 55:
  if (yyn == 55)
    /* "VondaGrammar.y":237  */ /* lalr1.java:489  */
    {
    yyval = new StatTimeout(null, ((RTExpression)(yystack.valueAt (5-(3)))), ((StatAbstractBlock)(yystack.valueAt (5-(5))))).setPos((yyloc));
  };
  break;
    

  case 56:
  if (yyn == 56)
    /* "VondaGrammar.y":243  */ /* lalr1.java:489  */
    {
    yyval = new StatTimeout(((RTExpression)(yystack.valueAt (7-(3)))), ((RTExpression)(yystack.valueAt (7-(5)))), ((StatAbstractBlock)(yystack.valueAt (7-(7))))).setPos((yyloc));
  };
  break;
    

  case 57:
  if (yyn == 57)
    /* "VondaGrammar.y":249  */ /* lalr1.java:489  */
    {
    yyval = new StatSwitch(((RTExpression)(yystack.valueAt (5-(3)))), ((StatAbstractBlock)(yystack.valueAt (5-(5))))).setPos((yyloc));
  };
  break;
    

  case 58:
  if (yyn == 58)
    /* "VondaGrammar.y":275  */ /* lalr1.java:489  */
    {
    ExpSingleValue val =
      new ExpSingleValue("case " + (( ExpSingleValue )(yystack.valueAt (3-(2)))).toString() + ":", "label");
    RTStatement lbl = val.ensureStatement();
    val.setPos((yyloc)); lbl.setPos((yyloc));
    yyval = lbl;
  };
  break;
    

  case 59:
  if (yyn == 59)
    /* "VondaGrammar.y":282  */ /* lalr1.java:489  */
    {
    ExpSingleValue val =
      new ExpSingleValue("case " + (( ExpSingleValue )(yystack.valueAt (3-(2)))).toString() + ":", "label");
    RTStatement lbl = val.ensureStatement();
    val.setPos((yyloc)); lbl.setPos((yyloc));
    yyval = lbl;
  };
  break;
    

  case 60:
  if (yyn == 60)
    /* "VondaGrammar.y":289  */ /* lalr1.java:489  */
    {
    ExpSingleValue val =
      new ExpSingleValue("case " + (( String )(yystack.valueAt (3-(2)))) + ":", "label");
    RTStatement lbl = val.ensureStatement();
    val.setPos((yyloc)); lbl.setPos((yyloc));
    yyval = lbl;
  };
  break;
    

  case 61:
  if (yyn == 61)
    /* "VondaGrammar.y":296  */ /* lalr1.java:489  */
    {
    ExpSingleValue val = new ExpSingleValue("default:", "label");
    RTStatement lbl = val.ensureStatement();
    val.setPos((yyloc)); lbl.setPos((yyloc));
    yyval = lbl;
  };
  break;
    

  case 62:
  if (yyn == 62)
    /* "VondaGrammar.y":305  */ /* lalr1.java:489  */
    {
    yyval = new StatVarDef(true, null, (( String )(yystack.valueAt (4-(2)))), ((RTExpression)(yystack.valueAt (4-(3))))).setPos((yyloc));
  };
  break;
    

  case 63:
  if (yyn == 63)
    /* "VondaGrammar.y":308  */ /* lalr1.java:489  */
    {
    yyval = new StatVarDef(false, ((Type)(yystack.valueAt (4-(1)))), (( String )(yystack.valueAt (4-(2)))), ((RTExpression)(yystack.valueAt (4-(3))))).setPos((yyloc));
  };
  break;
    

  case 64:
  if (yyn == 64)
    /* "VondaGrammar.y":311  */ /* lalr1.java:489  */
    {
    yyval = new StatVarDef(true, ((Type)(yystack.valueAt (5-(2)))), (( String )(yystack.valueAt (5-(3)))), ((RTExpression)(yystack.valueAt (5-(4))))).setPos((yyloc));
  };
  break;
    

  case 65:
  if (yyn == 65)
    /* "VondaGrammar.y":314  */ /* lalr1.java:489  */
    {
    yyval = new StatVarDef(true, null, (( String )(yystack.valueAt (3-(2)))), null).setPos((yyloc));
  };
  break;
    

  case 66:
  if (yyn == 66)
    /* "VondaGrammar.y":317  */ /* lalr1.java:489  */
    {
    yyval = new StatVarDef(false, ((Type)(yystack.valueAt (3-(1)))), (( String )(yystack.valueAt (3-(2)))), null).setPos((yyloc));
  };
  break;
    

  case 67:
  if (yyn == 67)
    /* "VondaGrammar.y":320  */ /* lalr1.java:489  */
    {
    yyval = new StatVarDef(true, ((Type)(yystack.valueAt (4-(2)))), (( String )(yystack.valueAt (4-(3)))), null).setPos((yyloc));
  };
  break;
    

  case 68:
  if (yyn == 68)
    /* "VondaGrammar.y":326  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (2-(2)))); };
  break;
    

  case 69:
  if (yyn == 69)
    /* "VondaGrammar.y":327  */ /* lalr1.java:489  */
    {
    yyval = new ExpListLiteral(new LinkedList<RTExpression>()).setPos(yystack.locationAt (3-(2)), yystack.locationAt (3-(3)));
  };
  break;
    

  case 70:
  if (yyn == 70)
    /* "VondaGrammar.y":330  */ /* lalr1.java:489  */
    {
    yyval = new ExpListLiteral(((LinkedList<RTExpression>)(yystack.valueAt (4-(3))))).setPos(yystack.locationAt (4-(2)), yystack.locationAt (4-(4)));
  };
  break;
    

  case 71:
  if (yyn == 71)
    /* "VondaGrammar.y":336  */ /* lalr1.java:489  */
    { yyval = new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (1-(1))))); }}; };
  break;
    

  case 72:
  if (yyn == 72)
    /* "VondaGrammar.y":337  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))); ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))).addFirst(((RTExpression)(yystack.valueAt (3-(1))))); };
  break;
    

  case 73:
  if (yyn == 73)
    /* "VondaGrammar.y":341  */ /* lalr1.java:489  */
    {
    yyval = new StatMethodDeclaration("public", ((Type)(yystack.valueAt (6-(2)))), ((Type)(yystack.valueAt (6-(1)))), (( String )(yystack.valueAt (6-(3)))), null, ((StatAbstractBlock)(yystack.valueAt (6-(6))))).setPos((yyloc));
  };
  break;
    

  case 74:
  if (yyn == 74)
    /* "VondaGrammar.y":344  */ /* lalr1.java:489  */
    {
    yyval = new StatMethodDeclaration("public", ((Type)(yystack.valueAt (6-(2)))), ((Type)(yystack.valueAt (6-(1)))), (( String )(yystack.valueAt (6-(3)))), ((LinkedList)(yystack.valueAt (6-(5)))), ((StatAbstractBlock)(yystack.valueAt (6-(6))))).setPos((yyloc));
  };
  break;
    

  case 75:
  if (yyn == 75)
    /* "VondaGrammar.y":347  */ /* lalr1.java:489  */
    {
    yyval = new StatMethodDeclaration("public", null, ((Type)(yystack.valueAt (5-(1)))), (( String )(yystack.valueAt (5-(2)))), null, ((StatAbstractBlock)(yystack.valueAt (5-(5))))).setPos((yyloc));
  };
  break;
    

  case 76:
  if (yyn == 76)
    /* "VondaGrammar.y":350  */ /* lalr1.java:489  */
    {
    yyval = new StatMethodDeclaration("public", null, ((Type)(yystack.valueAt (5-(1)))), (( String )(yystack.valueAt (5-(2)))), ((LinkedList)(yystack.valueAt (5-(4)))), ((StatAbstractBlock)(yystack.valueAt (5-(5))))).setPos((yyloc));
  };
  break;
    

  case 77:
  if (yyn == 77)
    /* "VondaGrammar.y":353  */ /* lalr1.java:489  */
    {
    yyval = new StatMethodDeclaration("public", ((Type)(yystack.valueAt (5-(1)))), null, (( String )(yystack.valueAt (5-(2)))), null, ((StatAbstractBlock)(yystack.valueAt (5-(5))))).setPos((yyloc));
  };
  break;
    

  case 78:
  if (yyn == 78)
    /* "VondaGrammar.y":356  */ /* lalr1.java:489  */
    {
    yyval = new StatMethodDeclaration("public", ((Type)(yystack.valueAt (5-(1)))), null, (( String )(yystack.valueAt (5-(2)))), ((LinkedList)(yystack.valueAt (5-(4)))), ((StatAbstractBlock)(yystack.valueAt (5-(5))))).setPos((yyloc));
  };
  break;
    

  case 79:
  if (yyn == 79)
    /* "VondaGrammar.y":359  */ /* lalr1.java:489  */
    {
    yyval = new StatMethodDeclaration("public", null, null, (( String )(yystack.valueAt (4-(1)))), null, ((StatAbstractBlock)(yystack.valueAt (4-(4))))).setPos((yyloc));
  };
  break;
    

  case 80:
  if (yyn == 80)
    /* "VondaGrammar.y":362  */ /* lalr1.java:489  */
    {
    yyval = new StatMethodDeclaration("public", null, null, (( String )(yystack.valueAt (4-(1)))), ((LinkedList)(yystack.valueAt (4-(3)))), ((StatAbstractBlock)(yystack.valueAt (4-(4))))).setPos((yyloc));
  };
  break;
    

  case 81:
  if (yyn == 81)
    /* "VondaGrammar.y":367  */ /* lalr1.java:489  */
    { yyval = ((Type)(yystack.valueAt (4-(2)))); };
  break;
    

  case 82:
  if (yyn == 82)
    /* "VondaGrammar.y":371  */ /* lalr1.java:489  */
    { yyval = ((StatAbstractBlock)(yystack.valueAt (1-(1)))); };
  break;
    

  case 83:
  if (yyn == 83)
    /* "VondaGrammar.y":372  */ /* lalr1.java:489  */
    { yyval = null; };
  break;
    

  case 84:
  if (yyn == 84)
    /* "VondaGrammar.y":376  */ /* lalr1.java:489  */
    { yyval = new LinkedList(){{ add((( String )(yystack.valueAt (2-(1))))); }}; };
  break;
    

  case 85:
  if (yyn == 85)
    /* "VondaGrammar.y":377  */ /* lalr1.java:489  */
    { yyval = new LinkedList(){{ add(((Type)(yystack.valueAt (3-(1))))); add((( String )(yystack.valueAt (3-(2))))); }}; };
  break;
    

  case 86:
  if (yyn == 86)
    /* "VondaGrammar.y":378  */ /* lalr1.java:489  */
    { yyval = ((LinkedList)(yystack.valueAt (3-(3)))); ((LinkedList)(yystack.valueAt (3-(3)))).addFirst((( String )(yystack.valueAt (3-(1))))); };
  break;
    

  case 87:
  if (yyn == 87)
    /* "VondaGrammar.y":379  */ /* lalr1.java:489  */
    {
    yyval = ((LinkedList)(yystack.valueAt (4-(4)))); ((LinkedList)(yystack.valueAt (4-(4)))).addFirst((( String )(yystack.valueAt (4-(2))))); ((LinkedList)(yystack.valueAt (4-(4)))).addFirst(((Type)(yystack.valueAt (4-(1)))));
  };
  break;
    

  case 88:
  if (yyn == 88)
    /* "VondaGrammar.y":387  */ /* lalr1.java:489  */
    {
    ExpVariable var = new ExpVariable((( String )(yystack.valueAt (4-(1))))); var.setPos(yystack.locationAt (4-(1)));
    yyval = new StatSetOperation(var, true, ((RTExpression)(yystack.valueAt (4-(3))))).setPos((yyloc));
  };
  break;
    

  case 89:
  if (yyn == 89)
    /* "VondaGrammar.y":391  */ /* lalr1.java:489  */
    {
    ExpVariable var = new ExpVariable((( String )(yystack.valueAt (4-(1))))); var.setPos(yystack.locationAt (4-(1)));
    yyval = new StatSetOperation(var, false, ((RTExpression)(yystack.valueAt (4-(3))))).setPos((yyloc));
  };
  break;
    

  case 90:
  if (yyn == 90)
    /* "VondaGrammar.y":395  */ /* lalr1.java:489  */
    {
    yyval = new StatSetOperation(((RTExpression)(yystack.valueAt (4-(1)))), true, ((RTExpression)(yystack.valueAt (4-(3))))).setPos((yyloc));
  };
  break;
    

  case 91:
  if (yyn == 91)
    /* "VondaGrammar.y":398  */ /* lalr1.java:489  */
    {
    yyval = new StatSetOperation(((RTExpression)(yystack.valueAt (4-(1)))), false, ((RTExpression)(yystack.valueAt (4-(3))))).setPos((yyloc));
  };
  break;
    

  case 92:
  if (yyn == 92)
    /* "VondaGrammar.y":401  */ /* lalr1.java:489  */
    {
    yyval = new StatSetOperation(((RTExpression)(yystack.valueAt (4-(1)))), true, ((RTExpression)(yystack.valueAt (4-(3))))).setPos((yyloc));
  };
  break;
    

  case 93:
  if (yyn == 93)
    /* "VondaGrammar.y":404  */ /* lalr1.java:489  */
    {
    yyval = new StatSetOperation(((RTExpression)(yystack.valueAt (4-(1)))), false, ((RTExpression)(yystack.valueAt (4-(3))))).setPos((yyloc));
  };
  break;
    

  case 94:
  if (yyn == 94)
    /* "VondaGrammar.y":413  */ /* lalr1.java:489  */
    {
    yyval = new ExpFuncCall((( String )(yystack.valueAt (3-(1)))), new LinkedList<RTExpression>(), false).setPos((yyloc));
  };
  break;
    

  case 95:
  if (yyn == 95)
    /* "VondaGrammar.y":416  */ /* lalr1.java:489  */
    {
    yyval = new ExpFuncCall((( String )(yystack.valueAt (4-(1)))), ((LinkedList<RTExpression>)(yystack.valueAt (4-(3)))), false).setPos((yyloc));
  };
  break;
    

  case 96:
  if (yyn == 96)
    /* "VondaGrammar.y":422  */ /* lalr1.java:489  */
    { yyval = new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (1-(1))))); }}; };
  break;
    

  case 97:
  if (yyn == 97)
    /* "VondaGrammar.y":423  */ /* lalr1.java:489  */
    { yyval = new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (1-(1))))); }}; };
  break;
    

  case 98:
  if (yyn == 98)
    /* "VondaGrammar.y":424  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))); ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))).addFirst(((RTExpression)(yystack.valueAt (3-(1))))); };
  break;
    

  case 99:
  if (yyn == 99)
    /* "VondaGrammar.y":425  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))); ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))).addFirst(((RTExpression)(yystack.valueAt (3-(1))))); };
  break;
    

  case 100:
  if (yyn == 100)
    /* "VondaGrammar.y":429  */ /* lalr1.java:489  */
    { yyval = new Type("Array", new Type((( String )(yystack.valueAt (3-(1)))))); };
  break;
    

  case 101:
  if (yyn == 101)
    /* "VondaGrammar.y":430  */ /* lalr1.java:489  */
    { yyval = new Type((( String )(yystack.valueAt (1-(1))))); };
  break;
    

  case 102:
  if (yyn == 102)
    /* "VondaGrammar.y":431  */ /* lalr1.java:489  */
    {
    yyval = new Type((( String )(yystack.valueAt (4-(1)))), ((LinkedList<Type>)(yystack.valueAt (4-(3)))).toArray(new Type[((LinkedList<Type>)(yystack.valueAt (4-(3)))).size()]));
  };
  break;
    

  case 103:
  if (yyn == 103)
    /* "VondaGrammar.y":437  */ /* lalr1.java:489  */
    { yyval = new LinkedList<Type>(){{ add(((Type)(yystack.valueAt (1-(1))))); }}; };
  break;
    

  case 104:
  if (yyn == 104)
    /* "VondaGrammar.y":438  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<Type>)(yystack.valueAt (3-(3)))); ((LinkedList<Type>)(yystack.valueAt (3-(3)))).addFirst(((Type)(yystack.valueAt (3-(1))))); };
  break;
    

  case 105:
  if (yyn == 105)
    /* "VondaGrammar.y":442  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 106:
  if (yyn == 106)
    /* "VondaGrammar.y":443  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 107:
  if (yyn == 107)
    /* "VondaGrammar.y":444  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 108:
  if (yyn == 108)
    /* "VondaGrammar.y":449  */ /* lalr1.java:489  */
    {
    yyval = new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "||").setPos((yyloc));
  };
  break;
    

  case 109:
  if (yyn == 109)
    /* "VondaGrammar.y":452  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 110:
  if (yyn == 110)
    /* "VondaGrammar.y":456  */ /* lalr1.java:489  */
    {
    yyval = new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "&&").setPos((yyloc));
  };
  break;
    

  case 111:
  if (yyn == 111)
    /* "VondaGrammar.y":459  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 112:
  if (yyn == 112)
    /* "VondaGrammar.y":463  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 113:
  if (yyn == 113)
    /* "VondaGrammar.y":464  */ /* lalr1.java:489  */
    {
    yyval = new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "|").setPos((yyloc));
  };
  break;
    

  case 114:
  if (yyn == 114)
    /* "VondaGrammar.y":470  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 115:
  if (yyn == 115)
    /* "VondaGrammar.y":471  */ /* lalr1.java:489  */
    {
    yyval = new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "^").setPos((yyloc));
  };
  break;
    

  case 116:
  if (yyn == 116)
    /* "VondaGrammar.y":477  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 117:
  if (yyn == 117)
    /* "VondaGrammar.y":478  */ /* lalr1.java:489  */
    {
    yyval = new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "&").setPos((yyloc));
  };
  break;
    

  case 118:
  if (yyn == 118)
    /* "VondaGrammar.y":484  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 119:
  if (yyn == 119)
    /* "VondaGrammar.y":485  */ /* lalr1.java:489  */
    {
    yyval = new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "==").setPos((yyloc));
  };
  break;
    

  case 120:
  if (yyn == 120)
    /* "VondaGrammar.y":488  */ /* lalr1.java:489  */
    {
    yyval = new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "!=").setPos((yyloc));
  };
  break;
    

  case 121:
  if (yyn == 121)
    /* "VondaGrammar.y":494  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 122:
  if (yyn == 122)
    /* "VondaGrammar.y":495  */ /* lalr1.java:489  */
    {
    yyval = new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "<").setPos((yyloc));
  };
  break;
    

  case 123:
  if (yyn == 123)
    /* "VondaGrammar.y":498  */ /* lalr1.java:489  */
    {
    yyval = new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), ">").setPos((yyloc));
  };
  break;
    

  case 124:
  if (yyn == 124)
    /* "VondaGrammar.y":501  */ /* lalr1.java:489  */
    {
    yyval = new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), ">=").setPos((yyloc));
  };
  break;
    

  case 125:
  if (yyn == 125)
    /* "VondaGrammar.y":504  */ /* lalr1.java:489  */
    {
    yyval = new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "<=").setPos((yyloc));
  };
  break;
    

  case 126:
  if (yyn == 126)
    /* "VondaGrammar.y":510  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 127:
  if (yyn == 127)
    /* "VondaGrammar.y":511  */ /* lalr1.java:489  */
    {
    yyval = new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "+").setPos((yyloc));
  };
  break;
    

  case 128:
  if (yyn == 128)
    /* "VondaGrammar.y":514  */ /* lalr1.java:489  */
    {
    yyval = new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "-").setPos((yyloc));
  };
  break;
    

  case 129:
  if (yyn == 129)
    /* "VondaGrammar.y":520  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 130:
  if (yyn == 130)
    /* "VondaGrammar.y":521  */ /* lalr1.java:489  */
    {
    yyval = new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "*").setPos((yyloc));
  };
  break;
    

  case 131:
  if (yyn == 131)
    /* "VondaGrammar.y":524  */ /* lalr1.java:489  */
    {
    yyval = new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "/").setPos((yyloc));
  };
  break;
    

  case 132:
  if (yyn == 132)
    /* "VondaGrammar.y":527  */ /* lalr1.java:489  */
    {
    yyval = new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "%").setPos((yyloc));
  };
  break;
    

  case 133:
  if (yyn == 133)
    /* "VondaGrammar.y":533  */ /* lalr1.java:489  */
    {
    ExpSingleValue es = new ExpSingleValue("1", "int"); es.setPos((yyloc));
    yyval = new ExpArithmetic(((RTExpression)(yystack.valueAt (2-(2)))), es, "+").setPos((yyloc));
  };
  break;
    

  case 134:
  if (yyn == 134)
    /* "VondaGrammar.y":537  */ /* lalr1.java:489  */
    {
    ExpSingleValue es = new ExpSingleValue("1", "int"); es.setPos((yyloc));
    yyval = new ExpArithmetic(((RTExpression)(yystack.valueAt (2-(2)))), es, "-").setPos((yyloc));
  };
  break;
    

  case 135:
  if (yyn == 135)
    /* "VondaGrammar.y":541  */ /* lalr1.java:489  */
    { yyval = new ExpArithmetic(((RTExpression)(yystack.valueAt (2-(2)))), null, "+").setPos((yyloc)); };
  break;
    

  case 136:
  if (yyn == 136)
    /* "VondaGrammar.y":542  */ /* lalr1.java:489  */
    { yyval = new ExpArithmetic(((RTExpression)(yystack.valueAt (2-(2)))), null, "-").setPos((yyloc)); };
  break;
    

  case 137:
  if (yyn == 137)
    /* "VondaGrammar.y":543  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 138:
  if (yyn == 138)
    /* "VondaGrammar.y":547  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 139:
  if (yyn == 139)
    /* "VondaGrammar.y":548  */ /* lalr1.java:489  */
    { new ExpCast(((Type)(yystack.valueAt (4-(2)))), ((RTExpression)(yystack.valueAt (4-(4))))).setPos((yyloc)); };
  break;
    

  case 140:
  if (yyn == 140)
    /* "VondaGrammar.y":553  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 141:
  if (yyn == 141)
    /* "VondaGrammar.y":554  */ /* lalr1.java:489  */
    { yyval = new ExpBoolean(((RTExpression)(yystack.valueAt (2-(2)))), null, "!").setPos((yyloc)); };
  break;
    

  case 142:
  if (yyn == 142)
    /* "VondaGrammar.y":555  */ /* lalr1.java:489  */
    { yyval = new ExpArithmetic(((RTExpression)(yystack.valueAt (2-(2)))), null, "~").setPos((yyloc)); };
  break;
    

  case 143:
  if (yyn == 143)
    /* "VondaGrammar.y":559  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 144:
  if (yyn == 144)
    /* "VondaGrammar.y":565  */ /* lalr1.java:489  */
    { yyval = new ExpSingleValue("null", "null").setPos((yyloc)); };
  break;
    

  case 145:
  if (yyn == 145)
    /* "VondaGrammar.y":566  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 146:
  if (yyn == 146)
    /* "VondaGrammar.y":567  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 147:
  if (yyn == 147)
    /* "VondaGrammar.y":571  */ /* lalr1.java:489  */
    { yyval = new ExpVariable((( String )(yystack.valueAt (1-(1))))).setPos((yyloc)); };
  break;
    

  case 148:
  if (yyn == 148)
    /* "VondaGrammar.y":572  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 149:
  if (yyn == 149)
    /* "VondaGrammar.y":576  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (3-(2)))); };
  break;
    

  case 150:
  if (yyn == 150)
    /* "VondaGrammar.y":577  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 151:
  if (yyn == 151)
    /* "VondaGrammar.y":581  */ /* lalr1.java:489  */
    { yyval = ((ExpSingleValue)(yystack.valueAt (1-(1)))); };
  break;
    

  case 152:
  if (yyn == 152)
    /* "VondaGrammar.y":582  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 153:
  if (yyn == 153)
    /* "VondaGrammar.y":583  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 154:
  if (yyn == 154)
    /* "VondaGrammar.y":584  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 155:
  if (yyn == 155)
    /* "VondaGrammar.y":585  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 156:
  if (yyn == 156)
    /* "VondaGrammar.y":589  */ /* lalr1.java:489  */
    { yyval = (( ExpSingleValue )(yystack.valueAt (1-(1)))); (( ExpSingleValue )(yystack.valueAt (1-(1)))).setPos((yyloc)); };
  break;
    

  case 157:
  if (yyn == 157)
    /* "VondaGrammar.y":590  */ /* lalr1.java:489  */
    { yyval = (( ExpSingleValue )(yystack.valueAt (1-(1)))); (( ExpSingleValue )(yystack.valueAt (1-(1)))).setPos((yyloc)); };
  break;
    

  case 158:
  if (yyn == 158)
    /* "VondaGrammar.y":591  */ /* lalr1.java:489  */
    { yyval = (( ExpSingleValue )(yystack.valueAt (1-(1)))); (( ExpSingleValue )(yystack.valueAt (1-(1)))).setPos((yyloc)); };
  break;
    

  case 159:
  if (yyn == 159)
    /* "VondaGrammar.y":595  */ /* lalr1.java:489  */
    {
    ExpVariable var = new ExpVariable((( String )(yystack.valueAt (4-(1))))); var.setPos(yystack.locationAt (4-(1)));
    yyval = new ExpArrayAccess(var, ((RTExpression)(yystack.valueAt (4-(3))))).setPos((yyloc));
  };
  break;
    

  case 160:
  if (yyn == 160)
    /* "VondaGrammar.y":599  */ /* lalr1.java:489  */
    { yyval = new ExpArrayAccess(((RTExpression)(yystack.valueAt (4-(1)))), ((RTExpression)(yystack.valueAt (4-(3))))).setPos((yyloc)); };
  break;
    

  case 161:
  if (yyn == 161)
    /* "VondaGrammar.y":603  */ /* lalr1.java:489  */
    { yyval = new ExpConditional(((RTExpression)(yystack.valueAt (5-(1)))), ((RTExpression)(yystack.valueAt (5-(3)))), ((RTExpression)(yystack.valueAt (5-(5))))).setPos((yyloc)); };
  break;
    

  case 162:
  if (yyn == 162)
    /* "VondaGrammar.y":631  */ /* lalr1.java:489  */
    {
    ExpVariable var = new ExpVariable((( String )(yystack.valueAt (2-(1))))); var.setPos(yystack.locationAt (2-(1)));
    yyval = new ExpAssignment(var, ((RTExpression)(yystack.valueAt (2-(2))))).setPos((yyloc));
  };
  break;
    

  case 163:
  if (yyn == 163)
    /* "VondaGrammar.y":635  */ /* lalr1.java:489  */
    { yyval = new ExpAssignment(((RTExpression)(yystack.valueAt (2-(1)))), ((RTExpression)(yystack.valueAt (2-(2))))).setPos((yyloc)); };
  break;
    

  case 164:
  if (yyn == 164)
    /* "VondaGrammar.y":636  */ /* lalr1.java:489  */
    { yyval = new ExpAssignment(((RTExpression)(yystack.valueAt (2-(1)))), ((RTExpression)(yystack.valueAt (2-(2))))).setPos((yyloc)); };
  break;
    

  case 165:
  if (yyn == 165)
    /* "VondaGrammar.y":640  */ /* lalr1.java:489  */
    {
    List<String> repr = new ArrayList<>(((LinkedList<RTExpression>)(yystack.valueAt (2-(2)))).size());
    for (int i = 0; i < ((LinkedList<RTExpression>)(yystack.valueAt (2-(2)))).size(); ++i) repr.add("");
    yyval = new ExpFieldAccess(((LinkedList<RTExpression>)(yystack.valueAt (2-(2)))), repr).setPos((yyloc)); ((LinkedList<RTExpression>)(yystack.valueAt (2-(2)))).addFirst(((RTExpression)(yystack.valueAt (2-(1)))));
  };
  break;
    

  case 166:
  if (yyn == 166)
    /* "VondaGrammar.y":648  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))); ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))).addFirst(((RTExpression)(yystack.valueAt (3-(2))))); };
  break;
    

  case 167:
  if (yyn == 167)
    /* "VondaGrammar.y":649  */ /* lalr1.java:489  */
    { yyval = new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (2-(2))))); }}; };
  break;
    

  case 168:
  if (yyn == 168)
    /* "VondaGrammar.y":653  */ /* lalr1.java:489  */
    { yyval = new ExpVariable((( String )(yystack.valueAt (1-(1))))).setPos((yyloc)); };
  break;
    

  case 169:
  if (yyn == 169)
    /* "VondaGrammar.y":654  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 170:
  if (yyn == 170)
    /* "VondaGrammar.y":655  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (3-(2)))); };
  break;
    

  case 171:
  if (yyn == 171)
    /* "VondaGrammar.y":659  */ /* lalr1.java:489  */
    { yyval = new ExpNew(new Type((( String )(yystack.valueAt (2-(2)))))).setPos((yyloc)); };
  break;
    

  case 172:
  if (yyn == 172)
    /* "VondaGrammar.y":660  */ /* lalr1.java:489  */
    {
    yyval = new ExpNew(new Type((( String )(yystack.valueAt (4-(2))))), new LinkedList<>()).setPos((yyloc));
  };
  break;
    

  case 173:
  if (yyn == 173)
    /* "VondaGrammar.y":663  */ /* lalr1.java:489  */
    {
    yyval = new ExpNew(new Type((( String )(yystack.valueAt (5-(2))))), ((LinkedList<RTExpression>)(yystack.valueAt (5-(4))))).setPos((yyloc));
  };
  break;
    

  case 174:
  if (yyn == 174)
    /* "VondaGrammar.y":666  */ /* lalr1.java:489  */
    {
    yyval = new ExpNew(((Type)(yystack.valueAt (4-(2)))), new LinkedList<>()).setPos((yyloc));
  };
  break;
    

  case 175:
  if (yyn == 175)
    /* "VondaGrammar.y":669  */ /* lalr1.java:489  */
    {
    yyval = new ExpNew(((Type)(yystack.valueAt (5-(2)))), ((LinkedList<RTExpression>)(yystack.valueAt (5-(4))))).setPos((yyloc));
  };
  break;
    

  case 176:
  if (yyn == 176)
    /* "VondaGrammar.y":672  */ /* lalr1.java:489  */
    {
    yyval = new ExpNew(new Type("Array", new Type((( String )(yystack.valueAt (5-(2)))))),
                    new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (5-(4))))); }}).setPos((yyloc));
  };
  break;
    

  case 177:
  if (yyn == 177)
    /* "VondaGrammar.y":679  */ /* lalr1.java:489  */
    {
    yyval = new ExpLambda(((LinkedList)(yystack.valueAt (4-(2)))), ((RTExpression)(yystack.valueAt (4-(4))))).setPos((yyloc));
  };
  break;
    

  case 178:
  if (yyn == 178)
    /* "VondaGrammar.y":682  */ /* lalr1.java:489  */
    {
    yyval = new ExpLambda(((LinkedList)(yystack.valueAt (4-(2)))), ((StatAbstractBlock)(yystack.valueAt (4-(4))))).setPos((yyloc));
  };
  break;
    

  case 179:
  if (yyn == 179)
    /* "VondaGrammar.y":685  */ /* lalr1.java:489  */
    {
    yyval = new ExpLambda(new LinkedList<>(), ((RTExpression)(yystack.valueAt (4-(4))))).setPos((yyloc));
  };
  break;
    

  case 180:
  if (yyn == 180)
    /* "VondaGrammar.y":688  */ /* lalr1.java:489  */
    {
    yyval = new ExpLambda(new LinkedList<>(), ((StatAbstractBlock)(yystack.valueAt (4-(4))))).setPos((yyloc));
  };
  break;
    

  case 181:
  if (yyn == 181)
    /* "VondaGrammar.y":695  */ /* lalr1.java:489  */
    {
    yyval = new ExpDialogueAct(((RTExpression)(yystack.valueAt (5-(2)))), ((RTExpression)(yystack.valueAt (5-(4)))), new LinkedList<RTExpression>()).setPos((yyloc));
  };
  break;
    

  case 182:
  if (yyn == 182)
    /* "VondaGrammar.y":698  */ /* lalr1.java:489  */
    {
    yyval = new ExpDialogueAct(((RTExpression)(yystack.valueAt (6-(2)))), ((RTExpression)(yystack.valueAt (6-(4)))), ((LinkedList<RTExpression>)(yystack.valueAt (6-(5))))).setPos((yyloc));
  };
  break;
    

  case 183:
  if (yyn == 183)
    /* "VondaGrammar.y":704  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (3-(2)))); };
  break;
    

  case 184:
  if (yyn == 184)
    /* "VondaGrammar.y":705  */ /* lalr1.java:489  */
    { yyval = new ExpVariable((( String )(yystack.valueAt (1-(1))))).setPos((yyloc)); };
  break;
    

  case 185:
  if (yyn == 185)
    /* "VondaGrammar.y":706  */ /* lalr1.java:489  */
    { yyval = (( ExpSingleValue )(yystack.valueAt (1-(1)))); };
  break;
    

  case 186:
  if (yyn == 186)
    /* "VondaGrammar.y":707  */ /* lalr1.java:489  */
    { yyval = new ExpSingleValue((( String )(yystack.valueAt (1-(1)))), "String").setPos((yyloc)); };
  break;
    

  case 187:
  if (yyn == 187)
    /* "VondaGrammar.y":711  */ /* lalr1.java:489  */
    {
    yyval = ((LinkedList<RTExpression>)(yystack.valueAt (5-(5)))); ((LinkedList<RTExpression>)(yystack.valueAt (5-(5)))).addFirst(((RTExpression)(yystack.valueAt (5-(4))))); ((LinkedList<RTExpression>)(yystack.valueAt (5-(5)))).addFirst(((RTExpression)(yystack.valueAt (5-(2)))));
  };
  break;
    

  case 188:
  if (yyn == 188)
    /* "VondaGrammar.y":714  */ /* lalr1.java:489  */
    { yyval = new LinkedList<RTExpression>(); };
  break;
    


/* "VondaGrammar.java":1997  */ /* lalr1.java:489  */
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

  private static final short yypact_ninf_ = -306;
  private static final short yytable_ninf_ = -148;

  /* YYPACT[STATE-NUM] -- Index in YYTABLE of the portion describing
   STATE-NUM.  */
  private static final short yypact_[] = yypact_init();
  private static final short[] yypact_init()
  {
    return new short[]
    {
     685,   277,   -39,   -18,   376,    21,    43,   849,   -10,    65,
      76,   103,   109,  -306,   112,  -306,  -306,   305,   137,   208,
     213,   227,  -306,  -306,   194,  -306,   754,  1192,   113,   286,
     191,     3,   685,   685,  -306,  -306,  -306,  -306,  -306,  -306,
    -306,  -306,  -306,  -306,  -306,   685,   685,   249,  -306,   237,
     259,   267,   271,  -306,  -306,    40,   291,    80,  -306,  -306,
     294,  -306,  -306,  -306,   293,   304,   309,  -306,  -306,    86,
     337,   211,   335,    18,  1192,   336,   306,   348,  1192,  -306,
    1205,  1205,   261,  -306,  1238,  1251,  1251,  1205,  1205,  -306,
     -38,   355,   372,   353,   381,   383,   358,    39,   333,   366,
    -306,  -306,  -306,  -306,  -306,   267,   271,   394,  -306,  -306,
     394,  1192,  1192,  1192,  1192,  1192,  1192,   432,   844,   882,
     897,   113,  -306,   405,  -306,  -306,   800,   401,  -306,   407,
      17,   274,   414,  -306,  -306,  -306,  1192,   415,  -306,   314,
     685,   685,  -306,  -306,  -306,  -306,   319,   421,  -306,   231,
     -12,  -306,  1192,  1192,  1192,  -306,  -306,  1192,  1192,  -306,
    -306,  -306,  -306,  -306,   935,  1192,   416,  -306,   412,   423,
     215,   254,  1192,   949,   427,    29,   428,   429,   987,   897,
    1001,    50,   192,  -306,  -306,  -306,  -306,   284,   422,  -306,
    -306,  -306,  -306,  -306,  1192,  1251,  1251,  1251,  1251,  1251,
    1251,  1251,  1251,  1251,  1251,  1251,  1251,  1251,  1251,  1251,
    1251,    59,    60,    73,   100,   -31,   -29,  -306,   132,  1039,
     430,   430,   425,   436,    62,   431,  1054,   413,  -306,    87,
     434,   433,  -306,  -306,   225,  -306,   437,    19,   286,   240,
    -306,  -306,   246,   438,  -306,   253,   435,   439,  1192,  -306,
     267,   184,    10,    14,    26,    30,  -306,  1192,  -306,  -306,
     442,  1192,    34,  1091,    41,   245,   849,   336,  -306,  -306,
     440,    69,   207,  -306,   443,   430,  1251,   -40,   372,   353,
     381,   383,   358,    39,    39,   333,   333,   333,   333,   366,
     366,  -306,  -306,  -306,   430,  1192,   430,   849,  -306,  -306,
    -306,   441,   453,   464,   256,  -306,  -306,  -306,   356,  1284,
    1284,  -306,   447,  -306,   113,  -306,  -306,  -306,   363,   325,
     430,   316,   316,   258,   316,   316,  -306,   102,  -306,  -306,
    -306,  -306,  -306,  -306,   123,  -306,   127,  1192,   849,   181,
    1125,  1192,   483,  -306,  -306,  1192,  -306,  -306,  -306,  -306,
    1192,  -306,   183,  -306,  -306,  -306,  1159,  1159,  -306,   441,
    -306,  -306,  -306,  -306,  -306,   286,   446,  -306,  -306,  -306,
    -306,   316,   316,  -306,  -306,  -306,  -306,   849,   185,  -306,
     849,   849,   186,   187,   849,  -306,   413,   430,  -306,   413,
    -306,   413,  -306,   445,  -306,  -306,  -306,  -306,   849,  -306,
    -306,   849,   849,  -306,  -306,   286,  -306,  -306,  -306,   448,
    -306
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
       0,     0,     0,    11,     0,    10,     9,     0,     0,     0,
       0,     0,   156,   157,   101,   158,     0,     0,     0,     0,
       0,     0,     8,     8,    15,    18,    20,    24,    25,    26,
      21,    23,    22,    27,    28,     8,     8,     0,    19,   154,
       0,     0,     0,   150,   151,   152,     0,   153,   148,   155,
       0,    38,    40,    41,     0,     0,     0,    42,    61,   147,
       0,   101,     0,     0,     0,    13,   171,     0,     0,   144,
       0,     0,   147,    36,     0,     0,     0,     0,     0,   154,
       0,   107,   109,   111,   112,   114,   116,   118,   121,   126,
     138,   129,   137,   140,   143,   145,   146,   152,   105,   106,
     153,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,   162,   101,    32,    29,    33,     0,    30,     0,
       0,   101,     0,   185,   186,   184,     0,     0,     1,   101,
       8,     8,     5,     4,     7,     3,   101,     0,    17,     0,
       0,   165,     0,     0,     0,   164,    16,     0,     0,   163,
      39,    58,    59,    60,     0,     0,     0,    65,     0,     0,
       0,   101,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,   147,   134,   152,   153,   133,   147,     0,   135,
     136,   141,   142,    37,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,    35,   147,     0,
      94,     0,     0,     0,    96,    97,     0,    68,   100,     0,
     103,     0,    34,    31,     0,   149,     0,     0,     0,     0,
       6,     2,     0,     0,    66,     0,     0,   168,     0,   169,
     167,     0,     0,     0,     0,     0,    94,     0,    62,    67,
       0,     0,     0,     0,     0,     0,     0,    13,    12,   172,
       0,    71,     0,   174,     0,     0,     0,     0,   108,   110,
     113,   115,   117,   119,   120,   124,   125,   122,   123,   127,
     128,   130,   131,   132,     0,     0,     0,     0,    89,    88,
      84,     0,     0,     0,     0,    79,    80,    95,     0,     0,
       0,    69,     0,   159,     0,   102,    81,   183,     0,   101,
       0,     0,     0,     0,     0,     0,    63,     0,   166,   160,
      91,    90,    93,    92,     0,    64,     0,     0,     0,     0,
       0,     0,    43,    14,   173,     0,   176,   175,    54,   139,
       0,    57,     0,    55,    45,    86,     0,     0,    85,     0,
      98,    99,    70,   104,   181,     0,     0,    83,    82,    75,
      76,     0,     0,    77,    78,   170,    46,     0,     0,    50,
       0,     0,     0,     0,     0,    72,   161,     0,   180,   179,
     178,   177,    87,     0,   182,    73,    74,    52,     0,    48,
      49,     0,     0,    44,    56,     0,    51,    47,    53,   188,
     187
    };
  }

/* YYPGOTO[NTERM-NUM].  */
  private static final short yypgoto_[] = yypgoto_init();
  private static final short[] yypgoto_init()
  {
    return new short[]
    {
    -306,   -24,  -306,  -306,   229,    52,  -306,   239,   373,  -306,
    -306,   384,  -306,  -306,  -306,  -306,  -306,  -306,  -306,   -16,
      -2,  -179,   467,  -306,  -305,  -201,  -306,     5,   121,   230,
     188,   493,  -306,   308,   310,   303,   313,   317,   234,   217,
     236,   106,   -46,  -306,  -306,  -306,   128,   144,  -306,  -306,
       0,  -306,   283,    16,   257,  -306,  -306,  -306,  -306,  -236,
      95
    };
  }

/* YYDEFGOTO[NTERM-NUM].  */
  private static final short yydefgoto_[] = yydefgoto_init();
  private static final short[] yydefgoto_init()
  {
    return new short[]
    {
      -1,    30,    31,    32,   177,    33,   126,    34,   127,    35,
      36,    37,    38,    39,    40,    41,    42,    43,    44,    45,
     122,   270,    46,    47,   369,   221,    48,    89,   222,    50,
     231,   224,    91,    92,    93,    94,    95,    96,    97,    98,
      99,   100,   101,   102,   103,   104,   105,   106,    53,    54,
     107,   108,   109,   110,   151,   250,    58,   225,    59,   137,
     366
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
      55,   274,   318,    62,   193,    49,   350,    55,   142,   143,
     128,   298,    49,   299,     8,   140,    57,   370,   303,   373,
     374,   144,   145,    57,    63,   194,    55,   194,   247,     8,
      71,    49,    55,    55,   194,   248,   194,    49,    49,   189,
     190,   322,    57,   139,   325,    55,    55,   312,    57,    57,
      49,    49,   330,   155,    28,   159,   331,   173,   171,    70,
     172,    57,    57,    67,   317,   235,   395,   396,   332,   169,
     202,   203,   333,   153,   154,   194,   337,   266,   125,   194,
     184,   184,   194,   340,   194,   184,   184,   184,   184,    68,
     119,   194,   204,   205,   194,   194,   185,   185,   275,   194,
     355,   185,   185,   185,   185,   155,   194,   294,   159,   295,
     128,   309,    73,   157,   158,   194,   240,   241,   345,   115,
     116,   296,   372,    74,   194,   194,    55,   194,    51,   393,
     119,    49,   117,   164,   194,    51,   119,   165,   194,   313,
      55,    55,    57,    75,    52,    49,    49,   246,   297,    76,
     375,    52,   194,   131,    51,   249,    57,    57,   392,    78,
      51,    51,   291,   292,   293,   194,   385,   194,   260,   409,
      52,   376,  -101,    51,    51,   377,    52,    52,   125,   164,
     300,   301,   119,   120,   111,   121,   183,   186,   194,    52,
      52,   138,   194,   191,   192,   184,   184,   184,   184,   184,
     184,   184,   184,   184,   184,   184,   184,   184,   184,   184,
     184,   185,   185,   185,   185,   185,   185,   185,   185,   185,
     185,   185,   185,   185,   185,   185,   185,   115,   116,   380,
     349,   387,   246,   398,   401,   402,   329,  -147,    72,   164,
     117,   118,    77,   165,   119,   120,   194,   121,   194,   194,
     194,   194,   194,   167,    51,   112,   129,   259,   132,   346,
     113,   119,   168,   246,   121,   119,    55,   244,    51,    51,
      52,    49,   194,   244,   114,   119,   184,   147,   245,   148,
     319,   119,    57,    56,    52,    52,   319,   244,   320,   146,
      56,   341,   185,   319,   321,   119,   308,    55,   319,   149,
     261,   324,    49,   174,   276,   168,   371,   121,   164,    56,
     150,   119,   165,    57,   188,    56,    56,    60,   342,    61,
      12,    79,   152,   133,   134,   168,   135,   121,    56,    56,
     136,   164,  -101,   156,   119,   120,   160,   121,    55,   161,
      80,    81,    22,    49,    23,    82,    25,    83,   223,   354,
     162,   230,    84,   178,    57,   163,   129,   179,   367,   121,
      26,   239,   166,    85,    86,   168,   242,   121,    87,    88,
     168,    29,   121,   300,   301,   170,   168,    55,   121,   176,
      55,    55,    49,   195,    55,    49,    49,   200,   201,    49,
     379,   206,   207,    57,    51,   180,    57,    57,    55,   196,
      57,    55,    55,    49,   358,   359,    49,    49,   197,    56,
      52,   364,   365,    64,    57,    65,    66,    57,    57,   285,
     286,   287,   288,    56,    56,    51,   208,   209,   210,   397,
     360,   361,   399,   400,   283,   284,   403,   198,   115,   116,
     199,    52,   289,   290,   119,    10,   233,   234,  -147,   304,
     406,   117,   164,   407,   408,   119,   120,   236,   121,   305,
     306,   243,   238,   257,   228,   258,    51,   265,   267,   223,
     276,   268,   223,   307,    26,   223,   308,   326,   194,   356,
     310,   319,    52,   314,   335,   323,   164,   315,   344,   316,
     357,   347,   362,   384,   394,   405,   343,   365,   141,   232,
     280,   217,   363,   278,   410,    51,   279,   328,    51,    51,
      90,   281,    51,     0,   348,     0,   282,     0,     0,     0,
     130,    52,     0,     0,    52,    52,    51,     0,    52,    51,
      51,   223,     0,   351,     0,   353,     0,     0,     0,     0,
       0,     0,    52,     0,   230,    52,    52,     0,     0,    56,
       0,     0,     0,   223,     0,     0,     0,     0,     0,   305,
     368,   368,     0,   368,   368,     0,     0,   175,     0,     0,
       0,   181,     0,     0,     0,     0,     0,   130,     0,     0,
      56,     0,     0,     0,     0,     0,     0,     0,     0,   223,
       0,     0,     0,     0,     0,   388,   390,     0,     0,     0,
       0,     0,     0,     0,   211,   212,   213,   214,   215,   216,
     368,   368,   227,   229,     0,     0,     0,     0,     0,     0,
       0,    56,     0,     0,     0,     0,   404,     0,     0,   237,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,   251,   252,   253,     0,     0,
     254,   255,     0,     0,     0,     0,     0,     0,   229,     0,
      56,     0,     0,    56,    56,   262,   264,    56,     0,     0,
       0,   271,   272,   271,     0,     0,     0,     0,     0,     0,
       0,    56,     0,     0,    56,    56,     0,   277,     1,     2,
       3,     4,     5,     6,     7,     0,     8,     9,    10,    11,
      12,     0,    13,    14,    15,    16,    17,    18,    19,    20,
      21,     0,   130,     0,     0,     0,     0,     0,     0,   271,
       0,     0,    22,     0,    23,    24,    25,     0,     0,    26,
       0,     0,    27,     0,     0,     0,    28,     0,     0,     0,
       0,   327,     0,     0,     0,     0,     0,     0,     0,     0,
     334,    29,     0,     0,   336,     0,   339,     1,     2,     3,
       4,     5,     6,     7,     0,     8,     9,    10,     0,    12,
       0,     0,    14,     0,     0,    17,    18,    19,    20,    21,
       0,     0,     0,     0,     0,     0,     0,     0,   352,     0,
       0,    22,     0,    23,   123,    25,     0,     0,    26,   124,
       0,    27,     0,     1,     2,     3,     4,     5,     6,     7,
       0,     8,     9,    10,     0,    12,     0,     0,    14,     0,
      29,    17,    18,    19,    20,    21,     0,     0,     0,     0,
     378,     0,     0,   382,   383,     0,     0,    22,   271,    23,
     123,    25,     0,   386,    26,     0,     0,    27,     0,   389,
     391,     0,     1,     2,     3,     4,     5,     6,     7,    12,
      79,     9,    10,     0,    12,     0,    29,    14,     0,     0,
      17,    18,    19,    20,    21,     0,     0,     0,     0,    80,
      81,    22,     0,    23,   218,    25,    22,     0,    23,    69,
      25,   219,   220,    26,     0,     0,    27,    12,    79,     0,
       0,     0,    85,    86,     0,     0,     0,    87,    88,     0,
      29,     0,    12,    79,     0,    29,     0,    80,    81,    22,
       0,    23,    82,    25,     0,     0,   226,     0,     0,    84,
       0,     0,    80,    81,    22,     0,    23,    82,    25,     0,
      85,    86,     0,     0,    84,    87,    88,     0,    29,   228,
      12,    79,     0,     0,     0,    85,    86,     0,     0,     0,
      87,    88,     0,    29,    12,    79,     0,     0,     0,     0,
      80,    81,    22,     0,    23,    82,    25,     0,     0,     0,
       0,     0,   219,   256,    80,    81,    22,     0,    23,    82,
      25,   263,     0,    85,    86,     0,    84,     0,    87,    88,
       0,    29,    12,    79,     0,     0,     0,    85,    86,     0,
       0,     0,    87,    88,     0,    29,    12,    79,     0,     0,
       0,     0,    80,    81,    22,     0,    23,    82,    25,     0,
       0,     0,     0,     0,    84,   269,    80,    81,    22,     0,
      23,    82,    25,     0,     0,    85,    86,     0,    84,   273,
      87,    88,     0,    29,    12,    79,     0,     0,     0,    85,
      86,     0,     0,     0,    87,    88,     0,    29,     0,    12,
      79,     0,     0,     0,    80,    81,    22,     0,    23,   218,
      25,     0,     0,     0,     0,     0,    84,   302,     0,    80,
      81,    22,     0,    23,    82,    25,     0,    85,    86,   311,
       0,    84,    87,    88,     0,    29,    12,    79,     0,     0,
       0,     0,    85,    86,     0,     0,     0,    87,    88,     0,
      29,     0,     0,     0,     0,     0,    80,    81,    22,     0,
      23,    82,    25,     0,     0,     0,     0,     0,    84,   338,
      12,    79,     0,     0,     0,     0,     0,     0,     0,    85,
      86,     0,     0,     0,    87,    88,     0,    29,     0,     0,
      80,    81,    22,     0,    23,    82,    25,     0,     0,     0,
       0,     0,    84,   381,    12,    79,     0,     0,     0,     0,
       0,     0,     0,    85,    86,     0,     0,     0,    87,    88,
       0,    29,     0,     0,    80,    81,    22,     0,    23,    82,
      25,     0,     0,    26,     0,     0,    84,    12,    79,     0,
       0,     0,     0,     0,     0,     0,     0,    85,    86,     0,
      12,    79,    87,    88,     0,    29,     0,    80,    81,    22,
       0,    23,    82,    25,     0,     0,     0,     0,     0,    84,
      80,    81,    22,     0,    23,   182,    25,     0,     0,     0,
      85,    86,    27,    12,    79,    87,    88,     0,    29,     0,
       0,     0,     0,    85,    86,     0,    12,    79,    87,    88,
       0,    29,     0,    80,    81,    22,     0,    23,   187,    25,
       0,     0,     0,     0,     0,    84,    80,    81,    22,     0,
      23,   182,    25,     0,     0,     0,    85,    86,    84,    12,
      79,    87,    88,     0,    29,     0,     0,     0,     0,    85,
      86,     0,     0,     0,    87,    88,     0,    29,     0,    80,
      81,    22,     0,    23,    82,    25,     0,     0,     0,     0,
       0,   219,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,    85,    86,     0,     0,     0,    87,    88,     0,
      29
    };
  }

private static final short yycheck_[] = yycheck_init();
  private static final short[] yycheck_init()
  {
    return new short[]
    {
       0,   180,   238,    42,    42,     0,    46,     7,    32,    33,
      26,    42,     7,    42,    11,    31,     0,   322,   219,   324,
     325,    45,    46,     7,    42,    65,    26,    65,    40,    11,
      40,    26,    32,    33,    65,    47,    65,    32,    33,    85,
      86,   242,    26,    40,   245,    45,    46,   226,    32,    33,
      45,    46,    42,    55,    51,    57,    42,    73,    40,     7,
      42,    45,    46,    42,    45,    48,   371,   372,    42,    71,
      31,    32,    42,    33,    34,    65,    42,    48,    26,    65,
      80,    81,    65,    42,    65,    85,    86,    87,    88,    46,
      50,    65,    53,    54,    65,    65,    80,    81,    48,    65,
     301,    85,    86,    87,    88,   107,    65,    48,   110,    49,
     126,    49,    47,    33,    34,    65,   140,   141,    49,    33,
      34,    48,   323,    47,    65,    65,   126,    65,     0,   365,
      50,   126,    46,    47,    65,     7,    50,    51,    65,    52,
     140,   141,   126,    40,     0,   140,   141,   149,    48,    40,
      48,     7,    65,    40,    26,   150,   140,   141,   359,    47,
      32,    33,   208,   209,   210,    65,   345,    65,   170,   405,
      26,    48,    40,    45,    46,    48,    32,    33,   126,    47,
      48,    49,    50,    51,    47,    53,    80,    81,    65,    45,
      46,     0,    65,    87,    88,   195,   196,   197,   198,   199,
     200,   201,   202,   203,   204,   205,   206,   207,   208,   209,
     210,   195,   196,   197,   198,   199,   200,   201,   202,   203,
     204,   205,   206,   207,   208,   209,   210,    33,    34,    48,
     276,    48,   234,    48,    48,    48,    52,    43,     8,    47,
      46,    47,    12,    51,    50,    51,    65,    53,    65,    65,
      65,    65,    65,    42,   126,    47,    26,    42,    28,    52,
      47,    50,    51,   265,    53,    50,   266,    42,   140,   141,
     126,   266,    65,    42,    47,    50,   276,    47,    47,    42,
      40,    50,   266,     0,   140,   141,    40,    42,    48,    40,
       7,    46,   276,    40,    48,    50,    40,   297,    40,    40,
      46,    48,   297,    73,    48,    51,    48,    53,    47,    26,
      43,    50,    51,   297,    84,    32,    33,    40,   266,    42,
      15,    16,    51,    37,    38,    51,    40,    53,    45,    46,
      44,    47,    48,    42,    50,    51,    42,    53,   338,    46,
      35,    36,    37,   338,    39,    40,    41,    42,   118,   297,
      46,   121,    47,    47,   338,    46,   126,    51,    42,    53,
      44,    47,    25,    58,    59,    51,    47,    53,    63,    64,
      51,    66,    53,    48,    49,    40,    51,   377,    53,    43,
     380,   381,   377,    28,   384,   380,   381,    29,    30,   384,
     338,    58,    59,   377,   266,    47,   380,   381,   398,    27,
     384,   401,   402,   398,    48,    49,   401,   402,    55,   126,
     266,    48,    49,    37,   398,    39,    40,   401,   402,   202,
     203,   204,   205,   140,   141,   297,    60,    61,    62,   377,
     309,   310,   380,   381,   200,   201,   384,    56,    33,    34,
      57,   297,   206,   207,    50,    13,    45,    40,    43,   219,
     398,    46,    47,   401,   402,    50,    51,    43,    53,   220,
     221,    40,    47,    47,    52,    42,   338,    40,    40,   239,
      48,    42,   242,    48,    44,   245,    40,    42,    65,    26,
      49,    40,   338,    49,    42,    47,    47,    54,    48,    52,
      26,    48,    45,    10,    48,    50,   267,    49,    31,   126,
     197,   117,   314,   195,   409,   377,   196,   250,   380,   381,
      17,   198,   384,    -1,   275,    -1,   199,    -1,    -1,    -1,
      27,   377,    -1,    -1,   380,   381,   398,    -1,   384,   401,
     402,   301,    -1,   294,    -1,   296,    -1,    -1,    -1,    -1,
      -1,    -1,   398,    -1,   314,   401,   402,    -1,    -1,   266,
      -1,    -1,    -1,   323,    -1,    -1,    -1,    -1,    -1,   320,
     321,   322,    -1,   324,   325,    -1,    -1,    74,    -1,    -1,
      -1,    78,    -1,    -1,    -1,    -1,    -1,    84,    -1,    -1,
     297,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,   359,
      -1,    -1,    -1,    -1,    -1,   356,   357,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,   111,   112,   113,   114,   115,   116,
     371,   372,   119,   120,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,   338,    -1,    -1,    -1,    -1,   387,    -1,    -1,   136,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,   152,   153,   154,    -1,    -1,
     157,   158,    -1,    -1,    -1,    -1,    -1,    -1,   165,    -1,
     377,    -1,    -1,   380,   381,   172,   173,   384,    -1,    -1,
      -1,   178,   179,   180,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,   398,    -1,    -1,   401,   402,    -1,   194,     3,     4,
       5,     6,     7,     8,     9,    -1,    11,    12,    13,    14,
      15,    -1,    17,    18,    19,    20,    21,    22,    23,    24,
      25,    -1,   219,    -1,    -1,    -1,    -1,    -1,    -1,   226,
      -1,    -1,    37,    -1,    39,    40,    41,    -1,    -1,    44,
      -1,    -1,    47,    -1,    -1,    -1,    51,    -1,    -1,    -1,
      -1,   248,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
     257,    66,    -1,    -1,   261,    -1,   263,     3,     4,     5,
       6,     7,     8,     9,    -1,    11,    12,    13,    -1,    15,
      -1,    -1,    18,    -1,    -1,    21,    22,    23,    24,    25,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,   295,    -1,
      -1,    37,    -1,    39,    40,    41,    -1,    -1,    44,    45,
      -1,    47,    -1,     3,     4,     5,     6,     7,     8,     9,
      -1,    11,    12,    13,    -1,    15,    -1,    -1,    18,    -1,
      66,    21,    22,    23,    24,    25,    -1,    -1,    -1,    -1,
     337,    -1,    -1,   340,   341,    -1,    -1,    37,   345,    39,
      40,    41,    -1,   350,    44,    -1,    -1,    47,    -1,   356,
     357,    -1,     3,     4,     5,     6,     7,     8,     9,    15,
      16,    12,    13,    -1,    15,    -1,    66,    18,    -1,    -1,
      21,    22,    23,    24,    25,    -1,    -1,    -1,    -1,    35,
      36,    37,    -1,    39,    40,    41,    37,    -1,    39,    40,
      41,    47,    48,    44,    -1,    -1,    47,    15,    16,    -1,
      -1,    -1,    58,    59,    -1,    -1,    -1,    63,    64,    -1,
      66,    -1,    15,    16,    -1,    66,    -1,    35,    36,    37,
      -1,    39,    40,    41,    -1,    -1,    44,    -1,    -1,    47,
      -1,    -1,    35,    36,    37,    -1,    39,    40,    41,    -1,
      58,    59,    -1,    -1,    47,    63,    64,    -1,    66,    52,
      15,    16,    -1,    -1,    -1,    58,    59,    -1,    -1,    -1,
      63,    64,    -1,    66,    15,    16,    -1,    -1,    -1,    -1,
      35,    36,    37,    -1,    39,    40,    41,    -1,    -1,    -1,
      -1,    -1,    47,    48,    35,    36,    37,    -1,    39,    40,
      41,    42,    -1,    58,    59,    -1,    47,    -1,    63,    64,
      -1,    66,    15,    16,    -1,    -1,    -1,    58,    59,    -1,
      -1,    -1,    63,    64,    -1,    66,    15,    16,    -1,    -1,
      -1,    -1,    35,    36,    37,    -1,    39,    40,    41,    -1,
      -1,    -1,    -1,    -1,    47,    48,    35,    36,    37,    -1,
      39,    40,    41,    -1,    -1,    58,    59,    -1,    47,    48,
      63,    64,    -1,    66,    15,    16,    -1,    -1,    -1,    58,
      59,    -1,    -1,    -1,    63,    64,    -1,    66,    -1,    15,
      16,    -1,    -1,    -1,    35,    36,    37,    -1,    39,    40,
      41,    -1,    -1,    -1,    -1,    -1,    47,    48,    -1,    35,
      36,    37,    -1,    39,    40,    41,    -1,    58,    59,    45,
      -1,    47,    63,    64,    -1,    66,    15,    16,    -1,    -1,
      -1,    -1,    58,    59,    -1,    -1,    -1,    63,    64,    -1,
      66,    -1,    -1,    -1,    -1,    -1,    35,    36,    37,    -1,
      39,    40,    41,    -1,    -1,    -1,    -1,    -1,    47,    48,
      15,    16,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    58,
      59,    -1,    -1,    -1,    63,    64,    -1,    66,    -1,    -1,
      35,    36,    37,    -1,    39,    40,    41,    -1,    -1,    -1,
      -1,    -1,    47,    48,    15,    16,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    58,    59,    -1,    -1,    -1,    63,    64,
      -1,    66,    -1,    -1,    35,    36,    37,    -1,    39,    40,
      41,    -1,    -1,    44,    -1,    -1,    47,    15,    16,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    58,    59,    -1,
      15,    16,    63,    64,    -1,    66,    -1,    35,    36,    37,
      -1,    39,    40,    41,    -1,    -1,    -1,    -1,    -1,    47,
      35,    36,    37,    -1,    39,    40,    41,    -1,    -1,    -1,
      58,    59,    47,    15,    16,    63,    64,    -1,    66,    -1,
      -1,    -1,    -1,    58,    59,    -1,    15,    16,    63,    64,
      -1,    66,    -1,    35,    36,    37,    -1,    39,    40,    41,
      -1,    -1,    -1,    -1,    -1,    47,    35,    36,    37,    -1,
      39,    40,    41,    -1,    -1,    -1,    58,    59,    47,    15,
      16,    63,    64,    -1,    66,    -1,    -1,    -1,    -1,    58,
      59,    -1,    -1,    -1,    63,    64,    -1,    66,    -1,    35,
      36,    37,    -1,    39,    40,    41,    -1,    -1,    -1,    -1,
      -1,    47,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    58,    59,    -1,    -1,    -1,    63,    64,    -1,
      66
    };
  }

/* YYSTOS[STATE-NUM] -- The (internal number of the) accessing
   symbol of state STATE-NUM.  */
  private static final byte yystos_[] = yystos_init();
  private static final byte[] yystos_init()
  {
    return new byte[]
    {
       0,     3,     4,     5,     6,     7,     8,     9,    11,    12,
      13,    14,    15,    17,    18,    19,    20,    21,    22,    23,
      24,    25,    37,    39,    40,    41,    44,    47,    51,    66,
      68,    69,    70,    72,    74,    76,    77,    78,    79,    80,
      81,    82,    83,    84,    85,    86,    89,    90,    93,    94,
      96,   113,   114,   115,   116,   117,   119,   120,   123,   125,
      40,    42,    42,    42,    37,    39,    40,    42,    46,    40,
      72,    40,    96,    47,    47,    40,    40,    96,    47,    16,
      35,    36,    40,    42,    47,    58,    59,    63,    64,    94,
      98,    99,   100,   101,   102,   103,   104,   105,   106,   107,
     108,   109,   110,   111,   112,   113,   114,   117,   118,   119,
     120,    47,    47,    47,    47,    33,    34,    46,    47,    50,
      51,    53,    87,    40,    45,    72,    73,    75,    86,    96,
      98,    40,    96,    37,    38,    40,    44,   126,     0,    40,
      86,    89,    68,    68,    68,    68,    40,    96,    42,    40,
      43,   121,    51,    33,    34,    87,    42,    33,    34,    87,
      42,    46,    46,    46,    47,    51,    25,    42,    51,    87,
      40,    40,    42,    86,    96,    98,    43,    71,    47,    51,
      47,    98,    40,   108,   117,   120,   108,    40,    96,   109,
     109,   108,   108,    42,    65,    28,    27,    55,    56,    57,
      29,    30,    31,    32,    53,    54,    58,    59,    60,    61,
      62,    98,    98,    98,    98,    98,    98,    78,    40,    47,
      48,    92,    95,    96,    98,   124,    44,    98,    52,    98,
      96,    97,    75,    45,    40,    48,    43,    98,    47,    47,
      68,    68,    47,    40,    42,    47,    87,    40,    47,    94,
     122,    98,    98,    98,    98,    98,    48,    47,    42,    42,
      87,    46,    98,    42,    98,    40,    48,    40,    42,    48,
      88,    98,    98,    48,    88,    48,    48,    98,   100,   101,
     102,   103,   104,   105,   105,   106,   106,   106,   106,   107,
     107,   109,   109,   109,    48,    49,    48,    48,    42,    42,
      48,    49,    48,    92,    96,    74,    74,    48,    40,    49,
      49,    45,    88,    52,    49,    54,    52,    45,   126,    40,
      48,    48,    92,    47,    48,    92,    42,    98,   121,    52,
      42,    42,    42,    42,    98,    42,    98,    42,    48,    98,
      42,    46,    72,    71,    48,    49,    52,    48,    74,   109,
      46,    74,    98,    74,    72,    92,    26,    26,    48,    49,
      95,    95,    45,    97,    48,    49,   127,    42,    74,    91,
      91,    48,    92,    91,    91,    48,    48,    48,    98,    72,
      48,    48,    98,    98,    10,    88,    98,    48,    74,    98,
      74,    98,    92,   126,    48,    91,    91,    72,    48,    72,
      72,    48,    48,    72,    74,    50,    72,    72,    72,   126,
     127
    };
  }

/* YYR1[YYN] -- Symbol number of symbol that rule YYN derives.  */
  private static final byte yyr1_[] = yyr1_init();
  private static final byte[] yyr1_init()
  {
    return new byte[]
    {
       0,    67,    68,    68,    68,    68,    68,    68,    68,    69,
      69,    69,    70,    71,    71,    72,    72,    72,    72,    72,
      72,    72,    72,    72,    72,    72,    72,    72,    72,    73,
      73,    74,    74,    75,    75,    76,    77,    77,    77,    77,
      77,    77,    77,    78,    78,    79,    79,    80,    80,    80,
      80,    80,    80,    80,    81,    82,    83,    84,    85,    85,
      85,    85,    86,    86,    86,    86,    86,    86,    87,    87,
      87,    88,    88,    89,    89,    89,    89,    89,    89,    89,
      89,    90,    91,    91,    92,    92,    92,    92,    93,    93,
      93,    93,    93,    93,    94,    94,    95,    95,    95,    95,
      96,    96,    96,    97,    97,    98,    98,    98,    99,    99,
     100,   100,   101,   101,   102,   102,   103,   103,   104,   104,
     104,   105,   105,   105,   105,   105,   106,   106,   106,   107,
     107,   107,   107,   108,   108,   108,   108,   108,   109,   109,
     110,   110,   110,   111,   112,   112,   112,   113,   113,   114,
     114,   115,   115,   115,   115,   115,   116,   116,   116,   117,
     117,   118,   119,   119,   119,   120,   121,   121,   122,   122,
     122,   123,   123,   123,   123,   123,   123,   124,   124,   124,
     124,   125,   125,   126,   126,   126,   126,   127,   127
    };
  }

/* YYR2[YYN] -- Number of symbols on the right hand side of rule YYN.  */
  private static final byte yyr2_[] = yyr2_init();
  private static final byte[] yyr2_init()
  {
    return new byte[]
    {
       0,     2,     3,     2,     2,     2,     3,     2,     0,     1,
       1,     1,     4,     0,     3,     1,     2,     2,     1,     1,
       1,     1,     1,     1,     1,     1,     1,     1,     1,     1,
       1,     3,     2,     1,     2,     3,     2,     3,     2,     3,
       2,     2,     2,     5,     7,     5,     6,     8,     7,     7,
       6,     8,     7,     8,     5,     5,     7,     5,     3,     3,
       3,     2,     4,     4,     5,     3,     3,     4,     2,     3,
       4,     1,     3,     6,     6,     5,     5,     5,     5,     4,
       4,     4,     1,     1,     2,     3,     3,     4,     4,     4,
       4,     4,     4,     4,     3,     4,     1,     1,     3,     3,
       3,     1,     4,     1,     3,     1,     1,     1,     3,     1,
       3,     1,     1,     3,     1,     3,     1,     3,     1,     3,
       3,     1,     3,     3,     3,     3,     1,     3,     3,     1,
       3,     3,     3,     2,     2,     2,     2,     1,     1,     4,
       1,     2,     2,     1,     1,     1,     1,     1,     1,     3,
       1,     1,     1,     1,     1,     1,     1,     1,     1,     4,
       4,     5,     2,     2,     2,     2,     3,     2,     1,     1,
       3,     2,     4,     5,     4,     5,     5,     4,     4,     4,
       4,     5,     6,     3,     1,     1,     1,     5,     0
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
  "switch_statement", "label_statement", "var_def", "assgn_exp",
  "nonempty_exp_list", "method_declaration", "class_spec", "opt_block",
  "args_list", "set_operation", "function_call", "nonempty_args_list",
  "type_spec", "type_spec_list", "exp", "ConditionalOrExpression",
  "ConditionalAndExpression", "InclusiveOrExpression",
  "ExclusiveOrExpression", "AndExpression", "EqualityExpression",
  "RelationalExpression", "AdditiveExpression", "MultiplicativeExpression",
  "UnaryExpression", "CastExpression", "LogicalUnaryExpression",
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
       0,   114,   114,   117,   118,   120,   121,   124,   127,   131,
     132,   133,   137,   143,   144,   148,   149,   150,   151,   152,
     153,   154,   155,   156,   157,   158,   159,   160,   161,   165,
     166,   172,   173,   177,   178,   182,   186,   187,   188,   189,
     190,   191,   192,   196,   197,   203,   204,   210,   212,   214,
     216,   218,   220,   224,   233,   237,   243,   249,   275,   282,
     289,   296,   305,   308,   311,   314,   317,   320,   326,   327,
     330,   336,   337,   341,   344,   347,   350,   353,   356,   359,
     362,   367,   371,   372,   376,   377,   378,   379,   387,   391,
     395,   398,   401,   404,   413,   416,   422,   423,   424,   425,
     429,   430,   431,   437,   438,   442,   443,   444,   449,   452,
     456,   459,   463,   464,   470,   471,   477,   478,   484,   485,
     488,   494,   495,   498,   501,   504,   510,   511,   514,   520,
     521,   524,   527,   533,   537,   541,   542,   543,   547,   548,
     553,   554,   555,   559,   565,   566,   567,   571,   572,   576,
     577,   581,   582,   583,   584,   585,   589,   590,   591,   595,
     599,   603,   631,   635,   636,   640,   648,   649,   653,   654,
     655,   659,   660,   663,   666,   669,   672,   679,   682,   685,
     688,   695,   698,   704,   705,   706,   707,   711,   714
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

  private static final int yylast_ = 1350;
  private static final int yynnts_ = 61;
  private static final int yyempty_ = -2;
  private static final int yyfinal_ = 138;
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

/* "VondaGrammar.java":3128  */ /* lalr1.java:1066  */

}

