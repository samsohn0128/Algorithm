import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class 상어초등학교_B21608 {
    private static int N;
    private static final Map<Integer, Set<Integer>> bestFriendsMap = new LinkedHashMap<>();
    private static int[][] seats;
    private static final int[] dy = {-1, 1, 0, 0};
    private static final int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        init();
        String answer = solution();
        System.out.println(answer);
    }

    private static void init() throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        seats = new int[N + 1][N + 1];
        for (int i = 0; i < N * N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            Set<Integer> bestFriendSet = new HashSet<>();
            int studentNumber = Integer.parseInt(st.nextToken());
            bestFriendSet.add(Integer.parseInt(st.nextToken()));
            bestFriendSet.add(Integer.parseInt(st.nextToken()));
            bestFriendSet.add(Integer.parseInt(st.nextToken()));
            bestFriendSet.add(Integer.parseInt(st.nextToken()));

            bestFriendsMap.put(studentNumber, bestFriendSet);
        }

        br.close();
    }

    private static String solution() {
        StringBuilder sb = new StringBuilder();
        bestFriendsMap.forEach(상어초등학교_B21608::setSeats);
        sb.append(calculateSatisfaction());
        return sb.toString();
    }

    private static void setSeats(int studentNumber, Set<Integer> bestFriendSet) {
        int maxBestFriends = -1;
        int maxEmptySeats = -1;
        int maxY = 0;
        int maxX = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (seats[i][j] == 0) {
                    int[] bestFriendsAndEmptySeats = getBestFriendsAndEmptySeats(bestFriendSet, i, j);
                    if (maxBestFriends < bestFriendsAndEmptySeats[0]) {
                        maxBestFriends = bestFriendsAndEmptySeats[0];
                        maxEmptySeats = bestFriendsAndEmptySeats[1];
                        maxY = i;
                        maxX = j;
                    } else if (maxBestFriends == bestFriendsAndEmptySeats[0]) {
                        if (maxEmptySeats < bestFriendsAndEmptySeats[1]) {
                            maxEmptySeats = bestFriendsAndEmptySeats[1];
                            maxY = i;
                            maxX = j;
                        }
                    }
                }
            }
        }
        seats[maxY][maxX] = studentNumber;
    }

    private static int[] getBestFriendsAndEmptySeats(Set<Integer> bestFriendSet, int y, int x) {
        int[] bestFriendsAndEmptySeats = new int[2];
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (isInSeats(ny, nx)) {
                if (seats[ny][nx] == 0) {
                    bestFriendsAndEmptySeats[1]++;
                } else if (bestFriendSet.contains(seats[ny][nx])) {
                    bestFriendsAndEmptySeats[0]++;
                }
            }
        }
        return bestFriendsAndEmptySeats;
    }

    private static int calculateSatisfaction() {
        int satisfaction = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                Set<Integer> bestFriendSet = bestFriendsMap.get(seats[i][j]);
                int numberOfBestFriends = getBestFriendsAndEmptySeats(bestFriendSet, i, j)[0];
                if (numberOfBestFriends != 0) {
                    satisfaction += Math.pow(10, numberOfBestFriends - 1);
                }
            }
        }
        return satisfaction;
    }

    private static boolean isInSeats(int y, int x) {
        return 0 < y && y <= N && 0 < x && x <= N;
    }
}