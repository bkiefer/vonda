package de.dfki.mlt.rudimant.compiler.tree;

import static de.dfki.mlt.rudimant.compiler.Visualize.*;
import static de.dfki.mlt.rudimant.compiler.tree.TestUtilities.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import de.dfki.mlt.rudimant.compiler.Visualize;
import de.dfki.mlt.rudimant.compiler.tree.*;

/**
 *
 * @author max
 */
public class BooleanTest {

  /*
  /// boolean operators:
NOT: '!';
EQUAL: '==';
AND1: '&'; TODO deprecated?
OR1: '|'; TODO deprecated?
AND2: '&&';
OR2: '||';
NOT_EQUAL: '!=';
SMALLER_EQUAL: '<=';
SMALLER: '<';
GREATER_EQUAL: '>=';
GREATER: '>';

   */

  @BeforeClass
  public static void setUpClass() {
    setUpNonEmpty();
  }
  
  public void assertBoolean(RudiTree dtr) {
    assertTrue(dtr instanceof StatVarDef);
    dtr = ((StatVarDef)dtr).toAssign;
    assertTrue(dtr instanceof ExpAssignment);
    dtr = ((ExpAssignment)dtr).right;
    assertTrue(dtr instanceof ExpBoolean);
  }

  @Test
  public void testBoolean() {
    String booleanExp = "boolean b = 4 < 5;";

    RudiTree dtr = getNodeOfInterest(parseAndTypecheck(booleanExp));
    assertBoolean(dtr);
  }

  @Test
  public void testBoolean2() {
    String booleanExp = "boolean b = 4 == 5;";
    RudiTree dtr = getNodeOfInterest(parseAndTypecheck(booleanExp));
    assertBoolean(dtr);
  }

  @Test
  public void testBoolean3() {
    String booleanExp = "boolean b = false;";
    RudiTree dtr = getNodeOfInterest(parseAndTypecheck(booleanExp));

    assertTrue(dtr instanceof StatVarDef);
    dtr = ((StatVarDef)dtr).toAssign;
    assertTrue(dtr instanceof ExpAssignment);
    dtr = ((ExpAssignment)dtr).right;
    assertTrue(dtr instanceof ExpSingleValue);
    assertEquals("false should be of type Boolean", "boolean",
        (((RTExpression) dtr).getType().toJava()));
  }

  @Test
  public void testBoolean4() {
    String booleanExp = "boolean b = !(5>5);";

    RudiTree dtr = getNodeOfInterest(parseAndTypecheck(booleanExp));
    assertBoolean(dtr);
  }

  @Test
  public void testBoolean5() {
    String booleanExp = "boolean b = (false && false || true);";

    RudiTree dtr = getNodeOfInterest(parseAndTypecheck(booleanExp));
    assertBoolean(dtr);
  }

  @Test
  public void testBoolean7() {
    String booleanExp = "boolean b = (var1 != var2);";

    RudiTree dtr = getNodeOfInterest(parseAndTypecheck(booleanExp));
    assertBoolean(dtr);
  }

  @Test
  public void testBoolean8() {
    String booleanExp = "boolean b = (var1 >= var2 && var1 <= var2);";

    RudiTree dtr = getNodeOfInterest(parseAndTypecheck(booleanExp));
    assertBoolean(dtr);
  }

  @Test
  public void testNegationScope() {
    String booleanExp = "boolean b = (! var1 == var2 && var1 <= var2 && var2 == var1);";

    RudiTree dtr = getNodeOfInterest(parseAndTypecheck(booleanExp));
    //Visualize.show(dtr, "foo");
    assertTrue(dtr instanceof StatVarDef);
    dtr = ((StatVarDef)dtr).toAssign;
    assertTrue(dtr instanceof ExpAssignment);
    dtr = ((ExpAssignment)dtr).right;
    assertTrue(dtr instanceof ExpBoolean);
    List<RudiTree> dtrs = new ArrayList<>();
    for (RudiTree d : dtr.getDtrs()) { dtrs.add(d); }
    //TODO: this ist most probably wrong; at least the ast for this expression
    //      looks sound
    assertEquals("!", ((ExpBoolean)dtrs.get(0)).operator) ;
    assertEquals("&&", ((ExpBoolean)dtrs.get(1)).operator) ;

  }

}
