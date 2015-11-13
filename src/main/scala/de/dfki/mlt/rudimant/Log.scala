package de.dfki.mlt.rudimant

import java.io.{PrintStream, PrintWriter}

trait Log {

  def debug(msg: String)
  def debug(template: String, arg1: Any) { debug(expandTemplate(template, arg1)) }
  def debug(template: String, arg1: Any, arg2: Any) { debug(expandTemplate(template, arg1, arg2)) }
  def debug(template: String, arg1: Any, arg2: Any, arg3: Any) { debug(expandTemplate(template, arg1, arg2, arg3)) }
  def debug(template: String, arg1: Any, arg2: Any, arg3: Any, arg4: Any) { debug(expandTemplate(template, arg1, arg2, arg3, arg4)) }

  def info(msg: String)
  def info(template: String, arg1: Any) { info(expandTemplate(template, arg1)) }
  def info(template: String, arg1: Any, arg2: Any) { info(expandTemplate(template, arg1, arg2)) }
  def info(template: String, arg1: Any, arg2: Any, arg3: Any) { info(expandTemplate(template, arg1, arg2, arg3)) }
  def info(template: String, arg1: Any, arg2: Any, arg3: Any, arg4: Any) { info(expandTemplate(template, arg1, arg2, arg3, arg4)) }

  def warning(msg: String)
  def warning(template: String, arg1: Any) { warning(expandTemplate(template, arg1)) }
  def warning(template: String, arg1: Any, arg2: Any) { warning(expandTemplate(template, arg1, arg2)) }
  def warning(template: String, arg1: Any, arg2: Any, arg3: Any) { warning(expandTemplate(template, arg1, arg2, arg3)) }
  def warning(template: String, arg1: Any, arg2: Any, arg3: Any, arg4: Any) { warning(expandTemplate(template, arg1, arg2, arg3, arg4)) }

  def error(ex: Throwable, msg: String)

  protected def expandTemplate(template: String, args: Any*) = template.replace("{}", "%s").format(args:_*)

}

object Log {

  private class PrintStreamLog(out: PrintStream, prefix: String) extends Log {

    require (prefix != null)

    def pre(level: String): Unit = {
      out.print(level)
      out.print(" [")
      out.print(prefix)
      out.print("]: ")
    }

    def debug(msg: String) = {
      pre("DEBUG")
      out.println(msg)
    }
    def info(msg: String) = {
      pre("INFO ")
      out.println(msg)
    }
    def warning(msg: String) = {
      pre("WARN ")
      out.println(msg)
    }
    def error(ex: Throwable, msg: String) = {
      pre("ERROR")
      out.println(msg)
      out.println(ex.getMessage)
      out.println(ex.getStackTrace)
    }
  }

   def apply(clazz: Class[_]): Log = new PrintStreamLog(System.out, clazz.getCanonicalName)

}
