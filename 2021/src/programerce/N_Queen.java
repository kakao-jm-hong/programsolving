package programerce;

public class N_Queen {
	public static void main(String[] args) {

	}

	static int N;
	static int answer;
	int row[] = new int[16];

	public int solution(int n) {
		N = n;
		answer = 0;

		for (int i = 1; i <= n; i++) {
			row[1] = i;
			DFS(1);
		}

		return answer;
	}

	void DFS(int now) {
		if (now == N) {
			++answer;
			return;
		}

		for (int i = 1; i <= N; i++) {
			row[now + 1] = i;
			if (Check(now + 1)) {
				DFS(now + 1);
			}
		}
		row[now + 1] = 0;
	}

	boolean Check(int now) {
		for (int i = 1; i < now; i++) {
			// 세로
			if (row[i] == row[now])
				return false;
			// 대각선
			if (Math.abs(row[i] - row[now]) == Math.abs(i - now))
				return false;
		}
		return true;
	}
}
