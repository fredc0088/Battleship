package battleship;

import static battleship.Constants.ONE;
import static battleship.Constants.ZERO;

/**
 * This class represents a space on the ocean without ship in it. It is consider
 * a ship type for code's simplicity.
 *
 * @author Federico Cocco
 */
public class EmptySea extends Ship {

    /**
     * Constructs a new <code>EmptySea</code> according to the parameters.
     */
    public EmptySea() {
        super(ONE);
    }

    /**
     * This method return the type of ship of the istance.
     *
     * @return a <code>String</code> indicating the ship type.
     */
    @Override
    public String getShipType() {
        return "empty sea";
    }

    /**
     * This method checks whether the ship has been sunk or not, thus stil being
     * in game.
     *
     * @return <code>false</code> as there was no ship to sink.
     */
    @Override
    public boolean isSunk() {
        return false;
    }

    /**
     * This method indicate if <code>this</code> is a real ship.
     *
     * @return <code>false</code> as it is not a real ship.
     */
    @Override
    public boolean isRealShip() {
        return false;
    }

    /**
     * This method checks whether a ship has been hit or not.
     *
     * @param row
     * @param column
     *
     * @return <code>false</code> as there is nothing to be shot.
     * @see <code>Ship.shootAt</code>
     */
    @Override
    public boolean shootAt(int row, int column) {
        return super.shootAt(row, column);
    }

    /**
     * This method prints the current status of the ship. 
     * . as part of the ship never hit, if hit it will indicate - as a hit on 
     * an empty square of the game.
     *
     * @return string representation of the status of the ship, "-" hit, "."
     * never hit.
     */
    @Override
    public String toString() {
        return (this.getShipStatus()[ZERO]) ? "-" : ".";
    }
}
