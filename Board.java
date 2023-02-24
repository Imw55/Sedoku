import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;


class Board {

	private int[][] board;
	private int dimension;

	public Board(String in) throws FileNotFoundException {
		dimension = calculateDimensions(in);
		board = new int[dimension][dimension];
		this.populate(in);
	}

	private static int calculateDimensions(String in) throws FileNotFoundException {
		File file = new File(in);
		Scanner scan = new Scanner(file);
		scan.nextLine();
		boolean flag = false;
		int dimension = 0;
		while(!flag) {
			if(scan.hasNext()) {
				scan.nextLine();
			}
			else {
				flag = true;
			}
			dimension++;
		}
		return dimension;
	}

	private void populate(String in) throws FileNotFoundException {
		File file = new File(in);
		Scanner scan = new Scanner(file);
		String str = scan.nextLine();
		boolean flag = false;
		int counter = 0;
		while(!flag) {
			str.replace(" ","");
			String[] temp = str.split(",");
			int[] out = new int[dimension];
			for(int i = 0; i<dimension; i++) {
				out[i] = Integer.parseInt(temp[i]);
			}
			board[counter] = out;
			if(scan.hasNext()) {
				str = scan.nextLine();
			}
			else {
				flag = true;
			}
			counter++;
		}
	}

	//Divide into Quadrants
	public String toString() {
		String rtrn = "";
		String a = System.lineSeparator();
		for(int i = 0; i<this.dimension; i++) {
			String temp = "";
			for(int l = 0; l<this.dimension; l++) {
				temp = temp + Integer.toString(this.board[i][l]) + " ";
			}
			temp = temp + a;
			rtrn = rtrn + temp;
		}
		return rtrn;
	}

	public void setValue(int row, int column, int value) {
		this.board[row][column] = value;
	}

	public int getValue(int row, int column) {
		return this.board[row][column];
	}

	public int getDimensions() {
		return this.dimension;
	}

	public static void main(String[] args) throws FileNotFoundException{
		Board a = new Board(args[0]);
		System.out.println(a);
	}

}