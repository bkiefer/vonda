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
    { yyval = _statements;};
  break;
    

  case 10:
  if (yyn == 10)
    /* "VondaGrammar.y":160  */ /* lalr1.java:489  */
    { yyval = "public"; };
  break;
    

  case 11:
  if (yyn == 11)
    /* "VondaGrammar.y":161  */ /* lalr1.java:489  */
    { yyval = "protected"; };
  break;
    

  case 12:
  if (yyn == 12)
    /* "VondaGrammar.y":162  */ /* lalr1.java:489  */
    { yyval = "private"; };
  break;
    

  case 13:
  if (yyn == 13)
    /* "VondaGrammar.y":166  */ /* lalr1.java:489  */
    {
    List<String> path = ((List<String>)(yystack.valueAt (3-(2))));
    String name = path.remove(path.size() - 1);
    yyval = setPos(new Import(name, path.toArray(new String[path.size()])), (yyloc));
  };
  break;
    

  case 14:
  if (yyn == 14)
    /* "VondaGrammar.y":174  */ /* lalr1.java:489  */
    { yyval = new ArrayList<String>(){{ add((( String )(yystack.valueAt (1-(1))))); }}; };
  break;
    

  case 15:
  if (yyn == 15)
    /* "VondaGrammar.y":175  */ /* lalr1.java:489  */
    { yyval = ((List<String>)(yystack.valueAt (3-(1)))); ((List<String>)(yystack.valueAt (3-(1)))).add((( String )(yystack.valueAt (3-(3))))); };
  break;
    

  case 16:
  if (yyn == 16)
    /* "VondaGrammar.y":179  */ /* lalr1.java:489  */
    { yyval = ((StatAbstractBlock)(yystack.valueAt (1-(1)))); };
  break;
    

  case 17:
  if (yyn == 17)
    /* "VondaGrammar.y":180  */ /* lalr1.java:489  */
    { yyval = setPos(getAssignmentStat(((RTExpression)(yystack.valueAt (2-(1))))), (yyloc)); };
  break;
    

  case 18:
  if (yyn == 18)
    /* "VondaGrammar.y":181  */ /* lalr1.java:489  */
    { yyval = setPos(new StatExpression(((RTExpression)(yystack.valueAt (2-(1))))), (yyloc)); };
  break;
    

  case 19:
  if (yyn == 19)
    /* "VondaGrammar.y":182  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatExpression(createPlusMinus(((RTExpression)(yystack.valueAt (3-(2)))), "+", (yyloc))), (yyloc));
  };
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
    { yyval = setPos(new StatExpression(((RTExpression)(yystack.valueAt (2-(1))))), (yyloc)); };
  break;
    

  case 22:
  if (yyn == 22)
    /* "VondaGrammar.y":189  */ /* lalr1.java:489  */
    { yyval = ((StatGrammarRule)(yystack.valueAt (1-(1)))); };
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
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 27:
  if (yyn == 27)
    /* "VondaGrammar.y":194  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 28:
  if (yyn == 28)
    /* "VondaGrammar.y":195  */ /* lalr1.java:489  */
    { yyval = ((StatIf)(yystack.valueAt (1-(1)))); };
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
    /* "VondaGrammar.y":198  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 32:
  if (yyn == 32)
    /* "VondaGrammar.y":199  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 33:
  if (yyn == 33)
    /* "VondaGrammar.y":203  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 34:
  if (yyn == 34)
    /* "VondaGrammar.y":204  */ /* lalr1.java:489  */
    { yyval = ((StatVarDef)(yystack.valueAt (1-(1)))); };
  break;
    

  case 35:
  if (yyn == 35)
    /* "VondaGrammar.y":208  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 36:
  if (yyn == 36)
    /* "VondaGrammar.y":214  */ /* lalr1.java:489  */
    { yyval = setPos(new StatAbstractBlock(((LinkedList<RTStatement>)(yystack.valueAt (3-(2)))), true), (yyloc)); };
  break;
    

  case 37:
  if (yyn == 37)
    /* "VondaGrammar.y":215  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatAbstractBlock(new ArrayList<RTStatement>(), true), (yyloc));
  };
  break;
    

  case 38:
  if (yyn == 38)
    /* "VondaGrammar.y":219  */ /* lalr1.java:489  */
    { yyval = new LinkedList<RTStatement>(){{ add(((RTStatement)(yystack.valueAt (1-(1))))); }}; };
  break;
    

  case 39:
  if (yyn == 39)
    /* "VondaGrammar.y":220  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RTStatement>)(yystack.valueAt (2-(2)))); ((LinkedList<RTStatement>)(yystack.valueAt (2-(2)))).addFirst(((RTStatement)(yystack.valueAt (2-(1))))); };
  break;
    

  case 40:
  if (yyn == 40)
    /* "VondaGrammar.y":224  */ /* lalr1.java:489  */
    { yyval = setPos(new StatGrammarRule((( String )(yystack.valueAt (3-(1)))), ((StatIf)(yystack.valueAt (3-(3))))), (yyloc)); };
  break;
    

  case 41:
  if (yyn == 41)
    /* "VondaGrammar.y":228  */ /* lalr1.java:489  */
    { yyval = setPos(new StatReturn("return"), (yyloc)); };
  break;
    

  case 42:
  if (yyn == 42)
    /* "VondaGrammar.y":229  */ /* lalr1.java:489  */
    { yyval = setPos(new StatReturn(((RTExpression)(yystack.valueAt (3-(2))))), (yyloc)); };
  break;
    

  case 43:
  if (yyn == 43)
    /* "VondaGrammar.y":230  */ /* lalr1.java:489  */
    { yyval = setPos(new StatReturn("break"), (yyloc)); };
  break;
    

  case 44:
  if (yyn == 44)
    /* "VondaGrammar.y":231  */ /* lalr1.java:489  */
    { yyval = setPos(new StatReturn("break", (( String )(yystack.valueAt (3-(2))))), (yyloc)); };
  break;
    

  case 45:
  if (yyn == 45)
    /* "VondaGrammar.y":232  */ /* lalr1.java:489  */
    { yyval = setPos(new StatReturn("cancel"), (yyloc)); };
  break;
    

  case 46:
  if (yyn == 46)
    /* "VondaGrammar.y":233  */ /* lalr1.java:489  */
    { yyval = setPos(new StatReturn("cancel_all"), (yyloc)); };
  break;
    

  case 47:
  if (yyn == 47)
    /* "VondaGrammar.y":234  */ /* lalr1.java:489  */
    { yyval = setPos(new StatReturn("continue"), (yyloc)); };
  break;
    

  case 48:
  if (yyn == 48)
    /* "VondaGrammar.y":238  */ /* lalr1.java:489  */
    { yyval = setPos(new StatIf(((RTExpression)(yystack.valueAt (5-(3)))), ((RTStatement)(yystack.valueAt (5-(5)))), null), (yyloc)); };
  break;
    

  case 49:
  if (yyn == 49)
    /* "VondaGrammar.y":239  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatIf(((RTExpression)(yystack.valueAt (7-(3)))), ((RTStatement)(yystack.valueAt (7-(5)))), ((RTStatement)(yystack.valueAt (7-(7))))), (yyloc));
  };
  break;
    

  case 50:
  if (yyn == 50)
    /* "VondaGrammar.y":245  */ /* lalr1.java:489  */
    { yyval = setPos(new StatWhile(((RTExpression)(yystack.valueAt (5-(3)))), ((RTStatement)(yystack.valueAt (5-(5)))), true), (yyloc)); };
  break;
    

  case 51:
  if (yyn == 51)
    /* "VondaGrammar.y":246  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatWhile(((RTExpression)(yystack.valueAt (6-(5)))), ((RTStatement)(yystack.valueAt (6-(2)))), false), (yyloc));
  };
  break;
    

  case 52:
  if (yyn == 52)
    /* "VondaGrammar.y":252  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatFor1(((StatVarDef)(yystack.valueAt (8-(3)))), ((RTExpression)(yystack.valueAt (8-(4)))), ((RTExpression)(yystack.valueAt (8-(6)))), ((RTStatement)(yystack.valueAt (8-(8))))), (yyloc)); };
  break;
    

  case 53:
  if (yyn == 53)
    /* "VondaGrammar.y":254  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatFor1(((StatVarDef)(yystack.valueAt (7-(3)))), null, ((RTExpression)(yystack.valueAt (7-(5)))), ((RTStatement)(yystack.valueAt (7-(7))))), (yyloc)); };
  break;
    

  case 54:
  if (yyn == 54)
    /* "VondaGrammar.y":256  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatFor1(((StatVarDef)(yystack.valueAt (7-(3)))), ((RTExpression)(yystack.valueAt (7-(4)))), null, ((RTStatement)(yystack.valueAt (7-(7))))), (yyloc)); };
  break;
    

  case 55:
  if (yyn == 55)
    /* "VondaGrammar.y":258  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatFor1(((StatVarDef)(yystack.valueAt (6-(3)))), null, null, ((RTStatement)(yystack.valueAt (6-(6))))), (yyloc)); };
  break;
    

  case 56:
  if (yyn == 56)
    /* "VondaGrammar.y":260  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatFor1(null, ((RTExpression)(yystack.valueAt (8-(4)))), ((RTExpression)(yystack.valueAt (8-(6)))), ((RTStatement)(yystack.valueAt (8-(8))))), (yyloc)); };
  break;
    

  case 57:
  if (yyn == 57)
    /* "VondaGrammar.y":262  */ /* lalr1.java:489  */
    {
    ExpVariable var = setPos(new ExpVariable((( String )(yystack.valueAt (7-(3))))), yystack.locationAt (7-(3)));
    yyval = setPos(new StatFor2(var, ((RTExpression)(yystack.valueAt (7-(5)))), ((RTStatement)(yystack.valueAt (7-(7))))), (yyloc));
  };
  break;
    

  case 58:
  if (yyn == 58)
    /* "VondaGrammar.y":266  */ /* lalr1.java:489  */
    {
    ExpVariable var = setPos(new ExpVariable((( String )(yystack.valueAt (8-(4))))), yystack.locationAt (8-(4)));
    yyval = setPos(new StatFor2(((Type)(yystack.valueAt (8-(3)))), var, ((RTExpression)(yystack.valueAt (8-(6)))), ((RTStatement)(yystack.valueAt (8-(8))))), (yyloc));
  };
  break;
    

  case 59:
  if (yyn == 59)
    /* "VondaGrammar.y":275  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatVarDef(true, new Type(null), (( String )(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(2))))), (yyloc));
  };
  break;
    

  case 60:
  if (yyn == 60)
    /* "VondaGrammar.y":278  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatVarDef(false, ((Type)(yystack.valueAt (4-(1)))), (( String )(yystack.valueAt (4-(2)))), ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc));
  };
  break;
    

  case 61:
  if (yyn == 61)
    /* "VondaGrammar.y":284  */ /* lalr1.java:489  */
    { yyval = setPos(new StatPropose(((RTExpression)(yystack.valueAt (5-(3)))), ((StatAbstractBlock)(yystack.valueAt (5-(5))))), (yyloc)); };
  break;
    

  case 62:
  if (yyn == 62)
    /* "VondaGrammar.y":288  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatTimeout(((RTExpression)(yystack.valueAt (7-(5)))), ((RTExpression)(yystack.valueAt (7-(3)))), ((StatAbstractBlock)(yystack.valueAt (7-(7))))), (yyloc));
  };
  break;
    

  case 63:
  if (yyn == 63)
    /* "VondaGrammar.y":294  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatTimeout(((RTExpression)(yystack.valueAt (7-(3)))), ((RTExpression)(yystack.valueAt (7-(5)))), ((StatAbstractBlock)(yystack.valueAt (7-(7))))), (yyloc));
  };
  break;
    

  case 64:
  if (yyn == 64)
    /* "VondaGrammar.y":300  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatSwitch(((RTExpression)(yystack.valueAt (5-(3)))), ((StatAbstractBlock)(yystack.valueAt (5-(5))))), (yyloc));
  };
  break;
    

  case 65:
  if (yyn == 65)
    /* "VondaGrammar.y":326  */ /* lalr1.java:489  */
    {
    ExpSingleValue val =
      new ExpSingleValue("case \"" + (( ExpSingleValue )(yystack.valueAt (3-(2)))).toString() + "\":", "label");
    RTStatement lbl = val.ensureStatement();
    setPos(val, (yyloc)); setPos(lbl, (yyloc));
    yyval = lbl;
  };
  break;
    

  case 66:
  if (yyn == 66)
    /* "VondaGrammar.y":333  */ /* lalr1.java:489  */
    {
    ExpSingleValue val =
      new ExpSingleValue("case " + (( ExpSingleValue )(yystack.valueAt (3-(2)))).toString() + ":", "label");
    RTStatement lbl = val.ensureStatement();
    setPos(val, (yyloc)); setPos(lbl, (yyloc));
    yyval = lbl;
  };
  break;
    

  case 67:
  if (yyn == 67)
    /* "VondaGrammar.y":340  */ /* lalr1.java:489  */
    {
    ExpSingleValue val =
      new ExpSingleValue("case " + (( ExpSingleValue )(yystack.valueAt (3-(2)))).toString() + ":", "label");
    RTStatement lbl = val.ensureStatement();
    setPos(val, (yyloc)); setPos(lbl, (yyloc));
    yyval = lbl;
  };
  break;
    

  case 68:
  if (yyn == 68)
    /* "VondaGrammar.y":347  */ /* lalr1.java:489  */
    {
    ExpSingleValue val =
      new ExpSingleValue("case " + (( String )(yystack.valueAt (3-(2)))) + ":", "label");
    RTStatement lbl = val.ensureStatement();
    setPos(val, (yyloc)); setPos(lbl, (yyloc));
    yyval = lbl;
  };
  break;
    

  case 69:
  if (yyn == 69)
    /* "VondaGrammar.y":354  */ /* lalr1.java:489  */
    {
    ExpSingleValue val = new ExpSingleValue("default:", "label");
    RTStatement lbl = val.ensureStatement();
    setPos(val, (yyloc)); setPos(lbl, (yyloc));
    yyval = lbl;
  };
  break;
    

  case 70:
  if (yyn == 70)
    /* "VondaGrammar.y":363  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatVarDef(true, new Type(null), (( String )(yystack.valueAt (4-(2)))), ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc));
  };
  break;
    

  case 71:
  if (yyn == 71)
    /* "VondaGrammar.y":366  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatVarDef(false, ((Type)(yystack.valueAt (4-(1)))), (( String )(yystack.valueAt (4-(2)))), ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc));
  };
  break;
    

  case 72:
  if (yyn == 72)
    /* "VondaGrammar.y":369  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatVarDef(true, ((Type)(yystack.valueAt (5-(2)))), (( String )(yystack.valueAt (5-(3)))), ((RTExpression)(yystack.valueAt (5-(4))))), (yyloc));
  };
  break;
    

  case 73:
  if (yyn == 73)
    /* "VondaGrammar.y":372  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatVarDef(true, new Type(null), (( String )(yystack.valueAt (3-(2)))), null), (yyloc));
  };
  break;
    

  case 74:
  if (yyn == 74)
    /* "VondaGrammar.y":375  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatVarDef(false, ((Type)(yystack.valueAt (3-(1)))), (( String )(yystack.valueAt (3-(2)))), null), (yyloc));
  };
  break;
    

  case 75:
  if (yyn == 75)
    /* "VondaGrammar.y":378  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatVarDef(true, ((Type)(yystack.valueAt (4-(2)))), (( String )(yystack.valueAt (4-(3)))), null), (yyloc));
  };
  break;
    

  case 76:
  if (yyn == 76)
    /* "VondaGrammar.y":384  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (2-(2)))); };
  break;
    

  case 77:
  if (yyn == 77)
    /* "VondaGrammar.y":385  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpListLiteral(new LinkedList<RTExpression>()), yystack.locationAt (3-(2)), yystack.locationAt (3-(3)));
  };
  break;
    

  case 78:
  if (yyn == 78)
    /* "VondaGrammar.y":388  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpListLiteral(((LinkedList<RTExpression>)(yystack.valueAt (4-(3))))), yystack.locationAt (4-(2)), yystack.locationAt (4-(4)));
  };
  break;
    

  case 79:
  if (yyn == 79)
    /* "VondaGrammar.y":394  */ /* lalr1.java:489  */
    { yyval = new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (1-(1))))); }}; };
  break;
    

  case 80:
  if (yyn == 80)
    /* "VondaGrammar.y":395  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))); ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))).addFirst(((RTExpression)(yystack.valueAt (3-(1))))); };
  break;
    

  case 81:
  if (yyn == 81)
    /* "VondaGrammar.y":400  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatMethodDeclaration("public", ((Type)(yystack.valueAt (10-(5)))), ((Type)(yystack.valueAt (10-(2)))), (( String )(yystack.valueAt (10-(6)))), ((LinkedList)(yystack.valueAt (10-(8)))), ((StatAbstractBlock)(yystack.valueAt (10-(10))))), (yyloc));
  };
  break;
    

  case 82:
  if (yyn == 82)
    /* "VondaGrammar.y":403  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatMethodDeclaration("public", ((Type)(yystack.valueAt (6-(1)))), null, (( String )(yystack.valueAt (6-(2)))), ((LinkedList)(yystack.valueAt (6-(4)))), ((StatAbstractBlock)(yystack.valueAt (6-(6))))), (yyloc));
  };
  break;
    

  case 83:
  if (yyn == 83)
    /* "VondaGrammar.y":409  */ /* lalr1.java:489  */
    { yyval = ((StatAbstractBlock)(yystack.valueAt (1-(1)))); };
  break;
    

  case 84:
  if (yyn == 84)
    /* "VondaGrammar.y":410  */ /* lalr1.java:489  */
    { yyval = null; };
  break;
    

  case 85:
  if (yyn == 85)
    /* "VondaGrammar.y":414  */ /* lalr1.java:489  */
    { yyval = ((LinkedList)(yystack.valueAt (1-(1)))); };
  break;
    

  case 86:
  if (yyn == 86)
    /* "VondaGrammar.y":415  */ /* lalr1.java:489  */
    { yyval = new LinkedList(); };
  break;
    

  case 87:
  if (yyn == 87)
    /* "VondaGrammar.y":419  */ /* lalr1.java:489  */
    { yyval = new LinkedList(){{ add((( String )(yystack.valueAt (1-(1))))); }}; };
  break;
    

  case 88:
  if (yyn == 88)
    /* "VondaGrammar.y":420  */ /* lalr1.java:489  */
    { yyval = new LinkedList(){{ add(((Type)(yystack.valueAt (2-(1))))); add((( String )(yystack.valueAt (2-(2))))); }}; };
  break;
    

  case 89:
  if (yyn == 89)
    /* "VondaGrammar.y":421  */ /* lalr1.java:489  */
    { yyval = ((LinkedList)(yystack.valueAt (3-(3)))); ((LinkedList)(yystack.valueAt (3-(3)))).addFirst((( String )(yystack.valueAt (3-(1))))); };
  break;
    

  case 90:
  if (yyn == 90)
    /* "VondaGrammar.y":422  */ /* lalr1.java:489  */
    {
    yyval = ((LinkedList)(yystack.valueAt (4-(4)))); ((LinkedList)(yystack.valueAt (4-(4)))).addFirst((( String )(yystack.valueAt (4-(2))))); ((LinkedList)(yystack.valueAt (4-(4)))).addFirst(((Type)(yystack.valueAt (4-(1)))));
  };
  break;
    

  case 91:
  if (yyn == 91)
    /* "VondaGrammar.y":430  */ /* lalr1.java:489  */
    {
    ExpVariable var = setPos(new ExpVariable((( String )(yystack.valueAt (4-(1))))), yystack.locationAt (4-(1)));
    yyval = setPos(new StatSetOperation(var, true, ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc));
  };
  break;
    

  case 92:
  if (yyn == 92)
    /* "VondaGrammar.y":434  */ /* lalr1.java:489  */
    {
    ExpVariable var = setPos(new ExpVariable((( String )(yystack.valueAt (4-(1))))), yystack.locationAt (4-(1)));
    yyval = setPos(new StatSetOperation(var, false, ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc));
  };
  break;
    

  case 93:
  if (yyn == 93)
    /* "VondaGrammar.y":438  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatSetOperation(((RTExpression)(yystack.valueAt (4-(1)))), true, ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc));
  };
  break;
    

  case 94:
  if (yyn == 94)
    /* "VondaGrammar.y":441  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatSetOperation(((RTExpression)(yystack.valueAt (4-(1)))), false, ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc));
  };
  break;
    

  case 95:
  if (yyn == 95)
    /* "VondaGrammar.y":444  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatSetOperation(((RTExpression)(yystack.valueAt (4-(1)))), true, ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc));
  };
  break;
    

  case 96:
  if (yyn == 96)
    /* "VondaGrammar.y":447  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatSetOperation(((RTExpression)(yystack.valueAt (4-(1)))), false, ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc));
  };
  break;
    

  case 97:
  if (yyn == 97)
    /* "VondaGrammar.y":456  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpFuncCall((( String )(yystack.valueAt (3-(1)))), new LinkedList<RTExpression>(), false), (yyloc));
  };
  break;
    

  case 98:
  if (yyn == 98)
    /* "VondaGrammar.y":459  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpFuncCall((( String )(yystack.valueAt (4-(1)))), ((LinkedList<RTExpression>)(yystack.valueAt (4-(3)))), false), (yyloc));
  };
  break;
    

  case 99:
  if (yyn == 99)
    /* "VondaGrammar.y":465  */ /* lalr1.java:489  */
    { yyval = new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (1-(1))))); }}; };
  break;
    

  case 100:
  if (yyn == 100)
    /* "VondaGrammar.y":466  */ /* lalr1.java:489  */
    { yyval = new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (1-(1))))); }}; };
  break;
    

  case 101:
  if (yyn == 101)
    /* "VondaGrammar.y":467  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))); ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))).addFirst(((RTExpression)(yystack.valueAt (3-(1))))); };
  break;
    

  case 102:
  if (yyn == 102)
    /* "VondaGrammar.y":468  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))); ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))).addFirst(((RTExpression)(yystack.valueAt (3-(1))))); };
  break;
    

  case 103:
  if (yyn == 103)
    /* "VondaGrammar.y":472  */ /* lalr1.java:489  */
    { yyval = new Type("Array", new Type((( String )(yystack.valueAt (3-(1)))))); };
  break;
    

  case 104:
  if (yyn == 104)
    /* "VondaGrammar.y":473  */ /* lalr1.java:489  */
    { yyval = new Type((( String )(yystack.valueAt (1-(1))))); };
  break;
    

  case 105:
  if (yyn == 105)
    /* "VondaGrammar.y":474  */ /* lalr1.java:489  */
    {
    yyval = new Type((( String )(yystack.valueAt (4-(1)))), ((LinkedList<Type>)(yystack.valueAt (4-(3)))).toArray(new Type[((LinkedList<Type>)(yystack.valueAt (4-(3)))).size()]));
  };
  break;
    

  case 106:
  if (yyn == 106)
    /* "VondaGrammar.y":480  */ /* lalr1.java:489  */
    { yyval = new LinkedList<Type>(){{ add(((Type)(yystack.valueAt (1-(1))))); }}; };
  break;
    

  case 107:
  if (yyn == 107)
    /* "VondaGrammar.y":481  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<Type>)(yystack.valueAt (3-(3)))); ((LinkedList<Type>)(yystack.valueAt (3-(3)))).addFirst(((Type)(yystack.valueAt (3-(1))))); };
  break;
    

  case 108:
  if (yyn == 108)
    /* "VondaGrammar.y":485  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "||"), (yyloc));
  };
  break;
    

  case 109:
  if (yyn == 109)
    /* "VondaGrammar.y":488  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 110:
  if (yyn == 110)
    /* "VondaGrammar.y":492  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "&&"), (yyloc));
  };
  break;
    

  case 111:
  if (yyn == 111)
    /* "VondaGrammar.y":495  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 112:
  if (yyn == 112)
    /* "VondaGrammar.y":499  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 113:
  if (yyn == 113)
    /* "VondaGrammar.y":500  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "|"), (yyloc));
  };
  break;
    

  case 114:
  if (yyn == 114)
    /* "VondaGrammar.y":506  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 115:
  if (yyn == 115)
    /* "VondaGrammar.y":507  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "^"), (yyloc));
  };
  break;
    

  case 116:
  if (yyn == 116)
    /* "VondaGrammar.y":513  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 117:
  if (yyn == 117)
    /* "VondaGrammar.y":514  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "&"), (yyloc));
  };
  break;
    

  case 118:
  if (yyn == 118)
    /* "VondaGrammar.y":520  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 119:
  if (yyn == 119)
    /* "VondaGrammar.y":521  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "=="), (yyloc));
  };
  break;
    

  case 120:
  if (yyn == 120)
    /* "VondaGrammar.y":524  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "!="), (yyloc));
  };
  break;
    

  case 121:
  if (yyn == 121)
    /* "VondaGrammar.y":530  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 122:
  if (yyn == 122)
    /* "VondaGrammar.y":531  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "<"), (yyloc));
  };
  break;
    

  case 123:
  if (yyn == 123)
    /* "VondaGrammar.y":534  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), ">"), (yyloc));
  };
  break;
    

  case 124:
  if (yyn == 124)
    /* "VondaGrammar.y":537  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), ">="), (yyloc));
  };
  break;
    

  case 125:
  if (yyn == 125)
    /* "VondaGrammar.y":540  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "<="), (yyloc));
  };
  break;
    

  case 126:
  if (yyn == 126)
    /* "VondaGrammar.y":546  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 127:
  if (yyn == 127)
    /* "VondaGrammar.y":547  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "+"), (yyloc));
  };
  break;
    

  case 128:
  if (yyn == 128)
    /* "VondaGrammar.y":550  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "-"), (yyloc));
  };
  break;
    

  case 129:
  if (yyn == 129)
    /* "VondaGrammar.y":556  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 130:
  if (yyn == 130)
    /* "VondaGrammar.y":557  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "*"), (yyloc));
  };
  break;
    

  case 131:
  if (yyn == 131)
    /* "VondaGrammar.y":560  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "/"), (yyloc));
  };
  break;
    

  case 132:
  if (yyn == 132)
    /* "VondaGrammar.y":563  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "%"), (yyloc));
  };
  break;
    

  case 133:
  if (yyn == 133)
    /* "VondaGrammar.y":569  */ /* lalr1.java:489  */
    {
    yyval = createPlusMinus(((RTExpression)(yystack.valueAt (2-(2)))), "+", (yyloc));
  };
  break;
    

  case 134:
  if (yyn == 134)
    /* "VondaGrammar.y":572  */ /* lalr1.java:489  */
    {
    yyval = createPlusMinus(((RTExpression)(yystack.valueAt (2-(2)))), "-", (yyloc));
  };
  break;
    

  case 135:
  if (yyn == 135)
    /* "VondaGrammar.y":575  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2-(2)))), null, "+"), (yyloc)); };
  break;
    

  case 136:
  if (yyn == 136)
    /* "VondaGrammar.y":576  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2-(2)))), null, "-"), (yyloc)); };
  break;
    

  case 137:
  if (yyn == 137)
    /* "VondaGrammar.y":577  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 138:
  if (yyn == 138)
    /* "VondaGrammar.y":581  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 139:
  if (yyn == 139)
    /* "VondaGrammar.y":582  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpCast(((Type)(yystack.valueAt (4-(2)))), ((RTExpression)(yystack.valueAt (4-(4))))), (yyloc)); };
  break;
    

  case 140:
  if (yyn == 140)
    /* "VondaGrammar.y":587  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 141:
  if (yyn == 141)
    /* "VondaGrammar.y":588  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (2-(2)))), null, "!"), (yyloc)); };
  break;
    

  case 142:
  if (yyn == 142)
    /* "VondaGrammar.y":589  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2-(2)))), null, "~"), (yyloc)); };
  break;
    

  case 143:
  if (yyn == 143)
    /* "VondaGrammar.y":593  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 144:
  if (yyn == 144)
    /* "VondaGrammar.y":594  */ /* lalr1.java:489  */
    {
    yyval = createPlusMinus(((RTExpression)(yystack.valueAt (2-(1)))), "+", (yyloc));
  };
  break;
    

  case 145:
  if (yyn == 145)
    /* "VondaGrammar.y":597  */ /* lalr1.java:489  */
    {
    yyval = createPlusMinus(((RTExpression)(yystack.valueAt (2-(1)))), "-", (yyloc));
  };
  break;
    

  case 146:
  if (yyn == 146)
    /* "VondaGrammar.y":603  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpSingleValue("null", "null"), (yyloc)); };
  break;
    

  case 147:
  if (yyn == 147)
    /* "VondaGrammar.y":604  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 148:
  if (yyn == 148)
    /* "VondaGrammar.y":605  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 149:
  if (yyn == 149)
    /* "VondaGrammar.y":609  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpVariable((( String )(yystack.valueAt (1-(1))))), (yyloc)); };
  break;
    

  case 150:
  if (yyn == 150)
    /* "VondaGrammar.y":610  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpCast(((Type)(yystack.valueAt (6-(3)))), ((RTExpression)(yystack.valueAt (6-(5))))), (yyloc)); };
  break;
    

  case 151:
  if (yyn == 151)
    /* "VondaGrammar.y":614  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (3-(2)))); ((RTExpression)(yystack.valueAt (3-(2)))).generateParens(); };
  break;
    

  case 152:
  if (yyn == 152)
    /* "VondaGrammar.y":615  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 153:
  if (yyn == 153)
    /* "VondaGrammar.y":619  */ /* lalr1.java:489  */
    { yyval = ((ExpSingleValue)(yystack.valueAt (1-(1)))); };
  break;
    

  case 154:
  if (yyn == 154)
    /* "VondaGrammar.y":620  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 155:
  if (yyn == 155)
    /* "VondaGrammar.y":621  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 156:
  if (yyn == 156)
    /* "VondaGrammar.y":622  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 157:
  if (yyn == 157)
    /* "VondaGrammar.y":623  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 158:
  if (yyn == 158)
    /* "VondaGrammar.y":627  */ /* lalr1.java:489  */
    { yyval = setPos((( ExpSingleValue )(yystack.valueAt (1-(1)))), (yyloc)); };
  break;
    

  case 159:
  if (yyn == 159)
    /* "VondaGrammar.y":628  */ /* lalr1.java:489  */
    { yyval = setPos((( ExpSingleValue )(yystack.valueAt (1-(1)))), (yyloc)); };
  break;
    

  case 160:
  if (yyn == 160)
    /* "VondaGrammar.y":629  */ /* lalr1.java:489  */
    { yyval = setPos((( ExpSingleValue )(yystack.valueAt (1-(1)))), (yyloc)); };
  break;
    

  case 161:
  if (yyn == 161)
    /* "VondaGrammar.y":630  */ /* lalr1.java:489  */
    { yyval = setPos((( ExpSingleValue )(yystack.valueAt (1-(1)))), (yyloc)); };
  break;
    

  case 162:
  if (yyn == 162)
    /* "VondaGrammar.y":634  */ /* lalr1.java:489  */
    {
    ExpVariable var = setPos(new ExpVariable((( String )(yystack.valueAt (4-(1))))), yystack.locationAt (4-(1)));
    yyval = setPos(new ExpArrayAccess(var, ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc));
  };
  break;
    

  case 163:
  if (yyn == 163)
    /* "VondaGrammar.y":638  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpArrayAccess(((RTExpression)(yystack.valueAt (4-(1)))), ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc)); };
  break;
    

  case 164:
  if (yyn == 164)
    /* "VondaGrammar.y":642  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpConditional(((RTExpression)(yystack.valueAt (5-(1)))), ((RTExpression)(yystack.valueAt (5-(3)))), ((RTExpression)(yystack.valueAt (5-(5))))), (yyloc)); };
  break;
    

  case 165:
  if (yyn == 165)
    /* "VondaGrammar.y":643  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 166:
  if (yyn == 166)
    /* "VondaGrammar.y":649  */ /* lalr1.java:489  */
    {
    ExpVariable var = setPos(new ExpVariable((( String )(yystack.valueAt (2-(1))))), yystack.locationAt (2-(1)));
    yyval = setPos(new ExpAssignment(var, ((RTExpression)(yystack.valueAt (2-(2))))), (yyloc));
  };
  break;
    

  case 167:
  if (yyn == 167)
    /* "VondaGrammar.y":653  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpAssignment(((RTExpression)(yystack.valueAt (2-(1)))), ((RTExpression)(yystack.valueAt (2-(2))))), (yyloc)); };
  break;
    

  case 168:
  if (yyn == 168)
    /* "VondaGrammar.y":654  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpAssignment(((RTExpression)(yystack.valueAt (2-(1)))), ((RTExpression)(yystack.valueAt (2-(2))))), (yyloc)); };
  break;
    

  case 169:
  if (yyn == 169)
    /* "VondaGrammar.y":658  */ /* lalr1.java:489  */
    {
    //List<String> repr = new ArrayList<>($2.size());
    //for (int i = 0; i < $2.size(); ++i) repr.add("");
    //$$ = setPos(new ExpFieldAccess($2, repr), @$); $2.addFirst($1);
    yyval = setPos(new ExpFieldAccess(((LinkedList<RTExpression>)(yystack.valueAt (2-(2))))), (yyloc)); ((LinkedList<RTExpression>)(yystack.valueAt (2-(2)))).addFirst(((RTExpression)(yystack.valueAt (2-(1)))));
  };
  break;
    

  case 170:
  if (yyn == 170)
    /* "VondaGrammar.y":664  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpFieldAccess(((LinkedList<RTExpression>)(yystack.valueAt (2-(2))))), (yyloc)); ((LinkedList<RTExpression>)(yystack.valueAt (2-(2)))).addFirst(setPos((( ExpSingleValue )(yystack.valueAt (2-(1)))), (yyloc)));
  };
  break;
    

  case 171:
  if (yyn == 171)
    /* "VondaGrammar.y":667  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpFieldAccess(((LinkedList<RTExpression>)(yystack.valueAt (2-(2))))), (yyloc)); ((LinkedList<RTExpression>)(yystack.valueAt (2-(2)))).addFirst(((RTExpression)(yystack.valueAt (2-(1))))); };
  break;
    

  case 172:
  if (yyn == 172)
    /* "VondaGrammar.y":672  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))); ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))).addFirst(((RTExpression)(yystack.valueAt (3-(2))))); };
  break;
    

  case 173:
  if (yyn == 173)
    /* "VondaGrammar.y":673  */ /* lalr1.java:489  */
    { yyval = new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (2-(2))))); }}; };
  break;
    

  case 174:
  if (yyn == 174)
    /* "VondaGrammar.y":677  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpVariable((( String )(yystack.valueAt (1-(1))))), (yyloc)); };
  break;
    

  case 175:
  if (yyn == 175)
    /* "VondaGrammar.y":678  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 176:
  if (yyn == 176)
    /* "VondaGrammar.y":679  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (3-(2)))); };
  break;
    

  case 177:
  if (yyn == 177)
    /* "VondaGrammar.y":683  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpNew(new Type((( String )(yystack.valueAt (2-(2)))))), (yyloc)); };
  break;
    

  case 178:
  if (yyn == 178)
    /* "VondaGrammar.y":684  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpNew(new Type((( String )(yystack.valueAt (4-(2))))), new LinkedList<>()), (yyloc));
  };
  break;
    

  case 179:
  if (yyn == 179)
    /* "VondaGrammar.y":687  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpNew(new Type((( String )(yystack.valueAt (5-(2))))), ((LinkedList<RTExpression>)(yystack.valueAt (5-(4))))), (yyloc));
  };
  break;
    

  case 180:
  if (yyn == 180)
    /* "VondaGrammar.y":690  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpNew(new Type("Array", new Type((( String )(yystack.valueAt (5-(2)))))),
                    new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (5-(4))))); }}), (yyloc));
  };
  break;
    

  case 181:
  if (yyn == 181)
    /* "VondaGrammar.y":694  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpNew(new Type("Array", new Type((( String )(yystack.valueAt (7-(2)))))),
                    new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (7-(6))))); }}), (yyloc));
  };
  break;
    

  case 182:
  if (yyn == 182)
    /* "VondaGrammar.y":698  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpNew(new Type((( String )(yystack.valueAt (7-(2)))), ((LinkedList<Type>)(yystack.valueAt (7-(4)))).toArray(new Type[((LinkedList<Type>)(yystack.valueAt (7-(4)))).size()])),
                    new LinkedList<>()), (yyloc));
  };
  break;
    

  case 183:
  if (yyn == 183)
    /* "VondaGrammar.y":702  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpNew(new Type((( String )(yystack.valueAt (8-(2)))), ((LinkedList<Type>)(yystack.valueAt (8-(4)))).toArray(new Type[((LinkedList<Type>)(yystack.valueAt (8-(4)))).size()])),
                    ((LinkedList<RTExpression>)(yystack.valueAt (8-(7))))), (yyloc));
  };
  break;
    

  case 184:
  if (yyn == 184)
    /* "VondaGrammar.y":709  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpLambda(((LinkedList)(yystack.valueAt (5-(2)))), ((RTExpression)(yystack.valueAt (5-(5))))), (yyloc));
  };
  break;
    

  case 185:
  if (yyn == 185)
    /* "VondaGrammar.y":712  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpLambda(((LinkedList)(yystack.valueAt (5-(2)))), ((StatAbstractBlock)(yystack.valueAt (5-(5))))), (yyloc));
  };
  break;
    

  case 186:
  if (yyn == 186)
    /* "VondaGrammar.y":719  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpDialogueAct(((RTExpression)(yystack.valueAt (6-(2)))), ((RTExpression)(yystack.valueAt (6-(4)))), ((LinkedList<RTExpression>)(yystack.valueAt (6-(5))))), (yyloc));
  };
  break;
    

  case 187:
  if (yyn == 187)
    /* "VondaGrammar.y":725  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (3-(2)))); };
  break;
    

  case 188:
  if (yyn == 188)
    /* "VondaGrammar.y":726  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpVariable((( String )(yystack.valueAt (1-(1))))), (yyloc)); };
  break;
    

  case 189:
  if (yyn == 189)
    /* "VondaGrammar.y":727  */ /* lalr1.java:489  */
    { yyval = setPos((( ExpSingleValue )(yystack.valueAt (1-(1)))), (yyloc)); };
  break;
    

  case 190:
  if (yyn == 190)
    /* "VondaGrammar.y":728  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpSingleValue((( String )(yystack.valueAt (1-(1)))), "String"), (yyloc)); };
  break;
    

  case 191:
  if (yyn == 191)
    /* "VondaGrammar.y":732  */ /* lalr1.java:489  */
    {
    yyval = ((LinkedList<RTExpression>)(yystack.valueAt (5-(5)))); ((LinkedList<RTExpression>)(yystack.valueAt (5-(5)))).addFirst(((RTExpression)(yystack.valueAt (5-(4))))); ((LinkedList<RTExpression>)(yystack.valueAt (5-(5)))).addFirst(((RTExpression)(yystack.valueAt (5-(2)))));
  };
  break;
    

  case 192:
  if (yyn == 192)
    /* "VondaGrammar.y":735  */ /* lalr1.java:489  */
    { yyval = new LinkedList<RTExpression>(); };
  break;
    

  case 193:
  if (yyn == 193)
    /* "VondaGrammar.y":739  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 194:
  if (yyn == 194)
    /* "VondaGrammar.y":740  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 195:
  if (yyn == 195)
    /* "VondaGrammar.y":741  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    


/* "VondaGrammar.java":2056  */ /* lalr1.java:489  */
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

  private static final short yypact_ninf_ = -333;
  private static final short yytable_ninf_ = -150;

  /* YYPACT[STATE-NUM] -- Index in YYTABLE of the portion describing
   STATE-NUM.  */
  private static final short yypact_[] = yypact_init();
  private static final short[] yypact_init()
  {
    return new short[]
    {
     906,    63,   -33,   -16,   218,   -12,    -6,   956,     5,    10,
      52,    66,  -333,    77,  -333,  -333,   379,    83,    88,   126,
     135,  1415,  1415,    76,  -333,  -333,   275,  -333,   797,  1246,
     112,   110,   187,     3,   906,   906,  -333,  -333,  -333,  -333,
    -333,  -333,  -333,  -333,  -333,  -333,  -333,   906,   906,  -333,
     732,   118,    76,   156,  -333,  -333,   -22,   147,   101,  -333,
     169,  -333,  -333,  -333,   167,   176,   182,   204,  -333,  -333,
    -333,   195,  -333,    -7,   207,   -28,   221,   115,  1280,  -333,
      25,  1280,   227,  -333,  1415,  1415,   128,  -333,  1314,  1429,
    1429,  1415,  1415,    76,   -21,   255,   220,   232,   233,   141,
      54,   201,    80,  -333,  -333,  -333,   165,  -333,    76,   156,
     239,  -333,  -333,   239,  -333,   250,  1280,  1280,  1280,  1280,
     -32,   252,  -333,  -333,   257,    11,  -333,  1280,  1280,   279,
     507,   835,   659,   112,  -333,  -333,  -333,   956,   256,  1314,
     254,   111,   245,  -333,  -333,  -333,  1280,   258,  -333,   906,
     906,  -333,  -333,  -333,  -333,  -333,  -333,  -333,   -26,  -333,
    1280,  1280,  1280,  -333,  -333,  1280,  1280,  -333,  -333,  -333,
    -333,  -333,  -333,  -333,   262,    30,  -333,   251,   268,    68,
     159,  1280,   970,   272,   265,  -333,   280,   267,    39,  -333,
    -333,  1280,   173,   271,  -333,  -333,  -333,  -333,  1429,  1280,
    1429,  1429,  1429,  1429,  1429,  1429,  1429,  1429,  1429,  1429,
    1429,  1429,  1429,  1429,  1429,  -333,  -333,  -333,   276,   274,
     281,   283,  -333,  -333,   282,  1280,  -333,    76,   292,   293,
    -333,  1348,  -333,   294,   295,   297,  1004,  -333,  -333,   289,
     298,   299,  -333,  -333,   300,  -333,   306,   307,   110,  -333,
    -333,  -333,   311,   312,   304,   315,   316,   317,   318,  1280,
    -333,  -333,   319,  1280,   320,   323,  1038,   324,    23,   956,
    -333,   325,  1073,  1108,   112,  1429,   255,   326,   220,   232,
     233,   141,    54,    54,   201,   201,   201,   201,    80,    80,
    -333,  -333,  -333,   325,  1280,  1280,   956,   327,  -333,  -333,
    -333,   143,   329,  -333,   105,  -333,  1382,  1382,  -333,   328,
     330,  -333,   112,  -333,  1429,   112,  -333,   331,    60,   333,
     334,  -333,  -333,  -333,  -333,  -333,  -333,   339,  -333,   340,
    -333,  1280,   956,   341,  1142,  1280,   336,   381,  -333,  -333,
     343,   345,   349,   348,  -333,  1280,  -333,   347,   359,  -333,
    -333,   311,   383,   360,  -333,  -333,  -333,  1280,  -333,   362,
     371,   110,   364,   164,  -333,   956,   368,  -333,   956,   956,
     374,   375,  -333,   956,  -333,  1280,  -333,   378,  -333,   325,
     325,  -333,  1177,   311,  -333,  -333,   382,   380,  -333,  -333,
    -333,  -333,  -333,   956,  -333,  -333,   956,   956,  -333,   384,
    1211,  -333,  -333,  -333,  -333,  -333,   311,   110,  -333,  -333,
    -333,  -333,  -333,   386,   387,   331,  -333,   164,  -333,  -333
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
       9,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,    12,     0,    11,    10,     0,     0,     0,     0,
       0,     0,     0,   158,   159,   161,   104,   160,     0,     0,
       0,     0,     0,     0,     9,     9,    16,    22,    24,    28,
      29,    30,    25,    27,    26,    31,    32,     9,     9,    23,
       9,     0,     0,     0,   152,   153,   154,     0,   155,   157,
       0,    43,    45,    46,     0,     0,     0,     0,    47,    69,
      33,     0,    34,   156,     0,   104,     0,     0,     0,    14,
       0,     0,     0,   146,     0,     0,   149,    41,     0,     0,
       0,     0,     0,   156,   165,   109,   111,   112,   114,   116,
     118,   121,   126,   138,   129,   137,   140,   143,   147,   148,
     154,   193,   194,   155,   195,     0,     0,     0,     0,     0,
     149,     0,   154,   155,     0,     0,   170,     0,     0,     0,
       0,     0,     0,     0,   166,    37,    35,    38,     0,     0,
       0,   104,     0,   189,   190,   188,     0,     0,     1,     9,
       9,     6,     5,     8,     3,    21,     4,   171,     0,   169,
       0,     0,     0,   168,    17,     0,     0,    18,   167,    44,
      65,    66,    67,    68,     0,     0,    73,     0,     0,     0,
     104,     0,     0,     0,     0,    13,     0,     0,   177,   134,
     133,     0,   149,     0,   135,   136,   141,   142,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,   145,   144,    42,     0,     0,
       0,     0,    20,    19,   174,     0,   175,   173,     0,     0,
      40,    86,    97,     0,   100,    99,     0,    76,   103,     0,
     106,     0,    39,    36,     0,   151,     0,     0,     0,     7,
       2,    74,    86,     0,     0,     0,     0,     0,     0,     0,
      70,    75,     0,     0,     0,     0,     0,     0,     0,     0,
      15,     0,     0,     0,     0,     0,   108,     0,   110,   113,
     115,   117,   119,   120,   124,   125,   122,   123,   127,   128,
     130,   131,   132,     0,     0,     0,     0,     0,   172,    92,
      91,   149,     0,    85,     0,    98,     0,     0,    77,     0,
      79,   162,     0,   105,     0,     0,   187,   192,    87,     0,
       0,    71,   163,    94,    93,    96,    95,     0,    72,     0,
      59,     0,     0,     0,     0,     0,     0,    48,    61,   178,
       0,     0,     0,     0,   139,     0,    64,     0,     0,    50,
     176,     0,     0,    88,   102,   101,    78,     0,   107,   138,
       0,     0,     0,     0,    51,     0,     0,    55,     0,     0,
       0,     0,    60,     0,   179,     0,   180,     0,   164,     0,
       0,    89,     0,     0,    80,   150,     0,     0,   186,    84,
      83,    82,    57,     0,    53,    54,     0,     0,    49,     0,
       0,    63,    62,   185,   184,    90,    86,     0,    56,    52,
      58,   181,   182,     0,     0,   192,   183,     0,   191,    81
    };
  }

