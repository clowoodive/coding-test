package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 백준 14502 - 연구소
 *
 * 구현(Implementation)
 *
 * 콘솔 입력
 *7 7
 *2 0 0 0 1 1 0
 *0 0 1 0 1 2 0
 *0 1 1 0 1 0 0
 *0 1 0 0 0 0 0
 *0 0 0 0 0 1 1
 *0 1 0 0 0 0 0
 *0 1 0 0 0 0 0
 *
 * */
class Implementation14502 {

    private static int N;

    private static int M;

    private static int lab[][];

    private static List<int[]> virusPosList = new ArrayList<>();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        lab = new int[N][M];

        for (int n = 0; n < N; n++) {
            st = new StringTokenizer((br.readLine()));
            for (int m = 0; m < M; m++) {
                lab[n][m] = Integer.parseInt(st.nextToken());
                if (lab[n][m] == 2) { // 바이러스 좌표 캐시
                    virusPosList.add(new int[]{n, m});
                }
            }
        }

        int[][] cloneLab = new int[N][M];
        int maxSafetyArea = 0;

        // 가벽 첫번째
        for (int n = 0; n < N; n++) {
            for (int m = 0; m < M; m++) {
                if (lab[n][m] == 0) {
                    lab[n][m] = 1;

                    // 가벽 두번째
                    for (int sn = n; sn < N; sn++) {
                        for (int sm = 0; sm < M; sm++) {
                            if (lab[sn][sm] == 0) {
                                lab[sn][sm] = 1;

                                // 가벽 세번째
                                for (int tn = sn; tn < N; tn++) {
                                    for (int tm = 0; tm < M; tm++) {
                                        if (lab[tn][tm] == 0) {
                                            lab[tn][tm] = 1;

                                            // 연구소 복사(메모리 절약)
                                            for (int i = 0; i < lab.length; i++) {
                                                cloneLab[i] = Arrays.copyOf(lab[i], lab[0].length);
                                            }

                                            // 바이러스 전파
                                            spreadVirus(cloneLab);

                                            // 빈칸 개수 세기
                                            int safetyArea = 0;
                                            for (int nb = 0; nb < N; nb++) {
                                                for (int mb = 0; mb < M; mb++) {
                                                    if (cloneLab[nb][mb] == 0)
                                                        safetyArea++;
                                                }
                                            }

                                            // 안전 영역 최대크기 갱신
                                            maxSafetyArea = Math.max(maxSafetyArea, safetyArea);

                                            lab[tn][tm] = 0;
                                        }
                                    }
                                }
                                lab[sn][sm] = 0;
                            }
                        }
                    }

                    lab[n][m] = 0;
                }
            }
        }

        System.out.println(maxSafetyArea);

    }

    private static void spreadVirus(int[][] cLab) {
        Queue<int[]> q = new LinkedList<>(virusPosList);

        while (q.isEmpty() == false) {
            int[] virus = q.poll();

            int n = virus[0];
            int m = virus[1];

            if (n > 0 && cLab[n - 1][m] == 0) {
                cLab[n - 1][m] = 2;
                q.add(new int[]{n - 1, m});
            }

            if (n < N - 1 && cLab[n + 1][m] == 0) {
                cLab[n + 1][m] = 2;
                q.add(new int[]{n + 1, m});
            }

            if (m > 0 && cLab[n][m - 1] == 0) {
                cLab[n][m - 1] = 2;
                q.add(new int[]{n, m - 1});
            }

            if (m < M - 1 && cLab[n][m + 1] == 0) {
                cLab[n][m + 1] = 2;
                q.add(new int[]{n, m + 1});
            }
        }
    }
}

// 기존 풀이
//class Implementatin14502 {
//
//    static int hCnt, vCnt;
//    static int[][] lab;
//
//    static int maxSafeArea;
//
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        hCnt = scanner.nextInt();
//        vCnt = scanner.nextInt();
//
//
//        lab = new int[hCnt][vCnt];
//        for (int i = 0; i < hCnt; i++) {
//            for (int j = 0; j < vCnt; j++) {
//                lab[i][j] = scanner.nextInt();
//            }
//        }
//
//        addWall(0);
//
//        System.out.println(maxSafeArea);
//
//    }
//
//    static void addWall(int wall) {
//        if(wall == 3) {
//            calcSafeArea();
//            return;
//        }
//
//        for (int i = 0; i < hCnt; i++) {
//            for (int j = 0; j < vCnt; j++) {
//                if(lab[i][j] == 0) {
//                    lab[i][j] = 1;
//                    addWall(wall + 1);
//                    lab[i][j] = 0;
//                }
//            }
//        }
//    }
//
//    static void calcSafeArea() {
//        int[][] copyLab = new int[hCnt][vCnt];
//        for (int h = 0; h < hCnt; h++) {
//            copyLab[h] = Arrays.copyOfRange(lab[h], 0, vCnt);
//        }
//
//        for (int i = 0; i < hCnt; i++) {
//            for (int j = 0; j < vCnt; j++) {
//                if(copyLab[i][j] == 2) {
//                    spreadVirus(copyLab, i-1, j);
//                    spreadVirus(copyLab, i+1, j);
//                    spreadVirus(copyLab, i, j+1);
//                    spreadVirus(copyLab, i, j-1);
//                }
//            }
//        }
//
//        int tempMaxSafeArea = 0;
//        for (int i = 0; i < hCnt; i++) {
//            for (int j = 0; j < vCnt; j++) {
//                if(copyLab[i][j] == 0) {
//                    tempMaxSafeArea++;
//                }
//            }
//        }
//
//        maxSafeArea = tempMaxSafeArea > maxSafeArea ? tempMaxSafeArea : maxSafeArea;
//
//    }
//
//    static void spreadVirus(int[][] copyLab, int i, int j) {
//        if(i >= hCnt || i < 0 || j >= vCnt || j < 0)
//            return;
//
//        if(copyLab[i][j] != 0)
//            return;
//
//        copyLab[i][j] = 2;
//
//        spreadVirus(copyLab, i-1, j);
//        spreadVirus(copyLab, i+1, j);
//        spreadVirus(copyLab, i, j+1);
//        spreadVirus(copyLab, i, j-1);
//
//    }
//}