# Sedoku
-Sedoku Project

Author: Ian Worgan

This project contains 5 files, this file(README.txt), Board.java, Board.class, Solver.java, and Solver.class.
This is a console and file based Sudoku solver, which takes a text file containing an unsolved sudoku, and uses a
backtracking algorithm to determine a solution. Instructions for running this program can be found below:

The text file must be formated as such (A square of characters with dimensions divisble by 3)

11,21,31 ... 1n,2n,3n
41,51,61 ... 4n,5n,6n
71,81,91 ... 7n,8n,9n
(Repeated vertically n times)

The command for running the code is as follows:
java Solver <filepath>

Below are Descriptions for methods contained in the Board class:
  
  private calculateDimensions(String in): Takes a filepath in the form of a String, and uses the File and Scanner classes
    to determine the number of rows in the text file, storing it as the int instance variable dimension.
    
  private Populate(String in): Takes a filepath in the form of a String, and  uses the File and Scanner classes to create an String array of each row,
    converts this array to int's and inserts into the int[][] instance variable board.
   
   public Board(String in): Takes a file path in the form of a string, passes filepath into calculateDimensions to get dimensions, initialzes
    board[][] as a dimensionsxdimensions array, calls populate using the filepath.
    
   public setValue(int row, int column, int value): Changes the value of Board at (row,column) to value.
   
   public getValue(int row, int column): Returns the value of Board at (row,column).
   
   public getDimensions(): returns dimensions instance variable.
   
Below are the descriptions for the methods contained in the Solver Class:

  private static checkSolvable(Board in): Takes a board and checks for duplicate numbers (excluding 0) in each row, column, and 3x3 square.
    returns false if any duplicates are found, false otherwise.
    
  private static checkValid(Board in, int row, int column, int value): Checks at position (row,column) on the Board in, whether inserting value would cause any
    duplicates in either a row, column, or 3x3 square.
    
  private static solve(Board unsolved, int row,  int column): Checks if row and column are at the end indexes of Board, is so returns true. Checks if column is at the 
    nth column, if so incriments the row by 1. Checks if value at (row,column) is non-zero, if so skips and recurs at next column. Calls checkValid() for each number 1-9,
    once valid number is found, inserts at (row, column). Recurs at next column, if solve() recursion is true, returns true. if no inputs are valid, backtracks to previous
    version. If no version is found with all indexes filled, returns false. (Utilizes backtracking algorithm).
    
  main(String[] args): Creates Board using String pulled from args[0]. Checks that template does not break sudoku's rules from the start (calls checkSolvable),
  if call returns false, prints error message and breaks. Prints out unsolved template. If solve() returns true, prints solution, else print unsolvable error.
    
   
   