/* YYPGOTO[NTERM-NUM].  */
  private static final short yypgoto_[] = yypgoto_init();
  private static final short[] yypgoto_init()
  {
    return new short[]
    {
    -333,    55,  -333,  -333,  -333,    49,   108,  -333,  -250,   305,
    -333,  -333,   303,  -333,  -333,  -333,  -333,  -333,  -333,  -333,
    -333,    28,   -18,  -263,   408,    31,  -246,  -332,  -333,   138,
     -42,     0,  -256,  -333,   247,   266,   264,   269,   277,    81,
      72,    62,   -20,   -86,  -333,  -333,  -333,   168,   219,  -333,
    -333,    32,  -333,   337,   249,   -10,  -333,  -333,  -333,  -333,
    -243,    53,   522
    };
  }

/* YYDEFGOTO[NTERM-NUM].  */
  private static final short yydefgoto_[] = yydefgoto_init();
  private static final short[] yydefgoto_init()
  {
    return new short[]
    {
      -1,    32,    33,    34,    80,    70,   136,   137,    36,   138,
      37,    38,    39,    40,    41,   182,    42,    43,    44,    45,
      46,    72,   134,   309,    48,   391,   302,   303,    49,    93,
     233,    74,   241,    94,    95,    96,    97,    98,    99,   100,
     101,   102,   103,   104,   105,   106,   107,   108,   109,    54,
      55,   110,   111,   112,   113,   157,   227,   114,   234,    59,
     147,   362,   140
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
      51,   121,   124,   194,   195,   317,   319,   198,    76,   340,
      62,   161,   162,   126,     8,   176,   130,   251,   343,   381,
     191,   338,   252,   131,   177,   131,   133,    63,    47,   131,
     142,    68,    56,    51,    51,    51,   155,   125,   163,    56,
     168,    69,   159,   346,   141,   199,    75,    51,    51,    35,
      51,   405,   224,   122,   122,    30,   358,   178,    77,   225,
      56,   149,    47,    47,   189,   190,    56,    56,   185,   186,
     335,   196,   197,   251,   131,    47,    47,   183,    47,    56,
      56,   131,    56,    35,    35,   206,   207,   272,   193,   151,
     152,   273,   163,   274,   384,   168,    35,    35,   159,    35,
      78,  -104,   153,   154,    60,   156,    61,    79,   208,   209,
     351,   261,   177,   390,   133,    71,   122,   122,   387,   131,
     125,   122,   122,   122,   122,    81,   290,   291,   292,   401,
     402,   116,   403,   240,   165,   166,   117,   413,    50,   244,
     253,   212,   213,   214,   167,    73,   353,   143,   144,    51,
      51,   145,   131,   141,   275,   146,   180,   253,   181,   158,
     414,   262,   264,   177,   415,   133,    73,   390,    52,    56,
     204,   205,    50,    50,   118,    52,   130,    47,    47,   131,
     191,    56,    56,   119,  -104,    50,    50,   148,    50,   344,
     164,   130,   -87,   351,   131,   132,    52,   133,    35,    35,
     215,   216,    52,    52,   249,   250,   263,   389,   160,    28,
     131,   177,   169,   133,   170,    52,    52,   298,    52,    53,
     174,   130,  -104,   171,   131,   132,    53,   133,   344,   172,
     122,   304,   122,   122,   122,   122,   122,   122,   122,   122,
     122,   122,   122,   122,   122,   122,   122,    53,   175,    58,
     336,   173,   320,    53,    53,    64,    58,    65,    66,    67,
     210,   211,   179,   226,   354,   355,    53,    53,   188,    53,
     123,   123,   288,   289,   240,    73,   201,    58,   284,   285,
     286,   287,   200,    58,    58,   282,   283,    50,    50,   202,
     131,   203,    10,   217,   359,   222,    58,    58,   246,    58,
     223,    56,   243,   245,   238,    52,   248,   122,   127,   128,
     259,   260,   240,   268,   269,   360,   271,    52,    52,  -149,
     275,   270,   129,   130,   294,   293,   131,   132,    56,   133,
     130,   295,   296,   123,   123,   299,   300,    57,   123,   123,
     123,   123,   311,   305,    57,   306,   122,   307,   312,   314,
     315,   320,   318,   316,   313,   321,    53,   322,   323,   324,
     325,   326,   328,   330,    56,    57,   331,   334,    53,    53,
      28,    57,    57,   345,   356,   353,   350,   337,   352,   372,
     357,   361,   363,   320,    57,    57,    58,    57,   364,   365,
     368,   373,   374,   375,    82,    83,   379,    56,    58,    58,
      56,    56,   376,   377,   349,    56,   320,    73,   380,   382,
     383,   385,   386,   388,    84,    85,    23,   393,    24,    25,
      86,    27,    87,   396,   397,    56,   400,    88,    56,    56,
     406,   407,   230,   411,    73,   416,   417,    52,    89,    90,
     367,   150,   242,    91,    92,   276,    31,   123,   419,   123,
     123,   123,   123,   123,   123,   123,   123,   123,   123,   123,
     123,   123,   123,   123,    52,   279,   278,     0,   418,     0,
      73,   280,     0,   392,    57,     0,   394,   395,     0,     0,
     281,   398,     0,     0,     0,     0,    57,    57,    53,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
      52,   408,     0,    73,   409,   410,    73,    73,     0,     0,
       0,    73,     0,     0,     0,    53,     0,     0,    58,     0,
       0,     0,    82,    83,   123,     0,     0,     0,     0,     0,
       0,    73,     0,    52,    73,    73,    52,    52,   115,     0,
       0,    52,    84,    85,    23,    58,    24,    25,    86,    27,
       0,    53,     0,     0,     0,   231,   232,     0,     0,     0,
       0,    52,     0,   123,    52,    52,    89,    90,     0,     0,
       0,    91,    92,     0,    31,     0,     0,     0,     0,     0,
       0,    58,     0,     0,    53,     0,     0,    53,    53,     0,
       0,     0,    53,     0,     0,     0,     0,     0,     0,     0,
     184,     0,     0,   187,     0,     0,    57,     0,     0,     0,
       0,     0,    53,     0,    58,    53,    53,    58,    58,     0,
       0,     0,    58,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,    57,     0,     0,     0,     0,   218,   219,
     220,   221,    58,     0,     0,    58,    58,     0,     0,   228,
     229,     0,   235,   237,   239,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,   247,    57,
       0,     0,     0,     0,    82,    83,     0,     0,     0,     0,
       0,     0,   254,   255,   256,     0,     0,   257,   258,     0,
       0,     0,     0,     0,    84,    85,    23,     0,    24,    25,
      86,    27,    57,   265,   267,    57,    57,    88,     0,     0,
      57,     0,   238,   239,     0,     0,     0,     0,    89,    90,
       0,   277,     0,    91,    92,     0,    31,     0,     0,     0,
      57,     0,     0,    57,    57,     1,     2,     3,     4,     5,
       6,     7,     0,     8,     9,    10,    11,   297,     0,    12,
      13,    14,    15,    16,    17,    18,    19,    20,   310,     0,
       0,     0,     0,     0,     0,     0,     0,    21,    22,    23,
       0,    24,    25,    26,    27,   155,   125,    28,     0,     0,
      29,   327,     0,     0,    30,   329,     0,     0,   333,     0,
       0,     0,     0,     0,   310,   342,     0,     0,     0,    31,
       1,     2,     3,     4,     5,     6,     7,     0,     8,     9,
      10,     0,     0,     0,     0,    13,   347,   348,    16,    17,
      18,    19,    20,     0,     0,     0,     0,     0,   235,   235,
       0,     0,    21,    22,    23,     0,    24,    25,    26,    27,
       0,     0,    28,   135,     0,    29,     0,     0,     0,     0,
      82,    83,     0,   366,     0,     0,   370,   371,     0,     0,
       0,     0,     0,     0,    31,     0,     0,   378,     0,     0,
      84,    85,    23,     0,    24,    25,    86,    27,     0,   310,
     236,     0,     0,    88,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,    89,    90,     0,   399,     0,    91,
      92,     0,    31,     0,   404,     0,     0,     0,     0,     1,
       2,     3,     4,     5,     6,     7,     0,     8,     9,    10,
      11,     0,   310,    12,    13,    14,    15,    16,    17,    18,
      19,    20,     0,     0,     0,     0,     0,     0,     0,     0,
       0,    21,    22,    23,     0,    24,    25,    26,    27,     0,
       0,    28,     0,     0,    29,     0,     0,     0,    30,     1,
       2,     3,     4,     5,     6,     7,     0,     8,     9,    10,
       0,     0,     0,    31,    13,     0,     0,    16,    17,    18,
      19,    20,     0,     0,     0,    82,    83,     0,     0,     0,
       0,    21,    22,    23,     0,    24,    25,    26,    27,     0,
       0,    28,     0,     0,    29,    84,    85,    23,     0,    24,
      25,    86,    27,   266,     0,     0,     0,     0,    88,    82,
      83,     0,     0,    31,     0,     0,     0,     0,     0,    89,
      90,     0,     0,     0,    91,    92,     0,    31,     0,    84,
      85,    23,     0,    24,    25,    86,    27,     0,     0,     0,
     308,     0,    88,    82,    83,     0,     0,     0,     0,     0,
       0,     0,     0,    89,    90,     0,     0,     0,    91,    92,
       0,    31,     0,    84,    85,    23,     0,    24,    25,    86,
      27,     0,     0,     0,     0,     0,    88,   332,    82,    83,
       0,     0,     0,     0,     0,     0,     0,    89,    90,     0,
       0,     0,    91,    92,     0,    31,     0,     0,    84,    85,
      23,     0,    24,    25,    86,    27,     0,     0,     0,     0,
       0,    88,   339,    82,    83,     0,     0,     0,     0,     0,
       0,     0,    89,    90,     0,     0,     0,    91,    92,     0,
      31,     0,     0,    84,    85,    23,     0,    24,    25,    86,
      27,     0,     0,     0,     0,     0,    88,    82,    83,     0,
       0,   341,     0,     0,     0,     0,     0,    89,    90,     0,
       0,     0,    91,    92,     0,    31,     0,    84,    85,    23,
       0,    24,    25,    86,    27,     0,     0,     0,     0,     0,
      88,   369,    82,    83,     0,     0,     0,     0,     0,     0,
       0,    89,    90,     0,     0,     0,    91,    92,     0,    31,
       0,     0,    84,    85,    23,     0,    24,    25,    86,    27,
       0,     0,    28,     0,     0,    88,    82,    83,     0,     0,
       0,     0,     0,     0,     0,     0,    89,    90,     0,     0,
       0,    91,    92,     0,    31,     0,    84,    85,    23,     0,
      24,    25,    86,    27,     0,     0,     0,     0,     0,    88,
     412,    82,    83,     0,     0,     0,     0,     0,     0,     0,
      89,    90,     0,     0,     0,    91,    92,     0,    31,     0,
       0,    84,    85,    23,     0,    24,    25,    86,    27,     0,
       0,     0,     0,     0,   139,    82,    83,     0,     0,     0,
       0,     0,     0,     0,     0,    89,    90,     0,     0,     0,
      91,    92,     0,    31,     0,    84,    85,    23,     0,    24,
      25,    86,    27,     0,     0,     0,     0,     0,    88,    82,
      83,     0,     0,     0,     0,     0,     0,     0,     0,    89,
      90,     0,     0,     0,    91,    92,     0,    31,     0,    84,
      85,    23,     0,    24,    25,   192,    27,     0,     0,     0,
       0,     0,   139,    82,    83,     0,     0,     0,     0,     0,
       0,     0,     0,    89,    90,     0,     0,     0,    91,    92,
       0,    31,     0,    84,    85,    23,     0,    24,    25,   301,
      27,     0,     0,     0,     0,     0,   139,    82,    83,     0,
       0,     0,     0,     0,     0,     0,     0,    89,    90,     0,
       0,     0,    91,    92,     0,    31,     0,    84,    85,    23,
       0,    24,    25,    86,    27,     0,     0,     0,     0,     0,
     231,    83,     0,     0,     0,     0,     0,     0,     0,     0,
       0,    89,    90,     0,     0,    83,    91,    92,     0,    31,
      84,    85,    23,     0,    24,    25,   120,    27,     0,     0,
       0,     0,     0,    29,    84,    85,    23,     0,    24,    25,
     120,    27,     0,     0,    89,    90,     0,    88,     0,    91,
      92,     0,    31,     0,     0,     0,     0,     0,    89,    90,
       0,     0,     0,    91,    92,     0,    31
    };
  }

