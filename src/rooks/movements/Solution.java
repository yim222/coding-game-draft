package rooks.movements;
//https://www.codingame.com/ide/puzzle/rooks-movements

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
/**
 * 
 * 
 * A task -
 *  1- From the rook - to get the blockers on each route. First - search for pieces that on the rook road. By direction. Do it by stream. filter those
 *  that have no row OR column equal to the Rook row/columns. - DONE
 *  2 - To associate each of them to the right direction (l , b , t ,r ). On each association, if exist already a tool, check what's closer to the rook. - V DONE (Possible also to start from the rook, and search I don't sure it's more efficient). 
 * 	3 - Then you should have a blockers to each side (u can do it with map, and also possible that it will be empty). - DONE
 * 
 * B task - 
 * Then you need to provide this blocker piece to the corresponding route. AND on the route to add this to the calculation. 
 * the result should be that each route have from to corrects and blocker corrects.
 * From there - conitnue. 
 * 
 * 
 * add blocker calculation to the route, (I suggest to choose sorted list/set for let it handle the lexicography  by itself. Also use the description to build a general
 * move object for any piece). 
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
 * - On the rook create: - IN PROGRESS... -  the route generates proper routes according to the blocker piece provided. NEXT: 
 * 
 * U need to assign it into the rook, all the routes. 
 * Then do the below tasks. 
 * 
 * 		fields: moves[]
 * 		methods: calculate blockers - for the routes. calculateMoves - with the routes. Print Moves. - U here - see the other "u here".  
 * 
 * - At the end clean and write comments. 
 * 		
 */
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;


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
 *  Task: - old... 
 *  1 - Creat object Available paths, which have Enum - direction, Enum - Blocker (wall/ ally/ opponent). int starting from (not inculde the 
 *  Rook itself). - V
 *  2- Take the rook position and break it down to columns (char letter), and row. - DONE
 *  	
 *  3- Create method that calculate from those values the the available paths ( l b t r)  - 
 *  	3.1 - Pass over the tools, and create iterator, that check if there are some in the path of the ROOK. - 
 *  4 - Create a generator of moves string which Takes those 4 objects and iterate on each of them for creating proper string. 
 *  	4.1 On each loop - insert those values into Ordered list or set. (Or you can do the iterator right, and the order will be happen by itself).
 *  5- At the end - you just to need to print those values. 
 *  
 *   GO
 */
public class Solution {
//https://www.codingame.com/ide/puzzle/rooks-movements
	public static void main(String args[]) {
		
		System.err.println("testing move: " + new Move(new Piece (new Position("d5"),'Q', 0) , new Position("d3"), true).getCombination());
		System.out.println("ANSWER");
		String[] inputs = {"d5"};
		
		int [] colors = {1,0,0, 1, 1 , 1, 1};
		String [] piecesProvided = {"c1", "e8", "d2", "b5", "a5" , "g5" , "d7"};
		
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
		Rook rook1 = new Rook(new Position(rookPosition));
		System.err.println("rook1 = " + rook1);
	
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
	
	public String getPositionString() {
		return column+""+row;
	}
	
}

class Rook extends Piece{

	private Map<Direction, Route> availableRoutes = new TreeMap<>();
	private Map<Direction, Piece> blockerPiecesMap = new TreeMap<>();
	private List<Piece> blockerPieces;
	SortedSet<String> movesStrings = new TreeSet<String>();
	Comparator<Move> comprator = new CompareMoves();
	SortedSet<Move> movesList = new TreeSet<Move>(comprator);

	
	public Rook(Position position) {
		super(position, 'R', 0);
		getAndFilterPieces();
		setAvailableRoutes();		
		
		generateMovesList();
		
//For testing
//		Route route = new Route(this, Direction.DOWN,  null);
//		Route route = new Route(this, Direction.DOWN,  blockerPiecesMap.get(Direction.DOWN));//
//		Route route = new Route(this, Direction.RIGHT,  null);
//		Route route = new Route(this, Direction.RIGHT,  blockerPiecesMap.get(Direction.RIGHT));//
		
//		Route route = new Route(this, Direction.UP,  null);
	}
	
