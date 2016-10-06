/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

import de.dfki.mlt.rudimant.RudimantCompiler;
import de.dfki.mlt.rudimant.Mem;
import com.google.googlejavaformat.java.FormatterException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;

import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * this visitor generates the java code
 *
 * @author Anna Welker, anna.welker@dfki.de
 */
public class VGenerationVisitor implements RudiVisitor {

  public static Logger logger = LoggerFactory.getLogger(RudimantCompiler.class);

  private RudimantCompiler out;
  private Mem mem;
  private VRuleConditionVisitor condV;
  private VConditionCreatorVisitor condV2;

  // activate this bool to get double escaped String literals
  private boolean escape = false;

  // flag to tell the if if is a real rule if (contains the condition that was calculated)
  private String ruleIf = null;

  public VGenerationVisitor(RudimantCompiler out) {
    this.out = out;
    this.mem = out.getMem();
    condV = new VRuleConditionVisitor();
    condV2 = new VConditionCreatorVisitor();
  }

  @Override
  public void visitNode(RudiTree node) {
    node.visit(this);
  }

  @Override
  public void visitNode(ExpAbstractWrapper node) {
    node.commentbefore.visit(this);
    node.exp.visit(this);
    node.commentafter.visit(this);
  }

  @Override
  public void visitNode(ExpArithmetic node) {
    String ret = "";
    if (node.minus) {
      out.append("-");
    }
    if (node.right != null) {
      out.append("(");
      node.left.visit(this);
      out.append(node.operator);
      node.right.visit(this);
      out.append(")");
      return;
    }
    node.left.visit(this);
  }

  @Override
  public void visitNode(ExpAssignment node) {
    //System.out.println(((UVariable) left).toString());
    if (node.declaration) {
      out.append(node.actualType + " ");
    }
    // visit also the left side, it could be using another class's variable!
    // System.out.println("Generating assignment");
    node.left.visit(this);
    out.append(" = ");
    node.right.visit(this);
    //out.append(";");
  }

  @Override
  public void visitNode(ExpBoolean node) {
    if (node.type == null) {
      node.getType();
    }
    String ret = "";
    if (node.not) {
      out.append("!");
    }

    String function = "";
    if (node.rdf) {
      function = "RdfClass.isSubclassOf(";
    } else {
      function = "isSubsumed(";
    }
    if (node.isSubsumed) {
      out.append(function);
      node.left.visit(this);
      out.append(", ");
      node.right.visit(this);
      out.append(")");
      return;
    } else if (node.doesSubsume) {
      out.append(function);
      node.right.visit(this);
      out.append(", ");
      node.left.visit(this);
      out.append(")");
      return;
    }
    if (node.right != null) {
      out.append("(");
      node.left.visit(this);
      out.append(" ");
      out.append(node.operator);
      out.append(" ");
      node.right.visit(this);
      out.append(")");
      //out.context.doLog(
      //        "\"" + ret.replace('"', ' ') +  " _ resulted to \" + " + ret);
      return;
    }
    out.append("(");
    node.left.visit(this);
    this.conditionHandling(node);
    out.append(")");
    //out.context.doLog("\"" + ret.replace('"', ' ') +  " resulted to \" + ("
    //        + ret + ")");
  }

  @Override
  public void visitNode(ExpDialogueAct node) {
    out.append("new DialogueAct(" + node.litGraph + ", ");
    // the first argument will never need to be more than a String
    out.append("\"" + node.rest.get(0) + "\"");
    for (int i = 1; i < node.rest.size(); i++) {
      String[] parts = node.rest.get(i).split("=");
      if (parts.length == 1) {
        // then this argument is a variable that is passed and should be found somewhere
        if (mem.variableExists(parts[0])) {
          out.append(", \" + " + parts[0] + " + \"");
        } else {
          // TODO: or throw an error here?
          out.append(", " + parts[0]);
        }
      } else // this argument is of kind x = y, look if y is a variable we know
      {
        if (mem.variableExists(parts[1])) {
          out.append(", " + parts[0] + " = \" + " + parts[1] + " + \"");
        } else {
          out.append(", " + parts[0] + " = " + parts[1]);
        }
      }
    }
    out.append(")\")");
  }

  @Override
  public void visitNode(ExpFuncOnObject node) {
    node.on.visit(this);
    out.append(".");
    node.funccall.visit(this);
  }

