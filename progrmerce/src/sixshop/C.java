package sixshop;

public class C {
	public static void main(String[] args) {
		System.out.println(new C().solution(1052353412));
	}
	
	public int solution(int n) {
		for (int i = n / 5; i >= 0; i--) {
			int five = i;
			int tmp = (n - five*5);
			if(tmp%3==0) {
				return five + (tmp/3);
			}
		}
		return -1;
	}
}
