package battleship;

/**
 *
 * @author Federico Cocco
 */
public abstract class Ship {
    
    private int lenght;

    public Ship(int lenght) {
        this.lenght = lenght;
    }
    
    abstract String getShipType();
}
