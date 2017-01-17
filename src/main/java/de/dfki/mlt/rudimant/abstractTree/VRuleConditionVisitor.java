/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Level;

import org.apache.thrift.TException;
import org.apache.tools.ant.taskdefs.Javadoc.AccessType;

import de.dfki.lt.hfc.db.rdfProxy.RdfClass;
import de.dfki.mlt.rudimant.Mem;
import de.dfki.mlt.rudimant.RudimantCompiler;

/**
 *
 * @author Anna Welker, anna.welker@dfki.de
 */
public class VRuleConditionVisitor extends VNullVisitor {

  private String currentRule;
  // map the new variables to what they represent
  private LinkedHashMap<String, String> realLook;
  // map the new variables to how they should be calculated
  private LinkedHashMap<String, String> compiledLook;
  private int counter;

  private RudimantCompiler rudi;
  private Mem mem;

  public void renewMap(String rule, LinkedHashMap<String, String> condLog,
          LinkedHashMap<String, String> compiledLook, RudimantCompiler rudi) {
    this.realLook = condLog;
    this.compiledLook = compiledLook;
    this.currentRule = rule;
    this.counter = 0;
    this.rudi = rudi;
    this.mem = rudi.getMem();
    this.funcargs = "";
  }

  @Override
  public void visitNode(ExpArithmetic node) {
    node.left.visit(this);
    if (node.right != null) {
      node.right.visit(this);
    }
  }

  private String lastbool;
  private String isTrue = "";
  private String collectElements;

  @Override
  public void visitNode(ExpBoolean node) {

    String n = "";
    if ("!".equals(node.operator)) {
      n = "!";
    }
    String function = "";
    if (node.right != null) {
      if (!(node.operator.equals("||") || node.operator.equals("&&"))) {
//      if (node.left.getType() == null // then this is probably an rdf
//              || !node.left.getType().equals("boolean")) {
        if(node.operator.contains("(")){
          collectElements = node.operator;
          node.left.visit(this);
          collectElements += (", ");
          node.right.visit(this);
          collectElements += (")");
        } else {
          collectElements = n + "(";
          node.left.visit(this);
          collectElements += node.operator + " ";
          node.right.visit(this);
          collectElements += (")");
        }
        this.lastbool = this.currentRule + this.counter++;
        this.compiledLook.put(this.lastbool, collectElements);
        this.realLook.put(lastbool, collectElements.replaceAll("\\\\\"", "\\\""));
        collectElements = null;
        return;
      }

      node.left.visit(this);
      String l = this.lastbool;
      node.right.visit(this);
      String r = this.lastbool;
      this.lastbool = this.currentRule + this.counter++;
      this.compiledLook.put(this.lastbool, n + "("
              + l + node.operator + r + ")");
      this.realLook.put(lastbool, n + "(" + l + node.operator + r + ")");
    } else {
      node.left.visit(this);
      String l = this.lastbool;
      if ("!".equals(node.operator)) {
        this.lastbool = this.currentRule + this.counter++;
        this.compiledLook.put(this.lastbool, n + l);
        this.realLook.put(lastbool, n + l);
      }
    }
    //System.out.println("bool number " + this.compiledLook.keySet().size());
  }

  public void visitDaToken(StringBuilder out, RTExpression exp) {
    if (exp instanceof UVariable) {
      out.append(((UVariable) exp).content);
    } else if (exp instanceof USingleValue
            && ((USingleValue) exp).type.equals("String")) {
      String s = ((USingleValue) exp).content;
//      out.append("\\\"").append(s).append("\\\"");
      out.append(s);
    } else {
      out.append("\" + ");
      whatVDATokenWants = "";
      this.visitNode(exp);
      out.append(whatVDATokenWants).append(" + \"");
      whatVDATokenWants = null;
    }
  }

  /**
   * a dummy to remember what variable is found in the DA; we do not want to
   * directly print this
   */
  private String whatVDATokenWants = null;

  @Override
  public void visitNode(ExpDialogueAct node) {
    StringBuilder out = new StringBuilder();
    out.append("new DialogueAct(\"");
    visitDaToken(out, node.daType);
    out.append('(');
    visitDaToken(out, node.proposition);
    for (int i = 0; i < node.exps.size(); i += 2) {
      out.append(", ");
      visitDaToken(out, node.exps.get(i));
      out.append(" = ");
      visitDaToken(out, node.exps.get(i + 1));
    }
    out.append(')');
    out.append("\")");
    String result = out.toString();
    if (!funcargs.equals("")) {
      this.funcargs += result;
      return;
    } else if (collectElements != null) {
      this.collectElements += result;
      return;
    }
    // normally, this should not be needed here
    this.lastbool = this.currentRule + this.counter++;
    this.compiledLook.put(this.lastbool, result);
    this.realLook.put(lastbool, result);
  }

  /*@Override
   public void visitNode(ExpFuncOnObject node) {
   if (collectElements != null) {
   this.collectElements += node.look + isTrue + " ";
   return;
   }
   this.lastbool = this.currentRule + this.counter++;
   this.compiledLook.put(this.lastbool, node.look + isTrue + " ");
   this.realLook.put(lastbool, node.look + isTrue + " ");
   isTrue = "";
   }*/
  private String fieldAccessPart = null;

