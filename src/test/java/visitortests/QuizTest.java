/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visitortests;

import org.junit.Test;

import de.dfki.mlt.rudimant.GrammarMain;

/**
 *
 * @author Christophe Biwer, christophe.biwer@dfki.de
 */
public class QuizTest {


  @Test
  public void TestQuiz() throws Exception {
    String[] strings = new String[]{
      // "-c", "rudi.config.yml",
      "-o", "target/test/testfiles",
      "-r", "src/test/resources/ontos/pal.ini",
      "-d", "src/test/resources/serious/quiz.rudi",};
    GrammarMain.main(strings);
  }

//  @Test
//  public void TestGreetUser() throws Exception {
//    String[] strings = new String[]{"src/test/resources/serious/greet_user.rudi",
//      "rudi.config.yml", "o=target/test/testfiles", "-d"};
//    GrammarMain.main(strings);
//  }
//
//  @Test
//  public void FirstRuleTest() throws Exception {
//    String[] strings = new String[]{"src/test/resources/FirstRule.rudi",
//      "rudi.config.yml", "-o=target/test/testfiles", "-d"};
//    GrammarMain.main(strings);
//  }
}
