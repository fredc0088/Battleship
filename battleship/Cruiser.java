package battleship;

import static battleship.Constants.THREE;

/**
 * This class represents a ship of class Cruiser.
 *
 *
 * @author Federico Cocco
 */
public class Cruiser extends Ship {

    /**
     * Constructs a new <code>Cruiser</code> according to the parameters.
     */
    public Cruiser() {
        super(THREE);
    }

    /**
     * This method return the type of ship of the istance.
     *
     * @return a <code>String</code> indicating the ship type.
     */
    @Override
    public String getShipType() {
        return "cruiser";
    }
}
