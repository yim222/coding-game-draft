package rooks.movements;
//https://www.codingame.com/ide/puzzle/rooks-movements

/**
 * U HERE - 
 * You did handling to the direction by the position of the rook. But it's good to empty cases. Now You need to think how to integrate it with the others "Blocker" Concepts. 
 * Notice that you just need to get the "close" blocker to the rook. You don't need to get all. And it's should be calculated on each direction. I don't sure I want to put it in the 
 */
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

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
public class Solution {
//https://www.codingame.com/ide/puzzle/rooks-movements
	public static void main(String args[]) {
		System.out.println("ANSWER");
		int cv = 'b' - 96;
		System.out.println(cv);
		Route r = new Route(Direction.LEFT, 5, 'd');
		r.generatePaths();
		System.err.println("route LEFT = " + r.describe + " to string = " + r);

		r = new Route(Direction.RIGHT, 5, 'd');
		r.generatePaths();
		System.err.println("route RIGHT = " + r.describe + " to string = " + r);
		
		
		r = new Route(Direction.DOWN, 5, 'd');
		r.generatePaths();
		System.err.println("route DOWN = " + r.describe + " to string = " + r);
		
		r = new Route(Direction.UP, 5, 'd');
		r.generatePaths();
		System.err.println("route UP = " + r.describe + " to string = " + r);



//		Scanner in = new Scanner(System.in);
//		String rookPosition = in.next();

//		char column = rookPosition.charAt(0);
//		int row = Integer.parseInt(rookPosition.charAt(1)+"");
//		System.err.println("column = " + column +  " row = " + row);
//		int nbPieces = in.nextInt();
//		for (int i = 0; i < nbPieces; i++) {
//			int colour = in.nextInt();
//			String onePiece = in.next();
//		}

		// Write an answer using System.out.println()
		// To debug: System.err.println("Debug messages...");

		System.out.println("ANSWER");
	}

}

class Rook {
	private char column;// a-96 = 1;
	private int row;
	Map<Direction, Route> availableRoutes = new TreeMap<>();

	public Rook(char column, int row) {
		this.column = column;
		this.row = row;
	}
}

class Route {

	int rookLine;
	char rookColumn;
	Direction direction;
	String describe;
	int from, to;

	public Route(Direction direction, int rookLine, char rookColumn) {
		super();
		this.rookLine = rookLine;
		this.rookColumn = rookColumn;
		this.direction = direction;
	}

	public void generatePaths() {

		switch (direction) {
		case LEFT:// should take the rook line on first col to the before the rookCol. For example
					// - if the rook on d5 - a-c5
			if (rookColumn - 96 == 1) {
				from = 0;
				to = 0;
				describe = "0";
			} else {

				from = 1;
				to = rookColumn - 96 - 1;
				describe = (char) (from + 96) + "-" + (char) (to + 96) + "" + rookLine;

			}
			break;
		case RIGHT:// should take the rook line from the next column of the rook to the  the last. For example
					// - if the rook on d5 - e-h5
			if (rookColumn - 96 == 8) {//if the rook at the last column
				from = 0;
				to = 0;
				describe = "0";
			} else {

				from = rookColumn - 96 + 1;
				to = 8;
				describe = (char) (from + 96) + "-" + (char) (to + 96) + "" + rookLine;

			}
			break;
			
		case DOWN:// should take the rook columns from the first line to the before the rookline. For example
			// - if the rook on d5 - d1-3
			if (rookLine  ==1) {
				from = 0;
				to = 0;
				describe = "0";
			} else {

				from = 1;
				to = rookLine-1;
				describe = rookColumn + "" + from  + "-"   + to;

			}
			break;
			
		case UP:// should take the rook columns from the next line of the rook to the  the lase line. For example
			// - if the rook on d5 - d6-8
			if (rookLine  ==8) {//last line
				from = 0;
				to = 0;
				describe = "0";
			} else {

				from = rookLine + 1;
				to = 8;
				describe = rookColumn + "" + from  + "-"   + to;

			}
			break;


		}

	}

	@Override
	public String toString() {
		return "Route [rookLine=" + rookLine + ", rookColumn=" + rookColumn + ", direction=" + direction + ", describe="
				+ describe + ", from=" + from + ", to=" + to + "]";
	}

}

class VerticalRoute extends Route {

	public VerticalRoute(Direction direction, int rookLine, char rookColumn) {
		super(direction, rookLine, rookColumn);
	}

}

class HorizonalRoute extends Route {

	public HorizonalRoute(Direction direction, int rookLine, char rookColumn) {
		super(direction, rookLine, rookColumn);
	}

}

enum Direction {
	LEFT, DOWN, UP, RIGHT
}

enum Blocker {
	WALL, ALLY, OPPONENT
}

class AvailableMoves {
	Direction direction;
	Blocker blocker;
	int fromColumn;
	int fromRow;
	
}