	public void getAndFilterPieces() {
		
		blockerPieces = new ArrayList<>(Board.pieces);
		
		blockerPieces = blockerPieces.stream()
		.filter(piece -> piece.getPosition().getColumn() == position.getColumn() || piece.getPosition().getRow() == position.getRow())
		.collect(Collectors.toList());
		
		//preparing the blockers map object
		
		blockerPiecesMap.put(Direction.LEFT, null);
		blockerPiecesMap.put(Direction.DOWN, null);
		blockerPiecesMap.put(Direction.UP, null);
		blockerPiecesMap.put(Direction.RIGHT, null);
		
		//associating each piece with the right direction
		
		blockerPieces.stream().forEach((piece) -> {

			Position piecePosition = piece.getPosition();
			System.out.println((piecePosition.getColumn() < position.getColumn()) + " piece = " + piece);
			if (piecePosition.getColumn() < position.getColumn()) {// left blocker
				if (blockerPiecesMap.get(Direction.LEFT) == null
						|| blockerPiecesMap.get(Direction.LEFT).getPosition().getColumn() < piecePosition.getColumn()) {// if it's closer to the rook
					blockerPiecesMap.put(Direction.LEFT, piece);

				}

			} 
			else if (piecePosition.getRow() < position.getRow()) {// down blocker
				if (blockerPiecesMap.get(Direction.DOWN) == null
						|| blockerPiecesMap.get(Direction.DOWN).getPosition().getRow() < piecePosition.getRow()) {// same
																													// concept

					blockerPiecesMap.put(Direction.DOWN, piece);

				}
			}
			else if (piecePosition.getRow() > position.getRow()) {// up blocker
				if (blockerPiecesMap.get(Direction.UP) == null
						|| blockerPiecesMap.get(Direction.UP).getPosition().getRow() > piecePosition.getRow()) {// same
																													// concept

					blockerPiecesMap.put(Direction.UP, piece);

				}
			}
			
			else if (piecePosition.getColumn() > position.getColumn()) {// right blocker
				if (blockerPiecesMap.get(Direction.RIGHT) == null
						|| blockerPiecesMap.get(Direction.RIGHT).getPosition().getColumn() > piecePosition.getColumn()) {//same concept
					blockerPiecesMap.put(Direction.RIGHT, piece);

				}

			}			
			
		});



		
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

	// Automatically set the available routes.
	public void setAvailableRoutes() {
		for (Map.Entry<Direction, Piece> entry : blockerPiecesMap.entrySet()) {
			availableRoutes.put(entry.getKey(), new Route(this, entry.getKey(), entry.getValue()));
		}
	}
	
	//now we need to do move generator, sorted alphabetically  
	
	
	@Override
	public String toString() {
		return "Rook [position=" + position + ", availableRoutes=" + availableRoutes + ", blockerPiecesMap="
				+ blockerPiecesMap + ", blockerPieces=" + blockerPieces + "]";
	}
	//Use sorted set 	https://www.geeksforgeeks.org/sortedset-java-examples/
	public void generateMovesList() {
		//U need to iterate in the right way according to the direction...
		
		//iterate the routes 
		
		//each route:
			//U need to iterate on the range and generates the "to" position, until no more.consider case 0, which is empty. 
			//assumption - from <=to. if them zero - no route.
		//
		for (Map.Entry<Direction, Route> entry: availableRoutes.entrySet()) {
			if(entry.getValue().to <1) {//this route is empty
				continue;
			}
			
			boolean horizonalRoute = entry.getKey().equals(Direction.LEFT) || entry.getKey().equals(Direction.RIGHT);
			
			for(int i = entry.getValue().from; i <= entry.getValue().to; i++) {
				Move move;
				if(horizonalRoute) {
					//case left\right - we take the col of this rook, and add them the range of the route as line number: 
					Position to = new Position(this.position.getColumn(), i);
					move = new  Move(this, to, entry.getValue().blockerType == Blocker.OPPONENT);//not good. Because although it's blocker, only on the square itself
					//is x not all. 
					
					movesStrings.add(move.getCombination());
					movesList.add(move);

				}
				else {
					//case up\down - we take the range of the route as col char (+96) and then the line of this rook.
					Position to = new Position((char)(i + 96), this.getPosition().getRow()); 
					move = new  Move(this, to, entry.getValue().blockerType == Blocker.OPPONENT);
					
					movesStrings.add(move.getCombination());
					movesList.add(move);


				}
			}
			System.err.println("all available moves: \n"+ movesStrings );
			
			System.out.println("try1:\n");
			System.out.print("[");
			for(Move m : movesList ) {
				System.out.print(m.getCombination() +",");
			}
			System.out.print("]\n");

			
			//U here - U did the adding but it's not sorted well, because It's considering the x. 
			//U need to think on way to sort it in different way. The assumption is that the from position is always the same, 
			//.. so all you need is to sort according to the to position. So maybe save them as "move" and at the printing show the combination..
//			[Rd5-c5, Rd5-d5, Rd5xd2, Rd5xd3, Rd5xd5, Rd5xd6, Rd5xd7, Rd5xf5, Rd5xg5]

		}
		
		
		
		//Inserting it to the set will do the job. 
		
		//then all remains is to print it. 

	}
	
	
	
}



/**
 * Generates by the piece and the other data the right route direction
 * @author lingar
 *
 */
class Route {//to use position
	
