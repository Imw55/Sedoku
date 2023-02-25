import java.io.FileNotFoundException;

class Solver {

	private static boolean solve(Board unsolved, int row, int column) {
		if(row == unsolved.getDimensions()-1 && column == unsolved.getDimensions()) {
			return true;
		}
		if(column == unsolved.getDimensions()) {
			row++;
			column = 0;
		}
		if(unsolved.getValue(row,column) != 0) {
			return solve(unsolved,row, column+1);
		}
		for(int i = 1; i<10; i++) {
			if(Solver.checkValid(unsolved,row,column,i)) {
				unsolved.setValue(row,column,i);
				if(Solver.solve(unsolved,row,column+1)) {
					return true;
				}
			}
			unsolved.setValue(row,column,0);
		}
		return false;
	}


	private static boolean checkValid(Board in, int row, int column, int num) {
		int squaresPerRow = in.getDimensions()/3;
		for(int i = 0; i<=in.getDimensions()-1; i++) {
			if(in.getValue(row,i) == num) {
				return false;
			}
		}
		for(int l = 0; l<in.getDimensions()-1; l++) {
			if(in.getValue(l, column) == num) {
				return false;
			}
		}
		int sqaureRow = row-row%3;
		int squareColumn = column-column%3;
		for(int a = sqaureRow; a<(sqaureRow+3);a++) {
			for(int b = squareColumn; b<(squareColumn+3); b++) {
				if(in.getValue(a,b) == num) {
					return false;
				}
			}
		}
		return true;
	}

	private static boolean checkSolvable(Board in) {
		int numSquare = in.getDimensions()/3;
		for(int i = 1; i<10; i++) {
			for(int l = 0; l<in.getDimensions(); l++) {
				int rowCounter = 0;
				for(int k = 0; k<in.getDimensions(); k++) {
					if(in.getValue(l,k) == i) {
						rowCounter++;
					}
					if(rowCounter > 1) {
						return false;
					}
				}
			}
			for(int c = 0; c<in.getDimensions(); c++) {
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
			int rowSquare = 0;
			while(rowSquare < in.getDimensions()) {
				int columnSquare = 0;
				while(columnSquare < in.getDimensions()) {
					int squareCounter = 0;
					for(int a = rowSquare; a<rowSquare+3; a++) {
						for(int b = columnSquare; b<columnSquare+3; b++) {
							if(in.getValue(a,b) == i) {
								squareCounter++;
							}
							if(squareCounter > 1) {
								return false;
							}
						}
					}
					columnSquare+=3;
				}
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