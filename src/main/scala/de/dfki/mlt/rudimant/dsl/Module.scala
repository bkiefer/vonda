package de.dfki.mlt.rudimant.dsl

import de.dfki.mlt.rudimant.RuleSet

import scala.collection.mutable.ArrayBuffer

trait Module extends RuleSet {

  def name = this.getClass.getCanonicalName

  override val rules = ArrayBuffer.empty[Rule[_]]

  protected def +=(rule: Rule[_]): Unit = {
    rules += rule
  }

  protected def activate(module: Module): Unit = ???

  protected def deactivate(module: Module): Unit = ???

}
