package de.dfki.mlt.rudimant.compiler.tree;

import static de.dfki.mlt.rudimant.compiler.Visualize.generate;
import static de.dfki.mlt.rudimant.compiler.tree.TestUtilities.*;
import static org.junit.Assert.*;

import java.io.File;

import org.junit.*;

import de.dfki.lt.hfc.WrongFormatException;
import de.dfki.mlt.rudimant.compiler.CompilerMain;

public class ImportTest {

  @Test
  public void testImport1() throws WrongFormatException {
    CompilerMain.main(new String[]{
        "-o", "target/generated/",
        "-r", RESOURCE_DIR + "ontos/pal.quads.ini",
        RESOURCE_DIR + "Main.rudi"
    });
    assertTrue(new File("target/generated/Main.java").exists());
    assertTrue(new File("target/generated/Import.java").exists());
    assertTrue(new File("target/generated/sub/Import.java").exists());
  }
}
