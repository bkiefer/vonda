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
    static final int BOOL_LITERAL = 295;
    /** Token number,to be returned by the scanner.  */
    static final int VARIABLE = 296;
    /** Token number,to be returned by the scanner.  */
    static final int OTHER_LITERAL = 297;


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
    /* "VondaGrammar.y":138  */ /* lalr1.java:489  */
    {
    yyval = ((LinkedList<RudiTree>)(yystack.valueAt (3-(3)))); ((StatMethodDeclaration)(yystack.valueAt (3-(2)))).setVisibility(((String)(yystack.valueAt (3-(1))))); ((LinkedList<RudiTree>)(yystack.valueAt (3-(3)))).addFirst(((StatMethodDeclaration)(yystack.valueAt (3-(2)))));
  };
  break;
    

  case 3:
  if (yyn == 3)
    /* "VondaGrammar.y":141  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))); ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))).addFirst(((StatMethodDeclaration)(yystack.valueAt (2-(1))))); };
  break;
    

  case 4:
  if (yyn == 4)
    /* "VondaGrammar.y":142  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))); ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))).addFirst(((RTStatement)(yystack.valueAt (2-(1))))); };
  break;
    

  case 5:
  if (yyn == 5)
    /* "VondaGrammar.y":144  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))); ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))).addFirst(((Import)(yystack.valueAt (2-(1))))); };
  break;
    

  case 6:
  if (yyn == 6)
    /* "VondaGrammar.y":145  */ /* lalr1.java:489  */
    {
    yyval = ((LinkedList<RudiTree>)(yystack.valueAt (3-(3)))); ((LinkedList<RudiTree>)(yystack.valueAt (3-(3)))).addFirst(setPos(new StatFieldDef(((String)(yystack.valueAt (3-(1)))), ((StatVarDef)(yystack.valueAt (3-(2))))), yystack.locationAt (3-(1)), yystack.locationAt (3-(2))));
  };
  break;
    

  case 7:
  if (yyn == 7)
    /* "VondaGrammar.y":148  */ /* lalr1.java:489  */
    {
    yyval = ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))); ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))).addFirst(setPos(new StatFieldDef(null, ((StatVarDef)(yystack.valueAt (2-(1))))), yystack.locationAt (2-(1))));
  };
  break;
    

  case 8:
  if (yyn == 8)
    /* "VondaGrammar.y":151  */ /* lalr1.java:489  */
    { yyval = _statements;};
  break;
    

  case 9:
  if (yyn == 9)
    /* "VondaGrammar.y":155  */ /* lalr1.java:489  */
    { yyval = "public"; };
  break;
    

  case 10:
  if (yyn == 10)
    /* "VondaGrammar.y":156  */ /* lalr1.java:489  */
    { yyval = "protected"; };
  break;
    

  case 11:
  if (yyn == 11)
    /* "VondaGrammar.y":157  */ /* lalr1.java:489  */
    { yyval = "private"; };
  break;
    

  case 12:
  if (yyn == 12)
    /* "VondaGrammar.y":161  */ /* lalr1.java:489  */
    {
    new Import((( String )(yystack.valueAt (4-(2)))), ((List<String>)(yystack.valueAt (4-(3)))).toArray(new String[((List<String>)(yystack.valueAt (4-(3)))).size()]));
  };
  break;
    

  case 13:
  if (yyn == 13)
    /* "VondaGrammar.y":167  */ /* lalr1.java:489  */
    { yyval = new ArrayList<String>(); };
  break;
    

  case 14:
  if (yyn == 14)
    /* "VondaGrammar.y":168  */ /* lalr1.java:489  */
    { yyval = ((List<String>)(yystack.valueAt (3-(3)))); ((List<String>)(yystack.valueAt (3-(3)))).add((( String )(yystack.valueAt (3-(2))))); };
  break;
    

  case 15:
  if (yyn == 15)
    /* "VondaGrammar.y":172  */ /* lalr1.java:489  */
    { yyval = ((StatAbstractBlock)(yystack.valueAt (1-(1)))); };
  break;
    

  case 16:
  if (yyn == 16)
    /* "VondaGrammar.y":173  */ /* lalr1.java:489  */
    { yyval = setPos(getAssignmentStat(((RTExpression)(yystack.valueAt (2-(1))))), (yyloc)); };
  break;
    

  case 17:
  if (yyn == 17)
    /* "VondaGrammar.y":174  */ /* lalr1.java:489  */
    {
    ExpSingleValue es = setPos(new ExpSingleValue("1", "int"), (yyloc));
    ExpArithmetic ar = new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(2)))), es, "+");
    setPos(ar, (yyloc));
    yyval = setPos(new StatExpression(ar), (yyloc));
  };
  break;
    

  case 18:
  if (yyn == 18)
    /* "VondaGrammar.y":180  */ /* lalr1.java:489  */
    {
    ExpSingleValue es = setPos(new ExpSingleValue("1", "int"), (yyloc));
    ExpArithmetic ar = new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(2)))), es, "-");
    setPos(ar, (yyloc));
    yyval = setPos(new StatExpression(ar), (yyloc));
  };
  break;
    

  case 19:
  if (yyn == 19)
    /* "VondaGrammar.y":186  */ /* lalr1.java:489  */
    { yyval = setPos(new StatExpression(((RTExpression)(yystack.valueAt (2-(1))))), (yyloc)); };
  break;
    

  case 20:
  if (yyn == 20)
    /* "VondaGrammar.y":187  */ /* lalr1.java:489  */
    { yyval = ((StatGrammarRule)(yystack.valueAt (1-(1)))); };
  break;
    

  case 21:
  if (yyn == 21)
    /* "VondaGrammar.y":188  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 22:
  if (yyn == 22)
    /* "VondaGrammar.y":189  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 23:
  if (yyn == 23)
    /* "VondaGrammar.y":190  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 24:
  if (yyn == 24)
    /* "VondaGrammar.y":191  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 25:
  if (yyn == 25)
    /* "VondaGrammar.y":192  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 26:
  if (yyn == 26)
    /* "VondaGrammar.y":193  */ /* lalr1.java:489  */
    { yyval = ((StatIf)(yystack.valueAt (1-(1)))); };
  break;
    

  case 27:
  if (yyn == 27)
    /* "VondaGrammar.y":194  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 28:
  if (yyn == 28)
    /* "VondaGrammar.y":195  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 29:
  if (yyn == 29)
    /* "VondaGrammar.y":196  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 30:
  if (yyn == 30)
    /* "VondaGrammar.y":197  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 31:
  if (yyn == 31)
    /* "VondaGrammar.y":201  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 32:
  if (yyn == 32)
    /* "VondaGrammar.y":202  */ /* lalr1.java:489  */
    { yyval = ((StatVarDef)(yystack.valueAt (1-(1)))); };
  break;
    

  case 33:
  if (yyn == 33)
    /* "VondaGrammar.y":206  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 34:
  if (yyn == 34)
    /* "VondaGrammar.y":213  */ /* lalr1.java:489  */
    { yyval = setPos(new StatAbstractBlock(((LinkedList<RTStatement>)(yystack.valueAt (3-(2)))), true), (yyloc)); };
  break;
    

  case 35:
  if (yyn == 35)
    /* "VondaGrammar.y":214  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatAbstractBlock(new ArrayList<RTStatement>(), true), (yyloc));
  };
  break;
    

  case 36:
  if (yyn == 36)
    /* "VondaGrammar.y":218  */ /* lalr1.java:489  */
    { yyval = new LinkedList<RTStatement>(){{ add(((RTStatement)(yystack.valueAt (1-(1))))); }}; };
  break;
    

  case 37:
  if (yyn == 37)
    /* "VondaGrammar.y":219  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RTStatement>)(yystack.valueAt (2-(2)))); ((LinkedList<RTStatement>)(yystack.valueAt (2-(2)))).addFirst(((RTStatement)(yystack.valueAt (2-(1))))); };
  break;
    

  case 38:
  if (yyn == 38)
    /* "VondaGrammar.y":223  */ /* lalr1.java:489  */
    { yyval = setPos(new StatGrammarRule((( String )(yystack.valueAt (3-(1)))), ((StatIf)(yystack.valueAt (3-(3))))), (yyloc)); };
  break;
    

  case 39:
  if (yyn == 39)
    /* "VondaGrammar.y":227  */ /* lalr1.java:489  */
    { yyval = setPos(new StatReturn("return"), (yyloc)); };
  break;
    

  case 40:
  if (yyn == 40)
    /* "VondaGrammar.y":228  */ /* lalr1.java:489  */
    { yyval = setPos(new StatReturn(((RTExpression)(yystack.valueAt (3-(2))))), (yyloc)); };
  break;
    

  case 41:
  if (yyn == 41)
    /* "VondaGrammar.y":229  */ /* lalr1.java:489  */
    { yyval = setPos(new StatReturn("break"), (yyloc)); };
  break;
    

  case 42:
  if (yyn == 42)
    /* "VondaGrammar.y":230  */ /* lalr1.java:489  */
    { yyval = setPos(new StatReturn("break", (( String )(yystack.valueAt (3-(2))))), (yyloc)); };
  break;
    

  case 43:
  if (yyn == 43)
    /* "VondaGrammar.y":231  */ /* lalr1.java:489  */
    { yyval = setPos(new StatReturn("cancel"), (yyloc)); };
  break;
    

  case 44:
  if (yyn == 44)
    /* "VondaGrammar.y":232  */ /* lalr1.java:489  */
    { yyval = setPos(new StatReturn("cancel_all"), (yyloc)); };
  break;
    

  case 45:
  if (yyn == 45)
    /* "VondaGrammar.y":233  */ /* lalr1.java:489  */
    { yyval = setPos(new StatReturn("continue"), (yyloc)); };
  break;
    

  case 46:
  if (yyn == 46)
    /* "VondaGrammar.y":237  */ /* lalr1.java:489  */
    { yyval = setPos(new StatIf(((RTExpression)(yystack.valueAt (5-(3)))), ((RTStatement)(yystack.valueAt (5-(5)))), null), (yyloc)); };
  break;
    

  case 47:
  if (yyn == 47)
    /* "VondaGrammar.y":238  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatIf(((RTExpression)(yystack.valueAt (7-(3)))), ((RTStatement)(yystack.valueAt (7-(5)))), ((RTStatement)(yystack.valueAt (7-(7))))), (yyloc));
  };
  break;
    

  case 48:
  if (yyn == 48)
    /* "VondaGrammar.y":244  */ /* lalr1.java:489  */
    { yyval = setPos(new StatWhile(((RTExpression)(yystack.valueAt (5-(3)))), ((RTStatement)(yystack.valueAt (5-(5)))), true), (yyloc)); };
  break;
    

  case 49:
  if (yyn == 49)
    /* "VondaGrammar.y":245  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatWhile(((RTExpression)(yystack.valueAt (6-(5)))), ((RTStatement)(yystack.valueAt (6-(2)))), false), (yyloc));
  };
  break;
    

  case 50:
  if (yyn == 50)
    /* "VondaGrammar.y":251  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatFor1(((StatVarDef)(yystack.valueAt (8-(3)))), ((RTExpression)(yystack.valueAt (8-(4)))), ((RTExpression)(yystack.valueAt (8-(6)))), ((RTStatement)(yystack.valueAt (8-(8))))), (yyloc)); };
  break;
    

  case 51:
  if (yyn == 51)
    /* "VondaGrammar.y":253  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatFor1(((StatVarDef)(yystack.valueAt (7-(3)))), null, ((RTExpression)(yystack.valueAt (7-(5)))), ((RTStatement)(yystack.valueAt (7-(7))))), (yyloc)); };
  break;
    

  case 52:
  if (yyn == 52)
    /* "VondaGrammar.y":255  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatFor1(((StatVarDef)(yystack.valueAt (7-(3)))), ((RTExpression)(yystack.valueAt (7-(4)))), null, ((RTStatement)(yystack.valueAt (7-(7))))), (yyloc)); };
  break;
    

  case 53:
  if (yyn == 53)
    /* "VondaGrammar.y":257  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatFor1(((StatVarDef)(yystack.valueAt (6-(3)))), null, null, ((RTStatement)(yystack.valueAt (6-(6))))), (yyloc)); };
  break;
    

  case 54:
  if (yyn == 54)
    /* "VondaGrammar.y":259  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatFor1(null, ((RTExpression)(yystack.valueAt (8-(4)))), ((RTExpression)(yystack.valueAt (8-(6)))), ((RTStatement)(yystack.valueAt (8-(8))))), (yyloc)); };
  break;
    

  case 55:
  if (yyn == 55)
    /* "VondaGrammar.y":261  */ /* lalr1.java:489  */
    {
    ExpVariable var = setPos(new ExpVariable((( String )(yystack.valueAt (7-(3))))), yystack.locationAt (7-(3)));
    yyval = setPos(new StatFor2(var, ((RTExpression)(yystack.valueAt (7-(5)))), ((RTStatement)(yystack.valueAt (7-(7))))), (yyloc));
  };
  break;
    

  case 56:
  if (yyn == 56)
    /* "VondaGrammar.y":265  */ /* lalr1.java:489  */
    {
    ExpVariable var = setPos(new ExpVariable((( String )(yystack.valueAt (8-(4))))), yystack.locationAt (8-(4)));
    yyval = setPos(new StatFor2(((Type)(yystack.valueAt (8-(3)))), var, ((RTExpression)(yystack.valueAt (8-(6)))), ((RTStatement)(yystack.valueAt (8-(8))))), (yyloc));
  };
  break;
    

  case 57:
  if (yyn == 57)
    /* "VondaGrammar.y":274  */ /* lalr1.java:489  */
    { yyval = setPos(new StatPropose(((RTExpression)(yystack.valueAt (5-(3)))), ((StatAbstractBlock)(yystack.valueAt (5-(5))))), (yyloc)); };
  break;
    

  case 58:
  if (yyn == 58)
    /* "VondaGrammar.y":278  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatTimeout(null, ((RTExpression)(yystack.valueAt (5-(3)))), ((StatAbstractBlock)(yystack.valueAt (5-(5))))), (yyloc));
  };
  break;
    

  case 59:
  if (yyn == 59)
    /* "VondaGrammar.y":284  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatTimeout(((RTExpression)(yystack.valueAt (7-(3)))), ((RTExpression)(yystack.valueAt (7-(5)))), ((StatAbstractBlock)(yystack.valueAt (7-(7))))), (yyloc));
  };
  break;
    

  case 60:
  if (yyn == 60)
    /* "VondaGrammar.y":290  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatSwitch(((RTExpression)(yystack.valueAt (5-(3)))), ((StatAbstractBlock)(yystack.valueAt (5-(5))))), (yyloc));
  };
  break;
    

  case 61:
  if (yyn == 61)
    /* "VondaGrammar.y":316  */ /* lalr1.java:489  */
    {
    ExpSingleValue val =
      new ExpSingleValue("case " + (( ExpSingleValue )(yystack.valueAt (3-(2)))).toString() + ":", "label");
    RTStatement lbl = val.ensureStatement();
    setPos(val, (yyloc)); setPos(lbl, (yyloc));
    yyval = lbl;
  };
  break;
    

  case 62:
  if (yyn == 62)
    /* "VondaGrammar.y":323  */ /* lalr1.java:489  */
    {
    ExpSingleValue val =
      new ExpSingleValue("case " + (( ExpSingleValue )(yystack.valueAt (3-(2)))).toString() + ":", "label");
    RTStatement lbl = val.ensureStatement();
    setPos(val, (yyloc)); setPos(lbl, (yyloc));
    yyval = lbl;
  };
  break;
    

  case 63:
  if (yyn == 63)
    /* "VondaGrammar.y":330  */ /* lalr1.java:489  */
    {
    ExpSingleValue val =
      new ExpSingleValue("case " + (( ExpSingleValue )(yystack.valueAt (3-(2)))).toString() + ":", "label");
    RTStatement lbl = val.ensureStatement();
    setPos(val, (yyloc)); setPos(lbl, (yyloc));
    yyval = lbl;
  };
  break;
    

  case 64:
  if (yyn == 64)
    /* "VondaGrammar.y":337  */ /* lalr1.java:489  */
    {
    ExpSingleValue val =
      new ExpSingleValue("case " + (( String )(yystack.valueAt (3-(2)))) + ":", "label");
    RTStatement lbl = val.ensureStatement();
    setPos(val, (yyloc)); setPos(lbl, (yyloc));
    yyval = lbl;
  };
  break;
    

  case 65:
  if (yyn == 65)
    /* "VondaGrammar.y":344  */ /* lalr1.java:489  */
    {
    ExpSingleValue val = new ExpSingleValue("default:", "label");
    RTStatement lbl = val.ensureStatement();
    setPos(val, (yyloc)); setPos(lbl, (yyloc));
    yyval = lbl;
  };
  break;
    

  case 66:
  if (yyn == 66)
    /* "VondaGrammar.y":353  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatVarDef(true, new Type(null), (( String )(yystack.valueAt (4-(2)))), ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc));
  };
  break;
    

  case 67:
  if (yyn == 67)
    /* "VondaGrammar.y":356  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatVarDef(false, ((Type)(yystack.valueAt (4-(1)))), (( String )(yystack.valueAt (4-(2)))), ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc));
  };
  break;
    

  case 68:
  if (yyn == 68)
    /* "VondaGrammar.y":359  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatVarDef(true, ((Type)(yystack.valueAt (5-(2)))), (( String )(yystack.valueAt (5-(3)))), ((RTExpression)(yystack.valueAt (5-(4))))), (yyloc));
  };
  break;
    

  case 69:
  if (yyn == 69)
    /* "VondaGrammar.y":362  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatVarDef(false, new Type(null), (( String )(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(2))))), (yyloc));
  };
  break;
    

  case 70:
  if (yyn == 70)
    /* "VondaGrammar.y":365  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatVarDef(true, new Type(null), (( String )(yystack.valueAt (3-(2)))), null), (yyloc));
  };
  break;
    

  case 71:
  if (yyn == 71)
    /* "VondaGrammar.y":368  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatVarDef(false, ((Type)(yystack.valueAt (3-(1)))), (( String )(yystack.valueAt (3-(2)))), null), (yyloc));
  };
  break;
    

  case 72:
  if (yyn == 72)
    /* "VondaGrammar.y":371  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatVarDef(true, ((Type)(yystack.valueAt (4-(2)))), (( String )(yystack.valueAt (4-(3)))), null), (yyloc));
  };
  break;
    

  case 73:
  if (yyn == 73)
    /* "VondaGrammar.y":377  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (2-(2)))); };
  break;
    

  case 74:
  if (yyn == 74)
    /* "VondaGrammar.y":378  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpListLiteral(new LinkedList<RTExpression>()), yystack.locationAt (3-(2)), yystack.locationAt (3-(3)));
  };
  break;
    

  case 75:
  if (yyn == 75)
    /* "VondaGrammar.y":381  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpListLiteral(((LinkedList<RTExpression>)(yystack.valueAt (4-(3))))), yystack.locationAt (4-(2)), yystack.locationAt (4-(4)));
  };
  break;
    

  case 76:
  if (yyn == 76)
    /* "VondaGrammar.y":387  */ /* lalr1.java:489  */
    { yyval = new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (1-(1))))); }}; };
  break;
    

  case 77:
  if (yyn == 77)
    /* "VondaGrammar.y":388  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))); ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))).addFirst(((RTExpression)(yystack.valueAt (3-(1))))); };
  break;
    

  case 78:
  if (yyn == 78)
    /* "VondaGrammar.y":392  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatMethodDeclaration("public", ((Type)(yystack.valueAt (6-(2)))), ((Type)(yystack.valueAt (6-(1)))), (( String )(yystack.valueAt (6-(3)))), null, ((StatAbstractBlock)(yystack.valueAt (6-(6))))), (yyloc));
  };
  break;
    

  case 79:
  if (yyn == 79)
    /* "VondaGrammar.y":395  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatMethodDeclaration("public", ((Type)(yystack.valueAt (7-(2)))), ((Type)(yystack.valueAt (7-(1)))), (( String )(yystack.valueAt (7-(3)))), ((LinkedList)(yystack.valueAt (7-(5)))), ((StatAbstractBlock)(yystack.valueAt (7-(7))))), (yyloc));
  };
  break;
    

  case 80:
  if (yyn == 80)
    /* "VondaGrammar.y":398  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatMethodDeclaration("public", null, ((Type)(yystack.valueAt (5-(1)))), (( String )(yystack.valueAt (5-(2)))), null, ((StatAbstractBlock)(yystack.valueAt (5-(5))))), (yyloc));
  };
  break;
    

  case 81:
  if (yyn == 81)
    /* "VondaGrammar.y":401  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatMethodDeclaration("public", null, ((Type)(yystack.valueAt (6-(1)))), (( String )(yystack.valueAt (6-(2)))), ((LinkedList)(yystack.valueAt (6-(4)))), ((StatAbstractBlock)(yystack.valueAt (6-(6))))), (yyloc));
  };
  break;
    

  case 82:
  if (yyn == 82)
    /* "VondaGrammar.y":404  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatMethodDeclaration("public", ((Type)(yystack.valueAt (5-(1)))), null, (( String )(yystack.valueAt (5-(2)))), null, ((StatAbstractBlock)(yystack.valueAt (5-(5))))), (yyloc));
  };
  break;
    

  case 83:
  if (yyn == 83)
    /* "VondaGrammar.y":407  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatMethodDeclaration("public", ((Type)(yystack.valueAt (6-(1)))), null, (( String )(yystack.valueAt (6-(2)))), ((LinkedList)(yystack.valueAt (6-(4)))), ((StatAbstractBlock)(yystack.valueAt (6-(6))))), (yyloc));
  };
  break;
    

  case 84:
  if (yyn == 84)
    /* "VondaGrammar.y":410  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatMethodDeclaration("public", null, null, (( String )(yystack.valueAt (4-(1)))), null, ((StatAbstractBlock)(yystack.valueAt (4-(4))))), (yyloc));
  };
  break;
    

  case 85:
  if (yyn == 85)
    /* "VondaGrammar.y":413  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatMethodDeclaration("public", null, null, (( String )(yystack.valueAt (5-(1)))), ((LinkedList)(yystack.valueAt (5-(3)))), ((StatAbstractBlock)(yystack.valueAt (5-(5))))), (yyloc));
  };
  break;
    

  case 86:
  if (yyn == 86)
    /* "VondaGrammar.y":418  */ /* lalr1.java:489  */
    { yyval = ((Type)(yystack.valueAt (4-(2)))); };
  break;
    

  case 87:
  if (yyn == 87)
    /* "VondaGrammar.y":422  */ /* lalr1.java:489  */
    { yyval = ((StatAbstractBlock)(yystack.valueAt (1-(1)))); };
  break;
    

  case 88:
  if (yyn == 88)
    /* "VondaGrammar.y":423  */ /* lalr1.java:489  */
    { yyval = null; };
  break;
    

  case 89:
  if (yyn == 89)
    /* "VondaGrammar.y":427  */ /* lalr1.java:489  */
    { yyval = new LinkedList(){{ add((( String )(yystack.valueAt (1-(1))))); }}; };
  break;
    

  case 90:
  if (yyn == 90)
    /* "VondaGrammar.y":428  */ /* lalr1.java:489  */
    { yyval = new LinkedList(){{ add(((Type)(yystack.valueAt (2-(1))))); add((( String )(yystack.valueAt (2-(2))))); }}; };
  break;
    

  case 91:
  if (yyn == 91)
    /* "VondaGrammar.y":429  */ /* lalr1.java:489  */
    { yyval = ((LinkedList)(yystack.valueAt (3-(3)))); ((LinkedList)(yystack.valueAt (3-(3)))).addFirst((( String )(yystack.valueAt (3-(1))))); };
  break;
    

  case 92:
  if (yyn == 92)
    /* "VondaGrammar.y":430  */ /* lalr1.java:489  */
    {
    yyval = ((LinkedList)(yystack.valueAt (4-(4)))); ((LinkedList)(yystack.valueAt (4-(4)))).addFirst((( String )(yystack.valueAt (4-(2))))); ((LinkedList)(yystack.valueAt (4-(4)))).addFirst(((Type)(yystack.valueAt (4-(1)))));
  };
  break;
    

  case 93:
  if (yyn == 93)
    /* "VondaGrammar.y":438  */ /* lalr1.java:489  */
    {
    ExpVariable var = setPos(new ExpVariable((( String )(yystack.valueAt (4-(1))))), yystack.locationAt (4-(1)));
    yyval = setPos(new StatSetOperation(var, true, ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc));
  };
  break;
    

  case 94:
  if (yyn == 94)
    /* "VondaGrammar.y":442  */ /* lalr1.java:489  */
    {
    ExpVariable var = setPos(new ExpVariable((( String )(yystack.valueAt (4-(1))))), yystack.locationAt (4-(1)));
    yyval = setPos(new StatSetOperation(var, false, ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc));
  };
  break;
    

  case 95:
  if (yyn == 95)
    /* "VondaGrammar.y":446  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatSetOperation(((RTExpression)(yystack.valueAt (4-(1)))), true, ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc));
  };
  break;
    

  case 96:
  if (yyn == 96)
    /* "VondaGrammar.y":449  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatSetOperation(((RTExpression)(yystack.valueAt (4-(1)))), false, ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc));
  };
  break;
    

  case 97:
  if (yyn == 97)
    /* "VondaGrammar.y":452  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatSetOperation(((RTExpression)(yystack.valueAt (4-(1)))), true, ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc));
  };
  break;
    

  case 98:
  if (yyn == 98)
    /* "VondaGrammar.y":455  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatSetOperation(((RTExpression)(yystack.valueAt (4-(1)))), false, ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc));
  };
  break;
    

  case 99:
  if (yyn == 99)
    /* "VondaGrammar.y":464  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpFuncCall((( String )(yystack.valueAt (3-(1)))), new LinkedList<RTExpression>(), false), (yyloc));
  };
  break;
    

  case 100:
  if (yyn == 100)
    /* "VondaGrammar.y":467  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpFuncCall((( String )(yystack.valueAt (4-(1)))), ((LinkedList<RTExpression>)(yystack.valueAt (4-(3)))), false), (yyloc));
  };
  break;
    

  case 101:
  if (yyn == 101)
    /* "VondaGrammar.y":473  */ /* lalr1.java:489  */
    { yyval = new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (1-(1))))); }}; };
  break;
    

  case 102:
  if (yyn == 102)
    /* "VondaGrammar.y":474  */ /* lalr1.java:489  */
    { yyval = new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (1-(1))))); }}; };
  break;
    

  case 103:
  if (yyn == 103)
    /* "VondaGrammar.y":475  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))); ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))).addFirst(((RTExpression)(yystack.valueAt (3-(1))))); };
  break;
    

  case 104:
  if (yyn == 104)
    /* "VondaGrammar.y":476  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))); ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))).addFirst(((RTExpression)(yystack.valueAt (3-(1))))); };
  break;
    

  case 105:
  if (yyn == 105)
    /* "VondaGrammar.y":480  */ /* lalr1.java:489  */
    { yyval = new Type("Array", new Type((( String )(yystack.valueAt (3-(1)))))); };
  break;
    

  case 106:
  if (yyn == 106)
    /* "VondaGrammar.y":481  */ /* lalr1.java:489  */
    { yyval = new Type((( String )(yystack.valueAt (1-(1))))); };
  break;
    

  case 107:
  if (yyn == 107)
    /* "VondaGrammar.y":482  */ /* lalr1.java:489  */
    {
    yyval = new Type((( String )(yystack.valueAt (4-(1)))), ((LinkedList<Type>)(yystack.valueAt (4-(3)))).toArray(new Type[((LinkedList<Type>)(yystack.valueAt (4-(3)))).size()]));
  };
  break;
    

  case 108:
  if (yyn == 108)
    /* "VondaGrammar.y":488  */ /* lalr1.java:489  */
    { yyval = new LinkedList<Type>(){{ add(((Type)(yystack.valueAt (1-(1))))); }}; };
  break;
    

  case 109:
  if (yyn == 109)
    /* "VondaGrammar.y":489  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<Type>)(yystack.valueAt (3-(3)))); ((LinkedList<Type>)(yystack.valueAt (3-(3)))).addFirst(((Type)(yystack.valueAt (3-(1))))); };
  break;
    

  case 110:
  if (yyn == 110)
    /* "VondaGrammar.y":493  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 111:
  if (yyn == 111)
    /* "VondaGrammar.y":494  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 112:
  if (yyn == 112)
    /* "VondaGrammar.y":495  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 113:
  if (yyn == 113)
    /* "VondaGrammar.y":500  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "||"), (yyloc));
  };
  break;
    

  case 114:
  if (yyn == 114)
    /* "VondaGrammar.y":503  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 115:
  if (yyn == 115)
    /* "VondaGrammar.y":507  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "&&"), (yyloc));
  };
  break;
    

  case 116:
  if (yyn == 116)
    /* "VondaGrammar.y":510  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 117:
  if (yyn == 117)
    /* "VondaGrammar.y":514  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 118:
  if (yyn == 118)
    /* "VondaGrammar.y":515  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "|"), (yyloc));
  };
  break;
    

  case 119:
  if (yyn == 119)
    /* "VondaGrammar.y":521  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 120:
  if (yyn == 120)
    /* "VondaGrammar.y":522  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "^"), (yyloc));
  };
  break;
    

  case 121:
  if (yyn == 121)
    /* "VondaGrammar.y":528  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 122:
  if (yyn == 122)
    /* "VondaGrammar.y":529  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "&"), (yyloc));
  };
  break;
    

  case 123:
  if (yyn == 123)
    /* "VondaGrammar.y":535  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 124:
  if (yyn == 124)
    /* "VondaGrammar.y":536  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "=="), (yyloc));
  };
  break;
    

  case 125:
  if (yyn == 125)
    /* "VondaGrammar.y":539  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "!="), (yyloc));
  };
  break;
    

  case 126:
  if (yyn == 126)
    /* "VondaGrammar.y":545  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 127:
  if (yyn == 127)
    /* "VondaGrammar.y":546  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "<"), (yyloc));
  };
  break;
    

  case 128:
  if (yyn == 128)
    /* "VondaGrammar.y":549  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), ">"), (yyloc));
  };
  break;
    

  case 129:
  if (yyn == 129)
    /* "VondaGrammar.y":552  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), ">="), (yyloc));
  };
  break;
    

  case 130:
  if (yyn == 130)
    /* "VondaGrammar.y":555  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "<="), (yyloc));
  };
  break;
    

  case 131:
  if (yyn == 131)
    /* "VondaGrammar.y":561  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 132:
  if (yyn == 132)
    /* "VondaGrammar.y":562  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "+"), (yyloc));
  };
  break;
    

  case 133:
  if (yyn == 133)
    /* "VondaGrammar.y":565  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "-"), (yyloc));
  };
  break;
    

  case 134:
  if (yyn == 134)
    /* "VondaGrammar.y":571  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 135:
  if (yyn == 135)
    /* "VondaGrammar.y":572  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "*"), (yyloc));
  };
  break;
    

  case 136:
  if (yyn == 136)
    /* "VondaGrammar.y":575  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "/"), (yyloc));
  };
  break;
    

  case 137:
  if (yyn == 137)
    /* "VondaGrammar.y":578  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "%"), (yyloc));
  };
  break;
    

  case 138:
  if (yyn == 138)
    /* "VondaGrammar.y":584  */ /* lalr1.java:489  */
    {
    ExpSingleValue es = setPos(new ExpSingleValue("1", "int"), (yyloc));
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2-(2)))), es, "+"), (yyloc));
  };
  break;
    

  case 139:
  if (yyn == 139)
    /* "VondaGrammar.y":588  */ /* lalr1.java:489  */
    {
    ExpSingleValue es = setPos(new ExpSingleValue("1", "int"), (yyloc));
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2-(2)))), es, "-"), (yyloc));
  };
  break;
    

  case 140:
  if (yyn == 140)
    /* "VondaGrammar.y":592  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2-(2)))), null, "+"), (yyloc)); };
  break;
    

  case 141:
  if (yyn == 141)
    /* "VondaGrammar.y":593  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2-(2)))), null, "-"), (yyloc)); };
  break;
    

  case 142:
  if (yyn == 142)
    /* "VondaGrammar.y":594  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 143:
  if (yyn == 143)
    /* "VondaGrammar.y":598  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 144:
  if (yyn == 144)
    /* "VondaGrammar.y":599  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpCast(((Type)(yystack.valueAt (4-(2)))), ((RTExpression)(yystack.valueAt (4-(4))))), (yyloc)); };
  break;
    

  case 145:
  if (yyn == 145)
    /* "VondaGrammar.y":604  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 146:
  if (yyn == 146)
    /* "VondaGrammar.y":605  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (2-(2)))), null, "!"), (yyloc)); };
  break;
    

  case 147:
  if (yyn == 147)
    /* "VondaGrammar.y":606  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2-(2)))), null, "~"), (yyloc)); };
  break;
    

  case 148:
  if (yyn == 148)
    /* "VondaGrammar.y":610  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 149:
  if (yyn == 149)
    /* "VondaGrammar.y":616  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpSingleValue("null", "null"), (yyloc)); };
  break;
    

  case 150:
  if (yyn == 150)
    /* "VondaGrammar.y":617  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 151:
  if (yyn == 151)
    /* "VondaGrammar.y":618  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 152:
  if (yyn == 152)
    /* "VondaGrammar.y":622  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpVariable((( String )(yystack.valueAt (1-(1))))), (yyloc)); };
  break;
    

  case 153:
  if (yyn == 153)
    /* "VondaGrammar.y":623  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 154:
  if (yyn == 154)
    /* "VondaGrammar.y":624  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpCast(((Type)(yystack.valueAt (6-(3)))), ((RTExpression)(yystack.valueAt (6-(5))))), (yyloc)); };
  break;
    

  case 155:
  if (yyn == 155)
    /* "VondaGrammar.y":628  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (3-(2)))); };
  break;
    

  case 156:
  if (yyn == 156)
    /* "VondaGrammar.y":629  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 157:
  if (yyn == 157)
    /* "VondaGrammar.y":633  */ /* lalr1.java:489  */
    { yyval = ((ExpSingleValue)(yystack.valueAt (1-(1)))); };
  break;
    

  case 158:
  if (yyn == 158)
    /* "VondaGrammar.y":634  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 159:
  if (yyn == 159)
    /* "VondaGrammar.y":635  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 160:
  if (yyn == 160)
    /* "VondaGrammar.y":636  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 161:
  if (yyn == 161)
    /* "VondaGrammar.y":637  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 162:
  if (yyn == 162)
    /* "VondaGrammar.y":641  */ /* lalr1.java:489  */
    { yyval = (( ExpSingleValue )(yystack.valueAt (1-(1)))); setPos((( ExpSingleValue )(yystack.valueAt (1-(1)))), (yyloc)); };
  break;
    

  case 163:
  if (yyn == 163)
    /* "VondaGrammar.y":642  */ /* lalr1.java:489  */
    { yyval = (( ExpSingleValue )(yystack.valueAt (1-(1)))); setPos((( ExpSingleValue )(yystack.valueAt (1-(1)))), (yyloc)); };
  break;
    

  case 164:
  if (yyn == 164)
    /* "VondaGrammar.y":643  */ /* lalr1.java:489  */
    { yyval = (( ExpSingleValue )(yystack.valueAt (1-(1)))); setPos((( ExpSingleValue )(yystack.valueAt (1-(1)))), (yyloc)); };
  break;
    

  case 165:
  if (yyn == 165)
    /* "VondaGrammar.y":644  */ /* lalr1.java:489  */
    { yyval = (( ExpSingleValue )(yystack.valueAt (1-(1)))); setPos((( ExpSingleValue )(yystack.valueAt (1-(1)))), (yyloc)); };
  break;
    

  case 166:
  if (yyn == 166)
    /* "VondaGrammar.y":648  */ /* lalr1.java:489  */
    {
    ExpVariable var = setPos(new ExpVariable((( String )(yystack.valueAt (4-(1))))), yystack.locationAt (4-(1)));
    yyval = setPos(new ExpArrayAccess(var, ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc));
  };
  break;
    

  case 167:
  if (yyn == 167)
    /* "VondaGrammar.y":652  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpArrayAccess(((RTExpression)(yystack.valueAt (4-(1)))), ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc)); };
  break;
    

  case 168:
  if (yyn == 168)
    /* "VondaGrammar.y":656  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpConditional(((RTExpression)(yystack.valueAt (5-(1)))), ((RTExpression)(yystack.valueAt (5-(3)))), ((RTExpression)(yystack.valueAt (5-(5))))), (yyloc)); };
  break;
    

  case 169:
  if (yyn == 169)
    /* "VondaGrammar.y":662  */ /* lalr1.java:489  */
    {
    ExpVariable var = setPos(new ExpVariable((( String )(yystack.valueAt (2-(1))))), yystack.locationAt (2-(1)));
    yyval = setPos(new ExpAssignment(var, ((RTExpression)(yystack.valueAt (2-(2))))), (yyloc));
  };
  break;
    

  case 170:
  if (yyn == 170)
    /* "VondaGrammar.y":666  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpAssignment(((RTExpression)(yystack.valueAt (2-(1)))), ((RTExpression)(yystack.valueAt (2-(2))))), (yyloc)); };
  break;
    

  case 171:
  if (yyn == 171)
    /* "VondaGrammar.y":667  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpAssignment(((RTExpression)(yystack.valueAt (2-(1)))), ((RTExpression)(yystack.valueAt (2-(2))))), (yyloc)); };
  break;
    

  case 172:
  if (yyn == 172)
    /* "VondaGrammar.y":671  */ /* lalr1.java:489  */
    {
    //List<String> repr = new ArrayList<>($2.size());
    //for (int i = 0; i < $2.size(); ++i) repr.add("");
    //$$ = setPos(new ExpFieldAccess($2, repr), @$); $2.addFirst($1);
    yyval = setPos(new ExpFieldAccess(((LinkedList<RTExpression>)(yystack.valueAt (2-(2))))), (yyloc)); ((LinkedList<RTExpression>)(yystack.valueAt (2-(2)))).addFirst(((RTExpression)(yystack.valueAt (2-(1)))));
  };
  break;
    

  case 173:
  if (yyn == 173)
    /* "VondaGrammar.y":677  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpFieldAccess(((LinkedList<RTExpression>)(yystack.valueAt (2-(2))))), (yyloc)); ((LinkedList<RTExpression>)(yystack.valueAt (2-(2)))).addFirst(((RTExpression)(yystack.valueAt (2-(1))))); };
  break;
    

  case 174:
  if (yyn == 174)
    /* "VondaGrammar.y":682  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))); ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))).addFirst(((RTExpression)(yystack.valueAt (3-(2))))); };
  break;
    

  case 175:
  if (yyn == 175)
    /* "VondaGrammar.y":683  */ /* lalr1.java:489  */
    { yyval = new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (2-(2))))); }}; };
  break;
    

  case 176:
  if (yyn == 176)
    /* "VondaGrammar.y":687  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpVariable((( String )(yystack.valueAt (1-(1))))), (yyloc)); };
  break;
    

  case 177:
  if (yyn == 177)
    /* "VondaGrammar.y":688  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 178:
  if (yyn == 178)
    /* "VondaGrammar.y":689  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (3-(2)))); };
  break;
    

  case 179:
  if (yyn == 179)
    /* "VondaGrammar.y":693  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpNew(new Type((( String )(yystack.valueAt (2-(2)))))), (yyloc)); };
  break;
    

  case 180:
  if (yyn == 180)
    /* "VondaGrammar.y":694  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpNew(new Type((( String )(yystack.valueAt (4-(2))))), new LinkedList<>()), (yyloc));
  };
  break;
    

  case 181:
  if (yyn == 181)
    /* "VondaGrammar.y":697  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpNew(new Type((( String )(yystack.valueAt (5-(2))))), ((LinkedList<RTExpression>)(yystack.valueAt (5-(4))))), (yyloc));
  };
  break;
    

  case 182:
  if (yyn == 182)
    /* "VondaGrammar.y":700  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpNew(new Type("Array", new Type((( String )(yystack.valueAt (5-(2)))))),
                    new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (5-(4))))); }}), (yyloc));
  };
  break;
    

  case 183:
  if (yyn == 183)
    /* "VondaGrammar.y":704  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpNew(new Type("Array", new Type((( String )(yystack.valueAt (7-(2)))))),
                    new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (7-(6))))); }}), (yyloc));
  };
  break;
    

  case 184:
  if (yyn == 184)
    /* "VondaGrammar.y":708  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpNew(new Type((( String )(yystack.valueAt (7-(2)))), ((LinkedList<Type>)(yystack.valueAt (7-(4)))).toArray(new Type[((LinkedList<Type>)(yystack.valueAt (7-(4)))).size()])),
                    new LinkedList<>()), (yyloc));
  };
  break;
    

  case 185:
  if (yyn == 185)
    /* "VondaGrammar.y":712  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpNew(new Type((( String )(yystack.valueAt (8-(2)))), ((LinkedList<Type>)(yystack.valueAt (8-(4)))).toArray(new Type[((LinkedList<Type>)(yystack.valueAt (8-(4)))).size()])),
                    ((LinkedList<RTExpression>)(yystack.valueAt (8-(7))))), (yyloc));
  };
  break;
    

  case 186:
  if (yyn == 186)
    /* "VondaGrammar.y":719  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpLambda(((LinkedList)(yystack.valueAt (5-(2)))), ((RTExpression)(yystack.valueAt (5-(5))))), (yyloc));
  };
  break;
    

  case 187:
  if (yyn == 187)
    /* "VondaGrammar.y":722  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpLambda(((LinkedList)(yystack.valueAt (5-(2)))), ((StatAbstractBlock)(yystack.valueAt (5-(5))))), (yyloc));
  };
  break;
    

  case 188:
  if (yyn == 188)
    /* "VondaGrammar.y":725  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpLambda(new LinkedList<>(), ((RTExpression)(yystack.valueAt (4-(4))))), (yyloc));
  };
  break;
    

  case 189:
  if (yyn == 189)
    /* "VondaGrammar.y":728  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpLambda(new LinkedList<>(), ((StatAbstractBlock)(yystack.valueAt (4-(4))))), (yyloc));
  };
  break;
    

  case 190:
  if (yyn == 190)
    /* "VondaGrammar.y":735  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpDialogueAct(((RTExpression)(yystack.valueAt (5-(2)))), ((RTExpression)(yystack.valueAt (5-(4)))), new LinkedList<RTExpression>()), (yyloc));
  };
  break;
    

  case 191:
  if (yyn == 191)
    /* "VondaGrammar.y":738  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpDialogueAct(((RTExpression)(yystack.valueAt (6-(2)))), ((RTExpression)(yystack.valueAt (6-(4)))), ((LinkedList<RTExpression>)(yystack.valueAt (6-(5))))), (yyloc));
  };
  break;
    

  case 192:
  if (yyn == 192)
    /* "VondaGrammar.y":744  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (3-(2)))); };
  break;
    

  case 193:
  if (yyn == 193)
    /* "VondaGrammar.y":745  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpVariable((( String )(yystack.valueAt (1-(1))))), (yyloc)); };
  break;
    

  case 194:
  if (yyn == 194)
    /* "VondaGrammar.y":746  */ /* lalr1.java:489  */
    { yyval = (( ExpSingleValue )(yystack.valueAt (1-(1)))); };
  break;
    

  case 195:
  if (yyn == 195)
    /* "VondaGrammar.y":747  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpSingleValue((( String )(yystack.valueAt (1-(1)))), "String"), (yyloc)); };
  break;
    

  case 196:
  if (yyn == 196)
    /* "VondaGrammar.y":751  */ /* lalr1.java:489  */
    {
    yyval = ((LinkedList<RTExpression>)(yystack.valueAt (5-(5)))); ((LinkedList<RTExpression>)(yystack.valueAt (5-(5)))).addFirst(((RTExpression)(yystack.valueAt (5-(4))))); ((LinkedList<RTExpression>)(yystack.valueAt (5-(5)))).addFirst(((RTExpression)(yystack.valueAt (5-(2)))));
  };
  break;
    

  case 197:
  if (yyn == 197)
    /* "VondaGrammar.y":754  */ /* lalr1.java:489  */
    { yyval = new LinkedList<RTExpression>(); };
  break;
    


