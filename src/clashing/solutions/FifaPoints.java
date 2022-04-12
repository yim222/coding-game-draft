package clashing.solutions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FifaPoints {
	static FifaPoints fInstance = new FifaPoints();

	public class Group {
		Team[] teams = new Team[4];
		int lastIdx = 0;

		Group(String[] groupData) {

			//System.out.println("Our group data:\n" + Arrays.toString(groupData));

			for (int i = 0; i < groupData.length; i++) {
				//System.out.println("element = " + groupData.length);

				parseTheMatch(groupData[i]);
				//System.out.println("???");

			}
			
			
			System.out.println("Our teams: \n" +  Arrays.toString(teams));
			// create teams and btw calculate by the provided string[]
			
			//converting to list: 
			List<Team> teamsList = Arrays.asList(teams);
			
			System.out.println("list = \n" + teamsList);
			Collections.sort(teamsList, new SortTable());
			

			// rank them properly
			System.out.println("list after sorting= \n" + teamsList);
			
			//print result 
			for (Team t: teamsList) {
				System.out.print(t.sign+" ");
			}
			
			

		}

		// parse the match - prepare the data
		public void parseTheMatch(String match) {
//			//System.out.println("Match = " + match);
			String[] words = match.split(" ");

//			//System.out.println("words = \n" + Arrays.toString(words));

			String[] words2 = words[2].split("-");
//			//System.out.println("words2 = \n" + Arrays.toString(words2));

			String team1 = words[0], team2 = words[1];
					int team1Score = Integer.parseInt( words2[0]), team2Score = Integer.parseInt( words2[1]);
					
			calculateRound(team1, team2, team1Score, team2Score); 
		}

		// and calculate
		public void calculateRound(String team1Sign, String team2Sign, int team1Score, int team2Score) {
			//System.out.println("calculate round");
			//assigning the data 
			Team team1 = getTeamBySign(team1Sign);
			Team team2 = getTeamBySign(team2Sign);

			team1.positiveGoals += team1Score;
			team2.negativeGoals += team1Score;
			
			team2.positiveGoals += team2Score;
			team1.negativeGoals += team2Score;
			
			if(team1Score > team2Score) {
				team1.points += 3;
			}
			else if(team1Score < team2Score) {
				team2.points += 3;
			}
			else {
				team1.points++;
				team2.points++;
			}

			
			
			
		}

		public void calculatedTable() {

		}
		// Recursion - not need

		public int indexOfTeam(String sign) {
			//System.out.println("Sign  = " + sign);
			//System.out.println("indexOf ... Our teams: \n" +  Arrays.toString(teams) + " is null? " + (teams[0] == null));
//					+ " is sign== ? " + (teams[0].sign == sign)  );

			for (int i = 0; i < 4; i++) {
//				if(teams[i]!=null) {
////					System.out.println(teams[i] + "vs " + sign);
//				}
				if (teams[i] != null && teams[i].sign.equals(sign)) {
					return i;
				}
			}
			return -1;
		}
		
		public Team getTeamBySign(String sign) {
			if(indexOfTeam(sign) < 0) {
//				teams[lastIdx++] = fInstance.new Team();
				//System.out.println("idnex =  = " + indexOfTeam(sign) );

				//System.out.println("Our teams: \n" +  Arrays.toString(teams));

				//System.out.println("sign = " + sign );

				teams[lastIdx] = new Team(sign);

				lastIdx++;
				return teams[lastIdx-1];
				

			}
			return teams[indexOfTeam(sign)];
		}

	}

	public static  class Team {
		
		public Team(String sign) {
			super();
			this.sign = sign;
		}
		String sign;
		int positiveGoals, negativeGoals, points;
		@Override
		public String toString() {
			return  sign + " " + positiveGoals + "-" + negativeGoals + " points=" + points + "\n";
		}
		
		

	}

	class SortTable implements Comparator<Team>{
		public int compare(Team a, Team b) {
			
			if(a.points != b.points) {
				
				if( a.points>b.points) 
					return -1;
				else 
					return 1;
			}
			else {
				if(a.positiveGoals-a.negativeGoals != b.positiveGoals - b.negativeGoals) {
					return a.positiveGoals-a.negativeGoals > b.positiveGoals - b.negativeGoals ?  -1: 1;
				}
				else {
					return a.positiveGoals > b.positiveGoals ? -1 : 1;
				}
			
			}
		}
	}
	
	public static void main(String[] args) {
		// get the data from file, and parse it into array
		String[] groupData = new String[6];
		try {
			File data = new File("./data.txt");

			// Creating an object of BufferedReader class
			BufferedReader br = new BufferedReader(new FileReader(data));

			String st;
			// Condition holds true till
			// there is character in a string
			int index = 0;
			while ((st = br.readLine()) != null) {

				// Print the string
//	            //System.out.println(st);
				groupData[index++] = st;
			}

			// Outer_Demo.Inner_Demo inner = outer.new Inner_Demo();

			FifaPoints.Group group = fInstance.new Group(groupData);

//	        //System.out.println(Arrays.toString(groupData));

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			//System.out.println("?2973293797?? - " );
		}
	}

}
/**
 * 
 * Group ={ 4 teams play round robin()
 * 
 * team = {
 * 
 * 
 * int scheduleMatch(other) return the points }
 * 
 * 
 * 
 * }
 */