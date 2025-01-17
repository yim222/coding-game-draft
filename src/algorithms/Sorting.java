package algorithms;

import java.util.Arrays;

public class Sorting {

	public static int[] list = {40, 23, 5, 66, 2 , 8};
	
	public static int actionCounter = 0;
	
	
	 
	public static void main(String[] args) {
		insertionSort();
		System.out.println("actions = " + actionCounter);
	}
	public static int[] insertionSort() {
		
		System.out.println("List at the start:\n" + Arrays.toString(list));
		
		for(int i = 0; i < list.length; i++) {
			int current = list[i];
			int previousI = i-1;
			while(previousI >= 0 && list[previousI] > current) {
				list[previousI+1] = list[previousI];
				list[previousI] = current;
				previousI--;
				actionCounter++;
			}
		}
		System.out.println("List after sorting:\n" + Arrays.toString(list));

		
		return list;
	}
	
	
}
