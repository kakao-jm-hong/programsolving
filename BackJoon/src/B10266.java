import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B10266 {
	static int N;
	static int[] strIndex = new int[360000];
	static int[] patternIndex = new int[360000];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		// strIndex Input
		st = new StringTokenizer(br.readLine());
		while (st.hasMoreTokens()) {
			strIndex[Integer.parseInt(st.nextToken())] = 1;
		}

		st = new StringTokenizer(br.readLine());
		while (st.hasMoreTokens()) {
			patternIndex[Integer.parseInt(st.nextToken())] = 1;
		}

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < 360000; i++)
			sb.append(strIndex[i]);

		String str = sb.append(sb).toString();
		sb = new StringBuilder();
		for (int i = 0; i < 360000; i++)
			sb.append(patternIndex[i]);

		String pattern = sb.toString();

		if (KMP(str, pattern) == 1)
			System.out.println("possible");
		else
			System.out.println("impossible");

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
}