/* "VondaGrammar.java":2086  */ /* lalr1.java:489  */
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

  private static final short yypact_ninf_ = -314;
  private static final short yytable_ninf_ = -153;

  /* YYPACT[STATE-NUM] -- Index in YYTABLE of the portion describing
   STATE-NUM.  */
  private static final short yypact_[] = yypact_init();
  private static final short[] yypact_init()
  {
    return new short[]
    {
     817,   150,    18,    78,   423,   111,   110,   914,   231,   254,
     261,   247,   274,  -314,   303,  -314,  -314,   301,   307,   316,
     324,   327,  1343,  1343,  -314,  -314,  -314,   206,  -314,   867,
    1377,   289,   263,   178,     4,   817,   817,  -314,  -314,  -314,
    -314,  -314,  -314,  -314,  -314,  -314,  -314,  -314,   817,   817,
     326,  -314,    36,   336,   346,   191,  -314,  -314,   131,   349,
     139,  -314,  -314,   351,  -314,  -314,  -314,   354,   356,   358,
     362,  -314,  -314,   425,  -314,   394,  -314,   395,   319,   401,
      21,  1411,   403,   330,  1411,  -314,  1343,  1343,   364,  -314,
    1445,  1479,  1479,  1343,  1343,   346,   -38,   417,   424,   398,
     393,   399,    60,    64,    85,   419,  -314,  -314,  -314,  -314,
    -314,   346,   191,   432,  -314,  -314,   432,  1411,  1411,  1411,
    1411,   204,   442,  -314,  -314,   443,  1411,  1411,   477,   928,
     963,   639,   289,   448,  -314,  -314,   914,   447,  1445,   -41,
     251,   441,  -314,  -314,  -314,  1411,   449,  -314,   305,   817,
     817,  -314,  -314,  -314,  -314,   337,   455,  -314,   229,  -314,
     149,  -314,  1411,  1411,  1411,  -314,  -314,  1411,  1411,  -314,
    -314,  -314,  -314,  -314,  -314,   997,   450,   105,  -314,   446,
     457,   275,   329,  1411,  1032,   463,    68,   464,   465,  1066,
    1101,   289,    76,  -314,  -314,  1411,  -314,   392,   458,  -314,
    -314,  -314,  -314,  -314,  1411,  1479,  1479,  1479,  1479,  1479,
    1479,  1479,  1479,  1479,  1479,  1479,  1479,  1479,  1479,  1479,
    1479,    80,   135,    92,   128,  -314,  -314,   -22,    15,  -314,
     244,  1135,   461,   460,   462,   469,   205,   466,  1170,   451,
    -314,    -6,   470,   459,  -314,  -314,  -314,   472,  -314,   468,
     -33,   263,   240,   448,  -314,  -314,   271,   467,   476,  1411,
    -314,   346,  -314,   283,   482,    22,    33,    57,    58,    62,
    -314,  1411,  -314,  -314,   484,  1411,    73,  1204,    77,   -31,
     914,   403,  -314,  -314,   483,   213,   486,    69,   480,   461,
    1479,   -36,   424,   398,   393,   399,    60,    64,    64,    85,
      85,    85,    85,   419,   419,  -314,  -314,  -314,   461,  1411,
     461,   914,  -314,  -314,   495,   487,   490,   290,  -314,   461,
    -314,   496,  1513,  1513,  -314,   499,  -314,   289,  -314,  1479,
    -314,  -314,   234,   269,   461,   282,   501,   309,   130,  -314,
     282,   502,  -314,  -314,  -314,  -314,  -314,  -314,   137,  -314,
     138,  1411,   914,   193,  1239,  1411,   542,  -314,  -314,  1411,
    1411,  -314,   505,  -314,  -314,  1411,  -314,   195,  -314,  -314,
    -314,  1274,   528,  -314,   495,  -314,  -314,  -314,  -314,   506,
    -314,   263,   508,  -314,  -314,  -314,   282,   282,   509,  -314,
    -314,   282,  -314,   914,   198,  -314,   914,   914,   199,   200,
     914,  -314,   202,  1308,   451,   461,  -314,   451,  1274,  -314,
    -314,   510,  -314,  -314,  -314,   282,  -314,  -314,   914,  -314,
    -314,   914,   914,  -314,  -314,  -314,   511,  -314,  -314,   451,
     263,  -314,  -314,  -314,  -314,  -314,   512,  -314
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
       0,     0,     0,     0,   162,   163,   165,   106,   164,     0,
       0,     0,     0,     0,     0,     8,     8,    15,    20,    22,
      26,    27,    28,    23,    25,    24,    29,    30,     8,     8,
       0,    21,   160,     0,     0,     0,   156,   157,   158,     0,
     159,   153,   161,     0,    41,    43,    44,     0,     0,     0,
       0,    45,    65,   106,    31,     0,    32,     0,   106,     0,
       0,     0,    13,   179,     0,   149,     0,     0,   152,    39,
       0,     0,     0,     0,     0,   160,     0,   112,   114,   116,
     117,   119,   121,   123,   126,   131,   143,   134,   142,   145,
     148,   150,   151,   158,   110,   111,   159,     0,     0,     0,
       0,   152,     0,   158,   159,     0,     0,     0,     0,     0,
       0,     0,     0,     0,    35,    33,    36,     0,     0,     0,
     106,     0,   194,   195,   193,     0,     0,     1,   106,     8,
       8,     5,     4,     7,     3,   106,     0,    19,     0,   173,
       0,   172,     0,     0,     0,   171,    16,     0,     0,   170,
      42,    61,    62,    63,    64,     0,     0,     0,    70,     0,
       0,     0,   106,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,   139,   138,     0,   169,   152,     0,   140,
     141,   146,   147,    40,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,    18,    17,     0,     0,    38,
     152,     0,    99,     0,     0,     0,   101,   102,     0,    73,
     105,     0,   108,     0,    69,    37,    34,     0,   155,     0,
       0,     0,     0,     0,     6,     2,     0,     0,   176,     0,
     177,   175,    71,     0,     0,     0,     0,     0,     0,     0,
      99,     0,    66,    72,     0,     0,     0,     0,     0,     0,
       0,    13,    12,   180,     0,    76,     0,     0,     0,     0,
       0,     0,   113,   115,   118,   120,   122,   124,   125,   129,
     130,   127,   128,   132,   133,   135,   136,   137,     0,     0,
       0,     0,    94,    93,     0,     0,     0,     0,    84,     0,
     100,    90,     0,     0,    74,     0,   166,     0,   107,     0,
      86,   192,     0,    89,     0,     0,     0,     0,     0,   174,
       0,     0,    67,   167,    96,    95,    98,    97,     0,    68,
       0,     0,     0,     0,     0,     0,    46,    14,   181,     0,
       0,   182,     0,    57,   144,     0,    60,     0,    58,    48,
      91,     0,     0,    85,     0,   103,   104,    75,   109,   144,
     190,     0,     0,    88,    87,    80,     0,     0,     0,   178,
      82,     0,    49,     0,     0,    53,     0,     0,     0,     0,
       0,    77,     0,     0,   168,     0,   189,   188,     0,    92,
     154,     0,   191,    81,    78,     0,    83,    55,     0,    51,
      52,     0,     0,    47,   183,   184,     0,    59,   187,   186,
       0,    79,    54,    50,    56,   185,   197,   196
    };
  }

