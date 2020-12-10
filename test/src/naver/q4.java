package naver;

import java.util.LinkedList;
import java.util.Queue;

public class q4 {
	public static void main(String[] args) {
		int A[][] = { { 5, 4, 4 }, { 4, 3, 4 }, { 3, 2, 4 }, { 2, 2, 2 }, { 3, 3, 4 }, { 1, 4, 4 }, { 4, 1, 1 } };
		System.out.println(solution(A));
	}

	static int N, M;

	static public int solution(int[][] A) {
		int answer = 0;
		N = A.length;
		M = A[0].length;
		boolean visit[][] = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (visit[i][j])
					continue;
				BFS(i, j, A, visit);
				answer++;
			}
		}
		return answer;
	}

	static int dy[] = { 1, -1, 0, 0 };
	static int dx[] = { 0, 0, 1, -1 };

	static class Pair {
		int y;
		int x;

		public Pair(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}

	static public void BFS(int i, int j, int A[][], boolean[][] visit) {

		int target = A[i][j];
		Queue<Pair> q = new LinkedList<>();

		visit[i][j] = true;
		q.add(new Pair(i, j));

		while (!q.isEmpty()) {
			Pair cur = q.poll();

			for (int d = 0; d < 4; d++) {
				int ny = cur.y + dy[d];
				int nx = cur.x + dx[d];

				if (ny < 0 || nx < 0 || ny >= N || nx >= M)
					continue;
				if (visit[ny][nx])
					continue;
				if (target != A[ny][nx])
					continue;
				visit[ny][nx] = true;
				q.add(new Pair(ny, nx));
			}
		}
	}
}
