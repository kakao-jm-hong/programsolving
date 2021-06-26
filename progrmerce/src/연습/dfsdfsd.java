package 연습;

import java.util.HashMap;
import java.util.Map;

public class dfsdfsd {
	
	public static void main(String[] args) {
		String S = "779091968 23 Sep 2009 system.zip\n 284164096 14 Aug 2013 to-do-list.xml\n 714080256 19 Jun 2013 blockbuster.mpeg\n  329 12 Dec 2010 notes.html\n 444596224 17 Jan 1950 delete-this.zip\n       641 24 May 1987 setup.png\n    245760 16 Jul 2005 archive.zip\n 839909376 31 Jan 1990 library.dll";
		System.out.println(new dfsdfsd().solution(S));
		System.out.println("check");
	}

	static Map<String, Integer> map = new HashMap<>();
	// Compare Value
	long minValue = 240 * 1024 * 8;
	int minDay = 31;
	int minMonth = 1;
	int minYear = 1990;

	public String solution(String S) {
		init();

		int answer = 0;

		String[] lists = S.trim().split("\n");
		for (String list : lists) {

			String[] data = list.trim().split(" ");
			long value = Long.parseLong(data[0]);
			int day = Integer.parseInt(data[1]);
			int month = map.get(data[2]);
			int year = Integer.parseInt(data[3]);

			if (check(value, day, month, year)) {
				answer++;
			}
		}

		if (answer == 0)
			return "NO FILES";
		return answer + "";
	}

	private boolean check(long value, int day, int month, int year) {
		if(minValue> value)
			return false;
		
		if(minYear > year)
			return false;
		else if(minYear == year) {
			if(minMonth > month)
				return false;
			else if(minMonth == month) {
				if(minDay > day)
					return false;
				else
					return true;
			}
		}
		
		return true;
	}

	static void init() {
		map.put("Jan", 1);
		map.put("Feb", 2);
		map.put("Mar", 3);
		map.put("Apr", 4);
		map.put("May", 5);
		map.put("Jun", 6);
		map.put("Jul", 7);
		map.put("Aug", 8);
		map.put("Sep", 9);
		map.put("Oct", 10);
		map.put("Nov", 11);
		map.put("Dec", 12);
	}
}
