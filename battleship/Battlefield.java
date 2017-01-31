package battleship;

import static battleship.Constants.ZERO;

/**
 * This class is a graphical representation in <code>String</code> of
 * the board for a battleship game.
 *
 * @author Federico Cocco
 */
public class Battlefield {
	private String[][] battlefield;

	public Battlefield(int sizeField){
		this.battlefield = new String [sizeField][sizeField];
        for (int i = ZERO; i < sizeField; i++) {
            for (int y = ZERO; y < sizeField; y++) {
                this.battlefield[i][y] = ".";
            }
        }
	}

	/**
     * This method update a position in the graphical representation of the ship
     * with a given new <code>String</code>.
     *
     * @param row
     * @param column
     * @param update
     */
    public void updateSquare(int row, int column, String update) {
        this.battlefield[row][column] = update;
    }

    /**
     * This methods prints the content of a square on the battlefield.
     * 
     * @param row    
     * @param column 
     */
    public void print(int row, int column) {
    	System.out.print(this.battlefield[row][column]);
    }

    /**
     * This method checks whether the location was an hit and if so, if 
     * already happened.
     * 
     * @param  row    
     * @param  column 
     * @return <code>true</code> if it is neither already hit "S" or sunk "X".       
     */
    public boolean isAlreadyHit(int row, int column) {
        if(this.battlefield[row][column].equals("S") || this.battlefield[row][column].equals("X")) {
            return false;
        } 
        return true;
    }
}