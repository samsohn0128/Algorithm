package SWExpert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 무선충전_P5644 {

	static int T;
	static int M, A;
	static int[] moveA = new int[101], moveB = new int[101];
	static Battery curA = new Battery(1, 1), curB = new Battery(10, 10);
	static int ans;
	static PriorityQueue<Battery> pqA = new PriorityQueue<>((b1, b2) -> b2.p - b1.p);
	static PriorityQueue<Battery> pqB = new PriorityQueue<>((b1, b2) -> b2.p - b1.p);

	// 0 : 가만히 1: 상, 2: 우, 3: 하, 4: 좌
	static Battery[] batteries = new Battery[9];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++)
				moveA[i] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++)
				moveB[i] = Integer.parseInt(st.nextToken());

			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine());
				batteries[i] = new Battery(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}

			curA.x = 1;
			curA.y = 1;
			curB.x = 10;
			curB.y = 10;
			ans = 0;

			for (int i = 0; i < M; i++) {
//				System.out.println(i);
				choseBC();
				move(i);
			}
			choseBC();
			System.out.println("#" + tc + " " + ans);
		}
	}

	static void choseBC() {
		for (int i = 0; i < A; i++) {
			if (getDist(curA, batteries[i]) <= batteries[i].c) {
				pqA.offer(batteries[i]);
//				System.out.println((i + 1) + "번째 배터리");
//				System.out.println("getDist A : " + getDist(curA, batteries[i]));
			}
			if (getDist(curB, batteries[i]) <= batteries[i].c) {
				pqB.offer(batteries[i]);
//				System.out.println((i + 1) + "번째 배터리");
//				System.out.println("getDist B : " + getDist(curB, batteries[i]));
			}
		}

		Battery bcA = pqA.poll();
		Battery bcB = pqB.poll();
		if (bcA != null && bcB != null) {
			if (bcA.x == bcB.x && bcA.y == bcB.y) {
				Battery bcA2 = pqA.poll();
				Battery bcB2 = pqB.poll();

				if (bcA2 != null && bcB2 != null) {
					ans += bcA.p + Math.max(bcA2.p, bcB2.p);
				} else if (bcA2 != null) {
					ans += bcA.p + bcA2.p;
				} else if (bcB2 != null) {
					ans += bcA.p + bcB2.p;
				} else {
					ans += bcA.p;
				}
			} else {
				ans += bcA.p + bcB.p;
			}
		} else if (bcA != null) {
			ans += bcA.p;
		} else if (bcB != null) {
			ans += bcB.p;
		}

		pqA.clear();
		pqB.clear();

//		System.out.println("ans : " + ans);
//		System.out.println("====================");
	}

	static void move(int t) {
		switch (moveA[t]) {
		case 0:
			break;
		case 1:
			curA.y--;
			break;
		case 2:
			curA.x++;
			break;
		case 3:
			curA.y++;
			break;
		case 4:
			curA.x--;
			break;
		}

		switch (moveB[t]) {
		case 0:
			break;
		case 1:
			curB.y--;
			break;
		case 2:
			curB.x++;
			break;
		case 3:
			curB.y++;
			break;
		case 4:
			curB.x--;
			break;
		}
	}

	static int getDist(Battery b1, Battery b2) {
		return Math.abs(b1.x - b2.x) + Math.abs(b1.y - b2.y);
	}

	static class Battery {
		int x;
		int y;
		int c;
		int p;

		public Battery(int x, int y, int c, int p) {
			this.x = x;
			this.y = y;
			this.c = c;
			this.p = p;
		}

		public Battery(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}