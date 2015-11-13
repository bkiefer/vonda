package de.dfki.mlt.rudimant

import de.dfki.mlt.rudimant.dsl.Rule

import scala.collection.mutable.ArrayBuffer

class RuleEngine {

  val log = Log(this.getClass)

  private val _rules = ArrayBuffer.empty[RuleSet]

  def add(rs: RuleSet): Unit = {
    log.debug("Adding ruleset {}", rs)
    if (!_rules.contains(rs)) {
      _rules += rs
    }
    else {
      log.warning("Engine already contains ruleset {}, skipping", rs)
    }
  }

  def remove(rs: RuleSet): Unit = {
    log.debug("Removing ruleset {}", rs)
    if (_rules.contains(rs)) {
      _rules -= rs
    }
    else {
      log.warning("Ruleset {} not found in the engine", rs)
    }
  }

  def rules: Iterable[Rule[_]] = _rules flatMap { _.rules }

}
