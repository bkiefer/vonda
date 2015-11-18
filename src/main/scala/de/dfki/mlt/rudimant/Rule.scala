package de.dfki.mlt.rudimant

import de.dfki.mlt.rudimant.RuleEngine.Env

trait Rule {

  def eval(env: RuleEngine.Env): Option[Action]

}

object Rule {

  trait ValueSink[-A] {
    def accept(value: A): Option[Action]
  }

  case class Root[A](
      get: () => A,
      next: Option[ValueSink[A]])
    extends Rule {

    require (get != null)
    require (next != null)

    override def eval(env: Env) = {
      next match {
        case Some(sk) => sk.accept(get())
        case _ => None
      }
    }
  }

  case class Filter[A](
      predicate: A => Boolean,
      thenBranch: Option[ValueSink[A]],
      elseBranch: Option[ValueSink[A]])
    extends ValueSink[A] {

    require (predicate != null)
    require (thenBranch != null)
    require (elseBranch != null)

    override def accept(value: A) = (predicate(value), thenBranch, elseBranch) match {
      case (true, Some(sk), _) => sk.accept(value)
      case (false, _, Some(sk)) => sk.accept(value)
      case _ => None
    }
  }

  case class Collect[A, B](
      pf: PartialFunction[A, B],
      thenBranch: Option[ValueSink[B]],
      elseBranch: Option[ValueSink[A]])
    extends ValueSink[A] {

    require (pf != null)
    require (thenBranch != null)
    require (elseBranch != null)

    override def accept(value: A) = (pf, thenBranch, elseBranch) match {
      case (f, Some(sk), _) if f.isDefinedAt(value) => sk.accept(f(value))
      case (_, _, Some(sk)) => sk.accept(value)
      case _ => None
    }
  }

  trait EmitAction[A] extends ValueSink[A]

  case class Do[A](
      body: A => Unit)
    extends EmitAction[A] {

    require (body != null)

    override def accept(value: A) = Some(Deferred({ () => body(value) }))
  }

  case class Propose[A](
      desc: Proposal.Descriptor,
      body: A => Unit)
    extends EmitAction[A] {

    require (body != null)

    override def accept(value: A) = Some(Proposal(desc, { () => body(value) }))
  }


}