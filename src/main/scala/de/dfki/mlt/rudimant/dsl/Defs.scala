package de.dfki.mlt.rudimant.dsl

import de.dfki.mlt.rudimant.Proposal.Descriptor
import de.dfki.mlt.rudimant.{Deferred, NoAction, Action, Proposal}

trait Env

object Words {
  object SuccessWord
  object FailureWord
}

trait ActionPhrase[In, +Out] {

  protected def newDo(body: In => Unit): Out
  protected def newPropose(desc: Proposal.Descriptor, body: In => Unit): Out
  protected def newCarry(cont: Selector[In] => Consumer[In]): Out

  class _propose(desc: Proposal.Descriptor) {
    def As(body: In => Unit): Out = newPropose(desc, body)
  }

  def Do(body: In => Unit): Out = newDo(body)
  def Propose(name: Proposal.Descriptor) = new _propose(name)

  def Carry(cont: Selector[In] => Consumer[In]): Out = newCarry(cont)

}

trait HasThen[In, +Out] {

  protected def _then_do(body: In => Unit): Out
  protected def _then_propose(desc: Proposal.Descriptor, body: In => Unit): Out
  protected def _then_carry(cont: Selector[In] => Consumer[In]): Out

  object _on_success extends ActionPhrase[In, Out] {
    override protected def newDo(body: In => Unit) = _then_do(body)
    override protected def newCarry(cont: (Selector[In]) => Consumer[In]) = _then_carry(cont)
    override protected def newPropose(desc: Proposal.Descriptor, body: In => Unit) = _then_propose(desc, body)
  }

  def On(w: Words.SuccessWord.type) = _on_success

}

trait HasElse[In, +Out] {

  protected def _else_do(body: In => Unit): Out
  protected def _else_propose(desc: Proposal.Descriptor, body: In => Unit): Out
  protected def _else_carry(cont: Selector[In] => Consumer[In]): Out

  object _on_failure extends ActionPhrase[In, Out] {
    override protected def newDo(body: In => Unit) = _else_do(body)
    override protected def newCarry(cont: (Selector[In]) => Consumer[In]) = _else_carry(cont)
    override protected def newPropose(desc: Proposal.Descriptor, body: In => Unit) = _else_propose(desc, body)
  }

  def On(w: Words.FailureWord.type) = _on_failure

}

trait Materialisable {
  def mat: Rule
}

trait Rule {

  def eval(env: Env): Action

}

trait TotalRule extends Rule {

}

trait Producer[A] {

}

trait Consumer[-A] {
  def eval(value: A): Action
}

trait ActionConsumer

case object NoConsumer extends Consumer[Any] with ActionConsumer {
  override def eval(value: Any) = NoAction
}

case class DeferConsumer[A](body: A => Unit) extends Consumer[A] with ActionConsumer {
  override def eval(value: A) = Deferred({ () => body(value) })
}

case class ProposeConsumer[A](desc: Proposal.Descriptor, body: A => Unit) extends Consumer[A] with ActionConsumer {
  override def eval(value: A) = Proposal(desc, { () => body(value) })
}

case class _Get[A](get: () => A, consumer: Consumer[A]) extends Rule with Materialisable with AcceptsFilter[_Get[A], A] {
  override def eval(env: Env) = consumer.eval(get())
  override def mat = this

  override def withFilter(fil: _Filter[A]) = copy(consumer = fil)
}

trait AcceptsFilter[Self, A] {
  def withFilter(fil: _Filter[A]): Self
}

trait AcceptsThenElse[Self, A, B] {
  def withThen(c: Consumer[A]): Self
  def withElse(c: Consumer[B]): Self
}

case class _Carry[A](consumer: Consumer[A]) extends Consumer[A] {
  override def eval(value: A) = consumer.eval(value)
}

case class _Map[A, B](func: A => B, consumer: Consumer[B]) extends Consumer[A] {
  override def eval(value: A) = consumer.eval(func(value))
}

case class _Filter[A](pred: A => Boolean, thenBranch: Consumer[A], elseBranch: Consumer[A])
    extends Consumer[A]
    with AcceptsThenElse[_Filter[A], A, A] {
  override def eval(value: A) = if (pred(value)) thenBranch.eval(value) else elseBranch.eval(value)

  override def withThen(c: Consumer[A]) = copy(thenBranch = c)
  override def withElse(c: Consumer[A]) = copy(elseBranch = c)
}

case class _Collect[A, B](pf: PartialFunction[A, B], succBranch: Consumer[B], failBranch: Consumer[A]) extends Consumer[A] {
  override def eval(value: A) = {
    if (pf.isDefinedAt(value)) { succBranch.eval(pf.apply(value)) } else { failBranch.eval(value) }
  }
}

trait Selector[A] extends ActionPhrase[A, Materialisable] {

