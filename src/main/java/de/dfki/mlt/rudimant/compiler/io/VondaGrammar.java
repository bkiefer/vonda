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
    yyval = setPos(new StatVarDef(true, new Type(null), (( String )(yystack.valueAt (3-(2)))), null), (yyloc));
  };
  break;
    

  case 69:
  if (yyn == 69)
    /* "VondaGrammar.y":351  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatVarDef(false, ((Type)(yystack.valueAt (3-(1)))), (( String )(yystack.valueAt (3-(2)))), null), (yyloc));
  };
  break;
    

  case 70:
  if (yyn == 70)
    /* "VondaGrammar.y":354  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatVarDef(true, ((Type)(yystack.valueAt (4-(2)))), (( String )(yystack.valueAt (4-(3)))), null), (yyloc));
  };
  break;
    

  case 71:
  if (yyn == 71)
    /* "VondaGrammar.y":360  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (2-(2)))); };
  break;
    

  case 72:
  if (yyn == 72)
    /* "VondaGrammar.y":361  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpListLiteral(new LinkedList<RTExpression>()), yystack.locationAt (3-(2)), yystack.locationAt (3-(3)));
  };
  break;
    

  case 73:
  if (yyn == 73)
    /* "VondaGrammar.y":364  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpListLiteral(((LinkedList<RTExpression>)(yystack.valueAt (4-(3))))), yystack.locationAt (4-(2)), yystack.locationAt (4-(4)));
  };
  break;
    

  case 74:
  if (yyn == 74)
    /* "VondaGrammar.y":370  */ /* lalr1.java:489  */
    { yyval = new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (1-(1))))); }}; };
  break;
    

  case 75:
  if (yyn == 75)
    /* "VondaGrammar.y":371  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))); ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))).addFirst(((RTExpression)(yystack.valueAt (3-(1))))); };
  break;
    

  case 76:
  if (yyn == 76)
    /* "VondaGrammar.y":375  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatMethodDeclaration("public", ((Type)(yystack.valueAt (6-(2)))), ((Type)(yystack.valueAt (6-(1)))), (( String )(yystack.valueAt (6-(3)))), null, ((StatAbstractBlock)(yystack.valueAt (6-(6))))), (yyloc));
  };
  break;
    

  case 77:
  if (yyn == 77)
    /* "VondaGrammar.y":378  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatMethodDeclaration("public", ((Type)(yystack.valueAt (7-(2)))), ((Type)(yystack.valueAt (7-(1)))), (( String )(yystack.valueAt (7-(3)))), ((LinkedList)(yystack.valueAt (7-(5)))), ((StatAbstractBlock)(yystack.valueAt (7-(7))))), (yyloc));
  };
  break;
    

  case 78:
  if (yyn == 78)
    /* "VondaGrammar.y":381  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatMethodDeclaration("public", null, ((Type)(yystack.valueAt (5-(1)))), (( String )(yystack.valueAt (5-(2)))), null, ((StatAbstractBlock)(yystack.valueAt (5-(5))))), (yyloc));
  };
  break;
    

  case 79:
  if (yyn == 79)
    /* "VondaGrammar.y":384  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatMethodDeclaration("public", null, ((Type)(yystack.valueAt (6-(1)))), (( String )(yystack.valueAt (6-(2)))), ((LinkedList)(yystack.valueAt (6-(4)))), ((StatAbstractBlock)(yystack.valueAt (6-(6))))), (yyloc));
  };
  break;
    

  case 80:
  if (yyn == 80)
    /* "VondaGrammar.y":387  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatMethodDeclaration("public", ((Type)(yystack.valueAt (5-(1)))), null, (( String )(yystack.valueAt (5-(2)))), null, ((StatAbstractBlock)(yystack.valueAt (5-(5))))), (yyloc));
  };
  break;
    

  case 81:
  if (yyn == 81)
    /* "VondaGrammar.y":390  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatMethodDeclaration("public", ((Type)(yystack.valueAt (6-(1)))), null, (( String )(yystack.valueAt (6-(2)))), ((LinkedList)(yystack.valueAt (6-(4)))), ((StatAbstractBlock)(yystack.valueAt (6-(6))))), (yyloc));
  };
  break;
    

  case 82:
  if (yyn == 82)
    /* "VondaGrammar.y":393  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatMethodDeclaration("public", null, null, (( String )(yystack.valueAt (4-(1)))), null, ((StatAbstractBlock)(yystack.valueAt (4-(4))))), (yyloc));
  };
  break;
    

  case 83:
  if (yyn == 83)
    /* "VondaGrammar.y":396  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatMethodDeclaration("public", null, null, (( String )(yystack.valueAt (5-(1)))), ((LinkedList)(yystack.valueAt (5-(3)))), ((StatAbstractBlock)(yystack.valueAt (5-(5))))), (yyloc));
  };
  break;
    

  case 84:
  if (yyn == 84)
    /* "VondaGrammar.y":401  */ /* lalr1.java:489  */
    { yyval = ((Type)(yystack.valueAt (4-(2)))); };
  break;
    

  case 85:
  if (yyn == 85)
    /* "VondaGrammar.y":405  */ /* lalr1.java:489  */
    { yyval = ((StatAbstractBlock)(yystack.valueAt (1-(1)))); };
  break;
    

  case 86:
  if (yyn == 86)
    /* "VondaGrammar.y":406  */ /* lalr1.java:489  */
    { yyval = null; };
  break;
    

  case 87:
  if (yyn == 87)
    /* "VondaGrammar.y":410  */ /* lalr1.java:489  */
    { yyval = new LinkedList(){{ add((( String )(yystack.valueAt (1-(1))))); }}; };
  break;
    

  case 88:
  if (yyn == 88)
    /* "VondaGrammar.y":411  */ /* lalr1.java:489  */
    { yyval = new LinkedList(){{ add(((Type)(yystack.valueAt (2-(1))))); add((( String )(yystack.valueAt (2-(2))))); }}; };
  break;
    

  case 89:
  if (yyn == 89)
    /* "VondaGrammar.y":412  */ /* lalr1.java:489  */
    { yyval = ((LinkedList)(yystack.valueAt (3-(3)))); ((LinkedList)(yystack.valueAt (3-(3)))).addFirst((( String )(yystack.valueAt (3-(1))))); };
  break;
    

  case 90:
  if (yyn == 90)
    /* "VondaGrammar.y":413  */ /* lalr1.java:489  */
    {
    yyval = ((LinkedList)(yystack.valueAt (4-(4)))); ((LinkedList)(yystack.valueAt (4-(4)))).addFirst((( String )(yystack.valueAt (4-(2))))); ((LinkedList)(yystack.valueAt (4-(4)))).addFirst(((Type)(yystack.valueAt (4-(1)))));
  };
  break;
    

  case 91:
  if (yyn == 91)
    /* "VondaGrammar.y":421  */ /* lalr1.java:489  */
    {
    ExpVariable var = setPos(new ExpVariable((( String )(yystack.valueAt (4-(1))))), yystack.locationAt (4-(1)));
    yyval = setPos(new StatSetOperation(var, true, ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc));
  };
  break;
    

  case 92:
  if (yyn == 92)
    /* "VondaGrammar.y":425  */ /* lalr1.java:489  */
    {
    ExpVariable var = setPos(new ExpVariable((( String )(yystack.valueAt (4-(1))))), yystack.locationAt (4-(1)));
    yyval = setPos(new StatSetOperation(var, false, ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc));
  };
  break;
    

  case 93:
  if (yyn == 93)
    /* "VondaGrammar.y":429  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatSetOperation(((RTExpression)(yystack.valueAt (4-(1)))), true, ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc));
  };
  break;
    

  case 94:
  if (yyn == 94)
    /* "VondaGrammar.y":432  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatSetOperation(((RTExpression)(yystack.valueAt (4-(1)))), false, ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc));
  };
  break;
    

  case 95:
  if (yyn == 95)
    /* "VondaGrammar.y":435  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatSetOperation(((RTExpression)(yystack.valueAt (4-(1)))), true, ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc));
  };
  break;
    

  case 96:
  if (yyn == 96)
    /* "VondaGrammar.y":438  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatSetOperation(((RTExpression)(yystack.valueAt (4-(1)))), false, ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc));
  };
  break;
    

  case 97:
  if (yyn == 97)
    /* "VondaGrammar.y":447  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpFuncCall((( String )(yystack.valueAt (3-(1)))), new LinkedList<RTExpression>(), false), (yyloc));
  };
  break;
    

  case 98:
  if (yyn == 98)
    /* "VondaGrammar.y":450  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpFuncCall((( String )(yystack.valueAt (4-(1)))), ((LinkedList<RTExpression>)(yystack.valueAt (4-(3)))), false), (yyloc));
  };
  break;
    

  case 99:
  if (yyn == 99)
    /* "VondaGrammar.y":456  */ /* lalr1.java:489  */
    { yyval = new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (1-(1))))); }}; };
  break;
    

  case 100:
  if (yyn == 100)
    /* "VondaGrammar.y":457  */ /* lalr1.java:489  */
    { yyval = new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (1-(1))))); }}; };
  break;
    

  case 101:
  if (yyn == 101)
    /* "VondaGrammar.y":458  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))); ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))).addFirst(((RTExpression)(yystack.valueAt (3-(1))))); };
  break;
    

  case 102:
  if (yyn == 102)
    /* "VondaGrammar.y":459  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))); ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))).addFirst(((RTExpression)(yystack.valueAt (3-(1))))); };
  break;
    

  case 103:
  if (yyn == 103)
    /* "VondaGrammar.y":463  */ /* lalr1.java:489  */
    { yyval = new Type("Array", new Type((( String )(yystack.valueAt (3-(1)))))); };
  break;
    

  case 104:
  if (yyn == 104)
    /* "VondaGrammar.y":464  */ /* lalr1.java:489  */
    { yyval = new Type((( String )(yystack.valueAt (1-(1))))); };
  break;
    

  case 105:
  if (yyn == 105)
    /* "VondaGrammar.y":465  */ /* lalr1.java:489  */
    {
    yyval = new Type((( String )(yystack.valueAt (4-(1)))), ((LinkedList<Type>)(yystack.valueAt (4-(3)))).toArray(new Type[((LinkedList<Type>)(yystack.valueAt (4-(3)))).size()]));
  };
  break;
    

  case 106:
  if (yyn == 106)
    /* "VondaGrammar.y":471  */ /* lalr1.java:489  */
    { yyval = new LinkedList<Type>(){{ add(((Type)(yystack.valueAt (1-(1))))); }}; };
  break;
    

  case 107:
  if (yyn == 107)
    /* "VondaGrammar.y":472  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<Type>)(yystack.valueAt (3-(3)))); ((LinkedList<Type>)(yystack.valueAt (3-(3)))).addFirst(((Type)(yystack.valueAt (3-(1))))); };
  break;
    

  case 108:
  if (yyn == 108)
    /* "VondaGrammar.y":476  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 109:
  if (yyn == 109)
    /* "VondaGrammar.y":477  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 110:
  if (yyn == 110)
    /* "VondaGrammar.y":478  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 111:
  if (yyn == 111)
    /* "VondaGrammar.y":483  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "||"), (yyloc));
  };
  break;
    

  case 112:
  if (yyn == 112)
    /* "VondaGrammar.y":486  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 113:
  if (yyn == 113)
    /* "VondaGrammar.y":490  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "&&"), (yyloc));
  };
  break;
    

  case 114:
  if (yyn == 114)
    /* "VondaGrammar.y":493  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 115:
  if (yyn == 115)
    /* "VondaGrammar.y":497  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 116:
  if (yyn == 116)
    /* "VondaGrammar.y":498  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "|"), (yyloc));
  };
  break;
    

  case 117:
  if (yyn == 117)
    /* "VondaGrammar.y":504  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 118:
  if (yyn == 118)
    /* "VondaGrammar.y":505  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "^"), (yyloc));
  };
  break;
    

  case 119:
  if (yyn == 119)
    /* "VondaGrammar.y":511  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 120:
  if (yyn == 120)
    /* "VondaGrammar.y":512  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "&"), (yyloc));
  };
  break;
    

  case 121:
  if (yyn == 121)
    /* "VondaGrammar.y":518  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 122:
  if (yyn == 122)
    /* "VondaGrammar.y":519  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "=="), (yyloc));
  };
  break;
    

  case 123:
  if (yyn == 123)
    /* "VondaGrammar.y":522  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "!="), (yyloc));
  };
  break;
    

  case 124:
  if (yyn == 124)
    /* "VondaGrammar.y":528  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 125:
  if (yyn == 125)
    /* "VondaGrammar.y":529  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "<"), (yyloc));
  };
  break;
    

  case 126:
  if (yyn == 126)
    /* "VondaGrammar.y":532  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), ">"), (yyloc));
  };
  break;
    

  case 127:
  if (yyn == 127)
    /* "VondaGrammar.y":535  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), ">="), (yyloc));
  };
  break;
    

  case 128:
  if (yyn == 128)
    /* "VondaGrammar.y":538  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "<="), (yyloc));
  };
  break;
    

  case 129:
  if (yyn == 129)
    /* "VondaGrammar.y":544  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 130:
  if (yyn == 130)
    /* "VondaGrammar.y":545  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "+"), (yyloc));
  };
  break;
    

  case 131:
  if (yyn == 131)
    /* "VondaGrammar.y":548  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "-"), (yyloc));
  };
  break;
    

  case 132:
  if (yyn == 132)
    /* "VondaGrammar.y":554  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 133:
  if (yyn == 133)
    /* "VondaGrammar.y":555  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "*"), (yyloc));
  };
  break;
    

  case 134:
  if (yyn == 134)
    /* "VondaGrammar.y":558  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "/"), (yyloc));
  };
  break;
    

  case 135:
  if (yyn == 135)
    /* "VondaGrammar.y":561  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "%"), (yyloc));
  };
  break;
    

  case 136:
  if (yyn == 136)
    /* "VondaGrammar.y":567  */ /* lalr1.java:489  */
    {
    ExpSingleValue es = setPos(new ExpSingleValue("1", "int"), (yyloc));
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2-(2)))), es, "+"), (yyloc));
  };
  break;
    

  case 137:
  if (yyn == 137)
    /* "VondaGrammar.y":571  */ /* lalr1.java:489  */
    {
    ExpSingleValue es = setPos(new ExpSingleValue("1", "int"), (yyloc));
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2-(2)))), es, "-"), (yyloc));
  };
  break;
    

  case 138:
  if (yyn == 138)
    /* "VondaGrammar.y":575  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2-(2)))), null, "+"), (yyloc)); };
  break;
    

  case 139:
  if (yyn == 139)
    /* "VondaGrammar.y":576  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2-(2)))), null, "-"), (yyloc)); };
  break;
    

  case 140:
  if (yyn == 140)
    /* "VondaGrammar.y":577  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 141:
  if (yyn == 141)
    /* "VondaGrammar.y":581  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 142:
  if (yyn == 142)
    /* "VondaGrammar.y":582  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpCast(((Type)(yystack.valueAt (4-(2)))), ((RTExpression)(yystack.valueAt (4-(4))))), (yyloc)); };
  break;
    

  case 143:
  if (yyn == 143)
    /* "VondaGrammar.y":587  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 144:
  if (yyn == 144)
    /* "VondaGrammar.y":588  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (2-(2)))), null, "!"), (yyloc)); };
  break;
    

  case 145:
  if (yyn == 145)
    /* "VondaGrammar.y":589  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2-(2)))), null, "~"), (yyloc)); };
  break;
    

  case 146:
  if (yyn == 146)
    /* "VondaGrammar.y":593  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 147:
  if (yyn == 147)
    /* "VondaGrammar.y":599  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpSingleValue("null", "null"), (yyloc)); };
  break;
    

  case 148:
  if (yyn == 148)
    /* "VondaGrammar.y":600  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 149:
  if (yyn == 149)
    /* "VondaGrammar.y":601  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 150:
  if (yyn == 150)
    /* "VondaGrammar.y":605  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpVariable((( String )(yystack.valueAt (1-(1))))), (yyloc)); };
  break;
    

  case 151:
  if (yyn == 151)
    /* "VondaGrammar.y":606  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 152:
  if (yyn == 152)
    /* "VondaGrammar.y":610  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (3-(2)))); };
  break;
    

  case 153:
  if (yyn == 153)
    /* "VondaGrammar.y":611  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 154:
  if (yyn == 154)
    /* "VondaGrammar.y":615  */ /* lalr1.java:489  */
    { yyval = ((ExpSingleValue)(yystack.valueAt (1-(1)))); };
  break;
    

  case 155:
  if (yyn == 155)
    /* "VondaGrammar.y":616  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 156:
  if (yyn == 156)
    /* "VondaGrammar.y":617  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 157:
  if (yyn == 157)
    /* "VondaGrammar.y":618  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 158:
  if (yyn == 158)
    /* "VondaGrammar.y":619  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 159:
  if (yyn == 159)
    /* "VondaGrammar.y":623  */ /* lalr1.java:489  */
    { yyval = (( ExpSingleValue )(yystack.valueAt (1-(1)))); setPos((( ExpSingleValue )(yystack.valueAt (1-(1)))), (yyloc)); };
  break;
    

  case 160:
  if (yyn == 160)
    /* "VondaGrammar.y":624  */ /* lalr1.java:489  */
    { yyval = (( ExpSingleValue )(yystack.valueAt (1-(1)))); setPos((( ExpSingleValue )(yystack.valueAt (1-(1)))), (yyloc)); };
  break;
    

  case 161:
  if (yyn == 161)
    /* "VondaGrammar.y":625  */ /* lalr1.java:489  */
    { yyval = (( ExpSingleValue )(yystack.valueAt (1-(1)))); setPos((( ExpSingleValue )(yystack.valueAt (1-(1)))), (yyloc)); };
  break;
    

  case 162:
  if (yyn == 162)
    /* "VondaGrammar.y":626  */ /* lalr1.java:489  */
    { yyval = (( ExpSingleValue )(yystack.valueAt (1-(1)))); setPos((( ExpSingleValue )(yystack.valueAt (1-(1)))), (yyloc)); };
  break;
    

  case 163:
  if (yyn == 163)
    /* "VondaGrammar.y":630  */ /* lalr1.java:489  */
    {
    ExpVariable var = setPos(new ExpVariable((( String )(yystack.valueAt (4-(1))))), yystack.locationAt (4-(1)));
    yyval = setPos(new ExpArrayAccess(var, ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc));
  };
  break;
    

  case 164:
  if (yyn == 164)
    /* "VondaGrammar.y":634  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpArrayAccess(((RTExpression)(yystack.valueAt (4-(1)))), ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc)); };
  break;
    

  case 165:
  if (yyn == 165)
    /* "VondaGrammar.y":638  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpConditional(((RTExpression)(yystack.valueAt (5-(1)))), ((RTExpression)(yystack.valueAt (5-(3)))), ((RTExpression)(yystack.valueAt (5-(5))))), (yyloc)); };
  break;
    

  case 166:
  if (yyn == 166)
    /* "VondaGrammar.y":644  */ /* lalr1.java:489  */
    {
    ExpVariable var = setPos(new ExpVariable((( String )(yystack.valueAt (2-(1))))), yystack.locationAt (2-(1)));
    yyval = setPos(new ExpAssignment(var, ((RTExpression)(yystack.valueAt (2-(2))))), (yyloc));
  };
  break;
    

  case 167:
  if (yyn == 167)
    /* "VondaGrammar.y":648  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpAssignment(((RTExpression)(yystack.valueAt (2-(1)))), ((RTExpression)(yystack.valueAt (2-(2))))), (yyloc)); };
  break;
    

  case 168:
  if (yyn == 168)
    /* "VondaGrammar.y":649  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpAssignment(((RTExpression)(yystack.valueAt (2-(1)))), ((RTExpression)(yystack.valueAt (2-(2))))), (yyloc)); };
  break;
    

  case 169:
  if (yyn == 169)
    /* "VondaGrammar.y":653  */ /* lalr1.java:489  */
    {
    //List<String> repr = new ArrayList<>($2.size());
    //for (int i = 0; i < $2.size(); ++i) repr.add("");
    //$$ = setPos(new ExpFieldAccess($2, repr), @$); $2.addFirst($1);
    yyval = setPos(new ExpFieldAccess(((LinkedList<RTExpression>)(yystack.valueAt (2-(2))))), (yyloc)); ((LinkedList<RTExpression>)(yystack.valueAt (2-(2)))).addFirst(((RTExpression)(yystack.valueAt (2-(1)))));
  };
  break;
    

  case 170:
  if (yyn == 170)
    /* "VondaGrammar.y":659  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpFieldAccess(((LinkedList<RTExpression>)(yystack.valueAt (2-(2))))), (yyloc)); ((LinkedList<RTExpression>)(yystack.valueAt (2-(2)))).addFirst(((RTExpression)(yystack.valueAt (2-(1))))); };
  break;
    

  case 171:
  if (yyn == 171)
    /* "VondaGrammar.y":663  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))); ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))).addFirst(((RTExpression)(yystack.valueAt (3-(2))))); };
  break;
    

  case 172:
  if (yyn == 172)
    /* "VondaGrammar.y":664  */ /* lalr1.java:489  */
    { yyval = new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (2-(2))))); }}; };
  break;
    

  case 173:
  if (yyn == 173)
    /* "VondaGrammar.y":668  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpVariable((( String )(yystack.valueAt (1-(1))))), (yyloc)); };
  break;
    

  case 174:
  if (yyn == 174)
    /* "VondaGrammar.y":669  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 175:
  if (yyn == 175)
    /* "VondaGrammar.y":670  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (3-(2)))); };
  break;
    

  case 176:
  if (yyn == 176)
    /* "VondaGrammar.y":674  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpNew(new Type((( String )(yystack.valueAt (2-(2)))))), (yyloc)); };
  break;
    

  case 177:
  if (yyn == 177)
    /* "VondaGrammar.y":675  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpNew(new Type((( String )(yystack.valueAt (4-(2))))), new LinkedList<>()), (yyloc));
  };
  break;
    

  case 178:
  if (yyn == 178)
    /* "VondaGrammar.y":678  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpNew(new Type((( String )(yystack.valueAt (5-(2))))), ((LinkedList<RTExpression>)(yystack.valueAt (5-(4))))), (yyloc));
  };
  break;
    

  case 179:
  if (yyn == 179)
    /* "VondaGrammar.y":681  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpNew(new Type("Array", new Type((( String )(yystack.valueAt (5-(2)))))),
                    new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (5-(4))))); }}), (yyloc));
  };
  break;
    

  case 180:
  if (yyn == 180)
    /* "VondaGrammar.y":685  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpNew(new Type("Array", new Type((( String )(yystack.valueAt (7-(2)))))),
                    new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (7-(6))))); }}), (yyloc));
  };
  break;
    

  case 181:
  if (yyn == 181)
    /* "VondaGrammar.y":689  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpNew(new Type((( String )(yystack.valueAt (7-(2)))), ((LinkedList<Type>)(yystack.valueAt (7-(4)))).toArray(new Type[((LinkedList<Type>)(yystack.valueAt (7-(4)))).size()])),
                    new LinkedList<>()), (yyloc));
  };
  break;
    

  case 182:
  if (yyn == 182)
    /* "VondaGrammar.y":693  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpNew(new Type((( String )(yystack.valueAt (8-(2)))), ((LinkedList<Type>)(yystack.valueAt (8-(4)))).toArray(new Type[((LinkedList<Type>)(yystack.valueAt (8-(4)))).size()])),
                    ((LinkedList<RTExpression>)(yystack.valueAt (8-(7))))), (yyloc));
  };
  break;
    

  case 183:
  if (yyn == 183)
    /* "VondaGrammar.y":700  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpLambda(((LinkedList)(yystack.valueAt (5-(2)))), ((RTExpression)(yystack.valueAt (5-(5))))), (yyloc));
  };
  break;
    

  case 184:
  if (yyn == 184)
    /* "VondaGrammar.y":703  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpLambda(((LinkedList)(yystack.valueAt (5-(2)))), ((StatAbstractBlock)(yystack.valueAt (5-(5))))), (yyloc));
  };
  break;
    

  case 185:
  if (yyn == 185)
    /* "VondaGrammar.y":706  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpLambda(new LinkedList<>(), ((RTExpression)(yystack.valueAt (4-(4))))), (yyloc));
  };
  break;
    

  case 186:
  if (yyn == 186)
    /* "VondaGrammar.y":709  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpLambda(new LinkedList<>(), ((StatAbstractBlock)(yystack.valueAt (4-(4))))), (yyloc));
  };
  break;
    

  case 187:
  if (yyn == 187)
    /* "VondaGrammar.y":716  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpDialogueAct(((RTExpression)(yystack.valueAt (5-(2)))), ((RTExpression)(yystack.valueAt (5-(4)))), new LinkedList<RTExpression>()), (yyloc));
  };
  break;
    

  case 188:
  if (yyn == 188)
    /* "VondaGrammar.y":719  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpDialogueAct(((RTExpression)(yystack.valueAt (6-(2)))), ((RTExpression)(yystack.valueAt (6-(4)))), ((LinkedList<RTExpression>)(yystack.valueAt (6-(5))))), (yyloc));
  };
  break;
    

  case 189:
  if (yyn == 189)
    /* "VondaGrammar.y":725  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (3-(2)))); };
  break;
    

  case 190:
  if (yyn == 190)
    /* "VondaGrammar.y":726  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpVariable((( String )(yystack.valueAt (1-(1))))), (yyloc)); };
  break;
    

  case 191:
  if (yyn == 191)
    /* "VondaGrammar.y":727  */ /* lalr1.java:489  */
    { yyval = (( ExpSingleValue )(yystack.valueAt (1-(1)))); };
  break;
    

  case 192:
  if (yyn == 192)
    /* "VondaGrammar.y":728  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpSingleValue((( String )(yystack.valueAt (1-(1)))), "String"), (yyloc)); };
  break;
    

  case 193:
  if (yyn == 193)
    /* "VondaGrammar.y":732  */ /* lalr1.java:489  */
    {
    yyval = ((LinkedList<RTExpression>)(yystack.valueAt (5-(5)))); ((LinkedList<RTExpression>)(yystack.valueAt (5-(5)))).addFirst(((RTExpression)(yystack.valueAt (5-(4))))); ((LinkedList<RTExpression>)(yystack.valueAt (5-(5)))).addFirst(((RTExpression)(yystack.valueAt (5-(2)))));
  };
  break;
    

  case 194:
  if (yyn == 194)
    /* "VondaGrammar.y":735  */ /* lalr1.java:489  */
    { yyval = new LinkedList<RTExpression>(); };
  break;
    