  @Override
  public void visitNode(UFieldAccess node) {
    List<String> representation = new ArrayList<>();
    String saved = null;
    if (fieldAccessPart != null) {
      saved = fieldAccessPart;
    }
    fieldAccessPart = "";
    node.parts.get(0).visit(this);
    representation.add(node.representation.get(0));
    for (int i = 1; i < node.parts.size(); i++) {
      if (node.parts.get(i) instanceof UPropertyAccess) {
        UPropertyAccess pa = (UPropertyAccess)node.parts.get(i);
        // then we are in the case that this is actually an rdf operation
        representation.add(node.representation.get(i));
        fieldAccessPart += pa.functional ? ".getSingleValue(\"" : ".getValue(\"";
        fieldAccessPart += node.representation.get(i) + "\") ";
      } else {
        // TODO: EXPLAIN THIS IF
        if (! (node.parts.get(i) instanceof UVariable))
          representation.clear();
        fieldAccessPart += (".");
        node.parts.get(i).visit(this);
      }
    }
    if (collectElements != null) {
      this.collectElements += fieldAccessPart + isTrue;
      fieldAccessPart = saved;
      return;
    } else if (!funcargs.equals("") && !fieldAccessPart.contains(funcargs)) {
      funcargs += fieldAccessPart;
      fieldAccessPart = saved;
      return;
    }
    this.lastbool = this.currentRule + this.counter++;
    this.compiledLook.put(this.lastbool, fieldAccessPart + isTrue);
    this.realLook.put(lastbool, fieldAccessPart + isTrue);
    isTrue = "";
    fieldAccessPart = saved;
  }

  private String funcargs = "";

  @Override
  public void visitNode(UFuncCall node) {
    String m = "";
    if (!funcargs.equals("")) {
      m = funcargs;
      funcargs = "";
    }
    if(node.realOrigin != null) {
      String t = node.realOrigin;
      funcargs = (t.substring(0, 1).toLowerCase() + t.substring(1) + ".");
    } else {
      funcargs = "";
    }
    funcargs += (node.content + "(");
    for (int i = 0; i < node.exps.size(); i++) {
      if (i > 0) {
        funcargs += (", ");
      }
      node.exps.get(i).visit(this);
    }
    funcargs += (")");
    if (fieldAccessPart != null) {
      this.fieldAccessPart += funcargs;
      funcargs = "";
      return;
    }
    if (collectElements != null) {
      this.collectElements += funcargs + isTrue;
      funcargs = "";
      return;
    }
    this.lastbool = this.currentRule + this.counter++;
    this.compiledLook.put(this.lastbool, funcargs + isTrue);
    this.realLook.put(lastbool, funcargs.replaceAll("\\\\\"", "\\\"") + isTrue);
    funcargs = m;
    isTrue = "";
  }

  @Override
  public void visitNode(USingleValue node) {
    if (collectElements != null) {
      this.collectElements += node.content + isTrue;
      return;
    } else if (!funcargs.equals("")) {
      funcargs += node.content + isTrue;
      return;
    }
    this.lastbool = this.currentRule + this.counter++;
    this.compiledLook.put(this.lastbool, node.content + isTrue);
    this.realLook.put(lastbool, node.content + isTrue);
    isTrue = "";
  }

  /* TODO: is this covered correctly in USingleValue?
   @Override
   public void visitNode(UString node) {
   if (collectElements != null) {
   this.collectElements += node.content.substring(0, node.content.length() - 1)
   + "\"" + " " + isTrue;
   return;
   } else if (!funcargs.equals("")) {
   this.funcargs += node.content.substring(0, node.content.length() - 1)
   + "\"" + " " + isTrue;
   return;
   }
   this.lastbool = this.currentRule + this.counter++;
   if (!node.content.contains("\"")) {
   this.compiledLook.put(this.lastbool,
   node.content + " " + isTrue);
   this.realLook.put(lastbool,
   node.content + " " + isTrue);
   return;
   }
   this.compiledLook.put(this.lastbool,
   node.content.substring(0, node.content.length() - 1)
   + "\"" + " " + isTrue);
   this.realLook.put(lastbool,
   node.content.substring(0, node.content.length() - 1)
   + "\"" + " " + isTrue);
   isTrue = "";
   }*/
  @Override
  public void visitNode(UVariable node) {
    String ret = "";
    // we might need to import this variable from elsewhere
    if (node.realOrigin != null) {
      String t = node.realOrigin;
      ret = t.substring(0, 1).toLowerCase() + t.substring(1)
              + "." + node.content;
    } else {
      ret = node.content;
    }
    if (fieldAccessPart != null) {
      this.fieldAccessPart += ret;
      return;
    } else if (!(this.whatVDATokenWants == null)) {
      this.whatVDATokenWants += ret;
      return;
    } else if (collectElements != null) {
      this.collectElements += ret;
      return;
    } else if (!funcargs.equals("")) {
      this.funcargs += ret;
      return;
    }
    // if none of the above happens, we do not want to collect the variable as
    // part of sth else, but give it a proper boolean (however that should happen)

    this.lastbool = this.currentRule + this.counter++;
    this.compiledLook.put(this.lastbool, ret + isTrue);
    this.realLook.put(lastbool, ret + isTrue);
    isTrue = "";
  }

  @Override
  public void visitNode(UWildcard node) {
    if (collectElements != null) {
      this.collectElements += "this.wildcard";
      return;
    } else if (!funcargs.equals("")) {
      this.funcargs += "this.wildcard";
      return;
    }
    this.lastbool = this.currentRule + this.counter++;
    this.compiledLook.put(this.lastbool, "this.wildcard" + " " + isTrue);
    this.realLook.put(lastbool, " _ " + isTrue);
    isTrue = "";
  }
}
