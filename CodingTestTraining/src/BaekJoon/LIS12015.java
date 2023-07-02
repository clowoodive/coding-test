package BaekJoon; // 제출시 제외

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 백준 12015 - 가장 긴 증가하는 부분 수열 2
 *
 * 최장 증가 부분 수열(LIS:Longest Increasing Sequence In O(n Log N))
 *
 * 1. Scanner를 사용하면 시간 초과 발생.
 * 2. BufferedReader를 사용해도 시간 초과 발생하며 다른 방식으로 풀이해야 함(수열 값이 아닌 길이만 도출하는 방식)
 *
 *
 * 콘솔 입력
 * 6
 * 10 20 10 30 20 50
 *
 * 결과 : 4
 * */

class LIS12015 { // 제출시 Main으로 변경
    private static int n;
    private static int[] numArray;
    private static int[] lengthMemory;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        String numStr = br.readLine();

        String[] strArray = numStr.split(" ");
        numArray = new int[n];
        lengthMemory = new int[n];
        int maxLength = 0;

        for (int i = 0; i < n; i++) {
            numArray[i] = Integer.parseInt(strArray[i]);
//            lengthMemory[i] = -1;
        }

        for (int i = 0; i < n; i++) {
            maxLength = Math.max(maxLength, calcDp(0));
        }


        System.out.println(maxLength);
    }

    private static int calcDp(int start) {
        int ret = 1;

        if (lengthMemory[start] > -1)
            return lengthMemory[start];

        for (int i = start + 1; i < n; i++) {
            if (numArray[i] > numArray[start]) {
                int length = calcDp(i);
                ret = lengthMemory[start] = Math.max(lengthMemory[start], length + 1);
            }
        }

        return ret;
    }
}