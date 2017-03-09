package de.dfki.mlt.rudimant.abstractTree;

import static de.dfki.mlt.rudimant.Visualize.generate;
import static de.dfki.mlt.rudimant.abstractTree.TstUtils.*;
import static org.junit.Assert.*;

import org.junit.*;

public class TestAdditionalEmpty {
  @BeforeClass
  public static void setUpClass() {
    setUpEmpty();
  }

  // exp to statement in if
  @Test
  public void testIfThenExp() {
    String in = "lab: if(true) emitDA(#InitialGreeting(Greet));";
    String exp = "public void lab(){ lab: if (true) "
            + "emitDA(new DialogueAct(\"InitialGreeting(Greet)\")); }";
    String s = generate(in);
    assertEquals(exp, getForMarked(s, exp));
  }

  // exp to statement in else of if
  @Test
  public void testIfThenElseExp() {
    String in = "lab: if(true) b=1; else emitDA(#InitialGreeting(Greet));";
    String exp = "public void lab(){ lab: if (true) int b = 1; "
            + "else emitDA(new DialogueAct(\"InitialGreeting(Greet)\")); }";
    String s = generate(in);
    assertEquals(exp, getForMarked(s, exp));
  }

  // exp to statement in for
  @Test
  public void testForExp() {
    String in = "lab: if(true) for(s : child.sessions) 23;";
    String exp = "public void lab(){ lab: if (true) "
            + "for (Object s_outer : child.sessions) {"
            + " Object s = (Object)s_outer; 23; }";
    String s = generate(in);
    assertEquals(exp, getForMarked(s, exp));
  }

  // TODO: COMPLETE FOR ALL FOR LOOPS, WHILE, AND DO ... WHILE
}
