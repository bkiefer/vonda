/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visitortests;

import java.io.IOException;

import org.apache.thrift.transport.TTransportException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import de.dfki.lt.hfc.WrongFormatException;
import de.dfki.mlt.rudimant.GrammarMain;
import de.dfki.mlt.rudimant.TypeException;

/**
 *
 * @author Anna Welker
 */
public class PrintTest {
  @BeforeClass
  public static void setUpClass()
      throws TTransportException, IOException, WrongFormatException {
    SeriousTest.setUpClass();
  }

  @AfterClass
  public static void tearDownClass() {
    SeriousTest.tearDownClass();
  }

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
  
  @Test
  public void SingleFileTest() throws Exception {
    String[] strings = {
        "-c", "src/test/resources/rudi.config.yml",
        "src/test/resources/basic/agentMethods.rudi"
    };
    GrammarMain.main(strings);
//    assertFail(GrammarMain.main(strings));
  }

//  @Test
//  public void ReturnTest() throws Exception {
//    String[] strings = new String[]{
//      "-c", "src/test/resources/rudi.config.yml", "-d",
//      "src/test/resources/test_return/aLotOfReturns.rudi"};
//    GrammarMain.main(strings);
//    //assertFail(GrammarMain.main(strings2));
//  }
//
//  @Test
//  public void miniTest() throws Exception {
//    String[] strings = new String[]{
//      "-c", "src/test/resources/rudi.config.yml", "-d",
//      "src/test/resources/MiniTest.rudi",
//    };
//    GrammarMain.main(strings);
//  }
}
