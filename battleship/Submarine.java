package battleship;

import static battleship.Constants.ONE;

/**
 * This class represents a Submarine.
 *
 *
 * @author Federico Cocco
 */
public class Submarine extends Ship {

    /**
     * Constructs a new <code>Submarine</code> according to the parameters.
     */
    public Submarine() {
        super(ONE);
    }

    /**
     * This method return the type of ship of the istance.
     *
     * @return a <code>String</code> indicating the ship type.
     */
    @Override
    public String getShipType() {
        return "submarine";
    }
}
