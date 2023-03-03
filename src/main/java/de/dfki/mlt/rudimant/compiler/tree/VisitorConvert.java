package de.dfki.mlt.rudimant.compiler.tree;

import java.io.IOException;
import java.io.Writer;

import de.dfki.mlt.rudimant.common.Location;
import de.dfki.mlt.rudimant.common.Position;
import de.dfki.mlt.rudimant.compiler.RudimantCompiler;
import de.dfki.mlt.rudimant.compiler.TokenHandler;

public class VisitorConvert {

  private RudimantCompiler rudi;
  private TokenHandler _th;
  private Writer _out;

  public VisitorConvert(Writer w, TokenHandler th, RudimantCompiler c) {
    _out = w;
    _th = th;
    rudi = c;
  }

  public void out(String s) {
    try {
      _out.write(s);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public void convert(GrammarFile gf) {
    Location loc = null;
    for (RudiTree r : gf.getDtrs()) {
      convertRec(r);
      loc = r.getLocation();
    }

    _th.finalComments(_out, loc.getEnd());
  }

  private boolean convertRec(RudiTree r) {
    // spit out all comment and white space tokens before me and delete them
    // from the tokens list
    _th.spitOut(_out, r.getLocation().getBegin().getCharpos());
    if (r instanceof ExpCast) {
      ExpCast cast = (ExpCast)r;
      out("isa(");
      if (cast.type.isRdfType()) {
        out(cast.getType().get_name());
      } else {
        out(cast.type.toString());
      }
      out(", ");
      convertRec(cast.expression);
      out(")");
      return true;
    } else if (r instanceof ExpLambda) {
      ExpLambda l = (ExpLambda)r;
      out("lambda(");
      for (String p : l.parameters) {
        if (p != l.parameters.get(0)) {
          out(", ");
        }
        out(p);
      }
      out(")");
      convertRec(l.body);
      return true;
    } else if (r instanceof Import) {
      out("include ");
      Import i = (Import) r;

      for (int j = 0; j < i.path.length; ++j) {
        out(i.path[j]); out(".");
      }
      out(i.name);
      out(";");

      try {
        rudi.processImport(i.name, i.path, i.location);
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

      return true;
    }
    if (r.getDtrs().iterator().hasNext()) {
      Position pos = r.location.getBegin();
      for (RudiTree d : r.getDtrs()) {
        _th.outToken(_out, pos, d.location.getBegin());
        convertRec(d);
        pos = d.location.getEnd();
      }
      _th.outToken(_out, pos, r.location.getEnd());
    } else {
      out(_th.getFullText(r));
    }
    return true;

  }

}
