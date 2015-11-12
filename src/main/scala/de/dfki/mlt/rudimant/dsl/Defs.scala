package de.dfki.mlt.rudimant.dsl

import de.dfki.mlt.rudimant.Proposal

import scala.language.dynamics

trait Env

trait Body[+A] {

  def apply(): A

}

object Body {

  def apply[A](body: => A): Body[A] = {
    new Body[A] {
      override def apply() = body
    }
  }

}

trait Rule[A] {

  def applies(env: Env): Boolean

  protected def bodyFor(env: Env): Body[A]

  def tryApplying(env: Env): Option[Body[A]] = {
    if (applies(env)) {
      val body = bodyFor(env)
      Some(body)
    }
    else {
      None
    }
  }

  def apply(env: Env): Unit = {
    for (body <- tryApplying(env)) {
      body.apply()
    }
  }

}

trait If[A] extends Rule[A] {

  def condition: If.Condition

  def thenBranch: Body[A]
  def elseBranch: Body[A]

  override def bodyFor(env: Env) = {
    if (condition.evaluate(env)) thenBranch else elseBranch
  }

}

object If {

  trait Condition { outer =>

    import Condition._

    def evaluate(env: Env): Boolean

    def Then[A](body: => A): IfThen[A] = IfThen[A](this, Body(body))

    def And(right: => Boolean) = Condition.And(this, Atom({ () => right }))

    def Or(right: => Boolean) = Condition.Or(this, Atom({ () => right }))

  }

  object Condition {

    case class Atom(thunk: () => Boolean) extends Condition {
      override def evaluate(env: Env) = thunk()
    }

    case class And(left: Condition, right: Condition) extends Condition {
      override def evaluate(env: Env) = left.evaluate(env) && right.evaluate(env)
    }

    case class Or(left: Condition, right: Condition) extends Condition {
      override def evaluate(env: Env) = left.evaluate(env) || right.evaluate(env)
    }

  }

  case class IfThen[A](condition: Condition, thenBranch: Body[A]) extends If[A] { outer =>
    override def applies(env: Env) = condition.evaluate(env)

    def elseBranch = null

    def Else(body: => A): IfThenElse[A] = IfThenElse[A](condition, thenBranch, Body(body))
  }

  case class IfThenElse[A](condition: Condition, thenBranch: Body[A], elseBranch: Body[A]) extends If[A] {
    override def applies(env: Env) = true
  }

  def apply(cond: => Boolean): Condition = Condition.Atom({ () => cond })

}

case class Propose(desc: Proposal.Descriptor) {

  def toProposal = Proposal(desc, ???)

  def apply(body: => Unit): Unit = {
    // TODO
  }
}
