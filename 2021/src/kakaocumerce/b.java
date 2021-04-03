package kakaocumerce;

public class b {

	public int solution(int[][] needs, int r) {
		len = 15;
		answer = 0;
		check = new boolean[15];
		Solve(0, r, needs);

		return answer;
	}

	private void Solve(int i, int r, int[][] needs) {
		if (r == 0) {
			int cnt = 0;
			for (int[] need : needs) {
				boolean isPossible = true;
				for (int j = 0; j < need.length; j++) {
					if (need[j] == 1 && !check[j]) {
						isPossible = false;
						break;
					}
				}
				if (isPossible) {
					cnt++;
				}
			}
			answer = Math.max(answer, cnt);

			return;
		} else if (i >= len) {
			return;
		}
		// 선택
		check[i] = true;
		Solve(i + 1, r - 1, needs);
		check[i] = false;
		// 비선택
		Solve(i + 1, r, needs);

	}

	static int answer = 0;
	static int len;
	static boolean check[];
}
