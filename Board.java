import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;


class Board {

	int[][] board;
	int dimension;

	public Board(String in) throws FileNotFoundException {
		dimension = getDimensions(in);
		board = new int[dimension][dimension];
		this.populate(in);
	}

	private static int getDimensions(String in) throws FileNotFoundException {
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
		while(scan.hasNext()) {
			str.replace(" ","");
			String[] temp = str.split(",");
			int[] input = new int[temp.length];
			for(int i = 0; i<temp.length; i++) {
				input[i] = Integer.parseInt(temp[i]);
			}
			board[counter] = input;
			if(scan.hasNext()) {
				counter++;
			}
		}
	}

	public String toString() {
		String rtrn = "";
		String a = System.lineSeparator();
		for(int i = 0; i<this.dimension; i++) {
			String temp = "";
			for(int l = 0; l<this.dimension; l++) {
				temp = temp + Integer.toString(this.board[i][l]) + " " + a;
			}
			rtrn = rtrn + temp;
		}
		return rtrn;
	}

	public static void main(String[] args) throws FileNotFoundException{
		Board a = new Board(args[0]);
		System.out.println(a);
	}

}