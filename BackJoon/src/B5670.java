import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;

public class B5670 {
	static int N;

	static class TrieNode {
		HashMap<Character, TrieNode> children;

		boolean isEndOfWord;
		int childCnt;

		public TrieNode() {
			childCnt = 0;
			isEndOfWord = false;
			children = new HashMap<Character, TrieNode>();
		}
	}

	static TrieNode root;

	static void insert(String key) {
		int level;
		int length = key.length();

		TrieNode pCrawl = root;

		for (level = 0; level < length; ++level) {
			if (!pCrawl.children.containsKey(key.charAt(level))) {
				pCrawl.children.put(key.charAt(level), new TrieNode());
				pCrawl.childCnt++;
			}
			pCrawl = pCrawl.children.get(key.charAt(level));
		}

		// for이 다놀고나면 가장 끝에 노드까지 다만들어진다.
		// pCrawl 은 end 노드

		pCrawl.isEndOfWord = true;

	}

	static double allWordCnt;

	static void search(TrieNode curNode, int cnt) {
		if (curNode.isEndOfWord) {
			allWordCnt += cnt;
		}

		if (curNode.childCnt == 0) {
			return;
		} else if (curNode.childCnt == 1) {
			Iterator<Character> keys = curNode.children.keySet().iterator();
			TrieNode next = curNode.children.get(keys.next());
			if (cnt == 0)
				search(next, 1);
			else if (curNode.isEndOfWord) {
				search(next, cnt + 1);
			} else
				search(next, cnt);
		} else {
			Iterator<Character> keys = curNode.children.keySet().iterator();
			while (keys.hasNext()) {
				TrieNode next = curNode.children.get(keys.next());
				search(next, cnt + 1);
			}
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		String line = null;
		StringBuilder sb = new StringBuilder();

		while ((line = br.readLine()) != null) {

			int N = Integer.parseInt(line);
			allWordCnt = 0;

			root = new TrieNode();

			for (int n = 0; n < N; n++) {
				String key = br.readLine();
				insert(key);
			}

			// 평균을 구한다.
			search(root, 0);
			double ans = allWordCnt / N;
			sb.append(String.format("%.2f", ans)).append('\n');
		}
		System.out.println(sb.toString());

	}
}
