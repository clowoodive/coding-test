package BaekJoon; // 제출시 제외

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 백준 7576 - 토마토
 *
 * 그래프이론(Graphs)
 *
 * BFS를 이용하여 익는 토마토의 좌표에 소요 되는 날의 수를 증감시켜 저장 하여 최단 소요 날 수 산출.
 * 시작부터 모두 익어있는 경우 또한 모든 알고리즘 처리 후에도 산출 가능하지만 효율성을 위해 미리 한번더 체크함.
 *
 * 콘솔 입력
 * 6 4
 * 0 0 0 0 0 0
 * 0 0 0 0 0 0
 * 0 0 0 0 0 0
 * 0 0 0 0 0 1
 * 결과 : 8
 * */
class BFS7576 { // 제출시 Main으로 변경

    private static int[][] tray;

    private static int M, N;

    private static Queue<int[]> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        tray = new int[N][M];

        int greenCount = 0;
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            for (int m = 0; m < M; m++) {
                int val = Integer.parseInt(st.nextToken());
                tray[n][m] = val;
                if (val == 1) {
                    queue.add(new int[]{n, m});
                } else if (val == 0)
                    greenCount++;
            }
        }

        // 모두 익어있을 경우
        if (greenCount == 0) {
            System.out.println(0);
            return;
        }

        int posVal = 0;
        while (queue.isEmpty() == false) {
            int[] pos = queue.poll();
            int n = pos[0];
            int m = pos[1];
            posVal = tray[n][m];

            if (n - 1 >= 0 && tray[n - 1][m] == 0) {
                tray[n - 1][m] = posVal + 1;
                queue.add(new int[]{n - 1, m});
                greenCount--;
            }

            if (n + 1 < N && tray[n + 1][m] == 0) {
                tray[n + 1][m] = posVal + 1;
                queue.add(new int[]{n + 1, m});
                greenCount--;
            }

            if (m - 1 >= 0 && tray[n][m - 1] == 0) {
                tray[n][m - 1] = posVal + 1;
                queue.add(new int[]{n, m - 1});
                greenCount--;
            }

            if (m + 1 < M && tray[n][m + 1] == 0) {
                tray[n][m + 1] = posVal + 1;
                queue.add(new int[]{n, m + 1});
                greenCount--;
            }
        }

        // 모두 익지 못하는 경우
        if (greenCount > 0) {
            System.out.println(-1);
            return;
        }

        System.out.println(posVal - 1);
    }
}

// 기존
//class BFS7576 { // 제출시 Main으로 변경
//
//    private static int[][] tray;
//
//    private static int m, n;
//
//    private static Queue<Section> queue = new LinkedList<>();
//
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        m = scanner.nextInt();
//        n = scanner.nextInt();
//
//        tray = new int[n][m];
//        int ingCount = 0;
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++) {
//                tray[i][j] = scanner.nextInt();
//                if (tray[i][j] == 0)
//                    ingCount++;
//                if (tray[i][j] == 1)
//                    queue.add(new Section(j, i));
//            }
//        }
//
//        // 모두 익어 있는 상태인 경우
//        if (ingCount == 0) {
//            System.out.println(0);
//            return;
//        }
//
//        Bfs();
//
//        int days = 0;
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++) {
//                if (tray[i][j] == 0) {
//                    System.out.println(-1); // 모두 익지 못하는 상황
//                    return;
//                }
//
//                days = Math.max(days, tray[i][j]);
//            }
//        }
//
//        System.out.println(days - 1); // 시작할때부터 모두 익어있는 경우(0)와 총 걸린 일수 도출 가능
//    }
//
//
//    private static void Bfs() {
//        int[] xPos = new int[]{0, 0, -1, 1};
//        int[] yPos = new int[]{-1, 1, 0, 0};
//
//        while (!queue.isEmpty()) {
//            Section complete = queue.poll();
//
//            for (int i = 0; i < xPos.length; i++) {
//                int nextX = complete.x + xPos[i];
//                int nextY = complete.y + yPos[i];
//                if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n && tray[nextY][nextX] == 0) {
//                    queue.add(new Section(nextX, nextY));
//                    tray[nextY][nextX] = tray[complete.y][complete.x] + 1;
//                }
//            }
//
//        }
//    }
//
//    private static class Section {
//        int x;
//        int y;
//
//        public Section(int x, int y) {
//            this.x = x;
//            this.y = y;
//        }
//    }
//}
