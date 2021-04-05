package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj18500 {
	static int R, C;
	static int[][] Map;
	static int N;
	static int plays[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		Map = new int[R][C];
		for (int i = 0; i < R; i++) {
			char[] c = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				Map[i][j] = c[j] == '.' ? 0 : 1;
			}
		}

		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int n = 0; n < N; n++) {
			// left
			int target = R - Integer.parseInt(st.nextToken());
			int j;
			if (n % 2 == 0) {
				j = 0;
				while (j < C) {
					if (Map[target][j] != 0)
						break;
					j++;
				}
			}
			// right
			else {
				j = C - 1;
				while (j >= 0) {
					if (Map[target][j] != 0)
						break;
					j--;
				}
			}
			Map[target][j] = 0;
			process();
			down();
		}

		print();
	}

	private static void print() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				sb.append(Map[i][j] == 0 ? '.' : 'x');
			}
			sb.append('\n');
		}
		System.out.println(sb.toString());
	}

	private static void down() {
		boolean check[][] = new boolean[R][C];
		for (int i = R - 1; i >= 0; i--) {
			for (int j = 0; j < C; j++) {
				if (check[i][j])
					continue;
				if (Map[i][j] == 0)
					continue;

				int target = Map[i][j];
				ArrayList<Pair> list = getTargets(target);
				int cnt = 1;
				while (true) {
					if (!check(cnt, list)) {
						cnt--;
						break;
					}
					cnt++;
				}
				for (Pair l : list) {
					Map[l.y + cnt][l.x] = target;
					check[l.y + cnt][l.x] = true;
				}
			}
		}
	}

	private static boolean check(int cnt, ArrayList<Pair> list) {
		for (Pair l : list) {
			if (l.y + cnt >= R)
				return false;
			if (Map[l.y + cnt][l.x] != 0)
				return false;
		}
		return true;
	}

	private static ArrayList<Pair> getTargets(int target) {
		ArrayList<Pair> list = new ArrayList<Pair>();
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (Map[i][j] == target) {
					Map[i][j] = 0;
					list.add(new Pair(i, j));
				}
			}
		}
		return list;
	}

	static class Pair {
		int y;
		int x;

		public Pair(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}

	static int dy[] = { 1, -1, 0, 0 };
	static int dx[] = { 0, 0, 1, -1 };

	private static void process() {
		int cluste = 0;
		boolean check[][] = new boolean[R][C];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (check[i][j])
					continue;
				if (Map[i][j] == 0)
					continue;
				cluste++;
				Queue<Pair> q = new LinkedList<>();
				check[i][j] = true;
				q.add(new Pair(i, j));
				Map[i][j] = cluste;
				while (!q.isEmpty()) {
					Pair cur = q.poll();

					for (int d = 0; d < 4; d++) {
						int ny = cur.y + dy[d];
						int nx = cur.x + dx[d];
						if (ny < 0 || nx < 0 || ny >= R || nx >= C)
							continue;
						if (check[ny][nx])
							continue;
						if (Map[ny][nx] == 0)
							continue;
						Map[ny][nx] = cluste;
						check[ny][nx] = true;
						q.add(new Pair(ny, nx));
					}
				}
			}
		}

	}
}
