package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj14466 {
	static int N, K, R;
	static boolean edge[][];
	static boolean cow[][];

	static class Edge implements Comparable<Edge> {
		int y;
		int x;
		int w;

		public Edge(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}

		public Edge(int y, int x, int w) {
			super();
			this.y = y;
			this.x = x;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return this.w - o.w;
		}
	}

	static int dy[] = { -1, 0, 1, 0 };
	static int dx[] = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		edge = new boolean[100001][4];
		cow = new boolean[N][N];

		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			int y1 = Integer.parseInt(st.nextToken()) - 1;
			int x1 = Integer.parseInt(st.nextToken()) - 1;
			int y2 = Integer.parseInt(st.nextToken()) - 1;
			int x2 = Integer.parseInt(st.nextToken()) - 1;
			int num1 = y1 * N + x1;
			int num2 = y2 * N + x2;

			// 0 1 2 3 위 오른쪽 아래 왼쪽

			// 1을 기준으로 설계
			// 오른쪽
			if (y1 == y2 && x1 < x2) {
				edge[num1][1] = edge[num2][3] = true;
			}
			// 왼쪽
			else if (y1 == y2 && x1 > x2) {
				edge[num1][3] = edge[num2][1] = true;
			}
			// 위쪽
			else if (y1 > y2 && x1 == x2) {
				edge[num1][0] = edge[num2][2] = true;
			}
			// 아래쪽
			else {
				edge[num1][2] = edge[num2][0] = true;
			}
		}

		list = new LinkedList<>();
		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken()) - 1;
			int x = Integer.parseInt(st.nextToken()) - 1;
			list.add(new Edge(y, x));
		}

		System.out.println(Solve());
	}

	static List<Edge> list;

	private static int Solve() {
		int cnt = 0;
		for (Edge cur : list) {
			cnt += Dickstra(cur.y, cur.x);
		}
		return cnt / 2;
	}

	static final int INF = 987654321;

	private static int Dickstra(int y, int x) {
		int[][] digit = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				digit[i][j] = INF;
				digit[j][i] = INF;
			}
		}

		digit[y][x] = 0;
		Queue<Edge> q = new PriorityQueue<>();
		q.add(new Edge(y, x, 0));
		while (!q.isEmpty()) {
			Edge cur = q.poll();
			for (int d = 0; d < 4; d++) {
				int ny = cur.y + dy[d];
				int nx = cur.x + dx[d];

				if (ny < 0 || nx < 0 || nx >= N || ny >= N)
					continue;

				int num = cur.y * N + cur.x;
				// 다리있으면
				int weight = 0;

				if (edge[num][d]) {
					weight = 1;
				}

				if (digit[cur.y][cur.x] + weight < digit[ny][nx]) {
					int nw = digit[ny][nx] = digit[cur.y][cur.x] + weight;
					q.add(new Edge(ny, nx, nw));
				}
			}
		}

		int cnt = 0;
		for (Edge l : list) {
			if (digit[l.y][l.x] != 0)
				cnt++;
		}
		return cnt;
	}
}
