package de.dfki.mlt.rudimant.compiler.tree;

import static de.dfki.mlt.rudimant.compiler.Visualize.*;
import static de.dfki.mlt.rudimant.compiler.tree.TstUtils.*;
import static org.junit.Assert.*;

import java.io.File;
import de.dfki.lt.hfc.WrongFormatException;
import de.dfki.mlt.rudimant.compiler.CompilerMain;

import org.junit.*;

public class TestMethodDeclaration {

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
    String methdecl = " void foo(List<Child> cs) { i = 1; }";
    String s = generate(methdecl);
    String expected = "public void foo(List<Rdf> cs) { int i = 1; }";
    assertEquals(expected, getForMarked(s, expected));
  }

  @Test
  public void testMethodOverload(){
    String methdecl1 = " void foo() { i = 1; }\n";
    String methdecl2 = "String foo(int a) { i = 1; }\n";
    String usage = "bar: if(true){foo();}";
    String s = generate(methdecl1 + methdecl2 + usage);
    String expected = "public void foo() { int i = 1; } "
            + "public String foo(int a) { int i = 1; }"
            + " public int bar(){ bar: if (true) { foo(); }";
    assertEquals(expected, getForMarked(s, expected));
  }

// causes a NullPointerException
//  @Test
//  public void testEmptyReturn(){
//    String methdecl = "void foo() { return; }";
//    String s = generate(methdecl);
//    String expected = "void foo() {return; }";
//    assertEquals(expected, getForEmpty(s, expected));
//  }

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