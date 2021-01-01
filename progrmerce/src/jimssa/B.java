package jimssa;

import java.util.HashMap;
import java.util.Map;

public class B {
	public static void main(String[] args) {
		int balloons[][] = { { 2, 2 }, { 4, 4, }, { 1, 4 }, { -1, -4 } };
		System.out.println(new B().solution(balloons));
	}
	private static int GCD(int a, int b) {
		if (b == 0) {
			return a;
		}
		return GCD(b, a % b);
	}

	public int solution(int[][] balloons) {
		Map<String, Integer> hash = new HashMap<>();
		for (int i = 0; i < balloons.length; i++) {
			int x = balloons[i][0];
			int y = balloons[i][1];
			int xx = (x<0) ? x*-1:x;
			int yy = (y<0) ? y*-1:y;
			int gcd = GCD(xx, yy);
			x = x / gcd;
			y = y / gcd;
			if(!hash.containsKey(x+":"+y)) {
				hash.put(x+":"+y, 1);
			}
		}
		return hash.size();
	}
}
