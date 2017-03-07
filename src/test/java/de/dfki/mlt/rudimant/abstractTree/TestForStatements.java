/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant.abstractTree;

import static de.dfki.mlt.rudimant.Visualize.generate;
import static de.dfki.mlt.rudimant.abstractTree.TstUtils.*;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Anna Welker, anna.welker@dfki.de
 */
public class TestForStatements {

  @BeforeClass
  public static void setUpClass() {
    setUpEmpty();
  }

  @Test
  public void test1() {
    String ifstat = "List<Child> getSeats();\n" +
                    "Iterator<DialogueAct> lastDAs();\n" +
                    "initiate_greet: if(true){ for(seat : getSeats()){} }";
    String s = generate(ifstat);
    String expected = "public void initiate_greet(){"
            + " initiate_greet: if (true) {for (Object seat_outer : getSeats()) "
            + "{ Rdf seat = (Rdf)seat_outer; {}}}}";
    assertEquals(expected, getForEmpty(s, expected));
  }

  @Test
  public void test2() {
    String ifstat = "Seat getSeats();\n" +
                    "Iterator<Child> kids();\n" +
                    "initiate_greet:\n" +
                    "  if(true){\n" +
                    "    for(k : kids()){}}";
    String s = generate(ifstat);
    String expected = "public void initiate_greet(){"
            + " initiate_greet: if (true) {"
            + "for (Object k_outer : kids()) { Rdf k = (Rdf)k_outer; {}}}}";
    assertEquals(expected, getForEmpty(s, expected));
  }

  @Test
  public void test3() {
    String ifstat = "Set<Object> getI(); for(s : getI()){"
            + "label: if(true) {s = null;}}";
    String s = generate(ifstat);
    String expected = "public void null1(){for (Object s_outer : getI()) " +
              "{ Object s = (Object)s_outer; {" +
              "//Rule label " +
              "label: " +
              "if (true) { s = null; }}}}";
    System.out.println(getForEmpty(s, expected));
    assertEquals(expected, getForEmpty(s, expected));
  }

}
