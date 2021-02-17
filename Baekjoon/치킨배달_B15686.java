package Baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 치킨배달_B15686 {

	static int N, M;

	static ArrayList<Node> house = new ArrayList<>();
	static int houseCnt;
	static ArrayList<Node> chicken = new ArrayList<>();
	static int chickenCnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				int input = Integer.parseInt(st.nextToken());
				if (input == 1) {
					house.add(new Node(i, j));
					houseCnt++;
				} else if (input == 2) {
					chicken.add(new Node(i, j));
					chickenCnt++;
				}
			}
		}

		if (chickenCnt < M) {
			System.out.println();
		} else {
			ArrayList<Node> list = new ArrayList<>();
			System.out.println(comb(0, list));
		}

		br.close();
	}

	static int comb(int idx, ArrayList<Node> list) {
		int min = Integer.MAX_VALUE;
		if (list.size() == M) {
			return getMinDist(list);
		}
		if (idx == chickenCnt) {
			return min;
		}

		list.add(chicken.get(idx));
		min = Math.min(min, comb(idx + 1, list));
		list.remove(chicken.get(idx));
		min = Math.min(min, comb(idx + 1, list));
		return min;
	}

	static int getMinDist(ArrayList<Node> list) {
		int ret = 0;
		for (Node n1 : house) {
			int min = Integer.MAX_VALUE;
			for (Node n2 : list) {
				min = Math.min(min, getDist(n1, n2));
			}
			ret += min;
		}
		return ret;
	}

	static int getDist(Node n1, Node n2) {
		return Math.abs(n1.y - n2.y) + Math.abs(n1.x - n2.x);
	}

	static class Node {
		int y;
		int x;

		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}