package sixshop;

public class A {
	public static void main(String[] args) {
		String s1 = "AxA";
		String s2 = "AyA";
		System.out.println(new A().solution(s1, s2));
	}
	public String solution(String s1, String s2) {
		String answer = "";

		int size = Math.min(s1.length(), s2.length());

		/*
		 * s1 s2 순서
		 */
		String answer1 = Solve(s1, s2, size);
		String answer2 = Solve(s2, s1, size);

		if (answer1.length() == answer2.length()) {
			if (answer1.compareTo(answer2) > 0) {
				answer = answer2;
			} else {
				answer = answer1;
			}
		} else if (answer1.length() > answer2.length()) {
			answer = answer2;
		} else {
			answer = answer1;
		}

		return answer;
	}

	private String Solve(String s1, String s2, int size) {
		int cnt = 0;
		for (int i = 0; i < size; i++) {
			boolean check = true;
			for (int j = s1.length() - 1 - i, k = 0; j < s1.length(); j++, k++) {
				if (s1.charAt(j) != s2.charAt(k)) {
					check = false;
					break;
				}
			}
			if (check) {
				cnt = i+1;
			}
		}
		return s1 + s2.substring(cnt, s2.length());
	}
}
