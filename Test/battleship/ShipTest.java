package Test.battleship;

import battleship.Ship;
import battleship.EmptySea;
import battleship.Battleship;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Federico Cocco
 */
public class ShipTest {

    private Battleship instance;
    
    public ShipTest() {
        this.instance = new Battleship();;
    }

    @BeforeClass
    public static void setUpClass() {      
    }

    @AfterClass
    public static void tearDownClass() {
    }


    /**
     * Test of isHorizontal method, of class Ship.
     */
    @Test
    public void testIsHorizontal() {
        System.out.println("isHorizontal");
        Ship instance_test = new Battleship();
        instance_test.setHorizontal(true);
        boolean expResult = true;
        boolean result = instance_test.isHorizontal();
        assertEquals(expResult, result);
    }

    /**
     * Test of Length method, of class Ship.
     */
    @Test
    public void testLength() {
        System.out.println("Length");
        Ship instance = null;
        int expResult = 0;
        int result = instance.Length();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getShipType method, of class Ship.
     */
    @Test
    public void testGetShipType() {
        System.out.println("getShipType");
        Battleship instance = new Battleship();;
        String expResult = "Battleship";
        String result = instance.getShipType();
        assertEquals(expResult, result);
    }

    /**
     * Test of setBowRow method, of class Ship.
     */
    @Test
    public void testSetBowRow() {
        System.out.println("setBowRow");
        int row = 5;
        Ship instance = new Battleship();
        instance.setBowRow(row);
        assertEquals("5", instance.getBowRow());
    }

    /**
     * Test of setBowRow if negative value is given.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetNegativeBowRow() {
        int row = -5;
        Ship instance = new Battleship();
        instance.setBowRow(row);
    }

    /**
     * Test of setBowColumn method, of class Ship.
     */
    @Test
    public void testSetBowColumn() {
        System.out.println("setBowColumn");
        int column = 0;
        Ship instance = null;
        instance.setBowColumn(column);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

        /**
     * Test of getBowRow method, of class Ship.
     */
    @Test
    public void testGetBowRow() {
        System.out.println("getBowRow");
        Ship instance = new Battleship();
        int expResult = 0;
        int result = instance.getBowRow();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBowColumn method, of class Ship.
     */
    @Test
    public void testGetBowColumn() {
        System.out.println("getBowColumn");
        Ship instance = null;
        int expResult = 0;
        int result = instance.getBowColumn();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    /**
     * Test of setHorizontal method, of class Ship.
     */
    @Test
    public void testSetHorizontal() {
        System.out.println("setHorizontal");
        boolean horizontal = false;
        Ship instance = null;
        instance.setHorizontal(horizontal);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of shootAt method, of class Ship.
     */
    @Test
    public void testShootAt() {
        System.out.println("shootAt");
        int row = 0;
        int column = 0;
        Ship instance = null;
        boolean expResult = false;
        boolean result = instance.shootAt(row, column);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isRealShip method, of class Ship.
     */
    @Test
    public void testIsRealShip() {
        System.out.println("isRealShip");
        Ship instance = new EmptySea();
        boolean expResult = false;
        boolean result = instance.isRealShip();
        assertEquals(expResult, result);
    }

    /**
     * Test of isSunk method, of class Ship.
     */
    @Test
    public void testIsSunk() {
        System.out.println("isSunk");
        Ship instance = new Battleship();
        boolean expResult = false;
        boolean result = instance.isSunk();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

//    public class ShipImpl extends Ship {
//
//        public ShipImpl() {
//            super(0);
//        }
//
//        public String getShipType() {
//            return "";
//        }
//    }
}