	Piece currentPiece;
	Position position;
	Direction direction;
	String describe;
	Blocker blockerType = Blocker.WALL;
	Piece blockerPiece;
	char connectionSign = '-';
	int from, to;

	public Route(Direction direction, Position position) {
		super();
		this.position = position;
		this.direction = direction;
	
		
		
	}
	
	

	public Route(Piece currentPiece, Direction direction, Piece blockerPiece) {
		super();
		this.currentPiece = currentPiece;
		this.direction = direction;
		this.blockerPiece = blockerPiece;
		System.err.println("??/ rook sign = " + currentPiece.getToolSign());

		switch(currentPiece.getToolSign()) {
		case 'R':
			generatePathsForRook();
			break;
		
		}
	}
	
	


	
	@Override
	public String toString() {
		return "Route [ position=" + position + ", direction=" + direction
				+ ", describe=" + describe + ", blockerType=" + blockerType + ", blockerPiece=" + blockerPiece
				+ ", connectionSign=" + connectionSign + ", from=" + from + ", to=" + to + "]";
	}



	public void generatePathsForRookOld() {//to use position

		
		

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
	
	
	public void generatePathsForRook() {//to use position
		System.err.println("Testing - generatePathsForRook");
		//first set the blockerType
		if(blockerPiece != null && blockerPiece.getColor() == currentPiece.getColor()) {
			blockerType = Blocker.ALLY;
		}
		else if(blockerPiece != null && blockerPiece.getColor() != currentPiece.getColor()) {
			blockerType = Blocker.OPPONENT;
			connectionSign = 'x';
			
		}

		switch (direction) {
		case LEFT:// should take the rook line on first col to the before the rookCol. For example
					// - if the rook on d5 - a-c5
			if (currentPiece.getPosition().getColumn() - 96 == 1) {//It's next to the wall
				from = 0;
				to = 0;
				describe = "0";
			} else {
				// U need to handle here the three cases. Currently it's fit to WALL. Make it fit to ally/opponent too. 
				if (blockerType == Blocker.WALL) {
					from = 1;

				}
				
				else if (blockerType == Blocker.ALLY) {
					from = blockerPiece.getPosition().getColumn()-95;//one square to right

				}
				else if (blockerType == Blocker.OPPONENT) {
					from = blockerPiece.getPosition().getColumn()-96;//one square to right

				}
				
				
				
				to = currentPiece.getPosition().getColumn() - 96 - 1;//U need to consider the blocker type and position
				
				describe = (char) (from + 96) + "-" + (char) (to + 96) + "" + currentPiece.getPosition().getRow();

			}
			break;
		case RIGHT:// should take the rook line from the next column of the rook to the  the last. For example
					// - if the rook on d5 - e-h5
			if (currentPiece.getPosition().getColumn() - 96 == 8) {//if the rook at the last column
				from = 0;
				to = 0;
				describe = "0";
			} else {
				//11 U need to handle here the three cases. Currently it's fit to WALL. Make it fit to ally/opponent too. 
				from = currentPiece.getPosition().getColumn() - 96 + 1;

				if (blockerType == Blocker.WALL) {
					to = 8;

				}
				
				else if (blockerType == Blocker.ALLY) {
					to = blockerPiece.getPosition().getColumn() -96 -1;//one square to left

				}
				else if (blockerType == Blocker.OPPONENT) {
					to = blockerPiece.getPosition().getColumn() -96;//the square itself

				}
				describe = (char) (from + 96) + "-" + (char) (to + 96) + "" + currentPiece.getPosition().getRow();

				
			}
			break;
			
		case DOWN:// should take the rook columns from the first line to the before the position.getRow(). For example
			// - if the rook on d5 - d1-4
			if (currentPiece.getPosition().getRow()  ==1) {
				from = 0;
				to = 0;
				describe = "0";
			} else {
				//11 U need to handle here the three cases. Currently it's fit to WALL. Make it fit to ally/opponent too. 
				to = currentPiece.getPosition().getRow()-1;

				if (blockerType == Blocker.WALL) {
					from = 1;


				}
				
				else if (blockerType == Blocker.ALLY) {
					from = blockerPiece.getPosition().getRow() + 1;//one square above

				}
				else if (blockerType == Blocker.OPPONENT) {
					from = blockerPiece.getPosition().getRow();//the square itself

				}
				
				describe = currentPiece.getPosition().getColumn() + "" + from  + "-"   + to;

				

			}
			break;
			
		case UP:// should take the rook columns from the next line of the rook to the  the last line. For example
			// - if the rook on d5 - d6-8
			if (currentPiece.getPosition().getRow() ==8) {//last line
				from = 0;
				to = 0;
				describe = "0";
			} else {
				from = currentPiece.getPosition().getRow() + 1;

				//11 U need to handle here the three cases. Currently it's fit to WALL. Make it fit to ally/opponent too. 
				if (blockerType == Blocker.WALL) {

					to = 8;

				}
				
				else if (blockerType == Blocker.ALLY) {
					to = blockerPiece.getPosition().getRow() - 1;//one square below

				}
				else if (blockerType == Blocker.OPPONENT) {
					to = blockerPiece.getPosition().getRow();//the square itself

				}
				

				describe = currentPiece.getPosition().getColumn() + "" + from  + "-"   + to;

			}
			break;


		}

	}

	

}


class CompareMoves implements Comparator<Move>{
	