/* YYPGOTO[NTERM-NUM].  */
  private static final short yypgoto_[] = yypgoto_init();
  private static final short[] yypgoto_init()
  {
    return new short[]
    {
    -314,    49,  -314,  -314,   278,    19,    11,  -314,   331,   428,
    -314,  -314,   437,  -314,  -314,  -314,  -314,  -314,  -314,  -314,
     299,    -1,  -232,   532,  -314,  -313,   -68,  -314,     2,   103,
     453,  -190,   411,  -314,   363,   361,   365,   368,   360,   260,
     253,   258,    20,   -88,  -314,  -314,  -314,    34,   126,  -314,
    -314,     0,  -314,   238,    17,   -40,  -314,  -314,  -314,  -314,
    -241,   134
    };
  }

/* YYDEFGOTO[NTERM-NUM].  */
  private static final short yydefgoto_[] = yydefgoto_init();
  private static final short[] yydefgoto_init()
  {
    return new short[]
    {
      -1,    33,    34,    35,   188,    74,   135,   136,    37,   137,
      38,    39,    40,    41,    42,    43,    44,    45,    46,    47,
      76,   196,   284,    49,    50,   385,   233,    51,    95,   234,
      77,   243,   139,    97,    98,    99,   100,   101,   102,   103,
     104,   105,   106,   107,   108,   109,   110,   111,   112,    56,
      57,   113,   114,   115,   116,   159,   261,    61,   237,    62,
     146,   382
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
      58,   288,    52,   199,   200,   203,   325,    58,   248,    52,
     332,   365,   262,   331,   161,     8,   355,    60,    75,    36,
     130,   312,   123,   123,    60,   204,   133,   390,   204,    58,
     204,    52,     8,   204,    54,    58,    58,    52,    52,   124,
     124,    54,   122,   125,   204,   148,    60,   326,    58,    58,
      52,    52,    60,    60,    36,    36,    31,   165,   313,   169,
     204,    65,   182,    54,   183,    60,    60,    36,    36,    54,
      54,   161,   133,   413,   414,   343,   344,   180,   416,   157,
     158,   204,    54,    54,   151,   152,   123,   123,   204,   210,
     211,   123,   123,   123,   123,   212,   213,   153,   154,   204,
     345,   346,   431,   124,   124,   347,   193,   194,   124,   124,
     124,   124,   165,   201,   202,   169,   351,   280,   214,   215,
     354,    66,   361,   204,   204,   289,    55,   401,   204,   308,
     305,   306,   307,    55,   204,   204,    58,   378,    52,   204,
     411,   310,   204,   204,   216,   217,   204,   253,   262,    58,
      58,    52,    52,    60,    71,    55,   130,    72,   204,   264,
     260,    55,    55,   316,   163,   164,    60,    60,    36,    36,
      54,   426,   167,   168,    55,    55,   264,   311,   147,   389,
     274,   253,   130,    54,    54,   309,   392,   393,   336,   436,
     130,    63,   262,    64,   204,   341,   204,   263,   254,   255,
     130,   204,   364,   204,   204,   123,   123,   123,   123,   123,
     123,   123,   123,   123,   123,   123,   123,   123,   123,   123,
     123,   339,   124,   124,   124,   124,   124,   124,   124,   124,
     124,   124,   124,   124,   124,   124,   124,   124,    59,   126,
     127,   379,   396,   162,   405,    59,   370,   418,   421,   422,
    -152,   424,   175,   128,   129,   322,   195,   130,   131,   204,
     132,   204,    55,   359,   204,   204,   204,    59,   204,   388,
     258,   204,    78,    59,    59,    55,    55,   259,   264,   204,
      58,   333,    52,   380,   381,  -106,    59,    59,    82,   334,
     123,   356,   175,   -89,   314,   130,   131,    60,   132,    48,
     142,   143,    80,   179,   144,   132,   409,   124,   145,    81,
    -106,    58,   333,    52,    54,    83,    12,    85,   273,   314,
     335,   179,   369,   132,   333,   383,   130,    29,    60,   123,
     140,   321,   340,   149,    48,    48,    86,    87,    24,   290,
      25,    26,    88,    28,    89,    54,   124,    48,    48,    90,
     333,    84,    58,   252,    52,   117,   130,   179,   387,   132,
      91,    92,   178,   395,   118,    93,    94,   155,    32,    60,
     130,   179,   119,   132,    59,   120,   275,   160,   189,   184,
     130,   179,   190,   132,   191,   256,    54,    59,    59,   179,
     158,   132,   166,    58,   170,    52,    58,    58,    52,    52,
      58,   171,    52,   172,   417,   173,    55,   419,   420,   174,
      60,   423,   175,    60,    60,   130,   195,    60,    58,   176,
      52,    58,    58,    52,    52,   375,   376,    54,    96,   432,
      54,    54,   433,   434,    54,    60,   177,    55,    60,    60,
     175,  -106,   181,   130,   131,   205,   132,   187,    48,    48,
     208,   206,    54,    53,   207,    54,    54,   209,   126,   127,
      67,    79,    68,    69,    70,   299,   300,   301,   302,  -152,
     297,   298,   128,   175,   303,   304,   130,   131,    55,   132,
     218,   219,   220,   130,   141,   225,   226,    53,    53,    53,
      10,   244,   186,   246,   249,   192,   257,   251,   271,   240,
     272,    53,    53,   156,   279,   281,    29,   290,   282,   319,
     321,   320,   330,   371,   328,   337,   323,   204,    59,    55,
     327,   329,    55,    55,   175,   342,    55,   349,   221,   222,
     223,   224,   358,   185,   360,   362,   333,   227,   228,   372,
     236,   239,   241,   198,    55,   377,   374,    55,    55,    59,
     386,   391,   400,   403,   408,   410,   250,   412,   415,   357,
     435,   430,   381,   318,   245,   229,   150,   293,   292,   296,
     437,     0,   294,   265,   266,   267,   295,     0,   268,   269,
       0,     0,   235,     0,     0,   242,   236,     0,     0,     0,
      59,   247,     0,     0,   276,   278,     0,     0,     0,     0,
     285,   287,    53,    53,     0,     0,   241,     0,     0,     0,
       0,     0,     0,     0,     0,   291,     0,     0,     0,     0,
     363,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,    59,     0,     0,    59,    59,     0,     0,    59,   366,
       0,   368,     0,     0,   242,     0,     0,     0,     0,   285,
     373,     0,     0,     0,    12,    85,    59,     0,     0,    59,
      59,     0,     0,     0,     0,   318,   384,     0,     0,     0,
     338,   384,     0,     0,    86,    87,    24,     0,    25,    26,
      88,    28,   348,     0,   317,     0,   350,    90,   353,     0,
       0,     0,   240,     0,     0,     0,     0,     0,    91,    92,
       0,     0,   406,    93,    94,   235,    32,     0,     0,   235,
       0,     0,     0,     0,     0,     0,   235,   384,   384,     0,
     367,     0,   384,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,   236,   236,     0,   427,     0,     0,   428,
       0,     0,     0,     0,     0,     0,   384,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,   394,     0,     0,   398,   399,   235,     0,     0,
     285,   402,     0,     0,     0,     0,   404,     0,     0,     0,
     242,     0,   407,     0,     0,     0,     0,     0,     0,     0,
     235,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,   285,     0,     0,     0,     0,   429,
       1,     2,     3,     4,     5,     6,     7,   235,     8,     9,
      10,    11,    12,     0,    13,    14,    15,    16,    17,    18,
      19,    20,    21,     0,     0,     0,     0,     0,     0,     0,
       0,     0,    22,    23,    24,     0,    25,    26,    27,    28,
       0,     0,    29,     0,     0,    30,     0,     0,     0,    31,
       1,     2,     3,     4,     5,     6,     7,     0,     8,     9,
      10,     0,    12,     0,    32,    14,     0,     0,    17,    18,
      19,    20,    21,     0,     0,     0,     0,     0,     0,     0,
       0,     0,    22,    23,    24,     0,    25,    26,    73,    28,
       0,     0,    29,   134,     0,    30,     0,     1,     2,     3,
       4,     5,     6,     7,     0,     8,     9,    10,     0,    12,
       0,     0,    14,     0,    32,    17,    18,    19,    20,    21,
       0,     0,     0,    12,    85,     0,     0,     0,     0,    22,
      23,    24,     0,    25,    26,    73,    28,     0,     0,    29,
       0,     0,    30,    86,    87,    24,     0,    25,    26,   230,
      28,     0,     0,     0,     0,     0,   231,   232,    12,    85,
       0,    32,     0,     0,     0,     0,     0,    91,    92,     0,
       0,     0,    93,    94,     0,    32,     0,     0,    86,    87,
      24,     0,    25,    26,    88,    28,     0,     0,   238,     0,
       0,    90,    12,    85,     0,     0,     0,     0,     0,     0,
       0,     0,    91,    92,     0,     0,     0,    93,    94,     0,
      32,     0,    86,    87,    24,     0,    25,    26,    88,    28,
       0,     0,     0,     0,     0,   231,   270,    12,    85,     0,
       0,     0,     0,     0,     0,     0,    91,    92,     0,     0,
       0,    93,    94,     0,    32,     0,     0,    86,    87,    24,
       0,    25,    26,    88,    28,   277,     0,     0,     0,     0,
      90,    12,    85,     0,     0,     0,     0,     0,     0,     0,
       0,    91,    92,     0,     0,     0,    93,    94,     0,    32,
       0,    86,    87,    24,     0,    25,    26,    88,    28,     0,
       0,     0,     0,     0,    90,   283,    12,    85,     0,     0,
       0,     0,     0,     0,     0,    91,    92,     0,     0,     0,
      93,    94,     0,    32,     0,     0,    86,    87,    24,     0,
      25,    26,    88,    28,     0,     0,     0,     0,     0,    90,
      12,    85,     0,     0,   286,     0,     0,     0,     0,     0,
      91,    92,     0,     0,     0,    93,    94,     0,    32,     0,
      86,    87,    24,     0,    25,    26,   230,    28,     0,     0,
       0,     0,     0,   138,   315,    12,    85,     0,     0,     0,
       0,     0,     0,     0,    91,    92,     0,     0,     0,    93,
      94,     0,    32,     0,     0,    86,    87,    24,     0,    25,
      26,    88,    28,     0,     0,     0,   324,     0,    90,    12,
      85,     0,     0,     0,     0,     0,     0,     0,     0,    91,
      92,     0,     0,     0,    93,    94,     0,    32,     0,    86,
      87,    24,     0,    25,    26,    88,    28,     0,     0,     0,
       0,     0,    90,   352,    12,    85,     0,     0,     0,     0,
       0,     0,     0,    91,    92,     0,     0,     0,    93,    94,
       0,    32,     0,     0,    86,    87,    24,     0,    25,    26,
      88,    28,     0,     0,     0,     0,     0,    90,   397,    12,
      85,     0,     0,     0,     0,     0,     0,     0,    91,    92,
       0,     0,     0,    93,    94,     0,    32,     0,     0,    86,
      87,    24,     0,    25,    26,    88,    28,     0,     0,    29,
       0,     0,    90,    12,    85,     0,     0,     0,     0,     0,
       0,     0,     0,    91,    92,     0,     0,     0,    93,    94,
       0,    32,     0,    86,    87,    24,     0,    25,    26,    88,
      28,     0,     0,     0,     0,     0,    90,   425,    12,    85,
       0,     0,     0,     0,     0,     0,     0,    91,    92,     0,
       0,     0,    93,    94,     0,    32,     0,     0,    86,    87,
      24,     0,    25,    26,   121,    28,     0,     0,     0,     0,
       0,    30,    12,    85,     0,     0,     0,     0,     0,     0,
       0,     0,    91,    92,     0,     0,     0,    93,    94,     0,
      32,     0,    86,    87,    24,     0,    25,    26,    88,    28,
       0,     0,     0,     0,     0,   138,    12,    85,     0,     0,
       0,     0,     0,     0,     0,     0,    91,    92,     0,     0,
       0,    93,    94,     0,    32,     0,    86,    87,    24,     0,
      25,    26,    88,    28,     0,     0,     0,     0,     0,    90,
      12,    85,     0,     0,     0,     0,     0,     0,     0,     0,
      91,    92,     0,     0,     0,    93,    94,     0,    32,     0,
      86,    87,    24,     0,    25,    26,   197,    28,     0,     0,
       0,     0,     0,   138,    12,    85,     0,     0,     0,     0,
       0,     0,     0,     0,    91,    92,     0,     0,     0,    93,
      94,     0,    32,     0,    86,    87,    24,     0,    25,    26,
     121,    28,     0,     0,     0,     0,     0,    90,    12,    85,
       0,     0,     0,     0,     0,     0,     0,     0,    91,    92,
       0,     0,     0,    93,    94,     0,    32,     0,    86,    87,
      24,     0,    25,    26,    88,    28,     0,     0,     0,     0,
       0,   231,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,    91,    92,     0,     0,     0,    93,    94,     0,
      32
    };
  }

