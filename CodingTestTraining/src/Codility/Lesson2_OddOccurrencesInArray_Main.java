package Codility;

import java.util.HashSet;
import java.util.Set;

public class Lesson2_OddOccurrencesInArray_Main {
    public static void main(String[] args) {
        int solution = solution(new int[]{9, 3, 9, 3, 9, 7, 9});
        System.out.println(solution);
    }

    private static int solution(int[] A) {
        Set<Integer> checkSet = new HashSet<>();

        for (int v : A) {
            if (checkSet.contains(v))
                checkSet.remove(v);
            else
                checkSet.add(v);
        }

        return checkSet.stream().findAny().orElse(0);
    }
}
