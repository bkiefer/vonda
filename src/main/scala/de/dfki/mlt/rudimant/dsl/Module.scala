package de.dfki.mlt.rudimant.dsl

import de.dfki.mlt.rudimant.{RuleEngine, Log, RuleSet, Rule}

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

import Module._

trait Module extends RuleSet {

  def name = this.getClass.getCanonicalName

  log.debug("Initialising module \"{}\"", name)

  override val rules = ArrayBuffer.empty[Rule]

  protected def addRule(path: List[String], name: String, rule: Rule): Unit = {
    val ps = if (path.isEmpty) "" else currentPath.mkString("/") + "/"

    // TODO: process disjoint rules!

    val ruleName = Module.this.name + "#" + ps + name

    log.debug("Adding rule \"{}\": {}", ruleName, rule)
    rules += NamedRule(ruleName, rule)
  }

  protected def engine: RuleEngine = ???

  protected def activate(module: Module): Unit = {
    log.warning("Not implemented: activate({}), ignoring!", module)
  }

  protected def deactivate(module: Module): Unit = {
    log.warning("Not implemented: deactivate({}), ignoring!", module)
  }

  private val ctxStack = mutable.Stack[String]()

  protected def currentPath: List[String] = ctxStack.toList.reverse

  protected def Disjoint(name: String)(body: => Unit): Unit = {
    log.debug("Creating disjoint context \"{}\"", name)

    // now execute the body
    ctxStack.push(name)
    try {
      body
    }
    finally {
      ctxStack.pop()
    }

    log.debug("Done creating disjoint context {}", name)
  }

  protected lazy val log = Log(this.getClass)

  protected class RuleDef(name: String) {
    def :=(r: Materialisable): Rule = {
      val rule = r.mat
      Module.this.addRule(currentPath, name, rule)
      rule
    }
  }

  protected def Rule(name: String): RuleDef = new RuleDef(name)

//  protected def $$[A](body: With[A] => Unit) = Re(body)

  protected def With[A](body: => A) = Selector.RootNode({ () => body })

  protected def $[A](body: => A) = With(body)

//  protected def Do[A](body: A => Unit) = Action.DoAction(body)

}

object Module {

  case class NamedRule(name: String, rule: Rule) extends Rule {
    override def toString = "rule:" + name
    override def eval(env: RuleEngine.Env) = rule.eval(env)
  }

}
