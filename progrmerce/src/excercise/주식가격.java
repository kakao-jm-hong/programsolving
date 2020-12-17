package excercise;

import java.util.Arrays;
import java.util.Stack;

public class 주식가격 {

	public static void main(String[] args) {
		int prices[] = { 1, 2, 3, 2, 3, 2 };
		System.out.println(Arrays.toString(new 주식가격().solution(prices)));
	}

	static class Pair {
		int time;
		int price;

		public Pair(int time, int price) {
			super();
			this.time = time;
			this.price = price;
		}
	}

	public int[] solution(int[] prices) {

		Stack<Pair> stack = new Stack<Pair>();
		int[] answer = new int[prices.length];

		stack.add(new Pair(0, prices[0]));

		for (int t = 1; t < prices.length; t++) {

			while (!stack.isEmpty()) {
				Pair cur = stack.peek();
				if (cur.price < prices[t]) {
					stack.add(new Pair(t, prices[t]));
					break;
				} else {
					stack.pop();
					answer[cur.time] = t - cur.time;
				}
			}
			
		}

		while (!stack.isEmpty()) {
			Pair cur = stack.pop();
			answer[cur.time] = prices.length - 1 - cur.time;

		}
		return answer;
	}
}
