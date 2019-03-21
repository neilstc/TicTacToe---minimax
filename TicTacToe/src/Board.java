
/** this class represent our playground, the class checks if the game is still running 
 * manages user input, initialize, shows the board manages cells and most importantly 
 * implement the minimax algorithm 
 * 
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class Board {


	private final int boardSize = 3;
	private List<Cell> emptyCells;
	private List<Cell> rootValues;
	private Scanner scanner; 
	private CellState [][] board;


	Board(){
		initializeBoard();
	}


	public void initializeBoard(){

		this.rootValues = new ArrayList<>();
		this.scanner = new Scanner (System.in);
		board = new CellState [boardSize][boardSize];	

	}


	public Boolean isRunning() {

		if(isWinning(CellState.COMPUTER)) { 
			return false ;}
		if(isWinning(CellState.USER)) { 
			return false ;}
		if(getEmptyCells().isEmpty()) { 
			return false; } 
		return true;

	}

	public Boolean isWinning(CellState player) {

		if(board[0][0] == player && board[1][1] == player && board[2][2] == player) {
			return true;
		}
		else if(board[0][2] == player && board[1][1] == player && board[2][0] == player) {
			return true;
		}

		for (int i = 0; i < boardSize; i++) {

			// rows

			if(board[0][i] == player && board[1][i]== player && board[2][i] == player) {
				return true;
			}
			//columns			
			if(board[i][0] == player && board[i][1]== player && board[i][2] == player) {
				return true;
			}

		}

		return false;

	}



	public void makeUserInput() {
		System.out.println("USER'S TURN: ");
		int x = scanner.nextInt();
		int y = scanner.nextInt();
		Cell cell = new Cell(x,y);
		move(cell,CellState.USER);

	}


	public void setupBoard() {

		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {

				board[i][j] = CellState.EMPTY;
			}	

		}
	}

	
	private List <Cell> getEmptyCells(){
		emptyCells = new ArrayList<>();

		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				if(board[i][j] == CellState.EMPTY) {
					emptyCells.add(new Cell(i,j));

				}

			}

		}

		return emptyCells;

	}
	public void move(Cell cell, CellState player) {
		this.board[cell.getX()][cell.getY()] = player;
	}
	
	
	
	public int Min(List <Integer> list ) {


		int min = Integer.MAX_VALUE;
		int index = Integer.MIN_VALUE;


		for (int i = 0; i < list.size(); i++) {

			if(list.get(i) < min ) {
				min = list.get(i);
				index = i; 
			}

		}
		return list.get(index) ;	
	}


	public int Max(List <Integer> list ) {


		int max = Integer.MIN_VALUE;
		int index = Integer.MIN_VALUE;


		for (int i = 0; i < list.size(); i++) {

			if(list.get(i) > max ) {
				max = list.get(i);
				index = i; 
			}

		}
		return list.get(index) ;	
	}


	public Cell getBestMove() {

		int max = Integer.MIN_VALUE;
		int best = Integer.MIN_VALUE;

		for (int i = 0; i < rootValues.size(); i++) {
			if(max < rootValues.get(i).getMinimaxValue()) {
				max = rootValues.get(i).getMinimaxValue();
				best = i;
			}

		}
		return rootValues.get(best);

	}

	

	public void callMinMax(int depth, CellState player) {

		rootValues.clear();
		minimax(depth, player); 
	}

	public int minimax(int depth, CellState player) {

		if(isWinning(CellState.COMPUTER)) { 
			return  +1;
			
		}
		if(isWinning(CellState.USER)) {
			return -1 ;
	
		}

		List <Cell> availableCells = getEmptyCells();

		if(availableCells.isEmpty()) { 
			return 0 ;
			}

		List <Integer> scores = new ArrayList<>();
		for (int i = 0; i < availableCells.size(); i++) {
			Cell point =  availableCells.get(i);

			if(player == CellState.COMPUTER) {
				
				move(point, CellState.COMPUTER);
				int currentScore = minimax(depth+1,CellState.USER);
				scores.add(currentScore);

				if(depth == 0) {
					point.setMinimaxValue(currentScore);
					rootValues.add(point);
				}
			}
			else if (player == CellState.USER){
				move(point,CellState.USER);
				scores.add(minimax(depth+1,CellState.COMPUTER));
			}
			board[point.getX()][point.getY()] = CellState.EMPTY;
		}
		
		if(player == CellState.COMPUTER) {
			return Max(scores);
		}
		return Min(scores);
		
	}
	
	
// prints the playground 
	public void displayBoard() {
		System.out.println();

		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize;j++) {
				System.out.print(board[i][j]);

			}
			System.out.println();

		}

	}


	/** getters and setters 
	 * 
	 * @return
	 */

	public List<Cell> getRootValues() {
		return rootValues;
	}



	public void setRootValues(List<Cell> rootValues) {
		this.rootValues = rootValues;
	}



	public Scanner getScanner() {
		return scanner;
	}



	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}



	public void setEmptyCells(List<Cell> emptyCells) {
		this.emptyCells = emptyCells;
	}

	
	

}
