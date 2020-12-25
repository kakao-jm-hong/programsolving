package nt;

public class a {

	public static void main(String[] args) {
		String s = "44335550555666";
		System.out.println(new a().solution(s));
	}

	static String[][] StrNum = { { "." }, { ".", "Q", "Z" }, { "A", "B", "C" }, { "D", "E", "F" }, { "G", "H", "I" },
			{ "J", "K", "L" }, { "M", "N", "O" }, { "P", "R", "S" }, { "T", "U", "V" }, { "W", "X", "Y" } };

	public String solution(String s) {
		StringBuilder sb = new StringBuilder();
		int t = 0;
		while (t < s.length()) {
			int cnt = 1;
			char pri = s.charAt(t);
			if (pri == '0') {
				t++;
				continue;
			}
			while (t < s.length() && cnt <= 2) {
				t++;
				if (t == s.length()) {
					break;
				}
				char cur = s.charAt(t);

				if (pri == cur) {
					cnt++;
				} else if (cur == '0') {
					break;
				} else if (pri != cur) {
					break;
				}
			}
			sb.append(StrNum[pri - '0'][cnt - 1]);
			if (cnt == 3) {
				t++;
			}

			if (t == s.length()) {
				break;
			}
		}

		return sb.toString();
	}
}
