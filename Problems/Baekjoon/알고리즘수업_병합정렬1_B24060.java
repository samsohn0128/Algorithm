import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 알고리즘수업_B24060 {

    private static int sizeOfArr;
    private static int numberOfSaves;
    private static int[] arr;
    private static int saveCount;
    private static int answer = -1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = setBufferedReader();
        init(br);
        mergeSort(arr, 0, sizeOfArr - 1);
        System.out.println(answer);
        br.close();
    }

    private static BufferedReader setBufferedReader() throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        return new BufferedReader(new InputStreamReader(System.in));
    }

    private static void init(BufferedReader br) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        sizeOfArr = Integer.parseInt(st.nextToken());
        numberOfSaves = Integer.parseInt(st.nextToken());

        arr = new int[sizeOfArr];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < sizeOfArr; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }

    private static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int i = left;
        int j = mid + 1;

        int[] temp = new int[right - left + 1];
        int t = 0;
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[t++] = arr[i++];
            } else {
                temp[t++] = arr[j++];
            }
        }
        while (i <= mid) {
            temp[t++] = arr[i++];
        }
        while (j <= right) {
            temp[t++] = arr[j++];
        }
        for (int k = 0; k < temp.length; k++) {
            if (++saveCount == numberOfSaves) {
                answer = temp[k];
            }
            arr[left + k] = temp[k];
        }
    }
}