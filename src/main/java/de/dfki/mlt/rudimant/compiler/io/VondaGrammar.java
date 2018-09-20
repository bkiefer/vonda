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
import de.dfki.mlt.rudimant.common.Position;
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
    static final int WHILE = 279;
    /** Token number,to be returned by the scanner.  */
    static final int ARROW = 280;
    /** Token number,to be returned by the scanner.  */
    static final int ANDAND = 281;
    /** Token number,to be returned by the scanner.  */
    static final int OROR = 282;
    /** Token number,to be returned by the scanner.  */
    static final int EQEQ = 283;
    /** Token number,to be returned by the scanner.  */
    static final int NOTEQ = 284;
    /** Token number,to be returned by the scanner.  */
    static final int GTEQ = 285;
    /** Token number,to be returned by the scanner.  */
    static final int LTEQ = 286;
    /** Token number,to be returned by the scanner.  */
    static final int MINUSEQ = 287;
    /** Token number,to be returned by the scanner.  */
    static final int PLUSEQ = 288;
    /** Token number,to be returned by the scanner.  */
    static final int MINUSMINUS = 289;
    /** Token number,to be returned by the scanner.  */
    static final int PLUSPLUS = 290;
    /** Token number,to be returned by the scanner.  */
    static final int STRING = 291;
    /** Token number,to be returned by the scanner.  */
    static final int WILDCARD = 292;
    /** Token number,to be returned by the scanner.  */
    static final int INT = 293;
    /** Token number,to be returned by the scanner.  */
    static final int BOOL_LITERAL = 294;
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
    /* "VondaGrammar.y":142  */ /* lalr1.java:489  */
    {
    yyval = ((LinkedList<RudiTree>)(yystack.valueAt (3-(3)))); ((StatMethodDeclaration)(yystack.valueAt (3-(2)))).setVisibility(((String)(yystack.valueAt (3-(1))))); ((LinkedList<RudiTree>)(yystack.valueAt (3-(3)))).addFirst(((StatMethodDeclaration)(yystack.valueAt (3-(2)))));
  };
  break;
    

  case 3:
  if (yyn == 3)
    /* "VondaGrammar.y":145  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))); ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))).addFirst(((StatMethodDeclaration)(yystack.valueAt (2-(1))))); };
  break;
    

  case 4:
  if (yyn == 4)
    /* "VondaGrammar.y":146  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))); ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))).addFirst(((RTExpression)(yystack.valueAt (2-(1))))); };
  break;
    

  case 5:
  if (yyn == 5)
    /* "VondaGrammar.y":147  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))); ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))).addFirst(((RTStatement)(yystack.valueAt (2-(1))))); };
  break;
    

  case 6:
  if (yyn == 6)
    /* "VondaGrammar.y":149  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))); ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))).addFirst(((Import)(yystack.valueAt (2-(1))))); };
  break;
    

  case 7:
  if (yyn == 7)
    /* "VondaGrammar.y":150  */ /* lalr1.java:489  */
    {
    yyval = ((LinkedList<RudiTree>)(yystack.valueAt (3-(3)))); ((LinkedList<RudiTree>)(yystack.valueAt (3-(3)))).addFirst(setPos(new StatFieldDef(((String)(yystack.valueAt (3-(1)))), ((StatVarDef)(yystack.valueAt (3-(2))))), yystack.locationAt (3-(1)), yystack.locationAt (3-(2))));
  };
  break;
    

  case 8:
  if (yyn == 8)
    /* "VondaGrammar.y":153  */ /* lalr1.java:489  */
    {
    yyval = ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))); ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))).addFirst(setPos(new StatFieldDef(null, ((StatVarDef)(yystack.valueAt (2-(1))))), yystack.locationAt (2-(1))));
  };
  break;
    

  case 9:
  if (yyn == 9)
    /* "VondaGrammar.y":156  */ /* lalr1.java:489  */
    {
    yyval = ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))); ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))).addFirst(((StatFieldDef)(yystack.valueAt (2-(1)))));
  };
  break;
    

  case 10:
  if (yyn == 10)
    /* "VondaGrammar.y":159  */ /* lalr1.java:489  */
    { yyval = _statements;};
  break;
    

  case 11:
  if (yyn == 11)
    /* "VondaGrammar.y":163  */ /* lalr1.java:489  */
    { yyval = "public"; };
  break;
    

  case 12:
  if (yyn == 12)
    /* "VondaGrammar.y":164  */ /* lalr1.java:489  */
    { yyval = "protected"; };
  break;
    

  case 13:
  if (yyn == 13)
    /* "VondaGrammar.y":165  */ /* lalr1.java:489  */
    { yyval = "private"; };
  break;
    

  case 14:
  if (yyn == 14)
    /* "VondaGrammar.y":169  */ /* lalr1.java:489  */
    {
    List<String> path = ((List<String>)(yystack.valueAt (3-(2))));
    String name = path.remove(path.size() - 1);
    yyval = setPos(new Import(name, path.toArray(new String[path.size()])), (yyloc));
  };
  break;
    

  case 15:
  if (yyn == 15)
    /* "VondaGrammar.y":177  */ /* lalr1.java:489  */
    { yyval = new ArrayList<String>(){{ add((( String )(yystack.valueAt (1-(1))))); }}; };
  break;
    

  case 16:
  if (yyn == 16)
    /* "VondaGrammar.y":178  */ /* lalr1.java:489  */
    { yyval = ((List<String>)(yystack.valueAt (3-(1)))); ((List<String>)(yystack.valueAt (3-(1)))).add((( String )(yystack.valueAt (3-(3))))); };
  break;
    

  case 17:
  if (yyn == 17)
    /* "VondaGrammar.y":182  */ /* lalr1.java:489  */
    { yyval = ((StatAbstractBlock)(yystack.valueAt (1-(1)))); };
  break;
    

  case 18:
  if (yyn == 18)
    /* "VondaGrammar.y":183  */ /* lalr1.java:489  */
    { yyval = setPos(getAssignmentStat(((RTExpression)(yystack.valueAt (2-(1))))), (yyloc)); };
  break;
    

  case 19:
  if (yyn == 19)
    /* "VondaGrammar.y":184  */ /* lalr1.java:489  */
    { yyval = setPos(new StatExpression(((RTExpression)(yystack.valueAt (2-(1))))), (yyloc)); };
  break;
    

  case 20:
  if (yyn == 20)
    /* "VondaGrammar.y":185  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatExpression(createPlusMinus(((RTExpression)(yystack.valueAt (3-(2)))), "+", (yyloc))), (yyloc));
  };
  break;
    

  case 21:
  if (yyn == 21)
    /* "VondaGrammar.y":188  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatExpression(createPlusMinus(((RTExpression)(yystack.valueAt (3-(2)))), "+", (yyloc))), (yyloc));
  };
  break;
    

  case 22:
  if (yyn == 22)
    /* "VondaGrammar.y":191  */ /* lalr1.java:489  */
    { yyval = setPos(new StatExpression(((RTExpression)(yystack.valueAt (2-(1))))), (yyloc)); };
  break;
    

  case 23:
  if (yyn == 23)
    /* "VondaGrammar.y":192  */ /* lalr1.java:489  */
    { yyval = ((StatGrammarRule)(yystack.valueAt (1-(1)))); };
  break;
    

  case 24:
  if (yyn == 24)
    /* "VondaGrammar.y":193  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 25:
  if (yyn == 25)
    /* "VondaGrammar.y":194  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 26:
  if (yyn == 26)
    /* "VondaGrammar.y":195  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 27:
  if (yyn == 27)
    /* "VondaGrammar.y":196  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 28:
  if (yyn == 28)
    /* "VondaGrammar.y":197  */ /* lalr1.java:489  */
    { yyval = ((StatIf)(yystack.valueAt (1-(1)))); };
  break;
    

  case 29:
  if (yyn == 29)
    /* "VondaGrammar.y":198  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 30:
  if (yyn == 30)
    /* "VondaGrammar.y":199  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 31:
  if (yyn == 31)
    /* "VondaGrammar.y":200  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 32:
  if (yyn == 32)
    /* "VondaGrammar.y":201  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 33:
  if (yyn == 33)
    /* "VondaGrammar.y":205  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 34:
  if (yyn == 34)
    /* "VondaGrammar.y":206  */ /* lalr1.java:489  */
    { yyval = ((StatVarDef)(yystack.valueAt (1-(1)))); };
  break;
    

  case 35:
  if (yyn == 35)
    /* "VondaGrammar.y":210  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 36:
  if (yyn == 36)
    /* "VondaGrammar.y":216  */ /* lalr1.java:489  */
    { yyval = setPos(new StatAbstractBlock(((LinkedList<RTStatement>)(yystack.valueAt (3-(2)))), true), (yyloc)); };
  break;
    

  case 37:
  if (yyn == 37)
    /* "VondaGrammar.y":217  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatAbstractBlock(new ArrayList<RTStatement>(), true), (yyloc));
  };
  break;
    

  case 38:
  if (yyn == 38)
    /* "VondaGrammar.y":221  */ /* lalr1.java:489  */
    { yyval = new LinkedList<RTStatement>(){{ add(((RTStatement)(yystack.valueAt (1-(1))))); }}; };
  break;
    

  case 39:
  if (yyn == 39)
    /* "VondaGrammar.y":222  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RTStatement>)(yystack.valueAt (2-(2)))); ((LinkedList<RTStatement>)(yystack.valueAt (2-(2)))).addFirst(((RTStatement)(yystack.valueAt (2-(1))))); };
  break;
    

  case 40:
  if (yyn == 40)
    /* "VondaGrammar.y":226  */ /* lalr1.java:489  */
    { yyval = setPos(new StatGrammarRule((( String )(yystack.valueAt (3-(1)))), ((StatIf)(yystack.valueAt (3-(3))))), (yyloc)); };
  break;
    

  case 41:
  if (yyn == 41)
    /* "VondaGrammar.y":230  */ /* lalr1.java:489  */
    { yyval = setPos(new StatReturn("return"), (yyloc)); };
  break;
    

  case 42:
  if (yyn == 42)
    /* "VondaGrammar.y":231  */ /* lalr1.java:489  */
    { yyval = setPos(new StatReturn(((RTExpression)(yystack.valueAt (3-(2))))), (yyloc)); };
  break;
    

  case 43:
  if (yyn == 43)
    /* "VondaGrammar.y":232  */ /* lalr1.java:489  */
    { yyval = setPos(new StatReturn("break"), (yyloc)); };
  break;
    

  case 44:
  if (yyn == 44)
    /* "VondaGrammar.y":233  */ /* lalr1.java:489  */
    { yyval = setPos(new StatReturn("break", (( String )(yystack.valueAt (3-(2))))), (yyloc)); };
  break;
    

  case 45:
  if (yyn == 45)
    /* "VondaGrammar.y":234  */ /* lalr1.java:489  */
    { yyval = setPos(new StatReturn("cancel"), (yyloc)); };
  break;
    

  case 46:
  if (yyn == 46)
    /* "VondaGrammar.y":235  */ /* lalr1.java:489  */
    { yyval = setPos(new StatReturn("cancel_all"), (yyloc)); };
  break;
    

  case 47:
  if (yyn == 47)
    /* "VondaGrammar.y":236  */ /* lalr1.java:489  */
    { yyval = setPos(new StatReturn("continue"), (yyloc)); };
  break;
    

  case 48:
  if (yyn == 48)
    /* "VondaGrammar.y":240  */ /* lalr1.java:489  */
    { yyval = setPos(new StatIf(((RTExpression)(yystack.valueAt (5-(3)))), ((RTStatement)(yystack.valueAt (5-(5)))), null), (yyloc)); };
  break;
    

  case 49:
  if (yyn == 49)
    /* "VondaGrammar.y":241  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatIf(((RTExpression)(yystack.valueAt (7-(3)))), ((RTStatement)(yystack.valueAt (7-(5)))), ((RTStatement)(yystack.valueAt (7-(7))))), (yyloc));
  };
  break;
    

  case 50:
  if (yyn == 50)
    /* "VondaGrammar.y":247  */ /* lalr1.java:489  */
    { yyval = setPos(new StatWhile(((RTExpression)(yystack.valueAt (5-(3)))), ((RTStatement)(yystack.valueAt (5-(5)))), true), (yyloc)); };
  break;
    

  case 51:
  if (yyn == 51)
    /* "VondaGrammar.y":248  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatWhile(((RTExpression)(yystack.valueAt (6-(5)))), ((RTStatement)(yystack.valueAt (6-(2)))), false), (yyloc));
  };
  break;
    

  case 52:
  if (yyn == 52)
    /* "VondaGrammar.y":254  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatFor1(((StatVarDef)(yystack.valueAt (8-(3)))), ((RTExpression)(yystack.valueAt (8-(4)))), ((RTExpression)(yystack.valueAt (8-(6)))), ((RTStatement)(yystack.valueAt (8-(8))))), (yyloc)); };
  break;
    

  case 53:
  if (yyn == 53)
    /* "VondaGrammar.y":256  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatFor1(((StatVarDef)(yystack.valueAt (7-(3)))), null, ((RTExpression)(yystack.valueAt (7-(5)))), ((RTStatement)(yystack.valueAt (7-(7))))), (yyloc)); };
  break;
    

  case 54:
  if (yyn == 54)
    /* "VondaGrammar.y":258  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatFor1(((StatVarDef)(yystack.valueAt (7-(3)))), ((RTExpression)(yystack.valueAt (7-(4)))), null, ((RTStatement)(yystack.valueAt (7-(7))))), (yyloc)); };
  break;
    

  case 55:
  if (yyn == 55)
    /* "VondaGrammar.y":260  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatFor1(((StatVarDef)(yystack.valueAt (6-(3)))), null, null, ((RTStatement)(yystack.valueAt (6-(6))))), (yyloc)); };
  break;
    

  case 56:
  if (yyn == 56)
    /* "VondaGrammar.y":262  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatFor1(null, ((RTExpression)(yystack.valueAt (8-(4)))), ((RTExpression)(yystack.valueAt (8-(6)))), ((RTStatement)(yystack.valueAt (8-(8))))), (yyloc)); };
  break;
    

  case 57:
  if (yyn == 57)
    /* "VondaGrammar.y":264  */ /* lalr1.java:489  */
    {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (7-(3))))), yystack.locationAt (7-(3)));
    yyval = setPos(new StatFor2(var, ((RTExpression)(yystack.valueAt (7-(5)))), ((RTStatement)(yystack.valueAt (7-(7))))), (yyloc));
  };
  break;
    

  case 58:
  if (yyn == 58)
    /* "VondaGrammar.y":268  */ /* lalr1.java:489  */
    {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (8-(4))))), yystack.locationAt (8-(4)));
    yyval = setPos(new StatFor2(((Type)(yystack.valueAt (8-(3)))), var, ((RTExpression)(yystack.valueAt (8-(6)))), ((RTStatement)(yystack.valueAt (8-(8))))), (yyloc));
  };
  break;
    

  case 59:
  if (yyn == 59)
    /* "VondaGrammar.y":277  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatVarDef(false, Type.getNoType(), (( String )(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(2))))), (yyloc));
  };
  break;
    

  case 60:
  if (yyn == 60)
    /* "VondaGrammar.y":280  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatVarDef(false, ((Type)(yystack.valueAt (4-(1)))), (( String )(yystack.valueAt (4-(2)))), ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc));
  };
  break;
    

  case 61:
  if (yyn == 61)
    /* "VondaGrammar.y":286  */ /* lalr1.java:489  */
    { yyval = setPos(new StatPropose(((RTExpression)(yystack.valueAt (5-(3)))), ((StatAbstractBlock)(yystack.valueAt (5-(5))))), (yyloc)); };
  break;
    

  case 62:
  if (yyn == 62)
    /* "VondaGrammar.y":290  */ /* lalr1.java:489  */
    {
    // labeled timeout
    yyval = setPos(new StatTimeout(((RTExpression)(yystack.valueAt (7-(3)))), ((RTExpression)(yystack.valueAt (7-(5)))), ((StatAbstractBlock)(yystack.valueAt (7-(7))))), (yyloc));
  };
  break;
    

  case 63:
  if (yyn == 63)
    /* "VondaGrammar.y":294  */ /* lalr1.java:489  */
    {
    // behaviour timeout
    yyval = setPos(new StatTimeout(((RTExpression)(yystack.valueAt (7-(3)))), ((RTExpression)(yystack.valueAt (7-(5)))), ((StatAbstractBlock)(yystack.valueAt (7-(7))))), (yyloc));
  };
  break;
    

  case 64:
  if (yyn == 64)
    /* "VondaGrammar.y":301  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatSwitch(((RTExpression)(yystack.valueAt (5-(3)))), ((StatAbstractBlock)(yystack.valueAt (5-(5))))), (yyloc));
  };
  break;
    

  case 65:
  if (yyn == 65)
    /* "VondaGrammar.y":327  */ /* lalr1.java:489  */
    {
    ExpLiteral val =
      new ExpLiteral("case \"" + (( ExpLiteral )(yystack.valueAt (3-(2)))).toString() + "\":", "label");
    RTStatement lbl = val.ensureStatement();
    setPos(val, (yyloc)); setPos(lbl, (yyloc));
    yyval = lbl;
  };
  break;
    

  case 66:
  if (yyn == 66)
    /* "VondaGrammar.y":334  */ /* lalr1.java:489  */
    {
    ExpLiteral val =
      new ExpLiteral("case " + (( ExpLiteral )(yystack.valueAt (3-(2)))).toString() + ":", "label");
    RTStatement lbl = val.ensureStatement();
    setPos(val, (yyloc)); setPos(lbl, (yyloc));
    yyval = lbl;
  };
  break;
    

  case 67:
  if (yyn == 67)
    /* "VondaGrammar.y":341  */ /* lalr1.java:489  */
    {
    ExpLiteral val =
      new ExpLiteral("case " + (( ExpLiteral )(yystack.valueAt (3-(2)))).toString() + ":", "label");
    RTStatement lbl = val.ensureStatement();
    setPos(val, (yyloc)); setPos(lbl, (yyloc));
    yyval = lbl;
  };
  break;
    

  case 68:
  if (yyn == 68)
    /* "VondaGrammar.y":348  */ /* lalr1.java:489  */
    {
    ExpLiteral val =
      new ExpLiteral("case " + (( String )(yystack.valueAt (3-(2)))) + ":", "label");
    RTStatement lbl = val.ensureStatement();
    setPos(val, (yyloc)); setPos(lbl, (yyloc));
    yyval = lbl;
  };
  break;
    

  case 69:
  if (yyn == 69)
    /* "VondaGrammar.y":355  */ /* lalr1.java:489  */
    {
    ExpLiteral val = new ExpLiteral("default:", "label");
    RTStatement lbl = val.ensureStatement();
    setPos(val, (yyloc)); setPos(lbl, (yyloc));
    yyval = lbl;
  };
  break;
    

  case 70:
  if (yyn == 70)
    /* "VondaGrammar.y":364  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatVarDef(true, Type.getNoType(), (( String )(yystack.valueAt (4-(2)))), ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc));
  };
  break;
    

  case 71:
  if (yyn == 71)
    /* "VondaGrammar.y":367  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatVarDef(false, ((Type)(yystack.valueAt (4-(1)))), (( String )(yystack.valueAt (4-(2)))), ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc));
  };
  break;
    

  case 72:
  if (yyn == 72)
    /* "VondaGrammar.y":370  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatVarDef(true, ((Type)(yystack.valueAt (5-(2)))), (( String )(yystack.valueAt (5-(3)))), ((RTExpression)(yystack.valueAt (5-(4))))), (yyloc));
  };
  break;
    

  case 73:
  if (yyn == 73)
    /* "VondaGrammar.y":373  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatVarDef(true, Type.getNoType(), (( String )(yystack.valueAt (3-(2)))), null), (yyloc));
  };
  break;
    

  case 74:
  if (yyn == 74)
    /* "VondaGrammar.y":376  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatVarDef(false, ((Type)(yystack.valueAt (3-(1)))), (( String )(yystack.valueAt (3-(2)))), null), (yyloc));
  };
  break;
    

  case 75:
  if (yyn == 75)
    /* "VondaGrammar.y":379  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatVarDef(true, ((Type)(yystack.valueAt (4-(2)))), (( String )(yystack.valueAt (4-(3)))), null), (yyloc));
  };
  break;
    

  case 76:
  if (yyn == 76)
    /* "VondaGrammar.y":385  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatFieldDef(null,
                      setPos(new StatVarDef(false, ((Type)(yystack.valueAt (7-(5)))), (( String )(yystack.valueAt (7-(6)))), null), (yyloc)), ((Type)(yystack.valueAt (7-(2))))), (yyloc));
  };
  break;
    

  case 77:
  if (yyn == 77)
    /* "VondaGrammar.y":392  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (2-(2)))); };
  break;
    

  case 78:
  if (yyn == 78)
    /* "VondaGrammar.y":393  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpListLiteral(new LinkedList<RTExpression>()), yystack.locationAt (3-(2)), yystack.locationAt (3-(3)));
  };
  break;
    

  case 79:
  if (yyn == 79)
    /* "VondaGrammar.y":396  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpListLiteral(((LinkedList<RTExpression>)(yystack.valueAt (4-(3))))), yystack.locationAt (4-(2)), yystack.locationAt (4-(4)));
  };
  break;
    

  case 80:
  if (yyn == 80)
    /* "VondaGrammar.y":402  */ /* lalr1.java:489  */
    { yyval = new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (1-(1))))); }}; };
  break;
    

  case 81:
  if (yyn == 81)
    /* "VondaGrammar.y":403  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))); ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))).addFirst(((RTExpression)(yystack.valueAt (3-(1))))); };
  break;
    

  case 82:
  if (yyn == 82)
    /* "VondaGrammar.y":408  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatMethodDeclaration("public", ((Type)(yystack.valueAt (10-(5)))), ((Type)(yystack.valueAt (10-(2)))), (( String )(yystack.valueAt (10-(6)))), ((LinkedList)(yystack.valueAt (10-(8)))), ((StatAbstractBlock)(yystack.valueAt (10-(10))))), (yyloc));
  };
  break;
    

  case 83:
  if (yyn == 83)
    /* "VondaGrammar.y":411  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatMethodDeclaration("public", ((Type)(yystack.valueAt (6-(1)))), null, (( String )(yystack.valueAt (6-(2)))), ((LinkedList)(yystack.valueAt (6-(4)))), ((StatAbstractBlock)(yystack.valueAt (6-(6))))), (yyloc));
  };
  break;
    

  case 84:
  if (yyn == 84)
    /* "VondaGrammar.y":417  */ /* lalr1.java:489  */
    { yyval = ((StatAbstractBlock)(yystack.valueAt (1-(1)))); };
  break;
    

  case 85:
  if (yyn == 85)
    /* "VondaGrammar.y":418  */ /* lalr1.java:489  */
    { yyval = null; };
  break;
    

  case 86:
  if (yyn == 86)
    /* "VondaGrammar.y":422  */ /* lalr1.java:489  */
    { yyval = ((LinkedList)(yystack.valueAt (1-(1)))); };
  break;
    

  case 87:
  if (yyn == 87)
    /* "VondaGrammar.y":423  */ /* lalr1.java:489  */
    { yyval = new LinkedList(); };
  break;
    

  case 88:
  if (yyn == 88)
    /* "VondaGrammar.y":427  */ /* lalr1.java:489  */
    { yyval = new LinkedList(){{ add(Type.getNoType()); add((( String )(yystack.valueAt (1-(1))))); }}; };
  break;
    

  case 89:
  if (yyn == 89)
    /* "VondaGrammar.y":428  */ /* lalr1.java:489  */
    { yyval = new LinkedList(){{ add(((Type)(yystack.valueAt (2-(1))))); add((( String )(yystack.valueAt (2-(2))))); }}; };
  break;
    

  case 90:
  if (yyn == 90)
    /* "VondaGrammar.y":429  */ /* lalr1.java:489  */
    {
    yyval = ((LinkedList)(yystack.valueAt (3-(3))));
    ((LinkedList)(yystack.valueAt (3-(3)))).addFirst((( String )(yystack.valueAt (3-(1))))); ((LinkedList)(yystack.valueAt (3-(3)))).addFirst(Type.getNoType());
  };
  break;
    

  case 91:
  if (yyn == 91)
    /* "VondaGrammar.y":433  */ /* lalr1.java:489  */
    {
    yyval = ((LinkedList)(yystack.valueAt (4-(4)))); ((LinkedList)(yystack.valueAt (4-(4)))).addFirst((( String )(yystack.valueAt (4-(2))))); ((LinkedList)(yystack.valueAt (4-(4)))).addFirst(((Type)(yystack.valueAt (4-(1)))));
  };
  break;
    

  case 92:
  if (yyn == 92)
    /* "VondaGrammar.y":441  */ /* lalr1.java:489  */
    {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (4-(1))))), yystack.locationAt (4-(1)));
    yyval = setPos(new StatSetOperation(var, true, ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc));
  };
  break;
    

  case 93:
  if (yyn == 93)
    /* "VondaGrammar.y":445  */ /* lalr1.java:489  */
    {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (4-(1))))), yystack.locationAt (4-(1)));
    yyval = setPos(new StatSetOperation(var, false, ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc));
  };
  break;
    

  case 94:
  if (yyn == 94)
    /* "VondaGrammar.y":449  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatSetOperation(((RTExpression)(yystack.valueAt (4-(1)))), true, ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc));
  };
  break;
    

  case 95:
  if (yyn == 95)
    /* "VondaGrammar.y":452  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatSetOperation(((RTExpression)(yystack.valueAt (4-(1)))), false, ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc));
  };
  break;
    

  case 96:
  if (yyn == 96)
    /* "VondaGrammar.y":455  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatSetOperation(((RTExpression)(yystack.valueAt (4-(1)))), true, ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc));
  };
  break;
    

  case 97:
  if (yyn == 97)
    /* "VondaGrammar.y":458  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatSetOperation(((RTExpression)(yystack.valueAt (4-(1)))), false, ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc));
  };
  break;
    

  case 98:
  if (yyn == 98)
    /* "VondaGrammar.y":467  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpFuncCall((( String )(yystack.valueAt (3-(1)))), new LinkedList<RTExpression>(), false), (yyloc));
  };
  break;
    

  case 99:
  if (yyn == 99)
    /* "VondaGrammar.y":470  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpFuncCall((( String )(yystack.valueAt (4-(1)))), ((LinkedList<RTExpression>)(yystack.valueAt (4-(3)))), false), (yyloc));
  };
  break;
    

  case 100:
  if (yyn == 100)
    /* "VondaGrammar.y":476  */ /* lalr1.java:489  */
    { yyval = new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (1-(1))))); }}; };
  break;
    

  case 101:
  if (yyn == 101)
    /* "VondaGrammar.y":477  */ /* lalr1.java:489  */
    { yyval = new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (1-(1))))); }}; };
  break;
    

  case 102:
  if (yyn == 102)
    /* "VondaGrammar.y":478  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))); ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))).addFirst(((RTExpression)(yystack.valueAt (3-(1))))); };
  break;
    

  case 103:
  if (yyn == 103)
    /* "VondaGrammar.y":479  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))); ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))).addFirst(((RTExpression)(yystack.valueAt (3-(1))))); };
  break;
    

  case 104:
  if (yyn == 104)
    /* "VondaGrammar.y":483  */ /* lalr1.java:489  */
    {
    yyval = new Type("Array",
                  new ArrayList<Type>(){{ add(new Type((( String )(yystack.valueAt (3-(1)))))); }});
  };
  break;
    

  case 105:
  if (yyn == 105)
    /* "VondaGrammar.y":487  */ /* lalr1.java:489  */
    { yyval = new Type((( String )(yystack.valueAt (1-(1))))); };
  break;
    

  case 106:
  if (yyn == 106)
    /* "VondaGrammar.y":488  */ /* lalr1.java:489  */
    { yyval = new Type((( String )(yystack.valueAt (4-(1)))), ((LinkedList<Type>)(yystack.valueAt (4-(3))))); };
  break;
    

  case 107:
  if (yyn == 107)
    /* "VondaGrammar.y":492  */ /* lalr1.java:489  */
    { yyval = new LinkedList<Type>(){{ add(((Type)(yystack.valueAt (1-(1))))); }}; };
  break;
    

  case 108:
  if (yyn == 108)
    /* "VondaGrammar.y":493  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<Type>)(yystack.valueAt (3-(3)))); ((LinkedList<Type>)(yystack.valueAt (3-(3)))).addFirst(((Type)(yystack.valueAt (3-(1))))); };
  break;
    

  case 109:
  if (yyn == 109)
    /* "VondaGrammar.y":497  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "||"), (yyloc));
  };
  break;
    

  case 110:
  if (yyn == 110)
    /* "VondaGrammar.y":500  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 111:
  if (yyn == 111)
    /* "VondaGrammar.y":504  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "&&"), (yyloc));
  };
  break;
    

  case 112:
  if (yyn == 112)
    /* "VondaGrammar.y":507  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 113:
  if (yyn == 113)
    /* "VondaGrammar.y":511  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 114:
  if (yyn == 114)
    /* "VondaGrammar.y":512  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "|"), (yyloc));
  };
  break;
    

  case 115:
  if (yyn == 115)
    /* "VondaGrammar.y":518  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 116:
  if (yyn == 116)
    /* "VondaGrammar.y":519  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "^"), (yyloc));
  };
  break;
    

  case 117:
  if (yyn == 117)
    /* "VondaGrammar.y":525  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 118:
  if (yyn == 118)
    /* "VondaGrammar.y":526  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "&"), (yyloc));
  };
  break;
    

  case 119:
  if (yyn == 119)
    /* "VondaGrammar.y":532  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 120:
  if (yyn == 120)
    /* "VondaGrammar.y":533  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "=="), (yyloc));
  };
  break;
    

  case 121:
  if (yyn == 121)
    /* "VondaGrammar.y":536  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "!="), (yyloc));
  };
  break;
    

  case 122:
  if (yyn == 122)
    /* "VondaGrammar.y":542  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 123:
  if (yyn == 123)
    /* "VondaGrammar.y":543  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "<"), (yyloc));
  };
  break;
    

  case 124:
  if (yyn == 124)
    /* "VondaGrammar.y":546  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), ">"), (yyloc));
  };
  break;
    

  case 125:
  if (yyn == 125)
    /* "VondaGrammar.y":549  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), ">="), (yyloc));
  };
  break;
    

  case 126:
  if (yyn == 126)
    /* "VondaGrammar.y":552  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "<="), (yyloc));
  };
  break;
    

  case 127:
  if (yyn == 127)
    /* "VondaGrammar.y":558  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 128:
  if (yyn == 128)
    /* "VondaGrammar.y":559  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "+"), (yyloc));
  };
  break;
    

  case 129:
  if (yyn == 129)
    /* "VondaGrammar.y":562  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "-"), (yyloc));
  };
  break;
    

  case 130:
  if (yyn == 130)
    /* "VondaGrammar.y":568  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 131:
  if (yyn == 131)
    /* "VondaGrammar.y":569  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "*"), (yyloc));
  };
  break;
    

  case 132:
  if (yyn == 132)
    /* "VondaGrammar.y":572  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "/"), (yyloc));
  };
  break;
    

  case 133:
  if (yyn == 133)
    /* "VondaGrammar.y":575  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "%"), (yyloc));
  };
  break;
    

  case 134:
  if (yyn == 134)
    /* "VondaGrammar.y":581  */ /* lalr1.java:489  */
    {
    yyval = createPlusMinus(((RTExpression)(yystack.valueAt (2-(2)))), "+", (yyloc));
  };
  break;
    

  case 135:
  if (yyn == 135)
    /* "VondaGrammar.y":584  */ /* lalr1.java:489  */
    {
    yyval = createPlusMinus(((RTExpression)(yystack.valueAt (2-(2)))), "-", (yyloc));
  };
  break;
    

  case 136:
  if (yyn == 136)
    /* "VondaGrammar.y":587  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2-(2)))), null, "+"), (yyloc)); };
  break;
    

  case 137:
  if (yyn == 137)
    /* "VondaGrammar.y":588  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2-(2)))), null, "-"), (yyloc)); };
  break;
    

  case 138:
  if (yyn == 138)
    /* "VondaGrammar.y":589  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 139:
  if (yyn == 139)
    /* "VondaGrammar.y":593  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 140:
  if (yyn == 140)
    /* "VondaGrammar.y":594  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpCast(((Type)(yystack.valueAt (4-(2)))), ((RTExpression)(yystack.valueAt (4-(4))))), (yyloc)); };
  break;
    

  case 141:
  if (yyn == 141)
    /* "VondaGrammar.y":599  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 142:
  if (yyn == 142)
    /* "VondaGrammar.y":600  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (2-(2)))), null, "!"), (yyloc)); };
  break;
    

  case 143:
  if (yyn == 143)
    /* "VondaGrammar.y":601  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2-(2)))), null, "~"), (yyloc)); };
  break;
    

  case 144:
  if (yyn == 144)
    /* "VondaGrammar.y":605  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 145:
  if (yyn == 145)
    /* "VondaGrammar.y":606  */ /* lalr1.java:489  */
    {
    yyval = createPlusMinus(((RTExpression)(yystack.valueAt (2-(1)))), "+", (yyloc));
  };
  break;
    

  case 146:
  if (yyn == 146)
    /* "VondaGrammar.y":609  */ /* lalr1.java:489  */
    {
    yyval = createPlusMinus(((RTExpression)(yystack.valueAt (2-(1)))), "-", (yyloc));
  };
  break;
    

  case 147:
  if (yyn == 147)
    /* "VondaGrammar.y":615  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpLiteral("null", "null"), (yyloc)); };
  break;
    

  case 148:
  if (yyn == 148)
    /* "VondaGrammar.y":616  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 149:
  if (yyn == 149)
    /* "VondaGrammar.y":617  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 150:
  if (yyn == 150)
    /* "VondaGrammar.y":621  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpIdentifier((( String )(yystack.valueAt (1-(1))))), (yyloc)); };
  break;
    

  case 151:
  if (yyn == 151)
    /* "VondaGrammar.y":622  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpCast(((Type)(yystack.valueAt (6-(3)))), ((RTExpression)(yystack.valueAt (6-(5))))), (yyloc)); };
  break;
    

  case 152:
  if (yyn == 152)
    /* "VondaGrammar.y":626  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (3-(2)))); ((RTExpression)(yystack.valueAt (3-(2)))).generateParens(); };
  break;
    

  case 153:
  if (yyn == 153)
    /* "VondaGrammar.y":627  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 154:
  if (yyn == 154)
    /* "VondaGrammar.y":631  */ /* lalr1.java:489  */
    { yyval = ((ExpLiteral)(yystack.valueAt (1-(1)))); };
  break;
    

  case 155:
  if (yyn == 155)
    /* "VondaGrammar.y":632  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 156:
  if (yyn == 156)
    /* "VondaGrammar.y":633  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 157:
  if (yyn == 157)
    /* "VondaGrammar.y":634  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 158:
  if (yyn == 158)
    /* "VondaGrammar.y":635  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 159:
  if (yyn == 159)
    /* "VondaGrammar.y":639  */ /* lalr1.java:489  */
    { yyval = setPos((( ExpLiteral )(yystack.valueAt (1-(1)))), (yyloc)); };
  break;
    

  case 160:
  if (yyn == 160)
    /* "VondaGrammar.y":640  */ /* lalr1.java:489  */
    { yyval = setPos((( ExpLiteral )(yystack.valueAt (1-(1)))), (yyloc)); };
  break;
    

  case 161:
  if (yyn == 161)
    /* "VondaGrammar.y":641  */ /* lalr1.java:489  */
    { yyval = setPos((( ExpLiteral )(yystack.valueAt (1-(1)))), (yyloc)); };
  break;
    

  case 162:
  if (yyn == 162)
    /* "VondaGrammar.y":642  */ /* lalr1.java:489  */
    { yyval = setPos((( ExpLiteral )(yystack.valueAt (1-(1)))), (yyloc)); };
  break;
    

  case 163:
  if (yyn == 163)
    /* "VondaGrammar.y":646  */ /* lalr1.java:489  */
    {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (4-(1))))), yystack.locationAt (4-(1)));
    yyval = setPos(new ExpArrayAccess(var, ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc));
  };
  break;
    

  case 164:
  if (yyn == 164)
    /* "VondaGrammar.y":650  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpArrayAccess(((RTExpression)(yystack.valueAt (4-(1)))), ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc)); };
  break;
    

  case 165:
  if (yyn == 165)
    /* "VondaGrammar.y":654  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpConditional(((RTExpression)(yystack.valueAt (5-(1)))), ((RTExpression)(yystack.valueAt (5-(3)))), ((RTExpression)(yystack.valueAt (5-(5))))), (yyloc)); };
  break;
    

  case 166:
  if (yyn == 166)
    /* "VondaGrammar.y":655  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 167:
  if (yyn == 167)
    /* "VondaGrammar.y":661  */ /* lalr1.java:489  */
    {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (2-(1))))), yystack.locationAt (2-(1)));
    yyval = setPos(new ExpAssignment(var, ((RTExpression)(yystack.valueAt (2-(2))))), (yyloc));
  };
  break;
    

  case 168:
  if (yyn == 168)
    /* "VondaGrammar.y":665  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpAssignment(((RTExpression)(yystack.valueAt (2-(1)))), ((RTExpression)(yystack.valueAt (2-(2))))), (yyloc)); };
  break;
    

  case 169:
  if (yyn == 169)
    /* "VondaGrammar.y":666  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpAssignment(((RTExpression)(yystack.valueAt (2-(1)))), ((RTExpression)(yystack.valueAt (2-(2))))), (yyloc)); };
  break;
    

  case 170:
  if (yyn == 170)
    /* "VondaGrammar.y":670  */ /* lalr1.java:489  */
    {
    //List<String> repr = new ArrayList<>($2.size());
    //for (int i = 0; i < $2.size(); ++i) repr.add("");
    //$$ = setPos(new ExpFieldAccess($2, repr), @$); $2.addFirst($1);
    yyval = setPos(new ExpFieldAccess(((LinkedList<RTExpression>)(yystack.valueAt (2-(2))))), (yyloc)); ((LinkedList<RTExpression>)(yystack.valueAt (2-(2)))).addFirst(((RTExpression)(yystack.valueAt (2-(1)))));
  };
  break;
    

  case 171:
  if (yyn == 171)
    /* "VondaGrammar.y":676  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpFieldAccess(((LinkedList<RTExpression>)(yystack.valueAt (2-(2))))), (yyloc)); ((LinkedList<RTExpression>)(yystack.valueAt (2-(2)))).addFirst(setPos((( ExpLiteral )(yystack.valueAt (2-(1)))), (yyloc)));
  };
  break;
    

  case 172:
  if (yyn == 172)
    /* "VondaGrammar.y":679  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpFieldAccess(((LinkedList<RTExpression>)(yystack.valueAt (2-(2))))), (yyloc)); ((LinkedList<RTExpression>)(yystack.valueAt (2-(2)))).addFirst(((RTExpression)(yystack.valueAt (2-(1))))); };
  break;
    

  case 173:
  if (yyn == 173)
    /* "VondaGrammar.y":684  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))); ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))).addFirst(((RTExpression)(yystack.valueAt (3-(2))))); };
  break;
    

  case 174:
  if (yyn == 174)
    /* "VondaGrammar.y":685  */ /* lalr1.java:489  */
    { yyval = new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (2-(2))))); }}; };
  break;
    

  case 175:
  if (yyn == 175)
    /* "VondaGrammar.y":689  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpIdentifier((( String )(yystack.valueAt (1-(1))))), (yyloc)); };
  break;
    

  case 176:
  if (yyn == 176)
    /* "VondaGrammar.y":690  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 177:
  if (yyn == 177)
    /* "VondaGrammar.y":691  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (3-(2)))); };
  break;
    

  case 178:
  if (yyn == 178)
    /* "VondaGrammar.y":695  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpNew(new Type((( String )(yystack.valueAt (2-(2)))))), (yyloc)); };
  break;
    

  case 179:
  if (yyn == 179)
    /* "VondaGrammar.y":696  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpNew(new Type((( String )(yystack.valueAt (4-(2))))), new LinkedList<>()), (yyloc));
  };
  break;
    

  case 180:
  if (yyn == 180)
    /* "VondaGrammar.y":699  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpNew(new Type((( String )(yystack.valueAt (5-(2))))), ((LinkedList<RTExpression>)(yystack.valueAt (5-(4))))), (yyloc));
  };
  break;
    

  case 181:
  if (yyn == 181)
    /* "VondaGrammar.y":702  */ /* lalr1.java:489  */
    {
    List<Type> sub = new ArrayList<Type>() {{ add(new Type((( String )(yystack.valueAt (5-(2)))))); }};
    yyval = setPos(new ExpNew(new Type("Array", sub),
                    new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (5-(4))))); }}), (yyloc));
  };
  break;
    

  case 182:
  if (yyn == 182)
    /* "VondaGrammar.y":707  */ /* lalr1.java:489  */
    {
    List<Type> sub = new ArrayList<Type>() {{ add(new Type((( String )(yystack.valueAt (7-(2)))))); }};
    yyval = setPos(new ExpNew(new Type("Array", sub),
                    new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (7-(6))))); }}), (yyloc));
  };
  break;
    

  case 183:
  if (yyn == 183)
    /* "VondaGrammar.y":712  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpNew(new Type((( String )(yystack.valueAt (7-(2)))), ((LinkedList<Type>)(yystack.valueAt (7-(4))))),
                    new LinkedList<>()), (yyloc));
  };
  break;
    

  case 184:
  if (yyn == 184)
    /* "VondaGrammar.y":716  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpNew(new Type((( String )(yystack.valueAt (8-(2)))), ((LinkedList<Type>)(yystack.valueAt (8-(4))))),
                    ((LinkedList<RTExpression>)(yystack.valueAt (8-(7))))), (yyloc));
  };
  break;
    

  case 185:
  if (yyn == 185)
    /* "VondaGrammar.y":723  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpLambda(((LinkedList)(yystack.valueAt (5-(2)))), ((RTExpression)(yystack.valueAt (5-(5))))), (yyloc));
  };
  break;
    

  case 186:
  if (yyn == 186)
    /* "VondaGrammar.y":726  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpLambda(((LinkedList)(yystack.valueAt (5-(2)))), ((StatAbstractBlock)(yystack.valueAt (5-(5))))), (yyloc));
  };
  break;
    

  case 187:
  if (yyn == 187)
    /* "VondaGrammar.y":733  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpDialogueAct(((RTExpression)(yystack.valueAt (6-(2)))), ((RTExpression)(yystack.valueAt (6-(4)))), ((LinkedList<RTExpression>)(yystack.valueAt (6-(5))))), (yyloc));
  };
  break;
    

  case 188:
  if (yyn == 188)
    /* "VondaGrammar.y":739  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (3-(2)))); };
  break;
    

  case 189:
  if (yyn == 189)
    /* "VondaGrammar.y":740  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpIdentifier((( String )(yystack.valueAt (1-(1))))), (yyloc)); };
  break;
    

  case 190:
  if (yyn == 190)
    /* "VondaGrammar.y":741  */ /* lalr1.java:489  */
    { yyval = setPos((( ExpLiteral )(yystack.valueAt (1-(1)))), (yyloc)); };
  break;
    

  case 191:
  if (yyn == 191)
    /* "VondaGrammar.y":742  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpLiteral((( String )(yystack.valueAt (1-(1)))), "String"), (yyloc)); };
  break;
    

  case 192:
  if (yyn == 192)
    /* "VondaGrammar.y":746  */ /* lalr1.java:489  */
    {
    yyval = ((LinkedList<RTExpression>)(yystack.valueAt (5-(5)))); ((LinkedList<RTExpression>)(yystack.valueAt (5-(5)))).addFirst(((RTExpression)(yystack.valueAt (5-(4))))); ((LinkedList<RTExpression>)(yystack.valueAt (5-(5)))).addFirst(((RTExpression)(yystack.valueAt (5-(2)))));
  };
  break;
    

  case 193:
  if (yyn == 193)
    /* "VondaGrammar.y":749  */ /* lalr1.java:489  */
    { yyval = new LinkedList<RTExpression>(); };
  break;
    

  case 194:
  if (yyn == 194)
    /* "VondaGrammar.y":753  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 195:
  if (yyn == 195)
    /* "VondaGrammar.y":754  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 196:
  if (yyn == 196)
    /* "VondaGrammar.y":755  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    


/* "VondaGrammar.java":2074  */ /* lalr1.java:489  */
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

  private static final short yypact_ninf_ = -347;
  private static final short yytable_ninf_ = -151;

  /* YYPACT[STATE-NUM] -- Index in YYTABLE of the portion describing
   STATE-NUM.  */
  private static final short yypact_[] = yypact_init();
  private static final short[] yypact_init()
  {
    return new short[]
    {
     862,    -3,   -18,   -11,   178,    10,    40,   911,    52,    19,
      56,    65,  -347,    69,  -347,  -347,   521,    75,    83,    91,
    1424,  1424,    85,  -347,  -347,   330,  -347,   684,  1259,   118,
     128,   144,    -2,   862,   862,  -347,  -347,  -347,  -347,  -347,
    -347,  -347,  -347,  -347,  -347,   862,   862,   862,  -347,   754,
     123,    85,   119,  -347,  -347,   -10,   131,    26,  -347,   145,
    -347,  -347,  -347,   129,   151,   154,   156,  -347,  -347,  -347,
     167,  -347,    32,   182,   104,   187,    57,  1292,  -347,   163,
    1292,   195,  -347,  1424,  1424,    71,  -347,  1325,  1457,  1457,
    1424,  1424,    85,   -15,   225,   200,   205,   208,   201,    82,
     211,   233,  -347,  -347,  -347,   198,  -347,    85,   119,   215,
    -347,  -347,   215,  -347,   229,  1292,  1292,  1292,   112,   232,
    -347,  -347,   238,    13,  -347,  1292,  1292,   271,   790,   925,
     958,   118,  -347,  -347,  -347,   911,   255,  1325,   257,    79,
     259,  -347,  -347,  -347,  1292,   264,  -347,   118,   862,   862,
    -347,  -347,  -347,  -347,  -347,  -347,  -347,  -347,   -31,  -347,
    1292,  1292,  1292,  -347,  -347,  1292,  1292,  -347,  -347,  -347,
    -347,  -347,  -347,  -347,   267,   -22,  -347,   266,   273,     2,
      27,  1292,   991,   279,   272,  -347,   284,   277,    92,  -347,
    -347,  1292,   210,   278,  -347,  -347,  -347,  -347,  1457,  1292,
    1457,  1457,  1457,  1457,  1457,  1457,  1457,  1457,  1457,  1457,
    1457,  1457,  1457,  1457,  1457,  -347,  -347,  -347,   281,   283,
     285,   282,  -347,  -347,   290,  1292,  -347,    85,   296,   297,
    -347,  1358,  -347,   298,   295,   299,  -347,   303,  1024,  -347,
     300,   293,  -347,  -347,   302,  -347,   312,   311,   128,   306,
    -347,  -347,  -347,   318,   317,   309,   319,   322,   324,   327,
    1292,  -347,  -347,   329,  1292,   332,   333,  1057,   339,    38,
     911,  -347,   341,  1091,  1125,   118,  1457,   225,   347,   200,
     205,   208,   201,    82,    82,   211,   211,   211,   211,   233,
     233,  -347,  -347,  -347,   341,  1292,  1292,   911,   348,  -347,
    -347,  -347,   146,   349,  -347,   -23,  -347,  1391,  1391,  -347,
    -347,   350,   360,   118,  -347,  1457,   118,  -347,   365,   355,
     -35,   351,   375,  -347,  -347,  -347,  -347,  -347,  -347,   368,
    -347,   369,  -347,  1292,   911,   370,  1158,  1292,   377,   410,
    -347,  -347,   373,   376,   371,   372,  -347,  1292,  -347,   379,
     380,  -347,  -347,   318,   399,   383,  -347,  -347,  -347,  1292,
    -347,   381,   385,   128,   390,   118,    67,  -347,   911,   391,
    -347,   911,   911,   393,   394,  -347,   911,  -347,  1292,  -347,
     396,  -347,   341,   341,  -347,  1192,   318,  -347,  -347,    20,
     395,  -347,   405,  -347,  -347,  -347,  -347,   911,  -347,  -347,
     911,   911,  -347,   398,  1225,  -347,  -347,  -347,  -347,  -347,
    -347,   318,   128,   401,  -347,  -347,  -347,  -347,  -347,   403,
     420,   365,  -347,    67,  -347,  -347
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
      10,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,    13,     0,    12,    11,     0,     0,     0,     0,
       0,     0,   159,   160,   162,   105,   161,     0,     0,     0,
       0,     0,     0,    10,    10,    17,    23,    25,    28,    29,
      30,    26,    27,    31,    32,    10,    10,    10,    24,    10,
       0,     0,     0,   153,   154,   155,     0,   156,   158,     0,
      43,    45,    46,     0,     0,     0,     0,    47,    69,    33,
       0,    34,   157,     0,   105,     0,     0,     0,    15,     0,
       0,     0,   147,     0,     0,   150,    41,     0,     0,     0,
       0,     0,   157,   166,   110,   112,   113,   115,   117,   119,
     122,   127,   139,   130,   138,   141,   144,   148,   149,   155,
     194,   195,   156,   196,     0,     0,     0,     0,   150,     0,
     155,   156,     0,     0,   171,     0,     0,     0,     0,     0,
       0,     0,   167,    37,    35,    38,     0,     0,     0,   105,
       0,   190,   191,   189,     0,     0,     1,     0,    10,    10,
       6,     5,     8,     9,     3,    22,     4,   172,     0,   170,
       0,     0,     0,   169,    18,     0,     0,    19,   168,    44,
      65,    66,    67,    68,     0,     0,    73,     0,     0,     0,
     105,     0,     0,     0,     0,    14,     0,     0,   178,   135,
     134,     0,   150,     0,   136,   137,   142,   143,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,   146,   145,    42,     0,   158,
       0,     0,    21,    20,   175,     0,   176,   174,     0,     0,
      40,    87,    98,     0,   101,   100,   104,     0,     0,    77,
     107,     0,    39,    36,     0,   152,     0,     0,     0,     0,
       7,     2,    74,    87,     0,     0,     0,     0,     0,     0,
       0,    70,    75,     0,     0,     0,     0,     0,     0,     0,
       0,    16,     0,     0,     0,     0,     0,   109,     0,   111,
     114,   116,   118,   120,   121,   125,   126,   123,   124,   128,
     129,   131,   132,   133,     0,     0,     0,     0,     0,   173,
      93,    92,   150,     0,    86,     0,    99,     0,     0,   163,
      78,     0,    80,     0,   106,     0,     0,   188,   193,     0,
      88,     0,     0,    71,   164,    95,    94,    97,    96,     0,
      72,     0,    59,     0,     0,     0,     0,     0,     0,    48,
      61,   179,     0,     0,     0,     0,   140,     0,    64,     0,
       0,    50,   177,     0,     0,    89,   103,   102,    79,     0,
     108,   139,     0,     0,     0,     0,     0,    51,     0,     0,
      55,     0,     0,     0,     0,    60,     0,   180,     0,   181,
       0,   165,     0,     0,    90,     0,     0,    81,   151,     0,
       0,   187,     0,    85,    84,    83,    57,     0,    53,    54,
       0,     0,    49,     0,     0,    63,    62,   186,   185,    91,
      76,    87,     0,     0,    56,    52,    58,   182,   183,     0,
       0,   193,   184,     0,   192,    82
    };
  }

