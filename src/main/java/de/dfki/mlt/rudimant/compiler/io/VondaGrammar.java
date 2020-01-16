/* A Bison parser, made by GNU Bison 3.4.1.  */

/* Skeleton implementation for Bison LALR(1) parsers in Java

   Copyright (C) 2007-2015, 2018 Free Software Foundation, Inc.

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



/* "%code imports" blocks.  */
/* "VondaGrammar.y":3  */


import java.io.Reader;
import java.util.*;
import de.dfki.mlt.rudimant.compiler.Type;
import de.dfki.mlt.rudimant.common.Position;
import de.dfki.mlt.rudimant.compiler.tree.*;

@SuppressWarnings({"serial", "unchecked", "fallthrough", "unused"})

/* "VondaGrammar.java":50  */

/**
 * A Bison parser, automatically generated from <tt>VondaGrammar.y</tt>.
 *
 * @author LALR (1) parser skeleton written by Paolo Bonzini.
 */
public class VondaGrammar
{
    /** Version number for the Bison executable that generated this parser.  */
  public static final String bisonVersion = "3.4.1";

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
    static final int IDENTIFIER = 295;
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
    public void print (java.io.PrintStream out) {
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
    /* "VondaGrammar.y":140  */
    {
    yyval = ((LinkedList<RudiTree>)(yystack.valueAt (0))); ((StatMethodDeclaration)(yystack.valueAt (1))).setVisibility(((String)(yystack.valueAt (2)))); ((LinkedList<RudiTree>)(yystack.valueAt (0))).addFirst(((StatMethodDeclaration)(yystack.valueAt (1))));
  };
  break;
    

  case 3:
  if (yyn == 3)
    /* "VondaGrammar.y":143  */
    { yyval = ((LinkedList<RudiTree>)(yystack.valueAt (0))); ((LinkedList<RudiTree>)(yystack.valueAt (0))).addFirst(((StatMethodDeclaration)(yystack.valueAt (1)))); };
  break;
    

  case 4:
  if (yyn == 4)
    /* "VondaGrammar.y":144  */
    { yyval = ((LinkedList<RudiTree>)(yystack.valueAt (0))); ((LinkedList<RudiTree>)(yystack.valueAt (0))).addFirst(((RTExpression)(yystack.valueAt (1)))); };
  break;
    

  case 5:
  if (yyn == 5)
    /* "VondaGrammar.y":145  */
    { yyval = ((LinkedList<RudiTree>)(yystack.valueAt (0))); ((LinkedList<RudiTree>)(yystack.valueAt (0))).addFirst(((RTStatement)(yystack.valueAt (1)))); };
  break;
    

  case 6:
  if (yyn == 6)
    /* "VondaGrammar.y":147  */
    { yyval = ((LinkedList<RudiTree>)(yystack.valueAt (0))); ((LinkedList<RudiTree>)(yystack.valueAt (0))).addFirst(((Import)(yystack.valueAt (1)))); };
  break;
    

  case 7:
  if (yyn == 7)
    /* "VondaGrammar.y":148  */
    {
    yyval = ((LinkedList<RudiTree>)(yystack.valueAt (0))); ((LinkedList<RudiTree>)(yystack.valueAt (0))).addFirst(setPos(new StatFieldDef(((String)(yystack.valueAt (2))), ((StatVarDef)(yystack.valueAt (1)))), yystack.locationAt (2), yystack.locationAt (1)));
  };
  break;
    

  case 8:
  if (yyn == 8)
    /* "VondaGrammar.y":151  */
    {
    yyval = ((LinkedList<RudiTree>)(yystack.valueAt (0))); ((LinkedList<RudiTree>)(yystack.valueAt (0))).addFirst(setPos(new StatFieldDef(null, ((StatVarDef)(yystack.valueAt (1)))), yystack.locationAt (1)));
  };
  break;
    

  case 9:
  if (yyn == 9)
    /* "VondaGrammar.y":154  */
    {
    yyval = ((LinkedList<RudiTree>)(yystack.valueAt (0))); ((LinkedList<RudiTree>)(yystack.valueAt (0))).addFirst(((StatFieldDef)(yystack.valueAt (1))));
  };
  break;
    

  case 10:
  if (yyn == 10)
    /* "VondaGrammar.y":157  */
    { yyval = _statements;};
  break;
    

  case 11:
  if (yyn == 11)
    /* "VondaGrammar.y":161  */
    { yyval = "public"; };
  break;
    

  case 12:
  if (yyn == 12)
    /* "VondaGrammar.y":162  */
    { yyval = "protected"; };
  break;
    

  case 13:
  if (yyn == 13)
    /* "VondaGrammar.y":163  */
    { yyval = "private"; };
  break;
    

  case 14:
  if (yyn == 14)
    /* "VondaGrammar.y":167  */
    {
    List<String> path = ((List<String>)(yystack.valueAt (1)));
    String name = path.remove(path.size() - 1);
    yyval = setPos(new Import(name, path.toArray(new String[path.size()])), (yyloc));
  };
  break;
    

  case 15:
  if (yyn == 15)
    /* "VondaGrammar.y":175  */
    { yyval = new ArrayList<String>(){{ add((( String )(yystack.valueAt (0)))); }}; };
  break;
    

  case 16:
  if (yyn == 16)
    /* "VondaGrammar.y":176  */
    { yyval = ((List<String>)(yystack.valueAt (2))); ((List<String>)(yystack.valueAt (2))).add((( String )(yystack.valueAt (0)))); };
  break;
    

  case 17:
  if (yyn == 17)
    /* "VondaGrammar.y":180  */
    { yyval = ((StatAbstractBlock)(yystack.valueAt (0))); };
  break;
    

  case 18:
  if (yyn == 18)
    /* "VondaGrammar.y":181  */
    { yyval = setPos(getAssignmentStat(((RTExpression)(yystack.valueAt (1)))), (yyloc)); };
  break;
    

  case 19:
  if (yyn == 19)
    /* "VondaGrammar.y":182  */
    { yyval = setPos(new StatExpression(((RTExpression)(yystack.valueAt (1)))), (yyloc)); };
  break;
    

  case 20:
  if (yyn == 20)
    /* "VondaGrammar.y":183  */
    {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (1)))), yystack.locationAt (1));
    yyval = setPos(new StatExpression(createPlusMinus(var, "++", (yyloc))), (yyloc));
  };
  break;
    

  case 21:
  if (yyn == 21)
    /* "VondaGrammar.y":187  */
    {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (1)))), yystack.locationAt (1));
    yyval = setPos(new StatExpression(createPlusMinus(var, "--", (yyloc))), (yyloc));
  };
  break;
    

  case 22:
  if (yyn == 22)
    /* "VondaGrammar.y":191  */
    {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (2)))), yystack.locationAt (2));
    yyval = setPos(new StatExpression(createPlusMinus(var, "+++", (yyloc))), (yyloc));
  };
  break;
    

  case 23:
  if (yyn == 23)
    /* "VondaGrammar.y":195  */
    {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (2)))), yystack.locationAt (2));
    yyval = setPos(new StatExpression(createPlusMinus(var, "---", (yyloc))), (yyloc));
  };
  break;
    

  case 24:
  if (yyn == 24)
    /* "VondaGrammar.y":199  */
    {
    yyval = setPos(new StatExpression(createPlusMinus(((RTExpression)(yystack.valueAt (1))), "++", (yyloc))), (yyloc));
  };
  break;
    

  case 25:
  if (yyn == 25)
    /* "VondaGrammar.y":202  */
    {
    yyval = setPos(new StatExpression(createPlusMinus(((RTExpression)(yystack.valueAt (1))), "--", (yyloc))), (yyloc));
  };
  break;
    

  case 26:
  if (yyn == 26)
    /* "VondaGrammar.y":205  */
    {
    yyval = setPos(new StatExpression(createPlusMinus(((RTExpression)(yystack.valueAt (2))), "+++", (yyloc))), (yyloc));
  };
  break;
    

  case 27:
  if (yyn == 27)
    /* "VondaGrammar.y":208  */
    {
    yyval = setPos(new StatExpression(createPlusMinus(((RTExpression)(yystack.valueAt (2))), "---", (yyloc))), (yyloc));
  };
  break;
    

  case 28:
  if (yyn == 28)
    /* "VondaGrammar.y":211  */
    { yyval = setPos(new StatExpression(((RTExpression)(yystack.valueAt (1)))), (yyloc)); };
  break;
    

  case 29:
  if (yyn == 29)
    /* "VondaGrammar.y":212  */
    { yyval = ((StatGrammarRule)(yystack.valueAt (0))); };
  break;
    

  case 30:
  if (yyn == 30)
    /* "VondaGrammar.y":213  */
    { yyval = ((RTStatement)(yystack.valueAt (0))); };
  break;
    

  case 31:
  if (yyn == 31)
    /* "VondaGrammar.y":214  */
    { yyval = ((RTStatement)(yystack.valueAt (0))); };
  break;
    

  case 32:
  if (yyn == 32)
    /* "VondaGrammar.y":215  */
    { yyval = ((RTStatement)(yystack.valueAt (0))); };
  break;
    

  case 33:
  if (yyn == 33)
    /* "VondaGrammar.y":216  */
    { yyval = ((RTStatement)(yystack.valueAt (0))); };
  break;
    

  case 34:
  if (yyn == 34)
    /* "VondaGrammar.y":217  */
    { yyval = ((StatIf)(yystack.valueAt (0))); };
  break;
    

  case 35:
  if (yyn == 35)
    /* "VondaGrammar.y":218  */
    { yyval = ((RTStatement)(yystack.valueAt (0))); };
  break;
    

  case 36:
  if (yyn == 36)
    /* "VondaGrammar.y":219  */
    { yyval = ((RTStatement)(yystack.valueAt (0))); };
  break;
    

  case 37:
  if (yyn == 37)
    /* "VondaGrammar.y":220  */
    { yyval = ((RTStatement)(yystack.valueAt (0))); };
  break;
    

  case 38:
  if (yyn == 38)
    /* "VondaGrammar.y":221  */
    { yyval = ((RTStatement)(yystack.valueAt (0))); };
  break;
    

  case 39:
  if (yyn == 39)
    /* "VondaGrammar.y":225  */
    { yyval = ((RTStatement)(yystack.valueAt (0))); };
  break;
    

  case 40:
  if (yyn == 40)
    /* "VondaGrammar.y":226  */
    { yyval = ((StatVarDef)(yystack.valueAt (0))); };
  break;
    

  case 41:
  if (yyn == 41)
    /* "VondaGrammar.y":230  */
    { yyval = ((RTStatement)(yystack.valueAt (0))); };
  break;
    

  case 42:
  if (yyn == 42)
    /* "VondaGrammar.y":236  */
    { yyval = setPos(new StatAbstractBlock(((LinkedList<RTStatement>)(yystack.valueAt (1))), true), (yyloc)); };
  break;
    

  case 43:
  if (yyn == 43)
    /* "VondaGrammar.y":237  */
    {
    yyval = setPos(new StatAbstractBlock(new ArrayList<RTStatement>(), true), (yyloc));
  };
  break;
    

  case 44:
  if (yyn == 44)
    /* "VondaGrammar.y":241  */
    { yyval = new LinkedList<RTStatement>(){{ add(((RTStatement)(yystack.valueAt (0)))); }}; };
  break;
    

  case 45:
  if (yyn == 45)
    /* "VondaGrammar.y":242  */
    { yyval = ((LinkedList<RTStatement>)(yystack.valueAt (0))); ((LinkedList<RTStatement>)(yystack.valueAt (0))).addFirst(((RTStatement)(yystack.valueAt (1)))); };
  break;
    

  case 46:
  if (yyn == 46)
    /* "VondaGrammar.y":246  */
    { yyval = setPos(new StatGrammarRule((( String )(yystack.valueAt (2))), ((StatIf)(yystack.valueAt (0)))), (yyloc)); };
  break;
    

  case 47:
  if (yyn == 47)
    /* "VondaGrammar.y":250  */
    { yyval = setPos(new StatReturn("return"), (yyloc)); };
  break;
    

  case 48:
  if (yyn == 48)
    /* "VondaGrammar.y":251  */
    { yyval = setPos(new StatReturn(((RTExpression)(yystack.valueAt (1)))), (yyloc)); };
  break;
    

  case 49:
  if (yyn == 49)
    /* "VondaGrammar.y":252  */
    { yyval = setPos(new StatReturn("break"), (yyloc)); };
  break;
    

  case 50:
  if (yyn == 50)
    /* "VondaGrammar.y":253  */
    { yyval = setPos(new StatReturn("break", (( String )(yystack.valueAt (1)))), (yyloc)); };
  break;
    

  case 51:
  if (yyn == 51)
    /* "VondaGrammar.y":254  */
    { yyval = setPos(new StatReturn("cancel"), (yyloc)); };
  break;
    

  case 52:
  if (yyn == 52)
    /* "VondaGrammar.y":255  */
    { yyval = setPos(new StatReturn("cancel_all"), (yyloc)); };
  break;
    

  case 53:
  if (yyn == 53)
    /* "VondaGrammar.y":256  */
    { yyval = setPos(new StatReturn("continue"), (yyloc)); };
  break;
    

  case 54:
  if (yyn == 54)
    /* "VondaGrammar.y":260  */
    { yyval = setPos(new StatIf(((RTExpression)(yystack.valueAt (2))), ((RTStatement)(yystack.valueAt (0))), null), (yyloc)); };
  break;
    

  case 55:
  if (yyn == 55)
    /* "VondaGrammar.y":261  */
    {
    yyval = setPos(new StatIf(((RTExpression)(yystack.valueAt (4))), ((RTStatement)(yystack.valueAt (2))), ((RTStatement)(yystack.valueAt (0)))), (yyloc));
  };
  break;
    

  case 56:
  if (yyn == 56)
    /* "VondaGrammar.y":267  */
    { yyval = setPos(new StatWhile(((RTExpression)(yystack.valueAt (2))), ((RTStatement)(yystack.valueAt (0))), true), (yyloc)); };
  break;
    

  case 57:
  if (yyn == 57)
    /* "VondaGrammar.y":268  */
    {
    yyval = setPos(new StatWhile(((RTExpression)(yystack.valueAt (1))), ((RTStatement)(yystack.valueAt (4))), false), (yyloc));
  };
  break;
    

  case 58:
  if (yyn == 58)
    /* "VondaGrammar.y":274  */
    {
    yyval = setPos(new StatFor1(((StatVarDef)(yystack.valueAt (5))), ((RTExpression)(yystack.valueAt (4))), ((RTExpression)(yystack.valueAt (2))), ((RTStatement)(yystack.valueAt (0)))), (yyloc)); };
  break;
    

  case 59:
  if (yyn == 59)
    /* "VondaGrammar.y":276  */
    {
    yyval = setPos(new StatFor1(((StatVarDef)(yystack.valueAt (4))), null, ((RTExpression)(yystack.valueAt (2))), ((RTStatement)(yystack.valueAt (0)))), (yyloc)); };
  break;
    

  case 60:
  if (yyn == 60)
    /* "VondaGrammar.y":278  */
    {
    yyval = setPos(new StatFor1(((StatVarDef)(yystack.valueAt (4))), ((RTExpression)(yystack.valueAt (3))), null, ((RTStatement)(yystack.valueAt (0)))), (yyloc)); };
  break;
    

  case 61:
  if (yyn == 61)
    /* "VondaGrammar.y":280  */
    {
    yyval = setPos(new StatFor1(((StatVarDef)(yystack.valueAt (3))), null, null, ((RTStatement)(yystack.valueAt (0)))), (yyloc)); };
  break;
    

  case 62:
  if (yyn == 62)
    /* "VondaGrammar.y":282  */
    {
    yyval = setPos(new StatFor1(null, ((RTExpression)(yystack.valueAt (4))), ((RTExpression)(yystack.valueAt (2))), ((RTStatement)(yystack.valueAt (0)))), (yyloc)); };
  break;
    

  case 63:
  if (yyn == 63)
    /* "VondaGrammar.y":284  */
    {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (4)))), yystack.locationAt (4));
    yyval = setPos(new StatFor2(var, ((RTExpression)(yystack.valueAt (2))), ((RTStatement)(yystack.valueAt (0)))), (yyloc));
  };
  break;
    

  case 64:
  if (yyn == 64)
    /* "VondaGrammar.y":288  */
    {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (4)))), yystack.locationAt (4));
    yyval = setPos(new StatFor2(((Type)(yystack.valueAt (5))), var, ((RTExpression)(yystack.valueAt (2))), ((RTStatement)(yystack.valueAt (0)))), (yyloc));
  };
  break;
    

  case 65:
  if (yyn == 65)
    /* "VondaGrammar.y":297  */
    {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (2)))), yystack.locationAt (2));
    ExpAssignment ass = setPos(new ExpAssignment(var, ((RTExpression)(yystack.valueAt (1)))), yystack.locationAt (2), yystack.locationAt (1));
    yyval = setPos(new StatVarDef(false, Type.getNoType(), ass), (yyloc));
  };
  break;
    

  case 66:
  if (yyn == 66)
    /* "VondaGrammar.y":302  */
    {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (2)))), yystack.locationAt (2));
    ExpAssignment ass = setPos(new ExpAssignment(var, ((RTExpression)(yystack.valueAt (1)))), yystack.locationAt (2), yystack.locationAt (1));
    yyval = setPos(new StatVarDef(false, ((Type)(yystack.valueAt (3))), ass), (yyloc));
  };
  break;
    

  case 67:
  if (yyn == 67)
    /* "VondaGrammar.y":307  */
    {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (2)))), yystack.locationAt (2));
    ExpAssignment ass = setPos(new ExpAssignment(var, ((RTExpression)(yystack.valueAt (1)))), yystack.locationAt (2), yystack.locationAt (1));
    yyval = setPos(new StatVarDef(true, Type.getNoType(), ass), (yyloc));
  };
  break;
    

  case 68:
  if (yyn == 68)
    /* "VondaGrammar.y":312  */
    {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (2)))), yystack.locationAt (2));
    ExpAssignment ass = setPos(new ExpAssignment(var, ((RTExpression)(yystack.valueAt (1)))), yystack.locationAt (2), yystack.locationAt (1));
    yyval = setPos(new StatVarDef(true, ((Type)(yystack.valueAt (3))), ass), (yyloc));
  };
  break;
    

  case 69:
  if (yyn == 69)
    /* "VondaGrammar.y":320  */
    { yyval = setPos(new StatPropose(((RTExpression)(yystack.valueAt (2))), ((StatAbstractBlock)(yystack.valueAt (0)))), (yyloc)); };
  break;
    

  case 70:
  if (yyn == 70)
    /* "VondaGrammar.y":324  */
    {
    yyval = setPos(new StatTimeout(((RTExpression)(yystack.valueAt (4))), ((RTExpression)(yystack.valueAt (2))), ((StatAbstractBlock)(yystack.valueAt (0)))), (yyloc));
  };
  break;
    

  case 71:
  if (yyn == 71)
    /* "VondaGrammar.y":330  */
    {
    yyval = setPos(new StatSwitch(((RTExpression)(yystack.valueAt (2))), ((StatAbstractBlock)(yystack.valueAt (0)))), (yyloc));
  };
  break;
    

  case 72:
  if (yyn == 72)
    /* "VondaGrammar.y":337  */
    {
    ExpLiteral val =
      new ExpLiteral("case \"" + (( ExpLiteral )(yystack.valueAt (1))).toString() + "\":", "label");
    RTStatement lbl = val.ensureStatement();
    setPos(val, (yyloc)); setPos(lbl, (yyloc));
    yyval = lbl;
  };
  break;
    

  case 73:
  if (yyn == 73)
    /* "VondaGrammar.y":344  */
    {
    ExpLiteral val =
      new ExpLiteral("case " + (( ExpLiteral )(yystack.valueAt (1))).toString() + ":", "label");
    RTStatement lbl = val.ensureStatement();
    setPos(val, (yyloc)); setPos(lbl, (yyloc));
    yyval = lbl;
  };
  break;
    

  case 74:
  if (yyn == 74)
    /* "VondaGrammar.y":351  */
    {
    ExpLiteral val =
      new ExpLiteral("case " + (( ExpLiteral )(yystack.valueAt (1))).toString() + ":", "label");
    RTStatement lbl = val.ensureStatement();
    setPos(val, (yyloc)); setPos(lbl, (yyloc));
    yyval = lbl;
  };
  break;
    

  case 75:
  if (yyn == 75)
    /* "VondaGrammar.y":358  */
    {
    ExpLiteral val =
      new ExpLiteral("case " + (( String )(yystack.valueAt (1))) + ":", "label");
    RTStatement lbl = val.ensureStatement();
    setPos(val, (yyloc)); setPos(lbl, (yyloc));
    yyval = lbl;
  };
  break;
    

  case 76:
  if (yyn == 76)
    /* "VondaGrammar.y":365  */
    {
    ExpLiteral val = new ExpLiteral("default:", "label");
    RTStatement lbl = val.ensureStatement();
    setPos(val, (yyloc)); setPos(lbl, (yyloc));
    yyval = lbl;
  };
  break;
    

  case 77:
  if (yyn == 77)
    /* "VondaGrammar.y":374  */
    {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (2)))), yystack.locationAt (2));
    ExpAssignment ass = setPos(new ExpAssignment(var, ((RTExpression)(yystack.valueAt (1)))), yystack.locationAt (2), yystack.locationAt (1));
    yyval = setPos(new StatVarDef(true, Type.getNoType(), ass), (yyloc));
  };
  break;
    

  case 78:
  if (yyn == 78)
    /* "VondaGrammar.y":379  */
    {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (2)))), yystack.locationAt (2));
    ExpAssignment ass = setPos(new ExpAssignment(var, ((RTExpression)(yystack.valueAt (1)))), yystack.locationAt (2), yystack.locationAt (1));
    yyval = setPos(new StatVarDef(false, ((Type)(yystack.valueAt (3))), ass), (yyloc));
  };
  break;
    

  case 79:
  if (yyn == 79)
    /* "VondaGrammar.y":384  */
    {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (2)))), yystack.locationAt (2));
    ExpAssignment ass = setPos(new ExpAssignment(var, ((RTExpression)(yystack.valueAt (1)))), yystack.locationAt (2), yystack.locationAt (1));
    yyval = setPos(new StatVarDef(true, ((Type)(yystack.valueAt (3))), ass), (yyloc));
    };
  break;
    

  case 80:
  if (yyn == 80)
    /* "VondaGrammar.y":389  */
    {
    yyval = setPos(new StatVarDef(true, Type.getNoType(), (( String )(yystack.valueAt (1)))), (yyloc));
  };
  break;
    

  case 81:
  if (yyn == 81)
    /* "VondaGrammar.y":392  */
    {
    yyval = setPos(new StatVarDef(false, ((Type)(yystack.valueAt (2))), (( String )(yystack.valueAt (1)))), (yyloc));
  };
  break;
    

  case 82:
  if (yyn == 82)
    /* "VondaGrammar.y":395  */
    {
    yyval = setPos(new StatVarDef(true, ((Type)(yystack.valueAt (2))), (( String )(yystack.valueAt (1)))), (yyloc));
  };
  break;
    

  case 83:
  if (yyn == 83)
    /* "VondaGrammar.y":401  */
    {
    yyval = setPos(new StatFieldDef(null,
           setPos(new StatVarDef(false, ((Type)(yystack.valueAt (2))), (( String )(yystack.valueAt (1)))), (yyloc)), ((Type)(yystack.valueAt (5)))), (yyloc));
  };
  break;
    

  case 84:
  if (yyn == 84)
    /* "VondaGrammar.y":408  */
    { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;
    

  case 85:
  if (yyn == 85)
    /* "VondaGrammar.y":409  */
    {
    yyval = setPos(new ExpListLiteral(new LinkedList<RTExpression>()), yystack.locationAt (1), yystack.locationAt (0));
  };
  break;
    

  case 86:
  if (yyn == 86)
    /* "VondaGrammar.y":412  */
    {
    yyval = setPos(new ExpListLiteral(((LinkedList<RTExpression>)(yystack.valueAt (1)))), yystack.locationAt (2), yystack.locationAt (0));
  };
  break;
    

  case 87:
  if (yyn == 87)
    /* "VondaGrammar.y":418  */
    { yyval = new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (0)))); }}; };
  break;
    

  case 88:
  if (yyn == 88)
    /* "VondaGrammar.y":419  */
    { yyval = ((LinkedList<RTExpression>)(yystack.valueAt (0))); ((LinkedList<RTExpression>)(yystack.valueAt (0))).addFirst(((RTExpression)(yystack.valueAt (2)))); };
  break;
    

  case 89:
  if (yyn == 89)
    /* "VondaGrammar.y":424  */
    {
    yyval = setPos(new StatMethodDeclaration("public", ((Type)(yystack.valueAt (5))), ((Type)(yystack.valueAt (8))), (( String )(yystack.valueAt (4))), ((LinkedList)(yystack.valueAt (2))), ((StatAbstractBlock)(yystack.valueAt (0)))), (yyloc));
  };
  break;
    

  case 90:
  if (yyn == 90)
    /* "VondaGrammar.y":427  */
    {
    yyval = setPos(new StatMethodDeclaration("public", ((Type)(yystack.valueAt (5))), null, (( String )(yystack.valueAt (4))), ((LinkedList)(yystack.valueAt (2))), ((StatAbstractBlock)(yystack.valueAt (0)))), (yyloc));
  };
  break;
    

  case 91:
  if (yyn == 91)
    /* "VondaGrammar.y":433  */
    { yyval = ((StatAbstractBlock)(yystack.valueAt (0))); };
  break;
    

  case 92:
  if (yyn == 92)
    /* "VondaGrammar.y":434  */
    { yyval = null; };
  break;
    

  case 93:
  if (yyn == 93)
    /* "VondaGrammar.y":438  */
    { yyval = ((LinkedList)(yystack.valueAt (0))); };
  break;
    

  case 94:
  if (yyn == 94)
    /* "VondaGrammar.y":439  */
    { yyval = new LinkedList(); };
  break;
    

  case 95:
  if (yyn == 95)
    /* "VondaGrammar.y":443  */
    { yyval = new LinkedList(){{ add(Type.getNoType()); add((( String )(yystack.valueAt (0)))); }}; };
  break;
    

  case 96:
  if (yyn == 96)
    /* "VondaGrammar.y":444  */
    { yyval = new LinkedList(){{ add(((Type)(yystack.valueAt (1)))); add((( String )(yystack.valueAt (0)))); }}; };
  break;
    

  case 97:
  if (yyn == 97)
    /* "VondaGrammar.y":445  */
    {
    yyval = ((LinkedList)(yystack.valueAt (0)));
    ((LinkedList)(yystack.valueAt (0))).addFirst((( String )(yystack.valueAt (2)))); ((LinkedList)(yystack.valueAt (0))).addFirst(Type.getNoType());
  };
  break;
    

  case 98:
  if (yyn == 98)
    /* "VondaGrammar.y":449  */
    {
    yyval = ((LinkedList)(yystack.valueAt (0))); ((LinkedList)(yystack.valueAt (0))).addFirst((( String )(yystack.valueAt (2)))); ((LinkedList)(yystack.valueAt (0))).addFirst(((Type)(yystack.valueAt (3))));
  };
  break;
    

  case 99:
  if (yyn == 99)
    /* "VondaGrammar.y":457  */
    {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (3)))), yystack.locationAt (3));
    yyval = setPos(new StatSetOperation(var, true, ((RTExpression)(yystack.valueAt (1)))), (yyloc));
  };
  break;
    

  case 100:
  if (yyn == 100)
    /* "VondaGrammar.y":461  */
    {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (3)))), yystack.locationAt (3));
    yyval = setPos(new StatSetOperation(var, false, ((RTExpression)(yystack.valueAt (1)))), (yyloc));
  };
  break;
    

  case 101:
  if (yyn == 101)
    /* "VondaGrammar.y":465  */
    {
    yyval = setPos(new StatSetOperation(((RTExpression)(yystack.valueAt (3))), true, ((RTExpression)(yystack.valueAt (1)))), (yyloc));
  };
  break;
    

  case 102:
  if (yyn == 102)
    /* "VondaGrammar.y":468  */
    {
    yyval = setPos(new StatSetOperation(((RTExpression)(yystack.valueAt (3))), false, ((RTExpression)(yystack.valueAt (1)))), (yyloc));
  };
  break;
    

  case 103:
  if (yyn == 103)
    /* "VondaGrammar.y":471  */
    {
    yyval = setPos(new StatSetOperation(((RTExpression)(yystack.valueAt (3))), true, ((RTExpression)(yystack.valueAt (1)))), (yyloc));
  };
  break;
    

  case 104:
  if (yyn == 104)
    /* "VondaGrammar.y":474  */
    {
    yyval = setPos(new StatSetOperation(((RTExpression)(yystack.valueAt (3))), false, ((RTExpression)(yystack.valueAt (1)))), (yyloc));
  };
  break;
    

  case 105:
  if (yyn == 105)
    /* "VondaGrammar.y":483  */
    {
    yyval = setPos(new ExpFuncCall((( String )(yystack.valueAt (2))), new LinkedList<RTExpression>(), false), (yyloc));
  };
  break;
    

  case 106:
  if (yyn == 106)
    /* "VondaGrammar.y":486  */
    {
    yyval = setPos(new ExpFuncCall((( String )(yystack.valueAt (3))), ((LinkedList<RTExpression>)(yystack.valueAt (1))), false), (yyloc));
  };
  break;
    

  case 107:
  if (yyn == 107)
    /* "VondaGrammar.y":492  */
    { yyval = new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (0)))); }}; };
  break;
    

  case 108:
  if (yyn == 108)
    /* "VondaGrammar.y":493  */
    { yyval = new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (0)))); }}; };
  break;
    

  case 109:
  if (yyn == 109)
    /* "VondaGrammar.y":494  */
    { yyval = ((LinkedList<RTExpression>)(yystack.valueAt (0))); ((LinkedList<RTExpression>)(yystack.valueAt (0))).addFirst(((RTExpression)(yystack.valueAt (2)))); };
  break;
    

  case 110:
  if (yyn == 110)
    /* "VondaGrammar.y":495  */
    { yyval = ((LinkedList<RTExpression>)(yystack.valueAt (0))); ((LinkedList<RTExpression>)(yystack.valueAt (0))).addFirst(((RTExpression)(yystack.valueAt (2)))); };
  break;
    

  case 111:
  if (yyn == 111)
    /* "VondaGrammar.y":499  */
    {
    yyval = new Type("Array",
                  new ArrayList<Type>(){{ add(new Type((( String )(yystack.valueAt (2))))); }});
  };
  break;
    

  case 112:
  if (yyn == 112)
    /* "VondaGrammar.y":503  */
    { yyval = new Type((( String )(yystack.valueAt (0)))); };
  break;
    

  case 113:
  if (yyn == 113)
    /* "VondaGrammar.y":504  */
    { yyval = new Type((( String )(yystack.valueAt (3))), ((LinkedList<Type>)(yystack.valueAt (1)))); };
  break;
    

  case 114:
  if (yyn == 114)
    /* "VondaGrammar.y":508  */
    { yyval = new LinkedList<Type>(){{ add(((Type)(yystack.valueAt (0)))); }}; };
  break;
    

  case 115:
  if (yyn == 115)
    /* "VondaGrammar.y":509  */
    { yyval = ((LinkedList<Type>)(yystack.valueAt (0))); ((LinkedList<Type>)(yystack.valueAt (0))).addFirst(((Type)(yystack.valueAt (2)))); };
  break;
    

  case 116:
  if (yyn == 116)
    /* "VondaGrammar.y":513  */
    {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "||"), (yyloc));
  };
  break;
    

  case 117:
  if (yyn == 117)
    /* "VondaGrammar.y":516  */
    { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;
    

  case 118:
  if (yyn == 118)
    /* "VondaGrammar.y":520  */
    {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "&&"), (yyloc));
  };
  break;
    

  case 119:
  if (yyn == 119)
    /* "VondaGrammar.y":523  */
    { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;
    

  case 120:
  if (yyn == 120)
    /* "VondaGrammar.y":527  */
    { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;
    

  case 121:
  if (yyn == 121)
    /* "VondaGrammar.y":528  */
    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "|"), (yyloc));
  };
  break;
    

  case 122:
  if (yyn == 122)
    /* "VondaGrammar.y":534  */
    { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;
    

  case 123:
  if (yyn == 123)
    /* "VondaGrammar.y":535  */
    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "^"), (yyloc));
  };
  break;
    

  case 124:
  if (yyn == 124)
    /* "VondaGrammar.y":541  */
    { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;
    

  case 125:
  if (yyn == 125)
    /* "VondaGrammar.y":542  */
    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "&"), (yyloc));
  };
  break;
    

  case 126:
  if (yyn == 126)
    /* "VondaGrammar.y":548  */
    { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;
    

  case 127:
  if (yyn == 127)
    /* "VondaGrammar.y":549  */
    {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "=="), (yyloc));
  };
  break;
    

  case 128:
  if (yyn == 128)
    /* "VondaGrammar.y":552  */
    {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "!="), (yyloc));
  };
  break;
    

  case 129:
  if (yyn == 129)
    /* "VondaGrammar.y":558  */
    { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;
    

  case 130:
  if (yyn == 130)
    /* "VondaGrammar.y":559  */
    {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "<"), (yyloc));
  };
  break;
    

  case 131:
  if (yyn == 131)
    /* "VondaGrammar.y":562  */
    {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), ">"), (yyloc));
  };
  break;
    

  case 132:
  if (yyn == 132)
    /* "VondaGrammar.y":565  */
    {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), ">="), (yyloc));
  };
  break;
    

  case 133:
  if (yyn == 133)
    /* "VondaGrammar.y":568  */
    {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "<="), (yyloc));
  };
  break;
    

  case 134:
  if (yyn == 134)
    /* "VondaGrammar.y":574  */
    { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;
    

  case 135:
  if (yyn == 135)
    /* "VondaGrammar.y":575  */
    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "+"), (yyloc));
  };
  break;
    

  case 136:
  if (yyn == 136)
    /* "VondaGrammar.y":578  */
    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "-"), (yyloc));
  };
  break;
    

  case 137:
  if (yyn == 137)
    /* "VondaGrammar.y":584  */
    { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;
    

  case 138:
  if (yyn == 138)
    /* "VondaGrammar.y":585  */
    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "*"), (yyloc));
  };
  break;
    

  case 139:
  if (yyn == 139)
    /* "VondaGrammar.y":588  */
    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "/"), (yyloc));
  };
  break;
    

  case 140:
  if (yyn == 140)
    /* "VondaGrammar.y":591  */
    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "%"), (yyloc));
  };
  break;
    

  case 141:
  if (yyn == 141)
    /* "VondaGrammar.y":597  */
    { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;
    

  case 142:
  if (yyn == 142)
    /* "VondaGrammar.y":598  */
    { yyval = setPos(new ExpCast(((Type)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0)))), (yyloc)); };
  break;
    

  case 143:
  if (yyn == 143)
    /* "VondaGrammar.y":602  */
    {
    yyval = createPlusMinus(((RTExpression)(yystack.valueAt (0))), "++", (yyloc));
  };
  break;
    

  case 144:
  if (yyn == 144)
    /* "VondaGrammar.y":605  */
    {
    yyval = createPlusMinus(((RTExpression)(yystack.valueAt (0))), "--", (yyloc));
  };
  break;
    

  case 145:
  if (yyn == 145)
    /* "VondaGrammar.y":608  */
    { yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (0))), null, "+"), (yyloc)); };
  break;
    

  case 146:
  if (yyn == 146)
    /* "VondaGrammar.y":609  */
    { yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (0))), null, "-"), (yyloc)); };
  break;
    

  case 147:
  if (yyn == 147)
    /* "VondaGrammar.y":610  */
    { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;
    

  case 148:
  if (yyn == 148)
    /* "VondaGrammar.y":614  */
    { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;
    

  case 149:
  if (yyn == 149)
    /* "VondaGrammar.y":615  */
    { yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (0))), null, "!"), (yyloc)); };
  break;
    

  case 150:
  if (yyn == 150)
    /* "VondaGrammar.y":616  */
    { yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (0))), null, "~"), (yyloc)); };
  break;
    

  case 151:
  if (yyn == 151)
    /* "VondaGrammar.y":620  */
    { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;
    

  case 152:
  if (yyn == 152)
    /* "VondaGrammar.y":621  */
    {
    yyval = createPlusMinus(((RTExpression)(yystack.valueAt (1))), "+++", (yyloc));
  };
  break;
    

  case 153:
  if (yyn == 153)
    /* "VondaGrammar.y":624  */
    {
    yyval = createPlusMinus(((RTExpression)(yystack.valueAt (1))), "---", (yyloc));
  };
  break;
    

  case 154:
  if (yyn == 154)
    /* "VondaGrammar.y":630  */
    { yyval = setPos(new ExpLiteral("null", "null"), (yyloc)); };
  break;
    

  case 155:
  if (yyn == 155)
    /* "VondaGrammar.y":631  */
    { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;
    

  case 156:
  if (yyn == 156)
    /* "VondaGrammar.y":632  */
    { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;
    

  case 157:
  if (yyn == 157)
    /* "VondaGrammar.y":636  */
    { yyval = setPos(new ExpIdentifier((( String )(yystack.valueAt (0)))), (yyloc)); };
  break;
    

  case 158:
  if (yyn == 158)
    /* "VondaGrammar.y":637  */
    { yyval = setPos(new ExpCast(((Type)(yystack.valueAt (3))), ((RTExpression)(yystack.valueAt (1)))), (yyloc)); };
  break;
    

  case 159:
  if (yyn == 159)
    /* "VondaGrammar.y":641  */
    { yyval = ((RTExpression)(yystack.valueAt (1))); ((RTExpression)(yystack.valueAt (1))).generateParens(); };
  break;
    

  case 160:
  if (yyn == 160)
    /* "VondaGrammar.y":642  */
    { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;
    

  case 161:
  if (yyn == 161)
    /* "VondaGrammar.y":646  */
    { yyval = ((ExpLiteral)(yystack.valueAt (0))); };
  break;
    

  case 162:
  if (yyn == 162)
    /* "VondaGrammar.y":647  */
    { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;
    

  case 163:
  if (yyn == 163)
    /* "VondaGrammar.y":648  */
    { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;
    

  case 164:
  if (yyn == 164)
    /* "VondaGrammar.y":649  */
    { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;
    

  case 165:
  if (yyn == 165)
    /* "VondaGrammar.y":650  */
    { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;
    

  case 166:
  if (yyn == 166)
    /* "VondaGrammar.y":654  */
    { yyval = setPos((( ExpLiteral )(yystack.valueAt (0))), (yyloc)); };
  break;
    

  case 167:
  if (yyn == 167)
    /* "VondaGrammar.y":655  */
    { yyval = setPos((( ExpLiteral )(yystack.valueAt (0))), (yyloc)); };
  break;
    

  case 168:
  if (yyn == 168)
    /* "VondaGrammar.y":656  */
    { yyval = setPos((( ExpLiteral )(yystack.valueAt (0))), (yyloc)); };
  break;
    

  case 169:
  if (yyn == 169)
    /* "VondaGrammar.y":657  */
    { yyval = setPos((( ExpLiteral )(yystack.valueAt (0))), (yyloc)); };
  break;
    

  case 170:
  if (yyn == 170)
    /* "VondaGrammar.y":661  */
    {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (3)))), yystack.locationAt (3));
    yyval = setPos(new ExpArrayAccess(var, ((RTExpression)(yystack.valueAt (1)))), (yyloc));
  };
  break;
    

  case 171:
  if (yyn == 171)
    /* "VondaGrammar.y":665  */
    { yyval = setPos(new ExpArrayAccess(((RTExpression)(yystack.valueAt (3))), ((RTExpression)(yystack.valueAt (1)))), (yyloc)); };
  break;
    

  case 172:
  if (yyn == 172)
    /* "VondaGrammar.y":669  */
    { yyval = setPos(new ExpConditional(((RTExpression)(yystack.valueAt (4))), ((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0)))), (yyloc)); };
  break;
    

  case 173:
  if (yyn == 173)
    /* "VondaGrammar.y":670  */
    { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;
    

  case 174:
  if (yyn == 174)
    /* "VondaGrammar.y":676  */
    { yyval = setPos(new ExpAssignment(((RTExpression)(yystack.valueAt (1))), ((RTExpression)(yystack.valueAt (0)))), (yyloc)); };
  break;
    

  case 175:
  if (yyn == 175)
    /* "VondaGrammar.y":677  */
    { yyval = setPos(new ExpAssignment(((RTExpression)(yystack.valueAt (1))), ((RTExpression)(yystack.valueAt (0)))), (yyloc)); };
  break;
    

  case 176:
  if (yyn == 176)
    /* "VondaGrammar.y":678  */
    {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (1)))), yystack.locationAt (1));
    ExpAssignment ass = setPos(new ExpAssignment(var, ((RTExpression)(yystack.valueAt (0)))), (yyloc));
    yyval = ass;
  };
  break;
    

  case 177:
  if (yyn == 177)
    /* "VondaGrammar.y":686  */
    {
    yyval = setPos(new ExpFieldAccess(((LinkedList<RTExpression>)(yystack.valueAt (0)))), (yyloc)); ((LinkedList<RTExpression>)(yystack.valueAt (0))).addFirst(((RTExpression)(yystack.valueAt (1))));
  };
  break;
    

  case 178:
  if (yyn == 178)
    /* "VondaGrammar.y":689  */
    {
    yyval = setPos(new ExpFieldAccess(((LinkedList<RTExpression>)(yystack.valueAt (0)))), (yyloc)); ((LinkedList<RTExpression>)(yystack.valueAt (0))).addFirst(setPos((( ExpLiteral )(yystack.valueAt (1))), (yyloc)));
  };
  break;
    

  case 179:
  if (yyn == 179)
    /* "VondaGrammar.y":692  */
    { yyval = setPos(new ExpFieldAccess(((LinkedList<RTExpression>)(yystack.valueAt (0)))), (yyloc)); ((LinkedList<RTExpression>)(yystack.valueAt (0))).addFirst(((RTExpression)(yystack.valueAt (1)))); };
  break;
    

  case 180:
  if (yyn == 180)
    /* "VondaGrammar.y":696  */
    { yyval = ((LinkedList<RTExpression>)(yystack.valueAt (0))); ((LinkedList<RTExpression>)(yystack.valueAt (0))).addFirst(((RTExpression)(yystack.valueAt (1)))); };
  break;
    

  case 181:
  if (yyn == 181)
    /* "VondaGrammar.y":697  */
    { yyval = new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (0)))); }}; };
  break;
    

  case 182:
  if (yyn == 182)
    /* "VondaGrammar.y":701  */
    { yyval = setPos(new ExpIdentifier((( String )(yystack.valueAt (0)))), (yyloc)); };
  break;
    

  case 183:
  if (yyn == 183)
    /* "VondaGrammar.y":702  */
    { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;
    

  case 184:
  if (yyn == 184)
    /* "VondaGrammar.y":703  */
    { yyval = ((RTExpression)(yystack.valueAt (1))); };
  break;
    

  case 185:
  if (yyn == 185)
    /* "VondaGrammar.y":707  */
    { yyval = setPos(new ExpNew(new Type((( String )(yystack.valueAt (0))))), (yyloc)); };
  break;
    

  case 186:
  if (yyn == 186)
    /* "VondaGrammar.y":708  */
    {
    yyval = setPos(new ExpNew(new Type((( String )(yystack.valueAt (2)))), new LinkedList<>()), (yyloc));
  };
  break;
    

  case 187:
  if (yyn == 187)
    /* "VondaGrammar.y":711  */
    {
    yyval = setPos(new ExpNew(new Type((( String )(yystack.valueAt (3)))), ((LinkedList<RTExpression>)(yystack.valueAt (1)))), (yyloc));
  };
  break;
    

  case 188:
  if (yyn == 188)
    /* "VondaGrammar.y":714  */
    {
    List<Type> sub = new ArrayList<Type>() {{ add(new Type((( String )(yystack.valueAt (3))))); }};
    yyval = setPos(new ExpNew(new Type("Array", sub),
                    new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (1)))); }}), (yyloc));
  };
  break;
    

  case 189:
  if (yyn == 189)
    /* "VondaGrammar.y":719  */
    {
    List<Type> sub = new ArrayList<Type>() {{ add(new Type((( String )(yystack.valueAt (5))))); }};
    yyval = setPos(new ExpNew(new Type("Array", sub),
                    new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (1)))); }}), (yyloc));
  };
  break;
    

  case 190:
  if (yyn == 190)
    /* "VondaGrammar.y":724  */
    {
    yyval = setPos(new ExpNew(new Type((( String )(yystack.valueAt (5))), ((LinkedList<Type>)(yystack.valueAt (3)))),
                    new LinkedList<>()), (yyloc));
  };
  break;
    

  case 191:
  if (yyn == 191)
    /* "VondaGrammar.y":728  */
    {
    yyval = setPos(new ExpNew(new Type((( String )(yystack.valueAt (6))), ((LinkedList<Type>)(yystack.valueAt (4)))),
                    ((LinkedList<RTExpression>)(yystack.valueAt (1)))), (yyloc));
  };
  break;
    

  case 192:
  if (yyn == 192)
    /* "VondaGrammar.y":735  */
    {
    yyval = setPos(new ExpLambda(((LinkedList)(yystack.valueAt (3))), ((RTExpression)(yystack.valueAt (0)))), (yyloc));
  };
  break;
    

  case 193:
  if (yyn == 193)
    /* "VondaGrammar.y":738  */
    {
    yyval = setPos(new ExpLambda(((LinkedList)(yystack.valueAt (3))), ((StatAbstractBlock)(yystack.valueAt (0)))), (yyloc));
  };
  break;
    

  case 194:
  if (yyn == 194)
    /* "VondaGrammar.y":745  */
    {
    yyval = setPos(new ExpDialogueAct(((RTExpression)(yystack.valueAt (4))), ((RTExpression)(yystack.valueAt (2))), ((LinkedList<RTExpression>)(yystack.valueAt (1)))), (yyloc));
  };
  break;
    

  case 195:
  if (yyn == 195)
    /* "VondaGrammar.y":751  */
    { yyval = ((RTExpression)(yystack.valueAt (1))); };
  break;
    

  case 196:
  if (yyn == 196)
    /* "VondaGrammar.y":754  */
    { yyval = setPos(new ExpLiteral((( String )(yystack.valueAt (0))), "String"), (yyloc)); };
  break;
    

  case 197:
  if (yyn == 197)
    /* "VondaGrammar.y":755  */
    { yyval = setPos((( ExpLiteral )(yystack.valueAt (0))), (yyloc)); };
  break;
    

  case 198:
  if (yyn == 198)
    /* "VondaGrammar.y":756  */
    { yyval = setPos(new ExpLiteral((( String )(yystack.valueAt (0))), "String"), (yyloc)); };
  break;
    

  case 199:
  if (yyn == 199)
    /* "VondaGrammar.y":760  */
    {
    yyval = ((LinkedList<RTExpression>)(yystack.valueAt (0))); ((LinkedList<RTExpression>)(yystack.valueAt (0))).addFirst(((RTExpression)(yystack.valueAt (1)))); ((LinkedList<RTExpression>)(yystack.valueAt (0))).addFirst(((RTExpression)(yystack.valueAt (3))));
  };
  break;
    

  case 200:
  if (yyn == 200)
    /* "VondaGrammar.y":763  */
    { yyval = new LinkedList<RTExpression>(); };
  break;
    

  case 201:
  if (yyn == 201)
    /* "VondaGrammar.y":767  */
    { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;
    

  case 202:
  if (yyn == 202)
    /* "VondaGrammar.y":768  */
    { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;
    

  case 203:
  if (yyn == 203)
    /* "VondaGrammar.y":769  */
    { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;
    


/* "VondaGrammar.java":2148  */

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

  private static final short yypact_ninf_ = -367;
  private static final short yytable_ninf_ = -158;

  /* YYPACT[STATE-NUM] -- Index in YYTABLE of the portion describing
   STATE-NUM.  */
  private static final short yypact_[] = yypact_init();
  private static final short[] yypact_init()
  {
    return new short[]
    {
     899,     8,   -11,    56,   198,    88,   102,   994,    94,    63,
     107,   120,  -367,   131,  -367,  -367,   302,   168,   170,   172,
     126,   140,   166,  -367,  -367,   541,  -367,   948,  1276,   184,
     162,   230,     4,   899,   899,  -367,  -367,  -367,  -367,  -367,
    -367,  -367,  -367,  -367,  -367,   899,   899,   899,  -367,   849,
     193,   166,   191,  -367,  -367,     7,   201,    32,  -367,   237,
    -367,  -367,  -367,   241,   252,   254,   257,  -367,  -367,  -367,
     259,  -367,   148,   264,   -15,   266,    13,  1309,  -367,   161,
    1309,   267,  -367,   473,   473,   179,  -367,  1342,  1441,  1441,
     473,   473,   166,   -14,   285,   258,   260,   265,   211,    78,
     206,   216,  -367,  -367,  -367,   251,  -367,   166,   191,   262,
    -367,  -367,   262,  -367,   273,  1309,  1309,  1309,   166,   130,
     274,   166,   281,   137,   283,    31,  -367,  1309,  1309,   284,
     286,   314,   669,   709,   762,   184,  -367,  -367,  -367,   994,
     287,  1342,   291,   108,   278,  -367,  -367,  -367,  1309,   288,
    -367,   184,   899,   899,  -367,  -367,  -367,  -367,  -367,  -367,
    -367,  -367,    64,  -367,  1309,  1309,  1309,  -367,  -367,  1309,
    1309,   303,   306,  -367,  -367,  -367,  -367,  -367,  -367,  -367,
     308,    20,  -367,   307,   315,    70,   322,    27,  1309,  1008,
     323,   319,  -367,   330,   324,    46,   158,  -367,  -367,  -367,
    -367,  1309,   175,   327,  -367,  -367,  -367,  -367,  1441,  1309,
    1441,  1441,  1441,  1441,  1441,  1441,  1441,  1441,  1441,  1441,
    1441,  1441,  1441,  1441,  1441,  -367,  -367,  -367,   328,   329,
     331,  -367,   184,  -367,  -367,  -367,   333,  1309,  -367,   166,
     335,   339,  -367,  -367,  -367,  1375,  -367,   336,   334,   337,
    -367,   338,  1041,  -367,   343,   340,  -367,  -367,   345,  -367,
     352,   351,   162,   349,  -367,  -367,  -367,   361,   362,   354,
     370,   372,   375,   376,  -367,  -367,  1309,  -367,  -367,   377,
     -36,   363,  1309,   378,   379,  1074,   381,    87,   994,  -367,
     383,  1108,  1142,   184,  1441,   285,   360,   258,   260,   265,
     211,    78,    78,   206,   206,   206,   206,   216,   216,  -367,
    -367,  -367,   383,  1309,   994,   380,   384,  -367,  -367,  -367,
      97,   388,  -367,   -20,  -367,  1408,  1408,  -367,  -367,   386,
     391,   184,  -367,  1441,   184,  -367,   392,   382,   115,   389,
     402,  -367,  -367,  -367,  -367,  -367,  -367,   396,  -367,   403,
     262,   398,  -367,  1309,   994,   399,  1175,  1309,   406,   439,
    -367,  -367,   404,   408,   400,   409,  -367,  1309,  -367,   405,
    -367,   473,  -367,   361,   432,   415,  -367,  -367,  -367,  1309,
    -367,   419,   430,   162,   424,   184,    96,  -367,  -367,   451,
     994,   446,  -367,   994,   994,   447,   448,  -367,   994,  -367,
    1309,  -367,   450,  -367,   383,   419,  -367,  1209,   361,  -367,
    -367,   150,   449,  -367,   458,  -367,  -367,  -367,  -367,  -367,
     994,  -367,  -367,   994,   994,  -367,   452,  1242,  -367,  -367,
    -367,  -367,  -367,   361,   162,   455,  -367,  -367,  -367,  -367,
    -367,   456,   457,   392,  -367,    96,  -367,  -367
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
       0,     0,   166,   167,   169,   112,   168,     0,     0,     0,
       0,     0,     0,    10,    10,    17,    29,    31,    34,    35,
      36,    32,    33,    37,    38,    10,    10,    10,    30,    10,
       0,     0,     0,   160,   161,   162,     0,   163,   165,     0,
      49,    51,    52,     0,     0,     0,     0,    53,    76,    39,
       0,    40,   164,     0,   112,     0,     0,     0,    15,     0,
       0,     0,   154,     0,     0,   157,    47,     0,     0,     0,
       0,     0,   164,   173,   117,   119,   120,   122,   124,   126,
     129,   134,   137,   141,   147,   148,   151,   155,   156,   162,
     201,   202,   163,   203,     0,     0,     0,     0,     0,   157,
       0,     0,     0,   157,     0,     0,   178,     0,     0,     0,
       0,     0,     0,     0,     0,     0,   176,    43,    41,    44,
       0,     0,     0,   112,     0,   197,   198,   196,     0,     0,
       1,     0,    10,    10,     6,     5,     8,     9,     3,    28,
       4,   179,     0,   177,     0,     0,     0,   175,    18,     0,
       0,     0,     0,    19,   174,    50,    72,    73,    74,    75,
       0,     0,    80,     0,     0,     0,     0,   112,     0,     0,
       0,     0,    14,     0,     0,   185,   157,   144,   162,   163,
     143,     0,   157,     0,   145,   146,   149,   150,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,   153,   152,    48,     0,     0,
       0,    21,     0,    25,    20,    24,   182,     0,   183,   181,
       0,     0,    23,    22,    46,    94,   105,     0,   108,   107,
     111,     0,     0,    84,   114,     0,    45,    42,     0,   159,
       0,     0,     0,     0,     7,     2,    81,    94,     0,     0,
       0,     0,     0,     0,    27,    26,     0,    77,    82,     0,
     112,     0,     0,     0,     0,     0,     0,     0,     0,    16,
       0,     0,     0,     0,     0,   116,     0,   118,   121,   123,
     125,   127,   128,   132,   133,   130,   131,   135,   136,   138,
     139,   140,     0,     0,     0,     0,     0,   180,   100,    99,
     157,     0,    93,     0,   106,     0,     0,   170,    85,     0,
      87,     0,   113,     0,     0,   195,   200,     0,    95,     0,
       0,    78,   171,   102,   101,   104,   103,     0,    79,     0,
       0,     0,    65,     0,     0,     0,     0,     0,     0,    54,
      69,   186,     0,     0,     0,     0,   142,     0,    71,     0,
      56,     0,   184,     0,     0,    96,   110,   109,    86,     0,
     115,   141,     0,     0,     0,     0,     0,    57,    67,     0,
       0,     0,    61,     0,     0,     0,     0,    66,     0,   187,
       0,   188,     0,   172,     0,     0,    97,     0,     0,    88,
     158,     0,     0,   194,     0,    92,    91,    90,    68,    63,
       0,    59,    60,     0,     0,    55,     0,     0,    70,   193,
     192,    98,    83,    94,     0,     0,    62,    58,    64,   189,
     190,     0,     0,   200,   191,     0,   199,    89
    };
  }

/* YYPGOTO[NTERM-NUM].  */
  private static final short yypgoto_[] = yypgoto_init();
  private static final short[] yypgoto_init()
  {
    return new short[]
    {
    -367,   -24,  -367,  -367,  -367,   167,    45,  -367,  -286,   367,
    -367,  -367,   368,  -367,  -367,  -367,  -367,  -367,  -367,  -367,
      58,  -367,   489,  -285,   478,    74,  -262,  -366,  -367,    68,
     -34,     0,  -290,  -367,   313,   312,   316,   321,   311,    80,
      55,    81,   -28,   -72,  -367,  -367,  -367,   136,   235,  -367,
    -367,   263,  -367,   364,    36,   -21,  -367,  -367,  -367,  -367,
    -260,    82,   438
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
      71,    46,   136,   329,    47,   417,   321,   322,    48,    92,
     247,    73,   255,    93,    94,    95,    96,    97,    98,    99,
     100,   101,   102,   103,   104,   105,   106,   107,   108,    53,
      54,   109,   110,   111,   112,   161,   239,   113,   248,    58,
     149,   384,   142
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
      50,   126,   336,   365,   360,   339,   362,   406,    75,   154,
     155,   197,   200,   208,   183,     8,   134,   135,   206,   207,
     375,   156,   157,   158,   186,   160,   368,   182,   294,   144,
     163,    61,    50,    50,    50,   183,    57,   134,   135,   165,
     166,   380,   431,    57,   143,    50,    50,    50,    59,    50,
      60,   209,    70,   187,   151,   188,   122,   124,    45,   134,
     204,   205,   266,    57,   169,   170,   171,   172,    49,    57,
      57,   236,   134,   282,   173,    72,   190,   183,   237,   134,
     135,    57,    57,    57,   134,    57,   163,   203,   121,   121,
     152,    45,    45,   291,   409,    72,   292,   126,    62,   293,
     416,    49,    49,    45,    45,    45,   266,    45,   216,   217,
      76,   267,   278,    49,    49,    49,   134,    49,   428,   199,
     199,   429,   134,   412,   199,   199,   199,   199,   264,   265,
      67,   218,   219,   357,    74,   254,    51,  -112,   415,   134,
      27,   258,   441,    51,   132,   -95,   373,   133,    68,   134,
     135,   263,    50,    50,    77,  -112,    51,    51,   183,   416,
      78,   135,   118,    51,   373,   183,   119,    34,   135,    51,
      51,   442,   231,   120,   443,    57,   118,   132,    80,   234,
     123,    51,    51,    51,   132,    51,   281,   120,    57,    57,
     159,   125,   432,   238,   309,   310,   311,   433,   145,   146,
      34,    34,   147,   192,   193,   132,   148,    72,   201,   125,
      45,    45,    34,    34,    34,   115,    34,   116,   317,   117,
      49,    49,   132,  -112,   143,   133,   132,   134,   135,   201,
     150,   134,   315,   162,    63,    52,    64,    65,    66,   214,
     215,   164,    52,   168,   199,   323,   199,   199,   199,   199,
     199,   199,   199,   199,   199,   199,   199,   199,   199,   199,
     199,   381,    52,    55,   220,   221,   366,   340,    52,    52,
      55,   303,   304,   305,   306,    51,   222,   223,   224,   175,
      52,    52,    52,   180,    52,   225,   226,   176,    51,    51,
      55,   376,   377,   254,   301,   302,    55,    55,   177,   405,
     178,   307,   308,   179,   181,   366,   185,   195,    55,    55,
      55,   210,    55,   211,   134,   227,   212,    81,    82,    34,
      34,   232,   213,   233,    57,   235,   242,    10,   243,   260,
     199,   254,   257,   359,   382,   262,    83,    84,    22,   259,
      23,    24,    85,    26,    86,   274,   198,   198,   275,    87,
      57,   198,   198,   198,   198,   276,    72,   277,   250,   370,
      88,    89,   280,   287,    56,    90,    91,   288,    30,   199,
     289,    56,   290,   340,    52,   294,   312,   318,   313,   314,
     132,   319,    72,   325,   324,   414,   326,    52,    52,   327,
      57,    56,   331,   333,   332,   334,   335,    56,    56,   392,
     337,   338,    55,   350,   341,   342,   367,   199,   340,    56,
      56,    56,   343,    56,   344,    55,    55,   345,   346,   348,
     352,   353,    72,   356,    51,   385,    57,    27,   371,    57,
      57,   378,   372,   340,    57,   419,   374,   386,   421,   422,
     379,   383,   375,   425,   387,   388,   390,   393,   397,   398,
      51,   401,   399,   404,   114,   400,    57,   407,    72,    57,
      57,    72,    72,   402,   408,   436,    72,   410,   437,   438,
     411,   198,   413,   198,   198,   198,   198,   198,   198,   198,
     198,   198,   198,   198,   198,   198,   198,   198,    72,    82,
      51,    72,    72,   418,   420,   423,   424,   427,   435,   244,
     439,   434,   433,    56,   444,   445,   256,    83,    84,    22,
     153,    23,    24,   196,    26,   191,    56,    56,   194,   447,
      28,   295,   297,    52,   300,   446,    51,   298,     0,    51,
      51,    88,    89,   299,    51,     0,    90,    91,     0,    30,
       0,     0,     0,     0,   167,     0,   174,     0,     0,    52,
       0,    55,     0,   228,   229,   230,    51,   198,     0,    51,
      51,     0,     0,   184,     0,   240,   241,     0,     0,     0,
     249,   251,   253,   127,   128,   129,   130,    55,     0,     0,
       0,     0,     0,     0,  -157,     0,   261,   131,   132,    52,
       0,   133,     0,   134,   135,     0,   198,     0,   167,     0,
       0,   174,   269,   270,   271,     0,     0,   272,   273,     0,
       0,     0,     0,     0,     0,     0,     0,    55,     0,     0,
       0,     0,     0,     0,     0,    52,   284,   286,    52,    52,
       0,     0,     0,    52,   198,     0,     0,     0,     0,   251,
       0,     0,     0,     0,     0,     0,     0,   296,     0,     0,
       0,   268,    56,    55,     0,    52,    55,    55,    52,    52,
       0,    55,     0,     0,     0,     0,     0,     0,     0,     0,
     268,     0,     0,     0,   279,   316,   283,     0,    56,     0,
       0,     0,     0,    55,    81,    82,    55,    55,     0,     0,
     330,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,    83,    84,    22,     0,    23,    24,    85,
      26,     0,     0,     0,   347,     0,   245,   246,    56,     0,
     351,     0,     0,   355,    81,    82,     0,    88,    89,   330,
     364,     0,    90,    91,     0,    30,     0,     0,     0,     0,
       0,     0,     0,    83,    84,    22,     0,    23,    24,    85,
      26,   369,     0,     0,    56,     0,    87,    56,    56,     0,
     250,     0,    56,   249,   249,     0,     0,    88,    89,   349,
       0,     0,    90,    91,     0,    30,   358,    81,    82,     0,
       0,     0,     0,     0,    56,     0,     0,    56,    56,     0,
       0,   391,     0,     0,   395,   396,    83,    84,    22,     0,
      23,    24,    85,    26,     0,   403,   252,     0,     0,    87,
       0,     0,     0,     0,     0,     0,     0,   330,     0,     0,
      88,    89,     0,     0,     0,    90,    91,     0,    30,     0,
       0,     0,     0,     0,     0,     0,     0,     0,   426,   389,
       0,     0,     0,     0,     0,   430,     0,     0,     0,     0,
       0,     0,     1,     2,     3,     4,     5,     6,     7,     0,
       8,     9,    10,    11,     0,   330,    12,    13,    14,    15,
      16,    17,    18,    19,     0,     0,     0,     0,     0,     0,
       0,     0,     0,    20,    21,    22,     0,    23,    24,    25,
      26,   159,   125,    27,     0,     0,    28,     0,     0,    29,
       0,     0,     1,     2,     3,     4,     5,     6,     7,     0,
       8,     9,    10,    11,     0,    30,    12,    13,    14,    15,
      16,    17,    18,    19,     0,     0,     0,     0,     0,     0,
       0,     0,     0,    20,    21,    22,     0,    23,    24,    25,
      26,     0,     0,    27,     0,     0,    28,     0,     0,    29,
       0,     1,     2,     3,     4,     5,     6,     7,     0,     8,
       9,    10,     0,     0,     0,    30,    13,     0,     0,    16,
      17,    18,    19,     0,     0,     0,     0,     0,     0,     0,
       0,     0,    20,    21,    22,     0,    23,    24,    25,    26,
       0,     0,    27,   137,     0,    28,     0,     1,     2,     3,
       4,     5,     6,     7,     0,     8,     9,    10,     0,     0,
       0,     0,    13,     0,    30,    16,    17,    18,    19,     0,
       0,     0,     0,    81,    82,     0,     0,     0,    20,    21,
      22,     0,    23,    24,    25,    26,     0,     0,    27,     0,
       0,    28,    83,    84,    22,     0,    23,    24,    85,    26,
     285,     0,     0,     0,     0,    87,    81,    82,     0,     0,
      30,     0,     0,     0,     0,     0,    88,    89,     0,     0,
       0,    90,    91,     0,    30,    83,    84,    22,     0,    23,
      24,    85,    26,     0,     0,     0,   328,     0,    87,    81,
      82,     0,     0,     0,     0,     0,     0,     0,     0,    88,
      89,     0,     0,     0,    90,    91,     0,    30,    83,    84,
      22,     0,    23,    24,    85,    26,     0,     0,     0,     0,
       0,    87,   354,    81,    82,     0,     0,     0,     0,     0,
       0,     0,    88,    89,     0,     0,     0,    90,    91,     0,
      30,     0,    83,    84,    22,     0,    23,    24,    85,    26,
       0,     0,     0,     0,     0,    87,   361,    81,    82,     0,
       0,     0,     0,     0,     0,     0,    88,    89,     0,     0,
       0,    90,    91,     0,    30,     0,    83,    84,    22,     0,
      23,    24,    85,    26,     0,     0,     0,     0,     0,    87,
      81,    82,     0,   363,     0,     0,     0,     0,     0,     0,
      88,    89,     0,     0,     0,    90,    91,     0,    30,    83,
      84,    22,     0,    23,    24,    85,    26,     0,     0,     0,
       0,     0,    87,   394,    81,    82,     0,     0,     0,     0,
       0,     0,     0,    88,    89,     0,     0,     0,    90,    91,
       0,    30,     0,    83,    84,    22,     0,    23,    24,    85,
      26,     0,     0,    27,     0,     0,    87,    81,    82,     0,
       0,     0,     0,     0,     0,     0,     0,    88,    89,     0,
       0,     0,    90,    91,     0,    30,    83,    84,    22,     0,
      23,    24,    85,    26,     0,     0,     0,     0,     0,    87,
     440,    81,    82,     0,     0,     0,     0,     0,     0,     0,
      88,    89,     0,     0,     0,    90,    91,     0,    30,     0,
      83,    84,    22,     0,    23,    24,    85,    26,     0,     0,
       0,     0,     0,   141,    81,    82,     0,     0,     0,     0,
       0,     0,     0,     0,    88,    89,     0,     0,     0,    90,
      91,     0,    30,    83,    84,    22,     0,    23,    24,    85,
      26,     0,     0,     0,     0,     0,    87,    81,    82,     0,
       0,     0,     0,     0,     0,     0,     0,    88,    89,     0,
       0,     0,    90,    91,     0,    30,    83,    84,    22,     0,
      23,    24,   202,    26,     0,     0,     0,     0,     0,   141,
      81,    82,     0,     0,     0,     0,     0,     0,     0,     0,
      88,    89,     0,     0,     0,    90,    91,     0,    30,    83,
      84,    22,     0,    23,    24,   320,    26,     0,     0,     0,
       0,     0,   141,    81,    82,     0,     0,     0,     0,     0,
       0,     0,     0,    88,    89,     0,     0,     0,    90,    91,
       0,    30,    83,    84,    22,     0,    23,    24,    85,    26,
       0,     0,     0,     0,     0,   245,     0,    82,     0,     0,
       0,     0,     0,     0,     0,     0,    88,    89,     0,     0,
       0,    90,    91,     0,    30,    83,    84,    22,     0,    23,
      24,   196,    26,     0,     0,     0,     0,     0,    87,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,    88,
      89,     0,     0,     0,    90,    91,     0,    30
    };
  }

private static final short yycheck_[] = yycheck_init();
  private static final short[] yycheck_init()
  {
    return new short[]
    {
       0,    22,   262,   293,   290,   267,   291,   373,     8,    33,
      34,    83,    84,    27,    50,    11,    52,    53,    90,    91,
      40,    45,    46,    47,    11,    49,   312,    42,    48,    29,
      51,    42,    32,    33,    34,    50,     0,    52,    53,    32,
      33,   331,   408,     7,    40,    45,    46,    47,    40,    49,
      42,    65,     7,    40,    50,    42,    20,    21,     0,    52,
      88,    89,    42,    27,    32,    33,    34,    35,     0,    33,
      34,    40,    52,    46,    42,     7,    76,    50,    47,    52,
      53,    45,    46,    47,    52,    49,   107,    87,    20,    21,
      32,    33,    34,    47,   379,    27,    50,   118,    42,    53,
     386,    33,    34,    45,    46,    47,    42,    49,    30,    31,
      47,    47,    42,    45,    46,    47,    52,    49,   404,    83,
      84,   407,    52,   383,    88,    89,    90,    91,   152,   153,
      42,    53,    54,    46,    40,   135,     0,    40,    42,    52,
      44,   141,   427,     7,    47,    48,    49,    50,    46,    52,
      53,   151,   152,   153,    47,    40,    20,    21,    50,   445,
      40,    53,    36,    27,    49,    50,    40,     0,    53,    33,
      34,   433,    42,    47,   434,   139,    36,    47,    47,    42,
      40,    45,    46,    47,    47,    49,   186,    47,   152,   153,
      42,    43,    42,   125,   222,   223,   224,    47,    36,    37,
      33,    34,    40,    42,    43,    47,    44,   139,    50,    43,
     152,   153,    45,    46,    47,    47,    49,    47,   239,    47,
     152,   153,    47,    48,    40,    50,    47,    52,    53,    50,
       0,    52,   232,    40,    36,     0,    38,    39,    40,    28,
      29,    50,     7,    42,   208,   245,   210,   211,   212,   213,
     214,   215,   216,   217,   218,   219,   220,   221,   222,   223,
     224,   333,    27,     0,    58,    59,   294,   267,    33,    34,
       7,   216,   217,   218,   219,   139,    60,    61,    62,    42,
      45,    46,    47,    24,    49,    34,    35,    46,   152,   153,
      27,   325,   326,   293,   214,   215,    33,    34,    46,   371,
      46,   220,   221,    46,    40,   333,    40,    40,    45,    46,
      47,    26,    49,    55,    52,    42,    56,    15,    16,   152,
     153,    47,    57,    42,   288,    42,    42,    13,    42,    51,
     294,   331,    45,   288,   334,    47,    34,    35,    36,    48,
      38,    39,    40,    41,    42,    42,    83,    84,    42,    47,
     314,    88,    89,    90,    91,    47,   288,    42,    51,   314,
      58,    59,    40,    40,     0,    63,    64,    48,    66,   333,
      40,     7,    48,   373,   139,    48,    48,    42,    49,    48,
      47,    42,   314,    49,    48,   385,    49,   152,   153,    51,
     354,    27,    49,    48,    54,    43,    45,    33,    34,   354,
      51,    40,   139,    40,    42,    51,    46,   371,   408,    45,
      46,    47,    42,    49,    42,   152,   153,    42,    42,    42,
      42,    42,   354,    42,   288,    43,   390,    44,    48,   393,
     394,    45,    48,   433,   398,   390,    48,    48,   393,   394,
      49,    49,    40,   398,    48,    42,    48,    48,    42,    10,
     314,    51,    48,    48,    16,    47,   420,    25,   390,   423,
     424,   393,   394,    54,    49,   420,   398,    48,   423,   424,
      40,   208,    48,   210,   211,   212,   213,   214,   215,   216,
     217,   218,   219,   220,   221,   222,   223,   224,   420,    16,
     354,   423,   424,    42,    48,    48,    48,    47,    40,   131,
      48,    52,    47,   139,    48,    48,   139,    34,    35,    36,
      32,    38,    39,    40,    41,    77,   152,   153,    80,   445,
      47,   208,   210,   288,   213,   443,   390,   211,    -1,   393,
     394,    58,    59,   212,   398,    -1,    63,    64,    -1,    66,
      -1,    -1,    -1,    -1,    55,    -1,    57,    -1,    -1,   314,
      -1,   288,    -1,   115,   116,   117,   420,   294,    -1,   423,
     424,    -1,    -1,    74,    -1,   127,   128,    -1,    -1,    -1,
     132,   133,   134,    32,    33,    34,    35,   314,    -1,    -1,
      -1,    -1,    -1,    -1,    43,    -1,   148,    46,    47,   354,
      -1,    50,    -1,    52,    53,    -1,   333,    -1,   109,    -1,
      -1,   112,   164,   165,   166,    -1,    -1,   169,   170,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,   354,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,   390,   188,   189,   393,   394,
      -1,    -1,    -1,   398,   371,    -1,    -1,    -1,    -1,   201,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,   209,    -1,    -1,
      -1,   162,   288,   390,    -1,   420,   393,   394,   423,   424,
      -1,   398,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
     181,    -1,    -1,    -1,   185,   237,   187,    -1,   314,    -1,
      -1,    -1,    -1,   420,    15,    16,   423,   424,    -1,    -1,
     252,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    34,    35,    36,    -1,    38,    39,    40,
      41,    -1,    -1,    -1,   276,    -1,    47,    48,   354,    -1,
     282,    -1,    -1,   285,    15,    16,    -1,    58,    59,   291,
     292,    -1,    63,    64,    -1,    66,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    34,    35,    36,    -1,    38,    39,    40,
      41,   313,    -1,    -1,   390,    -1,    47,   393,   394,    -1,
      51,    -1,   398,   325,   326,    -1,    -1,    58,    59,   280,
      -1,    -1,    63,    64,    -1,    66,   287,    15,    16,    -1,
      -1,    -1,    -1,    -1,   420,    -1,    -1,   423,   424,    -1,
      -1,   353,    -1,    -1,   356,   357,    34,    35,    36,    -1,
      38,    39,    40,    41,    -1,   367,    44,    -1,    -1,    47,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,   379,    -1,    -1,
      58,    59,    -1,    -1,    -1,    63,    64,    -1,    66,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,   400,   350,
      -1,    -1,    -1,    -1,    -1,   407,    -1,    -1,    -1,    -1,
      -1,    -1,     3,     4,     5,     6,     7,     8,     9,    -1,
      11,    12,    13,    14,    -1,   427,    17,    18,    19,    20,
      21,    22,    23,    24,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    34,    35,    36,    -1,    38,    39,    40,
      41,    42,    43,    44,    -1,    -1,    47,    -1,    -1,    50,
      -1,    -1,     3,     4,     5,     6,     7,     8,     9,    -1,
      11,    12,    13,    14,    -1,    66,    17,    18,    19,    20,
      21,    22,    23,    24,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    34,    35,    36,    -1,    38,    39,    40,
      41,    -1,    -1,    44,    -1,    -1,    47,    -1,    -1,    50,
      -1,     3,     4,     5,     6,     7,     8,     9,    -1,    11,
      12,    13,    -1,    -1,    -1,    66,    18,    -1,    -1,    21,
      22,    23,    24,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    34,    35,    36,    -1,    38,    39,    40,    41,
      -1,    -1,    44,    45,    -1,    47,    -1,     3,     4,     5,
       6,     7,     8,     9,    -1,    11,    12,    13,    -1,    -1,
      -1,    -1,    18,    -1,    66,    21,    22,    23,    24,    -1,
      -1,    -1,    -1,    15,    16,    -1,    -1,    -1,    34,    35,
      36,    -1,    38,    39,    40,    41,    -1,    -1,    44,    -1,
      -1,    47,    34,    35,    36,    -1,    38,    39,    40,    41,
      42,    -1,    -1,    -1,    -1,    47,    15,    16,    -1,    -1,
      66,    -1,    -1,    -1,    -1,    -1,    58,    59,    -1,    -1,
      -1,    63,    64,    -1,    66,    34,    35,    36,    -1,    38,
      39,    40,    41,    -1,    -1,    -1,    45,    -1,    47,    15,
      16,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    58,
      59,    -1,    -1,    -1,    63,    64,    -1,    66,    34,    35,
      36,    -1,    38,    39,    40,    41,    -1,    -1,    -1,    -1,
      -1,    47,    48,    15,    16,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    58,    59,    -1,    -1,    -1,    63,    64,    -1,
      66,    -1,    34,    35,    36,    -1,    38,    39,    40,    41,
      -1,    -1,    -1,    -1,    -1,    47,    48,    15,    16,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    58,    59,    -1,    -1,
      -1,    63,    64,    -1,    66,    -1,    34,    35,    36,    -1,
      38,    39,    40,    41,    -1,    -1,    -1,    -1,    -1,    47,
      15,    16,    -1,    51,    -1,    -1,    -1,    -1,    -1,    -1,
      58,    59,    -1,    -1,    -1,    63,    64,    -1,    66,    34,
      35,    36,    -1,    38,    39,    40,    41,    -1,    -1,    -1,
      -1,    -1,    47,    48,    15,    16,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    58,    59,    -1,    -1,    -1,    63,    64,
      -1,    66,    -1,    34,    35,    36,    -1,    38,    39,    40,
      41,    -1,    -1,    44,    -1,    -1,    47,    15,    16,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    58,    59,    -1,
      -1,    -1,    63,    64,    -1,    66,    34,    35,    36,    -1,
      38,    39,    40,    41,    -1,    -1,    -1,    -1,    -1,    47,
      48,    15,    16,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      58,    59,    -1,    -1,    -1,    63,    64,    -1,    66,    -1,
      34,    35,    36,    -1,    38,    39,    40,    41,    -1,    -1,
      -1,    -1,    -1,    47,    15,    16,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    58,    59,    -1,    -1,    -1,    63,
      64,    -1,    66,    34,    35,    36,    -1,    38,    39,    40,
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
      -1,    -1,    -1,    -1,    -1,    47,    -1,    16,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    58,    59,    -1,    -1,
      -1,    63,    64,    -1,    66,    34,    35,    36,    -1,    38,
      39,    40,    41,    -1,    -1,    -1,    -1,    -1,    47,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    58,
      59,    -1,    -1,    -1,    63,    64,    -1,    66
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
      98,   129,    42,    43,   129,    40,    40,   110,   118,   121,
     110,    50,    40,    98,   109,   109,   110,   110,    27,    65,
      26,    55,    56,    57,    28,    29,    30,    31,    53,    54,
      58,    59,    60,    61,    62,    34,    35,    42,   129,   129,
     129,    42,    47,    42,    42,    42,    40,    47,    96,   123,
     129,   129,    42,    42,    79,    47,    48,    97,   125,   129,
      51,   129,    44,   129,    98,    99,    76,    45,    98,    48,
      51,   129,    47,    98,    68,    68,    42,    47,    89,   129,
     129,   129,   129,   129,    42,    42,    47,    42,    42,    89,
      40,    98,    46,    89,   129,    42,   129,    40,    48,    40,
      48,    47,    50,    53,    48,   101,   129,   102,   103,   104,
     105,   106,   106,   107,   107,   107,   107,   108,   108,   109,
     109,   109,    48,    49,    48,    98,   129,   122,    42,    42,
      40,    93,    94,    98,    48,    49,    49,    51,    45,    90,
     129,    49,    54,    48,    43,    45,   127,    51,    40,    93,
      98,    42,    51,    42,    42,    42,    42,   129,    42,    89,
      40,   129,    42,    42,    48,   129,    42,    46,    89,    73,
      75,    48,    90,    51,   129,    99,   109,    46,    75,   129,
      73,    48,    48,    49,    48,    40,    97,    97,    45,    49,
      99,   110,    98,    49,   128,    43,    48,    48,    42,    89,
      48,   129,    73,    48,    48,   129,   129,    42,    10,    48,
      47,    51,    54,   129,    48,   110,    94,    25,    49,    90,
      48,    40,   127,    48,    98,    42,    75,    92,    42,    73,
      48,    73,    73,    48,    48,    73,   129,    47,    75,    75,
     129,    94,    42,    47,    52,    40,    73,    73,    73,    48,
      48,    90,    93,   127,    48,    48,   128,    92
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
      84,    85,    86,    86,    86,    86,    86,    87,    87,    87,
      87,    87,    87,    88,    89,    89,    89,    90,    90,    91,
      91,    92,    92,    93,    93,    94,    94,    94,    94,    95,
      95,    95,    95,    95,    95,    96,    96,    97,    97,    97,
      97,    98,    98,    98,    99,    99,   100,   100,   101,   101,
     102,   102,   103,   103,   104,   104,   105,   105,   105,   106,
     106,   106,   106,   106,   107,   107,   107,   108,   108,   108,
     108,   109,   109,   110,   110,   110,   110,   110,   111,   111,
     111,   112,   112,   112,   113,   113,   113,   114,   114,   115,
     115,   116,   116,   116,   116,   116,   117,   117,   117,   117,
     118,   118,   119,   119,   120,   120,   120,   121,   121,   121,
     122,   122,   123,   123,   123,   124,   124,   124,   124,   124,
     124,   124,   125,   125,   126,   127,   127,   127,   127,   128,
     128,   129,   129,   129
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
       7,     5,     3,     3,     3,     3,     2,     4,     4,     5,
       3,     3,     4,     7,     2,     3,     4,     1,     3,    10,
       6,     1,     1,     1,     0,     1,     2,     3,     4,     4,
       4,     4,     4,     4,     4,     3,     4,     1,     1,     3,
       3,     3,     1,     4,     1,     3,     3,     1,     3,     1,
       1,     3,     1,     3,     1,     3,     1,     3,     3,     1,
       3,     3,     3,     3,     1,     3,     3,     1,     3,     3,
       3,     1,     4,     2,     2,     2,     2,     1,     1,     2,
       2,     1,     2,     2,     1,     1,     1,     1,     6,     3,
       1,     1,     1,     1,     1,     1,     1,     1,     1,     1,
       4,     4,     5,     1,     2,     2,     2,     2,     2,     2,
       3,     2,     1,     1,     3,     2,     4,     5,     5,     7,
       7,     8,     5,     5,     6,     3,     1,     1,     1,     5,
       0,     1,     1,     1
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
  "WILDCARD", "INT", "BOOL_LITERAL", "IDENTIFIER", "OTHER_LITERAL", "';'",
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
  "CastExpression", "UnaryExpression", "LogicalUnaryExpression",
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
     324,   330,   337,   344,   351,   358,   365,   374,   379,   384,
     389,   392,   395,   401,   408,   409,   412,   418,   419,   424,
     427,   433,   434,   438,   439,   443,   444,   445,   449,   457,
     461,   465,   468,   471,   474,   483,   486,   492,   493,   494,
     495,   499,   503,   504,   508,   509,   513,   516,   520,   523,
     527,   528,   534,   535,   541,   542,   548,   549,   552,   558,
     559,   562,   565,   568,   574,   575,   578,   584,   585,   588,
     591,   597,   598,   602,   605,   608,   609,   610,   614,   615,
     616,   620,   621,   624,   630,   631,   632,   636,   637,   641,
     642,   646,   647,   648,   649,   650,   654,   655,   656,   657,
     661,   665,   669,   670,   676,   677,   678,   686,   689,   692,
     696,   697,   701,   702,   703,   707,   708,   711,   714,   719,
     724,   728,   735,   738,   745,   751,   754,   755,   756,   760,
     763,   767,   768,   769
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
                       yystack.valueAt ((yynrhs) - (yyi + 1)),
                       yystack.locationAt ((yynrhs) - (yyi + 1)));
  }

  /* YYTRANSLATE(TOKEN-NUM) -- Symbol number corresponding to TOKEN-NUM
     as returned by yylex, with out-of-bounds checking.  */
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

  private static final int yylast_ = 1507;
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
/* "VondaGrammar.y":59  */

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

/* "VondaGrammar.java":3351  */

}

