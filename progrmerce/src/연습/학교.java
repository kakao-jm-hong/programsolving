package 연습;

import java.util.Arrays;

public class 학교 {
	static int dp[][];
	static int N, M;

	public int solution(int m, int n, int[][] puddles) {
		dp = new int[n + 1][m + 1];
		N = n;
		M = m;
		for (int i = 1; i <= n; i++) {
			Arrays.fill(dp[i], -1);
		}
		for (int[] puddle : puddles) {
			dp[puddle[1]][puddle[0]] = 0;
		}
		dp[n][m] = 1;
		int answer = DFS(1, 1);
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=m;j++) {
				System.out.print(dp[i][j]+" ");
			}
			System.out.println();
		}
		return answer;
	}

	private int DFS(int i, int j) {
		if (N == i && M == j)
			return 1;
		if (N < i || M < j)
			return 0;
		if (dp[i][j] != -1) {
			return dp[i][j];
		}

		int down = DFS(i + 1, j);
		int right = DFS(i, j + 1);

		return dp[i][j] = (down + right) % 1000000007;
	}
}
