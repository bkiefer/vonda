package de.dfki.mlt.rudimant.dsl

import de.dfki.mlt.rudimant.Proposal

trait Env

object Words {
  object SuccessWord
  object FailureWord
}

trait ActionPhrase[In, Out] {

  protected def newDo(body: In => Unit): Out
  protected def newPropose(desc: Proposal.Descriptor, body: In => Unit): Out
  protected def newCarry(cont: With[In] => Rule): Out

  class _propose(desc: Proposal.Descriptor) {
    def As(body: In => Unit): Out = newPropose(desc, body)
  }

  def Do(body: In => Unit): Out = newDo(body)
  def Propose(name: Proposal.Descriptor) = new _propose(name)

  def Carry(cont: With[In] => Rule): Out = newCarry(cont)

}

trait PartialCondition[A, B] extends ActionPhrase[A, PartialRule[B]] {

  object _on_success extends ActionPhrase[A, PartialRule[B]] {
    override protected def newDo(body: A => Unit) = ???
    override protected def newCarry(cont: (With[A]) => Rule) = ???
    override protected def newPropose(desc: Proposal.Descriptor, body: A => Unit) = ???
  }

  def On(w: Words.SuccessWord.type) = _on_success

}

trait IfTrueCondition[A] {

  object _on_success extends ActionPhrase[A, IfTrueRule[A]] {
    override protected def newDo(body: A => Unit) = ???
    override protected def newCarry(cont: (With[A]) => Rule) = ???
    override protected def newPropose(desc: Proposal.Descriptor, body: A => Unit) = ???
  }

  def On(w: Words.SuccessWord.type) = _on_success

}

trait Rule {

  def apply(env: Env) = ???

}

trait PartialRule[B] extends Rule {

  object _on_failure extends ActionPhrase[B, TotalRule] {
    override protected def newDo(body: B => Unit) = ???
    override protected def newCarry(cont: (With[B]) => Rule) = ???
    override protected def newPropose(desc: Proposal.Descriptor, body: B => Unit) = ???
  }

  def On(w: Words.FailureWord.type) = _on_failure

}

trait IfTrueRule[A] extends Rule {
  object _on_failure extends ActionPhrase[A, TotalRule] {
    override protected def newDo(body: A => Unit) = ???
    override protected def newCarry(cont: (With[A]) => Rule) = ???
    override protected def newPropose(desc: Proposal.Descriptor, body: A => Unit) = ???
  }

  def On(w: Words.FailureWord.type) = _on_failure

}

trait TotalRule extends Rule {

}

trait With[A] extends ActionPhrase[A, TotalRule] {

  def Filter(predicate: A => Boolean): IfTrueCondition[A] = ??? //IfDefined[A] { case a if f(a) => a }
  def Collect[B](func: PartialFunction[A, B]): PartialCondition[B, A]

  override protected def newDo(body: A => Unit) = ???
  override protected def newCarry(cont: (With[A]) => Rule) = ???
  override protected def newPropose(desc: Proposal.Descriptor, body: A => Unit) = ???

}

object With {
  def apply[A](cond: => A): With[A] = ???
}

trait PlainWords {
  import Words._

  protected val Success = SuccessWord
  protected val Failure = FailureWord

}