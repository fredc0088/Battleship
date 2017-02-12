package Test.battleship;

import battleship.Ship;
import battleship.EmptySea;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *  This is a series of tests for the class EmptySea.
 * 
 * @author Federico Cocco
 */
public class EmptySeaTest {
    
    public EmptySeaTest() {
    }

    /**
     * Test: method should return "empty sea".
     */
    @Test
    public void testGetShipType() {
        System.out.println("--------testGetShipType-------");
        System.out.println("        For getShipType()");        
        EmptySea instance = new EmptySea();
        String expResult = "empty sea";
        String result = instance.getShipType();
        assertEquals(expResult, result);
    }

    /**
     * Test: isSunk method should always return false.
     */
    @Test
    public void testIsSunk() {
        System.out.println("--------testIsSunk-------");
        System.out.println("        For isSunk");
        EmptySea instance = new EmptySea();
        boolean expResult = false;
        boolean result = instance.isSunk();
        assertEquals(expResult, result);
    }

    /**
     * Test: the method should return <code>false</code> as it is not a real ship.
     */
    @Test
    public void testIsRealShip() {
        System.out.println("--------testIsRealShip-------");
        System.out.println("        For isRealShip()");
        EmptySea instance = new EmptySea();
        boolean expResult = false;
        boolean result = instance.isRealShip();
        assertEquals(expResult, result);
    }

    /**
     * Test: the right length has been set and
     *      can be rerieved.
     */
    @Test
    public void TestGetEmptySeaLength() {
        System.out.println("--------TestGetEmptySeaLength-------");
        System.out.println("        For Length()");
        Ship instance = new EmptySea();
        int expectedResult = 1;
        assertEquals(expectedResult, instance.Length());
    }

    /**
     * Test: when hit, EmptySea is considered as hit, but still it will be not
     *      returned as such bacause it does not represent a real ship.
     */
    @Test
    public void testShootingAtTheEmptySea() {
        System.out.println("--------testShootAtCloserPartToTheBow-------");
        System.out.println("        For shootAt()");
        Ship instance = new EmptySea();
        instance.setBowRow(4);
        instance.setBowColumn(6);
        if(instance.shootAt(4, 6)) {
            fail("It should not sense as being hit.");
        }
        assertTrue(instance.getShipStatus()[0]);
    }
    
        /**
     * Test: to string returns the right graphical representation of the ship
     *      in its current status.
     */
    @Test 
    public void testGetStringRepresentationOfShipSTatus() {
        System.out.println("--------testGetStringRepresentationOfShipSTatus-------");
        System.out.println("        For toString()");
        Ship instance = new EmptySea();
        instance.setBowRow(3);
        instance.setBowColumn(6);
        instance.shootAt(3, 6);
        assertEquals("-", instance.toString());
    }
}
