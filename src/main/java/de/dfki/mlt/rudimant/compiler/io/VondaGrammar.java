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
    /* "VondaGrammar.y":129  */ /* lalr1.java:489  */
    {
    yyval = ((LinkedList<RudiTree>)(yystack.valueAt (3-(3)))); ((StatMethodDeclaration)(yystack.valueAt (3-(2)))).setVisibility(((String)(yystack.valueAt (3-(1))))); ((LinkedList<RudiTree>)(yystack.valueAt (3-(3)))).addFirst(((StatMethodDeclaration)(yystack.valueAt (3-(2)))));
  };
  break;
    

  case 3:
  if (yyn == 3)
    /* "VondaGrammar.y":132  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))); ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))).addFirst(((StatMethodDeclaration)(yystack.valueAt (2-(1))))); };
  break;
    

  case 4:
  if (yyn == 4)
    /* "VondaGrammar.y":133  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))); ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))).addFirst(((RTStatement)(yystack.valueAt (2-(1))))); };
  break;
    

  case 5:
  if (yyn == 5)
    /* "VondaGrammar.y":135  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))); ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))).addFirst(((Import)(yystack.valueAt (2-(1))))); };
  break;
    

  case 6:
  if (yyn == 6)
    /* "VondaGrammar.y":136  */ /* lalr1.java:489  */
    {
    yyval = ((LinkedList<RudiTree>)(yystack.valueAt (3-(3)))); ((LinkedList<RudiTree>)(yystack.valueAt (3-(3)))).addFirst(setPos(new StatFieldDef(((String)(yystack.valueAt (3-(1)))), ((StatVarDef)(yystack.valueAt (3-(2))))), yystack.locationAt (3-(1)), yystack.locationAt (3-(2))));
  };
  break;
    

  case 7:
  if (yyn == 7)
    /* "VondaGrammar.y":139  */ /* lalr1.java:489  */
    {
    yyval = ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))); ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))).addFirst(setPos(new StatFieldDef(null, ((StatVarDef)(yystack.valueAt (2-(1))))), yystack.locationAt (2-(1))));
  };
  break;
    

  case 8:
  if (yyn == 8)
    /* "VondaGrammar.y":142  */ /* lalr1.java:489  */
    { yyval = _statements;};
  break;
    

  case 9:
  if (yyn == 9)
    /* "VondaGrammar.y":146  */ /* lalr1.java:489  */
    { yyval = "public"; };
  break;
    

  case 10:
  if (yyn == 10)
    /* "VondaGrammar.y":147  */ /* lalr1.java:489  */
    { yyval = "protected"; };
  break;
    

  case 11:
  if (yyn == 11)
    /* "VondaGrammar.y":148  */ /* lalr1.java:489  */
    { yyval = "private"; };
  break;
    

  case 12:
  if (yyn == 12)
    /* "VondaGrammar.y":152  */ /* lalr1.java:489  */
    {
    new Import((( String )(yystack.valueAt (4-(2)))), ((List<String>)(yystack.valueAt (4-(3)))).toArray(new String[((List<String>)(yystack.valueAt (4-(3)))).size()]));
  };
  break;
    

  case 13:
  if (yyn == 13)
    /* "VondaGrammar.y":158  */ /* lalr1.java:489  */
    { yyval = new ArrayList<String>(); };
  break;
    

  case 14:
  if (yyn == 14)
    /* "VondaGrammar.y":159  */ /* lalr1.java:489  */
    { yyval = ((List<String>)(yystack.valueAt (3-(3)))); ((List<String>)(yystack.valueAt (3-(3)))).add((( String )(yystack.valueAt (3-(2))))); };
  break;
    

  case 15:
  if (yyn == 15)
    /* "VondaGrammar.y":163  */ /* lalr1.java:489  */
    { yyval = ((StatAbstractBlock)(yystack.valueAt (1-(1)))); };
  break;
    

  case 16:
  if (yyn == 16)
    /* "VondaGrammar.y":164  */ /* lalr1.java:489  */
    { yyval = setPos(new StatExpression(((RTExpression)(yystack.valueAt (2-(1))))), (yyloc)); };
  break;
    

  case 17:
  if (yyn == 17)
    /* "VondaGrammar.y":165  */ /* lalr1.java:489  */
    {
    ExpSingleValue es = setPos(new ExpSingleValue("1", "int"), (yyloc));
    ExpArithmetic ar = new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(2)))), es, "+");
    setPos(ar, (yyloc));
    yyval = setPos(new StatExpression(ar), (yyloc));
  };
  break;
    

  case 18:
  if (yyn == 18)
    /* "VondaGrammar.y":171  */ /* lalr1.java:489  */
    {
    ExpSingleValue es = setPos(new ExpSingleValue("1", "int"), (yyloc));
    ExpArithmetic ar = new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(2)))), es, "-");
    setPos(ar, (yyloc));
    yyval = setPos(new StatExpression(ar), (yyloc));
  };
  break;
    

  case 19:
  if (yyn == 19)
    /* "VondaGrammar.y":177  */ /* lalr1.java:489  */
    { yyval = setPos(new StatExpression(((RTExpression)(yystack.valueAt (2-(1))))), (yyloc)); };
  break;
    

  case 20:
  if (yyn == 20)
    /* "VondaGrammar.y":178  */ /* lalr1.java:489  */
    { yyval = ((StatGrammarRule)(yystack.valueAt (1-(1)))); };
  break;
    

  case 21:
  if (yyn == 21)
    /* "VondaGrammar.y":179  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 22:
  if (yyn == 22)
    /* "VondaGrammar.y":180  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 23:
  if (yyn == 23)
    /* "VondaGrammar.y":181  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 24:
  if (yyn == 24)
    /* "VondaGrammar.y":182  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 25:
  if (yyn == 25)
    /* "VondaGrammar.y":183  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 26:
  if (yyn == 26)
    /* "VondaGrammar.y":184  */ /* lalr1.java:489  */
    { yyval = ((StatIf)(yystack.valueAt (1-(1)))); };
  break;
    

  case 27:
  if (yyn == 27)
    /* "VondaGrammar.y":185  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 28:
  if (yyn == 28)
    /* "VondaGrammar.y":186  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 29:
  if (yyn == 29)
    /* "VondaGrammar.y":187  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 30:
  if (yyn == 30)
    /* "VondaGrammar.y":188  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 31:
  if (yyn == 31)
    /* "VondaGrammar.y":192  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 32:
  if (yyn == 32)
    /* "VondaGrammar.y":193  */ /* lalr1.java:489  */
    { yyval = ((StatVarDef)(yystack.valueAt (1-(1)))); };
  break;
    

  case 33:
  if (yyn == 33)
    /* "VondaGrammar.y":199  */ /* lalr1.java:489  */
    { yyval = setPos(new StatAbstractBlock(((LinkedList<RTStatement>)(yystack.valueAt (3-(2)))), true), (yyloc)); };
  break;
    

  case 34:
  if (yyn == 34)
    /* "VondaGrammar.y":200  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatAbstractBlock(new ArrayList<RTStatement>(), true), (yyloc));
  };
  break;
    

  case 35:
  if (yyn == 35)
    /* "VondaGrammar.y":204  */ /* lalr1.java:489  */
    { yyval = new LinkedList<RTStatement>(){{ add(((RTStatement)(yystack.valueAt (1-(1))))); }}; };
  break;
    

  case 36:
  if (yyn == 36)
    /* "VondaGrammar.y":205  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RTStatement>)(yystack.valueAt (2-(2)))); ((LinkedList<RTStatement>)(yystack.valueAt (2-(2)))).addFirst(((RTStatement)(yystack.valueAt (2-(1))))); };
  break;
    

  case 37:
  if (yyn == 37)
    /* "VondaGrammar.y":209  */ /* lalr1.java:489  */
    { yyval = setPos(new StatGrammarRule((( String )(yystack.valueAt (3-(1)))), ((StatIf)(yystack.valueAt (3-(3))))), (yyloc)); };
  break;
    

  case 38:
  if (yyn == 38)
    /* "VondaGrammar.y":213  */ /* lalr1.java:489  */
    { yyval = setPos(new StatReturn("return"), (yyloc)); };
  break;
    

  case 39:
  if (yyn == 39)
    /* "VondaGrammar.y":214  */ /* lalr1.java:489  */
    { yyval = setPos(new StatReturn(((RTExpression)(yystack.valueAt (3-(2))))), (yyloc)); };
  break;
    

  case 40:
  if (yyn == 40)
    /* "VondaGrammar.y":215  */ /* lalr1.java:489  */
    { yyval = setPos(new StatReturn("break"), (yyloc)); };
  break;
    

  case 41:
  if (yyn == 41)
    /* "VondaGrammar.y":216  */ /* lalr1.java:489  */
    { yyval = setPos(new StatReturn("break", (( String )(yystack.valueAt (3-(2))))), (yyloc)); };
  break;
    

  case 42:
  if (yyn == 42)
    /* "VondaGrammar.y":217  */ /* lalr1.java:489  */
    { yyval = setPos(new StatReturn("cancel"), (yyloc)); };
  break;
    

  case 43:
  if (yyn == 43)
    /* "VondaGrammar.y":218  */ /* lalr1.java:489  */
    { yyval = setPos(new StatReturn("cancel_all"), (yyloc)); };
  break;
    

  case 44:
  if (yyn == 44)
    /* "VondaGrammar.y":219  */ /* lalr1.java:489  */
    { yyval = setPos(new StatReturn("continue"), (yyloc)); };
  break;
    

  case 45:
  if (yyn == 45)
    /* "VondaGrammar.y":223  */ /* lalr1.java:489  */
    { yyval = setPos(new StatIf(((RTExpression)(yystack.valueAt (5-(3)))), ((RTStatement)(yystack.valueAt (5-(5)))), null), (yyloc)); };
  break;
    

  case 46:
  if (yyn == 46)
    /* "VondaGrammar.y":224  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatIf(((RTExpression)(yystack.valueAt (7-(3)))), ((RTStatement)(yystack.valueAt (7-(5)))), ((RTStatement)(yystack.valueAt (7-(7))))), (yyloc));
  };
  break;
    

  case 47:
  if (yyn == 47)
    /* "VondaGrammar.y":230  */ /* lalr1.java:489  */
    { yyval = setPos(new StatWhile(((RTExpression)(yystack.valueAt (5-(3)))), ((RTStatement)(yystack.valueAt (5-(5)))), true), (yyloc)); };
  break;
    

  case 48:
  if (yyn == 48)
    /* "VondaGrammar.y":231  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatWhile(((RTExpression)(yystack.valueAt (6-(5)))), ((RTStatement)(yystack.valueAt (6-(2)))), false), (yyloc));
  };
  break;
    

  case 49:
  if (yyn == 49)
    /* "VondaGrammar.y":237  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatFor1(((StatVarDef)(yystack.valueAt (8-(3)))), ((RTExpression)(yystack.valueAt (8-(4)))), ((RTExpression)(yystack.valueAt (8-(6)))), ((RTStatement)(yystack.valueAt (8-(8))))), (yyloc)); };
  break;
    

  case 50:
  if (yyn == 50)
    /* "VondaGrammar.y":239  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatFor1(((StatVarDef)(yystack.valueAt (7-(3)))), null, ((RTExpression)(yystack.valueAt (7-(5)))), ((RTStatement)(yystack.valueAt (7-(7))))), (yyloc)); };
  break;
    

  case 51:
  if (yyn == 51)
    /* "VondaGrammar.y":241  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatFor1(((StatVarDef)(yystack.valueAt (7-(3)))), ((RTExpression)(yystack.valueAt (7-(4)))), null, ((RTStatement)(yystack.valueAt (7-(7))))), (yyloc)); };
  break;
    

  case 52:
  if (yyn == 52)
    /* "VondaGrammar.y":243  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatFor1(((StatVarDef)(yystack.valueAt (6-(3)))), null, null, ((RTStatement)(yystack.valueAt (6-(6))))), (yyloc)); };
  break;
    

  case 53:
  if (yyn == 53)
    /* "VondaGrammar.y":245  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatFor1(null, ((RTExpression)(yystack.valueAt (8-(4)))), ((RTExpression)(yystack.valueAt (8-(6)))), ((RTStatement)(yystack.valueAt (8-(8))))), (yyloc)); };
  break;
    

  case 54:
  if (yyn == 54)
    /* "VondaGrammar.y":247  */ /* lalr1.java:489  */
    {
    ExpVariable var = setPos(new ExpVariable((( String )(yystack.valueAt (7-(3))))), yystack.locationAt (7-(3)));
    yyval = setPos(new StatFor2(var, ((RTExpression)(yystack.valueAt (7-(5)))), ((RTStatement)(yystack.valueAt (7-(7))))), (yyloc));
  };
  break;
    

  case 55:
  if (yyn == 55)
    /* "VondaGrammar.y":251  */ /* lalr1.java:489  */
    {
    ExpVariable var = setPos(new ExpVariable((( String )(yystack.valueAt (8-(4))))), yystack.locationAt (8-(4)));
    yyval = setPos(new StatFor2(((Type)(yystack.valueAt (8-(3)))), var, ((RTExpression)(yystack.valueAt (8-(6)))), ((RTStatement)(yystack.valueAt (8-(8))))), (yyloc));
  };
  break;
    

  case 56:
  if (yyn == 56)
    /* "VondaGrammar.y":260  */ /* lalr1.java:489  */
    { yyval = setPos(new StatPropose(((RTExpression)(yystack.valueAt (5-(3)))), ((StatAbstractBlock)(yystack.valueAt (5-(5))))), (yyloc)); };
  break;
    

  case 57:
  if (yyn == 57)
    /* "VondaGrammar.y":264  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatTimeout(null, ((RTExpression)(yystack.valueAt (5-(3)))), ((StatAbstractBlock)(yystack.valueAt (5-(5))))), (yyloc));
  };
  break;
    

  case 58:
  if (yyn == 58)
    /* "VondaGrammar.y":270  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatTimeout(((RTExpression)(yystack.valueAt (7-(3)))), ((RTExpression)(yystack.valueAt (7-(5)))), ((StatAbstractBlock)(yystack.valueAt (7-(7))))), (yyloc));
  };
  break;
    

  case 59:
  if (yyn == 59)
    /* "VondaGrammar.y":276  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatSwitch(((RTExpression)(yystack.valueAt (5-(3)))), ((StatAbstractBlock)(yystack.valueAt (5-(5))))), (yyloc));
  };
  break;
    

  case 60:
  if (yyn == 60)
    /* "VondaGrammar.y":302  */ /* lalr1.java:489  */
    {
    ExpSingleValue val =
      new ExpSingleValue("case " + (( ExpSingleValue )(yystack.valueAt (3-(2)))).toString() + ":", "label");
    RTStatement lbl = val.ensureStatement();
    setPos(val, (yyloc)); setPos(lbl, (yyloc));
    yyval = lbl;
  };
  break;
    

  case 61:
  if (yyn == 61)
    /* "VondaGrammar.y":309  */ /* lalr1.java:489  */
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
    /* "VondaGrammar.y":316  */ /* lalr1.java:489  */
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
    /* "VondaGrammar.y":323  */ /* lalr1.java:489  */
    {
    ExpSingleValue val =
      new ExpSingleValue("case " + (( String )(yystack.valueAt (3-(2)))) + ":", "label");
    RTStatement lbl = val.ensureStatement();
    setPos(val, (yyloc)); setPos(lbl, (yyloc));
    yyval = lbl;
  };
  break;
    

  case 64:
  if (yyn == 64)
    /* "VondaGrammar.y":330  */ /* lalr1.java:489  */
    {
    ExpSingleValue val = new ExpSingleValue("default:", "label");
    RTStatement lbl = val.ensureStatement();
    setPos(val, (yyloc)); setPos(lbl, (yyloc));
    yyval = lbl;
  };
  break;
    

  case 65:
  if (yyn == 65)
    /* "VondaGrammar.y":339  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatVarDef(true, new Type(null), (( String )(yystack.valueAt (4-(2)))), ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc));
  };
  break;
    

  case 66:
  if (yyn == 66)
    /* "VondaGrammar.y":342  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatVarDef(false, ((Type)(yystack.valueAt (4-(1)))), (( String )(yystack.valueAt (4-(2)))), ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc));
  };
  break;
    

  case 67:
  if (yyn == 67)
    /* "VondaGrammar.y":345  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatVarDef(true, ((Type)(yystack.valueAt (5-(2)))), (( String )(yystack.valueAt (5-(3)))), ((RTExpression)(yystack.valueAt (5-(4))))), (yyloc));
  };
  break;
    

  case 68:
  if (yyn == 68)
    /* "VondaGrammar.y":348  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatVarDef(false, new Type(null), (( String )(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(2))))), (yyloc));
  };
  break;
    

  case 69:
  if (yyn == 69)
    /* "VondaGrammar.y":351  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatVarDef(true, new Type(null), (( String )(yystack.valueAt (3-(2)))), null), (yyloc));
  };
  break;
    

  case 70:
  if (yyn == 70)
    /* "VondaGrammar.y":354  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatVarDef(false, ((Type)(yystack.valueAt (3-(1)))), (( String )(yystack.valueAt (3-(2)))), null), (yyloc));
  };
  break;
    

  case 71:
  if (yyn == 71)
    /* "VondaGrammar.y":357  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatVarDef(true, ((Type)(yystack.valueAt (4-(2)))), (( String )(yystack.valueAt (4-(3)))), null), (yyloc));
  };
  break;
    

  case 72:
  if (yyn == 72)
    /* "VondaGrammar.y":363  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (2-(2)))); };
  break;
    

  case 73:
  if (yyn == 73)
    /* "VondaGrammar.y":364  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpListLiteral(new LinkedList<RTExpression>()), yystack.locationAt (3-(2)), yystack.locationAt (3-(3)));
  };
  break;
    

  case 74:
  if (yyn == 74)
    /* "VondaGrammar.y":367  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpListLiteral(((LinkedList<RTExpression>)(yystack.valueAt (4-(3))))), yystack.locationAt (4-(2)), yystack.locationAt (4-(4)));
  };
  break;
    

  case 75:
  if (yyn == 75)
    /* "VondaGrammar.y":373  */ /* lalr1.java:489  */
    { yyval = new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (1-(1))))); }}; };
  break;
    

  case 76:
  if (yyn == 76)
    /* "VondaGrammar.y":374  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))); ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))).addFirst(((RTExpression)(yystack.valueAt (3-(1))))); };
  break;
    

  case 77:
  if (yyn == 77)
    /* "VondaGrammar.y":378  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatMethodDeclaration("public", ((Type)(yystack.valueAt (6-(2)))), ((Type)(yystack.valueAt (6-(1)))), (( String )(yystack.valueAt (6-(3)))), null, ((StatAbstractBlock)(yystack.valueAt (6-(6))))), (yyloc));
  };
  break;
    

  case 78:
  if (yyn == 78)
    /* "VondaGrammar.y":381  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatMethodDeclaration("public", ((Type)(yystack.valueAt (7-(2)))), ((Type)(yystack.valueAt (7-(1)))), (( String )(yystack.valueAt (7-(3)))), ((LinkedList)(yystack.valueAt (7-(5)))), ((StatAbstractBlock)(yystack.valueAt (7-(7))))), (yyloc));
  };
  break;
    

  case 79:
  if (yyn == 79)
    /* "VondaGrammar.y":384  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatMethodDeclaration("public", null, ((Type)(yystack.valueAt (5-(1)))), (( String )(yystack.valueAt (5-(2)))), null, ((StatAbstractBlock)(yystack.valueAt (5-(5))))), (yyloc));
  };
  break;
    

  case 80:
  if (yyn == 80)
    /* "VondaGrammar.y":387  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatMethodDeclaration("public", null, ((Type)(yystack.valueAt (6-(1)))), (( String )(yystack.valueAt (6-(2)))), ((LinkedList)(yystack.valueAt (6-(4)))), ((StatAbstractBlock)(yystack.valueAt (6-(6))))), (yyloc));
  };
  break;
    

  case 81:
  if (yyn == 81)
    /* "VondaGrammar.y":390  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatMethodDeclaration("public", ((Type)(yystack.valueAt (5-(1)))), null, (( String )(yystack.valueAt (5-(2)))), null, ((StatAbstractBlock)(yystack.valueAt (5-(5))))), (yyloc));
  };
  break;
    

  case 82:
  if (yyn == 82)
    /* "VondaGrammar.y":393  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatMethodDeclaration("public", ((Type)(yystack.valueAt (6-(1)))), null, (( String )(yystack.valueAt (6-(2)))), ((LinkedList)(yystack.valueAt (6-(4)))), ((StatAbstractBlock)(yystack.valueAt (6-(6))))), (yyloc));
  };
  break;
    

  case 83:
  if (yyn == 83)
    /* "VondaGrammar.y":396  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatMethodDeclaration("public", null, null, (( String )(yystack.valueAt (4-(1)))), null, ((StatAbstractBlock)(yystack.valueAt (4-(4))))), (yyloc));
  };
  break;
    

  case 84:
  if (yyn == 84)
    /* "VondaGrammar.y":399  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatMethodDeclaration("public", null, null, (( String )(yystack.valueAt (5-(1)))), ((LinkedList)(yystack.valueAt (5-(3)))), ((StatAbstractBlock)(yystack.valueAt (5-(5))))), (yyloc));
  };
  break;
    

  case 85:
  if (yyn == 85)
    /* "VondaGrammar.y":404  */ /* lalr1.java:489  */
    { yyval = ((Type)(yystack.valueAt (4-(2)))); };
  break;
    

  case 86:
  if (yyn == 86)
    /* "VondaGrammar.y":408  */ /* lalr1.java:489  */
    { yyval = ((StatAbstractBlock)(yystack.valueAt (1-(1)))); };
  break;
    

  case 87:
  if (yyn == 87)
    /* "VondaGrammar.y":409  */ /* lalr1.java:489  */
    { yyval = null; };
  break;
    

  case 88:
  if (yyn == 88)
    /* "VondaGrammar.y":413  */ /* lalr1.java:489  */
    { yyval = new LinkedList(){{ add((( String )(yystack.valueAt (1-(1))))); }}; };
  break;
    

  case 89:
  if (yyn == 89)
    /* "VondaGrammar.y":414  */ /* lalr1.java:489  */
    { yyval = new LinkedList(){{ add(((Type)(yystack.valueAt (2-(1))))); add((( String )(yystack.valueAt (2-(2))))); }}; };
  break;
    

  case 90:
  if (yyn == 90)
    /* "VondaGrammar.y":415  */ /* lalr1.java:489  */
    { yyval = ((LinkedList)(yystack.valueAt (3-(3)))); ((LinkedList)(yystack.valueAt (3-(3)))).addFirst((( String )(yystack.valueAt (3-(1))))); };
  break;
    

  case 91:
  if (yyn == 91)
    /* "VondaGrammar.y":416  */ /* lalr1.java:489  */
    {
    yyval = ((LinkedList)(yystack.valueAt (4-(4)))); ((LinkedList)(yystack.valueAt (4-(4)))).addFirst((( String )(yystack.valueAt (4-(2))))); ((LinkedList)(yystack.valueAt (4-(4)))).addFirst(((Type)(yystack.valueAt (4-(1)))));
  };
  break;
    

  case 92:
  if (yyn == 92)
    /* "VondaGrammar.y":424  */ /* lalr1.java:489  */
    {
    ExpVariable var = setPos(new ExpVariable((( String )(yystack.valueAt (4-(1))))), yystack.locationAt (4-(1)));
    yyval = setPos(new StatSetOperation(var, true, ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc));
  };
  break;
    

  case 93:
  if (yyn == 93)
    /* "VondaGrammar.y":428  */ /* lalr1.java:489  */
    {
    ExpVariable var = setPos(new ExpVariable((( String )(yystack.valueAt (4-(1))))), yystack.locationAt (4-(1)));
    yyval = setPos(new StatSetOperation(var, false, ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc));
  };
  break;
    

  case 94:
  if (yyn == 94)
    /* "VondaGrammar.y":432  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatSetOperation(((RTExpression)(yystack.valueAt (4-(1)))), true, ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc));
  };
  break;
    

  case 95:
  if (yyn == 95)
    /* "VondaGrammar.y":435  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatSetOperation(((RTExpression)(yystack.valueAt (4-(1)))), false, ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc));
  };
  break;
    

  case 96:
  if (yyn == 96)
    /* "VondaGrammar.y":438  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatSetOperation(((RTExpression)(yystack.valueAt (4-(1)))), true, ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc));
  };
  break;
    

  case 97:
  if (yyn == 97)
    /* "VondaGrammar.y":441  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatSetOperation(((RTExpression)(yystack.valueAt (4-(1)))), false, ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc));
  };
  break;
    

  case 98:
  if (yyn == 98)
    /* "VondaGrammar.y":450  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpFuncCall((( String )(yystack.valueAt (3-(1)))), new LinkedList<RTExpression>(), false), (yyloc));
  };
  break;
    

  case 99:
  if (yyn == 99)
    /* "VondaGrammar.y":453  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpFuncCall((( String )(yystack.valueAt (4-(1)))), ((LinkedList<RTExpression>)(yystack.valueAt (4-(3)))), false), (yyloc));
  };
  break;
    

  case 100:
  if (yyn == 100)
    /* "VondaGrammar.y":459  */ /* lalr1.java:489  */
    { yyval = new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (1-(1))))); }}; };
  break;
    

  case 101:
  if (yyn == 101)
    /* "VondaGrammar.y":460  */ /* lalr1.java:489  */
    { yyval = new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (1-(1))))); }}; };
  break;
    

  case 102:
  if (yyn == 102)
    /* "VondaGrammar.y":461  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))); ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))).addFirst(((RTExpression)(yystack.valueAt (3-(1))))); };
  break;
    

  case 103:
  if (yyn == 103)
    /* "VondaGrammar.y":462  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))); ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))).addFirst(((RTExpression)(yystack.valueAt (3-(1))))); };
  break;
    

  case 104:
  if (yyn == 104)
    /* "VondaGrammar.y":466  */ /* lalr1.java:489  */
    { yyval = new Type("Array", new Type((( String )(yystack.valueAt (3-(1)))))); };
  break;
    

  case 105:
  if (yyn == 105)
    /* "VondaGrammar.y":467  */ /* lalr1.java:489  */
    { yyval = new Type((( String )(yystack.valueAt (1-(1))))); };
  break;
    

  case 106:
  if (yyn == 106)
    /* "VondaGrammar.y":468  */ /* lalr1.java:489  */
    {
    yyval = new Type((( String )(yystack.valueAt (4-(1)))), ((LinkedList<Type>)(yystack.valueAt (4-(3)))).toArray(new Type[((LinkedList<Type>)(yystack.valueAt (4-(3)))).size()]));
  };
  break;
    

  case 107:
  if (yyn == 107)
    /* "VondaGrammar.y":474  */ /* lalr1.java:489  */
    { yyval = new LinkedList<Type>(){{ add(((Type)(yystack.valueAt (1-(1))))); }}; };
  break;
    

  case 108:
  if (yyn == 108)
    /* "VondaGrammar.y":475  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<Type>)(yystack.valueAt (3-(3)))); ((LinkedList<Type>)(yystack.valueAt (3-(3)))).addFirst(((Type)(yystack.valueAt (3-(1))))); };
  break;
    

  case 109:
  if (yyn == 109)
    /* "VondaGrammar.y":479  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 110:
  if (yyn == 110)
    /* "VondaGrammar.y":480  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 111:
  if (yyn == 111)
    /* "VondaGrammar.y":481  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 112:
  if (yyn == 112)
    /* "VondaGrammar.y":486  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "||"), (yyloc));
  };
  break;
    

  case 113:
  if (yyn == 113)
    /* "VondaGrammar.y":489  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 114:
  if (yyn == 114)
    /* "VondaGrammar.y":493  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "&&"), (yyloc));
  };
  break;
    

  case 115:
  if (yyn == 115)
    /* "VondaGrammar.y":496  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 116:
  if (yyn == 116)
    /* "VondaGrammar.y":500  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 117:
  if (yyn == 117)
    /* "VondaGrammar.y":501  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "|"), (yyloc));
  };
  break;
    

  case 118:
  if (yyn == 118)
    /* "VondaGrammar.y":507  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 119:
  if (yyn == 119)
    /* "VondaGrammar.y":508  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "^"), (yyloc));
  };
  break;
    

  case 120:
  if (yyn == 120)
    /* "VondaGrammar.y":514  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 121:
  if (yyn == 121)
    /* "VondaGrammar.y":515  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "&"), (yyloc));
  };
  break;
    

  case 122:
  if (yyn == 122)
    /* "VondaGrammar.y":521  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 123:
  if (yyn == 123)
    /* "VondaGrammar.y":522  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "=="), (yyloc));
  };
  break;
    

  case 124:
  if (yyn == 124)
    /* "VondaGrammar.y":525  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "!="), (yyloc));
  };
  break;
    

  case 125:
  if (yyn == 125)
    /* "VondaGrammar.y":531  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 126:
  if (yyn == 126)
    /* "VondaGrammar.y":532  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "<"), (yyloc));
  };
  break;
    

  case 127:
  if (yyn == 127)
    /* "VondaGrammar.y":535  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), ">"), (yyloc));
  };
  break;
    

  case 128:
  if (yyn == 128)
    /* "VondaGrammar.y":538  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), ">="), (yyloc));
  };
  break;
    

  case 129:
  if (yyn == 129)
    /* "VondaGrammar.y":541  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "<="), (yyloc));
  };
  break;
    

  case 130:
  if (yyn == 130)
    /* "VondaGrammar.y":547  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 131:
  if (yyn == 131)
    /* "VondaGrammar.y":548  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "+"), (yyloc));
  };
  break;
    

  case 132:
  if (yyn == 132)
    /* "VondaGrammar.y":551  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "-"), (yyloc));
  };
  break;
    

  case 133:
  if (yyn == 133)
    /* "VondaGrammar.y":557  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 134:
  if (yyn == 134)
    /* "VondaGrammar.y":558  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "*"), (yyloc));
  };
  break;
    

  case 135:
  if (yyn == 135)
    /* "VondaGrammar.y":561  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "/"), (yyloc));
  };
  break;
    

  case 136:
  if (yyn == 136)
    /* "VondaGrammar.y":564  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "%"), (yyloc));
  };
  break;
    

  case 137:
  if (yyn == 137)
    /* "VondaGrammar.y":570  */ /* lalr1.java:489  */
    {
    ExpSingleValue es = setPos(new ExpSingleValue("1", "int"), (yyloc));
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2-(2)))), es, "+"), (yyloc));
  };
  break;
    

  case 138:
  if (yyn == 138)
    /* "VondaGrammar.y":574  */ /* lalr1.java:489  */
    {
    ExpSingleValue es = setPos(new ExpSingleValue("1", "int"), (yyloc));
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2-(2)))), es, "-"), (yyloc));
  };
  break;
    

  case 139:
  if (yyn == 139)
    /* "VondaGrammar.y":578  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2-(2)))), null, "+"), (yyloc)); };
  break;
    

  case 140:
  if (yyn == 140)
    /* "VondaGrammar.y":579  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2-(2)))), null, "-"), (yyloc)); };
  break;
    

  case 141:
  if (yyn == 141)
    /* "VondaGrammar.y":580  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 142:
  if (yyn == 142)
    /* "VondaGrammar.y":584  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 143:
  if (yyn == 143)
    /* "VondaGrammar.y":585  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpCast(((Type)(yystack.valueAt (4-(2)))), ((RTExpression)(yystack.valueAt (4-(4))))), (yyloc)); };
  break;
    

  case 144:
  if (yyn == 144)
    /* "VondaGrammar.y":590  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 145:
  if (yyn == 145)
    /* "VondaGrammar.y":591  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (2-(2)))), null, "!"), (yyloc)); };
  break;
    

  case 146:
  if (yyn == 146)
    /* "VondaGrammar.y":592  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2-(2)))), null, "~"), (yyloc)); };
  break;
    

  case 147:
  if (yyn == 147)
    /* "VondaGrammar.y":596  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 148:
  if (yyn == 148)
    /* "VondaGrammar.y":602  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpSingleValue("null", "null"), (yyloc)); };
  break;
    

  case 149:
  if (yyn == 149)
    /* "VondaGrammar.y":603  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 150:
  if (yyn == 150)
    /* "VondaGrammar.y":604  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 151:
  if (yyn == 151)
    /* "VondaGrammar.y":608  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpVariable((( String )(yystack.valueAt (1-(1))))), (yyloc)); };
  break;
    

  case 152:
  if (yyn == 152)
    /* "VondaGrammar.y":609  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 153:
  if (yyn == 153)
    /* "VondaGrammar.y":613  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (3-(2)))); };
  break;
    

  case 154:
  if (yyn == 154)
    /* "VondaGrammar.y":614  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 155:
  if (yyn == 155)
    /* "VondaGrammar.y":618  */ /* lalr1.java:489  */
    { yyval = ((ExpSingleValue)(yystack.valueAt (1-(1)))); };
  break;
    

  case 156:
  if (yyn == 156)
    /* "VondaGrammar.y":619  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 157:
  if (yyn == 157)
    /* "VondaGrammar.y":620  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 158:
  if (yyn == 158)
    /* "VondaGrammar.y":621  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 159:
  if (yyn == 159)
    /* "VondaGrammar.y":622  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 160:
  if (yyn == 160)
    /* "VondaGrammar.y":626  */ /* lalr1.java:489  */
    { yyval = (( ExpSingleValue )(yystack.valueAt (1-(1)))); setPos((( ExpSingleValue )(yystack.valueAt (1-(1)))), (yyloc)); };
  break;
    

  case 161:
  if (yyn == 161)
    /* "VondaGrammar.y":627  */ /* lalr1.java:489  */
    { yyval = (( ExpSingleValue )(yystack.valueAt (1-(1)))); setPos((( ExpSingleValue )(yystack.valueAt (1-(1)))), (yyloc)); };
  break;
    

  case 162:
  if (yyn == 162)
    /* "VondaGrammar.y":628  */ /* lalr1.java:489  */
    { yyval = (( ExpSingleValue )(yystack.valueAt (1-(1)))); setPos((( ExpSingleValue )(yystack.valueAt (1-(1)))), (yyloc)); };
  break;
    

  case 163:
  if (yyn == 163)
    /* "VondaGrammar.y":629  */ /* lalr1.java:489  */
    { yyval = (( ExpSingleValue )(yystack.valueAt (1-(1)))); setPos((( ExpSingleValue )(yystack.valueAt (1-(1)))), (yyloc)); };
  break;
    

  case 164:
  if (yyn == 164)
    /* "VondaGrammar.y":633  */ /* lalr1.java:489  */
    {
    ExpVariable var = setPos(new ExpVariable((( String )(yystack.valueAt (4-(1))))), yystack.locationAt (4-(1)));
    yyval = setPos(new ExpArrayAccess(var, ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc));
  };
  break;
    

  case 165:
  if (yyn == 165)
    /* "VondaGrammar.y":637  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpArrayAccess(((RTExpression)(yystack.valueAt (4-(1)))), ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc)); };
  break;
    

  case 166:
  if (yyn == 166)
    /* "VondaGrammar.y":641  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpConditional(((RTExpression)(yystack.valueAt (5-(1)))), ((RTExpression)(yystack.valueAt (5-(3)))), ((RTExpression)(yystack.valueAt (5-(5))))), (yyloc)); };
  break;
    

  case 167:
  if (yyn == 167)
    /* "VondaGrammar.y":647  */ /* lalr1.java:489  */
    {
    ExpVariable var = setPos(new ExpVariable((( String )(yystack.valueAt (2-(1))))), yystack.locationAt (2-(1)));
    yyval = setPos(new ExpAssignment(var, ((RTExpression)(yystack.valueAt (2-(2))))), (yyloc));
  };
  break;
    

  case 168:
  if (yyn == 168)
    /* "VondaGrammar.y":651  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpAssignment(((RTExpression)(yystack.valueAt (2-(1)))), ((RTExpression)(yystack.valueAt (2-(2))))), (yyloc)); };
  break;
    

  case 169:
  if (yyn == 169)
    /* "VondaGrammar.y":652  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpAssignment(((RTExpression)(yystack.valueAt (2-(1)))), ((RTExpression)(yystack.valueAt (2-(2))))), (yyloc)); };
  break;
    

  case 170:
  if (yyn == 170)
    /* "VondaGrammar.y":656  */ /* lalr1.java:489  */
    {
    //List<String> repr = new ArrayList<>($2.size());
    //for (int i = 0; i < $2.size(); ++i) repr.add("");
    //$$ = setPos(new ExpFieldAccess($2, repr), @$); $2.addFirst($1);
    yyval = setPos(new ExpFieldAccess(((LinkedList<RTExpression>)(yystack.valueAt (2-(2))))), (yyloc)); ((LinkedList<RTExpression>)(yystack.valueAt (2-(2)))).addFirst(((RTExpression)(yystack.valueAt (2-(1)))));
  };
  break;
    

  case 171:
  if (yyn == 171)
    /* "VondaGrammar.y":662  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpFieldAccess(((LinkedList<RTExpression>)(yystack.valueAt (2-(2))))), (yyloc)); ((LinkedList<RTExpression>)(yystack.valueAt (2-(2)))).addFirst(((RTExpression)(yystack.valueAt (2-(1))))); };
  break;
    

  case 172:
  if (yyn == 172)
    /* "VondaGrammar.y":667  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))); ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))).addFirst(((RTExpression)(yystack.valueAt (3-(2))))); };
  break;
    

  case 173:
  if (yyn == 173)
    /* "VondaGrammar.y":668  */ /* lalr1.java:489  */
    { yyval = new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (2-(2))))); }}; };
  break;
    

  case 174:
  if (yyn == 174)
    /* "VondaGrammar.y":672  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpVariable((( String )(yystack.valueAt (1-(1))))), (yyloc)); };
  break;
    

  case 175:
  if (yyn == 175)
    /* "VondaGrammar.y":673  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 176:
  if (yyn == 176)
    /* "VondaGrammar.y":674  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (3-(2)))); };
  break;
    

  case 177:
  if (yyn == 177)
    /* "VondaGrammar.y":678  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpNew(new Type((( String )(yystack.valueAt (2-(2)))))), (yyloc)); };
  break;
    

  case 178:
  if (yyn == 178)
    /* "VondaGrammar.y":679  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpNew(new Type((( String )(yystack.valueAt (4-(2))))), new LinkedList<>()), (yyloc));
  };
  break;
    

  case 179:
  if (yyn == 179)
    /* "VondaGrammar.y":682  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpNew(new Type((( String )(yystack.valueAt (5-(2))))), ((LinkedList<RTExpression>)(yystack.valueAt (5-(4))))), (yyloc));
  };
  break;
    

  case 180:
  if (yyn == 180)
    /* "VondaGrammar.y":685  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpNew(new Type("Array", new Type((( String )(yystack.valueAt (5-(2)))))),
                    new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (5-(4))))); }}), (yyloc));
  };
  break;
    

  case 181:
  if (yyn == 181)
    /* "VondaGrammar.y":689  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpNew(new Type("Array", new Type((( String )(yystack.valueAt (7-(2)))))),
                    new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (7-(6))))); }}), (yyloc));
  };
  break;
    

  case 182:
  if (yyn == 182)
    /* "VondaGrammar.y":693  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpNew(new Type((( String )(yystack.valueAt (7-(2)))), ((LinkedList<Type>)(yystack.valueAt (7-(4)))).toArray(new Type[((LinkedList<Type>)(yystack.valueAt (7-(4)))).size()])),
                    new LinkedList<>()), (yyloc));
  };
  break;
    

  case 183:
  if (yyn == 183)
    /* "VondaGrammar.y":697  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpNew(new Type((( String )(yystack.valueAt (8-(2)))), ((LinkedList<Type>)(yystack.valueAt (8-(4)))).toArray(new Type[((LinkedList<Type>)(yystack.valueAt (8-(4)))).size()])),
                    ((LinkedList<RTExpression>)(yystack.valueAt (8-(7))))), (yyloc));
  };
  break;
    

  case 184:
  if (yyn == 184)
    /* "VondaGrammar.y":704  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpLambda(((LinkedList)(yystack.valueAt (5-(2)))), ((RTExpression)(yystack.valueAt (5-(5))))), (yyloc));
  };
  break;
    

  case 185:
  if (yyn == 185)
    /* "VondaGrammar.y":707  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpLambda(((LinkedList)(yystack.valueAt (5-(2)))), ((StatAbstractBlock)(yystack.valueAt (5-(5))))), (yyloc));
  };
  break;
    

  case 186:
  if (yyn == 186)
    /* "VondaGrammar.y":710  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpLambda(new LinkedList<>(), ((RTExpression)(yystack.valueAt (4-(4))))), (yyloc));
  };
  break;
    

  case 187:
  if (yyn == 187)
    /* "VondaGrammar.y":713  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpLambda(new LinkedList<>(), ((StatAbstractBlock)(yystack.valueAt (4-(4))))), (yyloc));
  };
  break;
    

  case 188:
  if (yyn == 188)
    /* "VondaGrammar.y":720  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpDialogueAct(((RTExpression)(yystack.valueAt (5-(2)))), ((RTExpression)(yystack.valueAt (5-(4)))), new LinkedList<RTExpression>()), (yyloc));
  };
  break;
    

  case 189:
  if (yyn == 189)
    /* "VondaGrammar.y":723  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpDialogueAct(((RTExpression)(yystack.valueAt (6-(2)))), ((RTExpression)(yystack.valueAt (6-(4)))), ((LinkedList<RTExpression>)(yystack.valueAt (6-(5))))), (yyloc));
  };
  break;
    

  case 190:
  if (yyn == 190)
    /* "VondaGrammar.y":729  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (3-(2)))); };
  break;
    

  case 191:
  if (yyn == 191)
    /* "VondaGrammar.y":730  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpVariable((( String )(yystack.valueAt (1-(1))))), (yyloc)); };
  break;
    

  case 192:
  if (yyn == 192)
    /* "VondaGrammar.y":731  */ /* lalr1.java:489  */
    { yyval = (( ExpSingleValue )(yystack.valueAt (1-(1)))); };
  break;
    

  case 193:
  if (yyn == 193)
    /* "VondaGrammar.y":732  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpSingleValue((( String )(yystack.valueAt (1-(1)))), "String"), (yyloc)); };
  break;
    

  case 194:
  if (yyn == 194)
    /* "VondaGrammar.y":736  */ /* lalr1.java:489  */
    {
    yyval = ((LinkedList<RTExpression>)(yystack.valueAt (5-(5)))); ((LinkedList<RTExpression>)(yystack.valueAt (5-(5)))).addFirst(((RTExpression)(yystack.valueAt (5-(4))))); ((LinkedList<RTExpression>)(yystack.valueAt (5-(5)))).addFirst(((RTExpression)(yystack.valueAt (5-(2)))));
  };
  break;
    

  case 195:
  if (yyn == 195)
    /* "VondaGrammar.y":739  */ /* lalr1.java:489  */
    { yyval = new LinkedList<RTExpression>(); };
  break;
    


