package battleship;

import java.util.Random;

import java.util.ArrayList;

import static battleship.Constants.ZERO;

import static battleship.Constants.ONE;

import static battleship.Constants.TEN;

/**
 *
 * @author Federico Cocco
 */
public class Ocean implements Damageable {

    /**
     * The ships (and empty spaces) on the board.
     */
    private Ship[][] ships;
    /**
     * The total number of shots fired by the user.
     */
    private int shotsFired;
    /**
     * Number of times a shot hit a ship.
     */
    private int hitCount;
    /**
     * Number of ships thrown out of the game(sunk).
     */
    private int shipsSunk;

    /**
     * Constructs a new <code>Ocean</code> according to the parameters.
     */
    public Ocean() {
        initialiseBoard(this.ships);
        this.shotsFired = ZERO;
        this.shipsSunk = ZERO;
        this.hitCount = ZERO;
    }

    /**
     * This method is to place all ten ships randomly on the (initially empty)
     * ocean (board).
     */
    public void placeAllShipsRandomly() {
        ArrayList<Ship> theShips = initialiseShips();
        for (Ship x : theShips) {
            this.placeShipRandomly(x, this.ships, new Random());
        }
    }

    /**
     * This method places a ship on the board in a random way.
     *
     * @param ship
     * @param board
     */
    private void placeShipRandomly(Ship ship, Ship[][] board, Random placer) {
        /* Setting variables. */
        int x = placer.nextInt(board.length),
                y = placer.nextInt(board.length),
                length = ship.Length();
        boolean ship_orientation = placer.nextBoolean();
        if (this.checkSpaceAvailability(x, y, length, board.length, ship_orientation)) {
            ship.setBowRow(placer.nextInt(board.length)); // Setting the position 
            ship.setBowColumn(placer.nextInt(board.length)); // of the front of the ship
            ship.setHorizontal(ship_orientation); //  and its orientation.
            for (int i = ZERO; i < length; i++) { // Iterate through the length of the ship.
                if (ship.isHorizontal()) {
                    board[x][y++] = ship;
                } else {
                    board[x++][y] = ship;
                }
            }
        } else {
            placeShipRandomly(ship, board, new Random()); //recursive call of the method with new placer.
        }
    }

    /**
     * This method check the availability on the board for a ship to be placed.
     * It check first if still is iterating inside whitin the board's borders,
     * the it checks all the surrounding spaces whether they are "empty". To
     * verify that, <code>isOccupied</code> method is used.
     *
     * It also checks when the loop first start, if it is not on the border,
     * whether the previous (diagonally as well) squares are occupied.
     *
     * This method aim to leave the ships completely detached from each other,
     * in all directions.
     *
     * @param co_x
     * @param co_y
     * @param lenght_loop
     * @param length_board
     *
     * @return <code>true</code> if it is possible to place a ship in the given
     * spaces.
     */
    private boolean checkSpaceAvailability(int co_x, int co_y, int lenght_loop,
            int length_board, boolean horizontality) {
        boolean isFree = false; //flag that states whether the ship can be placed.

        boolean flag = false; // flag to decide whether the loop start from o or -1.
        /**
         * check if there is need to consider to check the squares the squares
         * in front of the bow of the ship (<code>co_y</code> or
         * </code>co_x</code>, -1),
         */
        if (horizontality && co_y > ZERO) { // if horizontal
            co_y--;
            flag = true;
        }
        if (!horizontality && co_x > ZERO) { // or not.
            co_x--;
            flag = true;
        }
        for (int i = (flag) ? ZERO - ONE : ZERO; i <= lenght_loop; i++) {
            /* Checks if we are still the boundary of the board, if the current position is occupied
                and, if they exist, the surrounding squares. */
            if (co_x < length_board && co_y < length_board && !this.isOccupied(co_x, co_y)) {
                if (horizontality) {
                    if (co_x > ZERO && this.isOccupied(co_x - ONE, co_y)) {
                        break;
                    }
                    if (co_x < length_board - ONE && this.isOccupied(co_x + ONE, co_y)) {
                        break;
                    }
                    isFree = true;
                    co_y++;
                } else {
                    if (co_y > ZERO && this.isOccupied(co_x, co_y - ONE)) {
                        break;
                    }
                    if (co_y < length_board - ONE && this.isOccupied(co_x, co_y + ONE)) {
                        break;
                    }
                    isFree = true;
                    co_x++;
                }
            }
        }
        return isFree;
    }

    /**
     * This method initialise all the ships at the beginning of the game.
     *
     * @return an <code>ArrayList</code> containing the ships for the game.
     */
    private ArrayList<Ship> initialiseShips() {
        ArrayList<Ship> shipsInGame = new ArrayList<>();
        shipsInGame.add(new Battleship());
        shipsInGame.add(new Cruiser());
        shipsInGame.add(new Cruiser());
        shipsInGame.add(new Destroyer());
        shipsInGame.add(new Destroyer());
        shipsInGame.add(new Destroyer());
        shipsInGame.add(new Submarine());
        shipsInGame.add(new Submarine());
        shipsInGame.add(new Submarine());
        shipsInGame.add(new Submarine());
        shipsInGame = this.sorting(shipsInGame);
        return shipsInGame;
    }

