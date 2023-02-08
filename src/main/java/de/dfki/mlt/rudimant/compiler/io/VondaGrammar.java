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
    S_STATIC(15),                  /* STATIC  */
    S_INCLUDE(16),                 /* INCLUDE  */
    S_NEW(17),                     /* NEW  */
    S_NULL(18),                    /* NULL  */
    S_PRIVATE(19),                 /* PRIVATE  */
    S_PROPOSE(20),                 /* PROPOSE  */
    S_PROTECTED(21),               /* PROTECTED  */
    S_PUBLIC(22),                  /* PUBLIC  */
    S_RETURN(23),                  /* RETURN  */
    S_SWITCH(24),                  /* SWITCH  */
    S_TIMEOUT(25),                 /* TIMEOUT  */
    S_WHILE(26),                   /* WHILE  */
    S_ARROW(27),                   /* ARROW  */
    S_ANDAND(28),                  /* ANDAND  */
    S_OROR(29),                    /* OROR  */
    S_EQEQ(30),                    /* EQEQ  */
    S_NOTEQ(31),                   /* NOTEQ  */
    S_GTEQ(32),                    /* GTEQ  */
    S_LTEQ(33),                    /* LTEQ  */
    S_MINUSEQ(34),                 /* MINUSEQ  */
    S_PLUSEQ(35),                  /* PLUSEQ  */
    S_MINUSMINUS(36),              /* MINUSMINUS  */
    S_PLUSPLUS(37),                /* PLUSPLUS  */
    S_STRING(38),                  /* STRING  */
    S_WILDCARD(39),                /* WILDCARD  */
    S_INT(40),                     /* INT  */
    S_BOOL_LITERAL(41),            /* BOOL_LITERAL  */
    S_IDENTIFIER(42),              /* IDENTIFIER  */
    S_OTHER_LITERAL(43),           /* OTHER_LITERAL  */
    S_44_(44),                     /* ';'  */
    S_45_(45),                     /* '.'  */
    S_46_(46),                     /* '*'  */
    S_47_(47),                     /* '{'  */
    S_48_(48),                     /* '}'  */
    S_49_(49),                     /* ':'  */
    S_50_(50),                     /* '('  */
    S_51_(51),                     /* ')'  */
    S_52_(52),                     /* ','  */
    S_53_(53),                     /* '#'  */
    S_54_(54),                     /* '='  */
    S_55_(55),                     /* '['  */
    S_56_(56),                     /* ']'  */
    S_57_(57),                     /* '<'  */
    S_58_(58),                     /* '>'  */
    S_59_(59),                     /* '|'  */
    S_60_(60),                     /* '^'  */
    S_61_(61),                     /* '&'  */
    S_62_(62),                     /* '+'  */
    S_63_(63),                     /* '-'  */
    S_64_(64),                     /* '/'  */
    S_65_(65),                     /* '%'  */
    S_66_(66),                     /* '!'  */
    S_67_(67),                     /* '~'  */
    S_68_(68),                     /* '?'  */
    S_YYACCEPT(69),                /* $accept  */
    S_root(70),                    /* root  */
    S_imports(71),                 /* imports  */
    S_grammar_file(72),            /* grammar_file  */
    S_visibility_spec(73),         /* visibility_spec  */
    S_includes(74),                /* includes  */
    S_path(75),                    /* path  */
    S_statement_no_def(76),        /* statement_no_def  */
    S_statement(77),               /* statement  */
    S_blk_statement(78),           /* blk_statement  */
    S_block(79),                   /* block  */
    S_statements(80),              /* statements  */
    S_grammar_rule(81),            /* grammar_rule  */
    S_return_statement(82),        /* return_statement  */
    S_if_statement(83),            /* if_statement  */
    S_while_statement(84),         /* while_statement  */
    S_for_statement(85),           /* for_statement  */
    S_var_decl(86),                /* var_decl  */
    S_propose_statement(87),       /* propose_statement  */
    S_timeout_statement(88),       /* timeout_statement  */
    S_switch_statement(89),        /* switch_statement  */
    S_label_statement(90),         /* label_statement  */
    S_var_def(91),                 /* var_def  */
    S_field_def(92),               /* field_def  */
    S_assgn_exp(93),               /* assgn_exp  */
    S_nonempty_exp_list(94),       /* nonempty_exp_list  */
    S_method_declaration(95),      /* method_declaration  */
    S_opt_block(96),               /* opt_block  */
    S_opt_args_list(97),           /* opt_args_list  */
    S_args_list(98),               /* args_list  */
    S_set_operation(99),           /* set_operation  */
    S_function_call(100),          /* function_call  */
    S_nonempty_args_list(101),     /* nonempty_args_list  */
    S_type_spec(102),              /* type_spec  */
    S_type_spec_list(103),         /* type_spec_list  */
    S_ConditionalOrExpression(104), /* ConditionalOrExpression  */
    S_ConditionalAndExpression(105), /* ConditionalAndExpression  */
    S_InclusiveOrExpression(106),  /* InclusiveOrExpression  */
    S_ExclusiveOrExpression(107),  /* ExclusiveOrExpression  */
    S_AndExpression(108),          /* AndExpression  */
    S_EqualityExpression(109),     /* EqualityExpression  */
    S_RelationalExpression(110),   /* RelationalExpression  */
    S_AdditiveExpression(111),     /* AdditiveExpression  */
    S_MultiplicativeExpression(112), /* MultiplicativeExpression  */
    S_CastExpression(113),         /* CastExpression  */
    S_UnaryExpression(114),        /* UnaryExpression  */
    S_LogicalUnaryExpression(115), /* LogicalUnaryExpression  */
    S_PostfixExpression(116),      /* PostfixExpression  */
    S_PrimaryExpression(117),      /* PrimaryExpression  */
    S_ComplexPrimary(118),         /* ComplexPrimary  */
    S_ComplexPrimaryNoParenthesis(119), /* ComplexPrimaryNoParenthesis  */
    S_Literal(120),                /* Literal  */
    S_ArrayAccess(121),            /* ArrayAccess  */
    S_field_access(122),           /* field_access  */
    S_field_access_rest(123),      /* field_access_rest  */
    S_simple_nofa_exp(124),        /* simple_nofa_exp  */
    S_ConditionalExpression(125),  /* ConditionalExpression  */
    S_assignment(126),             /* assignment  */
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
      SymbolKind.S_STATIC,
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
      SymbolKind.S_68_,
      SymbolKind.S_YYACCEPT,
      SymbolKind.S_root,
      SymbolKind.S_imports,
      SymbolKind.S_grammar_file,
      SymbolKind.S_visibility_spec,
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
      SymbolKind.S_ComplexPrimary,
      SymbolKind.S_ComplexPrimaryNoParenthesis,
      SymbolKind.S_Literal,
      SymbolKind.S_ArrayAccess,
      SymbolKind.S_field_access,
      SymbolKind.S_field_access_rest,
      SymbolKind.S_simple_nofa_exp,
      SymbolKind.S_ConditionalExpression,
      SymbolKind.S_assignment,
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
  "FOR", "IF", "IMPORT", "STATIC", "INCLUDE", "NEW", "NULL", "PRIVATE",
  "PROPOSE", "PROTECTED", "PUBLIC", "RETURN", "SWITCH", "TIMEOUT", "WHILE",
  "ARROW", "ANDAND", "OROR", "EQEQ", "NOTEQ", "GTEQ", "LTEQ", "MINUSEQ",
  "PLUSEQ", "MINUSMINUS", "PLUSPLUS", "STRING", "WILDCARD", "INT",
  "BOOL_LITERAL", "IDENTIFIER", "OTHER_LITERAL", "';'", "'.'", "'*'",
  "'{'", "'}'", "':'", "'('", "')'", "','", "'#'", "'='", "'['", "']'",
  "'<'", "'>'", "'|'", "'^'", "'&'", "'+'", "'-'", "'/'", "'%'", "'!'",
  "'~'", "'?'", "$accept", "root", "imports", "grammar_file",
  "visibility_spec", "includes", "path", "statement_no_def", "statement",
  "blk_statement", "block", "statements", "grammar_rule",
  "return_statement", "if_statement", "while_statement", "for_statement",
  "var_decl", "propose_statement", "timeout_statement", "switch_statement",
  "label_statement", "var_def", "field_def", "assgn_exp",
  "nonempty_exp_list", "method_declaration", "opt_block", "opt_args_list",
  "args_list", "set_operation", "function_call", "nonempty_args_list",
  "type_spec", "type_spec_list", "ConditionalOrExpression",
  "ConditionalAndExpression", "InclusiveOrExpression",
  "ExclusiveOrExpression", "AndExpression", "EqualityExpression",
  "RelationalExpression", "AdditiveExpression", "MultiplicativeExpression",
  "CastExpression", "UnaryExpression", "LogicalUnaryExpression",
  "PostfixExpression", "PrimaryExpression", "ComplexPrimary",
  "ComplexPrimaryNoParenthesis", "Literal", "ArrayAccess", "field_access",
  "field_access_rest", "simple_nofa_exp", "ConditionalExpression",
  "assignment", "new_exp", "lambda_exp", "dialogueact_exp", "da_token",
  "da_args", "exp", null
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
    /** Token STATIC, to be returned by the scanner.  */
    static final int STATIC = 270;
    /** Token INCLUDE, to be returned by the scanner.  */
    static final int INCLUDE = 271;
    /** Token NEW, to be returned by the scanner.  */
    static final int NEW = 272;
    /** Token NULL, to be returned by the scanner.  */
    static final int NULL = 273;
    /** Token PRIVATE, to be returned by the scanner.  */
    static final int PRIVATE = 274;
    /** Token PROPOSE, to be returned by the scanner.  */
    static final int PROPOSE = 275;
    /** Token PROTECTED, to be returned by the scanner.  */
    static final int PROTECTED = 276;
    /** Token PUBLIC, to be returned by the scanner.  */
    static final int PUBLIC = 277;
    /** Token RETURN, to be returned by the scanner.  */
    static final int RETURN = 278;
    /** Token SWITCH, to be returned by the scanner.  */
    static final int SWITCH = 279;
    /** Token TIMEOUT, to be returned by the scanner.  */
    static final int TIMEOUT = 280;
    /** Token WHILE, to be returned by the scanner.  */
    static final int WHILE = 281;
    /** Token ARROW, to be returned by the scanner.  */
    static final int ARROW = 282;
    /** Token ANDAND, to be returned by the scanner.  */
    static final int ANDAND = 283;
    /** Token OROR, to be returned by the scanner.  */
    static final int OROR = 284;
    /** Token EQEQ, to be returned by the scanner.  */
    static final int EQEQ = 285;
    /** Token NOTEQ, to be returned by the scanner.  */
    static final int NOTEQ = 286;
    /** Token GTEQ, to be returned by the scanner.  */
    static final int GTEQ = 287;
    /** Token LTEQ, to be returned by the scanner.  */
    static final int LTEQ = 288;
    /** Token MINUSEQ, to be returned by the scanner.  */
    static final int MINUSEQ = 289;
    /** Token PLUSEQ, to be returned by the scanner.  */
    static final int PLUSEQ = 290;
    /** Token MINUSMINUS, to be returned by the scanner.  */
    static final int MINUSMINUS = 291;
    /** Token PLUSPLUS, to be returned by the scanner.  */
    static final int PLUSPLUS = 292;
    /** Token STRING, to be returned by the scanner.  */
    static final int STRING = 293;
    /** Token WILDCARD, to be returned by the scanner.  */
    static final int WILDCARD = 294;
    /** Token INT, to be returned by the scanner.  */
    static final int INT = 295;
    /** Token BOOL_LITERAL, to be returned by the scanner.  */
    static final int BOOL_LITERAL = 296;
    /** Token IDENTIFIER, to be returned by the scanner.  */
    static final int IDENTIFIER = 297;
    /** Token OTHER_LITERAL, to be returned by the scanner.  */
    static final int OTHER_LITERAL = 298;

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
          case 2: /* root: imports root  */
  if (yyn == 2)
    /* "VondaGrammar.y":143  */
                 { yyval = ((LinkedList<RudiTree>)(yystack.valueAt (0))); ((LinkedList<RudiTree>)(yystack.valueAt (0))).addFirst(((Import)(yystack.valueAt (1)))); };
  break;


  case 3: /* root: grammar_file  */
  if (yyn == 3)
    /* "VondaGrammar.y":144  */
                 { yyval = ((LinkedList<RudiTree>)(yystack.valueAt (0)));};
  break;


  case 4: /* imports: IMPORT path ';'  */
  if (yyn == 4)
    /* "VondaGrammar.y":148  */
                    { yyval = setPos(new Import(((List<String>)(yystack.valueAt (1)))), (yyloc)); };
  break;


  case 5: /* imports: IMPORT path '.' '*' ';'  */
  if (yyn == 5)
    /* "VondaGrammar.y":149  */
                            {
    ((List<String>)(yystack.valueAt (3))).add("*");
    yyval = setPos(new Import(((List<String>)(yystack.valueAt (3)))), (yyloc));
  };
  break;


  case 6: /* imports: IMPORT STATIC path ';'  */
  if (yyn == 6)
    /* "VondaGrammar.y":153  */
                           { yyval = setPos(new Import(((List<String>)(yystack.valueAt (1))), true), (yyloc)); };
  break;


  case 7: /* imports: IMPORT STATIC path '.' '*' ';'  */
  if (yyn == 7)
    /* "VondaGrammar.y":154  */
                                   {
    ((List<String>)(yystack.valueAt (3))).add("*");
    yyval = setPos(new Import(((List<String>)(yystack.valueAt (3))), true), (yyloc));
  };
  break;


  case 8: /* grammar_file: visibility_spec method_declaration grammar_file  */
  if (yyn == 8)
    /* "VondaGrammar.y":160  */
                                                    {
    yyval = ((LinkedList<RudiTree>)(yystack.valueAt (0))); ((StatMethodDeclaration)(yystack.valueAt (1))).setVisibility(((String)(yystack.valueAt (2)))); ((LinkedList<RudiTree>)(yystack.valueAt (0))).addFirst(((StatMethodDeclaration)(yystack.valueAt (1))));
  };
  break;


  case 9: /* grammar_file: method_declaration grammar_file  */
  if (yyn == 9)
    /* "VondaGrammar.y":163  */
                                    { yyval = ((LinkedList<RudiTree>)(yystack.valueAt (0))); ((LinkedList<RudiTree>)(yystack.valueAt (0))).addFirst(((StatMethodDeclaration)(yystack.valueAt (1)))); };
  break;


  case 10: /* grammar_file: function_call grammar_file  */
  if (yyn == 10)
    /* "VondaGrammar.y":164  */
                               { yyval = ((LinkedList<RudiTree>)(yystack.valueAt (0))); ((LinkedList<RudiTree>)(yystack.valueAt (0))).addFirst(((RTExpression)(yystack.valueAt (1)))); };
  break;


  case 11: /* grammar_file: statement_no_def grammar_file  */
  if (yyn == 11)
    /* "VondaGrammar.y":165  */
                                  { yyval = ((LinkedList<RudiTree>)(yystack.valueAt (0))); ((LinkedList<RudiTree>)(yystack.valueAt (0))).addFirst(((RTStatement)(yystack.valueAt (1)))); };
  break;


  case 12: /* grammar_file: includes grammar_file  */
  if (yyn == 12)
    /* "VondaGrammar.y":167  */
                          { yyval = ((LinkedList<RudiTree>)(yystack.valueAt (0))); ((LinkedList<RudiTree>)(yystack.valueAt (0))).addFirst(((Include)(yystack.valueAt (1)))); };
  break;


  case 13: /* grammar_file: visibility_spec var_def grammar_file  */
  if (yyn == 13)
    /* "VondaGrammar.y":168  */
                                          {
    yyval = ((LinkedList<RudiTree>)(yystack.valueAt (0))); ((LinkedList<RudiTree>)(yystack.valueAt (0))).addFirst(setPos(new StatFieldDef(((String)(yystack.valueAt (2))), ((StatVarDef)(yystack.valueAt (1)))), yystack.locationAt (2), yystack.locationAt (1)));
  };
  break;


  case 14: /* grammar_file: var_def grammar_file  */
  if (yyn == 14)
    /* "VondaGrammar.y":171  */
                          {
    yyval = ((LinkedList<RudiTree>)(yystack.valueAt (0))); ((LinkedList<RudiTree>)(yystack.valueAt (0))).addFirst(setPos(new StatFieldDef(null, ((StatVarDef)(yystack.valueAt (1)))), yystack.locationAt (1)));
  };
  break;


  case 15: /* grammar_file: field_def grammar_file  */
  if (yyn == 15)
    /* "VondaGrammar.y":174  */
                           {
    yyval = ((LinkedList<RudiTree>)(yystack.valueAt (0))); ((LinkedList<RudiTree>)(yystack.valueAt (0))).addFirst(((StatFieldDef)(yystack.valueAt (1))));
  };
  break;


  case 16: /* grammar_file: %empty  */
  if (yyn == 16)
    /* "VondaGrammar.y":177  */
           { yyval = _statements;};
  break;


  case 17: /* visibility_spec: PUBLIC  */
  if (yyn == 17)
    /* "VondaGrammar.y":181  */
           { yyval = "public"; };
  break;


  case 18: /* visibility_spec: PROTECTED  */
  if (yyn == 18)
    /* "VondaGrammar.y":182  */
              { yyval = "protected"; };
  break;


  case 19: /* visibility_spec: PRIVATE  */
  if (yyn == 19)
    /* "VondaGrammar.y":183  */
             { yyval = "private"; };
  break;


  case 20: /* includes: INCLUDE path ';'  */
  if (yyn == 20)
    /* "VondaGrammar.y":187  */
                     {
    List<String> path = ((List<String>)(yystack.valueAt (1)));
    String name = path.remove(path.size() - 1);
    yyval = setPos(new Include(name, path.toArray(new String[path.size()])), (yyloc));
  };
  break;


  case 21: /* path: IDENTIFIER  */
  if (yyn == 21)
    /* "VondaGrammar.y":195  */
               { yyval = new ArrayList<String>(){{ add((( String )(yystack.valueAt (0)))); }}; };
  break;


  case 22: /* path: path '.' IDENTIFIER  */
  if (yyn == 22)
    /* "VondaGrammar.y":196  */
                        { yyval = ((List<String>)(yystack.valueAt (2))); ((List<String>)(yystack.valueAt (2))).add((( String )(yystack.valueAt (0)))); };
  break;


  case 23: /* statement_no_def: block  */
  if (yyn == 23)
    /* "VondaGrammar.y":200  */
          { yyval = ((StatAbstractBlock)(yystack.valueAt (0))); };
  break;


  case 24: /* statement_no_def: assignment ';'  */
  if (yyn == 24)
    /* "VondaGrammar.y":201  */
                   { yyval = setPos(getAssignmentStat(((RTExpression)(yystack.valueAt (1)))), (yyloc)); };
  break;


  case 25: /* statement_no_def: field_access ';'  */
  if (yyn == 25)
    /* "VondaGrammar.y":202  */
                     { yyval = setPos(new StatExpression(((RTExpression)(yystack.valueAt (1)))), (yyloc)); };
  break;


  case 26: /* statement_no_def: PLUSPLUS IDENTIFIER ';'  */
  if (yyn == 26)
    /* "VondaGrammar.y":203  */
                            {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (1)))), yystack.locationAt (1));
    yyval = setPos(new StatExpression(createPlusMinus(var, "++", (yyloc))), (yyloc));
  };
  break;


  case 27: /* statement_no_def: MINUSMINUS IDENTIFIER ';'  */
  if (yyn == 27)
    /* "VondaGrammar.y":207  */
                              {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (1)))), yystack.locationAt (1));
    yyval = setPos(new StatExpression(createPlusMinus(var, "--", (yyloc))), (yyloc));
  };
  break;


  case 28: /* statement_no_def: IDENTIFIER PLUSPLUS ';'  */
  if (yyn == 28)
    /* "VondaGrammar.y":211  */
                            {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (2)))), yystack.locationAt (2));
    yyval = setPos(new StatExpression(createPlusMinus(var, "+++", (yyloc))), (yyloc));
  };
  break;


  case 29: /* statement_no_def: IDENTIFIER MINUSMINUS ';'  */
  if (yyn == 29)
    /* "VondaGrammar.y":215  */
                              {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (2)))), yystack.locationAt (2));
    yyval = setPos(new StatExpression(createPlusMinus(var, "---", (yyloc))), (yyloc));
  };
  break;


  case 30: /* statement_no_def: PLUSPLUS field_access ';'  */
  if (yyn == 30)
    /* "VondaGrammar.y":219  */
                              {
    yyval = setPos(new StatExpression(createPlusMinus(((RTExpression)(yystack.valueAt (1))), "++", (yyloc))), (yyloc));
  };
  break;


  case 31: /* statement_no_def: MINUSMINUS field_access ';'  */
  if (yyn == 31)
    /* "VondaGrammar.y":222  */
                                {
    yyval = setPos(new StatExpression(createPlusMinus(((RTExpression)(yystack.valueAt (1))), "--", (yyloc))), (yyloc));
  };
  break;


  case 32: /* statement_no_def: field_access PLUSPLUS ';'  */
  if (yyn == 32)
    /* "VondaGrammar.y":225  */
                              {
    yyval = setPos(new StatExpression(createPlusMinus(((RTExpression)(yystack.valueAt (2))), "+++", (yyloc))), (yyloc));
  };
  break;


  case 33: /* statement_no_def: field_access MINUSMINUS ';'  */
  if (yyn == 33)
    /* "VondaGrammar.y":228  */
                                {
    yyval = setPos(new StatExpression(createPlusMinus(((RTExpression)(yystack.valueAt (2))), "---", (yyloc))), (yyloc));
  };
  break;


  case 34: /* statement_no_def: function_call ';'  */
  if (yyn == 34)
    /* "VondaGrammar.y":231  */
                      { yyval = setPos(new StatExpression(((RTExpression)(yystack.valueAt (1)))), (yyloc)); };
  break;


  case 35: /* statement_no_def: grammar_rule  */
  if (yyn == 35)
    /* "VondaGrammar.y":232  */
                 { yyval = ((StatGrammarRule)(yystack.valueAt (0))); };
  break;


  case 36: /* statement_no_def: set_operation  */
  if (yyn == 36)
    /* "VondaGrammar.y":233  */
                  { yyval = ((RTStatement)(yystack.valueAt (0))); };
  break;


  case 37: /* statement_no_def: return_statement  */
  if (yyn == 37)
    /* "VondaGrammar.y":234  */
                     { yyval = ((RTStatement)(yystack.valueAt (0))); };
  break;


  case 38: /* statement_no_def: propose_statement  */
  if (yyn == 38)
    /* "VondaGrammar.y":235  */
                      { yyval = ((RTStatement)(yystack.valueAt (0))); };
  break;


  case 39: /* statement_no_def: timeout_statement  */
  if (yyn == 39)
    /* "VondaGrammar.y":236  */
                      { yyval = ((RTStatement)(yystack.valueAt (0))); };
  break;


  case 40: /* statement_no_def: if_statement  */
  if (yyn == 40)
    /* "VondaGrammar.y":237  */
                 { yyval = ((StatIf)(yystack.valueAt (0))); };
  break;


  case 41: /* statement_no_def: while_statement  */
  if (yyn == 41)
    /* "VondaGrammar.y":238  */
                    { yyval = ((RTStatement)(yystack.valueAt (0))); };
  break;


  case 42: /* statement_no_def: for_statement  */
  if (yyn == 42)
    /* "VondaGrammar.y":239  */
                  { yyval = ((RTStatement)(yystack.valueAt (0))); };
  break;


  case 43: /* statement_no_def: switch_statement  */
  if (yyn == 43)
    /* "VondaGrammar.y":240  */
                     { yyval = ((RTStatement)(yystack.valueAt (0))); };
  break;


  case 44: /* statement_no_def: label_statement  */
  if (yyn == 44)
    /* "VondaGrammar.y":241  */
                    { yyval = ((RTStatement)(yystack.valueAt (0))); };
  break;


  case 45: /* statement: statement_no_def  */
  if (yyn == 45)
    /* "VondaGrammar.y":245  */
                     { yyval = ((RTStatement)(yystack.valueAt (0))); };
  break;


  case 46: /* statement: var_def  */
  if (yyn == 46)
    /* "VondaGrammar.y":246  */
            { yyval = ((StatVarDef)(yystack.valueAt (0))); };
  break;


  case 47: /* blk_statement: statement  */
  if (yyn == 47)
    /* "VondaGrammar.y":250  */
              { yyval = ((RTStatement)(yystack.valueAt (0))); };
  break;


  case 48: /* block: '{' statements '}'  */
  if (yyn == 48)
    /* "VondaGrammar.y":256  */
                       { yyval = setPos(new StatAbstractBlock(((LinkedList<RTStatement>)(yystack.valueAt (1))), true), (yyloc)); };
  break;


  case 49: /* block: '{' '}'  */
  if (yyn == 49)
    /* "VondaGrammar.y":257  */
            {
    yyval = setPos(new StatAbstractBlock(new ArrayList<RTStatement>(), true), (yyloc));
  };
  break;


  case 50: /* statements: blk_statement  */
  if (yyn == 50)
    /* "VondaGrammar.y":261  */
                          { yyval = new LinkedList<RTStatement>(){{ add(((RTStatement)(yystack.valueAt (0)))); }}; };
  break;


  case 51: /* statements: blk_statement statements  */
  if (yyn == 51)
    /* "VondaGrammar.y":262  */
                             { yyval = ((LinkedList<RTStatement>)(yystack.valueAt (0))); ((LinkedList<RTStatement>)(yystack.valueAt (0))).addFirst(((RTStatement)(yystack.valueAt (1)))); };
  break;


  case 52: /* grammar_rule: IDENTIFIER ':' if_statement  */
  if (yyn == 52)
    /* "VondaGrammar.y":266  */
                                { yyval = setPos(new StatGrammarRule((( String )(yystack.valueAt (2))), ((StatIf)(yystack.valueAt (0)))), (yyloc)); };
  break;


  case 53: /* return_statement: RETURN ';'  */
  if (yyn == 53)
    /* "VondaGrammar.y":270  */
               { yyval = setPos(new StatReturn("return"), (yyloc)); };
  break;


  case 54: /* return_statement: RETURN exp ';'  */
  if (yyn == 54)
    /* "VondaGrammar.y":271  */
                   { yyval = setPos(new StatReturn(((RTExpression)(yystack.valueAt (1)))), (yyloc)); };
  break;


  case 55: /* return_statement: BREAK ';'  */
  if (yyn == 55)
    /* "VondaGrammar.y":272  */
              { yyval = setPos(new StatReturn("break"), (yyloc)); };
  break;


  case 56: /* return_statement: BREAK IDENTIFIER ';'  */
  if (yyn == 56)
    /* "VondaGrammar.y":273  */
                         { yyval = setPos(new StatReturn("break", (( String )(yystack.valueAt (1)))), (yyloc)); };
  break;


  case 57: /* return_statement: CANCEL ';'  */
  if (yyn == 57)
    /* "VondaGrammar.y":274  */
               { yyval = setPos(new StatReturn("cancel"), (yyloc)); };
  break;


  case 58: /* return_statement: CANCEL_ALL ';'  */
  if (yyn == 58)
    /* "VondaGrammar.y":275  */
                   { yyval = setPos(new StatReturn("cancel_all"), (yyloc)); };
  break;


  case 59: /* return_statement: CONTINUE ';'  */
  if (yyn == 59)
    /* "VondaGrammar.y":276  */
                 { yyval = setPos(new StatReturn("continue"), (yyloc)); };
  break;


  case 60: /* if_statement: IF '(' exp ')' statement  */
  if (yyn == 60)
    /* "VondaGrammar.y":280  */
                             { yyval = setPos(new StatIf(((RTExpression)(yystack.valueAt (2))), ((RTStatement)(yystack.valueAt (0))), null), (yyloc)); };
  break;


  case 61: /* if_statement: IF '(' exp ')' statement ELSE statement  */
  if (yyn == 61)
    /* "VondaGrammar.y":281  */
                                            {
    yyval = setPos(new StatIf(((RTExpression)(yystack.valueAt (4))), ((RTStatement)(yystack.valueAt (2))), ((RTStatement)(yystack.valueAt (0)))), (yyloc));
  };
  break;


  case 62: /* while_statement: WHILE '(' exp ')' statement  */
  if (yyn == 62)
    /* "VondaGrammar.y":287  */
                                { yyval = setPos(new StatWhile(((RTExpression)(yystack.valueAt (2))), ((RTStatement)(yystack.valueAt (0))), true), (yyloc)); };
  break;


  case 63: /* while_statement: DO statement WHILE '(' exp ')'  */
  if (yyn == 63)
    /* "VondaGrammar.y":288  */
                                   {
    yyval = setPos(new StatWhile(((RTExpression)(yystack.valueAt (1))), ((RTStatement)(yystack.valueAt (4))), false), (yyloc));
  };
  break;


  case 64: /* for_statement: FOR '(' var_decl exp ';' exp ')' statement  */
  if (yyn == 64)
    /* "VondaGrammar.y":294  */
                                               {
    yyval = setPos(new StatFor1(((StatVarDef)(yystack.valueAt (5))), ((RTExpression)(yystack.valueAt (4))), ((RTExpression)(yystack.valueAt (2))), ((RTStatement)(yystack.valueAt (0)))), (yyloc)); };
  break;


  case 65: /* for_statement: FOR '(' var_decl ';' exp ')' statement  */
  if (yyn == 65)
    /* "VondaGrammar.y":296  */
                                               {
    yyval = setPos(new StatFor1(((StatVarDef)(yystack.valueAt (4))), null, ((RTExpression)(yystack.valueAt (2))), ((RTStatement)(yystack.valueAt (0)))), (yyloc)); };
  break;


  case 66: /* for_statement: FOR '(' var_decl exp ';' ')' statement  */
  if (yyn == 66)
    /* "VondaGrammar.y":298  */
                                               {
    yyval = setPos(new StatFor1(((StatVarDef)(yystack.valueAt (4))), ((RTExpression)(yystack.valueAt (3))), null, ((RTStatement)(yystack.valueAt (0)))), (yyloc)); };
  break;


  case 67: /* for_statement: FOR '(' var_decl ';' ')' statement  */
  if (yyn == 67)
    /* "VondaGrammar.y":300  */
                                               {
    yyval = setPos(new StatFor1(((StatVarDef)(yystack.valueAt (3))), null, null, ((RTStatement)(yystack.valueAt (0)))), (yyloc)); };
  break;


  case 68: /* for_statement: FOR '(' ';' exp ';' exp ')' statement  */
  if (yyn == 68)
    /* "VondaGrammar.y":302  */
                                              {
    yyval = setPos(new StatFor1(null, ((RTExpression)(yystack.valueAt (4))), ((RTExpression)(yystack.valueAt (2))), ((RTStatement)(yystack.valueAt (0)))), (yyloc)); };
  break;


  case 69: /* for_statement: FOR '(' IDENTIFIER ':' exp ')' statement  */
  if (yyn == 69)
    /* "VondaGrammar.y":304  */
                                             {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (4)))), yystack.locationAt (4));
    yyval = setPos(new StatFor2(var, ((RTExpression)(yystack.valueAt (2))), ((RTStatement)(yystack.valueAt (0)))), (yyloc));
  };
  break;


  case 70: /* for_statement: FOR '(' type_spec IDENTIFIER ':' exp ')' statement  */
  if (yyn == 70)
    /* "VondaGrammar.y":308  */
                                                       {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (4)))), yystack.locationAt (4));
    yyval = setPos(new StatFor2(((Type)(yystack.valueAt (5))), var, ((RTExpression)(yystack.valueAt (2))), ((RTStatement)(yystack.valueAt (0)))), (yyloc));
  };
  break;


  case 71: /* var_decl: IDENTIFIER assgn_exp ';'  */
  if (yyn == 71)
    /* "VondaGrammar.y":317  */
                             {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (2)))), yystack.locationAt (2));
    ExpAssignment ass = setPos(new ExpAssignment(var, ((RTExpression)(yystack.valueAt (1)))), yystack.locationAt (2), yystack.locationAt (1));
    yyval = setPos(new StatVarDef(false, Type.getNoType(), ass), (yyloc));
  };
  break;


  case 72: /* var_decl: type_spec IDENTIFIER assgn_exp ';'  */
  if (yyn == 72)
    /* "VondaGrammar.y":322  */
                                       {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (2)))), yystack.locationAt (2));
    ExpAssignment ass = setPos(new ExpAssignment(var, ((RTExpression)(yystack.valueAt (1)))), yystack.locationAt (2), yystack.locationAt (1));
    yyval = setPos(new StatVarDef(false, ((Type)(yystack.valueAt (3))), ass), (yyloc));
  };
  break;


  case 73: /* var_decl: FINAL IDENTIFIER assgn_exp ';'  */
  if (yyn == 73)
    /* "VondaGrammar.y":327  */
                                   {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (2)))), yystack.locationAt (2));
    ExpAssignment ass = setPos(new ExpAssignment(var, ((RTExpression)(yystack.valueAt (1)))), yystack.locationAt (2), yystack.locationAt (1));
    yyval = setPos(new StatVarDef(true, Type.getNoType(), ass), (yyloc));
  };
  break;


  case 74: /* var_decl: FINAL type_spec IDENTIFIER assgn_exp ';'  */
  if (yyn == 74)
    /* "VondaGrammar.y":332  */
                                             {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (2)))), yystack.locationAt (2));
    ExpAssignment ass = setPos(new ExpAssignment(var, ((RTExpression)(yystack.valueAt (1)))), yystack.locationAt (2), yystack.locationAt (1));
    yyval = setPos(new StatVarDef(true, ((Type)(yystack.valueAt (3))), ass), (yyloc));
  };
  break;


  case 75: /* propose_statement: PROPOSE '(' exp ')' block  */
  if (yyn == 75)
    /* "VondaGrammar.y":340  */
                              { yyval = setPos(new StatPropose(((RTExpression)(yystack.valueAt (2))), ((StatAbstractBlock)(yystack.valueAt (0)))), (yyloc)); };
  break;


  case 76: /* timeout_statement: TIMEOUT '(' exp ',' exp ')' block  */
  if (yyn == 76)
    /* "VondaGrammar.y":344  */
                                      {
    yyval = setPos(new StatTimeout(((RTExpression)(yystack.valueAt (4))), ((RTExpression)(yystack.valueAt (2))), ((StatAbstractBlock)(yystack.valueAt (0)))), (yyloc));
  };
  break;


  case 77: /* switch_statement: SWITCH '(' exp ')' block  */
  if (yyn == 77)
    /* "VondaGrammar.y":350  */
                             {
    yyval = setPos(new StatSwitch(((RTExpression)(yystack.valueAt (2))), ((StatAbstractBlock)(yystack.valueAt (0)))), (yyloc));
  };
  break;


  case 78: /* label_statement: CASE STRING ':'  */
  if (yyn == 78)
    /* "VondaGrammar.y":357  */
                      {
    ExpLiteral val =
      new ExpLiteral("case \"" + (( ExpLiteral )(yystack.valueAt (1))).toString() + "\":", "label");
    RTStatement lbl = val.ensureStatement();
    setPos(val, (yyloc)); setPos(lbl, (yyloc));
    yyval = lbl;
  };
  break;


  case 79: /* label_statement: CASE INT ':'  */
  if (yyn == 79)
    /* "VondaGrammar.y":364  */
                      {
    ExpLiteral val =
      new ExpLiteral("case " + (( ExpLiteral )(yystack.valueAt (1))).toString() + ":", "label");
    RTStatement lbl = val.ensureStatement();
    setPos(val, (yyloc)); setPos(lbl, (yyloc));
    yyval = lbl;
  };
  break;


  case 80: /* label_statement: CASE BOOL_LITERAL ':'  */
  if (yyn == 80)
    /* "VondaGrammar.y":371  */
                               {
    ExpLiteral val =
      new ExpLiteral("case " + (( ExpLiteral )(yystack.valueAt (1))).toString() + ":", "label");
    RTStatement lbl = val.ensureStatement();
    setPos(val, (yyloc)); setPos(lbl, (yyloc));
    yyval = lbl;
  };
  break;


  case 81: /* label_statement: CASE IDENTIFIER ':'  */
  if (yyn == 81)
    /* "VondaGrammar.y":378  */
                        {
    ExpLiteral val =
      new ExpLiteral("case " + (( String )(yystack.valueAt (1))) + ":", "label");
    RTStatement lbl = val.ensureStatement();
    setPos(val, (yyloc)); setPos(lbl, (yyloc));
    yyval = lbl;
  };
  break;


  case 82: /* label_statement: DEFAULT ':'  */
  if (yyn == 82)
    /* "VondaGrammar.y":385  */
                      {
    ExpLiteral val = new ExpLiteral("default:", "label");
    RTStatement lbl = val.ensureStatement();
    setPos(val, (yyloc)); setPos(lbl, (yyloc));
    yyval = lbl;
  };
  break;


  case 83: /* var_def: FINAL IDENTIFIER assgn_exp ';'  */
  if (yyn == 83)
    /* "VondaGrammar.y":394  */
                                   {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (2)))), yystack.locationAt (2));
    ExpAssignment ass = setPos(new ExpAssignment(var, ((RTExpression)(yystack.valueAt (1)))), yystack.locationAt (2), yystack.locationAt (1));
    yyval = setPos(new StatVarDef(true, Type.getNoType(), ass), (yyloc));
  };
  break;


  case 84: /* var_def: type_spec IDENTIFIER assgn_exp ';'  */
  if (yyn == 84)
    /* "VondaGrammar.y":399  */
                                       {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (2)))), yystack.locationAt (2));
    ExpAssignment ass = setPos(new ExpAssignment(var, ((RTExpression)(yystack.valueAt (1)))), yystack.locationAt (2), yystack.locationAt (1));
    yyval = setPos(new StatVarDef(false, ((Type)(yystack.valueAt (3))), ass), (yyloc));
  };
  break;


  case 85: /* var_def: FINAL type_spec IDENTIFIER assgn_exp ';'  */
  if (yyn == 85)
    /* "VondaGrammar.y":404  */
                                             {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (2)))), yystack.locationAt (2));
    ExpAssignment ass = setPos(new ExpAssignment(var, ((RTExpression)(yystack.valueAt (1)))), yystack.locationAt (2), yystack.locationAt (1));
    yyval = setPos(new StatVarDef(true, ((Type)(yystack.valueAt (3))), ass), (yyloc));
    };
  break;


  case 86: /* var_def: FINAL IDENTIFIER ';'  */
  if (yyn == 86)
    /* "VondaGrammar.y":409  */
                         {
    yyval = setPos(new StatVarDef(true, Type.getNoType(), (( String )(yystack.valueAt (1)))), (yyloc));
  };
  break;


  case 87: /* var_def: type_spec IDENTIFIER ';'  */
  if (yyn == 87)
    /* "VondaGrammar.y":412  */
                             {
    yyval = setPos(new StatVarDef(false, ((Type)(yystack.valueAt (2))), (( String )(yystack.valueAt (1)))), (yyloc));
  };
  break;


  case 88: /* var_def: FINAL type_spec IDENTIFIER ';'  */
  if (yyn == 88)
    /* "VondaGrammar.y":415  */
                                   {
    yyval = setPos(new StatVarDef(true, ((Type)(yystack.valueAt (2))), (( String )(yystack.valueAt (1)))), (yyloc));
  };
  break;


  case 89: /* field_def: '#' type_spec type_spec IDENTIFIER ';'  */
  if (yyn == 89)
    /* "VondaGrammar.y":421  */
                                           {
    yyval = setPos(new StatFieldDef(null,
           setPos(new StatVarDef(false, ((Type)(yystack.valueAt (2))), (( String )(yystack.valueAt (1)))), (yyloc)), ((Type)(yystack.valueAt (3)))), (yyloc));
  };
  break;


  case 90: /* assgn_exp: '=' exp  */
  if (yyn == 90)
    /* "VondaGrammar.y":428  */
            { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 91: /* assgn_exp: '=' '{' '}'  */
  if (yyn == 91)
    /* "VondaGrammar.y":429  */
                {
    yyval = setPos(new ExpListLiteral(new LinkedList<RTExpression>()), yystack.locationAt (1), yystack.locationAt (0));
  };
  break;


  case 92: /* assgn_exp: '=' '{' nonempty_exp_list '}'  */
  if (yyn == 92)
    /* "VondaGrammar.y":432  */
                                  {
    yyval = setPos(new ExpListLiteral(((LinkedList<RTExpression>)(yystack.valueAt (1)))), yystack.locationAt (2), yystack.locationAt (0));
  };
  break;


  case 93: /* nonempty_exp_list: exp  */
  if (yyn == 93)
    /* "VondaGrammar.y":438  */
        { yyval = new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (0)))); }}; };
  break;


  case 94: /* nonempty_exp_list: exp ',' nonempty_exp_list  */
  if (yyn == 94)
    /* "VondaGrammar.y":439  */
                              { yyval = ((LinkedList<RTExpression>)(yystack.valueAt (0))); ((LinkedList<RTExpression>)(yystack.valueAt (0))).addFirst(((RTExpression)(yystack.valueAt (2)))); };
  break;


  case 95: /* method_declaration: '#' type_spec type_spec IDENTIFIER '(' opt_args_list ')' opt_block  */
  if (yyn == 95)
    /* "VondaGrammar.y":444  */
                                                                       {
    yyval = setPos(new StatMethodDeclaration("public", ((Type)(yystack.valueAt (5))), ((Type)(yystack.valueAt (6))), (( String )(yystack.valueAt (4))), ((LinkedList)(yystack.valueAt (2))), ((StatAbstractBlock)(yystack.valueAt (0)))), (yyloc));
  };
  break;


  case 96: /* method_declaration: type_spec IDENTIFIER '(' opt_args_list ')' opt_block  */
  if (yyn == 96)
    /* "VondaGrammar.y":447  */
                                                         {
    yyval = setPos(new StatMethodDeclaration("public", ((Type)(yystack.valueAt (5))), null, (( String )(yystack.valueAt (4))), ((LinkedList)(yystack.valueAt (2))), ((StatAbstractBlock)(yystack.valueAt (0)))), (yyloc));
  };
  break;


  case 97: /* opt_block: block  */
  if (yyn == 97)
    /* "VondaGrammar.y":453  */
          { yyval = ((StatAbstractBlock)(yystack.valueAt (0))); };
  break;


  case 98: /* opt_block: ';'  */
  if (yyn == 98)
    /* "VondaGrammar.y":454  */
        { yyval = null; };
  break;


  case 99: /* opt_args_list: args_list  */
  if (yyn == 99)
    /* "VondaGrammar.y":458  */
              { yyval = ((LinkedList)(yystack.valueAt (0))); };
  break;


  case 100: /* opt_args_list: %empty  */
  if (yyn == 100)
    /* "VondaGrammar.y":459  */
           { yyval = new LinkedList(); };
  break;


  case 101: /* args_list: IDENTIFIER  */
  if (yyn == 101)
    /* "VondaGrammar.y":463  */
               { yyval = new LinkedList(){{ add(Type.getNoType()); add((( String )(yystack.valueAt (0)))); }}; };
  break;


  case 102: /* args_list: type_spec IDENTIFIER  */
  if (yyn == 102)
    /* "VondaGrammar.y":464  */
                         { yyval = new LinkedList(){{ add(((Type)(yystack.valueAt (1)))); add((( String )(yystack.valueAt (0)))); }}; };
  break;


  case 103: /* args_list: IDENTIFIER ',' args_list  */
  if (yyn == 103)
    /* "VondaGrammar.y":465  */
                             {
    yyval = ((LinkedList)(yystack.valueAt (0)));
    ((LinkedList)(yystack.valueAt (0))).addFirst((( String )(yystack.valueAt (2)))); ((LinkedList)(yystack.valueAt (0))).addFirst(Type.getNoType());
  };
  break;


  case 104: /* args_list: type_spec IDENTIFIER ',' args_list  */
  if (yyn == 104)
    /* "VondaGrammar.y":469  */
                                       {
    yyval = ((LinkedList)(yystack.valueAt (0))); ((LinkedList)(yystack.valueAt (0))).addFirst((( String )(yystack.valueAt (2)))); ((LinkedList)(yystack.valueAt (0))).addFirst(((Type)(yystack.valueAt (3))));
  };
  break;


  case 105: /* set_operation: IDENTIFIER PLUSEQ exp ';'  */
  if (yyn == 105)
    /* "VondaGrammar.y":477  */
                              {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (3)))), yystack.locationAt (3));
    yyval = setPos(new StatSetOperation(var, true, ((RTExpression)(yystack.valueAt (1)))), (yyloc));
  };
  break;


  case 106: /* set_operation: IDENTIFIER MINUSEQ exp ';'  */
  if (yyn == 106)
    /* "VondaGrammar.y":481  */
                               {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (3)))), yystack.locationAt (3));
    yyval = setPos(new StatSetOperation(var, false, ((RTExpression)(yystack.valueAt (1)))), (yyloc));
  };
  break;


  case 107: /* set_operation: ArrayAccess PLUSEQ exp ';'  */
  if (yyn == 107)
    /* "VondaGrammar.y":485  */
                               {
    yyval = setPos(new StatSetOperation(((RTExpression)(yystack.valueAt (3))), true, ((RTExpression)(yystack.valueAt (1)))), (yyloc));
  };
  break;


  case 108: /* set_operation: ArrayAccess MINUSEQ exp ';'  */
  if (yyn == 108)
    /* "VondaGrammar.y":488  */
                                {
    yyval = setPos(new StatSetOperation(((RTExpression)(yystack.valueAt (3))), false, ((RTExpression)(yystack.valueAt (1)))), (yyloc));
  };
  break;


  case 109: /* set_operation: field_access PLUSEQ exp ';'  */
  if (yyn == 109)
    /* "VondaGrammar.y":491  */
                                {
    yyval = setPos(new StatSetOperation(((RTExpression)(yystack.valueAt (3))), true, ((RTExpression)(yystack.valueAt (1)))), (yyloc));
  };
  break;


  case 110: /* set_operation: field_access MINUSEQ exp ';'  */
  if (yyn == 110)
    /* "VondaGrammar.y":494  */
                                 {
    yyval = setPos(new StatSetOperation(((RTExpression)(yystack.valueAt (3))), false, ((RTExpression)(yystack.valueAt (1)))), (yyloc));
  };
  break;


  case 111: /* function_call: IDENTIFIER '(' ')'  */
  if (yyn == 111)
    /* "VondaGrammar.y":503  */
                       {
    yyval = setPos(new ExpFuncCall((( String )(yystack.valueAt (2))), new LinkedList<RTExpression>(), false), (yyloc));
  };
  break;


  case 112: /* function_call: IDENTIFIER '(' nonempty_args_list ')'  */
  if (yyn == 112)
    /* "VondaGrammar.y":506  */
                                           {
    yyval = setPos(new ExpFuncCall((( String )(yystack.valueAt (3))), ((LinkedList<RTExpression>)(yystack.valueAt (1))), false), (yyloc));
  };
  break;


  case 113: /* nonempty_args_list: exp  */
  if (yyn == 113)
    /* "VondaGrammar.y":512  */
        { yyval = new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (0)))); }}; };
  break;


  case 114: /* nonempty_args_list: lambda_exp  */
  if (yyn == 114)
    /* "VondaGrammar.y":513  */
               { yyval = new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (0)))); }}; };
  break;


  case 115: /* nonempty_args_list: exp ',' nonempty_args_list  */
  if (yyn == 115)
    /* "VondaGrammar.y":514  */
                               { yyval = ((LinkedList<RTExpression>)(yystack.valueAt (0))); ((LinkedList<RTExpression>)(yystack.valueAt (0))).addFirst(((RTExpression)(yystack.valueAt (2)))); };
  break;


  case 116: /* nonempty_args_list: lambda_exp ',' nonempty_args_list  */
  if (yyn == 116)
    /* "VondaGrammar.y":515  */
                                      { yyval = ((LinkedList<RTExpression>)(yystack.valueAt (0))); ((LinkedList<RTExpression>)(yystack.valueAt (0))).addFirst(((RTExpression)(yystack.valueAt (2)))); };
  break;


  case 117: /* type_spec: IDENTIFIER '[' ']'  */
  if (yyn == 117)
    /* "VondaGrammar.y":519  */
                       {
    yyval = new Type("Array",
                  new ArrayList<Type>(){{ add(new Type((( String )(yystack.valueAt (2))))); }});
  };
  break;


  case 118: /* type_spec: IDENTIFIER  */
  if (yyn == 118)
    /* "VondaGrammar.y":523  */
               { yyval = new Type((( String )(yystack.valueAt (0)))); };
  break;


  case 119: /* type_spec: IDENTIFIER '<' type_spec_list '>'  */
  if (yyn == 119)
    /* "VondaGrammar.y":524  */
                                      { yyval = new Type((( String )(yystack.valueAt (3))), ((LinkedList<Type>)(yystack.valueAt (1)))); };
  break;


  case 120: /* type_spec_list: type_spec  */
  if (yyn == 120)
    /* "VondaGrammar.y":528  */
              { yyval = new LinkedList<Type>(){{ add(((Type)(yystack.valueAt (0)))); }}; };
  break;


  case 121: /* type_spec_list: type_spec ',' type_spec_list  */
  if (yyn == 121)
    /* "VondaGrammar.y":529  */
                                 { yyval = ((LinkedList<Type>)(yystack.valueAt (0))); ((LinkedList<Type>)(yystack.valueAt (0))).addFirst(((Type)(yystack.valueAt (2)))); };
  break;


  case 122: /* ConditionalOrExpression: ConditionalOrExpression OROR ConditionalAndExpression  */
  if (yyn == 122)
    /* "VondaGrammar.y":533  */
                                                          {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "||"), (yyloc));
  };
  break;


  case 123: /* ConditionalOrExpression: ConditionalAndExpression  */
  if (yyn == 123)
    /* "VondaGrammar.y":536  */
                             { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 124: /* ConditionalAndExpression: ConditionalAndExpression ANDAND InclusiveOrExpression  */
  if (yyn == 124)
    /* "VondaGrammar.y":540  */
                                                          {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "&&"), (yyloc));
  };
  break;


  case 125: /* ConditionalAndExpression: InclusiveOrExpression  */
  if (yyn == 125)
    /* "VondaGrammar.y":543  */
                          { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 126: /* InclusiveOrExpression: ExclusiveOrExpression  */
  if (yyn == 126)
    /* "VondaGrammar.y":547  */
                          { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 127: /* InclusiveOrExpression: InclusiveOrExpression '|' ExclusiveOrExpression  */
  if (yyn == 127)
    /* "VondaGrammar.y":548  */
                                                    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "|"), (yyloc));
  };
  break;


  case 128: /* ExclusiveOrExpression: AndExpression  */
  if (yyn == 128)
    /* "VondaGrammar.y":554  */
                  { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 129: /* ExclusiveOrExpression: ExclusiveOrExpression '^' AndExpression  */
  if (yyn == 129)
    /* "VondaGrammar.y":555  */
                                            {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "^"), (yyloc));
  };
  break;


  case 130: /* AndExpression: EqualityExpression  */
  if (yyn == 130)
    /* "VondaGrammar.y":561  */
                       { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 131: /* AndExpression: AndExpression '&' EqualityExpression  */
  if (yyn == 131)
    /* "VondaGrammar.y":562  */
                                         {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "&"), (yyloc));
  };
  break;


  case 132: /* EqualityExpression: RelationalExpression  */
  if (yyn == 132)
    /* "VondaGrammar.y":568  */
                         { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 133: /* EqualityExpression: EqualityExpression EQEQ RelationalExpression  */
  if (yyn == 133)
    /* "VondaGrammar.y":569  */
                                                 {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "=="), (yyloc));
  };
  break;


  case 134: /* EqualityExpression: EqualityExpression NOTEQ RelationalExpression  */
  if (yyn == 134)
    /* "VondaGrammar.y":572  */
                                                  {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "!="), (yyloc));
  };
  break;


  case 135: /* RelationalExpression: AdditiveExpression  */
  if (yyn == 135)
    /* "VondaGrammar.y":578  */
                       { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 136: /* RelationalExpression: RelationalExpression '<' AdditiveExpression  */
  if (yyn == 136)
    /* "VondaGrammar.y":579  */
                                                {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "<"), (yyloc));
  };
  break;


  case 137: /* RelationalExpression: RelationalExpression '>' AdditiveExpression  */
  if (yyn == 137)
    /* "VondaGrammar.y":582  */
                                                {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), ">"), (yyloc));
  };
  break;


  case 138: /* RelationalExpression: RelationalExpression GTEQ AdditiveExpression  */
  if (yyn == 138)
    /* "VondaGrammar.y":585  */
                                                 {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), ">="), (yyloc));
  };
  break;


  case 139: /* RelationalExpression: RelationalExpression LTEQ AdditiveExpression  */
  if (yyn == 139)
    /* "VondaGrammar.y":588  */
                                                 {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "<="), (yyloc));
  };
  break;


  case 140: /* AdditiveExpression: MultiplicativeExpression  */
  if (yyn == 140)
    /* "VondaGrammar.y":594  */
                             { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 141: /* AdditiveExpression: AdditiveExpression '+' MultiplicativeExpression  */
  if (yyn == 141)
    /* "VondaGrammar.y":595  */
                                                    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "+"), (yyloc));
  };
  break;


  case 142: /* AdditiveExpression: AdditiveExpression '-' MultiplicativeExpression  */
  if (yyn == 142)
    /* "VondaGrammar.y":598  */
                                                    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "-"), (yyloc));
  };
  break;


  case 143: /* MultiplicativeExpression: CastExpression  */
  if (yyn == 143)
    /* "VondaGrammar.y":604  */
                   { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 144: /* MultiplicativeExpression: MultiplicativeExpression '*' CastExpression  */
  if (yyn == 144)
    /* "VondaGrammar.y":605  */
                                                {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "*"), (yyloc));
  };
  break;


  case 145: /* MultiplicativeExpression: MultiplicativeExpression '/' CastExpression  */
  if (yyn == 145)
    /* "VondaGrammar.y":608  */
                                                {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "/"), (yyloc));
  };
  break;


  case 146: /* MultiplicativeExpression: MultiplicativeExpression '%' CastExpression  */
  if (yyn == 146)
    /* "VondaGrammar.y":611  */
                                                {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "%"), (yyloc));
  };
  break;


  case 147: /* CastExpression: UnaryExpression  */
  if (yyn == 147)
    /* "VondaGrammar.y":617  */
                    { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 148: /* CastExpression: '(' type_spec ')' CastExpression  */
  if (yyn == 148)
    /* "VondaGrammar.y":618  */
                                     { yyval = setPos(new ExpCast(((Type)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0)))), (yyloc)); };
  break;


  case 149: /* UnaryExpression: PLUSPLUS UnaryExpression  */
  if (yyn == 149)
    /* "VondaGrammar.y":622  */
                             {
    yyval = createPlusMinus(((RTExpression)(yystack.valueAt (0))), "++", (yyloc));
  };
  break;


  case 150: /* UnaryExpression: MINUSMINUS UnaryExpression  */
  if (yyn == 150)
    /* "VondaGrammar.y":625  */
                               {
    yyval = createPlusMinus(((RTExpression)(yystack.valueAt (0))), "--", (yyloc));
  };
  break;


  case 151: /* UnaryExpression: '+' CastExpression  */
  if (yyn == 151)
    /* "VondaGrammar.y":628  */
                       { yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (0))), null, "+"), (yyloc)); };
  break;


  case 152: /* UnaryExpression: '-' CastExpression  */
  if (yyn == 152)
    /* "VondaGrammar.y":629  */
                       { yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (0))), null, "-"), (yyloc)); };
  break;


  case 153: /* UnaryExpression: LogicalUnaryExpression  */
  if (yyn == 153)
    /* "VondaGrammar.y":630  */
                           { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 154: /* LogicalUnaryExpression: PostfixExpression  */
  if (yyn == 154)
    /* "VondaGrammar.y":634  */
                      { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 155: /* LogicalUnaryExpression: '!' UnaryExpression  */
  if (yyn == 155)
    /* "VondaGrammar.y":635  */
                        { yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (0))), null, "!"), (yyloc)); };
  break;


  case 156: /* LogicalUnaryExpression: '~' UnaryExpression  */
  if (yyn == 156)
    /* "VondaGrammar.y":636  */
                        { yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (0))), null, "~"), (yyloc)); };
  break;


  case 157: /* PostfixExpression: PrimaryExpression  */
  if (yyn == 157)
    /* "VondaGrammar.y":640  */
                      { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 158: /* PostfixExpression: PostfixExpression PLUSPLUS  */
  if (yyn == 158)
    /* "VondaGrammar.y":641  */
                               {
    yyval = createPlusMinus(((RTExpression)(yystack.valueAt (1))), "+++", (yyloc));
  };
  break;


  case 159: /* PostfixExpression: PostfixExpression MINUSMINUS  */
  if (yyn == 159)
    /* "VondaGrammar.y":644  */
                                 {
    yyval = createPlusMinus(((RTExpression)(yystack.valueAt (1))), "---", (yyloc));
  };
  break;


  case 160: /* PrimaryExpression: NULL  */
  if (yyn == 160)
    /* "VondaGrammar.y":650  */
         { yyval = setPos(new ExpLiteral("null", "null"), (yyloc)); };
  break;


  case 161: /* PrimaryExpression: IDENTIFIER  */
  if (yyn == 161)
    /* "VondaGrammar.y":651  */
               { yyval = setPos(new ExpIdentifier((( String )(yystack.valueAt (0)))), (yyloc)); };
  break;


  case 162: /* PrimaryExpression: field_access  */
  if (yyn == 162)
    /* "VondaGrammar.y":652  */
                 { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 163: /* PrimaryExpression: ComplexPrimary  */
  if (yyn == 163)
    /* "VondaGrammar.y":653  */
                   { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 164: /* ComplexPrimary: '(' exp ')'  */
  if (yyn == 164)
    /* "VondaGrammar.y":657  */
                { yyval = ((RTExpression)(yystack.valueAt (1))); ((RTExpression)(yystack.valueAt (1))).generateParens(); };
  break;


  case 165: /* ComplexPrimary: ComplexPrimaryNoParenthesis  */
  if (yyn == 165)
    /* "VondaGrammar.y":658  */
                                { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 166: /* ComplexPrimaryNoParenthesis: Literal  */
  if (yyn == 166)
    /* "VondaGrammar.y":662  */
            { yyval = ((ExpLiteral)(yystack.valueAt (0))); };
  break;


  case 167: /* ComplexPrimaryNoParenthesis: ArrayAccess  */
  if (yyn == 167)
    /* "VondaGrammar.y":663  */
                { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 168: /* ComplexPrimaryNoParenthesis: function_call  */
  if (yyn == 168)
    /* "VondaGrammar.y":664  */
                  { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 169: /* ComplexPrimaryNoParenthesis: dialogueact_exp  */
  if (yyn == 169)
    /* "VondaGrammar.y":665  */
                    { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 170: /* Literal: STRING  */
  if (yyn == 170)
    /* "VondaGrammar.y":669  */
           { yyval = setPos((( ExpLiteral )(yystack.valueAt (0))), (yyloc)); };
  break;


  case 171: /* Literal: INT  */
  if (yyn == 171)
    /* "VondaGrammar.y":670  */
        { yyval = setPos((( ExpLiteral )(yystack.valueAt (0))), (yyloc)); };
  break;


  case 172: /* Literal: OTHER_LITERAL  */
  if (yyn == 172)
    /* "VondaGrammar.y":671  */
                  { yyval = setPos((( ExpLiteral )(yystack.valueAt (0))), (yyloc)); };
  break;


  case 173: /* Literal: BOOL_LITERAL  */
  if (yyn == 173)
    /* "VondaGrammar.y":672  */
                 { yyval = setPos((( ExpLiteral )(yystack.valueAt (0))), (yyloc)); };
  break;


  case 174: /* ArrayAccess: IDENTIFIER '[' exp ']'  */
  if (yyn == 174)
    /* "VondaGrammar.y":676  */
                           {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (3)))), yystack.locationAt (3));
    yyval = setPos(new ExpArrayAccess(var, ((RTExpression)(yystack.valueAt (1)))), (yyloc));
  };
  break;


  case 175: /* ArrayAccess: ComplexPrimary '[' exp ']'  */
  if (yyn == 175)
    /* "VondaGrammar.y":680  */
                               { yyval = setPos(new ExpArrayAccess(((RTExpression)(yystack.valueAt (3))), ((RTExpression)(yystack.valueAt (1)))), (yyloc)); };
  break;


  case 176: /* field_access: IDENTIFIER field_access_rest  */
  if (yyn == 176)
    /* "VondaGrammar.y":684  */
                                  {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (1)))), yystack.locationAt (1));
    ((LinkedList<RTExpression>)(yystack.valueAt (0))).addFirst(var);
    yyval = setPos(new ExpFieldAccess(((LinkedList<RTExpression>)(yystack.valueAt (0)))), (yyloc));
  };
  break;


  case 177: /* field_access: ComplexPrimary field_access_rest  */
  if (yyn == 177)
    /* "VondaGrammar.y":689  */
                                     {
    ((LinkedList<RTExpression>)(yystack.valueAt (0))).addFirst(((RTExpression)(yystack.valueAt (1))));
    yyval = setPos(new ExpFieldAccess(((LinkedList<RTExpression>)(yystack.valueAt (0)))), (yyloc));
  };
  break;


  case 178: /* field_access_rest: '.' simple_nofa_exp field_access_rest  */
  if (yyn == 178)
    /* "VondaGrammar.y":696  */
                                           { yyval = ((LinkedList<RTExpression>)(yystack.valueAt (0))); ((LinkedList<RTExpression>)(yystack.valueAt (0))).addFirst(((RTExpression)(yystack.valueAt (1)))); };
  break;


  case 179: /* field_access_rest: '.' simple_nofa_exp  */
  if (yyn == 179)
    /* "VondaGrammar.y":697  */
                         {
     List<RTExpression> l = new LinkedList<RTExpression>();
     l.add(((RTExpression)(yystack.valueAt (0))));
     yyval = l;
   };
  break;


  case 180: /* simple_nofa_exp: IDENTIFIER  */
  if (yyn == 180)
    /* "VondaGrammar.y":705  */
               { yyval = setPos(new ExpIdentifier((( String )(yystack.valueAt (0)))), (yyloc)); };
  break;


  case 181: /* simple_nofa_exp: '{' exp '}'  */
  if (yyn == 181)
    /* "VondaGrammar.y":706  */
                { yyval = ((RTExpression)(yystack.valueAt (1))); };
  break;


  case 182: /* simple_nofa_exp: function_call  */
  if (yyn == 182)
    /* "VondaGrammar.y":707  */
                  { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 183: /* ConditionalExpression: ConditionalOrExpression '?' exp ':' exp  */
  if (yyn == 183)
    /* "VondaGrammar.y":711  */
                                            { yyval = setPos(new ExpConditional(((RTExpression)(yystack.valueAt (4))), ((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0)))), (yyloc)); };
  break;


  case 184: /* ConditionalExpression: ConditionalOrExpression  */
  if (yyn == 184)
    /* "VondaGrammar.y":712  */
                            { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 185: /* assignment: field_access assgn_exp  */
  if (yyn == 185)
    /* "VondaGrammar.y":718  */
                           { yyval = setPos(new ExpAssignment(((RTExpression)(yystack.valueAt (1))), ((RTExpression)(yystack.valueAt (0)))), (yyloc)); };
  break;


  case 186: /* assignment: ArrayAccess assgn_exp  */
  if (yyn == 186)
    /* "VondaGrammar.y":719  */
                          { yyval = setPos(new ExpAssignment(((RTExpression)(yystack.valueAt (1))), ((RTExpression)(yystack.valueAt (0)))), (yyloc)); };
  break;


  case 187: /* assignment: IDENTIFIER assgn_exp  */
  if (yyn == 187)
    /* "VondaGrammar.y":720  */
                         {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (1)))), yystack.locationAt (1));
    ExpAssignment ass = setPos(new ExpAssignment(var, ((RTExpression)(yystack.valueAt (0)))), (yyloc));
    yyval = ass;
  };
  break;


  case 188: /* new_exp: NEW IDENTIFIER  */
  if (yyn == 188)
    /* "VondaGrammar.y":728  */
                   { yyval = setPos(new ExpNew(new Type((( String )(yystack.valueAt (0))))), (yyloc)); };
  break;


  case 189: /* new_exp: NEW IDENTIFIER '(' ')'  */
  if (yyn == 189)
    /* "VondaGrammar.y":729  */
                           {
    yyval = setPos(new ExpNew(new Type((( String )(yystack.valueAt (2)))), new LinkedList<>()), (yyloc));
  };
  break;


  case 190: /* new_exp: NEW IDENTIFIER '(' nonempty_exp_list ')'  */
  if (yyn == 190)
    /* "VondaGrammar.y":732  */
                                             {
    yyval = setPos(new ExpNew(new Type((( String )(yystack.valueAt (3)))), ((LinkedList<RTExpression>)(yystack.valueAt (1)))), (yyloc));
  };
  break;


  case 191: /* new_exp: NEW IDENTIFIER '[' exp ']'  */
  if (yyn == 191)
    /* "VondaGrammar.y":735  */
                               {
    List<Type> sub = new ArrayList<Type>() {{ add(new Type((( String )(yystack.valueAt (3))))); }};
    yyval = setPos(new ExpNew(new Type("Array", sub),
                    new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (1)))); }}), (yyloc));
  };
  break;


  case 192: /* new_exp: NEW IDENTIFIER '[' ']' '(' exp ')'  */
  if (yyn == 192)
    /* "VondaGrammar.y":740  */
                                      {
    List<Type> sub = new ArrayList<Type>() {{ add(new Type((( String )(yystack.valueAt (5))))); }};
    yyval = setPos(new ExpNew(new Type("Array", sub),
                    new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (1)))); }}), (yyloc));
  };
  break;


  case 193: /* new_exp: NEW IDENTIFIER '<' type_spec_list '>' '(' ')'  */
  if (yyn == 193)
    /* "VondaGrammar.y":745  */
                                                  {
    yyval = setPos(new ExpNew(new Type((( String )(yystack.valueAt (5))), ((LinkedList<Type>)(yystack.valueAt (3)))),
                    new LinkedList<>()), (yyloc));
  };
  break;


  case 194: /* new_exp: NEW IDENTIFIER '<' type_spec_list '>' '(' nonempty_exp_list ')'  */
  if (yyn == 194)
    /* "VondaGrammar.y":749  */
                                                                    {
    yyval = setPos(new ExpNew(new Type((( String )(yystack.valueAt (6))), ((LinkedList<Type>)(yystack.valueAt (4)))),
                    ((LinkedList<RTExpression>)(yystack.valueAt (1)))), (yyloc));
  };
  break;


  case 195: /* lambda_exp: '(' opt_args_list ')' ARROW exp  */
  if (yyn == 195)
    /* "VondaGrammar.y":756  */
                                    {
    yyval = setPos(new ExpLambda(((LinkedList)(yystack.valueAt (3))), ((RTExpression)(yystack.valueAt (0)))), (yyloc));
  };
  break;


  case 196: /* lambda_exp: '(' opt_args_list ')' ARROW block  */
  if (yyn == 196)
    /* "VondaGrammar.y":759  */
                                      {
    yyval = setPos(new ExpLambda(((LinkedList)(yystack.valueAt (3))), ((StatAbstractBlock)(yystack.valueAt (0)))), (yyloc));
  };
  break;


  case 197: /* dialogueact_exp: '#' da_token '(' da_token da_args ')'  */
  if (yyn == 197)
    /* "VondaGrammar.y":766  */
                                          {
    yyval = setPos(new ExpDialogueAct(((RTExpression)(yystack.valueAt (4))), ((RTExpression)(yystack.valueAt (2))), ((LinkedList<RTExpression>)(yystack.valueAt (1)))), (yyloc));
  };
  break;


  case 198: /* da_token: '{' exp '}'  */
  if (yyn == 198)
    /* "VondaGrammar.y":772  */
                { yyval = ((RTExpression)(yystack.valueAt (1))); };
  break;


  case 199: /* da_token: IDENTIFIER  */
  if (yyn == 199)
    /* "VondaGrammar.y":775  */
               { yyval = setPos(new ExpLiteral((( String )(yystack.valueAt (0))), "String"), (yyloc)); };
  break;


  case 200: /* da_token: STRING  */
  if (yyn == 200)
    /* "VondaGrammar.y":776  */
           { yyval = setPos((( ExpLiteral )(yystack.valueAt (0))), (yyloc)); };
  break;


  case 201: /* da_token: WILDCARD  */
  if (yyn == 201)
    /* "VondaGrammar.y":777  */
             { yyval = setPos(new ExpLiteral((( String )(yystack.valueAt (0))), "String"), (yyloc)); };
  break;


  case 202: /* da_args: ',' da_token '=' da_token da_args  */
  if (yyn == 202)
    /* "VondaGrammar.y":781  */
                                       {
    yyval = ((LinkedList<RTExpression>)(yystack.valueAt (0))); ((LinkedList<RTExpression>)(yystack.valueAt (0))).addFirst(((RTExpression)(yystack.valueAt (1)))); ((LinkedList<RTExpression>)(yystack.valueAt (0))).addFirst(((RTExpression)(yystack.valueAt (3))));
  };
  break;


  case 203: /* da_args: %empty  */
  if (yyn == 203)
    /* "VondaGrammar.y":784  */
           { yyval = new LinkedList<RTExpression>(); };
  break;


  case 204: /* exp: ConditionalExpression  */
  if (yyn == 204)
    /* "VondaGrammar.y":788  */
                          { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 205: /* exp: assignment  */
  if (yyn == 205)
    /* "VondaGrammar.y":789  */
               { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 206: /* exp: new_exp  */
  if (yyn == 206)
    /* "VondaGrammar.y":790  */
            { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;



/* "VondaGrammar.java":2563  */

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

  private static final short yypact_ninf_ = -368;
  private static final short yytable_ninf_ = -200;

/* YYPACT[STATE-NUM] -- Index in YYTABLE of the portion describing
   STATE-NUM.  */
  private static final short[] yypact_ = yypact_init();
  private static final short[] yypact_init()
  {
    return new short[]
    {
     839,   125,    26,    47,   309,    89,   -25,   941,    99,   120,
     140,    -4,   156,  -368,   162,  -368,  -368,   680,   180,   201,
     211,   446,   452,  -368,  -368,  -368,   236,  -368,   641,  1357,
     227,   179,   839,  -368,    -1,   890,   890,  -368,  -368,  -368,
    -368,  -368,  -368,  -368,  -368,  -368,  -368,   890,   890,   890,
    -368,   767,   206,   -28,  -368,  -368,   -20,    65,   238,  -368,
     261,  -368,  -368,  -368,   231,   268,   277,   295,  -368,  -368,
     245,  -368,   319,  -368,   291,   304,   202,   324,    15,  1357,
     156,  -368,    -2,   107,  1357,   325,  -368,  1492,  1492,    35,
    -368,  1391,  1525,  1525,  1492,  1492,  -368,   -13,   340,   310,
     311,   312,   129,    40,   233,   -34,  -368,  -368,  -368,   151,
    -368,   -28,   316,   316,  -368,  -368,  -368,   328,  1357,  1357,
    1357,    72,  -368,   331,    79,   335,  1357,  1357,   339,   341,
      61,   371,   968,  1005,  1039,   345,  -368,  -368,  -368,  -368,
     941,   342,   337,  -368,  -368,   152,  1357,   345,   344,  -368,
    -368,   146,   345,   890,   890,  -368,  -368,  -368,  -368,  -368,
    -368,  -368,   -31,  1357,  -368,  1357,  1357,  -368,  1357,  1357,
     347,   348,  -368,  -368,  -368,  -368,  -368,  -368,  -368,  -368,
    -368,   350,    14,  -368,   333,   351,    71,   360,   198,  1357,
    1073,   362,   354,   264,  -368,    36,  -368,   364,   357,   273,
     123,  -368,  -368,  -368,  1357,   249,   358,  -368,  -368,  -368,
    -368,  1525,  1357,  1525,  1525,  1525,  1525,  1525,  1525,  1525,
    1525,  1525,  1525,  1525,  1525,  1525,  1525,  1525,  -368,  -368,
    -368,   359,   361,   365,  -368,  -368,  -368,  -368,   368,   373,
    -368,  -368,   372,  1357,  -368,   383,  -368,  1425,  -368,   367,
     377,   378,  1107,  -368,  -368,   379,   381,   376,  -368,  -368,
    -368,   388,   396,   245,   345,  -368,  -368,  -368,   398,   397,
     389,   400,   403,   405,   406,  -368,  -368,  1357,  -368,  -368,
     408,   303,   411,  1357,   412,   413,  1141,   414,   137,   941,
    -368,   216,  -368,   415,   416,  1178,  1215,   345,  1525,   340,
     418,   310,   311,   312,   129,    40,    40,   233,   233,   233,
     233,   -34,   -34,  -368,  -368,  -368,   416,  1357,   941,  -368,
    -368,   417,  -368,   270,   409,  -368,   -22,  -368,  1459,  1459,
    -368,   421,   410,  -368,   345,  -368,  -368,    12,   419,   431,
      29,   426,   436,  -368,  -368,  -368,  -368,  -368,  -368,   428,
    -368,   447,   316,   429,  -368,  1357,   941,   449,  1249,  1357,
     454,   451,   457,  -368,  -368,  -368,   455,   453,   456,   450,
    -368,  1357,  -368,   458,  -368,  -368,   398,   487,   465,  -368,
    -368,  -368,  1357,  -368,  -368,   398,   245,   467,   469,    60,
    -368,  -368,   476,   941,   470,  -368,   941,   941,   471,   472,
    -368,   941,  -368,  -368,  1357,  -368,   474,  -368,   416,  -368,
    1286,   398,  -368,   475,   473,  -368,  -368,  -368,  -368,  -368,
    -368,   941,  -368,  -368,   941,   941,  -368,   477,  1320,  -368,
    -368,  -368,  -368,    60,   245,  -368,  -368,  -368,  -368,  -368,
     478,  -368,   419,  -368,  -368
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
      16,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,    19,     0,    18,    17,     0,     0,     0,
       0,     0,     0,   170,   171,   173,   118,   172,     0,     0,
       0,     0,    16,     3,     0,    16,    16,    23,    35,    37,
      40,    41,    42,    38,    39,    43,    44,    16,    16,    16,
      36,   168,     0,     0,   165,   166,   167,     0,     0,   169,
       0,    55,    57,    58,     0,     0,     0,     0,    59,    82,
       0,    45,     0,    46,   168,     0,   118,     0,     0,     0,
       0,    21,     0,     0,     0,     0,   160,     0,     0,   161,
      53,     0,     0,     0,     0,     0,   168,   184,   123,   125,
     126,   128,   130,   132,   135,   140,   143,   147,   153,   154,
     157,   163,   167,   162,   204,   205,   206,     0,     0,     0,
       0,     0,   167,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,   187,   176,    49,    47,
      50,     0,     0,   200,   201,   118,     0,     0,     0,     1,
       2,   118,     0,    16,    16,    12,    11,    14,    15,     9,
      34,    10,     0,     0,   177,     0,     0,   186,     0,     0,
       0,     0,    25,   185,    24,    56,    78,    79,    80,    81,
     199,     0,     0,    86,     0,     0,     0,     0,   118,     0,
       0,     0,     0,     0,     4,     0,    20,     0,     0,   188,
     161,   150,   162,   149,     0,   161,     0,   151,   152,   155,
     156,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,   159,   158,
      54,     0,     0,     0,    27,    31,    26,    30,     0,     0,
      29,    28,   180,     0,   182,   179,    52,   100,   111,     0,
     114,   113,     0,    90,   117,     0,   120,     0,    51,    48,
     164,     0,     0,     0,     0,    13,     8,    87,   100,     0,
       0,     0,     0,     0,     0,    33,    32,     0,    83,    88,
       0,   118,     0,     0,     0,     0,     0,     0,     0,     0,
       6,     0,    22,     0,     0,     0,     0,     0,     0,   122,
       0,   124,   127,   129,   131,   133,   134,   138,   139,   136,
     137,   141,   142,   144,   145,   146,     0,     0,     0,   106,
     105,     0,   178,   161,     0,    99,     0,   112,     0,     0,
      91,     0,    93,   174,     0,   119,   198,     0,   203,     0,
     101,     0,     0,    84,   175,   108,   107,   110,   109,     0,
      85,     0,     0,     0,    71,     0,     0,     0,     0,     0,
       0,    60,     0,     5,    75,   189,     0,     0,     0,     0,
     148,     0,    77,     0,    62,   181,     0,     0,   102,   116,
     115,    92,     0,   121,    89,   100,     0,     0,     0,     0,
      63,    73,     0,     0,     0,    67,     0,     0,     0,     0,
      72,     0,     7,   190,     0,   191,     0,   183,     0,   103,
       0,     0,    94,     0,     0,   197,    98,    97,    96,    74,
      69,     0,    65,    66,     0,     0,    61,     0,     0,    76,
     196,   195,   104,     0,     0,    68,    64,    70,   192,   193,
       0,    95,   203,   194,   202
    };
  }

/* YYPGOTO[NTERM-NUM].  */
  private static final short[] yypgoto_ = yypgoto_init();
  private static final short[] yypgoto_init()
  {
    return new short[]
    {
    -368,   493,  -368,    28,  -368,  -368,    -6,   228,    30,  -368,
    -233,   390,  -368,  -368,   401,  -368,  -368,  -368,  -368,  -368,
    -368,  -368,    96,  -368,   286,  -262,   497,   100,  -267,  -367,
    -368,   148,   -15,   329,  -289,  -368,   323,   327,   322,   336,
     321,   116,   133,   117,   -88,   244,  -368,  -368,  -368,   114,
    -368,  -368,     0,    18,   -51,  -368,  -368,   157,  -368,  -368,
    -368,  -260,   101,   468
    };
  }

/* YYDEFGOTO[NTERM-NUM].  */
  private static final short[] yydefgoto_ = yydefgoto_init();
  private static final short[] yydefgoto_init()
  {
    return new short[]
    {
       0,    31,    32,    33,    34,    35,    82,    71,   139,   140,
      37,   141,    38,    39,    40,    41,    42,   190,    43,    44,
      45,    46,    73,    48,   136,   331,    49,   418,   324,   325,
      50,    96,   249,    75,   257,    97,    98,    99,   100,   101,
     102,   103,   104,   105,   106,   107,   108,   109,   110,   111,
      54,    55,   112,   113,   137,   245,   114,   115,   116,   250,
      59,   148,   387,   332
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
      56,   341,   164,   338,   207,   208,    83,    56,   369,   409,
       8,    80,   225,   267,   165,   166,   211,   130,    57,   268,
     378,   122,   122,   133,    69,    57,   187,   163,    56,   298,
     226,   227,    56,   366,   133,    56,    56,    72,    81,   123,
     125,   151,   194,   195,   432,   383,    57,    56,    56,    56,
      57,    56,   152,    57,    57,   212,   384,   188,   267,   189,
     164,   364,   385,   155,   156,    57,    57,    57,   133,    57,
      62,  -118,   219,   220,   193,   157,   158,   159,   292,   161,
     130,   376,   293,   372,   184,   132,   135,   122,   122,   133,
     204,    63,   122,   122,   122,   122,    47,   221,   222,   168,
     169,   170,   171,   242,   416,   202,   202,    28,   243,   172,
     202,   202,   202,   202,    53,   279,   234,   130,   413,   133,
     412,    53,   132,   236,   130,   133,   414,   204,    47,   132,
     153,    47,    47,    68,   204,    53,    53,   313,   314,   315,
      56,    76,    53,    47,    47,    47,    53,    47,    51,    53,
      53,   196,   197,    56,    56,    74,   417,    58,    57,   217,
     218,    53,    53,    53,    58,    53,   440,    60,   130,    61,
      78,    57,    57,   132,   442,   429,    74,   430,   204,   149,
      51,   265,   266,    51,    51,    58,   359,   228,   229,    58,
      79,   133,    58,    58,   322,    51,    51,    51,    81,    51,
     417,   184,  -199,   135,    58,    58,    58,   184,    58,   135,
     370,   122,    84,   122,   122,   122,   122,   122,   122,   122,
     122,   122,   122,   122,   122,   122,   122,   122,    36,   202,
     118,   202,   202,   202,   202,   202,   202,   202,   202,   202,
     202,   202,   202,   202,   202,   202,   183,   283,   162,    47,
      47,   119,   133,   184,    53,   135,   133,   184,   292,   135,
      36,   120,   362,    36,    36,   143,   144,    53,    53,   145,
     126,   127,   128,   129,   146,    36,    36,    36,   244,    36,
     176,   130,   174,   143,   144,   131,   132,   180,    74,    56,
     133,   134,   146,   135,   130,   223,   224,    58,   122,   132,
    -118,    51,    51,   133,   134,   175,   135,    57,   290,   291,
      58,    58,  -118,   379,   380,   130,   202,   177,    56,   361,
     132,  -101,   376,   295,   133,   134,   178,   135,   296,    52,
     297,   201,   203,   305,   306,   160,    57,    77,   209,   210,
     311,   312,   167,   173,   179,   181,   182,    64,   374,    65,
      66,    67,   307,   308,   309,   310,    56,   133,   184,   147,
     135,    52,   185,    52,    52,    52,   186,   199,   213,   214,
     133,   215,   230,   216,    57,   235,    52,    52,    52,   237,
      52,    36,    36,   240,    10,   241,   395,   151,   260,   254,
     259,   275,   276,    56,   263,   278,    56,    56,   167,   173,
     277,    56,   281,    53,   288,   289,   292,   191,   294,   298,
     316,    57,   319,   317,    57,    57,   318,   320,   327,    57,
     206,    56,   132,   420,    56,    56,   422,   423,   130,   328,
     329,   426,    53,   334,   335,   333,   336,    74,   337,    57,
     340,   343,    57,    57,   345,   344,    58,   346,   269,   347,
     348,   435,   350,   352,   436,   437,   354,   355,   358,   363,
     377,   401,   382,    28,   256,   375,    74,   371,   269,   381,
      53,   386,   280,   388,   284,    58,   262,   389,   378,   390,
     393,   264,    52,    52,    23,   117,    24,    25,   121,    27,
      23,   391,    24,    25,   124,    27,    29,   142,   400,    70,
     396,   402,    29,   404,    74,    70,   403,    53,   406,   408,
      53,    53,   405,    58,   410,    53,   282,   411,   415,   385,
     419,   421,   424,   425,   428,   150,   433,   434,   438,   443,
     258,   154,   246,   441,   299,    53,   302,   304,    53,    53,
     301,    74,     0,   444,    74,    74,     0,   192,     0,    74,
      58,   303,   198,    58,    58,     0,     0,     0,    58,   142,
       0,     0,     0,     0,     0,     0,     0,   351,     0,    74,
       0,     0,    74,    74,   360,     0,   326,     0,    58,     0,
       0,    58,    58,     0,     0,     0,   231,   232,   233,     0,
       0,     0,     0,   339,   238,   239,     0,   342,     0,     0,
     251,   253,   255,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,   261,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,   256,     0,     0,     0,
       0,   270,     0,   271,   272,     0,   273,   274,   392,     0,
       0,     0,     0,     0,     1,     2,     3,     4,     5,     6,
       7,     0,     8,     9,    10,     0,     0,   285,   287,     0,
       0,    14,     0,   256,    17,    18,    19,    20,     0,     0,
       0,     0,   255,     0,     0,     0,     0,    21,    22,    23,
     300,    24,    25,    26,    27,     0,     0,     0,    28,   138,
       0,    29,     0,     0,    70,     0,     0,    85,    86,     0,
       0,     0,     0,     0,     0,   342,     0,     0,     0,     0,
       0,   321,     0,     0,   342,   142,    87,    88,    23,     0,
      24,    25,    89,    27,    90,     0,     0,     0,     0,     0,
      91,     0,     0,    70,     0,     0,     0,     0,     0,     0,
     342,     0,    92,    93,     0,   349,    94,    95,     0,     0,
       0,   353,     0,     0,   357,     0,     0,     0,     0,     0,
       0,     0,     0,     0,   368,     0,     0,   -16,     0,     0,
       1,     2,     3,     4,     5,     6,     7,     0,     8,     9,
      10,     0,     0,    12,     0,   373,    13,    14,    15,    16,
      17,    18,    19,    20,     0,     0,   251,   251,     0,     0,
       0,     0,     0,    21,    22,    23,     0,    24,    25,    26,
      27,   160,     0,     0,    28,     0,     0,    29,     0,     0,
      30,     0,     0,   394,     0,     0,   398,   399,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,   407,
       0,     0,     1,     2,     3,     4,     5,     6,     7,     0,
       8,     9,    10,    11,     0,    12,     0,     0,    13,    14,
      15,    16,    17,    18,    19,    20,     0,     0,     0,     0,
       0,     0,   427,     0,     0,    21,    22,    23,   431,    24,
      25,    26,    27,     0,     0,     0,    28,     0,     0,    29,
       0,     0,    30,     1,     2,     3,     4,     5,     6,     7,
       0,     8,     9,    10,     0,     0,    12,     0,     0,    13,
      14,    15,    16,    17,    18,    19,    20,     0,     0,     0,
       0,     0,     0,     0,     0,     0,    21,    22,    23,     0,
      24,    25,    26,    27,     0,     0,     0,    28,     0,     0,
      29,     0,     0,    30,     1,     2,     3,     4,     5,     6,
       7,     0,     8,     9,    10,     0,     0,     0,     0,     0,
       0,    14,     0,     0,    17,    18,    19,    20,     0,     0,
       0,     0,     0,     0,     0,     0,     0,    21,    22,    23,
       0,    24,    25,    26,    27,    85,    86,     0,    28,     0,
       0,    29,     0,     0,    70,     0,     0,     0,     0,     0,
       0,     0,     0,     0,    87,    88,    23,     0,    24,    25,
      89,    27,     0,     0,     0,     0,     0,     0,   247,   248,
       0,    70,    85,    86,     0,     0,     0,     0,     0,     0,
      92,    93,     0,     0,    94,    95,     0,     0,     0,     0,
       0,    87,    88,    23,     0,    24,    25,    89,    27,     0,
       0,     0,   252,     0,     0,    91,    85,    86,    70,     0,
       0,     0,     0,     0,     0,     0,     0,    92,    93,     0,
       0,    94,    95,     0,     0,    87,    88,    23,     0,    24,
      25,    89,    27,     0,     0,     0,     0,     0,     0,    91,
      85,    86,    70,     0,     0,   254,     0,     0,     0,     0,
       0,    92,    93,     0,     0,    94,    95,     0,     0,    87,
      88,    23,     0,    24,    25,    89,    27,   286,     0,     0,
       0,     0,     0,    91,    85,    86,    70,     0,     0,     0,
       0,     0,     0,     0,     0,    92,    93,     0,     0,    94,
      95,     0,     0,    87,    88,    23,     0,    24,    25,    89,
      27,     0,     0,     0,     0,   330,     0,    91,    85,    86,
      70,     0,     0,     0,     0,     0,     0,     0,     0,    92,
      93,     0,     0,    94,    95,     0,     0,    87,    88,    23,
       0,    24,    25,    89,    27,     0,     0,     0,     0,     0,
       0,    91,   356,     0,    70,    85,    86,     0,     0,     0,
       0,     0,     0,    92,    93,     0,     0,    94,    95,     0,
       0,     0,     0,     0,    87,    88,    23,     0,    24,    25,
      89,    27,     0,     0,     0,     0,     0,     0,    91,   365,
       0,    70,    85,    86,     0,     0,     0,     0,     0,     0,
      92,    93,     0,     0,    94,    95,     0,     0,     0,     0,
       0,    87,    88,    23,     0,    24,    25,    89,    27,     0,
       0,     0,     0,     0,     0,    91,    85,    86,    70,     0,
       0,   367,     0,     0,     0,     0,     0,    92,    93,     0,
       0,    94,    95,     0,     0,    87,    88,    23,     0,    24,
      25,    89,    27,     0,     0,     0,     0,     0,     0,    91,
     397,     0,    70,    85,    86,     0,     0,     0,     0,     0,
       0,    92,    93,     0,     0,    94,    95,     0,     0,     0,
       0,     0,    87,    88,    23,     0,    24,    25,    89,    27,
       0,     0,     0,    28,     0,     0,    91,    85,    86,    70,
       0,     0,     0,     0,     0,     0,     0,     0,    92,    93,
       0,     0,    94,    95,     0,     0,    87,    88,    23,     0,
      24,    25,    89,    27,     0,     0,     0,     0,     0,     0,
      91,   439,     0,    70,    85,    86,     0,     0,     0,     0,
       0,     0,    92,    93,     0,     0,    94,    95,     0,     0,
       0,     0,     0,    87,    88,    23,     0,    24,    25,    89,
      27,     0,     0,     0,     0,     0,     0,    91,    85,    86,
      70,     0,     0,     0,     0,     0,     0,     0,     0,    92,
      93,     0,     0,    94,    95,     0,     0,    87,    88,    23,
       0,    24,    25,   205,    27,     0,     0,     0,     0,     0,
       0,    91,    85,    86,    70,     0,     0,     0,     0,     0,
       0,     0,     0,    92,    93,     0,     0,    94,    95,     0,
       0,    87,    88,    23,     0,    24,    25,   323,    27,     0,
       0,     0,     0,     0,     0,    91,    85,    86,    70,     0,
       0,     0,     0,     0,     0,     0,     0,    92,    93,     0,
       0,    94,    95,     0,     0,    87,    88,    23,     0,    24,
      25,    89,    27,     0,     0,     0,     0,     0,     0,   247,
      86,     0,    70,     0,     0,     0,     0,     0,     0,     0,
       0,    92,    93,     0,     0,    94,    95,     0,    87,    88,
      23,     0,    24,    25,   200,    27,     0,     0,     0,     0,
       0,     0,    29,    86,     0,    70,     0,     0,     0,     0,
       0,     0,     0,     0,    92,    93,     0,     0,    94,    95,
       0,    87,    88,    23,     0,    24,    25,   200,    27,     0,
       0,     0,     0,     0,     0,    91,     0,     0,    70,     0,
       0,     0,     0,     0,     0,     0,     0,    92,    93,     0,
       0,    94,    95
    };
  }

private static final short[] yycheck_ = yycheck_init();
  private static final short[] yycheck_init()
  {
    return new short[]
    {
       0,   268,    53,   263,    92,    93,    12,     7,   297,   376,
      11,    15,    46,    44,    34,    35,    29,    45,     0,    50,
      42,    21,    22,    54,    49,     7,    11,    55,    28,    51,
      64,    65,    32,   295,    54,    35,    36,     7,    42,    21,
      22,    42,    44,    45,   411,   334,    28,    47,    48,    49,
      32,    51,    53,    35,    36,    68,    44,    42,    44,    44,
     111,   294,    50,    35,    36,    47,    48,    49,    54,    51,
      44,    42,    32,    33,    80,    47,    48,    49,    42,    51,
      45,    52,    46,   316,    55,    50,    57,    87,    88,    54,
      55,    44,    92,    93,    94,    95,     0,    57,    58,    34,
      35,    36,    37,    42,    44,    87,    88,    47,    47,    44,
      92,    93,    94,    95,     0,    44,    44,    45,   385,    54,
     382,     7,    50,    44,    45,    54,   386,    55,    32,    50,
      34,    35,    36,    44,    55,    21,    22,   225,   226,   227,
     140,    42,    28,    47,    48,    49,    32,    51,     0,    35,
      36,    44,    45,   153,   154,     7,   389,     0,   140,    30,
      31,    47,    48,    49,     7,    51,   428,    42,    45,    44,
      50,   153,   154,    50,   434,   408,    28,   410,    55,     0,
      32,   153,   154,    35,    36,    28,    49,    36,    37,    32,
      50,    54,    35,    36,   245,    47,    48,    49,    42,    51,
     433,    55,    50,    57,    47,    48,    49,    55,    51,    57,
     298,   211,    50,   213,   214,   215,   216,   217,   218,   219,
     220,   221,   222,   223,   224,   225,   226,   227,     0,   211,
      50,   213,   214,   215,   216,   217,   218,   219,   220,   221,
     222,   223,   224,   225,   226,   227,    44,    49,    42,   153,
     154,    50,    54,    55,   140,    57,    54,    55,    42,    57,
      32,    50,    46,    35,    36,    38,    39,   153,   154,    42,
      34,    35,    36,    37,    47,    47,    48,    49,   130,    51,
      49,    45,    44,    38,    39,    49,    50,    42,   140,   289,
      54,    55,    47,    57,    45,    62,    63,   140,   298,    50,
      51,   153,   154,    54,    55,    44,    57,   289,    44,    45,
     153,   154,    42,   328,   329,    45,   298,    49,   318,   289,
      50,    51,    52,    50,    54,    55,    49,    57,    55,     0,
      57,    87,    88,   217,   218,    44,   318,     8,    94,    95,
     223,   224,    56,    57,    49,    26,    42,    38,   318,    40,
      41,    42,   219,   220,   221,   222,   356,    54,    55,    30,
      57,    32,    76,    34,    35,    36,    42,    42,    28,    59,
      54,    60,    44,    61,   356,    44,    47,    48,    49,    44,
      51,   153,   154,    44,    13,    44,   356,    42,    51,    56,
      48,    44,    44,   393,    50,    44,   396,   397,   112,   113,
      50,   401,    42,   289,    42,    51,    42,    78,    51,    51,
      51,   393,    44,    52,   396,   397,    51,    44,    51,   401,
      91,   421,    50,   393,   424,   425,   396,   397,    45,    52,
      52,   401,   318,    52,    58,    56,    48,   289,    42,   421,
      42,    44,   424,   425,    44,    56,   289,    44,   162,    44,
      44,   421,    44,    42,   424,   425,    44,    44,    44,    44,
      51,    10,    52,    47,   135,    48,   318,    49,   182,    48,
     356,    52,   186,    42,   188,   318,   147,    51,    42,    51,
      51,   152,   153,   154,    38,    17,    40,    41,    42,    43,
      38,    44,    40,    41,    42,    43,    50,    29,    44,    53,
      51,    44,    50,    50,   356,    53,    51,   393,    58,    51,
     396,   397,    56,   356,    27,   401,   187,    52,    51,    50,
      44,    51,    51,    51,    50,    32,    51,    54,    51,    51,
     140,    34,   131,   433,   211,   421,   214,   216,   424,   425,
     213,   393,    -1,   442,   396,   397,    -1,    79,    -1,   401,
     393,   215,    84,   396,   397,    -1,    -1,    -1,   401,    91,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,   281,    -1,   421,
      -1,    -1,   424,   425,   288,    -1,   247,    -1,   421,    -1,
      -1,   424,   425,    -1,    -1,    -1,   118,   119,   120,    -1,
      -1,    -1,    -1,   264,   126,   127,    -1,   268,    -1,    -1,
     132,   133,   134,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,   146,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,   297,    -1,    -1,    -1,
      -1,   163,    -1,   165,   166,    -1,   168,   169,   352,    -1,
      -1,    -1,    -1,    -1,     3,     4,     5,     6,     7,     8,
       9,    -1,    11,    12,    13,    -1,    -1,   189,   190,    -1,
      -1,    20,    -1,   334,    23,    24,    25,    26,    -1,    -1,
      -1,    -1,   204,    -1,    -1,    -1,    -1,    36,    37,    38,
     212,    40,    41,    42,    43,    -1,    -1,    -1,    47,    48,
      -1,    50,    -1,    -1,    53,    -1,    -1,    17,    18,    -1,
      -1,    -1,    -1,    -1,    -1,   376,    -1,    -1,    -1,    -1,
      -1,   243,    -1,    -1,   385,   247,    36,    37,    38,    -1,
      40,    41,    42,    43,    44,    -1,    -1,    -1,    -1,    -1,
      50,    -1,    -1,    53,    -1,    -1,    -1,    -1,    -1,    -1,
     411,    -1,    62,    63,    -1,   277,    66,    67,    -1,    -1,
      -1,   283,    -1,    -1,   286,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,   296,    -1,    -1,     0,    -1,    -1,
       3,     4,     5,     6,     7,     8,     9,    -1,    11,    12,
      13,    -1,    -1,    16,    -1,   317,    19,    20,    21,    22,
      23,    24,    25,    26,    -1,    -1,   328,   329,    -1,    -1,
      -1,    -1,    -1,    36,    37,    38,    -1,    40,    41,    42,
      43,    44,    -1,    -1,    47,    -1,    -1,    50,    -1,    -1,
      53,    -1,    -1,   355,    -1,    -1,   358,   359,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,   371,
      -1,    -1,     3,     4,     5,     6,     7,     8,     9,    -1,
      11,    12,    13,    14,    -1,    16,    -1,    -1,    19,    20,
      21,    22,    23,    24,    25,    26,    -1,    -1,    -1,    -1,
      -1,    -1,   404,    -1,    -1,    36,    37,    38,   410,    40,
      41,    42,    43,    -1,    -1,    -1,    47,    -1,    -1,    50,
      -1,    -1,    53,     3,     4,     5,     6,     7,     8,     9,
      -1,    11,    12,    13,    -1,    -1,    16,    -1,    -1,    19,
      20,    21,    22,    23,    24,    25,    26,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    36,    37,    38,    -1,
      40,    41,    42,    43,    -1,    -1,    -1,    47,    -1,    -1,
      50,    -1,    -1,    53,     3,     4,     5,     6,     7,     8,
       9,    -1,    11,    12,    13,    -1,    -1,    -1,    -1,    -1,
      -1,    20,    -1,    -1,    23,    24,    25,    26,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    36,    37,    38,
      -1,    40,    41,    42,    43,    17,    18,    -1,    47,    -1,
      -1,    50,    -1,    -1,    53,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    36,    37,    38,    -1,    40,    41,
      42,    43,    -1,    -1,    -1,    -1,    -1,    -1,    50,    51,
      -1,    53,    17,    18,    -1,    -1,    -1,    -1,    -1,    -1,
      62,    63,    -1,    -1,    66,    67,    -1,    -1,    -1,    -1,
      -1,    36,    37,    38,    -1,    40,    41,    42,    43,    -1,
      -1,    -1,    47,    -1,    -1,    50,    17,    18,    53,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    62,    63,    -1,
      -1,    66,    67,    -1,    -1,    36,    37,    38,    -1,    40,
      41,    42,    43,    -1,    -1,    -1,    -1,    -1,    -1,    50,
      17,    18,    53,    -1,    -1,    56,    -1,    -1,    -1,    -1,
      -1,    62,    63,    -1,    -1,    66,    67,    -1,    -1,    36,
      37,    38,    -1,    40,    41,    42,    43,    44,    -1,    -1,
      -1,    -1,    -1,    50,    17,    18,    53,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    62,    63,    -1,    -1,    66,
      67,    -1,    -1,    36,    37,    38,    -1,    40,    41,    42,
      43,    -1,    -1,    -1,    -1,    48,    -1,    50,    17,    18,
      53,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    62,
      63,    -1,    -1,    66,    67,    -1,    -1,    36,    37,    38,
      -1,    40,    41,    42,    43,    -1,    -1,    -1,    -1,    -1,
      -1,    50,    51,    -1,    53,    17,    18,    -1,    -1,    -1,
      -1,    -1,    -1,    62,    63,    -1,    -1,    66,    67,    -1,
      -1,    -1,    -1,    -1,    36,    37,    38,    -1,    40,    41,
      42,    43,    -1,    -1,    -1,    -1,    -1,    -1,    50,    51,
      -1,    53,    17,    18,    -1,    -1,    -1,    -1,    -1,    -1,
      62,    63,    -1,    -1,    66,    67,    -1,    -1,    -1,    -1,
      -1,    36,    37,    38,    -1,    40,    41,    42,    43,    -1,
      -1,    -1,    -1,    -1,    -1,    50,    17,    18,    53,    -1,
      -1,    56,    -1,    -1,    -1,    -1,    -1,    62,    63,    -1,
      -1,    66,    67,    -1,    -1,    36,    37,    38,    -1,    40,
      41,    42,    43,    -1,    -1,    -1,    -1,    -1,    -1,    50,
      51,    -1,    53,    17,    18,    -1,    -1,    -1,    -1,    -1,
      -1,    62,    63,    -1,    -1,    66,    67,    -1,    -1,    -1,
      -1,    -1,    36,    37,    38,    -1,    40,    41,    42,    43,
      -1,    -1,    -1,    47,    -1,    -1,    50,    17,    18,    53,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    62,    63,
      -1,    -1,    66,    67,    -1,    -1,    36,    37,    38,    -1,
      40,    41,    42,    43,    -1,    -1,    -1,    -1,    -1,    -1,
      50,    51,    -1,    53,    17,    18,    -1,    -1,    -1,    -1,
      -1,    -1,    62,    63,    -1,    -1,    66,    67,    -1,    -1,
      -1,    -1,    -1,    36,    37,    38,    -1,    40,    41,    42,
      43,    -1,    -1,    -1,    -1,    -1,    -1,    50,    17,    18,
      53,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    62,
      63,    -1,    -1,    66,    67,    -1,    -1,    36,    37,    38,
      -1,    40,    41,    42,    43,    -1,    -1,    -1,    -1,    -1,
      -1,    50,    17,    18,    53,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    62,    63,    -1,    -1,    66,    67,    -1,
      -1,    36,    37,    38,    -1,    40,    41,    42,    43,    -1,
      -1,    -1,    -1,    -1,    -1,    50,    17,    18,    53,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    62,    63,    -1,
      -1,    66,    67,    -1,    -1,    36,    37,    38,    -1,    40,
      41,    42,    43,    -1,    -1,    -1,    -1,    -1,    -1,    50,
      18,    -1,    53,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    62,    63,    -1,    -1,    66,    67,    -1,    36,    37,
      38,    -1,    40,    41,    42,    43,    -1,    -1,    -1,    -1,
      -1,    -1,    50,    18,    -1,    53,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    62,    63,    -1,    -1,    66,    67,
      -1,    36,    37,    38,    -1,    40,    41,    42,    43,    -1,
      -1,    -1,    -1,    -1,    -1,    50,    -1,    -1,    53,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    62,    63,    -1,
      -1,    66,    67
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
      13,    14,    16,    19,    20,    21,    22,    23,    24,    25,
      26,    36,    37,    38,    40,    41,    42,    43,    47,    50,
      53,    70,    71,    72,    73,    74,    76,    79,    81,    82,
      83,    84,    85,    87,    88,    89,    90,    91,    92,    95,
      99,   100,   102,   118,   119,   120,   121,   122,   126,   129,
      42,    44,    44,    44,    38,    40,    41,    42,    44,    49,
      53,    76,    77,    91,   100,   102,    42,   102,    50,    50,
      15,    42,    75,    75,    50,    17,    18,    36,    37,    42,
      44,    50,    62,    63,    66,    67,   100,   104,   105,   106,
     107,   108,   109,   110,   111,   112,   113,   114,   115,   116,
     117,   118,   121,   122,   125,   126,   127,   132,    50,    50,
      50,    42,   121,   122,    42,   122,    34,    35,    36,    37,
      45,    49,    50,    54,    55,    57,    93,   123,    48,    77,
      78,    80,   132,    38,    39,    42,    47,   102,   130,     0,
      70,    42,    53,    91,    95,    72,    72,    72,    72,    72,
      44,    72,    42,    55,   123,    34,    35,    93,    34,    35,
      36,    37,    44,    93,    44,    44,    49,    49,    49,    49,
      42,    26,    42,    44,    55,    93,    42,    11,    42,    44,
      86,   102,   132,    75,    44,    45,    44,    45,   132,    42,
      42,   114,   122,   114,    55,    42,   102,   113,   113,   114,
     114,    29,    68,    28,    59,    60,    61,    30,    31,    32,
      33,    57,    58,    62,    63,    46,    64,    65,    36,    37,
      44,   132,   132,   132,    44,    44,    44,    44,   132,   132,
      44,    44,    42,    47,   100,   124,    83,    50,    51,   101,
     128,   132,    47,   132,    56,   132,   102,   103,    80,    48,
      51,   132,   102,    50,   102,    72,    72,    44,    50,    93,
     132,   132,   132,   132,   132,    44,    44,    50,    44,    44,
      93,    42,   102,    49,    93,   132,    44,   132,    42,    51,
      44,    45,    42,    46,    51,    50,    55,    57,    51,   105,
     132,   106,   107,   108,   109,   110,   110,   111,   111,   111,
     111,   112,   112,   113,   113,   113,    51,    52,    51,    44,
      44,   132,   123,    42,    97,    98,   102,    51,    52,    52,
      48,    94,   132,    56,    52,    58,    48,    42,   130,   102,
      42,    97,   102,    44,    56,    44,    44,    44,    44,   132,
      44,    93,    42,   132,    44,    44,    51,   132,    44,    49,
      93,    77,    46,    44,    79,    51,    94,    56,   132,   103,
     113,    49,    79,   132,    77,    48,    52,    51,    42,   101,
     101,    48,    52,   103,    44,    50,    52,   131,    42,    51,
      51,    44,    93,    51,   132,    77,    51,    51,   132,   132,
      44,    10,    44,    51,    50,    56,    58,   132,    51,    98,
      27,    52,    94,    97,   130,    51,    44,    79,    96,    44,
      77,    51,    77,    77,    51,    51,    77,   132,    50,    79,
      79,   132,    98,    51,    54,    77,    77,    77,    51,    51,
      94,    96,   130,    51,   131
    };
  }

/* YYR1[RULE-NUM] -- Symbol kind of the left-hand side of rule RULE-NUM.  */
  private static final short[] yyr1_ = yyr1_init();
  private static final short[] yyr1_init()
  {
    return new short[]
    {
       0,    69,    70,    70,    71,    71,    71,    71,    72,    72,
      72,    72,    72,    72,    72,    72,    72,    73,    73,    73,
      74,    75,    75,    76,    76,    76,    76,    76,    76,    76,
      76,    76,    76,    76,    76,    76,    76,    76,    76,    76,
      76,    76,    76,    76,    76,    77,    77,    78,    79,    79,
      80,    80,    81,    82,    82,    82,    82,    82,    82,    82,
      83,    83,    84,    84,    85,    85,    85,    85,    85,    85,
      85,    86,    86,    86,    86,    87,    88,    89,    90,    90,
      90,    90,    90,    91,    91,    91,    91,    91,    91,    92,
      93,    93,    93,    94,    94,    95,    95,    96,    96,    97,
      97,    98,    98,    98,    98,    99,    99,    99,    99,    99,
      99,   100,   100,   101,   101,   101,   101,   102,   102,   102,
     103,   103,   104,   104,   105,   105,   106,   106,   107,   107,
     108,   108,   109,   109,   109,   110,   110,   110,   110,   110,
     111,   111,   111,   112,   112,   112,   112,   113,   113,   114,
     114,   114,   114,   114,   115,   115,   115,   116,   116,   116,
     117,   117,   117,   117,   118,   118,   119,   119,   119,   119,
     120,   120,   120,   120,   121,   121,   122,   122,   123,   123,
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
       0,     2,     2,     1,     3,     5,     4,     6,     3,     2,
       2,     2,     2,     3,     2,     2,     0,     1,     1,     1,
       3,     1,     3,     1,     2,     2,     3,     3,     3,     3,
       3,     3,     3,     3,     2,     1,     1,     1,     1,     1,
       1,     1,     1,     1,     1,     1,     1,     1,     3,     2,
       1,     2,     3,     2,     3,     2,     3,     2,     2,     2,
       5,     7,     5,     6,     8,     7,     7,     6,     8,     7,
       8,     3,     4,     4,     5,     5,     7,     5,     3,     3,
       3,     3,     2,     4,     4,     5,     3,     3,     4,     5,
       2,     3,     4,     1,     3,     8,     6,     1,     1,     1,
       0,     1,     2,     3,     4,     4,     4,     4,     4,     4,
       4,     3,     4,     1,     1,     3,     3,     3,     1,     4,
       1,     3,     3,     1,     3,     1,     1,     3,     1,     3,
       1,     3,     1,     3,     3,     1,     3,     3,     3,     3,
       1,     3,     3,     1,     3,     3,     3,     1,     4,     2,
       2,     2,     2,     1,     1,     2,     2,     1,     2,     2,
       1,     1,     1,     1,     3,     1,     1,     1,     1,     1,
       1,     1,     1,     1,     4,     4,     2,     2,     3,     2,
       1,     3,     1,     5,     1,     2,     2,     2,     2,     4,
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
       0,   143,   143,   144,   148,   149,   153,   154,   160,   163,
     164,   165,   167,   168,   171,   174,   177,   181,   182,   183,
     187,   195,   196,   200,   201,   202,   203,   207,   211,   215,
     219,   222,   225,   228,   231,   232,   233,   234,   235,   236,
     237,   238,   239,   240,   241,   245,   246,   250,   256,   257,
     261,   262,   266,   270,   271,   272,   273,   274,   275,   276,
     280,   281,   287,   288,   294,   296,   298,   300,   302,   304,
     308,   317,   322,   327,   332,   340,   344,   350,   357,   364,
     371,   378,   385,   394,   399,   404,   409,   412,   415,   421,
     428,   429,   432,   438,   439,   444,   447,   453,   454,   458,
     459,   463,   464,   465,   469,   477,   481,   485,   488,   491,
     494,   503,   506,   512,   513,   514,   515,   519,   523,   524,
     528,   529,   533,   536,   540,   543,   547,   548,   554,   555,
     561,   562,   568,   569,   572,   578,   579,   582,   585,   588,
     594,   595,   598,   604,   605,   608,   611,   617,   618,   622,
     625,   628,   629,   630,   634,   635,   636,   640,   641,   644,
     650,   651,   652,   653,   657,   658,   662,   663,   664,   665,
     669,   670,   671,   672,   676,   680,   684,   689,   696,   697,
     705,   706,   707,   711,   712,   718,   719,   720,   728,   729,
     732,   735,   740,   745,   749,   756,   759,   766,   772,   775,
     776,   777,   781,   784,   788,   789,   790
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
    int code_max = 298;
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
       2,     2,     2,    66,     2,    53,     2,    65,    61,     2,
      50,    51,    46,    62,    52,    63,    45,    64,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,    49,    44,
      57,    54,    58,    68,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,    55,     2,    56,    60,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,    47,    59,    48,    67,     2,     2,     2,
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
      35,    36,    37,    38,    39,    40,    41,    42,    43
    };
  }


  private static final int YYLAST_ = 1592;
  private static final int YYEMPTY_ = -2;
  private static final int YYFINAL_ = 149;
  private static final int YYNTOKENS_ = 69;

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

/* "VondaGrammar.java":3777  */

}
