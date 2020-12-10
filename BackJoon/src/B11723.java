import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B11723 {
	static int set[] = new int[21];
	static int M;
	static int x;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		M = Integer.parseInt(br.readLine());

		while (M-- != 0) {
			st = new StringTokenizer(br.readLine());

			String order = st.nextToken();

			if ("add".equals(order)) {
				x = Integer.parseInt(st.nextToken());
				set[x] = 1;
			} else if ("remove".equals(order)) {
				x = Integer.parseInt(st.nextToken());
				set[x] = 0;
			} else if ("check".equals(order)) {
				x = Integer.parseInt(st.nextToken());
				sb.append(set[x]).append('\n');
			} else if ("toggle".equals(order)) {
				x = Integer.parseInt(st.nextToken());
				set[x] = (set[x] + 1) % 2;
			} else if ("all".equals(order)) {
				Arrays.fill(set, 1);
			} else if ("empty".equals(order)) {
				Arrays.fill(set, 0);
			}
		}
		System.out.println(sb.toString());
	}
}
