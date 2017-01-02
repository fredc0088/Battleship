/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test.battleship;

import battleship.Cruiser;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Administrator
 */
public class CruiserTest {
    
    public CruiserTest() {
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

    /**
     * Test of getShipType method, of class Cruiser.
     */
    @Test
    public void testGetShipType() {
        System.out.println("getShipType");
        Cruiser instance = new Cruiser();
        String expResult = "cruiser";
        String result = instance.getShipType();
        assertEquals(expResult, result);
    }

}