private static final short yycheck_[] = yycheck_init();
  private static final short[] yycheck_init()
  {
    return new short[]
    {
       0,    21,    22,    89,    90,   248,   252,    28,     8,   272,
      43,    33,    34,    23,    11,    43,    48,    43,   274,   351,
      52,   271,    48,    51,    52,    51,    54,    43,     0,    51,
      30,    43,     0,    33,    34,    35,    43,    44,    56,     7,
      58,    47,    52,   293,    41,    66,    41,    47,    48,     0,
      50,   383,    41,    21,    22,    52,   312,    75,    48,    48,
      28,    33,    34,    35,    84,    85,    34,    35,    43,    44,
      47,    91,    92,    43,    51,    47,    48,    77,    50,    47,
      48,    51,    50,    34,    35,    31,    32,    48,    88,    34,
      35,    52,   110,    54,   357,   113,    47,    48,   108,    50,
      48,    41,    47,    48,    41,    50,    43,    41,    54,    55,
      50,    43,    52,   363,    54,     7,    84,    85,   361,    51,
      44,    89,    90,    91,    92,    48,   212,   213,   214,   379,
     380,    48,   382,   133,    33,    34,    48,   400,     0,   139,
     158,    61,    62,    63,    43,     7,    41,    37,    38,   149,
     150,    41,    51,    41,    49,    45,    41,   175,    43,    41,
     406,   179,   180,    52,   407,    54,    28,   417,     0,   137,
      29,    30,    34,    35,    48,     7,    48,   149,   150,    51,
      52,   149,   150,    48,    41,    47,    48,     0,    50,   275,
      43,    48,    49,    50,    51,    52,    28,    54,   149,   150,
      35,    36,    34,    35,   149,   150,    47,    43,    52,    45,
      51,    52,    43,    54,    47,    47,    48,   227,    50,     0,
      25,    48,    49,    47,    51,    52,     7,    54,   314,    47,
     198,   231,   200,   201,   202,   203,   204,   205,   206,   207,
     208,   209,   210,   211,   212,   213,   214,    28,    41,     0,
     268,    47,   252,    34,    35,    37,     7,    39,    40,    41,
      59,    60,    41,   125,   306,   307,    47,    48,    41,    50,
      21,    22,   210,   211,   274,   137,    56,    28,   206,   207,
     208,   209,    27,    34,    35,   204,   205,   149,   150,    57,
      51,    58,    13,    43,   314,    43,    47,    48,    53,    50,
      43,   269,    46,    49,    53,   137,    48,   275,    33,    34,
      48,    43,   312,    41,    49,   315,    49,   149,   150,    44,
      49,    41,    47,    48,    50,    49,    51,    52,   296,    54,
      48,    50,    49,    84,    85,    43,    43,     0,    89,    90,
      91,    92,    53,    49,     7,    50,   314,    50,    50,    49,
      44,   351,    41,    46,    55,    43,   137,    53,    43,    43,
      43,    43,    43,    43,   332,    28,    43,    43,   149,   150,
      45,    34,    35,    47,    46,    41,    49,   269,    49,    43,
      50,    50,    49,   383,    47,    48,   137,    50,    49,    49,
      49,    10,    49,    48,    15,    16,    49,   365,   149,   150,
     368,   369,    53,    55,   296,   373,   406,   269,    49,    26,
      50,    49,    41,    49,    35,    36,    37,    49,    39,    40,
      41,    42,    43,    49,    49,   393,    48,    48,   396,   397,
      48,    51,   129,    49,   296,    49,    49,   269,    59,    60,
     332,    33,   137,    64,    65,   198,    67,   198,   417,   200,
     201,   202,   203,   204,   205,   206,   207,   208,   209,   210,
     211,   212,   213,   214,   296,   201,   200,    -1,   415,    -1,
     332,   202,    -1,   365,   137,    -1,   368,   369,    -1,    -1,
     203,   373,    -1,    -1,    -1,    -1,   149,   150,   269,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
     332,   393,    -1,   365,   396,   397,   368,   369,    -1,    -1,
      -1,   373,    -1,    -1,    -1,   296,    -1,    -1,   269,    -1,
      -1,    -1,    15,    16,   275,    -1,    -1,    -1,    -1,    -1,
      -1,   393,    -1,   365,   396,   397,   368,   369,    16,    -1,
      -1,   373,    35,    36,    37,   296,    39,    40,    41,    42,
      -1,   332,    -1,    -1,    -1,    48,    49,    -1,    -1,    -1,
      -1,   393,    -1,   314,   396,   397,    59,    60,    -1,    -1,
      -1,    64,    65,    -1,    67,    -1,    -1,    -1,    -1,    -1,
      -1,   332,    -1,    -1,   365,    -1,    -1,   368,   369,    -1,
      -1,    -1,   373,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      78,    -1,    -1,    81,    -1,    -1,   269,    -1,    -1,    -1,
      -1,    -1,   393,    -1,   365,   396,   397,   368,   369,    -1,
      -1,    -1,   373,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,   296,    -1,    -1,    -1,    -1,   116,   117,
     118,   119,   393,    -1,    -1,   396,   397,    -1,    -1,   127,
     128,    -1,   130,   131,   132,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,   146,   332,
      -1,    -1,    -1,    -1,    15,    16,    -1,    -1,    -1,    -1,
      -1,    -1,   160,   161,   162,    -1,    -1,   165,   166,    -1,
      -1,    -1,    -1,    -1,    35,    36,    37,    -1,    39,    40,
      41,    42,   365,   181,   182,   368,   369,    48,    -1,    -1,
     373,    -1,    53,   191,    -1,    -1,    -1,    -1,    59,    60,
      -1,   199,    -1,    64,    65,    -1,    67,    -1,    -1,    -1,
     393,    -1,    -1,   396,   397,     3,     4,     5,     6,     7,
       8,     9,    -1,    11,    12,    13,    14,   225,    -1,    17,
      18,    19,    20,    21,    22,    23,    24,    25,   236,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    35,    36,    37,
      -1,    39,    40,    41,    42,    43,    44,    45,    -1,    -1,
      48,   259,    -1,    -1,    52,   263,    -1,    -1,   266,    -1,
      -1,    -1,    -1,    -1,   272,   273,    -1,    -1,    -1,    67,
       3,     4,     5,     6,     7,     8,     9,    -1,    11,    12,
      13,    -1,    -1,    -1,    -1,    18,   294,   295,    21,    22,
      23,    24,    25,    -1,    -1,    -1,    -1,    -1,   306,   307,
      -1,    -1,    35,    36,    37,    -1,    39,    40,    41,    42,
      -1,    -1,    45,    46,    -1,    48,    -1,    -1,    -1,    -1,
      15,    16,    -1,   331,    -1,    -1,   334,   335,    -1,    -1,
      -1,    -1,    -1,    -1,    67,    -1,    -1,   345,    -1,    -1,
      35,    36,    37,    -1,    39,    40,    41,    42,    -1,   357,
      45,    -1,    -1,    48,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    59,    60,    -1,   375,    -1,    64,
      65,    -1,    67,    -1,   382,    -1,    -1,    -1,    -1,     3,
       4,     5,     6,     7,     8,     9,    -1,    11,    12,    13,
      14,    -1,   400,    17,    18,    19,    20,    21,    22,    23,
      24,    25,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    35,    36,    37,    -1,    39,    40,    41,    42,    -1,
      -1,    45,    -1,    -1,    48,    -1,    -1,    -1,    52,     3,
       4,     5,     6,     7,     8,     9,    -1,    11,    12,    13,
      -1,    -1,    -1,    67,    18,    -1,    -1,    21,    22,    23,
      24,    25,    -1,    -1,    -1,    15,    16,    -1,    -1,    -1,
      -1,    35,    36,    37,    -1,    39,    40,    41,    42,    -1,
      -1,    45,    -1,    -1,    48,    35,    36,    37,    -1,    39,
      40,    41,    42,    43,    -1,    -1,    -1,    -1,    48,    15,
      16,    -1,    -1,    67,    -1,    -1,    -1,    -1,    -1,    59,
      60,    -1,    -1,    -1,    64,    65,    -1,    67,    -1,    35,
      36,    37,    -1,    39,    40,    41,    42,    -1,    -1,    -1,
      46,    -1,    48,    15,    16,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    59,    60,    -1,    -1,    -1,    64,    65,
      -1,    67,    -1,    35,    36,    37,    -1,    39,    40,    41,
      42,    -1,    -1,    -1,    -1,    -1,    48,    49,    15,    16,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    59,    60,    -1,
      -1,    -1,    64,    65,    -1,    67,    -1,    -1,    35,    36,
      37,    -1,    39,    40,    41,    42,    -1,    -1,    -1,    -1,
      -1,    48,    49,    15,    16,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    59,    60,    -1,    -1,    -1,    64,    65,    -1,
      67,    -1,    -1,    35,    36,    37,    -1,    39,    40,    41,
      42,    -1,    -1,    -1,    -1,    -1,    48,    15,    16,    -1,
      -1,    53,    -1,    -1,    -1,    -1,    -1,    59,    60,    -1,
      -1,    -1,    64,    65,    -1,    67,    -1,    35,    36,    37,
      -1,    39,    40,    41,    42,    -1,    -1,    -1,    -1,    -1,
      48,    49,    15,    16,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    59,    60,    -1,    -1,    -1,    64,    65,    -1,    67,
      -1,    -1,    35,    36,    37,    -1,    39,    40,    41,    42,
      -1,    -1,    45,    -1,    -1,    48,    15,    16,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    59,    60,    -1,    -1,
      -1,    64,    65,    -1,    67,    -1,    35,    36,    37,    -1,
      39,    40,    41,    42,    -1,    -1,    -1,    -1,    -1,    48,
      49,    15,    16,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      59,    60,    -1,    -1,    -1,    64,    65,    -1,    67,    -1,
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
      42,    -1,    -1,    -1,    -1,    -1,    48,    15,    16,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    59,    60,    -1,
      -1,    -1,    64,    65,    -1,    67,    -1,    35,    36,    37,
      -1,    39,    40,    41,    42,    -1,    -1,    -1,    -1,    -1,
      48,    16,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    59,    60,    -1,    -1,    16,    64,    65,    -1,    67,
      35,    36,    37,    -1,    39,    40,    41,    42,    -1,    -1,
      -1,    -1,    -1,    48,    35,    36,    37,    -1,    39,    40,
      41,    42,    -1,    -1,    59,    60,    -1,    48,    -1,    64,
      65,    -1,    67,    -1,    -1,    -1,    -1,    -1,    59,    60,
      -1,    -1,    -1,    64,    65,    -1,    67
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
      25,    35,    36,    37,    39,    40,    41,    42,    45,    48,
      52,    67,    69,    70,    71,    73,    76,    78,    79,    80,
      81,    82,    84,    85,    86,    87,    88,    89,    92,    96,
      97,    99,   115,   116,   117,   118,   119,   121,   122,   127,
      41,    43,    43,    43,    37,    39,    40,    41,    43,    47,
      73,    74,    89,    97,    99,    41,    99,    48,    48,    41,
      72,    48,    15,    16,    35,    36,    41,    43,    48,    59,
      60,    64,    65,    97,   101,   102,   103,   104,   105,   106,
     107,   108,   109,   110,   111,   112,   113,   114,   115,   116,
     119,   120,   121,   122,   125,   130,    48,    48,    48,    48,
      41,   110,   119,   122,   110,    44,   123,    33,    34,    47,
      48,    51,    52,    54,    90,    46,    74,    75,    77,    48,
     130,    41,    99,    37,    38,    41,    45,   128,     0,    89,
      92,    69,    69,    69,    69,    43,    69,   123,    41,   123,
      52,    33,    34,    90,    43,    33,    34,    43,    90,    43,
      47,    47,    47,    47,    25,    41,    43,    52,    90,    41,
      41,    43,    83,    99,   130,    43,    44,   130,    41,   110,
     110,    52,    41,    99,   111,   111,   110,   110,    28,    66,
      27,    56,    57,    58,    29,    30,    31,    32,    54,    55,
      59,    60,    61,    62,    63,    35,    36,    43,   130,   130,
     130,   130,    43,    43,    41,    48,    97,   124,   130,   130,
      80,    48,    49,    98,   126,   130,    45,   130,    53,   130,
      99,   100,    77,    46,    99,    49,    53,   130,    48,    69,
      69,    43,    48,    90,   130,   130,   130,   130,   130,    48,
      43,    43,    90,    47,    90,   130,    43,   130,    41,    49,
      41,    49,    48,    52,    54,    49,   102,   130,   103,   104,
     105,   106,   107,   107,   108,   108,   108,   108,   109,   109,
     111,   111,   111,    49,    50,    50,    49,   130,   123,    43,
      43,    41,    94,    95,    99,    49,    50,    50,    46,    91,
     130,    53,    50,    55,    49,    44,    46,   128,    41,    94,
      99,    43,    53,    43,    43,    43,    43,   130,    43,   130,
      43,    43,    49,   130,    43,    47,    90,    74,    76,    49,
      91,    53,   130,   100,   111,    47,    76,   130,   130,    74,
      49,    50,    49,    41,    98,    98,    46,    50,   100,   110,
      99,    50,   129,    49,    49,    49,   130,    74,    49,    49,
     130,   130,    43,    10,    49,    48,    53,    55,   130,    49,
      49,    95,    26,    50,    91,    49,    41,   128,    49,    43,
      76,    93,    74,    49,    74,    74,    49,    49,    74,   130,
      48,    76,    76,    76,   130,    95,    48,    51,    74,    74,
      74,    49,    49,    91,    94,   128,    49,    49,   129,    93
    };
  }

