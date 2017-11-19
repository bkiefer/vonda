package de.dfki.mlt.rudimant.compiler.tree;

import static de.dfki.mlt.rudimant.compiler.Visualize.generate;
import static de.dfki.mlt.rudimant.compiler.tree.TstUtils.*;
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
    String exp = "public int lab(){ lab: if (true) "
            + "emitDA(new DialogueAct(\"InitialGreeting\", \"Greet\"));"
            + " return 0;";
    String s = generate(in);
    assertEquals(exp, getForMarked(s, exp));
  }

  // exp to statement in else of if
  @Test
  public void testIfThenElseExp() {
    String in = "lab: if(true) b=1; else emitDA(#InitialGreeting(Greet));";
    String exp = "public int lab(){ lab: if (true) int b = 1; "
            + "else emitDA(new DialogueAct(\"InitialGreeting\", \"Greet\")); "
            + "return 0;";
    String s = generate(in);
    assertEquals(exp, getForMarked(s, exp));
  }

  // exp to statement in for
  @Test
  public void testForExp() {
    String in = "lab: if(true) for(s : child.sessions) 23;";
    String exp = "public int lab(){ lab: if (true) "
            + "for (Object s_outer : child.sessions) {"
            + " Object s = (Object)s_outer; 23; } return 0;";
    String s = generate(in);
    assertEquals(exp, getForMarked(s, exp));
  }

  // TODO: COMPLETE FOR ALL FOR LOOPS, WHILE, AND DO ... WHILE


  // exp to statement in else of if
  @Test
  public void testDialogueAct() {
    String in = "int x=27;emitDA(#I(Greet, val=^x));";
    String exp = "x = 27;emitDA(new DialogueAct(\"I\", \"Greet\", \"val\", x));";
    String s = generate(in);
    assertEquals(exp, getForMarked(s, exp));
    assertTrue(s.contains("int x;"));
  }
}
