package h;

public class b {
	public static void main(String[] args) {
		System.out.println(new b().solution(6, 10, 0));
	}
	
	static String[] nums = { "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
			"eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eightten", "nineteen" };

	public String solution(int left, int right, int offset) {
		StringBuilder sb = new StringBuilder();
		int wordsSize = 0;
		while (wordsSize <= right) {
			String offsetStr = offset + "";

			for (int i = 0; i < offsetStr.length(); i++) {
				char c = offsetStr.charAt(i);
				if (c == '1' && i + 1 < offsetStr.length()) {
					int num = (c - '0') * 10 + (offsetStr.charAt(i + 1) - '0');
					sb.append(nums[num]);
					wordsSize += nums[num].length();
					i++;
				} else {
					sb.append(nums[c - '0']);
					wordsSize += nums[c - '0'].length();
				}
			}
			offset++;
		}
		System.out.println(sb.toString());
		return sb.toString().substring(left - 1, right);

	}
}
