import java.util.ArrayList;
import java.util.HashSet;

public class BaseballElimination {
	private int numberOfTeams;
	private ArrayList<String> teamsList;
	// put all the record in the teamsRecord: win, lose, remain, against list 75 59 28   0 3 8 7 3
	//so againt record should +3(NUMRECORD)
	private int[][] teamsRecord;
	//why static
	private final static int NUMRECORD = 3;
	private final static int INDEXWIN = 0;
	private final static int INDEXLOSE = 1;
	private final static int INDEXREMAIN = 2;


	public BaseballElimination(String filename) { // create a baseball division from given filename in format specified below
		//Write code to read in the input file and store the data.
		In in = new In(filename);
		numberOfTeams = in.readInt();
		teamsList = new ArrayList<String>(numberOfTeams);
		teamsRecord = new int[numberOfTeams][numberOfTeams + NUMRECORD];
		int line = 0;
		while (line < numberOfTeams) {
			String str = in.readString();
			teamsList.add(line, str);
			for(int i = 0; i < numberOfTeams + NUMRECORD; i++) {
				teamsRecord[line][i] = in.readInt();
				//StdOut.println(teamsRecord[line][i]);
			}
			line++;
		}
		
	}
	public int numberOfTeams() { // number of teams
		return numberOfTeams;
	}
	public Iterable<String> teams() {// all teams
		return teamsList;
	}
	public int wins(String team) { // number of wins for given team
		if (!teamsList.contains(team))
			throw new IllegalArgumentException();
		
		int i = teamsList.indexOf(team);
		return teamsRecord[i][INDEXWIN];
	}
	public int losses(String team) {// number of losses for given team
		if (!teamsList.contains(team))
			throw new IllegalArgumentException();
		
		int i = teamsList.indexOf(team);
		return teamsRecord[i][INDEXLOSE];
	}
	public int remaining(String team) { // number of remaining games for given team
		if (!teamsList.contains(team))
			throw new IllegalArgumentException();
		
		int i = teamsList.indexOf(team);
		return teamsRecord[i][INDEXREMAIN];
	}
	public int against(String team1, String team2) {// number of remaining games between team1 and team2
		if (!teamsList.contains(team1))
			throw new IllegalArgumentException();
		if (!teamsList.contains(team2))
			throw new IllegalArgumentException();
		
		int i = teamsList.indexOf(team1);
		int j = teamsList.indexOf(team2);
		return teamsRecord[i][j + NUMRECORD];
	}
	
	//Draw by hand the FlowNetwork graph that you want to construct for a few small examples.
	//Write the code to construct the FlowNetwork, print it out using the toString() method, 
	//and and make sure that it matches your intent. Do not continue until you have thoroughly tested this stage.
	//Setup FlowNetwork for team. so the Graphic exclude the team.
	// 0 is s, 1 is t
	//team vertices from 2~ N  (i+2)
	//against: N + M
	private FlowNetwork setupFlowNetwork(String team) {
		int v;
		FlowNetwork G;
		v = (numberOfTeams() - 1) * (numberOfTeams() - 2) / 2 + (numberOfTeams() - 1) + 2;
		G = new FlowNetwork(v);
		
		int indexTeam = teamsList.indexOf(team);
		
		int num = numberOfTeams() + 1;
		for(int i = 0; i < numberOfTeams(); i++) {
			//Add edge from s to game left
			
			if (i == indexTeam) continue;  //skip the team
			//StdOut.println("indexTeam:");
			//StdOut.println(indexTeam);
			
			for (int j = i + 1; j < numberOfTeams(); j++) {
				if (j == indexTeam) continue;
				//if (i < indexTeam) {
				int numAgainst = against(teamsList.get(i), teamsList.get(j));
				//TODO: Shall add if numAgainst is zero?
				
				if (i < indexTeam)
					G.addEdge(new FlowEdge(num, i + 2, numAgainst));
				else
					G.addEdge(new FlowEdge(num, i + 1, numAgainst));
				if (j < indexTeam)
					G.addEdge(new FlowEdge(num, j + 2, numAgainst));
				else
					G.addEdge(new FlowEdge(num, j + 1, numAgainst));
					
				G.addEdge(new FlowEdge(0, num++, numAgainst));
				//StdOut.printf("%d, %d, %d \n", i, j, num);
			}
			
			//Add adge from team to t
			int score;
			
			int maxScore = wins(team) + remaining(team);
			if (wins(teamsList.get(i)) + remaining(teamsList.get(i)) - teamsRecord[indexTeam][i + NUMRECORD] <= maxScore) {
				score = remaining(teamsList.get(i)) - teamsRecord[indexTeam][i + NUMRECORD];
			} else {
				score = maxScore - wins(teamsList.get(i));
			}
			
			//StdOut.println(i + 2);
			if (i < indexTeam)
				G.addEdge(new FlowEdge(i + 2, 1, score));
			else
				G.addEdge(new FlowEdge(i + 1, 1, score));
		}	
		//StdOut.println(G);
		return G;
	}
	
	
	
