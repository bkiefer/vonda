/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visitortests;

import Versuch2.GrammarMain;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author anna
 */
public class PrintTest {
  
  public PrintTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
  }
  
  @AfterClass
  public static void tearDownClass() {
  }
  
  @Before
  public void setUp() {
  }
  
  @After
  public void tearDown() {
  }


    @Test
    public void FirstRuleTest() throws Exception {
        String[] strings = new String[]{"FirstRule"};
        GrammarMain.main(strings);
    }
    
    @Test
    public void SecondRuleTest() throws Exception {
        String[] strings = new String[]{"SecondRule"};
        GrammarMain.main(strings);
    }
    
    @Test
    public void ThirdRuleTest() throws Exception {
        String[] strings = new String[]{"ThirdRule"};
        GrammarMain.main(strings);
    }
    
    /*
    @Test
    public void FourthAndFifthRuleTest() throws Exception {
        String[] strings = new String[]{"TwoMoreRules"};
        Main.main(strings);
    }*/
}
