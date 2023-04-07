package Codility;

import java.util.HashSet;
import java.util.Set;

public class Lesson3_PermMissingElem_Main {
    public static void main(String[] args) {
        int solution = solution(new int[]{2, 3, 1, 5});
        System.out.println(solution);
    }

    private static int solution(int[] A) {
        // 순차 값 포함된 array에서 누락된 값 찾기
        // 효율 고려

        Set<Integer> allNumSet = new HashSet<>();

        for (int i = 1; i <= A.length + 1; i++) {
            allNumSet.add(i);
        }

        for (var v : A) {
            allNumSet.remove(v);
        }

        return allNumSet.stream().findAny().orElse(0);
    }
}
