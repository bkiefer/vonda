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
    /* "VondaGrammar.y":140  */ /* lalr1.java:489  */
    {
    yyval = ((LinkedList<RudiTree>)(yystack.valueAt (3-(3)))); ((StatMethodDeclaration)(yystack.valueAt (3-(2)))).setVisibility(((String)(yystack.valueAt (3-(1))))); ((LinkedList<RudiTree>)(yystack.valueAt (3-(3)))).addFirst(((StatMethodDeclaration)(yystack.valueAt (3-(2)))));
  };
  break;
    

  case 3:
  if (yyn == 3)
    /* "VondaGrammar.y":143  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))); ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))).addFirst(((StatMethodDeclaration)(yystack.valueAt (2-(1))))); };
  break;
    

  case 4:
  if (yyn == 4)
    /* "VondaGrammar.y":144  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))); ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))).addFirst(((RTExpression)(yystack.valueAt (2-(1))))); };
  break;
    

  case 5:
  if (yyn == 5)
    /* "VondaGrammar.y":145  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))); ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))).addFirst(((RTStatement)(yystack.valueAt (2-(1))))); };
  break;
    

  case 6:
  if (yyn == 6)
    /* "VondaGrammar.y":147  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))); ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))).addFirst(((Import)(yystack.valueAt (2-(1))))); };
  break;
    

  case 7:
  if (yyn == 7)
    /* "VondaGrammar.y":148  */ /* lalr1.java:489  */
    {
    yyval = ((LinkedList<RudiTree>)(yystack.valueAt (3-(3)))); ((LinkedList<RudiTree>)(yystack.valueAt (3-(3)))).addFirst(setPos(new StatFieldDef(((String)(yystack.valueAt (3-(1)))), ((StatVarDef)(yystack.valueAt (3-(2))))), yystack.locationAt (3-(1)), yystack.locationAt (3-(2))));
  };
  break;
    

  case 8:
  if (yyn == 8)
    /* "VondaGrammar.y":151  */ /* lalr1.java:489  */
    {
    yyval = ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))); ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))).addFirst(setPos(new StatFieldDef(null, ((StatVarDef)(yystack.valueAt (2-(1))))), yystack.locationAt (2-(1))));
  };
  break;
    

  case 9:
  if (yyn == 9)
    /* "VondaGrammar.y":154  */ /* lalr1.java:489  */
    {
    yyval = ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))); ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))).addFirst(((StatFieldDef)(yystack.valueAt (2-(1)))));
  };
  break;
    

  case 10:
  if (yyn == 10)
    /* "VondaGrammar.y":157  */ /* lalr1.java:489  */
    { yyval = _statements;};
  break;
    

  case 11:
  if (yyn == 11)
    /* "VondaGrammar.y":161  */ /* lalr1.java:489  */
    { yyval = "public"; };
  break;
    

  case 12:
  if (yyn == 12)
    /* "VondaGrammar.y":162  */ /* lalr1.java:489  */
    { yyval = "protected"; };
  break;
    

  case 13:
  if (yyn == 13)
    /* "VondaGrammar.y":163  */ /* lalr1.java:489  */
    { yyval = "private"; };
  break;
    

  case 14:
  if (yyn == 14)
    /* "VondaGrammar.y":167  */ /* lalr1.java:489  */
    {
    List<String> path = ((List<String>)(yystack.valueAt (3-(2))));
    String name = path.remove(path.size() - 1);
    yyval = setPos(new Import(name, path.toArray(new String[path.size()])), (yyloc));
  };
  break;
    

  case 15:
  if (yyn == 15)
    /* "VondaGrammar.y":175  */ /* lalr1.java:489  */
    { yyval = new ArrayList<String>(){{ add((( String )(yystack.valueAt (1-(1))))); }}; };
  break;
    

  case 16:
  if (yyn == 16)
    /* "VondaGrammar.y":176  */ /* lalr1.java:489  */
    { yyval = ((List<String>)(yystack.valueAt (3-(1)))); ((List<String>)(yystack.valueAt (3-(1)))).add((( String )(yystack.valueAt (3-(3))))); };
  break;
    

  case 17:
  if (yyn == 17)
    /* "VondaGrammar.y":180  */ /* lalr1.java:489  */
    { yyval = ((StatAbstractBlock)(yystack.valueAt (1-(1)))); };
  break;
    

  case 18:
  if (yyn == 18)
    /* "VondaGrammar.y":181  */ /* lalr1.java:489  */
    { yyval = setPos(getAssignmentStat(((RTExpression)(yystack.valueAt (2-(1))))), (yyloc)); };
  break;
    

  case 19:
  if (yyn == 19)
    /* "VondaGrammar.y":182  */ /* lalr1.java:489  */
    { yyval = setPos(new StatExpression(((RTExpression)(yystack.valueAt (2-(1))))), (yyloc)); };
  break;
    

  case 20:
  if (yyn == 20)
    /* "VondaGrammar.y":183  */ /* lalr1.java:489  */
    {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (3-(2))))), yystack.locationAt (3-(2)));
    yyval = setPos(new StatExpression(createPlusMinus(var, "++", (yyloc))), (yyloc));
  };
  break;
    

  case 21:
  if (yyn == 21)
    /* "VondaGrammar.y":187  */ /* lalr1.java:489  */
    {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (3-(2))))), yystack.locationAt (3-(2)));
    yyval = setPos(new StatExpression(createPlusMinus(var, "--", (yyloc))), (yyloc));
  };
  break;
    

  case 22:
  if (yyn == 22)
    /* "VondaGrammar.y":191  */ /* lalr1.java:489  */
    {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (3-(1))))), yystack.locationAt (3-(1)));
    yyval = setPos(new StatExpression(createPlusMinus(var, "+++", (yyloc))), (yyloc));
  };
  break;
    

  case 23:
  if (yyn == 23)
    /* "VondaGrammar.y":195  */ /* lalr1.java:489  */
    {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (3-(1))))), yystack.locationAt (3-(1)));
    yyval = setPos(new StatExpression(createPlusMinus(var, "---", (yyloc))), (yyloc));
  };
  break;
    

  case 24:
  if (yyn == 24)
    /* "VondaGrammar.y":199  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatExpression(createPlusMinus(((RTExpression)(yystack.valueAt (3-(2)))), "++", (yyloc))), (yyloc));
  };
  break;
    

  case 25:
  if (yyn == 25)
    /* "VondaGrammar.y":202  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatExpression(createPlusMinus(((RTExpression)(yystack.valueAt (3-(2)))), "--", (yyloc))), (yyloc));
  };
  break;
    

  case 26:
  if (yyn == 26)
    /* "VondaGrammar.y":205  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatExpression(createPlusMinus(((RTExpression)(yystack.valueAt (3-(1)))), "+++", (yyloc))), (yyloc));
  };
  break;
    

  case 27:
  if (yyn == 27)
    /* "VondaGrammar.y":208  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatExpression(createPlusMinus(((RTExpression)(yystack.valueAt (3-(1)))), "---", (yyloc))), (yyloc));
  };
  break;
    

  case 28:
  if (yyn == 28)
    /* "VondaGrammar.y":211  */ /* lalr1.java:489  */
    { yyval = setPos(new StatExpression(((RTExpression)(yystack.valueAt (2-(1))))), (yyloc)); };
  break;
    

  case 29:
  if (yyn == 29)
    /* "VondaGrammar.y":212  */ /* lalr1.java:489  */
    { yyval = ((StatGrammarRule)(yystack.valueAt (1-(1)))); };
  break;
    

  case 30:
  if (yyn == 30)
    /* "VondaGrammar.y":213  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 31:
  if (yyn == 31)
    /* "VondaGrammar.y":214  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 32:
  if (yyn == 32)
    /* "VondaGrammar.y":215  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 33:
  if (yyn == 33)
    /* "VondaGrammar.y":216  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 34:
  if (yyn == 34)
    /* "VondaGrammar.y":217  */ /* lalr1.java:489  */
    { yyval = ((StatIf)(yystack.valueAt (1-(1)))); };
  break;
    

  case 35:
  if (yyn == 35)
    /* "VondaGrammar.y":218  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 36:
  if (yyn == 36)
    /* "VondaGrammar.y":219  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 37:
  if (yyn == 37)
    /* "VondaGrammar.y":220  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 38:
  if (yyn == 38)
    /* "VondaGrammar.y":221  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 39:
  if (yyn == 39)
    /* "VondaGrammar.y":225  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 40:
  if (yyn == 40)
    /* "VondaGrammar.y":226  */ /* lalr1.java:489  */
    { yyval = ((StatVarDef)(yystack.valueAt (1-(1)))); };
  break;
    

  case 41:
  if (yyn == 41)
    /* "VondaGrammar.y":230  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 42:
  if (yyn == 42)
    /* "VondaGrammar.y":236  */ /* lalr1.java:489  */
    { yyval = setPos(new StatAbstractBlock(((LinkedList<RTStatement>)(yystack.valueAt (3-(2)))), true), (yyloc)); };
  break;
    

  case 43:
  if (yyn == 43)
    /* "VondaGrammar.y":237  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatAbstractBlock(new ArrayList<RTStatement>(), true), (yyloc));
  };
  break;
    

  case 44:
  if (yyn == 44)
    /* "VondaGrammar.y":241  */ /* lalr1.java:489  */
    { yyval = new LinkedList<RTStatement>(){{ add(((RTStatement)(yystack.valueAt (1-(1))))); }}; };
  break;
    

  case 45:
  if (yyn == 45)
    /* "VondaGrammar.y":242  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RTStatement>)(yystack.valueAt (2-(2)))); ((LinkedList<RTStatement>)(yystack.valueAt (2-(2)))).addFirst(((RTStatement)(yystack.valueAt (2-(1))))); };
  break;
    

  case 46:
  if (yyn == 46)
    /* "VondaGrammar.y":246  */ /* lalr1.java:489  */
    { yyval = setPos(new StatGrammarRule((( String )(yystack.valueAt (3-(1)))), ((StatIf)(yystack.valueAt (3-(3))))), (yyloc)); };
  break;
    

  case 47:
  if (yyn == 47)
    /* "VondaGrammar.y":250  */ /* lalr1.java:489  */
    { yyval = setPos(new StatReturn("return"), (yyloc)); };
  break;
    

  case 48:
  if (yyn == 48)
    /* "VondaGrammar.y":251  */ /* lalr1.java:489  */
    { yyval = setPos(new StatReturn(((RTExpression)(yystack.valueAt (3-(2))))), (yyloc)); };
  break;
    

  case 49:
  if (yyn == 49)
    /* "VondaGrammar.y":252  */ /* lalr1.java:489  */
    { yyval = setPos(new StatReturn("break"), (yyloc)); };
  break;
    

  case 50:
  if (yyn == 50)
    /* "VondaGrammar.y":253  */ /* lalr1.java:489  */
    { yyval = setPos(new StatReturn("break", (( String )(yystack.valueAt (3-(2))))), (yyloc)); };
  break;
    

  case 51:
  if (yyn == 51)
    /* "VondaGrammar.y":254  */ /* lalr1.java:489  */
    { yyval = setPos(new StatReturn("cancel"), (yyloc)); };
  break;
    

  case 52:
  if (yyn == 52)
    /* "VondaGrammar.y":255  */ /* lalr1.java:489  */
    { yyval = setPos(new StatReturn("cancel_all"), (yyloc)); };
  break;
    

  case 53:
  if (yyn == 53)
    /* "VondaGrammar.y":256  */ /* lalr1.java:489  */
    { yyval = setPos(new StatReturn("continue"), (yyloc)); };
  break;
    

  case 54:
  if (yyn == 54)
    /* "VondaGrammar.y":260  */ /* lalr1.java:489  */
    { yyval = setPos(new StatIf(((RTExpression)(yystack.valueAt (5-(3)))), ((RTStatement)(yystack.valueAt (5-(5)))), null), (yyloc)); };
  break;
    

  case 55:
  if (yyn == 55)
    /* "VondaGrammar.y":261  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatIf(((RTExpression)(yystack.valueAt (7-(3)))), ((RTStatement)(yystack.valueAt (7-(5)))), ((RTStatement)(yystack.valueAt (7-(7))))), (yyloc));
  };
  break;
    

  case 56:
  if (yyn == 56)
    /* "VondaGrammar.y":267  */ /* lalr1.java:489  */
    { yyval = setPos(new StatWhile(((RTExpression)(yystack.valueAt (5-(3)))), ((RTStatement)(yystack.valueAt (5-(5)))), true), (yyloc)); };
  break;
    

  case 57:
  if (yyn == 57)
    /* "VondaGrammar.y":268  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatWhile(((RTExpression)(yystack.valueAt (6-(5)))), ((RTStatement)(yystack.valueAt (6-(2)))), false), (yyloc));
  };
  break;
    

  case 58:
  if (yyn == 58)
    /* "VondaGrammar.y":274  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatFor1(((StatVarDef)(yystack.valueAt (8-(3)))), ((RTExpression)(yystack.valueAt (8-(4)))), ((RTExpression)(yystack.valueAt (8-(6)))), ((RTStatement)(yystack.valueAt (8-(8))))), (yyloc)); };
  break;
    

  case 59:
  if (yyn == 59)
    /* "VondaGrammar.y":276  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatFor1(((StatVarDef)(yystack.valueAt (7-(3)))), null, ((RTExpression)(yystack.valueAt (7-(5)))), ((RTStatement)(yystack.valueAt (7-(7))))), (yyloc)); };
  break;
    

  case 60:
  if (yyn == 60)
    /* "VondaGrammar.y":278  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatFor1(((StatVarDef)(yystack.valueAt (7-(3)))), ((RTExpression)(yystack.valueAt (7-(4)))), null, ((RTStatement)(yystack.valueAt (7-(7))))), (yyloc)); };
  break;
    

  case 61:
  if (yyn == 61)
    /* "VondaGrammar.y":280  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatFor1(((StatVarDef)(yystack.valueAt (6-(3)))), null, null, ((RTStatement)(yystack.valueAt (6-(6))))), (yyloc)); };
  break;
    

  case 62:
  if (yyn == 62)
    /* "VondaGrammar.y":282  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatFor1(null, ((RTExpression)(yystack.valueAt (8-(4)))), ((RTExpression)(yystack.valueAt (8-(6)))), ((RTStatement)(yystack.valueAt (8-(8))))), (yyloc)); };
  break;
    

  case 63:
  if (yyn == 63)
    /* "VondaGrammar.y":284  */ /* lalr1.java:489  */
    {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (7-(3))))), yystack.locationAt (7-(3)));
    yyval = setPos(new StatFor2(var, ((RTExpression)(yystack.valueAt (7-(5)))), ((RTStatement)(yystack.valueAt (7-(7))))), (yyloc));
  };
  break;
    

  case 64:
  if (yyn == 64)
    /* "VondaGrammar.y":288  */ /* lalr1.java:489  */
    {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (8-(4))))), yystack.locationAt (8-(4)));
    yyval = setPos(new StatFor2(((Type)(yystack.valueAt (8-(3)))), var, ((RTExpression)(yystack.valueAt (8-(6)))), ((RTStatement)(yystack.valueAt (8-(8))))), (yyloc));
  };
  break;
    

  case 65:
  if (yyn == 65)
    /* "VondaGrammar.y":297  */ /* lalr1.java:489  */
    {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (3-(1))))), yystack.locationAt (3-(1)));
    ExpAssignment ass = setPos(new ExpAssignment(var, ((RTExpression)(yystack.valueAt (3-(2))))), yystack.locationAt (3-(1)), yystack.locationAt (3-(2)));
    yyval = setPos(new StatVarDef(false, Type.getNoType(), ass), (yyloc));
  };
  break;
    

  case 66:
  if (yyn == 66)
    /* "VondaGrammar.y":302  */ /* lalr1.java:489  */
    {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (4-(2))))), yystack.locationAt (4-(2)));
    ExpAssignment ass = setPos(new ExpAssignment(var, ((RTExpression)(yystack.valueAt (4-(3))))), yystack.locationAt (4-(2)), yystack.locationAt (4-(3)));
    yyval = setPos(new StatVarDef(false, ((Type)(yystack.valueAt (4-(1)))), ass), (yyloc));
  };
  break;
    

  case 67:
  if (yyn == 67)
    /* "VondaGrammar.y":307  */ /* lalr1.java:489  */
    {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (4-(2))))), yystack.locationAt (4-(2)));
    ExpAssignment ass = setPos(new ExpAssignment(var, ((RTExpression)(yystack.valueAt (4-(3))))), yystack.locationAt (4-(2)), yystack.locationAt (4-(3)));
    yyval = setPos(new StatVarDef(true, Type.getNoType(), ass), (yyloc));
  };
  break;
    

  case 68:
  if (yyn == 68)
    /* "VondaGrammar.y":312  */ /* lalr1.java:489  */
    {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (5-(3))))), yystack.locationAt (5-(3)));
    ExpAssignment ass = setPos(new ExpAssignment(var, ((RTExpression)(yystack.valueAt (5-(4))))), yystack.locationAt (5-(3)), yystack.locationAt (5-(4)));
    yyval = setPos(new StatVarDef(true, ((Type)(yystack.valueAt (5-(2)))), ass), (yyloc));
  };
  break;
    

  case 69:
  if (yyn == 69)
    /* "VondaGrammar.y":320  */ /* lalr1.java:489  */
    { yyval = setPos(new StatPropose(((RTExpression)(yystack.valueAt (5-(3)))), ((StatAbstractBlock)(yystack.valueAt (5-(5))))), (yyloc)); };
  break;
    

  case 70:
  if (yyn == 70)
    /* "VondaGrammar.y":324  */ /* lalr1.java:489  */
    {
    // labeled timeout
    yyval = setPos(new StatTimeout(((RTExpression)(yystack.valueAt (7-(3)))), ((RTExpression)(yystack.valueAt (7-(5)))), ((StatAbstractBlock)(yystack.valueAt (7-(7))))), (yyloc));
  };
  break;
    

  case 71:
  if (yyn == 71)
    /* "VondaGrammar.y":328  */ /* lalr1.java:489  */
    {
    // behaviour timeout
    yyval = setPos(new StatTimeout(((RTExpression)(yystack.valueAt (7-(3)))), ((RTExpression)(yystack.valueAt (7-(5)))), ((StatAbstractBlock)(yystack.valueAt (7-(7))))), (yyloc));
  };
  break;
    

  case 72:
  if (yyn == 72)
    /* "VondaGrammar.y":335  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatSwitch(((RTExpression)(yystack.valueAt (5-(3)))), ((StatAbstractBlock)(yystack.valueAt (5-(5))))), (yyloc));
  };
  break;
    

  case 73:
  if (yyn == 73)
    /* "VondaGrammar.y":342  */ /* lalr1.java:489  */
    {
    ExpLiteral val =
      new ExpLiteral("case \"" + (( ExpLiteral )(yystack.valueAt (3-(2)))).toString() + "\":", "label");
    RTStatement lbl = val.ensureStatement();
    setPos(val, (yyloc)); setPos(lbl, (yyloc));
    yyval = lbl;
  };
  break;
    

  case 74:
  if (yyn == 74)
    /* "VondaGrammar.y":349  */ /* lalr1.java:489  */
    {
    ExpLiteral val =
      new ExpLiteral("case " + (( ExpLiteral )(yystack.valueAt (3-(2)))).toString() + ":", "label");
    RTStatement lbl = val.ensureStatement();
    setPos(val, (yyloc)); setPos(lbl, (yyloc));
    yyval = lbl;
  };
  break;
    

  case 75:
  if (yyn == 75)
    /* "VondaGrammar.y":356  */ /* lalr1.java:489  */
    {
    ExpLiteral val =
      new ExpLiteral("case " + (( ExpLiteral )(yystack.valueAt (3-(2)))).toString() + ":", "label");
    RTStatement lbl = val.ensureStatement();
    setPos(val, (yyloc)); setPos(lbl, (yyloc));
    yyval = lbl;
  };
  break;
    

  case 76:
  if (yyn == 76)
    /* "VondaGrammar.y":363  */ /* lalr1.java:489  */
    {
    ExpLiteral val =
      new ExpLiteral("case " + (( String )(yystack.valueAt (3-(2)))) + ":", "label");
    RTStatement lbl = val.ensureStatement();
    setPos(val, (yyloc)); setPos(lbl, (yyloc));
    yyval = lbl;
  };
  break;
    

  case 77:
  if (yyn == 77)
    /* "VondaGrammar.y":370  */ /* lalr1.java:489  */
    {
    ExpLiteral val = new ExpLiteral("default:", "label");
    RTStatement lbl = val.ensureStatement();
    setPos(val, (yyloc)); setPos(lbl, (yyloc));
    yyval = lbl;
  };
  break;
    

  case 78:
  if (yyn == 78)
    /* "VondaGrammar.y":379  */ /* lalr1.java:489  */
    {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (4-(2))))), yystack.locationAt (4-(2)));
    ExpAssignment ass = setPos(new ExpAssignment(var, ((RTExpression)(yystack.valueAt (4-(3))))), yystack.locationAt (4-(2)), yystack.locationAt (4-(3)));
    yyval = setPos(new StatVarDef(true, Type.getNoType(), ass), (yyloc));
  };
  break;
    

  case 79:
  if (yyn == 79)
    /* "VondaGrammar.y":384  */ /* lalr1.java:489  */
    {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (4-(2))))), yystack.locationAt (4-(2)));
    ExpAssignment ass = setPos(new ExpAssignment(var, ((RTExpression)(yystack.valueAt (4-(3))))), yystack.locationAt (4-(2)), yystack.locationAt (4-(3)));
    yyval = setPos(new StatVarDef(false, ((Type)(yystack.valueAt (4-(1)))), ass), (yyloc));
  };
  break;
    

  case 80:
  if (yyn == 80)
    /* "VondaGrammar.y":389  */ /* lalr1.java:489  */
    {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (5-(3))))), yystack.locationAt (5-(3)));
    ExpAssignment ass = setPos(new ExpAssignment(var, ((RTExpression)(yystack.valueAt (5-(4))))), yystack.locationAt (5-(3)), yystack.locationAt (5-(4)));
    yyval = setPos(new StatVarDef(true, ((Type)(yystack.valueAt (5-(2)))), ass), (yyloc));
    };
  break;
    

  case 81:
  if (yyn == 81)
    /* "VondaGrammar.y":394  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatVarDef(true, Type.getNoType(), (( String )(yystack.valueAt (3-(2))))), (yyloc));
  };
  break;
    

  case 82:
  if (yyn == 82)
    /* "VondaGrammar.y":397  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatVarDef(false, ((Type)(yystack.valueAt (3-(1)))), (( String )(yystack.valueAt (3-(2))))), (yyloc));
  };
  break;
    

  case 83:
  if (yyn == 83)
    /* "VondaGrammar.y":400  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatVarDef(true, ((Type)(yystack.valueAt (4-(2)))), (( String )(yystack.valueAt (4-(3))))), (yyloc));
  };
  break;
    

  case 84:
  if (yyn == 84)
    /* "VondaGrammar.y":406  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatFieldDef(null,
           setPos(new StatVarDef(false, ((Type)(yystack.valueAt (7-(5)))), (( String )(yystack.valueAt (7-(6))))), (yyloc)), ((Type)(yystack.valueAt (7-(2))))), (yyloc));
  };
  break;
    

  case 85:
  if (yyn == 85)
    /* "VondaGrammar.y":413  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (2-(2)))); };
  break;
    

  case 86:
  if (yyn == 86)
    /* "VondaGrammar.y":414  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpListLiteral(new LinkedList<RTExpression>()), yystack.locationAt (3-(2)), yystack.locationAt (3-(3)));
  };
  break;
    

  case 87:
  if (yyn == 87)
    /* "VondaGrammar.y":417  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpListLiteral(((LinkedList<RTExpression>)(yystack.valueAt (4-(3))))), yystack.locationAt (4-(2)), yystack.locationAt (4-(4)));
  };
  break;
    

  case 88:
  if (yyn == 88)
    /* "VondaGrammar.y":423  */ /* lalr1.java:489  */
    { yyval = new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (1-(1))))); }}; };
  break;
    

  case 89:
  if (yyn == 89)
    /* "VondaGrammar.y":424  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))); ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))).addFirst(((RTExpression)(yystack.valueAt (3-(1))))); };
  break;
    

  case 90:
  if (yyn == 90)
    /* "VondaGrammar.y":429  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatMethodDeclaration("public", ((Type)(yystack.valueAt (10-(5)))), ((Type)(yystack.valueAt (10-(2)))), (( String )(yystack.valueAt (10-(6)))), ((LinkedList)(yystack.valueAt (10-(8)))), ((StatAbstractBlock)(yystack.valueAt (10-(10))))), (yyloc));
  };
  break;
    

  case 91:
  if (yyn == 91)
    /* "VondaGrammar.y":432  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatMethodDeclaration("public", ((Type)(yystack.valueAt (6-(1)))), null, (( String )(yystack.valueAt (6-(2)))), ((LinkedList)(yystack.valueAt (6-(4)))), ((StatAbstractBlock)(yystack.valueAt (6-(6))))), (yyloc));
  };
  break;
    

  case 92:
  if (yyn == 92)
    /* "VondaGrammar.y":438  */ /* lalr1.java:489  */
    { yyval = ((StatAbstractBlock)(yystack.valueAt (1-(1)))); };
  break;
    

  case 93:
  if (yyn == 93)
    /* "VondaGrammar.y":439  */ /* lalr1.java:489  */
    { yyval = null; };
  break;
    

  case 94:
  if (yyn == 94)
    /* "VondaGrammar.y":443  */ /* lalr1.java:489  */
    { yyval = ((LinkedList)(yystack.valueAt (1-(1)))); };
  break;
    

  case 95:
  if (yyn == 95)
    /* "VondaGrammar.y":444  */ /* lalr1.java:489  */
    { yyval = new LinkedList(); };
  break;
    

  case 96:
  if (yyn == 96)
    /* "VondaGrammar.y":448  */ /* lalr1.java:489  */
    { yyval = new LinkedList(){{ add(Type.getNoType()); add((( String )(yystack.valueAt (1-(1))))); }}; };
  break;
    

  case 97:
  if (yyn == 97)
    /* "VondaGrammar.y":449  */ /* lalr1.java:489  */
    { yyval = new LinkedList(){{ add(((Type)(yystack.valueAt (2-(1))))); add((( String )(yystack.valueAt (2-(2))))); }}; };
  break;
    

  case 98:
  if (yyn == 98)
    /* "VondaGrammar.y":450  */ /* lalr1.java:489  */
    {
    yyval = ((LinkedList)(yystack.valueAt (3-(3))));
    ((LinkedList)(yystack.valueAt (3-(3)))).addFirst((( String )(yystack.valueAt (3-(1))))); ((LinkedList)(yystack.valueAt (3-(3)))).addFirst(Type.getNoType());
  };
  break;
    

  case 99:
  if (yyn == 99)
    /* "VondaGrammar.y":454  */ /* lalr1.java:489  */
    {
    yyval = ((LinkedList)(yystack.valueAt (4-(4)))); ((LinkedList)(yystack.valueAt (4-(4)))).addFirst((( String )(yystack.valueAt (4-(2))))); ((LinkedList)(yystack.valueAt (4-(4)))).addFirst(((Type)(yystack.valueAt (4-(1)))));
  };
  break;
    

  case 100:
  if (yyn == 100)
    /* "VondaGrammar.y":462  */ /* lalr1.java:489  */
    {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (4-(1))))), yystack.locationAt (4-(1)));
    yyval = setPos(new StatSetOperation(var, true, ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc));
  };
  break;
    

  case 101:
  if (yyn == 101)
    /* "VondaGrammar.y":466  */ /* lalr1.java:489  */
    {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (4-(1))))), yystack.locationAt (4-(1)));
    yyval = setPos(new StatSetOperation(var, false, ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc));
  };
  break;
    

  case 102:
  if (yyn == 102)
    /* "VondaGrammar.y":470  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatSetOperation(((RTExpression)(yystack.valueAt (4-(1)))), true, ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc));
  };
  break;
    

  case 103:
  if (yyn == 103)
    /* "VondaGrammar.y":473  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatSetOperation(((RTExpression)(yystack.valueAt (4-(1)))), false, ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc));
  };
  break;
    

  case 104:
  if (yyn == 104)
    /* "VondaGrammar.y":476  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatSetOperation(((RTExpression)(yystack.valueAt (4-(1)))), true, ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc));
  };
  break;
    

  case 105:
  if (yyn == 105)
    /* "VondaGrammar.y":479  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatSetOperation(((RTExpression)(yystack.valueAt (4-(1)))), false, ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc));
  };
  break;
    

  case 106:
  if (yyn == 106)
    /* "VondaGrammar.y":488  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpFuncCall((( String )(yystack.valueAt (3-(1)))), new LinkedList<RTExpression>(), false), (yyloc));
  };
  break;
    

  case 107:
  if (yyn == 107)
    /* "VondaGrammar.y":491  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpFuncCall((( String )(yystack.valueAt (4-(1)))), ((LinkedList<RTExpression>)(yystack.valueAt (4-(3)))), false), (yyloc));
  };
  break;
    

  case 108:
  if (yyn == 108)
    /* "VondaGrammar.y":497  */ /* lalr1.java:489  */
    { yyval = new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (1-(1))))); }}; };
  break;
    

  case 109:
  if (yyn == 109)
    /* "VondaGrammar.y":498  */ /* lalr1.java:489  */
    { yyval = new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (1-(1))))); }}; };
  break;
    

  case 110:
  if (yyn == 110)
    /* "VondaGrammar.y":499  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))); ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))).addFirst(((RTExpression)(yystack.valueAt (3-(1))))); };
  break;
    

  case 111:
  if (yyn == 111)
    /* "VondaGrammar.y":500  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))); ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))).addFirst(((RTExpression)(yystack.valueAt (3-(1))))); };
  break;
    

  case 112:
  if (yyn == 112)
    /* "VondaGrammar.y":504  */ /* lalr1.java:489  */
    {
    yyval = new Type("Array",
                  new ArrayList<Type>(){{ add(new Type((( String )(yystack.valueAt (3-(1)))))); }});
  };
  break;
    

  case 113:
  if (yyn == 113)
    /* "VondaGrammar.y":508  */ /* lalr1.java:489  */
    { yyval = new Type((( String )(yystack.valueAt (1-(1))))); };
  break;
    

  case 114:
  if (yyn == 114)
    /* "VondaGrammar.y":509  */ /* lalr1.java:489  */
    { yyval = new Type((( String )(yystack.valueAt (4-(1)))), ((LinkedList<Type>)(yystack.valueAt (4-(3))))); };
  break;
    

  case 115:
  if (yyn == 115)
    /* "VondaGrammar.y":513  */ /* lalr1.java:489  */
    { yyval = new LinkedList<Type>(){{ add(((Type)(yystack.valueAt (1-(1))))); }}; };
  break;
    

  case 116:
  if (yyn == 116)
    /* "VondaGrammar.y":514  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<Type>)(yystack.valueAt (3-(3)))); ((LinkedList<Type>)(yystack.valueAt (3-(3)))).addFirst(((Type)(yystack.valueAt (3-(1))))); };
  break;
    

  case 117:
  if (yyn == 117)
    /* "VondaGrammar.y":518  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "||"), (yyloc));
  };
  break;
    

  case 118:
  if (yyn == 118)
    /* "VondaGrammar.y":521  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 119:
  if (yyn == 119)
    /* "VondaGrammar.y":525  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "&&"), (yyloc));
  };
  break;
    

  case 120:
  if (yyn == 120)
    /* "VondaGrammar.y":528  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 121:
  if (yyn == 121)
    /* "VondaGrammar.y":532  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 122:
  if (yyn == 122)
    /* "VondaGrammar.y":533  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "|"), (yyloc));
  };
  break;
    

  case 123:
  if (yyn == 123)
    /* "VondaGrammar.y":539  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 124:
  if (yyn == 124)
    /* "VondaGrammar.y":540  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "^"), (yyloc));
  };
  break;
    

  case 125:
  if (yyn == 125)
    /* "VondaGrammar.y":546  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 126:
  if (yyn == 126)
    /* "VondaGrammar.y":547  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "&"), (yyloc));
  };
  break;
    

  case 127:
  if (yyn == 127)
    /* "VondaGrammar.y":553  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 128:
  if (yyn == 128)
    /* "VondaGrammar.y":554  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "=="), (yyloc));
  };
  break;
    

  case 129:
  if (yyn == 129)
    /* "VondaGrammar.y":557  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "!="), (yyloc));
  };
  break;
    

  case 130:
  if (yyn == 130)
    /* "VondaGrammar.y":563  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 131:
  if (yyn == 131)
    /* "VondaGrammar.y":564  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "<"), (yyloc));
  };
  break;
    

  case 132:
  if (yyn == 132)
    /* "VondaGrammar.y":567  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), ">"), (yyloc));
  };
  break;
    

  case 133:
  if (yyn == 133)
    /* "VondaGrammar.y":570  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), ">="), (yyloc));
  };
  break;
    

  case 134:
  if (yyn == 134)
    /* "VondaGrammar.y":573  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "<="), (yyloc));
  };
  break;
    

  case 135:
  if (yyn == 135)
    /* "VondaGrammar.y":579  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 136:
  if (yyn == 136)
    /* "VondaGrammar.y":580  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "+"), (yyloc));
  };
  break;
    

  case 137:
  if (yyn == 137)
    /* "VondaGrammar.y":583  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "-"), (yyloc));
  };
  break;
    

  case 138:
  if (yyn == 138)
    /* "VondaGrammar.y":589  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 139:
  if (yyn == 139)
    /* "VondaGrammar.y":590  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "*"), (yyloc));
  };
  break;
    

  case 140:
  if (yyn == 140)
    /* "VondaGrammar.y":593  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "/"), (yyloc));
  };
  break;
    

  case 141:
  if (yyn == 141)
    /* "VondaGrammar.y":596  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "%"), (yyloc));
  };
  break;
    

  case 142:
  if (yyn == 142)
    /* "VondaGrammar.y":602  */ /* lalr1.java:489  */
    {
    yyval = createPlusMinus(((RTExpression)(yystack.valueAt (2-(2)))), "++", (yyloc));
  };
  break;
    

  case 143:
  if (yyn == 143)
    /* "VondaGrammar.y":605  */ /* lalr1.java:489  */
    {
    yyval = createPlusMinus(((RTExpression)(yystack.valueAt (2-(2)))), "--", (yyloc));
  };
  break;
    

  case 144:
  if (yyn == 144)
    /* "VondaGrammar.y":608  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2-(2)))), null, "+"), (yyloc)); };
  break;
    

  case 145:
  if (yyn == 145)
    /* "VondaGrammar.y":609  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2-(2)))), null, "-"), (yyloc)); };
  break;
    

  case 146:
  if (yyn == 146)
    /* "VondaGrammar.y":610  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 147:
  if (yyn == 147)
    /* "VondaGrammar.y":614  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 148:
  if (yyn == 148)
    /* "VondaGrammar.y":615  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpCast(((Type)(yystack.valueAt (4-(2)))), ((RTExpression)(yystack.valueAt (4-(4))))), (yyloc)); };
  break;
    

  case 149:
  if (yyn == 149)
    /* "VondaGrammar.y":619  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 150:
  if (yyn == 150)
    /* "VondaGrammar.y":620  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (2-(2)))), null, "!"), (yyloc)); };
  break;
    

  case 151:
  if (yyn == 151)
    /* "VondaGrammar.y":621  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2-(2)))), null, "~"), (yyloc)); };
  break;
    

  case 152:
  if (yyn == 152)
    /* "VondaGrammar.y":625  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 153:
  if (yyn == 153)
    /* "VondaGrammar.y":626  */ /* lalr1.java:489  */
    {
    yyval = createPlusMinus(((RTExpression)(yystack.valueAt (2-(1)))), "+++", (yyloc));
  };
  break;
    

  case 154:
  if (yyn == 154)
    /* "VondaGrammar.y":629  */ /* lalr1.java:489  */
    {
    yyval = createPlusMinus(((RTExpression)(yystack.valueAt (2-(1)))), "---", (yyloc));
  };
  break;
    

  case 155:
  if (yyn == 155)
    /* "VondaGrammar.y":635  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpLiteral("null", "null"), (yyloc)); };
  break;
    

  case 156:
  if (yyn == 156)
    /* "VondaGrammar.y":636  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 157:
  if (yyn == 157)
    /* "VondaGrammar.y":637  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 158:
  if (yyn == 158)
    /* "VondaGrammar.y":641  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpIdentifier((( String )(yystack.valueAt (1-(1))))), (yyloc)); };
  break;
    

  case 159:
  if (yyn == 159)
    /* "VondaGrammar.y":642  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpCast(((Type)(yystack.valueAt (6-(3)))), ((RTExpression)(yystack.valueAt (6-(5))))), (yyloc)); };
  break;
    

  case 160:
  if (yyn == 160)
    /* "VondaGrammar.y":646  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (3-(2)))); ((RTExpression)(yystack.valueAt (3-(2)))).generateParens(); };
  break;
    

  case 161:
  if (yyn == 161)
    /* "VondaGrammar.y":647  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 162:
  if (yyn == 162)
    /* "VondaGrammar.y":651  */ /* lalr1.java:489  */
    { yyval = ((ExpLiteral)(yystack.valueAt (1-(1)))); };
  break;
    

  case 163:
  if (yyn == 163)
    /* "VondaGrammar.y":652  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 164:
  if (yyn == 164)
    /* "VondaGrammar.y":653  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 165:
  if (yyn == 165)
    /* "VondaGrammar.y":654  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 166:
  if (yyn == 166)
    /* "VondaGrammar.y":655  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 167:
  if (yyn == 167)
    /* "VondaGrammar.y":659  */ /* lalr1.java:489  */
    { yyval = setPos((( ExpLiteral )(yystack.valueAt (1-(1)))), (yyloc)); };
  break;
    

  case 168:
  if (yyn == 168)
    /* "VondaGrammar.y":660  */ /* lalr1.java:489  */
    { yyval = setPos((( ExpLiteral )(yystack.valueAt (1-(1)))), (yyloc)); };
  break;
    

  case 169:
  if (yyn == 169)
    /* "VondaGrammar.y":661  */ /* lalr1.java:489  */
    { yyval = setPos((( ExpLiteral )(yystack.valueAt (1-(1)))), (yyloc)); };
  break;
    

  case 170:
  if (yyn == 170)
    /* "VondaGrammar.y":662  */ /* lalr1.java:489  */
    { yyval = setPos((( ExpLiteral )(yystack.valueAt (1-(1)))), (yyloc)); };
  break;
    

  case 171:
  if (yyn == 171)
    /* "VondaGrammar.y":666  */ /* lalr1.java:489  */
    {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (4-(1))))), yystack.locationAt (4-(1)));
    yyval = setPos(new ExpArrayAccess(var, ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc));
  };
  break;
    

  case 172:
  if (yyn == 172)
    /* "VondaGrammar.y":670  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpArrayAccess(((RTExpression)(yystack.valueAt (4-(1)))), ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc)); };
  break;
    

  case 173:
  if (yyn == 173)
    /* "VondaGrammar.y":674  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpConditional(((RTExpression)(yystack.valueAt (5-(1)))), ((RTExpression)(yystack.valueAt (5-(3)))), ((RTExpression)(yystack.valueAt (5-(5))))), (yyloc)); };
  break;
    

  case 174:
  if (yyn == 174)
    /* "VondaGrammar.y":675  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 175:
  if (yyn == 175)
    /* "VondaGrammar.y":681  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpAssignment(((RTExpression)(yystack.valueAt (2-(1)))), ((RTExpression)(yystack.valueAt (2-(2))))), (yyloc)); };
  break;
    

  case 176:
  if (yyn == 176)
    /* "VondaGrammar.y":682  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpAssignment(((RTExpression)(yystack.valueAt (2-(1)))), ((RTExpression)(yystack.valueAt (2-(2))))), (yyloc)); };
  break;
    

  case 177:
  if (yyn == 177)
    /* "VondaGrammar.y":683  */ /* lalr1.java:489  */
    {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (2-(1))))), yystack.locationAt (2-(1)));
    ExpAssignment ass = setPos(new ExpAssignment(var, ((RTExpression)(yystack.valueAt (2-(2))))), (yyloc));
    yyval = ass;
  };
  break;
    

  case 178:
  if (yyn == 178)
    /* "VondaGrammar.y":691  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpFieldAccess(((LinkedList<RTExpression>)(yystack.valueAt (2-(2))))), (yyloc)); ((LinkedList<RTExpression>)(yystack.valueAt (2-(2)))).addFirst(((RTExpression)(yystack.valueAt (2-(1)))));
  };
  break;
    

  case 179:
  if (yyn == 179)
    /* "VondaGrammar.y":694  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpFieldAccess(((LinkedList<RTExpression>)(yystack.valueAt (2-(2))))), (yyloc)); ((LinkedList<RTExpression>)(yystack.valueAt (2-(2)))).addFirst(setPos((( ExpLiteral )(yystack.valueAt (2-(1)))), (yyloc)));
  };
  break;
    

  case 180:
  if (yyn == 180)
    /* "VondaGrammar.y":697  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpFieldAccess(((LinkedList<RTExpression>)(yystack.valueAt (2-(2))))), (yyloc)); ((LinkedList<RTExpression>)(yystack.valueAt (2-(2)))).addFirst(((RTExpression)(yystack.valueAt (2-(1))))); };
  break;
    

  case 181:
  if (yyn == 181)
    /* "VondaGrammar.y":701  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))); ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))).addFirst(((RTExpression)(yystack.valueAt (3-(2))))); };
  break;
    

  case 182:
  if (yyn == 182)
    /* "VondaGrammar.y":702  */ /* lalr1.java:489  */
    { yyval = new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (2-(2))))); }}; };
  break;
    

  case 183:
  if (yyn == 183)
    /* "VondaGrammar.y":706  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpIdentifier((( String )(yystack.valueAt (1-(1))))), (yyloc)); };
  break;
    

  case 184:
  if (yyn == 184)
    /* "VondaGrammar.y":707  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 185:
  if (yyn == 185)
    /* "VondaGrammar.y":708  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (3-(2)))); };
  break;
    

  case 186:
  if (yyn == 186)
    /* "VondaGrammar.y":712  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpNew(new Type((( String )(yystack.valueAt (2-(2)))))), (yyloc)); };
  break;
    

  case 187:
  if (yyn == 187)
    /* "VondaGrammar.y":713  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpNew(new Type((( String )(yystack.valueAt (4-(2))))), new LinkedList<>()), (yyloc));
  };
  break;
    

  case 188:
  if (yyn == 188)
    /* "VondaGrammar.y":716  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpNew(new Type((( String )(yystack.valueAt (5-(2))))), ((LinkedList<RTExpression>)(yystack.valueAt (5-(4))))), (yyloc));
  };
  break;
    

  case 189:
  if (yyn == 189)
    /* "VondaGrammar.y":719  */ /* lalr1.java:489  */
    {
    List<Type> sub = new ArrayList<Type>() {{ add(new Type((( String )(yystack.valueAt (5-(2)))))); }};
    yyval = setPos(new ExpNew(new Type("Array", sub),
                    new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (5-(4))))); }}), (yyloc));
  };
  break;
    

  case 190:
  if (yyn == 190)
    /* "VondaGrammar.y":724  */ /* lalr1.java:489  */
    {
    List<Type> sub = new ArrayList<Type>() {{ add(new Type((( String )(yystack.valueAt (7-(2)))))); }};
    yyval = setPos(new ExpNew(new Type("Array", sub),
                    new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (7-(6))))); }}), (yyloc));
  };
  break;
    

  case 191:
  if (yyn == 191)
    /* "VondaGrammar.y":729  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpNew(new Type((( String )(yystack.valueAt (7-(2)))), ((LinkedList<Type>)(yystack.valueAt (7-(4))))),
                    new LinkedList<>()), (yyloc));
  };
  break;
    

  case 192:
  if (yyn == 192)
    /* "VondaGrammar.y":733  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpNew(new Type((( String )(yystack.valueAt (8-(2)))), ((LinkedList<Type>)(yystack.valueAt (8-(4))))),
                    ((LinkedList<RTExpression>)(yystack.valueAt (8-(7))))), (yyloc));
  };
  break;
    

  case 193:
  if (yyn == 193)
    /* "VondaGrammar.y":740  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpLambda(((LinkedList)(yystack.valueAt (5-(2)))), ((RTExpression)(yystack.valueAt (5-(5))))), (yyloc));
  };
  break;
    

  case 194:
  if (yyn == 194)
    /* "VondaGrammar.y":743  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpLambda(((LinkedList)(yystack.valueAt (5-(2)))), ((StatAbstractBlock)(yystack.valueAt (5-(5))))), (yyloc));
  };
  break;
    

  case 195:
  if (yyn == 195)
    /* "VondaGrammar.y":750  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpDialogueAct(((RTExpression)(yystack.valueAt (6-(2)))), ((RTExpression)(yystack.valueAt (6-(4)))), ((LinkedList<RTExpression>)(yystack.valueAt (6-(5))))), (yyloc));
  };
  break;
    

  case 196:
  if (yyn == 196)
    /* "VondaGrammar.y":756  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (3-(2)))); };
  break;
    

  case 197:
  if (yyn == 197)
    /* "VondaGrammar.y":759  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpLiteral((( String )(yystack.valueAt (1-(1)))), "String"), (yyloc)); };
  break;
    

  case 198:
  if (yyn == 198)
    /* "VondaGrammar.y":760  */ /* lalr1.java:489  */
    { yyval = setPos((( ExpLiteral )(yystack.valueAt (1-(1)))), (yyloc)); };
  break;
    

  case 199:
  if (yyn == 199)
    /* "VondaGrammar.y":761  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpLiteral((( String )(yystack.valueAt (1-(1)))), "String"), (yyloc)); };
  break;
    

  case 200:
  if (yyn == 200)
    /* "VondaGrammar.y":765  */ /* lalr1.java:489  */
    {
    yyval = ((LinkedList<RTExpression>)(yystack.valueAt (5-(5)))); ((LinkedList<RTExpression>)(yystack.valueAt (5-(5)))).addFirst(((RTExpression)(yystack.valueAt (5-(4))))); ((LinkedList<RTExpression>)(yystack.valueAt (5-(5)))).addFirst(((RTExpression)(yystack.valueAt (5-(2)))));
  };
  break;
    

  case 201:
  if (yyn == 201)
    /* "VondaGrammar.y":768  */ /* lalr1.java:489  */
    { yyval = new LinkedList<RTExpression>(); };
  break;
    

  case 202:
  if (yyn == 202)
    /* "VondaGrammar.y":772  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 203:
  if (yyn == 203)
    /* "VondaGrammar.y":773  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 204:
  if (yyn == 204)
    /* "VondaGrammar.y":774  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    


/* "VondaGrammar.java":2162  */ /* lalr1.java:489  */
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

  private static final short yypact_ninf_ = -374;
  private static final short yytable_ninf_ = -159;

  /* YYPACT[STATE-NUM] -- Index in YYTABLE of the portion describing
   STATE-NUM.  */
  private static final short yypact_[] = yypact_init();
  private static final short[] yypact_init()
  {
    return new short[]
    {
     934,   140,     2,     6,   200,    31,    36,  1029,   -20,    70,
      78,    98,  -374,   123,  -374,  -374,   520,   138,   148,   157,
     -10,    38,   164,  -374,  -374,   487,  -374,   983,  1311,   176,
     147,   220,     1,   934,   934,  -374,  -374,  -374,  -374,  -374,
    -374,  -374,  -374,  -374,  -374,   934,   934,   934,  -374,   832,
     184,   164,   201,  -374,  -374,    23,   187,    81,  -374,   192,
    -374,  -374,  -374,   180,   204,   206,   210,  -374,  -374,  -374,
     234,  -374,    89,   219,    87,   226,    -4,  1344,  -374,   131,
    1344,   244,  -374,   697,   697,    51,  -374,  1377,  1476,  1476,
     697,   697,   164,   -12,   259,   205,   230,   231,   181,    37,
     142,    84,  -374,  -374,  -374,   179,  -374,   164,   201,   235,
    -374,  -374,   235,  -374,   248,  1344,  1344,  1344,   164,    -7,
     246,   164,   253,    18,   260,    41,  -374,  1344,  1344,   261,
     262,   292,   648,   749,   865,   176,  -374,  -374,  -374,  1029,
     263,  1377,   258,   106,   256,  -374,  -374,  -374,  1344,   265,
    -374,   176,   934,   934,  -374,  -374,  -374,  -374,  -374,  -374,
    -374,  -374,    60,  -374,  1344,  1344,  1344,  -374,  -374,  1344,
    1344,   267,   268,  -374,  -374,  -374,  -374,  -374,  -374,  -374,
     266,   -31,  -374,   269,   272,    20,   277,    74,  1344,  1043,
     278,   271,  -374,   281,   274,   107,   119,  -374,  -374,  -374,
    -374,  1344,   175,   284,  -374,  -374,  -374,  -374,  1476,  1344,
    1476,  1476,  1476,  1476,  1476,  1476,  1476,  1476,  1476,  1476,
    1476,  1476,  1476,  1476,  1476,  -374,  -374,  -374,   286,   288,
     289,   287,  -374,   176,  -374,  -374,  -374,   279,  1344,  -374,
     164,   298,   299,  -374,  -374,  -374,  1410,  -374,   294,   295,
     296,  -374,   300,  1076,  -374,   304,   301,  -374,  -374,   314,
    -374,   306,   318,   147,   313,  -374,  -374,  -374,   303,   325,
     320,   332,   335,   336,   338,  -374,  -374,  1344,  -374,  -374,
     339,   144,   342,  1344,   341,   345,  1109,   347,    17,  1029,
    -374,   321,  1143,  1177,   176,  1476,   259,   349,   205,   230,
     231,   181,    37,    37,   142,   142,   142,   142,    84,    84,
    -374,  -374,  -374,   321,  1344,  1344,  1029,   348,   354,  -374,
    -374,  -374,   115,   360,  -374,   -26,  -374,  1443,  1443,  -374,
    -374,   355,   344,   176,  -374,  1476,   176,  -374,   361,   366,
     -22,   363,   374,  -374,  -374,  -374,  -374,  -374,  -374,   369,
    -374,   394,   235,   389,  -374,  1344,  1029,   391,  1210,  1344,
     398,   431,  -374,  -374,   396,   337,   397,   388,  -374,  1344,
    -374,   401,   409,  -374,   697,  -374,   303,   420,   410,  -374,
    -374,  -374,  1344,  -374,   415,   424,   147,   417,   176,   146,
    -374,  -374,   426,  1029,   418,  -374,  1029,  1029,   421,   422,
    -374,  1029,  -374,  1344,  -374,   425,  -374,   321,   321,   415,
    -374,  1244,   303,  -374,  -374,    47,   419,  -374,   433,  -374,
    -374,  -374,  -374,  -374,  1029,  -374,  -374,  1029,  1029,  -374,
     432,  1277,  -374,  -374,  -374,  -374,  -374,  -374,   303,   147,
     435,  -374,  -374,  -374,  -374,  -374,   441,   442,   361,  -374,
     146,  -374,  -374
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
       0,     0,   167,   168,   170,   113,   169,     0,     0,     0,
       0,     0,     0,    10,    10,    17,    29,    31,    34,    35,
      36,    32,    33,    37,    38,    10,    10,    10,    30,    10,
       0,     0,     0,   161,   162,   163,     0,   164,   166,     0,
      49,    51,    52,     0,     0,     0,     0,    53,    77,    39,
       0,    40,   165,     0,   113,     0,     0,     0,    15,     0,
       0,     0,   155,     0,     0,   158,    47,     0,     0,     0,
       0,     0,   165,   174,   118,   120,   121,   123,   125,   127,
     130,   135,   147,   138,   146,   149,   152,   156,   157,   163,
     202,   203,   164,   204,     0,     0,     0,     0,     0,   158,
       0,     0,     0,   158,     0,     0,   179,     0,     0,     0,
       0,     0,     0,     0,     0,     0,   177,    43,    41,    44,
       0,     0,     0,   113,     0,   198,   199,   197,     0,     0,
       1,     0,    10,    10,     6,     5,     8,     9,     3,    28,
       4,   180,     0,   178,     0,     0,     0,   176,    18,     0,
       0,     0,     0,    19,   175,    50,    73,    74,    75,    76,
       0,     0,    81,     0,     0,     0,     0,   113,     0,     0,
       0,     0,    14,     0,     0,   186,   158,   143,   163,   164,
     142,     0,   158,     0,   144,   145,   150,   151,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,   154,   153,    48,     0,   166,
       0,     0,    21,     0,    25,    20,    24,   183,     0,   184,
     182,     0,     0,    23,    22,    46,    95,   106,     0,   109,
     108,   112,     0,     0,    85,   115,     0,    45,    42,     0,
     160,     0,     0,     0,     0,     7,     2,    82,    95,     0,
       0,     0,     0,     0,     0,    27,    26,     0,    78,    83,
       0,   113,     0,     0,     0,     0,     0,     0,     0,     0,
      16,     0,     0,     0,     0,     0,   117,     0,   119,   122,
     124,   126,   128,   129,   133,   134,   131,   132,   136,   137,
     139,   140,   141,     0,     0,     0,     0,     0,     0,   181,
     101,   100,   158,     0,    94,     0,   107,     0,     0,   171,
      86,     0,    88,     0,   114,     0,     0,   196,   201,     0,
      96,     0,     0,    79,   172,   103,   102,   105,   104,     0,
      80,     0,     0,     0,    65,     0,     0,     0,     0,     0,
       0,    54,    69,   187,     0,     0,     0,     0,   148,     0,
      72,     0,     0,    56,     0,   185,     0,     0,    97,   111,
     110,    87,     0,   116,   147,     0,     0,     0,     0,     0,
      57,    67,     0,     0,     0,    61,     0,     0,     0,     0,
      66,     0,   188,     0,   189,     0,   173,     0,     0,     0,
      98,     0,     0,    89,   159,     0,     0,   195,     0,    93,
      92,    91,    68,    63,     0,    59,    60,     0,     0,    55,
       0,     0,    71,    70,   194,   193,    99,    84,    95,     0,
       0,    62,    58,    64,   190,   191,     0,     0,   201,   192,
       0,   200,    90
    };
  }

