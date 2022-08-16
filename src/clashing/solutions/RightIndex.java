package clashing.solutions;

/*
4 - times (height)
3 - length - (Width) 
line = the word. (Abc)

output: 
	
	aaa
	bbb
	ccc
	
	*/
import java.util.*;
import java.io.*;
import java.math.*;

public class RightIndex {

	/**
	 * Auto-generated code below aims at helping you parse the standard input
	 * according to the problem statement.
	 **/

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int h = in.nextInt();
		int w = in.nextInt();
		if (in.hasNextLine()) {
			in.nextLine();
		}
		String line = in.nextLine();
		for (int i = 0; i < h; i++) {
			int rightIndex = i;

			if (i > line.length()-1) {
				rightIndex = i % line.length();// i / line.length()   + line.length() % i ;

			}
			System.err.println(
					"index = " + rightIndex + " line = " + line + " right char = + " + line.charAt(rightIndex) + "\n");
			String printMe = "";
			for (int j = 0; j < w; j++) {
				printMe += line.charAt(rightIndex);
			}

			// Write an answer using System.out.println()
			// To debug: System.err.println("Debug messages...");

			System.out.println(printMe);
		}
	}
}
