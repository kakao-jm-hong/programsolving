package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj4256 {
	static int N;
	static int[] pre;
	static int[] in;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			pre = new int[N];
			in = new int[N];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				pre[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				in[i] = Integer.parseInt(st.nextToken());
			}
			post(0, N, 0, sb);
			sb.append('\n');
		}
		System.out.println(sb.toString());
	}

	private static void post(int start, int end, int cur, StringBuilder sb) {

		for (int i = start; i < end; i++) {
			if (in[i] == pre[cur]) {
				post(start, i, cur + 1, sb);
				post(i + 1, end, cur + 1 + i - start, sb);
				sb.append(pre[cur]).append(' ');
			}
		}
	}
}
