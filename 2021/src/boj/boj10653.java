package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj10653 {
	static int N, K;
	static int vertex[][];
	static int distance[][];
	static int dp[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		vertex = new int[N + 1][2];
		dp = new int[N + 1][K + 1];
		distance = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			vertex[i][0] = x;
			vertex[i][1] = y;
			Arrays.fill(dp[i], -1);
		}

		for (int i = 1; i < N; i++) {
			for (int j = i + 1; j <= N; j++) {
				distance[i][j] = getDistance(vertex[i][0], vertex[i][1], vertex[j][0], vertex[j][1]);
			}
		}
		// 현재 N번째를 순회햇을 때 K개의 수만큼 무시하고 왓을 때 최소의 거리 출력
		System.out.println(getDp(N, K));
	}

	private static int getDp(int n, int k) {
		if (dp[n][k] != -1)
			return dp[n][k];
		if (n == 1)
			return 0;
		int ans = Integer.MAX_VALUE;
		for (int i = 0; i <= k; i++) {
			if (1 <= n - 1 - i)
				ans = Math.min(ans, getDp(n - i - 1, k - i) + distance[n - i - 1][n]);
		}
		return dp[n][k] = ans;
	}

	static int getDistance(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}
}
