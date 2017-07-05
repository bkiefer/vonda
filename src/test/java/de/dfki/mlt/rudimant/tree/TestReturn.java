package de.dfki.mlt.rudimant.tree;

import static de.dfki.mlt.rudimant.Visualize.*;
import static de.dfki.mlt.rudimant.tree.TstUtils.*;
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
    String expected = "int fn() {return 1; }";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testReturn2(){
    String in = "foo: if (true) { return 1; }";
    String r = generate(in);
    String expected = "public boolean foo(){ foo: if (true) {return 1; }";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testReturn3(){
    String in = "foo: if (true) { return foo; }";
    String r = generate(in);
    String expected = "public boolean foo(){ foo: if (true) {break foo; }";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testReturn4(){
    String in = "bar: if (false) { foo: if (true) { return foo; } }";
    String r = generate(in);
    String expected = "public boolean bar(){ bar: if (false) {// Rule foo foo: if (true) {break foo; } }";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testReturn5(){
    String in = "foo: if (true) { return ; }";
    String r = generate(in);
    String expected = "public boolean foo(){ foo: if (true) {break foo; }";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testReturn6(){
    String in = "foo: if (true) { bar: if (false) return ; }";
    String r = generate(in);
    String expected = "public boolean foo(){ foo: if (true) {// Rule bar bar: if (false) break bar; } "
    		+ "return false;";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testReturn7(){
    String in = "foo: if (true) { return test; }";
    String r = generate(in);
    String expected = "public boolean foo(){ foo: if (true) {return test; } "
    		+ "return false;";
    assertEquals(expected, getForMarked(r, expected));
  }

  @Test
  public void testReturn8(){
    String in = "if (true) { return test; }";
    String r = generate(in);
    String expected = "if (true) {return test; }";
    assertEquals(expected, getForMarked(r, expected));
  }

}
