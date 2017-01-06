package battleship;

import static battleship.Constants.ONE;

/**
 * This class represents a space on the ocean without ship in it.
 *
 *
 * @author Federico Cocco
 */
public class EmptySea extends Ship {

    /* This variable represents a shot that hit water and miss ship. */
    private boolean empty_shot;

    /**
     * Constructs a new <code>EmptySea</code> according to the parameters.
     */
    public EmptySea() {
        super(ONE);
        this.empty_shot = false;
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
     */
    @Override
    public boolean shootAt(int row, int column) {
        return false;
    }

    public void hitEmptySea() {
        if (!this.empty_shot) {
            this.empty_shot = true;
        }
    }
    
    public boolean isMissedShot() {
        return this.empty_shot;
    }
}
