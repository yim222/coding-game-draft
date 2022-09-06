package clashing.solutions;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

//you have here calculator to check coprimes
//https://www.mathsisfun.com/numbers/coprime-calculator.html
//https://www.dcode.fr/euler-totient - for checking the answer



public class FindCoprimes {

	
	
	public static void main(String[] args) {
		
		int test1 = 9;
		int test2 = 30;
		
		int n = 900;
		
		System.out.println("factors of " + test1 + " = \n" + getFactors(test1));
//		printDivisors(100);
		
		System.out.printf("is %d and %d are coprime ? %s", test1, test2, isCoprime(test1, test2));
		
		System.out.println("\nNumbers of coprimes up to "+n +":\n" + countCoprimes(n));
		
	}
	
	public static int countCoprimes(int n) {
		int count = 0;
		for(int i =1; i <=n ; i++) {
			if(isCoprime(i, n)) {
				count++;
			}
		}
		return count;
	}
	
	public static boolean isCoprime(int a, int b) {
		List<Integer> aFactors = getFactors(a);
		List<Integer> bFactors = getFactors(b);
//		List<Integer> toCheck = aFactors.size();

		for(int i : aFactors) {
			if(i == 1)continue;
			if(bFactors.contains(i) ) {
				return false;
			}
		}
		

		
		return true;
	}
	
	public static List<Integer> getFactors(int n){
		List <Integer> factors = new ArrayList<>();
		for(int i =1; i <= Math.sqrt(n); i++ ) {
//			System.out.println("i = " + i);
			if(n % i == 0) {
				factors.add(i);
				
				if(i != Math.sqrt(n)) {
					factors.add(n/i);
				}
			}
			
		}
		factors.sort(Comparator.naturalOrder());
		return factors;

	}
	
	static void printDivisors(int n)
    {
        // Note that this loop runs till square root
        for (int i=1; i<=Math.sqrt(n); i++)
        {
            if (n%i==0)
            {
                // If divisors are equal, print only one
                if (n/i == i)
                    System.out.print(" "+ i);
      
                else // Otherwise print both
                    System.out.print(i+" " + n/i + " " );
            }
        }
    }
}