/* "VondaGrammar.java":2072  */ /* lalr1.java:489  */
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
  private static final short yytable_ninf_ = -152;

  /* YYPACT[STATE-NUM] -- Index in YYTABLE of the portion describing
   STATE-NUM.  */
  private static final short yypact_[] = yypact_init();
  private static final short[] yypact_init()
  {
    return new short[]
    {
     733,   267,   -37,    22,   340,    53,    99,   899,    52,    66,
     133,   179,   204,  -314,   231,  -314,  -314,   629,   245,   276,
     285,   288,  1362,  1362,  -314,  -314,  -314,   205,  -314,   805,
    1396,   213,   277,   347,    35,   733,   733,  -314,  -314,  -314,
    -314,  -314,  -314,  -314,  -314,  -314,  -314,  -314,   733,   733,
     299,  -314,   -17,   313,   317,   305,  -314,  -314,    96,   323,
     107,  -314,  -314,   326,  -314,  -314,  -314,   327,   354,   365,
     379,  -314,  -314,   265,   405,   292,   391,    19,  1396,   389,
     316,  1396,  -314,  1362,  1362,   351,  -314,  1430,  1464,  1464,
    1362,  1362,   317,   -38,   408,   424,   396,   398,   395,   353,
      88,   328,    63,  -314,  -314,  -314,  -314,  -314,   317,   305,
     406,  -314,  -314,   406,  1396,  1396,  1396,  1396,   108,   413,
    -314,  -314,   415,  1396,  1396,   446,   913,   948,   982,   213,
     417,   217,  -314,  -314,   852,   419,  -314,   420,    46,   296,
     410,  -314,  -314,  -314,  1396,   418,  -314,   311,   733,   733,
    -314,  -314,  -314,  -314,   324,   428,  -314,   114,  -314,   143,
    -314,  1396,  1396,  1396,  -314,  -314,  1396,  1396,  -314,  -314,
    -314,  -314,  -314,  -314,  1016,  1396,  -314,   422,  -314,   423,
     429,    12,   141,  1396,  1051,   430,    56,   432,   435,  1085,
    1120,   213,   112,  -314,  -314,   304,   426,  -314,  -314,  -314,
    -314,  -314,  1396,  1464,  1464,  1464,  1464,  1464,  1464,  1464,
    1464,  1464,  1464,  1464,  1464,  1464,  1464,  1464,  1464,   116,
     -49,   131,   134,  -314,  -314,   -22,     9,  -314,   253,  1154,
     434,   436,   437,   447,    -5,   439,  1189,   421,  -314,   -33,
     442,   438,  -314,  -314,  -314,   102,  -314,   454,   -27,   277,
     199,   417,  -314,  -314,   206,   451,   452,  1396,  -314,   317,
    -314,   240,   459,   136,    16,    34,    38,    67,  -314,  1396,
    -314,  -314,   461,  1396,    73,  1223,    78,   294,   899,   389,
    -314,  -314,   456,    61,   458,   229,   453,   434,  1464,   -32,
     424,   396,   398,   395,   353,    88,    88,   328,   328,   328,
     328,    63,    63,  -314,  -314,  -314,   434,  1396,   434,   899,
    -314,  -314,   466,   483,   463,   279,  -314,   434,  -314,   460,
    1498,  1498,  -314,   467,  -314,   213,  -314,  -314,  -314,   355,
     280,   434,   384,   465,   282,   135,  -314,   384,   469,  -314,
    -314,  -314,  -314,  -314,  -314,   192,  -314,   194,  1396,   899,
     197,  1258,  1396,   506,  -314,  -314,  1396,  1396,  -314,   471,
    -314,  -314,  1396,  -314,   218,  -314,  -314,  -314,  1293,   497,
    -314,   466,  -314,  -314,  -314,  -314,  -314,   277,   476,  -314,
    -314,  -314,   384,   384,   477,  -314,  -314,   384,  -314,   899,
     221,  -314,   899,   899,   225,   226,   899,  -314,   234,  1327,
     421,   434,  -314,   421,  1293,  -314,   480,  -314,  -314,  -314,
     384,  -314,  -314,   899,  -314,  -314,   899,   899,  -314,  -314,
    -314,   478,  -314,  -314,   421,   277,  -314,  -314,  -314,  -314,
    -314,   482,  -314
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
       0,     0,     0,     0,   160,   161,   163,   105,   162,     0,
       0,     0,     0,     0,     0,     8,     8,    15,    20,    22,
      26,    27,    28,    23,    25,    24,    29,    30,     8,     8,
       0,    21,   158,     0,     0,     0,   154,   155,   156,     0,
     157,   152,   159,     0,    40,    42,    43,     0,     0,     0,
       0,    44,    64,   151,     0,   105,     0,     0,     0,    13,
     177,     0,   148,     0,     0,   151,    38,     0,     0,     0,
       0,     0,   158,     0,   111,   113,   115,   116,   118,   120,
     122,   125,   130,   142,   133,   141,   144,   147,   149,   150,
     156,   109,   110,   157,     0,     0,     0,     0,   151,     0,
     156,   157,     0,     0,     0,     0,     0,     0,     0,     0,
       0,   105,    34,    31,    35,     0,    32,     0,     0,   105,
       0,   192,   193,   191,     0,     0,     1,   105,     8,     8,
       5,     4,     7,     3,   105,     0,    19,     0,   171,     0,
     170,     0,     0,     0,   169,    16,     0,     0,   168,    41,
      60,    61,    62,    63,     0,     0,   167,     0,    69,     0,
       0,     0,   105,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,   138,   137,   151,     0,   139,   140,   145,
     146,    39,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,    18,    17,     0,     0,    37,   151,     0,
      98,     0,     0,     0,   100,   101,     0,    72,   104,     0,
     107,     0,    68,    36,    33,     0,   153,     0,     0,     0,
       0,     0,     6,     2,     0,     0,   174,     0,   175,   173,
      70,     0,     0,     0,     0,     0,     0,     0,    98,     0,
      65,    71,     0,     0,     0,     0,     0,     0,     0,    13,
      12,   178,     0,    75,     0,     0,     0,     0,     0,     0,
     112,   114,   117,   119,   121,   123,   124,   128,   129,   126,
     127,   131,   132,   134,   135,   136,     0,     0,     0,     0,
      93,    92,     0,     0,     0,     0,    83,     0,    99,    89,
       0,     0,    73,     0,   164,     0,   106,    85,   190,     0,
      88,     0,     0,     0,     0,     0,   172,     0,     0,    66,
     165,    95,    94,    97,    96,     0,    67,     0,     0,     0,
       0,     0,     0,    45,    14,   179,     0,     0,   180,     0,
      56,   143,     0,    59,     0,    57,    47,    90,     0,     0,
      84,     0,   102,   103,    74,   108,   188,     0,     0,    87,
      86,    79,     0,     0,     0,   176,    81,     0,    48,     0,
       0,    52,     0,     0,     0,     0,     0,    76,     0,     0,
     166,     0,   187,   186,     0,    91,     0,   189,    80,    77,
       0,    82,    54,     0,    50,    51,     0,     0,    46,   181,
     182,     0,    58,   185,   184,     0,    78,    53,    49,    55,
     183,   195,   194
    };
  }

