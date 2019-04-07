
/** this is the main class,he is running the whole game from beginning to end
 * initialize, running and announce who won the game 
 * 
 */

import java.util.Random;
import java.lang.Exception;

public class Game {

	private Board board;
	private Random random;

	Game(){

		initialize();
		displayBoard();
		FirstMove();
		playGame();
		checkStatue();
	}


	// Constructor 
	public void initialize() {

		this.board = new Board();
		this.random = new Random();
		this.board.setupBoard();

	}


	// this function only defines the first move 
	public void FirstMove() {

		System.out.println("who starts ? 1  - COMPUTER , 2 - USER");
		int choice = board.getScanner().nextInt();

		if(choice == 1 ) {
			Cell cell = new Cell (random.nextInt(3),random.nextInt(3));
			board.move(cell,CellState.COMPUTER);
			board.displayBoard();
		}

	}


	// the main game function 
	private void playGame() {

		while(board.isRunning()) {


			Cell userCell = chooseCell();
			board.move(userCell,CellState.USER);
			board.displayBoard();

			if(!board.isRunning()) {
				break;
			}

			board.callMinMax(0,CellState.COMPUTER);

			for (Cell cell : board.getRootValues() ) {
				System.out.println("cell values: " + cell + "minimax value: " + cell.getMinimaxValue());
			}
			board.move(board.getBestMove(), CellState.COMPUTER);
			board.displayBoard();

		}
	}


// the general purpose of this function is to check if the input is ok and if
// the cell is empty
	public Cell chooseCell() {

		Cell userCell = new Cell(0,0);
		int x = 0;
		int y = 0;
		try {
		System.out.println("user move: ");
		 x = board.getScanner().nextInt() ;
         y = board.getScanner().nextInt();
        
       

         // catching out of bounds 
		if(x >='0' && x<= '2' || y >='0' && y<= '2') {
			System.out.println("your choise is our of the playground m8");
			x = 0;
			y =0;
			chooseCell();

		}
// checks if the cell is empty 
		else if(!(board.getCellState(x, y)==CellState.EMPTY)) {
			System.out.println("this cell is already taken! m8! choose an empty cell");
			chooseCell();
		}
		 userCell = new Cell(x,y);

		}
		
		// catches  any weird input like char and such 
		catch(Exception e ) {
			System.out.println("invaild input try again!");
			x=0;
			y=0;
			board.getScanner().next();
			chooseCell();

		}

		return userCell;

	}


	// this function will check who won the game
	private void checkStatue(){
		if(board.isWinning(CellState.COMPUTER)){
			System.out.println("the computer won! RAISE OF THE MACHINEEESSSSSS");
		}
		else if(board.isWinning(CellState.USER)){
			System.out.println("U WONNN ! ! ! ! !");
		}
		else {
			System.out.println("yup.... it's a tie... lame...");
		}			
	}
	public void displayBoard() {

		this.board.displayBoard();
	}
}
