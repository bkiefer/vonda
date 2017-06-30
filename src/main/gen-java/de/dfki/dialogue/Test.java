package de.dfki.dialogue;
import java.util.*;

import de.dfki.mlt.rudimant.agent.DialogueAct;
import de.dfki.lt.hfc.db.rdfProxy.*;
import de.dfki.lt.hfc.types.*;
import de.dfki.dialogue.Foo;
public class Test extends de.dfki.dialogue.Foo {
public Test()
{
  super();
}

Rdf reply;
public boolean process()
{
  reply = myLastDA().copy();
  return false;
}
}
