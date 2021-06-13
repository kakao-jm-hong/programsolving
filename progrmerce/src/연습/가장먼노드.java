package 연습;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class 가장먼노드 {
	public static void main(String[] args) {

	}

	static ArrayList<Integer> Edge[];

	public int solution(int n, int[][] edge) {
		int answer = 0;
		Edge = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++) {
			Edge[i] = new ArrayList<>();
		}

		for (int[] ver : edge) {
			int a = ver[0];
			int b = ver[1];

			Edge[a].add(b);
			Edge[b].add(a);
		}

		// BFS

		Queue<Integer> q = new LinkedList<>();
		boolean[] visit = new boolean[n + 1];

		visit[1] = true;
		q.add(1);

		while (!q.isEmpty()) {
			int size = q.size();
			answer = 0;

			for (int s = 0; s < size; s++) {
				int cur = q.poll();
				answer++;

				for (int next : Edge[cur]) {
					if (visit[next])
						continue;

					q.add(next);
					visit[next] = true;
				}
			}
		}

		return answer;
	}
}
