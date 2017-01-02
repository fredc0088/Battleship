package battleship;

import static battleship.Constants.TWO;

/**
 * This class represents a ship of class Destroyer.
 *
 *
 * @author Federico Cocco
 */
public class Destroyer extends Ship {

    /**
     * Constructs a new <code>Destroyer</code> according to the parameters.
     */
    public Destroyer() {
        super(TWO);
    }

    /**
     * This method return the type of ship of the istance.
     *
     * @return a <code>String</code> indicating the ship type.
     */
    @Override
    public String getShipType() {
        return "destroyer";
    }
}
