package clashing.solutions;

import java.util.Arrays;

public class AbacusHeshbonitYeladim {

	static String[] test1 = { "oooooooooo----", "oooooooooo----", "oooooooooo----", "oooooooooo----", "oooooooooo----",
			"ooooo----ooooo", "oo----oooooooo", "ooooooo----ooo", "oooooooo----oo", };

	static String[] test2 = { "oooooooooo----", "oooooooooo----", "oooooooooo----", "oooooooooo----", "oooooooooo----",
			"oooooooooo----", "oooooooooo----", "oooooooo----oo", "----oooooooooo"

	};

	static String[] test3 = {

			"----oooooooooo", "ooooooooo----o", "ooooooooo----o", "ooooooooo----o", "ooooooooo----o", "ooooooooo----o",
			"ooooooooo----o", "ooooooooo----o", "ooooooooo----o", };

	
	static String[] test3_2 = {

			"----oooooooooo",
			"----oooooooooo",
			"----oooooooooo",
			"----oooooooooo",
			"----oooooooooo",
			"----oooooooooo",
			"----oooooooooo",
			"----oooooooooo",
			"----oooooooooo",

			
	};

	static String[] test4 = {

			"ooooooooo----o",
			"oooooooo----oo",
			"ooooooo----ooo",
			"oooooo----oooo",
			"ooooo----ooooo",
			"oooo----oooooo",
			"ooo----ooooooo",
			"oo----oooooooo",
			"o----ooooooooo",

			
	};
	
	
	static String[] test5 = {

			"o----ooooooooo",
			"oo----oooooooo",
			"ooo----ooooooo",
			"oooo----oooooo",
			"ooooo----ooooo",
			"oooooo----oooo",
			"ooooooo----ooo",
			"oooooooo----oo",
			"ooooooooo----o",


			
	};

	public static String buildResult(String[] data) {
		String result = "";

		for (int i = 0; i < data.length; i++) {// except last
			if (i == 7) {
				result += ".";
			}
			int sum = count(data[i]);
			if (sum == 10) {
				if (result.length() == 0) {
					result += sum + "";
				} else {// there is some previous data
						// replace the last

					int lastDigit = Integer.parseInt(result.charAt(result.length() - 1) + "");
					lastDigit++;
					result = result.substring(0, result.length() - 1) + lastDigit + "0";
				}
			} else {
				result += sum;
			}
//			result+= count(data[i])+"".charAt(0);

		}

		return result;
	}

	public static int count(String row) {
		int startFrom = 0;
		int sum = 0;
		for (int i = 0; i < row.length(); i++) {
			if (row.charAt(i) == '-') {
				startFrom = i;
				break;
			}

		}

		for (int i = startFrom; i < row.length(); i++) {
			if (row.charAt(i) != '-') {
				sum++;

			}

		}

		return sum;

	}

	public static void main(String[] args) {
		System.out.println(Arrays.toString(test1));

		System.out.println(count("ooooooo----ooo"));

		float result = Float.parseFloat(buildResult(test1));
		System.out.println(String.format("%.2f", result));
		result = Float.parseFloat(buildResult(test2));
		System.out.println(String.format("%.2f", result));
		System.out.println(buildResult(test3));
		result = Float.parseFloat(buildResult(test3));

		System.out.println(String.format("%.2f", result));// seems to be unacuratie
//
//		System.out.println(buildResult(test3_2));
//		result = Float.parseFloat(buildResult(test3_2));

		System.out.println("Need to find accurate way. Appernetly with BigDouble22. ");// seems to be unacuratie
		
		
		System.out.println(buildResult(test4));
		result = Float.parseFloat(buildResult(test4));
		System.out.println(String.format("new = %.2f", Float.parseFloat(buildResult(test4))));

		System.out.println("Need to find accurate way. Appernetly with BigDouble. ");// seems to be unacuratie
		

		System.out.println(buildResult(test5));
		result = Float.parseFloat(buildResult(test5));

		System.out.println("Need to find accurate way. Appernetly with BigDouble. " );// seems to be unacuratie



	}
}
