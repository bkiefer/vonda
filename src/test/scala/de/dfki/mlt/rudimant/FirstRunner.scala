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

  val engine = new RuleEngine()

  engine.add(FirstModule)

  // list all active rules
  for (r <- engine.rules) {
    println(r)
  }

}
