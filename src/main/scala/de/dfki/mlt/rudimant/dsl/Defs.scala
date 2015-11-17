package de.dfki.mlt.rudimant.dsl

import de.dfki.mlt.rudimant.Proposal

trait Env

object Words {
  object DoWord
  object ThenWord
  object ElseWord
  object SuccessWord
  object FailureWord
  object IfWord
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
    def Carry(cont: With[A] => Rule): IfTrueRule[A] = ???
  }

  def On(w: Words.SuccessWord.type) = _success

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

trait Rule {

  def apply(env: Env) = ???

}

trait PartialRule[B] extends Rule {
//  def Else(b: Action[B]): TotalRule = ???

  object _failure {
    object _propose {
      def As(body: B => Unit): TotalRule = ???
    }

    def Do(b: B => Unit): TotalRule = ???
    def Propose(name: Proposal.Descriptor) = _propose
    def Carry(cont: With[B] => Rule): TotalRule = ???
  }

  def On(w: Words.FailureWord.type) = _failure

  def Else(w: Words.DoWord.type) = _failure

  def End = this

//  lazy val Else = new _Else

//  def Else() = elsie

//  def Else(): PartialRuleElse[B] = ???

//  val Else

//  def ElseDo(b: B => Unit) = Else(Action.DoAction(b))
//
//  def -->(b: Action[B]) = Else(b)
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

  def Else(b: Action[A]): TotalRule = ???
  def -->(b: Action[A]) = Else(b)
  def -:(b: Action[A]) = Else(b)
  def :>(b: Action[A]) = Else(b)
}

trait TotalRule extends Rule {

}

trait WithIf[A] {
  def True(f: A => Boolean): IfTrueCondition[A] = ???
  def Defined[B](f: PartialFunction[A, B]): PartialCondition[B, A]
}

trait With[A] {
  def Filter(predicate: A => Boolean): IfTrueCondition[A] = ??? //IfDefined[A] { case a if f(a) => a }
  def Collect[B](func: PartialFunction[A, B]): PartialCondition[B, A]

  def -?>(f: A => Boolean) = Filter(f)
  def -:>[B](f: PartialFunction[A, B]) = Collect(f)

  def Test(w: Words.IfWord.type): WithIf[A] = ???

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

trait PlainWords {
  import Words._

  protected val Do = DoWord
  protected val Then = ThenWord
  protected val Else = ElseWord
  protected val Success = SuccessWord
  protected val Failure = FailureWord
  protected val If = IfWord
}