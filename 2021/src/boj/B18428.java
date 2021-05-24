package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B18428 {
	static int N;

	static class Pair {
		int y;
		int x;

		public Pair(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}

	static ArrayList<Pair> teachers;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		char[][] map = new char[N][N];
		teachers = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = st.nextToken().charAt(0);

				if (map[i][j] == 'T') {
					teachers.add(new Pair(i, j));
				}
			}
		}
		boolean answer = DFS(0, 0, map);
		System.out.println(answer ? "YES" : "NO");

	}

	private static boolean DFS(int n, int cnt, char[][] map) {
		if (n == N * N)
			return false;
		if (cnt == 3 && isPossible(map)) {
			return true;
		} 
		
		if (cnt == 3) {
			return false;
		}

		int y = n / N;
		int x = n % N;

		// O 설치
		if (map[y][x] == 'X') {
			map[y][x] = 'O';
			if (DFS(n + 1, cnt + 1, map)) {
				return true;
			}
			map[y][x] = 'X';

			// 넘어가기
			if (DFS(n + 1, cnt, map)) {
				return true;
			}
		} else {
			if (DFS(n + 1, cnt, map))
				return true;
		}

		return false;
	}

	static int dy[] = { 1, -1, 0, 0 };
	static int dx[] = { 0, 0, 1, -1 };

	private static boolean isPossible(char[][] map) {
		// teachsrs 를 순회하며 S가 나오면 false을 반환 하고 모든teachres를 순회해도 그렇 않을 경우 true 반

		for (Pair t : teachers) {
			// 선생님이 발견하면 true
			if (Check(map, t)) {
				return false;
			}
		}
		return true;
	}

	private static boolean Check(char[][] map, Pair t) {
		for (int d = 0; d < 4; d++) {
			int ny = t.y;
			int nx = t.x;
			while (true) {
				ny = ny + dy[d];
				nx = nx + dx[d];
				if (ny < 0 || nx < 0 || ny >= N || nx >= N)
					break;
				if (map[ny][nx] == 'O' || map[ny][nx] == 'T')
					break;
				if (map[ny][nx] == 'S')
					return true;
			}
		}
		return false;
	}
}
