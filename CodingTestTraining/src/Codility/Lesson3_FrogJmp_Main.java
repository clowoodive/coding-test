package Codility;

public class Lesson3_FrogJmp_Main {
    public static void main(String[] args) {
        int solution = solution(10, 85, 30);
        System.out.println(solution);
    }

    private static int solution(int X, int Y, int D) {
        // 현재 포지션 X, 현재와 같거나 큰 포지션 Y, 고정된 Y 거리만큼 점프 가능
        // Y 포지션을 만족하는 점프 횟수 리턴
        // X <= Y 인 경우 고려

        int needDistance = Y - X;
        int quotient = needDistance / D;
        int remain = needDistance % D;

        return remain == 0 ? quotient : quotient + 1;
    }
}
