package battleship;

import static battleship.Constants.ZERO;

import static battleship.Constants.ONE;

/**
 * This class is a represnetation of a general ship archetipe for the game, to
 * be used to construct specific types of ships.
 *
 * @author Federico Cocco
 */
public abstract class Ship implements Damageable {

    /* The row containing the front of the ship. */
    private int bowRow;

    /* The column containing the front of the ship. */
    private int bowColumn;

    /* Number of squares occupied by the ship */
    private final int length;

    /* Whether the ship is in a horizonatal position or not. */
    private boolean horizontal;

    /* The part(s) of the ship (square) hit. */
    private boolean[] hit;

    /* Indicate which part of the ship is being the current context. */
    private int currentPart;

    /**
     * Constructs a new <code>Ship</code> according to the parameters.
     *
     * @param length
     */
    public Ship(int length) {
        this.length = length;
        this.hit = initialise(false);
    }

    /**
     * This method returns the row where the front is on.
     *
     * @return int <code>bowRow</code>
     */
    public int getBowRow() {
        return this.bowRow;
    }

    /**
     * This method returns the column where the front is on.
     *
     * @return <code>bowColumn</code>
     */
    public int getBowColumn() {
        return this.bowColumn;
    }

    /**
     * This method stated whther the ship is on a horizantal position.
     *
     * @return <code>true</code> if the ship occupies a single row.
     */
    public boolean isHorizontal() {
        return this.horizontal;
    }

    /**
     * This method is to obtain how many squares the ship occupies.
     *
     * @return <code>lenght</code>
     */
    public int Length() {
        return this.length;
    }

    /**
     * This method returns the type of ship of the istance.
     *
     */
    abstract String getShipType();

    /**
     * Set the value of <code>bowRow</code>.
     *
     * @param row
     */
    public void setBowRow(int row) {
        if (row < 0) {
            throw new IllegalArgumentException("Only positive integers allowed");
        }
        this.bowRow = row;
    }

    /**
     * Set the value of <code>bowColumn</code>.
     *
     * @param column
     */
    public void setBowColumn(int column) {
        if (column < 0) {
            throw new IllegalArgumentException("Only positive integers allowed");
        }
        this.bowColumn = column;
    }

    /**
     * Set whether the ship is in horizontal position.
     *
     * @param horizontal
     */
    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }

    /**
     * This method checks whether a ship has been hit or not. If a part of the
     * ship occupies the given row and column, and the ship hasn't been sunk,
     * that part of the ship will be considered as hit.
     *
     * @param row
     * @param column
     *
     * @return <code>true</code> if the ship was hit.
     */
    @Override
    public boolean shootAt(int row, int column) {
        if (this.isSunk() == false) {
            this.findPartHit(row, column, this.getBowRow(), this.getBowColumn(), ZERO);
            this.hit[this.currentPart] = true;
            return true;
        }
        return false;
    }

    /**
     * This method indicates whether this is a real entity ship or empty space.
     * It is an helper to set the board.
     *
     * @return <code>true</code>
     */
    public boolean isRealShip() {
        return true;
    }

    /**
     * This method checks whether the ship has been sunk or not, thus still
     * being in game.
     *
     * @return comparison of a count and <code>hit</code> array, indicating if
     * any element in it is still <code>false</code> (not hit)
     */
    public boolean isSunk() {
        int hitCount = ZERO;
        for (boolean i : this.hit) {
            if (i == true) {
                hitCount++;
            }
        }
        return hitCount == this.Length();
    }

    /**
     * This helper method checks whether the given coordinates on the board
     * match with any part of the ship. It first checks the front, if that is
     * not the part hit, it recursevely checks the rest of the ship.
     *
     * @param row
     * @param column
     * @param rowToCompare
     * @param columnToCompare
     * @param count
     * @return <code>false</code> if no part was hit, otherwise
     * <code>true</code>.
     */
    private void findPartHit(int row, int column, int rowToCompare, int columnToCompare, int shipPart) {
        if (row != rowToCompare || column != columnToCompare) {
            if (this.isHorizontal()) {
                findPartHit(row, column, rowToCompare, columnToCompare + ONE, shipPart + ONE);
            } else {
                findPartHit(row, column, rowToCompare + ONE, columnToCompare, shipPart + ONE);
            }
        } else if (row == rowToCompare && column == columnToCompare) {
            this.currentPart = shipPart;
        }
    }

    /**
     * Initialise a new array with <code>true</code> or <code>false</code>.
     *
     * @param val
     * @return a filled array of <code>boolean</code> values.
     */
    private boolean[] initialise(boolean val) {
        boolean[] initArray = new boolean[this.Length()];
        for (int i = ZERO; i < initArray.length; i++) {
            initArray[i] = val;
        }
        return initArray;
    }
}