    /**
     * This method sort a list of Ships in descending way by their size.
     *
     * @param toBeSorted
     *
     * @return the <code>ArrayList</code> sorted
     */
    private ArrayList<Ship> sorting(ArrayList<Ship> toBeSorted) {
        for (int i = ZERO; i < toBeSorted.size(); i++) {
            Ship current_el = toBeSorted.get(i);
            Ship next_el = toBeSorted.get(i + 1);
            if (current_el.Length() < next_el.Length()) {
                toBeSorted.set(i, next_el);
                toBeSorted.set(i, current_el);
            }
        }
        return toBeSorted;
    }

    /**
     * This method fill an array with the given object type.
     *
     * @param initArray
     */
    public static void initialiseBoard(Ship[][] initArray) {
        initArray = new Ship[TEN][TEN];
        for (int i = ZERO; i < initArray.length; i++) {
            for (int y = ZERO; y < initArray[i].length; y++) {
                initArray[i][y] = new EmptySea();
            }
        }
    }

    /**
     * Shoots at the part of the ship at that location. In addition, this method
     * updates the number of shots that have beenfired, and the number of hits.
     *
     *
     * Note: If a location contains a real ship, shootAt should return true
     * every time the user shoots at that same location. Once a ship has been
     * sunk, additional shots at its location should return false.
     *
     * @param row
     * @param column
     * @return <code>true</code> if the given location contains a real ship
     * still a float.
     */
    @Override
    public boolean shootAt(int row, int column) {
        if (this.ships[row][column].shootAt(row, column)) {
            this.hitCount++;
            return true;
        }
        if (!this.ships[row][column].isRealShip()) {
            ((EmptySea) this.ships[row][column]).hitEmptySea();
        }
        this.shotsFired++;
        return false;
    }

    /**
     * Returns true if the given location contains a ship, false if it does not.
     *
     * @param row
     * @param column
     * @return
     */
    public boolean isOccupied(int row, int column) {
        return !(this.ships[row][column].isRealShip());
    }

    /**
     * Returns true if the given location contains a ship, false if it does not.
     *
     * @param row
     * @param column
     * @return
     */
    public boolean hasSunkShipAt(int row, int column) {
        Ship ship_analysed = this.ships[row][column];
        return ship_analysed.isRealShip() && ship_analysed.isSunk();
    }

    /**
     * Returns the ship type at the given location (by calling the corresponding
     * method of that ship).
     *
     * @param row
     * @param column
     * @return
     */
    public String getShipTypeAt(int row, int column) {
        return this.ships[row][column].getShipType();
    }

    /**
     * Returns the number of shots fired.
     *
     * @return
     */
    public int getShotsFired() {
        return this.shotsFired;
    }

    /**
     * Returns the number of hits recorded. All hits are counted, not just the
     * first time a given square is hit (see also the documentation for the
     * shootAt method).
     *
     * @return
     */
    public int getHitCount() {
        return this.hitCount;
    }

    /**
     * Returns the number of ships sunk.
     *
     * @return
     */
    public int getShipsSunk() {
        return this.shipsSunk;
    }

    /**
     * Returns true if all ships have been sunk, otherwise false.
     *
     * @return true if all 10 ships were sunk.
     */
    public boolean isGameOver() {
        return this.getShipsSunk() >= TEN;
    }

    /**
     * Returns the 10x10 array of ships. (You will probably need this method for
     * testing. However, since it returns the actual array of actual ships, and
     * could therefore be modified by some class that has no right to do so, use
     * this method only in your unit testing.)
     *
     * @return the <code>ships</code> multidimensional array.
     */
    public Ship[][] getShipArray() {
        return this.ships;
    }

    /**
     * Prints the ocean on stdout. To aid the user, row numbers should be
     * displayed along the left edge of the array, and column numbers should be
     * displayed along the top. Numbers should be 0 to 9, not 1 to 10. The top
     * left corner square should be 0, 0. Use "S" to indicate a location that
     * you have fired upon and hit a (real) ship, "-" to indicate a location
     * that you have fired upon and found nothing there, "x" to indication a
     * location containing a sunken ship, and "." to indicate a location that
     * you have never fired upon. This is the only method in the Ocean class
     * that does any input/output, and it is never called from within the Ocean
     * class (except possibly during debugging), only from the BattleshipGame
     * class.
     */
    public void print() {
        for (int i = ZERO; i < this.ships.length; i++) {
            if (i > ZERO) {
                System.out.print(i - ONE);
            }
            for (int j = ZERO; j == this.ships.length; j++) {
                if (i == ZERO) {
                    if (j > ZERO) {
                        System.out.print(j);
                    }
                    continue;
                }
                Ship square = this.ships[i][j];
                if (!square.isRealShip()) {
                    if((EmptySea) square.isMissedShot()) {
                    } else {
                        System.out.print("");
                    }
                }
            }

        }
    }
}
