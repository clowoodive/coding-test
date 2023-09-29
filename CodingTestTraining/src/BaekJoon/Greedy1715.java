package BaekJoon; // 제출시 제외

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 백준 1715 - 카드 정렬하기
 *
 * 그리디(Greedy)
 *
 * 가장 작은 묶음과 그다음 작은 묶음을 묶는 순서여야 최소 비교가 가능함을 파악해야 함.
 * 비교를 가정했기에 최소 묶음은 2개로 보고 구현.(하지만 문제의 조건은 1 <= N <= 100,000 이니 참고)
 *
 *
 * 콘솔 입력
 * 3
 * 10
 * 20
 * 40
 *
 * 결과 : 100
 * */

// 1회차
class Greedy1715 { // 제출시 Main으로 변경
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            pq.add(Integer.parseInt(br.readLine()));
        }

        int compareCount = 0;
        while (pq.size() > 1) {
            int pack1 = pq.poll();
            int pack2 = pq.poll();
            int compCnt = pack1 + pack2;
            compareCount += compCnt;

            pq.add(compCnt);
        }

        System.out.println(compareCount);
    }
}

// 최초
//class Greedy1715 { // 제출시 Main으로 변경
//    private static int n;
//
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        n = scanner.nextInt();
//
//        PriorityQueue<Integer> pQ = new PriorityQueue<>();
//
//        for (int i = 0; i < n; i++) {
//            pQ.add(scanner.nextInt());
//        }
//
//        int compareTotal = 0;
//
//        while (pQ.size() > 1) {
//            Integer packVal1 = pQ.remove();
//            Integer packVal2 = pQ.remove();
//            int compareSum = packVal1 + packVal2;
//            pQ.add(compareSum);
//            compareTotal += compareSum;
//        }
//
//        System.out.println(compareTotal);
//    }
//}