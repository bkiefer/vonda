package de.dfki.mlt.rudimant

import dsl._

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

  def rules: Iterable[Rule] = _rules flatMap { _.rules }

  def evaluate(): Iterable[(Rule, Action)] = {
    for (r <- rules) yield (r, r.eval(null))
  }

}
