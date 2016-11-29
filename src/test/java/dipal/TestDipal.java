package dipal;

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

  private static final String RESOURCE_DIR = "src/test/resources/";

  private String[] enterName(String filename){
    String[] name = {"-c", RESOURCE_DIR + "dipal/dipal.yml",
        RESOURCE_DIR + "dipal/" + filename};

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
  public void testDipal() throws TTransportException, FileNotFoundException, IOException, WrongFormatException, TException {
    GrammarMain.main(enterName("PalAgent.rudi"));
  }

}
