package h;

import java.util.Arrays;

public class a {

	public static void main(String[] args) {
		int rows = 2;
		int columns = 4;

		int[][] swipes = { { 3, 1, 2, 2, 4 }, { 3, 1, 2, 2, 4 }, { 4, 2, 1, 2, 3 }, { 1, 1, 1, 2, 3 } };
		System.out.println(Arrays.toString(new a().solution(rows, columns, swipes)));
	}

	static int N;
	static int M;
	static int Map[][];

	public int[] solution(int rows, int columns, int[][] swipes) {
		N = rows;
		M = columns;
		Map = new int[N][M];
		int n = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				Map[i][j] = n++;
			}
		}
		int answer[] = new int[swipes.length];

		for (int i = 0; i < swipes.length; i++) {
			int d = swipes[i][0];
			int r1 = swipes[i][1] - 1;
			int c1 = swipes[i][2] - 1;
			int r2 = swipes[i][3] - 1;
			int c2 = swipes[i][4] - 1;

			answer[i] = Solve(d, r1, c1, r2, c2);
		}
		return answer;
	}

	static int dy[] = { 0, 1, -1, 0, 0 };
	static int dx[] = { 0, 0, 0, 1, -1 };

	public int Solve(int d, int r1, int c1, int r2, int c2) {
		int sum = 0;

		if (d == 1) {
			for (int c = c1; c <= c2; c++)
				sum += Map[r2][c];
		} else if (d == 2) {
			for (int c = c1; c <= c2; c++)
				sum += Map[r1][c];
		} else if (d == 3) {
			for (int r = r1; r <= r2; r++)
				sum += Map[r][c2];
		} else if (d == 4) {
			for (int r = r1; r <= r2; r++)
				sum += Map[r][c1];
		}

		int MoveMap[][] = Move(d, r1, c1, r2, c2, Map);

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (r1 <= i && i <= r2 && c1 <= j && j <= c2) {
					Map[i][j] = MoveMap[i][j];
				}
			}
		}
		return sum;
	}

	private int[][] Move(int d, int r1, int c1, int r2, int c2, int[][] map) {
		int m[][] = new int[N][M];
		for (int i = 0; i < N; i++)
			m[i] = map[i].clone();

		for (int i = r1; i <= r2; i++) {
			for (int j = c1; j <= c2; j++) {
				int y = i + dy[d];
				int x = j + dx[d];
				if (y > r2 || y < r1 || x > c2 || x < c1) {
					if (d == 1) {
						m[r1][j] = map[i][j];
					} else if (d == 2) {
						m[r2][j] = map[i][j];
					} else if (d == 3) {
						m[i][c1] = map[i][j];
					} else if (d == 4) {
						m[i][c2] = map[i][j];
					}
				} else {
					m[y][x] = map[i][j];
				}
			}
		}

		return m;
	}
}
