package de.dfki.mlt.rudimant

import dsl._


object sth {

  def length: Int = ???

}

object FirstModule extends Module {

  /*
    Rule("hoho") := (

      With (sth.length) If { _ != 0 }
        Then $$ ( w => w IfDefined { case i if i > 0 => math.sqrt(i) }
          Then Propose("move") { j =>
            // positive
            println("moving...")
          }
          Else $$ ( _ If { _ < -1 }
            Then Do { k =>
              log.info("{} is less than -1", k)
            }
//            Else NoOp
          )
        )
        Else Do { i =>
          // == 0
          activate(SecondModule)
        }
    )

  Rule ("is-nonzero") := {

//    val z = Propose("print-length") { i: Int =>
//      println("length is " + i)
//    } Else Do { j => }

//    val +> = true
//    val -> = false

    // @(sth.length) ->> { _ != 0 } -+> { i => } --> { j => }
    // @(sth.length) ->> { case i if i > 0 => i.toDouble } -?> { d => } -!> { i => }

    val zw = $(sth.length) -?> { _ != 0 } -+> Do { i => } --> Do { j => }

    val zx = $(sth.length) -:> { case i if i > 0 => i.toDouble } -+> Do { i => } --> Do { j => }

    val zwx = $(sth.length) -:> { case i if i > 0 => (i, i.toDouble) } -+>
          $$( _ -?> { _._1 == 0 }
              -+> Do { j => }
              --> Do { k => }
          ) -->
          Do { j => }


    val y = With { sth.length } If { _ != 0 } Then Re (w => w If { _ > 0 } Then Do { i => } Else Do { j => })
    
    val yy = With { sth.length } IfDefined { case i if i > 0 => math.sqrt(i) } Then Re (w =>
      w If { _ > 0}
        Then Re { w => w IfDefined { case i if i < 1 => i.toInt } Then 
            Propose("sqrd") { i =>
              
            } Else
          Do {
            k =>
          }
        }
        Else
          Do { j =>
          }
    ) Else
        Do { x =>
        }


    val a = With { sth.length } If { _ != 0 } Then Do { x => } //Else Do { y => }
    val b = With { sth.length } IfDefined { case i if i > 0 => math.sqrt(i) } Then Do { x => } //Else Do { y => }

//    val aa = a Then Propose("prnt") { i => println(i) } Else Do { j => println("no: " + j)}

//    val bb = b Then Propose("prnt") { i => println(i) } Else Do { x => println("damn") }

    val z = With { sth.length } If { _ != 0 } Then Propose("print-length") { i =>
      println("length is " + i)
//    }
    } Else Do { x => log.info("zero!") }

    ???
//    With { sth.length } If { _ != 0 } Then (Propose("print-length") { i: Int =>
//      println("length is " + i)
//    } Else Do { j =>
//      log("is zero, ignoring...")
//    })
  }
*/
  Rule ("has-sqrt") := {
    With { sth.length } IfDefined { case i if i > 0 => math.sqrt(i) } Do { d => }
  }

  Rule ("has-sqrt-2") := {
    With { sth.length } IfDefined { case i if i > 0 => math.sqrt(i) } Do { d => } Else() Do { i => }
  }

  Rule ("is-single-digit") := {
    With { sth.length.toString } If { _.length == 1 } Do { s => ??? }
  }

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
