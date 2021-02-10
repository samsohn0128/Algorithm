package SWExpert;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 상호의배틀필드_P1873 {

	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };
	static int T;
	static char[][] grid;
	static int H, W;
	static Tank tank;
	static int N;
	static char[] commands;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			grid = new char[H][W];
			for (int i = 0; i < H; i++) {
				grid[i] = br.readLine().toCharArray();
				for (int j = 0; j < W; j++) {
					switch (grid[i][j]) {
					case '^':
						tank = new Tank(i, j, 0);
						break;
					case '>':
						tank = new Tank(i, j, 1);
						break;
					case 'v':
						tank = new Tank(i, j, 2);
						break;
					case '<':
						tank = new Tank(i, j, 3);
						break;
					}
				}
			}

			N = Integer.parseInt(br.readLine());
			commands = br.readLine().toCharArray();

			for (int i = 0; i < N; i++) {
				switch (commands[i]) {
				case 'U':
					move(0);
					break;
				case 'R':
					move(1);
					break;
				case 'D':
					move(2);
					break;
				case 'L':
					move(3);
					break;
				case 'S':
					shoot();
					break;

				}
			}
			System.out.print("#" + tc + " ");
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					System.out.print(grid[i][j]);
				}
				System.out.println();
			}
		}

		br.close();
	}

	static void move(int dir) {
		if (tank.y + dy[dir] >= 0 && tank.y + dy[dir] < H && tank.x + dx[dir] >= 0 && tank.x + dx[dir] < W
				&& grid[tank.y + dy[dir]][tank.x + dx[dir]] == '.') {
			grid[tank.y][tank.x] = '.';

			tank.y += dy[dir];
			tank.x += dx[dir];
		}
		tank.dir = dir;
		switch(tank.dir) {
		case 0:
			grid[tank.y][tank.x]='^'; 
			break;
		case 1:
			grid[tank.y][tank.x]='>'; 
			break;
		case 2:
			grid[tank.y][tank.x]='v'; 
			break;
		case 3:
			grid[tank.y][tank.x]='<'; 
			break;
		}
	}

	static void shoot() {
		int shootX = tank.x;
		int shootY = tank.y;
		while (shootY + dy[tank.dir] >= 0 && shootY + dy[tank.dir] < H && shootX + dx[tank.dir] >= 0
				&& shootX + dx[tank.dir] < W) {
			if (grid[shootY + dy[tank.dir]][shootX + dx[tank.dir]] == '*') {
				grid[shootY + dy[tank.dir]][shootX + dx[tank.dir]] = '.';
				break;
			} else if (grid[shootY + dy[tank.dir]][shootX + dx[tank.dir]] == '#') {
				break;
			} else {
				shootY += dy[tank.dir];
				shootX += dx[tank.dir];
			}
		}
	}
}

class Tank {
	public int y = 0;
	public int x = 0;
	public int dir = 0;

	public Tank(int y, int x, int dir) {
		super();
		this.y = y;
		this.x = x;
		this.dir = dir;
	}
}