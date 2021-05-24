package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj17182 {
	static int N, K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		disit = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				disit[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		System.out.println(solve());
	}

	static int disit[][];
	static int dp[][];
	static int ans;

	private static int solve() {
		init();
		DFS(1 << K, 0, 1, K);
		return ans;
	}

	private static void DFS(int flag, int distance, int cnt, int cur) {
		if (cnt == N) {
			ans = Math.min(ans, distance);
			return;
		}

		if (ans <= distance)
			return;

		for (int i = 0; i < N; i++) {
			if ((flag & (1 << i)) == (1 << i))
				continue;
			DFS(flag | (1 << i), distance + disit[cur][i], cnt + 1, i);

		}

	}

	static final int INF = 987654321;

	private static void init() {
		ans = INF;

		for (int n = 0; n < N; n++) {
			for (int i = 0; i < N; i++) {
				if (i == n) {
					continue;
				}
				for (int j = 0; j < N; j++) {
					// 자기자신 가는 경로는 0
					if (j == i || j == n)
						continue;
					disit[i][j] = Math.min(disit[i][j], disit[i][n] + disit[n][j]);
				}
			}
		}
	}
}
