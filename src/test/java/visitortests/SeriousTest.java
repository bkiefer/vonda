/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visitortests;

import de.dfki.mlt.rudimant.GrammarMain;
import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Anna Welker, anna.welker@dfki.de
 */
public class SeriousTest {

  public SeriousTest() {
  }

  @BeforeClass
  public static void setUpClass() {
    File outDir = new File("target/test/testfiles");
    if (!outDir.exists()) {
      outDir.mkdirs();
    }
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

//  @Test
//  public void TestGreetUser() throws Exception {
//    String[] strings = new String[]{"src/test/resources/serious/greet_user.rudi",
//      "target/test/testfiles", "-nt"};
//    GrammarMain.main(strings);
//  }
  @Test
  public void FirstRuleTest() throws Exception {
    String[] strings = new String[]{"src/test/resources/FirstRule.rudi",
      "/../../rudi.config.yml", "-o=target/test/testfiles", "-d"};
    GrammarMain.main(strings);
  }
}
