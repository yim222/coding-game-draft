package draft;

import java.util.Scanner;

public class Ms2FormattedTime {
	static long D = 12_000_000;// 980287204;// 12_000_000;

	public static void main(String args[]) {
		/**
		 * TESTING: 
		 * 12000000 
		 * 3h 20m
		 * 
		 * 83521000 
		 * 23h 12m 1s
		 * 
		 * 0 
		 * 
		 * 0s
		 * 
		 * 202698400 2d 8h 18m 18s
		 * 
		 * 
		 * 122000 2m 2s
		 * 
		 */


		solveIt(12000000);
		
		solveIt(83521000);
		
		solveIt(0);

		solveIt(202698400);

		solveIt(122000);

	}

	public static String solveIt(long D) {
		boolean b = true;
		long[] types = { 60, 60, 24 };
		char[] letters = { 's', 'm', 'h', 'd' };
		String s = "";
		if (D < 1000) {
			System.out.println(D);
			System.out.println(0+"s");
			return 0+"s";

		}
		long time = D / 1000;
//		System.err.println("D = " + D);
		for (int i = 1; i <= 4; i++) {
			if (i == 4) {

				System.out.println(D);

				s = s.substring(1);

				System.out.println(s);

			}
//			System.err.println(time % types[i - 1]);
			// modules
			if (time % types[i - 1] != 0) {
				s = " " + time % types[i - 1] + letters[i - 1] + s;
			}

//			System.err.println(s);

			time = time / types[i - 1];
			if (i == 3) {

//					System.out.println("time = " + time);
				if (time > 0) {
					s = " " + time + letters[i] + s;
				}
				System.out.println(D);

				s = s.substring(1);

				System.out.println(s);
				return s;

			}
			// result

		}
		return "";
		// Write an answer using System.out.println()
		// To debug: System.err.println("Debug messages...");

	}
}

/*
 * 
 * 
 * Line 1: An integer D of the duration in milliseconds. Output Line 1:
 * Converted duration in the format of 0d 0h 0m 0s. It must at least have 0s. If
 * any other parts are not applicable, exclude them. Constraints Example
 * 
 * 
 * Input 12_000_000 Output 3h 20m
 * 
 * 12000000 3h 20m
 * 
 * 
 * 
 */

//tests:

/*
 * 
 * 
 * 
 * 12000000 3h 20m
 * 
 * 83521000 23h 12m 1s
 * 
 * 0 0s
 * 
 * 202698400 2d 8h 18m 18s
 * 
 * 
 * 122000 2m 2s
 * 
 * 
 * 980287204 589 11345d 22h 4s 16,338,120 - minute
 * 
 * 11,345d 22m 4s
 * 
 * 272,302 - hours -
 * 
 * starting from second
 * 
 * module - is the current
 * 
 * result is the next
 * 
 * 
 */