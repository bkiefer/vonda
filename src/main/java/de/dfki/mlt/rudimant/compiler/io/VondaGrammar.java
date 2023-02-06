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
    S_NotJustName(118),            /* NotJustName  */
    S_ComplexPrimary(119),         /* ComplexPrimary  */
    S_ComplexPrimaryNoParenthesis(120), /* ComplexPrimaryNoParenthesis  */
    S_Literal(121),                /* Literal  */
    S_ArrayAccess(122),            /* ArrayAccess  */
    S_ConditionalExpression(123),  /* ConditionalExpression  */
    S_assignment(124),             /* assignment  */
    S_field_access(125),           /* field_access  */
    S_field_access_rest(126),      /* field_access_rest  */
    S_simple_nofa_exp(127),        /* simple_nofa_exp  */
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
  "PostfixExpression", "PrimaryExpression", "NotJustName",
  "ComplexPrimary", "ComplexPrimaryNoParenthesis", "Literal",
  "ArrayAccess", "ConditionalExpression", "assignment", "field_access",
  "field_access_rest", "simple_nofa_exp", "new_exp", "lambda_exp",
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


  case 161: /* PrimaryExpression: NotJustName  */
  if (yyn == 161)
    /* "VondaGrammar.y":651  */
                { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 162: /* PrimaryExpression: ComplexPrimary  */
  if (yyn == 162)
    /* "VondaGrammar.y":652  */
                   { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 163: /* NotJustName: IDENTIFIER  */
  if (yyn == 163)
    /* "VondaGrammar.y":656  */
               { yyval = setPos(new ExpIdentifier((( String )(yystack.valueAt (0)))), (yyloc)); };
  break;


  case 164: /* NotJustName: '(' '(' type_spec ')' UnaryExpression ')'  */
  if (yyn == 164)
    /* "VondaGrammar.y":657  */
                                              { yyval = setPos(new ExpCast(((Type)(yystack.valueAt (3))), ((RTExpression)(yystack.valueAt (1)))), (yyloc)); };
  break;


  case 165: /* ComplexPrimary: '(' exp ')'  */
  if (yyn == 165)
    /* "VondaGrammar.y":661  */
                { yyval = ((RTExpression)(yystack.valueAt (1))); ((RTExpression)(yystack.valueAt (1))).generateParens(); };
  break;


  case 166: /* ComplexPrimary: ComplexPrimaryNoParenthesis  */
  if (yyn == 166)
    /* "VondaGrammar.y":662  */
                                { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 167: /* ComplexPrimaryNoParenthesis: Literal  */
  if (yyn == 167)
    /* "VondaGrammar.y":666  */
            { yyval = ((ExpLiteral)(yystack.valueAt (0))); };
  break;


  case 168: /* ComplexPrimaryNoParenthesis: ArrayAccess  */
  if (yyn == 168)
    /* "VondaGrammar.y":667  */
                { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 169: /* ComplexPrimaryNoParenthesis: field_access  */
  if (yyn == 169)
    /* "VondaGrammar.y":668  */
                 { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 170: /* ComplexPrimaryNoParenthesis: function_call  */
  if (yyn == 170)
    /* "VondaGrammar.y":669  */
                  { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 171: /* ComplexPrimaryNoParenthesis: dialogueact_exp  */
  if (yyn == 171)
    /* "VondaGrammar.y":670  */
                    { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 172: /* Literal: STRING  */
  if (yyn == 172)
    /* "VondaGrammar.y":674  */
           { yyval = setPos((( ExpLiteral )(yystack.valueAt (0))), (yyloc)); };
  break;


  case 173: /* Literal: INT  */
  if (yyn == 173)
    /* "VondaGrammar.y":675  */
        { yyval = setPos((( ExpLiteral )(yystack.valueAt (0))), (yyloc)); };
  break;


  case 174: /* Literal: OTHER_LITERAL  */
  if (yyn == 174)
    /* "VondaGrammar.y":676  */
                  { yyval = setPos((( ExpLiteral )(yystack.valueAt (0))), (yyloc)); };
  break;


  case 175: /* Literal: BOOL_LITERAL  */
  if (yyn == 175)
    /* "VondaGrammar.y":677  */
                 { yyval = setPos((( ExpLiteral )(yystack.valueAt (0))), (yyloc)); };
  break;


  case 176: /* ArrayAccess: IDENTIFIER '[' exp ']'  */
  if (yyn == 176)
    /* "VondaGrammar.y":681  */
                           {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (3)))), yystack.locationAt (3));
    yyval = setPos(new ExpArrayAccess(var, ((RTExpression)(yystack.valueAt (1)))), (yyloc));
  };
  break;


  case 177: /* ArrayAccess: ComplexPrimary '[' exp ']'  */
  if (yyn == 177)
    /* "VondaGrammar.y":685  */
                               { yyval = setPos(new ExpArrayAccess(((RTExpression)(yystack.valueAt (3))), ((RTExpression)(yystack.valueAt (1)))), (yyloc)); };
  break;


  case 178: /* ConditionalExpression: ConditionalOrExpression '?' exp ':' exp  */
  if (yyn == 178)
    /* "VondaGrammar.y":689  */
                                            { yyval = setPos(new ExpConditional(((RTExpression)(yystack.valueAt (4))), ((RTExpression)(yystack.valueAt (2))), ((RTExpression)(yystack.valueAt (0)))), (yyloc)); };
  break;


  case 179: /* ConditionalExpression: ConditionalOrExpression  */
  if (yyn == 179)
    /* "VondaGrammar.y":690  */
                            { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 180: /* assignment: field_access assgn_exp  */
  if (yyn == 180)
    /* "VondaGrammar.y":696  */
                           { yyval = setPos(new ExpAssignment(((RTExpression)(yystack.valueAt (1))), ((RTExpression)(yystack.valueAt (0)))), (yyloc)); };
  break;


  case 181: /* assignment: ArrayAccess assgn_exp  */
  if (yyn == 181)
    /* "VondaGrammar.y":697  */
                          { yyval = setPos(new ExpAssignment(((RTExpression)(yystack.valueAt (1))), ((RTExpression)(yystack.valueAt (0)))), (yyloc)); };
  break;


  case 182: /* assignment: IDENTIFIER assgn_exp  */
  if (yyn == 182)
    /* "VondaGrammar.y":698  */
                         {
    ExpIdentifier var = setPos(new ExpIdentifier((( String )(yystack.valueAt (1)))), yystack.locationAt (1));
    ExpAssignment ass = setPos(new ExpAssignment(var, ((RTExpression)(yystack.valueAt (0)))), (yyloc));
    yyval = ass;
  };
  break;


  case 183: /* field_access: NotJustName field_access_rest  */
  if (yyn == 183)
    /* "VondaGrammar.y":706  */
                                  {
    yyval = setPos(new ExpFieldAccess(((LinkedList<RTExpression>)(yystack.valueAt (0)))), (yyloc)); ((LinkedList<RTExpression>)(yystack.valueAt (0))).addFirst(((RTExpression)(yystack.valueAt (1))));
  };
  break;


  case 184: /* field_access: STRING field_access_rest  */
  if (yyn == 184)
    /* "VondaGrammar.y":709  */
                             {
    yyval = setPos(new ExpFieldAccess(((LinkedList<RTExpression>)(yystack.valueAt (0)))), (yyloc)); ((LinkedList<RTExpression>)(yystack.valueAt (0))).addFirst(setPos((( ExpLiteral )(yystack.valueAt (1))), (yyloc)));
  };
  break;


  case 185: /* field_access: function_call field_access_rest  */
  if (yyn == 185)
    /* "VondaGrammar.y":712  */
                                    { yyval = setPos(new ExpFieldAccess(((LinkedList<RTExpression>)(yystack.valueAt (0)))), (yyloc)); ((LinkedList<RTExpression>)(yystack.valueAt (0))).addFirst(((RTExpression)(yystack.valueAt (1)))); };
  break;


  case 186: /* field_access_rest: '.' simple_nofa_exp field_access_rest  */
  if (yyn == 186)
    /* "VondaGrammar.y":716  */
                                          { yyval = ((LinkedList<RTExpression>)(yystack.valueAt (0))); ((LinkedList<RTExpression>)(yystack.valueAt (0))).addFirst(((RTExpression)(yystack.valueAt (1)))); };
  break;


  case 187: /* field_access_rest: '.' simple_nofa_exp  */
  if (yyn == 187)
    /* "VondaGrammar.y":717  */
                        { yyval = new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (0)))); }}; };
  break;


  case 188: /* simple_nofa_exp: IDENTIFIER  */
  if (yyn == 188)
    /* "VondaGrammar.y":721  */
               { yyval = setPos(new ExpIdentifier((( String )(yystack.valueAt (0)))), (yyloc)); };
  break;


  case 189: /* simple_nofa_exp: function_call  */
  if (yyn == 189)
    /* "VondaGrammar.y":722  */
                  { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 190: /* simple_nofa_exp: '(' exp ')'  */
  if (yyn == 190)
    /* "VondaGrammar.y":723  */
                { yyval = ((RTExpression)(yystack.valueAt (1))); };
  break;


  case 191: /* new_exp: NEW IDENTIFIER  */
  if (yyn == 191)
    /* "VondaGrammar.y":727  */
                   { yyval = setPos(new ExpNew(new Type((( String )(yystack.valueAt (0))))), (yyloc)); };
  break;


  case 192: /* new_exp: NEW IDENTIFIER '(' ')'  */
  if (yyn == 192)
    /* "VondaGrammar.y":728  */
                           {
    yyval = setPos(new ExpNew(new Type((( String )(yystack.valueAt (2)))), new LinkedList<>()), (yyloc));
  };
  break;


  case 193: /* new_exp: NEW IDENTIFIER '(' nonempty_exp_list ')'  */
  if (yyn == 193)
    /* "VondaGrammar.y":731  */
                                             {
    yyval = setPos(new ExpNew(new Type((( String )(yystack.valueAt (3)))), ((LinkedList<RTExpression>)(yystack.valueAt (1)))), (yyloc));
  };
  break;


  case 194: /* new_exp: NEW IDENTIFIER '[' exp ']'  */
  if (yyn == 194)
    /* "VondaGrammar.y":734  */
                               {
    List<Type> sub = new ArrayList<Type>() {{ add(new Type((( String )(yystack.valueAt (3))))); }};
    yyval = setPos(new ExpNew(new Type("Array", sub),
                    new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (1)))); }}), (yyloc));
  };
  break;


  case 195: /* new_exp: NEW IDENTIFIER '[' ']' '(' exp ')'  */
  if (yyn == 195)
    /* "VondaGrammar.y":739  */
                                      {
    List<Type> sub = new ArrayList<Type>() {{ add(new Type((( String )(yystack.valueAt (5))))); }};
    yyval = setPos(new ExpNew(new Type("Array", sub),
                    new LinkedList<RTExpression>(){{ add(((RTExpression)(yystack.valueAt (1)))); }}), (yyloc));
  };
  break;


  case 196: /* new_exp: NEW IDENTIFIER '<' type_spec_list '>' '(' ')'  */
  if (yyn == 196)
    /* "VondaGrammar.y":744  */
                                                  {
    yyval = setPos(new ExpNew(new Type((( String )(yystack.valueAt (5))), ((LinkedList<Type>)(yystack.valueAt (3)))),
                    new LinkedList<>()), (yyloc));
  };
  break;


  case 197: /* new_exp: NEW IDENTIFIER '<' type_spec_list '>' '(' nonempty_exp_list ')'  */
  if (yyn == 197)
    /* "VondaGrammar.y":748  */
                                                                    {
    yyval = setPos(new ExpNew(new Type((( String )(yystack.valueAt (6))), ((LinkedList<Type>)(yystack.valueAt (4)))),
                    ((LinkedList<RTExpression>)(yystack.valueAt (1)))), (yyloc));
  };
  break;


  case 198: /* lambda_exp: '(' opt_args_list ')' ARROW exp  */
  if (yyn == 198)
    /* "VondaGrammar.y":755  */
                                    {
    yyval = setPos(new ExpLambda(((LinkedList)(yystack.valueAt (3))), ((RTExpression)(yystack.valueAt (0)))), (yyloc));
  };
  break;


  case 199: /* lambda_exp: '(' opt_args_list ')' ARROW block  */
  if (yyn == 199)
    /* "VondaGrammar.y":758  */
                                      {
    yyval = setPos(new ExpLambda(((LinkedList)(yystack.valueAt (3))), ((StatAbstractBlock)(yystack.valueAt (0)))), (yyloc));
  };
  break;


  case 200: /* dialogueact_exp: '#' da_token '(' da_token da_args ')'  */
  if (yyn == 200)
    /* "VondaGrammar.y":765  */
                                          {
    yyval = setPos(new ExpDialogueAct(((RTExpression)(yystack.valueAt (4))), ((RTExpression)(yystack.valueAt (2))), ((LinkedList<RTExpression>)(yystack.valueAt (1)))), (yyloc));
  };
  break;


  case 201: /* da_token: '{' exp '}'  */
  if (yyn == 201)
    /* "VondaGrammar.y":771  */
                { yyval = ((RTExpression)(yystack.valueAt (1))); };
  break;


  case 202: /* da_token: IDENTIFIER  */
  if (yyn == 202)
    /* "VondaGrammar.y":774  */
               { yyval = setPos(new ExpLiteral((( String )(yystack.valueAt (0))), "String"), (yyloc)); };
  break;


  case 203: /* da_token: STRING  */
  if (yyn == 203)
    /* "VondaGrammar.y":775  */
           { yyval = setPos((( ExpLiteral )(yystack.valueAt (0))), (yyloc)); };
  break;


  case 204: /* da_token: WILDCARD  */
  if (yyn == 204)
    /* "VondaGrammar.y":776  */
             { yyval = setPos(new ExpLiteral((( String )(yystack.valueAt (0))), "String"), (yyloc)); };
  break;


  case 205: /* da_args: ',' da_token '=' da_token da_args  */
  if (yyn == 205)
    /* "VondaGrammar.y":780  */
                                       {
    yyval = ((LinkedList<RTExpression>)(yystack.valueAt (0))); ((LinkedList<RTExpression>)(yystack.valueAt (0))).addFirst(((RTExpression)(yystack.valueAt (1)))); ((LinkedList<RTExpression>)(yystack.valueAt (0))).addFirst(((RTExpression)(yystack.valueAt (3))));
  };
  break;


  case 206: /* da_args: %empty  */
  if (yyn == 206)
    /* "VondaGrammar.y":783  */
           { yyval = new LinkedList<RTExpression>(); };
  break;


  case 207: /* exp: ConditionalExpression  */
  if (yyn == 207)
    /* "VondaGrammar.y":787  */
                          { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 208: /* exp: assignment  */
  if (yyn == 208)
    /* "VondaGrammar.y":788  */
               { yyval = ((RTExpression)(yystack.valueAt (0))); };
  break;


  case 209: /* exp: new_exp  */
  if (yyn == 209)
    /* "VondaGrammar.y":789  */
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

  private static final short yypact_ninf_ = -383;
  private static final short yytable_ninf_ = -203;

/* YYPACT[STATE-NUM] -- Index in YYTABLE of the portion describing
   STATE-NUM.  */
  private static final short[] yypact_ = yypact_init();
  private static final short[] yypact_init()
  {
    return new short[]
    {
     905,   119,   -18,    -7,   255,    13,    29,  1094,    40,    42,
      52,    -4,    63,  -383,    67,  -383,  -383,   756,    81,    97,
     105,    26,    96,   128,  -383,  -383,   287,  -383,  1024,  1473,
     127,   193,   905,  -383,     3,   973,   973,  -383,  -383,  -383,
    -383,  -383,  -383,  -383,  -383,  -383,  -383,   973,   973,   973,
    -383,   484,   164,   128,   158,  -383,  -383,    36,   214,    91,
    -383,   228,  -383,  -383,  -383,   188,   230,   238,   256,  -383,
    -383,   172,  -383,   285,  -383,   304,   271,    66,   277,    16,
    1507,    63,  -383,   332,   334,  1507,   284,  -383,   717,   717,
     162,  -383,  1541,   799,   799,   717,   717,   128,   -24,   301,
     275,   280,   282,   350,    51,   320,   -23,  -383,  -383,  -383,
     351,  -383,   128,   158,   293,  -383,  -383,   293,  -383,   309,
    1507,  1507,  1507,   128,    30,   305,   128,   314,    86,   322,
     -30,  -383,  1507,  1507,   330,   340,   349,   836,  1121,  1155,
     356,  -383,  -383,  -383,  1094,   354,  1541,   352,  -383,  -383,
     145,  1507,   356,   355,  -383,  -383,   200,   356,   973,   973,
    -383,  -383,  -383,  -383,  -383,  -383,  -383,  -383,    37,  -383,
    1507,  1507,  1507,  -383,  -383,  1507,  1507,   363,   364,  -383,
    -383,  -383,  -383,  -383,  -383,  -383,  -383,   365,   -25,  -383,
     357,   372,   -20,   375,   263,  1507,  1189,   376,   368,   345,
    -383,    58,  -383,   378,   370,   206,   134,  -383,  -383,  -383,
    -383,  1507,   306,   371,  -383,  -383,  -383,  -383,   799,  1507,
     799,   799,   799,   799,   799,   799,   799,   799,   799,   799,
     799,   799,   799,   799,   799,  -383,  -383,  -383,   374,   377,
     380,  -383,   356,  -383,  -383,  -383,   373,  1507,  -383,   128,
     382,   389,  -383,  -383,  -383,  1575,  -383,   383,   385,   391,
    1223,  -383,  -383,   379,   392,   387,  -383,  -383,   398,  -383,
     402,   409,   172,   356,  -383,  -383,  -383,   410,   411,   397,
     412,   416,   419,   420,  -383,  -383,  1507,  -383,  -383,   421,
     318,   424,  1507,   423,   425,  1257,   426,   279,  1094,  -383,
     222,  -383,   429,   427,  1294,  1331,   356,   799,   301,   430,
     275,   280,   282,   350,    51,    51,   320,   320,   320,   320,
     -23,   -23,  -383,  -383,  -383,   427,  1507,  1094,   431,   432,
    -383,  -383,  -383,   223,   433,  -383,   -11,  -383,  1609,  1609,
    -383,   428,   434,  -383,   356,  -383,   799,  -383,    99,   446,
     436,    20,   450,   438,  -383,  -383,  -383,  -383,  -383,  -383,
     451,  -383,   437,   293,   460,  -383,  1507,  1094,   461,  1365,
    1507,   441,   465,   469,  -383,  -383,  -383,   463,   466,   459,
     472,  -383,  1507,  -383,   467,  -383,   717,  -383,   410,   492,
     471,  -383,  -383,  -383,  1507,  -383,   481,  -383,   410,   172,
     482,   485,    71,  -383,  -383,   496,  1094,   493,  -383,  1094,
    1094,   494,   497,  -383,  1094,  -383,  -383,  1507,  -383,   486,
    -383,   427,   481,  -383,  1402,   410,  -383,  -383,   498,   489,
    -383,  -383,  -383,  -383,  -383,  -383,  1094,  -383,  -383,  1094,
    1094,  -383,   499,  1436,  -383,  -383,  -383,  -383,    71,   172,
    -383,  -383,  -383,  -383,  -383,   501,  -383,   446,  -383,  -383
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
       0,     0,     0,   172,   173,   175,   118,   174,     0,     0,
       0,     0,    16,     3,     0,    16,    16,    23,    35,    37,
      40,    41,    42,    38,    39,    43,    44,    16,    16,    16,
      36,    16,     0,     0,     0,   166,   167,   168,     0,   169,
     171,     0,    55,    57,    58,     0,     0,     0,     0,    59,
      82,     0,    45,     0,    46,   170,     0,   118,     0,     0,
       0,     0,    21,     0,     0,     0,     0,   160,     0,     0,
     163,    53,     0,     0,     0,     0,     0,   170,   179,   123,
     125,   126,   128,   130,   132,   135,   140,   143,   147,   153,
     154,   157,   161,   162,   168,   207,   208,   169,   209,     0,
       0,     0,     0,     0,   163,     0,     0,     0,   163,     0,
       0,   184,     0,     0,     0,     0,     0,     0,     0,     0,
       0,   182,    49,    47,    50,     0,     0,     0,   203,   204,
     118,     0,     0,     0,     1,     2,   118,     0,    16,    16,
      12,    11,    14,    15,     9,    34,    10,   185,     0,   183,
       0,     0,     0,   181,    24,     0,     0,     0,     0,    25,
     180,    56,    78,    79,    80,    81,   202,     0,     0,    86,
       0,     0,     0,     0,   118,     0,     0,     0,     0,     0,
       4,     0,    20,     0,     0,   191,   163,   150,   168,   169,
     149,     0,   163,     0,   151,   152,   155,   156,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,   159,   158,    54,     0,     0,
       0,    27,     0,    31,    26,    30,   188,     0,   189,   187,
       0,     0,    29,    28,    52,   100,   111,     0,   114,   113,
       0,    90,   117,     0,   120,     0,    51,    48,     0,   165,
       0,     0,     0,     0,    13,     8,    87,   100,     0,     0,
       0,     0,     0,     0,    33,    32,     0,    83,    88,     0,
     118,     0,     0,     0,     0,     0,     0,     0,     0,     6,
       0,    22,     0,     0,     0,     0,     0,     0,   122,     0,
     124,   127,   129,   131,   133,   134,   138,   139,   136,   137,
     141,   142,   144,   145,   146,     0,     0,     0,     0,     0,
     186,   106,   105,   163,     0,    99,     0,   112,     0,     0,
      91,     0,    93,   176,     0,   119,     0,   201,     0,   206,
       0,   101,     0,     0,    84,   177,   108,   107,   110,   109,
       0,    85,     0,     0,     0,    71,     0,     0,     0,     0,
       0,     0,    60,     0,     5,    75,   192,     0,     0,     0,
       0,   148,     0,    77,     0,    62,     0,   190,     0,     0,
     102,   116,   115,    92,     0,   121,   147,    89,   100,     0,
       0,     0,     0,    63,    73,     0,     0,     0,    67,     0,
       0,     0,     0,    72,     0,     7,   193,     0,   194,     0,
     178,     0,     0,   103,     0,     0,    94,   164,     0,     0,
     200,    98,    97,    96,    74,    69,     0,    65,    66,     0,
       0,    61,     0,     0,    76,   199,   198,   104,     0,     0,
      68,    64,    70,   195,   196,     0,    95,   206,   197,   205
    };
  }

/* YYPGOTO[NTERM-NUM].  */
  private static final short[] yypgoto_ = yypgoto_init();
  private static final short[] yypgoto_init()
  {
    return new short[]
    {
    -383,   515,  -383,    50,  -383,  -383,    -8,   156,    32,  -383,
    -273,   414,  -383,  -383,   417,  -383,  -383,  -383,  -383,  -383,
    -383,  -383,   235,  -383,     2,  -291,   520,   107,  -274,  -382,
    -383,   132,    53,   698,  -289,  -383,   343,   346,   342,   347,
     344,   171,   142,   170,   -92,   -80,  -383,  -383,  -383,   150,
     253,  -383,  -383,    18,  -383,   303,     0,    10,  -383,  -383,
    -383,  -383,  -262,   108,   477
    };
  }

/* YYDEFGOTO[NTERM-NUM].  */
  private static final short[] yydefgoto_ = yydefgoto_init();
  private static final short[] yydefgoto_init()
  {
    return new short[]
    {
       0,    31,    32,    33,    34,    35,    83,    72,   143,   144,
      37,   145,    38,    39,    40,    41,    42,   196,    43,    44,
      45,    46,    74,    48,   141,   341,    49,   433,   334,   335,
      50,    97,   257,    76,   265,    98,    99,   100,   101,   102,
     103,   104,   105,   106,   107,   108,   109,   110,   111,   112,
     113,    55,    56,   114,   115,   116,   117,   167,   249,   118,
     258,    60,   153,   400,   147
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
      59,   214,   215,   352,    84,   218,   423,    59,   207,   210,
     349,    81,   246,   377,     8,   216,   217,   380,    57,   276,
     247,   127,   129,   232,   288,    57,    63,   193,    59,   138,
     375,   390,    59,   131,   138,    59,    59,    64,    82,    73,
     307,   233,   234,   447,   219,   156,    57,    59,    59,    59,
      57,    59,   383,    57,    57,   395,   157,    69,   194,   173,
     195,   180,  -118,   169,   123,    57,    57,    57,   124,    57,
     171,   172,   388,   199,   241,   190,   125,   140,    70,   191,
     137,   276,    77,   226,   227,   160,   161,   277,   209,   209,
     138,   138,    79,   209,   209,   209,   209,   162,   163,   164,
     301,   166,    80,   426,   302,    82,   208,   208,   228,   229,
     189,   208,   208,   208,   208,   431,   173,    85,    28,   180,
     138,   190,   169,   140,   428,   175,   176,   177,   178,   432,
     244,   120,    51,   131,   123,   179,   137,   429,   128,    75,
     322,   323,   324,   397,    59,   138,   125,   121,   444,   398,
      53,   445,   455,   126,   126,   122,    36,    53,    59,    59,
      75,    61,    57,    62,    51,   148,   149,    51,    51,   150,
     278,    53,    53,   130,   151,   432,    57,    57,    53,    51,
      51,    51,    53,    51,   137,    53,    53,   457,    36,   211,
     278,    36,    36,   154,   289,  -202,   293,    53,    53,    53,
     190,    53,   140,    36,    36,    36,   168,    36,   274,   275,
     148,   149,   137,   170,   186,   381,   138,   211,   209,   151,
     209,   209,   209,   209,   209,   209,   209,   209,   209,   209,
     209,   209,   209,   209,   209,    47,   208,   182,   208,   208,
     208,   208,   208,   208,   208,   208,   208,   208,   208,   208,
     208,   208,   208,    54,   381,   190,   304,   140,   174,   330,
      54,   305,   248,   306,   301,  -118,   396,    47,   373,   158,
      47,    47,   181,   137,  -101,   388,    75,   138,   139,   183,
     140,    54,    47,    47,    47,    54,    47,   184,    54,    54,
      51,    51,   362,    65,    53,    66,    67,    68,    59,   371,
      54,    54,    54,    58,    54,   185,   422,   209,    53,    53,
      58,   187,   292,   188,    36,    36,    57,   138,   190,   192,
     140,   132,   133,   134,   135,   208,   205,    59,   370,   220,
     372,    58,  -163,   138,   221,    58,   136,   137,    58,    58,
     222,   138,   139,   223,   140,    57,   209,   138,   165,   130,
      58,    58,    58,   237,    58,   242,   137,  -118,   243,   385,
     138,   139,    10,   140,   208,   405,   245,    59,   316,   317,
     318,   319,   138,   190,   252,   140,   200,   201,   202,   203,
     224,   225,   230,   231,   253,    57,   209,   235,   236,   299,
     300,   391,   392,    47,    47,   314,   315,    54,   156,   408,
     320,   321,   267,   269,   208,   272,    59,   284,   285,    59,
      59,    54,    54,   262,    59,   286,   287,   290,   297,   298,
     301,   303,   307,   137,    57,   325,   331,    57,    57,   326,
      75,   327,    57,   332,   337,   343,    59,   338,   435,    59,
      59,   437,   438,   339,   344,   345,   441,    58,    53,   346,
     347,   348,   351,   355,    57,   354,   356,    57,    57,    75,
     357,    58,    58,   358,   359,   361,   363,   365,   450,   366,
     369,   451,   452,   374,    28,   414,   393,    53,   401,   382,
     390,   404,   386,   387,   389,   413,   394,     1,     2,     3,
       4,     5,     6,     7,   119,     8,     9,    10,   399,    75,
      12,   402,   403,    13,    14,    15,    16,    17,    18,    19,
      20,   406,   409,   415,   416,   418,   417,    53,   421,   424,
      21,    22,    23,   425,    24,    25,    26,    27,   165,   130,
     419,    28,   427,   430,    29,   398,   443,    30,    75,  -170,
     434,    75,    75,   449,   436,   439,    75,   155,   440,   448,
     453,    54,   458,   254,   159,   456,    53,   198,   266,    53,
      53,   308,   204,   311,    53,   459,   310,   313,    75,   312,
       0,    75,    75,     0,     0,     0,     0,     0,     0,     0,
      54,     0,     0,     0,     0,     0,    53,     0,     0,    53,
      53,     0,     0,     0,     0,     0,     0,   238,   239,   240,
       0,    58,     0,     0,     0,     0,     0,     0,     0,   250,
     251,     0,     0,     0,   259,   261,   263,     0,     0,     0,
      54,     0,     0,     0,     0,     0,     0,     0,   270,     0,
      58,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,   279,   280,   281,
       0,     0,   282,   283,     0,     0,     0,     0,     0,    54,
       0,     0,    54,    54,     0,     0,     0,    54,     0,     0,
      58,     0,   294,   296,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,   263,    54,
       0,     0,    54,    54,     0,     0,   309,     0,    52,     0,
       0,     0,     0,     0,     0,     0,    78,     0,     0,    58,
       0,     0,    58,    58,     0,     0,     0,    58,     0,     0,
       0,     0,     0,     0,   329,     0,     0,     0,   152,     0,
      52,     0,    52,    52,    52,    87,     0,   342,     0,    58,
       0,     0,    58,    58,     0,    52,    52,    52,     0,    52,
       0,     0,     0,    88,    89,    23,     0,    24,    25,   206,
      27,     0,     0,   360,     0,     0,     0,    29,     0,   364,
      71,     0,   368,    86,    87,     0,     0,   197,     0,    93,
      94,   342,   379,    95,    96,     0,     0,     0,     0,     0,
     213,     0,    88,    89,    23,     0,    24,    25,    90,    27,
      91,     0,     0,   384,     0,     0,    92,     0,     0,    71,
       0,     0,     0,     0,     0,   259,   259,    87,    93,    94,
       0,     0,    95,    96,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,    88,    89,    23,   264,    24,
      25,   206,    27,   407,   268,     0,   411,   412,     0,    92,
     271,     0,    71,    86,    87,   273,    52,    52,     0,   420,
       0,    93,    94,     0,     0,    95,    96,     0,     0,     0,
       0,   342,    88,    89,    23,     0,    24,    25,    90,    27,
       0,     0,     0,     0,     0,     0,   255,   256,     0,    71,
       0,   291,     0,     0,   442,     0,     0,     0,    93,    94,
       0,   446,    95,    96,     0,     0,     0,     0,     1,     2,
       3,     4,     5,     6,     7,     0,     8,     9,    10,    11,
     342,    12,     0,     0,    13,    14,    15,    16,    17,    18,
      19,    20,     0,     0,     0,     0,     0,     0,     0,     0,
     328,    21,    22,    23,     0,    24,    25,    26,    27,     0,
       0,     0,    28,   336,     0,    29,     0,     0,    30,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,   350,     0,     0,     0,   353,     1,     2,     3,     4,
       5,     6,     7,     0,     8,     9,    10,     0,     0,    12,
       0,     0,    13,    14,    15,    16,    17,    18,    19,    20,
       0,     0,     0,     0,   264,     0,     0,     0,     0,    21,
      22,    23,     0,    24,    25,    26,    27,     0,     0,     0,
      28,     0,     0,    29,     0,     0,    30,     1,     2,     3,
       4,     5,     6,     7,     0,     8,     9,    10,     0,     0,
       0,     0,   264,     0,    14,     0,     0,    17,    18,    19,
      20,     0,     0,     0,     0,     0,     0,     0,     0,     0,
      21,    22,    23,     0,    24,    25,    26,    27,     0,     0,
       0,    28,   142,     0,    29,     0,     0,    71,     0,     0,
       0,     0,     0,     0,     0,     0,   353,     0,     0,     0,
       0,     0,     0,     0,     0,     0,   353,     1,     2,     3,
       4,     5,     6,     7,     0,     8,     9,    10,     0,     0,
       0,     0,     0,     0,    14,     0,     0,    17,    18,    19,
      20,     0,     0,   353,     0,     0,     0,     0,     0,     0,
      21,    22,    23,     0,    24,    25,    26,    27,    86,    87,
       0,    28,     0,     0,    29,     0,     0,    71,     0,     0,
       0,     0,     0,     0,     0,     0,     0,    88,    89,    23,
       0,    24,    25,    90,    27,     0,     0,     0,   260,     0,
       0,    92,    86,    87,    71,     0,     0,     0,     0,     0,
       0,     0,     0,    93,    94,     0,     0,    95,    96,     0,
       0,    88,    89,    23,     0,    24,    25,    90,    27,     0,
       0,     0,     0,     0,     0,    92,    86,    87,    71,     0,
       0,   262,     0,     0,     0,     0,     0,    93,    94,     0,
       0,    95,    96,     0,     0,    88,    89,    23,     0,    24,
      25,    90,    27,   295,     0,     0,     0,     0,     0,    92,
      86,    87,    71,     0,     0,     0,     0,     0,     0,     0,
       0,    93,    94,     0,     0,    95,    96,     0,     0,    88,
      89,    23,     0,    24,    25,    90,    27,     0,     0,     0,
       0,   340,     0,    92,    86,    87,    71,     0,     0,     0,
       0,     0,     0,     0,     0,    93,    94,     0,     0,    95,
      96,     0,     0,    88,    89,    23,     0,    24,    25,    90,
      27,     0,     0,     0,     0,     0,     0,    92,   367,     0,
      71,    86,    87,     0,     0,     0,     0,     0,     0,    93,
      94,     0,     0,    95,    96,     0,     0,     0,     0,     0,
      88,    89,    23,     0,    24,    25,    90,    27,     0,     0,
       0,     0,     0,     0,    92,   376,     0,    71,    86,    87,
       0,     0,     0,     0,     0,     0,    93,    94,     0,     0,
      95,    96,     0,     0,     0,     0,     0,    88,    89,    23,
       0,    24,    25,    90,    27,     0,     0,     0,     0,     0,
       0,    92,    86,    87,    71,     0,     0,   378,     0,     0,
       0,     0,     0,    93,    94,     0,     0,    95,    96,     0,
       0,    88,    89,    23,     0,    24,    25,    90,    27,     0,
       0,     0,     0,     0,     0,    92,   410,     0,    71,    86,
      87,     0,     0,     0,     0,     0,     0,    93,    94,     0,
       0,    95,    96,     0,     0,     0,     0,     0,    88,    89,
      23,     0,    24,    25,    90,    27,     0,     0,     0,    28,
       0,     0,    92,    86,    87,    71,     0,     0,     0,     0,
       0,     0,     0,     0,    93,    94,     0,     0,    95,    96,
       0,     0,    88,    89,    23,     0,    24,    25,    90,    27,
       0,     0,     0,     0,     0,     0,    92,   454,     0,    71,
      86,    87,     0,     0,     0,     0,     0,     0,    93,    94,
       0,     0,    95,    96,     0,     0,     0,     0,     0,    88,
      89,    23,     0,    24,    25,    90,    27,     0,     0,     0,
       0,     0,     0,   146,    86,    87,    71,     0,     0,     0,
       0,     0,     0,     0,     0,    93,    94,     0,     0,    95,
      96,     0,     0,    88,    89,    23,     0,    24,    25,    90,
      27,     0,     0,     0,     0,     0,     0,    92,    86,    87,
      71,     0,     0,     0,     0,     0,     0,     0,     0,    93,
      94,     0,     0,    95,    96,     0,     0,    88,    89,    23,
       0,    24,    25,   212,    27,     0,     0,     0,     0,     0,
       0,   146,    86,    87,    71,     0,     0,     0,     0,     0,
       0,     0,     0,    93,    94,     0,     0,    95,    96,     0,
       0,    88,    89,    23,     0,    24,    25,   333,    27,     0,
       0,     0,     0,     0,     0,   146,    86,    87,    71,     0,
       0,     0,     0,     0,     0,     0,     0,    93,    94,     0,
       0,    95,    96,     0,     0,    88,    89,    23,     0,    24,
      25,    90,    27,     0,     0,     0,     0,     0,     0,   255,
       0,     0,    71,     0,     0,     0,     0,     0,     0,     0,
       0,    93,    94,     0,     0,    95,    96
    };
  }

private static final short[] yycheck_ = yycheck_init();
  private static final short[] yycheck_init()
  {
    return new short[]
    {
       0,    93,    94,   277,    12,    29,   388,     7,    88,    89,
     272,    15,    42,   304,    11,    95,    96,   306,     0,    44,
      50,    21,    22,    46,    44,     7,    44,    11,    28,    54,
     303,    42,    32,    23,    54,    35,    36,    44,    42,     7,
      51,    64,    65,   425,    68,    42,    28,    47,    48,    49,
      32,    51,   325,    35,    36,   344,    53,    44,    42,    57,
      44,    59,    42,    53,    38,    47,    48,    49,    42,    51,
      34,    35,    52,    81,    44,    55,    50,    57,    49,    77,
      50,    44,    42,    32,    33,    35,    36,    50,    88,    89,
      54,    54,    50,    93,    94,    95,    96,    47,    48,    49,
      42,    51,    50,   394,    46,    42,    88,    89,    57,    58,
      44,    93,    94,    95,    96,    44,   114,    50,    47,   117,
      54,    55,   112,    57,   398,    34,    35,    36,    37,   402,
      44,    50,     0,   123,    38,    44,    50,   399,    42,     7,
     232,   233,   234,    44,   144,    54,    50,    50,   421,    50,
       0,   424,   443,    21,    22,    50,     0,     7,   158,   159,
      28,    42,   144,    44,    32,    38,    39,    35,    36,    42,
     168,    21,    22,    45,    47,   448,   158,   159,    28,    47,
      48,    49,    32,    51,    50,    35,    36,   449,    32,    55,
     188,    35,    36,     0,   192,    50,   194,    47,    48,    49,
      55,    51,    57,    47,    48,    49,    42,    51,   158,   159,
      38,    39,    50,    55,    42,   307,    54,    55,   218,    47,
     220,   221,   222,   223,   224,   225,   226,   227,   228,   229,
     230,   231,   232,   233,   234,     0,   218,    49,   220,   221,
     222,   223,   224,   225,   226,   227,   228,   229,   230,   231,
     232,   233,   234,     0,   346,    55,    50,    57,    44,   249,
       7,    55,   130,    57,    42,    42,   346,    32,    46,    34,
      35,    36,    44,    50,    51,    52,   144,    54,    55,    49,
      57,    28,    47,    48,    49,    32,    51,    49,    35,    36,
     158,   159,   290,    38,   144,    40,    41,    42,   298,   297,
      47,    48,    49,     0,    51,    49,   386,   307,   158,   159,
       7,    26,    49,    42,   158,   159,   298,    54,    55,    42,
      57,    34,    35,    36,    37,   307,    42,   327,    49,    28,
     298,    28,    45,    54,    59,    32,    49,    50,    35,    36,
      60,    54,    55,    61,    57,   327,   346,    54,    44,    45,
      47,    48,    49,    44,    51,    50,    50,    51,    44,   327,
      54,    55,    13,    57,   346,   363,    44,   367,   226,   227,
     228,   229,    54,    55,    44,    57,    44,    45,    44,    45,
      30,    31,    62,    63,    44,   367,   386,    36,    37,    44,
      45,   338,   339,   158,   159,   224,   225,   144,    42,   367,
     230,   231,    48,    51,   386,    50,   406,    44,    44,   409,
     410,   158,   159,    56,   414,    50,    44,    42,    42,    51,
      42,    51,    51,    50,   406,    51,    44,   409,   410,    52,
     298,    51,   414,    44,    51,    56,   436,    52,   406,   439,
     440,   409,   410,    52,    52,    58,   414,   144,   298,    51,
      48,    42,    42,    56,   436,    44,    44,   439,   440,   327,
      44,   158,   159,    44,    44,    44,    42,    44,   436,    44,
      44,   439,   440,    44,    47,    10,    48,   327,    42,    49,
      42,    44,    51,    51,    51,    44,    52,     3,     4,     5,
       6,     7,     8,     9,    17,    11,    12,    13,    52,   367,
      16,    51,    51,    19,    20,    21,    22,    23,    24,    25,
      26,    51,    51,    44,    51,    56,    50,   367,    51,    27,
      36,    37,    38,    52,    40,    41,    42,    43,    44,    45,
      58,    47,    51,    51,    50,    50,    50,    53,   406,    55,
      44,   409,   410,    54,    51,    51,   414,    32,    51,    51,
      51,   298,    51,   136,    34,   448,   406,    80,   144,   409,
     410,   218,    85,   221,   414,   457,   220,   223,   436,   222,
      -1,   439,   440,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
     327,    -1,    -1,    -1,    -1,    -1,   436,    -1,    -1,   439,
     440,    -1,    -1,    -1,    -1,    -1,    -1,   120,   121,   122,
      -1,   298,    -1,    -1,    -1,    -1,    -1,    -1,    -1,   132,
     133,    -1,    -1,    -1,   137,   138,   139,    -1,    -1,    -1,
     367,    -1,    -1,    -1,    -1,    -1,    -1,    -1,   151,    -1,
     327,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,   170,   171,   172,
      -1,    -1,   175,   176,    -1,    -1,    -1,    -1,    -1,   406,
      -1,    -1,   409,   410,    -1,    -1,    -1,   414,    -1,    -1,
     367,    -1,   195,   196,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,   211,   436,
      -1,    -1,   439,   440,    -1,    -1,   219,    -1,     0,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,     8,    -1,    -1,   406,
      -1,    -1,   409,   410,    -1,    -1,    -1,   414,    -1,    -1,
      -1,    -1,    -1,    -1,   247,    -1,    -1,    -1,    30,    -1,
      32,    -1,    34,    35,    36,    18,    -1,   260,    -1,   436,
      -1,    -1,   439,   440,    -1,    47,    48,    49,    -1,    51,
      -1,    -1,    -1,    36,    37,    38,    -1,    40,    41,    42,
      43,    -1,    -1,   286,    -1,    -1,    -1,    50,    -1,   292,
      53,    -1,   295,    17,    18,    -1,    -1,    79,    -1,    62,
      63,   304,   305,    66,    67,    -1,    -1,    -1,    -1,    -1,
      92,    -1,    36,    37,    38,    -1,    40,    41,    42,    43,
      44,    -1,    -1,   326,    -1,    -1,    50,    -1,    -1,    53,
      -1,    -1,    -1,    -1,    -1,   338,   339,    18,    62,    63,
      -1,    -1,    66,    67,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    36,    37,    38,   140,    40,
      41,    42,    43,   366,   146,    -1,   369,   370,    -1,    50,
     152,    -1,    53,    17,    18,   157,   158,   159,    -1,   382,
      -1,    62,    63,    -1,    -1,    66,    67,    -1,    -1,    -1,
      -1,   394,    36,    37,    38,    -1,    40,    41,    42,    43,
      -1,    -1,    -1,    -1,    -1,    -1,    50,    51,    -1,    53,
      -1,   193,    -1,    -1,   417,    -1,    -1,    -1,    62,    63,
      -1,   424,    66,    67,    -1,    -1,    -1,    -1,     3,     4,
       5,     6,     7,     8,     9,    -1,    11,    12,    13,    14,
     443,    16,    -1,    -1,    19,    20,    21,    22,    23,    24,
      25,    26,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
     242,    36,    37,    38,    -1,    40,    41,    42,    43,    -1,
      -1,    -1,    47,   255,    -1,    50,    -1,    -1,    53,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,   273,    -1,    -1,    -1,   277,     3,     4,     5,     6,
       7,     8,     9,    -1,    11,    12,    13,    -1,    -1,    16,
      -1,    -1,    19,    20,    21,    22,    23,    24,    25,    26,
      -1,    -1,    -1,    -1,   306,    -1,    -1,    -1,    -1,    36,
      37,    38,    -1,    40,    41,    42,    43,    -1,    -1,    -1,
      47,    -1,    -1,    50,    -1,    -1,    53,     3,     4,     5,
       6,     7,     8,     9,    -1,    11,    12,    13,    -1,    -1,
      -1,    -1,   344,    -1,    20,    -1,    -1,    23,    24,    25,
      26,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      36,    37,    38,    -1,    40,    41,    42,    43,    -1,    -1,
      -1,    47,    48,    -1,    50,    -1,    -1,    53,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,   388,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,   398,     3,     4,     5,
       6,     7,     8,     9,    -1,    11,    12,    13,    -1,    -1,
      -1,    -1,    -1,    -1,    20,    -1,    -1,    23,    24,    25,
      26,    -1,    -1,   425,    -1,    -1,    -1,    -1,    -1,    -1,
      36,    37,    38,    -1,    40,    41,    42,    43,    17,    18,
      -1,    47,    -1,    -1,    50,    -1,    -1,    53,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    36,    37,    38,
      -1,    40,    41,    42,    43,    -1,    -1,    -1,    47,    -1,
      -1,    50,    17,    18,    53,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    62,    63,    -1,    -1,    66,    67,    -1,
      -1,    36,    37,    38,    -1,    40,    41,    42,    43,    -1,
      -1,    -1,    -1,    -1,    -1,    50,    17,    18,    53,    -1,
      -1,    56,    -1,    -1,    -1,    -1,    -1,    62,    63,    -1,
      -1,    66,    67,    -1,    -1,    36,    37,    38,    -1,    40,
      41,    42,    43,    44,    -1,    -1,    -1,    -1,    -1,    50,
      17,    18,    53,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    62,    63,    -1,    -1,    66,    67,    -1,    -1,    36,
      37,    38,    -1,    40,    41,    42,    43,    -1,    -1,    -1,
      -1,    48,    -1,    50,    17,    18,    53,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    62,    63,    -1,    -1,    66,
      67,    -1,    -1,    36,    37,    38,    -1,    40,    41,    42,
      43,    -1,    -1,    -1,    -1,    -1,    -1,    50,    51,    -1,
      53,    17,    18,    -1,    -1,    -1,    -1,    -1,    -1,    62,
      63,    -1,    -1,    66,    67,    -1,    -1,    -1,    -1,    -1,
      36,    37,    38,    -1,    40,    41,    42,    43,    -1,    -1,
      -1,    -1,    -1,    -1,    50,    51,    -1,    53,    17,    18,
      -1,    -1,    -1,    -1,    -1,    -1,    62,    63,    -1,    -1,
      66,    67,    -1,    -1,    -1,    -1,    -1,    36,    37,    38,
      -1,    40,    41,    42,    43,    -1,    -1,    -1,    -1,    -1,
      -1,    50,    17,    18,    53,    -1,    -1,    56,    -1,    -1,
      -1,    -1,    -1,    62,    63,    -1,    -1,    66,    67,    -1,
      -1,    36,    37,    38,    -1,    40,    41,    42,    43,    -1,
      -1,    -1,    -1,    -1,    -1,    50,    51,    -1,    53,    17,
      18,    -1,    -1,    -1,    -1,    -1,    -1,    62,    63,    -1,
      -1,    66,    67,    -1,    -1,    -1,    -1,    -1,    36,    37,
      38,    -1,    40,    41,    42,    43,    -1,    -1,    -1,    47,
      -1,    -1,    50,    17,    18,    53,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    62,    63,    -1,    -1,    66,    67,
      -1,    -1,    36,    37,    38,    -1,    40,    41,    42,    43,
      -1,    -1,    -1,    -1,    -1,    -1,    50,    51,    -1,    53,
      17,    18,    -1,    -1,    -1,    -1,    -1,    -1,    62,    63,
      -1,    -1,    66,    67,    -1,    -1,    -1,    -1,    -1,    36,
      37,    38,    -1,    40,    41,    42,    43,    -1,    -1,    -1,
      -1,    -1,    -1,    50,    17,    18,    53,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    62,    63,    -1,    -1,    66,
      67,    -1,    -1,    36,    37,    38,    -1,    40,    41,    42,
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
      -1,    -1,    53,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    62,    63,    -1,    -1,    66,    67
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
      99,   100,   102,   118,   119,   120,   121,   122,   124,   125,
     130,    42,    44,    44,    44,    38,    40,    41,    42,    44,
      49,    53,    76,    77,    91,   100,   102,    42,   102,    50,
      50,    15,    42,    75,    75,    50,    17,    18,    36,    37,
      42,    44,    50,    62,    63,    66,    67,   100,   104,   105,
     106,   107,   108,   109,   110,   111,   112,   113,   114,   115,
     116,   117,   118,   119,   122,   123,   124,   125,   128,   133,
      50,    50,    50,    38,    42,    50,   100,   125,    42,   125,
      45,   126,    34,    35,    36,    37,    49,    50,    54,    55,
      57,    93,    48,    77,    78,    80,    50,   133,    38,    39,
      42,    47,   102,   131,     0,    70,    42,    53,    91,    95,
      72,    72,    72,    72,    72,    44,    72,   126,    42,   126,
      55,    34,    35,    93,    44,    34,    35,    36,    37,    44,
      93,    44,    49,    49,    49,    49,    42,    26,    42,    44,
      55,    93,    42,    11,    42,    44,    86,   102,   133,    75,
      44,    45,    44,    45,   133,    42,    42,   114,   122,   125,
     114,    55,    42,   102,   113,   113,   114,   114,    29,    68,
      28,    59,    60,    61,    30,    31,    32,    33,    57,    58,
      62,    63,    46,    64,    65,    36,    37,    44,   133,   133,
     133,    44,    50,    44,    44,    44,    42,    50,   100,   127,
     133,   133,    44,    44,    83,    50,    51,   101,   129,   133,
      47,   133,    56,   133,   102,   103,    80,    48,   102,    51,
     133,   102,    50,   102,    72,    72,    44,    50,    93,   133,
     133,   133,   133,   133,    44,    44,    50,    44,    44,    93,
      42,   102,    49,    93,   133,    44,   133,    42,    51,    44,
      45,    42,    46,    51,    50,    55,    57,    51,   105,   133,
     106,   107,   108,   109,   110,   110,   111,   111,   111,   111,
     112,   112,   113,   113,   113,    51,    52,    51,   102,   133,
     126,    44,    44,    42,    97,    98,   102,    51,    52,    52,
      48,    94,   133,    56,    52,    58,    51,    48,    42,   131,
     102,    42,    97,   102,    44,    56,    44,    44,    44,    44,
     133,    44,    93,    42,   133,    44,    44,    51,   133,    44,
      49,    93,    77,    46,    44,    79,    51,    94,    56,   133,
     103,   113,    49,    79,   133,    77,    51,    51,    52,    51,
      42,   101,   101,    48,    52,   103,   114,    44,    50,    52,
     132,    42,    51,    51,    44,    93,    51,   133,    77,    51,
      51,   133,   133,    44,    10,    44,    51,    50,    56,    58,
     133,    51,   114,    98,    27,    52,    94,    51,    97,   131,
      51,    44,    79,    96,    44,    77,    51,    77,    77,    51,
      51,    77,   133,    50,    79,    79,   133,    98,    51,    54,
      77,    77,    77,    51,    51,    94,    96,   131,    51,   132
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
     117,   117,   117,   118,   118,   119,   119,   120,   120,   120,
     120,   120,   121,   121,   121,   121,   122,   122,   123,   123,
     124,   124,   124,   125,   125,   125,   126,   126,   127,   127,
     127,   128,   128,   128,   128,   128,   128,   128,   129,   129,
     130,   131,   131,   131,   131,   132,   132,   133,   133,   133
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
       1,     1,     1,     1,     6,     3,     1,     1,     1,     1,
       1,     1,     1,     1,     1,     1,     4,     4,     5,     1,
       2,     2,     2,     2,     2,     2,     3,     2,     1,     1,
       3,     2,     4,     5,     5,     7,     7,     8,     5,     5,
       6,     3,     1,     1,     1,     5,     0,     1,     1,     1
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
     650,   651,   652,   656,   657,   661,   662,   666,   667,   668,
     669,   670,   674,   675,   676,   677,   681,   685,   689,   690,
     696,   697,   698,   706,   709,   712,   716,   717,   721,   722,
     723,   727,   728,   731,   734,   739,   744,   748,   755,   758,
     765,   771,   774,   775,   776,   780,   783,   787,   788,   789
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


  private static final int YYLAST_ = 1676;
  private static final int YYEMPTY_ = -2;
  private static final int YYFINAL_ = 154;
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

/* "VondaGrammar.java":3812  */

}
