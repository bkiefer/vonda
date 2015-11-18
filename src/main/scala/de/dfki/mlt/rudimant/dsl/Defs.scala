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

trait Rule {
  def eval(env: Env): Action
}

trait Materialisable {
  def mat: Rule
}

trait Producer[A] {

}

trait Consumer[-A] {
  def eval(value: A): Action
}

trait Selector[A] extends ActionPhrase[A, Materialisable] {

  def Filter(predicate: A => Boolean): Materialisable with Consumer[A] with HasThen[A, Materialisable with Consumer[A] with HasElse[A, Materialisable with Consumer[A]]]

  def Collect[B](func: PartialFunction[A, B]): Materialisable with Consumer[A] with HasThen[B, Materialisable with Consumer[A] with HasElse[A, Materialisable with Consumer[A]]]

}

object Selector {

  sealed trait ActionConsumer
  
  case object NoConsumer extends Consumer[Any] with ActionConsumer {
    override def eval(value: Any) = NoAction
  }
  
  case class DeferConsumer[A](body: A => Unit) extends Consumer[A] with ActionConsumer {
    override def eval(value: A) = Deferred({ () => body(value) })
  }
  
  case class ProposeConsumer[A](desc: Proposal.Descriptor, body: A => Unit) extends Consumer[A] with ActionConsumer {
    override def eval(value: A) = Proposal(desc, { () => body(value) })
  }

  case class CarryConsumer[A](consumer: Consumer[A]) extends Consumer[A] {
    override def eval(value: A) = consumer.eval(value)
  }
  
  case class RootNode[A](
      get: () => A,
      follow: Consumer[A] = NoConsumer)
    extends Materialisable
      with Rule
      with Selector[A]
      with Producer[A] {

    override def mat = this

    override def eval(env: Env) = follow.eval(get())

    override def Filter(predicate: (A) => Boolean) = {
      FilterNode[A]({ child => RootNode(get, child) }, predicate)
    }

    override def Collect[B](pf: PartialFunction[A, B]) = {
      CollectNode[A, B]({ child => RootNode(get, child) }, pf)
    }

    override protected def newDo(body: A => Unit) = {
      RootNode[A](get, DeferConsumer(body))
    }

    override protected def newPropose(desc: Proposal.Descriptor, body: A => Unit) = {
      RootNode[A](get, ProposeConsumer(desc, body))
    }

    override protected def newCarry(cont: Selector[A] => Consumer[A]) = {
      RootNode[A](get, CarryConsumer(cont(this)))
    }

  }

  case class FilterNode[A](
      mkParent: FilterNode[A] => Materialisable,
      pred: A => Boolean,
      thenBranch: Consumer[A] = NoConsumer,
      elseBranch: Consumer[A] = NoConsumer)
    extends Materialisable
      with Consumer[A]
      with HasThen[A, FilterNode[A]]
      with HasElse[A, FilterNode[A]] {

    def parent = mkParent(this)

    override def mat = parent.mat

    override def eval(value: A) = if (pred(value)) thenBranch.eval(value) else elseBranch.eval(value)

    protected def toSelector: Selector[A] = ???

    override protected def _then_do(body: (A) => Unit) = {
      FilterNode[A]({ child => mkParent(child) }, pred, DeferConsumer(body), elseBranch)
    }

    override protected def _then_propose(desc: Descriptor, body: (A) => Unit) = {
      FilterNode[A]({ child => mkParent(child) }, pred, ProposeConsumer(desc, body), elseBranch)
    }

    override protected def _then_carry(cont: Selector[A] => Consumer[A]) = {
      FilterNode[A]({ child => mkParent(child) }, pred, CarryConsumer(cont(this.toSelector)), elseBranch)
    }

    override protected def _else_do(body: (A) => Unit) = {
      FilterNode[A]({ child => mkParent(child) }, pred, thenBranch, elseBranch)
    }

    override protected def _else_propose(desc: Descriptor, body: (A) => Unit) = {
      FilterNode[A]({ child => mkParent(child) }, pred, thenBranch, ProposeConsumer(desc, body))
    }

    override protected def _else_carry(cont: Selector[A] => Consumer[A]) = {
      FilterNode[A]({ child => mkParent(child) }, pred, thenBranch, CarryConsumer(cont(this.toSelector)))
    }

  }

  case class CollectNode[A, B](
      mkParent: CollectNode[A, B] => Materialisable,
      pf: PartialFunction[A, B],
      thenBranch: Consumer[B] = NoConsumer,
      elseBranch: Consumer[A] = NoConsumer)
    extends Materialisable
      with Consumer[A]
      with HasThen[B, CollectNode[A, B]]
      with HasElse[A, CollectNode[A, B]] {

    def parent = mkParent(this)

    override def mat = parent.mat

    override def eval(value: A) = {
      if (pf.isDefinedAt(value)) { thenBranch.eval(pf.apply(value)) } else { elseBranch.eval(value) }
    }

    protected def toThenSelector: Selector[B] = ???
    protected def toElseSelector: Selector[A] = ???

    override protected def _then_do(body: (B) => Unit) = {
      CollectNode[A, B]({ child => mkParent(child) }, pf, DeferConsumer(body), elseBranch)
    }

    override protected def _then_propose(desc: Descriptor, body: (B) => Unit) = {
      CollectNode[A, B]({ child => mkParent(child) }, pf, ProposeConsumer(desc, body), elseBranch)
    }

    override protected def _then_carry(cont: Selector[B] => Consumer[B]) = {
      CollectNode[A, B]({ child => mkParent(child) }, pf, CarryConsumer(cont(this.toThenSelector)), elseBranch)
    }

    override protected def _else_do(body: (A) => Unit) = {
      CollectNode[A, B]({ child => mkParent(child) }, pf, thenBranch, DeferConsumer(body))
    }

    override protected def _else_propose(desc: Descriptor, body: (A) => Unit) = {
      CollectNode[A, B]({ child => mkParent(child) }, pf, thenBranch, ProposeConsumer(desc, body))
    }

    override protected def _else_carry(cont: Selector[A] => Consumer[A]) = {
      CollectNode[A, B]({ child => mkParent(child) }, pf, thenBranch, CarryConsumer(cont(this.toElseSelector)))
    }

  }

}

trait PlainWords {
  import Words._

  protected val Success = SuccessWord
  protected val Failure = FailureWord

}