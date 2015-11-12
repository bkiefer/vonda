package de.dfki.mlt.rudimant

import de.dfki.mlt.rudimant.dsl.{Rule, Module}

trait RuleSet {

  def rules: Iterable[Rule[_]]

  def +=(module: Module): Unit

  def -=(module: Module): Unit

}

object RuleSet {

  def apply(): RuleSet = new RuleSet {
    override def rules = ???
    override def -=(module: Module) = ???
    override def +=(module: Module) = ???
  }

}