package de.dfki.mlt.rudimant

import dsl._

object FirstModule extends ObjModule {

  If (true) Then {

    Propose ("print-hello-world") {
      println("hello world!")
      activate(SecondModule)
    }

  }

}

object SecondModule extends ObjModule {

  If (true) Then {

    Propose ("print-hello-again") {
      println("hello again!")
      deactivate(this)
    }
  }

}

object FirstRunner extends App {

  val rules = RuleSet()

  rules += FirstModule

  // list all active rules
  for (r <- rules.rules) {
    println(r)
  }

}
