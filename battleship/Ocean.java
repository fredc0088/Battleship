package battleship;

import java.util.Random;

import java.util.ArrayList;

import static battleship.Constants.ZERO;

import static battleship.Constants.ONE;

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
     * Constructs a new <code>Ocean</code>. It also constructs the board
     * <code>ships</code> upon the game is played.
     */
    public Ocean() {
        this.ships = new Ship[TEN][TEN];
        for (int i = ZERO; i < ships.length; i++) {
            for (int y = ZERO; y < ships.length; y++) {
                this.ships[i][y] = new EmptySea();
                this.ships[i][y].setBowRow(i);
                this.ships[i][y].setBowColumn(y);

            }
        }
    }

    /**
     * This method initialise all the ships at the beginning of the game. To
     * have a future implementation using DI.
     *
     * @return an <code>ArrayList</code> containing the ships for the game.
     */
    private <T extends Ship> ArrayList initialiseShips() {
        ArrayList shipsInGame = new ArrayList<>();
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
     * This method sort a list of object generalising Ships in descending way by
     * their size.
     *
     * @param toBeSorted
     *
     * @return the <code>ArrayList</code> sorted
     */
    private <T extends Ship> void sorting(ArrayList<T> toBeSorted) {
        for (int i = ZERO; i < toBeSorted.size() - ONE; i++) {
            T current_el = toBeSorted.get(i);
            T next_el = toBeSorted.get(i + 1);
            if (current_el.Length() < next_el.Length()) {
                toBeSorted.set(i, next_el);
                toBeSorted.set(i, current_el);
            }
        }
    }

    /**
     * This method is to place all ships randomly on the (initially empty) ocean
     * (board). It takes an object that extends <code>Ship</code>.
     *
     * @param <T>
     */
    public <T extends Ship> void placeAllShipsRandomly() {
        ArrayList<T> theShips = initialiseShips();
        for (T x : theShips) {
            this.placeShipRandomly(x);
        }
    }

    /**
     * This method places a ship on the board in a randomic way.
     *
     * @param ship
     * @param board
     */
    private <T extends Ship> void placeShipRandomly(T ship) {
        /* Setting variables. */
        Random posAssigner = new Random();
        boolean shipIsNotPlaced = true;
        while (shipIsNotPlaced) {
            int x = posAssigner.nextInt(this.ships.length),
                    y = posAssigner.nextInt(this.ships.length);
            boolean ship_orientation = posAssigner.nextBoolean();
            if (this.checkSpaceAvailability(x, y, ship.Length(), ship_orientation)) {
                ship.setBowRow(x); // Setting the position 
                ship.setBowColumn(y); // of the front of the ship
                ship.setHorizontal(ship_orientation); //  and its orientation.
                for (int i = ZERO; i < ship.Length(); i++) { // Iterate through the length of the ship.
                    if (ship.isHorizontal()) {
                        this.ships[x][y++] = ship;
                    } else {
                        this.ships[x++][y] = ship;
                    }
                }
                shipIsNotPlaced = false;
            }
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
     * @param ship_length
     * @param length_board
     *
     * @return <code>true</code> if it is possible to place a ship in the given
     * spaces.
     */
    private boolean checkSpaceAvailability(int row, int column, int ship_length, boolean horizontality) {
        boolean isFreeSpace = false; //flag that states whether the ship can be placed.
        boolean notStartFromBorder = false; // notStartFromBorder to decide whether the loop start from o or -1.
        /*
         * check if there is need to consider to check the squares the squares
         * in front of the bow of the ship (column or row, -1),
         */
        if (horizontality && column > ZERO) { // if horizontal
            column--;
            notStartFromBorder = true;
        }
        if (!horizontality && row > ZERO) { // or not.
            row--;
            notStartFromBorder = true;
        }
        for (int i = (notStartFromBorder) ? ZERO - ONE : ZERO; i <= ship_length; i++) {
            /* Checks if we are still the boundary of the board, if the current 
            position is occupied and, if they exist, the surrounding squares. */
            if (row == this.ships.length || column == this.ships.length) {
                break;
            }
            if (this.isOccupied(row, column) == false) {
                if (horizontality) {
                    if (row > ZERO && this.isOccupied(row - ONE, column)) {
                        if (i == ship_length) {
                            isFreeSpace = false;
                        }
                        break;
                    }
                    if (row < this.ships.length - ONE && this.isOccupied(row + ONE, column)) {
                        if (i == ship_length) {
                            isFreeSpace = false;
                        }
                        break;
                    }
                    column++;
                } else {
                    if (column > ZERO && this.isOccupied(row, column - ONE)) {
                        if (i == ship_length) {
                            isFreeSpace = false;
                        }
                        break;
                    }
                    if (column < this.ships.length - ONE && this.isOccupied(row, column + ONE)) {
                        if (i == ship_length) {
                            isFreeSpace = false;
                        }
                        break;
                    }
                    row++;
                }
                if (i == ship_length - ONE) { // set to true if all space TO BE OCCUPIED is checked
                    isFreeSpace = true;
                }
            } else if (i == ship_length && this.isOccupied(row, column)) { // set notStartFromBorder back to false
                isFreeSpace = false;                                          // if the space behind the ship
            }                                                            // is occupied       
        }
        return isFreeSpace;
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
            this.hitCount++; // updates total hits
            return true;
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
     * Prints the ocean on stdout.
     *
     * The squares are counted from 0 to 9. Here is a legend of the significance
     * of what is going to appear on ouput: S : a place where a ship in that
     * position was hit. - : a shot hit an empty space. X : a sunken ship. . :
     * this position has not been shot yet.
     *
     * In order to achieve that, the method control the status of the ship,
     * received by the current ship, and decide which where to print it.
     */
    public void print() {
        for (int i = ZERO - ONE; i < this.ships.length; i++) {
            if (i >= ZERO) {
                System.out.print(i);
            }
            for (int j = ZERO - ONE; j < this.ships.length; j++) {
                if (i < ZERO) {
                    System.out.print((j >= ZERO) ? j : " ");
                } else if (i >= ZERO && j >= ZERO) {
                    if (this.ships[i][j].isHorizontal()) {
                        int column = ships[i][j].getBowColumn();
                        int result = ((column - j) < ZERO) ? (ZERO - (column - j)) : column - j;
                        System.out.print(this.ships[i][j].toString().charAt(result));
                    } else {
                        int row = ships[i][j].getBowRow();
                        int result = ((row - ZERO) < 0) ? (ZERO - (row - i)) : row - i;
                        System.out.print(this.ships[i][j].toString().charAt(result));
                    }
                }
            }
            System.out.println(" ");
        }
    }
}
