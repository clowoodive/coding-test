package BaekJoon; // 제출시 제외

import java.io.IOException;
import java.util.Scanner;

/*
 * 백준 2447 - 별 찍기 - 10
 *
 * 분할정복
 * 재귀
 *
 * 1. 단일 입력이므로 Scanner를 사용.
 * 2. 출력 좌표를 array로 매핑하며, 빈 공간을 배열에 세팅함으로써 마지막 콜스택 호출을 줄임.(약 210만에서 약 30만으로 줄임)
 * 3. 빈공간을 찾는 방식으로 8^7 = 약 210만의 콜스택과 그에 상응하는 배열의 값할당이 발생하는 것을 개선.
 * 4. 8방향의 처리를 k값만큼 7승 처리하게 됨.
 * 5. 하지만 1번이 더 효율적임??
 *        메모리(KB) 시간(ms)
 *    1번  61004      436
 *    2번  66096      516
 * 6. 출력시간이 소요되므로 StringBuilder나 BufferedWriter사용.
 *
 *
 * 콘솔 입력
 * 27
 *
 * 결과 :
 * */

// 2번
class Star2447 { // 제출시 Main으로 변경

    private static int[][] printArray;

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        printArray = new int[n][n];

//        // BufferedReader & StringTokenizer
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        n = Integer.parseInt(st.nextToken());
//        c = Integer.parseInt(st.nextToken());
//
//        int[] inputPos = new int[n];
//        for (int i = 0; i < n; i++) {
//            inputPos[i] = Integer.parseInt(br.readLine());
//        }

        outline(0, 0, n);

        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++) {
                if (printArray[y][x] == 0)
                    sb.append('*');
                else
                    sb.append(' ');
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }

    public static void outline(int posY, int posX, int size) {
        int partSize = size / 3;
        for (int y = posY + partSize; y < posY + partSize * 2; y++) {
            for (int x = posX + partSize; x < posX + partSize * 2; x++) {
                printArray[y][x] = 1;
            }
        }

        if (partSize < 3)
            return;

        outline(posY, posX, partSize);
        outline(posY, posX + partSize, partSize);
        outline(posY, posX + partSize * 2, partSize);

        outline(posY + partSize, posX, partSize);
        outline(posY + partSize, posX + partSize * 2, partSize);

        outline(posY + partSize * 2, posX, partSize);
        outline(posY + partSize * 2, posX + partSize, partSize);
        outline(posY + partSize * 2, posX + partSize * 2, partSize);
    }
}

// 1번
// *을 모두 찍는 방법 - 시간 초과

//class Star2447 { // 제출시 Main으로 변경
//
//    private static int[][] printArray;
//
//    public static void main(String[] args) throws IOException {
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
//
//        printArray = new int[n][n];
//
////        // BufferedReader & StringTokenizer
////        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
////
////        StringTokenizer st = new StringTokenizer(br.readLine());
////        n = Integer.parseInt(st.nextToken());
////        c = Integer.parseInt(st.nextToken());
////
////        int[] inputPos = new int[n];
////        for (int i = 0; i < n; i++) {
////            inputPos[i] = Integer.parseInt(br.readLine());
////        }
//
//        outline(0, 0, n);
//
//
//        for (int y = 0; y < n; y++) {
//            for (int x = 0; x < n; x++) {
//                if (printArray[y][x] == 1) System.out.print("*");
//                else System.out.print(" ");
//            }
//            System.out.println("");
//        }
//    }
//
//    public static void outline(int posY, int posX, int size) {
//
//        if (size <= 1) {
//            printArray[posY][posX] = 1;
//            return;
//        }
//
//        int partSize = size / 3;
//
//        outline(posY, posX, partSize);
//        outline(posY, posX + partSize, partSize);
//        outline(posY, posX + partSize * 2, partSize);
//
//        outline(posY + partSize, posX, partSize);
//        outline(posY + partSize, posX + partSize * 2, partSize);
//
//        outline(posY + partSize * 2, posX, partSize);
//        outline(posY + partSize * 2, posX + partSize, partSize);
//        outline(posY + partSize * 2, posX + partSize * 2, partSize);
//    }
//}