/* YYPGOTO[NTERM-NUM].  */
  private static final short yypgoto_[] = yypgoto_init();
  private static final short[] yypgoto_init()
  {
    return new short[]
    {
    -347,   179,  -347,  -347,  -347,    55,    34,  -347,  -268,   314,
    -347,  -347,   342,  -347,  -347,  -347,  -347,  -347,  -347,  -347,
      61,  -347,   209,  -263,   438,    49,  -250,  -346,  -347,   133,
      -5,     0,  -262,  -347,   275,   274,   280,   286,   276,    72,
      81,    97,   -19,   -61,  -347,  -347,  -347,   143,   174,  -347,
    -347,    36,  -347,   345,   252,   -16,  -347,  -347,  -347,   359,
    -229,    62,   474
    };
  }

/* YYDEFGOTO[NTERM-NUM].  */
  private static final short yydefgoto_[] = yydefgoto_init();
  private static final short[] yydefgoto_init()
  {
    return new short[]
    {
      -1,    31,    32,    33,    79,    69,   134,   135,    35,   136,
      36,    37,    38,    39,    40,   182,    41,    42,    43,    44,
      71,    46,   132,   311,    47,   395,   303,   304,    48,    92,
     233,    73,   241,    93,    94,    95,    96,    97,    98,    99,
     100,   101,   102,   103,   104,   105,   106,   107,   108,    53,
      54,   109,   110,   111,   112,   157,   227,   113,   234,    58,
     145,   364,   138
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
      50,   119,   122,   321,   340,  -105,   124,   384,    75,     8,
     342,   252,   198,   345,   353,   177,   253,   355,   131,   318,
     252,   130,   161,   162,    61,   276,   348,   194,   195,   140,
     130,    62,    50,    50,    50,   159,    55,    59,   139,    60,
     409,    70,   130,    55,   262,    50,    50,    50,   147,    50,
     199,   360,    67,   224,   130,    34,   120,   120,   165,   166,
     225,    45,   410,    55,   189,   190,    76,   411,   167,    55,
      55,   196,   197,   264,   155,   123,   183,   177,   130,   130,
     131,    55,    55,    55,   337,    55,    68,   193,    34,    34,
     130,   159,    74,   148,    45,    45,   387,   180,   394,   181,
      34,    34,    34,    77,    34,    78,    45,    45,    45,   393,
      45,    27,   206,   207,   405,   406,    80,   407,   128,   120,
     120,   191,   115,   130,   120,   120,   120,   120,   123,   177,
     116,   240,   131,    49,   390,   208,   209,   244,   117,   273,
      72,   419,   274,    51,   146,   275,   176,   249,    50,    50,
      51,   291,   292,   293,   177,   394,   130,   131,   139,   128,
      72,   420,   191,   158,   141,   142,    49,    49,   143,   160,
      51,    55,   144,   164,    52,   170,    51,    51,    49,    49,
      49,    52,    49,   421,    55,    55,  -105,   169,    51,    51,
      51,   174,    51,   128,   -88,   353,   129,   171,   130,   131,
     172,    52,   173,    34,    34,   185,   186,    52,    52,    45,
      45,   299,   150,   151,    63,   346,    64,    65,    66,    52,
      52,    52,   175,    52,   152,   153,   154,   179,   156,   204,
     205,   305,   215,   216,   120,   188,   120,   120,   120,   120,
     120,   120,   120,   120,   120,   120,   120,   120,   120,   120,
     120,   200,    57,   322,   346,   201,   226,   128,  -105,    57,
     129,   202,   130,   131,   163,   203,   168,   130,    72,   210,
     211,   217,   121,   121,   222,   240,   283,   284,    51,    57,
     223,    49,    49,   178,    10,    57,    57,   285,   286,   287,
     288,    51,    51,   212,   213,   214,   361,    57,    57,    57,
     243,    57,   356,   357,   339,   245,    55,   289,   290,    52,
     246,   248,   120,   240,   260,   261,   362,   236,   163,   269,
     270,   168,    52,    52,   271,   272,   276,   250,   251,   294,
     297,   351,   295,    55,   296,   121,   121,   128,   300,   301,
     121,   121,   121,   121,   307,    56,   306,   314,   308,   313,
     315,   120,    56,   322,   309,   316,   317,   319,   320,   323,
     324,   325,   125,   126,   326,   392,   327,   254,   370,   328,
      55,   330,    56,  -150,   332,   333,   127,   128,    56,    56,
     129,   336,   130,   131,   254,    27,   322,    57,   263,   265,
      56,    56,    56,   347,    56,   358,   352,   354,   365,   366,
      57,    57,   396,    72,    55,   398,   399,    55,    55,   359,
     402,   322,    55,    51,   363,   355,   367,   368,   371,   375,
     376,   377,   379,   378,   385,   389,   380,   382,   383,   388,
      72,   414,   386,    55,   415,   416,    55,    55,   391,   397,
      51,   400,   401,   404,    52,   413,   417,   412,   411,   242,
     121,   422,   121,   121,   121,   121,   121,   121,   121,   121,
     121,   121,   121,   121,   121,   121,   121,    72,   423,   230,
     149,    52,   425,   277,   279,   219,     0,    51,   338,   282,
      56,   280,     0,   424,     0,     0,     0,     0,   281,     0,
     114,     0,     0,    56,    56,     0,     0,     0,     0,     0,
       0,    72,     0,     0,    72,    72,     0,     0,    52,    72,
       0,    51,     0,     0,    51,    51,     0,     0,     0,    51,
       0,     0,    57,     0,     0,     0,     0,     0,   121,     0,
      72,     0,     0,    72,    72,     0,    81,    82,     0,     0,
      51,     0,    52,    51,    51,    52,    52,     0,     0,    57,
      52,   184,     0,     0,   187,    83,    84,    22,     0,    23,
      24,    85,    26,    86,     0,     0,     0,   121,    87,     0,
       0,    52,     0,     0,    52,    52,     0,     0,     0,    88,
      89,     0,     0,     0,    90,    91,    57,    30,     0,   218,
     220,   221,     0,     0,     0,     0,     0,     0,     0,   228,
     229,     0,   235,   237,   239,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,    56,     0,     0,   247,     0,
      57,     0,     0,    57,    57,     0,     0,     0,    57,     0,
       0,     0,     0,     0,   255,   256,   257,     0,     0,   258,
     259,     0,    56,     0,     0,     0,     0,     0,     0,    57,
       0,     0,    57,    57,     0,   266,   268,     0,     0,     0,
       0,     0,     0,     0,     0,   237,     0,     0,     0,     0,
       0,     0,     0,   278,     0,     0,     0,     0,     0,    56,
       0,     0,     0,     0,     0,     0,     0,     1,     2,     3,
       4,     5,     6,     7,     0,     8,     9,    10,     0,   298,
       0,     0,    13,     0,     0,    16,    17,    18,    19,     0,
       0,     0,   312,    56,     0,     0,    56,    56,    20,    21,
      22,    56,    23,    24,    25,    26,     0,     0,    27,   133,
       0,    28,     0,     0,   329,     0,     0,     0,   331,     0,
       0,   335,    56,     0,     0,    56,    56,   312,   344,     0,
      30,     0,     0,     0,     0,     0,     0,     1,     2,     3,
       4,     5,     6,     7,     0,     8,     9,    10,    11,   349,
     350,    12,    13,    14,    15,    16,    17,    18,    19,     0,
       0,   235,   235,     0,     0,     0,     0,     0,    20,    21,
      22,     0,    23,    24,    25,    26,   155,   123,    27,     0,
       0,    28,     0,     0,    29,    81,    82,   369,     0,     0,
     373,   374,     0,     0,     0,     0,     0,     0,     0,     0,
      30,   381,     0,     0,    83,    84,    22,     0,    23,    24,
      85,    26,     0,   312,     0,     0,     0,   231,   232,     0,
       0,     0,     0,     0,     0,     0,     0,     0,    88,    89,
       0,     0,   403,    90,    91,     0,    30,     0,     0,   408,
       0,     0,     0,     0,     0,     1,     2,     3,     4,     5,
       6,     7,     0,     8,     9,    10,    11,     0,   312,    12,
      13,    14,    15,    16,    17,    18,    19,     0,     0,     0,
       0,     0,     0,     0,     0,     0,    20,    21,    22,     0,
      23,    24,    25,    26,     0,     0,    27,     0,     0,    28,
       0,     0,    29,     0,     1,     2,     3,     4,     5,     6,
       7,     0,     8,     9,    10,     0,     0,     0,    30,    13,
       0,     0,    16,    17,    18,    19,     0,     0,     0,     0,
      81,    82,     0,     0,     0,    20,    21,    22,     0,    23,
      24,    25,    26,     0,     0,    27,     0,     0,    28,    83,
      84,    22,     0,    23,    24,    85,    26,     0,     0,     0,
       0,     0,    87,    81,    82,     0,   236,    30,     0,     0,
       0,     0,     0,    88,    89,     0,     0,     0,    90,    91,
       0,    30,    83,    84,    22,     0,    23,    24,    85,    26,
       0,     0,   238,     0,     0,    87,    81,    82,     0,     0,
       0,     0,     0,     0,     0,     0,    88,    89,     0,     0,
       0,    90,    91,     0,    30,    83,    84,    22,     0,    23,
      24,    85,    26,   267,     0,     0,     0,     0,    87,    81,
      82,     0,     0,     0,     0,     0,     0,     0,     0,    88,
      89,     0,     0,     0,    90,    91,     0,    30,    83,    84,
      22,     0,    23,    24,    85,    26,     0,     0,     0,   310,
       0,    87,    81,    82,     0,     0,     0,     0,     0,     0,
       0,     0,    88,    89,     0,     0,     0,    90,    91,     0,
      30,    83,    84,    22,     0,    23,    24,    85,    26,     0,
       0,     0,     0,     0,    87,   334,    81,    82,     0,     0,
       0,     0,     0,     0,     0,    88,    89,     0,     0,     0,
      90,    91,     0,    30,     0,    83,    84,    22,     0,    23,
      24,    85,    26,     0,     0,     0,     0,     0,    87,   341,
      81,    82,     0,     0,     0,     0,     0,     0,     0,    88,
      89,     0,     0,     0,    90,    91,     0,    30,     0,    83,
      84,    22,     0,    23,    24,    85,    26,     0,     0,     0,
       0,     0,    87,    81,    82,     0,   343,     0,     0,     0,
       0,     0,     0,    88,    89,     0,     0,     0,    90,    91,
       0,    30,    83,    84,    22,     0,    23,    24,    85,    26,
       0,     0,     0,     0,     0,    87,   372,    81,    82,     0,
       0,     0,     0,     0,     0,     0,    88,    89,     0,     0,
       0,    90,    91,     0,    30,     0,    83,    84,    22,     0,
      23,    24,    85,    26,     0,     0,    27,     0,     0,    87,
      81,    82,     0,     0,     0,     0,     0,     0,     0,     0,
      88,    89,     0,     0,     0,    90,    91,     0,    30,    83,
      84,    22,     0,    23,    24,    85,    26,     0,     0,     0,
       0,     0,    87,   418,    81,    82,     0,     0,     0,     0,
       0,     0,     0,    88,    89,     0,     0,     0,    90,    91,
       0,    30,     0,    83,    84,    22,     0,    23,    24,    85,
      26,     0,     0,     0,     0,     0,   137,    81,    82,     0,
       0,     0,     0,     0,     0,     0,     0,    88,    89,     0,
       0,     0,    90,    91,     0,    30,    83,    84,    22,     0,
      23,    24,    85,    26,     0,     0,     0,     0,     0,    87,
      81,    82,     0,     0,     0,     0,     0,     0,     0,     0,
      88,    89,     0,     0,     0,    90,    91,     0,    30,    83,
      84,    22,     0,    23,    24,   192,    26,     0,     0,     0,
       0,     0,   137,    81,    82,     0,     0,     0,     0,     0,
       0,     0,     0,    88,    89,     0,     0,     0,    90,    91,
       0,    30,    83,    84,    22,     0,    23,    24,   302,    26,
       0,     0,     0,     0,     0,   137,    81,    82,     0,     0,
       0,     0,     0,     0,     0,     0,    88,    89,     0,     0,
       0,    90,    91,     0,    30,    83,    84,    22,     0,    23,
      24,    85,    26,     0,     0,     0,     0,     0,   231,     0,
      82,     0,     0,     0,     0,     0,     0,     0,     0,    88,
      89,     0,     0,     0,    90,    91,     0,    30,    83,    84,
      22,     0,    23,    24,   118,    26,     0,     0,     0,     0,
       0,    28,     0,    82,     0,     0,     0,     0,     0,     0,
       0,     0,    88,    89,     0,     0,     0,    90,    91,     0,
      30,    83,    84,    22,     0,    23,    24,   118,    26,     0,
       0,     0,     0,     0,    87,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,    88,    89,     0,     0,     0,
      90,    91,     0,    30
    };
  }

