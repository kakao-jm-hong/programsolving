package nt;

public class b {
	public int solution(int n) {

		int len = n / 5;
		int answer = -1;
		for (int i = len; i >= 0; i--) {
			int five = i;
			if ((n - 5 * i) % 3 == 0) {
				int three = (n - 5 * i) / 3;
				answer = five + three;
				break;
			}
		}
		return answer;
	}
}