private static final short yycheck_[] = yycheck_init();
  private static final short[] yycheck_init()
  {
    return new short[]
    {
       0,   191,     0,    91,    92,    43,   238,     7,    49,     7,
     251,    47,    43,    46,    54,    11,    47,     0,     7,     0,
      51,    43,    22,    23,     7,    66,    27,   340,    66,    29,
      66,    29,    11,    66,     0,    35,    36,    35,    36,    22,
      23,     7,    22,    23,    66,    41,    29,    53,    48,    49,
      48,    49,    35,    36,    35,    36,    52,    58,    43,    60,
      66,    43,    41,    29,    43,    48,    49,    48,    49,    35,
      36,   111,    73,   386,   387,    53,    43,    78,   391,    43,
      44,    66,    48,    49,    35,    36,    86,    87,    66,    29,
      30,    91,    92,    93,    94,    31,    32,    48,    49,    66,
      43,    43,   415,    86,    87,    43,    86,    87,    91,    92,
      93,    94,   113,    93,    94,   116,    43,    49,    54,    55,
      43,    43,    53,    66,    66,    49,     0,   359,    66,    49,
     218,   219,   220,     7,    66,    66,   136,   327,   136,    66,
     381,    49,    66,    66,    59,    60,    66,   148,    43,   149,
     150,   149,   150,   136,    43,    29,    51,    47,    66,   160,
     158,    35,    36,   231,    33,    34,   149,   150,   149,   150,
     136,   403,    33,    34,    48,    49,   177,    49,     0,    49,
     181,   182,    51,   149,   150,    50,    49,    49,   256,   430,
      51,    41,    43,    43,    66,   263,    66,    48,   149,   150,
      51,    66,   290,    66,    66,   205,   206,   207,   208,   209,
     210,   211,   212,   213,   214,   215,   216,   217,   218,   219,
     220,   261,   205,   206,   207,   208,   209,   210,   211,   212,
     213,   214,   215,   216,   217,   218,   219,   220,     0,    33,
      34,   329,    49,    52,    49,     7,   314,    49,    49,    49,
      44,    49,    48,    47,    48,    50,    52,    51,    52,    66,
      54,    66,   136,    50,    66,    66,    66,    29,    66,   337,
      41,    66,    41,    35,    36,   149,   150,    48,   279,    66,
     280,    41,   280,    49,    50,    41,    48,    49,    41,    49,
     290,   280,    48,    49,    50,    51,    52,   280,    54,     0,
      37,    38,    48,    52,    41,    54,   374,   290,    45,    48,
      41,   311,    41,   311,   280,    41,    15,    16,    43,    50,
      49,    52,   311,    54,    41,    43,    51,    45,   311,   329,
      41,    41,    49,    34,    35,    36,    35,    36,    37,    49,
      39,    40,    41,    42,    43,   311,   329,    48,    49,    48,
      41,    48,   352,    48,   352,    48,    51,    52,    49,    54,
      59,    60,    43,   352,    48,    64,    65,    41,    67,   352,
      51,    52,    48,    54,   136,    48,    47,    41,    48,    80,
      51,    52,    52,    54,    54,    48,   352,   149,   150,    52,
      44,    54,    43,   393,    43,   393,   396,   397,   396,   397,
     400,    47,   400,    47,   393,    47,   280,   396,   397,    47,
     393,   400,    48,   396,   397,    51,    52,   400,   418,    25,
     418,   421,   422,   421,   422,   322,   323,   393,    17,   418,
     396,   397,   421,   422,   400,   418,    41,   311,   421,   422,
      48,    49,    41,    51,    52,    28,    54,    44,   149,   150,
      57,    27,   418,     0,    56,   421,   422,    58,    33,    34,
      37,     8,    39,    40,    41,   212,   213,   214,   215,    44,
     210,   211,    47,    48,   216,   217,    51,    52,   352,    54,
      61,    62,    63,    51,    31,    43,    43,    34,    35,    36,
      13,    43,    81,    46,    53,    84,    41,    48,    48,    53,
      43,    48,    49,    50,    41,    41,    45,    49,    43,    49,
      41,    49,    44,    26,    55,    48,    50,    66,   280,   393,
      50,    49,   396,   397,    48,    43,   400,    43,   117,   118,
     119,   120,    49,    80,    48,    55,    41,   126,   127,    49,
     129,   130,   131,    90,   418,    46,    50,   421,   422,   311,
      49,    49,    10,    48,    26,    49,   145,    49,    49,   281,
      49,    51,    50,   232,   136,   128,    34,   206,   205,   209,
     436,    -1,   207,   162,   163,   164,   208,    -1,   167,   168,
      -1,    -1,   129,    -1,    -1,   132,   175,    -1,    -1,    -1,
     352,   138,    -1,    -1,   183,   184,    -1,    -1,    -1,    -1,
     189,   190,   149,   150,    -1,    -1,   195,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,   204,    -1,    -1,    -1,    -1,
     289,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,   393,    -1,    -1,   396,   397,    -1,    -1,   400,   308,
      -1,   310,    -1,    -1,   191,    -1,    -1,    -1,    -1,   238,
     319,    -1,    -1,    -1,    15,    16,   418,    -1,    -1,   421,
     422,    -1,    -1,    -1,    -1,   334,   335,    -1,    -1,    -1,
     259,   340,    -1,    -1,    35,    36,    37,    -1,    39,    40,
      41,    42,   271,    -1,   231,    -1,   275,    48,   277,    -1,
      -1,    -1,    53,    -1,    -1,    -1,    -1,    -1,    59,    60,
      -1,    -1,   371,    64,    65,   252,    67,    -1,    -1,   256,
      -1,    -1,    -1,    -1,    -1,    -1,   263,   386,   387,    -1,
     309,    -1,   391,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,   322,   323,    -1,   405,    -1,    -1,   408,
      -1,    -1,    -1,    -1,    -1,    -1,   415,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,   351,    -1,    -1,   354,   355,   314,    -1,    -1,
     359,   360,    -1,    -1,    -1,    -1,   365,    -1,    -1,    -1,
     327,    -1,   371,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
     337,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,   403,    -1,    -1,    -1,    -1,   408,
       3,     4,     5,     6,     7,     8,     9,   374,    11,    12,
      13,    14,    15,    -1,    17,    18,    19,    20,    21,    22,
      23,    24,    25,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    35,    36,    37,    -1,    39,    40,    41,    42,
      -1,    -1,    45,    -1,    -1,    48,    -1,    -1,    -1,    52,
       3,     4,     5,     6,     7,     8,     9,    -1,    11,    12,
      13,    -1,    15,    -1,    67,    18,    -1,    -1,    21,    22,
      23,    24,    25,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    35,    36,    37,    -1,    39,    40,    41,    42,
      -1,    -1,    45,    46,    -1,    48,    -1,     3,     4,     5,
       6,     7,     8,     9,    -1,    11,    12,    13,    -1,    15,
      -1,    -1,    18,    -1,    67,    21,    22,    23,    24,    25,
      -1,    -1,    -1,    15,    16,    -1,    -1,    -1,    -1,    35,
      36,    37,    -1,    39,    40,    41,    42,    -1,    -1,    45,
      -1,    -1,    48,    35,    36,    37,    -1,    39,    40,    41,
      42,    -1,    -1,    -1,    -1,    -1,    48,    49,    15,    16,
      -1,    67,    -1,    -1,    -1,    -1,    -1,    59,    60,    -1,
      -1,    -1,    64,    65,    -1,    67,    -1,    -1,    35,    36,
      37,    -1,    39,    40,    41,    42,    -1,    -1,    45,    -1,
      -1,    48,    15,    16,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    59,    60,    -1,    -1,    -1,    64,    65,    -1,
      67,    -1,    35,    36,    37,    -1,    39,    40,    41,    42,
      -1,    -1,    -1,    -1,    -1,    48,    49,    15,    16,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    59,    60,    -1,    -1,
      -1,    64,    65,    -1,    67,    -1,    -1,    35,    36,    37,
      -1,    39,    40,    41,    42,    43,    -1,    -1,    -1,    -1,
      48,    15,    16,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    59,    60,    -1,    -1,    -1,    64,    65,    -1,    67,
      -1,    35,    36,    37,    -1,    39,    40,    41,    42,    -1,
      -1,    -1,    -1,    -1,    48,    49,    15,    16,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    59,    60,    -1,    -1,    -1,
      64,    65,    -1,    67,    -1,    -1,    35,    36,    37,    -1,
      39,    40,    41,    42,    -1,    -1,    -1,    -1,    -1,    48,
      15,    16,    -1,    -1,    53,    -1,    -1,    -1,    -1,    -1,
      59,    60,    -1,    -1,    -1,    64,    65,    -1,    67,    -1,
      35,    36,    37,    -1,    39,    40,    41,    42,    -1,    -1,
      -1,    -1,    -1,    48,    49,    15,    16,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    59,    60,    -1,    -1,    -1,    64,
      65,    -1,    67,    -1,    -1,    35,    36,    37,    -1,    39,
      40,    41,    42,    -1,    -1,    -1,    46,    -1,    48,    15,
      16,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    59,
      60,    -1,    -1,    -1,    64,    65,    -1,    67,    -1,    35,
      36,    37,    -1,    39,    40,    41,    42,    -1,    -1,    -1,
      -1,    -1,    48,    49,    15,    16,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    59,    60,    -1,    -1,    -1,    64,    65,
      -1,    67,    -1,    -1,    35,    36,    37,    -1,    39,    40,
      41,    42,    -1,    -1,    -1,    -1,    -1,    48,    49,    15,
      16,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    59,    60,
      -1,    -1,    -1,    64,    65,    -1,    67,    -1,    -1,    35,
      36,    37,    -1,    39,    40,    41,    42,    -1,    -1,    45,
      -1,    -1,    48,    15,    16,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    59,    60,    -1,    -1,    -1,    64,    65,
      -1,    67,    -1,    35,    36,    37,    -1,    39,    40,    41,
      42,    -1,    -1,    -1,    -1,    -1,    48,    49,    15,    16,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    59,    60,    -1,
      -1,    -1,    64,    65,    -1,    67,    -1,    -1,    35,    36,
      37,    -1,    39,    40,    41,    42,    -1,    -1,    -1,    -1,
      -1,    48,    15,    16,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    59,    60,    -1,    -1,    -1,    64,    65,    -1,
      67,    -1,    35,    36,    37,    -1,    39,    40,    41,    42,
      -1,    -1,    -1,    -1,    -1,    48,    15,    16,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    59,    60,    -1,    -1,
      -1,    64,    65,    -1,    67,    -1,    35,    36,    37,    -1,
      39,    40,    41,    42,    -1,    -1,    -1,    -1,    -1,    48,
      15,    16,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      59,    60,    -1,    -1,    -1,    64,    65,    -1,    67,    -1,
      35,    36,    37,    -1,    39,    40,    41,    42,    -1,    -1,
      -1,    -1,    -1,    48,    15,    16,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    59,    60,    -1,    -1,    -1,    64,
      65,    -1,    67,    -1,    35,    36,    37,    -1,    39,    40,
      41,    42,    -1,    -1,    -1,    -1,    -1,    48,    15,    16,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    59,    60,
      -1,    -1,    -1,    64,    65,    -1,    67,    -1,    35,    36,
      37,    -1,    39,    40,    41,    42,    -1,    -1,    -1,    -1,
      -1,    48,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    59,    60,    -1,    -1,    -1,    64,    65,    -1,
      67
    };
  }

