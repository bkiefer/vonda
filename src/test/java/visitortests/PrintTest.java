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
 * @author Anna Welker
 */
public class PrintTest {

//  @Test(expected = TypeException.class)
//  public void ImportFailTest() throws Exception {
//    String[] strings = new String[]{
//        "-c=src/test/resources/rudi.config.yml",
//        "src/test/resources/test_import/Test2.rudi"
//    };
//    GrammarMain.main(strings);
//  }

//  @Test
//  public void ImportTest() throws Exception {
//    String[] strings = {
//        "-c", "src/test/resources/rudi.config.yml",
//        "src/test/resources/test_import/Test.rudi"
//    };
//    GrammarMain.main(strings);
////    assertFail(GrammarMain.main(strings));
//  }

//  @Test
//  public void SingleFileTest() throws Exception {
//    String[] strings = {
//        "-c", "src/test/resources/rudi.config.yml",
//        "src/test/resources/basic/agentMethods.rudi"
//    };
//    GrammarMain.main(strings);
////    assertFail(GrammarMain.main(strings));
//  }

//  @Test
//  public void ReturnTest() throws Exception {
//    String[] strings = new String[]{
//      "-c", "src/test/resources/rudi.config.yml", "-d",
//      "src/test/resources/test_return/aLotOfReturns.rudi"};
//    GrammarMain.main(strings);
//    //assertFail(GrammarMain.main(strings2));
//  }
//
  @Test
  public void miniTest() throws Exception {
    String[] strings = new String[]{
      "-c", "src/test/resources/rudi.config.yml", "-d",
      "src/test/resources/MiniTest.rudi",
    };
    GrammarMain.main(strings);
  }
}
