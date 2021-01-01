package jimssa;

public class A {
	public int solution(String s) {
		int alpha[] = new int[26];
		for (int i = 0; i < s.length(); i++) {
			int c = s.charAt(i) - 'a';
			alpha[c]++;
		}

		int answer = 0;
		for (int i = 0; i < 26; i++) {
			if (alpha[i] % 2 == 1)
				answer++;
		}
		return answer;
	}
}
