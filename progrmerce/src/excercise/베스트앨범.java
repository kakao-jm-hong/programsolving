package excercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class 베스트앨범 {
	public static void main(String[] args) {
		String[] genres = { "classic", "pop", "classic", "classic", "pop" };
		int[] plays = { 500, 600, 150, 800, 2500 };

		System.out.println(Arrays.toString(new 베스트앨범().solution(genres, plays)));
	}

//	속한 노래가 많이 재생된 장르를 먼저 수록합니다.
//	장르 내에서 많이 재생된 노래를 먼저 수록합니다.
//	장르 내에서 재생 횟수가 같은 노래 중에서는 고유 번호가 낮은 노래를 먼저 수록합니다.

	static class Pair implements Comparable<Pair> {
		int index;
		int play;

		public Pair(int index, int play) {
			super();
			this.index = index;
			this.play = play;
		}

		@Override
		public int compareTo(Pair o) {

			if (o.play == this.play) {
				return Integer.compare(this.index, o.index);
			}

			return Integer.compare(o.play, this.play);
		}

	}

	static class Best implements Comparable<Best> {
		String genre;
		int play;

		public Best(String genre, int play) {
			super();
			this.genre = genre;
			this.play = play;
		}

		@Override
		public int compareTo(Best o) {
			return Integer.compare(o.play, this.play);
		}

	}

	public int[] solution(String[] genres, int[] plays) {
		Map<String, Integer> hash = new TreeMap<String, Integer>();
		Map<String, Integer> hashGenres = new TreeMap<String, Integer>();
		Set<Pair> set[] = new TreeSet[100];

		for (int i = 0; i < 100; i++)
			set[i] = new TreeSet<>();

		int genersNum = 0;
		int count = genres.length;
		List<Integer> list = new ArrayList<>();

		for (int i = 0; i < count; i++) {
			hash.put(genres[i], hash.getOrDefault(genres[i], 0) + plays[i]);

			if (!hashGenres.containsKey(genres[i])) {
				hashGenres.put(genres[i], genersNum++);
			}

			int tmp = hashGenres.get(genres[i]);
			set[tmp].add(new Pair(i, plays[i]));
		}

		Set<String> keys = hash.keySet();
		Queue<Best> q = new PriorityQueue<>();
		for (String key : keys)
			q.add(new Best(key, hash.get(key)));

		while (!q.isEmpty()) {
			Best cur = q.poll();
			int setNum = hashGenres.get(cur.genre);
			int s = 0;
			for (Iterator<Pair> it = set[setNum].iterator(); it.hasNext() && s < 2; s++) {
				Pair p = it.next();
				list.add(p.index);
			}
		}
		int answer[] = new int[list.size()];
		for(int i=0;i<list.size();i++)
			answer[i] = list.get(i);
		return answer;
	}

}
