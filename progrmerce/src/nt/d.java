package nt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class d {
	public static void main(String[] args) {
		String card[] = { "ABACDEFG", "NOPQRSTU", "HIJKLKMM" };
		String word[] = { "GPQM", "GPMZ", "EFU", "MMNA" };

		System.out.println(Arrays.toString(new d().solution(card, word)));
	}

	public String[] solution(String[] card, String[] word) {

		int[][] Alpha = new int[3][26];
		int[] check = new int[3];
		List<String> list = new ArrayList<>();

		for (int i = 0; i < card.length; i++) {
			for (int j = 0; j < card[i].length(); j++) {
				char a = card[i].charAt(j);
				Alpha[i][a - 'A']++;
			}
		}

		for (String w : word) {
			if (checkWord(Alpha, w)) {
				list.add(w);
			}
		}
		String[] answer = null;
		if (list.size() == 0) {
			answer = new String[1];
			answer[0] = "-1";
		} else {
			answer = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
				answer[i] = list.get(i);
			}
		}
		return answer;
	}

	private boolean checkWord(int[][] Alpha, String w) {
		int cnt = 0;
		int[][] alpha = new int[3][26];
		for (int i = 0; i < 3; i++) {
			alpha[i] = Alpha[i].clone();
		}
		int check[] = new int[3];
		for (int i = 0; i < w.length(); i++) {
			char c = w.charAt(i);
			for (int j = 0; j < 3; j++) {
				if (alpha[j][c - 'A'] > 0) {
					alpha[j][c - 'A']--;
					cnt++;
					check[j]++;
					break;
				}
			}
		}
		if (cnt != w.length()) {
			return false;
		}
		for (int i = 0; i < check.length; i++) {
			if (check[i] == 0)
				return false;
		}

		return true;
	}
}