/* YYPGOTO[NTERM-NUM].  */
  private static final short yypgoto_[] = yypgoto_init();
  private static final short[] yypgoto_init()
  {
    return new short[]
    {
    -314,    50,  -314,  -314,   256,    51,  -314,  -214,   402,  -314,
    -314,   412,  -314,  -314,  -314,  -314,  -314,  -314,  -314,   -21,
     315,  -224,   504,  -314,  -313,  -197,  -314,     2,   121,   472,
    -187,   367,  -314,   336,   339,   335,   341,   345,   200,   210,
     235,   -12,   -46,  -314,  -314,  -314,   128,   237,  -314,  -314,
       0,  -314,   290,    18,   -40,  -314,  -314,  -314,  -314,  -246,
     117
    };
  }

/* YYDEFGOTO[NTERM-NUM].  */
  private static final short yydefgoto_[] = yydefgoto_init();
  private static final short[] yydefgoto_init()
  {
    return new short[]
    {
      -1,    33,    34,    35,   188,    36,   134,    37,   135,    38,
      39,    40,    41,    42,    43,    44,    45,    46,    47,    48,
     176,   282,    49,    50,   381,   231,    51,    92,   232,    53,
     241,   234,    94,    95,    96,    97,    98,    99,   100,   101,
     102,   103,   104,   105,   106,   107,   108,   109,    56,    57,
     110,   111,   112,   113,   158,   259,    61,   235,    62,   145,
     378
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
      58,   307,    52,   329,   286,   201,    65,    58,   136,    52,
     119,   122,   323,   148,   160,   362,   316,   202,    60,   328,
     324,   310,   120,   120,   386,    60,   156,   157,   202,    58,
       8,    52,   314,   202,   202,    58,    58,    52,    52,   202,
     121,   121,   197,   198,   202,   320,     8,    60,    58,    58,
      52,    52,   311,    60,    60,   271,   184,   333,    74,   341,
     182,   202,   183,   127,   338,    66,    60,    60,   160,   408,
     409,   193,   194,   360,   411,   202,   147,   342,   199,   200,
     133,   343,   202,   120,   120,   150,   151,    31,   120,   120,
     120,   120,   363,    75,   365,   246,    71,   426,   152,   153,
     202,   121,   121,   370,   202,   278,   121,   121,   121,   121,
     344,   356,   202,   136,    77,   367,   348,   316,   380,   210,
     211,   351,   202,   380,   216,   217,   218,   202,    54,   162,
     163,   406,   397,   202,    58,    54,    52,   384,   375,   202,
     166,   167,   212,   213,   202,   260,    72,   127,    58,    58,
      52,    52,    60,   127,   402,   256,   174,    54,   127,   258,
     175,   287,   257,    54,    54,   306,    60,    60,   380,   380,
     303,   304,   305,   380,   405,   421,    54,    54,   202,   431,
     308,    78,   202,   309,   385,   133,   260,   422,   273,   340,
     423,   261,   127,   179,   127,   129,   380,   202,   252,   253,
     202,   202,   202,   120,   120,   120,   120,   120,   120,   120,
     120,   120,   120,   120,   120,   120,   120,   120,   120,   336,
      79,   121,   121,   121,   121,   121,   121,   121,   121,   121,
     121,   121,   121,   121,   121,   121,   121,    55,   123,   124,
     330,   388,   361,   389,    55,    80,   392,   330,   331,  -151,
     123,   124,   125,   126,   139,   332,   127,   128,   202,   129,
     202,  -151,    54,   202,   125,   174,    55,   401,   127,   128,
     413,   129,    55,    55,   416,   417,    54,    54,    58,    81,
      52,   330,   358,   419,   202,    55,    55,   202,   120,   337,
      59,   202,   202,   114,  -105,   202,    60,    59,   123,   124,
     202,   174,   -88,   312,   127,   128,   121,   129,    63,    58,
      64,    52,   125,   174,   141,   142,   127,   175,   143,    59,
     319,  -105,   144,   330,   115,    59,    59,    60,   288,   353,
     312,   383,   179,   116,   129,   178,   117,   260,    59,    59,
     154,   352,   130,   127,   179,   127,   129,   146,   179,    58,
     129,    52,   174,  -105,   159,   127,   128,   161,   129,   250,
     366,   157,   127,   179,   189,   129,   165,    60,   190,   169,
     191,    55,   254,   164,   170,   168,   179,    67,   129,    68,
      69,    70,   208,   209,    93,    55,    55,   214,   215,    58,
     180,    52,    58,    58,    52,    52,    58,   138,    52,   174,
     391,   171,   127,   175,   376,   377,    54,    60,   295,   296,
      60,    60,   172,    58,    60,    52,    58,    58,    52,    52,
     297,   298,   299,   300,    59,   164,   173,   379,   168,    29,
     177,    60,   181,   187,    60,    60,   203,    54,    59,    59,
     412,   372,   373,   414,   415,   186,   130,   418,   192,   301,
     302,   204,   205,   207,   138,   206,   223,   127,   224,    10,
     242,   245,   251,   247,   427,   244,   249,   428,   429,   255,
     269,   277,   270,   279,   262,   288,   238,    54,   280,    29,
      76,   219,   220,   221,   222,   317,   318,   202,   319,   321,
     225,   226,   325,   326,   237,   239,   272,   251,   327,   334,
     174,   137,   339,   140,   346,   355,   357,   330,   359,   368,
     371,   248,   369,   374,   382,    55,   396,    54,   387,   399,
      54,    54,   155,   404,    54,   407,   410,   430,   263,   264,
     265,   425,   377,   266,   267,   354,   243,   227,   149,   290,
     292,    54,   239,   291,    54,    54,    55,   293,   432,   185,
     274,   276,   294,     0,     0,     0,   283,   285,     0,   196,
     262,     0,     0,     0,     0,     0,     0,     0,    59,   289,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,    55,     0,     0,     0,
       0,     0,   262,     0,     0,     0,   138,     0,   233,    59,
       0,   240,     0,   283,     0,     0,   137,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,   335,     0,    55,     0,     0,    55,
      55,     0,     0,    55,     0,     0,   345,     0,     0,    59,
     347,     0,   350,     0,    12,    82,     0,     0,     0,     0,
      55,     0,     0,    55,    55,     0,     0,     0,     0,     0,
       0,     0,     0,   240,    83,    84,    24,     0,    25,    26,
      85,    28,    86,     0,   364,     0,     0,    87,     0,    59,
       0,     0,    59,    59,     0,     0,    59,     0,    88,    89,
       0,     0,     0,    90,    91,     0,    32,     0,     0,     0,
       0,   315,     0,    59,     0,     0,    59,    59,     0,     0,
       0,     0,     0,     0,     0,   390,     0,     0,   394,   395,
       0,     0,   233,   283,   398,     0,   233,     0,     0,   400,
       0,     0,     0,   233,     0,   403,     1,     2,     3,     4,
       5,     6,     7,     0,     8,     9,    10,    11,    12,     0,
      13,    14,    15,    16,    17,    18,    19,    20,    21,     0,
       0,     0,     0,     0,     0,     0,   283,     0,    22,    23,
      24,   424,    25,    26,    27,    28,     0,     0,    29,     0,
       0,    30,     0,     0,   233,    31,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,   240,     0,     0,
      32,     0,     0,     0,     0,     0,   233,     0,     1,     2,
       3,     4,     5,     6,     7,     0,     8,     9,    10,     0,
      12,     0,     0,    14,     0,     0,    17,    18,    19,    20,
      21,     0,     0,     0,     0,     0,     0,     0,     0,     0,
      22,    23,    24,   233,    25,    26,   131,    28,     0,     0,
      29,   132,     0,    30,     0,     1,     2,     3,     4,     5,
       6,     7,     0,     8,     9,    10,     0,    12,     0,     0,
      14,     0,    32,    17,    18,    19,    20,    21,     0,     0,
       0,     0,     0,     0,     0,     0,     0,    22,    23,    24,
       0,    25,    26,   131,    28,     0,     0,    29,     0,     0,
      30,     0,     1,     2,     3,     4,     5,     6,     7,     0,
       0,     9,    10,     0,    12,     0,     0,    14,     0,    32,
      17,    18,    19,    20,    21,     0,     0,     0,    12,    82,
       0,     0,     0,     0,    22,    23,    24,     0,    25,    26,
      73,    28,     0,     0,    29,     0,     0,    30,    83,    84,
      24,     0,    25,    26,   228,    28,     0,     0,     0,     0,
       0,   229,   230,    12,    82,     0,    32,     0,     0,     0,
       0,     0,    88,    89,     0,     0,     0,    90,    91,     0,
      32,     0,     0,    83,    84,    24,     0,    25,    26,    85,
      28,     0,     0,   236,     0,     0,    87,    12,    82,     0,
       0,     0,     0,     0,     0,     0,     0,    88,    89,     0,
       0,     0,    90,    91,     0,    32,     0,    83,    84,    24,
       0,    25,    26,    85,    28,     0,     0,     0,     0,     0,
      87,    12,    82,     0,     0,   238,     0,     0,     0,     0,
       0,    88,    89,     0,     0,     0,    90,    91,     0,    32,
       0,    83,    84,    24,     0,    25,    26,    85,    28,     0,
       0,     0,     0,     0,   229,   268,    12,    82,     0,     0,
       0,     0,     0,     0,     0,    88,    89,     0,     0,     0,
      90,    91,     0,    32,     0,     0,    83,    84,    24,     0,
      25,    26,    85,    28,   275,     0,     0,     0,     0,    87,
      12,    82,     0,     0,     0,     0,     0,     0,     0,     0,
      88,    89,     0,     0,     0,    90,    91,     0,    32,     0,
      83,    84,    24,     0,    25,    26,    85,    28,     0,     0,
       0,     0,     0,    87,   281,    12,    82,     0,     0,     0,
       0,     0,     0,     0,    88,    89,     0,     0,     0,    90,
      91,     0,    32,     0,     0,    83,    84,    24,     0,    25,
      26,    85,    28,     0,     0,     0,     0,     0,    87,    12,
      82,     0,     0,   284,     0,     0,     0,     0,     0,    88,
      89,     0,     0,     0,    90,    91,     0,    32,     0,    83,
      84,    24,     0,    25,    26,   228,    28,     0,     0,     0,
       0,     0,    87,   313,    12,    82,     0,     0,     0,     0,
       0,     0,     0,    88,    89,     0,     0,     0,    90,    91,
       0,    32,     0,     0,    83,    84,    24,     0,    25,    26,
      85,    28,     0,     0,     0,   322,     0,    87,    12,    82,
       0,     0,     0,     0,     0,     0,     0,     0,    88,    89,
       0,     0,     0,    90,    91,     0,    32,     0,    83,    84,
      24,     0,    25,    26,    85,    28,     0,     0,     0,     0,
       0,    87,   349,    12,    82,     0,     0,     0,     0,     0,
       0,     0,    88,    89,     0,     0,     0,    90,    91,     0,
      32,     0,     0,    83,    84,    24,     0,    25,    26,    85,
      28,     0,     0,     0,     0,     0,    87,   393,    12,    82,
       0,     0,     0,     0,     0,     0,     0,    88,    89,     0,
       0,     0,    90,    91,     0,    32,     0,     0,    83,    84,
      24,     0,    25,    26,    85,    28,     0,     0,    29,     0,
       0,    87,    12,    82,     0,     0,     0,     0,     0,     0,
       0,     0,    88,    89,     0,     0,     0,    90,    91,     0,
      32,     0,    83,    84,    24,     0,    25,    26,    85,    28,
       0,     0,     0,     0,     0,    87,   420,    12,    82,     0,
       0,     0,     0,     0,     0,     0,    88,    89,     0,     0,
       0,    90,    91,     0,    32,     0,     0,    83,    84,    24,
       0,    25,    26,   118,    28,     0,     0,     0,     0,     0,
      30,    12,    82,     0,     0,     0,     0,     0,     0,     0,
       0,    88,    89,     0,     0,     0,    90,    91,     0,    32,
       0,    83,    84,    24,     0,    25,    26,    85,    28,     0,
       0,     0,     0,     0,    87,    12,    82,     0,     0,     0,
       0,     0,     0,     0,     0,    88,    89,     0,     0,     0,
      90,    91,     0,    32,     0,    83,    84,    24,     0,    25,
      26,   195,    28,     0,     0,     0,     0,     0,    87,    12,
      82,     0,     0,     0,     0,     0,     0,     0,     0,    88,
      89,     0,     0,     0,    90,    91,     0,    32,     0,    83,
      84,    24,     0,    25,    26,   118,    28,     0,     0,     0,
       0,     0,    87,    12,    82,     0,     0,     0,     0,     0,
       0,     0,     0,    88,    89,     0,     0,     0,    90,    91,
       0,    32,     0,    83,    84,    24,     0,    25,    26,    85,
      28,     0,     0,     0,     0,     0,   229,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,    88,    89,     0,
       0,     0,    90,    91,     0,    32
    };
  }

