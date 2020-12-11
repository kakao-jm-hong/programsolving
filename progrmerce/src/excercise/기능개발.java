package excercise;

import java.util.ArrayList;
import java.util.List;

public class 기능개발 {
	public int[] solution(int[] progresses, int[] speeds) {
		int completeTime[] = new int[progresses.length];
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < progresses.length; i++) {
			completeTime[i] = (100 - progresses[i] + speeds[i]) / speeds[i];
		}

		int pivot = 0;
		for (int time = 0; time <= 100 && pivot < progresses.length; time++) {
			int cnt = 0;

			while (pivot < progresses.length && completeTime[pivot] <= time) {
				pivot++;
				cnt++;
			}
			if (cnt != 0)
				list.add(cnt);
		}

		int answer[] = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			answer[i] = list.get(i);
		}
		return answer;
	}
}
