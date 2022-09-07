import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 색종이만들기_B2630 {
    private static int lengthOfSide;
    private static int[][] coloredPapers;

    private static int blueCount, whiteCount;

    public static void main(String[] args) throws Exception {
        BufferedReader br = setBufferedReader();
        init(br);
        String answer = solution();
        System.out.println(answer);
        br.close();
    }

    private static BufferedReader setBufferedReader() throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        return new BufferedReader(new InputStreamReader(System.in));
    }

    private static void init(BufferedReader br) throws Exception {
        lengthOfSide = Integer.parseInt(br.readLine());
        coloredPapers = new int[lengthOfSide][];
        for (int i = 0; i < lengthOfSide; i++) {
            coloredPapers[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(str -> Integer.parseInt(str)).toArray();
        }
    }

    private static String solution() {
        countColoredPapers(0, 0, lengthOfSide, lengthOfSide);
        StringBuilder sb = new StringBuilder();
        sb.append(whiteCount);
        sb.append("\n");
        sb.append(blueCount);
        return sb.toString();
    }

    private static void countColoredPapers(int startY, int startX, int endY, int endX) {
        int color = checkOneColor(startY, startX, endY, endX);
//        System.out.print("startY = " + startY);
//        System.out.print(" startX = " + startX);
//        System.out.print(" endY = " + endY);
//        System.out.print(" endX = " + endX);
//        System.out.println();
//        System.out.println("color = " + color);
        if (color == 0) {
            whiteCount++;
        } else if (color == 1) {
            blueCount++;
        } else {
            countColoredPapers(startY, startX, (startY + endY) / 2, (startX + endX) / 2);
            countColoredPapers(startY, (startX + endX) / 2, (startY + endY) / 2, endX);
            countColoredPapers((startY + endY) / 2, startX, endY, (startX + endX) / 2);
            countColoredPapers((startY + endY) / 2, (startX + endX) / 2, endY, endX);
        }
    }

    private static int checkOneColor(int startY, int startX, int endY, int endX) {
        int color = coloredPapers[startY][startX];
        for (int i = startY; i < endY; i++) {
            for (int j = startX; j < endX; j++) {
                if (coloredPapers[i][j] != color) {
                    color = -1;
                    break;
                }
            }
        }
        return color;
    }
}