  @Override
  public void visitNode(ExpIf node) {
    node.boolexp.visit(this);
    out.append(" ? ");
    node.thenexp.visit(this);
    out.append(" : ");
    node.elseexp.visit(this);
  }

  @Override
  public void visitNode(ExpLambda node) {
    out.append(node.exp);
  }

  @Override
  public void visitNode(GrammarFile node) {

    String oldname = mem.getClassName();
    String oldrule = mem.getCurrentRule();
    String oldTrule = mem.getCurrentTopRule();
    mem.enterClass(out.className);
    //mem.enterNextEnvironment();

    // moved to typevisitor where it belongs
//    for (String s : mem.getTopLevelRules(out.className)) {
//      for (String n : mem.getNeededClasses(s)) {
//        mem.needsClass(out.className, n);
//      }
//    }
    out.append("import java.util.ArrayList;\n"
            + "import java.util.List;\n"
            + "import java.util.Set;\n"
            + "import java.util.HashSet;\n"
            + "import java.util.HashMap;\n"
            + "import org.slf4j.Logger;\n"
            + "import org.slf4j.LoggerFactory;\n\n");
    out.append("public class " + node.classname + " extends "
            + out.getWrapperClass() + "{\n");
    out.append("public static Logger logger = LoggerFactory.getLogger("
            + mem.getClassName() + ".class);\n");
    out.append("// add to this set the name of all rules you want to be logged\n");
    out.append("private Set<String> rulesToLog = new HashSet<>();\n");
//    out.append("private Boolean wholeCondition = null;\n\n");
    //        + "\tprivate int returnTo = 0;\n");
    // initialize all return markers
//    for (String k : out.rm.getMarkers()) {
//      out.append("int return_" + k + " = " + out.rm.getMarker(k) + ";\n");
//    }
    // initialize all class attributes before the main process method,
    // do all those import things now - but before that, we have to know about
    // all the variables declared here
    for (RudiTree r : node.rules) {
      if (r instanceof StatAbstractBlock) {
        for (RudiTree e : ((StatAbstractBlock) r).statblock) {
          if (e instanceof ExpAbstractWrapper && (((ExpAbstractWrapper) e).exp instanceof ExpAssignment)) {
            if (((ExpAssignment) ((ExpAbstractWrapper) e).exp).declaration) {
              ((ExpAbstractWrapper) e).exp.visit(this);
              out.append(";");
            }
          } else if (e instanceof ExpAbstractWrapper
                  && (((ExpAbstractWrapper) e).exp instanceof ExpAbstractWrapper
                  && ((ExpAbstractWrapper) ((ExpAbstractWrapper) e).exp).exp instanceof ExpAssignment)) {
            // then it is a class attribute and we want it to be defined outside
            // of the process method
            if (((ExpAssignment) ((ExpAbstractWrapper) ((ExpAbstractWrapper) e).exp).exp).declaration) {
              ((ExpAbstractWrapper) ((ExpAbstractWrapper) e).exp).exp.visit(this);
              out.append(";");
            }
          } else if (e instanceof StatVarDef || e instanceof StatFunDef){
            e.visit(this);
          }
        }
      }
    }
    for (RudiTree r : node.rules) {
      if (r instanceof StatAbstractBlock) {
        for (RudiTree e : ((StatAbstractBlock) r).statblock) {if (e instanceof StatImport) {
            r.visit(this);
          }
        }
      }
    }
    out.append("\tpublic void process(");
    // get all those classes the toplevel rules need
    int i = 0;
    for (String n : mem.getNeededClasses(out.className)) {
      if (i == 0) {
        out.append(n.substring(0, 1).toUpperCase() + n.substring(1) + " " + n);
      } else {
        out.append(", " + n.substring(0, 1).toUpperCase() + n.substring(1) + " " + n);
      }
      i++;
    }
    out.append("){\n");
    // initialize me according to the super class init
    out.append("this.init();\n");
    // use all methods created from rules in this file
    for (String toplevel : mem.getToplevelCalls(out.className)) {
      if (toplevel.contains("(")) {
        out.append(toplevel);

        String t = toplevel.substring(0, toplevel.indexOf(" "));
        Set<String> ncs = mem.getNeededClasses(t);
//        Set<String> ncs = mem.getNeededClasses(toplevel.substring(0, toplevel.indexOf(" ")));
        if (ncs != null) {
          i = 0;
          for (String c : ncs) {
            if (c.equals(out.className)
                    || (c.substring(0, 1).toUpperCase()
                    + c.substring(1)).equals(out.className)) {
              c = "this";
            }
            if (i == 0) {
              out.append(c.substring(0, 1).toLowerCase()
                    + c.substring(1));
            } else {
              out.append(", " + c.substring(0, 1).toLowerCase()
                    + c.substring(1));
            }
            i++;
          }
        }
        out.append(");\n");
      } else {
        out.append(toplevel + "(");
        // don't forget the needed class instances here
        i = 0;
        for (String n : mem.getNeededClasses(toplevel)) {
          if (i == 0) {
            out.append(n.substring(0, 1).toLowerCase()
                    + n.substring(1));
          } else {
            out.append(", " + n.substring(0, 1).toLowerCase()
                    + n.substring(1));
          }
          i++;
        }
        out.append(");");
      }
    }
    out.append("}\n");
    // Now, only get those statements that are not assignments of class attributes
    for (RudiTree r : node.rules) {
      if (r instanceof StatAbstractBlock) {
        for (RudiTree e : ((StatAbstractBlock) r).statblock) {
          if (e instanceof ExpAbstractWrapper && ((ExpAbstractWrapper) e).exp instanceof ExpAbstractWrapper
                  && ((ExpAbstractWrapper) ((ExpAbstractWrapper) e).exp).exp instanceof ExpAssignment) {
            if (((ExpAssignment) ((ExpAbstractWrapper) ((ExpAbstractWrapper) e).exp).exp).declaration) {
              continue;
            }
          } else if (e instanceof ExpAbstractWrapper && (((ExpAbstractWrapper) e).exp instanceof ExpAssignment)) {
            if (((ExpAssignment) ((ExpAbstractWrapper) e).exp).declaration) {
              continue;
            }
          } else if (e instanceof StatImport) {
            continue;
          } else if (e instanceof StatVarDef || e instanceof StatFunDef){
            continue;
          }
          e.visit(this);
        }
      } else {
        r.visit(this);
      }
    }
//    // add all the logger methods
//    for (String l : out.ll.getLogRules()) {
//      this.printRuleLogger(l, out.ll.getCond2log(l));
//    }
    out.append("}\n");
    mem.leaveClass(oldname, oldrule, oldTrule);
    mem.leaveEnvironment();
  }

