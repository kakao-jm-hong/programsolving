package kakaocumerce;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class c {

	static ArrayList<Integer>[] edge;

	static class Pair {
		int v;
		int w;

		public Pair(int v, int w) {
			super();
			this.v = v;
			this.w = w;
		}
	}

	public int[] solution(int n, int[] passenger, int[][] train) {
//    	종착역 번호, 이용수
		int[] answer = new int[2];

		edge = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++) {
			edge[i] = new ArrayList<>();
		}
		for (int[] t : train) {
			int a = t[0];
			int b = t[1];
			// a <--> b
			edge[a].add(b);
			edge[b].add(a);
		}

		// BFS
		boolean check[] = new boolean[n + 1];
		Queue<Pair> q = new LinkedList<>();

		// 번부터 출
		check[1] = true;
		q.add(new Pair(1, passenger[0]));

		// 종착역 번호 초기
		answer[0] = 1;
		answer[1] = passenger[0];

		while (!q.isEmpty()) {
			Pair cur = q.poll();
			if (cur.w > answer[1]) {
				answer[0] = cur.v;
				answer[1] = cur.w;
			} else if (cur.w == answer[1]) {
				answer[0] = Math.max(answer[0], cur.v);
			}

			for (int next : edge[cur.v]) {
				if (check[next])
					continue;
				check[next] = true;
				q.add(new Pair(next, cur.w + passenger[next - 1]));
			}
		}

		return answer;
	}
}