/* YYR1[YYN] -- Symbol number of symbol that rule YYN derives.  */
  private static final short yyr1_[] = yyr1_init();
  private static final short[] yyr1_init()
  {
    return new short[]
    {
       0,    68,    69,    69,    69,    69,    69,    69,    69,    69,
      70,    70,    70,    71,    72,    72,    73,    73,    73,    73,
      73,    73,    73,    73,    73,    73,    73,    73,    73,    73,
      73,    73,    73,    74,    74,    75,    76,    76,    77,    77,
      78,    79,    79,    79,    79,    79,    79,    79,    80,    80,
      81,    81,    82,    82,    82,    82,    82,    82,    82,    83,
      83,    84,    85,    86,    87,    88,    88,    88,    88,    88,
      89,    89,    89,    89,    89,    89,    90,    90,    90,    91,
      91,    92,    92,    93,    93,    94,    94,    95,    95,    95,
      95,    96,    96,    96,    96,    96,    96,    97,    97,    98,
      98,    98,    98,    99,    99,    99,   100,   100,   101,   101,
     102,   102,   103,   103,   104,   104,   105,   105,   106,   106,
     106,   107,   107,   107,   107,   107,   108,   108,   108,   109,
     109,   109,   109,   110,   110,   110,   110,   110,   111,   111,
     112,   112,   112,   113,   113,   113,   114,   114,   114,   115,
     115,   116,   116,   117,   117,   117,   117,   117,   118,   118,
     118,   118,   119,   119,   120,   120,   121,   121,   121,   122,
     122,   122,   123,   123,   124,   124,   124,   125,   125,   125,
     125,   125,   125,   125,   126,   126,   127,   128,   128,   128,
     128,   129,   129,   130,   130,   130
    };
  }

