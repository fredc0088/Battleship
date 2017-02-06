package Test.battleship;

import battleship.*;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.*;
import java.util.HashSet;
import java.util.Set;
import java.lang.Class;
import java.lang.Class.*;

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
     *
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

    @Test
    public void test10ShipsArePlacedAndRigthQuantityForEachType() {
        System.out.println("--------test10ShipsArePlacedAndRigthQuantityForEachType-------");
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

    @Test
    public void testShipsAreCorrectlyDistantiated() {
        System.out.println("--------testShipsAreCorrectlyDistantiated-------");
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
                            noDistance = true; if(noDistance)break TestLoop;
                        }
                        noDistance = (board[i - 1][j] instanceof EmptySea) ? false : !(board[i - 1][j].equals(board[i][j]));
                        if(noDistance)break TestLoop;
                    }
                    if (j > 0) {
                        if (i > 0 && (board[i - 1][j - 1] instanceof EmptySea) == false) {
                            noDistance = true; if(noDistance)break TestLoop;
                        }
                        noDistance = (board[i][j - 1] instanceof EmptySea) ? false : !(board[i][j - 1].equals(board[i][j]));
                        if(noDistance)break TestLoop;
                    }
                    if (i < board.length - 1) {
                        if (j > 0 && (board[i + 1][j - 1] instanceof EmptySea) == false) {
                            noDistance = true; if(noDistance)break TestLoop;
                        }
                        noDistance = (board[i + 1][j] instanceof EmptySea) ? false : !(board[i + 1][j].equals(board[i][j]));
                        if(noDistance)break TestLoop;
                        if ((j < board.length - 1) && (board[i + 1][j + 1] instanceof EmptySea) == false) {
                            noDistance = true; if(noDistance)break TestLoop;
                        }
                    }
                    if (j < board.length - 1) {
                        noDistance = (board[i][j + 1] instanceof EmptySea) ? false : !(board[i][j + 1].equals(board[i][j]));
                        if(noDistance)break TestLoop;
                    }
                }
            }
        }
        if (noDistance) {
            fail("The distance of at least 1 square between ships has not been respected.");
        }
    }

//    @Test
//    public void testGetShipTypeAtGivenPosition() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
//        System.out.println("--------testGetShipTypeAtGivenPosition-------");
//        System.out.println("        For getShipTypeAt()");
//        Ocean instance = new Ocean();
//        instance.placeAllShipsRandomly();
//        Ship[][] board = instance.getShipArray();
//        board[7][5] = new Submarine();
//        Field ships = instance.getClass().getField("ships");
//        ships.set(instance, "reflecting on life");
//    }
//    
    
    public void testGetShipTypeAtGivenPosition() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        System.out.println("--------testGetShipTypeAtGivenPosition-------");
        System.out.println("        For getShipTypeAt()");
        Ocean instance = new Ocean();
        instance.placeAllShipsRandomly();
        Ship[][] board = instance.getShipArray();
        
    }
    
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
