package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 백준 2042 - 구간 합 구하기
 *
 * Segment Tree/FenwickTree
 *
 * 콘솔 입력
 * 5 2 2
 * 1
 * 2
 * 3
 * 4
 * 5
 * 1 3 6
 * 2 2 5
 * 1 5 2
 * 2 3 5
 *
 * 결과 :
 * 17
 * 12
 *
 * */
class SegTree2042 {

    private static int N;
    private static int M;
    private static int K;

    private static long num[];
    private static long fenwick[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());


        num = new long[N + 1];
        fenwick = new long[N + 1];
        for (int i = 1; i <= N; i++) { // 인덱스의 2진값으로 처리하기에 1~N까지 사용
            num[i] = Long.parseLong(new StringTokenizer(br.readLine()).nextToken());
            updateFenwick(i, num[i]);
        }

        int a, b;
//        long c;
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
//            c = Long.parseLong(st.nextToken());

            if (a == 1) {
                long c = Long.parseLong(st.nextToken());
                long oldVal = num[b];
                num[b] = c;
                // 트리 업데이트
                updateFenwick(b, c - oldVal);
            } else if (a == 2) {
                int c = Integer.parseInt(st.nextToken());
                // 합 구하기
                long sum = summingFenWick(c) - summingFenWick(b - 1);
                // 출력하기기
                System.out.println(sum);
            }

        }
    }

    private static void updateFenwick(int idx, long val) {
        while (idx < N + 1) { // 또는 fenwick array의 크기
            fenwick[idx] += val;
            idx = idx + (idx & -idx);
        }
    }

    private static long summingFenWick(int idx) {
        long sum = 0;
        while (idx > 0) {
            sum += fenwick[idx];
            idx = idx - (idx & -idx);
        }

        return sum;
    }
}