/* YYPGOTO[NTERM-NUM].  */
  private static final short yypgoto_[] = yypgoto_init();
  private static final short[] yypgoto_init()
  {
    return new short[]
    {
    -374,   282,  -374,  -374,  -374,   467,    57,  -374,  -289,   340,
    -374,  -374,   362,  -374,  -374,  -374,  -374,  -374,  -374,  -374,
     358,  -374,   -51,  -273,   460,    44,  -267,  -373,  -374,    50,
     -96,     0,  -281,  -374,   290,   285,   291,   297,   283,    30,
      46,    27,   -74,   -46,  -374,  -374,  -374,   172,   323,  -374,
    -374,   208,  -374,   352,    59,     3,  -374,  -374,  -374,   383,
    -258,    58,   533
    };
  }

/* YYDEFGOTO[NTERM-NUM].  */
  private static final short yydefgoto_[] = yydefgoto_init();
  private static final short[] yydefgoto_init()
  {
    return new short[]
    {
      -1,    31,    32,    33,    79,    69,   138,   139,    35,   140,
      36,    37,    38,    39,    40,   189,    41,    42,    43,    44,
      71,    46,   136,   331,    47,   421,   323,   324,    48,    92,
     248,    73,   256,    93,    94,    95,    96,    97,    98,    99,
     100,   101,   102,   103,   104,   105,   106,   107,   108,    53,
      54,   109,   110,   111,   112,   161,   240,   113,   249,    58,
     149,   387,   142
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
      50,   341,   362,   410,   167,   338,   174,   186,    75,   197,
     200,   267,     8,   367,   378,   208,   206,   207,  -113,   364,
      74,   134,   295,   184,   370,   126,   118,   376,   183,   144,
     119,   135,    50,    50,    50,   232,   187,   120,   188,   436,
     132,   143,   204,   205,    61,    50,    50,    50,    62,    50,
      49,   151,   383,   209,   163,   165,   166,    72,   167,    57,
     235,   174,   279,   359,    70,   132,    57,   216,   217,   134,
     121,   121,   134,    67,   118,   134,   190,    72,   123,   122,
     124,   237,    68,    49,    49,   120,    57,   203,   238,   437,
     218,   219,    57,    57,   438,    49,    49,    49,   132,    49,
     420,   201,   267,   134,    57,    57,    57,   268,    57,   413,
     163,   269,   134,   169,   170,   171,   172,    76,   432,   433,
     283,   126,   434,   173,   183,    77,   134,   135,   416,   182,
     269,   159,   125,   134,   280,   255,   284,   183,    78,   134,
     135,   259,   199,   199,   222,   223,   224,   199,   199,   199,
     199,   264,    50,    50,   292,  -113,   183,   293,   446,   135,
     294,   420,   132,   -96,   376,   133,   132,   134,   135,   201,
      80,   447,    51,   192,   193,   239,   310,   311,   312,    51,
      59,   448,    60,   145,   146,   115,   282,   147,   419,    72,
      27,   148,    51,    51,   183,   116,   134,   135,    57,    51,
     220,   221,    49,    49,   117,    51,    51,   125,    55,   214,
     215,    57,    57,   225,   226,    55,   143,    51,    51,    51,
     150,    51,   132,  -113,   162,   133,   176,   134,   135,   168,
     351,   379,   380,   317,   175,    55,    63,   360,    64,    65,
      66,    55,    55,   319,   302,   303,   325,   308,   309,   368,
     177,   164,   178,    55,    55,    55,   179,    55,   180,   181,
     211,   384,   304,   305,   306,   307,   185,   199,   342,   199,
     199,   199,   199,   199,   199,   199,   199,   199,   199,   199,
     199,   199,   199,   199,   195,   210,   212,   134,   213,   368,
     227,   198,   198,   233,   255,   234,   198,   198,   198,   198,
     409,   392,   236,   243,   244,    10,   260,   261,   258,   275,
     276,    51,   263,   277,   278,   154,   155,   281,   288,   289,
     251,   290,   291,    52,    51,    51,   132,   156,   157,   158,
      52,   160,   295,   255,   313,   316,   385,   314,   315,    72,
     320,   321,   326,   340,   327,   328,   361,    55,    57,   336,
      52,   329,    56,   333,   199,   334,    52,    52,    45,    56,
      55,    55,   335,   337,   339,    27,    72,   343,    52,    52,
      52,   344,    52,   373,   345,    57,   342,   346,   347,    56,
     348,   350,   352,   354,   403,    56,    56,   355,   418,   358,
     152,    45,    45,   382,   199,   369,   374,    56,    56,    56,
     381,    56,   375,    45,    45,    45,    72,    45,   377,   388,
     386,   389,   342,   395,   378,    57,   198,   390,   198,   198,
     198,   198,   198,   198,   198,   198,   198,   198,   198,   198,
     198,   198,   198,   199,   265,   266,   391,   393,   342,   396,
     400,   401,   405,    72,   402,   411,    72,    72,   404,   407,
     423,    72,    57,   425,   426,    57,    57,   408,   429,   412,
      57,    51,    52,   414,   415,   417,   424,    34,   422,   427,
     428,   439,   431,   440,    72,    52,    52,    72,    72,   257,
     444,   441,   438,    57,   442,   443,    57,    57,    51,   449,
     450,    56,   153,   245,   452,   298,   301,    55,   296,   229,
      34,    34,   299,   198,    56,    56,   451,     0,     0,   300,
      45,    45,    34,    34,    34,     0,    34,     0,     0,   127,
     128,   129,   130,     0,    55,     0,     0,     0,    51,     0,
    -158,     0,     0,   131,   132,    81,    82,   133,     0,   134,
     135,     0,     0,   198,     0,     0,     0,     0,     0,   114,
       0,     0,     0,     0,    83,    84,    22,     0,    23,    24,
      85,    26,    86,     0,    55,    51,     0,    87,    51,    51,
       0,     0,     0,    51,     0,     0,     0,     0,    88,    89,
       0,     0,   198,    90,    91,     0,    30,     0,     0,     0,
       0,     0,     0,     0,     0,     0,    51,     0,     0,    51,
      51,    55,     0,     0,    55,    55,     0,     0,     0,    55,
     191,     0,    52,   194,     0,     0,     0,     0,     0,    34,
      34,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,    55,     0,     0,    55,    55,     0,     0,    52,
       0,    56,     0,     0,     0,     0,     0,     0,   228,   230,
     231,     0,     0,     0,     0,     0,     0,     0,     0,     0,
     241,   242,     0,    81,    82,   250,   252,   254,    56,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,    52,
       0,   262,    83,    84,    22,     0,    23,    24,    85,    26,
       0,     0,     0,     0,     0,   246,   247,   270,   271,   272,
       0,     0,   273,   274,     0,     0,    88,    89,    56,     0,
       0,    90,    91,    82,    30,     0,    52,     0,     0,    52,
      52,   285,   287,     0,    52,     0,     0,     0,     0,     0,
       0,    83,    84,    22,   252,    23,    24,   196,    26,     0,
       0,     0,   297,     0,    28,    56,     0,    52,    56,    56,
      52,    52,     0,    56,     0,    88,    89,     0,     0,     0,
      90,    91,     0,    30,    81,    82,     0,     0,     0,     0,
       0,   318,     0,     0,     0,     0,    56,     0,     0,    56,
      56,     0,     0,    83,    84,    22,   332,    23,    24,    85,
      26,     0,     0,     0,     0,     0,    87,     0,     0,     0,
     251,     0,     0,     0,     0,     0,     0,    88,    89,     0,
     349,     0,    90,    91,     0,    30,   353,     0,     0,   357,
       0,     0,     0,     0,     0,   332,   366,     0,     0,     0,
       0,     0,     0,     0,     0,     1,     2,     3,     4,     5,
       6,     7,     0,     8,     9,    10,    11,   371,   372,    12,
      13,    14,    15,    16,    17,    18,    19,     0,     0,     0,
     250,   250,     0,     0,     0,     0,    20,    21,    22,     0,
      23,    24,    25,    26,   159,   125,    27,     0,     0,    28,
      81,    82,    29,     0,     0,     0,     0,     0,   394,     0,
       0,   398,   399,     0,     0,     0,     0,     0,    30,    83,
      84,    22,   406,    23,    24,    85,    26,     0,     0,   253,
       0,     0,    87,     0,     0,   332,     0,     0,     0,     0,
       0,     0,     0,    88,    89,     0,     0,     0,    90,    91,
       0,    30,     0,     0,     0,     0,   430,     1,     2,     3,
       4,     5,     6,     7,   435,     8,     9,    10,    11,     0,
       0,    12,    13,    14,    15,    16,    17,    18,    19,     0,
       0,     0,     0,     0,   332,     0,     0,     0,    20,    21,
      22,     0,    23,    24,    25,    26,     0,     0,    27,     0,
       0,    28,     0,     0,    29,     0,     1,     2,     3,     4,
       5,     6,     7,     0,     8,     9,    10,     0,     0,     0,
      30,    13,     0,     0,    16,    17,    18,    19,     0,     0,
       0,     0,     0,     0,     0,     0,     0,    20,    21,    22,
       0,    23,    24,    25,    26,     0,     0,    27,   137,     0,
      28,     0,     1,     2,     3,     4,     5,     6,     7,     0,
       8,     9,    10,     0,     0,     0,     0,    13,     0,    30,
      16,    17,    18,    19,     0,     0,     0,     0,    81,    82,
       0,     0,     0,    20,    21,    22,     0,    23,    24,    25,
      26,     0,     0,    27,     0,     0,    28,    83,    84,    22,
       0,    23,    24,    85,    26,   286,     0,     0,     0,     0,
      87,    81,    82,     0,     0,    30,     0,     0,     0,     0,
       0,    88,    89,     0,     0,     0,    90,    91,     0,    30,
      83,    84,    22,     0,    23,    24,    85,    26,     0,     0,
       0,   330,     0,    87,    81,    82,     0,     0,     0,     0,
       0,     0,     0,     0,    88,    89,     0,     0,     0,    90,
      91,     0,    30,    83,    84,    22,     0,    23,    24,    85,
      26,     0,     0,     0,     0,     0,    87,   356,    81,    82,
       0,     0,     0,     0,     0,     0,     0,    88,    89,     0,
       0,     0,    90,    91,     0,    30,     0,    83,    84,    22,
       0,    23,    24,    85,    26,     0,     0,     0,     0,     0,
      87,   363,    81,    82,     0,     0,     0,     0,     0,     0,
       0,    88,    89,     0,     0,     0,    90,    91,     0,    30,
       0,    83,    84,    22,     0,    23,    24,    85,    26,     0,
       0,     0,     0,     0,    87,    81,    82,     0,   365,     0,
       0,     0,     0,     0,     0,    88,    89,     0,     0,     0,
      90,    91,     0,    30,    83,    84,    22,     0,    23,    24,
      85,    26,     0,     0,     0,     0,     0,    87,   397,    81,
      82,     0,     0,     0,     0,     0,     0,     0,    88,    89,
       0,     0,     0,    90,    91,     0,    30,     0,    83,    84,
      22,     0,    23,    24,    85,    26,     0,     0,    27,     0,
       0,    87,    81,    82,     0,     0,     0,     0,     0,     0,
       0,     0,    88,    89,     0,     0,     0,    90,    91,     0,
      30,    83,    84,    22,     0,    23,    24,    85,    26,     0,
       0,     0,     0,     0,    87,   445,    81,    82,     0,     0,
       0,     0,     0,     0,     0,    88,    89,     0,     0,     0,
      90,    91,     0,    30,     0,    83,    84,    22,     0,    23,
      24,    85,    26,     0,     0,     0,     0,     0,   141,    81,
      82,     0,     0,     0,     0,     0,     0,     0,     0,    88,
      89,     0,     0,     0,    90,    91,     0,    30,    83,    84,
      22,     0,    23,    24,    85,    26,     0,     0,     0,     0,
       0,    87,    81,    82,     0,     0,     0,     0,     0,     0,
       0,     0,    88,    89,     0,     0,     0,    90,    91,     0,
      30,    83,    84,    22,     0,    23,    24,   202,    26,     0,
       0,     0,     0,     0,   141,    81,    82,     0,     0,     0,
       0,     0,     0,     0,     0,    88,    89,     0,     0,     0,
      90,    91,     0,    30,    83,    84,    22,     0,    23,    24,
     322,    26,     0,     0,     0,     0,     0,   141,    81,    82,
       0,     0,     0,     0,     0,     0,     0,     0,    88,    89,
       0,     0,     0,    90,    91,     0,    30,    83,    84,    22,
       0,    23,    24,    85,    26,     0,     0,     0,     0,     0,
     246,     0,    82,     0,     0,     0,     0,     0,     0,     0,
       0,    88,    89,     0,     0,     0,    90,    91,     0,    30,
      83,    84,    22,     0,    23,    24,   196,    26,     0,     0,
       0,     0,     0,    87,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,    88,    89,     0,     0,     0,    90,
      91,     0,    30
    };
  }