/* YYR2[YYN] -- Number of symbols on the right hand side of rule YYN.  */
  private static final byte yyr2_[] = yyr2_init();
  private static final byte[] yyr2_init()
  {
    return new byte[]
    {
       0,     2,     3,     2,     2,     2,     2,     3,     2,     0,
       1,     1,     1,     3,     1,     3,     1,     2,     2,     3,
       3,     2,     1,     1,     1,     1,     1,     1,     1,     1,
       1,     1,     1,     1,     1,     1,     3,     2,     1,     2,
       3,     2,     3,     2,     3,     2,     2,     2,     5,     7,
       5,     6,     8,     7,     7,     6,     8,     7,     8,     3,
       4,     5,     7,     7,     5,     3,     3,     3,     3,     2,
       4,     4,     5,     3,     3,     4,     2,     3,     4,     1,
       3,    10,     6,     1,     1,     1,     0,     1,     2,     3,
       4,     4,     4,     4,     4,     4,     4,     3,     4,     1,
       1,     3,     3,     3,     1,     4,     1,     3,     3,     1,
       3,     1,     1,     3,     1,     3,     1,     3,     1,     3,
       3,     1,     3,     3,     3,     3,     1,     3,     3,     1,
       3,     3,     3,     2,     2,     2,     2,     1,     1,     4,
       1,     2,     2,     1,     2,     2,     1,     1,     1,     1,
       6,     3,     1,     1,     1,     1,     1,     1,     1,     1,
       1,     1,     4,     4,     5,     1,     2,     2,     2,     2,
       2,     2,     3,     2,     1,     1,     3,     2,     4,     5,
       5,     7,     7,     8,     5,     5,     6,     3,     1,     1,
       1,     5,     0,     1,     1,     1
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
  "var_decl", "propose_statement", "timeout_behaviour_statement",
  "timeout_statement", "switch_statement", "label_statement", "var_def",
  "assgn_exp", "nonempty_exp_list", "method_declaration", "opt_block",
  "opt_args_list", "args_list", "set_operation", "function_call",
  "nonempty_args_list", "type_spec", "type_spec_list",
  "ConditionalOrExpression", "ConditionalAndExpression",
  "InclusiveOrExpression", "ExclusiveOrExpression", "AndExpression",
  "EqualityExpression", "RelationalExpression", "AdditiveExpression",
  "MultiplicativeExpression", "UnaryExpression", "CastExpression",
  "LogicalUnaryExpression", "PostfixExpression", "PrimaryExpression",
  "NotJustName", "ComplexPrimary", "ComplexPrimaryNoParenthesis",
  "Literal", "ArrayAccess", "ConditionalExpression", "assignment",
  "field_access", "field_access_rest", "simple_nofa_exp", "new_exp",
  "lambda_exp", "dialogueact_exp", "da_token", "da_args", "exp", null
    };
  }

  /* YYRLINE[YYN] -- Source line where rule number YYN was defined.  */
  private static final short yyrline_[] = yyrline_init();
  private static final short[] yyrline_init()
  {
    return new short[]
    {
       0,   142,   142,   145,   146,   147,   149,   150,   153,   156,
     160,   161,   162,   166,   174,   175,   179,   180,   181,   182,
     185,   188,   189,   190,   191,   192,   193,   194,   195,   196,
     197,   198,   199,   203,   204,   208,   214,   215,   219,   220,
     224,   228,   229,   230,   231,   232,   233,   234,   238,   239,
     245,   246,   252,   254,   256,   258,   260,   262,   266,   275,
     278,   284,   288,   294,   300,   326,   333,   340,   347,   354,
     363,   366,   369,   372,   375,   378,   384,   385,   388,   394,
     395,   400,   403,   409,   410,   414,   415,   419,   420,   421,
     422,   430,   434,   438,   441,   444,   447,   456,   459,   465,
     466,   467,   468,   472,   473,   474,   480,   481,   485,   488,
     492,   495,   499,   500,   506,   507,   513,   514,   520,   521,
     524,   530,   531,   534,   537,   540,   546,   547,   550,   556,
     557,   560,   563,   569,   572,   575,   576,   577,   581,   582,
     587,   588,   589,   593,   594,   597,   603,   604,   605,   609,
     610,   614,   615,   619,   620,   621,   622,   623,   627,   628,
     629,   630,   634,   638,   642,   643,   649,   653,   654,   658,
     664,   667,   672,   673,   677,   678,   679,   683,   684,   687,
     690,   694,   698,   702,   709,   712,   719,   725,   726,   727,
     728,   732,   735,   739,   740,   741
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

  private static final int yylast_ = 1496;
  private static final int yynnts_ = 63;
  private static final int yyempty_ = -2;
  private static final int yyfinal_ = 148;
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
      return new StatVarDef(false, new Type(null), assign);
    }
    return new StatExpression(assign);
  }

  // generic method
  private ExpAssignment createPlusMinus(RTExpression variable, String plusOrMinus,
                                         Location loc) {
    ExpSingleValue es = setPos(new ExpSingleValue("1", "int"), loc);
    ExpArithmetic ar = setPos(new ExpArithmetic(variable, es, plusOrMinus), loc);
    ExpAssignment ass = setPos(new ExpAssignment(variable, ar), loc);
    return ass;
  }

/* "VondaGrammar.java":3245  */ /* lalr1.java:1066  */

}

