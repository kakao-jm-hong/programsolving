package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class B10021 {
	static int N, W;

	static class XY {
		int x;
		int y;

		public XY(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	static class Edge implements Comparable<Edge> {
		int a;
		int b;
		int w;

		public Edge(int a, int b, int w) {
			super();
			this.a = a;
			this.b = b;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return this.w - o.w;
		}

	}

	static ArrayList<Edge> edge;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());

		XY node[] = new XY[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			node[i] = new XY(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}

		edge = new ArrayList<Edge>();

		for (int i = 0; i < N - 1; i++) {
			for (int j = i + 1; j < N; j++) {
				int a = Math.abs(node[i].x - node[j].x);
				int b = Math.abs(node[i].y - node[j].y);

				int w = a * a + b * b;
				if (w < W)
					continue;

				edge.add(new Edge(i, j, w));
			}
		}

		Collections.sort(edge);

		parent = new int[N];
		for (int i = 0; i < N; i++)
			parent[i] = i;

		int answer = 0;
		int cnt = 0;

		for (int i = 0; cnt != N - 1 && i < edge.size(); i++) {
			Edge cur = edge.get(i);
			if (Union(cur.a, cur.b)) {
				answer += cur.w;
				cnt++;
			}
		}
		if (cnt != N - 1)
			System.out.print(-1);
		else
			System.out.println(answer);
	}

	static int parent[];

	static int Find(int x) {
		if (parent[x] == x)
			return x;

		return parent[x] = Find(parent[x]);
	}

	static boolean Union(int x, int y) {
		x = Find(x);
		y = Find(y);
		if (x == y)
			return false;

		parent[x] = y;
		return true;
	}
}
