package h;

import java.util.Arrays;

public class c {

	public static void main(String[] args) {
		String s1 = "ppwwwbpbbwwbw";
		String s2 = "pwbwpwwbw";

		System.out.println(new c().solution(s1, s2));
	}

	public int solution(String s1, String s2) {
		int answer = 0;

		boolean map1[][] = new boolean[32][32];
		boolean map2[][] = new boolean[32][32];

		dfs(0, 0, 31, 31, s1, 0, map1);
		dfs(0, 0, 31, 31, s2, 0, map2);

		for (int i = 0; i < 32; i++) {
			for (int j = 0; j < 32; j++) {
				if (map1[i][j] || map2[i][j])
					answer++;
			}
		}

		return answer;

	}

	public int dfs(int y1, int x1, int y2, int x2, String s, int n, boolean map[][]) {
		int next = n + 1;
		if (s.charAt(n) == 'p') {
			int midy = (y1 + y2) / 2;
			int midx = (x1 + x2) / 2;
			next = dfs(y1, midx + 1, midy, x2, s, next, map);
			next = dfs(y1, x1, midy, midx, s, next, map);
			next = dfs(midy + 1, x1, y2, midx, s, next, map);
			next = dfs(midy + 1, midx + 1, y2, x2, s, next, map);
		} else if (s.charAt(n) == 'b') {
			for (int i = y1; i <= y2; i++) {
				for (int j = x1; j <= x2; j++) {
					map[i][j] = true;
				}
			}
		} else {
			for (int i = y1; i <= y2; i++) {
				for (int j = x1; j <= x2; j++) {
					map[i][j] = false;
				}
			}
		}
		return next;
	}
}
