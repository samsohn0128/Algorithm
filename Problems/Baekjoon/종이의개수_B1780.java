import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 종이의개수_B1780 {
    private static int lengthOfSide;
    private static int[][] coloredPapers;
    private static int[] paperCounts = new int[3];

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
        for (int i = 0; i < 3; i++) {
            sb.append(paperCounts[i]);
            sb.append("\n");
        }
        return sb.toString();
    }

    private static void countColoredPapers(int startY, int startX, int endY, int endX) {
        int color = checkOneColor(startY, startX, endY, endX);
        if (color == -2) {
            countColoredPapers(startY, startX, startY + (endY - startY) / 3, startX + (endX - startX) / 3);
            countColoredPapers(startY, startX + (endX - startX) / 3, startY + (endY - startY) / 3, startX + (endX - startX) * 2 / 3);
            countColoredPapers(startY, startX + (endX - startX) * 2 / 3, startY + (endY - startY) / 3, endX);

            countColoredPapers(startY + (endY - startY) / 3, startX, startY + (endY - startY) * 2 / 3, startX + (endX - startX) / 3);
            countColoredPapers(startY + (endY - startY) / 3, startX + (endX - startX) / 3, startY + (endY - startY) * 2 / 3, startX + (endX - startX) * 2 / 3);
            countColoredPapers(startY + (endY - startY) / 3, startX + (endX - startX) * 2 / 3, startY + (endY - startY) * 2 / 3, endX);

            countColoredPapers(startY + (endY - startY) * 2 / 3, startX, endY, startX + (endX - startX) / 3);
            countColoredPapers(startY + (endY - startY) * 2 / 3, startX + (endX - startX) / 3, endY, startX + (endX - startX) * 2 / 3);
            countColoredPapers(startY + (endY - startY) * 2 / 3, startX + (endX - startX) * 2 / 3, endY, endX);
        } else {
            paperCounts[color + 1]++;
        }
    }

    private static int checkOneColor(int startY, int startX, int endY, int endX) {
        int color = coloredPapers[startY][startX];
        for (int i = startY; i < endY; i++) {
            for (int j = startX; j < endX; j++) {
                if (coloredPapers[i][j] != color) {
                    color = -2;
                    break;
                }
            }
        }
        return color;
    }
}