package hyn;

public class a {

	public static void main(String[] args) {
		System.out.println(new a().solution("AAAaBaAbBBBBbCcBdBdBdBcCeBfBeGgGGjGjGRvRvRvRvRvR"));
	}

	/*
	 * a ~ z 문자수 체크 사용했는지에 대해서
	 */
	static boolean[] check = new boolean[26];
	static int[] count = new int[26];

	public String solution(String sentence) {

		// 하나의 단어를 넣는다. (이게 하나의 단어라고 유추);
		for (int i = 0; i < sentence.length(); i++) {
			char c = sentence.charAt(i);
			if (isThatLowerCase(c))
				count[c - 'a']++;
		}

		String answer = Solve(sentence).trim();
		return isAnswerCase(answer);
	}

	public static class Pair {
		char c;
		int n;

		public Pair(char c, int n) {
			super();
			this.c = c;
			this.n = n;
		}
	}

	public static Pair getItem(String str) {
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (!isThatLowerCase(c)) {
				continue;
			}
			if (count[c - 'a'] == 0)
				continue;
			if (count[c - 'a'] == 2) {
				return new Pair(c, 2);
			} else {
				return new Pair(c, 1);
			}
		}
		return new Pair((char) 0, 0);
	}

	public String Solve(String str) {
		if (str.equals(""))
			return str;
		Pair cur = getItem(str);
		if (cur.n == 0)
			return str;
		if (cur.n == 2) {
			int s = str.indexOf(cur.c);
			int end = getLastIndex(s, cur.c, str);
			count[cur.c - 'a'] = 0;
			str = str.replaceAll(cur.c + "", " ").trim();
			String[] strSplit = str.split(" ");
			if (strSplit.length == 1) {
				return Solve(strSplit[0]);
			}
			if (strSplit.length == 3) {

				if (s + 2 == end) {
					return Solve(str.replaceAll(cur.c + "", "").replaceAll(" ", ""));
				}

			}

			String answer = "";
			for (String thisString : strSplit) {
				answer += Solve(thisString) + " ";
			}
			return answer;
		}

		int s = str.indexOf(cur.c);
		if (s == -1)
			return str;
		int end = getLastIndex(s, cur.c, str);
		int len = str.length();
		if (s == 0 || end == len - 1)
			return str;
		count[cur.c - 'a'] = 0;
		return (Solve(str.substring(0, s - 1)).trim() + " "
				+ Solve((str.substring(s - 1, end + 2).replaceAll(cur.c + "", ""))).trim() + " "
				+ Solve(str.substring(end + 2, len)).trim()).trim();

	}

	private static int getLastIndex(int s, char c, String str) {
		int ans = s + 2;
		for (; ans < str.length(); ans += 2) {
			if (str.charAt(ans) != c)
				return ans - 2;
		}
		return ans - 2;
	}

	public static String isAnswerCase(String answer) {

		answer = answer.trim();

		if (answer == null || answer.length() == 0)
			return "invalid";

		for (int i = 0; i < answer.length(); i++) {
			char c = answer.charAt(i);
			if (isThatLowerCase(c)) {
				return "invalid";
			}
		}
		return answer.trim();
	}

	public static boolean isThatLowerCase(char c) {
		if (c >= 'a' && c <= 'z')
			return true;

		return false;
	}
}
