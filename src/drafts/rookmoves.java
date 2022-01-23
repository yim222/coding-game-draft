package drafts;

import java.util.Scanner;

/**
 * 
 * @author Lingar
 *Steps: 
 *- Recognize where is the rook, and what squares available for it - if all empty. 
 *- Recognize what tools are on the Rook routes.
 *- check on each direction (up, down, right, left) 
 *		- if all empties so all the squares are available. 
 *		- If there is there an ally so until the ally exclude, all squares are available 
 *		- If the is there an opponent - all the squares until it includes it are available
 *		- For each condition generate and insert to array the valid value.
 *		- Sort it in lexicographic way ( it's starting from the  left bottom, so it's must start with a row, but it's not must.)
 *
 *  - Print all in proper way. 
 *  	Those are the steps.
 *  
 *  Now the analyze
 *  The rook is white. 
 *  
 *  You can take two arrays with 8 indexes.
 *  or you can do an object too. 
 *  
 *  
 *  You can 	
 *  
 *  Task: 
 *  1 - Creat object Available paths, which have Enum - direction, Enum - Blocker (wall/ ally/ opponent). int starting from (not inculde the 
 *  Rook itself). - V
 *  2- Take the rook position and break it down to columns (char letter), and row. - DONE
 *  	
 *  3- Create method that calculate from those values the the available paths ( l b t r)  - U here
 *  	3.1 - Pass over the tools, and create iterator, that check if there are some in the path of the ROOK. 
 *  4 - Create a generator of moves string which Takes those 4 objects and iterate on each of them for creating proper string. 
 *  	4.1 On each loop - insert those values into Ordered list or set. (Or you can do the iterator right, and the order will be happen by itself).
 *  5- At the end - you just to need to print those values. 
 *  
 *   GO
 */
public class rookmoves {
//https://www.codingame.com/ide/puzzle/rooks-movements
	public static void main(String args[]) {
		System.out.println("ANSWER");

		Scanner in = new Scanner(System.in);
		String rookPosition = in.next();
		
		char column = rookPosition.charAt(0);
		int row = Integer.parseInt(rookPosition.charAt(1)+"");
		System.err.println("column = " + column +  " row = " + row);
		int nbPieces = in.nextInt();
		for (int i = 0; i < nbPieces; i++) {
			int colour = in.nextInt();
			String onePiece = in.next();
		}

		// Write an answer using System.out.println()
		// To debug: System.err.println("Debug messages...");

		System.out.println("ANSWER");
	}
}

enum Direction {
	LEFT, DOWN, UP, RIGHT
}

enum Blocker {
	WALL, ALLY, OPPONENT
}

class AvailableMoves{
	Direction direction;
	Blocker blocker;
	int fromColumn;
	int fromRow;
	
	
}