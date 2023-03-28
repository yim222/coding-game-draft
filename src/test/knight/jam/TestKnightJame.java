package test.knight.jam;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;


import algorithms.KnightJam;

class TestKnightJame {

	@Test
	void testGetSquare() {
//		assertEquals(3,3);
		assertArrayEquals(new int[] {1,3}, KnightJam.getSquares(8));
		assertArrayEquals(new int[] {2,4}, KnightJam.getSquares(9));
		assertArrayEquals(null, KnightJam.getSquares(5));
		assertEquals(8,KnightJam.getSquares(3, 4));
		assertEquals(9,KnightJam.getSquares(2, 7));
		assertEquals(7,KnightJam.getSquares(2, 9));



//		assertEquals(new int[] {1,3}, KnightJam.getSquares(8));

//		fail("Not yet implemented");
	}
	
	@Test
	void testSetState() {
		KnightJam k = new KnightJam("1283567.4");
		assertEquals("12.356784", k.state);
		
	}
	
	@Test
	void testRun() {
		KnightJam k = new KnightJam("1283567.4");
		int count = k.run(0, k.state, 1, new ArrayList<String>());
		assertEquals(3, count);
		k = new KnightJam(".24751683");
		count = k.run(0, k.state, 8, new ArrayList<String>());
		assertEquals(-1, count);

	}
	
	
	@Test
	void testCompareAndSolve() {
		KnightJam k = new KnightJam("1283567.4");
		int[] result = k.compareAndSolve();
		assertEquals(new int[]{1,3}, result);
		

	}

}
