package de.dfki.mlt.rudimant

import de.dfki.mlt.rudimant.util.LoggingValueHolder
import dsl._

object Holder extends LoggingValueHolder(0)

object FirstModule extends Module with PlainWords {

  Rule("check-zero") := (
    With (Holder.value) Filter { _ != 0 }
      On Success Propose "nonzero" As { i =>
        log.info("nonzero: {}, will now set to 0", i)
        Holder.value = 0
      }
      On Failure Propose "zero" As { j =>
        log.info("zero -> will set to 1")
        Holder.value = 1
      }
  )

  Rule("nonzero") := (
    With (Holder.value) Filter { _ != 0 }
      On Success Do { i =>
        log.info("telling ya, it's non-zero")
    }
  )

  Rule("zero") := (
    With (Holder.value) Filter { _ == 0 }
      On Success Do { i =>
        log.info("this is indeed a ZERO")
    }
  )

  Rule("switch") := (
    With (()) Propose "switch" As { _ =>
      deactivate(this)
      activate(SecondModule)
    }
  )

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

  def doEvaluate(engine: RuleEngine): Unit = {

    println("Evaluating rules in engine " + engine)

    val results = engine.evaluate()

    println("Results: [\n" + (results map { case (r, a) => s"\t$r: $a\n" }).mkString + "]")

    println("Executing deferred actions...")
    for ((r, Deferred(body)) <- results) {
      println("Executing body of " + r)
      body()
    }
    println("Done executing deferred actions.")

    println("Executing proposals...")
    for ((r, Proposal(desc, body)) <- results) {
      println("Executing proposal \"" + desc + "\":")
      body()
    }
    println("Done executing proposals.")

  }


  val engine = new RuleEngine()
  engine.add(FirstModule)

  doEvaluate(engine)
  doEvaluate(engine)

}
