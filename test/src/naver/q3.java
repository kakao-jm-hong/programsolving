package naver;

public class q3 {
	public static void main(String[] args) {
		System.out.println(solution(670));
	}

	static public int solution(int N) {
		int answer = 0;
		String str = String.valueOf(N);
		StringBuilder sb = new StringBuilder();
		int insert = -1;

		if (str.charAt(0) == '-') {
			// minus
			// 제일앞에 작은수가 와야한다.
			for (int i = 1; i < str.length(); i++) {
				if ((str.charAt(i) - '0') > 5) {
					insert = i;
					break;
				}
			}
			if (insert == -1) {
				answer = N - 5;
			} else {
				sb.append(str.substring(0, insert)).append(5).append(str.substring(insert, str.length()));
				answer = Integer.parseInt(sb.toString());
			}
		} else {
			// plus
			// 제일앞에 큰수가 와야한다.
			for (int i = 0; i < str.length(); i++) {
				if ((str.charAt(i) - '0') < 5) {
					insert = i;
					break;
				}
			}
			if (insert == -1) {
				answer = N + 5;
			} else {
				sb.append(str.substring(0, insert)).append(5).append(str.substring(insert, str.length()));
				answer = Integer.parseInt(sb.toString());
			}
		}
		return answer;
	}
}
