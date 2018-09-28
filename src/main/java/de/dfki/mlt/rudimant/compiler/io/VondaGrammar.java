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
    /* "VondaGrammar.y":147  */ /* lalr1.java:489  */
    {
    yyval = ((LinkedList<RudiTree>)(yystack.valueAt (3-(3)))); ((StatMethodDeclaration)(yystack.valueAt (3-(2)))).setVisibility(((String)(yystack.valueAt (3-(1))))); ((LinkedList<RudiTree>)(yystack.valueAt (3-(3)))).addFirst(((StatMethodDeclaration)(yystack.valueAt (3-(2)))));
  };
  break;
    

  case 3:
  if (yyn == 3)
    /* "VondaGrammar.y":150  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))); ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))).addFirst(((StatMethodDeclaration)(yystack.valueAt (2-(1))))); };
  break;
    

  case 4:
  if (yyn == 4)
    /* "VondaGrammar.y":151  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))); ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))).addFirst(((RTExpression)(yystack.valueAt (2-(1))))); };
  break;
    

  case 5:
  if (yyn == 5)
    /* "VondaGrammar.y":152  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))); ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))).addFirst(((RTStatement)(yystack.valueAt (2-(1))))); };
  break;
    

  case 6:
  if (yyn == 6)
    /* "VondaGrammar.y":154  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))); ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))).addFirst(((Import)(yystack.valueAt (2-(1))))); };
  break;
    

  case 7:
  if (yyn == 7)
    /* "VondaGrammar.y":155  */ /* lalr1.java:489  */
    {
    yyval = ((LinkedList<RudiTree>)(yystack.valueAt (3-(3)))); ((LinkedList<RudiTree>)(yystack.valueAt (3-(3)))).addFirst(setPos(new StatFieldDef(((String)(yystack.valueAt (3-(1)))), ((StatVarDef)(yystack.valueAt (3-(2))))), yystack.locationAt (3-(1)), yystack.locationAt (3-(2))));
  };
  break;
    

  case 8:
  if (yyn == 8)
    /* "VondaGrammar.y":158  */ /* lalr1.java:489  */
    {
    yyval = ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))); ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))).addFirst(setPos(new StatFieldDef(null, ((StatVarDef)(yystack.valueAt (2-(1))))), yystack.locationAt (2-(1))));
  };
  break;
    

  case 9:
  if (yyn == 9)
    /* "VondaGrammar.y":161  */ /* lalr1.java:489  */
    {
    yyval = ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))); ((LinkedList<RudiTree>)(yystack.valueAt (2-(2)))).addFirst(((StatFieldDef)(yystack.valueAt (2-(1)))));
  };
  break;
    

  case 10:
  if (yyn == 10)
    /* "VondaGrammar.y":164  */ /* lalr1.java:489  */
    { yyval = _statements;};
  break;
    

  case 11:
  if (yyn == 11)
    /* "VondaGrammar.y":168  */ /* lalr1.java:489  */
    { yyval = "public"; };
  break;
    

  case 12:
  if (yyn == 12)
    /* "VondaGrammar.y":169  */ /* lalr1.java:489  */
    { yyval = "protected"; };
  break;
    

  case 13:
  if (yyn == 13)
    /* "VondaGrammar.y":170  */ /* lalr1.java:489  */
    { yyval = "private"; };
  break;
    

  case 14:
  if (yyn == 14)
    /* "VondaGrammar.y":174  */ /* lalr1.java:489  */
    {
    List<String> path = ((List<String>)(yystack.valueAt (3-(2))));
    String name = path.remove(path.size() - 1);
    yyval = setPos(new Import(name, path.toArray(new String[path.size()])), (yyloc));
  };
  break;
    

  case 15:
  if (yyn == 15)
    /* "VondaGrammar.y":182  */ /* lalr1.java:489  */
    { yyval = new ArrayList<String>(){{ add((( String )(yystack.valueAt (1-(1))))); }}; };
  break;
    

  case 16:
  if (yyn == 16)
    /* "VondaGrammar.y":183  */ /* lalr1.java:489  */
    { yyval = ((List<String>)(yystack.valueAt (3-(1)))); ((List<String>)(yystack.valueAt (3-(1)))).add((( String )(yystack.valueAt (3-(3))))); };
  break;
    

  case 17:
  if (yyn == 17)
    /* "VondaGrammar.y":187  */ /* lalr1.java:489  */
    { yyval = ((StatAbstractBlock)(yystack.valueAt (1-(1)))); };
  break;
    

  case 18:
  if (yyn == 18)
    /* "VondaGrammar.y":188  */ /* lalr1.java:489  */
    { yyval = setPos(getAssignmentStat(((RTExpression)(yystack.valueAt (2-(1))))), (yyloc)); };
  break;
    

  case 19:
  if (yyn == 19)
    /* "VondaGrammar.y":189  */ /* lalr1.java:489  */
    { yyval = setPos(new StatExpression(((RTExpression)(yystack.valueAt (2-(1))))), (yyloc)); };
  break;
    

  case 20:
  if (yyn == 20)
    /* "VondaGrammar.y":190  */ /* lalr1.java:489  */
    {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (3-(2))))), yystack.locationAt (3-(2)));
    yyval = setPos(new StatExpression(createPlusMinus(var, "++", (yyloc))), (yyloc));
  };
  break;
    

  case 21:
  if (yyn == 21)
    /* "VondaGrammar.y":194  */ /* lalr1.java:489  */
    {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (3-(2))))), yystack.locationAt (3-(2)));
    yyval = setPos(new StatExpression(createPlusMinus(var, "--", (yyloc))), (yyloc));
  };
  break;
    

  case 22:
  if (yyn == 22)
    /* "VondaGrammar.y":198  */ /* lalr1.java:489  */
    {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (3-(1))))), yystack.locationAt (3-(1)));
    yyval = setPos(new StatExpression(createPlusMinus(var, "+++", (yyloc))), (yyloc));
  };
  break;
    

  case 23:
  if (yyn == 23)
    /* "VondaGrammar.y":202  */ /* lalr1.java:489  */
    {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (3-(1))))), yystack.locationAt (3-(1)));
    yyval = setPos(new StatExpression(createPlusMinus(var, "---", (yyloc))), (yyloc));
  };
  break;
    

  case 24:
  if (yyn == 24)
    /* "VondaGrammar.y":206  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatExpression(createPlusMinus(((RTExpression)(yystack.valueAt (3-(2)))), "++", (yyloc))), (yyloc));
  };
  break;
    

  case 25:
  if (yyn == 25)
    /* "VondaGrammar.y":209  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatExpression(createPlusMinus(((RTExpression)(yystack.valueAt (3-(2)))), "--", (yyloc))), (yyloc));
  };
  break;
    

  case 26:
  if (yyn == 26)
    /* "VondaGrammar.y":212  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatExpression(createPlusMinus(((RTExpression)(yystack.valueAt (3-(1)))), "+++", (yyloc))), (yyloc));
  };
  break;
    

  case 27:
  if (yyn == 27)
    /* "VondaGrammar.y":215  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatExpression(createPlusMinus(((RTExpression)(yystack.valueAt (3-(1)))), "---", (yyloc))), (yyloc));
  };
  break;
    

  case 28:
  if (yyn == 28)
    /* "VondaGrammar.y":218  */ /* lalr1.java:489  */
    { yyval = setPos(new StatExpression(((RTExpression)(yystack.valueAt (2-(1))))), (yyloc)); };
  break;
    

  case 29:
  if (yyn == 29)
    /* "VondaGrammar.y":219  */ /* lalr1.java:489  */
    { yyval = ((StatGrammarRule)(yystack.valueAt (1-(1)))); };
  break;
    

  case 30:
  if (yyn == 30)
    /* "VondaGrammar.y":220  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 31:
  if (yyn == 31)
    /* "VondaGrammar.y":221  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 32:
  if (yyn == 32)
    /* "VondaGrammar.y":222  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 33:
  if (yyn == 33)
    /* "VondaGrammar.y":223  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 34:
  if (yyn == 34)
    /* "VondaGrammar.y":224  */ /* lalr1.java:489  */
    { yyval = ((StatIf)(yystack.valueAt (1-(1)))); };
  break;
    

  case 35:
  if (yyn == 35)
    /* "VondaGrammar.y":225  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 36:
  if (yyn == 36)
    /* "VondaGrammar.y":226  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 37:
  if (yyn == 37)
    /* "VondaGrammar.y":227  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 38:
  if (yyn == 38)
    /* "VondaGrammar.y":228  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 39:
  if (yyn == 39)
    /* "VondaGrammar.y":232  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 40:
  if (yyn == 40)
    /* "VondaGrammar.y":233  */ /* lalr1.java:489  */
    { yyval = ((StatVarDef)(yystack.valueAt (1-(1)))); };
  break;
    

  case 41:
  if (yyn == 41)
    /* "VondaGrammar.y":237  */ /* lalr1.java:489  */
    { yyval = ((RTStatement)(yystack.valueAt (1-(1)))); };
  break;
    

  case 42:
  if (yyn == 42)
    /* "VondaGrammar.y":243  */ /* lalr1.java:489  */
    { yyval = setPos(new StatAbstractBlock(((LinkedList<RTStatement>)(yystack.valueAt (3-(2)))), true), (yyloc)); };
  break;
    

  case 43:
  if (yyn == 43)
    /* "VondaGrammar.y":244  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatAbstractBlock(new ArrayList<RTStatement>(), true), (yyloc));
  };
  break;
    

  case 44:
  if (yyn == 44)
    /* "VondaGrammar.y":248  */ /* lalr1.java:489  */
    { yyval = new LinkedList<RTStatement>(){{ add(((RTStatement)(yystack.valueAt (1-(1))))); }}; };
  break;
    

  case 45:
  if (yyn == 45)
    /* "VondaGrammar.y":249  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RTStatement>)(yystack.valueAt (2-(2)))); ((LinkedList<RTStatement>)(yystack.valueAt (2-(2)))).addFirst(((RTStatement)(yystack.valueAt (2-(1))))); };
  break;
    

  case 46:
  if (yyn == 46)
    /* "VondaGrammar.y":253  */ /* lalr1.java:489  */
    { yyval = setPos(new StatGrammarRule((( String )(yystack.valueAt (3-(1)))), ((StatIf)(yystack.valueAt (3-(3))))), (yyloc)); };
  break;
    

  case 47:
  if (yyn == 47)
    /* "VondaGrammar.y":257  */ /* lalr1.java:489  */
    { yyval = setPos(new StatReturn("return"), (yyloc)); };
  break;
    

  case 48:
  if (yyn == 48)
    /* "VondaGrammar.y":258  */ /* lalr1.java:489  */
    { yyval = setPos(new StatReturn(((RTExpression)(yystack.valueAt (3-(2))))), (yyloc)); };
  break;
    

  case 49:
  if (yyn == 49)
    /* "VondaGrammar.y":259  */ /* lalr1.java:489  */
    { yyval = setPos(new StatReturn("break"), (yyloc)); };
  break;
    

  case 50:
  if (yyn == 50)
    /* "VondaGrammar.y":260  */ /* lalr1.java:489  */
    { yyval = setPos(new StatReturn("break", (( String )(yystack.valueAt (3-(2))))), (yyloc)); };
  break;
    

  case 51:
  if (yyn == 51)
    /* "VondaGrammar.y":261  */ /* lalr1.java:489  */
    { yyval = setPos(new StatReturn("cancel"), (yyloc)); };
  break;
    

  case 52:
  if (yyn == 52)
    /* "VondaGrammar.y":262  */ /* lalr1.java:489  */
    { yyval = setPos(new StatReturn("cancel_all"), (yyloc)); };
  break;
    

  case 53:
  if (yyn == 53)
    /* "VondaGrammar.y":263  */ /* lalr1.java:489  */
    { yyval = setPos(new StatReturn("continue"), (yyloc)); };
  break;
    

  case 54:
  if (yyn == 54)
    /* "VondaGrammar.y":267  */ /* lalr1.java:489  */
    { yyval = setPos(new StatIf(((RTExpression)(yystack.valueAt (5-(3)))), ((RTStatement)(yystack.valueAt (5-(5)))), null), (yyloc)); };
  break;
    

  case 55:
  if (yyn == 55)
    /* "VondaGrammar.y":268  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatIf(((RTExpression)(yystack.valueAt (7-(3)))), ((RTStatement)(yystack.valueAt (7-(5)))), ((RTStatement)(yystack.valueAt (7-(7))))), (yyloc));
  };
  break;
    

  case 56:
  if (yyn == 56)
    /* "VondaGrammar.y":274  */ /* lalr1.java:489  */
    { yyval = setPos(new StatWhile(((RTExpression)(yystack.valueAt (5-(3)))), ((RTStatement)(yystack.valueAt (5-(5)))), true), (yyloc)); };
  break;
    

  case 57:
  if (yyn == 57)
    /* "VondaGrammar.y":275  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatWhile(((RTExpression)(yystack.valueAt (6-(5)))), ((RTStatement)(yystack.valueAt (6-(2)))), false), (yyloc));
  };
  break;
    

  case 58:
  if (yyn == 58)
    /* "VondaGrammar.y":281  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatFor1(((StatVarDef)(yystack.valueAt (8-(3)))), ((RTExpression)(yystack.valueAt (8-(4)))), ((RTExpression)(yystack.valueAt (8-(6)))), ((RTStatement)(yystack.valueAt (8-(8))))), (yyloc)); };
  break;
    

  case 59:
  if (yyn == 59)
    /* "VondaGrammar.y":283  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatFor1(((StatVarDef)(yystack.valueAt (7-(3)))), null, ((RTExpression)(yystack.valueAt (7-(5)))), ((RTStatement)(yystack.valueAt (7-(7))))), (yyloc)); };
  break;
    

  case 60:
  if (yyn == 60)
    /* "VondaGrammar.y":285  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatFor1(((StatVarDef)(yystack.valueAt (7-(3)))), ((RTExpression)(yystack.valueAt (7-(4)))), null, ((RTStatement)(yystack.valueAt (7-(7))))), (yyloc)); };
  break;
    

  case 61:
  if (yyn == 61)
    /* "VondaGrammar.y":287  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatFor1(((StatVarDef)(yystack.valueAt (6-(3)))), null, null, ((RTStatement)(yystack.valueAt (6-(6))))), (yyloc)); };
  break;
    

  case 62:
  if (yyn == 62)
    /* "VondaGrammar.y":289  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatFor1(null, ((RTExpression)(yystack.valueAt (8-(4)))), ((RTExpression)(yystack.valueAt (8-(6)))), ((RTStatement)(yystack.valueAt (8-(8))))), (yyloc)); };
  break;
    

  case 63:
  if (yyn == 63)
    /* "VondaGrammar.y":291  */ /* lalr1.java:489  */
    {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (7-(3))))), yystack.locationAt (7-(3)));
    yyval = setPos(new StatFor2(var, ((RTExpression)(yystack.valueAt (7-(5)))), ((RTStatement)(yystack.valueAt (7-(7))))), (yyloc));
  };
  break;
    

  case 64:
  if (yyn == 64)
    /* "VondaGrammar.y":295  */ /* lalr1.java:489  */
    {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (8-(4))))), yystack.locationAt (8-(4)));
    yyval = setPos(new StatFor2(((Type)(yystack.valueAt (8-(3)))), var, ((RTExpression)(yystack.valueAt (8-(6)))), ((RTStatement)(yystack.valueAt (8-(8))))), (yyloc));
  };
  break;
    

  case 65:
  if (yyn == 65)
    /* "VondaGrammar.y":304  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatVarDef(false, Type.getNoType(), (( String )(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(2))))), (yyloc));
  };
  break;
    

  case 66:
  if (yyn == 66)
    /* "VondaGrammar.y":307  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatVarDef(false, ((Type)(yystack.valueAt (4-(1)))), (( String )(yystack.valueAt (4-(2)))), ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc));
  };
  break;
    

  case 67:
  if (yyn == 67)
    /* "VondaGrammar.y":313  */ /* lalr1.java:489  */
    { yyval = setPos(new StatPropose(((RTExpression)(yystack.valueAt (5-(3)))), ((StatAbstractBlock)(yystack.valueAt (5-(5))))), (yyloc)); };
  break;
    

  case 68:
  if (yyn == 68)
    /* "VondaGrammar.y":317  */ /* lalr1.java:489  */
    {
    // labeled timeout
    yyval = setPos(new StatTimeout(((RTExpression)(yystack.valueAt (7-(3)))), ((RTExpression)(yystack.valueAt (7-(5)))), ((StatAbstractBlock)(yystack.valueAt (7-(7))))), (yyloc));
  };
  break;
    

  case 69:
  if (yyn == 69)
    /* "VondaGrammar.y":321  */ /* lalr1.java:489  */
    {
    // behaviour timeout
    yyval = setPos(new StatTimeout(((RTExpression)(yystack.valueAt (7-(3)))), ((RTExpression)(yystack.valueAt (7-(5)))), ((StatAbstractBlock)(yystack.valueAt (7-(7))))), (yyloc));
  };
  break;
    

  case 70:
  if (yyn == 70)
    /* "VondaGrammar.y":328  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatSwitch(((RTExpression)(yystack.valueAt (5-(3)))), ((StatAbstractBlock)(yystack.valueAt (5-(5))))), (yyloc));
  };
  break;
    

  case 71:
  if (yyn == 71)
    /* "VondaGrammar.y":354  */ /* lalr1.java:489  */
    {
    ExpLiteral val =
      new ExpLiteral("case \"" + (( ExpLiteral )(yystack.valueAt (3-(2)))).toString() + "\":", "label");
    RTStatement lbl = val.ensureStatement();
    setPos(val, (yyloc)); setPos(lbl, (yyloc));
    yyval = lbl;
  };
  break;
    

  case 72:
  if (yyn == 72)
    /* "VondaGrammar.y":361  */ /* lalr1.java:489  */
    {
    ExpLiteral val =
      new ExpLiteral("case " + (( ExpLiteral )(yystack.valueAt (3-(2)))).toString() + ":", "label");
    RTStatement lbl = val.ensureStatement();
    setPos(val, (yyloc)); setPos(lbl, (yyloc));
    yyval = lbl;
  };
  break;
    

  case 73:
  if (yyn == 73)
    /* "VondaGrammar.y":368  */ /* lalr1.java:489  */
    {
    ExpLiteral val =
      new ExpLiteral("case " + (( ExpLiteral )(yystack.valueAt (3-(2)))).toString() + ":", "label");
    RTStatement lbl = val.ensureStatement();
    setPos(val, (yyloc)); setPos(lbl, (yyloc));
    yyval = lbl;
  };
  break;
    

  case 74:
  if (yyn == 74)
    /* "VondaGrammar.y":375  */ /* lalr1.java:489  */
    {
    ExpLiteral val =
      new ExpLiteral("case " + (( String )(yystack.valueAt (3-(2)))) + ":", "label");
    RTStatement lbl = val.ensureStatement();
    setPos(val, (yyloc)); setPos(lbl, (yyloc));
    yyval = lbl;
  };
  break;
    

  case 75:
  if (yyn == 75)
    /* "VondaGrammar.y":382  */ /* lalr1.java:489  */
    {
    ExpLiteral val = new ExpLiteral("default:", "label");
    RTStatement lbl = val.ensureStatement();
    setPos(val, (yyloc)); setPos(lbl, (yyloc));
    yyval = lbl;
  };
  break;
    

  case 76:
  if (yyn == 76)
    /* "VondaGrammar.y":391  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatVarDef(true, Type.getNoType(), (( String )(yystack.valueAt (4-(2)))), ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc));
  };
  break;
    

  case 77:
  if (yyn == 77)
    /* "VondaGrammar.y":394  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatVarDef(false, ((Type)(yystack.valueAt (4-(1)))), (( String )(yystack.valueAt (4-(2)))), ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc));
  };
  break;
    

  case 78:
  if (yyn == 78)
    /* "VondaGrammar.y":397  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatVarDef(true, ((Type)(yystack.valueAt (5-(2)))), (( String )(yystack.valueAt (5-(3)))), ((RTExpression)(yystack.valueAt (5-(4))))), (yyloc));
  };
  break;
    

  case 79:
  if (yyn == 79)
    /* "VondaGrammar.y":400  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatVarDef(true, Type.getNoType(), (( String )(yystack.valueAt (3-(2)))), null), (yyloc));
  };
  break;
    

  case 80:
  if (yyn == 80)
    /* "VondaGrammar.y":403  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatVarDef(false, ((Type)(yystack.valueAt (3-(1)))), (( String )(yystack.valueAt (3-(2)))), null), (yyloc));
  };
  break;
    

  case 81:
  if (yyn == 81)
    /* "VondaGrammar.y":406  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatVarDef(true, ((Type)(yystack.valueAt (4-(2)))), (( String )(yystack.valueAt (4-(3)))), null), (yyloc));
  };
  break;
    

  case 82:
  if (yyn == 82)
    /* "VondaGrammar.y":412  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatFieldDef(null,
                      setPos(new StatVarDef(false, ((Type)(yystack.valueAt (7-(5)))), (( String )(yystack.valueAt (7-(6)))), null), (yyloc)), ((Type)(yystack.valueAt (7-(2))))), (yyloc));
  };
  break;
    

  case 83:
  if (yyn == 83)
    /* "VondaGrammar.y":419  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (2-(2)))); };
  break;
    

  case 84:
  if (yyn == 84)
    /* "VondaGrammar.y":420  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpListLiteral(new LinkedList<RTExpression>()), yystack.locationAt (3-(2)), yystack.locationAt (3-(3)));
  };
  break;
    

  case 85:
  if (yyn == 85)
    /* "VondaGrammar.y":423  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpListLiteral(((LinkedList<RTExpression>)(yystack.valueAt (4-(3))))), yystack.locationAt (4-(2)), yystack.locationAt (4-(4)));
  };
  break;
    

  case 86:
  if (yyn == 86)
    /* "VondaGrammar.y":429  */ /* lalr1.java:489  */
    { yyval = new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (1-(1))))); }}; };
  break;
    

  case 87:
  if (yyn == 87)
    /* "VondaGrammar.y":430  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))); ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))).addFirst(((RTExpression)(yystack.valueAt (3-(1))))); };
  break;
    

  case 88:
  if (yyn == 88)
    /* "VondaGrammar.y":435  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatMethodDeclaration("public", ((Type)(yystack.valueAt (10-(5)))), ((Type)(yystack.valueAt (10-(2)))), (( String )(yystack.valueAt (10-(6)))), ((LinkedList)(yystack.valueAt (10-(8)))), ((StatAbstractBlock)(yystack.valueAt (10-(10))))), (yyloc));
  };
  break;
    

  case 89:
  if (yyn == 89)
    /* "VondaGrammar.y":438  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatMethodDeclaration("public", ((Type)(yystack.valueAt (6-(1)))), null, (( String )(yystack.valueAt (6-(2)))), ((LinkedList)(yystack.valueAt (6-(4)))), ((StatAbstractBlock)(yystack.valueAt (6-(6))))), (yyloc));
  };
  break;
    

  case 90:
  if (yyn == 90)
    /* "VondaGrammar.y":444  */ /* lalr1.java:489  */
    { yyval = ((StatAbstractBlock)(yystack.valueAt (1-(1)))); };
  break;
    

  case 91:
  if (yyn == 91)
    /* "VondaGrammar.y":445  */ /* lalr1.java:489  */
    { yyval = null; };
  break;
    

  case 92:
  if (yyn == 92)
    /* "VondaGrammar.y":449  */ /* lalr1.java:489  */
    { yyval = ((LinkedList)(yystack.valueAt (1-(1)))); };
  break;
    

  case 93:
  if (yyn == 93)
    /* "VondaGrammar.y":450  */ /* lalr1.java:489  */
    { yyval = new LinkedList(); };
  break;
    

  case 94:
  if (yyn == 94)
    /* "VondaGrammar.y":454  */ /* lalr1.java:489  */
    { yyval = new LinkedList(){{ add(Type.getNoType()); add((( String )(yystack.valueAt (1-(1))))); }}; };
  break;
    

  case 95:
  if (yyn == 95)
    /* "VondaGrammar.y":455  */ /* lalr1.java:489  */
    { yyval = new LinkedList(){{ add(((Type)(yystack.valueAt (2-(1))))); add((( String )(yystack.valueAt (2-(2))))); }}; };
  break;
    

  case 96:
  if (yyn == 96)
    /* "VondaGrammar.y":456  */ /* lalr1.java:489  */
    {
    yyval = ((LinkedList)(yystack.valueAt (3-(3))));
    ((LinkedList)(yystack.valueAt (3-(3)))).addFirst((( String )(yystack.valueAt (3-(1))))); ((LinkedList)(yystack.valueAt (3-(3)))).addFirst(Type.getNoType());
  };
  break;
    

  case 97:
  if (yyn == 97)
    /* "VondaGrammar.y":460  */ /* lalr1.java:489  */
    {
    yyval = ((LinkedList)(yystack.valueAt (4-(4)))); ((LinkedList)(yystack.valueAt (4-(4)))).addFirst((( String )(yystack.valueAt (4-(2))))); ((LinkedList)(yystack.valueAt (4-(4)))).addFirst(((Type)(yystack.valueAt (4-(1)))));
  };
  break;
    

  case 98:
  if (yyn == 98)
    /* "VondaGrammar.y":468  */ /* lalr1.java:489  */
    {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (4-(1))))), yystack.locationAt (4-(1)));
    yyval = setPos(new StatSetOperation(var, true, ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc));
  };
  break;
    

  case 99:
  if (yyn == 99)
    /* "VondaGrammar.y":472  */ /* lalr1.java:489  */
    {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (4-(1))))), yystack.locationAt (4-(1)));
    yyval = setPos(new StatSetOperation(var, false, ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc));
  };
  break;
    

  case 100:
  if (yyn == 100)
    /* "VondaGrammar.y":476  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatSetOperation(((RTExpression)(yystack.valueAt (4-(1)))), true, ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc));
  };
  break;
    

  case 101:
  if (yyn == 101)
    /* "VondaGrammar.y":479  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatSetOperation(((RTExpression)(yystack.valueAt (4-(1)))), false, ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc));
  };
  break;
    

  case 102:
  if (yyn == 102)
    /* "VondaGrammar.y":482  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatSetOperation(((RTExpression)(yystack.valueAt (4-(1)))), true, ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc));
  };
  break;
    

  case 103:
  if (yyn == 103)
    /* "VondaGrammar.y":485  */ /* lalr1.java:489  */
    {
    yyval = setPos(new StatSetOperation(((RTExpression)(yystack.valueAt (4-(1)))), false, ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc));
  };
  break;
    

  case 104:
  if (yyn == 104)
    /* "VondaGrammar.y":494  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpFuncCall((( String )(yystack.valueAt (3-(1)))), new LinkedList<RTExpression>(), false), (yyloc));
  };
  break;
    

  case 105:
  if (yyn == 105)
    /* "VondaGrammar.y":497  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpFuncCall((( String )(yystack.valueAt (4-(1)))), ((LinkedList<RTExpression>)(yystack.valueAt (4-(3)))), false), (yyloc));
  };
  break;
    

  case 106:
  if (yyn == 106)
    /* "VondaGrammar.y":503  */ /* lalr1.java:489  */
    { yyval = new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (1-(1))))); }}; };
  break;
    

  case 107:
  if (yyn == 107)
    /* "VondaGrammar.y":504  */ /* lalr1.java:489  */
    { yyval = new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (1-(1))))); }}; };
  break;
    

  case 108:
  if (yyn == 108)
    /* "VondaGrammar.y":505  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))); ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))).addFirst(((RTExpression)(yystack.valueAt (3-(1))))); };
  break;
    

  case 109:
  if (yyn == 109)
    /* "VondaGrammar.y":506  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))); ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))).addFirst(((RTExpression)(yystack.valueAt (3-(1))))); };
  break;
    

  case 110:
  if (yyn == 110)
    /* "VondaGrammar.y":510  */ /* lalr1.java:489  */
    {
    yyval = new Type("Array",
                  new ArrayList<Type>(){{ add(new Type((( String )(yystack.valueAt (3-(1)))))); }});
  };
  break;
    

  case 111:
  if (yyn == 111)
    /* "VondaGrammar.y":514  */ /* lalr1.java:489  */
    { yyval = new Type((( String )(yystack.valueAt (1-(1))))); };
  break;
    

  case 112:
  if (yyn == 112)
    /* "VondaGrammar.y":515  */ /* lalr1.java:489  */
    { yyval = new Type((( String )(yystack.valueAt (4-(1)))), ((LinkedList<Type>)(yystack.valueAt (4-(3))))); };
  break;
    

  case 113:
  if (yyn == 113)
    /* "VondaGrammar.y":519  */ /* lalr1.java:489  */
    { yyval = new LinkedList<Type>(){{ add(((Type)(yystack.valueAt (1-(1))))); }}; };
  break;
    

  case 114:
  if (yyn == 114)
    /* "VondaGrammar.y":520  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<Type>)(yystack.valueAt (3-(3)))); ((LinkedList<Type>)(yystack.valueAt (3-(3)))).addFirst(((Type)(yystack.valueAt (3-(1))))); };
  break;
    

  case 115:
  if (yyn == 115)
    /* "VondaGrammar.y":524  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "||"), (yyloc));
  };
  break;
    

  case 116:
  if (yyn == 116)
    /* "VondaGrammar.y":527  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 117:
  if (yyn == 117)
    /* "VondaGrammar.y":531  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "&&"), (yyloc));
  };
  break;
    

  case 118:
  if (yyn == 118)
    /* "VondaGrammar.y":534  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 119:
  if (yyn == 119)
    /* "VondaGrammar.y":538  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 120:
  if (yyn == 120)
    /* "VondaGrammar.y":539  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "|"), (yyloc));
  };
  break;
    

  case 121:
  if (yyn == 121)
    /* "VondaGrammar.y":545  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 122:
  if (yyn == 122)
    /* "VondaGrammar.y":546  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "^"), (yyloc));
  };
  break;
    

  case 123:
  if (yyn == 123)
    /* "VondaGrammar.y":552  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 124:
  if (yyn == 124)
    /* "VondaGrammar.y":553  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "&"), (yyloc));
  };
  break;
    

  case 125:
  if (yyn == 125)
    /* "VondaGrammar.y":559  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 126:
  if (yyn == 126)
    /* "VondaGrammar.y":560  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "=="), (yyloc));
  };
  break;
    

  case 127:
  if (yyn == 127)
    /* "VondaGrammar.y":563  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "!="), (yyloc));
  };
  break;
    

  case 128:
  if (yyn == 128)
    /* "VondaGrammar.y":569  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 129:
  if (yyn == 129)
    /* "VondaGrammar.y":570  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "<"), (yyloc));
  };
  break;
    

  case 130:
  if (yyn == 130)
    /* "VondaGrammar.y":573  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), ">"), (yyloc));
  };
  break;
    

  case 131:
  if (yyn == 131)
    /* "VondaGrammar.y":576  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), ">="), (yyloc));
  };
  break;
    

  case 132:
  if (yyn == 132)
    /* "VondaGrammar.y":579  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "<="), (yyloc));
  };
  break;
    

  case 133:
  if (yyn == 133)
    /* "VondaGrammar.y":585  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 134:
  if (yyn == 134)
    /* "VondaGrammar.y":586  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "+"), (yyloc));
  };
  break;
    

  case 135:
  if (yyn == 135)
    /* "VondaGrammar.y":589  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "-"), (yyloc));
  };
  break;
    

  case 136:
  if (yyn == 136)
    /* "VondaGrammar.y":595  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 137:
  if (yyn == 137)
    /* "VondaGrammar.y":596  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "*"), (yyloc));
  };
  break;
    

  case 138:
  if (yyn == 138)
    /* "VondaGrammar.y":599  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "/"), (yyloc));
  };
  break;
    

  case 139:
  if (yyn == 139)
    /* "VondaGrammar.y":602  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (3-(1)))), ((RTExpression)(yystack.valueAt (3-(3)))), "%"), (yyloc));
  };
  break;
    

  case 140:
  if (yyn == 140)
    /* "VondaGrammar.y":608  */ /* lalr1.java:489  */
    {
    yyval = createPlusMinus(((RTExpression)(yystack.valueAt (2-(2)))), "++", (yyloc));
  };
  break;
    

  case 141:
  if (yyn == 141)
    /* "VondaGrammar.y":611  */ /* lalr1.java:489  */
    {
    yyval = createPlusMinus(((RTExpression)(yystack.valueAt (2-(2)))), "--", (yyloc));
  };
  break;
    

  case 142:
  if (yyn == 142)
    /* "VondaGrammar.y":614  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2-(2)))), null, "+"), (yyloc)); };
  break;
    

  case 143:
  if (yyn == 143)
    /* "VondaGrammar.y":615  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2-(2)))), null, "-"), (yyloc)); };
  break;
    

  case 144:
  if (yyn == 144)
    /* "VondaGrammar.y":616  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 145:
  if (yyn == 145)
    /* "VondaGrammar.y":620  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 146:
  if (yyn == 146)
    /* "VondaGrammar.y":621  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpCast(((Type)(yystack.valueAt (4-(2)))), ((RTExpression)(yystack.valueAt (4-(4))))), (yyloc)); };
  break;
    

  case 147:
  if (yyn == 147)
    /* "VondaGrammar.y":626  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 148:
  if (yyn == 148)
    /* "VondaGrammar.y":627  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (2-(2)))), null, "!"), (yyloc)); };
  break;
    

  case 149:
  if (yyn == 149)
    /* "VondaGrammar.y":628  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2-(2)))), null, "~"), (yyloc)); };
  break;
    

  case 150:
  if (yyn == 150)
    /* "VondaGrammar.y":632  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 151:
  if (yyn == 151)
    /* "VondaGrammar.y":633  */ /* lalr1.java:489  */
    {
    yyval = createPlusMinus(((RTExpression)(yystack.valueAt (2-(1)))), "+++", (yyloc));
  };
  break;
    

  case 152:
  if (yyn == 152)
    /* "VondaGrammar.y":636  */ /* lalr1.java:489  */
    {
    yyval = createPlusMinus(((RTExpression)(yystack.valueAt (2-(1)))), "---", (yyloc));
  };
  break;
    

  case 153:
  if (yyn == 153)
    /* "VondaGrammar.y":642  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpLiteral("null", "null"), (yyloc)); };
  break;
    

  case 154:
  if (yyn == 154)
    /* "VondaGrammar.y":643  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 155:
  if (yyn == 155)
    /* "VondaGrammar.y":644  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 156:
  if (yyn == 156)
    /* "VondaGrammar.y":648  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpIdentifier((( String )(yystack.valueAt (1-(1))))), (yyloc)); };
  break;
    

  case 157:
  if (yyn == 157)
    /* "VondaGrammar.y":649  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpCast(((Type)(yystack.valueAt (6-(3)))), ((RTExpression)(yystack.valueAt (6-(5))))), (yyloc)); };
  break;
    

  case 158:
  if (yyn == 158)
    /* "VondaGrammar.y":653  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (3-(2)))); ((RTExpression)(yystack.valueAt (3-(2)))).generateParens(); };
  break;
    

  case 159:
  if (yyn == 159)
    /* "VondaGrammar.y":654  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 160:
  if (yyn == 160)
    /* "VondaGrammar.y":658  */ /* lalr1.java:489  */
    { yyval = ((ExpLiteral)(yystack.valueAt (1-(1)))); };
  break;
    

  case 161:
  if (yyn == 161)
    /* "VondaGrammar.y":659  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 162:
  if (yyn == 162)
    /* "VondaGrammar.y":660  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 163:
  if (yyn == 163)
    /* "VondaGrammar.y":661  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 164:
  if (yyn == 164)
    /* "VondaGrammar.y":662  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 165:
  if (yyn == 165)
    /* "VondaGrammar.y":666  */ /* lalr1.java:489  */
    { yyval = setPos((( ExpLiteral )(yystack.valueAt (1-(1)))), (yyloc)); };
  break;
    

  case 166:
  if (yyn == 166)
    /* "VondaGrammar.y":667  */ /* lalr1.java:489  */
    { yyval = setPos((( ExpLiteral )(yystack.valueAt (1-(1)))), (yyloc)); };
  break;
    

  case 167:
  if (yyn == 167)
    /* "VondaGrammar.y":668  */ /* lalr1.java:489  */
    { yyval = setPos((( ExpLiteral )(yystack.valueAt (1-(1)))), (yyloc)); };
  break;
    

  case 168:
  if (yyn == 168)
    /* "VondaGrammar.y":669  */ /* lalr1.java:489  */
    { yyval = setPos((( ExpLiteral )(yystack.valueAt (1-(1)))), (yyloc)); };
  break;
    

  case 169:
  if (yyn == 169)
    /* "VondaGrammar.y":673  */ /* lalr1.java:489  */
    {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (4-(1))))), yystack.locationAt (4-(1)));
    yyval = setPos(new ExpArrayAccess(var, ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc));
  };
  break;
    

  case 170:
  if (yyn == 170)
    /* "VondaGrammar.y":677  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpArrayAccess(((RTExpression)(yystack.valueAt (4-(1)))), ((RTExpression)(yystack.valueAt (4-(3))))), (yyloc)); };
  break;
    

  case 171:
  if (yyn == 171)
    /* "VondaGrammar.y":681  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpConditional(((RTExpression)(yystack.valueAt (5-(1)))), ((RTExpression)(yystack.valueAt (5-(3)))), ((RTExpression)(yystack.valueAt (5-(5))))), (yyloc)); };
  break;
    

  case 172:
  if (yyn == 172)
    /* "VondaGrammar.y":682  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 173:
  if (yyn == 173)
    /* "VondaGrammar.y":688  */ /* lalr1.java:489  */
    {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (2-(1))))), yystack.locationAt (2-(1)));
    yyval = setPos(new ExpAssignment(var, ((RTExpression)(yystack.valueAt (2-(2))))), (yyloc));
  };
  break;
    

  case 174:
  if (yyn == 174)
    /* "VondaGrammar.y":692  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpAssignment(((RTExpression)(yystack.valueAt (2-(1)))), ((RTExpression)(yystack.valueAt (2-(2))))), (yyloc)); };
  break;
    

  case 175:
  if (yyn == 175)
    /* "VondaGrammar.y":693  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpAssignment(((RTExpression)(yystack.valueAt (2-(1)))), ((RTExpression)(yystack.valueAt (2-(2))))), (yyloc)); };
  break;
    

  case 176:
  if (yyn == 176)
    /* "VondaGrammar.y":697  */ /* lalr1.java:489  */
    {
    //List<String> repr = new ArrayList<>($2.size());
    //for (int i = 0; i < $2.size(); ++i) repr.add("");
    //$$ = setPos(new ExpFieldAccess($2, repr), @$); $2.addFirst($1);
    yyval = setPos(new ExpFieldAccess(((LinkedList<RTExpression>)(yystack.valueAt (2-(2))))), (yyloc)); ((LinkedList<RTExpression>)(yystack.valueAt (2-(2)))).addFirst(((RTExpression)(yystack.valueAt (2-(1)))));
  };
  break;
    

  case 177:
  if (yyn == 177)
    /* "VondaGrammar.y":703  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpFieldAccess(((LinkedList<RTExpression>)(yystack.valueAt (2-(2))))), (yyloc)); ((LinkedList<RTExpression>)(yystack.valueAt (2-(2)))).addFirst(setPos((( ExpLiteral )(yystack.valueAt (2-(1)))), (yyloc)));
  };
  break;
    

  case 178:
  if (yyn == 178)
    /* "VondaGrammar.y":706  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpFieldAccess(((LinkedList<RTExpression>)(yystack.valueAt (2-(2))))), (yyloc)); ((LinkedList<RTExpression>)(yystack.valueAt (2-(2)))).addFirst(((RTExpression)(yystack.valueAt (2-(1))))); };
  break;
    

  case 179:
  if (yyn == 179)
    /* "VondaGrammar.y":711  */ /* lalr1.java:489  */
    { yyval = ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))); ((LinkedList<RTExpression>)(yystack.valueAt (3-(3)))).addFirst(((RTExpression)(yystack.valueAt (3-(2))))); };
  break;
    

  case 180:
  if (yyn == 180)
    /* "VondaGrammar.y":712  */ /* lalr1.java:489  */
    { yyval = new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (2-(2))))); }}; };
  break;
    

  case 181:
  if (yyn == 181)
    /* "VondaGrammar.y":716  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpIdentifier((( String )(yystack.valueAt (1-(1))))), (yyloc)); };
  break;
    

  case 182:
  if (yyn == 182)
    /* "VondaGrammar.y":717  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 183:
  if (yyn == 183)
    /* "VondaGrammar.y":718  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (3-(2)))); };
  break;
    

  case 184:
  if (yyn == 184)
    /* "VondaGrammar.y":722  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpNew(new Type((( String )(yystack.valueAt (2-(2)))))), (yyloc)); };
  break;
    

  case 185:
  if (yyn == 185)
    /* "VondaGrammar.y":723  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpNew(new Type((( String )(yystack.valueAt (4-(2))))), new LinkedList<>()), (yyloc));
  };
  break;
    

  case 186:
  if (yyn == 186)
    /* "VondaGrammar.y":726  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpNew(new Type((( String )(yystack.valueAt (5-(2))))), ((LinkedList<RTExpression>)(yystack.valueAt (5-(4))))), (yyloc));
  };
  break;
    

  case 187:
  if (yyn == 187)
    /* "VondaGrammar.y":729  */ /* lalr1.java:489  */
    {
    List<Type> sub = new ArrayList<Type>() {{ add(new Type((( String )(yystack.valueAt (5-(2)))))); }};
    yyval = setPos(new ExpNew(new Type("Array", sub),
                    new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (5-(4))))); }}), (yyloc));
  };
  break;
    

  case 188:
  if (yyn == 188)
    /* "VondaGrammar.y":734  */ /* lalr1.java:489  */
    {
    List<Type> sub = new ArrayList<Type>() {{ add(new Type((( String )(yystack.valueAt (7-(2)))))); }};
    yyval = setPos(new ExpNew(new Type("Array", sub),
                    new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (7-(6))))); }}), (yyloc));
  };
  break;
    

  case 189:
  if (yyn == 189)
    /* "VondaGrammar.y":739  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpNew(new Type((( String )(yystack.valueAt (7-(2)))), ((LinkedList<Type>)(yystack.valueAt (7-(4))))),
                    new LinkedList<>()), (yyloc));
  };
  break;
    

  case 190:
  if (yyn == 190)
    /* "VondaGrammar.y":743  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpNew(new Type((( String )(yystack.valueAt (8-(2)))), ((LinkedList<Type>)(yystack.valueAt (8-(4))))),
                    ((LinkedList<RTExpression>)(yystack.valueAt (8-(7))))), (yyloc));
  };
  break;
    

  case 191:
  if (yyn == 191)
    /* "VondaGrammar.y":750  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpLambda(((LinkedList)(yystack.valueAt (5-(2)))), ((RTExpression)(yystack.valueAt (5-(5))))), (yyloc));
  };
  break;
    

  case 192:
  if (yyn == 192)
    /* "VondaGrammar.y":753  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpLambda(((LinkedList)(yystack.valueAt (5-(2)))), ((StatAbstractBlock)(yystack.valueAt (5-(5))))), (yyloc));
  };
  break;
    

  case 193:
  if (yyn == 193)
    /* "VondaGrammar.y":760  */ /* lalr1.java:489  */
    {
    yyval = setPos(new ExpDialogueAct(((RTExpression)(yystack.valueAt (6-(2)))), ((RTExpression)(yystack.valueAt (6-(4)))), ((LinkedList<RTExpression>)(yystack.valueAt (6-(5))))), (yyloc));
  };
  break;
    

  case 194:
  if (yyn == 194)
    /* "VondaGrammar.y":766  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (3-(2)))); };
  break;
    

  case 195:
  if (yyn == 195)
    /* "VondaGrammar.y":767  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpIdentifier((( String )(yystack.valueAt (1-(1))))), (yyloc)); };
  break;
    

  case 196:
  if (yyn == 196)
    /* "VondaGrammar.y":768  */ /* lalr1.java:489  */
    { yyval = setPos((( ExpLiteral )(yystack.valueAt (1-(1)))), (yyloc)); };
  break;
    

  case 197:
  if (yyn == 197)
    /* "VondaGrammar.y":769  */ /* lalr1.java:489  */
    { yyval = setPos(new ExpLiteral((( String )(yystack.valueAt (1-(1)))), "String"), (yyloc)); };
  break;
    

  case 198:
  if (yyn == 198)
    /* "VondaGrammar.y":773  */ /* lalr1.java:489  */
    {
    yyval = ((LinkedList<RTExpression>)(yystack.valueAt (5-(5)))); ((LinkedList<RTExpression>)(yystack.valueAt (5-(5)))).addFirst(((RTExpression)(yystack.valueAt (5-(4))))); ((LinkedList<RTExpression>)(yystack.valueAt (5-(5)))).addFirst(((RTExpression)(yystack.valueAt (5-(2)))));
  };
  break;
    

  case 199:
  if (yyn == 199)
    /* "VondaGrammar.y":776  */ /* lalr1.java:489  */
    { yyval = new LinkedList<RTExpression>(); };
  break;
    

  case 200:
  if (yyn == 200)
    /* "VondaGrammar.y":780  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 201:
  if (yyn == 201)
    /* "VondaGrammar.y":781  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    

  case 202:
  if (yyn == 202)
    /* "VondaGrammar.y":782  */ /* lalr1.java:489  */
    { yyval = ((RTExpression)(yystack.valueAt (1-(1)))); };
  break;
    


