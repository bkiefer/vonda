/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visitortests;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.dfki.mlt.rudi.GrammarMain;

import static org.junit.Assert.*;

/**
 *
 * @author Anna Welker
 */
public class PrintTest {

  public PrintTest() {
  }

  @BeforeClass
  public static void setUpClass() {
  }

  @AfterClass
  public static void tearDownClass() {
  }

  @Before
  public void setUp() {
  }

  @After
  public void tearDown() {
  }

  /*@Test
  public void FirstRuleTest() throws Exception {
    String[] strings = new String[]{"src/test/resources/FirstRule.rudi",
      "src/test/testfiles", "-log"};
    GrammarMain.main(strings);
  }*/
  @Test(expected = UnsupportedOperationException.class)
  public void ImportFailTest() throws Exception {
    String[] strings2 = new String[]{"src/test/resources/test_import/Test2.rudi",
      "src/test/testfiles"};
    GrammarMain.main(strings2);
  }

  @Test
  public void ImportTest() throws Exception {
    String[] strings = new String[]{"src/test/resources/test_import/Test.rudi",
      "target/test/testfiles"};
    GrammarMain.main(strings);
    //assertFail(GrammarMain.main(strings2));
  }

  /*
    @Test
    public void SecondRuleTest() throws Exception {
        String[] strings = new String[]{"SecondRule.txt"};
        GrammarMain.main(strings);
    }

    @Test
    public void ThirdRuleTest() throws Exception {
        String[] strings = new String[]{"ThirdRule.txt"};
        GrammarMain.main(strings);
    }

    @Test
    public void TupleForTest() throws Exception {
        String[] strings = new String[]{"TupleFor.txt"};
        GrammarMain.main(strings);
    }

    @Test
    public void WholeQuizTest() throws Exception {
        String[] strings = new String[]{"RulesQuiz.txt"};
        GrammarMain.main(strings);
    }

    @Test
    public void LogTest() throws Exception {
        String[] strings = new String[]{"LogThis.txt", "-log"};
        GrammarMain.main(strings);
    }

    /*
    @Test
    public void FourthAndFifthRuleTest() throws Exception {
        String[] strings = new String[]{"TwoMoreRules"};
        Main.main(strings);
    }*/
}