  @Override
  public void visitNode(GrammarRule node
  ) {
    if (node.toplevel) {
      // this is a toplevel rule and will be converted to a method
      out.append("public void " + node.label + "(");
      // get all the required class instances
      int i = 0;
      for (String n : mem.getNeededClasses(node.label)) {
        if (i == 0) {
          out.append(n.substring(0, 1).toUpperCase() + n.substring(1) + " " + n);
        } else {
          out.append(", " + n.substring(0, 1).toUpperCase() + n.substring(1) + " " + n);
        }
        i++;
      }
      out.append("){\n");
      node.commentb.visit(this);
      this.ruleIf = this.printRuleLogger(node.label, node.ifstat.condition);
      out.append(node.label + ":\n");
      //mem.enterNextEnvironment();
      node.commenta.visit(this);
      node.ifstat.visit(this);
      //mem.leaveEnvironment();
      out.append("}\n");
    } else {
      // this is a sublevel rule and will get an if to determine whether it should be executed
      out.append("//Rule " + node.label + "\n");
      this.ruleIf = this.printRuleLogger(node.label, node.ifstat.condition);
      out.append(node.label + ":\n");
      node.commentb.visit(this);
//      if (out.rm.shouldAddReturnto(node.label) != null) {
//        out.append("if ((returnTo | (");
//        int i = 0;
//        for (String r : out.rm.shouldAddReturnto(node.label)) {
//          if (i != 0) {
//            out.append(" | return_" + r);
//          } else {
//            out.append("return_" + r);
//          }
//          i++;
//        }
//        out.append(")) == 0) {\n");
//      }
      node.commenta.visit(this);
      node.ifstat.visit(this);
    }
  }

  @Override
  public void visitNode(StatAbstractBlock node) {
    if (node.braces) {
      // when entering a statement block, we need to create a new local environment
      //mem.enterNextEnvironment();
      out.append("{");
    }
    for (RudiTree stat : node.statblock) {
      if (stat instanceof RTExpression) {
        stat.visit(this);
        out.append(";\n");
        break;
      }
      stat.visit(this);
    }
    if (node.braces) {
      out.append("}");
      //mem.leaveEnvironment();
    }
  }

