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
    String ifstat = "Seat getSeats();\n" +
                    "Iterator<DialogueAct> lastDAs();\n" +
                    "initiate_greet: if(true){ for(seat : getSeats()){} }";
    String s = generate(ifstat);
    String expected = "public void initiate_greet(){"
            + " initiate_greet: if (true) {for (Seat seat: getSeats()) {}}}";
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
            + "for (Rdf k: kids()) {}}}";
    assertEquals(expected, getForEmpty(s, expected));
  }

}
