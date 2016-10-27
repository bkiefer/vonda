/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

import de.dfki.mlt.rudimant.Mem;
import de.dfki.mlt.rudimant.RudimantCompiler;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Level;
import org.apache.thrift.TException;

/**
 *
 * @author Anna Welker, anna.welker@dfki.de
 */
public class VRuleConditionVisitor implements RudiVisitor {

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
  public void visitNode(RudiTree node) {
    node.visit(this);
  }

  @Override
  public void visitNode(ExpAbstractWrapper node) {
    node.exp.visit(this);
  }

  @Override
  public void visitNode(ExpArithmetic node) {
    node.left.visit(this);
    if (node.right != null) {
      node.right.visit(this);
    }
  }

  @Override
  public void visitNode(ExpAssignment node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  private String lastbool;
  private String isTrue = "";

  private String collectDAs;

  private String collectElements;

  @Override
  public void visitNode(ExpBoolean node) {

    String n = "";
    if (node.not) {
      n = "!";
    }
    String function = "";
    if (node.rdf) {
      function = "RdfClass.isSubclassOf(";
    } else {
      function = "isSubsumed(";
    }
    if (node.isSubsumed) {
      this.lastbool = this.currentRule + this.counter++;
      collectDAs = n;
      if (node.notIfSubsume) {
        collectDAs += "!";
      }
      collectDAs += (function);
      node.left.visit(this);
      collectDAs += (", ");
      node.right.visit(this);
      collectDAs += (")");

      this.compiledLook.put(this.lastbool, collectDAs);
      this.realLook.put(lastbool, collectDAs.replaceAll("\\\\\"", "\\\""));
      collectDAs = null;
      return;
    } else if (node.doesSubsume) {
      this.lastbool = this.currentRule + this.counter++;
      collectDAs = n;
      if (node.notIfSubsume) {
        collectDAs += "!";
      }
      collectDAs += (function);
      node.right.visit(this);
      collectDAs += (", ");
      node.left.visit(this);
      collectDAs += (")");
      this.compiledLook.put(this.lastbool, collectDAs);
      this.realLook.put(lastbool, collectDAs.replaceAll("\\\\\"", "\\\""));
      collectDAs = null;
      return;
    }
    if (node.right != null) {
      if (!(node.operator.equals("||") || node.operator.equals("&&"))) {
//      if (node.left.getType() == null // then this is probably an rdf
//              || !node.left.getType().equals("boolean")) {
        collectElements = n + "(";
        node.left.visit(this);
        collectElements += node.operator + " ";
        node.right.visit(this);
        this.lastbool = this.currentRule + this.counter++;
        this.compiledLook.put(this.lastbool, collectElements + ")");
        this.realLook.put(lastbool, collectElements.replaceAll("\\\\\"", "\\\"") + ")");
        collectElements = null;
        return;
      }

      node.left.visit(this);
      String l = this.lastbool;
      node.right.visit(this);
      String r = this.lastbool;
      this.lastbool = this.currentRule + this.counter++;
      this.compiledLook.put(this.lastbool, n + l + node.operator + r);
      this.realLook.put(lastbool, n + l + node.operator + r);
    } else {
      isTrue = node.isTrue + " ";
      node.left.visit(this);
      String l = this.lastbool;
      if (node.not) {
        this.lastbool = this.currentRule + this.counter++;
        this.compiledLook.put(this.lastbool, n + l);
        this.realLook.put(lastbool, n + l);
      }
    }
    //System.out.println("bool number " + this.compiledLook.keySet().size());
  }

  public void visitDaToken(StringBuilder out, RTExpression exp) {
    if (exp instanceof UVariable) {
      out.append(((UVariable)exp).representation);
    } else if (exp instanceof UString) {
      String s = ((UString)exp).content;
      out.append("\\\"").append(s.substring(1, s.length() -1)).append("\\\"");
    } else {
      out.append("\" + ");
      this.visitNode(exp);
      out.append(" + \"");
    }
  }

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
    out.append("\")");
    String result = out.toString();
    if (collectDAs != null) {
      this.collectDAs += result;
      return;
    } else if (!funcargs.equals("")) {
      this.funcargs += result;
      return;
    } else if (collectElements != null) {
      this.collectElements += result;
      return;
    }
    this.lastbool = this.currentRule + this.counter++;
    this.compiledLook.put(this.lastbool, result);
    this.realLook.put(lastbool, result.replaceAll("\\\\\"", "\\\""));
  }

