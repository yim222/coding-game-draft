package clashing.solutions;

public class CompareTemprature {

	public static int getF(int c) {
		return c * 9 / 5 + 32;
	}

	public static void main(String[] args) {

//		System.out.println(getF(0));
//Get the values 
//		1- Compare the b-t values 
//		2- Return Lower/Higher/Same (ben vs tom)

//		String[] test = { "5 -14", "5 -15", "5 -16" };
//		String[] test = { "-40 -40" };
//		String[] test = {"51 10", "49 10", "50 10"};
		String[] test = { "32 0" };
		String ssss[] ; 



		
		for(int i = 0; i < test.length; i++) {
			String[] inputs = test[i].split(" ");
			
			int ben = Integer.parseInt(inputs[0]);
			int tom = Integer.parseInt(inputs[1]);
			
			tom = getF(tom);
			
//			System.err.println("ben = " + ben + " tom = " + tom);
			
			if (ben > tom) {
				System.out.println("Higher");
			}
			else if(ben < tom){
				System.out.println("Lower");
			}
			else {
				System.out.println("Same");
			}
			
			
			
		}
		

	}

}
