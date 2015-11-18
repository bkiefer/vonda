package de.dfki.mlt.rudimant

import Proposal._

sealed trait Action

case class Deferred(body: () => Unit) extends Action
case class Proposal(descriptor: Descriptor, body: () => Unit) extends Action

object Proposal {

  type Descriptor = String

}
