package 연습;

public class a {
	static boolean isPosible;

	public int solution(int m, int n, String[] board) {
		int answer = 0;
		isPosible = true;

		char map[][] = new char[m][n];
		for (int i = 0; i < m; i++) {
			map[i] = board[i].toCharArray();
		}

		while (isPosible) {
			int plus = funA(m, n, map);
			down(m, n, map);
			answer += plus;
			isPosible = plus == 0 ? false : true;
		}

		return answer;
	}

	private void down(int m, int n, char[][] map) {
		for (int j = 0; j < n; j++) {
			for (int i = m - 1; i >= 0; i--) {
				if (map[i][j] == '.')
					continue;
				char tmp = map[i][j];
				map[i][j] = '.';
				int next = i;
				while (next + 1 < m && map[next+1][j] == '.')
					next++;
				map[next][j] = tmp;
			}
		}

	}

	private int funA(int m, int n, char[][] map) {
		int cnt = 0;
		boolean check[][] = new boolean[m][n];
		for (int i = 0; i < m - 1; i++) {
			for (int j = 0; j < n - 1; j++) {
				if (map[i][j] == '.')
					continue;

				if (map[i][j] == map[i + 1][j] && map[i][j] == map[i][j + 1] && map[i][j] == map[i + 1][j + 1]) {
					check[i][j] = check[i + 1][j] = check[i + 1][j + 1] = check[i][j + 1] = true;
				}
			}
		}

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (check[i][j]) {
					map[i][j] = '.';
					cnt++;
				}
			}
		}

		return cnt;
	}
}
