package 연습;

public class lulu {

	public static void main(String[] args) {
		System.out.println(new lulu().solution("ababbbabbb"));
	}

	public int solution(String S) {
		// write your code in Java SE 8

		int countA = 0;
		for (int i = 0; i < S.length(); i++) {
			if (S.charAt(i) == 'a')
				countA++;
		}
		if (countA % 3 != 0)
			return 0;
		else if (countA == 0) {
			return S.length()+1;
		}

		int mod = countA / 3;
		int answer = 0;

		int left[] = new int[2];
		int right[] = new int[2];

		left[0] = getLocateA(S, 0, mod);
		left[1] = getFirstA(S, left[0] + 1);
		right[0] = getLocateA(S, left[1] + 1, mod);
		right[1] = getFirstA(S, right[0] + 1);

		answer = (int) (Math.pow(2, left[1] - left[0]) * Math.pow(2, right[1] - right[0]));
		return answer;
	}

	private int getFirstA(String S, int start) {

		for (int i = start; i < S.length(); i++) {
			if (S.charAt(i) == 'a') {
				return i - 1;
			}
		}
		return -1;
	}

	private int getLocateA(String S, int start, int mod) {
		int count = 0;

		for (int i = start; i < S.length(); i++) {
			if (S.charAt(i) == 'a') {
				count++;
			}
			if (count == mod) {
				return i;
			}
		}
		return -1;
	}
}
