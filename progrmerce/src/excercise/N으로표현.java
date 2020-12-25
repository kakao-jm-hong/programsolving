package excercise;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class N으로표현 {
	static final int INF = 987654321;
	static Set<Integer> dp[] = new TreeSet[9];

	public int solution(int N, int number) {
		for (int i = 1; i <= 8; i++) {
			dp[i] = new TreeSet<Integer>();
		}

		int tmp = 0;
		for (int i = 1; i <= 8; i++) {
			tmp *= 10;
			tmp += N;
			dp[i].add(tmp);
		}

		dp[2].add(N + N);
		dp[2].add(N * N);
		dp[2].add(N / N);
		dp[2].add(N - N);

		for (int i = 3; i <= 8; i++) {
			for (int j = 1; j < i; j++) {
				for (Iterator<Integer> left = dp[j].iterator(); left.hasNext();) {
					int l = left.next();
					for (Iterator<Integer> right = dp[i - j].iterator(); right.hasNext();) {
						int r = right.next();
						dp[i].add(l + r);
						dp[i].add(l - r);
						if (r != 0)
							dp[i].add(l / r);
						dp[i].add(l * r);
					}
				}
			}
		}

		for (int i = 1; i <= 8; i++) {
			for (Iterator<Integer> it = dp[i].iterator(); it.hasNext();) {
				if (number == it.next()) {
					return i;
				}
			}
		}

		return -1;
	}
}
