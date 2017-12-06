package de.dfki.mlt.rudimant.compiler.tree;

import static de.dfki.mlt.rudimant.compiler.Visualize.*;
import static de.dfki.mlt.rudimant.compiler.tree.TestUtilities.*;
import static org.junit.Assert.*;


import org.junit.*;

public class MethodDeclarationTest {

  @BeforeClass
  public static void setUpClass() {
    setUpEmpty();
  }

  @Test
  public void test() {
    String methdecl = " void foo() { i = 1; }";
    String s = generate(methdecl);
    String expected = "public void foo() { int i = 1; }";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void testParType() {
    String methdecl = " void foo(List<Child> cs) { Child c = cs.get(0); }";
    String s = generate(methdecl);
    String expected = "public void foo(List<Rdf> cs)"
        + " { Rdf c = cs.get(0); }";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void testMethodOverload(){
    String methdecl1 = " void foo() { i = 1; }\n";
    String methdecl2 = "String foo(int a) { i = 1; }\n";
    String usage = "void bar() { if(true){foo();} }";
    String s = generate(methdecl1 + methdecl2 + usage);
    String expected = "public void foo() { int i = 1; } "
        + "public String foo(int a) { int i = 1; } "
        + "public void bar() { if (true) { foo(); }";
    assertEquals(expected, getForMarked(s, expected));
  }

// .,

  @Test
  public void testMethodMultipleParameters(){
    String methdecl = " void foo(int i, String s) { i = 1; }";
    String s = generate(methdecl);
    String expected = "public void foo(int i, String s) { i = 1; }";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void testCallUpon1(){
    String methdecl = " [List<T>]. T get(int a); List<String> l;"
    		+ "x = l.get(0);";
    String s = generate(methdecl);
    String expected = "public List<String> l;public String x;/**/x = l.get(0);";
    assertEquals(expected, getForMarked(s, expected));
    assertTrue(s.contains("String x;"));
    assertTrue(s.contains("List<String> l;"));
  }
}
