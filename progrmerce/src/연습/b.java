package 연습;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class b {

	public static void main(String[] args) {
		String[] orders = { "ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH" };
		int[] courcse = { 2, 3, 4 };

		System.out.println(new b().solution(orders, courcse)[1]);
	}

	static HashMap<String, Integer> map = new HashMap<>();

	public String[] solution(String[] orders, int[] course) {

		for (String order : orders) {
			String[] str = order.split("");
			Arrays.sort(str);
			for (int value : course) {
				DFS(0, value, str, 0, order.length(), 0);
			}
		}

		int[] max_value = new int[11];

		for (String key : map.keySet()) {
			max_value[key.length()] = Math.max(max_value[key.length()], map.get(key));
		}

		ArrayList<String> list = new ArrayList<>();

		for (String key : map.keySet()) {
			for (int value : course) {
				if (key.length() == value && map.get(key) == max_value[key.length()] && map.get(key) >= 2) {
					list.add(key);
				}
			}
		}

		String[] answer = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			answer[i] = list.get(i);
		}
		Arrays.sort(answer);
		return answer;
	}

	// 해시맵 저장.
	private void DFS(int flag, int course, String[] str, int count, int len, int cur) {
		if (count == course) {
			String answer = "";
			for (int i = 0; i < len; i++) {
				if ((1 << i) == (flag & (1 << i))) {
					answer += str[i].charAt(0);
				}
			}
			if (map.containsKey(answer)) {
				map.put(answer, map.get(answer) + 1);
			} else {
				map.put(answer, 1);
			}

			return;
		}

		if (cur == len)
			return;

		// 선택하지 않음
		DFS(flag, course, str, count, len, cur + 1);

		// 선택
		DFS((flag) | (1 << cur), course, str, count + 1, len, cur + 1);
	}
}
