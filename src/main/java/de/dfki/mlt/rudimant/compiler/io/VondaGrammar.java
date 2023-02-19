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
    S_ISA(27),                     /* ISA  */
    S_LAMBDA(28),                  /* LAMBDA  */
    S_ANDAND(29),                  /* ANDAND  */
    S_OROR(30),                    /* OROR  */
    S_EQEQ(31),                    /* EQEQ  */
    S_NOTEQ(32),                   /* NOTEQ  */
    S_GTEQ(33),                    /* GTEQ  */
    S_LTEQ(34),                    /* LTEQ  */
    S_MINUSEQ(35),                 /* MINUSEQ  */
    S_PLUSEQ(36),                  /* PLUSEQ  */
    S_MINUSMINUS(37),              /* MINUSMINUS  */
    S_PLUSPLUS(38),                /* PLUSPLUS  */
    S_STRING(39),                  /* STRING  */
    S_WILDCARD(40),                /* WILDCARD  */
    S_INT(41),                     /* INT  */
    S_BOOL_LITERAL(42),            /* BOOL_LITERAL  */
    S_IDENTIFIER(43),              /* IDENTIFIER  */
    S_OTHER_LITERAL(44),           /* OTHER_LITERAL  */
    S_45_(45),                     /* ';'  */
    S_46_(46),                     /* '.'  */
    S_47_(47),                     /* '*'  */
    S_48_(48),                     /* '{'  */
    S_49_(49),                     /* '}'  */
    S_50_(50),                     /* ':'  */
    S_51_(51),                     /* '('  */
    S_52_(52),                     /* ')'  */
    S_53_(53),                     /* ','  */
    S_54_(54),                     /* '#'  */
    S_55_(55),                     /* '='  */
    S_56_(56),                     /* '['  */
    S_57_(57),                     /* ']'  */
    S_58_(58),                     /* '<'  */
    S_59_(59),                     /* '>'  */
    S_60_(60),                     /* '|'  */
    S_61_(61),                     /* '^'  */
    S_62_(62),                     /* '&'  */
    S_63_(63),                     /* '+'  */
    S_64_(64),                     /* '-'  */
    S_65_(65),                     /* '/'  */
    S_66_(66),                     /* '%'  */
    S_67_(67),                     /* '!'  */
    S_68_(68),                     /* '~'  */
    S_69_(69),                     /* '?'  */
    S_YYACCEPT(70),                /* $accept  */
    S_root(71),                    /* root  */
    S_imports(72),                 /* imports  */
    S_grammar_file(73),            /* grammar_file  */
    S_visibility_spec(74),         /* visibility_spec  */
    S_includes(75),                /* includes  */
    S_path(76),                    /* path  */
    S_statement_no_def(77),        /* statement_no_def  */
    S_statement(78),               /* statement  */
    S_blk_statement(79),           /* blk_statement  */
    S_block(80),                   /* block  */
    S_statements(81),              /* statements  */
    S_grammar_rule(82),            /* grammar_rule  */
    S_return_statement(83),        /* return_statement  */
    S_if_statement(84),            /* if_statement  */
    S_while_statement(85),         /* while_statement  */
    S_for_statement(86),           /* for_statement  */
    S_var_decl(87),                /* var_decl  */
    S_propose_statement(88),       /* propose_statement  */
    S_timeout_statement(89),       /* timeout_statement  */
    S_switch_statement(90),        /* switch_statement  */
    S_label_statement(91),         /* label_statement  */
    S_var_def(92),                 /* var_def  */
    S_field_def(93),               /* field_def  */
    S_assgn_exp(94),               /* assgn_exp  */
    S_nonempty_exp_list(95),       /* nonempty_exp_list  */
    S_method_declaration(96),      /* method_declaration  */
    S_opt_block(97),               /* opt_block  */
    S_opt_args_list(98),           /* opt_args_list  */
    S_args_list(99),               /* args_list  */
    S_set_operation(100),          /* set_operation  */
    S_function_call(101),          /* function_call  */
    S_nonempty_args_list(102),     /* nonempty_args_list  */
    S_type_spec(103),              /* type_spec  */
    S_type_spec_list(104),         /* type_spec_list  */
    S_ConditionalOrExpression(105), /* ConditionalOrExpression  */
    S_ConditionalAndExpression(106), /* ConditionalAndExpression  */
    S_InclusiveOrExpression(107),  /* InclusiveOrExpression  */
    S_ExclusiveOrExpression(108),  /* ExclusiveOrExpression  */
    S_AndExpression(109),          /* AndExpression  */
    S_EqualityExpression(110),     /* EqualityExpression  */
    S_RelationalExpression(111),   /* RelationalExpression  */
    S_AdditiveExpression(112),     /* AdditiveExpression  */
    S_MultiplicativeExpression(113), /* MultiplicativeExpression  */
    S_CastExpression(114),         /* CastExpression  */
    S_UnaryExpression(115),        /* UnaryExpression  */
    S_LogicalUnaryExpression(116), /* LogicalUnaryExpression  */
    S_PostfixExpression(117),      /* PostfixExpression  */
    S_PrimaryExpression(118),      /* PrimaryExpression  */
    S_ComplexPrimary(119),         /* ComplexPrimary  */
    S_ComplexPrimaryNoParenthesis(120), /* ComplexPrimaryNoParenthesis  */
    S_Literal(121),                /* Literal  */
    S_ArrayAccess(122),            /* ArrayAccess  */
    S_field_access(123),           /* field_access  */
    S_field_access_rest(124),      /* field_access_rest  */
    S_simple_nofa_exp(125),        /* simple_nofa_exp  */
    S_ConditionalExpression(126),  /* ConditionalExpression  */
    S_assignment(127),             /* assignment  */
    S_new_exp(128),                /* new_exp  */
    S_lambda_exp(129),             /* lambda_exp  */
    S_dialogueact_exp(130),        /* dialogueact_exp  */
    S_da_token(131),               /* da_token  */
    S_da_args(132),                /* da_args  */
    S_exp(133);                    /* exp  */


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
      SymbolKind.S_ISA,
      SymbolKind.S_LAMBDA,
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
      SymbolKind.S_69_,
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
  "ISA", "LAMBDA", "ANDAND", "OROR", "EQEQ", "NOTEQ", "GTEQ", "LTEQ",
  "MINUSEQ", "PLUSEQ", "MINUSMINUS", "PLUSPLUS", "STRING", "WILDCARD",
  "INT", "BOOL_LITERAL", "IDENTIFIER", "OTHER_LITERAL", "';'", "'.'",
  "'*'", "'{'", "'}'", "':'", "'('", "')'", "','", "'#'", "'='", "'['",
  "']'", "'<'", "'>'", "'|'", "'^'", "'&'", "'+'", "'-'", "'/'", "'%'",
  "'!'", "'~'", "'?'", "$accept", "root", "imports", "grammar_file",
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
    /** Token ISA, to be returned by the scanner.  */
    static final int ISA = 282;
    /** Token LAMBDA, to be returned by the scanner.  */
    static final int LAMBDA = 283;
    /** Token ANDAND, to be returned by the scanner.  */
    static final int ANDAND = 284;
    /** Token OROR, to be returned by the scanner.  */
    static final int OROR = 285;
    /** Token EQEQ, to be returned by the scanner.  */
    static final int EQEQ = 286;
    /** Token NOTEQ, to be returned by the scanner.  */
    static final int NOTEQ = 287;
    /** Token GTEQ, to be returned by the scanner.  */
    static final int GTEQ = 288;
    /** Token LTEQ, to be returned by the scanner.  */
    static final int LTEQ = 289;
    /** Token MINUSEQ, to be returned by the scanner.  */
    static final int MINUSEQ = 290;
    /** Token PLUSEQ, to be returned by the scanner.  */
    static final int PLUSEQ = 291;
    /** Token MINUSMINUS, to be returned by the scanner.  */
    static final int MINUSMINUS = 292;
    /** Token PLUSPLUS, to be returned by the scanner.  */
    static final int PLUSPLUS = 293;
    /** Token STRING, to be returned by the scanner.  */
    static final int STRING = 294;
    /** Token WILDCARD, to be returned by the scanner.  */
    static final int WILDCARD = 295;
    /** Token INT, to be returned by the scanner.  */
    static final int INT = 296;
    /** Token BOOL_LITERAL, to be returned by the scanner.  */
    static final int BOOL_LITERAL = 297;
    /** Token IDENTIFIER, to be returned by the scanner.  */
    static final int IDENTIFIER = 298;
    /** Token OTHER_LITERAL, to be returned by the scanner.  */
    static final int OTHER_LITERAL = 299;

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
    /* "VondaGrammar.y":144  */
                 { yyval = ((LinkedList<RudiTree>)(yystack.valueAt (0))); ((LinkedList<RudiTree>)(yystack.valueAt (0))).addFirst(((Import)(yystack.valueAt (1)))); };
  break;


  case 3: /* root: grammar_file  */
  if (yyn == 3)
    /* "VondaGrammar.y":145  */
                 { yyval = ((LinkedList<RudiTree>)(yystack.valueAt (0)));};
  break;


  case 4: /* imports: IMPORT path ';'  */
  if (yyn == 4)
    /* "VondaGrammar.y":149  */
                    { yyval = setPos(new Import(((List<String>)(yystack.valueAt (1)))), (yyloc)); };
  break;


  case 5: /* imports: IMPORT path '.' '*' ';'  */
  if (yyn == 5)
    /* "VondaGrammar.y":150  */
                            {
    ((List<String>)(yystack.valueAt (3))).add("*");
    yyval = setPos(new Import(((List<String>)(yystack.valueAt (3)))), (yyloc));
  };
  break;


  case 6: /* imports: IMPORT STATIC path ';'  */
  if (yyn == 6)
    /* "VondaGrammar.y":154  */
                           { yyval = setPos(new Import(((List<String>)(yystack.valueAt (1))), true), (yyloc)); };
  break;


  case 7: /* imports: IMPORT STATIC path '.' '*' ';'  */
  if (yyn == 7)
    /* "VondaGrammar.y":155  */
                                   {
    ((List<String>)(yystack.valueAt (3))).add("*");
    yyval = setPos(new Import(((List<String>)(yystack.valueAt (3))), true), (yyloc));
  };
  break;


  case 8: /* grammar_file: visibility_spec method_declaration grammar_file  */
  if (yyn == 8)
    /* "VondaGrammar.y":161  */
                                                    {
    yyval = ((LinkedList<RudiTree>)(yystack.valueAt (0))); ((StatMethodDeclaration)(yystack.valueAt (1))).setVisibility(((String)(yystack.valueAt (2)))); ((LinkedList<RudiTree>)(yystack.valueAt (0))).addFirst(((StatMethodDeclaration)(yystack.valueAt (1))));
  };
  break;


  case 9: /* grammar_file: method_declaration grammar_file  */
  if (yyn == 9)
    /* "VondaGrammar.y":164  */
                                    { yyval = ((LinkedList<RudiTree>)(yystack.valueAt (0))); ((LinkedList<RudiTree>)(yystack.valueAt (0))).addFirst(((StatMethodDeclaration)(yystack.valueAt (1)))); };
  break;


  case 10: /* grammar_file: function_call grammar_file  */
  if (yyn == 10)
    /* "VondaGrammar.y":165  */
                               { yyval = ((LinkedList<RudiTree>)(yystack.valueAt (0))); ((LinkedList<RudiTree>)(yystack.valueAt (0))).addFirst(((RTExpression)(yystack.valueAt (1)))); };
  break;


  case 11: /* grammar_file: statement_no_def grammar_file  */
  if (yyn == 11)
    /* "VondaGrammar.y":166  */
                                  { yyval = ((LinkedList<RudiTree>)(yystack.valueAt (0))); ((LinkedList<RudiTree>)(yystack.valueAt (0))).addFirst(((RTStatement)(yystack.valueAt (1)))); };
  break;


  case 12: /* grammar_file: includes grammar_file  */
  if (yyn == 12)
    /* "VondaGrammar.y":168  */
                          { yyval = ((LinkedList<RudiTree>)(yystack.valueAt (0))); ((LinkedList<RudiTree>)(yystack.valueAt (0))).addFirst(((Include)(yystack.valueAt (1)))); };
  break;


  case 13: /* grammar_file: visibility_spec var_def grammar_file  */
  if (yyn == 13)
    /* "VondaGrammar.y":169  */
                                          {
    yyval = ((LinkedList<RudiTree>)(yystack.valueAt (0))); ((LinkedList<RudiTree>)(yystack.valueAt (0))).addFirst(setPos(new StatFieldDef(((String)(yystack.valueAt (2))), ((StatVarDef)(yystack.valueAt (1)))), yystack.locationAt (2), yystack.locationAt (1)));
  };
  break;


  case 14: /* grammar_file: var_def grammar_file  */
  if (yyn == 14)
    /* "VondaGrammar.y":172  */
                          {
    yyval = ((LinkedList<RudiTree>)(yystack.valueAt (0))); ((LinkedList<RudiTree>)(yystack.valueAt (0))).addFirst(setPos(new StatFieldDef(null, ((StatVarDef)(yystack.valueAt (1)))), yystack.locationAt (1)));
  };
  break;


  case 15: /* grammar_file: field_def grammar_file  */
  if (yyn == 15)
    /* "VondaGrammar.y":175  */
                           {
    yyval = ((LinkedList<RudiTree>)(yystack.valueAt (0))); ((LinkedList<RudiTree>)(yystack.valueAt (0))).addFirst(((StatFieldDef)(yystack.valueAt (1))));
  };
  break;


  case 16: /* grammar_file: %empty  */
  if (yyn == 16)
    /* "VondaGrammar.y":178  */
           { yyval = _statements;};
  break;


  case 17: /* visibility_spec: PUBLIC  */
  if (yyn == 17)
    /* "VondaGrammar.y":182  */
           { yyval = "public"; };
  break;


  case 18: /* visibility_spec: PROTECTED  */
  if (yyn == 18)
    /* "VondaGrammar.y":183  */
              { yyval = "protected"; };
  break;


  case 19: /* visibility_spec: PRIVATE  */
  if (yyn == 19)
    /* "VondaGrammar.y":184  */
             { yyval = "private"; };
  break;


  case 20: /* includes: INCLUDE path ';'  */
  if (yyn == 20)
    /* "VondaGrammar.y":188  */
                     {
    List<String> path = ((List<String>)(yystack.valueAt (1)));
    String name = path.remove(path.size() - 1);
    yyval = setPos(new Include(name, path.toArray(new String[path.size()])), (yyloc));
  };
  break;


  case 21: /* path: IDENTIFIER  */
  if (yyn == 21)
    /* "VondaGrammar.y":196  */
               { yyval = new ArrayList<String>(){{ add((( String )(yystack.valueAt (0)))); }}; };
  break;


  case 22: /* path: path '.' IDENTIFIER  */
  if (yyn == 22)
    /* "VondaGrammar.y":197  */
                        { yyval = ((List<String>)(yystack.valueAt (2))); ((List<String>)(yystack.valueAt (2))).add((( String )(yystack.valueAt (0)))); };
  break;


  case 23: /* statement_no_def: block  */
  if (yyn == 23)
    /* "VondaGrammar.y":201  */
          { yyval = ((StatAbstractBlock)(yystack.valueAt (0))); };
  break;


  case 24: /* statement_no_def: assignment ';'  */
  if (yyn == 24)
    /* "VondaGrammar.y":202  */
                   { yyval = setPos(getAssignmentStat(((RTExpression)(yystack.valueAt (1)))), (yyloc)); };
  break;


  case 25: /* statement_no_def: field_access ';'  */
  if (yyn == 25)
    /* "VondaGrammar.y":203  */
                     { yyval = setPos(new StatExpression(((RTExpression)(yystack.valueAt (1)))), (yyloc)); };
  break;


  case 26: /* statement_no_def: PLUSPLUS IDENTIFIER ';'  */
  if (yyn == 26)
    /* "VondaGrammar.y":204  */
                            {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (1)))), yystack.locationAt (1));
    yyval = setPos(new StatExpression(createPlusMinus(var, "++", (yyloc))), (yyloc));
  };
  break;


  case 27: /* statement_no_def: MINUSMINUS IDENTIFIER ';'  */
  if (yyn == 27)
    /* "VondaGrammar.y":208  */
                              {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (1)))), yystack.locationAt (1));
    yyval = setPos(new StatExpression(createPlusMinus(var, "--", (yyloc))), (yyloc));
  };
  break;


  case 28: /* statement_no_def: IDENTIFIER PLUSPLUS ';'  */
  if (yyn == 28)
    /* "VondaGrammar.y":212  */
                            {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (2)))), yystack.locationAt (2));
    yyval = setPos(new StatExpression(createPlusMinus(var, "+++", (yyloc))), (yyloc));
  };
  break;


  case 29: /* statement_no_def: IDENTIFIER MINUSMINUS ';'  */
  if (yyn == 29)
    /* "VondaGrammar.y":216  */
                              {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (2)))), yystack.locationAt (2));
    yyval = setPos(new StatExpression(createPlusMinus(var, "---", (yyloc))), (yyloc));
  };
  break;


  case 30: /* statement_no_def: PLUSPLUS field_access ';'  */
  if (yyn == 30)
    /* "VondaGrammar.y":220  */
                              {
    yyval = setPos(new StatExpression(createPlusMinus(((RTExpression)(yystack.valueAt (1))), "++", (yyloc))), (yyloc));
  };
  break;


  case 31: /* statement_no_def: MINUSMINUS field_access ';'  */
  if (yyn == 31)
    /* "VondaGrammar.y":223  */
                                {
    yyval = setPos(new StatExpression(createPlusMinus(((RTExpression)(yystack.valueAt (1))), "--", (yyloc))), (yyloc));
  };
  break;


  case 32: /* statement_no_def: field_access PLUSPLUS ';'  */
  if (yyn == 32)
    /* "VondaGrammar.y":226  */
                              {
    yyval = setPos(new StatExpression(createPlusMinus(((RTExpression)(yystack.valueAt (2))), "+++", (yyloc))), (yyloc));
  };
  break;


  case 33: /* statement_no_def: field_access MINUSMINUS ';'  */
  if (yyn == 33)
    /* "VondaGrammar.y":229  */
                                {
    yyval = setPos(new StatExpression(createPlusMinus(((RTExpression)(yystack.valueAt (2))), "---", (yyloc))), (yyloc));
  };
  break;


  case 34: /* statement_no_def: function_call ';'  */
  if (yyn == 34)
    /* "VondaGrammar.y":232  */
                      { yyval = setPos(new StatExpression(((RTExpression)(yystack.valueAt (1)))), (yyloc)); };
  break;


  case 35: /* statement_no_def: grammar_rule  */
  if (yyn == 35)
    /* "VondaGrammar.y":233  */
                 { yyval = ((StatGrammarRule)(yystack.valueAt (0))); };
  break;


  case 36: /* statement_no_def: set_operation  */
  if (yyn == 36)
    /* "VondaGrammar.y":234  */
                  { yyval = ((RTStatement)(yystack.valueAt (0))); };
  break;


  case 37: /* statement_no_def: return_statement  */
  if (yyn == 37)
    /* "VondaGrammar.y":235  */
                     { yyval = ((RTStatement)(yystack.valueAt (0))); };
  break;


  case 38: /* statement_no_def: propose_statement  */
  if (yyn == 38)
    /* "VondaGrammar.y":236  */
                      { yyval = ((RTStatement)(yystack.valueAt (0))); };
  break;


  case 39: /* statement_no_def: timeout_statement  */
  if (yyn == 39)
    /* "VondaGrammar.y":237  */
                      { yyval = ((RTStatement)(yystack.valueAt (0))); };
  break;


  case 40: /* statement_no_def: if_statement  */
  if (yyn == 40)
    /* "VondaGrammar.y":238  */
                 { yyval = ((StatIf)(yystack.valueAt (0))); };
  break;


  case 41: /* statement_no_def: while_statement  */
  if (yyn == 41)
    /* "VondaGrammar.y":239  */
                    { yyval = ((RTStatement)(yystack.valueAt (0))); };
  break;


  case 42: /* statement_no_def: for_statement  */
  if (yyn == 42)
    /* "VondaGrammar.y":240  */
                  { yyval = ((RTStatement)(yystack.valueAt (0))); };
  break;


  case 43: /* statement_no_def: switch_statement  */
  if (yyn == 43)
    /* "VondaGrammar.y":241  */
                     { yyval = ((RTStatement)(yystack.valueAt (0))); };
  break;


  case 44: /* statement_no_def: label_statement  */
  if (yyn == 44)
    /* "VondaGrammar.y":242  */
                    { yyval = ((RTStatement)(yystack.valueAt (0))); };
  break;


  case 45: /* statement: statement_no_def  */
  if (yyn == 45)
    /* "VondaGrammar.y":246  */
                     { yyval = ((RTStatement)(yystack.valueAt (0))); };
  break;


  case 46: /* statement: var_def  */
  if (yyn == 46)
    /* "VondaGrammar.y":247  */
            { yyval = ((StatVarDef)(yystack.valueAt (0))); };
  break;


  case 47: /* blk_statement: statement  */
  if (yyn == 47)
    /* "VondaGrammar.y":251  */
              { yyval = ((RTStatement)(yystack.valueAt (0))); };
  break;


  case 48: /* block: '{' statements '}'  */
  if (yyn == 48)
    /* "VondaGrammar.y":257  */
                       { yyval = setPos(new StatAbstractBlock(((LinkedList<RTStatement>)(yystack.valueAt (1))), true), (yyloc)); };
  break;


  case 49: /* block: '{' '}'  */
  if (yyn == 49)
    /* "VondaGrammar.y":258  */
            {
    yyval = setPos(new StatAbstractBlock(new ArrayList<RTStatement>(), true), (yyloc));
  };
  break;


  case 50: /* statements: blk_statement  */
  if (yyn == 50)
    /* "VondaGrammar.y":262  */
                          { yyval = new LinkedList<RTStatement>(){{ add(((RTStatement)(yystack.valueAt (0)))); }}; };
  break;


  case 51: /* statements: blk_statement statements  */
  if (yyn == 51)
    /* "VondaGrammar.y":263  */
                             { yyval = ((LinkedList<RTStatement>)(yystack.valueAt (0))); ((LinkedList<RTStatement>)(yystack.valueAt (0))).addFirst(((RTStatement)(yystack.valueAt (1)))); };
  break;


  case 52: /* grammar_rule: IDENTIFIER ':' if_statement  */
  if (yyn == 52)
    /* "VondaGrammar.y":267  */
                                { yyval = setPos(new StatGrammarRule((( String )(yystack.valueAt (2))), ((StatIf)(yystack.valueAt (0)))), (yyloc)); };
  break;


  case 53: /* return_statement: RETURN ';'  */
  if (yyn == 53)
    /* "VondaGrammar.y":271  */
               { yyval = setPos(new StatReturn("return"), (yyloc)); };
  break;


  case 54: /* return_statement: RETURN exp ';'  */
  if (yyn == 54)
    /* "VondaGrammar.y":272  */
                   { yyval = setPos(new StatReturn(((RTExpression)(yystack.valueAt (1)))), (yyloc)); };
  break;


  case 55: /* return_statement: BREAK ';'  */
  if (yyn == 55)
    /* "VondaGrammar.y":273  */
              { yyval = setPos(new StatReturn("break"), (yyloc)); };
  break;


  case 56: /* return_statement: BREAK IDENTIFIER ';'  */
  if (yyn == 56)
    /* "VondaGrammar.y":274  */
                         { yyval = setPos(new StatReturn("break", (( String )(yystack.valueAt (1)))), (yyloc)); };
  break;


  case 57: /* return_statement: CANCEL ';'  */
  if (yyn == 57)
    /* "VondaGrammar.y":275  */
               { yyval = setPos(new StatReturn("cancel"), (yyloc)); };
  break;


  case 58: /* return_statement: CANCEL_ALL ';'  */
  if (yyn == 58)
    /* "VondaGrammar.y":276  */
                   { yyval = setPos(new StatReturn("cancel_all"), (yyloc)); };
  break;


  case 59: /* return_statement: CONTINUE ';'  */
  if (yyn == 59)
    /* "VondaGrammar.y":277  */
                 { yyval = setPos(new StatReturn("continue"), (yyloc)); };
  break;


  case 60: /* if_statement: IF '(' exp ')' statement  */
  if (yyn == 60)
    /* "VondaGrammar.y":281  */
                             { yyval = setPos(new StatIf(((RTExpression)(yystack.valueAt (2))), ((RTStatement)(yystack.valueAt (0))), null), (yyloc)); };
  break;


  case 61: /* if_statement: IF '(' exp ')' statement ELSE statement  */
  if (yyn == 61)
    /* "VondaGrammar.y":282  */
                                            {
    yyval = setPos(new StatIf(((RTExpression)(yystack.valueAt (4))), ((RTStatement)(yystack.valueAt (2))), ((RTStatement)(yystack.valueAt (0)))), (yyloc));
  };
  break;


  case 62: /* while_statement: WHILE '(' exp ')' statement  */
  if (yyn == 62)
    /* "VondaGrammar.y":288  */
                                { yyval = setPos(new StatWhile(((RTExpression)(yystack.valueAt (2))), ((RTStatement)(yystack.valueAt (0))), true), (yyloc)); };
  break;


  case 63: /* while_statement: DO statement WHILE '(' exp ')'  */
  if (yyn == 63)
    /* "VondaGrammar.y":289  */
                                   {
    yyval = setPos(new StatWhile(((RTExpression)(yystack.valueAt (1))), ((RTStatement)(yystack.valueAt (4))), false), (yyloc));
  };
  break;


  case 64: /* for_statement: FOR '(' var_decl exp ';' exp ')' statement  */
  if (yyn == 64)
    /* "VondaGrammar.y":295  */
                                               {
    yyval = setPos(new StatFor1(((StatVarDef)(yystack.valueAt (5))), ((RTExpression)(yystack.valueAt (4))), ((RTExpression)(yystack.valueAt (2))), ((RTStatement)(yystack.valueAt (0)))), (yyloc)); };
  break;


  case 65: /* for_statement: FOR '(' var_decl ';' exp ')' statement  */
  if (yyn == 65)
    /* "VondaGrammar.y":297  */
                                               {
    yyval = setPos(new StatFor1(((StatVarDef)(yystack.valueAt (4))), null, ((RTExpression)(yystack.valueAt (2))), ((RTStatement)(yystack.valueAt (0)))), (yyloc)); };
  break;


  case 66: /* for_statement: FOR '(' var_decl exp ';' ')' statement  */
  if (yyn == 66)
    /* "VondaGrammar.y":299  */
                                               {
    yyval = setPos(new StatFor1(((StatVarDef)(yystack.valueAt (4))), ((RTExpression)(yystack.valueAt (3))), null, ((RTStatement)(yystack.valueAt (0)))), (yyloc)); };
  break;


  case 67: /* for_statement: FOR '(' var_decl ';' ')' statement  */
  if (yyn == 67)
    /* "VondaGrammar.y":301  */
                                               {
    yyval = setPos(new StatFor1(((StatVarDef)(yystack.valueAt (3))), null, null, ((RTStatement)(yystack.valueAt (0)))), (yyloc)); };
  break;


  case 68: /* for_statement: FOR '(' ';' exp ';' exp ')' statement  */
  if (yyn == 68)
    /* "VondaGrammar.y":303  */
                                              {
    yyval = setPos(new StatFor1(null, ((RTExpression)(yystack.valueAt (4))), ((RTExpression)(yystack.valueAt (2))), ((RTStatement)(yystack.valueAt (0)))), (yyloc)); };
  break;


  case 69: /* for_statement: FOR '(' IDENTIFIER ':' exp ')' statement  */
  if (yyn == 69)
    /* "VondaGrammar.y":305  */
                                             {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (4)))), yystack.locationAt (4));
    yyval = setPos(new StatFor2(var, ((RTExpression)(yystack.valueAt (2))), ((RTStatement)(yystack.valueAt (0)))), (yyloc));
  };
  break;


  case 70: /* for_statement: FOR '(' type_spec IDENTIFIER ':' exp ')' statement  */
  if (yyn == 70)
    /* "VondaGrammar.y":309  */
                                                       {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (4)))), yystack.locationAt (4));
    yyval = setPos(new StatFor2(((Type)(yystack.valueAt (5))), var, ((RTExpression)(yystack.valueAt (2))), ((RTStatement)(yystack.valueAt (0)))), (yyloc));
  };
  break;


  case 71: /* var_decl: IDENTIFIER assgn_exp ';'  */
  if (yyn == 71)
    /* "VondaGrammar.y":318  */
                             {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (2)))), yystack.locationAt (2));
    ExpAssignment ass = setPos(new ExpAssignment(var, ((RTExpression)(yystack.valueAt (1)))), yystack.locationAt (2), yystack.locationAt (1));
    yyval = setPos(new StatVarDef(false, Type.getNoType(), ass), (yyloc));
  };
  break;


  case 72: /* var_decl: type_spec IDENTIFIER assgn_exp ';'  */
  if (yyn == 72)
    /* "VondaGrammar.y":323  */
                                       {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (2)))), yystack.locationAt (2));
    ExpAssignment ass = setPos(new ExpAssignment(var, ((RTExpression)(yystack.valueAt (1)))), yystack.locationAt (2), yystack.locationAt (1));
    yyval = setPos(new StatVarDef(false, ((Type)(yystack.valueAt (3))), ass), (yyloc));
  };
  break;


  case 73: /* var_decl: FINAL IDENTIFIER assgn_exp ';'  */
  if (yyn == 73)
    /* "VondaGrammar.y":328  */
                                   {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (2)))), yystack.locationAt (2));
    ExpAssignment ass = setPos(new ExpAssignment(var, ((RTExpression)(yystack.valueAt (1)))), yystack.locationAt (2), yystack.locationAt (1));
    yyval = setPos(new StatVarDef(true, Type.getNoType(), ass), (yyloc));
  };
  break;


  case 74: /* var_decl: FINAL type_spec IDENTIFIER assgn_exp ';'  */
  if (yyn == 74)
    /* "VondaGrammar.y":333  */
                                             {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (2)))), yystack.locationAt (2));
    ExpAssignment ass = setPos(new ExpAssignment(var, ((RTExpression)(yystack.valueAt (1)))), yystack.locationAt (2), yystack.locationAt (1));
    yyval = setPos(new StatVarDef(true, ((Type)(yystack.valueAt (3))), ass), (yyloc));
  };
  break;


  case 75: /* propose_statement: PROPOSE '(' exp ')' block  */
  if (yyn == 75)
    /* "VondaGrammar.y":341  */
                              { yyval = setPos(new StatPropose(((RTExpression)(yystack.valueAt (2))), ((StatAbstractBlock)(yystack.valueAt (0)))), (yyloc)); };
  break;


  case 76: /* timeout_statement: TIMEOUT '(' exp ',' exp ')' block  */
  if (yyn == 76)
    /* "VondaGrammar.y":345  */
                                      {
    yyval = setPos(new StatTimeout(((RTExpression)(yystack.valueAt (4))), ((RTExpression)(yystack.valueAt (2))), ((StatAbstractBlock)(yystack.valueAt (0)))), (yyloc));
  };
  break;


  case 77: /* switch_statement: SWITCH '(' exp ')' block  */
  if (yyn == 77)
    /* "VondaGrammar.y":351  */
                             {
    yyval = setPos(new StatSwitch(((RTExpression)(yystack.valueAt (2))), ((StatAbstractBlock)(yystack.valueAt (0)))), (yyloc));
  };
  break;


  case 78: /* label_statement: CASE STRING ':'  */
  if (yyn == 78)
    /* "VondaGrammar.y":358  */
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
    /* "VondaGrammar.y":365  */
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
    /* "VondaGrammar.y":372  */
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
    /* "VondaGrammar.y":379  */
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
    /* "VondaGrammar.y":386  */
                      {
    ExpLiteral val = new ExpLiteral("default:", "label");
    RTStatement lbl = val.ensureStatement();
    setPos(val, (yyloc)); setPos(lbl, (yyloc));
    yyval = lbl;
  };
  break;


  case 83: /* var_def: FINAL IDENTIFIER assgn_exp ';'  */
  if (yyn == 83)
    /* "VondaGrammar.y":395  */
                                   {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (2)))), yystack.locationAt (2));
    ExpAssignment ass = setPos(new ExpAssignment(var, ((RTExpression)(yystack.valueAt (1)))), yystack.locationAt (2), yystack.locationAt (1));
    yyval = setPos(new StatVarDef(true, Type.getNoType(), ass), (yyloc));
  };
  break;


  case 84: /* var_def: type_spec IDENTIFIER assgn_exp ';'  */
  if (yyn == 84)
    /* "VondaGrammar.y":400  */
                                       {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (2)))), yystack.locationAt (2));
    ExpAssignment ass = setPos(new ExpAssignment(var, ((RTExpression)(yystack.valueAt (1)))), yystack.locationAt (2), yystack.locationAt (1));
    yyval = setPos(new StatVarDef(false, ((Type)(yystack.valueAt (3))), ass), (yyloc));
  };
  break;


  case 85: /* var_def: FINAL type_spec IDENTIFIER assgn_exp ';'  */
  if (yyn == 85)
    /* "VondaGrammar.y":405  */
                                             {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (2)))), yystack.locationAt (2));
    ExpAssignment ass = setPos(new ExpAssignment(var, ((RTExpression)(yystack.valueAt (1)))), yystack.locationAt (2), yystack.locationAt (1));
    yyval = setPos(new StatVarDef(true, ((Type)(yystack.valueAt (3))), ass), (yyloc));
    };
  break;


  case 86: /* var_def: FINAL IDENTIFIER ';'  */
  if (yyn == 86)
    /* "VondaGrammar.y":410  */
                         {
    yyval = setPos(new StatVarDef(true, Type.getNoType(), (( String )(yystack.valueAt (1)))), (yyloc));
  };
  break;


  case 87: /* var_def: type_spec IDENTIFIER ';'  */
  if (yyn == 87)
    /* "VondaGrammar.y":413  */
                             {
    yyval = setPos(new StatVarDef(false, ((Type)(yystack.valueAt (2))), (( String )(yystack.valueAt (1)))), (yyloc));
  };
  break;


  case 88: /* var_def: FINAL type_spec IDENTIFIER ';'  */
  if (yyn == 88)
    /* "VondaGrammar.y":416  */
                                   {
    yyval = setPos(new StatVarDef(true, ((Type)(yystack.valueAt (2))), (( String )(yystack.valueAt (1)))), (yyloc));
  };
  break;


  case 89: /* field_def: '#' type_spec type_spec IDENTIFIER ';'  */
  if (yyn == 89)
    /* "VondaGrammar.y":422  */
                                           {
    yyval = setPos(new StatFieldDef(null,
           setPos(new StatVarDef(false, ((Type)(yystack.valueAt (2))), (( String )(yystack.valueAt (1)))), (yyloc)), ((Type)(yystack.valueAt (3)))), (yyloc));
  };
  break;


  case 90: /* assgn_exp: '=' exp  */
  if (yyn == 90)
    /* "VondaGrammar.y":429  */
            { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 91: /* assgn_exp: '=' '{' '}'  */
  if (yyn == 91)
    /* "VondaGrammar.y":430  */
                {
    yyval = setPos(new ExpListLiteral(new LinkedList<RTExpression>()), yystack.locationAt (1), yystack.locationAt (0));
  };
  break;


  case 92: /* assgn_exp: '=' '{' nonempty_exp_list '}'  */
  if (yyn == 92)
    /* "VondaGrammar.y":433  */
                                  {
    yyval = setPos(new ExpListLiteral(((LinkedList<RTExpression>)(yystack.valueAt (1)))), yystack.locationAt (2), yystack.locationAt (0));
  };
  break;


  case 93: /* nonempty_exp_list: exp  */
  if (yyn == 93)
    /* "VondaGrammar.y":439  */
        { yyval = new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (0)))); }}; };
  break;


  case 94: /* nonempty_exp_list: exp ',' nonempty_exp_list  */
  if (yyn == 94)
    /* "VondaGrammar.y":440  */
                              { yyval = ((LinkedList<RTExpression>)(yystack.valueAt (0))); ((LinkedList<RTExpression>)(yystack.valueAt (0))).addFirst(((RTExpression)(yystack.valueAt (2)))); };
  break;


  case 95: /* method_declaration: '#' type_spec type_spec IDENTIFIER '(' opt_args_list ')' opt_block  */
  if (yyn == 95)
    /* "VondaGrammar.y":445  */
                                                                       {
    yyval = setPos(new StatMethodDeclaration("public", ((Type)(yystack.valueAt (5))), ((Type)(yystack.valueAt (6))), (( String )(yystack.valueAt (4))), ((LinkedList)(yystack.valueAt (2))), ((StatAbstractBlock)(yystack.valueAt (0)))), (yyloc));
  };
  break;


  case 96: /* method_declaration: type_spec IDENTIFIER '(' opt_args_list ')' opt_block  */
  if (yyn == 96)
    /* "VondaGrammar.y":448  */
                                                         {
    yyval = setPos(new StatMethodDeclaration("public", ((Type)(yystack.valueAt (5))), null, (( String )(yystack.valueAt (4))), ((LinkedList)(yystack.valueAt (2))), ((StatAbstractBlock)(yystack.valueAt (0)))), (yyloc));
  };
  break;


  case 97: /* opt_block: block  */
  if (yyn == 97)
    /* "VondaGrammar.y":454  */
          { yyval = ((StatAbstractBlock)(yystack.valueAt (0))); };
  break;


  case 98: /* opt_block: ';'  */
  if (yyn == 98)
    /* "VondaGrammar.y":455  */
        { yyval = null; };
  break;


  case 99: /* opt_args_list: args_list  */
  if (yyn == 99)
    /* "VondaGrammar.y":459  */
              { yyval = ((LinkedList)(yystack.valueAt (0))); };
  break;


  case 100: /* opt_args_list: %empty  */
  if (yyn == 100)
    /* "VondaGrammar.y":460  */
           { yyval = new LinkedList(); };
  break;


  case 101: /* args_list: IDENTIFIER  */
  if (yyn == 101)
    /* "VondaGrammar.y":464  */
               { yyval = new LinkedList(){{ add(Type.getNoType()); add((( String )(yystack.valueAt (0)))); }}; };
  break;


  case 102: /* args_list: type_spec IDENTIFIER  */
  if (yyn == 102)
    /* "VondaGrammar.y":465  */
                         { yyval = new LinkedList(){{ add(((Type)(yystack.valueAt (1)))); add((( String )(yystack.valueAt (0)))); }}; };
  break;


  case 103: /* args_list: IDENTIFIER ',' args_list  */
  if (yyn == 103)
    /* "VondaGrammar.y":466  */
                             {
    yyval = ((LinkedList)(yystack.valueAt (0)));
    ((LinkedList)(yystack.valueAt (0))).addFirst((( String )(yystack.valueAt (2)))); ((LinkedList)(yystack.valueAt (0))).addFirst(Type.getNoType());
  };
  break;


  case 104: /* args_list: type_spec IDENTIFIER ',' args_list  */
  if (yyn == 104)
    /* "VondaGrammar.y":470  */
                                       {
    yyval = ((LinkedList)(yystack.valueAt (0))); ((LinkedList)(yystack.valueAt (0))).addFirst((( String )(yystack.valueAt (2)))); ((LinkedList)(yystack.valueAt (0))).addFirst(((Type)(yystack.valueAt (3))));
  };
  break;


  case 105: /* set_operation: IDENTIFIER PLUSEQ exp ';'  */
  if (yyn == 105)
    /* "VondaGrammar.y":478  */
                              {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (3)))), yystack.locationAt (3));
    yyval = setPos(new StatSetOperation(var, true, ((RTExpression)(yystack.valueAt (1)))), (yyloc));
  };
  break;


  case 106: /* set_operation: IDENTIFIER MINUSEQ exp ';'  */
  if (yyn == 106)
    /* "VondaGrammar.y":482  */
                               {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (3)))), yystack.locationAt (3));
    yyval = setPos(new StatSetOperation(var, false, ((RTExpression)(yystack.valueAt (1)))), (yyloc));
  };
  break;


  case 107: /* set_operation: ArrayAccess PLUSEQ exp ';'  */
  if (yyn == 107)
    /* "VondaGrammar.y":486  */
                               {
    yyval = setPos(new StatSetOperation(((RTExpression)(yystack.valueAt (3))), true, ((RTExpression)(yystack.valueAt (1)))), (yyloc));
  };
  break;


  case 108: /* set_operation: ArrayAccess MINUSEQ exp ';'  */
  if (yyn == 108)
    /* "VondaGrammar.y":489  */
                                {
    yyval = setPos(new StatSetOperation(((RTExpression)(yystack.valueAt (3))), false, ((RTExpression)(yystack.valueAt (1)))), (yyloc));
  };
  break;


  case 109: /* set_operation: field_access PLUSEQ exp ';'  */
  if (yyn == 109)
    /* "VondaGrammar.y":492  */
                                {
    yyval = setPos(new StatSetOperation(((RTExpression)(yystack.valueAt (3))), true, ((RTExpression)(yystack.valueAt (1)))), (yyloc));
  };
  break;


  case 110: /* set_operation: field_access MINUSEQ exp ';'  */
  if (yyn == 110)
    /* "VondaGrammar.y":495  */
                                 {
    yyval = setPos(new StatSetOperation(((RTExpression)(yystack.valueAt (3))), false, ((RTExpression)(yystack.valueAt (1)))), (yyloc));
  };
  break;


  case 111: /* function_call: IDENTIFIER '(' ')'  */
  if (yyn == 111)
    /* "VondaGrammar.y":504  */
                       {
    yyval = setPos(new ExpFuncCall((( String )(yystack.valueAt (2))), new LinkedList<RTExpression>(), false), (yyloc));
  };
  break;


  case 112: /* function_call: IDENTIFIER '(' nonempty_args_list ')'  */
  if (yyn == 112)
    /* "VondaGrammar.y":507  */
                                           {
    yyval = setPos(new ExpFuncCall((( String )(yystack.valueAt (3))), ((LinkedList<RTExpression>)(yystack.valueAt (1))), false), (yyloc));
  };
  break;


  case 113: /* nonempty_args_list: exp  */
  if (yyn == 113)
    /* "VondaGrammar.y":513  */
        { yyval = new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (0)))); }}; };
  break;


  case 114: /* nonempty_args_list: lambda_exp  */
  if (yyn == 114)
    /* "VondaGrammar.y":514  */
               { yyval = new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (0)))); }}; };
  break;


  case 115: /* nonempty_args_list: exp ',' nonempty_args_list  */
  if (yyn == 115)
    /* "VondaGrammar.y":515  */
                               { yyval = ((LinkedList<RTExpression>)(yystack.valueAt (0))); ((LinkedList<RTExpression>)(yystack.valueAt (0))).addFirst(((RTExpression)(yystack.valueAt (2)))); };
  break;


  case 116: /* nonempty_args_list: lambda_exp ',' nonempty_args_list  */
  if (yyn == 116)
    /* "VondaGrammar.y":516  */
                                      { yyval = ((LinkedList<RTExpression>)(yystack.valueAt (0))); ((LinkedList<RTExpression>)(yystack.valueAt (0))).addFirst(((RTExpression)(yystack.valueAt (2)))); };
  break;


  case 117: /* type_spec: IDENTIFIER '[' ']'  */
  if (yyn == 117)
    /* "VondaGrammar.y":520  */
                       {
    yyval = new Type("Array",
                  new ArrayList<Type>(){{ add(new Type((( String )(yystack.valueAt (2))))); }});
  };
  break;


  case 118: /* type_spec: IDENTIFIER  */
  if (yyn == 118)
    /* "VondaGrammar.y":524  */
               { yyval = new Type((( String )(yystack.valueAt (0)))); };
  break;


  case 119: /* type_spec: IDENTIFIER '<' type_spec_list '>'  */
  if (yyn == 119)
    /* "VondaGrammar.y":525  */
                                      { yyval = new Type((( String )(yystack.valueAt (3))), ((LinkedList<Type>)(yystack.valueAt (1)))); };
  break;


  case 120: /* type_spec_list: type_spec  */
  if (yyn == 120)
    /* "VondaGrammar.y":529  */
              { yyval = new LinkedList<Type>(){{ add(((Type)(yystack.valueAt (0)))); }}; };
  break;


  case 121: /* type_spec_list: type_spec ',' type_spec_list  */
  if (yyn == 121)
    /* "VondaGrammar.y":530  */
                                 { yyval = ((LinkedList<Type>)(yystack.valueAt (0))); ((LinkedList<Type>)(yystack.valueAt (0))).addFirst(((Type)(yystack.valueAt (2)))); };
  break;


  case 122: /* ConditionalOrExpression: ConditionalOrExpression OROR ConditionalAndExpression  */
  if (yyn == 122)
    /* "VondaGrammar.y":534  */
                                                          {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "||"), (yyloc));
  };
  break;


  case 123: /* ConditionalOrExpression: ConditionalAndExpression  */
  if (yyn == 123)
    /* "VondaGrammar.y":537  */
                             { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 124: /* ConditionalAndExpression: ConditionalAndExpression ANDAND InclusiveOrExpression  */
  if (yyn == 124)
    /* "VondaGrammar.y":541  */
                                                          {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "&&"), (yyloc));
  };
  break;


  case 125: /* ConditionalAndExpression: InclusiveOrExpression  */
  if (yyn == 125)
    /* "VondaGrammar.y":544  */
                          { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 126: /* InclusiveOrExpression: ExclusiveOrExpression  */
  if (yyn == 126)
    /* "VondaGrammar.y":548  */
                          { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 127: /* InclusiveOrExpression: InclusiveOrExpression '|' ExclusiveOrExpression  */
  if (yyn == 127)
    /* "VondaGrammar.y":549  */
                                                    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "|"), (yyloc));
  };
  break;


  case 128: /* ExclusiveOrExpression: AndExpression  */
  if (yyn == 128)
    /* "VondaGrammar.y":555  */
                  { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 129: /* ExclusiveOrExpression: ExclusiveOrExpression '^' AndExpression  */
  if (yyn == 129)
    /* "VondaGrammar.y":556  */
                                            {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "^"), (yyloc));
  };
  break;


  case 130: /* AndExpression: EqualityExpression  */
  if (yyn == 130)
    /* "VondaGrammar.y":562  */
                       { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 131: /* AndExpression: AndExpression '&' EqualityExpression  */
  if (yyn == 131)
    /* "VondaGrammar.y":563  */
                                         {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "&"), (yyloc));
  };
  break;


  case 132: /* EqualityExpression: RelationalExpression  */
  if (yyn == 132)
    /* "VondaGrammar.y":569  */
                         { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 133: /* EqualityExpression: EqualityExpression EQEQ RelationalExpression  */
  if (yyn == 133)
    /* "VondaGrammar.y":570  */
                                                 {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "=="), (yyloc));
  };
  break;


  case 134: /* EqualityExpression: EqualityExpression NOTEQ RelationalExpression  */
  if (yyn == 134)
    /* "VondaGrammar.y":573  */
                                                  {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "!="), (yyloc));
  };
  break;


  case 135: /* RelationalExpression: AdditiveExpression  */
  if (yyn == 135)
    /* "VondaGrammar.y":579  */
                       { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 136: /* RelationalExpression: RelationalExpression '<' AdditiveExpression  */
  if (yyn == 136)
    /* "VondaGrammar.y":580  */
                                                {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "<"), (yyloc));
  };
  break;


  case 137: /* RelationalExpression: RelationalExpression '>' AdditiveExpression  */
  if (yyn == 137)
    /* "VondaGrammar.y":583  */
                                                {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), ">"), (yyloc));
  };
  break;


  case 138: /* RelationalExpression: RelationalExpression GTEQ AdditiveExpression  */
  if (yyn == 138)
    /* "VondaGrammar.y":586  */
                                                 {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), ">="), (yyloc));
  };
  break;


  case 139: /* RelationalExpression: RelationalExpression LTEQ AdditiveExpression  */
  if (yyn == 139)
    /* "VondaGrammar.y":589  */
                                                 {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "<="), (yyloc));
  };
  break;


  case 140: /* AdditiveExpression: MultiplicativeExpression  */
  if (yyn == 140)
    /* "VondaGrammar.y":595  */
                             { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 141: /* AdditiveExpression: AdditiveExpression '+' MultiplicativeExpression  */
  if (yyn == 141)
    /* "VondaGrammar.y":596  */
                                                    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "+"), (yyloc));
  };
  break;


  case 142: /* AdditiveExpression: AdditiveExpression '-' MultiplicativeExpression  */
  if (yyn == 142)
    /* "VondaGrammar.y":599  */
                                                    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "-"), (yyloc));
  };
  break;


  case 143: /* MultiplicativeExpression: CastExpression  */
  if (yyn == 143)
    /* "VondaGrammar.y":605  */
                   { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 144: /* MultiplicativeExpression: MultiplicativeExpression '*' CastExpression  */
  if (yyn == 144)
    /* "VondaGrammar.y":606  */
                                                {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "*"), (yyloc));
  };
  break;


  case 145: /* MultiplicativeExpression: MultiplicativeExpression '/' CastExpression  */
  if (yyn == 145)
    /* "VondaGrammar.y":609  */
                                                {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "/"), (yyloc));
  };
  break;


  case 146: /* MultiplicativeExpression: MultiplicativeExpression '%' CastExpression  */
  if (yyn == 146)
    /* "VondaGrammar.y":612  */
                                                {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "%"), (yyloc));
  };
  break;


  case 147: /* CastExpression: UnaryExpression  */
  if (yyn == 147)
    /* "VondaGrammar.y":618  */
                    { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 148: /* CastExpression: ISA '(' type_spec ',' CastExpression ')'  */
  if (yyn == 148)
    /* "VondaGrammar.y":619  */
                                             { yyval = setPos(new ExpCast(((Type)(yystack.valueAt (3))), ((RTExpression)(yystack.valueAt (1)))), (yyloc)); };
  break;


  case 149: /* UnaryExpression: PLUSPLUS UnaryExpression  */
  if (yyn == 149)
    /* "VondaGrammar.y":623  */
                             {
    yyval = createPlusMinus(((RTExpression)(yystack.valueAt (0))), "++", (yyloc));
  };
  break;


  case 150: /* UnaryExpression: MINUSMINUS UnaryExpression  */
  if (yyn == 150)
    /* "VondaGrammar.y":626  */
                               {
    yyval = createPlusMinus(((RTExpression)(yystack.valueAt (0))), "--", (yyloc));
  };
  break;


  case 151: /* UnaryExpression: '+' CastExpression  */
  if (yyn == 151)
    /* "VondaGrammar.y":629  */
                       { yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (0))), null, "+"), (yyloc)); };
  break;


  case 152: /* UnaryExpression: '-' CastExpression  */
  if (yyn == 152)
    /* "VondaGrammar.y":630  */
                       { yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (0))), null, "-"), (yyloc)); };
  break;


  case 153: /* UnaryExpression: LogicalUnaryExpression  */
  if (yyn == 153)
    /* "VondaGrammar.y":631  */
                           { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 154: /* LogicalUnaryExpression: PostfixExpression  */
  if (yyn == 154)
    /* "VondaGrammar.y":635  */
                      { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 155: /* LogicalUnaryExpression: '!' UnaryExpression  */
  if (yyn == 155)
    /* "VondaGrammar.y":636  */
                        { yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (0))), null, "!"), (yyloc)); };
  break;


  case 156: /* LogicalUnaryExpression: '~' UnaryExpression  */
  if (yyn == 156)
    /* "VondaGrammar.y":637  */
                        { yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (0))), null, "~"), (yyloc)); };
  break;


  case 157: /* PostfixExpression: PrimaryExpression  */
  if (yyn == 157)
    /* "VondaGrammar.y":641  */
                      { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 158: /* PostfixExpression: PostfixExpression PLUSPLUS  */
  if (yyn == 158)
    /* "VondaGrammar.y":642  */
                               {
    yyval = createPlusMinus(((RTExpression)(yystack.valueAt (1))), "+++", (yyloc));
  };
  break;


  case 159: /* PostfixExpression: PostfixExpression MINUSMINUS  */
  if (yyn == 159)
    /* "VondaGrammar.y":645  */
                                 {
    yyval = createPlusMinus(((RTExpression)(yystack.valueAt (1))), "---", (yyloc));
  };
  break;


  case 160: /* PrimaryExpression: NULL  */
  if (yyn == 160)
    /* "VondaGrammar.y":651  */
         { yyval = setPos(new ExpLiteral("null", "null"), (yyloc)); };
  break;


  case 161: /* PrimaryExpression: IDENTIFIER  */
  if (yyn == 161)
    /* "VondaGrammar.y":652  */
               { yyval = setPos(new ExpIdentifier((( String )(yystack.valueAt (0)))), (yyloc)); };
  break;


  case 162: /* PrimaryExpression: field_access  */
  if (yyn == 162)
    /* "VondaGrammar.y":653  */
                 { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 163: /* PrimaryExpression: ComplexPrimary  */
  if (yyn == 163)
    /* "VondaGrammar.y":654  */
                   { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 164: /* ComplexPrimary: '(' exp ')'  */
  if (yyn == 164)
    /* "VondaGrammar.y":658  */
                { yyval = ((RTExpression)(yystack.valueAt (1))); ((RTExpression)(yystack.valueAt (1))).generateParens(); };
  break;


  case 165: /* ComplexPrimary: ComplexPrimaryNoParenthesis  */
  if (yyn == 165)
    /* "VondaGrammar.y":659  */
                                { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 166: /* ComplexPrimaryNoParenthesis: Literal  */
  if (yyn == 166)
    /* "VondaGrammar.y":663  */
            { yyval = ((ExpLiteral)(yystack.valueAt (0))); };
  break;


  case 167: /* ComplexPrimaryNoParenthesis: ArrayAccess  */
  if (yyn == 167)
    /* "VondaGrammar.y":664  */
                { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 168: /* ComplexPrimaryNoParenthesis: function_call  */
  if (yyn == 168)
    /* "VondaGrammar.y":665  */
                  { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 169: /* ComplexPrimaryNoParenthesis: dialogueact_exp  */
  if (yyn == 169)
    /* "VondaGrammar.y":666  */
                    { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 170: /* Literal: STRING  */
  if (yyn == 170)
    /* "VondaGrammar.y":670  */
           { yyval = setPos((( ExpLiteral )(yystack.valueAt (0))), (yyloc)); };
  break;


  case 171: /* Literal: INT  */
  if (yyn == 171)
    /* "VondaGrammar.y":671  */
        { yyval = setPos((( ExpLiteral )(yystack.valueAt (0))), (yyloc)); };
  break;


  case 172: /* Literal: OTHER_LITERAL  */
  if (yyn == 172)
    /* "VondaGrammar.y":672  */
                  { yyval = setPos((( ExpLiteral )(yystack.valueAt (0))), (yyloc)); };
  break;


  case 173: /* Literal: BOOL_LITERAL  */
  if (yyn == 173)
    /* "VondaGrammar.y":673  */
                 { yyval = setPos((( ExpLiteral )(yystack.valueAt (0))), (yyloc)); };
  break;


  case 174: /* ArrayAccess: IDENTIFIER '[' exp ']'  */
  if (yyn == 174)
    /* "VondaGrammar.y":677  */
                           {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (3)))), yystack.locationAt (3));
    yyval = setPos(new ExpArrayAccess(var, ((RTExpression)(yystack.valueAt (1)))), (yyloc));
  };
  break;


  case 175: /* ArrayAccess: ComplexPrimary '[' exp ']'  */
  if (yyn == 175)
    /* "VondaGrammar.y":681  */
                               { yyval = setPos(new ExpArrayAccess(((RTExpression)(yystack.valueAt (3))), ((RTExpression)(yystack.valueAt (1)))), (yyloc)); };
  break;


  case 176: /* field_access: IDENTIFIER field_access_rest  */
  if (yyn == 176)
    /* "VondaGrammar.y":685  */
                                 {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (1)))), yystack.locationAt (1));
    ((LinkedList<RTExpression>)(yystack.valueAt (0))).addFirst(var);
    yyval = setPos(new ExpFieldAccess(((LinkedList<RTExpression>)(yystack.valueAt (0)))), (yyloc));
  };
  break;


  case 177: /* field_access: ComplexPrimary field_access_rest  */
  if (yyn == 177)
    /* "VondaGrammar.y":690  */
                                     {
    ((LinkedList<RTExpression>)(yystack.valueAt (0))).addFirst(((RTExpression)(yystack.valueAt (1))));
    yyval = setPos(new ExpFieldAccess(((LinkedList<RTExpression>)(yystack.valueAt (0)))), (yyloc));
  };
  break;


  case 178: /* field_access_rest: '.' simple_nofa_exp field_access_rest  */
  if (yyn == 178)
    /* "VondaGrammar.y":697  */
                                           { yyval = ((LinkedList<RTExpression>)(yystack.valueAt (0))); ((LinkedList<RTExpression>)(yystack.valueAt (0))).addFirst(((RTExpression)(yystack.valueAt (1)))); };
  break;


  case 179: /* field_access_rest: '.' simple_nofa_exp  */
  if (yyn == 179)
    /* "VondaGrammar.y":698  */
                         {
     List<RTExpression> l = new LinkedList<RTExpression>();
     l.add(((RTExpression)(yystack.valueAt (0))));
     yyval = l;
   };
  break;


  case 180: /* simple_nofa_exp: IDENTIFIER  */
  if (yyn == 180)
    /* "VondaGrammar.y":706  */
               { yyval = setPos(new ExpIdentifier((( String )(yystack.valueAt (0)))), (yyloc)); };
  break;


  case 181: /* simple_nofa_exp: '{' exp '}'  */
  if (yyn == 181)
    /* "VondaGrammar.y":707  */
                { yyval = ((RTExpression)(yystack.valueAt (1))); };
  break;


  case 182: /* simple_nofa_exp: function_call  */
  if (yyn == 182)
    /* "VondaGrammar.y":708  */
                  { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 183: /* ConditionalExpression: ConditionalOrExpression '?' exp ':' exp  */
  if (yyn == 183)
    /* "VondaGrammar.y":712  */
                                            { yyval = setPos(new ExpConditional(((RTExpression)(yystack.valueAt (4))), ((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0)))), (yyloc)); };
  break;


  case 184: /* ConditionalExpression: ConditionalOrExpression  */
  if (yyn == 184)
    /* "VondaGrammar.y":713  */
                            { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 185: /* assignment: field_access assgn_exp  */
  if (yyn == 185)
    /* "VondaGrammar.y":719  */
                           { yyval = setPos(new ExpAssignment(((RTExpression)(yystack.valueAt (1))), ((RTExpression)(yystack.valueAt (0)))), (yyloc)); };
  break;


  case 186: /* assignment: ArrayAccess assgn_exp  */
  if (yyn == 186)
    /* "VondaGrammar.y":720  */
                          { yyval = setPos(new ExpAssignment(((RTExpression)(yystack.valueAt (1))), ((RTExpression)(yystack.valueAt (0)))), (yyloc)); };
  break;


  case 187: /* assignment: IDENTIFIER assgn_exp  */
  if (yyn == 187)
    /* "VondaGrammar.y":721  */
                         {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (1)))), yystack.locationAt (1));
    ExpAssignment ass = setPos(new ExpAssignment(var, ((RTExpression)(yystack.valueAt (0)))), (yyloc));
    yyval = ass;
  };
  break;


  case 188: /* new_exp: NEW IDENTIFIER  */
  if (yyn == 188)
    /* "VondaGrammar.y":729  */
                   { yyval = setPos(new ExpNew(new Type((( String )(yystack.valueAt (0))))), (yyloc)); };
  break;


  case 189: /* new_exp: NEW IDENTIFIER '(' ')'  */
  if (yyn == 189)
    /* "VondaGrammar.y":730  */
                           {
    yyval = setPos(new ExpNew(new Type((( String )(yystack.valueAt (2)))), new LinkedList<>()), (yyloc));
  };
  break;


  case 190: /* new_exp: NEW IDENTIFIER '(' nonempty_exp_list ')'  */
  if (yyn == 190)
    /* "VondaGrammar.y":733  */
                                             {
    yyval = setPos(new ExpNew(new Type((( String )(yystack.valueAt (3)))), ((LinkedList<RTExpression>)(yystack.valueAt (1)))), (yyloc));
  };
  break;


  case 191: /* new_exp: NEW IDENTIFIER '[' exp ']'  */
  if (yyn == 191)
    /* "VondaGrammar.y":736  */
                               {
    List<Type> sub = new ArrayList<Type>() {{ add(new Type((( String )(yystack.valueAt (3))))); }};
    yyval = setPos(new ExpNew(new Type("Array", sub),
                    new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (1)))); }}), (yyloc));
  };
  break;


  case 192: /* new_exp: NEW IDENTIFIER '[' ']' '(' exp ')'  */
  if (yyn == 192)
    /* "VondaGrammar.y":741  */
                                      {
    List<Type> sub = new ArrayList<Type>() {{ add(new Type((( String )(yystack.valueAt (5))))); }};
    yyval = setPos(new ExpNew(new Type("Array", sub),
                    new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (1)))); }}), (yyloc));
  };
  break;


  case 193: /* new_exp: NEW IDENTIFIER '<' type_spec_list '>' '(' ')'  */
  if (yyn == 193)
    /* "VondaGrammar.y":746  */
                                                  {
    yyval = setPos(new ExpNew(new Type((( String )(yystack.valueAt (5))), ((LinkedList<Type>)(yystack.valueAt (3)))),
                    new LinkedList<>()), (yyloc));
  };
  break;


  case 194: /* new_exp: NEW IDENTIFIER '<' type_spec_list '>' '(' nonempty_exp_list ')'  */
  if (yyn == 194)
    /* "VondaGrammar.y":750  */
                                                                    {
    yyval = setPos(new ExpNew(new Type((( String )(yystack.valueAt (6))), ((LinkedList<Type>)(yystack.valueAt (4)))),
                    ((LinkedList<RTExpression>)(yystack.valueAt (1)))), (yyloc));
  };
  break;


  case 195: /* lambda_exp: LAMBDA '(' opt_args_list ')' exp  */
  if (yyn == 195)
    /* "VondaGrammar.y":757  */
                                     {
    yyval = setPos(new ExpLambda(((LinkedList)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0)))), (yyloc));
  };
  break;


  case 196: /* lambda_exp: LAMBDA '(' opt_args_list ')' block  */
  if (yyn == 196)
    /* "VondaGrammar.y":760  */
                                       {
    yyval = setPos(new ExpLambda(((LinkedList)(yystack.valueAt (2))), ((StatAbstractBlock)(yystack.valueAt (0)))), (yyloc));
  };
  break;


  case 197: /* dialogueact_exp: '#' da_token '(' da_token da_args ')'  */
  if (yyn == 197)
    /* "VondaGrammar.y":767  */
                                          {
    yyval = setPos(new ExpDialogueAct(((RTExpression)(yystack.valueAt (4))), ((RTExpression)(yystack.valueAt (2))), ((LinkedList<RTExpression>)(yystack.valueAt (1)))), (yyloc));
  };
  break;


  case 198: /* da_token: '{' exp '}'  */
  if (yyn == 198)
    /* "VondaGrammar.y":773  */
                { yyval = ((RTExpression)(yystack.valueAt (1))); };
  break;


  case 199: /* da_token: IDENTIFIER  */
  if (yyn == 199)
    /* "VondaGrammar.y":776  */
               { yyval = setPos(new ExpLiteral((( String )(yystack.valueAt (0))), "String"), (yyloc)); };
  break;


  case 200: /* da_token: STRING  */
  if (yyn == 200)
    /* "VondaGrammar.y":777  */
           { yyval = setPos((( ExpLiteral )(yystack.valueAt (0))), (yyloc)); };
  break;


  case 201: /* da_token: WILDCARD  */
  if (yyn == 201)
    /* "VondaGrammar.y":778  */
             { yyval = setPos(new ExpLiteral((( String )(yystack.valueAt (0))), "String"), (yyloc)); };
  break;


  case 202: /* da_args: ',' da_token '=' da_token da_args  */
  if (yyn == 202)
    /* "VondaGrammar.y":782  */
                                       {
    yyval = ((LinkedList<RTExpression>)(yystack.valueAt (0))); ((LinkedList<RTExpression>)(yystack.valueAt (0))).addFirst(((RTExpression)(yystack.valueAt (1)))); ((LinkedList<RTExpression>)(yystack.valueAt (0))).addFirst(((RTExpression)(yystack.valueAt (3))));
  };
  break;


  case 203: /* da_args: %empty  */
  if (yyn == 203)
    /* "VondaGrammar.y":785  */
           { yyval = new LinkedList<RTExpression>(); };
  break;


  case 204: /* exp: ConditionalExpression  */
  if (yyn == 204)
    /* "VondaGrammar.y":789  */
                          { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 205: /* exp: assignment  */
  if (yyn == 205)
    /* "VondaGrammar.y":790  */
               { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 206: /* exp: new_exp  */
  if (yyn == 206)
    /* "VondaGrammar.y":791  */
            { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;



/* "VondaGrammar.java":2567  */

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

  private static final short yypact_ninf_ = -317;
  private static final short yytable_ninf_ = -200;

/* YYPACT[STATE-NUM] -- Index in YYTABLE of the portion describing
   STATE-NUM.  */
  private static final short[] yypact_ = yypact_init();
  private static final short[] yypact_init()
  {
    return new short[]
    {
     789,    71,    41,    58,   431,    78,    82,   893,   100,   108,
     128,    -5,   139,  -317,   176,  -317,  -317,   647,   178,   194,
     199,   277,   295,  -317,  -317,  -317,   217,  -317,   573,  1258,
     284,   195,   789,  -317,    61,   841,   841,  -317,  -317,  -317,
    -317,  -317,  -317,  -317,  -317,  -317,  -317,   841,   841,   841,
    -317,   722,   218,   -13,  -317,  -317,    46,    84,   161,  -317,
     224,  -317,  -317,  -317,   221,   231,   239,   243,  -317,  -317,
     302,  -317,   279,  -317,   263,   260,   153,   268,    57,  1258,
     139,  -317,   123,   203,  1258,   270,  -317,   271,  1290,  1290,
     109,  -317,   415,   415,  1290,  1290,  -317,   -17,   296,   283,
     286,   290,   225,    50,   202,    33,  -317,  -317,  -317,   258,
    -317,   -13,   303,   303,  -317,  -317,  -317,   321,  1258,  1258,
    1258,    85,  -317,   323,   234,   328,  1258,  1258,   334,   341,
     -31,   379,   591,   911,   943,   352,  -317,  -317,  -317,  -317,
     893,   347,   345,  -317,  -317,   226,  1258,   352,   349,  -317,
    -317,   143,   352,   841,   841,  -317,  -317,  -317,  -317,  -317,
    -317,  -317,   219,  1258,  -317,  1258,  1258,  -317,  1258,  1258,
     370,   382,  -317,  -317,  -317,  -317,  -317,  -317,  -317,  -317,
    -317,   373,    32,  -317,   372,   385,   106,   391,   301,  1258,
     975,   393,   396,   309,  -317,   101,  -317,   400,   399,   256,
     352,   -27,  -317,  -317,  -317,  1258,  -317,  -317,  -317,  -317,
     415,  1258,   415,   415,   415,   415,   415,   415,   415,   415,
     415,   415,   415,   415,   415,   415,   415,  -317,  -317,  -317,
     408,   409,   412,  -317,  -317,  -317,  -317,   416,   420,  -317,
    -317,   425,  1258,  -317,   421,  -317,   426,  -317,   419,   427,
     428,  1007,  -317,  -317,   429,   432,   430,  -317,  -317,  -317,
     435,   445,   302,   352,  -317,  -317,  -317,   447,   450,   439,
     452,   453,   454,   455,  -317,  -317,  1258,  -317,  -317,   456,
     389,   462,  1258,   461,   463,  1042,   464,   -35,   893,  -317,
     150,  -317,   467,   459,  1060,  1098,   352,   460,   296,   465,
     283,   286,   290,   225,    50,    50,   202,   202,   202,   202,
      33,    33,  -317,  -317,  -317,   459,  1258,   893,  -317,  -317,
     470,  -317,   447,  -317,  1130,  1130,  -317,   471,   468,  -317,
     352,  -317,  -317,    40,   469,   480,    94,   472,  -317,   482,
    -317,  -317,  -317,  -317,  -317,  -317,   474,  -317,   484,   303,
     481,  -317,  1258,   893,   483,  1162,  1258,   489,   504,   492,
    -317,  -317,  -317,   486,   488,   485,   487,   415,  1258,  -317,
     491,  -317,  -317,   493,  -317,  -317,  -317,  1258,  -317,  -317,
     447,   302,   496,   490,   447,    80,   498,  -317,  -317,   499,
     893,   502,  -317,   893,   893,   503,   506,  -317,   893,  -317,
    -317,  1258,  -317,   505,   507,  -317,   459,  1194,  -317,   510,
     508,  -317,  -317,  -317,  -317,  -317,   447,  -317,  -317,   893,
    -317,  -317,   893,   893,  -317,   512,  1226,  -317,  -317,  -317,
    -317,    80,   302,  -317,  -317,  -317,  -317,  -317,  -317,   513,
    -317,   469,  -317,  -317
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
       0,    21,     0,     0,     0,     0,   160,     0,     0,     0,
     161,    53,     0,     0,     0,     0,   168,   184,   123,   125,
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
       0,   161,   150,   162,   149,     0,   151,   152,   155,   156,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,   159,   158,    54,
       0,     0,     0,    27,    31,    26,    30,     0,     0,    29,
      28,   180,     0,   182,   179,    52,     0,   111,     0,   114,
     113,     0,    90,   117,     0,   120,     0,    51,    48,   164,
       0,     0,     0,     0,    13,     8,    87,   100,     0,     0,
       0,     0,     0,     0,    33,    32,     0,    83,    88,     0,
     118,     0,     0,     0,     0,     0,     0,     0,     0,     6,
       0,    22,     0,     0,     0,     0,     0,     0,   122,     0,
     124,   127,   129,   131,   133,   134,   138,   139,   136,   137,
     141,   142,   144,   145,   146,     0,     0,     0,   106,   105,
       0,   178,   100,   112,     0,     0,    91,     0,    93,   174,
       0,   119,   198,     0,   203,     0,   101,     0,    99,     0,
      84,   175,   108,   107,   110,   109,     0,    85,     0,     0,
       0,    71,     0,     0,     0,     0,     0,     0,    60,     0,
       5,    75,   189,     0,     0,     0,     0,     0,     0,    77,
       0,    62,   181,     0,   116,   115,    92,     0,   121,    89,
     100,     0,     0,     0,     0,     0,   102,    63,    73,     0,
       0,     0,    67,     0,     0,     0,     0,    72,     0,     7,
     190,     0,   191,     0,     0,   183,     0,     0,    94,     0,
       0,   197,   103,    98,    97,    96,     0,    74,    69,     0,
      65,    66,     0,     0,    61,     0,     0,   148,    76,   196,
     195,     0,     0,   104,    68,    64,    70,   192,   193,     0,
      95,   203,   194,   202
    };
  }

/* YYPGOTO[NTERM-NUM].  */
  private static final short[] yypgoto_ = yypgoto_init();
  private static final short[] yypgoto_init()
  {
    return new short[]
    {
    -317,   534,  -317,   211,  -317,  -317,    -1,   251,    16,  -317,
    -289,   443,  -317,  -317,   436,  -317,  -317,  -317,  -317,  -317,
    -317,  -317,   156,  -317,    14,  -280,   535,   137,  -316,  -311,
    -317,    27,     5,   340,  -288,  -317,   360,   359,   362,   358,
     375,   144,   163,   155,   -91,    89,  -317,  -317,  -317,     9,
    -317,  -317,     0,    18,   -50,  -317,  -317,   138,  -317,  -317,
    -317,  -257,   146,   384
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
      45,    46,    73,    48,   136,   327,    49,   415,   337,   338,
      50,    96,   248,    75,   256,    97,    98,    99,   100,   101,
     102,   103,   104,   105,   106,   107,   108,   109,   110,   111,
      54,    55,   112,   113,   137,   244,   114,   115,   116,   249,
      59,   148,   382,   328
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
      56,   206,   207,   164,   361,   334,   373,    56,   366,    53,
      80,    83,   241,   210,   363,   356,    53,   242,    57,   130,
     133,   122,   122,    72,   132,    57,   369,    51,    56,   205,
      53,    53,    56,   130,    74,    56,    56,    53,    81,   123,
     125,    53,   378,   163,    53,    53,    57,    56,    56,    56,
      57,    56,   211,    57,    57,    74,    53,    53,    53,    51,
      53,   164,    51,    51,   409,    57,    57,    57,   187,    57,
     167,   173,     8,   412,    51,    51,    51,   266,    51,   193,
     224,   165,   166,   218,   219,   379,    62,   133,   122,   122,
     185,   380,   122,   122,   122,   122,   414,   408,   225,   226,
     188,   133,   189,    63,   151,   433,   203,   203,   220,   221,
     203,   203,   203,   203,    60,   152,    61,   428,   429,   168,
     169,   170,   171,    68,   410,   413,   167,   173,    28,   172,
     233,   130,    69,   312,   313,   314,   132,  -118,    58,   133,
      56,   205,   414,    76,   291,    58,   439,   384,   292,    53,
     184,   278,   135,    56,    56,   130,    47,   243,    57,    78,
     132,   133,    53,    53,   133,   205,    58,    74,   194,   195,
      58,    57,    57,    58,    58,   441,   268,   202,   204,    79,
      51,    51,    81,   208,   209,    58,    58,    58,    47,    58,
     153,    47,    47,   291,   321,   149,   268,   359,   183,   184,
     279,   135,   283,    47,    47,    47,   174,    47,   133,   184,
     122,   135,   122,   122,   122,   122,   122,   122,   122,   122,
     122,   122,   122,   122,   122,   122,   122,    84,   203,   118,
     203,   203,   203,   203,   203,   203,   203,   203,   203,   203,
     203,   203,   203,   203,   203,   119,   155,   156,   196,   197,
     120,    36,   126,   127,   128,   129,   216,   217,   157,   158,
     159,   162,   161,   130,   266,   222,   223,   131,   132,   175,
     267,   176,   133,   134,   133,   135,   404,  -199,    58,   235,
     130,   177,   184,    36,   135,   132,    36,    36,    56,   178,
     205,    58,    58,   179,   348,   227,   228,    53,    36,    36,
      36,   357,    36,   182,   358,   181,    57,   294,   160,    47,
      47,   186,   295,   199,   296,    74,    23,    56,    24,    25,
     121,    27,   200,   143,   144,   212,    53,   145,    29,   374,
     375,    70,   146,   371,    23,    57,    24,    25,   124,    27,
      52,   143,   144,   213,    74,   180,    29,   214,    77,    70,
     146,   282,   215,    56,   289,   290,   133,   184,   133,   135,
     304,   305,    53,   389,   264,   265,   229,   122,   234,   392,
     147,    57,    52,   236,    52,    52,    52,   310,   311,   239,
      74,   306,   307,   308,   309,   203,   240,    52,    52,    52,
      56,    52,    10,    56,    56,   151,   258,   259,    56,    53,
     262,   117,    53,    53,    36,    36,   418,    53,    57,   420,
     421,    57,    57,   142,   424,   274,    57,    74,   191,    56,
      74,    74,    56,    56,   276,    74,    58,   275,    53,   253,
     277,    53,    53,    86,   280,   434,   287,    57,   435,   436,
      57,    57,    87,   291,   133,   184,    74,   135,   288,    74,
      74,   293,    88,    89,    23,    58,    24,    25,   201,    27,
     315,   318,   316,   192,   317,   319,    29,   130,   198,    70,
      64,   323,    65,    66,    67,   255,   132,   322,    92,    93,
     324,   325,    94,    95,   332,   330,   329,   261,   333,   331,
     336,    58,   263,    52,    52,   340,   341,   342,   343,   344,
     345,   347,   230,   231,   232,   349,   351,    28,   352,   355,
     237,   238,   360,   367,   398,   368,   250,   252,   254,   372,
     376,   377,   381,   383,   385,   386,   387,   281,    58,   388,
     260,    58,    58,   390,   397,   393,    58,   399,   400,   401,
     297,   380,   402,   406,   417,   407,   403,   269,   411,   270,
     271,   416,   272,   273,   419,   422,   426,    58,   423,   427,
      58,    58,   431,   432,   437,   442,   150,   245,   440,   154,
     298,   300,   302,   284,   286,   301,     1,     2,     3,     4,
       5,     6,     7,   257,     8,     9,    10,   443,     0,   254,
     303,     0,     0,    14,     0,   299,    17,    18,    19,    20,
       0,     0,     0,   335,     0,     0,     0,   339,    85,    86,
      21,    22,    23,     0,    24,    25,    26,    27,    87,   246,
       0,    28,   138,     0,    29,     0,   320,    70,    88,    89,
      23,     0,    24,    25,    90,    27,   255,     0,     0,     0,
       0,     0,    29,   247,     0,    70,     0,     0,     0,     0,
       0,     0,     0,     0,    92,    93,     0,     0,    94,    95,
     346,     0,   339,     0,    85,    86,   350,     0,     0,   354,
     255,     0,     0,     0,    87,     0,     0,     0,     0,   365,
       0,     0,     0,     0,    88,    89,    23,     0,    24,    25,
      90,    27,    91,     0,     0,     0,     0,     0,    29,     0,
     370,    70,     0,     0,     0,     0,     0,     0,   250,   250,
      92,    93,     0,     0,    94,    95,     0,     0,     0,     0,
     339,     0,   -16,     0,   339,     1,     2,     3,     4,     5,
       6,     7,     0,     8,     9,    10,   391,     0,    12,   395,
     396,    13,    14,    15,    16,    17,    18,    19,    20,     0,
       0,     0,   405,     0,     0,     0,   339,     0,     0,    21,
      22,    23,     0,    24,    25,    26,    27,   160,     0,     0,
      28,     0,     0,    29,     0,     0,    30,     0,     0,     0,
       0,     0,     0,     0,     0,   425,     0,     0,     0,     0,
       0,   430,     1,     2,     3,     4,     5,     6,     7,     0,
       8,     9,    10,    11,     0,    12,     0,     0,    13,    14,
      15,    16,    17,    18,    19,    20,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,    21,    22,    23,     0,
      24,    25,    26,    27,     0,     0,     0,    28,     0,     0,
      29,     0,     0,    30,     1,     2,     3,     4,     5,     6,
       7,     0,     8,     9,    10,     0,     0,    12,     0,     0,
      13,    14,    15,    16,    17,    18,    19,    20,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,    21,    22,
      23,     0,    24,    25,    26,    27,     0,     0,     0,    28,
       0,     0,    29,     0,     0,    30,     1,     2,     3,     4,
       5,     6,     7,     0,     8,     9,    10,     0,     0,     0,
       0,     0,     0,    14,     0,     0,    17,    18,    19,    20,
       0,     0,     0,     0,     0,     0,     0,     0,    85,    86,
      21,    22,    23,     0,    24,    25,    26,    27,    87,     0,
       0,    28,     0,     0,    29,     0,     0,    70,    88,    89,
      23,     0,    24,    25,    90,    27,     0,     0,     0,   251,
      85,    86,    29,     0,     0,    70,     0,     0,     0,     0,
      87,     0,     0,     0,    92,    93,     0,     0,    94,    95,
      88,    89,    23,     0,    24,    25,    90,    27,     0,     0,
       0,     0,    85,    86,    29,     0,     0,    70,     0,     0,
     253,     0,    87,     0,     0,     0,    92,    93,     0,     0,
      94,    95,    88,    89,    23,     0,    24,    25,    90,    27,
     285,     0,     0,     0,    85,    86,    29,     0,     0,    70,
       0,     0,     0,     0,    87,     0,     0,     0,    92,    93,
       0,     0,    94,    95,    88,    89,    23,     0,    24,    25,
      90,    27,     0,     0,     0,     0,   326,     0,    29,    85,
      86,    70,     0,     0,     0,     0,     0,     0,     0,    87,
      92,    93,     0,     0,    94,    95,     0,    85,    86,    88,
      89,    23,     0,    24,    25,    90,    27,    87,     0,     0,
       0,     0,     0,    29,   353,     0,    70,    88,    89,    23,
       0,    24,    25,    90,    27,    92,    93,     0,     0,    94,
      95,    29,   362,     0,    70,    85,    86,     0,     0,     0,
       0,     0,     0,    92,    93,    87,     0,    94,    95,     0,
       0,     0,     0,     0,     0,    88,    89,    23,     0,    24,
      25,    90,    27,     0,     0,     0,     0,    85,    86,    29,
       0,     0,    70,     0,     0,   364,     0,    87,   246,     0,
       0,    92,    93,     0,     0,    94,    95,    88,    89,    23,
       0,    24,    25,    90,    27,     0,     0,     0,     0,    85,
      86,    29,     0,     0,    70,     0,     0,     0,     0,    87,
       0,     0,     0,    92,    93,     0,     0,    94,    95,    88,
      89,    23,     0,    24,    25,    90,    27,     0,     0,     0,
       0,    85,    86,    29,   394,     0,    70,     0,     0,     0,
       0,    87,     0,     0,     0,    92,    93,     0,     0,    94,
      95,    88,    89,    23,     0,    24,    25,    90,    27,     0,
       0,     0,    28,    85,    86,    29,     0,     0,    70,     0,
       0,     0,     0,    87,     0,     0,     0,    92,    93,     0,
       0,    94,    95,    88,    89,    23,     0,    24,    25,    90,
      27,     0,     0,     0,     0,    85,    86,    29,   438,     0,
      70,     0,     0,     0,     0,    87,     0,     0,     0,    92,
      93,     0,     0,    94,    95,    88,    89,    23,     0,    24,
      25,    90,    27,     0,     0,     0,     0,     0,    86,    29,
       0,     0,    70,     0,     0,     0,     0,     0,     0,     0,
       0,    92,    93,     0,     0,    94,    95,    88,    89,    23,
       0,    24,    25,   201,    27,     0,     0,     0,     0,     0,
       0,    29,     0,     0,    70,     0,     0,     0,     0,     0,
       0,     0,     0,    92,    93,     0,     0,    94,    95
    };
  }

private static final short[] yycheck_ = yycheck_init();
  private static final short[] yycheck_init()
  {
    return new short[]
    {
       0,    92,    93,    53,   293,   262,   322,     7,   296,     0,
      15,    12,    43,    30,   294,    50,     7,    48,     0,    46,
      55,    21,    22,     7,    51,     7,   315,     0,    28,    56,
      21,    22,    32,    46,     7,    35,    36,    28,    43,    21,
      22,    32,   330,    56,    35,    36,    28,    47,    48,    49,
      32,    51,    69,    35,    36,    28,    47,    48,    49,    32,
      51,   111,    35,    36,   380,    47,    48,    49,    11,    51,
      56,    57,    11,   384,    47,    48,    49,    45,    51,    80,
      47,    35,    36,    33,    34,    45,    45,    55,    88,    89,
      76,    51,    92,    93,    94,    95,   385,   377,    65,    66,
      43,    55,    45,    45,    43,   416,    88,    89,    58,    59,
      92,    93,    94,    95,    43,    54,    45,   406,   407,    35,
      36,    37,    38,    45,   381,    45,   112,   113,    48,    45,
      45,    46,    50,   224,   225,   226,    51,    43,     0,    55,
     140,    56,   431,    43,    43,     7,   426,    53,    47,   140,
      56,    45,    58,   153,   154,    46,     0,   130,   140,    51,
      51,    55,   153,   154,    55,    56,    28,   140,    45,    46,
      32,   153,   154,    35,    36,   432,   162,    88,    89,    51,
     153,   154,    43,    94,    95,    47,    48,    49,    32,    51,
      34,    35,    36,    43,   244,     0,   182,    47,    45,    56,
     186,    58,   188,    47,    48,    49,    45,    51,    55,    56,
     210,    58,   212,   213,   214,   215,   216,   217,   218,   219,
     220,   221,   222,   223,   224,   225,   226,    51,   210,    51,
     212,   213,   214,   215,   216,   217,   218,   219,   220,   221,
     222,   223,   224,   225,   226,    51,    35,    36,    45,    46,
      51,     0,    35,    36,    37,    38,    31,    32,    47,    48,
      49,    43,    51,    46,    45,    63,    64,    50,    51,    45,
      51,    50,    55,    56,    55,    58,   367,    51,   140,    45,
      46,    50,    56,    32,    58,    51,    35,    36,   288,    50,
      56,   153,   154,    50,   280,    37,    38,   288,    47,    48,
      49,   287,    51,    43,   288,    26,   288,    51,    45,   153,
     154,    43,    56,    43,    58,   288,    39,   317,    41,    42,
      43,    44,    51,    39,    40,    29,   317,    43,    51,   324,
     325,    54,    48,   317,    39,   317,    41,    42,    43,    44,
       0,    39,    40,    60,   317,    43,    51,    61,     8,    54,
      48,    50,    62,   353,    45,    46,    55,    56,    55,    58,
     216,   217,   353,   349,   153,   154,    45,   367,    45,   353,
      30,   353,    32,    45,    34,    35,    36,   222,   223,    45,
     353,   218,   219,   220,   221,   367,    45,    47,    48,    49,
     390,    51,    13,   393,   394,    43,    49,    52,   398,   390,
      51,    17,   393,   394,   153,   154,   390,   398,   390,   393,
     394,   393,   394,    29,   398,    45,   398,   390,    78,   419,
     393,   394,   422,   423,    51,   398,   288,    45,   419,    57,
      45,   422,   423,    18,    43,   419,    43,   419,   422,   423,
     422,   423,    27,    43,    55,    56,   419,    58,    52,   422,
     423,    52,    37,    38,    39,   317,    41,    42,    43,    44,
      52,    45,    53,    79,    52,    45,    51,    46,    84,    54,
      39,    52,    41,    42,    43,   135,    51,    51,    63,    64,
      53,    53,    67,    68,    49,    53,    57,   147,    43,    59,
      43,   353,   152,   153,   154,    45,    57,    45,    45,    45,
      45,    45,   118,   119,   120,    43,    45,    48,    45,    45,
     126,   127,    45,    53,    10,    50,   132,   133,   134,    49,
      49,    53,    53,    43,    52,    43,    52,   187,   390,    45,
     146,   393,   394,    52,    45,    52,   398,    45,    52,    51,
     200,    51,    57,    52,    45,    52,    59,   163,    52,   165,
     166,    53,   168,   169,    52,    52,    51,   419,    52,    52,
     422,   423,    52,    55,    52,    52,    32,   131,   431,    34,
     210,   212,   214,   189,   190,   213,     3,     4,     5,     6,
       7,     8,     9,   140,    11,    12,    13,   441,    -1,   205,
     215,    -1,    -1,    20,    -1,   211,    23,    24,    25,    26,
      -1,    -1,    -1,   263,    -1,    -1,    -1,   267,    17,    18,
      37,    38,    39,    -1,    41,    42,    43,    44,    27,    28,
      -1,    48,    49,    -1,    51,    -1,   242,    54,    37,    38,
      39,    -1,    41,    42,    43,    44,   296,    -1,    -1,    -1,
      -1,    -1,    51,    52,    -1,    54,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    63,    64,    -1,    -1,    67,    68,
     276,    -1,   322,    -1,    17,    18,   282,    -1,    -1,   285,
     330,    -1,    -1,    -1,    27,    -1,    -1,    -1,    -1,   295,
      -1,    -1,    -1,    -1,    37,    38,    39,    -1,    41,    42,
      43,    44,    45,    -1,    -1,    -1,    -1,    -1,    51,    -1,
     316,    54,    -1,    -1,    -1,    -1,    -1,    -1,   324,   325,
      63,    64,    -1,    -1,    67,    68,    -1,    -1,    -1,    -1,
     380,    -1,     0,    -1,   384,     3,     4,     5,     6,     7,
       8,     9,    -1,    11,    12,    13,   352,    -1,    16,   355,
     356,    19,    20,    21,    22,    23,    24,    25,    26,    -1,
      -1,    -1,   368,    -1,    -1,    -1,   416,    -1,    -1,    37,
      38,    39,    -1,    41,    42,    43,    44,    45,    -1,    -1,
      48,    -1,    -1,    51,    -1,    -1,    54,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,   401,    -1,    -1,    -1,    -1,
      -1,   407,     3,     4,     5,     6,     7,     8,     9,    -1,
      11,    12,    13,    14,    -1,    16,    -1,    -1,    19,    20,
      21,    22,    23,    24,    25,    26,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    37,    38,    39,    -1,
      41,    42,    43,    44,    -1,    -1,    -1,    48,    -1,    -1,
      51,    -1,    -1,    54,     3,     4,     5,     6,     7,     8,
       9,    -1,    11,    12,    13,    -1,    -1,    16,    -1,    -1,
      19,    20,    21,    22,    23,    24,    25,    26,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    37,    38,
      39,    -1,    41,    42,    43,    44,    -1,    -1,    -1,    48,
      -1,    -1,    51,    -1,    -1,    54,     3,     4,     5,     6,
       7,     8,     9,    -1,    11,    12,    13,    -1,    -1,    -1,
      -1,    -1,    -1,    20,    -1,    -1,    23,    24,    25,    26,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    17,    18,
      37,    38,    39,    -1,    41,    42,    43,    44,    27,    -1,
      -1,    48,    -1,    -1,    51,    -1,    -1,    54,    37,    38,
      39,    -1,    41,    42,    43,    44,    -1,    -1,    -1,    48,
      17,    18,    51,    -1,    -1,    54,    -1,    -1,    -1,    -1,
      27,    -1,    -1,    -1,    63,    64,    -1,    -1,    67,    68,
      37,    38,    39,    -1,    41,    42,    43,    44,    -1,    -1,
      -1,    -1,    17,    18,    51,    -1,    -1,    54,    -1,    -1,
      57,    -1,    27,    -1,    -1,    -1,    63,    64,    -1,    -1,
      67,    68,    37,    38,    39,    -1,    41,    42,    43,    44,
      45,    -1,    -1,    -1,    17,    18,    51,    -1,    -1,    54,
      -1,    -1,    -1,    -1,    27,    -1,    -1,    -1,    63,    64,
      -1,    -1,    67,    68,    37,    38,    39,    -1,    41,    42,
      43,    44,    -1,    -1,    -1,    -1,    49,    -1,    51,    17,
      18,    54,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    27,
      63,    64,    -1,    -1,    67,    68,    -1,    17,    18,    37,
      38,    39,    -1,    41,    42,    43,    44,    27,    -1,    -1,
      -1,    -1,    -1,    51,    52,    -1,    54,    37,    38,    39,
      -1,    41,    42,    43,    44,    63,    64,    -1,    -1,    67,
      68,    51,    52,    -1,    54,    17,    18,    -1,    -1,    -1,
      -1,    -1,    -1,    63,    64,    27,    -1,    67,    68,    -1,
      -1,    -1,    -1,    -1,    -1,    37,    38,    39,    -1,    41,
      42,    43,    44,    -1,    -1,    -1,    -1,    17,    18,    51,
      -1,    -1,    54,    -1,    -1,    57,    -1,    27,    28,    -1,
      -1,    63,    64,    -1,    -1,    67,    68,    37,    38,    39,
      -1,    41,    42,    43,    44,    -1,    -1,    -1,    -1,    17,
      18,    51,    -1,    -1,    54,    -1,    -1,    -1,    -1,    27,
      -1,    -1,    -1,    63,    64,    -1,    -1,    67,    68,    37,
      38,    39,    -1,    41,    42,    43,    44,    -1,    -1,    -1,
      -1,    17,    18,    51,    52,    -1,    54,    -1,    -1,    -1,
      -1,    27,    -1,    -1,    -1,    63,    64,    -1,    -1,    67,
      68,    37,    38,    39,    -1,    41,    42,    43,    44,    -1,
      -1,    -1,    48,    17,    18,    51,    -1,    -1,    54,    -1,
      -1,    -1,    -1,    27,    -1,    -1,    -1,    63,    64,    -1,
      -1,    67,    68,    37,    38,    39,    -1,    41,    42,    43,
      44,    -1,    -1,    -1,    -1,    17,    18,    51,    52,    -1,
      54,    -1,    -1,    -1,    -1,    27,    -1,    -1,    -1,    63,
      64,    -1,    -1,    67,    68,    37,    38,    39,    -1,    41,
      42,    43,    44,    -1,    -1,    -1,    -1,    -1,    18,    51,
      -1,    -1,    54,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    63,    64,    -1,    -1,    67,    68,    37,    38,    39,
      -1,    41,    42,    43,    44,    -1,    -1,    -1,    -1,    -1,
      -1,    51,    -1,    -1,    54,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    63,    64,    -1,    -1,    67,    68
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
      26,    37,    38,    39,    41,    42,    43,    44,    48,    51,
      54,    71,    72,    73,    74,    75,    77,    80,    82,    83,
      84,    85,    86,    88,    89,    90,    91,    92,    93,    96,
     100,   101,   103,   119,   120,   121,   122,   123,   127,   130,
      43,    45,    45,    45,    39,    41,    42,    43,    45,    50,
      54,    77,    78,    92,   101,   103,    43,   103,    51,    51,
      15,    43,    76,    76,    51,    17,    18,    27,    37,    38,
      43,    45,    63,    64,    67,    68,   101,   105,   106,   107,
     108,   109,   110,   111,   112,   113,   114,   115,   116,   117,
     118,   119,   122,   123,   126,   127,   128,   133,    51,    51,
      51,    43,   122,   123,    43,   123,    35,    36,    37,    38,
      46,    50,    51,    55,    56,    58,    94,   124,    49,    78,
      79,    81,   133,    39,    40,    43,    48,   103,   131,     0,
      71,    43,    54,    92,    96,    73,    73,    73,    73,    73,
      45,    73,    43,    56,   124,    35,    36,    94,    35,    36,
      37,    38,    45,    94,    45,    45,    50,    50,    50,    50,
      43,    26,    43,    45,    56,    94,    43,    11,    43,    45,
      87,   103,   133,    76,    45,    46,    45,    46,   133,    43,
      51,    43,   115,   123,   115,    56,   114,   114,   115,   115,
      30,    69,    29,    60,    61,    62,    31,    32,    33,    34,
      58,    59,    63,    64,    47,    65,    66,    37,    38,    45,
     133,   133,   133,    45,    45,    45,    45,   133,   133,    45,
      45,    43,    48,   101,   125,    84,    28,    52,   102,   129,
     133,    48,   133,    57,   133,   103,   104,    81,    49,    52,
     133,   103,    51,   103,    73,    73,    45,    51,    94,   133,
     133,   133,   133,   133,    45,    45,    51,    45,    45,    94,
      43,   103,    50,    94,   133,    45,   133,    43,    52,    45,
      46,    43,    47,    52,    51,    56,    58,   103,   106,   133,
     107,   108,   109,   110,   111,   111,   112,   112,   112,   112,
     113,   113,   114,   114,   114,    52,    53,    52,    45,    45,
     133,   124,    51,    52,    53,    53,    49,    95,   133,    57,
      53,    59,    49,    43,   131,   103,    43,    98,    99,   103,
      45,    57,    45,    45,    45,    45,   133,    45,    94,    43,
     133,    45,    45,    52,   133,    45,    50,    94,    78,    47,
      45,    80,    52,    95,    57,   133,   104,    53,    50,    80,
     133,    78,    49,    98,   102,   102,    49,    53,   104,    45,
      51,    53,   132,    43,    53,    52,    43,    52,    45,    94,
      52,   133,    78,    52,    52,   133,   133,    45,    10,    45,
      52,    51,    57,    59,   114,   133,    52,    52,    95,    98,
     131,    52,    99,    45,    80,    97,    53,    45,    78,    52,
      78,    78,    52,    52,    78,   133,    51,    52,    80,    80,
     133,    52,    55,    99,    78,    78,    78,    52,    52,    95,
      97,   131,    52,   132
    };
  }

/* YYR1[RULE-NUM] -- Symbol kind of the left-hand side of rule RULE-NUM.  */
  private static final short[] yyr1_ = yyr1_init();
  private static final short[] yyr1_init()
  {
    return new short[]
    {
       0,    70,    71,    71,    72,    72,    72,    72,    73,    73,
      73,    73,    73,    73,    73,    73,    73,    74,    74,    74,
      75,    76,    76,    77,    77,    77,    77,    77,    77,    77,
      77,    77,    77,    77,    77,    77,    77,    77,    77,    77,
      77,    77,    77,    77,    77,    78,    78,    79,    80,    80,
      81,    81,    82,    83,    83,    83,    83,    83,    83,    83,
      84,    84,    85,    85,    86,    86,    86,    86,    86,    86,
      86,    87,    87,    87,    87,    88,    89,    90,    91,    91,
      91,    91,    91,    92,    92,    92,    92,    92,    92,    93,
      94,    94,    94,    95,    95,    96,    96,    97,    97,    98,
      98,    99,    99,    99,    99,   100,   100,   100,   100,   100,
     100,   101,   101,   102,   102,   102,   102,   103,   103,   103,
     104,   104,   105,   105,   106,   106,   107,   107,   108,   108,
     109,   109,   110,   110,   110,   111,   111,   111,   111,   111,
     112,   112,   112,   113,   113,   113,   113,   114,   114,   115,
     115,   115,   115,   115,   116,   116,   116,   117,   117,   117,
     118,   118,   118,   118,   119,   119,   120,   120,   120,   120,
     121,   121,   121,   121,   122,   122,   123,   123,   124,   124,
     125,   125,   125,   126,   126,   127,   127,   127,   128,   128,
     128,   128,   128,   128,   128,   129,   129,   130,   131,   131,
     131,   131,   132,   132,   133,   133,   133
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
       1,     3,     3,     1,     3,     3,     3,     1,     6,     2,
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
       0,   144,   144,   145,   149,   150,   154,   155,   161,   164,
     165,   166,   168,   169,   172,   175,   178,   182,   183,   184,
     188,   196,   197,   201,   202,   203,   204,   208,   212,   216,
     220,   223,   226,   229,   232,   233,   234,   235,   236,   237,
     238,   239,   240,   241,   242,   246,   247,   251,   257,   258,
     262,   263,   267,   271,   272,   273,   274,   275,   276,   277,
     281,   282,   288,   289,   295,   297,   299,   301,   303,   305,
     309,   318,   323,   328,   333,   341,   345,   351,   358,   365,
     372,   379,   386,   395,   400,   405,   410,   413,   416,   422,
     429,   430,   433,   439,   440,   445,   448,   454,   455,   459,
     460,   464,   465,   466,   470,   478,   482,   486,   489,   492,
     495,   504,   507,   513,   514,   515,   516,   520,   524,   525,
     529,   530,   534,   537,   541,   544,   548,   549,   555,   556,
     562,   563,   569,   570,   573,   579,   580,   583,   586,   589,
     595,   596,   599,   605,   606,   609,   612,   618,   619,   623,
     626,   629,   630,   631,   635,   636,   637,   641,   642,   645,
     651,   652,   653,   654,   658,   659,   663,   664,   665,   666,
     670,   671,   672,   673,   677,   681,   685,   690,   697,   698,
     706,   707,   708,   712,   713,   719,   720,   721,   729,   730,
     733,   736,   741,   746,   750,   757,   760,   767,   773,   776,
     777,   778,   782,   785,   789,   790,   791
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
    int code_max = 299;
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
       2,     2,     2,    67,     2,    54,     2,    66,    62,     2,
      51,    52,    47,    63,    53,    64,    46,    65,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,    50,    45,
      58,    55,    59,    69,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,    56,     2,    57,    61,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,    48,    60,    49,    68,     2,     2,     2,
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
      35,    36,    37,    38,    39,    40,    41,    42,    43,    44
    };
  }


  private static final int YYLAST_ = 1358;
  private static final int YYEMPTY_ = -2;
  private static final int YYFINAL_ = 149;
  private static final int YYNTOKENS_ = 70;

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

/* "VondaGrammar.java":3733  */

}
