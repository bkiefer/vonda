package de.dfki.mlt.rudimant.dsl

import de.dfki.mlt.rudimant.Proposal

trait Env

object Words {
  object SuccessWord
  object FailureWord
}

trait PartialCondition[A, B]  {

  object _success {
    object _propose {
      def As(body: A => Unit): PartialRule[B] = ???
    }

    def Do(body: A => Unit): PartialRule[B] = ???
    def Propose(name: Proposal.Descriptor) = _propose
    def Carry(cont: With[A] => Rule): PartialRule[B] = ???
  }

  def On(w: Words.SuccessWord.type) = _success

}

trait IfTrueCondition[A] {
  object _success {

    object _propose {
      def As(body: A => Unit): IfTrueRule[A] = ???
    }

    def Do(body: A => Unit): IfTrueRule[A] = ???
    def Propose(name: Proposal.Descriptor) = _propose
    def Carry(cont: With[A] => Rule): IfTrueRule[A] = ???
  }

  def On(w: Words.SuccessWord.type) = _success

}

trait Rule {

  def apply(env: Env) = ???

}

trait PartialRule[B] extends Rule {

  object _failure {
    object _propose {
      def As(body: B => Unit): TotalRule = ???
    }

    def Do(b: B => Unit): TotalRule = ???
    def Propose(name: Proposal.Descriptor) = _propose
    def Carry(cont: With[B] => Rule): TotalRule = ???
  }

  def On(w: Words.FailureWord.type) = _failure

}

trait IfTrueRule[A] extends Rule {
  object _failure {
    object _propose {
      def As(body: A => Unit): TotalRule = ???
    }

    def Do(b: A => Unit): TotalRule = ???
    def Propose(name: Proposal.Descriptor) = _propose
    def Carry(cont: With[A] => Rule): TotalRule = ???
  }

  def On(w: Words.FailureWord.type) = _failure

}

trait TotalRule extends Rule {

}

trait With[A] {
  def Filter(predicate: A => Boolean): IfTrueCondition[A] = ??? //IfDefined[A] { case a if f(a) => a }
  def Collect[B](func: PartialFunction[A, B]): PartialCondition[B, A]

  def Do(body: A => Unit): TotalRule = ???

  object _propose {
    def As(body: A => Unit): TotalRule = ???
  }

  def Propose(desc: Proposal.Descriptor) = _propose

}

object With {
  def apply[A](cond: => A): With[A] = ???
}

trait PlainWords {
  import Words._

  protected val Success = SuccessWord
  protected val Failure = FailureWord

}