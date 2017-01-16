package battleship;

import java.util.Random;

import java.util.ArrayList;

import static battleship.Constants.ZERO;

import static battleship.Constants.ONE;

import static battleship.Constants.TWO;

import static battleship.Constants.TEN;

/**
 * This class is a representation of the game and the board. It is a 10x10
 * board, then 10 ships are randomly placed on it, without touching each other
 * on any directions. The user needs to enter the two coordinates (as integers)
 * to try to shoot down any of those ships. For point's sake a counter of hit
 * ships, sunken ships and overall shots fired are kept. When 10 ships are
 * sunken, the game ends.
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
     * The graphical representation of the board and its current status.
     */
    private String[][] battlefield;

    {
        this.shotsFired = ZERO;
        this.shipsSunk = ZERO;
        this.hitCount = ZERO;
    }

    /**
     * Constructs a new <code>Ocean</code> according to the parameters.
     */
    public Ocean() {
        this.ships = initialiseBoard();
        this.battlefield = initialiseBattlefield();
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
            ship.setBowRow(x); // Setting the position 
            ship.setBowColumn(y); // of the front of the ship
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
     * @param row
     * @param column
     * @param lenght_loop
     * @param length_board
     *
     * @return <code>true</code> if it is possible to place a ship in the given
     * spaces.
     */
    private boolean checkSpaceAvailability(int row, int column, int lenght_loop,
            int length_board, boolean horizontality) {
        boolean isFree = false; //flag that states whether the ship can be placed.
        boolean flag = false; // flag to decide whether the loop start from o or -1.
        /*
         * check if there is need to consider to check the squares the squares
         * in front of the bow of the ship (column or row, -1),
         */
        if (horizontality && column > ZERO) { // if horizontal
            column--;
            flag = true;
        }
        if (!horizontality && row > ZERO) { // or not.
            row--;
            flag = true;
        }
        for (int i = (flag) ? ZERO - ONE : ZERO; i <= lenght_loop; i++) {
            /* Checks if we are still the boundary of the board, if the current 
            position is occupied and, if they exist, the surrounding squares. */
            if (row == length_board || column == length_board) {
                break;
            }
            if (this.isOccupied(row, column) == false) {
                if (horizontality) {
                    if (row > ZERO && this.isOccupied(row - ONE, column)) {
                        if (i == lenght_loop) {
                            isFree = false;
                        }
                        break;
                    }
                    if (row < length_board - ONE && this.isOccupied(row + ONE, column)) {
                        if (i == lenght_loop) {
                            isFree = false;
                        }
                        break;
                    }
                    column++;
                } else {
                    if (column > ZERO && this.isOccupied(row, column - ONE)) {
                        if (i == lenght_loop) {
                            isFree = false;
                        }
                        break;
                    }
                    if (column < length_board - ONE && this.isOccupied(row, column + ONE)) {
                        if (i == lenght_loop) {
                            isFree = false;
                        }
                        break;
                    }
                    row++;
                }
                if (i == lenght_loop - ONE) { // set to true if all space TO BE OCCUPIED is checked
                    isFree = true;
                }
            } else if (i == lenght_loop && this.isOccupied(row, column)) { // set flag back to false
                isFree = false;                                          // if the space behind the ship
            }                                                            // is occupied       
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
        this.sorting(shipsInGame);
        return shipsInGame;
    }

    /**
     * This method sort a list of Ships in descending way by their size.
     *
     * @param toBeSorted
     *
     * @return the <code>ArrayList</code> sorted
     */
    private void sorting(ArrayList<Ship> toBeSorted) {
        for (int i = ZERO; i < toBeSorted.size() - ONE; i++) {
            Ship current_el = toBeSorted.get(i);
            Ship next_el = toBeSorted.get(i + 1);
            if (current_el.Length() < next_el.Length()) {
                toBeSorted.set(i, next_el);
                toBeSorted.set(i, current_el);
            }
        }
    }

    /**
     * This method fill an array with the given object type.
     *
     */
    private static Ship[][] initialiseBoard() {
        Ship[][] initArray = new Ship[TEN][TEN];
        for (int i = ZERO; i < initArray.length; i++) {
            for (int y = ZERO; y < initArray[i].length; y++) {
                initArray[i][y] = new EmptySea();
            }
        }
        return initArray;
    }

    /**
     * This method fill an array with <code>String</code> objects.
     *
     */
    private static String[][] initialiseBattlefield() {
        String[][] initArray = new String[TEN][TEN];
        for (int i = ZERO; i < initArray.length; i++) {
            for (int y = ZERO; y < initArray[i].length; y++) {
                initArray[i][y] = ".";
            }
        }
        return initArray;
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
     * still floating.
     */
    @Override
    public boolean shootAt(int row, int column) {
        if (row != (int) row || column != (int) column) {
            throw new IllegalArgumentException("Only entire numbers are allowed");
        }
        if (row < ZERO || column < ZERO) {
            throw new IllegalArgumentException("Must enter only positive values");
        }
        if (row >= TEN || column >= TEN) {
            throw new IllegalArgumentException("Values cannot be higher than the board");
        }
        this.shotsFired++; // updates total shots fired
        if (this.ships[row][column].shootAt(row, column)) {
            if (this.hasSunkShipAt(row, column)) {
                this.shipsSunk++; // updates total ship sunken
            }
            if (!"S".equals(this.battlefield[row][column])) {
                this.updateOceanGraphic(row, column, "S");
                this.hitCount++; // updates total hits
            }
            return true;
        }
        if (this.ships[row][column].isRealShip() == false) {
            this.updateOceanGraphic(row, column, "-");
        }
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
        return this.ships[row][column].isRealShip();
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
        return ship_analysed.isSunk();
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
     * Gets how many shots have been fired until that moment during the current
     * game.
     *
     * @return the number of shots fired.
     */
    public int getShotsFired() {
        return this.shotsFired;
    }

    /**
     * Gets how many succesful hits has been shot until that moment during the
     * current game. All hits are counted, not just the first time a given
     * square is hit.
     *
     * @return the number hits.
     */
    public int getHitCount() {
        return this.hitCount;
    }

    /**
     * Gets how many ships has been sunken until that moment during the current
     * game.
     *
     * @return the number of ships sunken.
     */
    public int getShipsSunk() {
        return this.shipsSunk;
    }

    /**
     * Returns true if all ships have been sunk, otherwise false.
     *
     * @return true if all 10 ships were sunken.
     */
    public boolean isGameOver() {
        return this.getShipsSunk() >= TEN;
    }

    /**
     * Returns the 10x10 array of ships. Method solely for testing.
     *
     * @return the <code>ships</code> multidimensional array.
     */
    public Ship[][] getShipArray() {
        return this.ships;
    }

    /**
     * This method update a position in the graphical representation of the ship
     * with a given new <code>String</code>.
     *
     * @param row
     * @param column
     * @param update
     */
    private void updateOceanGraphic(int row, int column, String update) {
        this.battlefield[row][column] = update;
    }

    /**
     * Prints the ocean on stdout.
     *
     * The squares are counted from 0 to 9. Here is a legend of the significance
     * of what is going to appear on ouput: S : a place where a ship in that
     * position was hit. - : a shot hit an empty space. X : a sunken ship. . :
     * this position has not been shot yet.
     */
    public void print() {
        for (int i = ZERO - ONE; i <= this.ships.length; i++) {
            if (i >= ZERO && i < this.ships.length) {
                System.out.print(i + "  ");
            }
            for (int j = ZERO - TWO; j <= this.ships.length; j++) {
                try {
                    if (i < ZERO) {
                        if (j >= ONE) {
                            System.out.print(j - ONE);
                        } else {
                            System.out.print(" ");
                        }
                        continue;
                    }
                    if (this.hasSunkShipAt(i, j)) {
                        System.out.print("X");
                    } else {
                        System.out.print(this.battlefield[i][j]);
                    }
                } catch (IndexOutOfBoundsException ex) {
                }
            }
            System.out.println(" ");
        }
    }
}