private static final short yycheck_[] = yycheck_init();
  private static final short[] yycheck_init()
  {
    return new short[]
    {
       0,    20,    21,   253,   272,    40,    22,   353,     8,    11,
     273,    42,    27,   275,    49,    50,    47,    40,    53,   248,
      42,    52,    32,    33,    42,    48,   294,    88,    89,    29,
      52,    42,    32,    33,    34,    51,     0,    40,    40,    42,
     386,     7,    52,     7,    42,    45,    46,    47,    50,    49,
      65,   313,    42,    40,    52,     0,    20,    21,    32,    33,
      47,     0,    42,    27,    83,    84,    47,    47,    42,    33,
      34,    90,    91,    46,    42,    43,    76,    50,    52,    52,
      53,    45,    46,    47,    46,    49,    46,    87,    33,    34,
      52,   107,    40,    32,    33,    34,   359,    40,   366,    42,
      45,    46,    47,    47,    49,    40,    45,    46,    47,    42,
      49,    44,    30,    31,   382,   383,    47,   385,    47,    83,
      84,    50,    47,    52,    88,    89,    90,    91,    43,    50,
      47,   131,    53,     0,   363,    53,    54,   137,    47,    47,
       7,   404,    50,     0,     0,    53,    42,   147,   148,   149,
       7,   212,   213,   214,    50,   423,    52,    53,    40,    47,
      27,   411,    50,    40,    36,    37,    33,    34,    40,    50,
      27,   135,    44,    42,     0,    46,    33,    34,    45,    46,
      47,     7,    49,   412,   148,   149,    40,    42,    45,    46,
      47,    24,    49,    47,    48,    49,    50,    46,    52,    53,
      46,    27,    46,   148,   149,    42,    43,    33,    34,   148,
     149,   227,    33,    34,    36,   276,    38,    39,    40,    45,
      46,    47,    40,    49,    45,    46,    47,    40,    49,    28,
      29,   231,    34,    35,   198,    40,   200,   201,   202,   203,
     204,   205,   206,   207,   208,   209,   210,   211,   212,   213,
     214,    26,     0,   253,   315,    55,   123,    47,    48,     7,
      50,    56,    52,    53,    55,    57,    57,    52,   135,    58,
      59,    42,    20,    21,    42,   275,   204,   205,   135,    27,
      42,   148,   149,    74,    13,    33,    34,   206,   207,   208,
     209,   148,   149,    60,    61,    62,   315,    45,    46,    47,
      45,    49,   307,   308,   270,    48,   270,   210,   211,   135,
      51,    47,   276,   313,    47,    42,   316,    51,   109,    40,
      48,   112,   148,   149,    40,    48,    48,   148,   149,    48,
      48,   297,    49,   297,    49,    83,    84,    47,    42,    42,
      88,    89,    90,    91,    49,     0,    48,    54,    49,    49,
      48,   315,     7,   353,    51,    43,    45,    51,    40,    42,
      51,    42,    32,    33,    42,   365,    42,   158,   334,    42,
     334,    42,    27,    43,    42,    42,    46,    47,    33,    34,
      50,    42,    52,    53,   175,    44,   386,   135,   179,   180,
      45,    46,    47,    46,    49,    45,    48,    48,    43,    48,
     148,   149,   368,   270,   368,   371,   372,   371,   372,    49,
     376,   411,   376,   270,    49,    40,    48,    48,    48,    42,
      10,    48,    51,    47,    25,    40,    54,    48,    48,    48,
     297,   397,    49,   397,   400,   401,   400,   401,    48,    48,
     297,    48,    48,    47,   270,    40,    48,    52,    47,   135,
     198,    48,   200,   201,   202,   203,   204,   205,   206,   207,
     208,   209,   210,   211,   212,   213,   214,   334,    48,   127,
      32,   297,   423,   198,   200,   116,    -1,   334,   269,   203,
     135,   201,    -1,   421,    -1,    -1,    -1,    -1,   202,    -1,
      16,    -1,    -1,   148,   149,    -1,    -1,    -1,    -1,    -1,
      -1,   368,    -1,    -1,   371,   372,    -1,    -1,   334,   376,
      -1,   368,    -1,    -1,   371,   372,    -1,    -1,    -1,   376,
      -1,    -1,   270,    -1,    -1,    -1,    -1,    -1,   276,    -1,
     397,    -1,    -1,   400,   401,    -1,    15,    16,    -1,    -1,
     397,    -1,   368,   400,   401,   371,   372,    -1,    -1,   297,
     376,    77,    -1,    -1,    80,    34,    35,    36,    -1,    38,
      39,    40,    41,    42,    -1,    -1,    -1,   315,    47,    -1,
      -1,   397,    -1,    -1,   400,   401,    -1,    -1,    -1,    58,
      59,    -1,    -1,    -1,    63,    64,   334,    66,    -1,   115,
     116,   117,    -1,    -1,    -1,    -1,    -1,    -1,    -1,   125,
     126,    -1,   128,   129,   130,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,   270,    -1,    -1,   144,    -1,
     368,    -1,    -1,   371,   372,    -1,    -1,    -1,   376,    -1,
      -1,    -1,    -1,    -1,   160,   161,   162,    -1,    -1,   165,
     166,    -1,   297,    -1,    -1,    -1,    -1,    -1,    -1,   397,
      -1,    -1,   400,   401,    -1,   181,   182,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,   191,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,   199,    -1,    -1,    -1,    -1,    -1,   334,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,     3,     4,     5,
       6,     7,     8,     9,    -1,    11,    12,    13,    -1,   225,
      -1,    -1,    18,    -1,    -1,    21,    22,    23,    24,    -1,
      -1,    -1,   238,   368,    -1,    -1,   371,   372,    34,    35,
      36,   376,    38,    39,    40,    41,    -1,    -1,    44,    45,
      -1,    47,    -1,    -1,   260,    -1,    -1,    -1,   264,    -1,
      -1,   267,   397,    -1,    -1,   400,   401,   273,   274,    -1,
      66,    -1,    -1,    -1,    -1,    -1,    -1,     3,     4,     5,
       6,     7,     8,     9,    -1,    11,    12,    13,    14,   295,
     296,    17,    18,    19,    20,    21,    22,    23,    24,    -1,
      -1,   307,   308,    -1,    -1,    -1,    -1,    -1,    34,    35,
      36,    -1,    38,    39,    40,    41,    42,    43,    44,    -1,
      -1,    47,    -1,    -1,    50,    15,    16,   333,    -1,    -1,
     336,   337,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      66,   347,    -1,    -1,    34,    35,    36,    -1,    38,    39,
      40,    41,    -1,   359,    -1,    -1,    -1,    47,    48,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    58,    59,
      -1,    -1,   378,    63,    64,    -1,    66,    -1,    -1,   385,
      -1,    -1,    -1,    -1,    -1,     3,     4,     5,     6,     7,
       8,     9,    -1,    11,    12,    13,    14,    -1,   404,    17,
      18,    19,    20,    21,    22,    23,    24,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    34,    35,    36,    -1,
      38,    39,    40,    41,    -1,    -1,    44,    -1,    -1,    47,
      -1,    -1,    50,    -1,     3,     4,     5,     6,     7,     8,
       9,    -1,    11,    12,    13,    -1,    -1,    -1,    66,    18,
      -1,    -1,    21,    22,    23,    24,    -1,    -1,    -1,    -1,
      15,    16,    -1,    -1,    -1,    34,    35,    36,    -1,    38,
      39,    40,    41,    -1,    -1,    44,    -1,    -1,    47,    34,
      35,    36,    -1,    38,    39,    40,    41,    -1,    -1,    -1,
      -1,    -1,    47,    15,    16,    -1,    51,    66,    -1,    -1,
      -1,    -1,    -1,    58,    59,    -1,    -1,    -1,    63,    64,
      -1,    66,    34,    35,    36,    -1,    38,    39,    40,    41,
      -1,    -1,    44,    -1,    -1,    47,    15,    16,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    58,    59,    -1,    -1,
      -1,    63,    64,    -1,    66,    34,    35,    36,    -1,    38,
      39,    40,    41,    42,    -1,    -1,    -1,    -1,    47,    15,
      16,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    58,
      59,    -1,    -1,    -1,    63,    64,    -1,    66,    34,    35,
      36,    -1,    38,    39,    40,    41,    -1,    -1,    -1,    45,
      -1,    47,    15,    16,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    58,    59,    -1,    -1,    -1,    63,    64,    -1,
      66,    34,    35,    36,    -1,    38,    39,    40,    41,    -1,
      -1,    -1,    -1,    -1,    47,    48,    15,    16,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    58,    59,    -1,    -1,    -1,
      63,    64,    -1,    66,    -1,    34,    35,    36,    -1,    38,
      39,    40,    41,    -1,    -1,    -1,    -1,    -1,    47,    48,
      15,    16,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    58,
      59,    -1,    -1,    -1,    63,    64,    -1,    66,    -1,    34,
      35,    36,    -1,    38,    39,    40,    41,    -1,    -1,    -1,
      -1,    -1,    47,    15,    16,    -1,    51,    -1,    -1,    -1,
      -1,    -1,    -1,    58,    59,    -1,    -1,    -1,    63,    64,
      -1,    66,    34,    35,    36,    -1,    38,    39,    40,    41,
      -1,    -1,    -1,    -1,    -1,    47,    48,    15,    16,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    58,    59,    -1,    -1,
      -1,    63,    64,    -1,    66,    -1,    34,    35,    36,    -1,
      38,    39,    40,    41,    -1,    -1,    44,    -1,    -1,    47,
      15,    16,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      58,    59,    -1,    -1,    -1,    63,    64,    -1,    66,    34,
      35,    36,    -1,    38,    39,    40,    41,    -1,    -1,    -1,
      -1,    -1,    47,    48,    15,    16,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    58,    59,    -1,    -1,    -1,    63,    64,
      -1,    66,    -1,    34,    35,    36,    -1,    38,    39,    40,
      41,    -1,    -1,    -1,    -1,    -1,    47,    15,    16,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    58,    59,    -1,
      -1,    -1,    63,    64,    -1,    66,    34,    35,    36,    -1,
      38,    39,    40,    41,    -1,    -1,    -1,    -1,    -1,    47,
      15,    16,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      58,    59,    -1,    -1,    -1,    63,    64,    -1,    66,    34,
      35,    36,    -1,    38,    39,    40,    41,    -1,    -1,    -1,
      -1,    -1,    47,    15,    16,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    58,    59,    -1,    -1,    -1,    63,    64,
      -1,    66,    34,    35,    36,    -1,    38,    39,    40,    41,
      -1,    -1,    -1,    -1,    -1,    47,    15,    16,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    58,    59,    -1,    -1,
      -1,    63,    64,    -1,    66,    34,    35,    36,    -1,    38,
      39,    40,    41,    -1,    -1,    -1,    -1,    -1,    47,    -1,
      16,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    58,
      59,    -1,    -1,    -1,    63,    64,    -1,    66,    34,    35,
      36,    -1,    38,    39,    40,    41,    -1,    -1,    -1,    -1,
      -1,    47,    -1,    16,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    58,    59,    -1,    -1,    -1,    63,    64,    -1,
      66,    34,    35,    36,    -1,    38,    39,    40,    41,    -1,
      -1,    -1,    -1,    -1,    47,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    58,    59,    -1,    -1,    -1,
      63,    64,    -1,    66
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
      13,    14,    17,    18,    19,    20,    21,    22,    23,    24,
      34,    35,    36,    38,    39,    40,    41,    44,    47,    50,
      66,    68,    69,    70,    72,    75,    77,    78,    79,    80,
      81,    83,    84,    85,    86,    87,    88,    91,    95,    96,
      98,   114,   115,   116,   117,   118,   120,   121,   126,    40,
      42,    42,    42,    36,    38,    39,    40,    42,    46,    72,
      73,    87,    96,    98,    40,    98,    47,    47,    40,    71,
      47,    15,    16,    34,    35,    40,    42,    47,    58,    59,
      63,    64,    96,   100,   101,   102,   103,   104,   105,   106,
     107,   108,   109,   110,   111,   112,   113,   114,   115,   118,
     119,   120,   121,   124,   129,    47,    47,    47,    40,   109,
     118,   121,   109,    43,   122,    32,    33,    46,    47,    50,
      52,    53,    89,    45,    73,    74,    76,    47,   129,    40,
      98,    36,    37,    40,    44,   127,     0,    50,    87,    91,
      68,    68,    68,    68,    68,    42,    68,   122,    40,   122,
      50,    32,    33,    89,    42,    32,    33,    42,    89,    42,
      46,    46,    46,    46,    24,    40,    42,    50,    89,    40,
      40,    42,    82,    98,   129,    42,    43,   129,    40,   109,
     109,    50,    40,    98,   110,   110,   109,   109,    27,    65,
      26,    55,    56,    57,    28,    29,    30,    31,    53,    54,
      58,    59,    60,    61,    62,    34,    35,    42,   129,   126,
     129,   129,    42,    42,    40,    47,    96,   123,   129,   129,
      79,    47,    48,    97,   125,   129,    51,   129,    44,   129,
      98,    99,    76,    45,    98,    48,    51,   129,    47,    98,
      68,    68,    42,    47,    89,   129,   129,   129,   129,   129,
      47,    42,    42,    89,    46,    89,   129,    42,   129,    40,
      48,    40,    48,    47,    50,    53,    48,   101,   129,   102,
     103,   104,   105,   106,   106,   107,   107,   107,   107,   108,
     108,   110,   110,   110,    48,    49,    49,    48,   129,   122,
      42,    42,    40,    93,    94,    98,    48,    49,    49,    51,
      45,    90,   129,    49,    54,    48,    43,    45,   127,    51,
      40,    93,    98,    42,    51,    42,    42,    42,    42,   129,
      42,   129,    42,    42,    48,   129,    42,    46,    89,    73,
      75,    48,    90,    51,   129,    99,   110,    46,    75,   129,
     129,    73,    48,    49,    48,    40,    97,    97,    45,    49,
      99,   109,    98,    49,   128,    43,    48,    48,    48,   129,
      73,    48,    48,   129,   129,    42,    10,    48,    47,    51,
      54,   129,    48,    48,    94,    25,    49,    90,    48,    40,
     127,    48,    98,    42,    75,    92,    73,    48,    73,    73,
      48,    48,    73,   129,    47,    75,    75,    75,   129,    94,
      42,    47,    52,    40,    73,    73,    73,    48,    48,    90,
      93,   127,    48,    48,   128,    92
    };
  }

