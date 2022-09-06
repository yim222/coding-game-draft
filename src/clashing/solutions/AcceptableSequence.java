package clashing.solutions;
/**
 * Don't sure that it is working
 * @author lingar
 *
 */
public class AcceptableSequence {

	public static boolean isAccepted(String s) {
		int down = 0, up =s.length()-1;
		//first check the border 
		char upper = s.charAt(up), lower = s.charAt(down);
		boolean foundNumLeft = false , foundNumRight ;

		if(! 
				(Character.isAlphabetic(lower) &&Character.isAlphabetic(upper) )) {
			
			return false;
			
		}

		while(true) {
			if(down >= up) {
				break;
			}
			
			
			upper = s.charAt(--up);
			lower = s.charAt(++down);
			
			if(Character.isAlphabetic(lower) || Character.isAlphabetic(upper) ) {
				
				
				if(foundNumLeft) {
					return false;
				}
			}
			
		}
		
		return false;
	}
	
	

}

