package BaekJoon; // 제출시 제외

import java.util.Scanner;

/*
 * 백준 9663 - N-Queen
 *
 * 완전탐색(BruteForce)
 *
 * n중첩 for 문이 되기에 재귀로 해결 해야 할듯.
 * n x n 보드에서 n개를 놓아야 하는데 같은 행에는 놓을 수 없으므로 행 마다 1개씩 존재해야만 n개를 놓을수 있다는 것을 발견해야 함.
 * 먼저 놓여진 퀸의 좌표를 매번 탐색해서 타임오버가 나는 것이기에 백트래킹 필요.
 * 1차원 배열로 표현하거나(행은 index, 열은 array[index] 값), 먼저 놓여진 좌표를 따로 관리한 뒤 "수식으로 검사"를 해야 함.
 *
 * 콘솔 입력
 * 8
 * 결과 : 92
 * */
class BruteForce9663 { // 제출시 Main으로 변경

    private static int[] yBoard;

    private static int caseCount;

    private static int n;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();

        yBoard = new int[n];

        putQueen(0);

        System.out.println(caseCount);
    }

    private static void putQueen(int indexY) {
        if (indexY >= n) {
            caseCount++;
            return;
        }

        for (int x = 0; x < n; x++) {
            if (canPut(indexY, x)) {
                yBoard[indexY] = x;
                putQueen(indexY + 1);
            }
        }
    }

    private static boolean canPut(int indexY, int x) {
        // indexY - 1 까지의 좌표들과 겹치는지 검사

        for (int i = 0; i < indexY; i++) {
            // 위쪽 밑 같은 행은 앞단계에서 이미 검증됨

            // 같은 열에 있는 경우
            if (yBoard[i] == x)
                return false;

            // 오른쪽 대각선에 있는 경우
            if (indexY - i == x - yBoard[i])
                return false;

            // 왼쪽 아래 대각선에 있는 경우
            if (indexY - i + x - yBoard[i] == 0)
                return false;
        }
        return true;
    }
}

// 최초 구현 버전 - 로직은 맞으나 최적화가 덜되었고 타임오버 발생

//class BruteForce9663 { // 제출시 Main으로 변경
//
//    // put 체크 좌표. x측 왼쪽을 포함한 위쪽 세 방향만 체크하면 됨.
//    private static int[] yPos = {-1, -1, -1, 0};
//    private static int[] xPos = {0, -1, 1, -1};
//
//    private static int[][] board;
//
//    private static int caseCount;
//
//    private static int n;
//
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        n = scanner.nextInt();
//
//        board = new int[n][n];
//
//
//        putQueen(0, 0);
//
//        System.out.println(caseCount);
//    }
//
//
//    private static void putQueen(int startY, int putCnt) {
//        if (putCnt >= n) {
//            caseCount++;
//            return;
//        }
//
//        if (startY >= n)
//            return;
//
//        for (int y = startY; y < n; y++) {
//            for (int x = 0; x < n; x++) {
//                if (canPut(y, x)) {
//                    board[y][x] = 1;
//                    putQueen(y + 1, putCnt + 1); // y + 1해도 됨. 가로는 더이상 놓을 수 없으므로.
//                    board[y][x] = 0;
//                }
//            }
//        }
//    }
//
//    private static boolean canPut(int y, int x) {
//        int checkY;
//        int checkX;
//        for (int i = 1; i <= y || i <= x; i++) {
//            for (int j = 0; j < yPos.length; j++) {
//                checkY = y + yPos[j] * i;
//                checkX = x + xPos[j] * i;
//                if (checkY >= n || checkY < 0 || checkX >= n | checkX < 0)
//                    continue;
//
//                if (board[checkY][checkX] != 0) {
//                    return false;
//                }
//            }
//        }
//
//        return true;
//    }
//}


// 검색으로 참고한 코드

//class BruteForce9663 { // 제출시 Main으로 변경
//
//    public static int[] arr;
//    public static int N;
//    public static int count = 0;
//
//    public static void main(String[] args) {
//
//        Scanner in = new Scanner(System.in);
//        N = in.nextInt();
//        arr = new int[N];
//
//        nQueen(0);
//        System.out.println(count);
//
//    }
//
//    public static void nQueen(int depth) {
//        // 모든 원소를 다 채운 상태면 count 증가 및 return
//        if (depth == N) {
//            count++;
//            return;
//        }
//
//        for (int i = 0; i < N; i++) {
//            arr[depth] = i;
//            // 놓을 수 있는 위치일 경우 재귀호출
//            if (Possibility(depth)) {
//                nQueen(depth + 1);
//            }
//        }
//
//    }
//
//    public static boolean Possibility(int col) {
//
//        for (int i = 0; i < col; i++) {
//            // 해당 열의 행과 i열의 행이 일치할경우 (같은 행에 존재할 경우)
//            if (arr[col] == arr[i]) {
//                return false;
//            }
//
//            /*
//             * 대각선상에 놓여있는 경우
//             * (열의 차와 행의 차가 같을 경우가 대각선에 놓여있는 경우다)
//             */
//            else if (Math.abs(col - i) == Math.abs(arr[col] - arr[i])) {
//                return false;
//            }
//        }
//
//        return true;
//    }
//}
