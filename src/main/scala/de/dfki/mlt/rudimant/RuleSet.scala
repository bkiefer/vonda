package de.dfki.mlt.rudimant

import de.dfki.mlt.rudimant.dsl.{Rule, Module}

trait RuleSet {

  def rules: Iterable[Rule[_]]

}