/* YYSTOS[STATE-NUM] -- The (internal number of the) accessing
   symbol of state STATE-NUM.  */
  private static final short yystos_[] = yystos_init();
  private static final short[] yystos_init()
  {
    return new short[]
    {
       0,     3,     4,     5,     6,     7,     8,     9,    11,    12,
      13,    14,    15,    17,    18,    19,    20,    21,    22,    23,
      24,    25,    35,    36,    37,    39,    40,    41,    42,    45,
      48,    52,    67,    69,    70,    71,    73,    76,    78,    79,
      80,    81,    82,    83,    84,    85,    86,    87,    88,    91,
      92,    95,    96,    98,   115,   116,   117,   118,   119,   121,
     122,   125,   127,    41,    43,    43,    43,    37,    39,    40,
      41,    43,    47,    41,    73,    74,    88,    98,    41,    98,
      48,    48,    41,    41,    48,    16,    35,    36,    41,    43,
      48,    59,    60,    64,    65,    96,   100,   101,   102,   103,
     104,   105,   106,   107,   108,   109,   110,   111,   112,   113,
     114,   115,   116,   119,   120,   121,   122,    48,    48,    48,
      48,    41,   110,   119,   122,   110,    33,    34,    47,    48,
      51,    52,    54,    89,    46,    74,    75,    77,    48,   100,
      41,    98,    37,    38,    41,    45,   128,     0,    41,    88,
      91,    69,    69,    69,    69,    41,    98,    43,    44,   123,
      41,   123,    52,    33,    34,    89,    43,    33,    34,    89,
      43,    47,    47,    47,    47,    48,    25,    41,    43,    52,
      89,    41,    41,    43,    88,    98,   100,    44,    72,    48,
      52,    54,   100,   110,   110,    52,    89,    41,    98,   111,
     111,   110,   110,    43,    66,    28,    27,    56,    57,    58,
      29,    30,    31,    32,    54,    55,    59,    60,    61,    62,
      63,   100,   100,   100,   100,    43,    43,   100,   100,    80,
      41,    48,    49,    94,    97,    98,   100,   126,    45,   100,
      53,   100,    98,    99,    43,    77,    46,    98,    49,    53,
     100,    48,    48,    89,    69,    69,    48,    41,    41,    48,
      96,   124,    43,    48,    89,   100,   100,   100,   100,   100,
      49,    48,    43,    43,    89,    47,   100,    43,   100,    41,
      49,    41,    43,    49,    90,   100,    53,   100,    99,    49,
      49,   100,   102,   103,   104,   105,   106,   107,   107,   108,
     108,   108,   108,   109,   109,   111,   111,   111,    49,    50,
      49,    49,    43,    43,    50,    49,    94,    98,    76,    49,
      49,    41,    50,    50,    46,    90,    53,    50,    55,    49,
      44,    46,   128,    41,    49,    49,    94,    48,   100,   123,
      49,    94,    43,    53,    43,    43,    43,    43,   100,    43,
     100,    43,    49,   100,    43,    47,    74,    72,    49,    50,
      48,    53,    55,    76,   111,    47,    76,   100,    76,    74,
      94,    26,    49,    76,    50,    97,    97,    46,    99,   111,
      49,    50,   129,    43,    76,    93,    49,    49,    94,    49,
      93,    49,    49,    49,   100,    74,    49,    49,   100,   100,
      10,    90,   100,    48,   100,    49,    76,   100,    26,    94,
      49,   128,    49,    93,    93,    49,    93,    74,    49,    74,
      74,    49,    49,    74,    49,    49,    90,    76,    76,   100,
      51,    93,    74,    74,    74,    49,   128,   129
    };
  }

