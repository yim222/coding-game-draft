package rooks.movements;
//https://www.codingame.com/ide/puzzle/rooks-movements

import java.util.ArrayList;
/**
 * U HERE - 
 * 
 * U did getting the pieces to the board. It's working well on the online editor too. tested. 
 * - Now you need to (all do with streams!) 
 * A task -
 *  1- From the rook - to get the blockers on each route. First - search for pieces that on the rook road. By direction. Do it by stream. filter those
 *  that have no row OR column equal to the Rook row/columns. 
 *  2 - To associate each of them to the right direction (l , b , t ,r ). On each association, if exist already a tool, check what's closer to the rook. (Possible also to start from the rook, and search I don't sure it's more efficient). 
 * 	3 - Then you should have a blockers to each side (u can do it with map, and also possible that it will be empty). 
 * B task - 
 * Then you need to provide this blocker piece to the corresponding route. AND on the route to add this to the calculation. 
 * From there - conitnue. 
 * 
 * 
 * add blicker calculation to the route, 
 * 
 * TODO 2:
 * 
 * 		**It's according to the schema. Board contains rook, and have a static array of the moves. Rook have position and get all data,
 * 			and calculating the things it needs, including the moves. 
 * 			By running the "printMoves() it's should provide the solution.
 * 
 * 
 * 
 * - create object Board, contains pieces and the rook. Also, Object position, Piece, Move (the string)-V
 * - Replace the using to use position. -V
 * - Create arrayList from the pieces. as static on the board. - V Done 
 * - On the rook create: 
 * 		fields: moves[]
 * 		methods: calculate blockers - for the routes. calculateMoves - with the routes. Print Moves. 
 * 
 * - At the end clean and write comments. 
 * 		
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
		String[] inputs = {"d5"};
		
		int [] colors = {1,0,1};
		String [] piecesProvided = {"c1", "e8", "d3"};
		
		String rookPosition = inputs[0];// here is the string. 
		
		char column = rookPosition.charAt(0);
		int row = Integer.parseInt(rookPosition.charAt(1)+"");
		int cv = 'b' - 96;
		System.out.println(cv);
		for (int i = 0; i < colors.length ; i++ ) {
			int colour = colors[i];
            String onePiece = piecesProvided[i];
            
            Position piecePosition = new Position(onePiece);
            Piece piece = new Piece(piecePosition, colour);
            
            Board.addPiece(piece);
            
		}
		System.err.println("Board pieces test - " + Board.pieces);
		Position position1 = new Position(column, row);
		Route r = new Route(Direction.LEFT, position1/*Piece blocker*/);
		r.generatePaths();
		System.err.println("route LEFT = " + r.describe + " to string = " + r);

		r = new Route(Direction.RIGHT, position1);
		r.generatePaths();
		System.err.println("route RIGHT = " + r.describe + " to string = " + r);
		
		
		r = new Route(Direction.DOWN, position1);
		r.generatePaths();
		System.err.println("route DOWN = " + r.describe + " to string = " + r);
		
		r = new Route(Direction.UP, position1);
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

class Position{
	private char column;// a-96 = 1;
	private int row;
	
	public Position() {
		super();
	}
	public Position(char column, int row) {
		super();
		this.column = column;
		this.row = row;
	}
	
	public Position(String positionString) {		
		column = positionString.charAt(0);
		row = Integer.parseInt(positionString.charAt(1)+"");
		
	}
	public char getColumn() {
		return column;
	}
	public void setColumn(char column) {
		this.column = column;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	@Override
	public String toString() {
		return "Position [column=" + column + ", row=" + row + "]";
	}
	
}

class Rook {
	private Position position;
	private Map<Direction, Route> availableRoutes = new TreeMap<>();

	public Rook(Position position) {
		this.position = position;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Map<Direction, Route> getAvailableRoutes() {
		return availableRoutes;
	}

	public void setAvailableRoutes(Map<Direction, Route> availableRoutes) {
		this.availableRoutes = availableRoutes;
	}

	@Override
	public String toString() {
		return "Rook [position=" + position + ", availableRoutes=" + availableRoutes + "]";
	}
	
	
	
}

class Route {//to use position

	Position position;
	Direction direction;
	String describe;
	Blocker blockerType;
	Piece blockerPiece;
	char connectionSign = '-';
	int from, to;

	public Route(Direction direction, Position position) {
		super();
		this.position = position;
		this.direction = direction;
		
		
	}

	@Override
	public String toString() {
		return "Route [position=" + position + ", direction=" + direction + ", describe=" + describe + ", from=" + from
				+ ", to=" + to + "]";
	}

	public void generatePaths() {//to use position

		switch (direction) {
		case LEFT:// should take the rook line on first col to the before the rookCol. For example
					// - if the rook on d5 - a-c5
			if (position.getColumn() - 96 == 1) {
				from = 0;
				to = 0;
				describe = "0";
			} else {

				from = 1;
				to = position.getColumn() - 96 - 1;
				describe = (char) (from + 96) + "-" + (char) (to + 96) + "" + position.getRow();

			}
			break;
		case RIGHT:// should take the rook line from the next column of the rook to the  the last. For example
					// - if the rook on d5 - e-h5
			if (position.getColumn() - 96 == 8) {//if the rook at the last column
				from = 0;
				to = 0;
				describe = "0";
			} else {

				from = position.getColumn() - 96 + 1;
				to = 8;
				describe = (char) (from + 96) + "-" + (char) (to + 96) + "" + position.getRow();

			}
			break;
			
		case DOWN:// should take the rook columns from the first line to the before the position.getRow(). For example
			// - if the rook on d5 - d1-3
			if (position.getRow()  ==1) {
				from = 0;
				to = 0;
				describe = "0";
			} else {

				from = 1;
				to = position.getRow()-1;
				describe = position.getColumn() + "" + from  + "-"   + to;

			}
			break;
			
		case UP:// should take the rook columns from the next line of the rook to the  the lase line. For example
			// - if the rook on d5 - d6-8
			if (position.getRow() ==8) {//last line
				from = 0;
				to = 0;
				describe = "0";
			} else {

				from = position.getRow() + 1;
				to = 8;
				describe = position.getColumn() + "" + from  + "-"   + to;

			}
			break;


		}

	}

	

}

class Board{
	public Board (Rook myRook) {
		
	}
	public static ArrayList<Piece> pieces = new ArrayList<>();
	
	public static void addPiece(Piece piece) {
		pieces.add(piece);
	}
	
	
	
}

class Piece{
	
	private Position position;
	private char toolSign = '*';// * = unknown
	private int color;//color is either 0 (WHITE) or 1 (BLACK)
	

	
	
	
	public Piece() {
		
	}
	
	
	
	
	public Piece(Position position, int color) {
		super();
		this.position = position;
		this.color = color;
	}




	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public char getToolSign() {
		return toolSign;
	}
	public void setToolSign(char toolSign) {
		this.toolSign = toolSign;
	}
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	@Override
	public String toString() {
		return "Piece [position=" + position + ", toolSign=" + toolSign + ", color=" + color + "]";
	}
	
	
	
	
}

class Move{
	
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