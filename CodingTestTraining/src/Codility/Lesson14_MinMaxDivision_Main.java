package Codility;

public class Lesson14_MinMaxDivision_Main {
    public static void main(String[] args) {
        int solution = solution(3, 5, new int[]{2, 1, 5, 1, 2, 2, 2});
        System.out.println(solution);
    }

    private static int solution(int K, int M, int[] A) {
        int lowerBound = 0;
        int upperBound = 0;
        for (int i = 0; i < A.length; i++) {
            upperBound += A[i];
            lowerBound = Math.max(lowerBound, A[i]);
        }
        int result = upperBound;
        while (lowerBound <= upperBound) {
            int candidateMinimalLargeSum = (lowerBound + upperBound) / 2;
            int blocks = 1;
            int sum = 0;
            for (int i = 0; i < A.length; i++) {
                if (sum + A[i] > candidateMinimalLargeSum) {
                    blocks++;
                    sum = A[i];
                } else {
                    sum += A[i];
                }
            }
            if (blocks <= K) {
                result = candidateMinimalLargeSum;
                upperBound = candidateMinimalLargeSum - 1;
            } else {
                lowerBound = candidateMinimalLargeSum + 1;
            }
        }
        return result;

    }
}
