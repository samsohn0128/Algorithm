package SOCAR;

import java.util.Arrays;
import java.util.stream.IntStream;

public class SOCAR2 {
    int[] index = new int[101];

    int calSwapCount(int[] numbers, int[] temp) {
        int[] numbersTemp = IntStream.of(numbers).toArray();
        Arrays.fill(index, 0);
        int cnt = 0;
        for (int i = 0; i < numbersTemp.length; i++) {
            index[numbersTemp[i]] = i;
        }

        for (int i = 0; i < numbersTemp.length; i++) {
            if (numbersTemp[i] != temp[i]) {
                int targetIndex = index[temp[i]];
                int curValue = numbersTemp[i];
                int targetValue = numbersTemp[targetIndex];
                numbersTemp[i] = targetValue;
                numbersTemp[targetIndex] = curValue;
                index[targetValue] = i;
                index[curValue] = targetIndex;
                cnt++;
            }
        }
        return cnt;
    }

    int solution(int[] numbers, int k) {
        int[] temp = new int[numbers.length];
        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < numbers.length; i++) {
            temp[i] = numbers[i];
            index[numbers[i]] = i;
        }
        Arrays.sort(temp);
        while (true) {
            boolean flag = true;
            for (int i = 0; i < temp.length - 1; i++) {
                if (Math.abs(temp[i] - temp[i + 1]) > k) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                answer = Math.min(answer, calSwapCount(numbers, temp));
            }
            if (!np(temp)) {
                break;
            }
        }


        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

    boolean np(int[] arr) {
        int i = arr.length - 1;
        while (i > 0 && arr[i - 1] >= arr[i]) {
            --i;
        }

        if (i == 0)
            return false;

        int j = arr.length - 1;
        while (arr[i - 1] >= arr[j]) {
            --j;
        }

        swap(arr, i - 1, j);

        int k = arr.length - 1;
        while (i < k) {
            swap(arr, i++, k--);
        }

        return true;
    }

    void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}