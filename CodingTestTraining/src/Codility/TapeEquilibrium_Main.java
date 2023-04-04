package Codility;

import java.util.Arrays;

public class TapeEquilibrium_Main {
    public static void main(String[] args) {
        int solution = solution(new int[]{3, 1, 2, 4, 3});
        System.out.println(solution);
    }

    private static int solution(int[] A) {
        // 규칙 찾기
        // 효율 고려

        int leftSum = A[0];
        int rightSum = Arrays.stream(A).sum() - A[0];
        int minAbsDiff = Math.abs(leftSum - rightSum);

        for (int i = 1; i < A.length - 1; i++) {
            leftSum += A[i];
            rightSum -= A[i];
            var nowAbsDiff = Math.abs(leftSum - rightSum);
            if (minAbsDiff > nowAbsDiff)
                minAbsDiff = nowAbsDiff;
        }

        return minAbsDiff;
    }
}