/* "VondaGrammar.java":2132  */ /* lalr1.java:489  */
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

  private static final short yypact_ninf_ = -365;
  private static final short yytable_ninf_ = -157;

  /* YYPACT[STATE-NUM] -- Index in YYTABLE of the portion describing
   STATE-NUM.  */
  private static final short yypact_[] = yypact_init();
  private static final short[] yypact_init()
  {
    return new short[]
    {
     888,   133,    12,    27,   235,    43,    -6,   959,    77,    99,
     135,    84,  -365,   144,  -365,  -365,   487,   148,   156,   166,
      26,    98,   123,  -365,  -365,   172,  -365,   581,  1360,   180,
      75,   227,    10,   888,   888,  -365,  -365,  -365,  -365,  -365,
    -365,  -365,  -365,  -365,  -365,   888,   888,   888,  -365,   798,
     197,   123,   189,  -365,  -365,   -22,   242,    46,  -365,   243,
    -365,  -365,  -365,   216,   241,   246,   247,  -365,  -365,  -365,
     264,  -365,    89,   249,   -26,   254,   160,  1393,  -365,   127,
    1393,   255,  -365,  1525,  1525,    92,  -365,  1426,  1558,  1558,
    1525,  1525,   123,   -23,   275,   248,   250,   245,   130,    21,
     119,   220,  -365,  -365,  -365,   153,  -365,   123,   189,   261,
    -365,  -365,   261,  -365,   265,  1393,  1393,  1393,   123,    54,
     267,   123,   273,    67,   274,    -4,  -365,  1393,  1393,   277,
     278,   304,   992,  1026,  1059,   180,  -365,  -365,  -365,   959,
     276,  1426,   279,    15,   271,  -365,  -365,  -365,  1393,   281,
    -365,   180,   888,   888,  -365,  -365,  -365,  -365,  -365,  -365,
    -365,  -365,    47,  -365,  1393,  1393,  1393,  -365,  -365,  1393,
    1393,   282,   283,  -365,  -365,  -365,  -365,  -365,  -365,  -365,
     287,   -14,  -365,   285,   284,    25,   164,  1393,  1092,   297,
     291,  -365,   300,   293,    90,    50,  -365,  -365,  -365,  -365,
    1393,   188,   294,  -365,  -365,  -365,  -365,  1558,  1393,  1558,
    1558,  1558,  1558,  1558,  1558,  1558,  1558,  1558,  1558,  1558,
    1558,  1558,  1558,  1558,  -365,  -365,  -365,   295,   310,   312,
     301,  -365,   180,  -365,  -365,  -365,   316,  1393,  -365,   123,
     322,   324,  -365,  -365,  -365,  1459,  -365,   319,   321,   325,
    -365,   317,  1125,  -365,   327,   323,  -365,  -365,   330,  -365,
     336,   328,    75,   329,  -365,  -365,  -365,   344,   343,   335,
     345,   347,   349,   354,  -365,  -365,  1393,  -365,  -365,   359,
    1393,   361,   362,  1158,   366,   -21,   959,  -365,   346,  1192,
    1226,   180,  1558,   275,   363,   248,   250,   245,   130,    21,
      21,   119,   119,   119,   119,   220,   220,  -365,  -365,  -365,
     346,  1393,  1393,   959,   369,   370,  -365,  -365,  -365,   181,
     371,  -365,    55,  -365,  1492,  1492,  -365,  -365,   368,   358,
     180,  -365,  1558,   180,  -365,   372,   377,   -35,   374,   384,
    -365,  -365,  -365,  -365,  -365,  -365,   380,  -365,   381,  -365,
    1393,   959,   386,  1259,  1393,   383,   422,  -365,  -365,   388,
     390,   389,   387,  -365,  1393,  -365,   394,   396,  -365,  1525,
    -365,   344,   420,   397,  -365,  -365,  -365,  1393,  -365,   399,
     408,    75,   401,   180,    19,  -365,   959,   403,  -365,   959,
     959,   404,   406,  -365,   959,  -365,  1393,  -365,   411,  -365,
     346,   346,   399,  -365,  1293,   344,  -365,  -365,    76,   407,
    -365,   415,  -365,  -365,  -365,  -365,   959,  -365,  -365,   959,
     959,  -365,   413,  1326,  -365,  -365,  -365,  -365,  -365,  -365,
     344,    75,   419,  -365,  -365,  -365,  -365,  -365,   414,   423,
     372,  -365,    19,  -365,  -365
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
       0,     0,   165,   166,   168,   111,   167,     0,     0,     0,
       0,     0,     0,    10,    10,    17,    29,    31,    34,    35,
      36,    32,    33,    37,    38,    10,    10,    10,    30,    10,
       0,     0,     0,   159,   160,   161,     0,   162,   164,     0,
      49,    51,    52,     0,     0,     0,     0,    53,    75,    39,
       0,    40,   163,     0,   111,     0,     0,     0,    15,     0,
       0,     0,   153,     0,     0,   156,    47,     0,     0,     0,
       0,     0,   163,   172,   116,   118,   119,   121,   123,   125,
     128,   133,   145,   136,   144,   147,   150,   154,   155,   161,
     200,   201,   162,   202,     0,     0,     0,     0,     0,   156,
       0,     0,     0,   156,     0,     0,   177,     0,     0,     0,
       0,     0,     0,     0,     0,     0,   173,    43,    41,    44,
       0,     0,     0,   111,     0,   196,   197,   195,     0,     0,
       1,     0,    10,    10,     6,     5,     8,     9,     3,    28,
       4,   178,     0,   176,     0,     0,     0,   175,    18,     0,
       0,     0,     0,    19,   174,    50,    71,    72,    73,    74,
       0,     0,    79,     0,     0,     0,   111,     0,     0,     0,
       0,    14,     0,     0,   184,   156,   141,   161,   162,   140,
       0,   156,     0,   142,   143,   148,   149,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,   152,   151,    48,     0,   164,     0,
       0,    21,     0,    25,    20,    24,   181,     0,   182,   180,
       0,     0,    23,    22,    46,    93,   104,     0,   107,   106,
     110,     0,     0,    83,   113,     0,    45,    42,     0,   158,
       0,     0,     0,     0,     7,     2,    80,    93,     0,     0,
       0,     0,     0,     0,    27,    26,     0,    76,    81,     0,
       0,     0,     0,     0,     0,     0,     0,    16,     0,     0,
       0,     0,     0,   115,     0,   117,   120,   122,   124,   126,
     127,   131,   132,   129,   130,   134,   135,   137,   138,   139,
       0,     0,     0,     0,     0,     0,   179,    99,    98,   156,
       0,    92,     0,   105,     0,     0,   169,    84,     0,    86,
       0,   112,     0,     0,   194,   199,     0,    94,     0,     0,
      77,   170,   101,   100,   103,   102,     0,    78,     0,    65,
       0,     0,     0,     0,     0,     0,    54,    67,   185,     0,
       0,     0,     0,   146,     0,    70,     0,     0,    56,     0,
     183,     0,     0,    95,   109,   108,    85,     0,   114,   145,
       0,     0,     0,     0,     0,    57,     0,     0,    61,     0,
       0,     0,     0,    66,     0,   186,     0,   187,     0,   171,
       0,     0,     0,    96,     0,     0,    87,   157,     0,     0,
     193,     0,    91,    90,    89,    63,     0,    59,    60,     0,
       0,    55,     0,     0,    69,    68,   192,   191,    97,    82,
      93,     0,     0,    62,    58,    64,   188,   189,     0,     0,
     199,   190,     0,   198,    88
    };
  }

