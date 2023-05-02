package BaekJoon;

import java.util.Scanner;

/*
 * 백준 11659 - 구간 합 구하기 4
 *
 * 누적 합(PrefixSum)
 *
 * 콘솔 입력
 * 5 3
 * 5 4 3 2 1
 * 1 3
 * 2 4
 * 5 5
 *
 * */
class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int arraySize = scanner.nextInt();
        int questionCount = scanner.nextInt();

        int[] inputArray = new int[arraySize + 1];
        int[] prefixSumArray = new int[arraySize + 1];
        for (int i = 1; i <= arraySize; i++) {
            inputArray[i] = scanner.nextInt();
            prefixSumArray[i] = inputArray[i] + prefixSumArray[i - 1];
        }

        for (int i = 0; i < questionCount; i++) {
            int startNum = scanner.nextInt();
            int endNum = scanner.nextInt();

            System.out.println(prefixSumArray[endNum] - prefixSumArray[startNum - 1]);
        }
    }
}
