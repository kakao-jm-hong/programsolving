package 연습;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 항공권 {

	public static void main(String[] args) {
		String tickets[][] = { { "ICN", "SFO" }, { "ICN", "ATL" }, { "SFO", "ATL" }, { "ATL", "ICN" },
				{ "ATL", "SFO" } };
		String[] answer = new 항공권().solution(tickets);
		for (String a : answer) {
			System.out.print(a + " ");
		}
	}

	static Map<String, Integer> map = new HashMap<>();
	static Map<String, Integer> air = new HashMap<>();
	static List<String> Edge[];
	static int endCount;
	static String[] answer;
	static int Mycount;

	public String[] solution(String[][] tickets) {
		Mycount = 0;
		endCount = tickets.length + 1;

		for (String[] ticket : tickets) {
			for (String t : ticket) {
				if (map.containsKey(t))
					continue;
				map.put(t, Mycount++);
			}
		}

		Edge = new ArrayList[Mycount];
		for (int i = 0; i < Mycount; i++) {
			Edge[i] = new ArrayList<String>();
		}

		for (String[] ticket : tickets) {
			air.put(ticket[0] + "_" + ticket[1], air.getOrDefault(ticket[0] + "_" + ticket[1], 0) + 1);
			Edge[map.get(ticket[0])].add(ticket[1]);

		}

		answer = new String[endCount];
		String[] curList = new String[endCount];
		curList[0] = "ICN";

		DFS("ICN", 1, curList);

		return answer;
	}

	private void DFS(String node, int count, String[] curList) {
		int cur = map.get(node);

		if (count == endCount) {
			// 비교알고리즘.
			if (Compare(curList)) {
				for (int i = 0; i < endCount; i++) {
					answer[i] = curList[i];
				}
			}
			return;
		}

		for (String next : Edge[cur]) {
			String newString = node + "_" + next;
			if (air.get(newString) == 0)
				continue;
			air.put(newString, air.get(newString) - 1);
			curList[count] = next;
			DFS(next, count + 1, curList);
			air.put(newString, air.get(newString) + 1);
		}
	}

	private boolean Compare(String[] curList) {
		if (answer[0] == null)
			return true;

		for (int i = 0; i < endCount; i++) {

			if (answer[i].compareTo(curList[i]) == 0)
				continue;
			if (answer[i].compareTo(curList[i]) < 0)
				return false;
			else
				return true;
		}

		return false;
	}
}
