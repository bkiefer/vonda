package de.dfki.mlt.rudimant

import Proposal._

case class Proposal(descriptor: Descriptor, body: () => Unit) {

  def apply(): Unit = {
    body()
  }

}

object Proposal {

  type Descriptor = String

}
