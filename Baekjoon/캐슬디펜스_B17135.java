package Baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class 캐슬디펜스_B17135 {

	static int N, M, D;
	static ArrayList<Node> enemies = new ArrayList<>();
	static ArrayList<Node> enemiesTemp = new ArrayList<>();
	static int[] tgt = new int[3];
	static Node[] archers = new Node[3];
	static int ans;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		int id = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int input = Integer.parseInt(st.nextToken());
				if (input == 1)
					enemies.add(new Node(id++, i, j));
			}
		}

		Collections.sort(enemies, (n1, n2) -> n1.x - n2.x);
		for (int i = 0; i < 3; i++)
			archers[i] = new Node(0, N, 0);

		comb(0, 0);

		System.out.println(ans);
		br.close();
	}

	static void comb(int num, int tgtIdx) {
		if (tgtIdx == 3) {
			for (int i = 0; i < 3; i++)
				archers[i].x = tgt[i];

			enemiesTemp.clear();
			for (Node enm : enemies)
				enemiesTemp.add(new Node(enm.id, enm.y, enm.x));
			ans = Math.max(ans, shoot());
			return;
		}

		if (num == M)
			return;

		comb(num + 1, tgtIdx);
		tgt[tgtIdx] = num;
		comb(num + 1, tgtIdx + 1);
	}

	static int shoot() {
		int cnt = 0;
		while (!chk()) {
			boolean[] dead = new boolean[enemiesTemp.size()];
			for (Node arc : archers) {
				int d = Integer.MAX_VALUE;
				int dIdx = 0;
				for (int i = 0; i < enemiesTemp.size(); i++) {
					if (!enemiesTemp.get(i).alive)
						continue;

					if (d > dist(arc, enemiesTemp.get(i))) {
						d = dist(arc, enemiesTemp.get(i));
						dIdx = i;
					}
				}
				if (d <= D) {
					dead[dIdx] = true;
				}
			}
			for (int i = 0; i < enemiesTemp.size(); i++) {
				if (dead[i]) {
					enemiesTemp.get(i).alive = false;
					cnt++;
				}
			}

			for (Node enm : enemiesTemp) {
				if (++enm.y == N)
					enm.alive = false;
			}
		}
		return cnt;
	}

	static boolean chk() {
		boolean ret = true;
		for (Node enm : enemiesTemp) {
			if (enm.alive) {
				ret = false;
				break;
			}
		}
		return ret;
	}

	static int dist(Node n1, Node n2) {
		return Math.abs(n1.y - n2.y) + Math.abs(n1.x - n2.x);
	}

	static class Node {
		int id;
		int y;
		int x;
		boolean alive = true;

		public Node(int id, int y, int x) {
			this.id = id;
			this.y = y;
			this.x = x;
		}
	}
}