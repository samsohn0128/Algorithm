import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class 킹_퀸_룩_비숍_나이트_폰_B3003 {

    private static final int numberOfKing = 1;
    private static final int numberOfQueen = 1;
    private static final int numberOfRook = 2;
    private static final int numberOfBishop = 2;
    private static final int numberOfKnight = 2;
    private static final int numberOfPawn = 8;

    private static int king, queen, rook, bishop, knight, pawn;

    public static void main(String[] args) throws Exception {
        init();
        String answer = solution();
        System.out.println(answer);
    }

    private static void init() throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        king = Integer.parseInt(st.nextToken());
        queen = Integer.parseInt(st.nextToken());
        rook = Integer.parseInt(st.nextToken());
        bishop = Integer.parseInt(st.nextToken());
        knight = Integer.parseInt(st.nextToken());
        pawn = Integer.parseInt(st.nextToken());
    }

    private static String solution() {
        StringBuilder sb = new StringBuilder();
        sb.append(numberOfKing - king);
        sb.append(" ");
        sb.append(numberOfQueen - queen);
        sb.append(" ");
        sb.append(numberOfRook - rook);
        sb.append(" ");
        sb.append(numberOfBishop - bishop);
        sb.append(" ");
        sb.append(numberOfKnight - knight);
        sb.append(" ");
        sb.append(numberOfPawn - pawn);
        return sb.toString();
    }
}