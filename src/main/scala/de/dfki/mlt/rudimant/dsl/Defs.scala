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
  protected def newCarry(cont: Selector[In] => Rule): Out

  class _propose(desc: Proposal.Descriptor) {
    def As(body: In => Unit): Out = newPropose(desc, body)
  }

  def Do(body: In => Unit): Out = newDo(body)
  def Propose(name: Proposal.Descriptor) = new _propose(name)

  def Carry(cont: Selector[In] => Rule): Out = newCarry(cont)

}

trait HasThen[In, Out] {

  object _on_success extends ActionPhrase[In, Out] {
    override protected def newDo(body: In => Unit) = ???
    override protected def newCarry(cont: (Selector[In]) => Rule) = ???
    override protected def newPropose(desc: Proposal.Descriptor, body: In => Unit) = ???
  }

  def On(w: Words.SuccessWord.type) = _on_success

}

trait HasElse[In, Out] extends Rule {

  object _on_failure extends ActionPhrase[In, Out] {
    override protected def newDo(body: In => Unit) = ???
    override protected def newCarry(cont: (Selector[In]) => Rule) = ???
    override protected def newPropose(desc: Proposal.Descriptor, body: In => Unit) = ???
  }

  def On(w: Words.FailureWord.type) = _on_failure

}

trait Rule {

  def apply(env: Env) = ???

}

trait TotalRule extends Rule {

}

trait Selector[A] extends ActionPhrase[A, TotalRule] {

  def Filter(predicate: A => Boolean): Rule with HasThen[A, Rule with HasElse[A, Rule]] = ???
  def Collect[B](func: PartialFunction[A, B]): Rule with HasThen[B, Rule with HasElse[A, Rule]] = ???

  override protected def newDo(body: A => Unit) = ???
  override protected def newCarry(cont: (Selector[A]) => Rule) = ???
  override protected def newPropose(desc: Proposal.Descriptor, body: A => Unit) = ???

}

object Selector {

  case class Base[A](get: () => A) extends Selector[A]

}

trait PlainWords {
  import Words._

  protected val Success = SuccessWord
  protected val Failure = FailureWord

}