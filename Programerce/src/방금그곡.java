import java.util.StringTokenizer;

public class 방금그곡 {
//	음악이 00:00를 넘겨서까지 재생되는 일은 없다.
//	조건이 일치하는 음악이 여러 개일 때에는 라디오에서 재생된 시간이 제일 긴 음악 제목을 반환한다. 재생된 시간도 같을 경우 먼저 입력된 음악 제목을 반환한다.
//	조건이 일치하는 음악이 없을 때에는 `(None)`을 반환한다.
	public static void main(String[] args) {
		String m = "ABC";
		String musicinfos[] = { "12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF" };
	}

	static int[] getPi(String Pattern) {
		int[] pi = new int[Pattern.length()];

		int j = 0;

		for (int i = 1; i < Pattern.length(); i++) {

			while (j > 0 && Pattern.charAt(i) != Pattern.charAt(j)) {
				j = pi[j - 1];
			}
			if (Pattern.charAt(i) == Pattern.charAt(j)) {
				pi[i] = ++j;
			}
		}
		return pi;
	}

	static int KMP(String Origin, String Pattern) {

		int j = 0;
		int[] pi = getPi(Pattern);
		for (int i = 0; i < Origin.length(); i++) {

			while (j > 0 && Origin.charAt(i) != Pattern.charAt(j)) {
				j = pi[j - 1];
			}

			if (Origin.charAt(i) == Pattern.charAt(j)) {
				if (j == Pattern.length() - 1) {
					return 1;
				} else {
					++j;
				}
			}

		}

		return 0;
	}

	static public String solution(String m, String[] musicinfos) {
		String answer = "(None)";
		String tmp;
		String title;
		String music;
		StringTokenizer st;
		int maxTime = 0;
		// 입력이 #을 어렵게 되니간 C# -> c로 치환한다.

		m = change(m);

		for (String musicinfo : musicinfos) {
			st = new StringTokenizer(musicinfo, ",");
			// start time
			tmp = st.nextToken();
			int startTime = 60 * Integer.parseInt(tmp.substring(0, 2)) + Integer.parseInt(tmp.substring(3, 5));
			tmp = st.nextToken();
			int endTime = 60 * Integer.parseInt(tmp.substring(0, 2)) + Integer.parseInt(tmp.substring(3, 5));

			title = st.nextToken();
			tmp = st.nextToken();
			tmp = change(tmp);
			int playTime = endTime - startTime;

			if (tmp.length() >= playTime) {
				music = tmp.substring(0, playTime);
			} else {
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < (playTime / tmp.length()); i++)
					sb.append(tmp);

				sb.append(tmp.substring(0, playTime % tmp.length()));
				music = sb.toString();
			}

			// KMP
			if (KMP(music, m) == 1 && maxTime < playTime) {
				maxTime = playTime;
				System.out.println(maxTime);
				answer = title;
			}

		}

		return answer;
	}

	public static String change(String m) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < m.length() - 1; i++) {
			if (m.charAt(i + 1) == '#') {
				sb.append((char) (32 + m.charAt(i)));
				i++;
			} else {
				sb.append(m.charAt(i));
			}
		}

		if (m.charAt(m.length() - 1) != '#') {
			sb.append(m.charAt(m.length() - 1));
		}

		return sb.toString();
	}
}
