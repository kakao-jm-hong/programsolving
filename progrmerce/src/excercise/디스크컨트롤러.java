package excercise;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class 디스크컨트롤러 {

	public static void main(String[] args) {
		int[][] jobs = { { 1, 9 }, { 1, 4 }, { 1, 5 }, { 1, 7 }, { 1, 3 } };
		System.out.println(new 디스크컨트롤러().solution(jobs));
	}

	public class PQ implements Comparable<PQ> {
		int arriveTime;
		int burstTime;

		public PQ(int arriveTime, int burstTime) {
			super();
			this.arriveTime = arriveTime;
			this.burstTime = burstTime;
		}

		@Override
		public int compareTo(PQ o) {
			return this.burstTime - o.burstTime;
		}
	}

	public int solution(int[][] jobs) {
		int answer = 0;
		Arrays.sort(jobs, new NC());
		Queue<PQ> pq = new PriorityQueue<PQ>();

		int curTime = 0;
		int index = 0;
		while (true) {
			// 들어오는 작업
			while (index < jobs.length && jobs[index][0] <= curTime)
				pq.add(new PQ(jobs[index][0], jobs[index++][1]));

			// Burst
			if (pq.isEmpty() && index >= jobs.length) {
				break;
			} else if (pq.isEmpty()) {
				curTime = jobs[index][0];
			} else {
				PQ cur = pq.poll();
				curTime += cur.burstTime;
				answer += curTime - cur.arriveTime;
			}

		}
		return answer / jobs.length;
	}

	public class NC implements Comparator<int[]> {

		@Override
		public int compare(int[] o1, int[] o2) {
			return o1[0] - o2[0];
		}
	}
}
