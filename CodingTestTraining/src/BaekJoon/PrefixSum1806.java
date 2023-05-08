package BaekJoon;

import java.util.Scanner;

/*
 * 백준 1806 - 부분합
 *
 * 누적 합(PrefixSum)
 *
 * 콘솔 입력
 * 10 15
 * 5 1 3 5 10 7 4 9 2 8
 *
 * */
class PrefixSum1806 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int arraySize = scanner.nextInt();
        int checkSum = scanner.nextInt();

        int[] inputArray = new int[arraySize + 1];
        int[] pSumAry = new int[arraySize + 1];
        for (int i = 1; i <= arraySize; i++) {
            inputArray[i] = scanner.nextInt();
            pSumAry[i] = inputArray[i] + pSumAry[i - 1];
        }

        int minLength = 0;

        for (int head = 1, tail = 0; head <= arraySize; head++) {
            if (pSumAry[head] - pSumAry[tail] >= checkSum) {
                while ((pSumAry[head] - pSumAry[tail] >= checkSum) && tail < head) {
                    tail++;
                }
                minLength = minLength == 0 ? head - tail + 1 : Math.min(minLength, head - tail + 1);
            }
        }

        System.out.println(minLength);
    }
}
