package excercise;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class 이중우선순위큐 {
	public int[] solution(String[] operations) {
		Queue<Integer> MinHeap = new PriorityQueue<>();
		Queue<Integer> MaxHeap = new PriorityQueue<>(Comparator.reverseOrder());
		int[] answer = new int[2];

		for (String operation : operations) {
			StringTokenizer st = new StringTokenizer(operation);
			String order = st.nextToken();
			int num = Integer.parseInt(st.nextToken());

			if (order.equals("I")) {
				MinHeap.add(num);
				MaxHeap.add(num);
			} else if (!MaxHeap.isEmpty()) {
				if (num == 1) {
					int max = MaxHeap.poll();
					MinHeap.remove(max);
				} else {
					int min = MinHeap.poll();
					MaxHeap.remove(min);
				}
			}
		}

		if (MinHeap.isEmpty()) {
			answer[0] = 0;
			answer[1] = 0;
		} else {
			answer[0] = MaxHeap.poll();
			answer[1] = MinHeap.poll();
		}

		return answer;
	}
}
