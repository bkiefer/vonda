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
    S_declarations(73),            /* declarations  */
    S_grammar_file(74),            /* grammar_file  */
    S_visibility_spec(75),         /* visibility_spec  */
    S_includes(76),                /* includes  */
    S_path(77),                    /* path  */
    S_statement_no_def(78),        /* statement_no_def  */
    S_statement(79),               /* statement  */
    S_blk_statement(80),           /* blk_statement  */
    S_block(81),                   /* block  */
    S_statements(82),              /* statements  */
    S_grammar_rule(83),            /* grammar_rule  */
    S_return_statement(84),        /* return_statement  */
    S_if_statement(85),            /* if_statement  */
    S_while_statement(86),         /* while_statement  */
    S_for_statement(87),           /* for_statement  */
    S_var_decl(88),                /* var_decl  */
    S_propose_statement(89),       /* propose_statement  */
    S_timeout_statement(90),       /* timeout_statement  */
    S_switch_statement(91),        /* switch_statement  */
    S_label_statement(92),         /* label_statement  */
    S_var_def(93),                 /* var_def  */
    S_field_def(94),               /* field_def  */
    S_assgn_exp(95),               /* assgn_exp  */
    S_nonempty_exp_list(96),       /* nonempty_exp_list  */
    S_method_declaration(97),      /* method_declaration  */
    S_opt_block(98),               /* opt_block  */
    S_opt_args_list(99),           /* opt_args_list  */
    S_args_list(100),              /* args_list  */
    S_set_operation(101),          /* set_operation  */
    S_function_call(102),          /* function_call  */
    S_nonempty_args_list(103),     /* nonempty_args_list  */
    S_type_spec(104),              /* type_spec  */
    S_type_spec_list(105),         /* type_spec_list  */
    S_ConditionalOrExpression(106), /* ConditionalOrExpression  */
    S_ConditionalAndExpression(107), /* ConditionalAndExpression  */
    S_InclusiveOrExpression(108),  /* InclusiveOrExpression  */
    S_ExclusiveOrExpression(109),  /* ExclusiveOrExpression  */
    S_AndExpression(110),          /* AndExpression  */
    S_EqualityExpression(111),     /* EqualityExpression  */
    S_RelationalExpression(112),   /* RelationalExpression  */
    S_AdditiveExpression(113),     /* AdditiveExpression  */
    S_MultiplicativeExpression(114), /* MultiplicativeExpression  */
    S_CastExpression(115),         /* CastExpression  */
    S_UnaryExpression(116),        /* UnaryExpression  */
    S_LogicalUnaryExpression(117), /* LogicalUnaryExpression  */
    S_PostfixExpression(118),      /* PostfixExpression  */
    S_PrimaryExpression(119),      /* PrimaryExpression  */
    S_ComplexPrimary(120),         /* ComplexPrimary  */
    S_ComplexPrimaryNoParenthesis(121), /* ComplexPrimaryNoParenthesis  */
    S_Literal(122),                /* Literal  */
    S_ArrayAccess(123),            /* ArrayAccess  */
    S_field_access(124),           /* field_access  */
    S_field_access_rest(125),      /* field_access_rest  */
    S_simple_nofa_exp(126),        /* simple_nofa_exp  */
    S_ConditionalExpression(127),  /* ConditionalExpression  */
    S_assignment(128),             /* assignment  */
    S_new_exp(129),                /* new_exp  */
    S_lambda_exp(130),             /* lambda_exp  */
    S_dialogueact_exp(131),        /* dialogueact_exp  */
    S_da_token(132),               /* da_token  */
    S_da_args(133),                /* da_args  */
    S_exp(134);                    /* exp  */


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
  "ISA", "LAMBDA", "ANDAND", "OROR", "EQEQ", "NOTEQ", "GTEQ", "LTEQ",
  "MINUSEQ", "PLUSEQ", "MINUSMINUS", "PLUSPLUS", "STRING", "WILDCARD",
  "INT", "BOOL_LITERAL", "IDENTIFIER", "OTHER_LITERAL", "';'", "'.'",
  "'*'", "'{'", "'}'", "':'", "'('", "')'", "','", "'#'", "'='", "'['",
  "']'", "'<'", "'>'", "'|'", "'^'", "'&'", "'+'", "'-'", "'/'", "'%'",
  "'!'", "'~'", "'?'", "$accept", "root", "imports", "declarations",
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
    /* "VondaGrammar.y":145  */
                 { yyval = ((LinkedList<RudiTree>)(yystack.valueAt (0))); ((LinkedList<RudiTree>)(yystack.valueAt (0))).addFirst(((Import)(yystack.valueAt (1)))); };
  break;


  case 3: /* root: grammar_file  */
  if (yyn == 3)
    /* "VondaGrammar.y":146  */
                 { yyval = ((LinkedList<RudiTree>)(yystack.valueAt (0)));};
  break;


  case 4: /* imports: IMPORT path ';'  */
  if (yyn == 4)
    /* "VondaGrammar.y":150  */
                    { yyval = setPos(new Import(((List<String>)(yystack.valueAt (1)))), (yyloc)); };
  break;


  case 5: /* imports: IMPORT path '.' '*' ';'  */
  if (yyn == 5)
    /* "VondaGrammar.y":151  */
                            {
    ((List<String>)(yystack.valueAt (3))).add("*");
    yyval = setPos(new Import(((List<String>)(yystack.valueAt (3)))), (yyloc));
  };
  break;


  case 6: /* imports: IMPORT STATIC path ';'  */
  if (yyn == 6)
    /* "VondaGrammar.y":155  */
                           { yyval = setPos(new Import(((List<String>)(yystack.valueAt (1))), true), (yyloc)); };
  break;


  case 7: /* imports: IMPORT STATIC path '.' '*' ';'  */
  if (yyn == 7)
    /* "VondaGrammar.y":156  */
                                   {
    ((List<String>)(yystack.valueAt (3))).add("*");
    yyval = setPos(new Import(((List<String>)(yystack.valueAt (3))), true), (yyloc));
  };
  break;


  case 8: /* declarations: visibility_spec method_declaration  */
  if (yyn == 8)
    /* "VondaGrammar.y":162  */
                                       { yyval = ((StatMethodDeclaration)(yystack.valueAt (0))); ((StatMethodDeclaration)(yystack.valueAt (0))).setVisibility(((String)(yystack.valueAt (1)))); };
  break;


  case 9: /* declarations: method_declaration  */
  if (yyn == 9)
    /* "VondaGrammar.y":163  */
                       { yyval = ((StatMethodDeclaration)(yystack.valueAt (0))); };
  break;


  case 10: /* declarations: visibility_spec var_def  */
  if (yyn == 10)
    /* "VondaGrammar.y":164  */
                             { yyval = setPos(new StatFieldDef(((String)(yystack.valueAt (1))), ((StatVarDef)(yystack.valueAt (0)))), yystack.locationAt (1), yystack.locationAt (0)); };
  break;


  case 11: /* declarations: var_def  */
  if (yyn == 11)
    /* "VondaGrammar.y":165  */
             { yyval = setPos(new StatFieldDef(null, ((StatVarDef)(yystack.valueAt (0)))), yystack.locationAt (0)); };
  break;


  case 12: /* declarations: field_def  */
  if (yyn == 12)
    /* "VondaGrammar.y":166  */
              { yyval = ((StatFieldDef)(yystack.valueAt (0))); };
  break;


  case 13: /* grammar_file: declarations grammar_file  */
  if (yyn == 13)
    /* "VondaGrammar.y":170  */
                              { yyval = ((LinkedList<RudiTree>)(yystack.valueAt (0))); ((LinkedList<RudiTree>)(yystack.valueAt (0))).addFirst(((RTStatement)(yystack.valueAt (1)))); };
  break;


  case 14: /* grammar_file: function_call grammar_file  */
  if (yyn == 14)
    /* "VondaGrammar.y":171  */
                               { yyval = ((LinkedList<RudiTree>)(yystack.valueAt (0))); ((LinkedList<RudiTree>)(yystack.valueAt (0))).addFirst(((RTExpression)(yystack.valueAt (1)))); };
  break;


  case 15: /* grammar_file: statement_no_def grammar_file  */
  if (yyn == 15)
    /* "VondaGrammar.y":172  */
                                  { yyval = ((LinkedList<RudiTree>)(yystack.valueAt (0))); ((LinkedList<RudiTree>)(yystack.valueAt (0))).addFirst(((RTStatement)(yystack.valueAt (1)))); };
  break;


  case 16: /* grammar_file: includes grammar_file  */
  if (yyn == 16)
    /* "VondaGrammar.y":174  */
                          { yyval = ((LinkedList<RudiTree>)(yystack.valueAt (0))); ((LinkedList<RudiTree>)(yystack.valueAt (0))).addFirst(((Include)(yystack.valueAt (1)))); };
  break;


  case 17: /* grammar_file: %empty  */
  if (yyn == 17)
    /* "VondaGrammar.y":175  */
           { yyval = _statements; };
  break;


  case 18: /* visibility_spec: PUBLIC  */
  if (yyn == 18)
    /* "VondaGrammar.y":179  */
           { yyval = "public"; };
  break;


  case 19: /* visibility_spec: PROTECTED  */
  if (yyn == 19)
    /* "VondaGrammar.y":180  */
              { yyval = "protected"; };
  break;


  case 20: /* visibility_spec: PRIVATE  */
  if (yyn == 20)
    /* "VondaGrammar.y":181  */
             { yyval = "private"; };
  break;


  case 21: /* includes: INCLUDE path ';'  */
  if (yyn == 21)
    /* "VondaGrammar.y":185  */
                     {
    List<String> path = ((List<String>)(yystack.valueAt (1)));
    String name = path.remove(path.size() - 1);
    yyval = setPos(new Include(name, path.toArray(new String[path.size()])), (yyloc));
  };
  break;


  case 22: /* path: IDENTIFIER  */
  if (yyn == 22)
    /* "VondaGrammar.y":193  */
               { yyval = new ArrayList<String>(){{ add((( String )(yystack.valueAt (0)))); }}; };
  break;


  case 23: /* path: path '.' IDENTIFIER  */
  if (yyn == 23)
    /* "VondaGrammar.y":194  */
                        { yyval = ((List<String>)(yystack.valueAt (2))); ((List<String>)(yystack.valueAt (2))).add((( String )(yystack.valueAt (0)))); };
  break;


  case 24: /* statement_no_def: block  */
  if (yyn == 24)
    /* "VondaGrammar.y":198  */
          { yyval = ((StatAbstractBlock)(yystack.valueAt (0))); };
  break;


  case 25: /* statement_no_def: assignment ';'  */
  if (yyn == 25)
    /* "VondaGrammar.y":199  */
                   { yyval = setPos(getAssignmentStat(((RTExpression)(yystack.valueAt (1)))), (yyloc)); };
  break;


  case 26: /* statement_no_def: field_access ';'  */
  if (yyn == 26)
    /* "VondaGrammar.y":200  */
                     { yyval = setPos(new StatExpression(((RTExpression)(yystack.valueAt (1)))), (yyloc)); };
  break;


  case 27: /* statement_no_def: PLUSPLUS IDENTIFIER ';'  */
  if (yyn == 27)
    /* "VondaGrammar.y":201  */
                            {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (1)))), yystack.locationAt (1));
    yyval = setPos(new StatExpression(createPlusMinus(var, "++", (yyloc))), (yyloc));
  };
  break;


  case 28: /* statement_no_def: MINUSMINUS IDENTIFIER ';'  */
  if (yyn == 28)
    /* "VondaGrammar.y":205  */
                              {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (1)))), yystack.locationAt (1));
    yyval = setPos(new StatExpression(createPlusMinus(var, "--", (yyloc))), (yyloc));
  };
  break;


  case 29: /* statement_no_def: IDENTIFIER PLUSPLUS ';'  */
  if (yyn == 29)
    /* "VondaGrammar.y":209  */
                            {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (2)))), yystack.locationAt (2));
    yyval = setPos(new StatExpression(createPlusMinus(var, "+++", (yyloc))), (yyloc));
  };
  break;


  case 30: /* statement_no_def: IDENTIFIER MINUSMINUS ';'  */
  if (yyn == 30)
    /* "VondaGrammar.y":213  */
                              {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (2)))), yystack.locationAt (2));
    yyval = setPos(new StatExpression(createPlusMinus(var, "---", (yyloc))), (yyloc));
  };
  break;


  case 31: /* statement_no_def: PLUSPLUS field_access ';'  */
  if (yyn == 31)
    /* "VondaGrammar.y":217  */
                              {
    yyval = setPos(new StatExpression(createPlusMinus(((RTExpression)(yystack.valueAt (1))), "++", (yyloc))), (yyloc));
  };
  break;


  case 32: /* statement_no_def: MINUSMINUS field_access ';'  */
  if (yyn == 32)
    /* "VondaGrammar.y":220  */
                                {
    yyval = setPos(new StatExpression(createPlusMinus(((RTExpression)(yystack.valueAt (1))), "--", (yyloc))), (yyloc));
  };
  break;


  case 33: /* statement_no_def: field_access PLUSPLUS ';'  */
  if (yyn == 33)
    /* "VondaGrammar.y":223  */
                              {
    yyval = setPos(new StatExpression(createPlusMinus(((RTExpression)(yystack.valueAt (2))), "+++", (yyloc))), (yyloc));
  };
  break;


  case 34: /* statement_no_def: field_access MINUSMINUS ';'  */
  if (yyn == 34)
    /* "VondaGrammar.y":226  */
                                {
    yyval = setPos(new StatExpression(createPlusMinus(((RTExpression)(yystack.valueAt (2))), "---", (yyloc))), (yyloc));
  };
  break;


  case 35: /* statement_no_def: function_call ';'  */
  if (yyn == 35)
    /* "VondaGrammar.y":229  */
                      { yyval = setPos(new StatExpression(((RTExpression)(yystack.valueAt (1)))), (yyloc)); };
  break;


  case 36: /* statement_no_def: grammar_rule  */
  if (yyn == 36)
    /* "VondaGrammar.y":230  */
                 { yyval = ((StatGrammarRule)(yystack.valueAt (0))); };
  break;


  case 37: /* statement_no_def: set_operation  */
  if (yyn == 37)
    /* "VondaGrammar.y":231  */
                  { yyval = ((RTStatement)(yystack.valueAt (0))); };
  break;


  case 38: /* statement_no_def: return_statement  */
  if (yyn == 38)
    /* "VondaGrammar.y":232  */
                     { yyval = ((RTStatement)(yystack.valueAt (0))); };
  break;


  case 39: /* statement_no_def: propose_statement  */
  if (yyn == 39)
    /* "VondaGrammar.y":233  */
                      { yyval = ((RTStatement)(yystack.valueAt (0))); };
  break;


  case 40: /* statement_no_def: timeout_statement  */
  if (yyn == 40)
    /* "VondaGrammar.y":234  */
                      { yyval = ((RTStatement)(yystack.valueAt (0))); };
  break;


  case 41: /* statement_no_def: if_statement  */
  if (yyn == 41)
    /* "VondaGrammar.y":235  */
                 { yyval = ((StatIf)(yystack.valueAt (0))); };
  break;


  case 42: /* statement_no_def: while_statement  */
  if (yyn == 42)
    /* "VondaGrammar.y":236  */
                    { yyval = ((RTStatement)(yystack.valueAt (0))); };
  break;


  case 43: /* statement_no_def: for_statement  */
  if (yyn == 43)
    /* "VondaGrammar.y":237  */
                  { yyval = ((RTStatement)(yystack.valueAt (0))); };
  break;


  case 44: /* statement_no_def: switch_statement  */
  if (yyn == 44)
    /* "VondaGrammar.y":238  */
                     { yyval = ((RTStatement)(yystack.valueAt (0))); };
  break;


  case 45: /* statement_no_def: label_statement  */
  if (yyn == 45)
    /* "VondaGrammar.y":239  */
                    { yyval = ((RTStatement)(yystack.valueAt (0))); };
  break;


  case 46: /* statement: statement_no_def  */
  if (yyn == 46)
    /* "VondaGrammar.y":243  */
                     { yyval = ((RTStatement)(yystack.valueAt (0))); };
  break;


  case 47: /* statement: var_def  */
  if (yyn == 47)
    /* "VondaGrammar.y":244  */
            { yyval = ((StatVarDef)(yystack.valueAt (0))); };
  break;


  case 48: /* blk_statement: statement  */
  if (yyn == 48)
    /* "VondaGrammar.y":248  */
              { yyval = ((RTStatement)(yystack.valueAt (0))); };
  break;


  case 49: /* block: '{' statements '}'  */
  if (yyn == 49)
    /* "VondaGrammar.y":254  */
                       { yyval = setPos(new StatAbstractBlock(((LinkedList<RTStatement>)(yystack.valueAt (1))), true), (yyloc)); };
  break;


  case 50: /* block: '{' '}'  */
  if (yyn == 50)
    /* "VondaGrammar.y":255  */
            {
    yyval = setPos(new StatAbstractBlock(new ArrayList<RTStatement>(), true), (yyloc));
  };
  break;


  case 51: /* statements: blk_statement  */
  if (yyn == 51)
    /* "VondaGrammar.y":259  */
                          { yyval = new LinkedList<RTStatement>(){{ add(((RTStatement)(yystack.valueAt (0)))); }}; };
  break;


  case 52: /* statements: blk_statement statements  */
  if (yyn == 52)
    /* "VondaGrammar.y":260  */
                             { yyval = ((LinkedList<RTStatement>)(yystack.valueAt (0))); ((LinkedList<RTStatement>)(yystack.valueAt (0))).addFirst(((RTStatement)(yystack.valueAt (1)))); };
  break;


  case 53: /* grammar_rule: IDENTIFIER ':' if_statement  */
  if (yyn == 53)
    /* "VondaGrammar.y":264  */
                                { yyval = setPos(new StatGrammarRule((( String )(yystack.valueAt (2))), ((StatIf)(yystack.valueAt (0)))), (yyloc)); };
  break;


  case 54: /* return_statement: RETURN ';'  */
  if (yyn == 54)
    /* "VondaGrammar.y":268  */
               { yyval = setPos(new StatReturn("return"), (yyloc)); };
  break;


  case 55: /* return_statement: RETURN exp ';'  */
  if (yyn == 55)
    /* "VondaGrammar.y":269  */
                   { yyval = setPos(new StatReturn(((RTExpression)(yystack.valueAt (1)))), (yyloc)); };
  break;


  case 56: /* return_statement: BREAK ';'  */
  if (yyn == 56)
    /* "VondaGrammar.y":270  */
              { yyval = setPos(new StatReturn("break"), (yyloc)); };
  break;


  case 57: /* return_statement: BREAK IDENTIFIER ';'  */
  if (yyn == 57)
    /* "VondaGrammar.y":271  */
                         { yyval = setPos(new StatReturn("break", (( String )(yystack.valueAt (1)))), (yyloc)); };
  break;


  case 58: /* return_statement: CANCEL ';'  */
  if (yyn == 58)
    /* "VondaGrammar.y":272  */
               { yyval = setPos(new StatReturn("cancel"), (yyloc)); };
  break;


  case 59: /* return_statement: CANCEL_ALL ';'  */
  if (yyn == 59)
    /* "VondaGrammar.y":273  */
                   { yyval = setPos(new StatReturn("cancel_all"), (yyloc)); };
  break;


  case 60: /* return_statement: CONTINUE ';'  */
  if (yyn == 60)
    /* "VondaGrammar.y":274  */
                 { yyval = setPos(new StatReturn("continue"), (yyloc)); };
  break;


  case 61: /* if_statement: IF '(' exp ')' statement  */
  if (yyn == 61)
    /* "VondaGrammar.y":278  */
                             { yyval = setPos(new StatIf(((RTExpression)(yystack.valueAt (2))), ((RTStatement)(yystack.valueAt (0))), null), (yyloc)); };
  break;


  case 62: /* if_statement: IF '(' exp ')' statement ELSE statement  */
  if (yyn == 62)
    /* "VondaGrammar.y":279  */
                                            {
    yyval = setPos(new StatIf(((RTExpression)(yystack.valueAt (4))), ((RTStatement)(yystack.valueAt (2))), ((RTStatement)(yystack.valueAt (0)))), (yyloc));
  };
  break;


  case 63: /* while_statement: WHILE '(' exp ')' statement  */
  if (yyn == 63)
    /* "VondaGrammar.y":285  */
                                { yyval = setPos(new StatWhile(((RTExpression)(yystack.valueAt (2))), ((RTStatement)(yystack.valueAt (0))), true), (yyloc)); };
  break;


  case 64: /* while_statement: DO statement WHILE '(' exp ')'  */
  if (yyn == 64)
    /* "VondaGrammar.y":286  */
                                   {
    yyval = setPos(new StatWhile(((RTExpression)(yystack.valueAt (1))), ((RTStatement)(yystack.valueAt (4))), false), (yyloc));
  };
  break;


  case 65: /* for_statement: FOR '(' var_decl exp ';' exp ')' statement  */
  if (yyn == 65)
    /* "VondaGrammar.y":292  */
                                               {
    yyval = setPos(new StatFor1(((StatVarDef)(yystack.valueAt (5))), ((RTExpression)(yystack.valueAt (4))), ((RTExpression)(yystack.valueAt (2))), ((RTStatement)(yystack.valueAt (0)))), (yyloc)); };
  break;


  case 66: /* for_statement: FOR '(' var_decl ';' exp ')' statement  */
  if (yyn == 66)
    /* "VondaGrammar.y":294  */
                                               {
    yyval = setPos(new StatFor1(((StatVarDef)(yystack.valueAt (4))), null, ((RTExpression)(yystack.valueAt (2))), ((RTStatement)(yystack.valueAt (0)))), (yyloc)); };
  break;


  case 67: /* for_statement: FOR '(' var_decl exp ';' ')' statement  */
  if (yyn == 67)
    /* "VondaGrammar.y":296  */
                                               {
    yyval = setPos(new StatFor1(((StatVarDef)(yystack.valueAt (4))), ((RTExpression)(yystack.valueAt (3))), null, ((RTStatement)(yystack.valueAt (0)))), (yyloc)); };
  break;


  case 68: /* for_statement: FOR '(' var_decl ';' ')' statement  */
  if (yyn == 68)
    /* "VondaGrammar.y":298  */
                                               {
    yyval = setPos(new StatFor1(((StatVarDef)(yystack.valueAt (3))), null, null, ((RTStatement)(yystack.valueAt (0)))), (yyloc)); };
  break;


  case 69: /* for_statement: FOR '(' ';' exp ';' exp ')' statement  */
  if (yyn == 69)
    /* "VondaGrammar.y":300  */
                                              {
    yyval = setPos(new StatFor1(null, ((RTExpression)(yystack.valueAt (4))), ((RTExpression)(yystack.valueAt (2))), ((RTStatement)(yystack.valueAt (0)))), (yyloc)); };
  break;


  case 70: /* for_statement: FOR '(' IDENTIFIER ':' exp ')' statement  */
  if (yyn == 70)
    /* "VondaGrammar.y":302  */
                                             {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (4)))), yystack.locationAt (4));
    yyval = setPos(new StatFor2(var, ((RTExpression)(yystack.valueAt (2))), ((RTStatement)(yystack.valueAt (0)))), (yyloc));
  };
  break;


  case 71: /* for_statement: FOR '(' type_spec IDENTIFIER ':' exp ')' statement  */
  if (yyn == 71)
    /* "VondaGrammar.y":306  */
                                                       {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (4)))), yystack.locationAt (4));
    yyval = setPos(new StatFor2(((Type)(yystack.valueAt (5))), var, ((RTExpression)(yystack.valueAt (2))), ((RTStatement)(yystack.valueAt (0)))), (yyloc));
  };
  break;


  case 72: /* var_decl: IDENTIFIER assgn_exp ';'  */
  if (yyn == 72)
    /* "VondaGrammar.y":315  */
                             {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (2)))), yystack.locationAt (2));
    ExpAssignment ass = setPos(new ExpAssignment(var, ((RTExpression)(yystack.valueAt (1)))), yystack.locationAt (2), yystack.locationAt (1));
    yyval = setPos(new StatVarDef(false, Type.getNoType(), ass), (yyloc));
  };
  break;


  case 73: /* var_decl: type_spec IDENTIFIER assgn_exp ';'  */
  if (yyn == 73)
    /* "VondaGrammar.y":320  */
                                       {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (2)))), yystack.locationAt (2));
    ExpAssignment ass = setPos(new ExpAssignment(var, ((RTExpression)(yystack.valueAt (1)))), yystack.locationAt (2), yystack.locationAt (1));
    yyval = setPos(new StatVarDef(false, ((Type)(yystack.valueAt (3))), ass), (yyloc));
  };
  break;


  case 74: /* var_decl: FINAL IDENTIFIER assgn_exp ';'  */
  if (yyn == 74)
    /* "VondaGrammar.y":325  */
                                   {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (2)))), yystack.locationAt (2));
    ExpAssignment ass = setPos(new ExpAssignment(var, ((RTExpression)(yystack.valueAt (1)))), yystack.locationAt (2), yystack.locationAt (1));
    yyval = setPos(new StatVarDef(true, Type.getNoType(), ass), (yyloc));
  };
  break;


  case 75: /* var_decl: FINAL type_spec IDENTIFIER assgn_exp ';'  */
  if (yyn == 75)
    /* "VondaGrammar.y":330  */
                                             {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (2)))), yystack.locationAt (2));
    ExpAssignment ass = setPos(new ExpAssignment(var, ((RTExpression)(yystack.valueAt (1)))), yystack.locationAt (2), yystack.locationAt (1));
    yyval = setPos(new StatVarDef(true, ((Type)(yystack.valueAt (3))), ass), (yyloc));
  };
  break;


  case 76: /* propose_statement: PROPOSE '(' exp ')' block  */
  if (yyn == 76)
    /* "VondaGrammar.y":338  */
                              { yyval = setPos(new StatPropose(((RTExpression)(yystack.valueAt (2))), ((StatAbstractBlock)(yystack.valueAt (0)))), (yyloc)); };
  break;


  case 77: /* timeout_statement: TIMEOUT '(' exp ',' exp ')' block  */
  if (yyn == 77)
    /* "VondaGrammar.y":342  */
                                      {
    yyval = setPos(new StatTimeout(((RTExpression)(yystack.valueAt (4))), ((RTExpression)(yystack.valueAt (2))), ((StatAbstractBlock)(yystack.valueAt (0)))), (yyloc));
  };
  break;


  case 78: /* switch_statement: SWITCH '(' exp ')' block  */
  if (yyn == 78)
    /* "VondaGrammar.y":348  */
                             {
    yyval = setPos(new StatSwitch(((RTExpression)(yystack.valueAt (2))), ((StatAbstractBlock)(yystack.valueAt (0)))), (yyloc));
  };
  break;


  case 79: /* label_statement: CASE STRING ':'  */
  if (yyn == 79)
    /* "VondaGrammar.y":355  */
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
    /* "VondaGrammar.y":362  */
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
    /* "VondaGrammar.y":369  */
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
    /* "VondaGrammar.y":376  */
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
    /* "VondaGrammar.y":383  */
                      {
    ExpLiteral val = new ExpLiteral("default:", "label");
    RTStatement lbl = val.ensureStatement();
    setPos(val, (yyloc)); setPos(lbl, (yyloc));
    yyval = lbl;
  };
  break;


  case 84: /* var_def: FINAL IDENTIFIER assgn_exp ';'  */
  if (yyn == 84)
    /* "VondaGrammar.y":392  */
                                   {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (2)))), yystack.locationAt (2));
    ExpAssignment ass = setPos(new ExpAssignment(var, ((RTExpression)(yystack.valueAt (1)))), yystack.locationAt (2), yystack.locationAt (1));
    yyval = setPos(new StatVarDef(true, Type.getNoType(), ass), (yyloc));
  };
  break;


  case 85: /* var_def: type_spec IDENTIFIER assgn_exp ';'  */
  if (yyn == 85)
    /* "VondaGrammar.y":397  */
                                       {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (2)))), yystack.locationAt (2));
    ExpAssignment ass = setPos(new ExpAssignment(var, ((RTExpression)(yystack.valueAt (1)))), yystack.locationAt (2), yystack.locationAt (1));
    yyval = setPos(new StatVarDef(false, ((Type)(yystack.valueAt (3))), ass), (yyloc));
  };
  break;


  case 86: /* var_def: FINAL type_spec IDENTIFIER assgn_exp ';'  */
  if (yyn == 86)
    /* "VondaGrammar.y":402  */
                                             {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (2)))), yystack.locationAt (2));
    ExpAssignment ass = setPos(new ExpAssignment(var, ((RTExpression)(yystack.valueAt (1)))), yystack.locationAt (2), yystack.locationAt (1));
    yyval = setPos(new StatVarDef(true, ((Type)(yystack.valueAt (3))), ass), (yyloc));
    };
  break;


  case 87: /* var_def: FINAL IDENTIFIER ';'  */
  if (yyn == 87)
    /* "VondaGrammar.y":407  */
                         {
    yyval = setPos(new StatVarDef(true, Type.getNoType(), (( String )(yystack.valueAt (1)))), (yyloc));
  };
  break;


  case 88: /* var_def: type_spec IDENTIFIER ';'  */
  if (yyn == 88)
    /* "VondaGrammar.y":410  */
                             {
    yyval = setPos(new StatVarDef(false, ((Type)(yystack.valueAt (2))), (( String )(yystack.valueAt (1)))), (yyloc));
  };
  break;


  case 89: /* var_def: FINAL type_spec IDENTIFIER ';'  */
  if (yyn == 89)
    /* "VondaGrammar.y":413  */
                                   {
    yyval = setPos(new StatVarDef(true, ((Type)(yystack.valueAt (2))), (( String )(yystack.valueAt (1)))), (yyloc));
  };
  break;


  case 90: /* field_def: '#' type_spec type_spec IDENTIFIER ';'  */
  if (yyn == 90)
    /* "VondaGrammar.y":419  */
                                           {
    yyval = setPos(new StatFieldDef(null,
           setPos(new StatVarDef(false, ((Type)(yystack.valueAt (2))), (( String )(yystack.valueAt (1)))), (yyloc)), ((Type)(yystack.valueAt (3)))), (yyloc));
  };
  break;


  case 91: /* assgn_exp: '=' exp  */
  if (yyn == 91)
    /* "VondaGrammar.y":426  */
            { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 92: /* assgn_exp: '=' '{' '}'  */
  if (yyn == 92)
    /* "VondaGrammar.y":427  */
                {
    yyval = setPos(new ExpListLiteral(new LinkedList<RTExpression>()), yystack.locationAt (1), yystack.locationAt (0));
  };
  break;


  case 93: /* assgn_exp: '=' '{' nonempty_exp_list '}'  */
  if (yyn == 93)
    /* "VondaGrammar.y":430  */
                                  {
    yyval = setPos(new ExpListLiteral(((LinkedList<RTExpression>)(yystack.valueAt (1)))), yystack.locationAt (2), yystack.locationAt (0));
  };
  break;


  case 94: /* nonempty_exp_list: exp  */
  if (yyn == 94)
    /* "VondaGrammar.y":436  */
        { yyval = new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (0)))); }}; };
  break;


  case 95: /* nonempty_exp_list: exp ',' nonempty_exp_list  */
  if (yyn == 95)
    /* "VondaGrammar.y":437  */
                              { yyval = ((LinkedList<RTExpression>)(yystack.valueAt (0))); ((LinkedList<RTExpression>)(yystack.valueAt (0))).addFirst(((RTExpression)(yystack.valueAt (2)))); };
  break;


  case 96: /* method_declaration: '#' type_spec type_spec IDENTIFIER '(' opt_args_list ')' opt_block  */
  if (yyn == 96)
    /* "VondaGrammar.y":442  */
                                                                       {
    yyval = setPos(new StatMethodDeclaration("public", ((Type)(yystack.valueAt (5))), ((Type)(yystack.valueAt (6))), (( String )(yystack.valueAt (4))), ((LinkedList)(yystack.valueAt (2))), ((StatAbstractBlock)(yystack.valueAt (0)))), (yyloc));
  };
  break;


  case 97: /* method_declaration: type_spec IDENTIFIER '(' opt_args_list ')' opt_block  */
  if (yyn == 97)
    /* "VondaGrammar.y":445  */
                                                         {
    yyval = setPos(new StatMethodDeclaration("public", ((Type)(yystack.valueAt (5))), null, (( String )(yystack.valueAt (4))), ((LinkedList)(yystack.valueAt (2))), ((StatAbstractBlock)(yystack.valueAt (0)))), (yyloc));
  };
  break;


  case 98: /* opt_block: block  */
  if (yyn == 98)
    /* "VondaGrammar.y":451  */
          { yyval = ((StatAbstractBlock)(yystack.valueAt (0))); };
  break;


  case 99: /* opt_block: ';'  */
  if (yyn == 99)
    /* "VondaGrammar.y":452  */
        { yyval = null; };
  break;


  case 100: /* opt_args_list: args_list  */
  if (yyn == 100)
    /* "VondaGrammar.y":456  */
              { yyval = ((LinkedList)(yystack.valueAt (0))); };
  break;


  case 101: /* opt_args_list: %empty  */
  if (yyn == 101)
    /* "VondaGrammar.y":457  */
           { yyval = new LinkedList(); };
  break;


  case 102: /* args_list: IDENTIFIER  */
  if (yyn == 102)
    /* "VondaGrammar.y":461  */
               { yyval = new LinkedList(){{ add(Type.getNoType()); add((( String )(yystack.valueAt (0)))); }}; };
  break;


  case 103: /* args_list: type_spec IDENTIFIER  */
  if (yyn == 103)
    /* "VondaGrammar.y":462  */
                         { yyval = new LinkedList(){{ add(((Type)(yystack.valueAt (1)))); add((( String )(yystack.valueAt (0)))); }}; };
  break;


  case 104: /* args_list: IDENTIFIER ',' args_list  */
  if (yyn == 104)
    /* "VondaGrammar.y":463  */
                             {
    yyval = ((LinkedList)(yystack.valueAt (0)));
    ((LinkedList)(yystack.valueAt (0))).addFirst((( String )(yystack.valueAt (2)))); ((LinkedList)(yystack.valueAt (0))).addFirst(Type.getNoType());
  };
  break;


  case 105: /* args_list: type_spec IDENTIFIER ',' args_list  */
  if (yyn == 105)
    /* "VondaGrammar.y":467  */
                                       {
    yyval = ((LinkedList)(yystack.valueAt (0))); ((LinkedList)(yystack.valueAt (0))).addFirst((( String )(yystack.valueAt (2)))); ((LinkedList)(yystack.valueAt (0))).addFirst(((Type)(yystack.valueAt (3))));
  };
  break;


  case 106: /* set_operation: IDENTIFIER PLUSEQ exp ';'  */
  if (yyn == 106)
    /* "VondaGrammar.y":475  */
                              {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (3)))), yystack.locationAt (3));
    yyval = setPos(new StatSetOperation(var, true, ((RTExpression)(yystack.valueAt (1)))), (yyloc));
  };
  break;


  case 107: /* set_operation: IDENTIFIER MINUSEQ exp ';'  */
  if (yyn == 107)
    /* "VondaGrammar.y":479  */
                               {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (3)))), yystack.locationAt (3));
    yyval = setPos(new StatSetOperation(var, false, ((RTExpression)(yystack.valueAt (1)))), (yyloc));
  };
  break;


  case 108: /* set_operation: ArrayAccess PLUSEQ exp ';'  */
  if (yyn == 108)
    /* "VondaGrammar.y":483  */
                               {
    yyval = setPos(new StatSetOperation(((RTExpression)(yystack.valueAt (3))), true, ((RTExpression)(yystack.valueAt (1)))), (yyloc));
  };
  break;


  case 109: /* set_operation: ArrayAccess MINUSEQ exp ';'  */
  if (yyn == 109)
    /* "VondaGrammar.y":486  */
                                {
    yyval = setPos(new StatSetOperation(((RTExpression)(yystack.valueAt (3))), false, ((RTExpression)(yystack.valueAt (1)))), (yyloc));
  };
  break;


  case 110: /* set_operation: field_access PLUSEQ exp ';'  */
  if (yyn == 110)
    /* "VondaGrammar.y":489  */
                                {
    yyval = setPos(new StatSetOperation(((RTExpression)(yystack.valueAt (3))), true, ((RTExpression)(yystack.valueAt (1)))), (yyloc));
  };
  break;


  case 111: /* set_operation: field_access MINUSEQ exp ';'  */
  if (yyn == 111)
    /* "VondaGrammar.y":492  */
                                 {
    yyval = setPos(new StatSetOperation(((RTExpression)(yystack.valueAt (3))), false, ((RTExpression)(yystack.valueAt (1)))), (yyloc));
  };
  break;


  case 112: /* function_call: IDENTIFIER '(' ')'  */
  if (yyn == 112)
    /* "VondaGrammar.y":501  */
                       {
    yyval = setPos(new ExpFuncCall((( String )(yystack.valueAt (2))), new LinkedList<RTExpression>(), false), (yyloc));
  };
  break;


  case 113: /* function_call: IDENTIFIER '(' nonempty_args_list ')'  */
  if (yyn == 113)
    /* "VondaGrammar.y":504  */
                                           {
    yyval = setPos(new ExpFuncCall((( String )(yystack.valueAt (3))), ((LinkedList<RTExpression>)(yystack.valueAt (1))), false), (yyloc));
  };
  break;


  case 114: /* nonempty_args_list: exp  */
  if (yyn == 114)
    /* "VondaGrammar.y":510  */
        { yyval = new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (0)))); }}; };
  break;


  case 115: /* nonempty_args_list: lambda_exp  */
  if (yyn == 115)
    /* "VondaGrammar.y":511  */
               { yyval = new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (0)))); }}; };
  break;


  case 116: /* nonempty_args_list: exp ',' nonempty_args_list  */
  if (yyn == 116)
    /* "VondaGrammar.y":512  */
                               { yyval = ((LinkedList<RTExpression>)(yystack.valueAt (0))); ((LinkedList<RTExpression>)(yystack.valueAt (0))).addFirst(((RTExpression)(yystack.valueAt (2)))); };
  break;


  case 117: /* nonempty_args_list: lambda_exp ',' nonempty_args_list  */
  if (yyn == 117)
    /* "VondaGrammar.y":513  */
                                      { yyval = ((LinkedList<RTExpression>)(yystack.valueAt (0))); ((LinkedList<RTExpression>)(yystack.valueAt (0))).addFirst(((RTExpression)(yystack.valueAt (2)))); };
  break;


  case 118: /* type_spec: IDENTIFIER '[' ']'  */
  if (yyn == 118)
    /* "VondaGrammar.y":517  */
                       {
    yyval = new Type("Array",
                  new ArrayList<Type>(){{ add(new Type((( String )(yystack.valueAt (2))))); }});
  };
  break;


  case 119: /* type_spec: IDENTIFIER  */
  if (yyn == 119)
    /* "VondaGrammar.y":521  */
               { yyval = new Type((( String )(yystack.valueAt (0)))); };
  break;


  case 120: /* type_spec: IDENTIFIER '<' type_spec_list '>'  */
  if (yyn == 120)
    /* "VondaGrammar.y":522  */
                                      { yyval = new Type((( String )(yystack.valueAt (3))), ((LinkedList<Type>)(yystack.valueAt (1)))); };
  break;


  case 121: /* type_spec_list: type_spec  */
  if (yyn == 121)
    /* "VondaGrammar.y":526  */
              { yyval = new LinkedList<Type>(){{ add(((Type)(yystack.valueAt (0)))); }}; };
  break;


  case 122: /* type_spec_list: type_spec ',' type_spec_list  */
  if (yyn == 122)
    /* "VondaGrammar.y":527  */
                                 { yyval = ((LinkedList<Type>)(yystack.valueAt (0))); ((LinkedList<Type>)(yystack.valueAt (0))).addFirst(((Type)(yystack.valueAt (2)))); };
  break;


  case 123: /* ConditionalOrExpression: ConditionalOrExpression OROR ConditionalAndExpression  */
  if (yyn == 123)
    /* "VondaGrammar.y":531  */
                                                          {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "||"), (yyloc));
  };
  break;


  case 124: /* ConditionalOrExpression: ConditionalAndExpression  */
  if (yyn == 124)
    /* "VondaGrammar.y":534  */
                             { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 125: /* ConditionalAndExpression: ConditionalAndExpression ANDAND InclusiveOrExpression  */
  if (yyn == 125)
    /* "VondaGrammar.y":538  */
                                                          {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "&&"), (yyloc));
  };
  break;


  case 126: /* ConditionalAndExpression: InclusiveOrExpression  */
  if (yyn == 126)
    /* "VondaGrammar.y":541  */
                          { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 127: /* InclusiveOrExpression: ExclusiveOrExpression  */
  if (yyn == 127)
    /* "VondaGrammar.y":545  */
                          { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 128: /* InclusiveOrExpression: InclusiveOrExpression '|' ExclusiveOrExpression  */
  if (yyn == 128)
    /* "VondaGrammar.y":546  */
                                                    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "|"), (yyloc));
  };
  break;


  case 129: /* ExclusiveOrExpression: AndExpression  */
  if (yyn == 129)
    /* "VondaGrammar.y":552  */
                  { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 130: /* ExclusiveOrExpression: ExclusiveOrExpression '^' AndExpression  */
  if (yyn == 130)
    /* "VondaGrammar.y":553  */
                                            {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "^"), (yyloc));
  };
  break;


  case 131: /* AndExpression: EqualityExpression  */
  if (yyn == 131)
    /* "VondaGrammar.y":559  */
                       { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 132: /* AndExpression: AndExpression '&' EqualityExpression  */
  if (yyn == 132)
    /* "VondaGrammar.y":560  */
                                         {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "&"), (yyloc));
  };
  break;


  case 133: /* EqualityExpression: RelationalExpression  */
  if (yyn == 133)
    /* "VondaGrammar.y":566  */
                         { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 134: /* EqualityExpression: EqualityExpression EQEQ RelationalExpression  */
  if (yyn == 134)
    /* "VondaGrammar.y":567  */
                                                 {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "=="), (yyloc));
  };
  break;


  case 135: /* EqualityExpression: EqualityExpression NOTEQ RelationalExpression  */
  if (yyn == 135)
    /* "VondaGrammar.y":570  */
                                                  {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "!="), (yyloc));
  };
  break;


  case 136: /* RelationalExpression: AdditiveExpression  */
  if (yyn == 136)
    /* "VondaGrammar.y":576  */
                       { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 137: /* RelationalExpression: RelationalExpression '<' AdditiveExpression  */
  if (yyn == 137)
    /* "VondaGrammar.y":577  */
                                                {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "<"), (yyloc));
  };
  break;


  case 138: /* RelationalExpression: RelationalExpression '>' AdditiveExpression  */
  if (yyn == 138)
    /* "VondaGrammar.y":580  */
                                                {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), ">"), (yyloc));
  };
  break;


  case 139: /* RelationalExpression: RelationalExpression GTEQ AdditiveExpression  */
  if (yyn == 139)
    /* "VondaGrammar.y":583  */
                                                 {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), ">="), (yyloc));
  };
  break;


  case 140: /* RelationalExpression: RelationalExpression LTEQ AdditiveExpression  */
  if (yyn == 140)
    /* "VondaGrammar.y":586  */
                                                 {
    yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "<="), (yyloc));
  };
  break;


  case 141: /* AdditiveExpression: MultiplicativeExpression  */
  if (yyn == 141)
    /* "VondaGrammar.y":592  */
                             { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 142: /* AdditiveExpression: AdditiveExpression '+' MultiplicativeExpression  */
  if (yyn == 142)
    /* "VondaGrammar.y":593  */
                                                    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "+"), (yyloc));
  };
  break;


  case 143: /* AdditiveExpression: AdditiveExpression '-' MultiplicativeExpression  */
  if (yyn == 143)
    /* "VondaGrammar.y":596  */
                                                    {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "-"), (yyloc));
  };
  break;


  case 144: /* MultiplicativeExpression: CastExpression  */
  if (yyn == 144)
    /* "VondaGrammar.y":602  */
                   { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 145: /* MultiplicativeExpression: MultiplicativeExpression '*' CastExpression  */
  if (yyn == 145)
    /* "VondaGrammar.y":603  */
                                                {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "*"), (yyloc));
  };
  break;


  case 146: /* MultiplicativeExpression: MultiplicativeExpression '/' CastExpression  */
  if (yyn == 146)
    /* "VondaGrammar.y":606  */
                                                {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "/"), (yyloc));
  };
  break;


  case 147: /* MultiplicativeExpression: MultiplicativeExpression '%' CastExpression  */
  if (yyn == 147)
    /* "VondaGrammar.y":609  */
                                                {
    yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0))), "%"), (yyloc));
  };
  break;


  case 148: /* CastExpression: UnaryExpression  */
  if (yyn == 148)
    /* "VondaGrammar.y":615  */
                    { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 149: /* CastExpression: ISA '(' type_spec ',' ConditionalOrExpression ')'  */
  if (yyn == 149)
    /* "VondaGrammar.y":616  */
                                                      { yyval = setPos(new ExpCast(((Type)(yystack.valueAt (3))), ((RTExpression)(yystack.valueAt (1)))), (yyloc)); };
  break;


  case 150: /* UnaryExpression: PLUSPLUS UnaryExpression  */
  if (yyn == 150)
    /* "VondaGrammar.y":620  */
                             {
    yyval = createPlusMinus(((RTExpression)(yystack.valueAt (0))), "++", (yyloc));
  };
  break;


  case 151: /* UnaryExpression: MINUSMINUS UnaryExpression  */
  if (yyn == 151)
    /* "VondaGrammar.y":623  */
                               {
    yyval = createPlusMinus(((RTExpression)(yystack.valueAt (0))), "--", (yyloc));
  };
  break;


  case 152: /* UnaryExpression: '+' CastExpression  */
  if (yyn == 152)
    /* "VondaGrammar.y":626  */
                       { yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (0))), null, "+"), (yyloc)); };
  break;


  case 153: /* UnaryExpression: '-' CastExpression  */
  if (yyn == 153)
    /* "VondaGrammar.y":627  */
                       { yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (0))), null, "-"), (yyloc)); };
  break;


  case 154: /* UnaryExpression: LogicalUnaryExpression  */
  if (yyn == 154)
    /* "VondaGrammar.y":628  */
                           { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 155: /* LogicalUnaryExpression: PostfixExpression  */
  if (yyn == 155)
    /* "VondaGrammar.y":632  */
                      { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 156: /* LogicalUnaryExpression: '!' UnaryExpression  */
  if (yyn == 156)
    /* "VondaGrammar.y":633  */
                        { yyval = setPos(new ExpBoolean(((RTExpression)(yystack.valueAt (0))), null, "!"), (yyloc)); };
  break;


  case 157: /* LogicalUnaryExpression: '~' UnaryExpression  */
  if (yyn == 157)
    /* "VondaGrammar.y":634  */
                        { yyval = setPos(new ExpArithmetic(((RTExpression)(yystack.valueAt (0))), null, "~"), (yyloc)); };
  break;


  case 158: /* PostfixExpression: PrimaryExpression  */
  if (yyn == 158)
    /* "VondaGrammar.y":638  */
                      { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 159: /* PostfixExpression: PostfixExpression PLUSPLUS  */
  if (yyn == 159)
    /* "VondaGrammar.y":639  */
                               {
    yyval = createPlusMinus(((RTExpression)(yystack.valueAt (1))), "+++", (yyloc));
  };
  break;


  case 160: /* PostfixExpression: PostfixExpression MINUSMINUS  */
  if (yyn == 160)
    /* "VondaGrammar.y":642  */
                                 {
    yyval = createPlusMinus(((RTExpression)(yystack.valueAt (1))), "---", (yyloc));
  };
  break;


  case 161: /* PrimaryExpression: NULL  */
  if (yyn == 161)
    /* "VondaGrammar.y":648  */
         { yyval = setPos(new ExpLiteral("null", "null"), (yyloc)); };
  break;


  case 162: /* PrimaryExpression: IDENTIFIER  */
  if (yyn == 162)
    /* "VondaGrammar.y":649  */
               { yyval = setPos(new ExpIdentifier((( String )(yystack.valueAt (0)))), (yyloc)); };
  break;


  case 163: /* PrimaryExpression: field_access  */
  if (yyn == 163)
    /* "VondaGrammar.y":650  */
                 { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 164: /* PrimaryExpression: ComplexPrimary  */
  if (yyn == 164)
    /* "VondaGrammar.y":651  */
                   { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 165: /* ComplexPrimary: '(' exp ')'  */
  if (yyn == 165)
    /* "VondaGrammar.y":655  */
                { yyval = ((RTExpression)(yystack.valueAt (1))); ((RTExpression)(yystack.valueAt (1))).generateParens(); };
  break;


  case 166: /* ComplexPrimary: ComplexPrimaryNoParenthesis  */
  if (yyn == 166)
    /* "VondaGrammar.y":656  */
                                { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 167: /* ComplexPrimaryNoParenthesis: Literal  */
  if (yyn == 167)
    /* "VondaGrammar.y":660  */
            { yyval = ((ExpLiteral)(yystack.valueAt (0))); };
  break;


  case 168: /* ComplexPrimaryNoParenthesis: ArrayAccess  */
  if (yyn == 168)
    /* "VondaGrammar.y":661  */
                { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 169: /* ComplexPrimaryNoParenthesis: function_call  */
  if (yyn == 169)
    /* "VondaGrammar.y":662  */
                  { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 170: /* ComplexPrimaryNoParenthesis: dialogueact_exp  */
  if (yyn == 170)
    /* "VondaGrammar.y":663  */
                    { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 171: /* Literal: STRING  */
  if (yyn == 171)
    /* "VondaGrammar.y":667  */
           { yyval = setPos((( ExpLiteral )(yystack.valueAt (0))), (yyloc)); };
  break;


  case 172: /* Literal: INT  */
  if (yyn == 172)
    /* "VondaGrammar.y":668  */
        { yyval = setPos((( ExpLiteral )(yystack.valueAt (0))), (yyloc)); };
  break;


  case 173: /* Literal: OTHER_LITERAL  */
  if (yyn == 173)
    /* "VondaGrammar.y":669  */
                  { yyval = setPos((( ExpLiteral )(yystack.valueAt (0))), (yyloc)); };
  break;


  case 174: /* Literal: BOOL_LITERAL  */
  if (yyn == 174)
    /* "VondaGrammar.y":670  */
                 { yyval = setPos((( ExpLiteral )(yystack.valueAt (0))), (yyloc)); };
  break;


  case 175: /* ArrayAccess: IDENTIFIER '[' exp ']'  */
  if (yyn == 175)
    /* "VondaGrammar.y":674  */
                           {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (3)))), yystack.locationAt (3));
    yyval = setPos(new ExpArrayAccess(var, ((RTExpression)(yystack.valueAt (1)))), (yyloc));
  };
  break;


  case 176: /* ArrayAccess: ComplexPrimary '[' exp ']'  */
  if (yyn == 176)
    /* "VondaGrammar.y":678  */
                               { yyval = setPos(new ExpArrayAccess(((RTExpression)(yystack.valueAt (3))), ((RTExpression)(yystack.valueAt (1)))), (yyloc)); };
  break;


  case 177: /* field_access: IDENTIFIER field_access_rest  */
  if (yyn == 177)
    /* "VondaGrammar.y":682  */
                                 {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (1)))), yystack.locationAt (1));
    ((LinkedList<RTExpression>)(yystack.valueAt (0))).addFirst(var);
    yyval = setPos(new ExpFieldAccess(((LinkedList<RTExpression>)(yystack.valueAt (0)))), (yyloc));
  };
  break;


  case 178: /* field_access: ISA '(' type_spec ',' ConditionalOrExpression ')' field_access_rest  */
  if (yyn == 178)
    /* "VondaGrammar.y":687  */
                                                                        {
    ExpCast c = setPos(new ExpCast(((Type)(yystack.valueAt (4))), ((RTExpression)(yystack.valueAt (2)))), yystack.locationAt (4), yystack.locationAt (2));
    ((LinkedList<RTExpression>)(yystack.valueAt (0))).addFirst(c);
    yyval = setPos(new ExpFieldAccess(((LinkedList<RTExpression>)(yystack.valueAt (0)))), (yyloc));
  };
  break;


  case 179: /* field_access: ComplexPrimary field_access_rest  */
  if (yyn == 179)
    /* "VondaGrammar.y":692  */
                                     {
    ((LinkedList<RTExpression>)(yystack.valueAt (0))).addFirst(((RTExpression)(yystack.valueAt (1))));
    yyval = setPos(new ExpFieldAccess(((LinkedList<RTExpression>)(yystack.valueAt (0)))), (yyloc));
  };
  break;


  case 180: /* field_access_rest: '.' simple_nofa_exp field_access_rest  */
  if (yyn == 180)
    /* "VondaGrammar.y":699  */
                                           { yyval = ((LinkedList<RTExpression>)(yystack.valueAt (0))); ((LinkedList<RTExpression>)(yystack.valueAt (0))).addFirst(((RTExpression)(yystack.valueAt (1)))); };
  break;


  case 181: /* field_access_rest: '.' simple_nofa_exp  */
  if (yyn == 181)
    /* "VondaGrammar.y":700  */
                         {
     List<RTExpression> l = new LinkedList<RTExpression>();
     l.add(((RTExpression)(yystack.valueAt (0))));
     yyval = l;
   };
  break;


  case 182: /* simple_nofa_exp: IDENTIFIER  */
  if (yyn == 182)
    /* "VondaGrammar.y":708  */
               { yyval = setPos(new ExpIdentifier((( String )(yystack.valueAt (0)))), (yyloc)); };
  break;


  case 183: /* simple_nofa_exp: '{' exp '}'  */
  if (yyn == 183)
    /* "VondaGrammar.y":709  */
                { yyval = ((RTExpression)(yystack.valueAt (1))); };
  break;


  case 184: /* simple_nofa_exp: function_call  */
  if (yyn == 184)
    /* "VondaGrammar.y":710  */
                  { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 185: /* ConditionalExpression: ConditionalOrExpression '?' exp ':' exp  */
  if (yyn == 185)
    /* "VondaGrammar.y":714  */
                                            { yyval = setPos(new ExpConditional(((RTExpression)(yystack.valueAt (4))), ((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0)))), (yyloc)); };
  break;


  case 186: /* ConditionalExpression: ConditionalOrExpression  */
  if (yyn == 186)
    /* "VondaGrammar.y":715  */
                            { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 187: /* assignment: field_access assgn_exp  */
  if (yyn == 187)
    /* "VondaGrammar.y":721  */
                           { yyval = setPos(new ExpAssignment(((RTExpression)(yystack.valueAt (1))), ((RTExpression)(yystack.valueAt (0)))), (yyloc)); };
  break;


  case 188: /* assignment: ArrayAccess assgn_exp  */
  if (yyn == 188)
    /* "VondaGrammar.y":722  */
                          { yyval = setPos(new ExpAssignment(((RTExpression)(yystack.valueAt (1))), ((RTExpression)(yystack.valueAt (0)))), (yyloc)); };
  break;


  case 189: /* assignment: IDENTIFIER assgn_exp  */
  if (yyn == 189)
    /* "VondaGrammar.y":723  */
                         {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (1)))), yystack.locationAt (1));
    ExpAssignment ass = setPos(new ExpAssignment(var, ((RTExpression)(yystack.valueAt (0)))), (yyloc));
    yyval = ass;
  };
  break;


  case 190: /* new_exp: NEW IDENTIFIER  */
  if (yyn == 190)
    /* "VondaGrammar.y":731  */
                   { yyval = setPos(new ExpNew(new Type((( String )(yystack.valueAt (0))))), (yyloc)); };
  break;


  case 191: /* new_exp: NEW IDENTIFIER '(' ')'  */
  if (yyn == 191)
    /* "VondaGrammar.y":732  */
                           {
    yyval = setPos(new ExpNew(new Type((( String )(yystack.valueAt (2)))), new LinkedList<>()), (yyloc));
  };
  break;


  case 192: /* new_exp: NEW IDENTIFIER '(' nonempty_exp_list ')'  */
  if (yyn == 192)
    /* "VondaGrammar.y":735  */
                                             {
    yyval = setPos(new ExpNew(new Type((( String )(yystack.valueAt (3)))), ((LinkedList<RTExpression>)(yystack.valueAt (1)))), (yyloc));
  };
  break;


  case 193: /* new_exp: NEW IDENTIFIER '[' exp ']'  */
  if (yyn == 193)
    /* "VondaGrammar.y":738  */
                               {
    List<Type> sub = new ArrayList<Type>() {{ add(new Type((( String )(yystack.valueAt (3))))); }};
    yyval = setPos(new ExpNew(new Type("Array", sub),
                    new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (1)))); }}), (yyloc));
  };
  break;


  case 194: /* new_exp: NEW IDENTIFIER '[' ']' '(' exp ')'  */
  if (yyn == 194)
    /* "VondaGrammar.y":743  */
                                      {
    List<Type> sub = new ArrayList<Type>() {{ add(new Type((( String )(yystack.valueAt (5))))); }};
    yyval = setPos(new ExpNew(new Type("Array", sub),
                    new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (1)))); }}), (yyloc));
  };
  break;


  case 195: /* new_exp: NEW IDENTIFIER '<' type_spec_list '>' '(' ')'  */
  if (yyn == 195)
    /* "VondaGrammar.y":748  */
                                                  {
    yyval = setPos(new ExpNew(new Type((( String )(yystack.valueAt (5))), ((LinkedList<Type>)(yystack.valueAt (3)))),
                    new LinkedList<>()), (yyloc));
  };
  break;


  case 196: /* new_exp: NEW IDENTIFIER '<' type_spec_list '>' '(' nonempty_exp_list ')'  */
  if (yyn == 196)
    /* "VondaGrammar.y":752  */
                                                                    {
    yyval = setPos(new ExpNew(new Type((( String )(yystack.valueAt (6))), ((LinkedList<Type>)(yystack.valueAt (4)))),
                    ((LinkedList<RTExpression>)(yystack.valueAt (1)))), (yyloc));
  };
  break;


  case 197: /* lambda_exp: LAMBDA '(' opt_args_list ')' exp  */
  if (yyn == 197)
    /* "VondaGrammar.y":759  */
                                     {
    yyval = setPos(new ExpLambda(((LinkedList)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0)))), (yyloc));
  };
  break;


  case 198: /* lambda_exp: LAMBDA '(' opt_args_list ')' block  */
  if (yyn == 198)
    /* "VondaGrammar.y":762  */
                                       {
    yyval = setPos(new ExpLambda(((LinkedList)(yystack.valueAt (2))), ((StatAbstractBlock)(yystack.valueAt (0)))), (yyloc));
  };
  break;


  case 199: /* dialogueact_exp: '#' da_token '(' da_token da_args ')'  */
  if (yyn == 199)
    /* "VondaGrammar.y":769  */
                                          {
    yyval = setPos(new ExpDialogueAct(((RTExpression)(yystack.valueAt (4))), ((RTExpression)(yystack.valueAt (2))), ((LinkedList<RTExpression>)(yystack.valueAt (1)))), (yyloc));
  };
  break;


  case 200: /* da_token: '{' exp '}'  */
  if (yyn == 200)
    /* "VondaGrammar.y":775  */
                { yyval = ((RTExpression)(yystack.valueAt (1))); };
  break;


  case 201: /* da_token: IDENTIFIER  */
  if (yyn == 201)
    /* "VondaGrammar.y":778  */
               { yyval = setPos(new ExpLiteral((( String )(yystack.valueAt (0))), "String"), (yyloc)); };
  break;


  case 202: /* da_token: STRING  */
  if (yyn == 202)
    /* "VondaGrammar.y":779  */
           { yyval = setPos((( ExpLiteral )(yystack.valueAt (0))), (yyloc)); };
  break;


  case 203: /* da_token: WILDCARD  */
  if (yyn == 203)
    /* "VondaGrammar.y":780  */
             { yyval = setPos(new ExpLiteral((( String )(yystack.valueAt (0))), "String"), (yyloc)); };
  break;


  case 204: /* da_args: ',' da_token '=' da_token da_args  */
  if (yyn == 204)
    /* "VondaGrammar.y":784  */
                                       {
    yyval = ((LinkedList<RTExpression>)(yystack.valueAt (0))); ((LinkedList<RTExpression>)(yystack.valueAt (0))).addFirst(((RTExpression)(yystack.valueAt (1)))); ((LinkedList<RTExpression>)(yystack.valueAt (0))).addFirst(((RTExpression)(yystack.valueAt (3))));
  };
  break;


  case 205: /* da_args: %empty  */
  if (yyn == 205)
    /* "VondaGrammar.y":787  */
           { yyval = new LinkedList<RTExpression>(); };
  break;


  case 206: /* exp: ConditionalExpression  */
  if (yyn == 206)
    /* "VondaGrammar.y":791  */
                          { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 207: /* exp: assignment  */
  if (yyn == 207)
    /* "VondaGrammar.y":792  */
               { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 208: /* exp: new_exp  */
  if (yyn == 208)
    /* "VondaGrammar.y":793  */
            { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;



/* "VondaGrammar.java":2579  */

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
  private static final short yytable_ninf_ = -202;

/* YYPACT[STATE-NUM] -- Index in YYTABLE of the portion describing
   STATE-NUM.  */
  private static final short[] yypact_ = yypact_init();
  private static final short[] yypact_init()
  {
    return new short[]
    {
     753,   159,    -1,   110,   304,   158,     2,   695,   169,   178,
     196,    61,   209,  -375,   222,  -375,  -375,   543,   242,   259,
     262,   291,   156,   297,  -375,  -375,  -375,   324,  -375,   642,
    1187,   214,   349,   753,   822,  -375,     5,   822,   822,  -375,
    -375,  -375,  -375,  -375,  -375,  -375,  -375,  -375,  -375,  -375,
    -375,  -375,  -375,   480,   261,    25,  -375,  -375,    64,   213,
     305,  -375,   308,  -375,  -375,  -375,   313,   314,   315,   317,
    -375,  -375,   282,  -375,   329,  -375,   327,   326,    86,   333,
      32,  1187,   209,  -375,   113,   131,  1187,   335,  -375,   330,
    1219,  1219,   133,  -375,  1251,  1251,  1219,  1219,  -375,   -22,
     354,   328,   323,   325,   253,    59,   226,    82,  -375,  -375,
    -375,   269,  -375,    25,   331,   331,  -375,  -375,  -375,   345,
    1187,  1187,  1187,   348,   129,  -375,   352,   252,   353,  1187,
    1187,   357,   360,   -30,   380,   228,   840,   872,   348,  -375,
    -375,  -375,  -375,   695,   350,   342,  -375,  -375,   150,  1187,
     348,   355,  -375,  -375,  -375,   205,   348,  -375,  -375,  -375,
    -375,  -375,  -375,   101,  1187,  -375,  1187,  1187,  -375,  1187,
    1187,   362,   363,  -375,  -375,  -375,  -375,  -375,  -375,  -375,
    -375,  -375,   364,    60,  -375,   361,   366,    85,   371,    77,
    1187,   904,   376,   368,   274,  -375,   162,  -375,   378,   372,
     225,   348,    28,  -375,  -375,  -375,  1187,  -375,  -375,  -375,
    -375,  1251,  1187,  1251,  1251,  1251,  1251,  1251,  1251,  1251,
    1251,  1251,  1251,  1251,  1251,  1251,  1251,  1251,  -375,  -375,
    -375,   377,   370,   384,   379,  -375,  -375,  -375,  -375,   392,
     393,  -375,  -375,   389,  1187,  -375,   401,  -375,   399,  -375,
     402,   400,   405,   936,  -375,  -375,   398,   409,   404,  -375,
    -375,  -375,   415,   422,   282,   348,  -375,   424,   423,   412,
     427,   428,   431,   432,  -375,  -375,  1187,  -375,  -375,   445,
     219,   451,  1187,   450,   452,   971,   453,   136,   695,  -375,
     217,  -375,   464,   465,   989,  1027,   348,   461,   354,   470,
     328,   323,   325,   253,    59,    59,   226,   226,   226,   226,
      82,    82,  -375,  -375,  -375,   465,  1187,   695,  1251,  -375,
    -375,   477,  -375,   424,  -375,  1059,  1059,  -375,   478,   476,
    -375,   348,  -375,  -375,   112,   479,   487,   -28,   481,  -375,
     492,  -375,  -375,  -375,  -375,  -375,  -375,   484,  -375,   494,
     331,   485,  -375,  1187,   695,   488,  1091,  1187,   499,   536,
     502,  -375,  -375,  -375,   496,   501,   497,   498,  1251,  1187,
    -375,   503,  -375,   -16,  -375,   504,  -375,  -375,  -375,  1187,
    -375,  -375,   424,   282,   510,   512,   424,    53,   500,  -375,
    -375,   522,   695,   516,  -375,   695,   695,   517,   519,  -375,
     695,  -375,  -375,  1187,  -375,   521,   -11,  -375,   465,   401,
    1123,  -375,   523,   518,  -375,  -375,  -375,  -375,  -375,   424,
    -375,  -375,   695,  -375,  -375,   695,   695,  -375,   524,  1155,
     401,  -375,  -375,  -375,  -375,    53,   282,  -375,  -375,  -375,
    -375,  -375,  -375,   525,  -375,   479,  -375,  -375
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
       0,     0,     0,     0,   171,   172,   174,   119,   173,     0,
       0,     0,     0,    17,    17,     3,     0,    17,    17,    24,
      36,    38,    41,    42,    43,    39,    40,    44,    45,    11,
      12,     9,    37,   169,     0,     0,   166,   167,   168,     0,
       0,   170,     0,    56,    58,    59,     0,     0,     0,     0,
      60,    83,     0,    46,     0,    47,   169,     0,   119,     0,
       0,     0,     0,    22,     0,     0,     0,     0,   161,     0,
       0,     0,   162,    54,     0,     0,     0,     0,   169,   186,
     124,   126,   127,   129,   131,   133,   136,   141,   144,   148,
     154,   155,   158,   164,   168,   163,   206,   207,   208,     0,
       0,     0,     0,     0,     0,   168,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,   189,
     177,    50,    48,    51,     0,     0,   202,   203,   119,     0,
       0,     0,     1,     2,    13,   119,     0,    10,     8,    16,
      15,    35,    14,     0,     0,   179,     0,     0,   188,     0,
       0,     0,     0,    26,   187,    25,    57,    79,    80,    81,
      82,   201,     0,     0,    87,     0,     0,     0,     0,   119,
       0,     0,     0,     0,     0,     4,     0,    21,     0,     0,
     190,     0,   162,   151,   163,   150,     0,   152,   153,   156,
     157,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,   160,   159,
      55,     0,     0,     0,     0,    28,    32,    27,    31,     0,
       0,    30,    29,   182,     0,   184,   181,    53,     0,   112,
       0,   115,   114,     0,    91,   118,     0,   121,     0,    52,
      49,   165,     0,     0,     0,     0,    88,   101,     0,     0,
       0,     0,     0,     0,    34,    33,     0,    84,    89,     0,
     119,     0,     0,     0,     0,     0,     0,     0,     0,     6,
       0,    23,     0,     0,     0,     0,     0,     0,   123,     0,
     125,   128,   130,   132,   134,   135,   139,   140,   137,   138,
     142,   143,   145,   146,   147,     0,     0,     0,     0,   107,
     106,     0,   180,   101,   113,     0,     0,    92,     0,    94,
     175,     0,   120,   200,     0,   205,     0,   102,     0,   100,
       0,    85,   176,   109,   108,   111,   110,     0,    86,     0,
       0,     0,    72,     0,     0,     0,     0,     0,     0,    61,
       0,     5,    76,   191,     0,     0,     0,     0,     0,     0,
      78,     0,    63,     0,   183,     0,   117,   116,    93,     0,
     122,    90,   101,     0,     0,     0,     0,     0,   103,    64,
      74,     0,     0,     0,    68,     0,     0,     0,     0,    73,
       0,     7,   192,     0,   193,     0,     0,   185,     0,     0,
       0,    95,     0,     0,   199,   104,    99,    98,    97,     0,
      75,    70,     0,    66,    67,     0,     0,    62,     0,     0,
     149,    77,   178,   198,   197,     0,     0,   105,    69,    65,
      71,   194,   195,     0,    96,   205,   196,   204
    };
  }

/* YYPGOTO[NTERM-NUM].  */
  private static final short[] yypgoto_ = yypgoto_init();
  private static final short[] yypgoto_init()
  {
    return new short[]
    {
    -375,   546,  -375,  -375,    72,  -375,  -375,    -2,   134,    56,
    -375,  -284,   440,  -375,  -375,   455,  -375,  -375,  -375,  -375,
    -375,  -375,  -375,   128,  -375,     7,  -290,   554,   157,  -320,
    -374,  -375,    49,     1,   278,  -264,  -307,   391,   387,   390,
     388,   375,   111,    80,   109,   -89,   -70,  -375,  -375,  -375,
      35,  -375,  -375,     0,    17,   -53,  -375,  -375,   116,  -375,
    -375,  -375,  -263,   160,   429
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
      46,    47,    48,    75,    50,   139,   328,    51,   418,   338,
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
      58,   335,   165,   375,   364,   207,   208,    58,   211,   362,
      85,   373,   415,   243,   211,  -119,     8,    59,   244,   211,
     203,   205,   125,   125,    59,   386,   209,   210,   185,    58,
     138,   370,   367,    58,    58,    55,   409,    58,    58,   126,
     128,   430,    55,   188,    64,   437,    59,   212,   155,    53,
      59,    59,    71,    58,    59,    59,    76,    55,    55,   156,
     165,   406,   412,    74,    55,   168,   174,   380,    55,    55,
      59,   133,    55,    55,   133,   189,    82,   190,    76,   135,
     194,   164,    53,    53,   206,   186,    53,    53,    55,   411,
     125,   125,   219,   220,   125,   125,   125,   125,   416,   166,
     167,    29,    53,   417,    83,   266,   154,   204,   204,   159,
     160,   204,   204,   204,   204,   136,    60,   221,   222,   136,
     413,   168,   174,    60,   431,   162,   433,   282,    49,   225,
     278,   184,   136,   185,    38,   138,   312,   313,   314,   443,
     136,   136,   185,    58,   138,    60,   266,   226,   227,    60,
      60,   417,   267,    60,    60,    65,   136,   381,   195,   196,
      59,    49,    49,   382,   157,    49,    49,    38,    38,    60,
     268,    38,    38,   445,   235,   133,   197,   198,    55,   133,
     135,    49,   245,    21,   135,   206,   357,    38,   136,   206,
     268,   136,    76,   322,   279,    24,   283,    25,    26,   124,
      28,  -201,    62,    70,    63,   291,   185,    30,   138,   292,
      72,   125,    78,   125,   125,   125,   125,   125,   125,   125,
     125,   125,   125,   125,   125,   125,   125,   125,   204,    80,
     204,   204,   204,   204,   204,   204,   204,   204,   204,   204,
     204,   204,   204,   204,   204,    87,    88,    81,   169,   170,
     171,   172,    83,   146,   147,    89,   248,   148,   173,    60,
     291,   185,   149,   138,   360,    90,    91,    24,   136,    25,
      26,    92,    28,    86,   136,   185,   294,   138,    54,    30,
     249,   295,    72,   296,   217,   218,    79,   349,    58,   223,
     224,    94,    95,   120,   358,    96,    97,   237,   133,   306,
     307,   308,   309,   135,   163,    59,   228,   229,   206,   150,
     121,    54,    54,   122,    54,    54,    54,    58,   125,   289,
     290,   146,   147,    55,    21,   181,   376,   377,   304,   305,
     149,    54,   310,   311,    59,   204,    24,    76,    25,    26,
     127,    28,   123,    66,   359,    67,    68,    69,    30,   152,
     175,    72,    55,   176,    58,   182,   432,   391,   192,   129,
     130,   131,   132,   177,   178,   179,    76,   180,   125,   183,
     133,    59,   161,   372,   134,   135,   187,   432,   200,   136,
     137,   201,   138,   213,   215,   204,   136,   216,   214,    55,
     230,   155,    58,    10,   261,    58,    58,   236,   238,   260,
      58,   234,   241,    76,    60,   242,   264,   274,   275,    59,
     394,   277,    59,    59,   280,   276,   257,    59,   255,   287,
     288,   291,    58,   316,   293,    58,    58,    55,   263,   315,
      55,    55,   318,    60,   265,    55,   317,   319,   320,    59,
     135,    76,    59,    59,    76,    76,   119,   133,   421,    76,
     323,   423,   424,   325,   324,   330,   427,    55,   326,   145,
      55,    55,   331,   332,   333,   334,   281,   337,   341,   342,
      60,    76,   343,   344,    76,    76,   345,   346,   438,   297,
     -17,   439,   440,     1,     2,     3,     4,     5,     6,     7,
     348,     8,     9,    10,   350,   352,    12,   353,   356,    13,
      14,    15,    16,    17,    18,    19,    20,    21,    60,   361,
     193,    60,    60,    29,   368,   199,    60,    22,    23,    24,
     369,    25,    26,    27,    28,   161,   374,   378,    29,   379,
     385,    30,   383,   387,    31,   388,   389,   392,    60,   390,
     395,    60,    60,   336,   399,   340,   400,   401,   402,   231,
     232,   233,   403,   419,   404,   408,   410,   405,   239,   240,
      87,    88,   414,   382,   252,   254,   256,   420,   422,   425,
      89,   426,   429,   436,   257,   435,   441,   446,   262,   153,
      90,    91,    24,   259,    25,    26,    92,    28,    93,   247,
     158,   303,   444,   269,    30,   270,   271,    72,   272,   273,
     300,   340,   298,   302,   301,   447,    94,    95,     0,   257,
      96,    97,     0,     0,     0,     0,     0,     0,     0,   284,
     286,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,   256,     0,     0,     0,     0,
       0,   299,     0,     0,     0,     1,     2,     3,     4,     5,
       6,     7,     0,     8,     9,    10,     0,     0,     0,     0,
     340,     0,    14,     0,   340,    17,    18,    19,    20,    21,
       0,     0,     0,   321,     0,     0,     0,     0,     0,    22,
      23,    24,     0,    25,    26,    27,    28,     0,     0,     0,
      29,   141,     0,    30,     0,     0,    72,   340,     1,     2,
       3,     4,     5,     6,     7,   347,     8,     9,    10,     0,
       0,   351,     0,     0,   355,    14,     0,     0,    17,    18,
      19,    20,    21,     0,   366,     0,     0,     0,     0,     0,
       0,     0,    22,    23,    24,     0,    25,    26,    27,    28,
       0,     0,     0,    29,     0,   371,    30,     0,     0,    72,
       0,     0,     0,     0,   252,   252,     1,     2,     3,     4,
       5,     6,     7,     0,     8,     9,    10,    11,     0,    12,
       0,     0,    13,    14,    15,    16,    17,    18,    19,    20,
      21,     0,   393,     0,     0,   397,   398,     0,     0,     0,
      22,    23,    24,     0,    25,    26,    27,    28,   407,     0,
       0,    29,     0,     0,    30,     0,     0,    31,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     1,     2,     3,     4,     5,
       6,     7,   428,     8,     9,    10,     0,     0,    12,   434,
       0,    13,    14,    15,    16,    17,    18,    19,    20,    21,
       0,     0,     0,     0,     0,     0,     0,    87,    88,    22,
      23,    24,     0,    25,    26,    27,    28,    89,     0,     0,
      29,     0,     0,    30,     0,     0,    31,    90,    91,    24,
       0,    25,    26,    92,    28,     0,     0,     0,   253,    87,
      88,    30,     0,     0,    72,     0,     0,     0,     0,    89,
       0,     0,     0,    94,    95,     0,     0,    96,    97,    90,
      91,    24,     0,    25,    26,    92,    28,     0,     0,     0,
       0,    87,    88,    30,     0,     0,    72,     0,     0,   255,
       0,    89,     0,     0,     0,    94,    95,     0,     0,    96,
      97,    90,    91,    24,     0,    25,    26,    92,    28,   285,
       0,     0,     0,    87,    88,    30,     0,     0,    72,     0,
       0,     0,     0,    89,     0,     0,     0,    94,    95,     0,
       0,    96,    97,    90,    91,    24,     0,    25,    26,    92,
      28,     0,     0,     0,     0,   327,     0,    30,    87,    88,
      72,     0,     0,     0,     0,     0,     0,     0,    89,    94,
      95,     0,     0,    96,    97,     0,    87,    88,    90,    91,
      24,     0,    25,    26,    92,    28,    89,     0,     0,     0,
       0,     0,    30,   354,     0,    72,    90,    91,    24,     0,
      25,    26,    92,    28,    94,    95,     0,     0,    96,    97,
      30,   363,     0,    72,    87,    88,     0,     0,     0,     0,
       0,     0,    94,    95,    89,     0,    96,    97,     0,     0,
       0,     0,     0,     0,    90,    91,    24,     0,    25,    26,
      92,    28,     0,     0,     0,     0,    87,    88,    30,     0,
       0,    72,     0,     0,   365,     0,    89,   248,     0,     0,
      94,    95,     0,     0,    96,    97,    90,    91,    24,     0,
      25,    26,    92,    28,     0,     0,     0,     0,    87,    88,
      30,     0,     0,    72,     0,     0,     0,     0,    89,     0,
       0,     0,    94,    95,     0,     0,    96,    97,    90,    91,
      24,     0,    25,    26,    92,    28,     0,     0,     0,     0,
      87,    88,    30,   396,     0,    72,     0,     0,     0,     0,
      89,     0,     0,     0,    94,    95,     0,     0,    96,    97,
      90,    91,    24,     0,    25,    26,    92,    28,     0,     0,
       0,    29,    87,    88,    30,     0,     0,    72,     0,     0,
       0,     0,    89,     0,     0,     0,    94,    95,     0,     0,
      96,    97,    90,    91,    24,     0,    25,    26,    92,    28,
       0,     0,     0,     0,    87,    88,    30,   442,     0,    72,
       0,     0,     0,     0,    89,     0,     0,     0,    94,    95,
       0,     0,    96,    97,    90,    91,    24,     0,    25,    26,
      92,    28,     0,     0,     0,     0,     0,    88,    30,     0,
       0,    72,     0,     0,     0,     0,    21,     0,     0,     0,
      94,    95,     0,     0,    96,    97,    90,    91,    24,     0,
      25,    26,   202,    28,     0,     0,     0,     0,     0,    88,
      30,     0,     0,    72,     0,     0,     0,     0,    89,     0,
       0,     0,    94,    95,     0,     0,    96,    97,    90,    91,
      24,     0,    25,    26,   202,    28,     0,     0,     0,     0,
       0,     0,    30,     0,     0,    72,     0,     0,     0,     0,
       0,     0,     0,     0,    94,    95,     0,     0,    96,    97
    };
  }

private static final short[] yycheck_ = yycheck_init();
  private static final short[] yycheck_init()
  {
    return new short[]
    {
       0,   264,    55,   323,   294,    94,    95,     7,    30,   293,
      12,   318,   386,    43,    30,    43,    11,     0,    48,    30,
      90,    91,    22,    23,     7,    53,    96,    97,    56,    29,
      58,   315,   296,    33,    34,     0,    52,    37,    38,    22,
      23,    52,     7,    11,    45,   419,    29,    69,    43,     0,
      33,    34,    50,    53,    37,    38,     7,    22,    23,    54,
     113,   368,   382,     7,    29,    58,    59,   331,    33,    34,
      53,    46,    37,    38,    46,    43,    15,    45,    29,    51,
      82,    56,    33,    34,    56,    78,    37,    38,    53,   379,
      90,    91,    33,    34,    94,    95,    96,    97,    45,    35,
      36,    48,    53,   387,    43,    45,    34,    90,    91,    37,
      38,    94,    95,    96,    97,    55,     0,    58,    59,    55,
     383,   114,   115,     7,   408,    53,   410,    50,     0,    47,
      45,    45,    55,    56,     0,    58,   225,   226,   227,   429,
      55,    55,    56,   143,    58,    29,    45,    65,    66,    33,
      34,   435,    51,    37,    38,    45,    55,    45,    45,    46,
     143,    33,    34,    51,    36,    37,    38,    33,    34,    53,
     163,    37,    38,   436,    45,    46,    45,    46,   143,    46,
      51,    53,   133,    27,    51,    56,    50,    53,    55,    56,
     183,    55,   143,   246,   187,    39,   189,    41,    42,    43,
      44,    51,    43,    45,    45,    43,    56,    51,    58,    47,
      54,   211,    43,   213,   214,   215,   216,   217,   218,   219,
     220,   221,   222,   223,   224,   225,   226,   227,   211,    51,
     213,   214,   215,   216,   217,   218,   219,   220,   221,   222,
     223,   224,   225,   226,   227,    17,    18,    51,    35,    36,
      37,    38,    43,    39,    40,    27,    28,    43,    45,   143,
      43,    56,    48,    58,    47,    37,    38,    39,    55,    41,
      42,    43,    44,    51,    55,    56,    51,    58,     0,    51,
      52,    56,    54,    58,    31,    32,     8,   280,   288,    63,
      64,    63,    64,    51,   287,    67,    68,    45,    46,   219,
     220,   221,   222,    51,    43,   288,    37,    38,    56,    31,
      51,    33,    34,    51,    36,    37,    38,   317,   318,    45,
      46,    39,    40,   288,    27,    43,   325,   326,   217,   218,
      48,    53,   223,   224,   317,   318,    39,   288,    41,    42,
      43,    44,    51,    39,   288,    41,    42,    43,    51,     0,
      45,    54,   317,    45,   354,    26,   409,   350,    80,    35,
      36,    37,    38,    50,    50,    50,   317,    50,   368,    43,
      46,   354,    45,   317,    50,    51,    43,   430,    43,    55,
      56,    51,    58,    29,    61,   368,    55,    62,    60,   354,
      45,    43,   392,    13,    52,   395,   396,    45,    45,    49,
     400,   123,    45,   354,   288,    45,    51,    45,    45,   392,
     354,    45,   395,   396,    43,    51,   138,   400,    57,    43,
      52,    43,   422,    53,    52,   425,   426,   392,   150,    52,
     395,   396,    53,   317,   156,   400,    52,    45,    45,   422,
      51,   392,   425,   426,   395,   396,    17,    46,   392,   400,
      51,   395,   396,    53,    52,    57,   400,   422,    53,    30,
     425,   426,    53,    59,    49,    43,   188,    43,    45,    57,
     354,   422,    45,    45,   425,   426,    45,    45,   422,   201,
       0,   425,   426,     3,     4,     5,     6,     7,     8,     9,
      45,    11,    12,    13,    43,    45,    16,    45,    45,    19,
      20,    21,    22,    23,    24,    25,    26,    27,   392,    45,
      81,   395,   396,    48,    53,    86,   400,    37,    38,    39,
      50,    41,    42,    43,    44,    45,    49,    49,    48,    53,
      43,    51,    53,    52,    54,    43,    52,    52,   422,    45,
      52,   425,   426,   265,    45,   267,    10,    45,    52,   120,
     121,   122,    51,    53,    57,    52,    52,    59,   129,   130,
      17,    18,    52,    51,   135,   136,   137,    45,    52,    52,
      27,    52,    51,    55,   296,    52,    52,    52,   149,    33,
      37,    38,    39,   143,    41,    42,    43,    44,    45,   134,
      36,   216,   435,   164,    51,   166,   167,    54,   169,   170,
     213,   323,   211,   215,   214,   445,    63,    64,    -1,   331,
      67,    68,    -1,    -1,    -1,    -1,    -1,    -1,    -1,   190,
     191,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,   206,    -1,    -1,    -1,    -1,
      -1,   212,    -1,    -1,    -1,     3,     4,     5,     6,     7,
       8,     9,    -1,    11,    12,    13,    -1,    -1,    -1,    -1,
     382,    -1,    20,    -1,   386,    23,    24,    25,    26,    27,
      -1,    -1,    -1,   244,    -1,    -1,    -1,    -1,    -1,    37,
      38,    39,    -1,    41,    42,    43,    44,    -1,    -1,    -1,
      48,    49,    -1,    51,    -1,    -1,    54,   419,     3,     4,
       5,     6,     7,     8,     9,   276,    11,    12,    13,    -1,
      -1,   282,    -1,    -1,   285,    20,    -1,    -1,    23,    24,
      25,    26,    27,    -1,   295,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    37,    38,    39,    -1,    41,    42,    43,    44,
      -1,    -1,    -1,    48,    -1,   316,    51,    -1,    -1,    54,
      -1,    -1,    -1,    -1,   325,   326,     3,     4,     5,     6,
       7,     8,     9,    -1,    11,    12,    13,    14,    -1,    16,
      -1,    -1,    19,    20,    21,    22,    23,    24,    25,    26,
      27,    -1,   353,    -1,    -1,   356,   357,    -1,    -1,    -1,
      37,    38,    39,    -1,    41,    42,    43,    44,   369,    -1,
      -1,    48,    -1,    -1,    51,    -1,    -1,    54,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,     3,     4,     5,     6,     7,
       8,     9,   403,    11,    12,    13,    -1,    -1,    16,   410,
      -1,    19,    20,    21,    22,    23,    24,    25,    26,    27,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    17,    18,    37,
      38,    39,    -1,    41,    42,    43,    44,    27,    -1,    -1,
      48,    -1,    -1,    51,    -1,    -1,    54,    37,    38,    39,
      -1,    41,    42,    43,    44,    -1,    -1,    -1,    48,    17,
      18,    51,    -1,    -1,    54,    -1,    -1,    -1,    -1,    27,
      -1,    -1,    -1,    63,    64,    -1,    -1,    67,    68,    37,
      38,    39,    -1,    41,    42,    43,    44,    -1,    -1,    -1,
      -1,    17,    18,    51,    -1,    -1,    54,    -1,    -1,    57,
      -1,    27,    -1,    -1,    -1,    63,    64,    -1,    -1,    67,
      68,    37,    38,    39,    -1,    41,    42,    43,    44,    45,
      -1,    -1,    -1,    17,    18,    51,    -1,    -1,    54,    -1,
      -1,    -1,    -1,    27,    -1,    -1,    -1,    63,    64,    -1,
      -1,    67,    68,    37,    38,    39,    -1,    41,    42,    43,
      44,    -1,    -1,    -1,    -1,    49,    -1,    51,    17,    18,
      54,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    27,    63,
      64,    -1,    -1,    67,    68,    -1,    17,    18,    37,    38,
      39,    -1,    41,    42,    43,    44,    27,    -1,    -1,    -1,
      -1,    -1,    51,    52,    -1,    54,    37,    38,    39,    -1,
      41,    42,    43,    44,    63,    64,    -1,    -1,    67,    68,
      51,    52,    -1,    54,    17,    18,    -1,    -1,    -1,    -1,
      -1,    -1,    63,    64,    27,    -1,    67,    68,    -1,    -1,
      -1,    -1,    -1,    -1,    37,    38,    39,    -1,    41,    42,
      43,    44,    -1,    -1,    -1,    -1,    17,    18,    51,    -1,
      -1,    54,    -1,    -1,    57,    -1,    27,    28,    -1,    -1,
      63,    64,    -1,    -1,    67,    68,    37,    38,    39,    -1,
      41,    42,    43,    44,    -1,    -1,    -1,    -1,    17,    18,
      51,    -1,    -1,    54,    -1,    -1,    -1,    -1,    27,    -1,
      -1,    -1,    63,    64,    -1,    -1,    67,    68,    37,    38,
      39,    -1,    41,    42,    43,    44,    -1,    -1,    -1,    -1,
      17,    18,    51,    52,    -1,    54,    -1,    -1,    -1,    -1,
      27,    -1,    -1,    -1,    63,    64,    -1,    -1,    67,    68,
      37,    38,    39,    -1,    41,    42,    43,    44,    -1,    -1,
      -1,    48,    17,    18,    51,    -1,    -1,    54,    -1,    -1,
      -1,    -1,    27,    -1,    -1,    -1,    63,    64,    -1,    -1,
      67,    68,    37,    38,    39,    -1,    41,    42,    43,    44,
      -1,    -1,    -1,    -1,    17,    18,    51,    52,    -1,    54,
      -1,    -1,    -1,    -1,    27,    -1,    -1,    -1,    63,    64,
      -1,    -1,    67,    68,    37,    38,    39,    -1,    41,    42,
      43,    44,    -1,    -1,    -1,    -1,    -1,    18,    51,    -1,
      -1,    54,    -1,    -1,    -1,    -1,    27,    -1,    -1,    -1,
      63,    64,    -1,    -1,    67,    68,    37,    38,    39,    -1,
      41,    42,    43,    44,    -1,    -1,    -1,    -1,    -1,    18,
      51,    -1,    -1,    54,    -1,    -1,    -1,    -1,    27,    -1,
      -1,    -1,    63,    64,    -1,    -1,    67,    68,    37,    38,
      39,    -1,    41,    42,    43,    44,    -1,    -1,    -1,    -1,
      -1,    -1,    51,    -1,    -1,    54,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    63,    64,    -1,    -1,    67,    68
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
      26,    27,    37,    38,    39,    41,    42,    43,    44,    48,
      51,    54,    71,    72,    73,    74,    75,    76,    78,    81,
      83,    84,    85,    86,    87,    89,    90,    91,    92,    93,
      94,    97,   101,   102,   104,   120,   121,   122,   123,   124,
     128,   131,    43,    45,    45,    45,    39,    41,    42,    43,
      45,    50,    54,    78,    79,    93,   102,   104,    43,   104,
      51,    51,    15,    43,    77,    77,    51,    17,    18,    27,
      37,    38,    43,    45,    63,    64,    67,    68,   102,   106,
     107,   108,   109,   110,   111,   112,   113,   114,   115,   116,
     117,   118,   119,   120,   123,   124,   127,   128,   129,   134,
      51,    51,    51,    51,    43,   123,   124,    43,   124,    35,
      36,    37,    38,    46,    50,    51,    55,    56,    58,    95,
     125,    49,    79,    80,    82,   134,    39,    40,    43,    48,
     104,   132,     0,    71,    74,    43,    54,    93,    97,    74,
      74,    45,    74,    43,    56,   125,    35,    36,    95,    35,
      36,    37,    38,    45,    95,    45,    45,    50,    50,    50,
      50,    43,    26,    43,    45,    56,    95,    43,    11,    43,
      45,    88,   104,   134,    77,    45,    46,    45,    46,   134,
      43,    51,    43,   116,   124,   116,    56,   115,   115,   116,
     116,    30,    69,    29,    60,    61,    62,    31,    32,    33,
      34,    58,    59,    63,    64,    47,    65,    66,    37,    38,
      45,   134,   134,   134,   104,    45,    45,    45,    45,   134,
     134,    45,    45,    43,    48,   102,   126,    85,    28,    52,
     103,   130,   134,    48,   134,    57,   134,   104,   105,    82,
      49,    52,   134,   104,    51,   104,    45,    51,    95,   134,
     134,   134,   134,   134,    45,    45,    51,    45,    45,    95,
      43,   104,    50,    95,   134,    45,   134,    43,    52,    45,
      46,    43,    47,    52,    51,    56,    58,   104,   107,   134,
     108,   109,   110,   111,   112,   112,   113,   113,   113,   113,
     114,   114,   115,   115,   115,    52,    53,    52,    53,    45,
      45,   134,   125,    51,    52,    53,    53,    49,    96,   134,
      57,    53,    59,    49,    43,   132,   104,    43,    99,   100,
     104,    45,    57,    45,    45,    45,    45,   134,    45,    95,
      43,   134,    45,    45,    52,   134,    45,    50,    95,    79,
      47,    45,    81,    52,    96,    57,   134,   105,    53,    50,
      81,   134,    79,   106,    49,    99,   103,   103,    49,    53,
     105,    45,    51,    53,   133,    43,    53,    52,    43,    52,
      45,    95,    52,   134,    79,    52,    52,   134,   134,    45,
      10,    45,    52,    51,    57,    59,   106,   134,    52,    52,
      52,    96,    99,   132,    52,   100,    45,    81,    98,    53,
      45,    79,    52,    79,    79,    52,    52,    79,   134,    51,
      52,    81,   125,    81,   134,    52,    55,   100,    79,    79,
      79,    52,    52,    96,    98,   132,    52,   133
    };
  }

/* YYR1[RULE-NUM] -- Symbol kind of the left-hand side of rule RULE-NUM.  */
  private static final short[] yyr1_ = yyr1_init();
  private static final short[] yyr1_init()
  {
    return new short[]
    {
       0,    70,    71,    71,    72,    72,    72,    72,    73,    73,
      73,    73,    73,    74,    74,    74,    74,    74,    75,    75,
      75,    76,    77,    77,    78,    78,    78,    78,    78,    78,
      78,    78,    78,    78,    78,    78,    78,    78,    78,    78,
      78,    78,    78,    78,    78,    78,    79,    79,    80,    81,
      81,    82,    82,    83,    84,    84,    84,    84,    84,    84,
      84,    85,    85,    86,    86,    87,    87,    87,    87,    87,
      87,    87,    88,    88,    88,    88,    89,    90,    91,    92,
      92,    92,    92,    92,    93,    93,    93,    93,    93,    93,
      94,    95,    95,    95,    96,    96,    97,    97,    98,    98,
      99,    99,   100,   100,   100,   100,   101,   101,   101,   101,
     101,   101,   102,   102,   103,   103,   103,   103,   104,   104,
     104,   105,   105,   106,   106,   107,   107,   108,   108,   109,
     109,   110,   110,   111,   111,   111,   112,   112,   112,   112,
     112,   113,   113,   113,   114,   114,   114,   114,   115,   115,
     116,   116,   116,   116,   116,   117,   117,   117,   118,   118,
     118,   119,   119,   119,   119,   120,   120,   121,   121,   121,
     121,   122,   122,   122,   122,   123,   123,   124,   124,   124,
     125,   125,   126,   126,   126,   127,   127,   128,   128,   128,
     129,   129,   129,   129,   129,   129,   129,   130,   130,   131,
     132,   132,   132,   132,   133,   133,   134,   134,   134
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
       1,     0,     1,     2,     3,     4,     4,     4,     4,     4,
       4,     4,     3,     4,     1,     1,     3,     3,     3,     1,
       4,     1,     3,     3,     1,     3,     1,     1,     3,     1,
       3,     1,     3,     1,     3,     3,     1,     3,     3,     3,
       3,     1,     3,     3,     1,     3,     3,     3,     1,     6,
       2,     2,     2,     2,     1,     1,     2,     2,     1,     2,
       2,     1,     1,     1,     1,     3,     1,     1,     1,     1,
       1,     1,     1,     1,     1,     4,     4,     2,     7,     2,
       3,     2,     1,     3,     1,     5,     1,     2,     2,     2,
       2,     4,     5,     5,     7,     7,     8,     5,     5,     6,
       3,     1,     1,     1,     5,     0,     1,     1,     1
    };
  }



  /* YYRLINE[YYN] -- Source line where rule number YYN was defined.  */
  private static final short[] yyrline_ = yyrline_init();
  private static final short[] yyrline_init()
  {
    return new short[]
    {
       0,   145,   145,   146,   150,   151,   155,   156,   162,   163,
     164,   165,   166,   170,   171,   172,   174,   175,   179,   180,
     181,   185,   193,   194,   198,   199,   200,   201,   205,   209,
     213,   217,   220,   223,   226,   229,   230,   231,   232,   233,
     234,   235,   236,   237,   238,   239,   243,   244,   248,   254,
     255,   259,   260,   264,   268,   269,   270,   271,   272,   273,
     274,   278,   279,   285,   286,   292,   294,   296,   298,   300,
     302,   306,   315,   320,   325,   330,   338,   342,   348,   355,
     362,   369,   376,   383,   392,   397,   402,   407,   410,   413,
     419,   426,   427,   430,   436,   437,   442,   445,   451,   452,
     456,   457,   461,   462,   463,   467,   475,   479,   483,   486,
     489,   492,   501,   504,   510,   511,   512,   513,   517,   521,
     522,   526,   527,   531,   534,   538,   541,   545,   546,   552,
     553,   559,   560,   566,   567,   570,   576,   577,   580,   583,
     586,   592,   593,   596,   602,   603,   606,   609,   615,   616,
     620,   623,   626,   627,   628,   632,   633,   634,   638,   639,
     642,   648,   649,   650,   651,   655,   656,   660,   661,   662,
     663,   667,   668,   669,   670,   674,   678,   682,   687,   692,
     699,   700,   708,   709,   710,   714,   715,   721,   722,   723,
     731,   732,   735,   738,   743,   748,   752,   759,   762,   769,
     775,   778,   779,   780,   784,   787,   791,   792,   793
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


  private static final int YYLAST_ = 1319;
  private static final int YYEMPTY_ = -2;
  private static final int YYFINAL_ = 152;
  private static final int YYNTOKENS_ = 70;

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

/* "VondaGrammar.java":3737  */

}
