package de.dfki.mlt.rudimant

trait ActionSelector {

  def select(proposals: Set[Proposal]): Option[Proposal]

}