/* YYR1[YYN] -- Symbol number of symbol that rule YYN derives.  */
  private static final short yyr1_[] = yyr1_init();
  private static final short[] yyr1_init()
  {
    return new short[]
    {
       0,    67,    68,    68,    68,    68,    68,    68,    68,    68,
      68,    69,    69,    69,    70,    71,    71,    72,    72,    72,
      72,    72,    72,    72,    72,    72,    72,    72,    72,    72,
      72,    72,    72,    73,    73,    74,    75,    75,    76,    76,
      77,    78,    78,    78,    78,    78,    78,    78,    79,    79,
      80,    80,    81,    81,    81,    81,    81,    81,    81,    82,
      82,    83,    84,    84,    85,    86,    86,    86,    86,    86,
      87,    87,    87,    87,    87,    87,    88,    89,    89,    89,
      90,    90,    91,    91,    92,    92,    93,    93,    94,    94,
      94,    94,    95,    95,    95,    95,    95,    95,    96,    96,
      97,    97,    97,    97,    98,    98,    98,    99,    99,   100,
     100,   101,   101,   102,   102,   103,   103,   104,   104,   105,
     105,   105,   106,   106,   106,   106,   106,   107,   107,   107,
     108,   108,   108,   108,   109,   109,   109,   109,   109,   110,
     110,   111,   111,   111,   112,   112,   112,   113,   113,   113,
     114,   114,   115,   115,   116,   116,   116,   116,   116,   117,
     117,   117,   117,   118,   118,   119,   119,   120,   120,   120,
     121,   121,   121,   122,   122,   123,   123,   123,   124,   124,
     124,   124,   124,   124,   124,   125,   125,   126,   127,   127,
     127,   127,   128,   128,   129,   129,   129
    };
  }

