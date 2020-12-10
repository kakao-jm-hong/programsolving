package excercise;

import java.util.HashMap;
import java.util.Map;

public class 전화번호목록 {
	public static void main(String[] args) {
		String phone_book[] = { "119", "97674223", "1195524421" };
		System.out.println(new 전화번호목록().solution(phone_book));

	}

	public boolean solution(String[] phone_book) {

		Map<String, Integer> hash[] = new HashMap[21];

		for (int i = 1; i <= 20; i++) {
			hash[i] = new HashMap<>();
		}

		for (String str : phone_book) {
			int len = str.length();
			for (int i = 1; i <= len; i++) {
				String subStr = str.substring(0, i);
				hash[i].put(subStr, hash[i].getOrDefault(subStr, 0) + 1);
			}
		}

		for (String str : phone_book) {
			int len = str.length();

			if (hash[len].containsKey(str) && hash[len].get(str) >= 2) {
				return false;
			}
		}

		return true;
	}
}