/* "VondaGrammar.java":2063  */ /* lalr1.java:489  */
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

  private static final short yypact_ninf_ = -303;
  private static final short yytable_ninf_ = -151;

  /* YYPACT[STATE-NUM] -- Index in YYTABLE of the portion describing
   STATE-NUM.  */
  private static final short yypact_[] = yypact_init();
  private static final short[] yypact_init()
  {
    return new short[]
    {
     802,   158,   -37,   -26,   359,   -15,    25,   946,    20,    65,
      90,   100,   137,  -303,   120,  -303,  -303,   582,   144,   150,
     204,   226,  1341,  1341,  -303,  -303,  -303,   206,  -303,   852,
    1375,   240,   315,   300,    19,   802,   802,  -303,  -303,  -303,
    -303,  -303,  -303,  -303,  -303,  -303,  -303,  -303,   802,   802,
     266,  -303,   324,   275,   279,   282,  -303,  -303,    22,   284,
      52,  -303,  -303,   299,  -303,  -303,  -303,   298,   328,   336,
     347,  -303,  -303,   215,   355,   268,   308,     1,  1375,   365,
     135,  1375,  -303,  1341,  1341,   194,  -303,  1409,  1443,  1443,
    1341,  1341,   279,   -33,   391,   415,   387,   389,   390,   372,
      14,   346,   364,  -303,  -303,  -303,  -303,  -303,   279,   282,
     398,  -303,  -303,   398,  1375,  1375,  1375,  1375,   249,   414,
    -303,  -303,   417,  1375,  1375,   445,   662,   730,   961,   240,
    -303,   281,  -303,  -303,   899,   413,  -303,   421,    45,   258,
     419,  -303,  -303,  -303,  1375,   420,  -303,   330,   802,   802,
    -303,  -303,  -303,  -303,   333,   426,  -303,    99,  -303,   311,
    -303,  1375,  1375,  1375,  -303,  -303,  1375,  1375,  -303,  -303,
    -303,  -303,  -303,  -303,   995,  1375,   422,  -303,   418,   429,
     221,   318,  1375,  1030,   428,    67,   432,   431,  1064,  1099,
     240,    73,  -303,  -303,   325,   427,  -303,  -303,  -303,  -303,
    -303,  1375,  1443,  1443,  1443,  1443,  1443,  1443,  1443,  1443,
    1443,  1443,  1443,  1443,  1443,  1443,  1443,  1443,    76,    15,
      94,    95,  -303,  -303,    -9,     9,  -303,   238,  1133,   430,
     433,   434,   436,   127,   435,  1168,   412,  -303,   -45,   437,
     424,  -303,  -303,   248,  -303,   438,    -7,   315,   272,  -303,
    -303,   306,   440,   441,  1375,  -303,   279,  -303,   317,   443,
     217,    33,    44,    49,    55,  -303,  1375,  -303,  -303,   451,
    1375,    57,  1202,    69,   293,   946,   365,  -303,  -303,   446,
     130,   448,   218,   425,   430,  1443,   -23,   415,   387,   389,
     390,   372,    14,    14,   346,   346,   346,   346,   364,   364,
    -303,  -303,  -303,   430,  1375,   430,   946,  -303,  -303,   456,
     472,   450,   320,  -303,   430,  -303,   452,  1477,  1477,  -303,
     454,  -303,   240,  -303,  -303,  -303,   373,   289,   430,   198,
     455,   322,    96,  -303,   198,   457,  -303,  -303,  -303,  -303,
    -303,  -303,   105,  -303,   124,  1375,   946,   170,  1237,  1375,
     491,  -303,  -303,  1375,  1375,  -303,   459,  -303,  -303,  1375,
    -303,   189,  -303,  -303,  -303,  1272,   479,  -303,   456,  -303,
    -303,  -303,  -303,  -303,   315,   460,  -303,  -303,  -303,   198,
     198,   461,  -303,  -303,   198,  -303,   946,   195,  -303,   946,
     946,   202,   207,   946,  -303,   216,  1306,   412,   430,  -303,
     412,  1272,  -303,   462,  -303,  -303,  -303,   198,  -303,  -303,
     946,  -303,  -303,   946,   946,  -303,  -303,  -303,   463,  -303,
    -303,   412,   315,  -303,  -303,  -303,  -303,  -303,   458,  -303
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
       0,     0,     0,     0,   159,   160,   162,   104,   161,     0,
       0,     0,     0,     0,     0,     8,     8,    15,    20,    22,
      26,    27,    28,    23,    25,    24,    29,    30,     8,     8,
       0,    21,   157,     0,     0,     0,   153,   154,   155,     0,
     156,   151,   158,     0,    40,    42,    43,     0,     0,     0,
       0,    44,    64,   150,     0,   104,     0,     0,     0,    13,
     176,     0,   147,     0,     0,   150,    38,     0,     0,     0,
       0,     0,   157,     0,   110,   112,   114,   115,   117,   119,
     121,   124,   129,   141,   132,   140,   143,   146,   148,   149,
     155,   108,   109,   156,     0,     0,     0,     0,   150,     0,
     155,   156,     0,     0,     0,     0,     0,     0,     0,     0,
     166,   104,    34,    31,    35,     0,    32,     0,     0,   104,
       0,   191,   192,   190,     0,     0,     1,   104,     8,     8,
       5,     4,     7,     3,   104,     0,    19,     0,   170,     0,
     169,     0,     0,     0,   168,    16,     0,     0,   167,    41,
      60,    61,    62,    63,     0,     0,     0,    68,     0,     0,
       0,   104,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,   137,   136,   150,     0,   138,   139,   144,   145,
      39,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,    18,    17,     0,     0,    37,   150,     0,    97,
       0,     0,     0,    99,   100,     0,    71,   103,     0,   106,
       0,    36,    33,     0,   152,     0,     0,     0,     0,     6,
       2,     0,     0,   173,     0,   174,   172,    69,     0,     0,
       0,     0,     0,     0,     0,    97,     0,    65,    70,     0,
       0,     0,     0,     0,     0,     0,    13,    12,   177,     0,
      74,     0,     0,     0,     0,     0,     0,   111,   113,   116,
     118,   120,   122,   123,   127,   128,   125,   126,   130,   131,
     133,   134,   135,     0,     0,     0,     0,    92,    91,     0,
       0,     0,     0,    82,     0,    98,    88,     0,     0,    72,
       0,   163,     0,   105,    84,   189,     0,    87,     0,     0,
       0,     0,     0,   171,     0,     0,    66,   164,    94,    93,
      96,    95,     0,    67,     0,     0,     0,     0,     0,     0,
      45,    14,   178,     0,     0,   179,     0,    56,   142,     0,
      59,     0,    57,    47,    89,     0,     0,    83,     0,   101,
     102,    73,   107,   187,     0,     0,    86,    85,    78,     0,
       0,     0,   175,    80,     0,    48,     0,     0,    52,     0,
       0,     0,     0,     0,    75,     0,     0,   165,     0,   186,
     185,     0,    90,     0,   188,    79,    76,     0,    81,    54,
       0,    50,    51,     0,     0,    46,   180,   181,     0,    58,
     184,   183,     0,    77,    53,    49,    55,   182,   194,   193
    };
  }

