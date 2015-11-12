package de.dfki.mlt.rudimant

object FirstRunner extends App {

  import dsl._

  val rules = RuleSet()

  Module ("first") {

    If (true) Then {

      Propose ("print-hello-world") {
        println("hello world!")
      }

    }

  }

}
