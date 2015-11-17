package de.dfki.mlt.rudimant.dsl

import de.dfki.mlt.rudimant.{Log, RuleSet}

import scala.collection.mutable.ArrayBuffer

trait Module extends RuleSet {

  def name = this.getClass.getCanonicalName

  override val rules = ArrayBuffer.empty[Rule[_]]

  protected def +=(rule: Rule[_]): Unit = {
    rules += rule
  }

  protected def activate(module: Module): Unit = ???

  protected def deactivate(module: Module): Unit = ???

  protected def log: Log = ???

  protected trait RuleDef {
    def :=[A <: _Rule](r: A): A = ???
  }

  protected def Rule(name: String): RuleDef = ???

  protected def $$[A](body: With[A] => Unit) = Re(body)

  protected def $[A](body: => A) = With(body)

//  protected def Do[A](body: A => Unit) = Action.DoAction(body)

}
