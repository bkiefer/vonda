package de.dfki.mlt.rudimant.abstractTree;

import static de.dfki.mlt.rudimant.abstractTree.TstUtils.*;
import static org.junit.Assert.*;

import java.io.File;

import org.junit.*;

import de.dfki.lt.hfc.WrongFormatException;
import de.dfki.mlt.rudimant.GrammarMain;
import de.dfki.mlt.rudimant.TypeException;

public class TestImport {

  @Test
  public void testImport() throws WrongFormatException {
    GrammarMain.main(new String[]{
        "-o", "target/generated/",
        "-r", RESOURCE_DIR + "ontos/pal.ini",
        RESOURCE_DIR + "main.rudi"
    });
    assertTrue(new File("target/generated/Main.java").exists());
    assertTrue(new File("target/generated/Import.java").exists());
  }
}
