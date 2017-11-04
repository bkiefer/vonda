package de.dfki.mlt.rudimant.compiler.tree;

import static de.dfki.mlt.rudimant.compiler.Visualize.*;
import static de.dfki.mlt.rudimant.compiler.tree.TstUtils.*;
import static org.junit.Assert.*;

import org.junit.*;

public class TestReturn {
  @BeforeClass
  public static void setUpClass() {
    setUpEmpty();
  }

  @Test
  public void testReturn1(){
    String in = "int fn() { return 1; }";
    String r = generate(in);
    String expected = "int fn() { return 1; }";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testReturn2(){
    String in = "foo: if (true) { return 1; }";
    String r = generate(in);
    String expected = "public int foo(){ foo: if (true) { return 1; } return 0; }";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testBreak1(){
    String in = "foo: if (true) { break foo; }";
    String r = generate(in);
    String expected = "public int foo(){ foo: if (true) { break foo; } return 0; }";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testBreak2(){
    String in = "bar: if (false) { foo: if (true) { break foo; } }";
    String r = generate(in);
    String expected = "public int bar(){ bar: if (false) { // Rule foo foo: if (true) { break foo; } } return 0; }";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testBreak3(){
    String in = "foo: if (true) { break foo ; }";
    String r = generate(in);
    String expected = "public int foo(){ foo: if (true) { break foo; } return 0; }";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testBreak4(){
    String in = "foo: if (true) { bar: if (false) break bar ; }";
    String r = generate(in);
    String expected = "public int foo(){ foo: if (true) { // Rule bar bar: if (false) break bar; } "
    		+ "return 0; }";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testReturn7(){
    String in = "foo: if (true) { return test; }";
    String r = generate(in);
    String expected = "public int foo(){ foo: if (true) { return test; } "
    		+ "return 0; }";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testReturn8(){
    String in = "if (true) { return test; }";
    String r = generate(in);
    String expected = "if (true) { return test; }";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testBreak(){
    String in = "test_rule: if (true) { second_rule: if (false) { break test_rule; } }";
    String r = generate(in);
    String expected = "public int test_rule(){"
        + " test_rule: if (true) {"
        + " // Rule second_rule "
        + "second_rule: if (false) {"
        + " break test_rule; } }"
        + " return 0;";
    assertEquals(expected, getForMarked(r, expected));
  }


}
