package Codility;

public class Lesson16_MaxNonoverlappingSegments_Main {
    public static void main(String[] args) {
        int solution = solution(new int[]{1, 3, 7, 9, 9}, new int[]{5, 6, 8, 9, 10});
        System.out.println(solution);
    }

    private static int solution(int[] A, int[] B) {
        // 그리디
        // 같은 인덱스의 두 배열 값은 A <= B
        // B 배열의 값은 항상 뒤로 갈수록 크거나 같음
        // 직전 세그먼트에 속하지 않는 다음 세그먼트를 A배열 값으로만 판단하면 그 뒤의 세그먼트는 더 확인하지 않아도 됨. 어차피 겹침
        // N이 0이면 세그먼트 개수는 0

        if (A.length <= 0)
            return 0;

        int arraySize = A.length;
        int lastSegIdx = 0;
        int segCount = 1;

        for (int i = 1; i < arraySize; i++) {
            if (A[i] <= B[lastSegIdx])
                continue;
            segCount++;
            lastSegIdx = i;
        }

        return segCount;
    }
}
