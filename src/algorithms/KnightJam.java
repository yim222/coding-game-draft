package algorithms;

//check for eclipse
import java.util.List;

// TODO NExt - create Empty methods for all needed below. -done
//Create needed tests for that. -done
//Start develop with that.--> u here there is some unexpected behavior in test Run. Debug it, and understand where is the problem.  
/**
 * 
 * @author lingar
 *
 *         This problem:
 *
 *         https://www.codingame.com/ide/puzzle/knights-jam
 * 
 *         U need to find if it's possible. And what is the most smallest
 *         possible.
 * 
 * 
 *         In simple way:
 * 
 *         You should iterate on all combinations.
 * 
 *         And create some method that check the possible moves to schema and
 *         return it.
 * 
 *         And on each to check if you have the right sequence.
 * 
 *         And on if not to continue.
 * 
 *         Until when?
 * 
 *         Until you have previous sequence that already have checked,
 * 
 *         So you need to check it as well.
 * 
 *         And on each branch to check all the possible movements.
 * 
 * 
 *         Brain storm -
 * 
 *         What combinations methods you know?
 * 
 *         If it's semtric, it's the same? not sure. leave it.
 * 
 * 
 *         TODOPlanning - So what you need?
 * 
 *         - To take some 1-8+. in random way. - Not you have tests.
 * 
 *         iterate and provide all their sequence ? not you don't need to do
 *         this.
 * 
 *         U can easily know what squares can move knight to this square.
 * 
 *         1-6,8 | 2 - 7,9 | 3 - 4,8 and so on. So you don't need to calculate
 *         it.
 * 
 *         Also - for sure You dont back to the last square
 * 
 *         It's long I trust them that they're checked. But for the condition is
 *         true. If it's back to previous sequence, you cannot find. It's logic.
 *         אינדוקציה גם /
 * 
 *         I don't know if there is a more clever way to solve it.
 * 
 *         You have on each check two paths.
 * 
 *         From there you have only one.
 * 
 *         If the cycle reccuring - it's not found.
 * 
 *         TODO:
 * 
 *         Method - Get squares that return the possible paths - with param of
 *         the square, and one method for last, that will return the other. - V
 * 
 *         Method - setState that change the state by itself and last checked.
 * 
 *         Method: run
 * 
 *         On each calcualtion to count.
 * 
 *         To check - if it's true - if does to return the count. To check if
 *         it's already exist - return -1. To save the sequence. To run on the
 *         new sequence. if not - to run more... until u return the count.
 * 
 *         -so save path 1 and 2. - to get the max from them.
 * 
 *         This is the answer.
 * 
 * 
 *         Start
 *
 */

public class KnightJam {

	// * Method - Get path that return the possible squares - with param of the
	// square, and one method for last, that will return the other.

	public KnightJam(String state) {
		this.state = state;
	}

	public String state = "";

	public void setState(int lastChecked) {
		// if -1 it's the first time. return
		// getSquares(current, last)

		int currentSquare = this.state.indexOf('.') + 1;
		int nextSquad = getSquares(currentSquare, lastChecked);

//		System.out.println( 
//				"state = " + state
//				+ " | current = "+ (currentSquare)
//				+ " |next = " + nextSquad);

		// manipulating string.
		// putting the point at the right place first.
		String state2 = "init";
		if (nextSquad < currentSquare) {
			System.out.println("state = " + state);
			state2 = state.substring(0, nextSquad - 1) + '.' + state.substring(nextSquad, currentSquare - 1)
					+ state.charAt(nextSquad - 1) + state.substring(currentSquare);

//			state = state.substring(0, nextSquad-1) + '.' 
//			+ state.substring(nextSquad +1, currentSquare) 
//			+ state.charAt(nextSquad-1)
//			+ state.substring(currentSquare);

		} else {
			state2 = state.substring(0, currentSquare - 1) + state.charAt(nextSquad - 1)
					+ state.substring(currentSquare, nextSquad - 1) + '.'
					+state.substring(nextSquad);
					//+ (nextSquad + 1 <= 8 ? state.substring(nextSquad) : "");
		}
//		System.out.println(state.substring(20,0));
		state = state2;

	}
	//debugger on exceptions - 
	//https://stackoverflow.com/questions/3066199/break-when-exception-is-thrown

	public int run(int count, String state, int lastChecked, List<String> alreadyChecked) {
		// check if it's ordered

		boolean ordered = state.equals("12345678.");

		// if true return the count
		if (ordered) {
			return count;
		}

		// if not ordered-
		// if exist already - stop and return -1.
		if (alreadyChecked.contains(state)) {
			return -1;
		}

		// if not exist
		// add to the exists
		alreadyChecked.add(state);
		// count
		count++;

		// get the next state and last checked
		// next lastChecked
		int tempLastChecked = state.indexOf('.')+1;
		
		setState(lastChecked);
		lastChecked = tempLastChecked;

		// continue to roll it.

//		run(count, state, lastChecked, alreadyChecked);

		return run(count, this.state, lastChecked, alreadyChecked);
	}

	public int[] compareAndSolve() {

		/**
		 * This will check both branches of the initial state. and compare what exist if
		 * does and what right.
		 */
		// will return the number of the sequence from the less last checked, and the
		// count. [1/2, count]
		// if not exist both [-1,-1]
		return null;
	}

	public static int[] getSquares(int emptySquare) {

		switch (emptySquare) {
		case 1:
			return new int[] { 6, 8 };

		case 2:
			return new int[] { 7, 9 };

		case 3:
			return new int[] { 4, 8 };
		case 4:
			return new int[] { 3, 9 };
		case 6:
			return new int[] { 1, 7 };
		case 7:
			return new int[] { 2, 6 };
		case 8:
			return new int[] { 1, 3 };
		case 9:
			return new int[] { 2, 4 };

		default:
			return null;

		}
	}

	public static int getSquares(int emptySquare, int lastSquare) {
		int[] squares = getSquares(emptySquare);
		return lastSquare == squares[0] ? squares[1] : squares[0];
	}

}