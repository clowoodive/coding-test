package BaekJoon; // 제출시 제외

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 백준 10026 - 적록색약
 *
 * 깊이 우선 탐색(Depth First Search, DFS)
 *
 * 1. 메모리 계산.
 * 2. 적록색약/아닌사람이 보는 구역 구분하는 방법.
 *
 * 콘솔 입력
 * 5
 * RRRBB
 * GGBBB
 * BBBRR
 * BBRRR
 * RRRRR
 *
 *
 * 결과 :
 * 4 3
 *
 * */


// 1차
class DFS10026 { // 제출시 Main으로 변경

    private static int N = 0;

    private static char[][] drawing;

    private static boolean[][] visited;

    private static char sameColorFirst = 'R';

    private static char sameColorSecond = 'G';

    public static void main(String[] args) throws IOException {
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();

        // BufferedReader & StringTokenizer
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 가로 세로 줄 수
        N = Integer.parseInt(st.nextToken());

        drawing = new char[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String strLine = br.readLine();
            for (int j = 0; j < N; j++) {
                drawing[i][j] = strLine.charAt(j);
            }
        }

        int normalCount = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j] == true)
                    continue;

                recursionColor(i, j, drawing[i][j]);
                normalCount++;
            }
        }

        for (int i = 0; i < N; i++) {
            Arrays.fill(visited[i], false);
        }

        int weakCount = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j] == true)
                    continue;

                recursionColor(i, j, drawing[i][j]);
                weakCount++;
            }
        }


        System.out.println(normalCount + " " + weakCount);
    }

    public static void recursionColor(int i, int j, char nc) {
        if (i < 0 || j < 0 | i >= N || j >= N)
            return;

        if (visited[i][j] == true)
            return;

        if (drawing[i][j] != nc)
            return;

        visited[i][j] = true;

        recursionColor(i - 1, j, nc);
        recursionColor(i + 1, j, nc);
        recursionColor(i, j - 1, nc);
        recursionColor(i, j + 1, nc);

        if (nc == sameColorFirst)
            drawing[i][j] = sameColorSecond;
    }
}