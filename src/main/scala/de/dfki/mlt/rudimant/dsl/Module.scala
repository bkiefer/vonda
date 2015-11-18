package de.dfki.mlt.rudimant.dsl

import de.dfki.mlt.rudimant.{Log, RuleSet}

import scala.collection.mutable.ArrayBuffer

trait Module extends RuleSet { module =>

  def name = this.getClass.getCanonicalName

  log.debug("Initialising module \"{}\"", name)

  override val rules = ArrayBuffer.empty[Rule]

  protected def +=(rule: Rule): Unit = {
    rules += rule
  }

  protected def activate(module: Module): Unit = ???

  protected def deactivate(module: Module): Unit = ???

  protected lazy val log = Log(this.getClass)

  protected class RuleDef(name: String) {
    def :=[A <: Materialisable](r: A): Rule = {
      val m = r.mat
      module += Module.NamedRule(name, m)
      log.debug("Defined rule \"{}\"", name)
      m
    }
  }

  protected def Rule(name: String): RuleDef = new RuleDef(name)

//  protected def $$[A](body: With[A] => Unit) = Re(body)

  protected def With[A](body: => A) = Selector.Base({ () => body })

  protected def $[A](body: => A) = With(body)

//  protected def Do[A](body: A => Unit) = Action.DoAction(body)

}

object Module {

  case class NamedRule(name: String, rule: Rule) extends Rule {
    override def toString = "#" + name
    override def eval(env: Env) = rule.eval(env)
  }

}
