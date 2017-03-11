package battleship;

/**
 * This interface can be implemented for items which can be targets or contain
 * targets.
 *
 * @author Federico Cocco
 */
public interface Shootable {

    public boolean shootAt(int row, int column);
}
