package clashing.solutions;

import java.util.*;
import java.io.*;
import java.math.*;

public class Holes {

	/**
	 * Auto-generated code below aims at helping you parse the standard input
	 * according to the problem statement.
	 */
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int h = in.nextInt();
		int p = in.nextInt();

		// Write an answer using System.out.println()
		// To debug: System.err.println("Debug messages...");
		if (h <= 0) {
			System.out.println("ERROR");
			return;
		}
		int currentHole =0, amount =0;
		//if p<=n 
		if(p<=h) {
			//current is the p
			currentHole = p-1;//-1 for the index.
			//amount is only 1.
			amount = 1;
		}
		//p>h
		else {
			int lines = p/h;
			int reminder = p%h;
			currentHole = reminder == 0? h-1: reminder-1;
			amount = reminder == 0 ? lines: lines +1; 
		}
		
		System.out.println(currentHole);
		System.out.println(amount);
	}

}
