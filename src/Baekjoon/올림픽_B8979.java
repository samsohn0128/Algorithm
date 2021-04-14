package Baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class 올림픽_B8979 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		Country[] countries = new Country[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			countries[i] = new Country(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}

		Arrays.sort(countries, new Comparator<Country>() {
			@Override
			public int compare(Country c1, Country c2) {
				if (c1.gold == c2.gold) {
					if (c1.silver == c2.silver) {
						if (c1.bronze == c2.bronze) {
							return c1.id - c2.id;
						} else {
							return c2.bronze - c1.bronze;
						}
					} else {
						return c2.silver - c1.silver;
					}
				} else {
					return c2.gold - c1.gold;
				}
			}
		});

		countries[0].rank = 1;
		for (int i = 1; i < N; i++) {
			if (countries[i].gold == countries[i - 1].gold && countries[i].silver == countries[i - 1].silver
					&& countries[i].bronze == countries[i - 1].bronze) {
				countries[i].rank = countries[i - 1].rank;
				countries[i].cnt = countries[i - 1].cnt + 1;
			} else {
				countries[i].rank = countries[i - 1].rank + countries[i - 1].cnt;
			}
		}
		
		for (int i = 0; i < N; i++) {
			if(countries[i].id == K) {
				System.out.println(countries[i].rank);
				break;
			}
		}

		br.close();
	}

	static class Country {
		public int id;
		public int gold;
		public int silver;
		public int bronze;
		public int cnt = 1;
		public int rank;

		public Country(int id, int gold, int silver, int bronze) {
			this.id = id;
			this.gold = gold;
			this.silver = silver;
			this.bronze = bronze;
		}

		@Override
		public String toString() {
			return "[id=" + id + ", gold=" + gold + ", silver=" + silver + ", bronze=" + bronze + ", cnt=" + cnt
					+ ", rank=" + rank + "]";
		}
		
	}
}
