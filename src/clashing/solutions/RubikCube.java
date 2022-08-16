package clashing.solutions;

import java.util.Arrays;

/**
 * 
 * @author lingar
 *
 *         Actually it's complicated description to a simple solution. Just to
 *         take the String and check it against the provided String.
 *
 *         First remove Spaces
 *
 *         Then check it.
 */
public class RubikCube {
	public static String proper = "    UUU\r\n" + "    UUU\r\n" + "    UUU\r\n" + "\r\n" + "LLL FFF RRR BBB\r\n"
			+ "LLL FFF RRR BBB\r\n" + "LLL FFF RRR BBB\r\n" + "\r\n" + "    DDD\r\n" + "    DDD\r\n" + "    DDD\r\n"
			+ "";

	// Solvable
	public static String test1 = "    UUB\r\n" + "    UUB\r\n" + "    UUB\r\n" + "\r\n" + "LLL FFU RRR DBB\r\n"
			+ "LLL FFU RRR DBB\r\n" + "LLL FFU RRR DBB\r\n" + "\r\n" + "    DDF\r\n" + "    DDF\r\n" + "    DDF\r\n"
			+ "";
	// Solvable
	public static String test2 = "    UUU\r\n" + "    UUU\r\n" + "    UUU\r\n" + "\r\n" + "LLL FFF RRR BBB\r\n"
			+ "LLL FFF RRR BBB\r\n" + "LLL FFF RRR BBB\r\n" + "\r\n" + "    DDD\r\n" + "    DDD\r\n" + "    DDD\r\n"
			+ "";

	// UNSOLVABLE
	public static String test3 = "UUU\r\n" + "    UUU\r\n" + "    UUU\r\n" + "\r\n" + "UUU UUU UUU UUU\r\n"
			+ "UUU UUU UUU UUU\r\n" + "UUU UUU UUU UUU\r\n" + "\r\n" + "    UUU\r\n" + "    UUU\r\n" + "    UUU\r\n"
			+ "";
	// Solvable
	public static String test4 = "DUD\r\n" + "    DUU\r\n" + "    BDL\r\n" + "\r\n" + "LLL UFF ULR FBF\r\n"
			+ "LLR BFF URB DBF\r\n" + "FRD BUU BFB DBR\r\n" + "\r\n" + "    LRR\r\n" + "    DDR\r\n" + "    ULR\r\n"
			+ "";
	// Solvable
	public static String test5 = "UUU\r\n" + "    UUU\r\n" + "    UFU\r\n" + "\r\n" + "LLL FUF RRR BBB\r\n"
			+ "LLL FFF RRR BBB\r\n" + "LLL FFF RRR BBB\r\n" + "\r\n" + "    DDD\r\n" + "    DDD\r\n" + "    DDD\r\n"
			+ "";
	// Solvable
	public static String test6 = "UUU\r\n" + "    UUU\r\n" + "    UUF\r\n" + "\r\n" + "LLL FFR URR BBB\r\n"
			+ "LLL FFF RRR BBB\r\n" + "LLL FFF RRR BBB\r\n" + "\r\n" + "    DDD\r\n" + "    DDD\r\n" + "    DDD\r\n"
			+ "";

	// Unsolvable
	public static String test7 = "LRD\r\n" + "    DFR\r\n" + "    RBD\r\n" + "\r\n" + "ULF LRB DBD LLB\r\n"
			+ "RLL BUU BFD FFU\r\n" + "BDR URL UDF RFL\r\n" + "\r\n" + "    FBU\r\n" + "    LRU\r\n" + "    DFU\r\n"
			+ "";

	// Unsolvable
	public static String test8 = "    BDL\r\n"
			+ "    DBL\r\n"
			+ "    LUR\r\n"
			+ "\r\n"
			+ "BFR BBD LUFDUR\r\n"
			+ "UBR RRD DLDFUR\r\n"
			+ "RBF FUU FRFBFD\r\n"
			+ "\r\n"
			+ "    UBF\r\n"
			+ "    LLD\r\n"
			+ "    LUR\r\n"
			+ "";

	public static void main(String[] args) {
		String test = test8 ; // ------> change here the case 
		test = cleanWhiteSpaces(test);
		proper = cleanWhiteSpaces(proper);

		String output = "A";
//		System.out.println("Test1 = " + test1);
		if (isSolveable(test)) {
			output = "SOLVABLE";
		} else {
			output = "UNSOLVABLE";

		}

		System.out.println(output);

	}

	public static boolean isSolveable(String word) {
//		System.out.println("???" + word);
		// check the test against the proper. remove for each one from the proper - if
		// exist. If not exist - that's unsolveable.
		// If the proper

		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
//			System.out.println(c + " " + proper.contains(c+""));
//			System.out.println("Proper = " + proper);

			if (proper.contains(c + "")) {
//				https://stackoverflow.com/a/10897513/9727918
//				proper = proper.replace(word.charAt(i)+"", "");//that;s replacing all
				proper = proper.replaceFirst(c + "", "");
			} else {
				return false;
			}
		}

		return true;
	}

	public static String cleanWhiteSpaces(String par) {
		// https://stackoverflow.com/a/17661556/9727918
		String newWord = par.replaceAll("\\s+", "");

		return newWord;
	}

}
