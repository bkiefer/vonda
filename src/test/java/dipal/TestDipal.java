package dipal;

import static visitortests.SeriousTest.*;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.thrift.TException;
import org.apache.thrift.transport.TTransportException;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Test;

import de.dfki.lt.hfc.WrongFormatException;
import de.dfki.mlt.rudimant.GrammarMain;
import visitortests.*;


public class TestDipal {

  private static String[] enterName(String filename){
    String[] name = {"-c", RESOURCE_DIR + "dipal/dipal.yml",
        "-p", Integer.toString(SERVER_PORT), "-v",
        RESOURCE_DIR + "dipal/" + filename};

    return name;
  }

  @BeforeClass
  public static void setUpClass()
    throws TTransportException, IOException, WrongFormatException {
    SeriousTest.setupClass("dipal/ontologies/pal.ini");
  }

  @AfterClass
  public static void tearDownClass() {
    SeriousTest.tearDownClass();
  }

  @Test
  public void testDipal() throws TTransportException, FileNotFoundException, IOException, WrongFormatException, TException {
    GrammarMain.main(enterName("PalAgent.rudi"));
  }

  public static void main(String[] args) throws TTransportException, FileNotFoundException, IOException, WrongFormatException, TException {
    setUpClass();
    GrammarMain.main(enterName("PalAgent.rudi"));
    tearDownClass();
  }

}
