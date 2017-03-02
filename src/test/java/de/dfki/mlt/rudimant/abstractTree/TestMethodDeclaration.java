package de.dfki.mlt.rudimant.abstractTree;

import static de.dfki.mlt.rudimant.Visualize.*;
import static org.junit.Assert.*;
import static de.dfki.mlt.rudimant.abstractTree.TstUtils.*;

import org.junit.*;

public class TestMethodDeclaration {

  @BeforeClass
  public static void setUpClass() {
    setUpEmpty();
  }

  @Test
  public void test() {
    String methdecl = "void foo() { i = 1; }";

    String s = generate(methdecl);
    s = normalizeSpaces(s);
    String expected = "void foo() {int i = 1; }} ";
    assertEquals(expected, s.substring(s.length() - expected.length()));
  }

  @Test
  public void testParType() {
    String methdecl = "void foo(List<Child> cs) { i = 1; }";

    String s = generate(methdecl);
    s = normalizeSpaces(s);
    String expected = "void foo(List<Rdf> cs) {int i = 1; }} ";
    assertEquals(expected, s.substring(s.length() - expected.length()));
  }

  @Test
  public void testMethodOverload(){
    String methdecl1 = "void foo() { i = 1; }\n";
    String methdecl2 = "String foo(int a) { i = 1; }\n";
    String usage = "if(true){foo();}";
    String s = generate(methdecl1 + methdecl2 + usage);
    s = normalizeSpaces(s);
    //System.out.println(s);
    String expected = "void foo() {int i = 1; } String foo(int a) {int i = 1; }if (true) {foo(); }} ";
    assertEquals(expected, s.substring(s.length() - expected.length()));
  }

}
