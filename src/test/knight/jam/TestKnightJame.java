package test.knight.jam;

import static org.junit.jupiter.api.Assertions.*;

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

}
