class KAKAO2021_합승택시요금 {

	final int INF = 201 * 100000;
	int[][] edges = new int[201][201];
	int[][] dist = new int[201][201];

	public int solution(int n, int s, int a, int b, int[][] fares) {
		for (int i = 0; i < fares.length; i++) {
			edges[fares[i][0]][fares[i][1]] = fares[i][2];
			edges[fares[i][1]][fares[i][0]] = fares[i][2];
		}
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (i == j)
					dist[i][j] = 0;
				else if (edges[i][j] > 0)
					dist[i][j] = edges[i][j];
				else
					dist[i][j] = INF;
			}
		}

		for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					dist[i][j] = Integer.min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			}
		}
		int answer = dist[s][a] + dist[s][b];
		for (int i = 1; i <= n; i++) {
			answer = Integer.min(answer, dist[s][i] + dist[i][a] + dist[i][b]);
		}
		return answer;
	}
}