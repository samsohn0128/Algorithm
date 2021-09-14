import java.util.LinkedList;
import java.util.Queue;

class KAKAO2021_카드짝맞추기 {

	int cnt;
	int[] dy = { -1, 1, 0, 0 }, dx = { 0, 0, -1, 1 };
	int answer = Integer.MAX_VALUE;

	public int solution(int[][] board, int r, int c) {
		recur(board, r, c, 0);
		return answer;
	}

	void recur(int[][] board, int y, int x, int cnt) {
		boolean flag = true;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (board[i][j] != 0) {
					flag = false;
					break;
				}
			}
		}
		if (flag) {
			if (answer > cnt) {
				answer = cnt;
				return;
			}
		}

		int[][] dist = bfs(board, y, x);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (board[i][j] != 0) {
					int before = board[i][j];
					cnt += dist[i][j] + 1;
					int[][] ndist = bfs(board, i, j);
					for (int k = 0; k < 4; k++) {
						boolean breakFlag = false;
						for (int l = 0; l < 4; l++) {
							if (board[k][l] == board[i][j] && (k != i || l != j)) {
								cnt += ndist[k][l] + 1;
								board[i][j] = 0;
								board[k][l] = 0;
								recur(board, k, l, cnt);
								board[i][j] = before;
								board[k][l] = before;
								cnt -= ndist[k][l] + 1;
								cnt -= dist[i][j] + 1;
								breakFlag = true;
								break;
							}
						}
						if (breakFlag)
							break;
					}

				}
			}
		}
	}

	int[][] bfs(int[][] board, int y, int x) {
		int[][] dist = new int[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				dist[i][j] = Integer.MAX_VALUE;
			}
		}
		dist[y][x] = 0;

		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(y, x, 0));
		while (!q.isEmpty()) {
			Node temp = q.poll();
			if (dist[temp.y][temp.x] < temp.dist)
				continue;

			int ctrlUp = getCtrlUp(board, temp.y, temp.x);
			if (ctrlUp != -1) {
				if (dist[ctrlUp][temp.x] > temp.dist + 1) {
					dist[ctrlUp][temp.x] = temp.dist + 1;
					q.offer(new Node(ctrlUp, temp.x, temp.dist + 1));
				}
			}
			int ctrlDown = getCtrlDown(board, temp.y, temp.x);
			if (ctrlDown != -1) {
				if (dist[ctrlDown][temp.x] > temp.dist + 1) {
					dist[ctrlDown][temp.x] = temp.dist + 1;
					q.offer(new Node(ctrlDown, temp.x, temp.dist + 1));
				}
			}
			int ctrlLeft = getCtrlLeft(board, temp.y, temp.x);
			if (ctrlLeft != -1) {
				if (dist[temp.y][ctrlLeft] > temp.dist + 1) {
					dist[temp.y][ctrlLeft] = temp.dist + 1;
					q.offer(new Node(temp.y, ctrlLeft, temp.dist + 1));
				}
			}
			int ctrlRight = getCtrlRight(board, temp.y, temp.x);
			if (ctrlRight != -1) {
				if (dist[temp.y][ctrlRight] > temp.dist + 1) {
					dist[temp.y][ctrlRight] = temp.dist + 1;
					q.offer(new Node(temp.y, ctrlRight, temp.dist + 1));
				}
			}
			for (int i = 0; i < 4; i++) {
				int ny = temp.y + dy[i];
				int nx = temp.x + dx[i];
				if (ny >= 0 && ny < 4 && nx >= 0 && nx < 4) {
					if (dist[ny][nx] > temp.dist + 1) {
						dist[ny][nx] = temp.dist + 1;
						q.offer(new Node(ny, nx, temp.dist + 1));
					}
				}
			}
		}
//		for (int i = 0; i < 4; i++) {
//			for (int j = 0; j < 4; j++) {
//				System.out.print(dist[i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();
		return dist;
	}

	int getCtrlUp(int[][] board, int y, int x) {
		if (y == 0)
			return -1;
		y--;
		while (y > 0 && board[y][x] == 0) {
			y--;
		}
		return y;
	}

	int getCtrlDown(int[][] board, int y, int x) {
		if (y == 3)
			return -1;
		y++;
		while (y < 3 && board[y][x] == 0) {
			y++;
		}
		return y;
	}

	int getCtrlLeft(int[][] board, int y, int x) {
		if (x == 0)
			return -1;
		x--;
		while (x > 0 && board[y][x] == 0) {
			x--;
		}
		return x;
	}

	int getCtrlRight(int[][] board, int y, int x) {
		if (x == 3)
			return -1;
		x++;
		while (x < 3 && board[y][x] == 0) {
			x++;
		}
		return x;
	}

	class Node {
		int y;
		int x;
		int dist;

		public Node(int y, int x, int dist) {
			this.y = y;
			this.x = x;
			this.dist = dist;
		}
	}
}