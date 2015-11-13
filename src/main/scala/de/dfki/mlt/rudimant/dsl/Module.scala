package de.dfki.mlt.rudimant.dsl

import de.dfki.mlt.rudimant.RuleSet

trait Module extends RuleSet {

  def name: String

  def rules: Iterable[Rule[_]]

}

trait ObjModule extends Module {

  override final def name = this.getClass.getCanonicalName

  override def rules = ???

  protected def activate(module: Module): Unit = ???

  protected def deactivate(module: Module): Unit = ???

}
