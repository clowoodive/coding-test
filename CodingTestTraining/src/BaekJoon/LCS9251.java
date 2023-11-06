package BaekJoon; // 제출시 제외

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 백준 9251 - LCS(Longest Common Subsequence)
 *
 * DP
 *
 *
 * 콘솔 입력 :
 * ACAYKP
 * CAPCAK
 *
 * 결과 : 4
 *
 * */
class LCS9251 { // 제출시 Main으로 변경

    private static char firstCharAry[];
    private static char secondCharAry[];

    private static int xMax, yMax;

    private static int lcs[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        firstCharAry = br.readLine().toCharArray();
        secondCharAry = br.readLine().toCharArray();

        xMax = firstCharAry.length;
        yMax = secondCharAry.length;

        lcs = new int[yMax + 1][xMax + 1];
        for (int y = 1; y < yMax + 1; y++) {
            for (int x = 1; x < xMax + 1; x++) {
                if (firstCharAry[x - 1] == secondCharAry[y - 1]) {
                    // 같으면 직전 같은 개수(대각선) + 1
                    lcs[y][x] = lcs[y - 1][x - 1] + 1;
                } else {
                    // 다르면 이전 값중 큰값(왼/위) 유지
                    lcs[y][x] = Math.max(lcs[y][x - 1], lcs[y - 1][x]);
                }
            }
        }

        System.out.println(lcs[yMax][xMax]);
    }
}
