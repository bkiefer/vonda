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
    S_ELLIPSIS(28),                /* ELLIPSIS  */
    S_LAMBDA(29),                  /* LAMBDA  */
    S_ANDAND(30),                  /* ANDAND  */
    S_OROR(31),                    /* OROR  */
    S_EQEQ(32),                    /* EQEQ  */
    S_NOTEQ(33),                   /* NOTEQ  */
    S_GTEQ(34),                    /* GTEQ  */
    S_LTEQ(35),                    /* LTEQ  */
    S_MINUSEQ(36),                 /* MINUSEQ  */
    S_PLUSEQ(37),                  /* PLUSEQ  */
    S_MINUSMINUS(38),              /* MINUSMINUS  */
    S_PLUSPLUS(39),                /* PLUSPLUS  */
    S_STRING(40),                  /* STRING  */
    S_WILDCARD(41),                /* WILDCARD  */
    S_INT(42),                     /* INT  */
    S_BOOL_LITERAL(43),            /* BOOL_LITERAL  */
    S_IDENTIFIER(44),              /* IDENTIFIER  */
    S_OTHER_LITERAL(45),           /* OTHER_LITERAL  */
    S_46_(46),                     /* ';'  */
    S_47_(47),                     /* '.'  */
    S_48_(48),                     /* '*'  */
    S_49_(49),                     /* '{'  */
    S_50_(50),                     /* '}'  */
    S_51_(51),                     /* ':'  */
    S_52_(52),                     /* '('  */
    S_53_(53),                     /* ')'  */
    S_54_(54),                     /* ','  */
    S_55_(55),                     /* '#'  */
    S_56_(56),                     /* '='  */
    S_57_(57),                     /* '['  */
    S_58_(58),                     /* ']'  */
    S_59_(59),                     /* '<'  */
    S_60_(60),                     /* '>'  */
    S_61_(61),                     /* '|'  */
    S_62_(62),                     /* '^'  */
    S_63_(63),                     /* '&'  */
    S_64_(64),                     /* '+'  */
    S_65_(65),                     /* '-'  */
    S_66_(66),                     /* '/'  */
    S_67_(67),                     /* '%'  */
    S_68_(68),                     /* '!'  */
    S_69_(69),                     /* '~'  */
    S_70_(70),                     /* '?'  */
    S_YYACCEPT(71),                /* $accept  */
    S_root(72),                    /* root  */
    S_imports(73),                 /* imports  */
    S_declarations(74),            /* declarations  */
    S_grammar_file(75),            /* grammar_file  */
    S_visibility_spec(76),         /* visibility_spec  */
    S_includes(77),                /* includes  */
    S_path(78),                    /* path  */
    S_statement_no_def(79),        /* statement_no_def  */
    S_statement(80),               /* statement  */
    S_blk_statement(81),           /* blk_statement  */
    S_block(82),                   /* block  */
    S_statements(83),              /* statements  */
    S_grammar_rule(84),            /* grammar_rule  */
    S_return_statement(85),        /* return_statement  */
    S_if_statement(86),            /* if_statement  */
    S_while_statement(87),         /* while_statement  */
    S_for_statement(88),           /* for_statement  */
    S_var_decl(89),                /* var_decl  */
    S_propose_statement(90),       /* propose_statement  */
    S_timeout_statement(91),       /* timeout_statement  */
    S_switch_statement(92),        /* switch_statement  */
    S_label_statement(93),         /* label_statement  */
    S_var_def(94),                 /* var_def  */
    S_field_def(95),               /* field_def  */
    S_assgn_exp(96),               /* assgn_exp  */
    S_nonempty_exp_list(97),       /* nonempty_exp_list  */
    S_method_declaration(98),      /* method_declaration  */
    S_opt_block(99),               /* opt_block  */
    S_opt_args_list(100),          /* opt_args_list  */
    S_args_list(101),              /* args_list  */
    S_set_operation(102),          /* set_operation  */
    S_function_call(103),          /* function_call  */
    S_nonempty_args_list(104),     /* nonempty_args_list  */
    S_type_spec(105),              /* type_spec  */
    S_type_spec_list(106),         /* type_spec_list  */
    S_ConditionalOrExpression(107), /* ConditionalOrExpression  */
    S_ConditionalAndExpression(108), /* ConditionalAndExpression  */
    S_InclusiveOrExpression(109),  /* InclusiveOrExpression  */
    S_ExclusiveOrExpression(110),  /* ExclusiveOrExpression  */
    S_AndExpression(111),          /* AndExpression  */
    S_EqualityExpression(112),     /* EqualityExpression  */
    S_RelationalExpression(113),   /* RelationalExpression  */
    S_AdditiveExpression(114),     /* AdditiveExpression  */
    S_MultiplicativeExpression(115), /* MultiplicativeExpression  */
    S_CastExpression(116),         /* CastExpression  */
    S_UnaryExpression(117),        /* UnaryExpression  */
    S_LogicalUnaryExpression(118), /* LogicalUnaryExpression  */
    S_PostfixExpression(119),      /* PostfixExpression  */
    S_PrimaryExpression(120),      /* PrimaryExpression  */
    S_ComplexPrimary(121),         /* ComplexPrimary  */
    S_ComplexPrimaryNoParenthesis(122), /* ComplexPrimaryNoParenthesis  */
    S_Literal(123),                /* Literal  */
    S_ArrayAccess(124),            /* ArrayAccess  */
    S_field_access(125),           /* field_access  */
    S_field_access_rest(126),      /* field_access_rest  */
    S_simple_nofa_exp(127),        /* simple_nofa_exp  */
    S_ConditionalExpression(128),  /* ConditionalExpression  */
    S_assignment(129),             /* assignment  */
    S_new_exp(130),                /* new_exp  */
    S_lambda_exp(131),             /* lambda_exp  */
    S_dialogueact_exp(132),        /* dialogueact_exp  */
    S_da_token(133),               /* da_token  */
    S_da_args(134),                /* da_args  */
    S_exp(135);                    /* exp  */


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
      SymbolKind.S_ELLIPSIS,
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
      SymbolKind.S_70_,
      SymbolKind.S_YYACCEPT,
      SymbolKind.S_root,
      SymbolKind.S_imports,
      SymbolKind.S_declarations,
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
  "ISA", "ELLIPSIS", "LAMBDA", "ANDAND", "OROR", "EQEQ", "NOTEQ", "GTEQ",
  "LTEQ", "MINUSEQ", "PLUSEQ", "MINUSMINUS", "PLUSPLUS", "STRING",
  "WILDCARD", "INT", "BOOL_LITERAL", "IDENTIFIER", "OTHER_LITERAL", "';'",
  "'.'", "'*'", "'{'", "'}'", "':'", "'('", "')'", "','", "'#'", "'='",
  "'['", "']'", "'<'", "'>'", "'|'", "'^'", "'&'", "'+'", "'-'", "'/'",
  "'%'", "'!'", "'~'", "'?'", "$accept", "root", "imports", "declarations",
  "grammar_file", "visibility_spec", "includes", "path",
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
  "ComplexPrimary", "ComplexPrimaryNoParenthesis", "Literal",
  "ArrayAccess", "field_access", "field_access_rest", "simple_nofa_exp",
  "ConditionalExpression", "assignment", "new_exp", "lambda_exp",
  "dialogueact_exp", "da_token", "da_args", "exp", null
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
    /** Token ELLIPSIS, to be returned by the scanner.  */
    static final int ELLIPSIS = 283;
    /** Token LAMBDA, to be returned by the scanner.  */
    static final int LAMBDA = 284;
    /** Token ANDAND, to be returned by the scanner.  */
    static final int ANDAND = 285;
    /** Token OROR, to be returned by the scanner.  */
    static final int OROR = 286;
    /** Token EQEQ, to be returned by the scanner.  */
    static final int EQEQ = 287;
    /** Token NOTEQ, to be returned by the scanner.  */
    static final int NOTEQ = 288;
    /** Token GTEQ, to be returned by the scanner.  */
    static final int GTEQ = 289;
    /** Token LTEQ, to be returned by the scanner.  */
    static final int LTEQ = 290;
    /** Token MINUSEQ, to be returned by the scanner.  */
    static final int MINUSEQ = 291;
    /** Token PLUSEQ, to be returned by the scanner.  */
    static final int PLUSEQ = 292;
    /** Token MINUSMINUS, to be returned by the scanner.  */
    static final int MINUSMINUS = 293;
    /** Token PLUSPLUS, to be returned by the scanner.  */
    static final int PLUSPLUS = 294;
    /** Token STRING, to be returned by the scanner.  */
    static final int STRING = 295;
    /** Token WILDCARD, to be returned by the scanner.  */
    static final int WILDCARD = 296;
    /** Token INT, to be returned by the scanner.  */
    static final int INT = 297;
    /** Token BOOL_LITERAL, to be returned by the scanner.  */
    static final int BOOL_LITERAL = 298;
    /** Token IDENTIFIER, to be returned by the scanner.  */
    static final int IDENTIFIER = 299;
    /** Token OTHER_LITERAL, to be returned by the scanner.  */
    static final int OTHER_LITERAL = 300;

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
    /* "VondaGrammar.y":147  */
                 { yyval = ((LinkedList<RudiTree>)(yystack.valueAt (0))); ((LinkedList<RudiTree>)(yystack.valueAt (0))).addFirst(((Import)(yystack.valueAt (1)))); };
  break;


  case 3: /* root: grammar_file  */
  if (yyn == 3)
    /* "VondaGrammar.y":148  */
                 { yyval = ((LinkedList<RudiTree>)(yystack.valueAt (0)));};
  break;


  case 4: /* imports: IMPORT path ';'  */
  if (yyn == 4)
    /* "VondaGrammar.y":152  */
                    { yyval = setPos(new Import(((List<String>)(yystack.valueAt (1)))), (yyloc)); };
  break;


  case 5: /* imports: IMPORT path '.' '*' ';'  */
  if (yyn == 5)
    /* "VondaGrammar.y":153  */
                            {
    ((List<String>)(yystack.valueAt (3))).add("*");
    yyval = setPos(new Import(((List<String>)(yystack.valueAt (3)))), (yyloc));
  };
  break;


  case 6: /* imports: IMPORT STATIC path ';'  */
  if (yyn == 6)
    /* "VondaGrammar.y":157  */
                           { yyval = setPos(new Import(((List<String>)(yystack.valueAt (1))), true), (yyloc)); };
  break;


  case 7: /* imports: IMPORT STATIC path '.' '*' ';'  */
  if (yyn == 7)
    /* "VondaGrammar.y":158  */
                                   {
    ((List<String>)(yystack.valueAt (3))).add("*");
    yyval = setPos(new Import(((List<String>)(yystack.valueAt (3))), true), (yyloc));
  };
  break;


  case 8: /* declarations: visibility_spec method_declaration  */
  if (yyn == 8)
    /* "VondaGrammar.y":164  */
                                       { yyval = ((StatMethodDeclaration)(yystack.valueAt (0))); ((StatMethodDeclaration)(yystack.valueAt (0))).setVisibility(((String)(yystack.valueAt (1)))); };
  break;


  case 9: /* declarations: method_declaration  */
  if (yyn == 9)
    /* "VondaGrammar.y":165  */
                       { yyval = ((StatMethodDeclaration)(yystack.valueAt (0))); };
  break;


  case 10: /* declarations: visibility_spec var_def  */
  if (yyn == 10)
    /* "VondaGrammar.y":166  */
                             { yyval = setPos(new StatFieldDef(((String)(yystack.valueAt (1))), ((StatVarDef)(yystack.valueAt (0)))), yystack.locationAt (1), yystack.locationAt (0)); };
  break;


  case 11: /* declarations: var_def  */
  if (yyn == 11)
    /* "VondaGrammar.y":167  */
             { yyval = setPos(new StatFieldDef(null, ((StatVarDef)(yystack.valueAt (0)))), yystack.locationAt (0)); };
  break;


  case 12: /* declarations: field_def  */
  if (yyn == 12)
    /* "VondaGrammar.y":168  */
              { yyval = ((StatFieldDef)(yystack.valueAt (0))); };
  break;


  case 13: /* grammar_file: declarations grammar_file  */
  if (yyn == 13)
    /* "VondaGrammar.y":172  */
                              { yyval = ((LinkedList<RudiTree>)(yystack.valueAt (0))); ((LinkedList<RudiTree>)(yystack.valueAt (0))).addFirst(((RTStatement)(yystack.valueAt (1)))); };
  break;


  case 14: /* grammar_file: function_call grammar_file  */
  if (yyn == 14)
    /* "VondaGrammar.y":173  */
                               { yyval = ((LinkedList<RudiTree>)(yystack.valueAt (0))); ((LinkedList<RudiTree>)(yystack.valueAt (0))).addFirst(((RTExpression)(yystack.valueAt (1)))); };
  break;


  case 15: /* grammar_file: statement_no_def grammar_file  */
  if (yyn == 15)
    /* "VondaGrammar.y":174  */
                                  { yyval = ((LinkedList<RudiTree>)(yystack.valueAt (0))); ((LinkedList<RudiTree>)(yystack.valueAt (0))).addFirst(((RTStatement)(yystack.valueAt (1)))); };
  break;


  case 16: /* grammar_file: includes grammar_file  */
  if (yyn == 16)
    /* "VondaGrammar.y":176  */
                          { yyval = ((LinkedList<RudiTree>)(yystack.valueAt (0))); ((LinkedList<RudiTree>)(yystack.valueAt (0))).addFirst(((Include)(yystack.valueAt (1)))); };
  break;


  case 17: /* grammar_file: %empty  */
  if (yyn == 17)
    /* "VondaGrammar.y":177  */
           { yyval = _statements; };
  break;


  case 18: /* visibility_spec: PUBLIC  */
  if (yyn == 18)
    /* "VondaGrammar.y":181  */
           { yyval = "public"; };
  break;


  case 19: /* visibility_spec: PROTECTED  */
  if (yyn == 19)
    /* "VondaGrammar.y":182  */
              { yyval = "protected"; };
  break;


  case 20: /* visibility_spec: PRIVATE  */
  if (yyn == 20)
    /* "VondaGrammar.y":183  */
             { yyval = "private"; };
  break;


  case 21: /* includes: INCLUDE path ';'  */
  if (yyn == 21)
    /* "VondaGrammar.y":187  */
                     {
    List<String> path = ((List<String>)(yystack.valueAt (1)));
    String name = path.remove(path.size() - 1);
    yyval = setPos(new Include(name, path.toArray(new String[path.size()])), (yyloc));
  };
  break;


  case 22: /* path: IDENTIFIER  */
  if (yyn == 22)
    /* "VondaGrammar.y":195  */
               { yyval = new ArrayList<String>(){{ add((( String )(yystack.valueAt (0)))); }}; };
  break;


  case 23: /* path: path '.' IDENTIFIER  */
  if (yyn == 23)
    /* "VondaGrammar.y":196  */
                        { yyval = ((List<String>)(yystack.valueAt (2))); ((List<String>)(yystack.valueAt (2))).add((( String )(yystack.valueAt (0)))); };
  break;


  case 24: /* statement_no_def: block  */
  if (yyn == 24)
    /* "VondaGrammar.y":200  */
          { yyval = ((StatAbstractBlock)(yystack.valueAt (0))); };
  break;


  case 25: /* statement_no_def: assignment ';'  */
  if (yyn == 25)
    /* "VondaGrammar.y":201  */
                   { yyval = setPos(getAssignmentStat(((RTExpression)(yystack.valueAt (1)))), (yyloc)); };
  break;


  case 26: /* statement_no_def: field_access ';'  */
  if (yyn == 26)
    /* "VondaGrammar.y":202  */
                     { yyval = setPos(new StatExpression(((RTExpression)(yystack.valueAt (1)))), (yyloc)); };
  break;


  case 27: /* statement_no_def: PLUSPLUS IDENTIFIER ';'  */
  if (yyn == 27)
    /* "VondaGrammar.y":203  */
                            {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (1)))), yystack.locationAt (1));
    yyval = setPos(new StatExpression(createPlusMinus(var, "++", (yyloc))), (yyloc));
  };
  break;


  case 28: /* statement_no_def: MINUSMINUS IDENTIFIER ';'  */
  if (yyn == 28)
    /* "VondaGrammar.y":207  */
                              {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (1)))), yystack.locationAt (1));
    yyval = setPos(new StatExpression(createPlusMinus(var, "--", (yyloc))), (yyloc));
  };
  break;


  case 29: /* statement_no_def: IDENTIFIER PLUSPLUS ';'  */
  if (yyn == 29)
    /* "VondaGrammar.y":211  */
                            {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (2)))), yystack.locationAt (2));
    yyval = setPos(new StatExpression(createPlusMinus(var, "+++", (yyloc))), (yyloc));
  };
  break;


  case 30: /* statement_no_def: IDENTIFIER MINUSMINUS ';'  */
  if (yyn == 30)
    /* "VondaGrammar.y":215  */
                              {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (2)))), yystack.locationAt (2));
    yyval = setPos(new StatExpression(createPlusMinus(var, "---", (yyloc))), (yyloc));
  };
  break;


  case 31: /* statement_no_def: PLUSPLUS field_access ';'  */
  if (yyn == 31)
    /* "VondaGrammar.y":219  */
                              {
    yyval = setPos(new StatExpression(createPlusMinus(((RTExpression)(yystack.valueAt (1))), "++", (yyloc))), (yyloc));
  };
  break;


  case 32: /* statement_no_def: MINUSMINUS field_access ';'  */
  if (yyn == 32)
    /* "VondaGrammar.y":222  */
                                {
    yyval = setPos(new StatExpression(createPlusMinus(((RTExpression)(yystack.valueAt (1))), "--", (yyloc))), (yyloc));
  };
  break;


  case 33: /* statement_no_def: field_access PLUSPLUS ';'  */
  if (yyn == 33)
    /* "VondaGrammar.y":225  */
                              {
    yyval = setPos(new StatExpression(createPlusMinus(((RTExpression)(yystack.valueAt (2))), "+++", (yyloc))), (yyloc));
  };
  break;


  case 34: /* statement_no_def: field_access MINUSMINUS ';'  */
  if (yyn == 34)
    /* "VondaGrammar.y":228  */
                                {
    yyval = setPos(new StatExpression(createPlusMinus(((RTExpression)(yystack.valueAt (2))), "---", (yyloc))), (yyloc));
  };
  break;


  case 35: /* statement_no_def: function_call ';'  */
  if (yyn == 35)
    /* "VondaGrammar.y":231  */
                      { yyval = setPos(new StatExpression(((RTExpression)(yystack.valueAt (1)))), (yyloc)); };
  break;


  case 36: /* statement_no_def: grammar_rule  */
  if (yyn == 36)
    /* "VondaGrammar.y":232  */
                 { yyval = ((StatGrammarRule)(yystack.valueAt (0))); };
  break;


  case 37: /* statement_no_def: set_operation  */
  if (yyn == 37)
    /* "VondaGrammar.y":233  */
                  { yyval = ((RTStatement)(yystack.valueAt (0))); };
  break;


  case 38: /* statement_no_def: return_statement  */
  if (yyn == 38)
    /* "VondaGrammar.y":234  */
                     { yyval = ((RTStatement)(yystack.valueAt (0))); };
  break;


  case 39: /* statement_no_def: propose_statement  */
  if (yyn == 39)
    /* "VondaGrammar.y":235  */
                      { yyval = ((RTStatement)(yystack.valueAt (0))); };
  break;


  case 40: /* statement_no_def: timeout_statement  */
  if (yyn == 40)
    /* "VondaGrammar.y":236  */
                      { yyval = ((RTStatement)(yystack.valueAt (0))); };
  break;


  case 41: /* statement_no_def: if_statement  */
  if (yyn == 41)
    /* "VondaGrammar.y":237  */
                 { yyval = ((StatIf)(yystack.valueAt (0))); };
  break;


  case 42: /* statement_no_def: while_statement  */
  if (yyn == 42)
    /* "VondaGrammar.y":238  */
                    { yyval = ((RTStatement)(yystack.valueAt (0))); };
  break;


  case 43: /* statement_no_def: for_statement  */
  if (yyn == 43)
    /* "VondaGrammar.y":239  */
                  { yyval = ((RTStatement)(yystack.valueAt (0))); };
  break;


  case 44: /* statement_no_def: switch_statement  */
  if (yyn == 44)
    /* "VondaGrammar.y":240  */
                     { yyval = ((RTStatement)(yystack.valueAt (0))); };
  break;


  case 45: /* statement_no_def: label_statement  */
  if (yyn == 45)
    /* "VondaGrammar.y":241  */
                    { yyval = ((RTStatement)(yystack.valueAt (0))); };
  break;


  case 46: /* statement: statement_no_def  */
  if (yyn == 46)
    /* "VondaGrammar.y":245  */
                     { yyval = ((RTStatement)(yystack.valueAt (0))); };
  break;


  case 47: /* statement: var_def  */
  if (yyn == 47)
    /* "VondaGrammar.y":246  */
            { yyval = ((StatVarDef)(yystack.valueAt (0))); };
  break;


  case 48: /* blk_statement: statement  */
  if (yyn == 48)
    /* "VondaGrammar.y":250  */
              { yyval = ((RTStatement)(yystack.valueAt (0))); };
  break;


  case 49: /* block: '{' statements '}'  */
  if (yyn == 49)
    /* "VondaGrammar.y":256  */
                       { yyval = setPos(new StatAbstractBlock(((LinkedList<RTStatement>)(yystack.valueAt (1))), true), (yyloc)); };
  break;


  case 50: /* block: '{' '}'  */
  if (yyn == 50)
    /* "VondaGrammar.y":257  */
            {
    yyval = setPos(new StatAbstractBlock(new ArrayList<RTStatement>(), true), (yyloc));
  };
  break;


  case 51: /* statements: blk_statement  */
  if (yyn == 51)
    /* "VondaGrammar.y":261  */
                          { yyval = new LinkedList<RTStatement>(){{ add(((RTStatement)(yystack.valueAt (0)))); }}; };
  break;


  case 52: /* statements: blk_statement statements  */
  if (yyn == 52)
    /* "VondaGrammar.y":262  */
                             { yyval = ((LinkedList<RTStatement>)(yystack.valueAt (0))); ((LinkedList<RTStatement>)(yystack.valueAt (0))).addFirst(((RTStatement)(yystack.valueAt (1)))); };
  break;


  case 53: /* grammar_rule: IDENTIFIER ':' if_statement  */
  if (yyn == 53)
    /* "VondaGrammar.y":266  */
                                { yyval = setPos(new StatGrammarRule((( String )(yystack.valueAt (2))), ((StatIf)(yystack.valueAt (0)))), (yyloc)); };
  break;


  case 54: /* return_statement: RETURN ';'  */
  if (yyn == 54)
    /* "VondaGrammar.y":270  */
               { yyval = setPos(new StatReturn("return"), (yyloc)); };
  break;


  case 55: /* return_statement: RETURN exp ';'  */
  if (yyn == 55)
    /* "VondaGrammar.y":271  */
                   { yyval = setPos(new StatReturn(((RTExpression)(yystack.valueAt (1)))), (yyloc)); };
  break;


  case 56: /* return_statement: BREAK ';'  */
  if (yyn == 56)
    /* "VondaGrammar.y":272  */
              { yyval = setPos(new StatReturn("break"), (yyloc)); };
  break;


  case 57: /* return_statement: BREAK IDENTIFIER ';'  */
  if (yyn == 57)
    /* "VondaGrammar.y":273  */
                         { yyval = setPos(new StatReturn("break", (( String )(yystack.valueAt (1)))), (yyloc)); };
  break;


  case 58: /* return_statement: CANCEL ';'  */
  if (yyn == 58)
    /* "VondaGrammar.y":274  */
               { yyval = setPos(new StatReturn("cancel"), (yyloc)); };
  break;


  case 59: /* return_statement: CANCEL_ALL ';'  */
  if (yyn == 59)
    /* "VondaGrammar.y":275  */
                   { yyval = setPos(new StatReturn("cancel_all"), (yyloc)); };
  break;


  case 60: /* return_statement: CONTINUE ';'  */
  if (yyn == 60)
    /* "VondaGrammar.y":276  */
                 { yyval = setPos(new StatReturn("continue"), (yyloc)); };
  break;


  case 61: /* if_statement: IF '(' exp ')' statement  */
  if (yyn == 61)
    /* "VondaGrammar.y":280  */
                             { yyval = setPos(new StatIf(((RTExpression)(yystack.valueAt (2))), ((RTStatement)(yystack.valueAt (0))), null), (yyloc)); };
  break;


  case 62: /* if_statement: IF '(' exp ')' statement ELSE statement  */
  if (yyn == 62)
    /* "VondaGrammar.y":281  */
                                            {
    yyval = setPos(new StatIf(((RTExpression)(yystack.valueAt (4))), ((RTStatement)(yystack.valueAt (2))), ((RTStatement)(yystack.valueAt (0)))), (yyloc));
  };
  break;


  case 63: /* while_statement: WHILE '(' exp ')' statement  */
  if (yyn == 63)
    /* "VondaGrammar.y":287  */
                                { yyval = setPos(new StatWhile(((RTExpression)(yystack.valueAt (2))), ((RTStatement)(yystack.valueAt (0))), true), (yyloc)); };
  break;


  case 64: /* while_statement: DO statement WHILE '(' exp ')'  */
  if (yyn == 64)
    /* "VondaGrammar.y":288  */
                                   {
    yyval = setPos(new StatWhile(((RTExpression)(yystack.valueAt (1))), ((RTStatement)(yystack.valueAt (4))), false), (yyloc));
  };
  break;


  case 65: /* for_statement: FOR '(' var_decl exp ';' exp ')' statement  */
  if (yyn == 65)
    /* "VondaGrammar.y":294  */
                                               {
    yyval = setPos(new StatFor1(((StatVarDef)(yystack.valueAt (5))), ((RTExpression)(yystack.valueAt (4))), ((RTExpression)(yystack.valueAt (2))), ((RTStatement)(yystack.valueAt (0)))), (yyloc)); };
  break;


  case 66: /* for_statement: FOR '(' var_decl ';' exp ')' statement  */
  if (yyn == 66)
    /* "VondaGrammar.y":296  */
                                               {
    yyval = setPos(new StatFor1(((StatVarDef)(yystack.valueAt (4))), null, ((RTExpression)(yystack.valueAt (2))), ((RTStatement)(yystack.valueAt (0)))), (yyloc)); };
  break;


  case 67: /* for_statement: FOR '(' var_decl exp ';' ')' statement  */
  if (yyn == 67)
    /* "VondaGrammar.y":298  */
                                               {
    yyval = setPos(new StatFor1(((StatVarDef)(yystack.valueAt (4))), ((RTExpression)(yystack.valueAt (3))), null, ((RTStatement)(yystack.valueAt (0)))), (yyloc)); };
  break;


  case 68: /* for_statement: FOR '(' var_decl ';' ')' statement  */
  if (yyn == 68)
    /* "VondaGrammar.y":300  */
                                               {
    yyval = setPos(new StatFor1(((StatVarDef)(yystack.valueAt (3))), null, null, ((RTStatement)(yystack.valueAt (0)))), (yyloc)); };
  break;


  case 69: /* for_statement: FOR '(' ';' exp ';' exp ')' statement  */
  if (yyn == 69)
    /* "VondaGrammar.y":302  */
                                              {
    yyval = setPos(new StatFor1(null, ((RTExpression)(yystack.valueAt (4))), ((RTExpression)(yystack.valueAt (2))), ((RTStatement)(yystack.valueAt (0)))), (yyloc)); };
  break;


  case 70: /* for_statement: FOR '(' IDENTIFIER ':' exp ')' statement  */
  if (yyn == 70)
    /* "VondaGrammar.y":304  */
                                             {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (4)))), yystack.locationAt (4));
    yyval = setPos(new StatFor2(var, ((RTExpression)(yystack.valueAt (2))), ((RTStatement)(yystack.valueAt (0)))), (yyloc));
  };
  break;


  case 71: /* for_statement: FOR '(' type_spec IDENTIFIER ':' exp ')' statement  */
  if (yyn == 71)
    /* "VondaGrammar.y":308  */
                                                       {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (4)))), yystack.locationAt (4));
    yyval = setPos(new StatFor2(((Type)(yystack.valueAt (5))), var, ((RTExpression)(yystack.valueAt (2))), ((RTStatement)(yystack.valueAt (0)))), (yyloc));
  };
  break;


  case 72: /* var_decl: IDENTIFIER assgn_exp ';'  */
  if (yyn == 72)
    /* "VondaGrammar.y":317  */
                             {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (2)))), yystack.locationAt (2));
    ExpAssignment ass = setPos(new ExpAssignment(var, ((RTExpression)(yystack.valueAt (1)))), yystack.locationAt (2), yystack.locationAt (1));
    yyval = setPos(new StatVarDef(false, Type.getNoType(), ass), (yyloc));
  };
  break;


  case 73: /* var_decl: type_spec IDENTIFIER assgn_exp ';'  */
  if (yyn == 73)
    /* "VondaGrammar.y":322  */
                                       {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (2)))), yystack.locationAt (2));
    ExpAssignment ass = setPos(new ExpAssignment(var, ((RTExpression)(yystack.valueAt (1)))), yystack.locationAt (2), yystack.locationAt (1));
    yyval = setPos(new StatVarDef(false, ((Type)(yystack.valueAt (3))), ass), (yyloc));
  };
  break;


  case 74: /* var_decl: FINAL IDENTIFIER assgn_exp ';'  */
  if (yyn == 74)
    /* "VondaGrammar.y":327  */
                                   {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (2)))), yystack.locationAt (2));
    ExpAssignment ass = setPos(new ExpAssignment(var, ((RTExpression)(yystack.valueAt (1)))), yystack.locationAt (2), yystack.locationAt (1));
    yyval = setPos(new StatVarDef(true, Type.getNoType(), ass), (yyloc));
  };
  break;


  case 75: /* var_decl: FINAL type_spec IDENTIFIER assgn_exp ';'  */
  if (yyn == 75)
    /* "VondaGrammar.y":332  */
                                             {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (2)))), yystack.locationAt (2));
    ExpAssignment ass = setPos(new ExpAssignment(var, ((RTExpression)(yystack.valueAt (1)))), yystack.locationAt (2), yystack.locationAt (1));
    yyval = setPos(new StatVarDef(true, ((Type)(yystack.valueAt (3))), ass), (yyloc));
  };
  break;


  case 76: /* propose_statement: PROPOSE '(' exp ')' block  */
  if (yyn == 76)
    /* "VondaGrammar.y":340  */
                              { yyval = setPos(new StatPropose(((RTExpression)(yystack.valueAt (2))), ((StatAbstractBlock)(yystack.valueAt (0)))), (yyloc)); };
  break;


  case 77: /* timeout_statement: TIMEOUT '(' exp ',' exp ')' block  */
  if (yyn == 77)
    /* "VondaGrammar.y":344  */
                                      {
    yyval = setPos(new StatTimeout(((RTExpression)(yystack.valueAt (4))), ((RTExpression)(yystack.valueAt (2))), ((StatAbstractBlock)(yystack.valueAt (0)))), (yyloc));
  };
  break;


  case 78: /* switch_statement: SWITCH '(' exp ')' block  */
  if (yyn == 78)
    /* "VondaGrammar.y":350  */
                             {
    yyval = setPos(new StatSwitch(((RTExpression)(yystack.valueAt (2))), ((StatAbstractBlock)(yystack.valueAt (0)))), (yyloc));
  };
  break;


  case 79: /* label_statement: CASE STRING ':'  */
  if (yyn == 79)
    /* "VondaGrammar.y":357  */
                      {
    ExpLiteral val =
      new ExpLiteral("case \"" + (( ExpLiteral )(yystack.valueAt (1))).toString() + "\":", "label");
    RTStatement lbl = val.ensureStatement();
    setPos(val, (yyloc)); setPos(lbl, (yyloc));
    yyval = lbl;
  };
  break;


  case 80: /* label_statement: CASE INT ':'  */
  if (yyn == 80)
    /* "VondaGrammar.y":364  */
                      {
    ExpLiteral val =
      new ExpLiteral("case " + (( ExpLiteral )(yystack.valueAt (1))).toString() + ":", "label");
    RTStatement lbl = val.ensureStatement();
    setPos(val, (yyloc)); setPos(lbl, (yyloc));
    yyval = lbl;
  };
  break;


  case 81: /* label_statement: CASE BOOL_LITERAL ':'  */
  if (yyn == 81)
    /* "VondaGrammar.y":371  */
                               {
    ExpLiteral val =
      new ExpLiteral("case " + (( ExpLiteral )(yystack.valueAt (1))).toString() + ":", "label");
    RTStatement lbl = val.ensureStatement();
    setPos(val, (yyloc)); setPos(lbl, (yyloc));
    yyval = lbl;
  };
  break;


  case 82: /* label_statement: CASE IDENTIFIER ':'  */
  if (yyn == 82)
    /* "VondaGrammar.y":378  */
                        {
    ExpLiteral val =
      new ExpLiteral("case " + (( String )(yystack.valueAt (1))) + ":", "label");
    RTStatement lbl = val.ensureStatement();
    setPos(val, (yyloc)); setPos(lbl, (yyloc));
    yyval = lbl;
  };
  break;


  case 83: /* label_statement: DEFAULT ':'  */
  if (yyn == 83)
    /* "VondaGrammar.y":385  */
                      {
    ExpLiteral val = new ExpLiteral("default:", "label");
    RTStatement lbl = val.ensureStatement();
    setPos(val, (yyloc)); setPos(lbl, (yyloc));
    yyval = lbl;
  };
  break;


  case 84: /* var_def: FINAL IDENTIFIER assgn_exp ';'  */
  if (yyn == 84)
    /* "VondaGrammar.y":394  */
                                   {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (2)))), yystack.locationAt (2));
    ExpAssignment ass = setPos(new ExpAssignment(var, ((RTExpression)(yystack.valueAt (1)))), yystack.locationAt (2), yystack.locationAt (1));
    yyval = setPos(new StatVarDef(true, Type.getNoType(), ass), (yyloc));
  };
  break;


  case 85: /* var_def: type_spec IDENTIFIER assgn_exp ';'  */
  if (yyn == 85)
    /* "VondaGrammar.y":399  */
                                       {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (2)))), yystack.locationAt (2));
    ExpAssignment ass = setPos(new ExpAssignment(var, ((RTExpression)(yystack.valueAt (1)))), yystack.locationAt (2), yystack.locationAt (1));
    yyval = setPos(new StatVarDef(false, ((Type)(yystack.valueAt (3))), ass), (yyloc));
  };
  break;


  case 86: /* var_def: FINAL type_spec IDENTIFIER assgn_exp ';'  */
  if (yyn == 86)
    /* "VondaGrammar.y":404  */
                                             {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (2)))), yystack.locationAt (2));
    ExpAssignment ass = setPos(new ExpAssignment(var, ((RTExpression)(yystack.valueAt (1)))), yystack.locationAt (2), yystack.locationAt (1));
    yyval = setPos(new StatVarDef(true, ((Type)(yystack.valueAt (3))), ass), (yyloc));
    };
  break;


  case 87: /* var_def: FINAL IDENTIFIER ';'  */
  if (yyn == 87)
    /* "VondaGrammar.y":409  */
                         {
    yyval = setPos(new StatVarDef(true, Type.getNoType(), (( String )(yystack.valueAt (1)))), (yyloc));
  };
  break;


  case 88: /* var_def: type_spec IDENTIFIER ';'  */
  if (yyn == 88)
    /* "VondaGrammar.y":412  */
                             {
    yyval = setPos(new StatVarDef(false, ((Type)(yystack.valueAt (2))), (( String )(yystack.valueAt (1)))), (yyloc));
  };
  break;


  case 89: /* var_def: FINAL type_spec IDENTIFIER ';'  */
  if (yyn == 89)
    /* "VondaGrammar.y":415  */
                                   {
    yyval = setPos(new StatVarDef(true, ((Type)(yystack.valueAt (2))), (( String )(yystack.valueAt (1)))), (yyloc));
  };
  break;


  case 90: /* field_def: '#' type_spec type_spec IDENTIFIER ';'  */
  if (yyn == 90)
    /* "VondaGrammar.y":421  */
                                           {
    yyval = setPos(new StatFieldDef(null,
           setPos(new StatVarDef(false, ((Type)(yystack.valueAt (2))), (( String )(yystack.valueAt (1)))), (yyloc)), ((Type)(yystack.valueAt (3)))), (yyloc));
  };
  break;


  case 91: /* assgn_exp: '=' exp  */
  if (yyn == 91)
    /* "VondaGrammar.y":428  */
            { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 92: /* assgn_exp: '=' '{' '}'  */
  if (yyn == 92)
    /* "VondaGrammar.y":429  */
                {
    yyval = setPos(new ExpListLiteral(new LinkedList<RTExpression>()), yystack.locationAt (1), yystack.locationAt (0));
  };
  break;


  case 93: /* assgn_exp: '=' '{' nonempty_exp_list '}'  */
  if (yyn == 93)
    /* "VondaGrammar.y":432  */
                                  {
    yyval = setPos(new ExpListLiteral(((LinkedList<RTExpression>)(yystack.valueAt (1)))), yystack.locationAt (2), yystack.locationAt (0));
  };
  break;


  case 94: /* nonempty_exp_list: exp  */
  if (yyn == 94)
    /* "VondaGrammar.y":438  */
        { yyval = new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (0)))); }}; };
  break;


  case 95: /* nonempty_exp_list: exp ',' nonempty_exp_list  */
  if (yyn == 95)
    /* "VondaGrammar.y":439  */
                              { yyval = ((LinkedList<RTExpression>)(yystack.valueAt (0))); ((LinkedList<RTExpression>)(yystack.valueAt (0))).addFirst(((RTExpression)(yystack.valueAt (2)))); };
  break;


  case 96: /* method_declaration: '#' type_spec type_spec IDENTIFIER '(' opt_args_list ')' opt_block  */
  if (yyn == 96)
    /* "VondaGrammar.y":444  */
                                                                       {
    yyval = setPos(new StatMethodDeclaration("public", ((Type)(yystack.valueAt (5))), ((Type)(yystack.valueAt (6))), (( String )(yystack.valueAt (4))), ((LinkedList)(yystack.valueAt (2))), ((StatAbstractBlock)(yystack.valueAt (0)))), (yyloc));
  };
  break;


  case 97: /* method_declaration: type_spec IDENTIFIER '(' opt_args_list ')' opt_block  */
  if (yyn == 97)
    /* "VondaGrammar.y":447  */
                                                         {
    yyval = setPos(new StatMethodDeclaration("public", ((Type)(yystack.valueAt (5))), null, (( String )(yystack.valueAt (4))), ((LinkedList)(yystack.valueAt (2))), ((StatAbstractBlock)(yystack.valueAt (0)))), (yyloc));
  };
  break;


  case 98: /* opt_block: block  */
  if (yyn == 98)
    /* "VondaGrammar.y":453  */
          { yyval = ((StatAbstractBlock)(yystack.valueAt (0))); };
  break;


  case 99: /* opt_block: ';'  */
  if (yyn == 99)
    /* "VondaGrammar.y":454  */
        { yyval = null; };
  break;


  case 100: /* opt_args_list: args_list  */
  if (yyn == 100)
    /* "VondaGrammar.y":458  */
              { yyval = ((LinkedList)(yystack.valueAt (0))); };
  break;


  case 101: /* opt_args_list: %empty  */
  if (yyn == 101)
    /* "VondaGrammar.y":459  */
           { yyval = new LinkedList(); };
  break;


  case 102: /* args_list: IDENTIFIER  */
  if (yyn == 102)
    /* "VondaGrammar.y":463  */
               { yyval = new LinkedList(){{ add(Type.getNoType()); add((( String )(yystack.valueAt (0)))); }}; };
  break;


  case 103: /* args_list: type_spec IDENTIFIER  */
  if (yyn == 103)
    /* "VondaGrammar.y":464  */
                         { yyval = new LinkedList(){{ add(((Type)(yystack.valueAt (1)))); add((( String )(yystack.valueAt (0)))); }}; };
  break;


  case 104: /* args_list: type_spec ELLIPSIS IDENTIFIER  */
  if (yyn == 104)
    /* "VondaGrammar.y":465  */
                                  {
    // TODO: MAKE OPTIONAL SEQUENCE TYPE FROM $1, does this suffice?
    Type optt = new Type("Array",
       new ArrayList<Type>(){{ add(((Type)(yystack.valueAt (2)))); }});
    yyval = new LinkedList(){{ add(optt); add((( String )(yystack.valueAt (0)))); }};
  };
  break;


  case 105: /* args_list: IDENTIFIER ',' args_list  */
  if (yyn == 105)
    /* "VondaGrammar.y":471  */
                             {
    yyval = ((LinkedList)(yystack.valueAt (0)));
    ((LinkedList)(yystack.valueAt (0))).addFirst((( String )(yystack.valueAt (2)))); ((LinkedList)(yystack.valueAt (0))).addFirst(Type.getNoType());
  };
  break;


  case 106: /* args_list: type_spec IDENTIFIER ',' args_list  */
  if (yyn == 106)
    /* "VondaGrammar.y":475  */
                                       {
    yyval = ((LinkedList)(yystack.valueAt (0))); ((LinkedList)(yystack.valueAt (0))).addFirst((( String )(yystack.valueAt (2)))); ((LinkedList)(yystack.valueAt (0))).addFirst(((Type)(yystack.valueAt (3))));
  };
  break;


  case 107: /* set_operation: IDENTIFIER PLUSEQ exp ';'  */
  if (yyn == 107)
    /* "VondaGrammar.y":483  */
                              {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (3)))), yystack.locationAt (3));
    yyval = setPos(new StatSetOperation(var, true, ((RTExpression)(yystack.valueAt (1)))), (yyloc));
  };
  break;


  case 108: /* set_operation: IDENTIFIER MINUSEQ exp ';'  */
  if (yyn == 108)
    /* "VondaGrammar.y":487  */
                               {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (3)))), yystack.locationAt (3));
    yyval = setPos(new StatSetOperation(var, false, ((RTExpression)(yystack.valueAt (1)))), (yyloc));
  };
  break;


  case 109: /* set_operation: ArrayAccess PLUSEQ exp ';'  */
  if (yyn == 109)
    /* "VondaGrammar.y":491  */
                               {
    yyval = setPos(new StatSetOperation(((RTExpression)(yystack.valueAt (3))), true, ((RTExpression)(yystack.valueAt (1)))), (yyloc));
  };
  break;


  case 110: /* set_operation: ArrayAccess MINUSEQ exp ';'  */
  if (yyn == 110)
    /* "VondaGrammar.y":494  */
                                {
    yyval = setPos(new StatSetOperation(((RTExpression)(yystack.valueAt (3))), false, ((RTExpression)(yystack.valueAt (1)))), (yyloc));
  };
  break;


  case 111: /* set_operation: field_access PLUSEQ exp ';'  */
  if (yyn == 111)
    /* "VondaGrammar.y":497  */
                                {
    yyval = setPos(new StatSetOperation(((RTExpression)(yystack.valueAt (3))), true, ((RTExpression)(yystack.valueAt (1)))), (yyloc));
  };
  break;


  case 112: /* set_operation: field_access MINUSEQ exp ';'  */
  if (yyn == 112)
    /* "VondaGrammar.y":500  */
                                 {
    yyval = setPos(new StatSetOperation(((RTExpression)(yystack.valueAt (3))), false, ((RTExpression)(yystack.valueAt (1)))), (yyloc));
  };
  break;


  case 113: /* function_call: IDENTIFIER '(' ')'  */
  if (yyn == 113)
    /* "VondaGrammar.y":509  */
                       {
    yyval = setPos(new ExpFuncCall((( String )(yystack.valueAt (2))), new LinkedList<RTExpression>(), false), (yyloc));
  };
  break;


  case 114: /* function_call: IDENTIFIER '(' nonempty_args_list ')'  */
  if (yyn == 114)
    /* "VondaGrammar.y":512  */
                                           {
    yyval = setPos(new ExpFuncCall((( String )(yystack.valueAt (3))), ((LinkedList<RTExpression>)(yystack.valueAt (1))), false), (yyloc));
  };
  break;


  case 115: /* nonempty_args_list: exp  */
  if (yyn == 115)
    /* "VondaGrammar.y":518  */
        { yyval = new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (0)))); }}; };
  break;


  case 116: /* nonempty_args_list: lambda_exp  */
  if (yyn == 116)
    /* "VondaGrammar.y":519  */
               { yyval = new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (0)))); }}; };
  break;


  case 117: /* nonempty_args_list: exp ',' nonempty_args_list  */
  if (yyn == 117)
    /* "VondaGrammar.y":520  */
                               { yyval = ((LinkedList<RTExpression>)(yystack.valueAt (0))); ((LinkedList<RTExpression>)(yystack.valueAt (0))).addFirst(((RTExpression)(yystack.valueAt (2)))); };
  break;


  case 118: /* nonempty_args_list: lambda_exp ',' nonempty_args_list  */
  if (yyn == 118)
    /* "VondaGrammar.y":521  */
                                      { yyval = ((LinkedList<RTExpression>)(yystack.valueAt (0))); ((LinkedList<RTExpression>)(yystack.valueAt (0))).addFirst(((RTExpression)(yystack.valueAt (2)))); };
  break;


  case 119: /* type_spec: IDENTIFIER '[' ']'  */
  if (yyn == 119)
    /* "VondaGrammar.y":525  */
                       {
    yyval = new Type("Array",
                  new ArrayList<Type>(){{ add(new Type((( String )(yystack.valueAt (2))))); }});
  };
  break;


  case 120: /* type_spec: IDENTIFIER  */
  if (yyn == 120)
    /* "VondaGrammar.y":529  */
               { yyval = new Type((( String )(yystack.valueAt (0)))); };
  break;


  case 121: /* type_spec: IDENTIFIER '<' type_spec_list '>'  */
  if (yyn == 121)
    /* "VondaGrammar.y":530  */
                                      { yyval = new Type((( String )(yystack.valueAt (3))), ((LinkedList<Type>)(yystack.valueAt (1)))); };
  break;


  case 122: /* type_spec_list: type_spec  */
  if (yyn == 122)
    /* "VondaGrammar.y":534  */
              { yyval = new LinkedList<Type>(){{ add(((Type)(yystack.valueAt (0)))); }}; };
  break;


  case 123: /* type_spec_list: type_spec ',' type_spec_list  */
  if (yyn == 123)
    /* "VondaGrammar.y":535  */
                                 { yyval = ((LinkedList<Type>)(yystack.valueAt (0))); ((LinkedList<Type>)(yystack.valueAt (0))).addFirst(((Type)(yystack.valueAt (2)))); };
  break;


  case 124: /* ConditionalOrExpression: ConditionalOrExpression OROR ConditionalAndExpression  */
  if (yyn == 124)
    /* "VondaGrammar.y":539  */
                                                          {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "||"), (yyloc));
  };
  break;


  case 125: /* ConditionalOrExpression: ConditionalAndExpression  */
  if (yyn == 125)
    /* "VondaGrammar.y":542  */
                             { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 126: /* ConditionalAndExpression: ConditionalAndExpression ANDAND InclusiveOrExpression  */
  if (yyn == 126)
    /* "VondaGrammar.y":546  */
                                                          {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "&&"), (yyloc));
  };
  break;


  case 127: /* ConditionalAndExpression: InclusiveOrExpression  */
  if (yyn == 127)
    /* "VondaGrammar.y":549  */
                          { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 128: /* InclusiveOrExpression: ExclusiveOrExpression  */
  if (yyn == 128)
    /* "VondaGrammar.y":553  */
                          { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 129: /* InclusiveOrExpression: InclusiveOrExpression '|' ExclusiveOrExpression  */
  if (yyn == 129)
    /* "VondaGrammar.y":554  */
                                                    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "|"), (yyloc));
  };
  break;


  case 130: /* ExclusiveOrExpression: AndExpression  */
  if (yyn == 130)
    /* "VondaGrammar.y":560  */
                  { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 131: /* ExclusiveOrExpression: ExclusiveOrExpression '^' AndExpression  */
  if (yyn == 131)
    /* "VondaGrammar.y":561  */
                                            {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "^"), (yyloc));
  };
  break;


  case 132: /* AndExpression: EqualityExpression  */
  if (yyn == 132)
    /* "VondaGrammar.y":567  */
                       { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 133: /* AndExpression: AndExpression '&' EqualityExpression  */
  if (yyn == 133)
    /* "VondaGrammar.y":568  */
                                         {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "&"), (yyloc));
  };
  break;


  case 134: /* EqualityExpression: RelationalExpression  */
  if (yyn == 134)
    /* "VondaGrammar.y":574  */
                         { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 135: /* EqualityExpression: EqualityExpression EQEQ RelationalExpression  */
  if (yyn == 135)
    /* "VondaGrammar.y":575  */
                                                 {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "=="), (yyloc));
  };
  break;


  case 136: /* EqualityExpression: EqualityExpression NOTEQ RelationalExpression  */
  if (yyn == 136)
    /* "VondaGrammar.y":578  */
                                                  {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "!="), (yyloc));
  };
  break;


  case 137: /* RelationalExpression: AdditiveExpression  */
  if (yyn == 137)
    /* "VondaGrammar.y":584  */
                       { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 138: /* RelationalExpression: RelationalExpression '<' AdditiveExpression  */
  if (yyn == 138)
    /* "VondaGrammar.y":585  */
                                                {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "<"), (yyloc));
  };
  break;


  case 139: /* RelationalExpression: RelationalExpression '>' AdditiveExpression  */
  if (yyn == 139)
    /* "VondaGrammar.y":588  */
                                                {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), ">"), (yyloc));
  };
  break;


  case 140: /* RelationalExpression: RelationalExpression GTEQ AdditiveExpression  */
  if (yyn == 140)
    /* "VondaGrammar.y":591  */
                                                 {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), ">="), (yyloc));
  };
  break;


  case 141: /* RelationalExpression: RelationalExpression LTEQ AdditiveExpression  */
  if (yyn == 141)
    /* "VondaGrammar.y":594  */
                                                 {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "<="), (yyloc));
  };
  break;


  case 142: /* AdditiveExpression: MultiplicativeExpression  */
  if (yyn == 142)
    /* "VondaGrammar.y":600  */
                             { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 143: /* AdditiveExpression: AdditiveExpression '+' MultiplicativeExpression  */
  if (yyn == 143)
    /* "VondaGrammar.y":601  */
                                                    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "+"), (yyloc));
  };
  break;


  case 144: /* AdditiveExpression: AdditiveExpression '-' MultiplicativeExpression  */
  if (yyn == 144)
    /* "VondaGrammar.y":604  */
                                                    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "-"), (yyloc));
  };
  break;


  case 145: /* MultiplicativeExpression: CastExpression  */
  if (yyn == 145)
    /* "VondaGrammar.y":610  */
                   { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 146: /* MultiplicativeExpression: MultiplicativeExpression '*' CastExpression  */
  if (yyn == 146)
    /* "VondaGrammar.y":611  */
                                                {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "*"), (yyloc));
  };
  break;


  case 147: /* MultiplicativeExpression: MultiplicativeExpression '/' CastExpression  */
  if (yyn == 147)
    /* "VondaGrammar.y":614  */
                                                {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "/"), (yyloc));
  };
  break;


  case 148: /* MultiplicativeExpression: MultiplicativeExpression '%' CastExpression  */
  if (yyn == 148)
    /* "VondaGrammar.y":617  */
                                                {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "%"), (yyloc));
  };
  break;


  case 149: /* CastExpression: UnaryExpression  */
  if (yyn == 149)
    /* "VondaGrammar.y":623  */
                    { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 150: /* CastExpression: ISA '(' type_spec ',' ConditionalOrExpression ')'  */
  if (yyn == 150)
    /* "VondaGrammar.y":624  */
                                                      { yyval = setPos(new ExpCast(((Type)(yystack.valueAt (3))), ((RTExpression)(yystack.valueAt (1)))), (yyloc)); };
  break;


  case 151: /* UnaryExpression: PLUSPLUS UnaryExpression  */
  if (yyn == 151)
    /* "VondaGrammar.y":628  */
                             {
    yyval = createPlusMinus(((RTExpression)(yystack.valueAt (0))), "++", (yyloc));
  };
  break;


  case 152: /* UnaryExpression: MINUSMINUS UnaryExpression  */
  if (yyn == 152)
    /* "VondaGrammar.y":631  */
                               {
    yyval = createPlusMinus(((RTExpression)(yystack.valueAt (0))), "--", (yyloc));
  };
  break;


  case 153: /* UnaryExpression: '+' CastExpression  */
  if (yyn == 153)
    /* "VondaGrammar.y":634  */
                       { yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (0))), null, "+"), (yyloc)); };
  break;


  case 154: /* UnaryExpression: '-' CastExpression  */
  if (yyn == 154)
    /* "VondaGrammar.y":635  */
                       { yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (0))), null, "-"), (yyloc)); };
  break;


  case 155: /* UnaryExpression: LogicalUnaryExpression  */
  if (yyn == 155)
    /* "VondaGrammar.y":636  */
                           { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 156: /* LogicalUnaryExpression: PostfixExpression  */
  if (yyn == 156)
    /* "VondaGrammar.y":640  */
                      { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 157: /* LogicalUnaryExpression: '!' UnaryExpression  */
  if (yyn == 157)
    /* "VondaGrammar.y":641  */
                        { yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (0))), null, "!"), (yyloc)); };
  break;


  case 158: /* LogicalUnaryExpression: '~' UnaryExpression  */
  if (yyn == 158)
    /* "VondaGrammar.y":642  */
                        { yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (0))), null, "~"), (yyloc)); };
  break;


  case 159: /* PostfixExpression: PrimaryExpression  */
  if (yyn == 159)
    /* "VondaGrammar.y":646  */
                      { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 160: /* PostfixExpression: PostfixExpression PLUSPLUS  */
  if (yyn == 160)
    /* "VondaGrammar.y":647  */
                               {
    yyval = createPlusMinus(((RTExpression)(yystack.valueAt (1))), "+++", (yyloc));
  };
  break;


  case 161: /* PostfixExpression: PostfixExpression MINUSMINUS  */
  if (yyn == 161)
    /* "VondaGrammar.y":650  */
                                 {
    yyval = createPlusMinus(((RTExpression)(yystack.valueAt (1))), "---", (yyloc));
  };
  break;


  case 162: /* PrimaryExpression: NULL  */
  if (yyn == 162)
    /* "VondaGrammar.y":656  */
         { yyval = setPos(new ExpLiteral("null", "null"), (yyloc)); };
  break;


  case 163: /* PrimaryExpression: IDENTIFIER  */
  if (yyn == 163)
    /* "VondaGrammar.y":657  */
               { yyval = setPos(new ExpIdentifier((( String )(yystack.valueAt (0)))), (yyloc)); };
  break;


  case 164: /* PrimaryExpression: field_access  */
  if (yyn == 164)
    /* "VondaGrammar.y":658  */
                 { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 165: /* PrimaryExpression: ComplexPrimary  */
  if (yyn == 165)
    /* "VondaGrammar.y":659  */
                   { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 166: /* ComplexPrimary: '(' exp ')'  */
  if (yyn == 166)
    /* "VondaGrammar.y":663  */
                { yyval = ((RTExpression)(yystack.valueAt (1))); ((RTExpression)(yystack.valueAt (1))).generateParens(); };
  break;


  case 167: /* ComplexPrimary: ComplexPrimaryNoParenthesis  */
  if (yyn == 167)
    /* "VondaGrammar.y":664  */
                                { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 168: /* ComplexPrimaryNoParenthesis: Literal  */
  if (yyn == 168)
    /* "VondaGrammar.y":668  */
            { yyval = ((ExpLiteral)(yystack.valueAt (0))); };
  break;


  case 169: /* ComplexPrimaryNoParenthesis: ArrayAccess  */
  if (yyn == 169)
    /* "VondaGrammar.y":669  */
                { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 170: /* ComplexPrimaryNoParenthesis: function_call  */
  if (yyn == 170)
    /* "VondaGrammar.y":670  */
                  { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 171: /* ComplexPrimaryNoParenthesis: dialogueact_exp  */
  if (yyn == 171)
    /* "VondaGrammar.y":671  */
                    { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 172: /* Literal: STRING  */
  if (yyn == 172)
    /* "VondaGrammar.y":675  */
           { yyval = setPos((( ExpLiteral )(yystack.valueAt (0))), (yyloc)); };
  break;


  case 173: /* Literal: INT  */
  if (yyn == 173)
    /* "VondaGrammar.y":676  */
        { yyval = setPos((( ExpLiteral )(yystack.valueAt (0))), (yyloc)); };
  break;


  case 174: /* Literal: OTHER_LITERAL  */
  if (yyn == 174)
    /* "VondaGrammar.y":677  */
                  { yyval = setPos((( ExpLiteral )(yystack.valueAt (0))), (yyloc)); };
  break;


  case 175: /* Literal: BOOL_LITERAL  */
  if (yyn == 175)
    /* "VondaGrammar.y":678  */
                 { yyval = setPos((( ExpLiteral )(yystack.valueAt (0))), (yyloc)); };
  break;


  case 176: /* ArrayAccess: IDENTIFIER '[' exp ']'  */
  if (yyn == 176)
    /* "VondaGrammar.y":682  */
                           {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (3)))), yystack.locationAt (3));
    yyval = setPos(new ExpArrayAccess(var, ((RTExpression)(yystack.valueAt (1)))), (yyloc));
  };
  break;


  case 177: /* ArrayAccess: ComplexPrimary '[' exp ']'  */
  if (yyn == 177)
    /* "VondaGrammar.y":686  */
                               { yyval = setPos(new ExpArrayAccess(((RTExpression)(yystack.valueAt (3))), ((RTExpression)(yystack.valueAt (1)))), (yyloc)); };
  break;


  case 178: /* field_access: IDENTIFIER field_access_rest  */
  if (yyn == 178)
    /* "VondaGrammar.y":690  */
                                 {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (1)))), yystack.locationAt (1));
    ((LinkedList<RTExpression>)(yystack.valueAt (0))).addFirst(var);
    yyval = setPos(new ExpFieldAccess(((LinkedList<RTExpression>)(yystack.valueAt (0)))), (yyloc));
  };
  break;


  case 179: /* field_access: ISA '(' type_spec ',' ConditionalOrExpression ')' field_access_rest  */
  if (yyn == 179)
    /* "VondaGrammar.y":695  */
                                                                        {
    ExpCast c = setPos(new ExpCast(((Type)(yystack.valueAt (4))), ((RTExpression)(yystack.valueAt (2)))), yystack.locationAt (4), yystack.locationAt (2));
    ((LinkedList<RTExpression>)(yystack.valueAt (0))).addFirst(c);
    yyval = setPos(new ExpFieldAccess(((LinkedList<RTExpression>)(yystack.valueAt (0)))), (yyloc));
  };
  break;


  case 180: /* field_access: ComplexPrimary field_access_rest  */
  if (yyn == 180)
    /* "VondaGrammar.y":700  */
                                     {
    ((LinkedList<RTExpression>)(yystack.valueAt (0))).addFirst(((RTExpression)(yystack.valueAt (1))));
    yyval = setPos(new ExpFieldAccess(((LinkedList<RTExpression>)(yystack.valueAt (0)))), (yyloc));
  };
  break;


  case 181: /* field_access_rest: '.' simple_nofa_exp field_access_rest  */
  if (yyn == 181)
    /* "VondaGrammar.y":707  */
                                           { yyval = ((LinkedList<RTExpression>)(yystack.valueAt (0))); ((LinkedList<RTExpression>)(yystack.valueAt (0))).addFirst(((RTExpression)(yystack.valueAt (1)))); };
  break;


  case 182: /* field_access_rest: '.' simple_nofa_exp  */
  if (yyn == 182)
    /* "VondaGrammar.y":708  */
                         {
     List<RTExpression> l = new LinkedList<RTExpression>();
     l.add(((RTExpression)(yystack.valueAt (0))));
     yyval = l;
   };
  break;


  case 183: /* simple_nofa_exp: IDENTIFIER  */
  if (yyn == 183)
    /* "VondaGrammar.y":716  */
               { yyval = setPos(new ExpIdentifier((( String )(yystack.valueAt (0)))), (yyloc)); };
  break;


  case 184: /* simple_nofa_exp: '{' exp '}'  */
  if (yyn == 184)
    /* "VondaGrammar.y":717  */
                { yyval = ((RTExpression)(yystack.valueAt (1))); };
  break;


  case 185: /* simple_nofa_exp: function_call  */
  if (yyn == 185)
    /* "VondaGrammar.y":718  */
                  { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 186: /* ConditionalExpression: ConditionalOrExpression '?' exp ':' exp  */
  if (yyn == 186)
    /* "VondaGrammar.y":722  */
                                            { yyval = setPos(new ExpConditional(((RTExpression)(yystack.valueAt (4))), ((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0)))), (yyloc)); };
  break;


  case 187: /* ConditionalExpression: ConditionalOrExpression  */
  if (yyn == 187)
    /* "VondaGrammar.y":723  */
                            { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 188: /* assignment: field_access assgn_exp  */
  if (yyn == 188)
    /* "VondaGrammar.y":729  */
                           { yyval = setPos(new ExpAssignment(((RTExpression)(yystack.valueAt (1))), ((RTExpression)(yystack.valueAt (0)))), (yyloc)); };
  break;


  case 189: /* assignment: ArrayAccess assgn_exp  */
  if (yyn == 189)
    /* "VondaGrammar.y":730  */
                          { yyval = setPos(new ExpAssignment(((RTExpression)(yystack.valueAt (1))), ((RTExpression)(yystack.valueAt (0)))), (yyloc)); };
  break;


  case 190: /* assignment: IDENTIFIER assgn_exp  */
  if (yyn == 190)
    /* "VondaGrammar.y":731  */
                         {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (1)))), yystack.locationAt (1));
    ExpAssignment ass = setPos(new ExpAssignment(var, ((RTExpression)(yystack.valueAt (0)))), (yyloc));
    yyval = ass;
  };
  break;


  case 191: /* new_exp: NEW IDENTIFIER  */
  if (yyn == 191)
    /* "VondaGrammar.y":739  */
                   { yyval = setPos(new ExpNew(new Type((( String )(yystack.valueAt (0))))), (yyloc)); };
  break;


  case 192: /* new_exp: NEW IDENTIFIER '(' ')'  */
  if (yyn == 192)
    /* "VondaGrammar.y":740  */
                           {
    yyval = setPos(new ExpNew(new Type((( String )(yystack.valueAt (2)))), new LinkedList<>()), (yyloc));
  };
  break;


  case 193: /* new_exp: NEW IDENTIFIER '(' nonempty_exp_list ')'  */
  if (yyn == 193)
    /* "VondaGrammar.y":743  */
                                             {
    yyval = setPos(new ExpNew(new Type((( String )(yystack.valueAt (3)))), ((LinkedList<RTExpression>)(yystack.valueAt (1)))), (yyloc));
  };
  break;


  case 194: /* new_exp: NEW IDENTIFIER '[' exp ']'  */
  if (yyn == 194)
    /* "VondaGrammar.y":746  */
                               {
    List<Type> sub = new ArrayList<Type>() {{ add(new Type((( String )(yystack.valueAt (3))))); }};
    yyval = setPos(new ExpNew(new Type("Array", sub),
                    new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (1)))); }}), (yyloc));
  };
  break;


  case 195: /* new_exp: NEW IDENTIFIER '[' ']' '(' exp ')'  */
  if (yyn == 195)
    /* "VondaGrammar.y":751  */
                                      {
    List<Type> sub = new ArrayList<Type>() {{ add(new Type((( String )(yystack.valueAt (5))))); }};
    yyval = setPos(new ExpNew(new Type("Array", sub),
                    new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (1)))); }}), (yyloc));
  };
  break;


  case 196: /* new_exp: NEW IDENTIFIER '<' type_spec_list '>' '(' ')'  */
  if (yyn == 196)
    /* "VondaGrammar.y":756  */
                                                  {
    yyval = setPos(new ExpNew(new Type((( String )(yystack.valueAt (5))), ((LinkedList<Type>)(yystack.valueAt (3)))),
                    new LinkedList<>()), (yyloc));
  };
  break;


  case 197: /* new_exp: NEW IDENTIFIER '<' type_spec_list '>' '(' nonempty_exp_list ')'  */
  if (yyn == 197)
    /* "VondaGrammar.y":760  */
                                                                    {
    yyval = setPos(new ExpNew(new Type((( String )(yystack.valueAt (6))), ((LinkedList<Type>)(yystack.valueAt (4)))),
                    ((LinkedList<RTExpression>)(yystack.valueAt (1)))), (yyloc));
  };
  break;


  case 198: /* lambda_exp: LAMBDA '(' opt_args_list ')' exp  */
  if (yyn == 198)
    /* "VondaGrammar.y":767  */
                                     {
    yyval = setPos(new ExpLambda(((LinkedList)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0)))), (yyloc));
  };
  break;


  case 199: /* lambda_exp: LAMBDA '(' opt_args_list ')' block  */
  if (yyn == 199)
    /* "VondaGrammar.y":770  */
                                       {
    yyval = setPos(new ExpLambda(((LinkedList)(yystack.valueAt (2))), ((StatAbstractBlock)(yystack.valueAt (0)))), (yyloc));
  };
  break;


  case 200: /* dialogueact_exp: '#' da_token '(' da_token da_args ')'  */
  if (yyn == 200)
    /* "VondaGrammar.y":777  */
                                          {
    yyval = setPos(new ExpDialogueAct(((RTExpression)(yystack.valueAt (4))), ((RTExpression)(yystack.valueAt (2))), ((LinkedList<RTExpression>)(yystack.valueAt (1)))), (yyloc));
  };
  break;


  case 201: /* da_token: '{' exp '}'  */
  if (yyn == 201)
    /* "VondaGrammar.y":783  */
                { yyval = ((RTExpression)(yystack.valueAt (1))); };
  break;


  case 202: /* da_token: IDENTIFIER  */
  if (yyn == 202)
    /* "VondaGrammar.y":786  */
               { yyval = setPos(new ExpLiteral((( String )(yystack.valueAt (0))), "String"), (yyloc)); };
  break;


  case 203: /* da_token: STRING  */
  if (yyn == 203)
    /* "VondaGrammar.y":787  */
           { yyval = setPos((( ExpLiteral )(yystack.valueAt (0))), (yyloc)); };
  break;


  case 204: /* da_token: WILDCARD  */
  if (yyn == 204)
    /* "VondaGrammar.y":788  */
             { yyval = setPos(new ExpLiteral((( String )(yystack.valueAt (0))), "String"), (yyloc)); };
  break;


  case 205: /* da_args: ',' da_token '=' da_token da_args  */
  if (yyn == 205)
    /* "VondaGrammar.y":792  */
                                       {
    yyval = ((LinkedList<RTExpression>)(yystack.valueAt (0))); ((LinkedList<RTExpression>)(yystack.valueAt (0))).addFirst(((RTExpression)(yystack.valueAt (1)))); ((LinkedList<RTExpression>)(yystack.valueAt (0))).addFirst(((RTExpression)(yystack.valueAt (3))));
  };
  break;


  case 206: /* da_args: %empty  */
  if (yyn == 206)
    /* "VondaGrammar.y":795  */
           { yyval = new LinkedList<RTExpression>(); };
  break;


  case 207: /* exp: ConditionalExpression  */
  if (yyn == 207)
    /* "VondaGrammar.y":799  */
                          { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 208: /* exp: assignment  */
  if (yyn == 208)
    /* "VondaGrammar.y":800  */
               { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 209: /* exp: new_exp  */
  if (yyn == 209)
    /* "VondaGrammar.y":801  */
            { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;



/* "VondaGrammar.java":2595  */

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

  private static final short yypact_ninf_ = -375;
  private static final short yytable_ninf_ = -203;

/* YYPACT[STATE-NUM] -- Index in YYTABLE of the portion describing
   STATE-NUM.  */
  private static final short[] yypact_ = yypact_init();
  private static final short[] yypact_init()
  {
    return new short[]
    {
     834,   233,    55,    84,   296,    96,    29,   940,   123,    69,
     107,    -2,   139,  -375,   158,  -375,  -375,   598,   177,   205,
     209,   219,    32,   154,  -375,  -375,  -375,   213,  -375,   758,
    1317,   252,   187,   834,   887,  -375,    -3,   887,   887,  -375,
    -375,  -375,  -375,  -375,  -375,  -375,  -375,  -375,  -375,  -375,
    -375,  -375,  -375,   677,   147,    11,  -375,  -375,    30,   126,
     157,  -375,   235,  -375,  -375,  -375,   240,   244,   247,   264,
    -375,  -375,   280,  -375,   299,  -375,   284,   288,   230,   293,
      19,  1317,   139,  -375,   200,   221,  1317,   301,  -375,   291,
    1349,  1349,   -36,  -375,  1381,  1381,  1349,  1349,  -375,   -22,
     330,   300,   302,   304,   290,   -16,   220,    71,  -375,  -375,
    -375,   309,  -375,    11,   306,   306,  -375,  -375,  -375,   317,
    1317,  1317,  1317,   321,    53,  -375,   320,   155,   323,  1317,
    1317,   324,   326,   129,   360,   420,   959,   992,   321,  -375,
    -375,  -375,  -375,   940,   325,   327,  -375,  -375,    75,  1317,
     321,   322,  -375,  -375,  -375,   285,   321,  -375,  -375,  -375,
    -375,  -375,  -375,    77,  1317,  -375,  1317,  1317,  -375,  1317,
    1317,   331,   335,  -375,  -375,  -375,  -375,  -375,  -375,  -375,
    -375,  -375,   332,    47,  -375,   318,   336,    60,   339,   257,
    1317,  1024,   342,   334,   303,  -375,    44,  -375,   344,   338,
     223,   321,   122,  -375,  -375,  -375,  1317,  -375,  -375,  -375,
    -375,  1381,  1317,  1381,  1381,  1381,  1381,  1381,  1381,  1381,
    1381,  1381,  1381,  1381,  1381,  1381,  1381,  1381,  -375,  -375,
    -375,   341,   345,   347,   348,  -375,  -375,  -375,  -375,   346,
     349,  -375,  -375,   353,  1317,  -375,   351,  -375,   354,  -375,
     355,   357,   358,  1056,  -375,  -375,   365,   361,   366,  -375,
    -375,  -375,   359,   363,   280,   321,  -375,   385,   384,   373,
     387,   388,   389,   390,  -375,  -375,  1317,  -375,  -375,   394,
     -31,   398,  1317,   397,   400,  1092,   404,   144,   940,  -375,
     113,  -375,   407,   406,  1124,  1156,   321,   403,   330,   410,
     300,   302,   304,   290,   -16,   -16,   220,   220,   220,   220,
      71,    71,  -375,  -375,  -375,   406,  1317,   940,  1381,  -375,
    -375,   416,  -375,   385,  -375,  1188,  1188,  -375,   421,   422,
    -375,   321,  -375,  -375,   104,   423,   426,   274,   425,  -375,
      41,  -375,  -375,  -375,  -375,  -375,  -375,   427,  -375,   428,
     306,   429,  -375,  1317,   940,   430,  1220,  1317,   433,   476,
     441,  -375,  -375,  -375,   437,   439,   434,   436,  1381,  1317,
    -375,   440,  -375,    -4,  -375,   444,  -375,  -375,  -375,  1317,
    -375,  -375,   385,   280,   447,   449,   385,   159,   458,   450,
    -375,  -375,   460,   940,   454,  -375,   940,   940,   456,   457,
    -375,   940,  -375,  -375,  1317,  -375,   461,     4,  -375,   406,
     351,  1252,  -375,   462,   463,  -375,  -375,  -375,  -375,  -375,
    -375,   385,  -375,  -375,   940,  -375,  -375,   940,   940,  -375,
     465,  1285,   351,  -375,  -375,  -375,  -375,   159,   280,  -375,
    -375,  -375,  -375,  -375,  -375,   467,  -375,   423,  -375,  -375
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
      17,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,    20,     0,    19,    18,     0,     0,     0,
       0,     0,     0,     0,   172,   173,   175,   120,   174,     0,
       0,     0,     0,    17,    17,     3,     0,    17,    17,    24,
      36,    38,    41,    42,    43,    39,    40,    44,    45,    11,
      12,     9,    37,   170,     0,     0,   167,   168,   169,     0,
       0,   171,     0,    56,    58,    59,     0,     0,     0,     0,
      60,    83,     0,    46,     0,    47,   170,     0,   120,     0,
       0,     0,     0,    22,     0,     0,     0,     0,   162,     0,
       0,     0,   163,    54,     0,     0,     0,     0,   170,   187,
     125,   127,   128,   130,   132,   134,   137,   142,   145,   149,
     155,   156,   159,   165,   169,   164,   207,   208,   209,     0,
       0,     0,     0,     0,     0,   169,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,   190,
     178,    50,    48,    51,     0,     0,   203,   204,   120,     0,
       0,     0,     1,     2,    13,   120,     0,    10,     8,    16,
      15,    35,    14,     0,     0,   180,     0,     0,   189,     0,
       0,     0,     0,    26,   188,    25,    57,    79,    80,    81,
      82,   202,     0,     0,    87,     0,     0,     0,     0,   120,
       0,     0,     0,     0,     0,     4,     0,    21,     0,     0,
     191,     0,   163,   152,   164,   151,     0,   153,   154,   157,
     158,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,   161,   160,
      55,     0,     0,     0,     0,    28,    32,    27,    31,     0,
       0,    30,    29,   183,     0,   185,   182,    53,     0,   113,
       0,   116,   115,     0,    91,   119,     0,   122,     0,    52,
      49,   166,     0,     0,     0,     0,    88,   101,     0,     0,
       0,     0,     0,     0,    34,    33,     0,    84,    89,     0,
     120,     0,     0,     0,     0,     0,     0,     0,     0,     6,
       0,    23,     0,     0,     0,     0,     0,     0,   124,     0,
     126,   129,   131,   133,   135,   136,   140,   141,   138,   139,
     143,   144,   146,   147,   148,     0,     0,     0,     0,   108,
     107,     0,   181,   101,   114,     0,     0,    92,     0,    94,
     176,     0,   121,   201,     0,   206,     0,   120,     0,   100,
       0,    85,   177,   110,   109,   112,   111,     0,    86,     0,
       0,     0,    72,     0,     0,     0,     0,     0,     0,    61,
       0,     5,    76,   192,     0,     0,     0,     0,     0,     0,
      78,     0,    63,     0,   184,     0,   118,   117,    93,     0,
     123,    90,   101,     0,     0,     0,     0,     0,     0,   103,
      64,    74,     0,     0,     0,    68,     0,     0,     0,     0,
      73,     0,     7,   193,     0,   194,     0,     0,   186,     0,
       0,     0,    95,     0,     0,   200,   105,    99,    98,    97,
     104,     0,    75,    70,     0,    66,    67,     0,     0,    62,
       0,     0,   150,    77,   179,   199,   198,     0,     0,   106,
      69,    65,    71,   195,   196,     0,    96,   206,   197,   205
    };
  }

/* YYPGOTO[NTERM-NUM].  */
  private static final short[] yypgoto_ = yypgoto_init();
  private static final short[] yypgoto_init()
  {
    return new short[]
    {
    -375,   481,  -375,  -375,   225,  -375,  -375,    -9,    45,    24,
    -375,  -283,   374,  -375,  -375,   391,  -375,  -375,  -375,  -375,
    -375,  -375,  -375,   273,  -375,     3,  -290,   485,    85,  -318,
    -374,  -375,   115,    26,   266,  -260,  -312,   312,   311,   313,
     319,   316,   138,    34,   135,   -80,    80,  -375,  -375,  -375,
     102,  -375,  -375,     0,    17,   -53,  -375,  -375,   151,  -375,
    -375,  -375,  -263,    81,   464
    };
  }

/* YYDEFGOTO[NTERM-NUM].  */
  private static final short[] yydefgoto_ = yydefgoto_init();
  private static final short[] yydefgoto_init()
  {
    return new short[]
    {
       0,    32,    33,    34,    35,    36,    37,    84,    73,   142,
     143,    39,   144,    40,    41,    42,    43,    44,   191,    45,
      46,    47,    48,    75,    50,   139,   328,    51,   419,   338,
     339,    52,    98,   250,    77,   258,    99,   100,   101,   102,
     103,   104,   105,   106,   107,   108,   109,   110,   111,   112,
     113,    56,    57,   114,   115,   140,   246,   116,   117,   118,
     251,    61,   151,   384,   329
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
      58,   335,   165,    85,   364,   375,   373,    58,     8,   211,
     362,   133,   416,    82,   207,   208,   135,    59,   219,   220,
     136,   206,   125,   125,    59,   136,   185,   211,   138,    58,
     188,    74,   370,    58,    58,   211,   367,    58,    58,   126,
     128,   155,    83,   221,   222,    38,    59,   439,   212,   410,
      59,    59,   156,    58,    59,    59,   407,   432,   133,    21,
     165,   168,   174,   189,   413,   190,   166,   167,   164,   388,
      59,   380,    24,   194,    25,    26,   124,    28,    38,    38,
      71,   186,    38,    38,    30,   389,   136,    72,   291,   412,
     125,   125,   292,   266,   125,   125,   125,   125,    38,   235,
     133,    64,    55,   136,   418,   135,   278,   204,   204,    55,
     206,   204,   204,   204,   204,    53,   136,   168,   174,   225,
     414,    80,    76,   266,    55,    55,   433,  -202,   435,   267,
      65,    55,   185,   136,   138,    55,    55,   226,   227,    55,
      55,   445,    70,    58,    76,   312,   313,   314,    53,    53,
     381,    60,    53,    53,   418,    55,   382,   291,    60,    81,
      59,   360,   169,   170,   171,   172,   268,    78,    53,   133,
     203,   205,   173,   243,   135,   447,   209,   210,   244,   206,
      60,    21,   136,    83,    60,    60,   268,   152,    60,    60,
     279,   163,   283,   322,    24,   357,    25,    26,   127,    28,
     136,   237,   133,   175,    60,   417,    30,   135,    29,    72,
      86,   125,   206,   125,   125,   125,   125,   125,   125,   125,
     125,   125,   125,   125,   125,   125,   125,   125,   204,   120,
     204,   204,   204,   204,   204,   204,   204,   204,   204,   204,
     204,   204,   204,   204,   204,    55,   195,   196,   245,   129,
     130,   131,   132,   306,   307,   308,   309,   121,    76,   154,
     133,   122,   159,   160,   134,   135,    54,   197,   198,   136,
     137,   123,   138,    49,    79,   294,   184,    62,   162,    63,
     295,   176,   296,   349,   223,   224,   136,   185,    58,   138,
     358,   177,   146,   147,    60,   178,   148,   150,   179,    54,
      54,   149,    54,    54,    54,    59,    49,    49,   282,   157,
      49,    49,   359,   136,   185,   180,   138,    58,   125,    54,
     146,   147,   217,   218,   181,   182,    49,  -102,   386,   149,
     161,   185,   183,   138,    59,   204,    66,   187,    67,    68,
      69,   372,   185,   201,   138,   200,   192,   228,   229,   289,
     290,   376,   377,   392,    58,   304,   305,   434,   310,   311,
     213,   214,   136,   230,   215,   155,   236,   216,   125,   238,
     241,    59,   242,    10,   264,   260,   255,   274,   395,   434,
     261,   275,   277,   280,   276,   204,   287,   288,   291,   234,
      55,   293,   319,    58,   315,   320,    58,    58,   133,   316,
     317,    58,   318,    76,   257,   135,   323,   334,   324,   333,
      59,   325,   326,    59,    59,   331,   263,   423,    59,    55,
     425,   426,   265,   330,    58,   429,   332,    58,    58,   337,
     341,   342,    76,   343,   344,   345,   346,    87,    88,    60,
     348,    59,   350,   352,    59,    59,   353,    89,   440,   248,
     356,   441,   442,   361,   281,    29,    55,   368,    90,    91,
      24,   369,    25,    26,    92,    28,   374,   297,    60,    76,
     385,   378,    30,   249,   391,    72,   379,   383,   387,   400,
     390,   119,   393,   396,    94,    95,   401,   402,    96,    97,
     403,   404,   405,   409,   145,    55,   406,   411,    55,    55,
     415,   382,   420,    55,   421,    60,   422,   424,    76,   427,
     428,    76,    76,   431,   153,   437,    76,   259,   443,   438,
     448,   158,   446,   298,   300,   247,    55,   301,   449,    55,
      55,   336,   303,   340,   302,     0,     0,     0,     0,    76,
       0,     0,    76,    76,    60,   193,     0,    60,    60,     0,
     199,     0,    60,     0,     0,     0,     0,     0,     0,     0,
       0,     0,   257,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,    60,     0,     0,    60,    60,
       0,     0,     0,     0,   231,   232,   233,     0,     0,   340,
       0,     0,     0,   239,   240,     0,     0,   257,     0,   252,
     254,   256,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,   262,     0,    87,    88,     0,     0,     0,
       0,     0,     0,     0,     0,    89,     0,     0,   269,     0,
     270,   271,     0,   272,   273,     0,    90,    91,    24,     0,
      25,    26,    92,    28,    93,     0,     0,     0,   340,     0,
      30,     0,   340,    72,   284,   286,     0,     0,     0,     0,
       0,     0,    94,    95,     0,     0,    96,    97,     0,     0,
     256,     0,     0,     0,     0,     0,   299,   -17,     0,     0,
       1,     2,     3,     4,     5,     6,     7,   340,     8,     9,
      10,     0,     0,    12,     0,     0,    13,    14,    15,    16,
      17,    18,    19,    20,    21,     0,     0,     0,   321,     0,
       0,     0,     0,     0,     0,    22,    23,    24,     0,    25,
      26,    27,    28,   161,     0,     0,    29,     0,     0,    30,
       0,     0,    31,     0,     0,     0,     0,     0,     0,     0,
     347,     0,     0,     0,     0,     0,   351,     0,     0,   355,
       0,     0,     0,     0,     0,     0,     0,     0,     0,   366,
       0,     1,     2,     3,     4,     5,     6,     7,     0,     8,
       9,    10,     0,     0,     0,     0,     0,     0,    14,     0,
     371,    17,    18,    19,    20,    21,     0,     0,     0,   252,
     252,     0,     0,     0,     0,     0,    22,    23,    24,     0,
      25,    26,    27,    28,     0,     0,     0,    29,   141,     0,
      30,     0,     0,    72,     0,     0,     0,   394,     0,     0,
     398,   399,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,   408,     0,     0,     0,     1,     2,     3,
       4,     5,     6,     7,     0,     8,     9,    10,    11,     0,
      12,     0,     0,    13,    14,    15,    16,    17,    18,    19,
      20,    21,     0,     0,     0,     0,     0,     0,   430,     0,
       0,     0,    22,    23,    24,   436,    25,    26,    27,    28,
       0,     0,     0,    29,     0,     0,    30,     0,     0,    31,
       1,     2,     3,     4,     5,     6,     7,     0,     8,     9,
      10,     0,     0,    12,     0,     0,    13,    14,    15,    16,
      17,    18,    19,    20,    21,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,    22,    23,    24,     0,    25,
      26,    27,    28,     0,     0,     0,    29,     0,     0,    30,
       0,     0,    31,     1,     2,     3,     4,     5,     6,     7,
       0,     8,     9,    10,     0,     0,     0,     0,     0,     0,
      14,     0,     0,    17,    18,    19,    20,    21,     0,     0,
       0,     0,     0,     0,     0,     0,    87,    88,    22,    23,
      24,     0,    25,    26,    27,    28,    89,     0,     0,    29,
       0,     0,    30,     0,     0,    72,     0,    90,    91,    24,
       0,    25,    26,    92,    28,     0,     0,     0,   253,    87,
      88,    30,     0,     0,    72,     0,     0,     0,     0,    89,
       0,     0,     0,    94,    95,     0,     0,    96,    97,     0,
      90,    91,    24,     0,    25,    26,    92,    28,     0,     0,
       0,    87,    88,     0,    30,     0,     0,    72,     0,     0,
     255,    89,     0,     0,     0,     0,    94,    95,     0,     0,
      96,    97,    90,    91,    24,     0,    25,    26,    92,    28,
     285,     0,     0,    87,    88,     0,    30,     0,     0,    72,
       0,     0,     0,    89,     0,     0,     0,     0,    94,    95,
       0,     0,    96,    97,    90,    91,    24,     0,    25,    26,
      92,    28,     0,     0,     0,     0,   327,     0,    30,    87,
      88,    72,     0,     0,     0,     0,     0,     0,     0,    89,
      94,    95,     0,     0,    96,    97,     0,     0,     0,     0,
      90,    91,    24,     0,    25,    26,    92,    28,     0,     0,
       0,    87,    88,     0,    30,   354,     0,    72,     0,     0,
       0,    89,     0,     0,     0,     0,    94,    95,     0,     0,
      96,    97,    90,    91,    24,     0,    25,    26,    92,    28,
       0,     0,     0,    87,    88,     0,    30,   363,     0,    72,
       0,     0,     0,    89,     0,     0,     0,     0,    94,    95,
       0,     0,    96,    97,    90,    91,    24,     0,    25,    26,
      92,    28,     0,     0,     0,    87,    88,     0,    30,     0,
       0,    72,     0,     0,   365,    89,     0,   248,     0,     0,
      94,    95,     0,     0,    96,    97,    90,    91,    24,     0,
      25,    26,    92,    28,     0,     0,     0,    87,    88,     0,
      30,     0,     0,    72,     0,     0,     0,    89,     0,     0,
       0,     0,    94,    95,     0,     0,    96,    97,    90,    91,
      24,     0,    25,    26,    92,    28,     0,     0,     0,    87,
      88,     0,    30,   397,     0,    72,     0,     0,     0,    89,
       0,     0,     0,     0,    94,    95,     0,     0,    96,    97,
      90,    91,    24,     0,    25,    26,    92,    28,     0,     0,
       0,    29,    87,    88,    30,     0,     0,    72,     0,     0,
       0,     0,    89,     0,     0,     0,    94,    95,     0,     0,
      96,    97,     0,    90,    91,    24,     0,    25,    26,    92,
      28,     0,     0,     0,    87,    88,     0,    30,   444,     0,
      72,     0,     0,     0,    89,     0,     0,     0,     0,    94,
      95,     0,     0,    96,    97,    90,    91,    24,     0,    25,
      26,    92,    28,     0,     0,     0,     0,    88,     0,    30,
       0,     0,    72,     0,     0,     0,    21,     0,     0,     0,
       0,    94,    95,     0,     0,    96,    97,    90,    91,    24,
       0,    25,    26,   202,    28,     0,     0,     0,     0,    88,
       0,    30,     0,     0,    72,     0,     0,     0,    89,     0,
       0,     0,     0,    94,    95,     0,     0,    96,    97,    90,
      91,    24,     0,    25,    26,   202,    28,     0,     0,     0,
       0,     0,     0,    30,     0,     0,    72,     0,     0,     0,
       0,     0,     0,     0,     0,    94,    95,     0,     0,    96,
      97
    };
  }

private static final short[] yycheck_ = yycheck_init();
  private static final short[] yycheck_init()
  {
    return new short[]
    {
       0,   264,    55,    12,   294,   323,   318,     7,    11,    31,
     293,    47,   386,    15,    94,    95,    52,     0,    34,    35,
      56,    57,    22,    23,     7,    56,    57,    31,    59,    29,
      11,     7,   315,    33,    34,    31,   296,    37,    38,    22,
      23,    44,    44,    59,    60,     0,    29,   421,    70,    53,
      33,    34,    55,    53,    37,    38,   368,    53,    47,    27,
     113,    58,    59,    44,   382,    46,    36,    37,    57,    28,
      53,   331,    40,    82,    42,    43,    44,    45,    33,    34,
      51,    78,    37,    38,    52,    44,    56,    55,    44,   379,
      90,    91,    48,    46,    94,    95,    96,    97,    53,    46,
      47,    46,     0,    56,   387,    52,    46,    90,    91,     7,
      57,    94,    95,    96,    97,     0,    56,   114,   115,    48,
     383,    52,     7,    46,    22,    23,   409,    52,   411,    52,
      46,    29,    57,    56,    59,    33,    34,    66,    67,    37,
      38,   431,    46,   143,    29,   225,   226,   227,    33,    34,
      46,     0,    37,    38,   437,    53,    52,    44,     7,    52,
     143,    48,    36,    37,    38,    39,   163,    44,    53,    47,
      90,    91,    46,    44,    52,   438,    96,    97,    49,    57,
      29,    27,    56,    44,    33,    34,   183,     0,    37,    38,
     187,    44,   189,   246,    40,    51,    42,    43,    44,    45,
      56,    46,    47,    46,    53,    46,    52,    52,    49,    55,
      52,   211,    57,   213,   214,   215,   216,   217,   218,   219,
     220,   221,   222,   223,   224,   225,   226,   227,   211,    52,
     213,   214,   215,   216,   217,   218,   219,   220,   221,   222,
     223,   224,   225,   226,   227,   143,    46,    47,   133,    36,
      37,    38,    39,   219,   220,   221,   222,    52,   143,    34,
      47,    52,    37,    38,    51,    52,     0,    46,    47,    56,
      57,    52,    59,     0,     8,    52,    46,    44,    53,    46,
      57,    46,    59,   280,    64,    65,    56,    57,   288,    59,
     287,    51,    40,    41,   143,    51,    44,    31,    51,    33,
      34,    49,    36,    37,    38,   288,    33,    34,    51,    36,
      37,    38,   288,    56,    57,    51,    59,   317,   318,    53,
      40,    41,    32,    33,    44,    26,    53,    53,    54,    49,
      46,    57,    44,    59,   317,   318,    40,    44,    42,    43,
      44,   317,    57,    52,    59,    44,    80,    38,    39,    46,
      47,   325,   326,   350,   354,   217,   218,   410,   223,   224,
      30,    61,    56,    46,    62,    44,    46,    63,   368,    46,
      46,   354,    46,    13,    52,    50,    58,    46,   354,   432,
      53,    46,    46,    44,    52,   368,    44,    53,    44,   123,
     288,    53,    46,   393,    53,    46,   396,   397,    47,    54,
      53,   401,    54,   288,   138,    52,    52,    44,    53,    50,
     393,    54,    54,   396,   397,    54,   150,   393,   401,   317,
     396,   397,   156,    58,   424,   401,    60,   427,   428,    44,
      46,    58,   317,    46,    46,    46,    46,    17,    18,   288,
      46,   424,    44,    46,   427,   428,    46,    27,   424,    29,
      46,   427,   428,    46,   188,    49,   354,    54,    38,    39,
      40,    51,    42,    43,    44,    45,    50,   201,   317,   354,
      44,    50,    52,    53,    46,    55,    54,    54,    53,    46,
      53,    17,    53,    53,    64,    65,    10,    46,    68,    69,
      53,    52,    58,    53,    30,   393,    60,    53,   396,   397,
      53,    52,    44,   401,    54,   354,    46,    53,   393,    53,
      53,   396,   397,    52,    33,    53,   401,   143,    53,    56,
      53,    36,   437,   211,   213,   134,   424,   214,   447,   427,
     428,   265,   216,   267,   215,    -1,    -1,    -1,    -1,   424,
      -1,    -1,   427,   428,   393,    81,    -1,   396,   397,    -1,
      86,    -1,   401,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,   296,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,   424,    -1,    -1,   427,   428,
      -1,    -1,    -1,    -1,   120,   121,   122,    -1,    -1,   323,
      -1,    -1,    -1,   129,   130,    -1,    -1,   331,    -1,   135,
     136,   137,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,   149,    -1,    17,    18,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    27,    -1,    -1,   164,    -1,
     166,   167,    -1,   169,   170,    -1,    38,    39,    40,    -1,
      42,    43,    44,    45,    46,    -1,    -1,    -1,   382,    -1,
      52,    -1,   386,    55,   190,   191,    -1,    -1,    -1,    -1,
      -1,    -1,    64,    65,    -1,    -1,    68,    69,    -1,    -1,
     206,    -1,    -1,    -1,    -1,    -1,   212,     0,    -1,    -1,
       3,     4,     5,     6,     7,     8,     9,   421,    11,    12,
      13,    -1,    -1,    16,    -1,    -1,    19,    20,    21,    22,
      23,    24,    25,    26,    27,    -1,    -1,    -1,   244,    -1,
      -1,    -1,    -1,    -1,    -1,    38,    39,    40,    -1,    42,
      43,    44,    45,    46,    -1,    -1,    49,    -1,    -1,    52,
      -1,    -1,    55,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
     276,    -1,    -1,    -1,    -1,    -1,   282,    -1,    -1,   285,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,   295,
      -1,     3,     4,     5,     6,     7,     8,     9,    -1,    11,
      12,    13,    -1,    -1,    -1,    -1,    -1,    -1,    20,    -1,
     316,    23,    24,    25,    26,    27,    -1,    -1,    -1,   325,
     326,    -1,    -1,    -1,    -1,    -1,    38,    39,    40,    -1,
      42,    43,    44,    45,    -1,    -1,    -1,    49,    50,    -1,
      52,    -1,    -1,    55,    -1,    -1,    -1,   353,    -1,    -1,
     356,   357,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,   369,    -1,    -1,    -1,     3,     4,     5,
       6,     7,     8,     9,    -1,    11,    12,    13,    14,    -1,
      16,    -1,    -1,    19,    20,    21,    22,    23,    24,    25,
      26,    27,    -1,    -1,    -1,    -1,    -1,    -1,   404,    -1,
      -1,    -1,    38,    39,    40,   411,    42,    43,    44,    45,
      -1,    -1,    -1,    49,    -1,    -1,    52,    -1,    -1,    55,
       3,     4,     5,     6,     7,     8,     9,    -1,    11,    12,
      13,    -1,    -1,    16,    -1,    -1,    19,    20,    21,    22,
      23,    24,    25,    26,    27,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    38,    39,    40,    -1,    42,
      43,    44,    45,    -1,    -1,    -1,    49,    -1,    -1,    52,
      -1,    -1,    55,     3,     4,     5,     6,     7,     8,     9,
      -1,    11,    12,    13,    -1,    -1,    -1,    -1,    -1,    -1,
      20,    -1,    -1,    23,    24,    25,    26,    27,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    17,    18,    38,    39,
      40,    -1,    42,    43,    44,    45,    27,    -1,    -1,    49,
      -1,    -1,    52,    -1,    -1,    55,    -1,    38,    39,    40,
      -1,    42,    43,    44,    45,    -1,    -1,    -1,    49,    17,
      18,    52,    -1,    -1,    55,    -1,    -1,    -1,    -1,    27,
      -1,    -1,    -1,    64,    65,    -1,    -1,    68,    69,    -1,
      38,    39,    40,    -1,    42,    43,    44,    45,    -1,    -1,
      -1,    17,    18,    -1,    52,    -1,    -1,    55,    -1,    -1,
      58,    27,    -1,    -1,    -1,    -1,    64,    65,    -1,    -1,
      68,    69,    38,    39,    40,    -1,    42,    43,    44,    45,
      46,    -1,    -1,    17,    18,    -1,    52,    -1,    -1,    55,
      -1,    -1,    -1,    27,    -1,    -1,    -1,    -1,    64,    65,
      -1,    -1,    68,    69,    38,    39,    40,    -1,    42,    43,
      44,    45,    -1,    -1,    -1,    -1,    50,    -1,    52,    17,
      18,    55,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    27,
      64,    65,    -1,    -1,    68,    69,    -1,    -1,    -1,    -1,
      38,    39,    40,    -1,    42,    43,    44,    45,    -1,    -1,
      -1,    17,    18,    -1,    52,    53,    -1,    55,    -1,    -1,
      -1,    27,    -1,    -1,    -1,    -1,    64,    65,    -1,    -1,
      68,    69,    38,    39,    40,    -1,    42,    43,    44,    45,
      -1,    -1,    -1,    17,    18,    -1,    52,    53,    -1,    55,
      -1,    -1,    -1,    27,    -1,    -1,    -1,    -1,    64,    65,
      -1,    -1,    68,    69,    38,    39,    40,    -1,    42,    43,
      44,    45,    -1,    -1,    -1,    17,    18,    -1,    52,    -1,
      -1,    55,    -1,    -1,    58,    27,    -1,    29,    -1,    -1,
      64,    65,    -1,    -1,    68,    69,    38,    39,    40,    -1,
      42,    43,    44,    45,    -1,    -1,    -1,    17,    18,    -1,
      52,    -1,    -1,    55,    -1,    -1,    -1,    27,    -1,    -1,
      -1,    -1,    64,    65,    -1,    -1,    68,    69,    38,    39,
      40,    -1,    42,    43,    44,    45,    -1,    -1,    -1,    17,
      18,    -1,    52,    53,    -1,    55,    -1,    -1,    -1,    27,
      -1,    -1,    -1,    -1,    64,    65,    -1,    -1,    68,    69,
      38,    39,    40,    -1,    42,    43,    44,    45,    -1,    -1,
      -1,    49,    17,    18,    52,    -1,    -1,    55,    -1,    -1,
      -1,    -1,    27,    -1,    -1,    -1,    64,    65,    -1,    -1,
      68,    69,    -1,    38,    39,    40,    -1,    42,    43,    44,
      45,    -1,    -1,    -1,    17,    18,    -1,    52,    53,    -1,
      55,    -1,    -1,    -1,    27,    -1,    -1,    -1,    -1,    64,
      65,    -1,    -1,    68,    69,    38,    39,    40,    -1,    42,
      43,    44,    45,    -1,    -1,    -1,    -1,    18,    -1,    52,
      -1,    -1,    55,    -1,    -1,    -1,    27,    -1,    -1,    -1,
      -1,    64,    65,    -1,    -1,    68,    69,    38,    39,    40,
      -1,    42,    43,    44,    45,    -1,    -1,    -1,    -1,    18,
      -1,    52,    -1,    -1,    55,    -1,    -1,    -1,    27,    -1,
      -1,    -1,    -1,    64,    65,    -1,    -1,    68,    69,    38,
      39,    40,    -1,    42,    43,    44,    45,    -1,    -1,    -1,
      -1,    -1,    -1,    52,    -1,    -1,    55,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    64,    65,    -1,    -1,    68,
      69
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
      26,    27,    38,    39,    40,    42,    43,    44,    45,    49,
      52,    55,    72,    73,    74,    75,    76,    77,    79,    82,
      84,    85,    86,    87,    88,    90,    91,    92,    93,    94,
      95,    98,   102,   103,   105,   121,   122,   123,   124,   125,
     129,   132,    44,    46,    46,    46,    40,    42,    43,    44,
      46,    51,    55,    79,    80,    94,   103,   105,    44,   105,
      52,    52,    15,    44,    78,    78,    52,    17,    18,    27,
      38,    39,    44,    46,    64,    65,    68,    69,   103,   107,
     108,   109,   110,   111,   112,   113,   114,   115,   116,   117,
     118,   119,   120,   121,   124,   125,   128,   129,   130,   135,
      52,    52,    52,    52,    44,   124,   125,    44,   125,    36,
      37,    38,    39,    47,    51,    52,    56,    57,    59,    96,
     126,    50,    80,    81,    83,   135,    40,    41,    44,    49,
     105,   133,     0,    72,    75,    44,    55,    94,    98,    75,
      75,    46,    75,    44,    57,   126,    36,    37,    96,    36,
      37,    38,    39,    46,    96,    46,    46,    51,    51,    51,
      51,    44,    26,    44,    46,    57,    96,    44,    11,    44,
      46,    89,   105,   135,    78,    46,    47,    46,    47,   135,
      44,    52,    44,   117,   125,   117,    57,   116,   116,   117,
     117,    31,    70,    30,    61,    62,    63,    32,    33,    34,
      35,    59,    60,    64,    65,    48,    66,    67,    38,    39,
      46,   135,   135,   135,   105,    46,    46,    46,    46,   135,
     135,    46,    46,    44,    49,   103,   127,    86,    29,    53,
     104,   131,   135,    49,   135,    58,   135,   105,   106,    83,
      50,    53,   135,   105,    52,   105,    46,    52,    96,   135,
     135,   135,   135,   135,    46,    46,    52,    46,    46,    96,
      44,   105,    51,    96,   135,    46,   135,    44,    53,    46,
      47,    44,    48,    53,    52,    57,    59,   105,   108,   135,
     109,   110,   111,   112,   113,   113,   114,   114,   114,   114,
     115,   115,   116,   116,   116,    53,    54,    53,    54,    46,
      46,   135,   126,    52,    53,    54,    54,    50,    97,   135,
      58,    54,    60,    50,    44,   133,   105,    44,   100,   101,
     105,    46,    58,    46,    46,    46,    46,   135,    46,    96,
      44,   135,    46,    46,    53,   135,    46,    51,    96,    80,
      48,    46,    82,    53,    97,    58,   135,   106,    54,    51,
      82,   135,    80,   107,    50,   100,   104,   104,    50,    54,
     106,    46,    52,    54,   134,    44,    54,    53,    28,    44,
      53,    46,    96,    53,   135,    80,    53,    53,   135,   135,
      46,    10,    46,    53,    52,    58,    60,   107,   135,    53,
      53,    53,    97,   100,   133,    53,   101,    46,    82,    99,
      44,    54,    46,    80,    53,    80,    80,    53,    53,    80,
     135,    52,    53,    82,   126,    82,   135,    53,    56,   101,
      80,    80,    80,    53,    53,    97,    99,   133,    53,   134
    };
  }

/* YYR1[RULE-NUM] -- Symbol kind of the left-hand side of rule RULE-NUM.  */
  private static final short[] yyr1_ = yyr1_init();
  private static final short[] yyr1_init()
  {
    return new short[]
    {
       0,    71,    72,    72,    73,    73,    73,    73,    74,    74,
      74,    74,    74,    75,    75,    75,    75,    75,    76,    76,
      76,    77,    78,    78,    79,    79,    79,    79,    79,    79,
      79,    79,    79,    79,    79,    79,    79,    79,    79,    79,
      79,    79,    79,    79,    79,    79,    80,    80,    81,    82,
      82,    83,    83,    84,    85,    85,    85,    85,    85,    85,
      85,    86,    86,    87,    87,    88,    88,    88,    88,    88,
      88,    88,    89,    89,    89,    89,    90,    91,    92,    93,
      93,    93,    93,    93,    94,    94,    94,    94,    94,    94,
      95,    96,    96,    96,    97,    97,    98,    98,    99,    99,
     100,   100,   101,   101,   101,   101,   101,   102,   102,   102,
     102,   102,   102,   103,   103,   104,   104,   104,   104,   105,
     105,   105,   106,   106,   107,   107,   108,   108,   109,   109,
     110,   110,   111,   111,   112,   112,   112,   113,   113,   113,
     113,   113,   114,   114,   114,   115,   115,   115,   115,   116,
     116,   117,   117,   117,   117,   117,   118,   118,   118,   119,
     119,   119,   120,   120,   120,   120,   121,   121,   122,   122,
     122,   122,   123,   123,   123,   123,   124,   124,   125,   125,
     125,   126,   126,   127,   127,   127,   128,   128,   129,   129,
     129,   130,   130,   130,   130,   130,   130,   130,   131,   131,
     132,   133,   133,   133,   133,   134,   134,   135,   135,   135
    };
  }

/* YYR2[RULE-NUM] -- Number of symbols on the right-hand side of rule RULE-NUM.  */
  private static final byte[] yyr2_ = yyr2_init();
  private static final byte[] yyr2_init()
  {
    return new byte[]
    {
       0,     2,     2,     1,     3,     5,     4,     6,     2,     1,
       2,     1,     1,     2,     2,     2,     2,     0,     1,     1,
       1,     3,     1,     3,     1,     2,     2,     3,     3,     3,
       3,     3,     3,     3,     3,     2,     1,     1,     1,     1,
       1,     1,     1,     1,     1,     1,     1,     1,     1,     3,
       2,     1,     2,     3,     2,     3,     2,     3,     2,     2,
       2,     5,     7,     5,     6,     8,     7,     7,     6,     8,
       7,     8,     3,     4,     4,     5,     5,     7,     5,     3,
       3,     3,     3,     2,     4,     4,     5,     3,     3,     4,
       5,     2,     3,     4,     1,     3,     8,     6,     1,     1,
       1,     0,     1,     2,     3,     3,     4,     4,     4,     4,
       4,     4,     4,     3,     4,     1,     1,     3,     3,     3,
       1,     4,     1,     3,     3,     1,     3,     1,     1,     3,
       1,     3,     1,     3,     1,     3,     3,     1,     3,     3,
       3,     3,     1,     3,     3,     1,     3,     3,     3,     1,
       6,     2,     2,     2,     2,     1,     1,     2,     2,     1,
       2,     2,     1,     1,     1,     1,     3,     1,     1,     1,
       1,     1,     1,     1,     1,     1,     4,     4,     2,     7,
       2,     3,     2,     1,     3,     1,     5,     1,     2,     2,
       2,     2,     4,     5,     5,     7,     7,     8,     5,     5,
       6,     3,     1,     1,     1,     5,     0,     1,     1,     1
    };
  }



  /* YYRLINE[YYN] -- Source line where rule number YYN was defined.  */
  private static final short[] yyrline_ = yyrline_init();
  private static final short[] yyrline_init()
  {
    return new short[]
    {
       0,   147,   147,   148,   152,   153,   157,   158,   164,   165,
     166,   167,   168,   172,   173,   174,   176,   177,   181,   182,
     183,   187,   195,   196,   200,   201,   202,   203,   207,   211,
     215,   219,   222,   225,   228,   231,   232,   233,   234,   235,
     236,   237,   238,   239,   240,   241,   245,   246,   250,   256,
     257,   261,   262,   266,   270,   271,   272,   273,   274,   275,
     276,   280,   281,   287,   288,   294,   296,   298,   300,   302,
     304,   308,   317,   322,   327,   332,   340,   344,   350,   357,
     364,   371,   378,   385,   394,   399,   404,   409,   412,   415,
     421,   428,   429,   432,   438,   439,   444,   447,   453,   454,
     458,   459,   463,   464,   465,   471,   475,   483,   487,   491,
     494,   497,   500,   509,   512,   518,   519,   520,   521,   525,
     529,   530,   534,   535,   539,   542,   546,   549,   553,   554,
     560,   561,   567,   568,   574,   575,   578,   584,   585,   588,
     591,   594,   600,   601,   604,   610,   611,   614,   617,   623,
     624,   628,   631,   634,   635,   636,   640,   641,   642,   646,
     647,   650,   656,   657,   658,   659,   663,   664,   668,   669,
     670,   671,   675,   676,   677,   678,   682,   686,   690,   695,
     700,   707,   708,   716,   717,   718,   722,   723,   729,   730,
     731,   739,   740,   743,   746,   751,   756,   760,   767,   770,
     777,   783,   786,   787,   788,   792,   795,   799,   800,   801
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
    int code_max = 300;
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
       2,     2,     2,    68,     2,    55,     2,    67,    63,     2,
      52,    53,    48,    64,    54,    65,    47,    66,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,    51,    46,
      59,    56,    60,    70,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,    57,     2,    58,    62,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,    49,    61,    50,    69,     2,     2,     2,
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
      35,    36,    37,    38,    39,    40,    41,    42,    43,    44,
      45
    };
  }


  private static final int YYLAST_ = 1450;
  private static final int YYEMPTY_ = -2;
  private static final int YYFINAL_ = 152;
  private static final int YYNTOKENS_ = 71;

/* Unqualified %code blocks.  */
/* "VondaGrammar.y":61  */

  private List<RudiTree> _statements = new LinkedList<>();

  //private GrammarFile _result = new GrammarFile(_statements);

  public List<RudiTree> getResult() { return _statements; }

  public static <T extends RudiTree> T setPos(T rt, Location l) {
    return setPos(rt, l, l) ;
  }

  public static <T extends RudiTree> T setPos(T rt, Location start, Location end) {
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

/* "VondaGrammar.java":3782  */

}
