package de.dfki.mlt.rudimant

import dsl._

import scala.language.postfixOps

object Holder {

  var value: Int = 0

}

object FirstModule extends Module with PlainWords {

  Rule("check-zero") := (
    With (Holder.value) Filter { _ != 0 }
      On Success Propose "nonzero" As { i =>
        println("nonzero: " + i)
      }
      On Failure Propose "zero" As { j =>
        println("zero!")
      }
  )

//  Rule("switch") := {
//    With (true) Do { t =>
//      deactivate(this)
//      activate(SecondModule)
//    }
//  }

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
//  for (r <- engine.rules) {
//    println(r)
//  }

  println("Setting holder to 0")
  Holder.value = 0

  val e0 = engine.evaluate()

  println("Evaluation: [\n" + (e0 map { case (r, a) => s"\t$r: $a\n" }).mkString + "]")

  for ((r, Proposal(desc, body)) <- e0) {
    println("Executing proposal \"" + desc + "\":")
    body()
  }

  println("Setting holder to 42")
  Holder.value = 42

  val e1 = engine.evaluate()

  println("Evaluation: [\n" + (e1 map { case (r, a) => s"\t$r: $a\n" }).mkString + "]")

  for ((r, Proposal(desc, body)) <- e1) {
    println("Executing proposal \"" + desc + "\":")
    body()
  }

}
