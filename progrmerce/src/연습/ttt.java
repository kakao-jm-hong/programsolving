import java.util.Arrays;
import java.util.Comparator;

class Solution {
	static public String[] solution(String[][] tickets) {
		String[] answer = new String[tickets.length+1];
		Arrays.sort(tickets, new CMP());
		boolean[] check = new boolean[tickets.length];

		dfs("ICN", 0, tickets, check, answer);
		return answer;
	}

	static public boolean dfs(String location, int s, String[][] tickets, boolean[] check, String[] answer) {
		answer[s] = location;
		if (s == tickets.length) {
			return true;
		}
		StringBuilder
		for (int i = 0; i < tickets.length; i++) {
			if (check[i])
				continue;

			if (location.equals(tickets[i][0])) {
				check[i] = true;
				
				if (dfs(tickets[i][1], s + 1, tickets, check, answer))
					return true;
				
				check[i] = false;
			}
		}

		return false;
	}

	static public class CMP implements Comparator<String[]> {

		@Override
		public int compare(String[] o1, String[] o2) {
            			if(o1[0].equals(o2[0])) {
				return o1[1].compareTo(o2[1]);
			}
			return o1[0].compareTo(o2[0]);
		}

	}
}