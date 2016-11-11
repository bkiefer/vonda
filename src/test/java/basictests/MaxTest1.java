/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basictests;

import visitortests.*;
import java.io.IOException;

import org.apache.thrift.transport.TTransportException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import de.dfki.lt.hfc.WrongFormatException;
import de.dfki.mlt.rudimant.GrammarMain;

/**
 *
 * @author mawo
 */
public class MaxTest1 {

  private static final String RESOURCE_DIR = "src/test/resources/";


  private String[] enterName(String filename){
    String[] name = {"-c", "src/test/resources/rudi.config.yml", "-d",
    "src/test/resources/basic/" + filename};

    return name;
  }

  @BeforeClass
  public static void setUpClass()
    throws TTransportException, IOException, WrongFormatException {
    SeriousTest.setUpClass();
  }

  @AfterClass
  public static void tearDownClass() {
    SeriousTest.tearDownClass();
  }

  @Test
  public void miniTest() throws Exception {
//    String[] strings = new String[]{
//      "-c", "src/test/resources/rudi.config.yml", "-d",
//      "src/test/resources/MiniTest.rudi",};

    GrammarMain.main(enterName("MaxTest1.rudi"));
  }
}
