package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B15581 {

	static class Edge {
		int v;
		int w;

		public Edge(int v, int w) {
			super();
			this.v = v;
			this.w = w;
		}

	}

	static ArrayList<Edge>[] edge;
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());

		edge = new ArrayList[N + 1];

		for (int n = 1; n <= N; n++) {
			edge[n] = new ArrayList<>();
		}
		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			edge[a].add(new Edge(b, w));
			edge[b].add(new Edge(a, w));
		}

		StringBuilder sb = new StringBuilder();
		for (int q = 0; q < Q; q++) {
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			sb.append(Solve(k, v)).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static Object Solve(int k, int v) {
		int cnt = 0;
		boolean visit[] = new boolean[N + 1];
		Queue<Integer> q = new LinkedList<>();
		q.add(v);
		visit[v] = true;
		while (!q.isEmpty()) {
			int cur = q.poll();

			for (Edge next : edge[cur]) {
				if (visit[next.v])
					continue;
				if (k > next.w)
					continue;
				visit[next.v] = true;
				cnt++;
				q.add(next.v);
			}
		}
		return cnt;
	}
}
