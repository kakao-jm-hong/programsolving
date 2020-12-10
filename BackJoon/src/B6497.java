import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class B6497 {
	static int N, M;

	static class Edge implements Comparable<Edge> {
		int x;
		int y;
		int w;

		public Edge(int x, int y, int w) {
			super();
			this.x = x;
			this.y = y;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return w - o.w;
		}
	}

	static Edge ver[];

	static int parrent[];

	static int Find(int x) {
		if (parrent[x] == x)
			return x;

		int xx = Find(parrent[x]);
		return parrent[x] = xx;
	}

	static boolean Union(int x, int y) {
		x = Find(x);
		y = Find(y);

		if (x == y)
			return false;

		parrent[x] = y;

		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		while (true) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			if (N == 0 && M == 0)
				break;

			parrent = new int[N + 1];
			ver = new Edge[M];
			for (int n = 1; n <= N; n++)
				parrent[n] = n;

			int allWeight = 0;
			for (int m = 0; m < M; m++) {

				st = new StringTokenizer(br.readLine());

				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());

				allWeight += w;
				ver[m] = new Edge(x, y, w);
			}

			Arrays.sort(ver);
			int cnt = 0;
			int weight = 0;
			for (int m = 0; m < M && cnt != N - 1; m++) {

				if (Union(ver[m].x, ver[m].y)) {
					cnt++;
					weight += ver[m].w;
				}
			}
			sb.append(allWeight - weight).append('\n');
		}
		System.out.println(sb.toString());
	}
}
