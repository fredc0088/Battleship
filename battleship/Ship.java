package battleship;

import static battleship.Constants.ZERO;

import static battleship.Constants.ONE;

/**
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
        this.bowRow = row;
    }

    /**
     * Set the value of <code>bowColumn</code>.
     *
     * @param column
     */
    public void setBowColumn(int column) {
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
        if (this.isSunk() == false && this.occupiesSlot(row, column, ONE)
                && this.hit[this.currentPart] == false) {
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
        return hitCount == hit.length;
    }

    private boolean occupiesSlot(int row, int column, int count) {
        if (row == this.getBowRow() && column == this.getBowColumn()) {
            this.currentPart = count;
            return true;
        } else {
            if (count == this.length) {
                return false;
            }
            if (this.isHorizontal()) {
                occupiesSlot(row, column + ONE, count + ONE);
            } else {
                occupiesSlot(row + ONE, column, count + ONE);
            }
        }
        return false;
    }

    private boolean[] initialise(boolean val) {
        boolean[] initArray = new boolean[length];
        for (int i = ZERO; i < initArray.length; i++) {
            initArray[i] = val;
        }
        return initArray;
    }
    
}
