package de.dfki.mlt.rudimant.dsl

import de.dfki.mlt.rudimant.Proposal.Descriptor
import de.dfki.mlt.rudimant.{Deferred, NoAction, Action, Proposal}

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

  protected def _then_do(body: In => Unit): Out
  protected def _then_propose(desc: Proposal.Descriptor, body: In => Unit): Out

  object _on_success extends ActionPhrase[In, Out] {
    override protected def newDo(body: In => Unit) = _then_do(body)
    override protected def newCarry(cont: (Selector[In]) => Rule) = ???
    override protected def newPropose(desc: Proposal.Descriptor, body: In => Unit) = _then_propose(desc, body)
  }

  def On(w: Words.SuccessWord.type) = _on_success

}

trait HasElse[In, Out] extends Rule {

  protected def _else_do(body: In => Unit): Out
  protected def _else_propose(desc: Proposal.Descriptor, body: In => Unit): Out
  protected def _else_carry(cont: Selector[In] => Rule): Out

  object _on_failure extends ActionPhrase[In, Out] {
    override protected def newDo(body: In => Unit) = _else_do(body)
    override protected def newCarry(cont: (Selector[In]) => Rule) = _else_carry(cont)
    override protected def newPropose(desc: Proposal.Descriptor, body: In => Unit) = _else_propose(desc, body)
  }

  def On(w: Words.FailureWord.type) = _on_failure

}

trait Materialisable {
  def materialise: Rule
}

trait Rule {

  def eval(env: Env): Action

}

trait TotalRule extends Rule {

}

trait Consumer[-A] {
  def eval(value: A): Action
}

case object NoConsumer extends Consumer[Any] {
  override def eval(value: Any) = NoAction
}

case class DeferConsumer[A](body: A => Unit) extends Consumer[A] {
  override def eval(value: A) = Deferred({ () => body(value) })
}

case class ProposeConsumer[A](desc: Proposal.Descriptor, body: A => Unit) extends Consumer[A] {
  override def eval(value: A) = Proposal(desc, { () => body(value) })
}

case class _Get[A](get: () => A, consumer: Consumer[A]) extends Rule with Materialisable {
  override def eval(env: Env) = consumer.eval(get())
  override def materialise = this
}

case class _Carry[A](consumer: Consumer[A]) extends Consumer[A] {
  override def eval(value: A) = consumer.eval(value)
}

case class _Map[A, B](func: A => B, consumer: Consumer[B]) extends Consumer[A] {
  override def eval(value: A) = consumer.eval(func(value))
}

case class _Filter[A](pred: A => Boolean, thenBranch: Consumer[A], elseBranch: Consumer[A]) extends Consumer[A] {
  override def eval(value: A) = if (pred(value)) thenBranch.eval(value) else elseBranch.eval(value)
}

case class _Collect[A, B](pf: PartialFunction[A, B], succBranch: Consumer[B], failBranch: Consumer[A]) extends Consumer[A] {
  override def eval(value: A) = {
    if (pf.isDefinedAt(value)) { succBranch.eval(pf.apply(value)) } else { failBranch.eval(value) }
  }
}

trait Selector[A] extends ActionPhrase[A, TotalRule] {

  def Filter(predicate: A => Boolean): Rule with HasThen[A, Rule with HasElse[A, Rule]]
  def Collect[B](func: PartialFunction[A, B]): Rule with HasThen[B, Rule with HasElse[A, Rule]] = ???

  override protected def newDo(body: A => Unit) = ???
  override protected def newCarry(cont: (Selector[A]) => Rule) = ???
  override protected def newPropose(desc: Proposal.Descriptor, body: A => Unit) = ???

}

object Selector {

  case class Base[A](get: () => A) extends Selector[A] {

    override def Filter(predicate: (A) => Boolean) = new Rule with HasThen[A, Rule with HasElse[A, Rule]] with Materialisable {
      override def materialise = _Get(get, _Filter(predicate, NoConsumer, NoConsumer))
      @deprecated override def eval(env: Env) = materialise.eval(env)

      override protected def _then_do(thenBody: (A) => Unit) = new Rule with HasElse[A, Rule] with Materialisable {
        override def materialise = _Get(get, _Filter(predicate, DeferConsumer(thenBody), NoConsumer))
        @deprecated override def eval(env: Env) = materialise.eval(env)
        override protected def _else_do(elseBody: (A) => Unit) = _Get(get, _Filter(predicate, DeferConsumer(thenBody), DeferConsumer(elseBody)))
        override protected def _else_propose(desc: Descriptor, elseBody: (A) => Unit) = _Get(get, _Filter(predicate, DeferConsumer(thenBody), ProposeConsumer(desc, elseBody)))
        override protected def _else_carry(cont: (Selector[A]) => Rule) = ???
      }

      override protected def _then_propose(thenDesc: Descriptor, thenBody: (A) => Unit) = new Rule with HasElse[A, Rule] with Materialisable {
        override def materialise = _Get(get, _Filter(predicate, ProposeConsumer(thenDesc, thenBody), NoConsumer))
        @deprecated override def eval(env: Env) = materialise.eval(env)
        override protected def _else_do(elseBody: (A) => Unit) = _Get(get, _Filter(predicate, ProposeConsumer(thenDesc, thenBody), DeferConsumer(elseBody)))
        override protected def _else_propose(elseDesc: Descriptor, elseBody: (A) => Unit) = _Get(get, _Filter(predicate, ProposeConsumer(thenDesc, thenBody), ProposeConsumer(elseDesc, elseBody)))
        override protected def _else_carry(cont: (Selector[A]) => Rule) = ???
      }

    }

//    override def Collect[B](func: PartialFunction[A, B]) = ???
  }

}

trait PlainWords {
  import Words._

  protected val Success = SuccessWord
  protected val Failure = FailureWord

}