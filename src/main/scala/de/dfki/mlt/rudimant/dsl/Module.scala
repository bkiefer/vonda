package de.dfki.mlt.rudimant.dsl

import de.dfki.mlt.rudimant.RuleSet

trait Module extends RuleSet {

  def name: String

  def rules: Iterable[Rule[_]]

}

object Module {

  private class DefaultModule(val name: String)
      extends Module {

    override def rules = ???

    override def +=(module: Module) = ???

    override def -=(module: Module) = ???
  }

  class Builder(name: String) {
    def apply(body: => Unit): Module = {
      val mod = new DefaultModule(name)
      println("defining module \"" + name + "\"")
      mod
    }
  }

  def apply(name: String) = new Builder(name)

}
