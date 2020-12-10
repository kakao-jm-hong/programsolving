package naver;

import java.util.Arrays;

public class q2 {
	public static void main(String[] args) {
		System.out.println(solution("azABaabza"));
	}

	static public int solution(String S) {
		int answer = Integer.MAX_VALUE;

		boolean[] upper = new boolean[26];
		boolean[] lower = new boolean[26];

		for (int i = 0; i < S.length() - 1; i++) {
			Arrays.fill(upper, false);
			Arrays.fill(lower, false);
			int cnt = 0;
			for (int j = i; j < S.length(); j++) {
				int target = S.charAt(j);
				if (target >= 97) {
					lower[target - 97] = true;
				} else {
					upper[target - 65] = true;

				}
				boolean check = true;
				
				for (int k = 0; k < 26; k++) {
					if (upper[k] != lower[k]) {
						check = false;
						break;
					}
				}
				if (check) {
					answer = Math.min(answer, j-i+1);
				}
			}
		}

		if (answer == Integer.MAX_VALUE)
			return -1;
		return answer;
	}
}
