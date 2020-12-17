package excercise;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class 프린터 {
	public static void main(String[] args) {

	}

	static class PQ {
		int seq;
		int pri;

		public PQ(int seq, int pri) {
			super();
			this.seq = seq;
			this.pri = pri;
		}

	}

	public int solution(int[] priorities, int location) {
		Queue<PQ> pq = new LinkedList<PQ>();

		for (int i = 0; i < priorities.length; i++) {
			pq.add(new PQ(i, priorities[i]));
		}

		int l = 1;
		while (!pq.isEmpty()) {
			PQ cur = pq.poll();
			if (check(cur.pri, pq)) {
				pq.add(cur);
			} else {
				if (cur.seq == location) {
					return l;
				}
				l++;
			}
		}

		return -1;
	}

	private boolean check(int pri, Queue<PQ> pq) {
		for (Iterator<PQ> it = pq.iterator(); it.hasNext();) {
			if (pri < it.next().pri)
				return true;
		}
		return false;
	}
}
