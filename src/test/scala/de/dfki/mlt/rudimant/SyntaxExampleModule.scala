package de.dfki.mlt.rudimant

import de.dfki.mlt.rudimant.dsl._


object SyntaxExampleModule extends Module with PlainWords {

  object sth {
    def length = 42
  }


  Rule ("partial-do") := (
    With { sth.length } Collect { case i if i > 0 => math.sqrt(i) }
      On Success Do { d => }
  )

  Rule ("partial-propose") := (
    With { sth.length } Collect { case i if i > 0 => math.sqrt(i) }
      On Success Propose "hello" As { d => }
  )

  Rule ("partial-do-do") := (
    With { sth.length } Collect { case i if i > 0 => math.sqrt(i) }
      On Success Do { d => }
      On Failure Do { i => }
  )

  Rule ("partial-do-propose") := (
    With { sth.length } Collect { case i if i > 0 => math.sqrt(i) }
      On Success Do { d => }
      On Failure Propose "yippieh" As { i => }
  )

  Rule ("partial-propose-do") := (
    With { sth.length } Collect { case i if i > 0 => math.sqrt(i) }
      On Success Propose "hello" As { d => }
      On Failure Do { i => }
  )

  Rule ("partial-propose-propose") := (
    With { sth.length } Collect { case i if i > 0 => math.sqrt(i) }
      On Success Propose "hello" As { d => }
      On Failure Propose "hurray" As { i => }
  )

  Rule ("boolean-do") := (
    With { sth.length } Filter { _ > 0 }
      On Success Do { i => }
  )

  Rule ("boolean-propose") := (
    With { sth.length } Filter { _ > 0 }
      On Success Propose "hi" As { i => }
  )

  Rule ("boolean-do-do") := (
    With { sth.length } Filter { _ > 0 }
      On Success Do { i => }
      On Failure Do { i => }
  )

  Rule ("boolean-do-propose") := (
    With { sth.length } Filter { _ > 0 }
      On Success Do { i => }
      On Failure Propose "hi" As { i => }
  )

  Rule ("boolean-propose-do") := (
    With { sth.length } Filter { _ > 0 }
      On Success Propose "hi" As { i => }
      On Failure Do { j => }
  )

  Rule ("boolean-propose-propose") := (
    With { sth.length } Filter { _ > 0 }
      On Success Propose "hi" As { i => }
      On Failure Propose "hint" As { j => }
  )

  Rule ("with-do") := (
    With (sth.length) Do { i => }
  )

  Rule ("with-propose") := (
    With (sth.length) Propose "hello" As { i =>
    }
  )

  Rule ("boolean-nested") := (
    With (sth.length) Filter { _ != 0 }
      On Success Carry ( _ Filter { _ > 0 }
        On Success Do { i => }
        On Failure Do { j => }
      )
  )

  Rule ("partial-nested-do") := (
    With (sth.length) Collect { case i if i > 0 => i.toDouble }
      On Success Carry ( _ Filter { _ > 0 }
        On Success Do { d => }
        On Failure Do { e => }
      )
      On Failure Do { j => }
  )

  Rule ("partial-nested-nested") := (
    With (sth.length) Collect { case i if i > 0 => i.toDouble }
      On Success Carry ( _ Filter { _ > 0 }
        On Success Do { d => }
        On Failure Do { e => }
      )
      On Failure Carry ( _ Filter { _ != 0 }
        On Success Do { j => }
      )
  )

  Rule ("partial-nested-maps") := (
    With (sth.length) Collect { case i if i > 0 => i.toDouble }
      On Success Carry ( _ Collect { case i if !i.isInfinity => i.toString }
        On Success Do { s => }  // string
        On Failure Do { d => }  // double
      )
      On Failure Do { i => }  // int
  )

  Rule("fire-and-stop") := {
    With (()) Propose "stop" As { _ =>
      deactivate(this)
    }
  }

}
