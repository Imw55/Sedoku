import java.io.FileNotFoundException;

class Solver {

/*	public static Board solve(Board unsolved) {

	}
*/

	public static boolean checkValid(Board in) {
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
		}
		return true;
	}

	public static void main(String[] args) throws FileNotFoundException{
		Board a = new Board(args[0]);
		System.out.println(Solver.checkValid(a));
	}
}