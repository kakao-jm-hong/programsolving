package naver;

import java.util.Arrays;

public class demo {
	public int solution(int[] A) {
		Arrays.sort(A);

		int target = 1;

		for (int i = 0; i < A.length; i++) {
			if (A[i] <= 0)
				continue;

			if (A[i] == target) {
				int j;
				for (j = i; j < A.length && A[j] == target; j++) {
				}
				i = j - 1;
				target++;
			} else {
				return target;
			}
		}
		if ((A[A.length - 1] + 1) < 1) {
			return 1;
		}
		return A[A.length - 1] + 1;
	}
}
