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
    S_53_(53),                     /* '['  */
    S_54_(54),                     /* ']'  */
    S_55_(55),                     /* '='  */
    S_56_(56),                     /* '<'  */
    S_57_(57),                     /* '>'  */
    S_58_(58),                     /* '|'  */
    S_59_(59),                     /* '^'  */
    S_60_(60),                     /* '&'  */
    S_61_(61),                     /* '+'  */
    S_62_(62),                     /* '-'  */
    S_63_(63),                     /* '/'  */
    S_64_(64),                     /* '%'  */
    S_65_(65),                     /* '!'  */
    S_66_(66),                     /* '~'  */
    S_67_(67),                     /* '?'  */
    S_68_(68),                     /* '#'  */
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
  "'{'", "'}'", "':'", "'('", "')'", "','", "'['", "']'", "'='", "'<'",
  "'>'", "'|'", "'^'", "'&'", "'+'", "'-'", "'/'", "'%'", "'!'", "'~'",
  "'?'", "'#'", "$accept", "root", "imports", "grammar_file",
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


  case 89: /* field_def: '[' type_spec ']' '.' type_spec IDENTIFIER ';'  */
  if (yyn == 89)
    /* "VondaGrammar.y":421  */
                                                   {
    yyval = setPos(new StatFieldDef(null,
           setPos(new StatVarDef(false, ((Type)(yystack.valueAt (2))), (( String )(yystack.valueAt (1)))), (yyloc)), ((Type)(yystack.valueAt (5)))), (yyloc));
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


  case 95: /* method_declaration: '[' type_spec ']' '.' type_spec IDENTIFIER '(' opt_args_list ')' opt_block  */
  if (yyn == 95)
    /* "VondaGrammar.y":444  */
                                                                               {
    yyval = setPos(new StatMethodDeclaration("public", ((Type)(yystack.valueAt (5))), ((Type)(yystack.valueAt (8))), (( String )(yystack.valueAt (4))), ((LinkedList)(yystack.valueAt (2))), ((StatAbstractBlock)(yystack.valueAt (0)))), (yyloc));
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

  private static final short yypact_ninf_ = -371;
  private static final short yytable_ninf_ = -164;

/* YYPACT[STATE-NUM] -- Index in YYTABLE of the portion describing
   STATE-NUM.  */
  private static final short[] yypact_ = yypact_init();
  private static final short[] yypact_init()
  {
    return new short[]
    {
     978,   178,    38,    59,   -16,    91,   140,  1129,     2,   112,
     148,     3,   172,  -371,   212,  -371,  -371,   516,   230,   236,
     245,   118,   159,   122,  -371,  -371,   232,  -371,  1080,  1334,
     247,   206,   252,   978,  -371,     9,  1029,  1029,  -371,  -371,
    -371,  -371,  -371,  -371,  -371,  -371,  -371,  -371,  1029,  1029,
    1029,  -371,   927,   256,   122,   249,  -371,  -371,    43,   260,
     117,  -371,   262,  -371,  -371,  -371,   261,   266,   278,   279,
    -371,  -371,  -371,   303,  -371,    69,   292,    63,   297,    21,
    1369,   172,  -371,   104,   239,  1369,   299,  -371,  1455,  1455,
     210,  -371,  1386,  1472,  1472,  1455,  1455,   122,    -6,   314,
     286,   288,   285,    93,    34,   185,    11,  -371,  -371,  -371,
     264,  -371,   122,   249,   293,  -371,  -371,   293,  -371,   305,
    1369,  1369,  1369,   122,    20,   300,   122,   308,    61,   309,
      39,  -371,  1369,  1369,   312,   313,   345,   476,   650,   743,
     247,  -371,  -371,  -371,  1129,   311,  1386,   316,   113,   306,
    -371,  -371,  -371,  1369,   315,  -371,  -371,   247,  1029,  1029,
    -371,  -371,  -371,  -371,  -371,  -371,  -371,  -371,    82,  -371,
    1369,  1369,  1369,  -371,  -371,  1369,  1369,   318,   319,  -371,
    -371,  -371,  -371,  -371,  -371,  -371,   321,   -15,  -371,   310,
     324,    25,   331,   202,  1369,   861,   332,   325,   273,  -371,
      41,  -371,   335,   327,   152,   171,  -371,  -371,  -371,  -371,
    1369,   258,   328,  -371,  -371,  -371,  -371,  1472,  1369,  1472,
    1472,  1472,  1472,  1472,  1472,  1472,  1472,  1472,  1472,  1472,
    1472,  1472,  1472,  1472,  -371,  -371,  -371,   329,   323,   338,
    -371,   247,  -371,  -371,  -371,   334,  1369,  -371,   122,   346,
     348,  -371,  -371,  -371,  1403,  -371,   342,   344,   350,  -371,
     340,  1144,  -371,   351,   341,  -371,  -371,   353,  -371,   352,
     357,   206,   354,  -371,  -371,  -371,   364,   363,   355,   367,
     368,   371,   373,  -371,  -371,  1369,  -371,  -371,   375,   125,
     378,  1369,   382,   383,  1178,   384,   127,  1129,  -371,    79,
    -371,   385,   386,  1213,  1230,   247,  1472,   314,   381,   286,
     288,   285,    93,    34,    34,   185,   185,   185,   185,    11,
      11,  -371,  -371,  -371,   386,  1369,  1129,   380,   391,  -371,
    -371,  -371,    78,   392,  -371,    -4,  -371,  1438,  1438,  -371,
    -371,   374,   387,   247,  -371,  1472,   247,  -371,   394,   389,
     151,   396,   390,  -371,  -371,  -371,  -371,  -371,  -371,   398,
    -371,   406,   293,   401,  -371,  1369,  1129,   402,  1265,  1369,
     410,   425,   411,  -371,  -371,  -371,   405,   407,   422,   421,
    -371,  1369,  -371,   429,  -371,  1455,  -371,   364,   454,   430,
    -371,  -371,  -371,  1369,  -371,   432,   443,   206,   435,   247,
     217,  -371,  -371,   446,  1129,   444,  -371,  1129,  1129,   447,
     453,  -371,  1129,  -371,  -371,  1369,  -371,   452,  -371,   386,
     432,  -371,  1282,   364,  -371,  -371,   166,   442,  -371,   463,
    -371,  -371,  -371,  -371,  -371,  1129,  -371,  -371,  1129,  1129,
    -371,   455,  1317,  -371,  -371,  -371,  -371,  -371,   364,   206,
     457,  -371,  -371,  -371,  -371,  -371,   458,   459,   394,  -371,
     217,  -371,  -371
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
       0,     0,     0,    16,     3,     0,    16,    16,    23,    35,
      37,    40,    41,    42,    38,    39,    43,    44,    16,    16,
      16,    36,    16,     0,     0,     0,   166,   167,   168,     0,
     169,   171,     0,    55,    57,    58,     0,     0,     0,     0,
      59,    82,    45,     0,    46,   170,     0,   118,     0,     0,
       0,     0,    21,     0,     0,     0,     0,   160,     0,     0,
     163,    53,     0,     0,     0,     0,     0,   170,   179,   123,
     125,   126,   128,   130,   132,   135,   140,   143,   147,   153,
     154,   157,   161,   162,   168,   207,   208,   169,   209,     0,
       0,     0,     0,     0,   163,     0,     0,     0,   163,     0,
       0,   184,     0,     0,     0,     0,     0,     0,     0,     0,
       0,   182,    49,    47,    50,     0,     0,     0,   118,     0,
     203,   204,   202,     0,     0,     1,     2,     0,    16,    16,
      12,    11,    14,    15,     9,    34,    10,   185,     0,   183,
       0,     0,     0,   181,    24,     0,     0,     0,     0,    25,
     180,    56,    78,    79,    80,    81,     0,     0,    86,     0,
       0,     0,     0,   118,     0,     0,     0,     0,     0,     4,
       0,    20,     0,     0,   191,   163,   150,   168,   169,   149,
       0,   163,     0,   151,   152,   155,   156,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,   159,   158,    54,     0,     0,     0,
      27,     0,    31,    26,    30,   188,     0,   189,   187,     0,
       0,    29,    28,    52,   100,   111,     0,   114,   113,   117,
       0,     0,    90,   120,     0,    51,    48,     0,   165,     0,
       0,     0,     0,    13,     8,    87,   100,     0,     0,     0,
       0,     0,     0,    33,    32,     0,    83,    88,     0,   118,
       0,     0,     0,     0,     0,     0,     0,     0,     6,     0,
      22,     0,     0,     0,     0,     0,     0,   122,     0,   124,
     127,   129,   131,   133,   134,   138,   139,   136,   137,   141,
     142,   144,   145,   146,     0,     0,     0,     0,     0,   186,
     106,   105,   163,     0,    99,     0,   112,     0,     0,   176,
      91,     0,    93,     0,   119,     0,     0,   201,   206,     0,
     101,     0,     0,    84,   177,   108,   107,   110,   109,     0,
      85,     0,     0,     0,    71,     0,     0,     0,     0,     0,
       0,    60,     0,     5,    75,   192,     0,     0,     0,     0,
     148,     0,    77,     0,    62,     0,   190,     0,     0,   102,
     116,   115,    92,     0,   121,   147,     0,     0,     0,     0,
       0,    63,    73,     0,     0,     0,    67,     0,     0,     0,
       0,    72,     0,     7,   193,     0,   194,     0,   178,     0,
       0,   103,     0,     0,    94,   164,     0,     0,   200,     0,
      98,    97,    96,    74,    69,     0,    65,    66,     0,     0,
      61,     0,     0,    76,   199,   198,   104,    89,   100,     0,
       0,    68,    64,    70,   195,   196,     0,     0,   206,   197,
       0,   205,    95
    };
  }

/* YYPGOTO[NTERM-NUM].  */
  private static final short[] yypgoto_ = yypgoto_init();
  private static final short[] yypgoto_init()
  {
    return new short[]
    {
    -371,   475,  -371,    36,  -371,  -371,    -5,    60,   199,  -371,
    -283,   377,  -371,  -371,   379,  -371,  -371,  -371,  -371,  -371,
    -371,  -371,   138,  -371,   -46,  -287,   487,    51,  -265,  -370,
    -371,   163,   -18,     0,  -284,  -371,   307,   304,   320,   322,
     339,   100,    46,    96,   -89,   -86,  -371,  -371,  -371,   333,
     388,  -371,  -371,   242,  -371,   451,     6,    -8,  -371,  -371,
    -371,  -371,  -270,    70,   503
    };
  }

/* YYDEFGOTO[NTERM-NUM].  */
  private static final short[] yydefgoto_ = yydefgoto_init();
  private static final short[] yydefgoto_init()
  {
    return new short[]
    {
       0,    32,    33,    34,    35,    36,    83,    72,   143,   144,
      38,   145,    39,    40,    41,    42,    43,   195,    44,    45,
      46,    47,    74,    49,   141,   341,    50,   432,   333,   334,
      51,    97,   256,    76,   264,    98,    99,   100,   101,   102,
     103,   104,   105,   106,   107,   108,   109,   110,   111,   112,
     113,    56,    57,   114,   115,   116,   117,   167,   248,   118,
     257,    61,   154,   398,   147
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
      53,   348,   206,   209,   213,   214,    60,    84,    78,   215,
     216,   351,   173,    60,   180,   131,   376,   421,    81,   374,
       8,   379,    66,   217,    67,    68,    69,   127,   129,   275,
     149,   190,   192,    53,    60,    53,    53,    53,   389,    60,
     139,   382,    60,    60,    77,    82,   169,   306,    53,    53,
      53,   148,    53,   446,    60,    60,    60,   231,    60,   394,
      37,   218,   157,   193,   240,   194,   225,   226,   173,   287,
     137,   180,   160,   161,   232,   233,   198,   171,   172,   196,
     139,   245,    64,   300,   162,   163,   164,   301,   166,   246,
     227,   228,   212,    37,   208,   208,    37,    37,   139,   208,
     208,   208,   208,    65,   169,   243,   424,   188,    37,    37,
      37,   137,    37,   165,   130,   131,   189,   431,   139,   140,
    -118,   300,   277,   223,   224,   372,   275,   427,   137,  -101,
     387,   138,   276,   139,   140,    70,   443,   139,    48,   444,
     263,   277,   321,   322,   323,   288,   267,   292,   199,   200,
      60,   175,   176,   177,   178,   456,   123,   272,    53,    53,
     124,   179,    79,    52,    60,    60,   189,   130,   125,   140,
      75,    48,   139,   158,    48,    48,   369,   431,   189,   458,
     139,   140,   139,   457,   126,   126,    48,    48,    48,    71,
      48,    75,   290,  -118,   273,   274,    52,   123,    80,    52,
      52,   128,   303,   387,   189,   304,    73,   140,   305,   125,
     447,    52,    52,    52,    82,    52,   448,   380,    37,    37,
      62,   137,    63,   208,   210,   208,   208,   208,   208,   208,
     208,   208,   208,   208,   208,   208,   208,   208,   208,   208,
     329,   327,    58,   361,   150,   151,   229,   230,   152,    58,
     370,   291,   155,   153,   335,   189,   380,   139,   140,   395,
     137,   430,    85,   210,    28,   139,   132,   133,   134,   135,
      58,   315,   316,   317,   318,    58,   352,  -163,    58,    58,
     120,   136,   137,   201,   202,   138,   121,   139,   140,   148,
      58,    58,    58,   247,    58,   122,    48,    48,   168,   420,
     234,   235,   170,    60,   174,   263,   181,    75,   137,  -118,
     182,   138,   208,   139,   140,   183,   403,   298,   299,   390,
     391,    52,    52,   313,   314,   319,   320,   184,   185,   186,
     207,   207,    60,    54,   187,   207,   207,   207,   207,   191,
      54,   204,   219,   263,   220,   222,   396,   221,   139,   236,
     241,   208,   242,   244,    54,    54,   251,   252,    10,   266,
     269,    54,   283,   284,   259,   271,    54,   268,   286,    54,
      54,   285,    60,   289,   296,   325,   297,   300,   302,   306,
     324,    54,    54,    54,   137,    54,    58,   352,    55,   326,
     330,   208,   331,   336,   339,    55,   337,   346,   344,   429,
      58,    58,   338,   343,   345,   347,   350,   353,   349,   354,
      60,   355,   356,    60,    60,   357,    55,   358,    60,   360,
     362,    55,   392,   352,    55,    55,   364,   365,   368,   373,
     381,   385,   389,    28,   399,   412,    55,    55,    55,   393,
      55,    60,   386,   388,    60,    60,   397,   400,   352,   401,
     402,    59,   404,   407,   411,   413,   414,   415,    59,   207,
      75,   207,   207,   207,   207,   207,   207,   207,   207,   207,
     207,   207,   207,   207,   207,   207,   416,    54,   417,    59,
     419,   422,   423,   425,    59,   426,   428,    59,    59,    75,
     433,    54,    54,    86,    87,   435,   371,   449,   438,    59,
      59,    59,   442,    59,   439,   450,   454,   448,   156,   459,
     460,   462,    88,    89,    23,   253,    24,    25,    90,    27,
     119,   265,   159,   309,   307,   384,   254,   255,   461,    75,
       0,     0,    55,    86,    87,     0,     0,    93,    94,    58,
     310,    95,    96,   311,    31,     0,    55,    55,   207,     0,
       0,     0,    88,    89,    23,     0,    24,    25,    90,    27,
      91,   312,     0,     0,     0,   406,    92,    75,    58,     0,
      75,    75,     0,     0,     0,    75,     0,    93,    94,     0,
       0,    95,    96,   197,    31,     0,     0,   207,   203,     0,
       0,     0,     0,     0,     0,    59,     0,     0,    75,     0,
       0,    75,    75,   434,     0,     0,   436,   437,    58,    59,
      59,   440,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,   237,   238,   239,     0,   207,     0,     0,
      54,     0,     0,     0,   451,   249,   250,   452,   453,     0,
     258,   260,   262,     0,     0,     0,    58,     0,     0,    58,
      58,     0,     0,     0,    58,     0,   270,     0,     0,    54,
       0,     0,     0,     0,     0,     0,     0,    86,    87,     0,
       0,     0,     0,   278,   279,   280,     0,    58,   281,   282,
      58,    58,     0,     0,     0,    55,    88,    89,    23,     0,
      24,    25,    90,    27,     0,     0,     0,   293,   295,    54,
      92,     0,     0,     0,   259,     0,     0,     0,     0,     0,
       0,    93,    94,   260,    55,    95,    96,     0,    31,     0,
       0,   308,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,    54,     0,     0,
      54,    54,     0,     0,     0,    54,     0,     0,    59,   328,
       0,     0,     0,     0,    55,     0,     0,     0,     0,     0,
      86,    87,     0,     0,   342,     0,     0,     0,    54,     0,
       0,    54,    54,     0,     0,     0,     0,    59,     0,    88,
      89,    23,     0,    24,    25,    90,    27,     0,   359,     0,
     261,     0,    55,    92,   363,    55,    55,   367,     0,     0,
      55,     0,     0,     0,    93,    94,   342,   378,    95,    96,
       0,    31,     0,     0,     0,     0,     0,    59,     0,     0,
       0,     0,     0,    55,     0,     0,    55,    55,   383,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
     258,   258,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,    59,     0,     0,    59,    59,
       0,     0,     0,    59,     0,     0,     0,     0,   405,     0,
       0,   409,   410,     0,     0,     0,     0,     0,    86,    87,
       0,     0,     0,     0,   418,     0,    59,     0,     0,    59,
      59,     0,     0,     0,     0,     0,   342,    88,    89,    23,
       0,    24,    25,    90,    27,   294,     0,     0,     0,     0,
       0,    92,     0,     0,     0,     0,     0,     0,   441,     0,
       0,     0,    93,    94,     0,   445,    95,    96,     0,    31,
       1,     2,     3,     4,     5,     6,     7,     0,     8,     9,
      10,     0,     0,    12,     0,   342,    13,    14,    15,    16,
      17,    18,    19,    20,     0,     0,     0,     0,     0,     0,
       0,     0,     0,    21,    22,    23,     0,    24,    25,    26,
      27,   165,   130,     0,    28,     0,     0,    29,     0,     0,
      30,     1,     2,     3,     4,     5,     6,     7,     0,     8,
       9,    10,    11,     0,    12,    31,     0,    13,    14,    15,
      16,    17,    18,    19,    20,     0,     0,     0,     0,     0,
       0,     0,     0,     0,    21,    22,    23,     0,    24,    25,
      26,    27,     0,     0,     0,    28,     0,     0,    29,     0,
       0,    30,     1,     2,     3,     4,     5,     6,     7,     0,
       8,     9,    10,     0,     0,    12,    31,     0,    13,    14,
      15,    16,    17,    18,    19,    20,     0,     0,     0,     0,
       0,     0,     0,     0,     0,    21,    22,    23,     0,    24,
      25,    26,    27,     0,     0,     0,    28,     0,     0,    29,
       0,     0,    30,     1,     2,     3,     4,     5,     6,     7,
       0,     8,     9,    10,     0,     0,     0,    31,     0,     0,
      14,     0,     0,    17,    18,    19,    20,     0,     0,     0,
       0,     0,     0,     0,     0,     0,    21,    22,    23,     0,
      24,    25,    26,    27,     0,     0,     0,    28,   142,     0,
      29,     0,     1,     2,     3,     4,     5,     6,     7,     0,
       8,     9,    10,     0,     0,     0,     0,     0,    31,    14,
       0,     0,    17,    18,    19,    20,     0,     0,     0,     0,
       0,    86,    87,     0,     0,    21,    22,    23,     0,    24,
      25,    26,    27,     0,     0,     0,    28,     0,     0,    29,
      88,    89,    23,     0,    24,    25,    90,    27,     0,     0,
       0,     0,   340,     0,    92,    86,    87,    31,     0,     0,
       0,     0,     0,     0,     0,    93,    94,     0,     0,    95,
      96,     0,    31,     0,    88,    89,    23,     0,    24,    25,
      90,    27,     0,     0,     0,     0,     0,     0,    92,   366,
      86,    87,     0,     0,     0,     0,     0,     0,     0,    93,
      94,     0,     0,    95,    96,     0,    31,    86,    87,    88,
      89,    23,     0,    24,    25,    90,    27,     0,     0,     0,
       0,     0,     0,    92,   375,     0,    88,    89,    23,     0,
      24,    25,    90,    27,    93,    94,     0,     0,    95,    96,
      92,    31,    86,    87,   377,     0,     0,     0,     0,     0,
       0,    93,    94,     0,     0,    95,    96,     0,    31,    86,
      87,    88,    89,    23,     0,    24,    25,    90,    27,     0,
       0,     0,     0,     0,     0,    92,   408,     0,    88,    89,
      23,     0,    24,    25,    90,    27,    93,    94,     0,    28,
      95,    96,    92,    31,    86,    87,     0,     0,     0,     0,
       0,     0,     0,    93,    94,     0,     0,    95,    96,     0,
      31,    86,    87,    88,    89,    23,     0,    24,    25,    90,
      27,     0,     0,     0,     0,     0,     0,    92,   455,     0,
      88,    89,    23,     0,    24,    25,    90,    27,    93,    94,
       0,     0,    95,    96,   146,    31,    86,    87,     0,     0,
       0,     0,     0,     0,     0,    93,    94,     0,     0,    95,
      96,     0,    31,    86,    87,    88,    89,    23,     0,    24,
      25,    90,    27,     0,     0,     0,     0,     0,     0,    92,
      86,    87,    88,    89,    23,     0,    24,    25,   211,    27,
      93,    94,     0,     0,    95,    96,   146,    31,     0,    88,
      89,    23,     0,    24,    25,   332,    27,    93,    94,     0,
       0,    95,    96,   146,    31,    86,    87,     0,     0,     0,
       0,     0,     0,     0,    93,    94,     0,     0,    95,    96,
       0,    31,     0,    87,    88,    89,    23,     0,    24,    25,
      90,    27,     0,     0,     0,     0,     0,     0,   254,     0,
      87,    88,    89,    23,     0,    24,    25,   205,    27,    93,
      94,     0,     0,    95,    96,    29,    31,     0,    88,    89,
      23,     0,    24,    25,   205,    27,    93,    94,     0,     0,
      95,    96,    92,    31,     0,     0,     0,     0,     0,     0,
       0,     0,     0,    93,    94,     0,     0,    95,    96,     0,
      31
    };
  }

private static final short[] yycheck_ = yycheck_init();
  private static final short[] yycheck_init()
  {
    return new short[]
    {
       0,   271,    88,    89,    93,    94,     0,    12,     8,    95,
      96,   276,    58,     7,    60,    23,   303,   387,    15,   302,
      11,   305,    38,    29,    40,    41,    42,    21,    22,    44,
      30,    77,    11,    33,    28,    35,    36,    37,    42,    33,
      55,   324,    36,    37,    42,    42,    54,    51,    48,    49,
      50,    42,    52,   423,    48,    49,    50,    46,    52,   343,
       0,    67,    53,    42,    44,    44,    32,    33,   114,    44,
      50,   117,    36,    37,    63,    64,    81,    34,    35,    79,
      55,    42,    44,    42,    48,    49,    50,    46,    52,    50,
      56,    57,    92,    33,    88,    89,    36,    37,    55,    93,
      94,    95,    96,    44,   112,    44,   393,    44,    48,    49,
      50,    50,    52,    44,    45,   123,    53,   400,    55,    56,
      42,    42,   168,    30,    31,    46,    44,   397,    50,    51,
      52,    53,    50,    55,    56,    44,   419,    55,     0,   422,
     140,   187,   231,   232,   233,   191,   146,   193,    44,    45,
     144,    34,    35,    36,    37,   442,    38,   157,   158,   159,
      42,    44,    50,     0,   158,   159,    53,    45,    50,    56,
       7,    33,    55,    35,    36,    37,    49,   460,    53,   449,
      55,    56,    55,   448,    21,    22,    48,    49,    50,    49,
      52,    28,   192,    42,   158,   159,    33,    38,    50,    36,
      37,    42,    50,    52,    53,    53,     7,    56,    56,    50,
      44,    48,    49,    50,    42,    52,    50,   306,   158,   159,
      42,    50,    44,   217,    53,   219,   220,   221,   222,   223,
     224,   225,   226,   227,   228,   229,   230,   231,   232,   233,
     248,   241,     0,   289,    38,    39,    61,    62,    42,     7,
     296,    49,     0,    47,   254,    53,   345,    55,    56,   345,
      50,    44,    50,    53,    47,    55,    34,    35,    36,    37,
      28,   225,   226,   227,   228,    33,   276,    45,    36,    37,
      50,    49,    50,    44,    45,    53,    50,    55,    56,    42,
      48,    49,    50,   130,    52,    50,   158,   159,    42,   385,
      36,    37,    53,   297,    44,   305,    44,   144,    50,    51,
      49,    53,   306,    55,    56,    49,   362,    44,    45,   337,
     338,   158,   159,   223,   224,   229,   230,    49,    49,    26,
      88,    89,   326,     0,    42,    93,    94,    95,    96,    42,
       7,    42,    28,   343,    58,    60,   346,    59,    55,    44,
      50,   345,    44,    44,    21,    22,    44,    44,    13,    48,
      54,    28,    44,    44,    54,    50,    33,    51,    44,    36,
      37,    50,   366,    42,    42,    52,    51,    42,    51,    51,
      51,    48,    49,    50,    50,    52,   144,   387,     0,    51,
      44,   385,    44,    51,    54,     7,    52,    45,    57,   399,
     158,   159,    52,    52,    51,    48,    42,    44,    54,    54,
     404,    44,    44,   407,   408,    44,    28,    44,   412,    44,
      42,    33,    48,   423,    36,    37,    44,    44,    44,    44,
      49,    51,    42,    47,    45,    10,    48,    49,    50,    52,
      52,   435,    51,    51,   438,   439,    52,    51,   448,    51,
      44,     0,    51,    51,    44,    44,    51,    50,     7,   217,
     297,   219,   220,   221,   222,   223,   224,   225,   226,   227,
     228,   229,   230,   231,   232,   233,    54,   144,    57,    28,
      51,    27,    52,    51,    33,    42,    51,    36,    37,   326,
      44,   158,   159,    17,    18,    51,   297,    55,    51,    48,
      49,    50,    50,    52,    51,    42,    51,    50,    33,    51,
      51,   460,    36,    37,    38,   136,    40,    41,    42,    43,
      17,   144,    35,   219,   217,   326,    50,    51,   458,   366,
      -1,    -1,   144,    17,    18,    -1,    -1,    61,    62,   297,
     220,    65,    66,   221,    68,    -1,   158,   159,   306,    -1,
      -1,    -1,    36,    37,    38,    -1,    40,    41,    42,    43,
      44,   222,    -1,    -1,    -1,   366,    50,   404,   326,    -1,
     407,   408,    -1,    -1,    -1,   412,    -1,    61,    62,    -1,
      -1,    65,    66,    80,    68,    -1,    -1,   345,    85,    -1,
      -1,    -1,    -1,    -1,    -1,   144,    -1,    -1,   435,    -1,
      -1,   438,   439,   404,    -1,    -1,   407,   408,   366,   158,
     159,   412,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,   120,   121,   122,    -1,   385,    -1,    -1,
     297,    -1,    -1,    -1,   435,   132,   133,   438,   439,    -1,
     137,   138,   139,    -1,    -1,    -1,   404,    -1,    -1,   407,
     408,    -1,    -1,    -1,   412,    -1,   153,    -1,    -1,   326,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    17,    18,    -1,
      -1,    -1,    -1,   170,   171,   172,    -1,   435,   175,   176,
     438,   439,    -1,    -1,    -1,   297,    36,    37,    38,    -1,
      40,    41,    42,    43,    -1,    -1,    -1,   194,   195,   366,
      50,    -1,    -1,    -1,    54,    -1,    -1,    -1,    -1,    -1,
      -1,    61,    62,   210,   326,    65,    66,    -1,    68,    -1,
      -1,   218,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,   404,    -1,    -1,
     407,   408,    -1,    -1,    -1,   412,    -1,    -1,   297,   246,
      -1,    -1,    -1,    -1,   366,    -1,    -1,    -1,    -1,    -1,
      17,    18,    -1,    -1,   261,    -1,    -1,    -1,   435,    -1,
      -1,   438,   439,    -1,    -1,    -1,    -1,   326,    -1,    36,
      37,    38,    -1,    40,    41,    42,    43,    -1,   285,    -1,
      47,    -1,   404,    50,   291,   407,   408,   294,    -1,    -1,
     412,    -1,    -1,    -1,    61,    62,   303,   304,    65,    66,
      -1,    68,    -1,    -1,    -1,    -1,    -1,   366,    -1,    -1,
      -1,    -1,    -1,   435,    -1,    -1,   438,   439,   325,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
     337,   338,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,   404,    -1,    -1,   407,   408,
      -1,    -1,    -1,   412,    -1,    -1,    -1,    -1,   365,    -1,
      -1,   368,   369,    -1,    -1,    -1,    -1,    -1,    17,    18,
      -1,    -1,    -1,    -1,   381,    -1,   435,    -1,    -1,   438,
     439,    -1,    -1,    -1,    -1,    -1,   393,    36,    37,    38,
      -1,    40,    41,    42,    43,    44,    -1,    -1,    -1,    -1,
      -1,    50,    -1,    -1,    -1,    -1,    -1,    -1,   415,    -1,
      -1,    -1,    61,    62,    -1,   422,    65,    66,    -1,    68,
       3,     4,     5,     6,     7,     8,     9,    -1,    11,    12,
      13,    -1,    -1,    16,    -1,   442,    19,    20,    21,    22,
      23,    24,    25,    26,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    36,    37,    38,    -1,    40,    41,    42,
      43,    44,    45,    -1,    47,    -1,    -1,    50,    -1,    -1,
      53,     3,     4,     5,     6,     7,     8,     9,    -1,    11,
      12,    13,    14,    -1,    16,    68,    -1,    19,    20,    21,
      22,    23,    24,    25,    26,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    36,    37,    38,    -1,    40,    41,
      42,    43,    -1,    -1,    -1,    47,    -1,    -1,    50,    -1,
      -1,    53,     3,     4,     5,     6,     7,     8,     9,    -1,
      11,    12,    13,    -1,    -1,    16,    68,    -1,    19,    20,
      21,    22,    23,    24,    25,    26,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    36,    37,    38,    -1,    40,
      41,    42,    43,    -1,    -1,    -1,    47,    -1,    -1,    50,
      -1,    -1,    53,     3,     4,     5,     6,     7,     8,     9,
      -1,    11,    12,    13,    -1,    -1,    -1,    68,    -1,    -1,
      20,    -1,    -1,    23,    24,    25,    26,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    36,    37,    38,    -1,
      40,    41,    42,    43,    -1,    -1,    -1,    47,    48,    -1,
      50,    -1,     3,     4,     5,     6,     7,     8,     9,    -1,
      11,    12,    13,    -1,    -1,    -1,    -1,    -1,    68,    20,
      -1,    -1,    23,    24,    25,    26,    -1,    -1,    -1,    -1,
      -1,    17,    18,    -1,    -1,    36,    37,    38,    -1,    40,
      41,    42,    43,    -1,    -1,    -1,    47,    -1,    -1,    50,
      36,    37,    38,    -1,    40,    41,    42,    43,    -1,    -1,
      -1,    -1,    48,    -1,    50,    17,    18,    68,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    61,    62,    -1,    -1,    65,
      66,    -1,    68,    -1,    36,    37,    38,    -1,    40,    41,
      42,    43,    -1,    -1,    -1,    -1,    -1,    -1,    50,    51,
      17,    18,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    61,
      62,    -1,    -1,    65,    66,    -1,    68,    17,    18,    36,
      37,    38,    -1,    40,    41,    42,    43,    -1,    -1,    -1,
      -1,    -1,    -1,    50,    51,    -1,    36,    37,    38,    -1,
      40,    41,    42,    43,    61,    62,    -1,    -1,    65,    66,
      50,    68,    17,    18,    54,    -1,    -1,    -1,    -1,    -1,
      -1,    61,    62,    -1,    -1,    65,    66,    -1,    68,    17,
      18,    36,    37,    38,    -1,    40,    41,    42,    43,    -1,
      -1,    -1,    -1,    -1,    -1,    50,    51,    -1,    36,    37,
      38,    -1,    40,    41,    42,    43,    61,    62,    -1,    47,
      65,    66,    50,    68,    17,    18,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    61,    62,    -1,    -1,    65,    66,    -1,
      68,    17,    18,    36,    37,    38,    -1,    40,    41,    42,
      43,    -1,    -1,    -1,    -1,    -1,    -1,    50,    51,    -1,
      36,    37,    38,    -1,    40,    41,    42,    43,    61,    62,
      -1,    -1,    65,    66,    50,    68,    17,    18,    -1,    -1,
      -1,    -1,    -1,    -1,    -1,    61,    62,    -1,    -1,    65,
      66,    -1,    68,    17,    18,    36,    37,    38,    -1,    40,
      41,    42,    43,    -1,    -1,    -1,    -1,    -1,    -1,    50,
      17,    18,    36,    37,    38,    -1,    40,    41,    42,    43,
      61,    62,    -1,    -1,    65,    66,    50,    68,    -1,    36,
      37,    38,    -1,    40,    41,    42,    43,    61,    62,    -1,
      -1,    65,    66,    50,    68,    17,    18,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    61,    62,    -1,    -1,    65,    66,
      -1,    68,    -1,    18,    36,    37,    38,    -1,    40,    41,
      42,    43,    -1,    -1,    -1,    -1,    -1,    -1,    50,    -1,
      18,    36,    37,    38,    -1,    40,    41,    42,    43,    61,
      62,    -1,    -1,    65,    66,    50,    68,    -1,    36,    37,
      38,    -1,    40,    41,    42,    43,    61,    62,    -1,    -1,
      65,    66,    50,    68,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    61,    62,    -1,    -1,    65,    66,    -1,
      68
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
      53,    68,    70,    71,    72,    73,    74,    76,    79,    81,
      82,    83,    84,    85,    87,    88,    89,    90,    91,    92,
      95,    99,   100,   102,   118,   119,   120,   121,   122,   124,
     125,   130,    42,    44,    44,    44,    38,    40,    41,    42,
      44,    49,    76,    77,    91,   100,   102,    42,   102,    50,
      50,    15,    42,    75,    75,    50,    17,    18,    36,    37,
      42,    44,    50,    61,    62,    65,    66,   100,   104,   105,
     106,   107,   108,   109,   110,   111,   112,   113,   114,   115,
     116,   117,   118,   119,   122,   123,   124,   125,   128,   133,
      50,    50,    50,    38,    42,    50,   100,   125,    42,   125,
      45,   126,    34,    35,    36,    37,    49,    50,    53,    55,
      56,    93,    48,    77,    78,    80,    50,   133,    42,   102,
      38,    39,    42,    47,   131,     0,    70,    53,    91,    95,
      72,    72,    72,    72,    72,    44,    72,   126,    42,   126,
      53,    34,    35,    93,    44,    34,    35,    36,    37,    44,
      93,    44,    49,    49,    49,    49,    26,    42,    44,    53,
      93,    42,    11,    42,    44,    86,   102,   133,    75,    44,
      45,    44,    45,   133,    42,    42,   114,   122,   125,   114,
      53,    42,   102,   113,   113,   114,   114,    29,    67,    28,
      58,    59,    60,    30,    31,    32,    33,    56,    57,    61,
      62,    46,    63,    64,    36,    37,    44,   133,   133,   133,
      44,    50,    44,    44,    44,    42,    50,   100,   127,   133,
     133,    44,    44,    83,    50,    51,   101,   129,   133,    54,
     133,    47,   133,   102,   103,    80,    48,   102,    51,    54,
     133,    50,   102,    72,    72,    44,    50,    93,   133,   133,
     133,   133,   133,    44,    44,    50,    44,    44,    93,    42,
     102,    49,    93,   133,    44,   133,    42,    51,    44,    45,
      42,    46,    51,    50,    53,    56,    51,   105,   133,   106,
     107,   108,   109,   110,   110,   111,   111,   111,   111,   112,
     112,   113,   113,   113,    51,    52,    51,   102,   133,   126,
      44,    44,    42,    97,    98,   102,    51,    52,    52,    54,
      48,    94,   133,    52,    57,    51,    45,    48,   131,    54,
      42,    97,   102,    44,    54,    44,    44,    44,    44,   133,
      44,    93,    42,   133,    44,    44,    51,   133,    44,    49,
      93,    77,    46,    44,    79,    51,    94,    54,   133,   103,
     113,    49,    79,   133,    77,    51,    51,    52,    51,    42,
     101,   101,    48,    52,   103,   114,   102,    52,   132,    45,
      51,    51,    44,    93,    51,   133,    77,    51,    51,   133,
     133,    44,    10,    44,    51,    50,    54,    57,   133,    51,
     114,    98,    27,    52,    94,    51,    42,   131,    51,   102,
      44,    79,    96,    44,    77,    51,    77,    77,    51,    51,
      77,   133,    50,    79,    79,   133,    98,    44,    50,    55,
      42,    77,    77,    77,    51,    51,    94,    97,   131,    51,
      51,   132,    96
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
       3,     3,     2,     4,     4,     5,     3,     3,     4,     7,
       2,     3,     4,     1,     3,    10,     6,     1,     1,     1,
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
       2,     2,     2,    65,     2,    68,     2,    64,    60,     2,
      50,    51,    46,    61,    52,    62,    45,    63,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,    49,    44,
      56,    55,    57,    67,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,    53,     2,    54,    59,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,    47,    58,    48,    66,     2,     2,     2,
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


  private static final int YYLAST_ = 1540;
  private static final int YYEMPTY_ = -2;
  private static final int YYFINAL_ = 155;
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

/* "VondaGrammar.java":3789  */

}
