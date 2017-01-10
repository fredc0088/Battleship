
package Test.battleship;

import battleship.Ocean;
import battleship.Ship;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Federico Cocco
 */
public class OceanTest {
    
    public OceanTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of placeAllShipsRandomly method, of class Ocean.
     */
    @Test
    public void testPlaceAllShipsRandomly() {
        System.out.println("placeAllShipsRandomly");
        Ocean instance = new Ocean();
        instance.placeAllShipsRandomly();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of shootAt method, of class Ocean.
     */
    @Test
    public void testShootAt() {
        System.out.println("shootAt");
        int row = 0;
        int column = 0;
        Ocean instance = new Ocean();
        boolean expResult = false;
        boolean result = instance.shootAt(row, column);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isOccupied method, of class Ocean.
     */
    @Test
    public void testIsOccupied() {
        System.out.println("isOccupied");
        int row = 0;
        int column = 0;
        Ocean instance = new Ocean();
        boolean expResult = false;
        boolean result = instance.isOccupied(row, column);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hasSunkShipAt method, of class Ocean.
     */
    @Test
    public void testHasSunkShipAt() {
        System.out.println("hasSunkShipAt");
        int row = 0;
        int column = 0;
        Ocean instance = new Ocean();
        boolean expResult = false;
        boolean result = instance.hasSunkShipAt(row, column);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getShipTypeAt method, of class Ocean.
     */
    @Test
    public void testGetShipTypeAt() {
        System.out.println("getShipTypeAt");
        int row = 0;
        int column = 0;
        Ocean instance = new Ocean();
        String expResult = "";
        String result = instance.getShipTypeAt(row, column);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getShotsFired method, of class Ocean.
     */
    @Test
    public void testGetShotsFired() {
        System.out.println("getShotsFired");
        Ocean instance = new Ocean();
        int expResult = 0;
        int result = instance.getShotsFired();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHitCount method, of class Ocean.
     */
    @Test
    public void testGetHitCount() {
        System.out.println("getHitCount");
        Ocean instance = new Ocean();
        int expResult = 0;
        int result = instance.getHitCount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getShipsSunk method, of class Ocean.
     */
    @Test
    public void testGetShipsSunk() {
        System.out.println("getShipsSunk");
        Ocean instance = new Ocean();
        int expResult = 0;
        int result = instance.getShipsSunk();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isGameOver method, of class Ocean.
     */
    @Test
    public void testIsGameOver() {
        System.out.println("isGameOver");
        Ocean instance = new Ocean();
        boolean expResult = false;
        boolean result = instance.isGameOver();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getShipArray method, of class Ocean.
     */
    @Test
    public void testGetShipArray() {
        System.out.println("getShipArray");
        Ocean instance = new Ocean();
        Ship[][] expResult = null;
        Ship[][] result = instance.getShipArray();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of print method, of class Ocean.
     */
    @Test
    public void testPrint() {
        System.out.println("print");
        Ocean instance = new Ocean();
        instance.print();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
