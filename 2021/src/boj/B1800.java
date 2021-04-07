package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

import boj.B1800.Edge;

public class B1800 {
	static class Edge implements Comparable<Edge> {
		int v;
		int w;

		public Edge(int v, int w) {
			super();
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return this.w - o.w;
		}

	}

	static int N, P, K;

	static ArrayList<Edge>[] edge;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		edge = new ArrayList[N + 1];
		for (int n = 1; n <= N; n++)
			edge[n] = new ArrayList<>();

		for (int p = 0; p < P; p++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			edge[a].add(new Edge(b, w));
			edge[b].add(new Edge(a, w));
		}

		// Dickstra
		int front = 0, rear = INF;
		int ans = -1;
		while (front <= rear) {
			int mid = (front + rear) / 2;
			if (Dickstra(mid)) {
				ans = mid;
				rear = mid - 1;
			} else {
				front = mid + 1;
			}
		}

		// print
		System.out.println(ans);
	}

	private static boolean Dickstra(int x) {
		// 초기화
		inti();

		// 시작지점 1번
		Queue<Edge> q = new PriorityQueue<Edge>();
		q.add(new Edge(1, 0));

		// 다잌스트라 탐색
		while (!q.isEmpty()) {
			Edge cur = q.poll();

			for (Edge next : edge[cur.v]) {
				int w = next.w <= x ? 0 : 1;
				if (dist[next.v] > cur.w + w) {
					dist[next.v] = cur.w + w;
					q.add(new Edge(next.v, cur.w + w));
				}
			}
		}

		return dist[N] <= K;

	}

	static int dist[];
	static final int INF = 987654321;

	private static void inti() {
		dist = new int[N + 1];
		for (int i = 2; i <= N; i++) {
			dist[i] = INF;
		}

	}

}