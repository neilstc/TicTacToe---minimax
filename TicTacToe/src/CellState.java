
/** this class represent who took each of the cells in the board 
 * every state has it's own symbol.
 * @author neilmichaeli
 *
 */

public enum CellState {
	
	
	COMPUTER ("X"), USER("O"), EMPTY("-");
	
	private final String text;
	
	private CellState(String text) {
		
		this.text = text;
	
	}
	
	public String toString() {
		return this.text;
	}
	
    

}

