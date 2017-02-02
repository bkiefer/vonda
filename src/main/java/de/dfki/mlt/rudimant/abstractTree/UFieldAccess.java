/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

import static de.dfki.mlt.rudimant.Constants.DIALOGUE_ACT_TYPE;
import java.util.ArrayList;
import java.util.List;

/**
 * this represents an access to the ontology (will result in an rdf object in
 * output)
 *
 * @author Anna Welker
 */
public class UFieldAccess extends RTExpLeaf {

  List<RudiTree> parts;
  List<String> representation;
  boolean asked = false;
  private List<String> typesToCastTo = new ArrayList<>();

  public UFieldAccess(String fullexp, List<RudiTree> parts, List<String> representation) {
    this.parts = parts;
    this.representation = representation;
    this.fullexp = fullexp;
  }

  /**
   * enter the given type as a type that we have to cast to in generation,
   * because getValue will only return Object
   * @param to
   */
  void addCastTo(String to){
    if(to == null){
      logger.warn("adding cast to null for a field access");
      return;
    }
    if(to.contains("<")){
      to = (DIALOGUE_ACT_TYPE.equals(to))
            ? "DialogueAct" : "Rdf";
    }
    typesToCastTo.add(0, to);
  }

  /**
   * gives a list of types that were encountered to be the runtime types of
   * the getValue calls contained in this fieldaccess; the first element of the
   * list will be the outermost cast!
   * @return
   */
  List<String> getTypesToCast(){
    return this.typesToCastTo;
  }

  @Override
  public void visit(RudiVisitor v) {
    v.visitNode(this);
  }

  @Override
  public String visitStringV(RTStringVisitor v){
    return v.visitNode(this);
  }

  public Iterable<? extends RudiTree> getDtrs() { return parts; }
}
