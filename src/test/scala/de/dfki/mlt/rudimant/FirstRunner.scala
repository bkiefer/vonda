package de.dfki.mlt.rudimant

import dsl._

import scala.language.postfixOps

object FirstModule extends Module with PlainWords {

  Rule("switch") := {
    With (true) Do { t =>
      deactivate(this)
      activate(SecondModule)
    }
  }

}

object SecondModule extends Module {

  Rule("switch") := {
    With (true) Do { _ =>
      deactivate(this)
      activate(FirstModule)
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

  val proposals = engine.evaluate()

  println("Proposals: [\n" + (proposals map { p => s"\t$p\n" }).mkString + "]")

}
