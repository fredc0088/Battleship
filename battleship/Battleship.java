package battleship;

import static battleship.Constants.FOUR;

/**
 * This class represents a ship of class battleship.
 *
 *
 * @author Federico Cocco
 */
public class Battleship extends Ship {

    /**
     * Constructs a new <code>Battleship</code> according to the parameters.
     */
    public Battleship() {
        super(FOUR);
    }

    /**
     * This method return the type of ship of the istance.
     *
     * @return a <code>String</code> indicating the ship type.
     */
    @Override
    public String getShipType() {
        return "battleship";
    }
}
