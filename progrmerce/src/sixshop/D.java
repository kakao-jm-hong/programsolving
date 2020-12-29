package sixshop;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class D {
	public static void main(String[] args) {
		int N = 50;
		int coffee_times[] = { 1, 3, 4, 1, 2 };
		System.out.println(Arrays.toString(new D().solution(N, coffee_times)));
	}

	public class Pair implements Comparable<Pair> {
		int n;
		long end_time;

		public Pair(int n, long end_time) {
			super();
			this.n = n;
			this.end_time = end_time;
		}

		@Override
		public int compareTo(Pair o) {
			if (this.end_time == o.end_time) {
				return this.n - o.n;
			}
			return Long.compare(this.end_time , o.end_time);
		}
	}

	public int[] solution(int N, int[] coffee_times) {
		int size = coffee_times.length;
		int[] answer = new int[size];
		Queue<Pair> q = new PriorityQueue<Pair>();
		int cur = 0;
		int rank = 1;
		long  time = 0;
		for (time = 0; cur != size;) {
			while (!q.isEmpty() && q.peek().end_time <= time) {
				Pair c = q.poll();
				answer[c.n] = rank++;
			}

			while (q.size() < N && cur<size) {
				q.add(new Pair(cur, time + coffee_times[cur++]));
			}

			time = q.peek().end_time;
		}
		while (!q.isEmpty()) {
			Pair c = q.poll();
			answer[c.n] = rank++;
		}

		int ans[] = new int[size];
		for (int i = 0; i < size; i++) {
			ans[answer[i]-1] = i + 1;
		}
		return ans;
	}
}
