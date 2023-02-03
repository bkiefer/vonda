/* A Bison parser, made by GNU Bison 3.8.2.  */

/* Skeleton implementation for Bison LALR(1) parsers in Java

   Copyright (C) 2007-2015, 2018-2021 Free Software Foundation, Inc.

   This program is free software: you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation, either version 3 of the License, or
   (at your option) any later version.

   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with this program.  If not, see <https://www.gnu.org/licenses/>.  */

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

/* DO NOT RELY ON FEATURES THAT ARE NOT DOCUMENTED in the manual,
   especially those whose name start with YY_ or yy_.  They are
   private implementation details that can be changed or removed.  */

package de.dfki.mlt.rudimant.compiler.io;



import java.text.MessageFormat;
import java.util.ArrayList;
/* "%code imports" blocks.  */
/* "VondaGrammar.y":3  */


import java.io.Reader;
import java.util.*;
import de.dfki.mlt.rudimant.compiler.Type;
import de.dfki.mlt.rudimant.common.Position;
import de.dfki.mlt.rudimant.compiler.tree.*;

@SuppressWarnings({"serial", "unchecked", "fallthrough", "unused"})

/* "VondaGrammar.java":56  */

/**
 * A Bison parser, automatically generated from <tt>VondaGrammar.y</tt>.
 *
 * @author LALR (1) parser skeleton written by Paolo Bonzini.
 */
public class VondaGrammar
{
  /** Version number for the Bison executable that generated this parser.  */
  public static final String bisonVersion = "3.8.2";

  /** Name of the skeleton that generated this parser.  */
  public static final String bisonSkeleton = "lalr1.java";



  /**
   * True if verbose error messages are enabled.
   */
  private boolean yyErrorVerbose = true;

  /**
   * Whether verbose error messages are enabled.
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
  public static class Location {
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
    public String toString() {
      if (begin.equals (end))
        return begin.toString();
      else
        return begin.toString() + "-" + end.toString();
    }
  }

  private Location yylloc(YYStack rhs, int n)
  {
    if (0 < n)
      return new Location(rhs.locationAt(n-1).begin, rhs.locationAt(0).end);
    else
      return new Location(rhs.locationAt(0).end);
  }

  public enum SymbolKind
  {
    S_YYEOF(0),                    /* "end of file"  */
    S_YYerror(1),                  /* error  */
    S_YYUNDEF(2),                  /* "invalid token"  */
    S_BREAK(3),                    /* BREAK  */
    S_CANCEL(4),                   /* CANCEL  */
    S_CANCEL_ALL(5),               /* CANCEL_ALL  */
    S_CASE(6),                     /* CASE  */
    S_CONTINUE(7),                 /* CONTINUE  */
    S_DEFAULT(8),                  /* DEFAULT  */
    S_DO(9),                       /* DO  */
    S_ELSE(10),                    /* ELSE  */
    S_FINAL(11),                   /* FINAL  */
    S_FOR(12),                     /* FOR  */
    S_IF(13),                      /* IF  */
    S_IMPORT(14),                  /* IMPORT  */
    S_INCLUDE(15),                 /* INCLUDE  */
    S_NEW(16),                     /* NEW  */
    S_NULL(17),                    /* NULL  */
    S_PRIVATE(18),                 /* PRIVATE  */
    S_PROPOSE(19),                 /* PROPOSE  */
    S_PROTECTED(20),               /* PROTECTED  */
    S_PUBLIC(21),                  /* PUBLIC  */
    S_RETURN(22),                  /* RETURN  */
    S_SWITCH(23),                  /* SWITCH  */
    S_TIMEOUT(24),                 /* TIMEOUT  */
    S_WHILE(25),                   /* WHILE  */
    S_ARROW(26),                   /* ARROW  */
    S_ANDAND(27),                  /* ANDAND  */
    S_OROR(28),                    /* OROR  */
    S_EQEQ(29),                    /* EQEQ  */
    S_NOTEQ(30),                   /* NOTEQ  */
    S_GTEQ(31),                    /* GTEQ  */
    S_LTEQ(32),                    /* LTEQ  */
    S_MINUSEQ(33),                 /* MINUSEQ  */
    S_PLUSEQ(34),                  /* PLUSEQ  */
    S_MINUSMINUS(35),              /* MINUSMINUS  */
    S_PLUSPLUS(36),                /* PLUSPLUS  */
    S_STRING(37),                  /* STRING  */
    S_WILDCARD(38),                /* WILDCARD  */
    S_INT(39),                     /* INT  */
    S_BOOL_LITERAL(40),            /* BOOL_LITERAL  */
    S_IDENTIFIER(41),              /* IDENTIFIER  */
    S_OTHER_LITERAL(42),           /* OTHER_LITERAL  */
    S_43_(43),                     /* ';'  */
    S_44_(44),                     /* '.'  */
    S_45_(45),                     /* '{'  */
    S_46_(46),                     /* '}'  */
    S_47_(47),                     /* ':'  */
    S_48_(48),                     /* '('  */
    S_49_(49),                     /* ')'  */
    S_50_(50),                     /* ','  */
    S_51_(51),                     /* '['  */
    S_52_(52),                     /* ']'  */
    S_53_(53),                     /* '='  */
    S_54_(54),                     /* '<'  */
    S_55_(55),                     /* '>'  */
    S_56_(56),                     /* '|'  */
    S_57_(57),                     /* '^'  */
    S_58_(58),                     /* '&'  */
    S_59_(59),                     /* '+'  */
    S_60_(60),                     /* '-'  */
    S_61_(61),                     /* '*'  */
    S_62_(62),                     /* '/'  */
    S_63_(63),                     /* '%'  */
    S_64_(64),                     /* '!'  */
    S_65_(65),                     /* '~'  */
    S_66_(66),                     /* '?'  */
    S_67_(67),                     /* '#'  */
    S_YYACCEPT(68),                /* $accept  */
    S_root(69),                    /* root  */
    S_grammar_file(70),            /* grammar_file  */
    S_visibility_spec(71),         /* visibility_spec  */
    S_imports(72),                 /* imports  */
    S_includes(73),                /* includes  */
    S_path(74),                    /* path  */
    S_statement_no_def(75),        /* statement_no_def  */
    S_statement(76),               /* statement  */
    S_blk_statement(77),           /* blk_statement  */
    S_block(78),                   /* block  */
    S_statements(79),              /* statements  */
    S_grammar_rule(80),            /* grammar_rule  */
    S_return_statement(81),        /* return_statement  */
    S_if_statement(82),            /* if_statement  */
    S_while_statement(83),         /* while_statement  */
    S_for_statement(84),           /* for_statement  */
    S_var_decl(85),                /* var_decl  */
    S_propose_statement(86),       /* propose_statement  */
    S_timeout_statement(87),       /* timeout_statement  */
    S_switch_statement(88),        /* switch_statement  */
    S_label_statement(89),         /* label_statement  */
    S_var_def(90),                 /* var_def  */
    S_field_def(91),               /* field_def  */
    S_assgn_exp(92),               /* assgn_exp  */
    S_nonempty_exp_list(93),       /* nonempty_exp_list  */
    S_method_declaration(94),      /* method_declaration  */
    S_opt_block(95),               /* opt_block  */
    S_opt_args_list(96),           /* opt_args_list  */
    S_args_list(97),               /* args_list  */
    S_set_operation(98),           /* set_operation  */
    S_function_call(99),           /* function_call  */
    S_nonempty_args_list(100),     /* nonempty_args_list  */
    S_type_spec(101),              /* type_spec  */
    S_type_spec_list(102),         /* type_spec_list  */
    S_ConditionalOrExpression(103), /* ConditionalOrExpression  */
    S_ConditionalAndExpression(104), /* ConditionalAndExpression  */
    S_InclusiveOrExpression(105),  /* InclusiveOrExpression  */
    S_ExclusiveOrExpression(106),  /* ExclusiveOrExpression  */
    S_AndExpression(107),          /* AndExpression  */
    S_EqualityExpression(108),     /* EqualityExpression  */
    S_RelationalExpression(109),   /* RelationalExpression  */
    S_AdditiveExpression(110),     /* AdditiveExpression  */
    S_MultiplicativeExpression(111), /* MultiplicativeExpression  */
    S_CastExpression(112),         /* CastExpression  */
    S_UnaryExpression(113),        /* UnaryExpression  */
    S_LogicalUnaryExpression(114), /* LogicalUnaryExpression  */
    S_PostfixExpression(115),      /* PostfixExpression  */
    S_PrimaryExpression(116),      /* PrimaryExpression  */
    S_NotJustName(117),            /* NotJustName  */
    S_ComplexPrimary(118),         /* ComplexPrimary  */
    S_ComplexPrimaryNoParenthesis(119), /* ComplexPrimaryNoParenthesis  */
    S_Literal(120),                /* Literal  */
    S_ArrayAccess(121),            /* ArrayAccess  */
    S_ConditionalExpression(122),  /* ConditionalExpression  */
    S_assignment(123),             /* assignment  */
    S_field_access(124),           /* field_access  */
    S_field_access_rest(125),      /* field_access_rest  */
    S_simple_nofa_exp(126),        /* simple_nofa_exp  */
    S_new_exp(127),                /* new_exp  */
    S_lambda_exp(128),             /* lambda_exp  */
    S_dialogueact_exp(129),        /* dialogueact_exp  */
    S_da_token(130),               /* da_token  */
    S_da_args(131),                /* da_args  */
    S_exp(132);                    /* exp  */


    private final int yycode_;

    SymbolKind (int n) {
      this.yycode_ = n;
    }

    private static final SymbolKind[] values_ = {
      SymbolKind.S_YYEOF,
      SymbolKind.S_YYerror,
      SymbolKind.S_YYUNDEF,
      SymbolKind.S_BREAK,
      SymbolKind.S_CANCEL,
      SymbolKind.S_CANCEL_ALL,
      SymbolKind.S_CASE,
      SymbolKind.S_CONTINUE,
      SymbolKind.S_DEFAULT,
      SymbolKind.S_DO,
      SymbolKind.S_ELSE,
      SymbolKind.S_FINAL,
      SymbolKind.S_FOR,
      SymbolKind.S_IF,
      SymbolKind.S_IMPORT,
      SymbolKind.S_INCLUDE,
      SymbolKind.S_NEW,
      SymbolKind.S_NULL,
      SymbolKind.S_PRIVATE,
      SymbolKind.S_PROPOSE,
      SymbolKind.S_PROTECTED,
      SymbolKind.S_PUBLIC,
      SymbolKind.S_RETURN,
      SymbolKind.S_SWITCH,
      SymbolKind.S_TIMEOUT,
      SymbolKind.S_WHILE,
      SymbolKind.S_ARROW,
      SymbolKind.S_ANDAND,
      SymbolKind.S_OROR,
      SymbolKind.S_EQEQ,
      SymbolKind.S_NOTEQ,
      SymbolKind.S_GTEQ,
      SymbolKind.S_LTEQ,
      SymbolKind.S_MINUSEQ,
      SymbolKind.S_PLUSEQ,
      SymbolKind.S_MINUSMINUS,
      SymbolKind.S_PLUSPLUS,
      SymbolKind.S_STRING,
      SymbolKind.S_WILDCARD,
      SymbolKind.S_INT,
      SymbolKind.S_BOOL_LITERAL,
      SymbolKind.S_IDENTIFIER,
      SymbolKind.S_OTHER_LITERAL,
      SymbolKind.S_43_,
      SymbolKind.S_44_,
      SymbolKind.S_45_,
      SymbolKind.S_46_,
      SymbolKind.S_47_,
      SymbolKind.S_48_,
      SymbolKind.S_49_,
      SymbolKind.S_50_,
      SymbolKind.S_51_,
      SymbolKind.S_52_,
      SymbolKind.S_53_,
      SymbolKind.S_54_,
      SymbolKind.S_55_,
      SymbolKind.S_56_,
      SymbolKind.S_57_,
      SymbolKind.S_58_,
      SymbolKind.S_59_,
      SymbolKind.S_60_,
      SymbolKind.S_61_,
      SymbolKind.S_62_,
      SymbolKind.S_63_,
      SymbolKind.S_64_,
      SymbolKind.S_65_,
      SymbolKind.S_66_,
      SymbolKind.S_67_,
      SymbolKind.S_YYACCEPT,
      SymbolKind.S_root,
      SymbolKind.S_grammar_file,
      SymbolKind.S_visibility_spec,
      SymbolKind.S_imports,
      SymbolKind.S_includes,
      SymbolKind.S_path,
      SymbolKind.S_statement_no_def,
      SymbolKind.S_statement,
      SymbolKind.S_blk_statement,
      SymbolKind.S_block,
      SymbolKind.S_statements,
      SymbolKind.S_grammar_rule,
      SymbolKind.S_return_statement,
      SymbolKind.S_if_statement,
      SymbolKind.S_while_statement,
      SymbolKind.S_for_statement,
      SymbolKind.S_var_decl,
      SymbolKind.S_propose_statement,
      SymbolKind.S_timeout_statement,
      SymbolKind.S_switch_statement,
      SymbolKind.S_label_statement,
      SymbolKind.S_var_def,
      SymbolKind.S_field_def,
      SymbolKind.S_assgn_exp,
      SymbolKind.S_nonempty_exp_list,
      SymbolKind.S_method_declaration,
      SymbolKind.S_opt_block,
      SymbolKind.S_opt_args_list,
      SymbolKind.S_args_list,
      SymbolKind.S_set_operation,
      SymbolKind.S_function_call,
      SymbolKind.S_nonempty_args_list,
      SymbolKind.S_type_spec,
      SymbolKind.S_type_spec_list,
      SymbolKind.S_ConditionalOrExpression,
      SymbolKind.S_ConditionalAndExpression,
      SymbolKind.S_InclusiveOrExpression,
      SymbolKind.S_ExclusiveOrExpression,
      SymbolKind.S_AndExpression,
      SymbolKind.S_EqualityExpression,
      SymbolKind.S_RelationalExpression,
      SymbolKind.S_AdditiveExpression,
      SymbolKind.S_MultiplicativeExpression,
      SymbolKind.S_CastExpression,
      SymbolKind.S_UnaryExpression,
      SymbolKind.S_LogicalUnaryExpression,
      SymbolKind.S_PostfixExpression,
      SymbolKind.S_PrimaryExpression,
      SymbolKind.S_NotJustName,
      SymbolKind.S_ComplexPrimary,
      SymbolKind.S_ComplexPrimaryNoParenthesis,
      SymbolKind.S_Literal,
      SymbolKind.S_ArrayAccess,
      SymbolKind.S_ConditionalExpression,
      SymbolKind.S_assignment,
      SymbolKind.S_field_access,
      SymbolKind.S_field_access_rest,
      SymbolKind.S_simple_nofa_exp,
      SymbolKind.S_new_exp,
      SymbolKind.S_lambda_exp,
      SymbolKind.S_dialogueact_exp,
      SymbolKind.S_da_token,
      SymbolKind.S_da_args,
      SymbolKind.S_exp
    };

    static final SymbolKind get(int code) {
      return values_[code];
    }

    public final int getCode() {
      return this.yycode_;
    }

    /* Return YYSTR after stripping away unnecessary quotes and
       backslashes, so that it's suitable for yyerror.  The heuristic is
       that double-quoting is unnecessary unless the string contains an
       apostrophe, a comma, or backslash (other than backslash-backslash).
       YYSTR is taken from yytname.  */
    private static String yytnamerr_(String yystr)
    {
      if (yystr.charAt (0) == '"')
        {
          StringBuffer yyr = new StringBuffer();
          strip_quotes: for (int i = 1; i < yystr.length(); i++)
            switch (yystr.charAt(i))
              {
              case '\'':
              case ',':
                break strip_quotes;

              case '\\':
                if (yystr.charAt(++i) != '\\')
                  break strip_quotes;
                /* Fall through.  */
              default:
                yyr.append(yystr.charAt(i));
                break;

              case '"':
                return yyr.toString();
              }
        }
      return yystr;
    }

    /* YYTNAME[SYMBOL-NUM] -- String name of the symbol SYMBOL-NUM.
       First, the terminals, then, starting at \a YYNTOKENS_, nonterminals.  */
    private static final String[] yytname_ = yytname_init();
  private static final String[] yytname_init()
  {
    return new String[]
    {
  "\"end of file\"", "error", "\"invalid token\"", "BREAK", "CANCEL",
  "CANCEL_ALL", "CASE", "CONTINUE", "DEFAULT", "DO", "ELSE", "FINAL",
  "FOR", "IF", "IMPORT", "INCLUDE", "NEW", "NULL", "PRIVATE", "PROPOSE",
  "PROTECTED", "PUBLIC", "RETURN", "SWITCH", "TIMEOUT", "WHILE", "ARROW",
  "ANDAND", "OROR", "EQEQ", "NOTEQ", "GTEQ", "LTEQ", "MINUSEQ", "PLUSEQ",
  "MINUSMINUS", "PLUSPLUS", "STRING", "WILDCARD", "INT", "BOOL_LITERAL",
  "IDENTIFIER", "OTHER_LITERAL", "';'", "'.'", "'{'", "'}'", "':'", "'('",
  "')'", "','", "'['", "']'", "'='", "'<'", "'>'", "'|'", "'^'", "'&'",
  "'+'", "'-'", "'*'", "'/'", "'%'", "'!'", "'~'", "'?'", "'#'", "$accept",
  "root", "grammar_file", "visibility_spec", "imports", "includes", "path",
  "statement_no_def", "statement", "blk_statement", "block", "statements",
  "grammar_rule", "return_statement", "if_statement", "while_statement",
  "for_statement", "var_decl", "propose_statement", "timeout_statement",
  "switch_statement", "label_statement", "var_def", "field_def",
  "assgn_exp", "nonempty_exp_list", "method_declaration", "opt_block",
  "opt_args_list", "args_list", "set_operation", "function_call",
  "nonempty_args_list", "type_spec", "type_spec_list",
  "ConditionalOrExpression", "ConditionalAndExpression",
  "InclusiveOrExpression", "ExclusiveOrExpression", "AndExpression",
  "EqualityExpression", "RelationalExpression", "AdditiveExpression",
  "MultiplicativeExpression", "CastExpression", "UnaryExpression",
  "LogicalUnaryExpression", "PostfixExpression", "PrimaryExpression",
  "NotJustName", "ComplexPrimary", "ComplexPrimaryNoParenthesis",
  "Literal", "ArrayAccess", "ConditionalExpression", "assignment",
  "field_access", "field_access_rest", "simple_nofa_exp", "new_exp",
  "lambda_exp", "dialogueact_exp", "da_token", "da_args", "exp", null
    };
  }

