package battleship;

import static battleship.Constants.ZERO;

/**
 * This class is a representation of a general ship archetipe for the game, to
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

    /**
     * Constructs a new <code>Ship</code> according to the parameters.
     *
     * @param length of the ship, as squared occupied.
     */
    public Ship(int length) {
        this.length = length;
        this.hit = new boolean[this.length];
        for (int i = ZERO; i < this.hit.length; i++) {
            this.hit[i] = false;
        }
    }

    /**
     * This method returns the row where the front is on.
     *
     * @return <code>bowRow</code>
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
     * This method stated whether the ship is on a horizontal position.
     *
     * @return <code>true</code> if the ship occupies a single row.
     */
    public boolean isHorizontal() {
        return this.horizontal;
    }

    /**
     * This method is to obtain how many squares the ship occupies.
     *
     * @return <code>lenght</code> of the ship.
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
     * @param row value for the bow.
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
     * @param column value for the bow.
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
     * @param horizontal <code>true</code> if the orientation is horizontal.
     */
    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }

    /**
     * This method checks where a ship has been hit. If a part of the ship
     * occupies the given row and column, and the ship hasn't been sunk, that
     * part of the ship will be considered as hit.
     *
     * @param row hit.
     * @param column hit.
     *
     * @return <code>true</code> if the ship was hit. <code>False</code> if the
     *          ship was already sunk.
     */
    @Override
    public boolean shootAt(int row, int column) {
        if (this.isSunk() == false) {
            int partFound = this.findPartHit(row, column, this.getBowRow(), this.getBowColumn());
            if (partFound < this.Length()) {
                this.hit[partFound] = true;
                if (this.isRealShip()) return true;
            }
        }
        return false;
    }

    /**
     * This method returns the part of the ship the given coordinates
     * match with. It first checks the front; then, if that is not the part hit,
     * it checks the rest of the ship. The method assumes the ship is certainly 
     * being hit; although in order to return a result even if used independently
     * and given different coordinates of the ones the ship is at, a value outside
     * the ship is returned, to be handled accordingly from whatever is the context
     * using this method.
     * 
     * @param row hit.
     * @param column hit.
     * @param rowToCompare row to compare the hit row against.
     * @param columnToCompare column to compare the hit row against.
     * 
     * @return <code>int</code> value that represents the part hit. Else, it 
     *          returns the value of the part AFTER the ship stern, a value to 
     *          be handled outside the method.
     * @see <code>Length</code>
     */
    private int findPartHit(int row, int column, int rowToCompare, int columnToCompare) {
        for (int i = ZERO; i < this.Length(); i++) {
            if (row == rowToCompare && column == columnToCompare) {
                return i;
            }
            if (this.horizontal) columnToCompare++;
            else rowToCompare++;
        }
        return this.Length(); // note: This condition will never happen with the current structure of the game.
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
     * This method returns the current status of the ship.
     *
     * @return the <code>boolean</code> array representing the ships' status.
     */
    public boolean[] getShipStatus() {
        return this.hit;
    }

    /**
     * This method checks whether the ship has been sunk or not, thus still
     * being in game.
     *
     * @return comparison of a count and <code>hit</code> array, indicating if
     *          any element in it is still <code>false</code> (not hit).
     * @see <code>Length</code>
     */
    public boolean isSunk() {
        int hitCount = ZERO;
        for (boolean i : this.hit) {
            if (i == true) hitCount++;
        }
        return hitCount == this.Length();
    }

    /**
     * This method prints the current status of the ship. 
     * . as part of the ship never hit, S if the part has been hit, 
     * the whole ship as X if sunken.
     *
     * @return the <code>String</code> representation of the status of the Ship.
     * @see <code>isSunken</code>, <code>getShipStatus</code>
     */
    @Override
    public String toString() {
        String shipStatus = "";
        boolean isSunken = this.isSunk();
        for (int i = ZERO; i < getShipStatus().length; i++) {
            if (isSunken) shipStatus += "X";
            else {
                shipStatus += (getShipStatus()[i]) ? "S" : ".";
            }
        }
        return shipStatus;
    }
}