/* YYPGOTO[NTERM-NUM].  */
  private static final short yypgoto_[] = yypgoto_init();
  private static final short[] yypgoto_init()
  {
    return new short[]
    {
    -365,   152,  -365,  -365,  -365,    59,    49,  -365,  -271,   350,
    -365,  -365,   357,  -365,  -365,  -365,  -365,  -365,  -365,  -365,
     116,  -365,    -2,  -287,   435,    48,  -266,  -364,  -365,   147,
    -116,     0,  -282,  -365,   286,   288,   289,   280,   296,    29,
      61,    45,   -71,   -66,  -365,  -365,  -365,   311,   348,  -365,
    -365,   263,  -365,   365,    37,   -16,  -365,  -365,  -365,   376,
    -259,    56,   592
    };
  }

/* YYDEFGOTO[NTERM-NUM].  */
  private static final short yydefgoto_[] = yydefgoto_init();
  private static final short[] yydefgoto_init()
  {
    return new short[]
    {
      -1,    31,    32,    33,    79,    69,   138,   139,    35,   140,
      36,    37,    38,    39,    40,   188,    41,    42,    43,    44,
      71,    46,   136,   328,    47,   414,   320,   321,    48,    92,
     247,    73,   255,    93,    94,    95,    96,    97,    98,    99,
     100,   101,   102,   103,   104,   105,   106,   107,   108,    53,
      54,   109,   110,   111,   112,   161,   239,   113,   248,    58,
     149,   382,   142
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
      50,   338,   359,   335,   207,  -111,   126,   403,    75,   362,
     165,   166,   196,   199,   371,   183,   182,   357,   135,   205,
     206,     8,   203,   204,   183,   354,   134,   135,   266,   144,
     134,   134,    50,    50,    50,   163,   236,    57,   134,   365,
      68,   428,   208,   237,    57,    50,    50,    50,   378,    50,
     143,   215,   216,   167,    61,   174,    70,   122,   124,    34,
     151,   412,   118,    27,    57,   183,   119,   278,   135,    62,
      57,    57,   184,   120,   217,   218,   189,   134,   169,   170,
     171,   172,    57,    57,    57,    67,    57,   202,   173,   266,
     406,   163,    34,    34,   267,   373,   231,   132,   134,   134,
     200,   132,   126,   292,    34,    34,    34,   167,    34,   234,
     174,   145,   146,   413,   132,   147,    45,    74,   429,   148,
     198,   198,   409,   430,    78,   198,   198,   198,   198,   424,
     425,   159,   125,   426,   118,   254,   438,   289,   123,   132,
     290,   258,   200,   291,   134,   120,    76,    49,   152,    45,
      45,   263,    50,    50,    72,   307,   308,   309,   213,   214,
     268,    45,    45,    45,   439,    45,   125,   121,   121,   191,
     192,   413,   440,    59,    72,    60,    57,   219,   220,   268,
      49,    49,    77,   279,   281,   154,   155,   224,   225,    57,
      57,    80,    49,    49,    49,   115,    49,   156,   157,   158,
     186,   160,   187,   116,   127,   128,   129,   130,   374,   375,
     280,    34,    34,   117,   183,  -156,   134,   135,   131,   132,
     143,  -111,   133,   316,   134,   135,   363,   150,   132,   -94,
     371,   133,   314,   134,   135,   132,  -111,   162,   133,   164,
     134,   135,   299,   300,   198,   322,   198,   198,   198,   198,
     198,   198,   198,   198,   198,   198,   198,   198,   198,   198,
     198,   379,   176,    55,   305,   306,   363,   339,    45,    45,
      55,    63,   238,    64,    65,    66,   301,   302,   303,   304,
     221,   222,   223,   355,   168,   175,    72,   177,   180,   181,
      55,   254,   178,   179,   185,   194,    55,    55,   402,    49,
      49,   209,   212,   210,   264,   265,   211,   226,    55,    55,
      55,    51,    55,   134,   232,   233,   235,    10,    51,   242,
     243,   257,   260,    57,   274,   275,   277,   259,   262,   198,
     254,    51,    51,   380,   276,   356,   250,   285,    51,   286,
     287,   288,   292,   310,    51,    51,   197,   197,    52,   313,
      57,   197,   197,   197,   197,    52,    51,    51,    51,   311,
      51,   312,   368,   132,   317,    56,   318,   323,   326,   198,
     324,   339,    56,   334,   325,    52,   330,   331,   332,   333,
     336,    52,    52,   411,   337,   340,   341,   342,    57,   343,
      27,   344,    56,    52,    52,    52,   345,    52,    56,    56,
     388,   347,    55,   349,   350,   339,   198,   377,   353,   364,
      56,    56,    56,   376,    56,    55,    55,   369,   370,   372,
     383,   381,   384,    57,   373,   393,    57,    57,   385,   386,
     339,    57,   394,    72,   389,   415,   395,   396,   417,   418,
     397,   398,   400,   421,   401,   404,   405,   407,   408,   410,
      51,   416,   419,    57,   420,   432,    57,    57,   423,   431,
      72,   436,   441,    51,    51,   433,   430,   153,   434,   435,
     197,   442,   197,   197,   197,   197,   197,   197,   197,   197,
     197,   197,   197,   197,   197,   197,   197,    52,   244,   256,
     444,   297,   228,   293,     0,     0,   443,   295,    72,   296,
      52,    52,    81,    82,    56,     0,     0,     0,   298,     0,
       0,     0,     0,     0,     0,     0,     0,    56,    56,     0,
       0,    83,    84,    22,     0,    23,    24,    85,    26,    86,
       0,     0,     0,    72,    87,     0,    72,    72,     0,     0,
       0,    72,     0,     0,     0,    88,    89,     0,     0,    55,
      90,    91,     0,    30,     0,   197,     0,     0,     0,     0,
       0,     0,     0,    72,     0,     0,    72,    72,     0,     0,
       0,     0,     0,     0,     0,     0,    55,     0,     0,     0,
       0,     0,     0,     0,     1,     2,     3,     4,     5,     6,
       7,     0,     8,     9,    10,   197,     0,    51,     0,    13,
       0,     0,    16,    17,    18,    19,     0,     0,   114,     0,
       0,     0,     0,     0,    55,    20,    21,    22,     0,    23,
      24,    25,    26,     0,    51,    27,   137,     0,    28,     0,
       0,     0,   197,     0,    52,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,    30,     0,    55,
       0,    56,    55,    55,     0,     0,     0,    55,     0,     0,
       0,    52,    51,     0,     0,     0,     0,     0,     0,   190,
       0,     0,   193,     0,     0,     0,     0,     0,    56,    55,
       0,     0,    55,    55,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,    51,     0,    52,
      51,    51,     0,     0,     0,    51,     0,   227,   229,   230,
       0,     0,     0,     0,     0,     0,    56,     0,     0,   240,
     241,     0,     0,     0,   249,   251,   253,    51,     0,     0,
      51,    51,     0,     0,    52,     0,     0,    52,    52,     0,
     261,     0,    52,     0,     0,     0,     0,     0,     0,     0,
       0,    56,     0,     0,    56,    56,   269,   270,   271,    56,
       0,   272,   273,     0,    52,     0,     0,    52,    52,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,   282,
     284,    56,     0,     0,    56,    56,     0,     0,     0,     0,
       0,     0,   251,     0,     0,     0,     0,     0,     0,     0,
     294,     1,     2,     3,     4,     5,     6,     7,     0,     8,
       9,    10,    11,     0,     0,    12,    13,    14,    15,    16,
      17,    18,    19,     0,     0,     0,     0,     0,     0,   315,
       0,     0,    20,    21,    22,     0,    23,    24,    25,    26,
     159,   125,    27,     0,   329,    28,     0,     0,    29,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,    30,     0,     0,     0,   346,     0,
       0,     0,   348,     0,     0,   352,     0,     0,     0,     0,
       0,   329,   361,     0,     0,     0,     0,     0,     0,     0,
       0,     1,     2,     3,     4,     5,     6,     7,     0,     8,
       9,    10,    11,   366,   367,    12,    13,    14,    15,    16,
      17,    18,    19,     0,     0,     0,   249,   249,     0,     0,
       0,     0,    20,    21,    22,     0,    23,    24,    25,    26,
       0,     0,    27,     0,     0,    28,     0,     0,    29,     0,
       0,     0,   387,     0,     0,   391,   392,     0,     0,     0,
       0,     0,     0,     0,    30,     0,   399,     0,     0,     0,
       0,     0,     1,     2,     3,     4,     5,     6,     7,   329,
       8,     9,    10,     0,     0,     0,     0,    13,     0,     0,
      16,    17,    18,    19,     0,     0,     0,     0,   422,     0,
       0,     0,     0,    20,    21,    22,   427,    23,    24,    25,
      26,     0,     0,    27,     0,     0,    28,    81,    82,     0,
       0,     0,     0,     0,     0,   329,     0,     0,     0,     0,
       0,     0,     0,     0,     0,    30,    83,    84,    22,     0,
      23,    24,    85,    26,     0,     0,     0,     0,     0,   245,
     246,    81,    82,     0,     0,     0,     0,     0,     0,     0,
      88,    89,     0,     0,     0,    90,    91,     0,    30,     0,
      83,    84,    22,     0,    23,    24,    85,    26,     0,     0,
       0,     0,     0,    87,    81,    82,     0,   250,     0,     0,
       0,     0,     0,     0,    88,    89,     0,     0,     0,    90,
      91,     0,    30,    83,    84,    22,     0,    23,    24,    85,
      26,     0,     0,   252,     0,     0,    87,    81,    82,     0,
       0,     0,     0,     0,     0,     0,     0,    88,    89,     0,
       0,     0,    90,    91,     0,    30,    83,    84,    22,     0,
      23,    24,    85,    26,   283,     0,     0,     0,     0,    87,
      81,    82,     0,     0,     0,     0,     0,     0,     0,     0,
      88,    89,     0,     0,     0,    90,    91,     0,    30,    83,
      84,    22,     0,    23,    24,    85,    26,     0,     0,     0,
     327,     0,    87,    81,    82,     0,     0,     0,     0,     0,
       0,     0,     0,    88,    89,     0,     0,     0,    90,    91,
       0,    30,    83,    84,    22,     0,    23,    24,    85,    26,
       0,     0,     0,     0,     0,    87,   351,    81,    82,     0,
       0,     0,     0,     0,     0,     0,    88,    89,     0,     0,
       0,    90,    91,     0,    30,     0,    83,    84,    22,     0,
      23,    24,    85,    26,     0,     0,     0,     0,     0,    87,
     358,    81,    82,     0,     0,     0,     0,     0,     0,     0,
      88,    89,     0,     0,     0,    90,    91,     0,    30,     0,
      83,    84,    22,     0,    23,    24,    85,    26,     0,     0,
       0,     0,     0,    87,    81,    82,     0,   360,     0,     0,
       0,     0,     0,     0,    88,    89,     0,     0,     0,    90,
      91,     0,    30,    83,    84,    22,     0,    23,    24,    85,
      26,     0,     0,     0,     0,     0,    87,   390,    81,    82,
       0,     0,     0,     0,     0,     0,     0,    88,    89,     0,
       0,     0,    90,    91,     0,    30,     0,    83,    84,    22,
       0,    23,    24,    85,    26,     0,     0,    27,     0,     0,
      87,    81,    82,     0,     0,     0,     0,     0,     0,     0,
       0,    88,    89,     0,     0,     0,    90,    91,     0,    30,
      83,    84,    22,     0,    23,    24,    85,    26,     0,     0,
       0,     0,     0,    87,   437,    81,    82,     0,     0,     0,
       0,     0,     0,     0,    88,    89,     0,     0,     0,    90,
      91,     0,    30,     0,    83,    84,    22,     0,    23,    24,
      85,    26,     0,     0,     0,     0,     0,   141,    81,    82,
       0,     0,     0,     0,     0,     0,     0,     0,    88,    89,
       0,     0,     0,    90,    91,     0,    30,    83,    84,    22,
       0,    23,    24,    85,    26,     0,     0,     0,     0,     0,
      87,    81,    82,     0,     0,     0,     0,     0,     0,     0,
       0,    88,    89,     0,     0,     0,    90,    91,     0,    30,
      83,    84,    22,     0,    23,    24,   201,    26,     0,     0,
       0,     0,     0,   141,    81,    82,     0,     0,     0,     0,
       0,     0,     0,     0,    88,    89,     0,     0,     0,    90,
      91,     0,    30,    83,    84,    22,     0,    23,    24,   319,
      26,     0,     0,     0,     0,     0,   141,    81,    82,     0,
       0,     0,     0,     0,     0,     0,     0,    88,    89,     0,
       0,     0,    90,    91,     0,    30,    83,    84,    22,     0,
      23,    24,    85,    26,     0,     0,     0,     0,     0,   245,
       0,    82,     0,     0,     0,     0,     0,     0,     0,     0,
      88,    89,     0,     0,     0,    90,    91,     0,    30,    83,
      84,    22,     0,    23,    24,   195,    26,     0,     0,     0,
       0,     0,    28,     0,    82,     0,     0,     0,     0,     0,
       0,     0,     0,    88,    89,     0,     0,     0,    90,    91,
       0,    30,    83,    84,    22,     0,    23,    24,   195,    26,
       0,     0,     0,     0,     0,    87,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,    88,    89,     0,     0,
       0,    90,    91,     0,    30
    };
  }