/* YYR1[YYN] -- Symbol number of symbol that rule YYN derives.  */
  private static final short yyr1_[] = yyr1_init();
  private static final short[] yyr1_init()
  {
    return new short[]
    {
       0,    68,    69,    69,    69,    69,    69,    69,    69,    70,
      70,    70,    71,    72,    72,    73,    73,    73,    73,    73,
      73,    73,    73,    73,    73,    73,    73,    73,    73,    73,
      73,    74,    74,    75,    76,    76,    77,    77,    78,    79,
      79,    79,    79,    79,    79,    79,    80,    80,    81,    81,
      82,    82,    82,    82,    82,    82,    82,    83,    84,    85,
      86,    87,    87,    87,    87,    87,    88,    88,    88,    88,
      88,    88,    88,    89,    89,    89,    90,    90,    91,    91,
      91,    91,    91,    91,    91,    91,    92,    93,    93,    94,
      94,    94,    94,    95,    95,    95,    95,    95,    95,    96,
      96,    97,    97,    97,    97,    98,    98,    98,    99,    99,
     100,   100,   100,   101,   101,   102,   102,   103,   103,   104,
     104,   105,   105,   106,   106,   106,   107,   107,   107,   107,
     107,   108,   108,   108,   109,   109,   109,   109,   110,   110,
     110,   110,   110,   111,   111,   112,   112,   112,   113,   114,
     114,   114,   115,   115,   115,   116,   116,   117,   117,   117,
     117,   117,   118,   118,   118,   118,   119,   119,   120,   121,
     121,   121,   122,   122,   123,   123,   124,   124,   124,   125,
     125,   125,   125,   125,   125,   125,   126,   126,   126,   126,
     127,   127,   128,   128,   128,   128,   129,   129
    };
  }