	public int compare(Move a, Move b){
		
		
		return a.getToPosition().getPositionString().compareTo(b.getToPosition().getPositionString());
		
//		return 0;
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
	
	protected Position position;
	protected char toolSign = '*';// * = unknown
	protected int color;//color is either 0 (WHITE) or 1 (BLACK)
	

	
	
	
	public Piece() {
		
	}
	
	
	
	
	public Piece(Position position, int color) {
		super();
		this.position = position;
		this.color = color;
	}



	



	public Piece(Position position, char toolSign, int color) {
		super();
		this.position = position;
		this.toolSign = toolSign;
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
/**
 * 
 * @author lingar
 *
 *	piece = Rd5 
 *	to = d3
 *	isCapture = true; 
 *
 *result: 
 *Rd5xd3
 *
 *piece = Rd5 
 *	to = a3
 *	isCapture = false; 
 *
 *result: 
 *Rd5-a3
 */
class Move{
	//piece , position to, 
		private Piece piece;
		private Position toPosition;
		private boolean isCapture;
		private String combination;
		public Move(Piece piece, Position toPosition, boolean isCapture) {
			super();
			this.piece = piece;
			this.toPosition = toPosition;
			this.isCapture = isCapture;
			
			combination = piece.getToolSign() + ""+ piece.getPosition().getPositionString() + (isCapture? 'x' : '-') + toPosition.getPositionString();
		}
		@Override
		public String toString() {
			return "Move [piece=" + piece + ", toPosition=" + toPosition + ", isCapture=" + isCapture + ", combination="
					+ combination + "]";
		}
		public Piece getPiece() {
			return piece;
		}
		public void setPiece(Piece piece) {
			this.piece = piece;
		}
		public Position getToPosition() {
			return toPosition;
		}
		public void setToPosition(Position toPosition) {
			this.toPosition = toPosition;
		}
		public boolean isCapture() {
			return isCapture;
		}
		public void setCapture(boolean isCapture) {
			this.isCapture = isCapture;
		}
		public String getCombination() {
			return combination;
		}
		public void setCombination(String combination) {
			this.combination = combination;
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



/*
 *Grabage"
 *
 * 
 */
//		Route r = new Route(Direction.LEFT, position1Piece blocker);
//		r.generatePathsForRookOld();
//		System.err.println("route LEFT = " + r.describe + " to string = " + r);
//
//		r = new Route(Direction.RIGHT, position1);
//		r.generatePathsForRookOld();
//		System.err.println("route RIGHT = " + r.describe + " to string = " + r);
//		
//		
//		r = new Route(Direction.DOWN, position1);
//		r.generatePathsForRookOld();
//		System.err.println("route DOWN = " + r.describe + " to string = " + r);
//		
//		r = new Route(Direction.UP, position1);
//		r.generatePathsForRookOld();
//		System.err.println("route UP = " + r.describe + " to string = " + r);
//


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
