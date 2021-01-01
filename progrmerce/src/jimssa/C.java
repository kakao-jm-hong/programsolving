package jimssa;

import java.util.ArrayList;
import java.util.Collections;

public class C {
	public static void main(String[] args) {

		int[] students = { 1, 3, 4, 7, 8 };
		int attendance = 8;
		System.out.println(new C().solution(students, attendance));
	}

	static ArrayList<Integer> A, B;

	public long solution(int[] students, int attendance) {
		int N = students.length;
		A = new ArrayList<Integer>();
		B = new ArrayList<Integer>();

		DFS(students, 0, N / 2, 0, A);
		DFS(students, N / 2, N, 0, B);
		// 정렬
		Collections.sort(A);
		Collections.sort(B);
		
		int l = 0;
		int r = B.size() - 1;
		
		long answer = 0;
		while (l < A.size() && r >= 0) {
			int lv = A.get(l);
			int rv = B.get(r);
			if (lv + rv == attendance) {
				long lc = 0;
				long rc = 0;
				while (l < A.size() && A.get(l) == lv) {
					lc++;
					l++;
				}
				while (r >= 0 && B.get(r) == rv) {
					rc++;
					r--;
				}
				answer += lc * rc;
			}
			if (lv + rv > attendance) {
				r--;
			}
			if (lv + rv < attendance) {
				l++;
			}
		}
		if (attendance == 0) {
			return answer - 1;
		} else {
			return answer;
		}
	}

	public static void DFS(int students[], int s, int n, int sum, ArrayList<Integer> list) {
		if (s == n) {
			list.add(sum);
			return;
		}
		DFS(students, s + 1, n, sum + students[s], list);
		DFS(students, s + 1, n, sum, list);

	}
}
