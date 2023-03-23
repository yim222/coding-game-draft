package algorithms;


// TODO NExt - create Empty methods for all needed below. 
//Create needed tests for that. 
//Start develop with that. 
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
 *         Method - Get path that return the possible paths - with param of the
 *         square, and one method for last, that will return the other.
 * 
 *         Method - get new State that return the new sequence by current
 *         sequence and last checked.
 * 
 *         Method: run
 * 
 *         On each calcualtion to count.
 * 
 *         To check - if it's true - if does to return the count. To check if
 *         it's already exist - return -1. To save the sequence. To run on the
 *         new sequence.
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
	
	public static int getSquares(int emptySquare, int lastSqaure) {
		int[] squares = getSquares(emptySquare);
		return lastSqaure == squares[0]? squares[1]: squares[0];
	}

}
