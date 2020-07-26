package naver;

public class q1 {
	public static void main(String[] args) {
		int A[] = { -1, 1, 3, 3, 3, 2, 3, 2, 1, 0 };
		System.out.println(solution(A));
	}

	static public int solution(int[] A) {
		int answer = 0;
		int[] periods = new int[A.length - 1];
		for (int i = 1; i < A.length; i++) {
			periods[i - 1] = A[i] - A[i - 1];
		}

		for (int i = 0; i < periods.length - 1; i++) {
			for (int j = i + 1; j < periods.length&&periods[i] == periods[j]; j++) {
				answer++;
			}
		}

		if (answer > 1000000000)
			return -1;
		return answer;
	}
}