  @Override
  public void visitNode(StatDoWhile node) {
    out.append("do");
    node.statblock.visit(this);
    out.append("while (");
    node.condition.visit(this);
    out.append(");");
  }

  @Override
  public void visitNode(StatFor1 node) {
    out.append("for ( ");
    node.assignment.visit(this);
    out.append("; ");
    node.condition.visit(this);
    out.append(";");
    if (node.arithmetic != null) {
      node.arithmetic.visit(this);
    }
    out.append(");");
    node.statblock.visit(this);
  }

  @Override
  public void visitNode(StatFor2 node) {
    // TODO: or should we check here that the type of the variable in assignment
    // is the type the iterable in exp returns? How?
    if (node.varType == null) {
      node.varType = ((RTExpression) node.exp).getType();
    }
    out.append("for (" + node.varType + " ");
    node.var.visit(this);
    out.append(": ");
    node.exp.visit(this);
    out.append(") ");
    node.statblock.visit(this);
  }

  @Override
  public void visitNode(StatFor3 node) {
    out.append("for (Object[] o : ");
    node.exp.visit(this);
    out.append(") {");
    int count = 0;
    for (String s : node.variables) {
      out.append("\nObject " + s + " = o[" + count++ + "]");
    }
    node.statblock.visit(this);
    out.append("}");
  }

  @Override
  public void visitNode(StatFunDef node) {
    // no generation here
  }

  @Override
  public void visitNode(StatIf node) {
    if (this.ruleIf != null) {
      out.append("if (" + ruleIf + ") ");
//      out.append("if (rulesToLog.contains(\"" + node.currentRule + "\") ? wholeCondition : ");
      ruleIf = null;
    } else {
      out.append("if (");
      node.condition.visit(this);
      out.append(") ");
    }
    node.statblockIf.visit(this);
    if (node.statblockElse != null) {
      out.append("else");
      node.statblockElse.visit(this);
    }
  }

  @Override
  public void visitNode(StatImport node) {
    logger.info("Processing import " + node.text);
    try {
      RudimantCompiler.getEmbedded(out).process(node.text);
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    } catch (FormatterException ex) {
      java.util.logging.Logger.getLogger(VGenerationVisitor.class.getName()).log(Level.SEVERE, null, ex);
    } //    out.append(node.text + ".process(");
    //    Set<String> ncs = mem.getNeededClasses(node.name);
    //    if (ncs != null) {
    //      int i = 0;
    //      for (String c : ncs) {
    //        if (c.equals(out.className)) {
    //          c = "this";
    //        }
    //        if (i == 0) {
    //          out.append(c.toLowerCase());
    //        } else {
    //          out.append(", " + c.toLowerCase());
    //        }
    //        i++;
    //      }
    //    }
    //    out.append(");\n");
    catch (TException ex) {
      throw new RuntimeException(ex);
    }
  }

  @Override
  public void visitNode(StatListCreation node) {
//    System.out.println("Rudi was here");
    out.append(node.listType + " " + node.variableName + " = new ArrayList<>();");
    for (RTExpression e : node.objects) {
      out.append(node.variableName + ".add(");
      this.visitNode(e);
      out.append(");\n");
    }
  }

  @Override
  public void visitNode(StatMethodDeclaration node) {
    //mem.enterNextEnvironment();
    String ret = node.visibility + " " + node.return_type + " " + node.name + "(";
    if (!node.parameters.isEmpty()) {
      for (int i = 0; i < node.parameters.size(); i++) {
        if (i == 1) {
          ret += node.partypes.get(i) + " " + node.parameters.get(i);
        } else {
          ret += ", " + node.partypes.get(i) + " " + node.parameters.get(i);
        }
      }
    }
    ret += ")";
    out.append(ret + "\n");
    node.block.visit(this);
    //mem.leaveEnvironment();
  }

  @Override
  public void visitNode(StatPropose node) {
    out.append("propose(");
    node.arg.visit(this);
    out.append(", new Proposal() {public void run()\n");
    node.block.visit(this);
    out.append("});");
  }

