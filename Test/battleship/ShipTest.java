package Test.battleship;

import battleship.Ship;
import battleship.EmptySea;
import battleship.Battleship;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This is a series of tests for the Ship class, therefore its concrete
 * implementation is testes (choice: Battleship).
 *
 * @author Federico Cocco
 */
public class ShipTest {

    public ShipTest() {
    }

    /**
     * Test: if the right length has been set and can be rerieved.
     */
    @Test
    public void TestGetShipLength() {
        System.out.println("--------TestGetShipLength-------");
        System.out.println("        For Length()");
        Ship instance = new Battleship();
        int expectedResult = 4;
        assertEquals(expectedResult, instance.Length());
    }

    /**
     * Test: the method can access the private field representing the column on
     * the board the bow is located in. It retrieves the field as before being
     * initialised thus holding a default value.
     */
    @Test
    public void testGetBowRowAsDefault() {
        System.out.println("--------testGetBowRowAsDefault-------");
        System.out.println("        For setBowRow() and getBowRow()");
        Ship instance = new EmptySea();
        assertEquals(0, instance.getBowRow());
    }

    /**
     * Test: the method can access the private field representing the column on
     * the board the bow is located in. It retrieves the field as before being
     * initialised thus holding a default value.
     */
    @Test
    public void testGetBowColumnAsDefault() {
        System.out.println("--------testGetBowColumn-------");
        System.out.println("        For setBowColumn() and getBowColumn()");
        Ship instance = new EmptySea();
        assertEquals(0, instance.getBowColumn());

    }

    /**
     * Test: the methods can access the private field representing the column on
     * the board the bow is located in. setBowRow can set the value of it, and
     * getBowRow can retrieve it.
     */
    @Test
    public void testGetBowRow() {
        System.out.println("--------testGetBowRow-------");
        System.out.println("        For setBowRow() and getBowRow()");
        Ship instance = new EmptySea();
        instance.setBowRow(3);
        int expectedResult = 3;
        assertEquals(expectedResult, instance.getBowRow());
    }

    /**
     * Test: the methods can access the private field representing the column on
     * the board the bow is located in. setBowColumn can set the value of it,
     * and getBowColumn can retrieve it.
     */
    @Test
    public void testGetBowColumn() {
        System.out.println("--------testGetBowColumn-------");
        System.out.println("        For setBowColumn() and getBowColumn()");
        Ship instance = new EmptySea();
        instance.setBowColumn(6);
        int expectedResult = 6;
        assertEquals(expectedResult, instance.getBowColumn());
    }

    /**
     * Test: the method gets the graphical representation of the ship in current
     * status.
     */
    @Test
    public void testGetShipStatus() {
        System.out.println("--------testGetShipStatus-------");
        System.out.println("        For getShipStatus()");
        Ship instance = new Battleship();
        assertEquals("....", instance.toString());
    }

    /**
     * Test: set negative values for bow (both column or row) throws an
     * exception.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetNegativeValueForBowIsNotAllowed() {
        System.out.println("--------testSetNegativeValueForBowIsNotAllowed-------");
        System.out.println("        For setBowColumn() or setBowRow()");
        Ship instance = new EmptySea();
        instance.setBowColumn(0 - 2);
    }

    /**
     * Test: set and get the orientation of the ship. <code>true</code> if the
     * ship is horizontal.
     */
    @Test
    public void testSetAndGetHorizontality() {
        System.out.println("--------testSetAndGetHorizontality-------");
        System.out.println("        For setHorizontal() or isHorizontal()");
        Ship instance = new EmptySea();
        instance.setHorizontal(false);
        boolean expectedResult = false;
        assertEquals(expectedResult, instance.isHorizontal());
    }

    /**
     * Test: a shot at different coordinates should not hit the ship.
     */
    @Test
    public void testMissingToHitShip() {
        System.out.println("--------testMissingToHitShip-------");
        System.out.println("        For shootAt()");
        Ship instance = new Battleship();
        instance.setHorizontal(true);
        instance.setBowRow(9);
        instance.setBowColumn(2);
        assertFalse(instance.shootAt(4, 2));
    }

    /**
     * Test: the right part of the ship gets hit. Also test that the ship can
     * sense to be hit. Also it demonstrates
     */
    @Test
    public void testShootAtCloserPartToTheBow() {
        System.out.println("--------testShootAtCloserPartToTheBow-------");
        System.out.println("        For shootAt()");
        Ship instance = new Battleship();
        instance.setHorizontal(true);
        instance.setBowRow(4);
        instance.setBowColumn(6);
        if (instance.shootAt(4, 7) == false) {
            fail("Unexpected error, the ship does not recognised as it was hit.");
        }
        assertTrue(instance.getShipStatus()[1]);
    }

    /**
     * Test: if the ship has not been sunk, it is hit. Otherwise not.
     */
    @Test
    public void testShipSunkCannotbeHit() {
        System.out.println("--------testShipSunkCannotbeHit-------");
        System.out.println("        For shootAt()");
        Ship instance = new Battleship();
        instance.setHorizontal(true);
        instance.setBowRow(4);
        int row = instance.getBowRow();
        instance.setBowColumn(6);
        int column = instance.getBowColumn();
        for (int i = 0; i < instance.Length(); i++) {
            instance.shootAt(row, column);
            column++;
        }
        if (instance.shootAt(row, instance.getBowColumn())) {
            fail("The ship counts as hit although is sunken");
        }
    }

    /**
     * Test: to string returns the right graphical representation of the ship in
     * its current status.
     */
    @Test
    public void testGetStringRepresentationOfShipSTatus() {
        System.out.println("--------testGetStringRepresentationOfShipSTatus-------");
        System.out.println("        For toString()");
        Ship instance = new Battleship();
        instance.setHorizontal(false);
        instance.setBowRow(3);
        instance.setBowColumn(6);
        instance.shootAt(5, 6);
        assertEquals("..S.", instance.toString());
    }

    /**
     * Test: the method returns the representation of the string after being sunk.
     */
    @Test
    public void testGetStringRepresentationOfSunkenShip() {
        System.out.println("--------testGetStringRepresentationOfSunkenShip-------");
        System.out.println("        For toString()");
        Ship instance = new Battleship();
        instance.setHorizontal(false);
        instance.setBowRow(0);
        int row = instance.getBowRow();
        instance.setBowColumn(1);
        int column = instance.getBowColumn();
        for (int i = 0; i < instance.Length(); i++) {
            instance.shootAt(row, column);
            row++;
        }
        assertEquals("XXXX", instance.toString());
    }
    
    /**
     * Test: the ship has been sunk so the method returns <code>true</code>.
     */
    @Test
    public void testTheShipIsSunken() {
        System.out.println("--------testTheShipIsSunken-------");
        System.out.println("        For isSunk()");
        Ship instance = new Battleship();
        instance.setHorizontal(false);
        instance.setBowRow(0);
        int row = instance.getBowRow();
        instance.setBowColumn(1);
        int column = instance.getBowColumn();
        for (int i = 0; i < instance.Length(); i++) {
            instance.shootAt(row, column);
            row++;
        }
        assertTrue(instance.isSunk());
    }
    
    /**
     * Test: the ship has not been sunk so the method returns <code>false</code>.
     */
    @Test
    public void testTheShipIsNotSunken() {
        System.out.println("--------testTheShipIsNotSunken-------");
        System.out.println("        For isSunk()");
        Ship instance = new Battleship();
        assertFalse(instance.isSunk());
    }
}