/* YYPGOTO[NTERM-NUM].  */
  private static final short yypgoto_[] = yypgoto_init();
  private static final short[] yypgoto_init()
  {
    return new short[]
    {
    -303,   -22,  -303,  -303,   235,    51,  -303,  -210,   380,  -303,
    -303,   396,  -303,  -303,  -303,  -303,  -303,  -303,  -303,   -14,
       4,  -224,   481,  -303,  -302,   -72,  -303,     2,   112,   416,
    -185,   403,  -303,   314,   319,   321,   323,   327,   227,   244,
     225,    74,   -85,  -303,  -303,  -303,   146,   269,  -303,  -303,
       0,  -303,   302,    18,   -38,  -303,  -303,  -303,  -303,  -246,
     101
    };
  }

/* YYDEFGOTO[NTERM-NUM].  */
  private static final short yydefgoto_[] = yydefgoto_init();
  private static final short[] yydefgoto_init()
  {
    return new short[]
    {
      -1,    33,    34,    35,   187,    36,   134,    37,   135,    38,
      39,    40,    41,    42,    43,    44,    45,    46,    47,    48,
     130,   279,    49,    50,   378,   230,    51,    92,   231,    53,
     240,   233,    94,    95,    96,    97,    98,    99,   100,   101,
     102,   103,   104,   105,   106,   107,   108,   109,    56,    57,
     110,   111,   112,   113,   158,   256,    61,   234,    62,   145,
     375
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
      58,   326,    52,   196,   197,   283,    65,    58,   321,    52,
     200,   320,     8,   150,   151,   136,   160,    66,    60,   313,
     148,   201,   120,   120,   359,    60,   152,   153,    71,    58,
       8,    52,   383,   201,   307,    58,    58,    52,    52,   325,
     121,   121,   181,   201,   182,   209,   210,    60,    58,    58,
      52,    52,   308,    60,    60,   162,   163,   201,    74,   201,
     147,    75,   164,   183,   168,   304,    60,    60,   211,   212,
     160,    31,    72,   127,   357,   201,   338,   405,   406,   179,
     133,   201,   408,   120,   120,   166,   167,   339,   120,   120,
     120,   120,   340,   360,   244,   362,   119,   122,   341,   201,
     345,   121,   121,   127,   367,   423,   121,   121,   121,   121,
     201,   201,   348,    77,   164,   201,   275,   168,   313,   377,
     136,   201,   284,   201,   377,   303,   249,   250,   403,   394,
     300,   301,   302,   201,    58,   201,    52,   372,    78,   201,
     253,    79,   201,   305,   306,   382,    54,   254,    58,    58,
      52,    52,    60,    54,   385,   399,   311,   192,   193,   255,
     201,   201,   201,   259,   198,   199,    60,    60,    81,   377,
     377,   201,   418,   386,   377,    54,   428,   317,    80,   330,
     353,    54,    54,   188,   269,   133,   335,   189,   419,   190,
     201,   420,   114,   201,    54,    54,   201,   377,   115,    63,
     358,    64,   120,   120,   120,   120,   120,   120,   120,   120,
     120,   120,   120,   120,   120,   120,   120,   120,   333,   389,
     121,   121,   121,   121,   121,   121,   121,   121,   121,   121,
     121,   121,   121,   121,   121,   121,   201,   364,   398,   123,
     124,   376,   174,    29,   410,   127,   175,   259,   123,   124,
    -150,   413,   116,   125,   126,   201,   414,   127,   128,   381,
     129,   201,   125,   174,   268,   416,   127,   175,   201,    55,
     337,   355,   127,   201,   117,    58,    55,    52,   259,  -104,
      54,   139,   201,   201,   201,   120,   174,   -87,   309,   127,
     128,   257,   129,    60,    54,    54,   402,   174,    55,   127,
     146,   175,    59,   121,    55,    55,    58,   154,    52,    59,
     178,   177,   129,   327,   123,   124,   159,    55,    55,   127,
     178,   328,   129,   157,    60,  -150,   350,   165,   125,   174,
    -104,    59,   127,   128,   161,   129,   257,    59,    59,   309,
     349,   178,   169,   129,   127,   170,    58,   327,    52,   180,
      59,    59,   141,   142,   257,   329,   143,   363,   327,   258,
     144,   316,   127,   327,    60,   270,   334,   156,   157,   285,
     178,   380,   129,   174,  -104,   171,   127,   128,   248,   129,
     176,   251,   178,   172,   129,   178,    58,   129,    52,    58,
      58,    52,    52,    58,   173,    52,    67,   388,    68,    69,
      70,   207,   208,    55,    60,   213,   214,    60,    60,   186,
      58,    60,    52,    58,    58,    52,    52,    55,    55,   202,
      93,    54,   373,   374,    76,   215,   216,   217,    60,   369,
     370,    60,    60,   138,   292,   293,    59,   409,   298,   299,
     411,   412,   203,   204,   415,   137,   205,   140,   206,   127,
      59,    59,    54,   294,   295,   296,   297,   222,    10,   242,
     223,   424,   243,   245,   425,   426,   155,   252,   247,   274,
     266,   237,   267,   276,   277,    29,   285,   316,   201,   323,
     356,   185,   314,   315,   191,   318,   336,   322,   331,   174,
     138,   324,    54,   184,   343,   352,   354,   327,   365,   366,
     371,   393,   368,   195,   379,   401,   384,   396,   374,   404,
     407,   351,   427,   422,   241,   149,   287,   218,   219,   220,
     221,   226,   288,     0,     0,   289,   224,   225,   290,   429,
     236,   238,    54,   291,     0,    54,    54,     0,     0,    54,
       0,     0,   232,     0,    55,   239,     0,   246,     0,     0,
     137,     0,     0,     0,     0,     0,    54,     0,     0,    54,
      54,     0,     0,     0,   260,   261,   262,     0,     0,   263,
     264,     0,     0,     0,     0,    55,     0,    59,   238,     0,
       0,     0,     0,     0,     0,   271,   273,     0,     0,     0,
       0,   280,   282,     0,     0,     0,     0,    12,    82,     0,
       0,     0,     0,     0,   286,     0,   239,     0,    59,     0,
       0,     0,     0,     0,     0,    55,     0,    83,    84,    24,
       0,    25,    26,    85,    28,    86,     0,     0,     0,     0,
      87,   138,     0,     0,     0,     0,     0,     0,   280,     0,
       0,    88,    89,     0,   312,     0,    90,    91,    59,    32,
       0,     0,     0,     0,     0,    55,     0,   332,    55,    55,
       0,     0,    55,     0,   232,     0,     0,   232,     0,   342,
       0,     0,     0,   344,   232,   347,     0,    12,    82,    55,
       0,     0,    55,    55,     0,     0,     0,     0,    59,     0,
       0,    59,    59,     0,     0,    59,     0,    83,    84,    24,
       0,    25,    26,   227,    28,     0,     0,   361,     0,     0,
     228,   229,    59,     0,     0,    59,    59,     0,     0,     0,
       0,    88,    89,     0,     0,   232,    90,    91,     0,    32,
       0,     0,     0,     0,     0,     0,     0,     0,   239,     0,
       0,     0,     0,     0,     0,    12,    82,   232,   387,     0,
       0,   391,   392,     0,     0,     0,   280,   395,     0,     0,
       0,     0,   397,     0,     0,    83,    84,    24,   400,    25,
      26,    85,    28,     0,     0,   235,     0,     0,    87,     0,
       0,     0,     0,     0,   232,     0,     0,     0,     0,    88,
      89,     0,     0,     0,    90,    91,     0,    32,     0,   280,
       0,     0,     0,     0,   421,     1,     2,     3,     4,     5,
       6,     7,     0,     8,     9,    10,    11,    12,     0,    13,
      14,    15,    16,    17,    18,    19,    20,    21,     0,     0,
       0,     0,     0,     0,     0,     0,     0,    22,    23,    24,
       0,    25,    26,    27,    28,     0,     0,    29,     0,     0,
      30,     0,     0,     0,    31,     1,     2,     3,     4,     5,
       6,     7,     0,     8,     9,    10,     0,    12,     0,    32,
      14,     0,     0,    17,    18,    19,    20,    21,     0,     0,
       0,     0,     0,     0,     0,     0,     0,    22,    23,    24,
       0,    25,    26,   131,    28,     0,     0,    29,   132,     0,
      30,     0,     1,     2,     3,     4,     5,     6,     7,     0,
       8,     9,    10,     0,    12,     0,     0,    14,     0,    32,
      17,    18,    19,    20,    21,     0,     0,     0,     0,     0,
       0,     0,     0,     0,    22,    23,    24,     0,    25,    26,
     131,    28,     0,     0,    29,     0,     0,    30,     0,     1,
       2,     3,     4,     5,     6,     7,     0,     0,     9,    10,
       0,    12,     0,     0,    14,     0,    32,    17,    18,    19,
      20,    21,     0,     0,     0,     0,    12,    82,     0,     0,
       0,    22,    23,    24,     0,    25,    26,    73,    28,     0,
       0,    29,     0,     0,    30,     0,    83,    84,    24,     0,
      25,    26,    85,    28,     0,     0,     0,     0,     0,    87,
      12,    82,     0,    32,   237,     0,     0,     0,     0,     0,
      88,    89,     0,     0,     0,    90,    91,     0,    32,     0,
      83,    84,    24,     0,    25,    26,    85,    28,     0,     0,
       0,     0,     0,   228,   265,    12,    82,     0,     0,     0,
       0,     0,     0,     0,    88,    89,     0,     0,     0,    90,
      91,     0,    32,     0,     0,    83,    84,    24,     0,    25,
      26,    85,    28,   272,     0,     0,     0,     0,    87,    12,
      82,     0,     0,     0,     0,     0,     0,     0,     0,    88,
      89,     0,     0,     0,    90,    91,     0,    32,     0,    83,
      84,    24,     0,    25,    26,    85,    28,     0,     0,     0,
       0,     0,    87,   278,    12,    82,     0,     0,     0,     0,
       0,     0,     0,    88,    89,     0,     0,     0,    90,    91,
       0,    32,     0,     0,    83,    84,    24,     0,    25,    26,
      85,    28,     0,     0,     0,     0,     0,    87,    12,    82,
       0,     0,   281,     0,     0,     0,     0,     0,    88,    89,
       0,     0,     0,    90,    91,     0,    32,     0,    83,    84,
      24,     0,    25,    26,   227,    28,     0,     0,     0,     0,
       0,    87,   310,    12,    82,     0,     0,     0,     0,     0,
       0,     0,    88,    89,     0,     0,     0,    90,    91,     0,
      32,     0,     0,    83,    84,    24,     0,    25,    26,    85,
      28,     0,     0,     0,   319,     0,    87,    12,    82,     0,
       0,     0,     0,     0,     0,     0,     0,    88,    89,     0,
       0,     0,    90,    91,     0,    32,     0,    83,    84,    24,
       0,    25,    26,    85,    28,     0,     0,     0,     0,     0,
      87,   346,    12,    82,     0,     0,     0,     0,     0,     0,
       0,    88,    89,     0,     0,     0,    90,    91,     0,    32,
       0,     0,    83,    84,    24,     0,    25,    26,    85,    28,
       0,     0,     0,     0,     0,    87,   390,    12,    82,     0,
       0,     0,     0,     0,     0,     0,    88,    89,     0,     0,
       0,    90,    91,     0,    32,     0,     0,    83,    84,    24,
       0,    25,    26,    85,    28,     0,     0,    29,     0,     0,
      87,    12,    82,     0,     0,     0,     0,     0,     0,     0,
       0,    88,    89,     0,     0,     0,    90,    91,     0,    32,
       0,    83,    84,    24,     0,    25,    26,    85,    28,     0,
       0,     0,     0,     0,    87,   417,    12,    82,     0,     0,
       0,     0,     0,     0,     0,    88,    89,     0,     0,     0,
      90,    91,     0,    32,     0,     0,    83,    84,    24,     0,
      25,    26,   118,    28,     0,     0,     0,     0,     0,    30,
      12,    82,     0,     0,     0,     0,     0,     0,     0,     0,
      88,    89,     0,     0,     0,    90,    91,     0,    32,     0,
      83,    84,    24,     0,    25,    26,    85,    28,     0,     0,
       0,     0,     0,    87,    12,    82,     0,     0,     0,     0,
       0,     0,     0,     0,    88,    89,     0,     0,     0,    90,
      91,     0,    32,     0,    83,    84,    24,     0,    25,    26,
     194,    28,     0,     0,     0,     0,     0,    87,    12,    82,
       0,     0,     0,     0,     0,     0,     0,     0,    88,    89,
       0,     0,     0,    90,    91,     0,    32,     0,    83,    84,
      24,     0,    25,    26,   118,    28,     0,     0,     0,     0,
       0,    87,    12,    82,     0,     0,     0,     0,     0,     0,
       0,     0,    88,    89,     0,     0,     0,    90,    91,     0,
      32,     0,    83,    84,    24,     0,    25,    26,    85,    28,
       0,     0,     0,     0,     0,   228,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,    88,    89,     0,     0,
       0,    90,    91,     0,    32
    };
  }

