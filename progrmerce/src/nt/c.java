package nt;

public class c {
	public int solution(int[] prices) {
		int answer = 0;
		int minPrice = Integer.MAX_VALUE;
		for (int p : prices) {
			minPrice = Math.min(minPrice, p);
			answer = Math.max(answer,p - minPrice);
		}
		return answer;
	}
}