  @Override
  public void visitNode(StatReturn node) {
    if (mem.isExistingRule(node.lit)) {
      if (mem.getTopLevelRules(mem.getClassName()).contains(node.curRuleLabel)) {
        out.append("return;\n");
        return;
      }
      //out.append("returnTo = returnTo | return_" + node.lit + ";\n");
      out.append("break " + node.lit + ";\n");
      return;

    } else if (node.toRet == null) {
      if (mem.getCurrentRule().equals(mem.getClassName())) {
        out.append("return;\n");
        return;
      }
      out.append("break " + mem.getCurrentRule() + ";\n");
      return;
    }
    out.append("return ");
    node.toRet.visit(this);
    out.append(";\n");
  }

  @Override
  public void visitNode(StatSetOperation node) {
    node.left.visit(this);
    if (node.add) {
      out.append(".add(");
    } else {
      out.append(".remove(");
    }
    node.right.visit(this);
    out.append(");");
  }

  @Override
  public void visitNode(StatTimeout node) {
    if (node.statblock == null) {
      out.append("newTimeout(" + node.name + "," + node.time + ");\n");
    }
    out.append("MyTimer t = newSpecialTimeout(" + node.name + "," + node.time + ");"
            + "t.timer = new Timer(timeToFire, new ActionListener(){\n"
            + "@Override\n public void actionPerformed(ActionEvent e)" + node.statblock + "});"
            + "\n" + "t.started = System.currentTimeMillis();\n"
            + " t.timer.start();\n");
  }

  @Override
  public void visitNode(StatVarDef node) {
    // no generation here
  }

  @Override
  public void visitNode(StatWhile node) {
    out.append("while (");
    node.condition.visit(this);
    out.append(")");
    node.statblock.visit(this);
  }

  @Override
  public void visitNode(UCharacter node) {
    out.append("\'" + node.content + "\'" + " ");
  }

  @Override
  public void visitNode(UComment node) {
    out.append(node.comment + " ");
  }

  @Override
  public void visitNode(UCommentBlock node) {
    for (UComment c : node.comments) {
      c.visit(this);
      out.append("\n");
    }
  }

  @Override
  public void visitNode(UFieldAccess node) {
    // TODO: tell me how the client is named!!!
    out.append(node.representation.get(0));
    for (int i = 1; i < node.representation.size(); i++) {
      out.append(".getValue(" + node.representation.get(i) + ", client)" + " ");
    }
  }

  @Override
  public void visitNode(UFuncCall node) {
    out.append(node.representation + "(");
    for (int i = 0; i < node.exps.size(); i++) {
      node.exps.get(i).visit(this);
      if (i != node.exps.size() - 1) {
        out.append(", ");
      }
    }
    out.append(")" + " ");
  }

  @Override
  public void visitNode(UNull node) {
    out.append("null" + " ");
  }

  @Override
  public void visitNode(UNumber node) {
    out.append(node.value);
  }

  @Override
  public void visitNode(UString node) {
    if (this.escape) {
      out.append("\\" + node.content.substring(0, node.content.length() - 1) + "\\\"" + " ");
    } else {
      out.append(node.content + " ");
    }
  }

  @Override
  public void visitNode(UVariable node) {
    if (node.isRdfClass) {
      out.append("\"" + node.representation + "\"");
    } // if the variable is not in the memory,
    else if (node.realOrigin != null) {
      String t = node.realOrigin;
      out.append(t.substring(0, 1).toLowerCase() + t.substring(1) + "." + node.representation + " ");
      return;
    } else {
      out.append(node.representation + " ");
    }
  }

  @Override
  public void visitNode(UWildcard node) {
    out.append("this.wildcard" + " ");   // wildcard is a local variable in resulting class
  }

  @Override
  public void visitNode(UnaryBoolean node) {
    out.append(node.content + " ");
  }

  private void conditionHandling(ExpBoolean node) {
    out.append(node.isTrue + " ");
  }

