package BaekJoon;

import java.util.Arrays;
import java.util.Scanner;

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

    static int hCnt, vCnt;
    static int[][] lab;

    static int maxSafeArea;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        hCnt = scanner.nextInt();
        vCnt = scanner.nextInt();


        lab = new int[hCnt][vCnt];
        for (int i = 0; i < hCnt; i++) {
            for (int j = 0; j < vCnt; j++) {
                lab[i][j] = scanner.nextInt();
            }
        }

        addWall(0);

        System.out.println(maxSafeArea);

    }

    static void addWall(int wall) {
        if(wall == 3) {
            calcSafeArea();
            return;
        }

        for (int i = 0; i < hCnt; i++) {
            for (int j = 0; j < vCnt; j++) {
                if(lab[i][j] == 0) {
                    lab[i][j] = 1;
                    addWall(wall + 1);
                    lab[i][j] = 0;
                }
            }
        }
    }

    static void calcSafeArea() {
        int[][] copyLab = new int[hCnt][vCnt];
        for (int h = 0; h < hCnt; h++) {
            copyLab[h] = Arrays.copyOfRange(lab[h], 0, vCnt);
        }

        for (int i = 0; i < hCnt; i++) {
            for (int j = 0; j < vCnt; j++) {
                if(copyLab[i][j] == 2) {
                    spreadVirus(copyLab, i-1, j);
                    spreadVirus(copyLab, i+1, j);
                    spreadVirus(copyLab, i, j+1);
                    spreadVirus(copyLab, i, j-1);
                }
            }
        }

        int tempMaxSafeArea = 0;
        for (int i = 0; i < hCnt; i++) {
            for (int j = 0; j < vCnt; j++) {
                if(copyLab[i][j] == 0) {
                    tempMaxSafeArea++;
                }
            }
        }

        maxSafeArea = tempMaxSafeArea > maxSafeArea ? tempMaxSafeArea : maxSafeArea;

    }

    static void spreadVirus(int[][] copyLab, int i, int j) {
        if(i >= hCnt || i < 0 || j >= vCnt || j < 0)
            return;

        if(copyLab[i][j] != 0)
            return;

        copyLab[i][j] = 2;

        spreadVirus(copyLab, i-1, j);
        spreadVirus(copyLab, i+1, j);
        spreadVirus(copyLab, i, j+1);
        spreadVirus(copyLab, i, j-1);

    }
}
