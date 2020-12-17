//CS2210a 2020
//Jessica Yang
//Student Number: 251083596
//ID: jyang762
//Date: October 4, 2020


public class Evaluate {
	final private char COMPUTER = 'o', EMPTY = 'g', HUMAN = 'b'; // Characters for the board pieces 

	private int size = 9973; // default game board size
	private int rows, cols, level, needed, score;
	
	private char[][] gameboard;
	
	/**
	 * Constructor method stores the given variables and creates an empty gameboard
	 * @param boardRows
	 * @param boardColumns
	 * @param tilesNeeded
	 * @param maxLevels
	 */
	public Evaluate (int boardRows, int boardColumns, int tilesNeeded, int maxLevels) {
		rows = boardRows;  // Updates class variables; 
		cols = boardColumns;
		level = maxLevels;
		needed = tilesNeeded;
		gameboard = new char[rows][cols];
		// Sets the gameboard to empty 
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				gameboard[i][j] = EMPTY;
			}
		}
	}
	
	/**
	 * Method createDictionary creates a new dictionary
	 * @return
	 */
	public Dictionary createDictionary() {
		return new Dictionary(size);
	}
	
	/**
	 * Method repeatedConfig will check if the current game board is already stored into the dictionary
	 * @param dict
	 * @return returns the Data item if it is contained with dict, and null otherwise 
	 */
	public Data repeatedConfig(Dictionary dict) {
		String key = "";
		// Generates the key of the gameboard
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				key = key + gameboard[i][j]; 
			}
		}
		return new Data(key, score, level);
	}
	
	/**
	 * Method insertConfig will store the current game board, score and level into a Data Item in the dictionary
	 * @param dict
	 * @param score
	 * @param level
	 */
	public void insertConfig(Dictionary dict, int score, int level) {
		String key = "";
		// Generates the key of the gameboard 
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				key = key + gameboard[i][j];
			}
		}
		Data toAdd = new Data(key, score, level); //creates new Data object to store into the dictionary
		try {
			dict.put(toAdd);
		} catch (DuplicatedKeyException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method storePlay stores the symbol into the game board
	 * @param row
	 * @param col
	 * @param symbol
	 */
	public void storePlay(int row, int col, char symbol) {
		gameboard[row][col] = symbol;
	}
	
	/**
	 * Method squareIsEmpty checks if current tile is g
	 * @param row
	 * @param col
	 * @return boolean 
	 */
	public boolean squareIsEmpty(int row, int col){
		return (gameboard[row][col] == EMPTY);
	}
	
	/**
	 * Method tileOfComputer checks if the current tile is o
	 * @param row
	 * @param col
	 * @return boolean
	 */
	public boolean tileOfComputer(int row, int col) {
		return (gameboard[row][col] == COMPUTER);
	}
	
	/**
	 * Method tileOfHuman checks if the current tile is b
	 * @param row
	 * @param col
	 * @return boolean
	 */
	public boolean tileOfHuman (int row, int col) {
		return (gameboard[row][col] == HUMAN);
	}
	
	/**
	 * Method wins will return true if there are the required number of adjacent tiles of type symbol in the same row, column, or diagonal of gameBoard.
	 * @param symbol
	 * @return returns boolean true if there is a win and false if there isnt a win 
	 */
	public boolean wins(char symbol) {
		for (int i = 0; i < rows; i ++) {
			for (int j = 0; j < cols; j++) {
				if (gameboard[i][j] == symbol) { // checks its surroundings (above , right, and diagonals)
					boolean pos = true; // represents diagonal in a positive slope
					boolean neg = true; // represents diagonal in a negative slope
					// Checks if the gameboard has enough spaces to win vertically (row) from current position
					if (i + needed <= rows ) {
						if (vert(symbol,i,j)) return true; // checks if there is a vertical (up) win using vert method
					}
					else {
						// If there is not enough space to check vertically up, then there is not enough space for a diagonal up win
						pos = false;
						neg = false;
					}
					// Checks if the gameboard has enough spaces to win horizontally (col) from current position
					if (j + needed <= cols) {
						if (hori(symbol,i,j)) return true; // checks if there is a horizontal (right) win using hori method
					}
					else {
						// If there is not enough space to check horizontally to the right, then there is not enough space for diagonal with positive slope 
						pos = false;
					}
					//  Checks diagonals if necessary 
					if (pos && diagPos(symbol,i,j)) return true;
					if (neg && j -needed +1 > 0 && diagNeg(symbol,i,j))  return true;
					
				}
			}
		}
		return false;
		
	}
	
	/** Method isDraw will check for draw by finding open spaces
	 * @return returns boolean value true if it is a draw and false if it is not a draw
	 */
	public boolean isDraw() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (squareIsEmpty(i,j)) return false; // if there are any open spaces left, then it is not a draw
			}
		}
		return true;
	}
	
	/**
	 * Method evalBoard will return 3 if the computer has won, 0 if the human has won, 2 if there is a draw and 1 if the game is undecided
	 * @return returns an int based on the rules given
	 */
	public int evalBoard() {
		if (wins(COMPUTER)) return 3;
		else if (wins(HUMAN)) return 0;
		else if (isDraw()) return 2;
		return 1;
	}
	
	
	/** Method vert checks vertical (row) in the up direction for win condition
	 * @param symbol 
	 * @param i : the row position of the given check location
	 * @param j : the col position of the given check location
	 * @return boolean value true if there is a win and false if no win in the vertical up direction
	 */
	private boolean vert(char symbol, int i, int j) {
		for (int k = 0; k < needed; k++) { // goes through the cells above one by one (of the amount needed to win)
			if (gameboard[i+k][j] != symbol) { // if any cell is not the given symbol, it is not a vertical win
				return false;
			}
		}
		return true;
	}
	
	/** Method hori checks horizontal (col) in the right direction for win condition
	 * @param symbol
	 * @param i : the row position of the given check location
	 * @param j : the col position of the given check location
	 * @return boolean value true if there is a win and false if no win in the horizontal right direction
	 */
	private boolean hori(char symbol, int i, int j) {
		for (int k = 0; k < needed; k++) { //goes through the cells to the right one by one (of the amount needed to win)
			if (gameboard[i][j+k] != symbol) { // if any cell is not the given symbol, it is not a horizontal win
				return false;
			}
		}
		return true;
	}
	
	/** Method diagPos checks the diagonal with positive slope
	 * @param symbol
	 * @param i : the row position of the given check location
	 * @param j : the col position of the given check location
	 * @return boolean value true if there is a win and false if no win in the diagonal right direction
	 */
	private boolean diagPos (char symbol, int i, int j) { 
		for (int k = 0; k < needed; k++) { // goes through the cells in the diagonal right direction one by one (of the amount needed to win)
			if (gameboard[i+k][j+k] != symbol) { // if any cell is not the given symbol, it is not a horizontal win in the positive direction
				return false;
			}
		}
		return true;
	}
	
	/** Method diagNeg checks the diagonal with negative slope
	 * @param symbol
	 * @param i : the row position of the given check location
	 * @param j : the col position of the given check location
	 * @return boolean value true if there is a win and false if no win in the horizontal left direction
	 */
	private boolean diagNeg (char symbol, int i, int j) {
		for (int k = 0; k < needed; k++) {// goes through the cells in the diagonal left direction one by one (of the amount needed to win)
			if (gameboard[i+k][j-k] != symbol) {// if any cell is not the given symbol, it is not a horizontal win in the negative direction
				return false;
			}
		}
		return true;
	}
}
