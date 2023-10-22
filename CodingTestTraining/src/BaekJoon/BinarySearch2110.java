package BaekJoon; // 제출시 제외

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 백준 2110 - 공유기 설치
 *
 * 이분 탐색(Binary Search)
 *
 * 1. Scanner를 사용하면 시간 초과 발생 -> BufferedReader를 사용
 * 2. 가장 인접한 두 공유기 사이의 거리를 최대로 하면서 공유기를 모두 설치 -> 최대한 균일한 간격으로 설치
 * 3. 간격을 정해서 설치할수 있는지 체크하는 접근
 * 4. 이때 간격을 이분 탐색으로 찾아나감
 * 5. 줄여 나갈 때 최소/최대 값에 +1, -1을 해서 해당 값을 제외 시키지 않으면 무한 루프에 빠지는 케이스 발생 가능
 *
 *
 * 콘솔 입력
 * 5 3
 * 1
 * 2
 * 8
 * 4
 * 9
 *
 * 결과 : 4
 * */

class BinarySearch2110 { // 제출시 Main으로 변경
    private static int N;
    private static int C;
    private static int[] inputPos;

    public static void main(String[] args) throws IOException {
        // BufferedReader & StringTokenizer
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        inputPos = new int[N];
        for (int i = 0; i < N; i++) {
            inputPos[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(inputPos);

        int totalDist = inputPos[N - 1] - inputPos[0];
        int minDist = 1;
        int maxDist = totalDist / (C - 1);

        while (minDist < maxDist) {
            var nowDist = (minDist + maxDist) / 2 + 1;
            if (canPutModem(nowDist) == true) {
                minDist = nowDist;
            } else {
                maxDist = nowDist - 1;
            }
        }

        System.out.println(minDist);
    }

    private static boolean canPutModem(int maxDist) {
        int prevPosX = inputPos[0];
        int cCount = 1; // 최초 좌표에 첫번째 공유기 놓았다고 가정.
        for (int i = 1; i < N; i++) {
            if (inputPos[i] >= prevPosX + maxDist) {
                cCount++;
                if (cCount >= C) {
                    return true;
                }
                prevPosX = inputPos[i];
            }
        }
        return false;
    }
}

// 이전 성공 케이스
//class BinarySearch2110 { // 제출시 Main으로 변경
//    public static void main(String[] args) throws IOException {
//        int n;
//        int c;
//
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
//
//        Arrays.sort(inputPos);
//
//        int minDist = 1; // 최소 거리
//        int maxDist = inputPos[n - 1] - inputPos[0]; // 최대 거리
//        int resultDist = 1;
//
//        while (minDist <= maxDist) {
//            int dist = (minDist + maxDist) / 2;
//
//            int wifiCnt = 1;
//            int prevWifiPos = inputPos[0];
//            for (int i = 1; i < n; i++) {
//                if (prevWifiPos + dist <= inputPos[i]) {
//                    wifiCnt++;
//                    prevWifiPos = inputPos[i];
//                }
//            }
//
//            if (wifiCnt >= c) {
//                minDist = dist + 1; //
//                if (resultDist < dist)
//                    resultDist = dist;
//            } else {
//                maxDist = dist - 1;
//            }
//
//        }
//
//        System.out.println(resultDist);
//    }
//}

// 실패

//class BinarySearch2110 { // 제출시 Main으로 변경
//    private static int n;
//    private static int[] numArray;
//    private static int[] lengthMemory;
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        String cmdLine = br.readLine();
//        String[] cmds = cmdLine.split(" ");
//        n = Integer.parseInt(cmds[0]);
//        int c = Integer.parseInt(cmds[1]);
//
//        char[] inputPos = new char[n * 2]; // \n 까지 고려해서 2배 할당
//        br.read(inputPos);
//
//        TreeSet<Integer> pos = new TreeSet<>();
//        for (char ch : inputPos) {
//            int numVal = Character.getNumericValue(ch);
//            if (numVal >= 0)
//                pos.add(numVal);
//            else
//                continue;
//        }
//
//        int distTotal = pos.last() - pos.first();
//        int dist = distTotal / (c - 1);
//
//        for (int d = dist + 1; d > 0; d--) {
//            int lastPos = pos.first();
//            int wifiCnt = 1;
//            for (int p : pos) {
//                if (lastPos + d <= p) {
//                    wifiCnt++;
//                    lastPos = p;
//                }
//            }
//
//            if (wifiCnt == c) {
//                System.out.println(d);
//                return;
//            }
//        }
//
//        System.out.println(0);
//    }
//}