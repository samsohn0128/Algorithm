package Baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 할로윈묘지_B3860 {
	static int W, H, G, E;
	static Node grid[][]; // 0 : enable, 1 : disable, 2 : warp from, 3: warp to, 4: end
	static int[][] dist;
	static ArrayList<Edge> edges = new ArrayList<>();

	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		while (true) {
			st = new StringTokenizer(br.readLine());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());

			if (W == 0 && H == 0)
				break;

			grid = new Node[H][W];
			dist = new int[H][W];
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					dist[i][j] = Integer.MAX_VALUE;
					grid[i][j] = new Node(0);
				}
			}
			grid[H - 1][W - 1].type = 4;
			dist[0][0] = 0;
			edges.clear();

			G = Integer.parseInt(br.readLine());
			for (int i = 0; i < G; i++) {
				st = new StringTokenizer(br.readLine());
				int X = Integer.parseInt(st.nextToken());
				int Y = Integer.parseInt(st.nextToken());
				grid[Y][X] = new Node(1);
			}
			E = Integer.parseInt(br.readLine());
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int X1 = Integer.parseInt(st.nextToken());
				int Y1 = Integer.parseInt(st.nextToken());
				int X2 = Integer.parseInt(st.nextToken());
				int Y2 = Integer.parseInt(st.nextToken());
				int T = Integer.parseInt(st.nextToken());
				grid[Y1][X1] = new Node(2, Y2, X2, T);
			}

			for (int y = 0; y < H; y++) {
				for (int x = 0; x < W; x++) {
					if (grid[y][x].type == 0) {
						for (int i = 0; i < 4; i++) {
							int ny = y + dy[i];
							int nx = x + dx[i];
							if (ny >= 0 && ny < H && nx >= 0 && nx < W) {
								if (grid[ny][nx].type != 1) {
									edges.add(new Edge(y, x, ny, nx, 1));
								}
							}
						}
					} else if (grid[y][x].type == 2) {
						edges.add(new Edge(y, x, grid[y][x].warpToY, grid[y][x].warpToX, grid[y][x].warpTime));
					}
				}
			}

			boolean flag = bellmanFord();

			if (flag) {
				if (dist[H - 1][W - 1] != Integer.MAX_VALUE)
					System.out.println(dist[H - 1][W - 1]);
				else
					System.out.println("Impossible");
			} else {
				System.out.println("Never");
			}
		}
		br.close();
	}

	static boolean bellmanFord() {
		boolean flag = true;
		for (int i = 0; i < H * W; i++) {
			for (Edge edge : edges) {
				if (dist[edge.fromY][edge.fromX] != Integer.MAX_VALUE) {
					if (dist[edge.toY][edge.toX] > dist[edge.fromY][edge.fromX] + edge.weight) {
						dist[edge.toY][edge.toX] = dist[edge.fromY][edge.fromX] + edge.weight;
					}
				}
			}
		}

		for (Edge edge : edges) {
			if (dist[edge.fromY][edge.fromX] != Integer.MAX_VALUE) {
				if (dist[edge.toY][edge.toX] > dist[edge.fromY][edge.fromX] + edge.weight) {
					flag = false;
				}
			}
		}

		return flag;
	}

	static class Node {
		int type = 0;
		int warpToY;
		int warpToX;
		int warpTime;

		public Node(int type) {
			this.type = type;
		}

		public Node(int type, int warpToY, int warpToX, int warpTime) {
			this.type = type;
			this.warpToY = warpToY;
			this.warpToX = warpToX;
			this.warpTime = warpTime;
		}
	}

	static class Edge {
		int fromY;
		int fromX;
		int toY;
		int toX;
		int weight;

		public Edge(int fromY, int fromX, int toY, int toX, int weight) {
			this.fromY = fromY;
			this.fromX = fromX;
			this.toY = toY;
			this.toX = toX;
			this.weight = weight;
		}
	}
}