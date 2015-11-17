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

object DoWord
object ThenWord
object ElseWord
object SuccessWord
object FailureWord
object IfWord

trait PartialCondition[A, B]  {

  object _success {
    object _propose {
      def As(body: A => Unit): PartialRule[B] = ???
    }

    def Do(body: A => Unit): PartialRule[B] = ???
    def Propose(name: Proposal.Descriptor) = _propose
    def Carry(cont: With[A] => _Rule): PartialRule[B] = ???
  }

  def On(w: SuccessWord.type) = _success

//  def Do(body: A => Unit): PartialRule[B] = ???

//  def -+>(action: Action[A]) = Then(action)

//  def ?(consumer: Cons[A, B]): TotalRule = ???

}

trait IfTrueCondition[A] {
  object _success {

    object _propose {
      def As(body: A => Unit): IfTrueRule[A] = ???
    }

    def Do(body: A => Unit): IfTrueRule[A] = ???
    def Propose(name: Proposal.Descriptor) = _propose
    def Carry(cont: With[A] => _Rule): IfTrueRule[A] = ???
  }

  def On(w: SuccessWord.type) = _success

  def Then(action: Action[A]): IfTrueRule[A] = ???

  def Do(body: A => Unit): IfTrueRule[A] = ???

  def -+>(action: Action[A]) = Then(action)
  def +:(action: Action[A]) = Then(action)

  def ?:(action: Action[A]) = Then(action)

//  def THEN(consumer: Cons[A, A]): TotalRule = ???
//  def ?(consumer: Cons[A, A]): TotalRule = ???
  def ?>(action: Action[A]) = Then(action)

//  def Then(cont: With[A] => _Rule): IfTrueRule[A] = ???
}

trait _Rule {

}

trait PartialRule[B] extends _Rule {
//  def Else(b: Action[B]): TotalRule = ???

  object _failure {
    object _propose {
      def As(body: B => Unit): TotalRule = ???
    }

    def Do(b: B => Unit): TotalRule = ???
    def Propose(name: Proposal.Descriptor) = _propose
    def Carry(cont: With[B] => _Rule): TotalRule = ???
  }

  def On(w: FailureWord.type) = _failure

  def Else(w: DoWord.type) = _failure

  def End = this

//  lazy val Else = new _Else

//  def Else() = elsie

//  def Else(): PartialRuleElse[B] = ???

//  val Else

//  def ElseDo(b: B => Unit) = Else(Action.DoAction(b))
//
//  def -->(b: Action[B]) = Else(b)
}

trait IfTrueRule[A] extends _Rule {
  object _failure {
    object _propose {
      def As(body: A => Unit): TotalRule = ???
    }

    def Do(b: A => Unit): TotalRule = ???
    def Propose(name: Proposal.Descriptor) = _propose
    def Carry(cont: With[A] => _Rule): TotalRule = ???
  }

  def On(w: FailureWord.type) = _failure

  def Else(b: Action[A]): TotalRule = ???
  def -->(b: Action[A]) = Else(b)
  def -:(b: Action[A]) = Else(b)
  def :>(b: Action[A]) = Else(b)
}

trait TotalRule extends _Rule {

}

trait WithIf[A] {
  def True(f: A => Boolean): IfTrueCondition[A] = ???
  def Defined[B](f: PartialFunction[A, B]): PartialCondition[B, A]
}

trait With[A] {
  def If(f: A => Boolean): IfTrueCondition[A] = ??? //IfDefined[A] { case a if f(a) => a }
  def IfDefined[B](f: PartialFunction[A, B]): PartialCondition[B, A]

  def -?>(f: A => Boolean) = If(f)
  def -:>[B](f: PartialFunction[A, B]) = IfDefined(f)

  def Test(w: IfWord.type): WithIf[A] = ???

  def Do(body: A => Unit): TotalRule = ???

  object _propose {
    def As(body: A => Unit): TotalRule = ???
  }

  def Propose(desc: Proposal.Descriptor) = _propose

//  def Propose(name: Proposal.Descriptor)(body: A => Unit): TotalRule = ???

}

object With {
  def apply[A](cond: => A): With[A] = ???
}

trait Action[+A] {
//  def Else(body: Action[A]): Action[A] = ???

//  def ::(body: Action[A]): Action[A] = ???

}

object Action {

  case class DoAction[A](body: A => Unit) extends Action[A] {

    def Else(action: Action[A]): Action[A] = ???

  }

  case class ProposeAction[A](desc: Proposal.Descriptor, body: A => Unit) extends Action[A] {
    def toProposal: Proposal = ???
  }

}

//object Do {
//  def apply[A](body: A => Unit) = Action.DoAction(body)
//}

object Re {
  def apply[A](body: With[A] => Unit): Action[A] = ???
}

object Propose {
  case class NamedProposalFactory[A](desc: Proposal.Descriptor) {
    def apply(body: A => Unit) = Action.ProposeAction(desc, body)
  }

  def apply[A](desc: Proposal.Descriptor) = NamedProposalFactory(desc)
}

trait PlainWords {
  protected val Do = DoWord
  protected val Then = ThenWord
  protected val Else = ElseWord
  protected val Success = SuccessWord
  protected val Failure = FailureWord
  protected val If = IfWord
}