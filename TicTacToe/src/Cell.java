

/** cells coordoinates and minimax values.
 * the minimax values will help the computer to take the best move 
 * 
 * @author neilmichaeli
 *
 */


public class Cell {
	
	
	private int x;
	private int y;
	private int minimaxValue; 
	
	Cell(int x, int y ){
		this.x = x;
		this.y = y;
		
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getMinimaxValue() {
		return minimaxValue;
	}

	public void setMinimaxValue(int minimaxValue) {
		this.minimaxValue = minimaxValue;
	}
	public String toString() {
		
		return "(" + x + "," + y + ")";
	}
	
	

}
