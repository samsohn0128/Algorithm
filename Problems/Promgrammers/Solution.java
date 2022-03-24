package Promgrammers;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Solution {
	public static void main(String[] args) throws FileNotFoundException {
		String[] info = { "java backend junior pizza 150", "python frontend senior chicken 210",
				"python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80",
				"python backend senior chicken 50" };
		String[] query = { "java and backend and junior and pizza 100" };
//		"python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250",
//		"- and backend and senior and - 150", "- and - and - and chicken 100", "- and - and - and - 150" 
		System.out.println(Arrays.toString(solution(info, query)));

	}

	static public int[] solution(String[] info, String[] query) {
		Tree t = new Tree();
		for (int i = 0; i < info.length; i++) {
			StringTokenizer st = new StringTokenizer(info[i]);
			Input input = new Input(st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken(),
					Integer.parseInt(st.nextToken()));
			t.insert(input);
		}
		ArrayList<Integer> ans = new ArrayList<Integer>();
		for (int i = 0; i < query.length; i++) {
			StringTokenizer st = new StringTokenizer(query[i]);
			String lang = st.nextToken();
			st.nextToken();
			String job = st.nextToken();
			st.nextToken();
			String degree = st.nextToken();
			st.nextToken();
			String food = st.nextToken();
			int score = Integer.parseInt(st.nextToken());
			Input queryInput = new Input(lang, job, degree, food, score);
			int cnt = 0;

			ans.add(cnt);
		}
		System.out.println();
		int[] answer = new int[ans.size()];
		for (int i = 0; i < answer.length; i++) {
			answer[i] = ans.get(i);
		}
		return answer;
	}

	// cpp, java, python, -
	// backend, frontend, -
	// junior, senior, -
	// score
	static void dfs(String str, int depth) {
		if (depth == 0) {

		} else if (depth == 1) {

		}
	}

	static class Tree {
		Node root = new Node("-");

		Tree() {
		}

		int count(Input query) {
			Node temp = root;
			temp = find(temp, query.lang);
			temp = find(temp, query.job);
			temp = find(temp, query.degree);
			temp = find(temp, query.food);
			int cnt = find2(temp, query.score);
			return cnt;
		}

		Node find(Node node, String str) {
			for (int i = 0; i < node.children.size(); i++) {
				if (node.children.get(i).str.equals(str)) {
					return node;
				}
			}
			return null;
		}

		int find2(Node node, int score) {
			int cnt = 0;
			for (int i = 0; i < node.children.size(); i++) {
				if (Integer.parseInt(node.children.get(i).str) >= score) {
					cnt++;
				}
			}
			return cnt;
		}

		void insert(Input input) {
			Node temp = root;
			boolean flag = false;
			for (int i = 0; i < temp.children.size(); i++) {
				if (temp.children.get(i).str.equals(input.lang)) {
					temp = temp.children.get(i);
					flag = true;
				}
			}
			if (!flag) {
				temp.children.add(new Node(input.lang));
				temp = temp.children.get(temp.children.size() - 1);
			}

			flag = false;
			for (int i = 0; i < temp.children.size(); i++) {
				if (temp.children.get(i).str.equals(input.job)) {
					temp = temp.children.get(i);
					flag = true;
				}
			}
			if (!flag) {
				temp.children.add(new Node(input.job));
				temp = temp.children.get(temp.children.size() - 1);
			}

			flag = false;
			for (int i = 0; i < temp.children.size(); i++) {
				if (temp.children.get(i).str.equals(input.degree)) {
					temp = temp.children.get(i);
					flag = true;
				}
			}
			if (!flag) {
				temp.children.add(new Node(input.degree));
				temp = temp.children.get(temp.children.size() - 1);
			}

			flag = false;
			for (int i = 0; i < temp.children.size(); i++) {
				if (temp.children.get(i).str.equals(input.food)) {
					temp = temp.children.get(i);
					flag = true;
				}
			}
			if (!flag) {
				temp.children.add(new Node(input.food));
				temp = temp.children.get(temp.children.size() - 1);
			}

			temp.children.add(new Node(Integer.toString(input.score)));
		}
	}

	static class Input {
		String lang;
		String job;
		String degree;
		String food;
		int score;

		public Input(String lang, String job, String degree, String food, int score) {
			this.lang = lang;
			this.job = job;
			this.degree = degree;
			this.food = food;
			this.score = score;
		}
	}

	static class Node {
		String str;
		ArrayList<Node> children;

		Node(String str) {
			this.str = str;
		}
	}
}