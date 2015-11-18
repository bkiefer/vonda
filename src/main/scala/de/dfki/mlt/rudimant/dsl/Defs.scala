package de.dfki.mlt.rudimant.dsl

import de.dfki.mlt.rudimant.Proposal.Descriptor
import de.dfki.mlt.rudimant.Rule.ValueSink
import de.dfki.mlt.rudimant._

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

trait Producer[A] {

}

trait Consumer[-A] {
  def toValueSink: ValueSink[A]
}

trait Selector[A] extends ActionPhrase[A, Materialisable] {

  def Filter(predicate: A => Boolean): Materialisable with Consumer[A] with HasThen[A, Materialisable with Consumer[A] with HasElse[A, Materialisable with Consumer[A]]]

  def Collect[B](func: PartialFunction[A, B]): Materialisable with Consumer[A] with HasThen[B, Materialisable with Consumer[A] with HasElse[A, Materialisable with Consumer[A]]]

}

object Selector {

  case class ActionNode[A](act: Rule.EmitAction[A]) extends Consumer[A] {
    override def toValueSink = act
  }
  
  case class RootNode[A](
      get: () => A,
      follow: Option[Consumer[A]] = None)
    extends Materialisable
      with Selector[A]
      with Producer[A] {

    override def mat = Rule.Root(get, follow map { _.toValueSink })

    override def Filter(predicate: (A) => Boolean) = {
      FilterNode[A]({ child => RootNode(get, Some(child)) }, predicate)
    }

    override def Collect[B](pf: PartialFunction[A, B]) = {
      CollectNode[A, B]({ child => RootNode(get, Some(child)) }, pf)
    }

    override protected def newDo(body: A => Unit) = {
      RootNode[A](get, Some(ActionNode(Rule.Do(body))))
    }

    override protected def newPropose(desc: Proposal.Descriptor, body: A => Unit) = {
      RootNode[A](get, Some(ActionNode(Rule.Propose(desc, body))))
    }

    override protected def newCarry(cont: Selector[A] => Consumer[A]) = {
      RootNode[A](get, Some(cont(this)))
    }

  }

  case class FilterNode[A](
      mkParent: FilterNode[A] => Materialisable,
      pred: A => Boolean,
      thenBranch: Option[Consumer[A]] = None,
      elseBranch: Option[Consumer[A]] = None)
    extends Materialisable
      with Consumer[A]
      with HasThen[A, FilterNode[A]]
      with HasElse[A, FilterNode[A]] {

    override def mat = mkParent(this).mat

    override def toValueSink = Rule.Filter(pred, thenBranch map { _.toValueSink }, elseBranch map { _.toValueSink })

    protected def toSelector: Selector[A] = ???

    override protected def _then_do(body: (A) => Unit) = {
      FilterNode[A]({ child => mkParent(child) }, pred, Some(ActionNode(Rule.Do(body))), elseBranch)
    }

    override protected def _then_propose(desc: Descriptor, body: (A) => Unit) = {
      FilterNode[A]({ child => mkParent(child) }, pred, Some(ActionNode(Rule.Propose(desc, body))), elseBranch)
    }

    override protected def _then_carry(cont: Selector[A] => Consumer[A]) = {
      FilterNode[A]({ child => mkParent(child) }, pred, Some(cont(this.toSelector)), elseBranch)
    }

    override protected def _else_do(body: (A) => Unit) = {
      FilterNode[A]({ child => mkParent(child) }, pred, thenBranch, Some(ActionNode(Rule.Do(body))))
    }

    override protected def _else_propose(desc: Descriptor, body: (A) => Unit) = {
      FilterNode[A]({ child => mkParent(child) }, pred, thenBranch, Some(ActionNode(Rule.Propose(desc, body))))
    }

    override protected def _else_carry(cont: Selector[A] => Consumer[A]) = {
      FilterNode[A]({ child => mkParent(child) }, pred, thenBranch, Some(cont(this.toSelector)))
    }

  }

  case class CollectNode[A, B](
      mkParent: CollectNode[A, B] => Materialisable,
      pf: PartialFunction[A, B],
      thenBranch: Option[Consumer[B]] = None,
      elseBranch: Option[Consumer[A]] = None)
    extends Materialisable
      with Consumer[A]
      with HasThen[B, CollectNode[A, B]]
      with HasElse[A, CollectNode[A, B]] {

    override def mat = mkParent(this).mat

    override def toValueSink = Rule.Collect(pf, thenBranch map { _.toValueSink }, elseBranch map { _.toValueSink })

    protected def toThenSelector: Selector[B] = ???
    protected def toElseSelector: Selector[A] = ???

    override protected def _then_do(body: (B) => Unit) = {
      CollectNode[A, B]({ child => mkParent(child) }, pf, Some(ActionNode(Rule.Do(body))), elseBranch)
    }

    override protected def _then_propose(desc: Descriptor, body: (B) => Unit) = {
      CollectNode[A, B]({ child => mkParent(child) }, pf, Some(ActionNode(Rule.Propose(desc, body))), elseBranch)
    }

    override protected def _then_carry(cont: Selector[B] => Consumer[B]) = {
      CollectNode[A, B]({ child => mkParent(child) }, pf, Some(cont(this.toThenSelector)), elseBranch)
    }

    override protected def _else_do(body: (A) => Unit) = {
      CollectNode[A, B]({ child => mkParent(child) }, pf, thenBranch, Some(ActionNode(Rule.Do(body))))
    }

    override protected def _else_propose(desc: Descriptor, body: (A) => Unit) = {
      CollectNode[A, B]({ child => mkParent(child) }, pf, thenBranch, Some(ActionNode(Rule.Propose(desc, body))))
    }

    override protected def _else_carry(cont: Selector[A] => Consumer[A]) = {
      CollectNode[A, B]({ child => mkParent(child) }, pf, thenBranch, Some(cont(this.toElseSelector)))
    }

  }

}

trait PlainWords {
  import Words._

  protected val Success = SuccessWord
  protected val Failure = FailureWord

}