  @Override
  public void visitNode(ExpFuncOnObject node) {
    if (collectElements != null) {
      this.collectElements += node.look + isTrue + " ";
      return;
    }
    this.lastbool = this.currentRule + this.counter++;
    this.compiledLook.put(this.lastbool, node.look + isTrue + " ");
    this.realLook.put(lastbool, node.look + isTrue + " ");
    isTrue = "";
  }

  @Override
  public void visitNode(ExpIf node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(ExpLambda node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(GrammarFile node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(GrammarRule node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(StatAbstractBlock node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(StatDoWhile node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(StatFor1 node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(StatFor2 node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(StatFor3 node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(StatFunDef node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(StatIf node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(StatImport node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(StatListCreation node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(StatMethodDeclaration node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(StatPropose node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(StatReturn node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(StatSetOperation node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(StatTimeout node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(StatVarDef node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(StatWhile node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(UCharacter node) {
    if (collectElements != null) {
      this.collectElements += node.content;
      return;
    } else if (!funcargs.equals("")) {
      this.funcargs += node.content;
      return;
    }
    this.lastbool = this.currentRule + this.counter++;
    this.compiledLook.put(this.lastbool, "\'" + node.content + "\'" + isTrue + " ");
    this.realLook.put(lastbool, node.content + isTrue);
    isTrue = "";
  }

  @Override
  public void visitNode(UComment node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void visitNode(UCommentBlock node) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  private String FieldAccessPart = null;
  
  @Override
  public void visitNode(UFieldAccess node) {
    List<String> representation = new ArrayList<>();
    node.parts.get(0).visit(this);
    representation.add(node.representation.get(0));
    String lastType = ((RTExpression) (node.parts.get(0))).getType();
    for (int i = 1; i < node.parts.size(); i++) {
      if (node.parts.get(i) instanceof UVariable) {
        try {
          if (this.rudi.getProxy().fetchRdfClass(lastType) != null) {
            representation.add(node.representation.get(i));
            // then we are in the case that this is actually an rdf operation
            out.append(".getValue(" + node.representation.get(i) + ", client) ");
            lastType = node.getPredicateType(rudi.getProxy(), mem, representation);
            continue;
          } else {
            representation.clear();
          }
        } catch (TException ex) {
          java.util.logging.Logger.getLogger(VGenerationVisitor.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
      out.append(".");
      node.parts.get(i).visit(this);
    }
    if (collectElements != null) {
      this.collectElements += t + isTrue;
      return;
    } else if (!funcargs.equals("")) {
      funcargs += t;
      return;
    }
    this.lastbool = this.currentRule + this.counter++;
    this.compiledLook.put(this.lastbool, t + isTrue);
    this.realLook.put(lastbool, t + isTrue);
    isTrue = "";
  }

  private String funcargs = "";

  @Override
  public void visitNode(UFuncCall node) {
    String m = "";
    if (!funcargs.equals("")) {
      m = funcargs;
      funcargs = "";
    }
    funcargs = (node.representation + "(");
    for (int i = 0; i < node.exps.size(); i++) {
      node.exps.get(i).visit(this);
      if (i != node.exps.size() - 1) {
        funcargs += (", ");
      }
    }
    funcargs += (")" + " ");
    if (collectElements != null) {
      this.collectElements += funcargs + isTrue;
      return;
    }
    this.lastbool = this.currentRule + this.counter++;
    this.compiledLook.put(this.lastbool, funcargs + isTrue);
    this.realLook.put(lastbool, funcargs.replaceAll("\\\\\"", "\\\"") + isTrue);
    funcargs = m;
    isTrue = "";
  }

  @Override
  public void visitNode(UNull node) {
    if (collectElements != null) {
      this.collectElements += "null";
      return;
    } else if (!funcargs.equals("")) {
      funcargs += "null";
      return;
    }
    this.lastbool = this.currentRule + this.counter++;
    this.compiledLook.put(this.lastbool, "null " + isTrue);
    this.realLook.put(lastbool, "null " + isTrue);
    isTrue = "";
  }

  @Override
  public void visitNode(UNumber node) {
    if (collectElements != null) {
      this.collectElements += node.value + isTrue;
      return;
    } else if (!funcargs.equals("")) {
      funcargs += node.value + isTrue;
      return;
    }
    this.lastbool = this.currentRule + this.counter++;
    this.compiledLook.put(this.lastbool, node.value + isTrue);
    this.realLook.put(lastbool, node.value + isTrue);
    isTrue = "";
  }

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
    this.compiledLook.put(this.lastbool,
            node.content.substring(0, node.content.length() - 1)
            + "\"" + " " + isTrue);
    this.realLook.put(lastbool,
            node.content.substring(0, node.content.length() - 1)
            + "\"" + " " + isTrue);
    isTrue = "";
  }

  @Override
  public void visitNode(UVariable node) {
    if (node.isRdfClass) {
      //if (node.getType().equals("DialogueAct") || mem.isRdf(node.representation)) {
      if(collectDAs != null){
        this.collectDAs += "\"" + node.representation + "\"";
        return;
      }
      this.lastbool = this.currentRule + this.counter++;
      this.compiledLook.put(this.lastbool, "\"" + node.representation + "\"");
      this.realLook.put(this.lastbool, "\"" + node.representation + "\"");
      return;
    }
    // if the variable is not in the memory,
    if (node.realOrigin != null) {
      String t = node.realOrigin;
      if (collectElements != null) {
        this.collectElements += t.substring(0, 1).toLowerCase() + t.substring(1) + "." + node.representation;
        return;
      } else if (!funcargs.equals("")) {
        this.funcargs += t.substring(0, 1).toLowerCase() + t.substring(1) + "." + node.representation;
        return;
      }
      //if (node.getType().equals("DialogueAct") || mem.isRdf(node.representation)) {
      if(collectDAs != null){
        this.collectDAs += t.substring(0, 1).toLowerCase() + t.substring(1) + "." + node.representation;
        return;
      }
      this.lastbool = this.currentRule + this.counter++;
      this.compiledLook.put(this.lastbool,
              t.substring(0, 1).toLowerCase() + t.substring(1) + "." + node.representation + " " + isTrue);
      this.realLook.put(lastbool, node.representation + " " + isTrue);
      return;
    } else {
      if (collectElements != null) {
        this.collectElements += node.representation;
        return;
      } else if (!funcargs.equals("")) {
        this.funcargs += node.representation;
        return;
      }
      if (collectDAs != null) {
        this.collectDAs += node.representation;
        return;
      }
      this.lastbool = this.currentRule + this.counter++;
      this.compiledLook.put(this.lastbool, node.representation + " " + isTrue);
      this.realLook.put(lastbool, node.representation + " " + isTrue);
      isTrue = "";
    }
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

  @Override
  public void visitNode(UnaryBoolean node) {
    if (collectElements != null) {
      this.collectElements += node.content + " " + isTrue;
      return;
    } else if (!funcargs.equals("")) {
      this.funcargs += node.content + " " + isTrue;
      return;
    }
    this.lastbool = this.currentRule + this.counter++;
    this.compiledLook.put(this.lastbool, node.content + " " + isTrue);
    this.realLook.put(lastbool, node.content + " " + isTrue);
    isTrue = "";
  }
}
