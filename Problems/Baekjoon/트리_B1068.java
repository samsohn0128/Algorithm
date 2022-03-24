import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 트리_B1068 {
    private static int N, erase;
    private static int[] parents;
    private static int[] childrenNum;

    public static void main(String[] args) throws Exception {
        init();
        int answer = 0;
        for (int i = 0; i < N; i++) {
            if (parents[i] != -1) {
                childrenNum[parents[i]]++;
            }
        }
        if (parents[erase] != -1)
            childrenNum[parents[erase]]--;
        for (int i = 0; i < N; i++) {
            if (childrenNum[i] == 0) {
                int node = i;
                boolean flag = true;
                while (parents[node] != -1) {
                    if (node == erase) {
                        flag = false;
                        break;
                    }
                    node = parents[node];
                }
                if (flag) {
                    if (node != erase) {
                        answer++;
                    }
                }
            }
        }
        System.out.println(answer);
    }

    private static void init() throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        parents = new int[N];
        childrenNum = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            parents[i] = Integer.parseInt(st.nextToken());
        }
        erase = Integer.parseInt(br.readLine());
    }
}