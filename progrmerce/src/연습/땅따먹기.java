package 연습;

import java.util.Arrays;

public class 땅따먹기 {
	static int n, m;
	static int answer;
	static int dp[][];

	int solution(int[][] land) {
		answer = 0;
		n = land.length;
		m = 4;
		dp = new int[n][m];
		for (int i = 0; i < n; i++)
			Arrays.fill(dp[i], -1);
		for (int i = 0; i < 4; i++) {
			dp[0][i] = DFS(0, i, land);
		}

		answer = Integer.MIN_VALUE;
		for (int i = 0; i < 4; i++)
			answer = Math.max(answer, dp[0][i]);
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<4;j++) {
				System.out.print(dp[i][j] +" ");
			}
			System.out.println();
		}
		return answer;
	}

	private int DFS(int deep, int ver, int[][] land) {
		if (deep == n)
			return 0;
		if (dp[deep][ver] != -1)
			return dp[deep][ver];
		int tmp = Integer.MIN_VALUE;

		for (int i = 0; i < 4; i++) {
			if (i == ver)
				continue;
			tmp = Math.max(tmp, DFS(deep + 1, i, land));
		}

		return dp[deep][ver] = land[deep][ver] + tmp;
	}
}
