import java.io.FileNotFoundException;

class Solver {

	private static boolean solve(Board unsolved, int row, int column) {
		//Precondition: checks if algorithm has reached end of board, ends recursion
		if(row == unsolved.getDimensions()-1 && column == unsolved.getDimensions()) {
			return true;
		}
		//Precondition: checks if last column has been reached, moves to next row
		if(column == unsolved.getDimensions()) {
			row++;
			column = 0;
		}
		//Precondition: checks if coordinate is not 0, moves to next entry
		if(unsolved.getValue(row,column) != 0) {
			return solve(unsolved,row, column+1);
		}
		//Checks all values 1-9
		for(int i = 1; i<10; i++) {
			//Checks if i is a valid entry at the coordinate, sets value if true
			if(Solver.checkValid(unsolved,row,column,i)) {
				unsolved.setValue(row,column,i);
				//Moves to next entry, onces an entry returns true, ends algorithim
				if(Solver.solve(unsolved,row,column+1)) {
					return true;
				}
			}
			//If no valid entries reverts to previous state
			unsolved.setValue(row,column,0);
		}
		//If cannot find valid state, returns false
		return false;
	}


	private static boolean checkValid(Board in, int row, int column, int num) {
		//Checks for duplicates in spesified row
		for(int i = 0; i<in.getDimensions(); i++) {
			if(in.getValue(row,i) == num) {
				return false;
			}
		}
		//Checks for duplicates in spesified column
		for(int l = 0; l<in.getDimensions(); l++) {
			if(in.getValue(l, column) == num) {
				return false;
			}
		}
		//Determines the start value of the 3x3 square to be checked
		int sqaureRow = row-row%3;
		int squareColumn = column-column%3;
		//Checks each entry of the 3x3 square for duplicate numbers
		for(int a = sqaureRow; a<(sqaureRow+3);a++) {
			for(int b = squareColumn; b<(squareColumn+3); b++) {
				if(in.getValue(a,b) == num) {
					return false;
				}
			}
		}
		//If no duplicates found
		return true;
	}

	private static boolean checkSolvable(Board in) {
		int numSquare = in.getDimensions()/3;
		//Checks values 1-9
		for(int i = 1; i<10; i++) {
			//Checks each row for i
			for(int l = 0; l<in.getDimensions(); l++) {
				//Counts number of instances of i
				int rowCounter = 0;
				for(int k = 0; k<in.getDimensions(); k++) {
					if(in.getValue(l,k) == i) {
						rowCounter++;
					}
					//If more than one instance of i, returns false
					if(rowCounter > 1) {
						return false;
					}
				}
			}
			//Checks each column for i
			for(int c = 0; c<in.getDimensions(); c++) {
				//Counts number of instances of i
				int columnCounter = 0;
				for(int h = 0; h<in.getDimensions(); h++) {
					if(in.getValue(h,c) == i) {
						columnCounter++;
					}
					if(columnCounter > 1) {
						return false;
					}
				}
			}
			//Starting point for the rows of a square
			int rowSquare = 0;
			//Checks when loop hits last row coordinate
			while(rowSquare < in.getDimensions()) {
				//Starting point for the column of a square
				int columnSquare = 0;
				//Checks when loop hits last column coordinate
				while(columnSquare < in.getDimensions()) {
					//Counts instances of i
					int squareCounter = 0;
					//Checks if each coordinate in a square is equal to i
					for(int a = rowSquare; a<rowSquare+3; a++) {
						for(int b = columnSquare; b<columnSquare+3; b++) {
							if(in.getValue(a,b) == i) {
								squareCounter++;
							}
							//If multiple instances of i, returns false
							if(squareCounter > 1) {
								return false;
							}
						}
					}
					//Moves column to next square
					columnSquare+=3;
				}
				//Moves row to next square
				rowSquare+=3;
			}
		}
		return true;
	}


	public static void main(String[] args) throws FileNotFoundException{
		Board a = new Board(args[0]);
		if(!checkSolvable(a)) {
			System.out.println("Error: Invalid Sudoku");
			return;
		}
		System.out.println("==========Input==========");
		System.out.println(a);
		System.out.println("==========Solved=========");
		if(solve(a,0,0)) {
			System.out.println(a);
		}
		else {
			System.out.println("Error: Unsolvable");
		}
	}
}