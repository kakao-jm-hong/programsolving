package sixshop;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class B {
	public static void main(String[] args) {
		int grade[] = {3,2,1,2};
		System.out.println(new B().solution(grade));
	}
	
	public int[] solution(int[] grade) {
		Integer g[] = Arrays.stream(grade).boxed().toArray(Integer[]::new);
		int size = grade.length;
		int[] answer = new int[size];
		Arrays.sort(g, Collections.reverseOrder());
		HashMap<Integer, Integer> hash = new HashMap<Integer, Integer>();

		int tmp = -1;
		int rank = 0;
		for (int i = 0; i < size; i++) {
			int cur = g[i];
			if (tmp != cur) {
				rank = i + 1;
				tmp = cur;
				hash.put(tmp, rank);
			}
		}
		
		for(int i=0;i<size;i++) {
			answer[i] = hash.get(grade[i]);
		}
		return answer;
	}
}
