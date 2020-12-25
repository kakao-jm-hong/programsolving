package excercise;

public class 정수삼각형 {
	public int solution(int[][] triangle) {
		int answer = 0;
		int N = triangle.length;

		int dp[][] = new int[N][N];
		for (int j = 0; j < N; j++) {
			dp[N - 1][j] = triangle[N - 1][j];
		}
		for (int i = N - 2; i >= 0; i--) {
			for (int j = 0; j < triangle[i].length; j++) {
				dp[i][j] = triangle[i][j] + Math.max(dp[i + 1][j + 1], dp[i + 1][j]);
			}
		}
		return dp[0][0];
	}
}