    /* The user-facing name of this symbol.  */
    public final String getName() {
      return yytnamerr_(yytname_[yycode_]);
    }

  };


  /**
   * Communication interface between the scanner and the Bison-generated
   * parser <tt>VondaGrammar</tt>.
   */
  public interface Lexer {
    /* Token kinds.  */
    /** Token "end of file", to be returned by the scanner.  */
    static final int YYEOF = 0;
    /** Token error, to be returned by the scanner.  */
    static final int YYerror = 256;
    /** Token "invalid token", to be returned by the scanner.  */
    static final int YYUNDEF = 257;
    /** Token BREAK, to be returned by the scanner.  */
    static final int BREAK = 258;
    /** Token CANCEL, to be returned by the scanner.  */
    static final int CANCEL = 259;
    /** Token CANCEL_ALL, to be returned by the scanner.  */
    static final int CANCEL_ALL = 260;
    /** Token CASE, to be returned by the scanner.  */
    static final int CASE = 261;
    /** Token CONTINUE, to be returned by the scanner.  */
    static final int CONTINUE = 262;
    /** Token DEFAULT, to be returned by the scanner.  */
    static final int DEFAULT = 263;
    /** Token DO, to be returned by the scanner.  */
    static final int DO = 264;
    /** Token ELSE, to be returned by the scanner.  */
    static final int ELSE = 265;
    /** Token FINAL, to be returned by the scanner.  */
    static final int FINAL = 266;
    /** Token FOR, to be returned by the scanner.  */
    static final int FOR = 267;
    /** Token IF, to be returned by the scanner.  */
    static final int IF = 268;
    /** Token IMPORT, to be returned by the scanner.  */
    static final int IMPORT = 269;
    /** Token INCLUDE, to be returned by the scanner.  */
    static final int INCLUDE = 270;
    /** Token NEW, to be returned by the scanner.  */
    static final int NEW = 271;
    /** Token NULL, to be returned by the scanner.  */
    static final int NULL = 272;
    /** Token PRIVATE, to be returned by the scanner.  */
    static final int PRIVATE = 273;
    /** Token PROPOSE, to be returned by the scanner.  */
    static final int PROPOSE = 274;
    /** Token PROTECTED, to be returned by the scanner.  */
    static final int PROTECTED = 275;
    /** Token PUBLIC, to be returned by the scanner.  */
    static final int PUBLIC = 276;
    /** Token RETURN, to be returned by the scanner.  */
    static final int RETURN = 277;
    /** Token SWITCH, to be returned by the scanner.  */
    static final int SWITCH = 278;
    /** Token TIMEOUT, to be returned by the scanner.  */
    static final int TIMEOUT = 279;
    /** Token WHILE, to be returned by the scanner.  */
    static final int WHILE = 280;
    /** Token ARROW, to be returned by the scanner.  */
    static final int ARROW = 281;
    /** Token ANDAND, to be returned by the scanner.  */
    static final int ANDAND = 282;
    /** Token OROR, to be returned by the scanner.  */
    static final int OROR = 283;
    /** Token EQEQ, to be returned by the scanner.  */
    static final int EQEQ = 284;
    /** Token NOTEQ, to be returned by the scanner.  */
    static final int NOTEQ = 285;
    /** Token GTEQ, to be returned by the scanner.  */
    static final int GTEQ = 286;
    /** Token LTEQ, to be returned by the scanner.  */
    static final int LTEQ = 287;
    /** Token MINUSEQ, to be returned by the scanner.  */
    static final int MINUSEQ = 288;
    /** Token PLUSEQ, to be returned by the scanner.  */
    static final int PLUSEQ = 289;
    /** Token MINUSMINUS, to be returned by the scanner.  */
    static final int MINUSMINUS = 290;
    /** Token PLUSPLUS, to be returned by the scanner.  */
    static final int PLUSPLUS = 291;
    /** Token STRING, to be returned by the scanner.  */
    static final int STRING = 292;
    /** Token WILDCARD, to be returned by the scanner.  */
    static final int WILDCARD = 293;
    /** Token INT, to be returned by the scanner.  */
    static final int INT = 294;
    /** Token BOOL_LITERAL, to be returned by the scanner.  */
    static final int BOOL_LITERAL = 295;
    /** Token IDENTIFIER, to be returned by the scanner.  */
    static final int IDENTIFIER = 296;
    /** Token OTHER_LITERAL, to be returned by the scanner.  */
    static final int OTHER_LITERAL = 297;

    /** Deprecated, use YYEOF instead.  */
    public static final int EOF = YYEOF;

    /**
     * Method to retrieve the beginning position of the last scanned token.
     * @return the position at which the last scanned token starts.
     */
    Position getStartPos();

    /**
     * Method to retrieve the ending position of the last scanned token.
     * @return the first position beyond the last scanned token.
     */
    Position getEndPos();

    /**
     * Method to retrieve the semantic value of the last scanned token.
     * @return the semantic value of the last scanned token.
     */
    Object getLVal();

    /**
     * Entry point for the scanner.  Returns the token identifier corresponding
     * to the next token and prepares to return the semantic value
     * and beginning/ending positions of the token.
     * @return the token identifier corresponding to the next token.
     */
    int yylex() throws java.io.IOException;

    /**
     * Emit an error referring to the given locationin a user-defined way.
     *
     * @param loc The location of the element to which the
     *                error message is related.
     * @param msg The string for the error message.
     */
     void yyerror(Location loc, String msg);


  }


  /**
   * The object doing lexical analysis for us.
   */
  private Lexer yylexer;





  /**
   * Instantiates the Bison-generated parser.
   * @param yylexer The scanner that will supply tokens to the parser.
   */
  public VondaGrammar(Lexer yylexer)
  {

    this.yylexer = yylexer;

  }


  private java.io.PrintStream yyDebugStream = System.err;

  /**
   * The <tt>PrintStream</tt> on which the debugging output is printed.
   */
  public final java.io.PrintStream getDebugStream() { return yyDebugStream; }

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


  private int yynerrs = 0;

  /**
   * The number of syntax errors so far.
   */
  public final int getNumberOfErrors() { return yynerrs; }

  /**
   * Print an error message via the lexer.
   * Use a <code>null</code> location.
   * @param msg The error message.
   */
  public final void yyerror(String msg) {
      yylexer.yyerror((Location)null, msg);
  }

  /**
   * Print an error message via the lexer.
   * @param loc The location associated with the message.
   * @param msg The error message.
   */
  public final void yyerror(Location loc, String msg) {
      yylexer.yyerror(loc, msg);
  }

  /**
   * Print an error message via the lexer.
   * @param pos The position associated with the message.
   * @param msg The error message.
   */
  public final void yyerror(Position pos, String msg) {
      yylexer.yyerror(new Location (pos), msg);
  }

  protected final void yycdebugNnl(String s) {
    if (0 < yydebug)
      yyDebugStream.print(s);
  }

  protected final void yycdebug(String s) {
    if (0 < yydebug)
      yyDebugStream.println(s);
  }

  private final class YYStack {
    private int[] stateStack = new int[16];
    private Location[] locStack = new Location[16];
    private Object[] valueStack = new Object[16];

    public int size = 16;
    public int height = -1;

    public final void push(int state, Object value, Location loc) {
      height++;
      if (size == height) {
        int[] newStateStack = new int[size * 2];
        System.arraycopy(stateStack, 0, newStateStack, 0, height);
        stateStack = newStateStack;
        Location[] newLocStack = new Location[size * 2];
        System.arraycopy(locStack, 0, newLocStack, 0, height);
        locStack = newLocStack;

        Object[] newValueStack = new Object[size * 2];
        System.arraycopy(valueStack, 0, newValueStack, 0, height);
        valueStack = newValueStack;

        size *= 2;
      }

      stateStack[height] = state;
      locStack[height] = loc;
      valueStack[height] = value;
    }

    public final void pop() {
      pop(1);
    }

    public final void pop(int num) {
      // Avoid memory leaks... garbage collection is a white lie!
      if (0 < num) {
        java.util.Arrays.fill(valueStack, height - num + 1, height + 1, null);
        java.util.Arrays.fill(locStack, height - num + 1, height + 1, null);
      }
      height -= num;
    }

    public final int stateAt(int i) {
      return stateStack[height - i];
    }


    public final Location locationAt(int i) {
      return locStack[height - i];
    }

    public final Object valueAt(int i) {
      return valueStack[height - i];
    }

    // Print the state stack on the debug stream.
    public void print(java.io.PrintStream out) {
      out.print ("Stack now");

      for (int i = 0; i <= height; i++) {
        out.print(' ');
        out.print(stateStack[i]);
      }
      out.println();
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
   * Whether error recovery is being done.  In this state, the parser
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
  private int yyLRGotoState(int yystate, int yysym) {
    int yyr = yypgoto_[yysym - YYNTOKENS_] + yystate;
    if (0 <= yyr && yyr <= YYLAST_ && yycheck_[yyr] == yystate)
      return yytable_[yyr];
    else
      return yydefgoto_[yysym - YYNTOKENS_];
  }

  private int yyaction(int yyn, YYStack yystack, int yylen)
  {
    /* If YYLEN is nonzero, implement the default value of the action:
       '$$ = $1'.  Otherwise, use the top of the stack.

       Otherwise, the following line sets YYVAL to garbage.
       This behavior is undocumented and Bison
       users should not rely upon it.  */
    Object yyval = (0 < yylen) ? yystack.valueAt(yylen - 1) : yystack.valueAt(0);
    Location yyloc = yylloc(yystack, yylen);

    yyReducePrint(yyn, yystack);

    switch (yyn)
      {
          case 2: /* root: imports grammar_file  */
  if (yyn == 2)
    /* "VondaGrammar.y":142  */
                         { yyval = ((LinkedList<RudiTree>)(yystack.valueAt (0))); ((LinkedList<RudiTree>)(yystack.valueAt (0))).addFirst(((Import)(yystack.valueAt (1)))); };
  break;


  case 3: /* root: grammar_file  */
  if (yyn == 3)
    /* "VondaGrammar.y":143  */
                 { yyval = ((LinkedList<RudiTree>)(yystack.valueAt (0)));};
  break;


  case 4: /* grammar_file: visibility_spec method_declaration grammar_file  */
  if (yyn == 4)
    /* "VondaGrammar.y":147  */
                                                    {
    yyval = ((LinkedList<RudiTree>)(yystack.valueAt (0))); ((StatMethodDeclaration)(yystack.valueAt (1))).setVisibility(((String)(yystack.valueAt (2)))); ((LinkedList<RudiTree>)(yystack.valueAt (0))).addFirst(((StatMethodDeclaration)(yystack.valueAt (1))));
  };
  break;


  case 5: /* grammar_file: method_declaration grammar_file  */
  if (yyn == 5)
    /* "VondaGrammar.y":150  */
                                    { yyval = ((LinkedList<RudiTree>)(yystack.valueAt (0))); ((LinkedList<RudiTree>)(yystack.valueAt (0))).addFirst(((StatMethodDeclaration)(yystack.valueAt (1)))); };
  break;


  case 6: /* grammar_file: function_call grammar_file  */
  if (yyn == 6)
    /* "VondaGrammar.y":151  */
                               { yyval = ((LinkedList<RudiTree>)(yystack.valueAt (0))); ((LinkedList<RudiTree>)(yystack.valueAt (0))).addFirst(((RTExpression)(yystack.valueAt (1)))); };
  break;


  case 7: /* grammar_file: statement_no_def grammar_file  */
  if (yyn == 7)
    /* "VondaGrammar.y":152  */
                                  { yyval = ((LinkedList<RudiTree>)(yystack.valueAt (0))); ((LinkedList<RudiTree>)(yystack.valueAt (0))).addFirst(((RTStatement)(yystack.valueAt (1)))); };
  break;


  case 8: /* grammar_file: includes grammar_file  */
  if (yyn == 8)
    /* "VondaGrammar.y":154  */
                          { yyval = ((LinkedList<RudiTree>)(yystack.valueAt (0))); ((LinkedList<RudiTree>)(yystack.valueAt (0))).addFirst(((Include)(yystack.valueAt (1)))); };
  break;


  case 9: /* grammar_file: visibility_spec var_def grammar_file  */
  if (yyn == 9)
    /* "VondaGrammar.y":155  */
                                          {
    yyval = ((LinkedList<RudiTree>)(yystack.valueAt (0))); ((LinkedList<RudiTree>)(yystack.valueAt (0))).addFirst(setPos(new StatFieldDef(((String)(yystack.valueAt (2))), ((StatVarDef)(yystack.valueAt (1)))), yystack.locationAt (2), yystack.locationAt (1)));
  };
  break;


  case 10: /* grammar_file: var_def grammar_file  */
  if (yyn == 10)
    /* "VondaGrammar.y":158  */
                          {
    yyval = ((LinkedList<RudiTree>)(yystack.valueAt (0))); ((LinkedList<RudiTree>)(yystack.valueAt (0))).addFirst(setPos(new StatFieldDef(null, ((StatVarDef)(yystack.valueAt (1)))), yystack.locationAt (1)));
  };
  break;


  case 11: /* grammar_file: field_def grammar_file  */
  if (yyn == 11)
    /* "VondaGrammar.y":161  */
                           {
    yyval = ((LinkedList<RudiTree>)(yystack.valueAt (0))); ((LinkedList<RudiTree>)(yystack.valueAt (0))).addFirst(((StatFieldDef)(yystack.valueAt (1))));
  };
  break;


  case 12: /* grammar_file: %empty  */
  if (yyn == 12)
    /* "VondaGrammar.y":164  */
           { yyval = _statements;};
  break;


  case 13: /* visibility_spec: PUBLIC  */
  if (yyn == 13)
    /* "VondaGrammar.y":168  */
           { yyval = "public"; };
  break;


  case 14: /* visibility_spec: PROTECTED  */
  if (yyn == 14)
    /* "VondaGrammar.y":169  */
              { yyval = "protected"; };
  break;


  case 15: /* visibility_spec: PRIVATE  */
  if (yyn == 15)
    /* "VondaGrammar.y":170  */
             { yyval = "private"; };
  break;


  case 16: /* imports: IMPORT path ';'  */
  if (yyn == 16)
    /* "VondaGrammar.y":173  */
                          { yyval = setPos(new Import(((List<String>)(yystack.valueAt (1)))), (yyloc)); };
  break;


  case 17: /* includes: INCLUDE path ';'  */
  if (yyn == 17)
    /* "VondaGrammar.y":176  */
                     {
    List<String> path = ((List<String>)(yystack.valueAt (1)));
    String name = path.remove(path.size() - 1);
    yyval = setPos(new Include(name, path.toArray(new String[path.size()])), (yyloc));
  };
  break;


  case 18: /* path: IDENTIFIER  */
  if (yyn == 18)
    /* "VondaGrammar.y":184  */
               { yyval = new ArrayList<String>(){{ add((( String )(yystack.valueAt (0)))); }}; };
  break;


  case 19: /* path: path '.' IDENTIFIER  */
  if (yyn == 19)
    /* "VondaGrammar.y":185  */
                        { yyval = ((List<String>)(yystack.valueAt (2))); ((List<String>)(yystack.valueAt (2))).add((( String )(yystack.valueAt (0)))); };
  break;


  case 20: /* statement_no_def: block  */
  if (yyn == 20)
    /* "VondaGrammar.y":189  */
          { yyval = ((StatAbstractBlock)(yystack.valueAt (0))); };
  break;


  case 21: /* statement_no_def: assignment ';'  */
  if (yyn == 21)
    /* "VondaGrammar.y":190  */
                   { yyval = setPos(getAssignmentStat(((RTExpression)(yystack.valueAt (1)))), (yyloc)); };
  break;


  case 22: /* statement_no_def: field_access ';'  */
  if (yyn == 22)
    /* "VondaGrammar.y":191  */
                     { yyval = setPos(new StatExpression(((RTExpression)(yystack.valueAt (1)))), (yyloc)); };
  break;


  case 23: /* statement_no_def: PLUSPLUS IDENTIFIER ';'  */
  if (yyn == 23)
    /* "VondaGrammar.y":192  */
                            {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (1)))), yystack.locationAt (1));
    yyval = setPos(new StatExpression(createPlusMinus(var, "++", (yyloc))), (yyloc));
  };
  break;


  case 24: /* statement_no_def: MINUSMINUS IDENTIFIER ';'  */
  if (yyn == 24)
    /* "VondaGrammar.y":196  */
                              {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (1)))), yystack.locationAt (1));
    yyval = setPos(new StatExpression(createPlusMinus(var, "--", (yyloc))), (yyloc));
  };
  break;


  case 25: /* statement_no_def: IDENTIFIER PLUSPLUS ';'  */
  if (yyn == 25)
    /* "VondaGrammar.y":200  */
                            {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (2)))), yystack.locationAt (2));
    yyval = setPos(new StatExpression(createPlusMinus(var, "+++", (yyloc))), (yyloc));
  };
  break;


  case 26: /* statement_no_def: IDENTIFIER MINUSMINUS ';'  */
  if (yyn == 26)
    /* "VondaGrammar.y":204  */
                              {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (2)))), yystack.locationAt (2));
    yyval = setPos(new StatExpression(createPlusMinus(var, "---", (yyloc))), (yyloc));
  };
  break;


  case 27: /* statement_no_def: PLUSPLUS field_access ';'  */
  if (yyn == 27)
    /* "VondaGrammar.y":208  */
                              {
    yyval = setPos(new StatExpression(createPlusMinus(((RTExpression)(yystack.valueAt (1))), "++", (yyloc))), (yyloc));
  };
  break;


  case 28: /* statement_no_def: MINUSMINUS field_access ';'  */
  if (yyn == 28)
    /* "VondaGrammar.y":211  */
                                {
    yyval = setPos(new StatExpression(createPlusMinus(((RTExpression)(yystack.valueAt (1))), "--", (yyloc))), (yyloc));
  };
  break;


  case 29: /* statement_no_def: field_access PLUSPLUS ';'  */
  if (yyn == 29)
    /* "VondaGrammar.y":214  */
                              {
    yyval = setPos(new StatExpression(createPlusMinus(((RTExpression)(yystack.valueAt (2))), "+++", (yyloc))), (yyloc));
  };
  break;


  case 30: /* statement_no_def: field_access MINUSMINUS ';'  */
  if (yyn == 30)
    /* "VondaGrammar.y":217  */
                                {
    yyval = setPos(new StatExpression(createPlusMinus(((RTExpression)(yystack.valueAt (2))), "---", (yyloc))), (yyloc));
  };
  break;


  case 31: /* statement_no_def: function_call ';'  */
  if (yyn == 31)
    /* "VondaGrammar.y":220  */
                      { yyval = setPos(new StatExpression(((RTExpression)(yystack.valueAt (1)))), (yyloc)); };
  break;


  case 32: /* statement_no_def: grammar_rule  */
  if (yyn == 32)
    /* "VondaGrammar.y":221  */
                 { yyval = ((StatGrammarRule)(yystack.valueAt (0))); };
  break;


  case 33: /* statement_no_def: set_operation  */
  if (yyn == 33)
    /* "VondaGrammar.y":222  */
                  { yyval = ((RTStatement)(yystack.valueAt (0))); };
  break;


  case 34: /* statement_no_def: return_statement  */
  if (yyn == 34)
    /* "VondaGrammar.y":223  */
                     { yyval = ((RTStatement)(yystack.valueAt (0))); };
  break;


  case 35: /* statement_no_def: propose_statement  */
  if (yyn == 35)
    /* "VondaGrammar.y":224  */
                      { yyval = ((RTStatement)(yystack.valueAt (0))); };
  break;


  case 36: /* statement_no_def: timeout_statement  */
  if (yyn == 36)
    /* "VondaGrammar.y":225  */
                      { yyval = ((RTStatement)(yystack.valueAt (0))); };
  break;


  case 37: /* statement_no_def: if_statement  */
  if (yyn == 37)
    /* "VondaGrammar.y":226  */
                 { yyval = ((StatIf)(yystack.valueAt (0))); };
  break;


  case 38: /* statement_no_def: while_statement  */
  if (yyn == 38)
    /* "VondaGrammar.y":227  */
                    { yyval = ((RTStatement)(yystack.valueAt (0))); };
  break;


  case 39: /* statement_no_def: for_statement  */
  if (yyn == 39)
    /* "VondaGrammar.y":228  */
                  { yyval = ((RTStatement)(yystack.valueAt (0))); };
  break;


  case 40: /* statement_no_def: switch_statement  */
  if (yyn == 40)
    /* "VondaGrammar.y":229  */
                     { yyval = ((RTStatement)(yystack.valueAt (0))); };
  break;


  case 41: /* statement_no_def: label_statement  */
  if (yyn == 41)
    /* "VondaGrammar.y":230  */
                    { yyval = ((RTStatement)(yystack.valueAt (0))); };
  break;


  case 42: /* statement: statement_no_def  */
  if (yyn == 42)
    /* "VondaGrammar.y":234  */
                     { yyval = ((RTStatement)(yystack.valueAt (0))); };
  break;


  case 43: /* statement: var_def  */
  if (yyn == 43)
    /* "VondaGrammar.y":235  */
            { yyval = ((StatVarDef)(yystack.valueAt (0))); };
  break;


  case 44: /* blk_statement: statement  */
  if (yyn == 44)
    /* "VondaGrammar.y":239  */
              { yyval = ((RTStatement)(yystack.valueAt (0))); };
  break;


  case 45: /* block: '{' statements '}'  */
  if (yyn == 45)
    /* "VondaGrammar.y":245  */
                       { yyval = setPos(new StatAbstractBlock(((LinkedList<RTStatement>)(yystack.valueAt (1))), true), (yyloc)); };
  break;


  case 46: /* block: '{' '}'  */
  if (yyn == 46)
    /* "VondaGrammar.y":246  */
            {
    yyval = setPos(new StatAbstractBlock(new ArrayList<RTStatement>(), true), (yyloc));
  };
  break;


  case 47: /* statements: blk_statement  */
  if (yyn == 47)
    /* "VondaGrammar.y":250  */
                          { yyval = new LinkedList<RTStatement>(){{ add(((RTStatement)(yystack.valueAt (0)))); }}; };
  break;


  case 48: /* statements: blk_statement statements  */
  if (yyn == 48)
    /* "VondaGrammar.y":251  */
                             { yyval = ((LinkedList<RTStatement>)(yystack.valueAt (0))); ((LinkedList<RTStatement>)(yystack.valueAt (0))).addFirst(((RTStatement)(yystack.valueAt (1)))); };
  break;


  case 49: /* grammar_rule: IDENTIFIER ':' if_statement  */
  if (yyn == 49)
    /* "VondaGrammar.y":255  */
                                { yyval = setPos(new StatGrammarRule((( String )(yystack.valueAt (2))), ((StatIf)(yystack.valueAt (0)))), (yyloc)); };
  break;


  case 50: /* return_statement: RETURN ';'  */
  if (yyn == 50)
    /* "VondaGrammar.y":259  */
               { yyval = setPos(new StatReturn("return"), (yyloc)); };
  break;


  case 51: /* return_statement: RETURN exp ';'  */
  if (yyn == 51)
    /* "VondaGrammar.y":260  */
                   { yyval = setPos(new StatReturn(((RTExpression)(yystack.valueAt (1)))), (yyloc)); };
  break;


  case 52: /* return_statement: BREAK ';'  */
  if (yyn == 52)
    /* "VondaGrammar.y":261  */
              { yyval = setPos(new StatReturn("break"), (yyloc)); };
  break;


  case 53: /* return_statement: BREAK IDENTIFIER ';'  */
  if (yyn == 53)
    /* "VondaGrammar.y":262  */
                         { yyval = setPos(new StatReturn("break", (( String )(yystack.valueAt (1)))), (yyloc)); };
  break;


  case 54: /* return_statement: CANCEL ';'  */
  if (yyn == 54)
    /* "VondaGrammar.y":263  */
               { yyval = setPos(new StatReturn("cancel"), (yyloc)); };
  break;


  case 55: /* return_statement: CANCEL_ALL ';'  */
  if (yyn == 55)
    /* "VondaGrammar.y":264  */
                   { yyval = setPos(new StatReturn("cancel_all"), (yyloc)); };
  break;


  case 56: /* return_statement: CONTINUE ';'  */
  if (yyn == 56)
    /* "VondaGrammar.y":265  */
                 { yyval = setPos(new StatReturn("continue"), (yyloc)); };
  break;


  case 57: /* if_statement: IF '(' exp ')' statement  */
  if (yyn == 57)
    /* "VondaGrammar.y":269  */
                             { yyval = setPos(new StatIf(((RTExpression)(yystack.valueAt (2))), ((RTStatement)(yystack.valueAt (0))), null), (yyloc)); };
  break;


  case 58: /* if_statement: IF '(' exp ')' statement ELSE statement  */
  if (yyn == 58)
    /* "VondaGrammar.y":270  */
                                            {
    yyval = setPos(new StatIf(((RTExpression)(yystack.valueAt (4))), ((RTStatement)(yystack.valueAt (2))), ((RTStatement)(yystack.valueAt (0)))), (yyloc));
  };
  break;


  case 59: /* while_statement: WHILE '(' exp ')' statement  */
  if (yyn == 59)
    /* "VondaGrammar.y":276  */
                                { yyval = setPos(new StatWhile(((RTExpression)(yystack.valueAt (2))), ((RTStatement)(yystack.valueAt (0))), true), (yyloc)); };
  break;


  case 60: /* while_statement: DO statement WHILE '(' exp ')'  */
  if (yyn == 60)
    /* "VondaGrammar.y":277  */
                                   {
    yyval = setPos(new StatWhile(((RTExpression)(yystack.valueAt (1))), ((RTStatement)(yystack.valueAt (4))), false), (yyloc));
  };
  break;


  case 61: /* for_statement: FOR '(' var_decl exp ';' exp ')' statement  */
  if (yyn == 61)
    /* "VondaGrammar.y":283  */
                                               {
    yyval = setPos(new StatFor1(((StatVarDef)(yystack.valueAt (5))), ((RTExpression)(yystack.valueAt (4))), ((RTExpression)(yystack.valueAt (2))), ((RTStatement)(yystack.valueAt (0)))), (yyloc)); };
  break;


  case 62: /* for_statement: FOR '(' var_decl ';' exp ')' statement  */
  if (yyn == 62)
    /* "VondaGrammar.y":285  */
                                               {
    yyval = setPos(new StatFor1(((StatVarDef)(yystack.valueAt (4))), null, ((RTExpression)(yystack.valueAt (2))), ((RTStatement)(yystack.valueAt (0)))), (yyloc)); };
  break;


  case 63: /* for_statement: FOR '(' var_decl exp ';' ')' statement  */
  if (yyn == 63)
    /* "VondaGrammar.y":287  */
                                               {
    yyval = setPos(new StatFor1(((StatVarDef)(yystack.valueAt (4))), ((RTExpression)(yystack.valueAt (3))), null, ((RTStatement)(yystack.valueAt (0)))), (yyloc)); };
  break;


  case 64: /* for_statement: FOR '(' var_decl ';' ')' statement  */
  if (yyn == 64)
    /* "VondaGrammar.y":289  */
                                               {
    yyval = setPos(new StatFor1(((StatVarDef)(yystack.valueAt (3))), null, null, ((RTStatement)(yystack.valueAt (0)))), (yyloc)); };
  break;


  case 65: /* for_statement: FOR '(' ';' exp ';' exp ')' statement  */
  if (yyn == 65)
    /* "VondaGrammar.y":291  */
                                              {
    yyval = setPos(new StatFor1(null, ((RTExpression)(yystack.valueAt (4))), ((RTExpression)(yystack.valueAt (2))), ((RTStatement)(yystack.valueAt (0)))), (yyloc)); };
  break;


  case 66: /* for_statement: FOR '(' IDENTIFIER ':' exp ')' statement  */
  if (yyn == 66)
    /* "VondaGrammar.y":293  */
                                             {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (4)))), yystack.locationAt (4));
    yyval = setPos(new StatFor2(var, ((RTExpression)(yystack.valueAt (2))), ((RTStatement)(yystack.valueAt (0)))), (yyloc));
  };
  break;


  case 67: /* for_statement: FOR '(' type_spec IDENTIFIER ':' exp ')' statement  */
  if (yyn == 67)
    /* "VondaGrammar.y":297  */
                                                       {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (4)))), yystack.locationAt (4));
    yyval = setPos(new StatFor2(((Type)(yystack.valueAt (5))), var, ((RTExpression)(yystack.valueAt (2))), ((RTStatement)(yystack.valueAt (0)))), (yyloc));
  };
  break;


  case 68: /* var_decl: IDENTIFIER assgn_exp ';'  */
  if (yyn == 68)
    /* "VondaGrammar.y":306  */
                             {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (2)))), yystack.locationAt (2));
    ExpAssignment ass = setPos(new ExpAssignment(var, ((RTExpression)(yystack.valueAt (1)))), yystack.locationAt (2), yystack.locationAt (1));
    yyval = setPos(new StatVarDef(false, Type.getNoType(), ass), (yyloc));
  };
  break;


  case 69: /* var_decl: type_spec IDENTIFIER assgn_exp ';'  */
  if (yyn == 69)
    /* "VondaGrammar.y":311  */
                                       {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (2)))), yystack.locationAt (2));
    ExpAssignment ass = setPos(new ExpAssignment(var, ((RTExpression)(yystack.valueAt (1)))), yystack.locationAt (2), yystack.locationAt (1));
    yyval = setPos(new StatVarDef(false, ((Type)(yystack.valueAt (3))), ass), (yyloc));
  };
  break;


  case 70: /* var_decl: FINAL IDENTIFIER assgn_exp ';'  */
  if (yyn == 70)
    /* "VondaGrammar.y":316  */
                                   {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (2)))), yystack.locationAt (2));
    ExpAssignment ass = setPos(new ExpAssignment(var, ((RTExpression)(yystack.valueAt (1)))), yystack.locationAt (2), yystack.locationAt (1));
    yyval = setPos(new StatVarDef(true, Type.getNoType(), ass), (yyloc));
  };
  break;


  case 71: /* var_decl: FINAL type_spec IDENTIFIER assgn_exp ';'  */
  if (yyn == 71)
    /* "VondaGrammar.y":321  */
                                             {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (2)))), yystack.locationAt (2));
    ExpAssignment ass = setPos(new ExpAssignment(var, ((RTExpression)(yystack.valueAt (1)))), yystack.locationAt (2), yystack.locationAt (1));
    yyval = setPos(new StatVarDef(true, ((Type)(yystack.valueAt (3))), ass), (yyloc));
  };
  break;


  case 72: /* propose_statement: PROPOSE '(' exp ')' block  */
  if (yyn == 72)
    /* "VondaGrammar.y":329  */
                              { yyval = setPos(new StatPropose(((RTExpression)(yystack.valueAt (2))), ((StatAbstractBlock)(yystack.valueAt (0)))), (yyloc)); };
  break;


  case 73: /* timeout_statement: TIMEOUT '(' exp ',' exp ')' block  */
  if (yyn == 73)
    /* "VondaGrammar.y":333  */
                                      {
    yyval = setPos(new StatTimeout(((RTExpression)(yystack.valueAt (4))), ((RTExpression)(yystack.valueAt (2))), ((StatAbstractBlock)(yystack.valueAt (0)))), (yyloc));
  };
  break;


  case 74: /* switch_statement: SWITCH '(' exp ')' block  */
  if (yyn == 74)
    /* "VondaGrammar.y":339  */
                             {
    yyval = setPos(new StatSwitch(((RTExpression)(yystack.valueAt (2))), ((StatAbstractBlock)(yystack.valueAt (0)))), (yyloc));
  };
  break;


  case 75: /* label_statement: CASE STRING ':'  */
  if (yyn == 75)
    /* "VondaGrammar.y":346  */
                      {
    ExpLiteral val =
      new ExpLiteral("case \"" + (( ExpLiteral )(yystack.valueAt (1))).toString() + "\":", "label");
    RTStatement lbl = val.ensureStatement();
    setPos(val, (yyloc)); setPos(lbl, (yyloc));
    yyval = lbl;
  };
  break;


  case 76: /* label_statement: CASE INT ':'  */
  if (yyn == 76)
    /* "VondaGrammar.y":353  */
                      {
    ExpLiteral val =
      new ExpLiteral("case " + (( ExpLiteral )(yystack.valueAt (1))).toString() + ":", "label");
    RTStatement lbl = val.ensureStatement();
    setPos(val, (yyloc)); setPos(lbl, (yyloc));
    yyval = lbl;
  };
  break;


  case 77: /* label_statement: CASE BOOL_LITERAL ':'  */
  if (yyn == 77)
    /* "VondaGrammar.y":360  */
                               {
    ExpLiteral val =
      new ExpLiteral("case " + (( ExpLiteral )(yystack.valueAt (1))).toString() + ":", "label");
    RTStatement lbl = val.ensureStatement();
    setPos(val, (yyloc)); setPos(lbl, (yyloc));
    yyval = lbl;
  };
  break;


  case 78: /* label_statement: CASE IDENTIFIER ':'  */
  if (yyn == 78)
    /* "VondaGrammar.y":367  */
                        {
    ExpLiteral val =
      new ExpLiteral("case " + (( String )(yystack.valueAt (1))) + ":", "label");
    RTStatement lbl = val.ensureStatement();
    setPos(val, (yyloc)); setPos(lbl, (yyloc));
    yyval = lbl;
  };
  break;


  case 79: /* label_statement: DEFAULT ':'  */
  if (yyn == 79)
    /* "VondaGrammar.y":374  */
                      {
    ExpLiteral val = new ExpLiteral("default:", "label");
    RTStatement lbl = val.ensureStatement();
    setPos(val, (yyloc)); setPos(lbl, (yyloc));
    yyval = lbl;
  };
  break;


  case 80: /* var_def: FINAL IDENTIFIER assgn_exp ';'  */
  if (yyn == 80)
    /* "VondaGrammar.y":383  */
                                   {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (2)))), yystack.locationAt (2));
    ExpAssignment ass = setPos(new ExpAssignment(var, ((RTExpression)(yystack.valueAt (1)))), yystack.locationAt (2), yystack.locationAt (1));
    yyval = setPos(new StatVarDef(true, Type.getNoType(), ass), (yyloc));
  };
  break;


  case 81: /* var_def: type_spec IDENTIFIER assgn_exp ';'  */
  if (yyn == 81)
    /* "VondaGrammar.y":388  */
                                       {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (2)))), yystack.locationAt (2));
    ExpAssignment ass = setPos(new ExpAssignment(var, ((RTExpression)(yystack.valueAt (1)))), yystack.locationAt (2), yystack.locationAt (1));
    yyval = setPos(new StatVarDef(false, ((Type)(yystack.valueAt (3))), ass), (yyloc));
  };
  break;


  case 82: /* var_def: FINAL type_spec IDENTIFIER assgn_exp ';'  */
  if (yyn == 82)
    /* "VondaGrammar.y":393  */
                                             {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (2)))), yystack.locationAt (2));
    ExpAssignment ass = setPos(new ExpAssignment(var, ((RTExpression)(yystack.valueAt (1)))), yystack.locationAt (2), yystack.locationAt (1));
    yyval = setPos(new StatVarDef(true, ((Type)(yystack.valueAt (3))), ass), (yyloc));
    };
  break;


  case 83: /* var_def: FINAL IDENTIFIER ';'  */
  if (yyn == 83)
    /* "VondaGrammar.y":398  */
                         {
    yyval = setPos(new StatVarDef(true, Type.getNoType(), (( String )(yystack.valueAt (1)))), (yyloc));
  };
  break;


  case 84: /* var_def: type_spec IDENTIFIER ';'  */
  if (yyn == 84)
    /* "VondaGrammar.y":401  */
                             {
    yyval = setPos(new StatVarDef(false, ((Type)(yystack.valueAt (2))), (( String )(yystack.valueAt (1)))), (yyloc));
  };
  break;


  case 85: /* var_def: FINAL type_spec IDENTIFIER ';'  */
  if (yyn == 85)
    /* "VondaGrammar.y":404  */
                                   {
    yyval = setPos(new StatVarDef(true, ((Type)(yystack.valueAt (2))), (( String )(yystack.valueAt (1)))), (yyloc));
  };
  break;


  case 86: /* field_def: '[' type_spec ']' '.' type_spec IDENTIFIER ';'  */
  if (yyn == 86)
    /* "VondaGrammar.y":410  */
                                                   {
    yyval = setPos(new StatFieldDef(null,
           setPos(new StatVarDef(false, ((Type)(yystack.valueAt (2))), (( String )(yystack.valueAt (1)))), (yyloc)), ((Type)(yystack.valueAt (5)))), (yyloc));
  };
  break;


  case 87: /* assgn_exp: '=' exp  */
  if (yyn == 87)
    /* "VondaGrammar.y":417  */
            { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 88: /* assgn_exp: '=' '{' '}'  */
  if (yyn == 88)
    /* "VondaGrammar.y":418  */
                {
    yyval = setPos(new ExpListLiteral(new LinkedList<RTExpression>()), yystack.locationAt (1), yystack.locationAt (0));
  };
  break;


  case 89: /* assgn_exp: '=' '{' nonempty_exp_list '}'  */
  if (yyn == 89)
    /* "VondaGrammar.y":421  */
                                  {
    yyval = setPos(new ExpListLiteral(((LinkedList<RTExpression>)(yystack.valueAt (1)))), yystack.locationAt (2), yystack.locationAt (0));
  };
  break;


  case 90: /* nonempty_exp_list: exp  */
  if (yyn == 90)
    /* "VondaGrammar.y":427  */
        { yyval = new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (0)))); }}; };
  break;


  case 91: /* nonempty_exp_list: exp ',' nonempty_exp_list  */
  if (yyn == 91)
    /* "VondaGrammar.y":428  */
                              { yyval = ((LinkedList<RTExpression>)(yystack.valueAt (0))); ((LinkedList<RTExpression>)(yystack.valueAt (0))).addFirst(((RTExpression)(yystack.valueAt (2)))); };
  break;


  case 92: /* method_declaration: '[' type_spec ']' '.' type_spec IDENTIFIER '(' opt_args_list ')' opt_block  */
  if (yyn == 92)
    /* "VondaGrammar.y":433  */
                                                                               {
    yyval = setPos(new StatMethodDeclaration("public", ((Type)(yystack.valueAt (5))), ((Type)(yystack.valueAt (8))), (( String )(yystack.valueAt (4))), ((LinkedList)(yystack.valueAt (2))), ((StatAbstractBlock)(yystack.valueAt (0)))), (yyloc));
  };
  break;


  case 93: /* method_declaration: type_spec IDENTIFIER '(' opt_args_list ')' opt_block  */
  if (yyn == 93)
    /* "VondaGrammar.y":436  */
                                                         {
    yyval = setPos(new StatMethodDeclaration("public", ((Type)(yystack.valueAt (5))), null, (( String )(yystack.valueAt (4))), ((LinkedList)(yystack.valueAt (2))), ((StatAbstractBlock)(yystack.valueAt (0)))), (yyloc));
  };
  break;


  case 94: /* opt_block: block  */
  if (yyn == 94)
    /* "VondaGrammar.y":442  */
          { yyval = ((StatAbstractBlock)(yystack.valueAt (0))); };
  break;


  case 95: /* opt_block: ';'  */
  if (yyn == 95)
    /* "VondaGrammar.y":443  */
        { yyval = null; };
  break;


  case 96: /* opt_args_list: args_list  */
  if (yyn == 96)
    /* "VondaGrammar.y":447  */
              { yyval = ((LinkedList)(yystack.valueAt (0))); };
  break;


  case 97: /* opt_args_list: %empty  */
  if (yyn == 97)
    /* "VondaGrammar.y":448  */
           { yyval = new LinkedList(); };
  break;


  case 98: /* args_list: IDENTIFIER  */
  if (yyn == 98)
    /* "VondaGrammar.y":452  */
               { yyval = new LinkedList(){{ add(Type.getNoType()); add((( String )(yystack.valueAt (0)))); }}; };
  break;


  case 99: /* args_list: type_spec IDENTIFIER  */
  if (yyn == 99)
    /* "VondaGrammar.y":453  */
                         { yyval = new LinkedList(){{ add(((Type)(yystack.valueAt (1)))); add((( String )(yystack.valueAt (0)))); }}; };
  break;


  case 100: /* args_list: IDENTIFIER ',' args_list  */
  if (yyn == 100)
    /* "VondaGrammar.y":454  */
                             {
    yyval = ((LinkedList)(yystack.valueAt (0)));
    ((LinkedList)(yystack.valueAt (0))).addFirst((( String )(yystack.valueAt (2)))); ((LinkedList)(yystack.valueAt (0))).addFirst(Type.getNoType());
  };
  break;


  case 101: /* args_list: type_spec IDENTIFIER ',' args_list  */
  if (yyn == 101)
    /* "VondaGrammar.y":458  */
                                       {
    yyval = ((LinkedList)(yystack.valueAt (0))); ((LinkedList)(yystack.valueAt (0))).addFirst((( String )(yystack.valueAt (2)))); ((LinkedList)(yystack.valueAt (0))).addFirst(((Type)(yystack.valueAt (3))));
  };
  break;


  case 102: /* set_operation: IDENTIFIER PLUSEQ exp ';'  */
  if (yyn == 102)
    /* "VondaGrammar.y":466  */
                              {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (3)))), yystack.locationAt (3));
    yyval = setPos(new StatSetOperation(var, true, ((RTExpression)(yystack.valueAt (1)))), (yyloc));
  };
  break;


  case 103: /* set_operation: IDENTIFIER MINUSEQ exp ';'  */
  if (yyn == 103)
    /* "VondaGrammar.y":470  */
                               {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (3)))), yystack.locationAt (3));
    yyval = setPos(new StatSetOperation(var, false, ((RTExpression)(yystack.valueAt (1)))), (yyloc));
  };
  break;


  case 104: /* set_operation: ArrayAccess PLUSEQ exp ';'  */
  if (yyn == 104)
    /* "VondaGrammar.y":474  */
                               {
    yyval = setPos(new StatSetOperation(((RTExpression)(yystack.valueAt (3))), true, ((RTExpression)(yystack.valueAt (1)))), (yyloc));
  };
  break;


  case 105: /* set_operation: ArrayAccess MINUSEQ exp ';'  */
  if (yyn == 105)
    /* "VondaGrammar.y":477  */
                                {
    yyval = setPos(new StatSetOperation(((RTExpression)(yystack.valueAt (3))), false, ((RTExpression)(yystack.valueAt (1)))), (yyloc));
  };
  break;


  case 106: /* set_operation: field_access PLUSEQ exp ';'  */
  if (yyn == 106)
    /* "VondaGrammar.y":480  */
                                {
    yyval = setPos(new StatSetOperation(((RTExpression)(yystack.valueAt (3))), true, ((RTExpression)(yystack.valueAt (1)))), (yyloc));
  };
  break;


  case 107: /* set_operation: field_access MINUSEQ exp ';'  */
  if (yyn == 107)
    /* "VondaGrammar.y":483  */
                                 {
    yyval = setPos(new StatSetOperation(((RTExpression)(yystack.valueAt (3))), false, ((RTExpression)(yystack.valueAt (1)))), (yyloc));
  };
  break;


  case 108: /* function_call: IDENTIFIER '(' ')'  */
  if (yyn == 108)
    /* "VondaGrammar.y":492  */
                       {
    yyval = setPos(new ExpFuncCall((( String )(yystack.valueAt (2))), new LinkedList<RTExpression>(), false), (yyloc));
  };
  break;


  case 109: /* function_call: IDENTIFIER '(' nonempty_args_list ')'  */
  if (yyn == 109)
    /* "VondaGrammar.y":495  */
                                           {
    yyval = setPos(new ExpFuncCall((( String )(yystack.valueAt (3))), ((LinkedList<RTExpression>)(yystack.valueAt (1))), false), (yyloc));
  };
  break;


  case 110: /* nonempty_args_list: exp  */
  if (yyn == 110)
    /* "VondaGrammar.y":501  */
        { yyval = new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (0)))); }}; };
  break;


  case 111: /* nonempty_args_list: lambda_exp  */
  if (yyn == 111)
    /* "VondaGrammar.y":502  */
               { yyval = new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (0)))); }}; };
  break;


  case 112: /* nonempty_args_list: exp ',' nonempty_args_list  */
  if (yyn == 112)
    /* "VondaGrammar.y":503  */
                               { yyval = ((LinkedList<RTExpression>)(yystack.valueAt (0))); ((LinkedList<RTExpression>)(yystack.valueAt (0))).addFirst(((RTExpression)(yystack.valueAt (2)))); };
  break;


  case 113: /* nonempty_args_list: lambda_exp ',' nonempty_args_list  */
  if (yyn == 113)
    /* "VondaGrammar.y":504  */
                                      { yyval = ((LinkedList<RTExpression>)(yystack.valueAt (0))); ((LinkedList<RTExpression>)(yystack.valueAt (0))).addFirst(((RTExpression)(yystack.valueAt (2)))); };
  break;


  case 114: /* type_spec: IDENTIFIER '[' ']'  */
  if (yyn == 114)
    /* "VondaGrammar.y":508  */
                       {
    yyval = new Type("Array",
                  new ArrayList<Type>(){{ add(new Type((( String )(yystack.valueAt (2))))); }});
  };
  break;


  case 115: /* type_spec: IDENTIFIER  */
  if (yyn == 115)
    /* "VondaGrammar.y":512  */
               { yyval = new Type((( String )(yystack.valueAt (0)))); };
  break;


  case 116: /* type_spec: IDENTIFIER '<' type_spec_list '>'  */
  if (yyn == 116)
    /* "VondaGrammar.y":513  */
                                      { yyval = new Type((( String )(yystack.valueAt (3))), ((LinkedList<Type>)(yystack.valueAt (1)))); };
  break;


  case 117: /* type_spec_list: type_spec  */
  if (yyn == 117)
    /* "VondaGrammar.y":517  */
              { yyval = new LinkedList<Type>(){{ add(((Type)(yystack.valueAt (0)))); }}; };
  break;


  case 118: /* type_spec_list: type_spec ',' type_spec_list  */
  if (yyn == 118)
    /* "VondaGrammar.y":518  */
                                 { yyval = ((LinkedList<Type>)(yystack.valueAt (0))); ((LinkedList<Type>)(yystack.valueAt (0))).addFirst(((Type)(yystack.valueAt (2)))); };
  break;


  case 119: /* ConditionalOrExpression: ConditionalOrExpression OROR ConditionalAndExpression  */
  if (yyn == 119)
    /* "VondaGrammar.y":522  */
                                                          {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "||"), (yyloc));
  };
  break;


  case 120: /* ConditionalOrExpression: ConditionalAndExpression  */
  if (yyn == 120)
    /* "VondaGrammar.y":525  */
                             { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 121: /* ConditionalAndExpression: ConditionalAndExpression ANDAND InclusiveOrExpression  */
  if (yyn == 121)
    /* "VondaGrammar.y":529  */
                                                          {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "&&"), (yyloc));
  };
  break;


  case 122: /* ConditionalAndExpression: InclusiveOrExpression  */
  if (yyn == 122)
    /* "VondaGrammar.y":532  */
                          { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 123: /* InclusiveOrExpression: ExclusiveOrExpression  */
  if (yyn == 123)
    /* "VondaGrammar.y":536  */
                          { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 124: /* InclusiveOrExpression: InclusiveOrExpression '|' ExclusiveOrExpression  */
  if (yyn == 124)
    /* "VondaGrammar.y":537  */
                                                    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "|"), (yyloc));
  };
  break;


  case 125: /* ExclusiveOrExpression: AndExpression  */
  if (yyn == 125)
    /* "VondaGrammar.y":543  */
                  { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 126: /* ExclusiveOrExpression: ExclusiveOrExpression '^' AndExpression  */
  if (yyn == 126)
    /* "VondaGrammar.y":544  */
                                            {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "^"), (yyloc));
  };
  break;


  case 127: /* AndExpression: EqualityExpression  */
  if (yyn == 127)
    /* "VondaGrammar.y":550  */
                       { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 128: /* AndExpression: AndExpression '&' EqualityExpression  */
  if (yyn == 128)
    /* "VondaGrammar.y":551  */
                                         {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "&"), (yyloc));
  };
  break;


  case 129: /* EqualityExpression: RelationalExpression  */
  if (yyn == 129)
    /* "VondaGrammar.y":557  */
                         { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 130: /* EqualityExpression: EqualityExpression EQEQ RelationalExpression  */
  if (yyn == 130)
    /* "VondaGrammar.y":558  */
                                                 {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "=="), (yyloc));
  };
  break;


  case 131: /* EqualityExpression: EqualityExpression NOTEQ RelationalExpression  */
  if (yyn == 131)
    /* "VondaGrammar.y":561  */
                                                  {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "!="), (yyloc));
  };
  break;


  case 132: /* RelationalExpression: AdditiveExpression  */
  if (yyn == 132)
    /* "VondaGrammar.y":567  */
                       { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 133: /* RelationalExpression: RelationalExpression '<' AdditiveExpression  */
  if (yyn == 133)
    /* "VondaGrammar.y":568  */
                                                {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "<"), (yyloc));
  };
  break;


  case 134: /* RelationalExpression: RelationalExpression '>' AdditiveExpression  */
  if (yyn == 134)
    /* "VondaGrammar.y":571  */
                                                {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), ">"), (yyloc));
  };
  break;


  case 135: /* RelationalExpression: RelationalExpression GTEQ AdditiveExpression  */
  if (yyn == 135)
    /* "VondaGrammar.y":574  */
                                                 {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), ">="), (yyloc));
  };
  break;


  case 136: /* RelationalExpression: RelationalExpression LTEQ AdditiveExpression  */
  if (yyn == 136)
    /* "VondaGrammar.y":577  */
                                                 {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "<="), (yyloc));
  };
  break;


  case 137: /* AdditiveExpression: MultiplicativeExpression  */
  if (yyn == 137)
    /* "VondaGrammar.y":583  */
                             { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 138: /* AdditiveExpression: AdditiveExpression '+' MultiplicativeExpression  */
  if (yyn == 138)
    /* "VondaGrammar.y":584  */
                                                    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "+"), (yyloc));
  };
  break;


  case 139: /* AdditiveExpression: AdditiveExpression '-' MultiplicativeExpression  */
  if (yyn == 139)
    /* "VondaGrammar.y":587  */
                                                    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "-"), (yyloc));
  };
  break;


  case 140: /* MultiplicativeExpression: CastExpression  */
  if (yyn == 140)
    /* "VondaGrammar.y":593  */
                   { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 141: /* MultiplicativeExpression: MultiplicativeExpression '*' CastExpression  */
  if (yyn == 141)
    /* "VondaGrammar.y":594  */
                                                {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "*"), (yyloc));
  };
  break;


  case 142: /* MultiplicativeExpression: MultiplicativeExpression '/' CastExpression  */
  if (yyn == 142)
    /* "VondaGrammar.y":597  */
                                                {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "/"), (yyloc));
  };
  break;


  case 143: /* MultiplicativeExpression: MultiplicativeExpression '%' CastExpression  */
  if (yyn == 143)
    /* "VondaGrammar.y":600  */
                                                {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "%"), (yyloc));
  };
  break;


  case 144: /* CastExpression: UnaryExpression  */
  if (yyn == 144)
    /* "VondaGrammar.y":606  */
                    { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 145: /* CastExpression: '(' type_spec ')' CastExpression  */
  if (yyn == 145)
    /* "VondaGrammar.y":607  */
                                     { yyval = setPos(new ExpCast(((Type)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0)))), (yyloc)); };
  break;


  case 146: /* UnaryExpression: PLUSPLUS UnaryExpression  */
  if (yyn == 146)
    /* "VondaGrammar.y":611  */
                             {
    yyval = createPlusMinus(((RTExpression)(yystack.valueAt (0))), "++", (yyloc));
  };
  break;


  case 147: /* UnaryExpression: MINUSMINUS UnaryExpression  */
  if (yyn == 147)
    /* "VondaGrammar.y":614  */
                               {
    yyval = createPlusMinus(((RTExpression)(yystack.valueAt (0))), "--", (yyloc));
  };
  break;


  case 148: /* UnaryExpression: '+' CastExpression  */
  if (yyn == 148)
    /* "VondaGrammar.y":617  */
                       { yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (0))), null, "+"), (yyloc)); };
  break;


  case 149: /* UnaryExpression: '-' CastExpression  */
  if (yyn == 149)
    /* "VondaGrammar.y":618  */
                       { yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (0))), null, "-"), (yyloc)); };
  break;


  case 150: /* UnaryExpression: LogicalUnaryExpression  */
  if (yyn == 150)
    /* "VondaGrammar.y":619  */
                           { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 151: /* LogicalUnaryExpression: PostfixExpression  */
  if (yyn == 151)
    /* "VondaGrammar.y":623  */
                      { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 152: /* LogicalUnaryExpression: '!' UnaryExpression  */
  if (yyn == 152)
    /* "VondaGrammar.y":624  */
                        { yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (0))), null, "!"), (yyloc)); };
  break;


  case 153: /* LogicalUnaryExpression: '~' UnaryExpression  */
  if (yyn == 153)
    /* "VondaGrammar.y":625  */
                        { yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (0))), null, "~"), (yyloc)); };
  break;


  case 154: /* PostfixExpression: PrimaryExpression  */
  if (yyn == 154)
    /* "VondaGrammar.y":629  */
                      { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 155: /* PostfixExpression: PostfixExpression PLUSPLUS  */
  if (yyn == 155)
    /* "VondaGrammar.y":630  */
                               {
    yyval = createPlusMinus(((RTExpression)(yystack.valueAt (1))), "+++", (yyloc));
  };
  break;


  case 156: /* PostfixExpression: PostfixExpression MINUSMINUS  */
  if (yyn == 156)
    /* "VondaGrammar.y":633  */
                                 {
    yyval = createPlusMinus(((RTExpression)(yystack.valueAt (1))), "---", (yyloc));
  };
  break;


  case 157: /* PrimaryExpression: NULL  */
  if (yyn == 157)
    /* "VondaGrammar.y":639  */
         { yyval = setPos(new ExpLiteral("null", "null"), (yyloc)); };
  break;


  case 158: /* PrimaryExpression: NotJustName  */
  if (yyn == 158)
    /* "VondaGrammar.y":640  */
                { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 159: /* PrimaryExpression: ComplexPrimary  */
  if (yyn == 159)
    /* "VondaGrammar.y":641  */
                   { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 160: /* NotJustName: IDENTIFIER  */
  if (yyn == 160)
    /* "VondaGrammar.y":645  */
               { yyval = setPos(new ExpIdentifier((( String )(yystack.valueAt (0)))), (yyloc)); };
  break;


  case 161: /* NotJustName: '(' '(' type_spec ')' UnaryExpression ')'  */
  if (yyn == 161)
    /* "VondaGrammar.y":646  */
                                              { yyval = setPos(new ExpCast(((Type)(yystack.valueAt (3))), ((RTExpression)(yystack.valueAt (1)))), (yyloc)); };
  break;


  case 162: /* ComplexPrimary: '(' exp ')'  */
  if (yyn == 162)
    /* "VondaGrammar.y":650  */
                { yyval = ((RTExpression)(yystack.valueAt (1))); ((RTExpression)(yystack.valueAt (1))).generateParens(); };
  break;


  case 163: /* ComplexPrimary: ComplexPrimaryNoParenthesis  */
  if (yyn == 163)
    /* "VondaGrammar.y":651  */
                                { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 164: /* ComplexPrimaryNoParenthesis: Literal  */
  if (yyn == 164)
    /* "VondaGrammar.y":655  */
            { yyval = ((ExpLiteral)(yystack.valueAt (0))); };
  break;


  case 165: /* ComplexPrimaryNoParenthesis: ArrayAccess  */
  if (yyn == 165)
    /* "VondaGrammar.y":656  */
                { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 166: /* ComplexPrimaryNoParenthesis: field_access  */
  if (yyn == 166)
    /* "VondaGrammar.y":657  */
                 { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 167: /* ComplexPrimaryNoParenthesis: function_call  */
  if (yyn == 167)
    /* "VondaGrammar.y":658  */
                  { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 168: /* ComplexPrimaryNoParenthesis: dialogueact_exp  */
  if (yyn == 168)
    /* "VondaGrammar.y":659  */
                    { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 169: /* Literal: STRING  */
  if (yyn == 169)
    /* "VondaGrammar.y":663  */
           { yyval = setPos((( ExpLiteral )(yystack.valueAt (0))), (yyloc)); };
  break;


  case 170: /* Literal: INT  */
  if (yyn == 170)
    /* "VondaGrammar.y":664  */
        { yyval = setPos((( ExpLiteral )(yystack.valueAt (0))), (yyloc)); };
  break;


  case 171: /* Literal: OTHER_LITERAL  */
  if (yyn == 171)
    /* "VondaGrammar.y":665  */
                  { yyval = setPos((( ExpLiteral )(yystack.valueAt (0))), (yyloc)); };
  break;


  case 172: /* Literal: BOOL_LITERAL  */
  if (yyn == 172)
    /* "VondaGrammar.y":666  */
                 { yyval = setPos((( ExpLiteral )(yystack.valueAt (0))), (yyloc)); };
  break;


  case 173: /* ArrayAccess: IDENTIFIER '[' exp ']'  */
  if (yyn == 173)
    /* "VondaGrammar.y":670  */
                           {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (3)))), yystack.locationAt (3));
    yyval = setPos(new ExpArrayAccess(var, ((RTExpression)(yystack.valueAt (1)))), (yyloc));
  };
  break;


  case 174: /* ArrayAccess: ComplexPrimary '[' exp ']'  */
  if (yyn == 174)
    /* "VondaGrammar.y":674  */
                               { yyval = setPos(new ExpArrayAccess(((RTExpression)(yystack.valueAt (3))), ((RTExpression)(yystack.valueAt (1)))), (yyloc)); };
  break;


  case 175: /* ConditionalExpression: ConditionalOrExpression '?' exp ':' exp  */
  if (yyn == 175)
    /* "VondaGrammar.y":678  */
                                            { yyval = setPos(new ExpConditional(((RTExpression)(yystack.valueAt (4))), ((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0)))), (yyloc)); };
  break;


  case 176: /* ConditionalExpression: ConditionalOrExpression  */
  if (yyn == 176)
    /* "VondaGrammar.y":679  */
                            { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 177: /* assignment: field_access assgn_exp  */
  if (yyn == 177)
    /* "VondaGrammar.y":685  */
                           { yyval = setPos(new ExpAssignment(((RTExpression)(yystack.valueAt (1))), ((RTExpression)(yystack.valueAt (0)))), (yyloc)); };
  break;


  case 178: /* assignment: ArrayAccess assgn_exp  */
  if (yyn == 178)
    /* "VondaGrammar.y":686  */
                          { yyval = setPos(new ExpAssignment(((RTExpression)(yystack.valueAt (1))), ((RTExpression)(yystack.valueAt (0)))), (yyloc)); };
  break;


  case 179: /* assignment: IDENTIFIER assgn_exp  */
  if (yyn == 179)
    /* "VondaGrammar.y":687  */
                         {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (1)))), yystack.locationAt (1));
    ExpAssignment ass = setPos(new ExpAssignment(var, ((RTExpression)(yystack.valueAt (0)))), (yyloc));
    yyval = ass;
  };
  break;


  case 180: /* field_access: NotJustName field_access_rest  */
  if (yyn == 180)
    /* "VondaGrammar.y":695  */
                                  {
    yyval = setPos(new ExpFieldAccess(((LinkedList<RTExpression>)(yystack.valueAt (0)))), (yyloc)); ((LinkedList<RTExpression>)(yystack.valueAt (0))).addFirst(((RTExpression)(yystack.valueAt (1))));
  };
  break;


  case 181: /* field_access: STRING field_access_rest  */
  if (yyn == 181)
    /* "VondaGrammar.y":698  */
                             {
    yyval = setPos(new ExpFieldAccess(((LinkedList<RTExpression>)(yystack.valueAt (0)))), (yyloc)); ((LinkedList<RTExpression>)(yystack.valueAt (0))).addFirst(setPos((( ExpLiteral )(yystack.valueAt (1))), (yyloc)));
  };
  break;


  case 182: /* field_access: function_call field_access_rest  */
  if (yyn == 182)
    /* "VondaGrammar.y":701  */
                                    { yyval = setPos(new ExpFieldAccess(((LinkedList<RTExpression>)(yystack.valueAt (0)))), (yyloc)); ((LinkedList<RTExpression>)(yystack.valueAt (0))).addFirst(((RTExpression)(yystack.valueAt (1)))); };
  break;


  case 183: /* field_access_rest: '.' simple_nofa_exp field_access_rest  */
  if (yyn == 183)
    /* "VondaGrammar.y":705  */
                                          { yyval = ((LinkedList<RTExpression>)(yystack.valueAt (0))); ((LinkedList<RTExpression>)(yystack.valueAt (0))).addFirst(((RTExpression)(yystack.valueAt (1)))); };
  break;


  case 184: /* field_access_rest: '.' simple_nofa_exp  */
  if (yyn == 184)
    /* "VondaGrammar.y":706  */
                        { yyval = new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (0)))); }}; };
  break;


  case 185: /* simple_nofa_exp: IDENTIFIER  */
  if (yyn == 185)
    /* "VondaGrammar.y":710  */
               { yyval = setPos(new ExpIdentifier((( String )(yystack.valueAt (0)))), (yyloc)); };
  break;


  case 186: /* simple_nofa_exp: function_call  */
  if (yyn == 186)
    /* "VondaGrammar.y":711  */
                  { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 187: /* simple_nofa_exp: '(' exp ')'  */
  if (yyn == 187)
    /* "VondaGrammar.y":712  */
                { yyval = ((RTExpression)(yystack.valueAt (1))); };
  break;


  case 188: /* new_exp: NEW IDENTIFIER  */
  if (yyn == 188)
    /* "VondaGrammar.y":716  */
                   { yyval = setPos(new ExpNew(new Type((( String )(yystack.valueAt (0))))), (yyloc)); };
  break;


  case 189: /* new_exp: NEW IDENTIFIER '(' ')'  */
  if (yyn == 189)
    /* "VondaGrammar.y":717  */
                           {
    yyval = setPos(new ExpNew(new Type((( String )(yystack.valueAt (2)))), new LinkedList<>()), (yyloc));
  };
  break;


  case 190: /* new_exp: NEW IDENTIFIER '(' nonempty_exp_list ')'  */
  if (yyn == 190)
    /* "VondaGrammar.y":720  */
                                             {
    yyval = setPos(new ExpNew(new Type((( String )(yystack.valueAt (3)))), ((LinkedList<RTExpression>)(yystack.valueAt (1)))), (yyloc));
  };
  break;


  case 191: /* new_exp: NEW IDENTIFIER '[' exp ']'  */
  if (yyn == 191)
    /* "VondaGrammar.y":723  */
                               {
    List<Type> sub = new ArrayList<Type>() {{ add(new Type((( String )(yystack.valueAt (3))))); }};
    yyval = setPos(new ExpNew(new Type("Array", sub),
                    new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (1)))); }}), (yyloc));
  };
  break;


  case 192: /* new_exp: NEW IDENTIFIER '[' ']' '(' exp ')'  */
  if (yyn == 192)
    /* "VondaGrammar.y":728  */
                                      {
    List<Type> sub = new ArrayList<Type>() {{ add(new Type((( String )(yystack.valueAt (5))))); }};
    yyval = setPos(new ExpNew(new Type("Array", sub),
                    new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (1)))); }}), (yyloc));
  };
  break;


  case 193: /* new_exp: NEW IDENTIFIER '<' type_spec_list '>' '(' ')'  */
  if (yyn == 193)
    /* "VondaGrammar.y":733  */
                                                  {
    yyval = setPos(new ExpNew(new Type((( String )(yystack.valueAt (5))), ((LinkedList<Type>)(yystack.valueAt (3)))),
                    new LinkedList<>()), (yyloc));
  };
  break;


  case 194: /* new_exp: NEW IDENTIFIER '<' type_spec_list '>' '(' nonempty_exp_list ')'  */
  if (yyn == 194)
    /* "VondaGrammar.y":737  */
                                                                    {
    yyval = setPos(new ExpNew(new Type((( String )(yystack.valueAt (6))), ((LinkedList<Type>)(yystack.valueAt (4)))),
                    ((LinkedList<RTExpression>)(yystack.valueAt (1)))), (yyloc));
  };
  break;


  case 195: /* lambda_exp: '(' opt_args_list ')' ARROW exp  */
  if (yyn == 195)
    /* "VondaGrammar.y":744  */
                                    {
    yyval = setPos(new ExpLambda(((LinkedList)(yystack.valueAt (3))), ((RTExpression)(yystack.valueAt (0)))), (yyloc));
  };
  break;


  case 196: /* lambda_exp: '(' opt_args_list ')' ARROW block  */
  if (yyn == 196)
    /* "VondaGrammar.y":747  */
                                      {
    yyval = setPos(new ExpLambda(((LinkedList)(yystack.valueAt (3))), ((StatAbstractBlock)(yystack.valueAt (0)))), (yyloc));
  };
  break;


  case 197: /* dialogueact_exp: '#' da_token '(' da_token da_args ')'  */
  if (yyn == 197)
    /* "VondaGrammar.y":754  */
                                          {
    yyval = setPos(new ExpDialogueAct(((RTExpression)(yystack.valueAt (4))), ((RTExpression)(yystack.valueAt (2))), ((LinkedList<RTExpression>)(yystack.valueAt (1)))), (yyloc));
  };
  break;


  case 198: /* da_token: '{' exp '}'  */
  if (yyn == 198)
    /* "VondaGrammar.y":760  */
                { yyval = ((RTExpression)(yystack.valueAt (1))); };
  break;


  case 199: /* da_token: IDENTIFIER  */
  if (yyn == 199)
    /* "VondaGrammar.y":763  */
               { yyval = setPos(new ExpLiteral((( String )(yystack.valueAt (0))), "String"), (yyloc)); };
  break;


  case 200: /* da_token: STRING  */
  if (yyn == 200)
    /* "VondaGrammar.y":764  */
           { yyval = setPos((( ExpLiteral )(yystack.valueAt (0))), (yyloc)); };
  break;


  case 201: /* da_token: WILDCARD  */
  if (yyn == 201)
    /* "VondaGrammar.y":765  */
             { yyval = setPos(new ExpLiteral((( String )(yystack.valueAt (0))), "String"), (yyloc)); };
  break;


  case 202: /* da_args: ',' da_token '=' da_token da_args  */
  if (yyn == 202)
    /* "VondaGrammar.y":769  */
                                       {
    yyval = ((LinkedList<RTExpression>)(yystack.valueAt (0))); ((LinkedList<RTExpression>)(yystack.valueAt (0))).addFirst(((RTExpression)(yystack.valueAt (1)))); ((LinkedList<RTExpression>)(yystack.valueAt (0))).addFirst(((RTExpression)(yystack.valueAt (3))));
  };
  break;


  case 203: /* da_args: %empty  */
  if (yyn == 203)
    /* "VondaGrammar.y":772  */
           { yyval = new LinkedList<RTExpression>(); };
  break;


  case 204: /* exp: ConditionalExpression  */
  if (yyn == 204)
    /* "VondaGrammar.y":776  */
                          { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 205: /* exp: assignment  */
  if (yyn == 205)
    /* "VondaGrammar.y":777  */
               { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 206: /* exp: new_exp  */
  if (yyn == 206)
    /* "VondaGrammar.y":778  */
            { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;



/* "VondaGrammar.java":2547  */

        default: break;
      }

    yySymbolPrint("-> $$ =", SymbolKind.get(yyr1_[yyn]), yyval, yyloc);

    yystack.pop(yylen);
    yylen = 0;
    /* Shift the result of the reduction.  */
    int yystate = yyLRGotoState(yystack.stateAt(0), yyr1_[yyn]);
    yystack.push(yystate, yyval, yyloc);
    return YYNEWSTATE;
  }


  /*--------------------------------.
  | Print this symbol on YYOUTPUT.  |
  `--------------------------------*/

  private void yySymbolPrint(String s, SymbolKind yykind,
                             Object yyvalue, Location yylocation) {
      if (0 < yydebug) {
          yycdebug(s
                   + (yykind.getCode() < YYNTOKENS_ ? " token " : " nterm ")
                   + yykind.getName() + " ("
                   + yylocation + ": "
                   + (yyvalue == null ? "(null)" : yyvalue.toString()) + ")");
      }
  }


  /**
   * Parse input from the scanner that was specified at object construction
   * time.  Return whether the end of the input was reached successfully.
   *
   * @return <tt>true</tt> if the parsing succeeds.  Note that this does not
   *          imply that there were no syntax errors.
   */
  public boolean parse() throws java.io.IOException

  {
    /* @$.  */
    Location yyloc;


    /* Lookahead token kind.  */
    int yychar = YYEMPTY_;
    /* Lookahead symbol kind.  */
    SymbolKind yytoken = null;

    /* State.  */
    int yyn = 0;
    int yylen = 0;
    int yystate = 0;
    YYStack yystack = new YYStack ();
    int label = YYNEWSTATE;


    /* The location where the error started.  */
    Location yyerrloc = null;

    /* Location. */
    Location yylloc = new Location (null, null);

    /* Semantic value of the lookahead.  */
    Object yylval = null;



    yycdebug ("Starting parse");
    yyerrstatus_ = 0;
    yynerrs = 0;

    /* Initialize the stack.  */
    yystack.push (yystate, yylval, yylloc);



    for (;;)
      switch (label)
      {
        /* New state.  Unlike in the C/C++ skeletons, the state is already
           pushed when we come here.  */
      case YYNEWSTATE:
        yycdebug ("Entering state " + yystate);
        if (0 < yydebug)
          yystack.print (yyDebugStream);

        /* Accept?  */
        if (yystate == YYFINAL_)
          return true;

        /* Take a decision.  First try without lookahead.  */
        yyn = yypact_[yystate];
        if (yyPactValueIsDefault (yyn))
          {
            label = YYDEFAULT;
            break;
          }

        /* Read a lookahead token.  */
        if (yychar == YYEMPTY_)
          {

            yycdebug ("Reading a token");
            yychar = yylexer.yylex ();
            yylval = yylexer.getLVal();
            yylloc = new Location(yylexer.getStartPos(),
                                          yylexer.getEndPos());

          }

        /* Convert token to internal form.  */
        yytoken = yytranslate_ (yychar);
        yySymbolPrint("Next token is", yytoken,
                      yylval, yylloc);

        if (yytoken == SymbolKind.S_YYerror)
          {
            // The scanner already issued an error message, process directly
            // to error recovery.  But do not keep the error token as
            // lookahead, it is too special and may lead us to an endless
            // loop in error recovery. */
            yychar = Lexer.YYUNDEF;
            yytoken = SymbolKind.S_YYUNDEF;
            yyerrloc = yylloc;
            label = YYERRLAB1;
          }
        else
          {
            /* If the proper action on seeing token YYTOKEN is to reduce or to
               detect an error, take that action.  */
            yyn += yytoken.getCode();
            if (yyn < 0 || YYLAST_ < yyn || yycheck_[yyn] != yytoken.getCode()) {
              label = YYDEFAULT;
            }

            /* <= 0 means reduce or error.  */
            else if ((yyn = yytable_[yyn]) <= 0)
              {
                if (yyTableValueIsError(yyn)) {
                  label = YYERRLAB;
                } else {
                  yyn = -yyn;
                  label = YYREDUCE;
                }
              }

            else
              {
                /* Shift the lookahead token.  */
                yySymbolPrint("Shifting", yytoken,
                              yylval, yylloc);

                /* Discard the token being shifted.  */
                yychar = YYEMPTY_;

                /* Count tokens shifted since error; after three, turn off error
                   status.  */
                if (yyerrstatus_ > 0)
                  --yyerrstatus_;

                yystate = yyn;
                yystack.push(yystate, yylval, yylloc);
                label = YYNEWSTATE;
              }
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
        label = yyaction(yyn, yystack, yylen);
        yystate = yystack.stateAt(0);
        break;

      /*------------------------------------.
      | yyerrlab -- here on detecting error |
      `------------------------------------*/
      case YYERRLAB:
        /* If not already recovering from an error, report this error.  */
        if (yyerrstatus_ == 0)
          {
            ++yynerrs;
            if (yychar == YYEMPTY_)
              yytoken = null;
            yyreportSyntaxError(new Context(this, yystack, yytoken, yylloc));
          }

        yyerrloc = yylloc;
        if (yyerrstatus_ == 3)
          {
            /* If just tried and failed to reuse lookahead token after an
               error, discard it.  */

            if (yychar <= Lexer.YYEOF)
              {
                /* Return failure if at end of input.  */
                if (yychar == Lexer.YYEOF)
                  return false;
              }
            else
              yychar = YYEMPTY_;
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
        yystate = yystack.stateAt(0);
        label = YYERRLAB1;
        break;

      /*-------------------------------------------------------------.
      | yyerrlab1 -- common code for both syntax error and YYERROR.  |
      `-------------------------------------------------------------*/
      case YYERRLAB1:
        yyerrstatus_ = 3;       /* Each real token shifted decrements this.  */

        // Pop stack until we find a state that shifts the error token.
        for (;;)
          {
            yyn = yypact_[yystate];
            if (!yyPactValueIsDefault (yyn))
              {
                yyn += SymbolKind.S_YYerror.getCode();
                if (0 <= yyn && yyn <= YYLAST_
                    && yycheck_[yyn] == SymbolKind.S_YYerror.getCode())
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
            yystate = yystack.stateAt(0);
            if (0 < yydebug)
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
        yySymbolPrint("Shifting", SymbolKind.get(yystos_[yyn]),
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




  /**
   * Information needed to get the list of expected tokens and to forge
   * a syntax error diagnostic.
   */
  public static final class Context {
    Context(VondaGrammar parser, YYStack stack, SymbolKind token, Location loc) {
      yyparser = parser;
      yystack = stack;
      yytoken = token;
      yylocation = loc;
    }

    private VondaGrammar yyparser;
    private YYStack yystack;


    /**
     * The symbol kind of the lookahead token.
     */
    public final SymbolKind getToken() {
      return yytoken;
    }

    private SymbolKind yytoken;

    /**
     * The location of the lookahead.
     */
    public final Location getLocation() {
      return yylocation;
    }

    private Location yylocation;
    static final int NTOKENS = VondaGrammar.YYNTOKENS_;

    /**
     * Put in YYARG at most YYARGN of the expected tokens given the
     * current YYCTX, and return the number of tokens stored in YYARG.  If
     * YYARG is null, return the number of expected tokens (guaranteed to
     * be less than YYNTOKENS).
     */
    int getExpectedTokens(SymbolKind yyarg[], int yyargn) {
      return getExpectedTokens (yyarg, 0, yyargn);
    }

    int getExpectedTokens(SymbolKind yyarg[], int yyoffset, int yyargn) {
      int yycount = yyoffset;
      int yyn = yypact_[this.yystack.stateAt(0)];
      if (!yyPactValueIsDefault(yyn))
        {
          /* Start YYX at -YYN if negative to avoid negative
             indexes in YYCHECK.  In other words, skip the first
             -YYN actions for this state because they are default
             actions.  */
          int yyxbegin = yyn < 0 ? -yyn : 0;
          /* Stay within bounds of both yycheck and yytname.  */
          int yychecklim = YYLAST_ - yyn + 1;
          int yyxend = yychecklim < NTOKENS ? yychecklim : NTOKENS;
          for (int yyx = yyxbegin; yyx < yyxend; ++yyx)
            if (yycheck_[yyx + yyn] == yyx && yyx != SymbolKind.S_YYerror.getCode()
                && !yyTableValueIsError(yytable_[yyx + yyn]))
              {
                if (yyarg == null)
                  yycount += 1;
                else if (yycount == yyargn)
                  return 0; // FIXME: this is incorrect.
                else
                  yyarg[yycount++] = SymbolKind.get(yyx);
              }
        }
      if (yyarg != null && yycount == yyoffset && yyoffset < yyargn)
        yyarg[yycount] = null;
      return yycount - yyoffset;
    }
  }




  private int yysyntaxErrorArguments(Context yyctx, SymbolKind[] yyarg, int yyargn) {
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
    int yycount = 0;
    if (yyctx.getToken() != null)
      {
        if (yyarg != null)
          yyarg[yycount] = yyctx.getToken();
        yycount += 1;
        yycount += yyctx.getExpectedTokens(yyarg, 1, yyargn);
      }
    return yycount;
  }


  /**
   * Build and emit a "syntax error" message in a user-defined way.
   *
   * @param ctx  The context of the error.
   */
  private void yyreportSyntaxError(Context yyctx) {
      if (yyErrorVerbose) {
          final int argmax = 5;
          SymbolKind[] yyarg = new SymbolKind[argmax];
          int yycount = yysyntaxErrorArguments(yyctx, yyarg, argmax);
          String[] yystr = new String[yycount];
          for (int yyi = 0; yyi < yycount; ++yyi) {
              yystr[yyi] = yyarg[yyi].getName();
          }
          String yyformat;
          switch (yycount) {
              default:
              case 0: yyformat = "syntax error"; break;
              case 1: yyformat = "syntax error, unexpected {0}"; break;
              case 2: yyformat = "syntax error, unexpected {0}, expecting {1}"; break;
              case 3: yyformat = "syntax error, unexpected {0}, expecting {1} or {2}"; break;
              case 4: yyformat = "syntax error, unexpected {0}, expecting {1} or {2} or {3}"; break;
              case 5: yyformat = "syntax error, unexpected {0}, expecting {1} or {2} or {3} or {4}"; break;
          }
          yyerror(yyctx.yylocation, new MessageFormat(yyformat).format(yystr));
      } else {
          yyerror(yyctx.yylocation, "syntax error");
      }
  }

  /**
   * Whether the given <code>yypact_</code> value indicates a defaulted state.
   * @param yyvalue   the value to check
   */
  private static boolean yyPactValueIsDefault(int yyvalue) {
    return yyvalue == yypact_ninf_;
  }

  /**
   * Whether the given <code>yytable_</code>
   * value indicates a syntax error.
   * @param yyvalue the value to check
   */
  private static boolean yyTableValueIsError(int yyvalue) {
    return yyvalue == yytable_ninf_;
  }

  private static final short yypact_ninf_ = -369;
  private static final short yytable_ninf_ = -161;

/* YYPACT[STATE-NUM] -- Index in YYTABLE of the portion describing
   STATE-NUM.  */
  private static final short[] yypact_ = yypact_init();
  private static final short[] yypact_init()
  {
    return new short[]
    {
     861,    35,     8,    50,   263,    66,    16,  1037,    77,    86,
      89,   111,   111,  -369,   149,  -369,  -369,   508,   158,   173,
     176,   170,   178,   195,  -369,  -369,    68,  -369,   991,  1319,
     206,   247,   217,  -369,    20,   934,   934,   934,  -369,  -369,
    -369,  -369,  -369,  -369,  -369,  -369,  -369,  -369,   934,   934,
     934,  -369,   788,   209,   195,   221,  -369,  -369,    31,   235,
     169,  -369,   244,  -369,  -369,  -369,   243,   250,   254,   258,
    -369,  -369,  -369,   268,  -369,   124,   284,   -39,   286,    15,
    1352,  -369,   139,   165,  1352,   295,  -369,  1484,  1484,   238,
    -369,  1385,  1517,  1517,  1484,  1484,   195,   -19,   312,   289,
     293,   288,   203,   146,   211,    87,  -369,  -369,  -369,   241,
    -369,   195,   221,   300,  -369,  -369,   300,  -369,   313,  1352,
    1352,  1352,   195,   -21,   307,   195,   314,   150,   316,   122,
    -369,  1352,  1352,   317,   318,   349,   522,   588,   719,   206,
    -369,  -369,  -369,  1037,   319,  1385,   315,   -38,   311,  -369,
    -369,  -369,  1352,   322,  -369,   206,   934,   934,  -369,  -369,
    -369,  -369,  -369,  -369,  -369,  -369,  -369,    19,  -369,  1352,
    1352,  1352,  -369,  -369,  1352,  1352,   323,   324,  -369,  -369,
    -369,  -369,  -369,  -369,  -369,   325,    71,  -369,   320,   328,
      93,   333,   100,  1352,  1051,   335,   329,  -369,   339,  -369,
     332,   183,    29,  -369,  -369,  -369,  -369,  1352,   275,   341,
    -369,  -369,  -369,  -369,  1517,  1352,  1517,  1517,  1517,  1517,
    1517,  1517,  1517,  1517,  1517,  1517,  1517,  1517,  1517,  1517,
    1517,  -369,  -369,  -369,   344,   338,   345,  -369,   206,  -369,
    -369,  -369,   353,  1352,  -369,   195,   362,   363,  -369,  -369,
    -369,  1418,  -369,   358,   359,   360,  -369,   356,  1084,  -369,
     361,   364,  -369,  -369,   367,  -369,   369,   371,   247,   366,
    -369,  -369,  -369,   379,   378,   372,   380,   382,   384,   385,
    -369,  -369,  1352,  -369,  -369,   387,    43,   391,  1352,   390,
     392,  1117,   393,   163,  1037,  -369,   396,  1151,  1185,   206,
    1517,   312,   397,   289,   293,   288,   203,   146,   146,   211,
     211,   211,   211,    87,    87,  -369,  -369,  -369,   396,  1352,
    1037,   398,   399,  -369,  -369,  -369,    57,   400,  -369,   147,
    -369,  1451,  1451,  -369,  -369,   405,   395,   206,  -369,  1517,
     206,  -369,   403,   402,   -31,   406,   413,  -369,  -369,  -369,
    -369,  -369,  -369,   407,  -369,   417,   300,   412,  -369,  1352,
    1037,   414,  1218,  1352,   419,   455,  -369,  -369,   424,   427,
     425,   423,  -369,  1352,  -369,   430,  -369,  1484,  -369,   379,
     454,   431,  -369,  -369,  -369,  1352,  -369,   433,   442,   247,
     435,   206,   200,  -369,  -369,   444,  1037,   436,  -369,  1037,
    1037,   440,   446,  -369,  1037,  -369,  1352,  -369,   445,  -369,
     396,   433,  -369,  1252,   379,  -369,  -369,   182,   447,  -369,
     456,  -369,  -369,  -369,  -369,  -369,  1037,  -369,  -369,  1037,
    1037,  -369,   449,  1285,  -369,  -369,  -369,  -369,  -369,   379,
     247,   451,  -369,  -369,  -369,  -369,  -369,   452,   458,   403,
    -369,   200,  -369,  -369
    };
  }

/* YYDEFACT[STATE-NUM] -- Default reduction number in state STATE-NUM.
   Performed when YYTABLE does not specify something else to do.  Zero
   means the default is an error.  */
  private static final short[] yydefact_ = yydefact_init();
  private static final short[] yydefact_init()
  {
    return new short[]
    {
      12,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,    15,     0,    14,    13,     0,     0,     0,
       0,     0,     0,   169,   170,   172,   115,   171,     0,     0,
       0,     0,     0,     3,     0,    12,    12,    12,    20,    32,
      34,    37,    38,    39,    35,    36,    40,    41,    12,    12,
      12,    33,    12,     0,     0,     0,   163,   164,   165,     0,
     166,   168,     0,    52,    54,    55,     0,     0,     0,     0,
      56,    79,    42,     0,    43,   167,     0,   115,     0,     0,
       0,    18,     0,     0,     0,     0,   157,     0,     0,   160,
      50,     0,     0,     0,     0,     0,   167,   176,   120,   122,
     123,   125,   127,   129,   132,   137,   140,   144,   150,   151,
     154,   158,   159,   165,   204,   205,   166,   206,     0,     0,
       0,     0,     0,   160,     0,     0,     0,   160,     0,     0,
     181,     0,     0,     0,     0,     0,     0,     0,     0,     0,
     179,    46,    44,    47,     0,     0,     0,   115,     0,   200,
     201,   199,     0,     0,     1,     0,    12,    12,     2,     8,
       7,    10,    11,     5,    31,     6,   182,     0,   180,     0,
       0,     0,   178,    21,     0,     0,     0,     0,    22,   177,
      53,    75,    76,    77,    78,     0,     0,    83,     0,     0,
       0,     0,   115,     0,     0,     0,     0,    16,     0,    17,
       0,   188,   160,   147,   165,   166,   146,     0,   160,     0,
     148,   149,   152,   153,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,   156,   155,    51,     0,     0,     0,    24,     0,    28,
      23,    27,   185,     0,   186,   184,     0,     0,    26,    25,
      49,    97,   108,     0,   111,   110,   114,     0,     0,    87,
     117,     0,    48,    45,     0,   162,     0,     0,     0,     0,
       9,     4,    84,    97,     0,     0,     0,     0,     0,     0,
      30,    29,     0,    80,    85,     0,   115,     0,     0,     0,
       0,     0,     0,     0,     0,    19,     0,     0,     0,     0,
       0,   119,     0,   121,   124,   126,   128,   130,   131,   135,
     136,   133,   134,   138,   139,   141,   142,   143,     0,     0,
       0,     0,     0,   183,   103,   102,   160,     0,    96,     0,
     109,     0,     0,   173,    88,     0,    90,     0,   116,     0,
       0,   198,   203,     0,    98,     0,     0,    81,   174,   105,
     104,   107,   106,     0,    82,     0,     0,     0,    68,     0,
       0,     0,     0,     0,     0,    57,    72,   189,     0,     0,
       0,     0,   145,     0,    74,     0,    59,     0,   187,     0,
       0,    99,   113,   112,    89,     0,   118,   144,     0,     0,
       0,     0,     0,    60,    70,     0,     0,     0,    64,     0,
       0,     0,     0,    69,     0,   190,     0,   191,     0,   175,
       0,     0,   100,     0,     0,    91,   161,     0,     0,   197,
       0,    95,    94,    93,    71,    66,     0,    62,    63,     0,
       0,    58,     0,     0,    73,   196,   195,   101,    86,    97,
       0,     0,    65,    61,    67,   192,   193,     0,     0,   203,
     194,     0,   202,    92
    };
  }

/* YYPGOTO[NTERM-NUM].  */
  private static final short[] yypgoto_ = yypgoto_init();
  private static final short[] yypgoto_init()
  {
    return new short[]
    {
    -369,  -369,   123,  -369,  -369,  -369,   496,    33,   106,  -369,
    -275,   368,  -369,  -369,   374,  -369,  -369,  -369,  -369,  -369,
    -369,  -369,     5,  -369,   327,  -290,   480,    64,  -270,  -368,
    -369,   347,    -1,     0,  -293,  -369,   302,   304,   306,   308,
     309,   113,   119,   125,   -64,   -70,  -369,  -369,  -369,   192,
     246,  -369,  -369,    92,  -369,   422,    38,   -22,  -369,  -369,
    -369,  -369,  -266,    78,   559
    };
  }

/* YYDEFGOTO[NTERM-NUM].  */
  private static final short[] yydefgoto_ = yydefgoto_init();
  private static final short[] yydefgoto_init()
  {
    return new short[]
    {
       0,    32,    33,    34,    35,    36,    82,    72,   142,   143,
      38,   144,    39,    40,    41,    42,    43,   194,    44,    45,
      46,    47,    74,    49,   140,   335,    50,   423,   327,   328,
      51,    96,   253,    76,   261,    97,    98,    99,   100,   101,
     102,   103,   104,   105,   106,   107,   108,   109,   110,   111,
     112,    56,    57,   113,   114,   115,   116,   166,   245,   117,
     254,    61,   153,   390,   146
    };
  }

/* YYTABLE[YYPACT[STATE-NUM]] -- What to do in state STATE-NUM.  If
   positive, shift that token.  If negative, reduce the rule whose
   number is the opposite.  If YYTABLE_NINF, syntax error.  */
  private static final short[] yytable_ = yytable_init();
  private static final short[] yytable_init()
  {
    return new short[]
    {
      53,   130,   342,   345,   187,    48,   371,   368,    78,   214,
    -115,   412,   188,   188,   138,   139,   139,   203,   206,   379,
     188,   366,   237,   139,   212,   213,   191,   136,   210,   211,
     148,     8,   168,    37,    53,    53,    53,    53,    60,   156,
      48,    48,    48,   374,   386,    60,   437,   215,    53,    53,
      53,    64,    53,    48,    48,    48,   192,    48,   193,   126,
     128,   147,   272,    71,   170,   171,    60,   273,    37,    37,
      37,   155,   138,    60,    60,    60,    62,   136,    63,   195,
     207,    37,    37,    37,   138,    37,    60,    60,    60,   168,
      60,   209,    58,    65,   188,   415,   138,   139,  -115,    58,
     130,   131,   132,   133,   134,   136,   -98,   379,   137,    70,
     138,   139,  -160,    73,   272,   135,   136,   422,    77,   137,
      58,   138,   139,   418,   138,   205,   205,    58,    58,    58,
     205,   205,   205,   205,    79,   434,   284,    80,   435,   260,
      58,    58,    58,   447,    58,   264,   138,   288,   228,   229,
     230,   188,    81,   138,   139,   269,    53,    53,   158,   159,
     160,    48,    48,   242,   315,   316,   317,   164,   129,   448,
     243,   161,   162,   163,   449,   165,   422,   222,   223,   204,
     204,    60,   197,   198,   204,   204,   204,   204,   381,    37,
      37,   287,    54,   240,    60,    60,   300,    84,   136,    54,
     224,   225,   174,   175,   176,   177,   119,   122,   199,   198,
     363,   123,   178,    54,    54,   122,   138,   154,   124,   127,
      54,   120,   138,   323,   121,   438,   124,    54,    54,    54,
     439,   297,   220,   221,   298,    58,   372,   299,   321,   129,
      54,    54,    54,   421,    54,    28,    55,   147,    58,    58,
     167,   329,   205,    55,   205,   205,   205,   205,   205,   205,
     205,   205,   205,   205,   205,   205,   205,   205,   205,   387,
     226,   227,   169,   346,    55,   372,   231,   232,   173,   270,
     271,    55,    55,    55,   149,   150,   136,   180,   151,   207,
     181,   138,   152,   185,    55,    55,    55,   182,    55,   260,
      66,   183,    67,    68,    69,   184,   204,   411,   204,   204,
     204,   204,   204,   204,   204,   204,   204,   204,   204,   204,
     204,   204,   204,   136,  -115,   186,   137,   190,   138,   139,
     382,   383,    60,   307,   308,    54,   201,   260,   205,   216,
     388,   309,   310,   311,   312,   217,   219,    52,    54,    54,
     218,   313,   314,   138,    75,   238,   233,   239,    60,   241,
     248,   249,    10,   266,   265,   263,   280,   281,   125,   125,
     268,   283,   256,   282,   286,    75,   293,   205,   294,   346,
     295,   296,    52,    52,    52,   172,    58,   179,   319,    55,
     300,   420,   204,   318,   320,    52,    52,    52,    60,    52,
     365,   136,    55,    55,   189,   324,   325,   330,   333,   331,
     332,   337,    58,   340,   346,   205,   339,   341,   343,   338,
     344,   347,    59,   349,   348,   350,   376,   351,   352,    59,
     354,   204,   356,   358,    60,   359,   362,    60,    60,   346,
     172,    28,    60,   179,   373,   385,   391,   377,   378,   380,
      59,   384,    58,   389,   381,   392,   393,    59,    59,    59,
     394,   396,   403,   399,    60,   404,   398,    60,    60,   204,
      59,    59,    59,   405,    59,   406,   244,   407,   408,   410,
     413,   414,   416,   417,   419,   426,    54,   424,    58,   429,
      75,    58,    58,   433,   274,   430,    58,   441,   445,   439,
     440,   450,   425,    52,    52,   427,   428,   451,    83,   250,
     431,   262,    54,   274,   157,   453,   301,   285,    58,   289,
     303,    58,    58,   304,    85,    86,   305,   452,   306,     0,
       0,     0,   442,     0,     0,   443,   444,     0,    85,    86,
      55,     0,     0,    87,    88,    23,     0,    24,    25,    89,
      27,    90,    54,     0,     0,     0,    91,    87,    88,    23,
       0,    24,    25,    89,    27,    59,    55,    92,    93,     0,
     251,   252,    94,    95,     0,    31,   118,     0,    59,    59,
       0,    92,    93,     0,     0,     0,    94,    95,    54,    31,
       0,    54,    54,     0,     0,     0,    54,     0,     0,     0,
       0,     0,     0,     0,    85,    86,    55,     0,     0,     0,
       0,     0,     0,   355,     0,     0,     0,     0,    54,     0,
     364,    54,    54,    87,    88,    23,     0,    24,    25,    89,
      27,     0,     0,     0,     0,     0,    91,     0,     0,   196,
     256,    75,    55,   200,     0,    55,    55,    92,    93,     0,
      55,     0,    94,    95,     0,    31,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,    75,     0,     0,
       0,     0,    55,     0,     0,    55,    55,     0,   234,   235,
     236,     0,     0,   395,     0,     0,     0,     0,     0,     0,
     246,   247,     0,     0,     0,   255,   257,   259,     0,     0,
       0,     0,     0,     0,     0,     0,     0,    75,     0,     0,
       0,   267,     0,     0,     0,     0,    59,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,   275,   276,
     277,     0,     0,   278,   279,    85,    86,     0,     0,     0,
       0,     0,    59,    75,     0,     0,    75,    75,     0,     0,
       0,    75,   290,   292,    87,    88,    23,     0,    24,    25,
      89,    27,     0,     0,   258,     0,   257,    91,     0,     0,
       0,     0,     0,    75,   302,     0,    75,    75,    92,    93,
       0,     0,    59,    94,    95,     0,    31,     0,     0,     0,
       0,     1,     2,     3,     4,     5,     6,     7,     0,     8,
       9,    10,   322,    12,     0,     0,    13,    14,    15,    16,
      17,    18,    19,    20,     0,     0,     0,   336,    59,     0,
       0,    59,    59,    21,    22,    23,    59,    24,    25,    26,
      27,   164,   129,    28,     0,     0,    29,     0,     0,    30,
       0,   353,     0,     0,     0,     0,     0,   357,    59,     0,
     361,    59,    59,     0,     0,    31,   336,   370,     0,     0,
       0,     0,     0,     0,     1,     2,     3,     4,     5,     6,
       7,     0,     8,     9,    10,    11,    12,     0,   375,    13,
      14,    15,    16,    17,    18,    19,    20,     0,     0,     0,
     255,   255,     0,     0,     0,     0,    21,    22,    23,     0,
      24,    25,    26,    27,     0,     0,    28,     0,     0,    29,
       0,     0,    30,     0,     0,     0,     0,     0,   397,     0,
       0,   401,   402,     0,     0,     0,     0,     0,    31,     0,
       0,     0,   409,     0,     0,     0,     0,     1,     2,     3,
       4,     5,     6,     7,   336,     8,     9,    10,     0,    12,
       0,     0,    13,    14,    15,    16,    17,    18,    19,    20,
       0,     0,     0,     0,     0,   432,     0,     0,     0,    21,
      22,    23,   436,    24,    25,    26,    27,     0,     0,    28,
       0,     0,    29,     0,     0,    30,     0,     0,     0,     0,
       0,     0,   336,     0,     1,     2,     3,     4,     5,     6,
       7,    31,     8,     9,    10,     0,     0,     0,     0,     0,
      14,     0,     0,    17,    18,    19,    20,     0,     0,     0,
       0,     0,     0,     0,     0,     0,    21,    22,    23,     0,
      24,    25,    26,    27,     0,     0,    28,   141,     0,    29,
       1,     2,     3,     4,     5,     6,     7,     0,     8,     9,
      10,     0,     0,     0,     0,     0,    14,     0,    31,    17,
      18,    19,    20,     0,     0,     0,     0,    85,    86,     0,
       0,     0,    21,    22,    23,     0,    24,    25,    26,    27,
       0,     0,    28,     0,     0,    29,    87,    88,    23,     0,
      24,    25,    89,    27,   291,     0,     0,     0,     0,    91,
      85,    86,     0,     0,    31,     0,     0,     0,     0,     0,
      92,    93,     0,     0,     0,    94,    95,     0,    31,    87,
      88,    23,     0,    24,    25,    89,    27,     0,     0,     0,
     334,     0,    91,    85,    86,     0,     0,     0,     0,     0,
       0,     0,     0,    92,    93,     0,     0,     0,    94,    95,
       0,    31,    87,    88,    23,     0,    24,    25,    89,    27,
       0,     0,     0,     0,     0,    91,   360,    85,    86,     0,
       0,     0,     0,     0,     0,     0,    92,    93,     0,     0,
       0,    94,    95,     0,    31,     0,    87,    88,    23,     0,
      24,    25,    89,    27,     0,     0,     0,     0,     0,    91,
     367,    85,    86,     0,     0,     0,     0,     0,     0,     0,
      92,    93,     0,     0,     0,    94,    95,     0,    31,     0,
      87,    88,    23,     0,    24,    25,    89,    27,     0,     0,
       0,     0,     0,    91,    85,    86,     0,   369,     0,     0,
       0,     0,     0,     0,    92,    93,     0,     0,     0,    94,
      95,     0,    31,    87,    88,    23,     0,    24,    25,    89,
      27,     0,     0,     0,     0,     0,    91,   400,    85,    86,
       0,     0,     0,     0,     0,     0,     0,    92,    93,     0,
       0,     0,    94,    95,     0,    31,     0,    87,    88,    23,
       0,    24,    25,    89,    27,     0,     0,    28,     0,     0,
      91,    85,    86,     0,     0,     0,     0,     0,     0,     0,
       0,    92,    93,     0,     0,     0,    94,    95,     0,    31,
      87,    88,    23,     0,    24,    25,    89,    27,     0,     0,
       0,     0,     0,    91,   446,    85,    86,     0,     0,     0,
       0,     0,     0,     0,    92,    93,     0,     0,     0,    94,
      95,     0,    31,     0,    87,    88,    23,     0,    24,    25,
      89,    27,     0,     0,     0,     0,     0,   145,    85,    86,
       0,     0,     0,     0,     0,     0,     0,     0,    92,    93,
       0,     0,     0,    94,    95,     0,    31,    87,    88,    23,
       0,    24,    25,    89,    27,     0,     0,     0,     0,     0,
      91,    85,    86,     0,     0,     0,     0,     0,     0,     0,
       0,    92,    93,     0,     0,     0,    94,    95,     0,    31,
      87,    88,    23,     0,    24,    25,   208,    27,     0,     0,
       0,     0,     0,   145,    85,    86,     0,     0,     0,     0,
       0,     0,     0,     0,    92,    93,     0,     0,     0,    94,
      95,     0,    31,    87,    88,    23,     0,    24,    25,   326,
      27,     0,     0,     0,     0,     0,   145,    85,    86,     0,
       0,     0,     0,     0,     0,     0,     0,    92,    93,     0,
       0,     0,    94,    95,     0,    31,    87,    88,    23,     0,
      24,    25,    89,    27,     0,     0,     0,     0,     0,   251,
       0,    86,     0,     0,     0,     0,     0,     0,     0,     0,
      92,    93,     0,     0,     0,    94,    95,     0,    31,    87,
      88,    23,     0,    24,    25,   202,    27,     0,     0,     0,
       0,     0,    29,     0,    86,     0,     0,     0,     0,     0,
       0,     0,     0,    92,    93,     0,     0,     0,    94,    95,
       0,    31,    87,    88,    23,     0,    24,    25,   202,    27,
       0,     0,     0,     0,     0,    91,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,    92,    93,     0,     0,
       0,    94,    95,     0,    31
    };
  }

private static final short[] yycheck_ = yycheck_init();
  private static final short[] yycheck_init()
  {
    return new short[]
    {
       0,    23,   268,   273,    43,     0,   299,   297,     8,    28,
      41,   379,    51,    51,    53,    54,    54,    87,    88,    50,
      51,   296,    43,    54,    94,    95,    11,    48,    92,    93,
      30,    11,    54,     0,    34,    35,    36,    37,     0,    34,
      35,    36,    37,   318,   337,     7,   414,    66,    48,    49,
      50,    43,    52,    48,    49,    50,    41,    52,    43,    21,
      22,    41,    43,    47,    33,    34,    28,    48,    35,    36,
      37,    51,    53,    35,    36,    37,    41,    48,    43,    79,
      51,    48,    49,    50,    53,    52,    48,    49,    50,   111,
      52,    91,     0,    43,    51,   385,    53,    54,    41,     7,
     122,    33,    34,    35,    36,    48,    49,    50,    51,    43,
      53,    54,    44,     7,    43,    47,    48,   392,    41,    51,
      28,    53,    54,   389,    53,    87,    88,    35,    36,    37,
      92,    93,    94,    95,    48,   410,    43,    48,   413,   139,
      48,    49,    50,   433,    52,   145,    53,    47,    61,    62,
      63,    51,    41,    53,    54,   155,   156,   157,    35,    36,
      37,   156,   157,    41,   228,   229,   230,    43,    44,   439,
      48,    48,    49,    50,   440,    52,   451,    31,    32,    87,
      88,   143,    43,    44,    92,    93,    94,    95,    41,   156,
     157,   191,     0,    43,   156,   157,    49,    48,    48,     7,
      54,    55,    33,    34,    35,    36,    48,    37,    43,    44,
      47,    41,    43,    21,    22,    37,    53,     0,    48,    41,
      28,    48,    53,   245,    48,    43,    48,    35,    36,    37,
      48,    48,    29,    30,    51,   143,   300,    54,   238,    44,
      48,    49,    50,    43,    52,    45,     0,    41,   156,   157,
      41,   251,   214,     7,   216,   217,   218,   219,   220,   221,
     222,   223,   224,   225,   226,   227,   228,   229,   230,   339,
      59,    60,    51,   273,    28,   339,    35,    36,    43,   156,
     157,    35,    36,    37,    37,    38,    48,    43,    41,    51,
      47,    53,    45,    25,    48,    49,    50,    47,    52,   299,
      37,    47,    39,    40,    41,    47,   214,   377,   216,   217,
     218,   219,   220,   221,   222,   223,   224,   225,   226,   227,
     228,   229,   230,    48,    49,    41,    51,    41,    53,    54,
     331,   332,   294,   220,   221,   143,    41,   337,   300,    27,
     340,   222,   223,   224,   225,    56,    58,     0,   156,   157,
      57,   226,   227,    53,     7,    48,    43,    43,   320,    43,
      43,    43,    13,    52,    49,    46,    43,    43,    21,    22,
      48,    43,    52,    48,    41,    28,    41,   339,    49,   379,
      41,    49,    35,    36,    37,    58,   294,    60,    50,   143,
      49,   391,   300,    49,    49,    48,    49,    50,   360,    52,
     294,    48,   156,   157,    77,    43,    43,    49,    52,    50,
      50,    50,   320,    44,   414,   377,    49,    46,    52,    55,
      41,    43,     0,    43,    52,    43,   320,    43,    43,     7,
      43,   339,    41,    43,   396,    43,    43,   399,   400,   439,
     113,    45,   404,   116,    47,    50,    44,    49,    49,    49,
      28,    46,   360,    50,    41,    49,    49,    35,    36,    37,
      43,    49,    43,    49,   426,    10,   360,   429,   430,   377,
      48,    49,    50,    49,    52,    48,   129,    52,    55,    49,
      26,    50,    49,    41,    49,    49,   294,    43,   396,    49,
     143,   399,   400,    48,   167,    49,   404,    41,    49,    48,
      53,    49,   396,   156,   157,   399,   400,    49,    12,   135,
     404,   143,   320,   186,    34,   451,   214,   190,   426,   192,
     216,   429,   430,   217,    16,    17,   218,   449,   219,    -1,
      -1,    -1,   426,    -1,    -1,   429,   430,    -1,    16,    17,
     294,    -1,    -1,    35,    36,    37,    -1,    39,    40,    41,
      42,    43,   360,    -1,    -1,    -1,    48,    35,    36,    37,
      -1,    39,    40,    41,    42,   143,   320,    59,    60,    -1,
      48,    49,    64,    65,    -1,    67,    17,    -1,   156,   157,
      -1,    59,    60,    -1,    -1,    -1,    64,    65,   396,    67,
      -1,   399,   400,    -1,    -1,    -1,   404,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    16,    17,   360,    -1,    -1,    -1,
      -1,    -1,    -1,   286,    -1,    -1,    -1,    -1,   426,    -1,
     293,   429,   430,    35,    36,    37,    -1,    39,    40,    41,
      42,    -1,    -1,    -1,    -1,    -1,    48,    -1,    -1,    80,
      52,   294,   396,    84,    -1,   399,   400,    59,    60,    -1,
     404,    -1,    64,    65,    -1,    67,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,   320,    -1,    -1,
      -1,    -1,   426,    -1,    -1,   429,   430,    -1,   119,   120,
     121,    -1,    -1,   356,    -1,    -1,    -1,    -1,    -1,    -1,
     131,   132,    -1,    -1,    -1,   136,   137,   138,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,   360,    -1,    -1,
      -1,   152,    -1,    -1,    -1,    -1,   294,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,   169,   170,
     171,    -1,    -1,   174,   175,    16,    17,    -1,    -1,    -1,
      -1,    -1,   320,   396,    -1,    -1,   399,   400,    -1,    -1,
      -1,   404,   193,   194,    35,    36,    37,    -1,    39,    40,
      41,    42,    -1,    -1,    45,    -1,   207,    48,    -1,    -1,
      -1,    -1,    -1,   426,   215,    -1,   429,   430,    59,    60,
      -1,    -1,   360,    64,    65,    -1,    67,    -1,    -1,    -1,
      -1,     3,     4,     5,     6,     7,     8,     9,    -1,    11,
      12,    13,   243,    15,    -1,    -1,    18,    19,    20,    21,
      22,    23,    24,    25,    -1,    -1,    -1,   258,   396,    -1,
      -1,   399,   400,    35,    36,    37,   404,    39,    40,    41,
      42,    43,    44,    45,    -1,    -1,    48,    -1,    -1,    51,
      -1,   282,    -1,    -1,    -1,    -1,    -1,   288,   426,    -1,
     291,   429,   430,    -1,    -1,    67,   297,   298,    -1,    -1,
      -1,    -1,    -1,    -1,     3,     4,     5,     6,     7,     8,
       9,    -1,    11,    12,    13,    14,    15,    -1,   319,    18,
      19,    20,    21,    22,    23,    24,    25,    -1,    -1,    -1,
     331,   332,    -1,    -1,    -1,    -1,    35,    36,    37,    -1,
      39,    40,    41,    42,    -1,    -1,    45,    -1,    -1,    48,
      -1,    -1,    51,    -1,    -1,    -1,    -1,    -1,   359,    -1,
      -1,   362,   363,    -1,    -1,    -1,    -1,    -1,    67,    -1,
      -1,    -1,   373,    -1,    -1,    -1,    -1,     3,     4,     5,
       6,     7,     8,     9,   385,    11,    12,    13,    -1,    15,
      -1,    -1,    18,    19,    20,    21,    22,    23,    24,    25,
      -1,    -1,    -1,    -1,    -1,   406,    -1,    -1,    -1,    35,
      36,    37,   413,    39,    40,    41,    42,    -1,    -1,    45,
      -1,    -1,    48,    -1,    -1,    51,    -1,    -1,    -1,    -1,
      -1,    -1,   433,    -1,     3,     4,     5,     6,     7,     8,
       9,    67,    11,    12,    13,    -1,    -1,    -1,    -1,    -1,
      19,    -1,    -1,    22,    23,    24,    25,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    35,    36,    37,    -1,
      39,    40,    41,    42,    -1,    -1,    45,    46,    -1,    48,
       3,     4,     5,     6,     7,     8,     9,    -1,    11,    12,
      13,    -1,    -1,    -1,    -1,    -1,    19,    -1,    67,    22,
      23,    24,    25,    -1,    -1,    -1,    -1,    16,    17,    -1,
      -1,    -1,    35,    36,    37,    -1,    39,    40,    41,    42,
      -1,    -1,    45,    -1,    -1,    48,    35,    36,    37,    -1,
      39,    40,    41,    42,    43,    -1,    -1,    -1,    -1,    48,
      16,    17,    -1,    -1,    67,    -1,    -1,    -1,    -1,    -1,
      59,    60,    -1,    -1,    -1,    64,    65,    -1,    67,    35,
      36,    37,    -1,    39,    40,    41,    42,    -1,    -1,    -1,
      46,    -1,    48,    16,    17,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    59,    60,    -1,    -1,    -1,    64,    65,
      -1,    67,    35,    36,    37,    -1,    39,    40,    41,    42,
      -1,    -1,    -1,    -1,    -1,    48,    49,    16,    17,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    59,    60,    -1,    -1,
      -1,    64,    65,    -1,    67,    -1,    35,    36,    37,    -1,
      39,    40,    41,    42,    -1,    -1,    -1,    -1,    -1,    48,
      49,    16,    17,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      59,    60,    -1,    -1,    -1,    64,    65,    -1,    67,    -1,
      35,    36,    37,    -1,    39,    40,    41,    42,    -1,    -1,
      -1,    -1,    -1,    48,    16,    17,    -1,    52,    -1,    -1,
      -1,    -1,    -1,    -1,    59,    60,    -1,    -1,    -1,    64,
      65,    -1,    67,    35,    36,    37,    -1,    39,    40,    41,
      42,    -1,    -1,    -1,    -1,    -1,    48,    49,    16,    17,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    59,    60,    -1,
      -1,    -1,    64,    65,    -1,    67,    -1,    35,    36,    37,
      -1,    39,    40,    41,    42,    -1,    -1,    45,    -1,    -1,
      48,    16,    17,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    59,    60,    -1,    -1,    -1,    64,    65,    -1,    67,
      35,    36,    37,    -1,    39,    40,    41,    42,    -1,    -1,
      -1,    -1,    -1,    48,    49,    16,    17,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    59,    60,    -1,    -1,    -1,    64,
      65,    -1,    67,    -1,    35,    36,    37,    -1,    39,    40,
      41,    42,    -1,    -1,    -1,    -1,    -1,    48,    16,    17,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    59,    60,
      -1,    -1,    -1,    64,    65,    -1,    67,    35,    36,    37,
      -1,    39,    40,    41,    42,    -1,    -1,    -1,    -1,    -1,
      48,    16,    17,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    59,    60,    -1,    -1,    -1,    64,    65,    -1,    67,
      35,    36,    37,    -1,    39,    40,    41,    42,    -1,    -1,
      -1,    -1,    -1,    48,    16,    17,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    59,    60,    -1,    -1,    -1,    64,
      65,    -1,    67,    35,    36,    37,    -1,    39,    40,    41,
      42,    -1,    -1,    -1,    -1,    -1,    48,    16,    17,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    59,    60,    -1,
      -1,    -1,    64,    65,    -1,    67,    35,    36,    37,    -1,
      39,    40,    41,    42,    -1,    -1,    -1,    -1,    -1,    48,
      -1,    17,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      59,    60,    -1,    -1,    -1,    64,    65,    -1,    67,    35,
      36,    37,    -1,    39,    40,    41,    42,    -1,    -1,    -1,
      -1,    -1,    48,    -1,    17,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    59,    60,    -1,    -1,    -1,    64,    65,
      -1,    67,    35,    36,    37,    -1,    39,    40,    41,    42,
      -1,    -1,    -1,    -1,    -1,    48,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    59,    60,    -1,    -1,
      -1,    64,    65,    -1,    67
    };
  }

/* YYSTOS[STATE-NUM] -- The symbol kind of the accessing symbol of
   state STATE-NUM.  */
  private static final short[] yystos_ = yystos_init();
  private static final short[] yystos_init()
  {
    return new short[]
    {
       0,     3,     4,     5,     6,     7,     8,     9,    11,    12,
      13,    14,    15,    18,    19,    20,    21,    22,    23,    24,
      25,    35,    36,    37,    39,    40,    41,    42,    45,    48,
      51,    67,    69,    70,    71,    72,    73,    75,    78,    80,
      81,    82,    83,    84,    86,    87,    88,    89,    90,    91,
      94,    98,    99,   101,   117,   118,   119,   120,   121,   123,
     124,   129,    41,    43,    43,    43,    37,    39,    40,    41,
      43,    47,    75,    76,    90,    99,   101,    41,   101,    48,
      48,    41,    74,    74,    48,    16,    17,    35,    36,    41,
      43,    48,    59,    60,    64,    65,    99,   103,   104,   105,
     106,   107,   108,   109,   110,   111,   112,   113,   114,   115,
     116,   117,   118,   121,   122,   123,   124,   127,   132,    48,
      48,    48,    37,    41,    48,    99,   124,    41,   124,    44,
     125,    33,    34,    35,    36,    47,    48,    51,    53,    54,
      92,    46,    76,    77,    79,    48,   132,    41,   101,    37,
      38,    41,    45,   130,     0,    51,    90,    94,    70,    70,
      70,    70,    70,    70,    43,    70,   125,    41,   125,    51,
      33,    34,    92,    43,    33,    34,    35,    36,    43,    92,
      43,    47,    47,    47,    47,    25,    41,    43,    51,    92,
      41,    11,    41,    43,    85,   101,   132,    43,    44,    43,
     132,    41,    41,   113,   121,   124,   113,    51,    41,   101,
     112,   112,   113,   113,    28,    66,    27,    56,    57,    58,
      29,    30,    31,    32,    54,    55,    59,    60,    61,    62,
      63,    35,    36,    43,   132,   132,   132,    43,    48,    43,
      43,    43,    41,    48,    99,   126,   132,   132,    43,    43,
      82,    48,    49,   100,   128,   132,    52,   132,    45,   132,
     101,   102,    79,    46,   101,    49,    52,   132,    48,   101,
      70,    70,    43,    48,    92,   132,   132,   132,   132,   132,
      43,    43,    48,    43,    43,    92,    41,   101,    47,    92,
     132,    43,   132,    41,    49,    41,    49,    48,    51,    54,
      49,   104,   132,   105,   106,   107,   108,   109,   109,   110,
     110,   110,   110,   111,   111,   112,   112,   112,    49,    50,
      49,   101,   132,   125,    43,    43,    41,    96,    97,   101,
      49,    50,    50,    52,    46,    93,   132,    50,    55,    49,
      44,    46,   130,    52,    41,    96,   101,    43,    52,    43,
      43,    43,    43,   132,    43,    92,    41,   132,    43,    43,
      49,   132,    43,    47,    92,    76,    78,    49,    93,    52,
     132,   102,   112,    47,    78,   132,    76,    49,    49,    50,
      49,    41,   100,   100,    46,    50,   102,   113,   101,    50,
     131,    44,    49,    49,    43,    92,    49,   132,    76,    49,
      49,   132,   132,    43,    10,    49,    48,    52,    55,   132,
      49,   113,    97,    26,    50,    93,    49,    41,   130,    49,
     101,    43,    78,    95,    43,    76,    49,    76,    76,    49,
      49,    76,   132,    48,    78,    78,   132,    97,    43,    48,
      53,    41,    76,    76,    76,    49,    49,    93,    96,   130,
      49,    49,   131,    95
    };
  }

/* YYR1[RULE-NUM] -- Symbol kind of the left-hand side of rule RULE-NUM.  */
  private static final short[] yyr1_ = yyr1_init();
  private static final short[] yyr1_init()
  {
    return new short[]
    {
       0,    68,    69,    69,    70,    70,    70,    70,    70,    70,
      70,    70,    70,    71,    71,    71,    72,    73,    74,    74,
      75,    75,    75,    75,    75,    75,    75,    75,    75,    75,
      75,    75,    75,    75,    75,    75,    75,    75,    75,    75,
      75,    75,    76,    76,    77,    78,    78,    79,    79,    80,
      81,    81,    81,    81,    81,    81,    81,    82,    82,    83,
      83,    84,    84,    84,    84,    84,    84,    84,    85,    85,
      85,    85,    86,    87,    88,    89,    89,    89,    89,    89,
      90,    90,    90,    90,    90,    90,    91,    92,    92,    92,
      93,    93,    94,    94,    95,    95,    96,    96,    97,    97,
      97,    97,    98,    98,    98,    98,    98,    98,    99,    99,
     100,   100,   100,   100,   101,   101,   101,   102,   102,   103,
     103,   104,   104,   105,   105,   106,   106,   107,   107,   108,
     108,   108,   109,   109,   109,   109,   109,   110,   110,   110,
     111,   111,   111,   111,   112,   112,   113,   113,   113,   113,
     113,   114,   114,   114,   115,   115,   115,   116,   116,   116,
     117,   117,   118,   118,   119,   119,   119,   119,   119,   120,
     120,   120,   120,   121,   121,   122,   122,   123,   123,   123,
     124,   124,   124,   125,   125,   126,   126,   126,   127,   127,
     127,   127,   127,   127,   127,   128,   128,   129,   130,   130,
     130,   130,   131,   131,   132,   132,   132
    };
  }

/* YYR2[RULE-NUM] -- Number of symbols on the right-hand side of rule RULE-NUM.  */
  private static final byte[] yyr2_ = yyr2_init();
  private static final byte[] yyr2_init()
  {
    return new byte[]
    {
       0,     2,     2,     1,     3,     2,     2,     2,     2,     3,
       2,     2,     0,     1,     1,     1,     3,     3,     1,     3,
       1,     2,     2,     3,     3,     3,     3,     3,     3,     3,
       3,     2,     1,     1,     1,     1,     1,     1,     1,     1,
       1,     1,     1,     1,     1,     3,     2,     1,     2,     3,
       2,     3,     2,     3,     2,     2,     2,     5,     7,     5,
       6,     8,     7,     7,     6,     8,     7,     8,     3,     4,
       4,     5,     5,     7,     5,     3,     3,     3,     3,     2,
       4,     4,     5,     3,     3,     4,     7,     2,     3,     4,
       1,     3,    10,     6,     1,     1,     1,     0,     1,     2,
       3,     4,     4,     4,     4,     4,     4,     4,     3,     4,
       1,     1,     3,     3,     3,     1,     4,     1,     3,     3,
       1,     3,     1,     1,     3,     1,     3,     1,     3,     1,
       3,     3,     1,     3,     3,     3,     3,     1,     3,     3,
       1,     3,     3,     3,     1,     4,     2,     2,     2,     2,
       1,     1,     2,     2,     1,     2,     2,     1,     1,     1,
       1,     6,     3,     1,     1,     1,     1,     1,     1,     1,
       1,     1,     1,     4,     4,     5,     1,     2,     2,     2,
       2,     2,     2,     3,     2,     1,     1,     3,     2,     4,
       5,     5,     7,     7,     8,     5,     5,     6,     3,     1,
       1,     1,     5,     0,     1,     1,     1
    };
  }



  /* YYRLINE[YYN] -- Source line where rule number YYN was defined.  */
  private static final short[] yyrline_ = yyrline_init();
  private static final short[] yyrline_init()
  {
    return new short[]
    {
       0,   142,   142,   143,   147,   150,   151,   152,   154,   155,
     158,   161,   164,   168,   169,   170,   173,   176,   184,   185,
     189,   190,   191,   192,   196,   200,   204,   208,   211,   214,
     217,   220,   221,   222,   223,   224,   225,   226,   227,   228,
     229,   230,   234,   235,   239,   245,   246,   250,   251,   255,
     259,   260,   261,   262,   263,   264,   265,   269,   270,   276,
     277,   283,   285,   287,   289,   291,   293,   297,   306,   311,
     316,   321,   329,   333,   339,   346,   353,   360,   367,   374,
     383,   388,   393,   398,   401,   404,   410,   417,   418,   421,
     427,   428,   433,   436,   442,   443,   447,   448,   452,   453,
     454,   458,   466,   470,   474,   477,   480,   483,   492,   495,
     501,   502,   503,   504,   508,   512,   513,   517,   518,   522,
     525,   529,   532,   536,   537,   543,   544,   550,   551,   557,
     558,   561,   567,   568,   571,   574,   577,   583,   584,   587,
     593,   594,   597,   600,   606,   607,   611,   614,   617,   618,
     619,   623,   624,   625,   629,   630,   633,   639,   640,   641,
     645,   646,   650,   651,   655,   656,   657,   658,   659,   663,
     664,   665,   666,   670,   674,   678,   679,   685,   686,   687,
     695,   698,   701,   705,   706,   710,   711,   712,   716,   717,
     720,   723,   728,   733,   737,   744,   747,   754,   760,   763,
     764,   765,   769,   772,   776,   777,   778
    };
  }


  // Report on the debug stream that the rule yyrule is going to be reduced.
  private void yyReducePrint (int yyrule, YYStack yystack)
  {
    if (yydebug == 0)
      return;

    int yylno = yyrline_[yyrule];
    int yynrhs = yyr2_[yyrule];
    /* Print the symbols being reduced, and their result.  */
    yycdebug ("Reducing stack by rule " + (yyrule - 1)
              + " (line " + yylno + "):");

    /* The symbols being reduced.  */
    for (int yyi = 0; yyi < yynrhs; yyi++)
      yySymbolPrint("   $" + (yyi + 1) + " =",
                    SymbolKind.get(yystos_[yystack.stateAt(yynrhs - (yyi + 1))]),
                    yystack.valueAt ((yynrhs) - (yyi + 1)),
                    yystack.locationAt ((yynrhs) - (yyi + 1)));
  }

  /* YYTRANSLATE_(TOKEN-NUM) -- Symbol number corresponding to TOKEN-NUM
     as returned by yylex, with out-of-bounds checking.  */
  private static final SymbolKind yytranslate_(int t)
  {
    // Last valid token kind.
    int code_max = 297;
    if (t <= 0)
      return SymbolKind.S_YYEOF;
    else if (t <= code_max)
      return SymbolKind.get(yytranslate_table_[t]);
    else
      return SymbolKind.S_YYUNDEF;
  }
  private static final byte[] yytranslate_table_ = yytranslate_table_init();
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
      54,    53,    55,    66,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,    51,     2,    52,    57,     2,     2,     2,     2,     2,
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


  private static final int YYLAST_ = 1584;
  private static final int YYEMPTY_ = -2;
  private static final int YYFINAL_ = 154;
  private static final int YYNTOKENS_ = 68;

/* Unqualified %code blocks.  */
/* "VondaGrammar.y":60  */

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

/* "VondaGrammar.java":3762  */

}
