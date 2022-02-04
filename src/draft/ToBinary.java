package draft;
//import Rook f
import java.util.Arrays;

public class ToBinary {
//	Rook r;
	public static void main(String args[]) {

		String organized = "a b c";
		String notOrganized = "a    b   c";

		String[] splitted = organized.split(" ");
		System.out.println(Arrays.toString(splitted));

		// Problem
		splitted = notOrganized.split(" ");
		System.out.println(Arrays.toString(splitted));

		// Solution
		String[] splittedWell = notOrganized.split("\\s+");// backslash not forward.
		System.out.println(Arrays.toString(splittedWell));
//    	s = 100:800:50
//    			s = 10:20:345
//    			s = 200:50:64
//    			answer

//    	Found: answer
//    	Expected: 113:20:50

		String[] tf1 = "113:72:12".split(":");
		System.out.println(Arrays.toString(tf1));
		int[] tf2 = new int[3];// h, m, s =0;
		for (int i = 0; i < 3; i++) {
			tf2[i] = Integer.parseInt(tf1[i]);
			if (i > 0 && tf2[i] / 60 > 0) {
				tf2[i - 1] += (tf2[i] / 60);
				tf2[i] = tf2[i] % 60;
			}

		}
		// after calculating we can assing it to the formats:
		for (int i = 0; i < 3; i++) {
			String s = tf2[i] > 10 ? "" + tf2[i] : "0" + tf2[i];
			System.out.print(s);

			if (i < 2)
			System.out.print(":");
		}

//        Scanner in = new Scanner(System.in);
//        int N = in.nextInt();
//        if (in.hasNextLine()) {
//            in.nextLine();
//        }
//        for (int i = 0; i < N; i++) {
//            String S = in.nextLine();
//            System.err.println("number = ..." + S);
//        }
//
//        // Write an answer using System.out.println()
//        // To debug: System.err.println("Debug messages...");
//
//        System.out.println("answer");
	}
}

/*
 * 
 * Today at the course I go over fibonacii. Let's do it well. import
 * java.util.*; import java.io.*; import java.math.*;
 * 
 * class Solution {
 * 
 * public static void main(String args[]) { Scanner in = new Scanner(System.in);
 * int t = in.nextInt(); int a = in.nextInt(); int b = in.nextInt(); for() } }
 */