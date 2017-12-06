package de.dfki.mlt.rudimant;
import static de.dfki.mlt.rudimant.compiler.tree.TestUtilities.RESOURCE_DIR;
import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import de.dfki.mlt.rudimant.compiler.CompilerMain;

public class CoverageTest {

  @Test
  public void Test() throws Exception {
    // enter here the file whose compilation you'd like to debug
    CompilerMain.main(new String[]{
      "-o", "target/generated/",// "-v",
      "-r", RESOURCE_DIR + "ontologies/inits/pal.inference.ini",
      "doc/AllYouCanDo.rudi",
    });

    assertTrue(new File("target/generated/AllYouCanDo.java").exists());
  }
}
