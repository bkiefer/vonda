package dipal;

import static visitortests.SeriousTest.RESOURCE_DIR;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.thrift.TException;
import org.apache.thrift.transport.TTransportException;
import org.junit.Test;

import de.dfki.lt.hfc.WrongFormatException;
import de.dfki.mlt.rudimant.GrammarMain;


public class TestDipal {

  private static String[] enterName(String filename){
    String[] name = {"-c", RESOURCE_DIR + "dipal/dipal.yml",
        RESOURCE_DIR + "dipal/" + filename};

    return name;
  }

  @Test
  public void testDipal() throws TTransportException, FileNotFoundException, IOException, WrongFormatException, TException {
    GrammarMain.main(enterName("PalAgent.rudi"));
  }

  public static void main(String[] args) throws TTransportException, FileNotFoundException, IOException, WrongFormatException, TException {
    GrammarMain.main(enterName("PalAgent.rudi"));
  }

}