	public boolean isEliminated(String team) {// is given team eliminated?

		//here check if maxScore of the team is less than other team's win score.
		//then return true directly.
		if (!teamsList.contains(team))
			throw new IllegalArgumentException();
		
		int maxScore = wins(team) + remaining(team);
		for(int i = 0; i < numberOfTeams(); i++) {
			if (i == teamsList.indexOf(team)) continue;  //skip the team
			if(maxScore < wins(teamsList.get(i))) { // is Eliminated
				return true;
			}
		}
		
		FlowNetwork G = setupFlowNetwork(team);
		//StdOut.println(G);
		FordFulkerson maxflow = new FordFulkerson(G, 0 ,1);


        // print min-cut
        //StdOut.print("Min cut: ");
        //for (int v = 0; v < G.V(); v++) {
        //    if (maxflow.inCut(v)) StdOut.print(v + " ");
        //}
        //StdOut.println();
        
        int c = 0;
        for (FlowEdge e : G.edges()) {
        	if (e.from() == 0) {
        		//StdOut.println(e);
        		c += e.capacity();
        	}
        }
        //StdOut.println("maxflow.value() and c");
        //StdOut.println(maxflow.value());
        //StdOut.println(c);
        if (c == maxflow.value())
        	return false;
        else
        	return true;

	}
	
	
	public Iterable<String> certificateOfElimination(String team) { // subset R of teams that eliminates given team; null if not eliminated
		//here check if maxScore of the team is less than other team's win score.
		//then return true directly.
		if (!teamsList.contains(team))
			throw new IllegalArgumentException();
		
		if(!isEliminated(team)) {
			return null;
		}
		
		HashSet<String> teamSet = new HashSet<String>();
		int maxScore = wins(team) + remaining(team);
		for(int i = 0; i < numberOfTeams(); i++) {
			if (i == teamsList.indexOf(team)) continue;  //skip the team
			if(maxScore < wins(teamsList.get(i))) { // is Eliminated
				//TODO: If find one team, immediately return. no chance to find another team.
				teamSet.add(teamsList.get(i));
				return teamSet;
			}
		}
		FlowNetwork G = setupFlowNetwork(team);
		//StdOut.println(G);
		FordFulkerson maxflow = new FordFulkerson(G, 0 ,1);
		
		
	     // print min-cut
        //StdOut.print("Min cut: ");
		//Here is accord the V to find out which team is certificateOfElimination
        for (int v = 0; v < G.V(); v++) {
            if (maxflow.inCut(v)) {
            	//StdOut.print(v + " ");
            	if (v > numberOfTeams()) {
            		for (FlowEdge e : G.edges()) {
                    	if (e.from() == v) {
                    		int teamIndex = e.to() - 2;
                    		if(teamIndex >= teamsList.indexOf(team))
                    			teamIndex++;
                    		teamSet.add(teamsList.get(teamIndex));
                    	}
                    }            		
            	}
            }
        }
        //StdOut.println();
		
		return teamSet;
	}
	
	//TODO: The last six methods should throw a java.lang.IllegalArgumentException 
	//if one (or both) of the input arguments are invalid teams. 
	
	public static void main(String[] args) {
	    BaseballElimination division = new BaseballElimination(args[0]);
	    
	    //FlowNetwork G = division.setupFlowNetwork("Philadelphia ");
	    //StdOut.println(G);
	    

	    //StdOut.println(division.isEliminated("Philadelphia"));
	    
	    for (String team : division.teams()) {
	        if (division.isEliminated(team)) {
	            StdOut.print(team + " is eliminated by the subset R = { ");
	            for (String t : division.certificateOfElimination(team))
	                StdOut.print(t + " ");
	            StdOut.println("}");
	        }
	        else {
	            StdOut.println(team + " is not eliminated");
	        }
	    }
	}
}