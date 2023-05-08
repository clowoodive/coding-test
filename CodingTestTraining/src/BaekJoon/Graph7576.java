package BaekJoon; // 제출시 제외

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

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
class Graph7576 { // 제출시 Main으로 변경

    private static int[][] tray;

    private static int m, n;

    private static Queue<Section> queue = new LinkedList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        m = scanner.nextInt();
        n = scanner.nextInt();

        tray = new int[n][m];
        int ingCount = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                tray[i][j] = scanner.nextInt();
                if (tray[i][j] == 0)
                    ingCount++;
                if (tray[i][j] == 1)
                    queue.add(new Section(j, i));
            }
        }

        // 모두 익어 있는 상태인 경우
        if (ingCount == 0) {
            System.out.println(0);
            return;
        }

        Bfs();

        int days = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (tray[i][j] == 0) {
                    System.out.println(-1); // 모두 익지 못하는 상황
                    return;
                }

                days = Math.max(days, tray[i][j]);
            }
        }

        System.out.println(days - 1); // 시작할때부터 모두 익어있는 경우(0)와 총 걸린 일수 도출 가능
    }


    private static void Bfs() {
        int[] xPos = new int[]{0, 0, -1, 1};
        int[] yPos = new int[]{-1, 1, 0, 0};

        while (!queue.isEmpty()) {
            Section complete = queue.poll();

            for (int i = 0; i < xPos.length; i++) {
                int nextX = complete.x + xPos[i];
                int nextY = complete.y + yPos[i];
                if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n && tray[nextY][nextX] == 0) {
                    queue.add(new Section(nextX, nextY));
                    tray[nextY][nextX] = tray[complete.y][complete.x] + 1;
                }
            }

        }
    }

    private static class Section {
        int x;
        int y;

        public Section(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
