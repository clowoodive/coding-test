package BaekJoon; // 제출시 제외

import java.util.Scanner;

/*
 * 백준 2098 - 외판원 순회(TSP: Traveling Salesperson Problem)
 *
 * 비트마스킹(BitMask) & 다이나믹 프로그래밍(DP)
 *
 * 6개의 도시가 서로 이동 가능 할 때 (n-1)! =  120개 경로가 나오고 이중 최소 경로를 찾아야 함.
 * 13! 정도만 해도 1초가 넘어가기에 시간복잡도를 줄여야 함.
 * 출발도시를 어떤 도시로 하더라도 마지막에 출발도시로 돌아오는 순환이 발생하기에 아무도시나 지정해서 시작하면 됨.
 * 뒤에 방문한 도시부터 최소 비용을 메모리에 저장/갱신해가면서(DP) 다른 케이스에서 [현재까지 방문한 도시][현재 방문도시] 가 같을때 계산없이
 * 최소비용을 그대로 참조함으로써 시간복잡도를 줄임.
 * https://chb2005.tistory.com/86
 *
 *
 * 콘솔 입력
 * 4
 * 0 10 15 20
 * 5 0 9 10
 * 6 13 0 12
 * 8 8 9 0
 * 결과 : 35
 * */

class BitMask2098 { // 제출시 Main으로 변경
    private static int n;

    private static int[][] costMap; // i에서 j로의 이동에 드는 비용 테이블

    private static int[][] costMemory; // [현재까지 방문한 도시 bitMask][현재 방문도시]

    private static int impossibleCost = 1000000 * 17 + 1; // 최대 비용 * 최대 노드수 + 1 = 나올수 없는 비용


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();

        costMemory = new int[1 << n][n];
        costMap = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                costMap[i][j] = scanner.nextInt();
            }
        }

        int minCost = tsp(0, 0);

        System.out.println(minCost);
    }

    private static int tsp(int nowIdx, int visitMask) {
        // 현재 도시 방문 마스킹
        visitMask |= 1 << nowIdx;

        // 모든 도시 방문 한 경우
        if (visitMask == (1 << n) - 1) {
            // 모든 도시 방문 했지만 시작점으로 돌아가지 못하는 경우
            if (costMap[nowIdx][0] == 0) {
                return impossibleCost;
            }
            return costMap[nowIdx][0];
        }

        int ret = costMemory[visitMask][nowIdx];
        if (ret != 0)
            return ret;

        // 이후 로직에서 갱신이 안되면 순회가 불가능함을 마킹
        ret = impossibleCost;

        for (int i = 0; i < n; i++) {
            if (i != nowIdx && (visitMask & (1 << i)) == 0 && costMap[nowIdx][i] != 0) { // 방문 가능
                int costUnderNow = tsp(i, visitMask) + costMap[nowIdx][i];
                if (ret > costUnderNow)
                    ret = costUnderNow;
            }
        }

        // 최소 비용 저장
        return costMemory[visitMask][nowIdx] = ret;
    }
}


// 시작노드 무작위 1개 지정해도 시간초과 발생
//class BitMask2098 { // 제출시 Main으로 변경
//    private static int[] visitMask;
//
//    private static int[][] cost; // i에서 j로의 이동에 드는 비용
//
//    private static int minCost = Integer.MAX_VALUE;
//
//    private static int n;
//
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        n = scanner.nextInt();
//
//        visitMask = new int[n];
//        cost = new int[n][n];
//
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                cost[i][j] = scanner.nextInt();
//            }
//        }
//
//            visitMask[0] = -1;
//            visit(0, 1);
//
//        System.out.println(minCost);
//    }
//
//    private static void visit(int source, int visitCount) {
//        if (visitCount >= n) {
//            for (int i = 0; i < n; i++) {
//                if (visitMask[i] == -1) {
//                    if (cost[source][i] != 0) {
//                        minCost = Math.min(minCost, cost[source][i] + visitMask[source] + 1);  // 마지막 순회 지역에서 출발지까지의 비용 + 나머지 비용 + -1 마킹값 상쇄
//                    }
//                    break;
//                }
//            }
//            return;
//        }
//
//        for (int i = 0; i < n; i++) {
//            if (visitMask[i] == 0 && cost[source][i] != 0) { // 방문 가능
//                visitMask[i] = visitMask[source] + cost[source][i];
//                visit(i, visitCount + 1);
//                visitMask[i] = 0;
//            }
//        }
//    }
//}

// 시간 초과 발생
//class BitMask2098 { // 제출시 Main으로 변경
//    private static int[] visitMask;
//
//    private static int[][] cost; // i에서 j로의 이동에 드는 비용
//
//    private static int minCost = Integer.MAX_VALUE;
//
//    private static int n;
//
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        n = scanner.nextInt();
//
//        visitMask = new int[n];
//        cost = new int[n][n];
//
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                cost[i][j] = scanner.nextInt();
//            }
//        }
//
//        for (int i = 0; i < n; i++) {
//            visitMask[i] = -1;
//            visit(i, 1);
//            visitMask[i] = 0;
//        }
//
//        System.out.println(minCost);
//    }
//
//    private static void visit(int source, int visitCount) {
//        if (visitCount >= n) {
//            for (int i = 0; i < n; i++) {
//                if (visitMask[i] == -1) {
//                    if (cost[source][i] != 0) {
//                        minCost = Math.min(minCost, cost[source][i] + visitMask[source] + 1);  // 마지막 순회 지역에서 출발지까지의 비용 + 나머지 비용 + -1 마킹값 상쇄
//                    }
//                    break;
//                }
//            }
//            return;
//        }
//
//        for (int i = 0; i < n; i++) {
//            if (visitMask[i] == 0 && cost[source][i] != 0) { // 방문 가능
//                visitMask[i] = visitMask[source] + cost[source][i];
//                visit(i, visitCount + 1);
//                visitMask[i] = 0;
//            }
//        }
//    }
//}

//// 메모리 초과 발생
//class BitMask2098 { // 제출시 Main으로 변경
//    private static int[] visitMask;
//
//    private static int[][] cost; // i에서 j로의 이동에 드는 비용
//
//    private static int minCost = Integer.MAX_VALUE;
//
//    private static int n;
//
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        n = scanner.nextInt();
//
//        visitMask = new int[n];
//        cost = new int[n][n];
//
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                cost[i][j] = scanner.nextInt();
//            }
//        }
//
//        for (int i = 0; i < n; i++) {
//            visitMask[i] = -1;
//            visit(i);
//            visitMask[i] = 0;
//        }
//
//        System.out.println(minCost);
//    }
//
//    private static void visit(int source) {
//        if (IntStream.of(visitMask).allMatch(mask -> mask != 0)) {
//            for (int i = 0; i < n; i++) {
//                if (visitMask[i] == -1) { // 출발지 i 검색
//                    int totalCost = cost[source][i] + IntStream.of(visitMask).sum() + 1; // 마지막 순회 지역에서 출발지까지의 비용 + 나머지 비용 + -1 마킹값 상쇄
//                    minCost = Math.min(minCost, totalCost);
//                }
//            }
//            return;
//        }
//
//        for (int i = 0; i < n; i++) {
//            if (visitMask[i] == 0 && cost[source][i] != 0) { // 방문 가능
//                visitMask[i] = cost[source][i];
//                visit(i);
//                visitMask[i] = 0;
//            }
//        }
//    }
//}