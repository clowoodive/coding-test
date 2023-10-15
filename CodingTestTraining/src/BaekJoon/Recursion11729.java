package BaekJoon; // 제출시 제외

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 백준 11729 - 하노이 탑 이동 순서
 *
 * 재귀
 *
 * 1. 총 원판에서 -1개(가장큰 원판 제외)의 원판을 보조(목표가 아닌) 봉으로 옮기는 것이 목표.
 * 2. 그후에 가장 큰 원판을 목표로 옮김.
 * 3. 가장 큰 원판이 옮겨지면 아무판이나 위에 올릴 수 있기에, 신경쓰지 않고 -1개의 원판을 다시 보조를 이용해서 목표로 옮김.
 * 4. -1개의 원판들을 옮기는 플로우가 재귀적으로 이루어짐.
 *
 * 콘솔 입력
 * 3
 *
 * 결과 :
 * 7
 * 1 3
 * 1 2
 * 3 2
 * 1 3
 * 2 1
 * 2 3
 * 1 3
 *
 * */


// 1차
class Recursion11729 { // 제출시 Main으로 변경

    private static int N = 0;

    private static int moveCount = 0;

    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();

        // BufferedReader & StringTokenizer
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 원판 개수
        N = Integer.parseInt(st.nextToken());

        recursion(N, 1, 2, 3);

        System.out.println(moveCount);
        System.out.println(sb);
    }

    public static void recursion(int n, int source, int sub, int dest) {
        if (n == 1) {
            sb.append(source + " " + dest + "\n");
//            System.out.println(source + " " + dest);
            moveCount++;
            return;
        }

        recursion(n - 1, source, dest, sub);
        sb.append(source + " " + dest + "\n");
        moveCount++;
        recursion(n - 1, sub, source, dest);
    }
}