
/** this is the main class,he is running the whole game from beginning to end
 * initialize, running and announce who won the game 
 * 
 */

import java.util.Random;

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
	
	
	
	public void initialize() {

		this.board = new Board();
		this.random = new Random();
		this.board.setupBoard();

	}

	
	
	public void FirstMove() {

		System.out.println("who starts ? 1  - COMPUTER , 2 - USER");
		int choice = board.getScanner().nextInt();

		if(choice == 1 ) {
			Cell cell = new Cell (random.nextInt(3),random.nextInt(3));
			board.move(cell,CellState.COMPUTER);
			board.displayBoard();
		}

	}
	
	
	
	private void playGame() {

		while(board.isRunning()) {
			System.out.println("user move: ");
			Cell userCell = new Cell(board.getScanner().nextInt()  ,board.getScanner().nextInt());
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




// this function will check who won the game w
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
