import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 구간합구하기5_B11660 {

    private static int sizeOfGird;
    private static int numberOfSums;
    private static int[][] grid;
    private static int[][] accumulateSums;
    private static int[][] sections;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        init(br);
        String answer = solution();
        System.out.println(answer);
    }

    private static void init(BufferedReader br) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        sizeOfGird = Integer.parseInt(st.nextToken());
        numberOfSums = Integer.parseInt(st.nextToken());

        grid = new int[sizeOfGird + 1][sizeOfGird + 1];
        accumulateSums = new int[sizeOfGird + 1][sizeOfGird + 1];
        for (int i = 1; i <= sizeOfGird; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= sizeOfGird; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        sections = new int[numberOfSums][4];
        for (int i = 0; i < numberOfSums; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                sections[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static String solution() {
        StringBuilder sb = new StringBuilder();
        setAccumulateSum();
        for (int i = 0; i < numberOfSums; i++) {
            int sum = getSum(sections[i][0], sections[i][1], sections[i][2], sections[i][3]);
            sb.append(sum);
            sb.append("\n");
        }
        return sb.toString();
    }

    private static void setAccumulateSum() {
        accumulateSums[1][1] = grid[1][1];
        for (int i = 2; i <= sizeOfGird; i++) {
            accumulateSums[i][1] = accumulateSums[i - 1][1] + grid[i][1];
            accumulateSums[1][i] = accumulateSums[1][i - 1] + grid[1][i];
        }
        for (int i = 2; i <= sizeOfGird; i++) {
            for (int j = 2; j <= sizeOfGird; j++) {
                accumulateSums[i][j] = accumulateSums[i - 1][j] + accumulateSums[i][j - 1] - accumulateSums[i - 1][j - 1] + grid[i][j];
            }
        }
    }

    private static int getSum(int startY, int startX, int endY, int endX) {
        return accumulateSums[endY][endX] - accumulateSums[endY][startX - 1] - accumulateSums[startY - 1][endX] + accumulateSums[startY - 1][startX - 1];
    }
}