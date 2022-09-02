import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

class 좌표압축_B18870 {

    private static int numberOfCoordinates;
    private static int[] coordinates;

    public static void main(String[] args) throws Exception {
        init();
        String answer = solution();
        System.out.println(answer);
    }

    private static void init() throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        numberOfCoordinates = Integer.parseInt(br.readLine());

        coordinates = new int[numberOfCoordinates];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < numberOfCoordinates; i++) {
            coordinates[i] = Integer.parseInt(st.nextToken());
        }
    }

    private static String solution() {
        int[][] coordinatesWithIndex = new int[numberOfCoordinates][2];
        for (int i = 0; i < coordinates.length; i++) {
            coordinatesWithIndex[i][0] = coordinates[i];
            coordinatesWithIndex[i][1] = i;
        }
        Arrays.sort(coordinates);
        Arrays.sort(coordinatesWithIndex, Comparator.comparingInt(coordinateWithIndex -> coordinateWithIndex[0]));
        int compressedNumber = 0;
        for (int i = 0; i < numberOfCoordinates; i++) {
            if (i == 0) {
                coordinatesWithIndex[i][0] = compressedNumber;
            } else if (coordinatesWithIndex[i][0] != coordinates[i - 1]) {
                coordinatesWithIndex[i][0] = ++compressedNumber;
            } else {
                coordinatesWithIndex[i][0] = compressedNumber;
            }
        }
        Arrays.sort(coordinatesWithIndex, Comparator.comparingInt(coordinateWithIndex -> coordinateWithIndex[1]));

        StringBuilder sb = new StringBuilder();
        for (int[] coordinateWithIndex : coordinatesWithIndex) {
            sb.append(coordinateWithIndex[0]);
            sb.append(" ");
        }
        return sb.toString();
    }
}