private static final short yycheck_[] = yycheck_init();
  private static final short[] yycheck_init()
  {
    return new short[]
    {
       0,   268,   291,   376,    55,   263,    57,    11,     8,    83,
      84,    42,    11,   294,    40,    27,    90,    91,    40,   292,
      40,    52,    48,    74,   313,    22,    36,    49,    50,    29,
      40,    53,    32,    33,    34,    42,    40,    47,    42,   412,
      47,    40,    88,    89,    42,    45,    46,    47,    42,    49,
       0,    50,   333,    65,    51,    32,    33,     7,   109,     0,
      42,   112,    42,    46,     7,    47,     7,    30,    31,    52,
      20,    21,    52,    42,    36,    52,    76,    27,    40,    20,
      21,    40,    46,    33,    34,    47,    27,    87,    47,    42,
      53,    54,    33,    34,    47,    45,    46,    47,    47,    49,
     389,    50,    42,    52,    45,    46,    47,    47,    49,   382,
     107,   162,    52,    32,    33,    34,    35,    47,   407,   408,
      46,   118,   411,    42,    50,    47,    52,    53,   386,    42,
     181,    42,    43,    52,   185,   135,   187,    50,    40,    52,
      53,   141,    83,    84,    60,    61,    62,    88,    89,    90,
      91,   151,   152,   153,    47,    40,    50,    50,   431,    53,
      53,   450,    47,    48,    49,    50,    47,    52,    53,    50,
      47,   438,     0,    42,    43,   125,   222,   223,   224,     7,
      40,   439,    42,    36,    37,    47,   186,    40,    42,   139,
      44,    44,    20,    21,    50,    47,    52,    53,   139,    27,
      58,    59,   152,   153,    47,    33,    34,    43,     0,    28,
      29,   152,   153,    34,    35,     7,    40,    45,    46,    47,
       0,    49,    47,    48,    40,    50,    46,    52,    53,    42,
     281,   327,   328,   233,    42,    27,    36,   288,    38,    39,
      40,    33,    34,   240,   214,   215,   246,   220,   221,   295,
      46,    50,    46,    45,    46,    47,    46,    49,    24,    40,
      55,   335,   216,   217,   218,   219,    40,   208,   268,   210,
     211,   212,   213,   214,   215,   216,   217,   218,   219,   220,
     221,   222,   223,   224,    40,    26,    56,    52,    57,   335,
      42,    83,    84,    47,   294,    42,    88,    89,    90,    91,
     374,   352,    42,    42,    42,    13,    48,    51,    45,    42,
      42,   139,    47,    47,    42,    33,    34,    40,    40,    48,
      51,    40,    48,     0,   152,   153,    47,    45,    46,    47,
       7,    49,    48,   333,    48,    48,   336,    49,    49,   289,
      42,    42,    48,    40,    49,    49,   289,   139,   289,    43,
      27,    51,     0,    49,   295,    54,    33,    34,     0,     7,
     152,   153,    48,    45,    51,    44,   316,    42,    45,    46,
      47,    51,    49,   316,    42,   316,   376,    42,    42,    27,
      42,    42,    40,    42,    47,    33,    34,    42,   388,    42,
      32,    33,    34,    49,   335,    46,    48,    45,    46,    47,
      45,    49,    48,    45,    46,    47,   356,    49,    48,    43,
      49,    48,   412,   356,    40,   356,   208,    48,   210,   211,
     212,   213,   214,   215,   216,   217,   218,   219,   220,   221,
     222,   223,   224,   374,   152,   153,    42,    48,   438,    48,
      42,    10,    54,   393,    48,    25,   396,   397,    51,    48,
     393,   401,   393,   396,   397,   396,   397,    48,   401,    49,
     401,   289,   139,    48,    40,    48,    48,     0,    42,    48,
      48,    52,    47,    40,   424,   152,   153,   427,   428,   139,
      48,   424,    47,   424,   427,   428,   427,   428,   316,    48,
      48,   139,    32,   131,   450,   210,   213,   289,   208,   116,
      33,    34,   211,   295,   152,   153,   448,    -1,    -1,   212,
     152,   153,    45,    46,    47,    -1,    49,    -1,    -1,    32,
      33,    34,    35,    -1,   316,    -1,    -1,    -1,   356,    -1,
      43,    -1,    -1,    46,    47,    15,    16,    50,    -1,    52,
      53,    -1,    -1,   335,    -1,    -1,    -1,    -1,    -1,    16,
      -1,    -1,    -1,    -1,    34,    35,    36,    -1,    38,    39,
      40,    41,    42,    -1,   356,   393,    -1,    47,   396,   397,
      -1,    -1,    -1,   401,    -1,    -1,    -1,    -1,    58,    59,
      -1,    -1,   374,    63,    64,    -1,    66,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,   424,    -1,    -1,   427,
     428,   393,    -1,    -1,   396,   397,    -1,    -1,    -1,   401,
      77,    -1,   289,    80,    -1,    -1,    -1,    -1,    -1,   152,
     153,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,   424,    -1,    -1,   427,   428,    -1,    -1,   316,
      -1,   289,    -1,    -1,    -1,    -1,    -1,    -1,   115,   116,
     117,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
     127,   128,    -1,    15,    16,   132,   133,   134,   316,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,   356,
      -1,   148,    34,    35,    36,    -1,    38,    39,    40,    41,
      -1,    -1,    -1,    -1,    -1,    47,    48,   164,   165,   166,
      -1,    -1,   169,   170,    -1,    -1,    58,    59,   356,    -1,
      -1,    63,    64,    16,    66,    -1,   393,    -1,    -1,   396,
     397,   188,   189,    -1,   401,    -1,    -1,    -1,    -1,    -1,
      -1,    34,    35,    36,   201,    38,    39,    40,    41,    -1,
      -1,    -1,   209,    -1,    47,   393,    -1,   424,   396,   397,
     427,   428,    -1,   401,    -1,    58,    59,    -1,    -1,    -1,
      63,    64,    -1,    66,    15,    16,    -1,    -1,    -1,    -1,
      -1,   238,    -1,    -1,    -1,    -1,   424,    -1,    -1,   427,
     428,    -1,    -1,    34,    35,    36,   253,    38,    39,    40,
      41,    -1,    -1,    -1,    -1,    -1,    47,    -1,    -1,    -1,
      51,    -1,    -1,    -1,    -1,    -1,    -1,    58,    59,    -1,
     277,    -1,    63,    64,    -1,    66,   283,    -1,    -1,   286,
      -1,    -1,    -1,    -1,    -1,   292,   293,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,     3,     4,     5,     6,     7,
       8,     9,    -1,    11,    12,    13,    14,   314,   315,    17,
      18,    19,    20,    21,    22,    23,    24,    -1,    -1,    -1,
     327,   328,    -1,    -1,    -1,    -1,    34,    35,    36,    -1,
      38,    39,    40,    41,    42,    43,    44,    -1,    -1,    47,
      15,    16,    50,    -1,    -1,    -1,    -1,    -1,   355,    -1,
      -1,   358,   359,    -1,    -1,    -1,    -1,    -1,    66,    34,
      35,    36,   369,    38,    39,    40,    41,    -1,    -1,    44,
      -1,    -1,    47,    -1,    -1,   382,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    58,    59,    -1,    -1,    -1,    63,    64,
      -1,    66,    -1,    -1,    -1,    -1,   403,     3,     4,     5,
       6,     7,     8,     9,   411,    11,    12,    13,    14,    -1,
      -1,    17,    18,    19,    20,    21,    22,    23,    24,    -1,
      -1,    -1,    -1,    -1,   431,    -1,    -1,    -1,    34,    35,
      36,    -1,    38,    39,    40,    41,    -1,    -1,    44,    -1,
      -1,    47,    -1,    -1,    50,    -1,     3,     4,     5,     6,
       7,     8,     9,    -1,    11,    12,    13,    -1,    -1,    -1,
      66,    18,    -1,    -1,    21,    22,    23,    24,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    34,    35,    36,
      -1,    38,    39,    40,    41,    -1,    -1,    44,    45,    -1,
      47,    -1,     3,     4,     5,     6,     7,     8,     9,    -1,
      11,    12,    13,    -1,    -1,    -1,    -1,    18,    -1,    66,
      21,    22,    23,    24,    -1,    -1,    -1,    -1,    15,    16,
      -1,    -1,    -1,    34,    35,    36,    -1,    38,    39,    40,
      41,    -1,    -1,    44,    -1,    -1,    47,    34,    35,    36,
      -1,    38,    39,    40,    41,    42,    -1,    -1,    -1,    -1,
      47,    15,    16,    -1,    -1,    66,    -1,    -1,    -1,    -1,
      -1,    58,    59,    -1,    -1,    -1,    63,    64,    -1,    66,
      34,    35,    36,    -1,    38,    39,    40,    41,    -1,    -1,
      -1,    45,    -1,    47,    15,    16,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    58,    59,    -1,    -1,    -1,    63,
      64,    -1,    66,    34,    35,    36,    -1,    38,    39,    40,
      41,    -1,    -1,    -1,    -1,    -1,    47,    48,    15,    16,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    58,    59,    -1,
      -1,    -1,    63,    64,    -1,    66,    -1,    34,    35,    36,
      -1,    38,    39,    40,    41,    -1,    -1,    -1,    -1,    -1,
      47,    48,    15,    16,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    58,    59,    -1,    -1,    -1,    63,    64,    -1,    66,
      -1,    34,    35,    36,    -1,    38,    39,    40,    41,    -1,
      -1,    -1,    -1,    -1,    47,    15,    16,    -1,    51,    -1,
      -1,    -1,    -1,    -1,    -1,    58,    59,    -1,    -1,    -1,
      63,    64,    -1,    66,    34,    35,    36,    -1,    38,    39,
      40,    41,    -1,    -1,    -1,    -1,    -1,    47,    48,    15,
      16,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    58,    59,
      -1,    -1,    -1,    63,    64,    -1,    66,    -1,    34,    35,
      36,    -1,    38,    39,    40,    41,    -1,    -1,    44,    -1,
      -1,    47,    15,    16,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    58,    59,    -1,    -1,    -1,    63,    64,    -1,
      66,    34,    35,    36,    -1,    38,    39,    40,    41,    -1,
      -1,    -1,    -1,    -1,    47,    48,    15,    16,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    58,    59,    -1,    -1,    -1,
      63,    64,    -1,    66,    -1,    34,    35,    36,    -1,    38,
      39,    40,    41,    -1,    -1,    -1,    -1,    -1,    47,    15,
      16,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    58,
      59,    -1,    -1,    -1,    63,    64,    -1,    66,    34,    35,
      36,    -1,    38,    39,    40,    41,    -1,    -1,    -1,    -1,
      -1,    47,    15,    16,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    58,    59,    -1,    -1,    -1,    63,    64,    -1,
      66,    34,    35,    36,    -1,    38,    39,    40,    41,    -1,
      -1,    -1,    -1,    -1,    47,    15,    16,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    58,    59,    -1,    -1,    -1,
      63,    64,    -1,    66,    34,    35,    36,    -1,    38,    39,
      40,    41,    -1,    -1,    -1,    -1,    -1,    47,    15,    16,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    58,    59,
      -1,    -1,    -1,    63,    64,    -1,    66,    34,    35,    36,
      -1,    38,    39,    40,    41,    -1,    -1,    -1,    -1,    -1,
      47,    -1,    16,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    58,    59,    -1,    -1,    -1,    63,    64,    -1,    66,
      34,    35,    36,    -1,    38,    39,    40,    41,    -1,    -1,
      -1,    -1,    -1,    47,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    58,    59,    -1,    -1,    -1,    63,
      64,    -1,    66
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
     119,   120,   121,   124,   129,    47,    47,    47,    36,    40,
      47,    96,   121,    40,   121,    43,   122,    32,    33,    34,
      35,    46,    47,    50,    52,    53,    89,    45,    73,    74,
      76,    47,   129,    40,    98,    36,    37,    40,    44,   127,
       0,    50,    87,    91,    68,    68,    68,    68,    68,    42,
      68,   122,    40,   122,    50,    32,    33,    89,    42,    32,
      33,    34,    35,    42,    89,    42,    46,    46,    46,    46,
      24,    40,    42,    50,    89,    40,    11,    40,    42,    82,
      98,   129,    42,    43,   129,    40,    40,   109,   118,   121,
     109,    50,    40,    98,   110,   110,   109,   109,    27,    65,
      26,    55,    56,    57,    28,    29,    30,    31,    53,    54,
      58,    59,    60,    61,    62,    34,    35,    42,   129,   126,
     129,   129,    42,    47,    42,    42,    42,    40,    47,    96,
     123,   129,   129,    42,    42,    79,    47,    48,    97,   125,
     129,    51,   129,    44,   129,    98,    99,    76,    45,    98,
      48,    51,   129,    47,    98,    68,    68,    42,    47,    89,
     129,   129,   129,   129,   129,    42,    42,    47,    42,    42,
      89,    40,    98,    46,    89,   129,    42,   129,    40,    48,
      40,    48,    47,    50,    53,    48,   101,   129,   102,   103,
     104,   105,   106,   106,   107,   107,   107,   107,   108,   108,
     110,   110,   110,    48,    49,    49,    48,    98,   129,   122,
      42,    42,    40,    93,    94,    98,    48,    49,    49,    51,
      45,    90,   129,    49,    54,    48,    43,    45,   127,    51,
      40,    93,    98,    42,    51,    42,    42,    42,    42,   129,
      42,    89,    40,   129,    42,    42,    48,   129,    42,    46,
      89,    73,    75,    48,    90,    51,   129,    99,   110,    46,
      75,   129,   129,    73,    48,    48,    49,    48,    40,    97,
      97,    45,    49,    99,   109,    98,    49,   128,    43,    48,
      48,    42,    89,    48,   129,    73,    48,    48,   129,   129,
      42,    10,    48,    47,    51,    54,   129,    48,    48,   109,
      94,    25,    49,    90,    48,    40,   127,    48,    98,    42,
      75,    92,    42,    73,    48,    73,    73,    48,    48,    73,
     129,    47,    75,    75,    75,   129,    94,    42,    47,    52,
      40,    73,    73,    73,    48,    48,    90,    93,   127,    48,
      48,   128,    92
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
      72,    72,    72,    72,    72,    72,    72,    72,    72,    73,
      73,    74,    75,    75,    76,    76,    77,    78,    78,    78,
      78,    78,    78,    78,    79,    79,    80,    80,    81,    81,
      81,    81,    81,    81,    81,    82,    82,    82,    82,    83,
      84,    84,    85,    86,    86,    86,    86,    86,    87,    87,
      87,    87,    87,    87,    88,    89,    89,    89,    90,    90,
      91,    91,    92,    92,    93,    93,    94,    94,    94,    94,
      95,    95,    95,    95,    95,    95,    96,    96,    97,    97,
      97,    97,    98,    98,    98,    99,    99,   100,   100,   101,
     101,   102,   102,   103,   103,   104,   104,   105,   105,   105,
     106,   106,   106,   106,   106,   107,   107,   107,   108,   108,
     108,   108,   109,   109,   109,   109,   109,   110,   110,   111,
     111,   111,   112,   112,   112,   113,   113,   113,   114,   114,
     115,   115,   116,   116,   116,   116,   116,   117,   117,   117,
     117,   118,   118,   119,   119,   120,   120,   120,   121,   121,
     121,   122,   122,   123,   123,   123,   124,   124,   124,   124,
     124,   124,   124,   125,   125,   126,   127,   127,   127,   127,
     128,   128,   129,   129,   129
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
       3,     3,     3,     3,     3,     3,     3,     3,     2,     1,
       1,     1,     1,     1,     1,     1,     1,     1,     1,     1,
       1,     1,     3,     2,     1,     2,     3,     2,     3,     2,
       3,     2,     2,     2,     5,     7,     5,     6,     8,     7,
       7,     6,     8,     7,     8,     3,     4,     4,     5,     5,
       7,     7,     5,     3,     3,     3,     3,     2,     4,     4,
       5,     3,     3,     4,     7,     2,     3,     4,     1,     3,
      10,     6,     1,     1,     1,     0,     1,     2,     3,     4,
       4,     4,     4,     4,     4,     4,     3,     4,     1,     1,
       3,     3,     3,     1,     4,     1,     3,     3,     1,     3,
       1,     1,     3,     1,     3,     1,     3,     1,     3,     3,
       1,     3,     3,     3,     3,     1,     3,     3,     1,     3,
       3,     3,     2,     2,     2,     2,     1,     1,     4,     1,
       2,     2,     1,     2,     2,     1,     1,     1,     1,     6,
       3,     1,     1,     1,     1,     1,     1,     1,     1,     1,
       1,     4,     4,     5,     1,     2,     2,     2,     2,     2,
       2,     3,     2,     1,     1,     3,     2,     4,     5,     5,
       7,     7,     8,     5,     5,     6,     3,     1,     1,     1,
       5,     0,     1,     1,     1
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
       0,   140,   140,   143,   144,   145,   147,   148,   151,   154,
     157,   161,   162,   163,   167,   175,   176,   180,   181,   182,
     183,   187,   191,   195,   199,   202,   205,   208,   211,   212,
     213,   214,   215,   216,   217,   218,   219,   220,   221,   225,
     226,   230,   236,   237,   241,   242,   246,   250,   251,   252,
     253,   254,   255,   256,   260,   261,   267,   268,   274,   276,
     278,   280,   282,   284,   288,   297,   302,   307,   312,   320,
     324,   328,   335,   342,   349,   356,   363,   370,   379,   384,
     389,   394,   397,   400,   406,   413,   414,   417,   423,   424,
     429,   432,   438,   439,   443,   444,   448,   449,   450,   454,
     462,   466,   470,   473,   476,   479,   488,   491,   497,   498,
     499,   500,   504,   508,   509,   513,   514,   518,   521,   525,
     528,   532,   533,   539,   540,   546,   547,   553,   554,   557,
     563,   564,   567,   570,   573,   579,   580,   583,   589,   590,
     593,   596,   602,   605,   608,   609,   610,   614,   615,   619,
     620,   621,   625,   626,   629,   635,   636,   637,   641,   642,
     646,   647,   651,   652,   653,   654,   655,   659,   660,   661,
     662,   666,   670,   674,   675,   681,   682,   683,   691,   694,
     697,   701,   702,   706,   707,   708,   712,   713,   716,   719,
     724,   729,   733,   740,   743,   750,   756,   759,   760,   761,
     765,   768,   772,   773,   774
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

  private static final int yylast_ = 1542;
  private static final int yynnts_ = 63;
  private static final int yyempty_ = -2;
  private static final int yyfinal_ = 150;
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
      return new StatVarDef(false, Type.getNoType(), (ExpAssignment)assign);
    }
    return new StatExpression(assign);
  }

  // generic method
  private ExpArithmetic createPlusMinus(RTExpression variable,
                                        String plusOrMinus,
                                        Location loc) {
    return setPos(new ExpArithmetic(variable, null, plusOrMinus), loc);
  }

/* "VondaGrammar.java":3374  */ /* lalr1.java:1066  */

}

