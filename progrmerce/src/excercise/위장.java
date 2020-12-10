package excercise;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class 위장 {
	public int solution(String[][] clothes) {

		Map<String, Integer> hash = new HashMap<String, Integer>();
		for (int i = 0; i < clothes.length; i++) {
			String kind = clothes[i][1];

			hash.put(kind, hash.getOrDefault(kind, 0) + 1);
		}

		int answer = 1;

		Set<String> keys = hash.keySet();

		for (String key : keys) {
			answer *= hash.get(key) + 1;
		}

		return answer - 1;
	}
}
