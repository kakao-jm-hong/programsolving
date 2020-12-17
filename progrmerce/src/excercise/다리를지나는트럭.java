package excercise;

import java.util.LinkedList;
import java.util.Queue;

public class 다리를지나는트럭 {
	public static void main(String[] args) {
		int bridge_length = 2;
		int weight = 10;
		int[] truck_weights = { 7, 4, 5, 6 };

		System.out.println(new 다리를지나는트럭().solution(bridge_length, weight, truck_weights));
	}

	public int solution(int bridge_length, int weight, int[] truck_weights) {
		int answer = 0;

		int totalWeight = 0;
		Queue<Integer> q = new LinkedList<Integer>();
		for (int truck : truck_weights) {
			while (true) {
				if (q.isEmpty()) {
					totalWeight += truck;
					answer++;
					break;
				} else if (q.size() == bridge_length) {
					totalWeight -= q.poll();
				} else {
					answer++;
					if (totalWeight + truck <= weight) {
						q.add(truck);
						totalWeight += truck;
						break;
					} else {
						q.add(0);
					}
				}
			}

		}
		return answer + bridge_length;
	}
}
