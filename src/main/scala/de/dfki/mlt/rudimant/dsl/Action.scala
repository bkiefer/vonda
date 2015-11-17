package de.dfki.mlt.rudimant.dsl

import de.dfki.mlt.rudimant.Proposal

sealed trait Action[+A] {

}

object Action {

  case class DoAction[A](body: A => Unit) extends Action[A] {

  }

  case class ProposeAction[A](desc: Proposal.Descriptor, body: A => Unit) extends Action[A] {
    def toProposal: Proposal = ???
  }

}
