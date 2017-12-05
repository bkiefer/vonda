/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.mlt.rudimant;

import static de.dfki.mlt.rudimant.compiler.tree.TestUtilities.RESOURCE_DIR;
import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import de.dfki.mlt.rudimant.compiler.CompilerMain;

/**
 *
 * @author Anna Welker
 */
public class CommentTest {

  @Test
  public void Test() throws Exception {
    // enter here the file whose compilation you'd like to debug
    CompilerMain.main(new String[]{
      "-o", "target/generated/",
      "-r", RESOURCE_DIR + "ontos/pal.quads.ini",
      RESOURCE_DIR + "Comments.rudi",
    });

    assertTrue(new File("target/generated/Comments.java").exists());
  }
}
