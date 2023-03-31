package test.knight.jam;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import algorithms.KnightJam;

//@TestMethodOrder(MethodOrderer.MethodName.class)//order by the name of the methods
//@TestMethodOrder(OrderAnnotation.class)
@TestMethodOrder(OrderAnnotation.class)

class TestKnightJame {

	@Test
    @Order(1)    

	void testGetSquare() {
//		assertEquals(3,3);
//		//System.out.println("first");
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
    @Order(2)    

	void testSetState() {
//		//System.out.println("second");
		KnightJam k = new KnightJam("1283567.4");
		k.setState(1);
		assertEquals("12.356784", k.state);
		k.setState(8);
		assertEquals("123.56784", k.state);
		k.setState(3);
		assertEquals("12345678.", k.state);		
		
		k = new KnightJam(".24751683");
		k.setState(8);
		
		assertEquals("12475.683", k.state);
		
		

	}
	
	@Test
    @Order(2)    

	void testSetState2() {
//		//System.out.println("second");
		KnightJam k = new KnightJam("12475.683");
		k.setState(1);
		
		
		assertEquals("124756.83", k.state);
		
		

	}
	
	@Test
    @Order(2)    

	void testSetState3() {
//		//System.out.println("second");
		KnightJam k = new KnightJam("13.456287");
		k.setState(4);
		
		
		assertEquals("1384562.7", k.state);
		
		

	}
	
    @Order(3)    
	@Test
	void testRun() {
//		//System.out.println("third");
		KnightJam k = new KnightJam("1283567.4");
		int count = k.run(0, k.state, 1, new ArrayList<String>());
		assertEquals(3, count);
	

	}
    @Order(3)    
   	@Test
   	void testRun2() {
//   		//System.out.println("third");
   		
   		KnightJam k = new KnightJam(".24751683");
   		int count = k.run(0, k.state, 8, new ArrayList<String>());
   		assertEquals(-1, count);

   	}
	
	
	@Test
    @Order(4)    
	void   testCompareAndSolve() {
//		//System.out.println("forth");
		KnightJam k = new KnightJam("1283567.4");
		int[] result = k.compareAndSolve();
		assertEquals(new int[]{1,3}, result);
		

	}

}
