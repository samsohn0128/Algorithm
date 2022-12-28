import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 스타트택시_B19238 {
    private static BufferedReader br;
    private static int N, M, fuel;
    private static int[][] grid;
    private static Node startNode;
    private static List<Passenger> passengers;
    private static int[][][][] distnaces;
    private static final int[] dy = {-1, 1, 0, 0};
    private static final int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        init();
        String answer = solution();
        System.out.println(answer);
    }

    private static void init() throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());
        grid = new int[N + 1][N + 1];
        passengers = new LinkedList<>();
        distnaces = new int[N + 1][N + 1][N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
                for (int k = 1; k <= N; k++) {
                    for (int l = 1; l <= N; l++) {
                        distnaces[i][j][k][l] = -1;
                    }
                }
            }
        }
        st = new StringTokenizer(br.readLine());
        startNode = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int passengerY = Integer.parseInt(st.nextToken());
            int passengerX = Integer.parseInt(st.nextToken());
            int destinationY = Integer.parseInt(st.nextToken());
            int destinationX = Integer.parseInt(st.nextToken());
            passengers.add(new Passenger(new Node(passengerY, passengerX), new Node(destinationY, destinationX)));
        }

        br.close();
    }

    private static String solution() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (grid[i][j] == 0) {
                    calculateMinDistances(i, j);
                }
            }
        }
        sb.append(operateTaxi());

        return sb.toString();
    }

    private static int operateTaxi() {
        int taxiY = startNode.y;
        int taxiX = startNode.x;
        while (!passengers.isEmpty()) {
            int minPassengerIndex = findMinPassengerIndex(taxiY, taxiX);
            Passenger minPassenger = passengers.get(minPassengerIndex);
            passengers.remove(minPassengerIndex);
            int spentFuelFromTaxiToPassenger = distnaces[taxiY][taxiX][minPassenger.from.y][minPassenger.from.x];
            if (spentFuelFromTaxiToPassenger == -1) {
                return -1;
            }
            fuel -= spentFuelFromTaxiToPassenger;
            if (fuel <= 0) {
                return -1;
            }

            int spentFuelFromPassengerToDestination = distnaces[minPassenger.from.y][minPassenger.from.x][minPassenger.to.y][minPassenger.to.x];
            if (spentFuelFromPassengerToDestination == -1) {
                return -1;
            }
            fuel -= spentFuelFromPassengerToDestination;
            if (fuel < 0) {
                return -1;
            }
            fuel += 2 * spentFuelFromPassengerToDestination;
            taxiY = minPassenger.to.y;
            taxiX = minPassenger.to.x;
        }
        return fuel;
    }

    private static int findMinPassengerIndex(int taxiY, int taxiX) {
        int minDistance = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int i = 0; i < passengers.size(); i++) {
            Node curPassenger = passengers.get(i).from;
            if (minDistance > distnaces[taxiY][taxiX][curPassenger.y][curPassenger.x]) {
                minDistance = distnaces[taxiY][taxiX][curPassenger.y][curPassenger.x];
                minIndex = i;
            } else if (minDistance == distnaces[taxiY][taxiX][curPassenger.y][curPassenger.x]) {
                if (passengers.get(minIndex).from.y > curPassenger.y) {
                    minIndex = i;
                } else if (passengers.get(minIndex).from.y == curPassenger.y) {
                    if (passengers.get(minIndex).from.x > curPassenger.x) {
                        minIndex = i;
                    }
                }
            }
        }
        return minIndex;
    }

    private static void calculateMinDistances(int startY, int startX) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(startY, startX));
        distnaces[startY][startX][startY][startX] = 0;
        int size = 1;
        int weight = 1;
        while (!queue.isEmpty()) {
            Node temp = queue.poll();
            for (int i = 0; i < 4; i++) {
                int ny = temp.y + dy[i];
                int nx = temp.x + dx[i];
                if (isInGrid(ny, nx) && grid[ny][nx] == 0) {
                    if (distnaces[startY][startX][ny][nx] == -1) {
                        queue.offer(new Node(ny, nx));
                        distnaces[startY][startX][ny][nx] = weight;
                    }
                }
            }
            if (--size == 0) {
                size = queue.size();
                weight++;
            }
        }

    }

    private static boolean isInGrid(int y, int x) {
        return 0 < y && y <= N && 0 < x && x <= N;
    }

    private static class Node {
        private int y;
        private int x;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    private static class Passenger {
        private Node from;
        private Node to;

        public Passenger(Node from, Node to) {
            this.from = from;
            this.to = to;
        }
    }
}