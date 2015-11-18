package de.dfki.mlt.rudimant.util

import de.dfki.mlt.rudimant.Log

class LoggingValueHolder[A](init: => A) {

  private lazy val log = Log(this.getClass)

  private var _value: A = init

  def value = {
    log.debug("accessing value (current: {})", _value)
    _value
  }

  def value_=(to: A) {
    log.debug("setting value to {} (was: {})", to, _value)
    _value = to
  }

}
