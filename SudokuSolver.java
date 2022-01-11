
public class SudokuSolver {

	static int N = 9; //Size of the Sudoku n*n grid
	
	static boolean solveSudoku(int grid[][], int row, int col)
	{
		//If we reach the end of the Sudoku grid 
		if(row == N-1 && col == N)
		{
			return true;
		}
		
		//If we reach the end of the column go to the next row and return to the first column of the new row
		if (col == N)
		{
			row++;
			col = 0;
		}
		
		//If the current cell does not contain a 0, go to the next cell
		if (grid[row][col] != 0)
		{
			return solveSudoku(grid, row, col+1);
		}
		
		//Insert number 1-9 in the current cell as long as its valid.
		//If it is not valid then backtrack.
		for(int num = 1; num <= 9; num++)
		{
			if(isValid(grid, row, col, num))
			{
				grid[row][col] = num;
				
				if(solveSudoku(grid, row, col+1))
				{
					return true;
				}
			}
			
			//Backtrack
			grid[row][col] = 0;
		}
		
		return false;	
	}
	
	static boolean isValid(int grid[][], int row, int col, int num) 
	{
		//Check if the number already exists in the row
		for(int c = 0; c < N; c++)
		{
			if(grid[row][c] == num)
			{
				return false;
			}
		}
		
		//Check if the number already exists in the column
		for(int r = 0; r < N; r++)
		{
			if(grid[r][col] == num)
			{
				return false;
			}
		}
		
		//Check if the number already exists in the 3*3 matrix it belongs to
		int rowBegin = row - (row % 3);
		int colBegin = col - (col % 3);
		
		for (int i = 0; i < 3; i++)
			for(int j = 0; j < 3; j++)
			{
				if(grid[rowBegin + i][colBegin + j] == num)
				{
					return false;
				}
			}
		
		return true;
	}
	
	//Print solved Sudoku
	static void print(int[][] grid)
    {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
            {
                System.out.print(grid[i][j] + " ");
                if(j%3 == 2 && j != N-1)
                {
                	System.out.print("| ");
                }
            }
            
            System.out.println();
            if(i%3 == 2 && i != N-1) 
            {
            	System.out.println("---------------------");
            }
        }
    }
	
	//Driver
	public static void main(String[] args)
	{
		//0 represents empty cells
		int sudokuToSolve[][] = { { 8, 1, 0, 0, 9, 0, 3, 7, 6 },
                		 		  { 0, 0, 2, 0, 3, 1, 0, 9, 0 },
                		 		  { 7, 0, 0, 0, 0, 0, 0, 0, 0 },
                		 		  { 0, 0, 0, 9, 0, 0, 0, 0, 0 },
                		 		  { 3, 0, 0, 0, 8, 0, 0, 0, 1 },
                		 		  { 0, 0, 0, 0, 0, 2, 0, 0, 0 },
                		 		  { 0, 0, 0, 0, 0, 0, 0, 0, 2 },
                		 		  { 0, 2, 0, 6, 5, 0, 4, 0, 0 },
                		 		  { 9, 5, 3, 0, 2, 0, 0, 8, 7 } };
		
		System.out.println("Sudoku to solve:");
		System.out.println("*Note: Empty cells are denoted by 0*");
		print(sudokuToSolve);
		System.out.println();
		
		if (solveSudoku(sudokuToSolve, 0, 0))
		{
			System.out.println("Solved Sudoku:");
            print(sudokuToSolve);
		}
        
		else
            System.out.println("No Solution exists");
	}
	
}