package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class B17780 {
	static int N, K;
	static int map[][];
	static int holes[][];
	static ArrayList<Integer>[][] units;
	static int dy[] = { 0, 0, 0, -1, 1 };
	static int dx[] = { 0, 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N + 1][N + 1];
		holes = new int[K + 1][3];
		units = new ArrayList[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				units[i][j] = new ArrayList<Integer>();
			}
		}
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 1; i <= K; i++) {
			st = new StringTokenizer(br.readLine());
			holes[i][0] = Integer.parseInt(st.nextToken());
			holes[i][1] = Integer.parseInt(st.nextToken());
			holes[i][2] = Integer.parseInt(st.nextToken());
			units[holes[i][0]][holes[i][1]].add(i);
		}

		System.out.println(Solve());
	}

	private static int Solve() {

		for (int turn = 1; turn <= 1000; turn++) {
			for (int k = 1; k <= K; k++) {
				// 이동
				int y = holes[k][0];
				int x = holes[k][1];
				int d = holes[k][2];

				int ny = y + dy[d];
				int nx = x + dx[d];

				int howGoing = getHowGoing(ny, nx);

				int index = getWhereHorse(k);
				if(index!=0)
					continue;
				if (howGoing == 2) {
					d = (d % 2 == 1) ? d + 1 : d - 1;
					ny = y + dy[d];
					nx = x + dx[d];
					howGoing = getHowGoing(ny, nx);
					if (howGoing == 1) {
						ArrayList<Integer> buffer = new ArrayList<Integer>(units[y][x].subList(index, units[y][x].size()));
						Collections.reverse(buffer);
						units[ny][nx].addAll(buffer);
						
						move(buffer,ny,nx);
						units[y][x] = new ArrayList<>(units[y][x].subList(0,index));
					} else if (howGoing == 0) {
						ArrayList<Integer> buffer = new ArrayList<Integer>(units[y][x].subList(index, units[y][x].size()));
						units[ny][nx].addAll(buffer);
						move(buffer,ny,nx);
						units[y][x] = new ArrayList<>(units[y][x].subList(0,index));
					}
				} else if (howGoing == 1) {
					ArrayList<Integer> buffer = new ArrayList<Integer>(units[y][x].subList(index, units[y][x].size()));
					Collections.reverse(buffer);
					units[ny][nx].addAll(buffer);
					move(buffer,ny,nx);
					units[y][x] = new ArrayList<>(units[y][x].subList(0,index));
				} else {
					ArrayList<Integer> buffer = new ArrayList<Integer>(units[y][x].subList(index, units[y][x].size()));
					units[ny][nx].addAll(buffer);
					move(buffer,ny,nx);
					units[y][x] = new ArrayList<>(units[y][x].subList(0,index));
				}
				holes[k][2] = d;
				if(!outOfBound(ny, nx) && units[ny][nx].size()>=4) {
					return turn;
				}
			}
		}
		return -1;
	}

	private static void move(ArrayList<Integer> buffer, int ny, int nx) {
		for(int k : buffer) {
			holes[k][0] = ny;
			holes[k][1] = nx;
		}
	}

	private static int getWhereHorse(int k) {
		int y = holes[k][0];
		int x = holes[k][1];

		for (int i = 0; i < units[y][x].size(); i++) {
			if (units[y][x].get(i) == k) {
				return i;
			}
		}
		return -1;
	}

	private static int getHowGoing(int ny, int nx) {
		if (outOfBound(ny, nx) || map[ny][nx] == 2) {
			return 2;
		} else if (map[ny][nx] == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	private static boolean outOfBound(int y, int x) {
		if (y < 1 || x < 1 || y > N || x > N)
			return true;
		return false;
	}
}