private static final short yycheck_[] = yycheck_init();
  private static final short[] yycheck_init()
  {
    return new short[]
    {
       0,   247,     0,    88,    89,   190,    43,     7,    53,     7,
      43,   235,    11,    35,    36,    29,    54,    43,     0,   229,
      34,    66,    22,    23,    47,     7,    48,    49,    43,    29,
      11,    29,   334,    66,    43,    35,    36,    35,    36,    46,
      22,    23,    41,    66,    43,    31,    32,    29,    48,    49,
      48,    49,    43,    35,    36,    33,    34,    66,     7,    66,
      41,    41,    58,    77,    60,    50,    48,    49,    54,    55,
     108,    52,    47,    51,   284,    66,    43,   379,   380,    75,
      29,    66,   384,    83,    84,    33,    34,    43,    88,    89,
      90,    91,    43,   303,    49,   305,    22,    23,    43,    66,
      43,    83,    84,    51,   314,   407,    88,    89,    90,    91,
      66,    66,    43,    48,   110,    66,    49,   113,   328,   329,
     134,    66,    49,    66,   334,    49,   148,   149,   374,   353,
     215,   216,   217,    66,   134,    66,   134,   322,    48,    66,
      41,    41,    66,    49,    49,    49,     0,    48,   148,   149,
     148,   149,   134,     7,    49,   365,   228,    83,    84,   157,
      66,    66,    66,   159,    90,    91,   148,   149,    48,   379,
     380,    66,   396,    49,   384,    29,   422,    50,    41,   251,
      50,    35,    36,    48,   180,   134,   258,    52,   398,    54,
      66,   401,    48,    66,    48,    49,    66,   407,    48,    41,
     285,    43,   202,   203,   204,   205,   206,   207,   208,   209,
     210,   211,   212,   213,   214,   215,   216,   217,   256,    49,
     202,   203,   204,   205,   206,   207,   208,   209,   210,   211,
     212,   213,   214,   215,   216,   217,    66,   309,    49,    33,
      34,    43,    48,    45,    49,    51,    52,   243,    33,    34,
      44,    49,    48,    47,    48,    66,    49,    51,    52,   331,
      54,    66,    47,    48,    43,    49,    51,    52,    66,     0,
      53,    53,    51,    66,    48,   275,     7,   275,   274,    41,
     134,    41,    66,    66,    66,   285,    48,    49,    50,    51,
      52,    43,    54,   275,   148,   149,   368,    48,    29,    51,
       0,    52,     0,   285,    35,    36,   306,    41,   306,     7,
      52,    43,    54,    41,    33,    34,    41,    48,    49,    51,
      52,    49,    54,    44,   306,    44,   275,    43,    47,    48,
      41,    29,    51,    52,    52,    54,    43,    35,    36,    50,
      47,    52,    43,    54,    51,    47,   346,    41,   346,    41,
      48,    49,    37,    38,    43,    49,    41,   306,    41,    48,
      45,    41,    51,    41,   346,    47,    49,    43,    44,    49,
      52,    49,    54,    48,    49,    47,    51,    52,    48,    54,
      25,    48,    52,    47,    54,    52,   386,    54,   386,   389,
     390,   389,   390,   393,    47,   393,    37,   346,    39,    40,
      41,    29,    30,   134,   386,    59,    60,   389,   390,    44,
     410,   393,   410,   413,   414,   413,   414,   148,   149,    28,
      17,   275,    49,    50,     8,    61,    62,    63,   410,   317,
     318,   413,   414,    30,   207,   208,   134,   386,   213,   214,
     389,   390,    27,    56,   393,    29,    57,    31,    58,    51,
     148,   149,   306,   209,   210,   211,   212,    43,    13,    46,
      43,   410,    41,    44,   413,   414,    50,    41,    48,    41,
      48,    53,    43,    41,    43,    45,    49,    41,    66,    55,
      55,    78,    49,    49,    81,    50,    43,    50,    48,    48,
      87,    53,   346,    77,    43,    49,    48,    41,    26,    49,
      46,    10,    50,    87,    49,    26,    49,    48,    50,    49,
      49,   276,    49,    51,   134,    34,   202,   114,   115,   116,
     117,   125,   203,    -1,    -1,   204,   123,   124,   205,   428,
     127,   128,   386,   206,    -1,   389,   390,    -1,    -1,   393,
      -1,    -1,   126,    -1,   275,   129,    -1,   144,    -1,    -1,
     134,    -1,    -1,    -1,    -1,    -1,   410,    -1,    -1,   413,
     414,    -1,    -1,    -1,   161,   162,   163,    -1,    -1,   166,
     167,    -1,    -1,    -1,    -1,   306,    -1,   275,   175,    -1,
      -1,    -1,    -1,    -1,    -1,   182,   183,    -1,    -1,    -1,
      -1,   188,   189,    -1,    -1,    -1,    -1,    15,    16,    -1,
      -1,    -1,    -1,    -1,   201,    -1,   190,    -1,   306,    -1,
      -1,    -1,    -1,    -1,    -1,   346,    -1,    35,    36,    37,
      -1,    39,    40,    41,    42,    43,    -1,    -1,    -1,    -1,
      48,   228,    -1,    -1,    -1,    -1,    -1,    -1,   235,    -1,
      -1,    59,    60,    -1,   228,    -1,    64,    65,   346,    67,
      -1,    -1,    -1,    -1,    -1,   386,    -1,   254,   389,   390,
      -1,    -1,   393,    -1,   248,    -1,    -1,   251,    -1,   266,
      -1,    -1,    -1,   270,   258,   272,    -1,    15,    16,   410,
      -1,    -1,   413,   414,    -1,    -1,    -1,    -1,   386,    -1,
      -1,   389,   390,    -1,    -1,   393,    -1,    35,    36,    37,
      -1,    39,    40,    41,    42,    -1,    -1,   304,    -1,    -1,
      48,    49,   410,    -1,    -1,   413,   414,    -1,    -1,    -1,
      -1,    59,    60,    -1,    -1,   309,    64,    65,    -1,    67,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,   322,    -1,
      -1,    -1,    -1,    -1,    -1,    15,    16,   331,   345,    -1,
      -1,   348,   349,    -1,    -1,    -1,   353,   354,    -1,    -1,
      -1,    -1,   359,    -1,    -1,    35,    36,    37,   365,    39,
      40,    41,    42,    -1,    -1,    45,    -1,    -1,    48,    -1,
      -1,    -1,    -1,    -1,   368,    -1,    -1,    -1,    -1,    59,
      60,    -1,    -1,    -1,    64,    65,    -1,    67,    -1,   396,
      -1,    -1,    -1,    -1,   401,     3,     4,     5,     6,     7,
       8,     9,    -1,    11,    12,    13,    14,    15,    -1,    17,
      18,    19,    20,    21,    22,    23,    24,    25,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    35,    36,    37,
      -1,    39,    40,    41,    42,    -1,    -1,    45,    -1,    -1,
      48,    -1,    -1,    -1,    52,     3,     4,     5,     6,     7,
       8,     9,    -1,    11,    12,    13,    -1,    15,    -1,    67,
      18,    -1,    -1,    21,    22,    23,    24,    25,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    35,    36,    37,
      -1,    39,    40,    41,    42,    -1,    -1,    45,    46,    -1,
      48,    -1,     3,     4,     5,     6,     7,     8,     9,    -1,
      11,    12,    13,    -1,    15,    -1,    -1,    18,    -1,    67,
      21,    22,    23,    24,    25,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    35,    36,    37,    -1,    39,    40,
      41,    42,    -1,    -1,    45,    -1,    -1,    48,    -1,     3,
       4,     5,     6,     7,     8,     9,    -1,    -1,    12,    13,
      -1,    15,    -1,    -1,    18,    -1,    67,    21,    22,    23,
      24,    25,    -1,    -1,    -1,    -1,    15,    16,    -1,    -1,
      -1,    35,    36,    37,    -1,    39,    40,    41,    42,    -1,
      -1,    45,    -1,    -1,    48,    -1,    35,    36,    37,    -1,
      39,    40,    41,    42,    -1,    -1,    -1,    -1,    -1,    48,
      15,    16,    -1,    67,    53,    -1,    -1,    -1,    -1,    -1,
      59,    60,    -1,    -1,    -1,    64,    65,    -1,    67,    -1,
      35,    36,    37,    -1,    39,    40,    41,    42,    -1,    -1,
      -1,    -1,    -1,    48,    49,    15,    16,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    59,    60,    -1,    -1,    -1,    64,
      65,    -1,    67,    -1,    -1,    35,    36,    37,    -1,    39,
      40,    41,    42,    43,    -1,    -1,    -1,    -1,    48,    15,
      16,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    59,
      60,    -1,    -1,    -1,    64,    65,    -1,    67,    -1,    35,
      36,    37,    -1,    39,    40,    41,    42,    -1,    -1,    -1,
      -1,    -1,    48,    49,    15,    16,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    59,    60,    -1,    -1,    -1,    64,    65,
      -1,    67,    -1,    -1,    35,    36,    37,    -1,    39,    40,
      41,    42,    -1,    -1,    -1,    -1,    -1,    48,    15,    16,
      -1,    -1,    53,    -1,    -1,    -1,    -1,    -1,    59,    60,
      -1,    -1,    -1,    64,    65,    -1,    67,    -1,    35,    36,
      37,    -1,    39,    40,    41,    42,    -1,    -1,    -1,    -1,
      -1,    48,    49,    15,    16,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    59,    60,    -1,    -1,    -1,    64,    65,    -1,
      67,    -1,    -1,    35,    36,    37,    -1,    39,    40,    41,
      42,    -1,    -1,    -1,    46,    -1,    48,    15,    16,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    59,    60,    -1,
      -1,    -1,    64,    65,    -1,    67,    -1,    35,    36,    37,
      -1,    39,    40,    41,    42,    -1,    -1,    -1,    -1,    -1,
      48,    49,    15,    16,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    59,    60,    -1,    -1,    -1,    64,    65,    -1,    67,
      -1,    -1,    35,    36,    37,    -1,    39,    40,    41,    42,
      -1,    -1,    -1,    -1,    -1,    48,    49,    15,    16,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    59,    60,    -1,    -1,
      -1,    64,    65,    -1,    67,    -1,    -1,    35,    36,    37,
      -1,    39,    40,    41,    42,    -1,    -1,    45,    -1,    -1,
      48,    15,    16,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    59,    60,    -1,    -1,    -1,    64,    65,    -1,    67,
      -1,    35,    36,    37,    -1,    39,    40,    41,    42,    -1,
      -1,    -1,    -1,    -1,    48,    49,    15,    16,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    59,    60,    -1,    -1,    -1,
      64,    65,    -1,    67,    -1,    -1,    35,    36,    37,    -1,
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
      -1,    48,    15,    16,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    59,    60,    -1,    -1,    -1,    64,    65,    -1,
      67,    -1,    35,    36,    37,    -1,    39,    40,    41,    42,
      -1,    -1,    -1,    -1,    -1,    48,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    59,    60,    -1,    -1,
      -1,    64,    65,    -1,    67
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
      47,    47,    47,    47,    48,    52,    25,    43,    52,    88,
      41,    41,    43,    87,    97,    99,    44,    72,    48,    52,
      54,    99,   109,   109,    41,    97,   110,   110,   109,   109,
      43,    66,    28,    27,    56,    57,    58,    29,    30,    31,
      32,    54,    55,    59,    60,    61,    62,    63,    99,    99,
      99,    99,    43,    43,    99,    99,    79,    41,    48,    49,
      93,    96,    97,    99,   125,    45,    99,    53,    99,    97,
      98,    76,    46,    41,    49,    44,    99,    48,    48,    69,
      69,    48,    41,    41,    48,    95,   123,    43,    48,    88,
      99,    99,    99,    99,    99,    49,    48,    43,    43,    88,
      47,    99,    43,    99,    41,    49,    41,    43,    49,    89,
      99,    53,    99,    98,    49,    49,    99,   101,   102,   103,
     104,   105,   106,   106,   107,   107,   107,   107,   108,   108,
     110,   110,   110,    49,    50,    49,    49,    43,    43,    50,
      49,    93,    97,    75,    49,    49,    41,    50,    50,    46,
      89,    53,    50,    55,    53,    46,   127,    41,    49,    49,
      93,    48,    99,   122,    49,    93,    43,    53,    43,    43,
      43,    43,    99,    43,    99,    43,    49,    99,    43,    47,
      73,    72,    49,    50,    48,    53,    55,    75,   110,    47,
      75,    99,    75,    73,    93,    26,    49,    75,    50,    96,
      96,    46,    98,    49,    50,   128,    43,    75,    92,    49,
      49,    93,    49,    92,    49,    49,    49,    99,    73,    49,
      49,    99,    99,    10,    89,    99,    48,    99,    49,    75,
      99,    26,    93,   127,    49,    92,    92,    49,    92,    73,
      49,    73,    73,    49,    49,    73,    49,    49,    89,    75,
      75,    99,    51,    92,    73,    73,    73,    49,   127,   128
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
      87,    88,    88,    88,    89,    89,    90,    90,    90,    90,
      90,    90,    90,    90,    91,    92,    92,    93,    93,    93,
      93,    94,    94,    94,    94,    94,    94,    95,    95,    96,
      96,    96,    96,    97,    97,    97,    98,    98,    99,    99,
      99,   100,   100,   101,   101,   102,   102,   103,   103,   104,
     104,   105,   105,   105,   106,   106,   106,   106,   106,   107,
     107,   107,   108,   108,   108,   108,   109,   109,   109,   109,
     109,   110,   110,   111,   111,   111,   112,   113,   113,   113,
     114,   114,   115,   115,   116,   116,   116,   116,   116,   117,
     117,   117,   117,   118,   118,   119,   120,   120,   120,   121,
     121,   122,   122,   123,   123,   123,   124,   124,   124,   124,
     124,   124,   124,   125,   125,   125,   125,   126,   126,   127,
     127,   127,   127,   128,   128
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
       4,     2,     3,     4,     1,     3,     6,     7,     5,     6,
       5,     6,     4,     5,     4,     1,     1,     1,     2,     3,
       4,     4,     4,     4,     4,     4,     4,     3,     4,     1,
       1,     3,     3,     3,     1,     4,     1,     3,     1,     1,
       1,     3,     1,     3,     1,     1,     3,     1,     3,     1,
       3,     1,     3,     3,     1,     3,     3,     3,     3,     1,
       3,     3,     1,     3,     3,     3,     2,     2,     2,     2,
       1,     1,     4,     1,     2,     2,     1,     1,     1,     1,
       1,     1,     3,     1,     1,     1,     1,     1,     1,     1,
       1,     1,     1,     4,     4,     5,     2,     2,     2,     2,
       2,     3,     2,     1,     1,     3,     2,     4,     5,     5,
       7,     7,     8,     5,     5,     4,     4,     5,     6,     3,
       1,     1,     1,     5,     0
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
     354,   360,   361,   364,   370,   371,   375,   378,   381,   384,
     387,   390,   393,   396,   401,   405,   406,   410,   411,   412,
     413,   421,   425,   429,   432,   435,   438,   447,   450,   456,
     457,   458,   459,   463,   464,   465,   471,   472,   476,   477,
     478,   483,   486,   490,   493,   497,   498,   504,   505,   511,
     512,   518,   519,   522,   528,   529,   532,   535,   538,   544,
     545,   548,   554,   555,   558,   561,   567,   571,   575,   576,
     577,   581,   582,   587,   588,   589,   593,   599,   600,   601,
     605,   606,   610,   611,   615,   616,   617,   618,   619,   623,
     624,   625,   626,   630,   634,   638,   644,   648,   649,   653,
     659,   663,   664,   668,   669,   670,   674,   675,   678,   681,
     685,   689,   693,   700,   703,   706,   709,   716,   719,   725,
     726,   727,   728,   732,   735
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

  private static final int yylast_ = 1544;
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

/* "VondaGrammar.java":3252  */ /* lalr1.java:1066  */

}