  def Filter(predicate: A => Boolean): Materialisable with Consumer[A] with HasThen[A, Materialisable with Consumer[A] with HasElse[A, Materialisable with Consumer[A]]]

  def Collect[B](func: PartialFunction[A, B]): Materialisable with Consumer[A] with HasThen[B, Materialisable with Consumer[A] with HasElse[A, Materialisable with Consumer[A]]]

}

object Selector {

  trait __Self[A] {
    def self: Consumer[A]
  }

  class __Defer[A](body: A => Unit) extends __Self[A] {
    override def self = DeferConsumer(body)
  }

  class __Propose[A](desc: Descriptor, body: A => Unit) extends __Self[A] {
    override def self = ProposeConsumer(desc, body)
  }

  case class Base[A](
      get: () => A,
      follow: Consumer[A])
    extends Materialisable
      with Selector[A]
      with Producer[A] { out =>

    override def mat = _Get(get, follow)

    override def Filter(predicate: (A) => Boolean) = {
      __Filter[A]({ child => Base(get, child) }, predicate, NoConsumer, NoConsumer)
    }

    override def Collect[B](pf: PartialFunction[A, B]) = {
      __Collect[A, B]({ child => Base(get, child) }, pf, NoConsumer, NoConsumer)
    }

    override protected def newDo(body: A => Unit) = {
      Base[A](get, DeferConsumer(body))
    }

    override protected def newPropose(desc: Proposal.Descriptor, body: A => Unit) = {
      Base[A](get, ProposeConsumer(desc, body))
    }

    override protected def newCarry(cont: Selector[A] => Consumer[A]) = {
      Base[A](get, _Carry(cont(this)))
    }

  }

  object Base {
    def apply[A](get: () => A): Base[A] = Base[A](get, NoConsumer)
  }

  case class __Filter[A](
      mkParent: __Filter[A] => Materialisable,
      pred: A => Boolean,
      thenBranch: Consumer[A],
      elseBranch: Consumer[A])
    extends Materialisable
      with Consumer[A]
      with HasThen[A, __Filter[A]]
      with HasElse[A, __Filter[A]] {

    def parent = mkParent(this)

    override def mat = parent.mat

    override def eval(value: A) = if (pred(value)) thenBranch.eval(value) else elseBranch.eval(value)

    override protected def _then_do(body: (A) => Unit) = {
      __Filter[A]({ child => mkParent(child) }, pred, DeferConsumer(body), elseBranch)
    }

    override protected def _then_propose(desc: Descriptor, body: (A) => Unit) = {
      __Filter[A]({ child => mkParent(child) }, pred, ProposeConsumer(desc, body), elseBranch)
    }

    override protected def _then_carry(cont: Selector[A] => Consumer[A]) = ???

    override protected def _else_do(body: (A) => Unit) = {
      __Filter[A]({ child => mkParent(child) }, pred, thenBranch, elseBranch)
    }

    override protected def _else_propose(desc: Descriptor, body: (A) => Unit) = {
      __Filter[A]({ child => mkParent(child) }, pred, thenBranch, ProposeConsumer(desc, body))
    }

    override protected def _else_carry(cont: Selector[A] => Consumer[A]) = ???

  }

  case class __Collect[A, B](
      mkParent: __Collect[A, B] => Materialisable,
      pf: PartialFunction[A, B],
      thenBranch: Consumer[B],
      elseBranch: Consumer[A])
    extends Materialisable
      with Consumer[A]
      with HasThen[B, __Collect[A, B]]
      with HasElse[A, __Collect[A, B]] {

    def parent = mkParent(this)

    override def mat = parent.mat

    override def eval(value: A) = {
      if (pf.isDefinedAt(value)) { thenBranch.eval(pf.apply(value)) } else { elseBranch.eval(value) }
    }

    override protected def _then_do(body: (B) => Unit) = {
      __Collect[A, B]({ child => mkParent(child) }, pf, DeferConsumer(body), elseBranch)
    }

    override protected def _then_propose(desc: Descriptor, body: (B) => Unit) = {
      __Collect[A, B]({ child => mkParent(child) }, pf, ProposeConsumer(desc, body), elseBranch)
    }

    override protected def _then_carry(cont: Selector[B] => Consumer[B]) = ???

    override protected def _else_do(body: (A) => Unit) = {
      __Collect[A, B]({ child => mkParent(child) }, pf, thenBranch, DeferConsumer(body))
    }

    override protected def _else_propose(desc: Descriptor, body: (A) => Unit) = {
      __Collect[A, B]({ child => mkParent(child) }, pf, thenBranch, ProposeConsumer(desc, body))
    }

    override protected def _else_carry(cont: Selector[A] => Consumer[A]) = ???

  }

}

trait PlainWords {
  import Words._

  protected val Success = SuccessWord
  protected val Failure = FailureWord

}