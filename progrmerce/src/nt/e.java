package nt;

public class e {
	public static void main(String[] args) {

	}

	public String solution(String p, int n) {
		// Input
		String tmp[] = p.split(" ");
		String AP = tmp[0];
		String timeStr[] = tmp[1].split(":");

		// 초로 전부 바꾼다.
		int time = 0;

		// AM PM
		if (AP.equals("PM") && Integer.parseInt(timeStr[0]) < 12) {
			time += 12 * 60 * 60;
		}
		// 시간
		if (!AP.equals("AM") || !timeStr[0].equals("12")) {

			time += Integer.parseInt(timeStr[0]) * 60 * 60;
		}
		// 분
		time += Integer.parseInt(timeStr[1]) * 60;
		// 초
		time += Integer.parseInt(timeStr[2]);

		// n초 후
		time += n;

		time = (time) % (24 * 60 * 60);

		// answer 계산
		int h = time / (60 * 60);
		time -= h * 60 * 60;
		int m = time / (60);
		time -= m * 60;
		int s = time;
		return String.format("%02d", h) + ":" + String.format("%02d", m) + ":" + String.format("%02d", s);
	}
}
