package battleship;

import static battleship.Constants.FOUR;
/**
 * This class represents a ship of class battleship
 * 
 * 
 * @author Federico Cocco
 */
public class Battleship extends Ship {

    public Battleship() {
        super(FOUR);
    }
    
    
    
    
    @Override
    public String getShipType() {
        return "battleship";
    }
}
