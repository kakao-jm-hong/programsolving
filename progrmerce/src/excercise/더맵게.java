package excercise;

import java.util.PriorityQueue;
import java.util.Queue;

import excercise.더맵게.SCOVI;

public class 더맵게 {

	public class SCOVI implements Comparable<SCOVI> {
		int k;

		public SCOVI(int k) {
			super();
			this.k = k;
		}

		@Override
		public int compareTo(SCOVI o) {
			return this.k - o.k;
		}
	}

	public int solution(int[] scoville, int K) {
		Queue<SCOVI> pq = new PriorityQueue<>();
		for (int k : scoville) {
			pq.add(new SCOVI(k));
		}

		int answer = 0;
		while (!pq.isEmpty()) {
			SCOVI p1 = pq.poll();
			if (p1.k >= K) {
				return answer;
			}

			if (pq.size() == 0) {
				break;
			}

			SCOVI p2 = pq.poll();

			SCOVI newP = new SCOVI(p1.k + (p2.k * 2));
			pq.add(newP);
			answer++;
		}

		return -1;
	}
}
