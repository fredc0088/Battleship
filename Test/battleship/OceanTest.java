package Test.battleship;

import battleship.*;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.*;
import java.util.HashSet;
import java.util.Set;

/**
 * This is the test for the Ocean class in battleship package.
 * 
 * @author Federico Cocco
 */
public class OceanTest {

    public OceanTest() {
    }

    /**
     * Test: when ocean is initialied, the board must contain only
     * <code>EmptySea</code>
     */
    @Test
    public void testTheOceanIsInitiallyEmpty() {
        System.out.println("--------testTheOceanIsInitiallyEmpty-------");
        Ocean instance = new Ocean();
        Ship[][] board = instance.getShipArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (!(board[i][j] instanceof EmptySea)) {
                    fail("Position at coordinates " + i + "and " + j + " is not an empty space class or being filled.");
                }
            }
        }
    }

    /**
     * Test: check that there are 10 ships placed on the board and for each
     *      type: 
     *          Name    Quantity 
     *      ----------- --------- 
     *       Battleship     1 
     *       Cruiser        2
     *       Destroyer      3 
     *       Submarine      4
     */
    @Test
    public void test10ShipsArePlacedAndRigthQuantityForEachType() {
        System.out.println("--------test10ShipsArePlacedAndRigthQuantityForEachType-------");
        System.out.println("        For placeAllShipsRandomly()");
        Ocean instance = new Ocean();
        instance.placeAllShipsRandomly();
        Ship[][] board = instance.getShipArray();
        Set<Ship> testAgainst = new HashSet<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].isRealShip()) {
                    testAgainst.add(board[i][j]);
                }
            }
        }
        if (testAgainst.size() < 10) {
            fail("There are less ships that it should.");
        } else if (testAgainst.size() > 10) {
            fail("Thre are more ships than it should.");
        }
        int submarine = 0, destroyer = 0, cruiser = 0, battleship = 0;
        for (Ship s : testAgainst) {
            switch (s.getClass().getSimpleName()) {
                case "Battleship":
                    battleship++;
                    break;
                case "Cruiser":
                    cruiser++;
                    break;
                case "Destroyer":
                    destroyer++;
                    break;
                case "Submarine":
                    submarine++;
                    break;
                default:
            }
        }
        if (submarine != 4 || destroyer != 3 || cruiser != 2 || battleship != 1) {
            fail("The right number of ships for each type was not correctly placed");
        }
    }

    /**
     * Test: check if the placed ships are at least a space away from each
     *      other.
     */
    @Test
    public void testShipsAreCorrectlyDistantiated() {
        System.out.println("--------testShipsAreCorrectlyDistantiated-------");
        System.out.println("        For placeAllShipsRandomly()");
        Ocean instance = new Ocean();
        instance.placeAllShipsRandomly();
        Ship[][] board = instance.getShipArray();
        boolean noDistance = false;
        TestLoop:
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (!(board[i][j] instanceof EmptySea)) {
                    if (i > 0) {
                        if (j < board.length - 1 && !(board[i - 1][j + 1] instanceof EmptySea)) {
                            noDistance = true;
                            if (noDistance) {
                                break TestLoop;
                            }
                        }
                        noDistance = (board[i - 1][j] instanceof EmptySea) ? false : !(board[i - 1][j].equals(board[i][j]));
                        if (noDistance) {
                            break TestLoop;
                        }
                    }
                    if (j > 0) {
                        if (i > 0 && (board[i - 1][j - 1] instanceof EmptySea) == false) {
                            noDistance = true;
                            if (noDistance) {
                                break TestLoop;
                            }
                        }
                        noDistance = (board[i][j - 1] instanceof EmptySea) ? false : !(board[i][j - 1].equals(board[i][j]));
                        if (noDistance) {
                            break TestLoop;
                        }
                    }
                    if (i < board.length - 1) {
                        if (j > 0 && (board[i + 1][j - 1] instanceof EmptySea) == false) {
                            noDistance = true;
                            if (noDistance) {
                                break TestLoop;
                            }
                        }
                        noDistance = (board[i + 1][j] instanceof EmptySea) ? false : !(board[i + 1][j].equals(board[i][j]));
                        if (noDistance) {
                            break TestLoop;
                        }
                        if ((j < board.length - 1) && (board[i + 1][j + 1] instanceof EmptySea) == false) {
                            noDistance = true;
                            if (noDistance) {
                                break TestLoop;
                            }
                        }
                    }
                    if (j < board.length - 1) {
                        noDistance = (board[i][j + 1] instanceof EmptySea) ? false : !(board[i][j + 1].equals(board[i][j]));
                        if (noDistance) {
                            break TestLoop;
                        }
                    }
                }
            }
        }
        if (noDistance) {
            fail("The distance of at least 1 square between ships has not been respected.");
        }
    }

    /**
     * Test: method getShipTypeAt retrieves the type of ship at given
     *      coordinates.
     */
    @Test
    public void testGetShipTypeAtGivenPosition() {
        System.out.println("--------testGetShipTypeAtGivenPosition-------");
        System.out.println("        For getShipTypeAt()");
        int row = 0;
        int column = 0;
        Ocean instance = new Ocean();
        instance.placeAllShipsRandomly();
        Ship[][] board = instance.getShipArray();
        Outerloop:
        for (Ship[] b : board) {
            for (Ship ship : b) {
                if (ship instanceof Submarine) {
                    row = ship.getBowRow();
                    column = ship.getBowColumn();
                    break Outerloop;
                }
            }
        }
        assertEquals(instance.getShipTypeAt(row, column), "submarine");
    }

    /**
     * Test: method getShipTypeAt retrieves "empty sea" type at give
     *      coordinates.
     */
    @Test
    public void testGetShipTypeAtGivenPositionEmptySea() {
        System.out.println("--------testGetShipTypeAtGivenPositionEmptySea-------");
        System.out.println("        For getShipTypeAt()");
        Ocean instance = new Ocean();
        assertEquals(instance.getShipTypeAt(4, 5), "empty sea");
    }

    /**
     * Test: shooting to a space with empty sea in it should return
     *      <code>false</code>.
     */
    @Test
    public void testShootAtEmptySpace() {
        System.out.println("--------testShootAtEmptySpace-------");
        System.out.println("        For shootAt()");
        Ocean instance = new Ocean();
        assertFalse(instance.shootAt(6, 9));
    }
    
    /**
     * Test: shooting to a space with empty sea in it should return
     *      <code>false</code>.
     */
    @Test
    public void testFiringIncreaseCount() {
        System.out.println("--------testFiringIncreaseCount-------");
        System.out.println("        For shootAt() and getShotsFired()");
        Ocean instance = new Ocean();
        assertFalse(instance.shootAt(6, 9));
    }

    /**
     * Test: shooting to any part of a floating ship should return
     *      <code>true</code>.
     */
    @Test
    public void testShootAtShipAndGetTrue() throws NullPointerException {
        System.out.println("--------testShootAtShipAndGetTrue-------");
        System.out.println("        For shootAt()");
        Ship toTest = null;
        Ocean instance = new Ocean();
        instance.placeAllShipsRandomly();
        Ship[][] board = instance.getShipArray();
        Outerloop:
        for (Ship[] b : board) {
            for (Ship ship : b) {
                if (!(ship instanceof EmptySea)) {
                    toTest = ship;
                    break Outerloop;
                }
            }
        }
        if (toTest != null) {
            assertTrue(instance.shootAt(toTest.getBowRow(), toTest.getBowColumn()));
        }
    }

    /**
     * Test: shooting to a space with a sunken ship should return
     *      <code>false</code>.
     */
    @Test
    public void testShootAtSunkenShip() throws NullPointerException {
        System.out.println("--------testShootAtSunkenShip-------");
        System.out.println("        For shootAt()");
        Ship toTest = null;
        Ocean instance = new Ocean();
        instance.placeAllShipsRandomly();
        Ship[][] board = instance.getShipArray();
        Outerloop:
        for (Ship[] b : board) {
            for (Ship ship : b) {
                if (!(ship instanceof EmptySea)) {
                    toTest = ship;
                    break Outerloop;
                }
            }
        }
        boolean isHorizontal;
        if (toTest != null) {
            isHorizontal = toTest.isHorizontal();
            int row = toTest.getBowRow(), column = toTest.getBowColumn();
            for (int i = 0; i < toTest.Length(); i++) {
                toTest.shootAt(row, column);
                if (isHorizontal) {
                    column++;
                } else {
                    row++;
                }
            }
            assertFalse(instance.shootAt(toTest.getBowRow(), toTest.getBowRow()));
        }
    }

    /**
     * Test: sunking a ship should increase the count of ships sunken in the game so far.
     */
    @Test
    public void testHittingAShipUpdatesTheSunkenShipsCount() throws NullPointerException {
        System.out.println("--------testHittingAShipUpdatesTheSunkenShipsCount-------");
        System.out.println("        For shootAt() and getShipsSunk()");
        Ship toTest = null;
        Ocean instance = new Ocean();
        instance.placeAllShipsRandomly();
        Ship[][] board = instance.getShipArray();
        Outerloop:
        for (Ship[] b : board) {
            for (Ship ship : b) {
                if (!(ship instanceof EmptySea)) {
                    toTest = ship;
                    break Outerloop;
                }
            }
        }
        boolean isHorizontal;
        if (toTest != null) {
            isHorizontal = toTest.isHorizontal();
            int row = toTest.getBowRow(), column = toTest.getBowColumn();
            for (int j = 0; j < toTest.Length(); j++) {
                instance.shootAt(row, column);
                if (isHorizontal) {
                    column++;
                } else {
                    row++;
                }
            }
            int expectedResult = 1;
            assertEquals(expectedResult,instance.getShipsSunk());
        }
    }
    
    /**
     * Test: hitting/sunking an already sunken ship should not
     *      increase the count of ships sunken.
     */
    @Test
    public void testSinkingSameShipDoesNotUpdatesTheSunkenShipsCount() throws NullPointerException {
        System.out.println("--------testSinkingSameShipDoesNotUpdatesTheSunkenShipsCount-------");
        System.out.println("        For shootAt() and getShipsSunk()");
        Ship toTest = null;
        Ocean instance = new Ocean();
        instance.placeAllShipsRandomly();
        Ship[][] board = instance.getShipArray();
        Outerloop:
        for (Ship[] b : board) {
            for (Ship ship : b) {
                if (!(ship instanceof EmptySea)) {
                    toTest = ship;
                    break Outerloop;
                }
            }
        }
        boolean isHorizontal;
        if (toTest != null) {
            isHorizontal = toTest.isHorizontal();
            int row = toTest.getBowRow(), column = toTest.getBowColumn();
            int i = 0;
            while(i < 2) {
                for (int j = 0; j < toTest.Length(); j++) {
                    instance.shootAt(row, column);
                    if (isHorizontal) {
                        column++;
                    } else {
                        row++;
                    }
                }
                i++;
            }
            int expectedResult = 1;
            assertEquals(expectedResult,instance.getShipsSunk());
        }
    }

    /**
     * Test: if there is a sunken ship in that space, it should return <code>true</code>.
     */
    @Test
    public void testChecksItHasSunkenShipFromGivenLocation() throws NullPointerException {
        System.out.println("--------testChecksItHasSunkenShipFromGivenLocation-------");
        System.out.println("        For hasSunkenShipsAt()");
        Ship toTest = null;
        Ocean instance = new Ocean();
        instance.placeAllShipsRandomly();
        Ship[][] board = instance.getShipArray();
        Outerloop:
        for (Ship[] b : board) {
            for (Ship ship : b) {
                if (!(ship instanceof EmptySea)) {
                    toTest = ship;
                    break Outerloop;
                }
            }
        }
        if (toTest != null) {
            int row = toTest.getBowRow(), column = toTest.getBowColumn();
            boolean isHorizontal = toTest.isHorizontal();
            for (int i = 0; i < toTest.Length(); i++) {
                    toTest.shootAt(row, column);
                    if (isHorizontal) {
                        column++;
                    } else {
                        row++;
                    }
                }
            assertTrue(instance.hasSunkShipAt(toTest.getBowRow(), toTest.getBowColumn()));
        }
    }

    /**
     * Test: if there is not a sunken ship in that space but an empty space, 
     *      it should return <code>false</code>.
     */
    @Test
    public void testChecksThereIsNotSunkenShipAtGivenLocationButEmptySpace() {
        System.out.println("--------testChecksThereIsNotSunkenShipAtGivenLocationButEmptySpace-------");
        System.out.println("        For hasSunkenShipsAt()");
        Ocean instance = new Ocean();
        assertFalse(instance.hasSunkShipAt(4,6));
    }
    
    /**
     * Test: if there is not a sunken ship in that space but a still floating one
     *          while being shot in the part occuping that space,
     *          it should return <code>false</code>.
     */
    @Test
    public void testChecksThereIsNotSunkenShipAtGivenLocationButAFloatingShip() throws NullPointerException {
        System.out.println("--------testChecksThereIsNotSunkenShipAtGivenLocationButAFloatingShip-------");
        System.out.println("        For hasSunkenShipsAt()");
        Ship toTest = null;
        Ocean instance = new Ocean();
        instance.placeAllShipsRandomly();
        Ship[][] board = instance.getShipArray();
        Outerloop:
        for (Ship[] b : board) {
            for (Ship ship : b) {
                if (!(ship instanceof EmptySea)) {
                    toTest = ship;
                    break Outerloop;
                }
            }
        }
        if (toTest != null) {
            int row = toTest.getBowRow(), column = toTest.getBowColumn();
            assertFalse(instance.hasSunkShipAt(row,column));
        }
    }
    
    /**
     * Test: if there is not a sunken ship in that space but a still floating one
     *       while being shot in the part occuping that space,it should return 
     *       <code>false</code>.
     */
    @Test
    public void testChecksThereIsNotSunkenShipAtGivenLocationButAHitFloatingShip() throws NullPointerException {
        System.out.println("--------testChecksThereIsNotSunkenShipAtGivenLocationButAHitFloatingShip-------");
        System.out.println("        For hasSunkenShipsAt()");
        Ship toTest = null;
        Ocean instance = new Ocean();
        instance.placeAllShipsRandomly();
        Ship[][] board = instance.getShipArray();
        Outerloop:
        for (Ship[] b : board) {
            for (Ship ship : b) {
                if (!(ship instanceof EmptySea) && !(ship instanceof Submarine)) {
                    toTest = ship;
                    break Outerloop;
                }
            }
        }
        if (toTest != null) {
            int row = toTest.getBowRow(), column = toTest.getBowColumn();
            toTest.shootAt(row, column);
            assertFalse(instance.hasSunkShipAt(row,column));
        }
    }
    
    /**
     * Test: there is no ship occuping a specific space, thus <code>false</code>
     *       should be returned.
     */
    @Test
    public void testNoShipOccupiesAGivenSpace() {
        System.out.println("--------testNoShipOccupiesAGivenSpace-------");
        System.out.println("        For isOccupied()");
        Ocean instance = new Ocean();
        assertFalse(instance.isOccupied(3, 7));
    }
    
    /**
     * Test: there is a ship occuping a specific space, thus <code>true</code>
     *       should be returned.
     */
    @Test
    public void testShipOccupiesAGivenSpace() throws NullPointerException {
        System.out.println("--------testShipOccupiesAGivenSpace-------");
        System.out.println("        For isOccupied()");
        Ship toTest = null;
        Ocean instance = new Ocean();
        instance.placeAllShipsRandomly();
        Ship[][] board = instance.getShipArray();
        Outerloop:
        for (Ship[] b : board) {
            for (Ship ship : b) {
                if (!(ship instanceof EmptySea) && !(ship instanceof Submarine)) {
                    toTest = ship;
                    break Outerloop;
                }
            }
        }
        if (toTest != null) {
            int row = toTest.getBowRow(), column = toTest.getBowColumn();
            assertTrue(instance.isOccupied(row,column));
        }
    }
    
    
    /**
     * Test: with all ships sunken, the game ends.
     */
    @Test
    public void testGameOver() {
        System.out.println("--------testShipsAreCorrectlyDistantiated-------");
        System.out.println("        For isGameOver()");
        Ocean instance = new Ocean();
        instance.placeAllShipsRandomly();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                instance.shootAt(i, j);
            }
        }
        assertTrue(instance.isGameOver());
    }
}
