package BaekJoon; // 제출시 제외

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 백준 12865 - 평범한 배낭
 *
 * 다이나믹 프로그래밍(DP)
 * top-down 방식 - 재귀
 * bottom-up 방식 - for loop
 *
 * https://st-lab.tistory.com/141
 * 1. 그림을 그려서 반복되는 sub problem을 찾고 점화식 작성하기.
 * 2. bottom-up 방식에서 메모이제이션을 통한 메모리 사용량 줄이는 방식으로 추가 구현 해볼 것.
 *
 * 콘솔 입력
 * 4 7
 * 6 13
 * 4 8
 * 3 6
 * 5 12
 *
 *
 * 결과 :
 * 4
 *
 * */


// top-down 재귀
class DynamicProgramming12865 { // 제출시 Main으로 변경

    private static int N = 0;

    private static int K = 0;

    private static int W[];

    private static int V[];

    private static Integer dp[][];

    public static void main(String[] args) throws IOException {
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();

        // BufferedReader & StringTokenizer
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 물건의 개수
        N = Integer.parseInt(st.nextToken());

        // 가방의 허용 무게
        K = Integer.parseInt(st.nextToken());

        // 물건
        W = new int[N + 1];
        V = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            StringTokenizer strToken = new StringTokenizer(br.readLine());
            W[i] = Integer.parseInt(strToken.nextToken());
            V[i] = Integer.parseInt(strToken.nextToken());
        }

        dp = new Integer[N + 1][K + 1];

        System.out.println(dp_bottomUp(N, K));
    }

    private static int dp_bottomUp(int im, int km) {
        int retVal = 0;

        for (int i = 0; i <= im; i++) {
            for (int k = 0; k <= km; k++) {
                if (i < 1 || k < 1)
                    dp[i][k] = 0;
                else if (W[i] > k) {
                    dp[i][k] = dp[i - 1][k];
                } else {
                    dp[i][k] = Math.max(dp[i - 1][k], V[i] + dp[i - 1][k - W[i]]);
                }
                retVal = Math.max(retVal, dp[i][k]);
            }
        }

        return retVal;
    }
}

// top-down 재귀
//class DynamicProgramming12865 { // 제출시 Main으로 변경
//
//    private static int N = 0;
//
//    private static int K = 0;
//
//    private static int W[];
//
//    private static int V[];
//
//    private static Integer dp[][];
//
//    public static void main(String[] args) throws IOException {
////        Scanner scanner = new Scanner(System.in);
////        int n = scanner.nextInt();
//
//        // BufferedReader & StringTokenizer
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        StringTokenizer st = new StringTokenizer(br.readLine());
//
//        // 물건의 개수
//        N = Integer.parseInt(st.nextToken());
//
//        // 가방의 허용 무게
//        K = Integer.parseInt(st.nextToken());
//
//        // 물건
//        W = new int[N + 1];
//        V = new int[N + 1];
//        for (int i = 1; i <= N; i++) {
//            StringTokenizer strToken = new StringTokenizer(br.readLine());
//            W[i] = Integer.parseInt(strToken.nextToken());
//            V[i] = Integer.parseInt(strToken.nextToken());
//        }
//
//        dp = new Integer[N + 1][K + 1];
//
//        System.out.println(dp_topdown(N, K));
//    }
//
//    private static int dp_topdown(int i, int k) {
//        if (i < 1 || k < 1) return 0;
//
//        if (dp[i][k] == null) {
//            if (W[i] > k) {
//                dp[i][k] = dp_topdown(i - 1, k);
//            } else if (W[i] <= k && i > 0) { // 항상 참이므로 조건문 제거?
//                dp[i][k] = Math.max(dp_topdown(i - 1, k), V[i] + dp_topdown(i - 1, k - W[i]));
//            }
//        }
//
//        return dp[i][k];
//    }
//}