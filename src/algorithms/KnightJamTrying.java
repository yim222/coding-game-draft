package algorithms;

import java.util.ArrayList;

public class KnightJamTrying {
	public static void main(String[] args) {
		KnightJam k = new KnightJam("1283567.4");
//		k.setState(1);
//		k.setState(8);
//		k.setState(4);
		int count = k.run(0, k.state, 1, new ArrayList<String>());
		System.out.println("count = " + count);
		
		k = new KnightJam(".24751683");
		System.out.println("state before= " + k.state);
		count = k.run(0, k.state, 8, new ArrayList<String>());	
		
		System.out.println("count = " + count);

	}
}