private static final short yycheck_[] = yycheck_init();
  private static final short[] yycheck_init()
  {
    return new short[]
    {
       0,   267,   289,   262,    27,    40,    22,   371,     8,   291,
      32,    33,    83,    84,    49,    50,    42,   288,    53,    90,
      91,    11,    88,    89,    50,    46,    52,    53,    42,    29,
      52,    52,    32,    33,    34,    51,    40,     0,    52,   310,
      46,   405,    65,    47,     7,    45,    46,    47,   330,    49,
      40,    30,    31,    55,    42,    57,     7,    20,    21,     0,
      50,    42,    36,    44,    27,    50,    40,    42,    53,    42,
      33,    34,    74,    47,    53,    54,    76,    52,    32,    33,
      34,    35,    45,    46,    47,    42,    49,    87,    42,    42,
     377,   107,    33,    34,    47,    40,    42,    47,    52,    52,
      50,    47,   118,    48,    45,    46,    47,   109,    49,    42,
     112,    36,    37,   384,    47,    40,     0,    40,    42,    44,
      83,    84,   381,    47,    40,    88,    89,    90,    91,   400,
     401,    42,    43,   404,    36,   135,   423,    47,    40,    47,
      50,   141,    50,    53,    52,    47,    47,     0,    32,    33,
      34,   151,   152,   153,     7,   221,   222,   223,    28,    29,
     162,    45,    46,    47,   430,    49,    43,    20,    21,    42,
      43,   442,   431,    40,    27,    42,   139,    58,    59,   181,
      33,    34,    47,   185,   186,    33,    34,    34,    35,   152,
     153,    47,    45,    46,    47,    47,    49,    45,    46,    47,
      40,    49,    42,    47,    32,    33,    34,    35,   324,   325,
      46,   152,   153,    47,    50,    43,    52,    53,    46,    47,
      40,    40,    50,   239,    52,    53,   292,     0,    47,    48,
      49,    50,   232,    52,    53,    47,    48,    40,    50,    50,
      52,    53,   213,   214,   207,   245,   209,   210,   211,   212,
     213,   214,   215,   216,   217,   218,   219,   220,   221,   222,
     223,   332,    46,     0,   219,   220,   332,   267,   152,   153,
       7,    36,   125,    38,    39,    40,   215,   216,   217,   218,
      60,    61,    62,   285,    42,    42,   139,    46,    24,    40,
      27,   291,    46,    46,    40,    40,    33,    34,   369,   152,
     153,    26,    57,    55,   152,   153,    56,    42,    45,    46,
      47,     0,    49,    52,    47,    42,    42,    13,     7,    42,
      42,    45,    51,   286,    42,    42,    42,    48,    47,   292,
     330,    20,    21,   333,    47,   286,    51,    40,    27,    48,
      40,    48,    48,    48,    33,    34,    83,    84,     0,    48,
     313,    88,    89,    90,    91,     7,    45,    46,    47,    49,
      49,    49,   313,    47,    42,     0,    42,    48,    51,   332,
      49,   371,     7,    45,    49,    27,    49,    54,    48,    43,
      51,    33,    34,   383,    40,    42,    51,    42,   351,    42,
      44,    42,    27,    45,    46,    47,    42,    49,    33,    34,
     351,    42,   139,    42,    42,   405,   369,    49,    42,    46,
      45,    46,    47,    45,    49,   152,   153,    48,    48,    48,
      43,    49,    48,   386,    40,    42,   389,   390,    48,    48,
     430,   394,    10,   286,    48,   386,    48,    47,   389,   390,
      51,    54,    48,   394,    48,    25,    49,    48,    40,    48,
     139,    48,    48,   416,    48,    40,   419,   420,    47,    52,
     313,    48,    48,   152,   153,   416,    47,    32,   419,   420,
     207,    48,   209,   210,   211,   212,   213,   214,   215,   216,
     217,   218,   219,   220,   221,   222,   223,   139,   131,   139,
     442,   211,   116,   207,    -1,    -1,   440,   209,   351,   210,
     152,   153,    15,    16,   139,    -1,    -1,    -1,   212,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,   152,   153,    -1,
      -1,    34,    35,    36,    -1,    38,    39,    40,    41,    42,
      -1,    -1,    -1,   386,    47,    -1,   389,   390,    -1,    -1,
      -1,   394,    -1,    -1,    -1,    58,    59,    -1,    -1,   286,
      63,    64,    -1,    66,    -1,   292,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,   416,    -1,    -1,   419,   420,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,   313,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,     3,     4,     5,     6,     7,     8,
       9,    -1,    11,    12,    13,   332,    -1,   286,    -1,    18,
      -1,    -1,    21,    22,    23,    24,    -1,    -1,    16,    -1,
      -1,    -1,    -1,    -1,   351,    34,    35,    36,    -1,    38,
      39,    40,    41,    -1,   313,    44,    45,    -1,    47,    -1,
      -1,    -1,   369,    -1,   286,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    66,    -1,   386,
      -1,   286,   389,   390,    -1,    -1,    -1,   394,    -1,    -1,
      -1,   313,   351,    -1,    -1,    -1,    -1,    -1,    -1,    77,
      -1,    -1,    80,    -1,    -1,    -1,    -1,    -1,   313,   416,
      -1,    -1,   419,   420,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,   386,    -1,   351,
     389,   390,    -1,    -1,    -1,   394,    -1,   115,   116,   117,
      -1,    -1,    -1,    -1,    -1,    -1,   351,    -1,    -1,   127,
     128,    -1,    -1,    -1,   132,   133,   134,   416,    -1,    -1,
     419,   420,    -1,    -1,   386,    -1,    -1,   389,   390,    -1,
     148,    -1,   394,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,   386,    -1,    -1,   389,   390,   164,   165,   166,   394,
      -1,   169,   170,    -1,   416,    -1,    -1,   419,   420,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,   187,
     188,   416,    -1,    -1,   419,   420,    -1,    -1,    -1,    -1,
      -1,    -1,   200,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
     208,     3,     4,     5,     6,     7,     8,     9,    -1,    11,
      12,    13,    14,    -1,    -1,    17,    18,    19,    20,    21,
      22,    23,    24,    -1,    -1,    -1,    -1,    -1,    -1,   237,
      -1,    -1,    34,    35,    36,    -1,    38,    39,    40,    41,
      42,    43,    44,    -1,   252,    47,    -1,    -1,    50,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    66,    -1,    -1,    -1,   276,    -1,
      -1,    -1,   280,    -1,    -1,   283,    -1,    -1,    -1,    -1,
      -1,   289,   290,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,     3,     4,     5,     6,     7,     8,     9,    -1,    11,
      12,    13,    14,   311,   312,    17,    18,    19,    20,    21,
      22,    23,    24,    -1,    -1,    -1,   324,   325,    -1,    -1,
      -1,    -1,    34,    35,    36,    -1,    38,    39,    40,    41,
      -1,    -1,    44,    -1,    -1,    47,    -1,    -1,    50,    -1,
      -1,    -1,   350,    -1,    -1,   353,   354,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    66,    -1,   364,    -1,    -1,    -1,
      -1,    -1,     3,     4,     5,     6,     7,     8,     9,   377,
      11,    12,    13,    -1,    -1,    -1,    -1,    18,    -1,    -1,
      21,    22,    23,    24,    -1,    -1,    -1,    -1,   396,    -1,
      -1,    -1,    -1,    34,    35,    36,   404,    38,    39,    40,
      41,    -1,    -1,    44,    -1,    -1,    47,    15,    16,    -1,
      -1,    -1,    -1,    -1,    -1,   423,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    66,    34,    35,    36,    -1,
      38,    39,    40,    41,    -1,    -1,    -1,    -1,    -1,    47,
      48,    15,    16,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      58,    59,    -1,    -1,    -1,    63,    64,    -1,    66,    -1,
      34,    35,    36,    -1,    38,    39,    40,    41,    -1,    -1,
      -1,    -1,    -1,    47,    15,    16,    -1,    51,    -1,    -1,
      -1,    -1,    -1,    -1,    58,    59,    -1,    -1,    -1,    63,
      64,    -1,    66,    34,    35,    36,    -1,    38,    39,    40,
      41,    -1,    -1,    44,    -1,    -1,    47,    15,    16,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    58,    59,    -1,
      -1,    -1,    63,    64,    -1,    66,    34,    35,    36,    -1,
      38,    39,    40,    41,    42,    -1,    -1,    -1,    -1,    47,
      15,    16,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      58,    59,    -1,    -1,    -1,    63,    64,    -1,    66,    34,
      35,    36,    -1,    38,    39,    40,    41,    -1,    -1,    -1,
      45,    -1,    47,    15,    16,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    58,    59,    -1,    -1,    -1,    63,    64,
      -1,    66,    34,    35,    36,    -1,    38,    39,    40,    41,
      -1,    -1,    -1,    -1,    -1,    47,    48,    15,    16,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    58,    59,    -1,    -1,
      -1,    63,    64,    -1,    66,    -1,    34,    35,    36,    -1,
      38,    39,    40,    41,    -1,    -1,    -1,    -1,    -1,    47,
      48,    15,    16,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      58,    59,    -1,    -1,    -1,    63,    64,    -1,    66,    -1,
      34,    35,    36,    -1,    38,    39,    40,    41,    -1,    -1,
      -1,    -1,    -1,    47,    15,    16,    -1,    51,    -1,    -1,
      -1,    -1,    -1,    -1,    58,    59,    -1,    -1,    -1,    63,
      64,    -1,    66,    34,    35,    36,    -1,    38,    39,    40,
      41,    -1,    -1,    -1,    -1,    -1,    47,    48,    15,    16,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    58,    59,    -1,
      -1,    -1,    63,    64,    -1,    66,    -1,    34,    35,    36,
      -1,    38,    39,    40,    41,    -1,    -1,    44,    -1,    -1,
      47,    15,    16,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    58,    59,    -1,    -1,    -1,    63,    64,    -1,    66,
      34,    35,    36,    -1,    38,    39,    40,    41,    -1,    -1,
      -1,    -1,    -1,    47,    48,    15,    16,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    58,    59,    -1,    -1,    -1,    63,
      64,    -1,    66,    -1,    34,    35,    36,    -1,    38,    39,
      40,    41,    -1,    -1,    -1,    -1,    -1,    47,    15,    16,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    58,    59,
      -1,    -1,    -1,    63,    64,    -1,    66,    34,    35,    36,
      -1,    38,    39,    40,    41,    -1,    -1,    -1,    -1,    -1,
      47,    15,    16,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    58,    59,    -1,    -1,    -1,    63,    64,    -1,    66,
      34,    35,    36,    -1,    38,    39,    40,    41,    -1,    -1,
      -1,    -1,    -1,    47,    15,    16,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    58,    59,    -1,    -1,    -1,    63,
      64,    -1,    66,    34,    35,    36,    -1,    38,    39,    40,
      41,    -1,    -1,    -1,    -1,    -1,    47,    15,    16,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    58,    59,    -1,
      -1,    -1,    63,    64,    -1,    66,    34,    35,    36,    -1,
      38,    39,    40,    41,    -1,    -1,    -1,    -1,    -1,    47,
      -1,    16,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      58,    59,    -1,    -1,    -1,    63,    64,    -1,    66,    34,
      35,    36,    -1,    38,    39,    40,    41,    -1,    -1,    -1,
      -1,    -1,    47,    -1,    16,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    58,    59,    -1,    -1,    -1,    63,    64,
      -1,    66,    34,    35,    36,    -1,    38,    39,    40,    41,
      -1,    -1,    -1,    -1,    -1,    47,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    58,    59,    -1,    -1,
      -1,    63,    64,    -1,    66
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
      24,    40,    42,    50,    89,    40,    40,    42,    82,    98,
     129,    42,    43,   129,    40,    40,   109,   118,   121,   109,
      50,    40,    98,   110,   110,   109,   109,    27,    65,    26,
      55,    56,    57,    28,    29,    30,    31,    53,    54,    58,
      59,    60,    61,    62,    34,    35,    42,   129,   126,   129,
     129,    42,    47,    42,    42,    42,    40,    47,    96,   123,
     129,   129,    42,    42,    79,    47,    48,    97,   125,   129,
      51,   129,    44,   129,    98,    99,    76,    45,    98,    48,
      51,   129,    47,    98,    68,    68,    42,    47,    89,   129,
     129,   129,   129,   129,    42,    42,    47,    42,    42,    89,
      46,    89,   129,    42,   129,    40,    48,    40,    48,    47,
      50,    53,    48,   101,   129,   102,   103,   104,   105,   106,
     106,   107,   107,   107,   107,   108,   108,   110,   110,   110,
      48,    49,    49,    48,    98,   129,   122,    42,    42,    40,
      93,    94,    98,    48,    49,    49,    51,    45,    90,   129,
      49,    54,    48,    43,    45,   127,    51,    40,    93,    98,
      42,    51,    42,    42,    42,    42,   129,    42,   129,    42,
      42,    48,   129,    42,    46,    89,    73,    75,    48,    90,
      51,   129,    99,   110,    46,    75,   129,   129,    73,    48,
      48,    49,    48,    40,    97,    97,    45,    49,    99,   109,
      98,    49,   128,    43,    48,    48,    48,   129,    73,    48,
      48,   129,   129,    42,    10,    48,    47,    51,    54,   129,
      48,    48,   109,    94,    25,    49,    90,    48,    40,   127,
      48,    98,    42,    75,    92,    73,    48,    73,    73,    48,
      48,    73,   129,    47,    75,    75,    75,   129,    94,    42,
      47,    52,    40,    73,    73,    73,    48,    48,    90,    93,
     127,    48,    48,   128,    92
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
      81,    81,    81,    81,    81,    82,    82,    83,    84,    84,
      85,    86,    86,    86,    86,    86,    87,    87,    87,    87,
      87,    87,    88,    89,    89,    89,    90,    90,    91,    91,
      92,    92,    93,    93,    94,    94,    94,    94,    95,    95,
      95,    95,    95,    95,    96,    96,    97,    97,    97,    97,
      98,    98,    98,    99,    99,   100,   100,   101,   101,   102,
     102,   103,   103,   104,   104,   105,   105,   105,   106,   106,
     106,   106,   106,   107,   107,   107,   108,   108,   108,   108,
     109,   109,   109,   109,   109,   110,   110,   111,   111,   111,
     112,   112,   112,   113,   113,   113,   114,   114,   115,   115,
     116,   116,   116,   116,   116,   117,   117,   117,   117,   118,
     118,   119,   119,   120,   120,   120,   121,   121,   121,   122,
     122,   123,   123,   123,   124,   124,   124,   124,   124,   124,
     124,   125,   125,   126,   127,   127,   127,   127,   128,   128,
     129,   129,   129
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
       7,     6,     8,     7,     8,     3,     4,     5,     7,     7,
       5,     3,     3,     3,     3,     2,     4,     4,     5,     3,
       3,     4,     7,     2,     3,     4,     1,     3,    10,     6,
       1,     1,     1,     0,     1,     2,     3,     4,     4,     4,
       4,     4,     4,     4,     3,     4,     1,     1,     3,     3,
       3,     1,     4,     1,     3,     3,     1,     3,     1,     1,
       3,     1,     3,     1,     3,     1,     3,     3,     1,     3,
       3,     3,     3,     1,     3,     3,     1,     3,     3,     3,
       2,     2,     2,     2,     1,     1,     4,     1,     2,     2,
       1,     2,     2,     1,     1,     1,     1,     6,     3,     1,
       1,     1,     1,     1,     1,     1,     1,     1,     1,     4,
       4,     5,     1,     2,     2,     2,     2,     2,     2,     3,
       2,     1,     1,     3,     2,     4,     5,     5,     7,     7,
       8,     5,     5,     6,     3,     1,     1,     1,     5,     0,
       1,     1,     1
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
       0,   147,   147,   150,   151,   152,   154,   155,   158,   161,
     164,   168,   169,   170,   174,   182,   183,   187,   188,   189,
     190,   194,   198,   202,   206,   209,   212,   215,   218,   219,
     220,   221,   222,   223,   224,   225,   226,   227,   228,   232,
     233,   237,   243,   244,   248,   249,   253,   257,   258,   259,
     260,   261,   262,   263,   267,   268,   274,   275,   281,   283,
     285,   287,   289,   291,   295,   304,   307,   313,   317,   321,
     328,   354,   361,   368,   375,   382,   391,   394,   397,   400,
     403,   406,   412,   419,   420,   423,   429,   430,   435,   438,
     444,   445,   449,   450,   454,   455,   456,   460,   468,   472,
     476,   479,   482,   485,   494,   497,   503,   504,   505,   506,
     510,   514,   515,   519,   520,   524,   527,   531,   534,   538,
     539,   545,   546,   552,   553,   559,   560,   563,   569,   570,
     573,   576,   579,   585,   586,   589,   595,   596,   599,   602,
     608,   611,   614,   615,   616,   620,   621,   626,   627,   628,
     632,   633,   636,   642,   643,   644,   648,   649,   653,   654,
     658,   659,   660,   661,   662,   666,   667,   668,   669,   673,
     677,   681,   682,   688,   692,   693,   697,   703,   706,   711,
     712,   716,   717,   718,   722,   723,   726,   729,   734,   739,
     743,   750,   753,   760,   766,   767,   768,   769,   773,   776,
     780,   781,   782
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

  private static final int yylast_ = 1624;
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
      return new StatVarDef(false, Type.getNoType(), assign);
    }
    return new StatExpression(assign);
  }

  // generic method
  private ExpArithmetic createPlusMinus(RTExpression variable,
                                        String plusOrMinus,
                                        Location loc) {
    /*
    ExpLiteral es = setPos(new ExpLiteral("1", "int"), loc);
    ExpArithmetic ar = setPos(new ExpArithmetic(variable, es, plusOrMinus), loc);
    ExpAssignment ass = setPos(new ExpAssignment(variable, ar), loc);
    */
    ExpArithmetic ass =
      setPos(new ExpArithmetic(variable, null, plusOrMinus), loc);
    return ass;
  }

/* "VondaGrammar.java":3364  */ /* lalr1.java:1066  */

}