private static final short yycheck_[] = yycheck_init();
  private static final short[] yycheck_init()
  {
    return new short[]
    {
       0,    50,     0,   249,   191,    43,    43,     7,    29,     7,
      22,    23,   236,    34,    54,    47,   230,    66,     0,    46,
      53,    43,    22,    23,   337,     7,    43,    44,    66,    29,
      11,    29,   229,    66,    66,    35,    36,    35,    36,    66,
      22,    23,    88,    89,    66,    50,    11,    29,    48,    49,
      48,    49,    43,    35,    36,    43,    77,   254,     7,    43,
      41,    66,    43,    51,   261,    43,    48,    49,   108,   382,
     383,    83,    84,   287,   387,    66,    41,    43,    90,    91,
      29,    43,    66,    83,    84,    35,    36,    52,    88,    89,
      90,    91,   306,    41,   308,    49,    43,   410,    48,    49,
      66,    83,    84,   317,    66,    49,    88,    89,    90,    91,
      43,    50,    66,   134,    48,   312,    43,   331,   332,    31,
      32,    43,    66,   337,    61,    62,    63,    66,     0,    33,
      34,   377,   356,    66,   134,     7,   134,   334,   325,    66,
      33,    34,    54,    55,    66,    43,    47,    51,   148,   149,
     148,   149,   134,    51,   368,    41,    48,    29,    51,   157,
      52,    49,    48,    35,    36,    49,   148,   149,   382,   383,
     216,   217,   218,   387,   371,   399,    48,    49,    66,   425,
      49,    48,    66,    49,    49,   134,    43,   401,    47,    53,
     404,    48,    51,    52,    51,    54,   410,    66,   148,   149,
      66,    66,    66,   203,   204,   205,   206,   207,   208,   209,
     210,   211,   212,   213,   214,   215,   216,   217,   218,   259,
      41,   203,   204,   205,   206,   207,   208,   209,   210,   211,
     212,   213,   214,   215,   216,   217,   218,     0,    33,    34,
      41,    49,   288,    49,     7,    41,    49,    41,    49,    44,
      33,    34,    47,    48,    41,    49,    51,    52,    66,    54,
      66,    44,   134,    66,    47,    48,    29,    49,    51,    52,
      49,    54,    35,    36,    49,    49,   148,   149,   278,    48,
     278,    41,    53,    49,    66,    48,    49,    66,   288,    49,
       0,    66,    66,    48,    41,    66,   278,     7,    33,    34,
      66,    48,    49,    50,    51,    52,   288,    54,    41,   309,
      43,   309,    47,    48,    37,    38,    51,    52,    41,    29,
      41,    41,    45,    41,    48,    35,    36,   309,    49,   278,
      50,    49,    52,    48,    54,    43,    48,    43,    48,    49,
      41,    47,    27,    51,    52,    51,    54,     0,    52,   349,
      54,   349,    48,    49,    41,    51,    52,    52,    54,    48,
     309,    44,    51,    52,    48,    54,    43,   349,    52,    43,
      54,   134,    48,    58,    47,    60,    52,    37,    54,    39,
      40,    41,    29,    30,    17,   148,   149,    59,    60,   389,
      75,   389,   392,   393,   392,   393,   396,    30,   396,    48,
     349,    47,    51,    52,    49,    50,   278,   389,   208,   209,
     392,   393,    47,   413,   396,   413,   416,   417,   416,   417,
     210,   211,   212,   213,   134,   110,    47,    43,   113,    45,
      25,   413,    41,    44,   416,   417,    28,   309,   148,   149,
     389,   320,   321,   392,   393,    78,   131,   396,    81,   214,
     215,    27,    56,    58,    87,    57,    43,    51,    43,    13,
      43,    41,   147,    53,   413,    46,    48,   416,   417,    41,
      48,    41,    43,    41,   159,    49,    53,   349,    43,    45,
       8,   114,   115,   116,   117,    49,    49,    66,    41,    50,
     123,   124,    50,    55,   127,   128,   181,   182,    44,    48,
      48,    29,    43,    31,    43,    49,    48,    41,    55,    26,
      50,   144,    49,    46,    49,   278,    10,   389,    49,    48,
     392,   393,    50,    26,   396,    49,    49,    49,   161,   162,
     163,    51,    50,   166,   167,   279,   134,   125,    34,   203,
     205,   413,   175,   204,   416,   417,   309,   206,   431,    77,
     183,   184,   207,    -1,    -1,    -1,   189,   190,    -1,    87,
     245,    -1,    -1,    -1,    -1,    -1,    -1,    -1,   278,   202,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,   349,    -1,    -1,    -1,
      -1,    -1,   277,    -1,    -1,    -1,   229,    -1,   126,   309,
      -1,   129,    -1,   236,    -1,    -1,   134,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,   257,    -1,   389,    -1,    -1,   392,
     393,    -1,    -1,   396,    -1,    -1,   269,    -1,    -1,   349,
     273,    -1,   275,    -1,    15,    16,    -1,    -1,    -1,    -1,
     413,    -1,    -1,   416,   417,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,   191,    35,    36,    37,    -1,    39,    40,
      41,    42,    43,    -1,   307,    -1,    -1,    48,    -1,   389,
      -1,    -1,   392,   393,    -1,    -1,   396,    -1,    59,    60,
      -1,    -1,    -1,    64,    65,    -1,    67,    -1,    -1,    -1,
      -1,   229,    -1,   413,    -1,    -1,   416,   417,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,   348,    -1,    -1,   351,   352,
      -1,    -1,   250,   356,   357,    -1,   254,    -1,    -1,   362,
      -1,    -1,    -1,   261,    -1,   368,     3,     4,     5,     6,
       7,     8,     9,    -1,    11,    12,    13,    14,    15,    -1,
      17,    18,    19,    20,    21,    22,    23,    24,    25,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,   399,    -1,    35,    36,
      37,   404,    39,    40,    41,    42,    -1,    -1,    45,    -1,
      -1,    48,    -1,    -1,   312,    52,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,   325,    -1,    -1,
      67,    -1,    -1,    -1,    -1,    -1,   334,    -1,     3,     4,
       5,     6,     7,     8,     9,    -1,    11,    12,    13,    -1,
      15,    -1,    -1,    18,    -1,    -1,    21,    22,    23,    24,
      25,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      35,    36,    37,   371,    39,    40,    41,    42,    -1,    -1,
      45,    46,    -1,    48,    -1,     3,     4,     5,     6,     7,
       8,     9,    -1,    11,    12,    13,    -1,    15,    -1,    -1,
      18,    -1,    67,    21,    22,    23,    24,    25,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    35,    36,    37,
      -1,    39,    40,    41,    42,    -1,    -1,    45,    -1,    -1,
      48,    -1,     3,     4,     5,     6,     7,     8,     9,    -1,
      -1,    12,    13,    -1,    15,    -1,    -1,    18,    -1,    67,
      21,    22,    23,    24,    25,    -1,    -1,    -1,    15,    16,
      -1,    -1,    -1,    -1,    35,    36,    37,    -1,    39,    40,
      41,    42,    -1,    -1,    45,    -1,    -1,    48,    35,    36,
      37,    -1,    39,    40,    41,    42,    -1,    -1,    -1,    -1,
      -1,    48,    49,    15,    16,    -1,    67,    -1,    -1,    -1,
      -1,    -1,    59,    60,    -1,    -1,    -1,    64,    65,    -1,
      67,    -1,    -1,    35,    36,    37,    -1,    39,    40,    41,
      42,    -1,    -1,    45,    -1,    -1,    48,    15,    16,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    59,    60,    -1,
      -1,    -1,    64,    65,    -1,    67,    -1,    35,    36,    37,
      -1,    39,    40,    41,    42,    -1,    -1,    -1,    -1,    -1,
      48,    15,    16,    -1,    -1,    53,    -1,    -1,    -1,    -1,
      -1,    59,    60,    -1,    -1,    -1,    64,    65,    -1,    67,
      -1,    35,    36,    37,    -1,    39,    40,    41,    42,    -1,
      -1,    -1,    -1,    -1,    48,    49,    15,    16,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    59,    60,    -1,    -1,    -1,
      64,    65,    -1,    67,    -1,    -1,    35,    36,    37,    -1,
      39,    40,    41,    42,    43,    -1,    -1,    -1,    -1,    48,
      15,    16,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      59,    60,    -1,    -1,    -1,    64,    65,    -1,    67,    -1,
      35,    36,    37,    -1,    39,    40,    41,    42,    -1,    -1,
      -1,    -1,    -1,    48,    49,    15,    16,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    59,    60,    -1,    -1,    -1,    64,
      65,    -1,    67,    -1,    -1,    35,    36,    37,    -1,    39,
      40,    41,    42,    -1,    -1,    -1,    -1,    -1,    48,    15,
      16,    -1,    -1,    53,    -1,    -1,    -1,    -1,    -1,    59,
      60,    -1,    -1,    -1,    64,    65,    -1,    67,    -1,    35,
      36,    37,    -1,    39,    40,    41,    42,    -1,    -1,    -1,
      -1,    -1,    48,    49,    15,    16,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    59,    60,    -1,    -1,    -1,    64,    65,
      -1,    67,    -1,    -1,    35,    36,    37,    -1,    39,    40,
      41,    42,    -1,    -1,    -1,    46,    -1,    48,    15,    16,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    59,    60,
      -1,    -1,    -1,    64,    65,    -1,    67,    -1,    35,    36,
      37,    -1,    39,    40,    41,    42,    -1,    -1,    -1,    -1,
      -1,    48,    49,    15,    16,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    59,    60,    -1,    -1,    -1,    64,    65,    -1,
      67,    -1,    -1,    35,    36,    37,    -1,    39,    40,    41,
      42,    -1,    -1,    -1,    -1,    -1,    48,    49,    15,    16,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    59,    60,    -1,
      -1,    -1,    64,    65,    -1,    67,    -1,    -1,    35,    36,
      37,    -1,    39,    40,    41,    42,    -1,    -1,    45,    -1,
      -1,    48,    15,    16,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    59,    60,    -1,    -1,    -1,    64,    65,    -1,
      67,    -1,    35,    36,    37,    -1,    39,    40,    41,    42,
      -1,    -1,    -1,    -1,    -1,    48,    49,    15,    16,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    59,    60,    -1,    -1,
      -1,    64,    65,    -1,    67,    -1,    -1,    35,    36,    37,
      -1,    39,    40,    41,    42,    -1,    -1,    -1,    -1,    -1,
      48,    15,    16,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    59,    60,    -1,    -1,    -1,    64,    65,    -1,    67,
      -1,    35,    36,    37,    -1,    39,    40,    41,    42,    -1,
      -1,    -1,    -1,    -1,    48,    15,    16,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    59,    60,    -1,    -1,    -1,
      64,    65,    -1,    67,    -1,    35,    36,    37,    -1,    39,
      40,    41,    42,    -1,    -1,    -1,    -1,    -1,    48,    15,
      16,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    59,
      60,    -1,    -1,    -1,    64,    65,    -1,    67,    -1,    35,
      36,    37,    -1,    39,    40,    41,    42,    -1,    -1,    -1,
      -1,    -1,    48,    15,    16,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    59,    60,    -1,    -1,    -1,    64,    65,
      -1,    67,    -1,    35,    36,    37,    -1,    39,    40,    41,
      42,    -1,    -1,    -1,    -1,    -1,    48,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    59,    60,    -1,
      -1,    -1,    64,    65,    -1,    67
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
      48,    52,    67,    69,    70,    71,    73,    75,    77,    78,
      79,    80,    81,    82,    83,    84,    85,    86,    87,    90,
      91,    94,    95,    97,   114,   115,   116,   117,   118,   120,
     121,   124,   126,    41,    43,    43,    43,    37,    39,    40,
      41,    43,    47,    41,    73,    41,    97,    48,    48,    41,
      41,    48,    16,    35,    36,    41,    43,    48,    59,    60,
      64,    65,    95,    99,   100,   101,   102,   103,   104,   105,
     106,   107,   108,   109,   110,   111,   112,   113,   114,   115,
     118,   119,   120,   121,    48,    48,    48,    48,    41,   109,
     118,   121,   109,    33,    34,    47,    48,    51,    52,    54,
      88,    41,    46,    73,    74,    76,    87,    97,    99,    41,
      97,    37,    38,    41,    45,   127,     0,    41,    87,    90,
      69,    69,    69,    69,    41,    97,    43,    44,   122,    41,
     122,    52,    33,    34,    88,    43,    33,    34,    88,    43,
      47,    47,    47,    47,    48,    52,    88,    25,    43,    52,
      88,    41,    41,    43,    87,    97,    99,    44,    72,    48,
      52,    54,    99,   109,   109,    41,    97,   110,   110,   109,
     109,    43,    66,    28,    27,    56,    57,    58,    29,    30,
      31,    32,    54,    55,    59,    60,    61,    62,    63,    99,
      99,    99,    99,    43,    43,    99,    99,    79,    41,    48,
      49,    93,    96,    97,    99,   125,    45,    99,    53,    99,
      97,    98,    43,    76,    46,    41,    49,    53,    99,    48,
      48,    88,    69,    69,    48,    41,    41,    48,    95,   123,
      43,    48,    88,    99,    99,    99,    99,    99,    49,    48,
      43,    43,    88,    47,    99,    43,    99,    41,    49,    41,
      43,    49,    89,    99,    53,    99,    98,    49,    49,    99,
     101,   102,   103,   104,   105,   106,   106,   107,   107,   107,
     107,   108,   108,   110,   110,   110,    49,    50,    49,    49,
      43,    43,    50,    49,    93,    97,    75,    49,    49,    41,
      50,    50,    46,    89,    53,    50,    55,    44,    46,   127,
      41,    49,    49,    93,    48,    99,   122,    49,    93,    43,
      53,    43,    43,    43,    43,    99,    43,    99,    43,    49,
      99,    43,    47,    73,    72,    49,    50,    48,    53,    55,
      75,   110,    47,    75,    99,    75,    73,    93,    26,    49,
      75,    50,    96,    96,    46,    98,    49,    50,   128,    43,
      75,    92,    49,    49,    93,    49,    92,    49,    49,    49,
      99,    73,    49,    49,    99,    99,    10,    89,    99,    48,
      99,    49,    75,    99,    26,    93,   127,    49,    92,    92,
      49,    92,    73,    49,    73,    73,    49,    49,    73,    49,
      49,    89,    75,    75,    99,    51,    92,    73,    73,    73,
      49,   127,   128
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
      73,    74,    74,    75,    75,    76,    76,    77,    78,    78,
      78,    78,    78,    78,    78,    79,    79,    80,    80,    81,
      81,    81,    81,    81,    81,    81,    82,    83,    84,    85,
      86,    86,    86,    86,    86,    87,    87,    87,    87,    87,
      87,    87,    88,    88,    88,    89,    89,    90,    90,    90,
      90,    90,    90,    90,    90,    91,    92,    92,    93,    93,
      93,    93,    94,    94,    94,    94,    94,    94,    95,    95,
      96,    96,    96,    96,    97,    97,    97,    98,    98,    99,
      99,    99,   100,   100,   101,   101,   102,   102,   103,   103,
     104,   104,   105,   105,   105,   106,   106,   106,   106,   106,
     107,   107,   107,   108,   108,   108,   108,   109,   109,   109,
     109,   109,   110,   110,   111,   111,   111,   112,   113,   113,
     113,   114,   114,   115,   115,   116,   116,   116,   116,   116,
     117,   117,   117,   117,   118,   118,   119,   120,   120,   120,
     121,   121,   122,   122,   123,   123,   123,   124,   124,   124,
     124,   124,   124,   124,   125,   125,   125,   125,   126,   126,
     127,   127,   127,   127,   128,   128
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
       1,     1,     1,     3,     2,     1,     2,     3,     2,     3,
       2,     3,     2,     2,     2,     5,     7,     5,     6,     8,
       7,     7,     6,     8,     7,     8,     5,     5,     7,     5,
       3,     3,     3,     3,     2,     4,     4,     5,     3,     3,
       3,     4,     2,     3,     4,     1,     3,     6,     7,     5,
       6,     5,     6,     4,     5,     4,     1,     1,     1,     2,
       3,     4,     4,     4,     4,     4,     4,     4,     3,     4,
       1,     1,     3,     3,     3,     1,     4,     1,     3,     1,
       1,     1,     3,     1,     3,     1,     1,     3,     1,     3,
       1,     3,     1,     3,     3,     1,     3,     3,     3,     3,
       1,     3,     3,     1,     3,     3,     3,     2,     2,     2,
       2,     1,     1,     4,     1,     2,     2,     1,     1,     1,
       1,     1,     1,     3,     1,     1,     1,     1,     1,     1,
       1,     1,     1,     1,     4,     4,     5,     2,     2,     2,
       2,     2,     3,     2,     1,     1,     3,     2,     4,     5,
       5,     7,     7,     8,     5,     5,     4,     4,     5,     6,
       3,     1,     1,     1,     5,     0
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
       0,   129,   129,   132,   133,   135,   136,   139,   142,   146,
     147,   148,   152,   158,   159,   163,   164,   165,   171,   177,
     178,   179,   180,   181,   182,   183,   184,   185,   186,   187,
     188,   192,   193,   199,   200,   204,   205,   209,   213,   214,
     215,   216,   217,   218,   219,   223,   224,   230,   231,   237,
     239,   241,   243,   245,   247,   251,   260,   264,   270,   276,
     302,   309,   316,   323,   330,   339,   342,   345,   348,   351,
     354,   357,   363,   364,   367,   373,   374,   378,   381,   384,
     387,   390,   393,   396,   399,   404,   408,   409,   413,   414,
     415,   416,   424,   428,   432,   435,   438,   441,   450,   453,
     459,   460,   461,   462,   466,   467,   468,   474,   475,   479,
     480,   481,   486,   489,   493,   496,   500,   501,   507,   508,
     514,   515,   521,   522,   525,   531,   532,   535,   538,   541,
     547,   548,   551,   557,   558,   561,   564,   570,   574,   578,
     579,   580,   584,   585,   590,   591,   592,   596,   602,   603,
     604,   608,   609,   613,   614,   618,   619,   620,   621,   622,
     626,   627,   628,   629,   633,   637,   641,   647,   651,   652,
     656,   662,   667,   668,   672,   673,   674,   678,   679,   682,
     685,   689,   693,   697,   704,   707,   710,   713,   720,   723,
     729,   730,   731,   732,   736,   739
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

  private static final int yylast_ = 1565;
  private static final int yynnts_ = 61;
  private static final int yyempty_ = -2;
  private static final int yyfinal_ = 146;
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

/* "VondaGrammar.java":3268  */ /* lalr1.java:1066  */

}

