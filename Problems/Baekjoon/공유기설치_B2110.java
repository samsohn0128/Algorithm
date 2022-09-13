import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 공유기설치_B2110 {
    private static int numberOfHouses;
    private static int numberOfRouters;
    private static int[] houses;

    private static final int TOO_MANY = -2;

    public static void main(String[] args) throws Exception {
        BufferedReader br = setBufferedReader();
        init(br);
        int answer = solution();
        System.out.println(answer);
        br.close();
    }

    private static BufferedReader setBufferedReader() throws Exception {
        return new BufferedReader(new InputStreamReader(System.in));
    }

    private static void init(BufferedReader br) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        numberOfHouses = Integer.parseInt(st.nextToken());
        numberOfRouters = Integer.parseInt(st.nextToken());

        houses = new int[numberOfHouses];
        for (int i = 0; i < numberOfHouses; i++) {
            houses[i] = Integer.parseInt(br.readLine());
        }
    }

    private static int solution() {
        Arrays.sort(houses);
        int maxDistance = getDistance(houses[numberOfHouses - 1], houses[0]) + 1;
        int answer = binarySearch(maxDistance);
        return answer;
    }

    private static int binarySearch(int distance) {
        int left = 1;
        int right = distance;
        int answer = 0;
        while (left <= right) {
            int middle = (left + right) / 2;
            int minDistance = enoughRouters(middle);
            if (minDistance == TOO_MANY) {
                right = middle - 1;
            } else {
                answer = Math.max(answer, minDistance);
                left = middle + 1;
            }
        }
        return answer;
    }

    private static int enoughRouters(int distance) {
        int routerCount = numberOfRouters - 1;
        int curRouterIndex = 0;
        int minDistance = Integer.MAX_VALUE;
        for (int i = 1; i < numberOfHouses; i++) {
            int routerDistance = getDistance(houses[i], houses[curRouterIndex]);
            if (routerDistance >= distance) {
                --routerCount;
                if (routerCount < 0) {
                    break;
                } else {
                    minDistance = Math.min(minDistance, routerDistance);
                    curRouterIndex = i;
                }
            }
        }
        return routerCount > 0 ? TOO_MANY : minDistance;
    }

    private static int getDistance(int house1, int house2) {
        return Math.abs(house2 - house1);
    }
}