  /**
   * creates and prints the logging method of the given rule
   *
   * @param rule
   */
  private String printRuleLogger(String rule, RTExpression bool_exp) {
    if (bool_exp instanceof ExpAbstractWrapper) {
      bool_exp = (RTExpression) ((ExpAbstractWrapper) bool_exp).exp;
    }
    if (bool_exp instanceof UnaryBoolean) {
      // there isnt much we could log
//      out.append("wholeCondition = " + ((UnaryBoolean) bool_exp).content + ";\n");
      return ((UnaryBoolean) bool_exp).content;
//      return;
    }
    ExpBoolean bool = (ExpBoolean) bool_exp;

    // remembers how the expressions should be realized by rudimant
    LinkedHashMap<String, String> compiledLook = new LinkedHashMap<>();

    // remembers how the expressions looked (for logging)
    LinkedHashMap<String, String> realLook = new LinkedHashMap<>();
    condV.renewMap(rule, realLook, compiledLook, this.mem);
    condV.visitNode(bool);
    // now create a condition from those things
    //System.out.println(realLook.keySet().size());
    condV2.newMap(realLook.keySet().toArray(), compiledLook);
    condV2.visitNode(bool_exp);

    out.append(condV2.getBoolCreation().toString());
//    for (String var : compiledLook.keySet()) {
//      out.append("//boolean " + var + " = " + compiledLook.get(var) + ";\n");
//    }

    out.append("if (rulesToLog.contains(\"" + rule + "\")){\n");
    // do all that logging

    out.append("HashMap<String, Boolean> " + rule + " = new HashMap<>();\n");
    for (String var : realLook.keySet()) {
      out.append(rule + ".put(\"" + realLook.get(var).replaceAll("\\\"", "\\\\\"") + "\", " + var + ");\n");
    }

    out.append("LoggerFunction(" + rule + ", \"" + rule + "\", \""
            + mem.getClassName() + "\");\n");

    out.append("}\n");
//    out.append("wholeCondition = " + condV2.getCondition().toString() + ";\n");
    return condV2.getCondition().toString();

//    out.append(rule + "Logger(");
//    if (!(out.ll.getVarAndType2log(rule) == null)) {
//      int i = 0;
//      for (String[] var2type : out.ll.getVarAndType2log(rule)) {
//        if (i < 1) {
//          out.append(var2type[0]);
//        } else {
//          out.append(", " + var2type[0]);
//        }
//      }
//    }
//    out.append(");");
  }

  public void dummyLoggingMethod(String rule, String className, Map toLog) {
    // log this very extensive
  }

//  /**
//   * creates and prints the logging method of the given rule
//   *
//   * @param rule
//   */
//  private void printRuleLogger(String rule, RudiTree bool_exp) {
//    if (rule == null) {
////      if (bool_exp instanceof ExpAbstractWrapper) {
////        printRuleLogger(null, ((ExpAbstractWrapper) bool_exp).exp);
////      } else if (bool_exp instanceof ExpBoolean) {
////        out.append("logger.info(\"" + ((ExpBoolean)bool_exp).fullexp
////                + ((ExpBoolean)bool_exp).isTrue + " was \" + "
////                + ((ExpBoolean)bool_exp).isTrue + ((ExpBoolean)bool_exp).fullexp);
////        if(!(((ExpBoolean)bool_exp).isSubsumed | ((ExpBoolean)bool_exp).doesSubsume)){
////          printRuleLogger(null, ((ExpBoolean)bool_exp).left);
////          if(((ExpBoolean)bool_exp).right != null){
////            printRuleLogger(null, ((ExpBoolean)bool_exp).right);
////          }
////        }
//      out.append("logger.info(\"");
//      this.escape = true;
//      this.visitNode(bool_exp);
//      this.escape = false;
//      out.append(" was \" + (");
//      this.visitNode(bool_exp);
//      out.append("));");
////      } else if (bool_exp instanceof ExpArithmetic) {
////
////      } else if (bool_exp instanceof UVariable) {
////
////      } else if (bool_exp instanceof ExpDialogueAct) {
////
////      } else { // it should be sth simple like String, Number, Variable, UnaryBoolean, ...
////
////      }
//      return;
//    }
//    out.append("public void " + rule + "Logger(");
//    if (!out.ll.getVarAndType2log(rule).isEmpty()) {
//      int i = 0;
//      for (String[] var2type : out.ll.getVarAndType2log(rule)) {
//        if (i < 1) {
//          out.append(var2type[1] + " " + var2type[0]);
//        } else {
//          out.append(", " + var2type[1] + " " + var2type[0]);
//        }
//      }
//    }
//    out.append(") {\n if (rulesToLog.contains(\"" + rule + "\")){\n");
//    // do all that logging
//    printRuleLogger(null, bool_exp);
//    out.append("}\n}\n");
//  }
}
