package Codility;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class CyclicRotation_Main {
    public static void main(String[] args) {
        int[] solution = solution(new int[]{3, 8, 9, 7, 6}, 3);
        System.out.println(Arrays.toString(solution));
    }

    private static int[] solution(int[] A, int K) {
        // 반복 회전 하지 않게
        // 회전 한 만큼 array를 잘라서 앞에 붙임
        if (A.length <= 1 || K == 0)
            return A;

        int shiftCnt = K % A.length;
        IntStream limit = Arrays.stream(A).limit(A.length - shiftCnt);
        IntStream skip = Arrays.stream(A).skip(A.length - shiftCnt);
        int[] ints = Stream.concat(skip.boxed(), limit.boxed()).mapToInt(m -> m).toArray();

        return ints;
    }
}
