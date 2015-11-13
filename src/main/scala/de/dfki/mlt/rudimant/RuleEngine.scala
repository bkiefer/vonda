package de.dfki.mlt.rudimant

import de.dfki.mlt.rudimant.dsl.Rule

class RuleEngine {

  val log = Log(this.getClass)

  def add(rs: RuleSet): Unit = {
    log.debug("Adding rule set {}", rs)
    ???
  }

  def rules: Iterable[Rule[_]] = {
    ???
  }

}
