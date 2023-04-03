package Codility;

import java.util.ArrayList;
import java.util.List;

public class BinaryGap_Main {
    public static void main(String[] args) {
        int solution = solution(1056);
        System.out.println(solution);
    }

    private static int solution(int N) {
        // Implement your solution here
        // 갭이 없으면 0
        // 가장 큰 갭수 출력
        // 바이너리 스트링으로 변경해서 처리. 비트연산으로 해도 됨
        int zeroStartIdx = 0;
        List<Integer> gaps = new ArrayList<>();
        String binStr = Integer.toBinaryString(N);

        for (int i = 0; i < binStr.length(); i++) {
            char binChar = binStr.charAt(i);
            if (binChar == '1') {
                if (zeroStartIdx > 0 && zeroStartIdx < i) {
                    gaps.add(i - zeroStartIdx);
                }
                zeroStartIdx = i + 1;
            }
        }

        return gaps.stream().mapToInt(gap -> gap).max().orElse(0);
    }
}
