package battleship;

/**
 * This interface can be implemented for items which are shootable against.
 * 
 * @author Federico Cocco
 */
public interface Damageable {
    
    public boolean shootAt(int row, int column);
}