/* YYR2[YYN] -- Number of symbols on the right hand side of rule YYN.  */
  private static final byte yyr2_[] = yyr2_init();
  private static final byte[] yyr2_init()
  {
    return new byte[]
    {
       0,     2,     3,     2,     2,     2,     2,     3,     2,     2,
       0,     1,     1,     1,     3,     1,     3,     1,     2,     2,
       3,     3,     2,     1,     1,     1,     1,     1,     1,     1,
       1,     1,     1,     1,     1,     1,     3,     2,     1,     2,
       3,     2,     3,     2,     3,     2,     2,     2,     5,     7,
       5,     6,     8,     7,     7,     6,     8,     7,     8,     3,
       4,     5,     7,     7,     5,     3,     3,     3,     3,     2,
       4,     4,     5,     3,     3,     4,     7,     2,     3,     4,
       1,     3,    10,     6,     1,     1,     1,     0,     1,     2,
       3,     4,     4,     4,     4,     4,     4,     4,     3,     4,
       1,     1,     3,     3,     3,     1,     4,     1,     3,     3,
       1,     3,     1,     1,     3,     1,     3,     1,     3,     1,
       3,     3,     1,     3,     3,     3,     3,     1,     3,     3,
       1,     3,     3,     3,     2,     2,     2,     2,     1,     1,
       4,     1,     2,     2,     1,     2,     2,     1,     1,     1,
       1,     6,     3,     1,     1,     1,     1,     1,     1,     1,
       1,     1,     1,     4,     4,     5,     1,     2,     2,     2,
       2,     2,     2,     3,     2,     1,     1,     3,     2,     4,
       5,     5,     7,     7,     8,     5,     5,     6,     3,     1,
       1,     1,     5,     0,     1,     1,     1
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
      91,    93,    61,    60,    62,   124,    94,    38,    43,    45,
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
  "SWITCH", "TIMEOUT", "WHILE", "ARROW", "ANDAND", "OROR", "EQEQ", "NOTEQ",
  "GTEQ", "LTEQ", "MINUSEQ", "PLUSEQ", "MINUSMINUS", "PLUSPLUS", "STRING",
  "WILDCARD", "INT", "BOOL_LITERAL", "VARIABLE", "OTHER_LITERAL", "';'",
  "'.'", "'{'", "'}'", "':'", "'('", "')'", "','", "'['", "']'", "'='",
  "'<'", "'>'", "'|'", "'^'", "'&'", "'+'", "'-'", "'*'", "'/'", "'%'",
  "'!'", "'~'", "'?'", "'#'", "$accept", "grammar_file", "visibility_spec",
  "imports", "path", "statement_no_def", "statement", "blk_statement",
  "block", "statements", "grammar_rule", "return_statement",
  "if_statement", "while_statement", "for_statement", "var_decl",
  "propose_statement", "timeout_statement", "switch_statement",
  "label_statement", "var_def", "field_def", "assgn_exp",
  "nonempty_exp_list", "method_declaration", "opt_block", "opt_args_list",
  "args_list", "set_operation", "function_call", "nonempty_args_list",
  "type_spec", "type_spec_list", "ConditionalOrExpression",
  "ConditionalAndExpression", "InclusiveOrExpression",
  "ExclusiveOrExpression", "AndExpression", "EqualityExpression",
  "RelationalExpression", "AdditiveExpression", "MultiplicativeExpression",
  "UnaryExpression", "CastExpression", "LogicalUnaryExpression",
  "PostfixExpression", "PrimaryExpression", "NotJustName",
  "ComplexPrimary", "ComplexPrimaryNoParenthesis", "Literal",
  "ArrayAccess", "ConditionalExpression", "assignment", "field_access",
  "field_access_rest", "simple_nofa_exp", "new_exp", "lambda_exp",
  "dialogueact_exp", "da_token", "da_args", "exp", null
    };
  }

  /* YYRLINE[YYN] -- Source line where rule number YYN was defined.  */
  private static final short yyrline_[] = yyrline_init();
  private static final short[] yyrline_init()
  {
    return new short[]
    {
       0,   142,   142,   145,   146,   147,   149,   150,   153,   156,
     159,   163,   164,   165,   169,   177,   178,   182,   183,   184,
     185,   188,   191,   192,   193,   194,   195,   196,   197,   198,
     199,   200,   201,   205,   206,   210,   216,   217,   221,   222,
     226,   230,   231,   232,   233,   234,   235,   236,   240,   241,
     247,   248,   254,   256,   258,   260,   262,   264,   268,   277,
     280,   286,   290,   294,   301,   327,   334,   341,   348,   355,
     364,   367,   370,   373,   376,   379,   385,   392,   393,   396,
     402,   403,   408,   411,   417,   418,   422,   423,   427,   428,
     429,   433,   441,   445,   449,   452,   455,   458,   467,   470,
     476,   477,   478,   479,   483,   487,   488,   492,   493,   497,
     500,   504,   507,   511,   512,   518,   519,   525,   526,   532,
     533,   536,   542,   543,   546,   549,   552,   558,   559,   562,
     568,   569,   572,   575,   581,   584,   587,   588,   589,   593,
     594,   599,   600,   601,   605,   606,   609,   615,   616,   617,
     621,   622,   626,   627,   631,   632,   633,   634,   635,   639,
     640,   641,   642,   646,   650,   654,   655,   661,   665,   666,
     670,   676,   679,   684,   685,   689,   690,   691,   695,   696,
     699,   702,   707,   712,   716,   723,   726,   733,   739,   740,
     741,   742,   746,   749,   753,   754,   755
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
      53,    52,    54,    65,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,    50,     2,    51,    56,     2,     2,     2,     2,     2,
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

  private static final int yylast_ = 1523;
  private static final int yynnts_ = 63;
  private static final int yyempty_ = -2;
  private static final int yyfinal_ = 146;
  private static final int yyterror_ = 1;
  private static final int yyerrcode_ = 256;
  private static final int yyntokens_ = 67;

  private static final int yyuser_token_number_max_ = 296;
  private static final int yyundef_token_ = 2;

/* User implementation code.  */
/* Unqualified %code blocks.  */
/* "VondaGrammar.y":59  */ /* lalr1.java:1066  */

  private List<RudiTree> _statements = new LinkedList<>();

  //private GrammarFile _result = new GrammarFile(_statements);

  public List<RudiTree> getResult() { return _statements; }

  public <T extends RudiTree> T setPos(T rt, Location l) {
    return setPos(rt, l, l) ;
  }

  public <T extends RudiTree> T setPos(T rt, Location start, Location end) {
    rt.location = new de.dfki.mlt.rudimant.common.Location(start.begin, end.end);
    return rt;
  }

  // if the left part is a variable, this must be transformed to StatVarDef
  private RudiTree getAssignmentStat(RTExpression assign) {
    assert(assign instanceof ExpAssignment);
    if (((ExpAssignment)assign).leftIsVariable()) {
      return new StatVarDef(false, Type.getNoType(), assign);
    }
    return new StatExpression(assign);
  }

  // generic method
  private ExpAssignment createPlusMinus(RTExpression variable, String plusOrMinus,
                                         Location loc) {
    ExpLiteral es = setPos(new ExpLiteral("1", "int"), loc);
    ExpArithmetic ar = setPos(new ExpArithmetic(variable, es, plusOrMinus), loc);
    ExpAssignment ass = setPos(new ExpAssignment(variable, ar), loc);
    return ass;
  }

/* "VondaGrammar.java":3272  */ /* lalr1.java:1066  */

}

