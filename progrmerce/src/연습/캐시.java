package 연습;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class 캐시 {

	static List<String> cache = new LinkedList<>();
	static int maxSize;

	public int solution(int cacheSize, String[] cities) {
		int answer = 0;
		maxSize = cacheSize;

		for (String city : cities) {
			answer += Solve(city.toLowerCase());
		}

		return answer;
	}

	private int Solve(String city) {
		if(maxSize==0)
			return 5;
		
		for (Iterator<String> it = cache.iterator();it.hasNext();) {
			String cur = it.next();
			if(cur.equals(city)) {
				it.remove();
				cache.add(city);
				return 1;
			}
		}
		
		if(cache.size() < maxSize) {
			cache.add(city);
		}else {
			cache.remove(cache.get(0));
			cache.add(city);
		}

		return 5;
	}
}