/* YYR2[YYN] -- Number of symbols on the right hand side of rule YYN.  */
  private static final byte yyr2_[] = yyr2_init();
  private static final byte[] yyr2_init()
  {
    return new byte[]
    {
       0,     2,     3,     2,     2,     2,     3,     2,     0,     1,
       1,     1,     4,     0,     3,     1,     2,     3,     3,     2,
       1,     1,     1,     1,     1,     1,     1,     1,     1,     1,
       1,     1,     1,     1,     3,     2,     1,     2,     3,     2,
       3,     2,     3,     2,     2,     2,     5,     7,     5,     6,
       8,     7,     7,     6,     8,     7,     8,     5,     5,     7,
       5,     3,     3,     3,     3,     2,     4,     4,     5,     3,
       3,     3,     4,     2,     3,     4,     1,     3,     6,     7,
       5,     6,     5,     6,     4,     5,     4,     1,     1,     1,
       2,     3,     4,     4,     4,     4,     4,     4,     4,     3,
       4,     1,     1,     3,     3,     3,     1,     4,     1,     3,
       1,     1,     1,     3,     1,     3,     1,     1,     3,     1,
       3,     1,     3,     1,     3,     3,     1,     3,     3,     3,
       3,     1,     3,     3,     1,     3,     3,     3,     2,     2,
       2,     2,     1,     1,     4,     1,     2,     2,     1,     1,
       1,     1,     1,     1,     6,     3,     1,     1,     1,     1,
       1,     1,     1,     1,     1,     1,     4,     4,     5,     2,
       2,     2,     2,     2,     3,     2,     1,     1,     3,     2,
       4,     5,     5,     7,     7,     8,     5,     5,     4,     4,
       5,     6,     3,     1,     1,     1,     5,     0
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
     295,   296,   297,    59,    46,   123,   125,    58,    40,    41,
      44,    61,    91,    93,    60,    62,   124,    94,    38,    43,
      45,    42,    47,    37,    33,   126,    63,    35
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
  "MINUSMINUS", "PLUSPLUS", "STRING", "WILDCARD", "INT", "BOOL_LITERAL",
  "VARIABLE", "OTHER_LITERAL", "';'", "'.'", "'{'", "'}'", "':'", "'('",
  "')'", "','", "'='", "'['", "']'", "'<'", "'>'", "'|'", "'^'", "'&'",
  "'+'", "'-'", "'*'", "'/'", "'%'", "'!'", "'~'", "'?'", "'#'", "$accept",
  "grammar_file", "visibility_spec", "imports", "path", "statement_no_def",
  "statement", "blk_statement", "block", "statements", "grammar_rule",
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
  "ArrayAccess", "ConditionalExpression", "assignment", "field_access",
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
       0,   138,   138,   141,   142,   144,   145,   148,   151,   155,
     156,   157,   161,   167,   168,   172,   173,   174,   180,   186,
     187,   188,   189,   190,   191,   192,   193,   194,   195,   196,
     197,   201,   202,   206,   213,   214,   218,   219,   223,   227,
     228,   229,   230,   231,   232,   233,   237,   238,   244,   245,
     251,   253,   255,   257,   259,   261,   265,   274,   278,   284,
     290,   316,   323,   330,   337,   344,   353,   356,   359,   362,
     365,   368,   371,   377,   378,   381,   387,   388,   392,   395,
     398,   401,   404,   407,   410,   413,   418,   422,   423,   427,
     428,   429,   430,   438,   442,   446,   449,   452,   455,   464,
     467,   473,   474,   475,   476,   480,   481,   482,   488,   489,
     493,   494,   495,   500,   503,   507,   510,   514,   515,   521,
     522,   528,   529,   535,   536,   539,   545,   546,   549,   552,
     555,   561,   562,   565,   571,   572,   575,   578,   584,   588,
     592,   593,   594,   598,   599,   604,   605,   606,   610,   616,
     617,   618,   622,   623,   624,   628,   629,   633,   634,   635,
     636,   637,   641,   642,   643,   644,   648,   652,   656,   662,
     666,   667,   671,   677,   682,   683,   687,   688,   689,   693,
     694,   697,   700,   704,   708,   712,   719,   722,   725,   728,
     735,   738,   744,   745,   746,   747,   751,   754
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
       2,     2,     2,    64,     2,    67,     2,    63,    58,     2,
      48,    49,    61,    59,    50,    60,    44,    62,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,    47,    43,
      54,    51,    55,    66,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,    52,     2,    53,    57,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,    45,    56,    46,    65,     2,     2,     2,
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
      35,    36,    37,    38,    39,    40,    41,    42
    };
  }

  private static final byte yytranslate_ (int t)
  {
    if (t >= 0 && t <= yyuser_token_number_max_)
      return yytranslate_table_[t];
    else
      return yyundef_token_;
  }

  private static final int yylast_ = 1580;
  private static final int yynnts_ = 62;
  private static final int yyempty_ = -2;
  private static final int yyfinal_ = 147;
  private static final int yyterror_ = 1;
  private static final int yyerrcode_ = 256;
  private static final int yyntokens_ = 68;

  private static final int yyuser_token_number_max_ = 297;
  private static final int yyundef_token_ = 2;

/* User implementation code.  */
/* Unqualified %code blocks.  */
/* "VondaGrammar.y":58  */ /* lalr1.java:1066  */

  private List<RudiTree> _statements = new LinkedList<>();

  private GrammarFile _result = new GrammarFile(_statements);

  public GrammarFile getResult() { return _result; }

  public <T extends RudiTree> T setPos(T rt, Location l) {
    rt.setPos(l, this);
    return rt;
  }

  public <T extends RudiTree> T setPos(T rt, Location start, Location end) {
    rt.setPos(start, end, this);
    return rt;
  }

  public String getFullText(Position start, Position end) {
  	return ((VondaLexer)this.yylexer).getFullText(start, end);
  }
  
  // if the left part is a variable, this must be transformed to StatVarDef
  private RudiTree getAssignmentStat(RTExpression assign) {
    assert(assign instanceof ExpAssignment);
    if (((ExpAssignment)assign).leftIsVariable()) {
      return new StatVarDef(false, new Type(null), assign);
    }
    return new StatExpression(assign);
  }

/* "VondaGrammar.java":3295  */ /* lalr1.java:1066  */

}

