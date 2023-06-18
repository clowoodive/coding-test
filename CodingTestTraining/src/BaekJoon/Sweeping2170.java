package BaekJoon; // 제출시 제외

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/*
 * 백준 2170 - 선 긋기
 *
 * 라인 스위핑(Sweeping)
 *
 * 1. x좌표를 기준으로 정렬해서 담고(TreeMap)
 * 2. key(x좌표)가 겹치는 경우 y값을 비교해서 더크면 map 갱신.
 * 3. 맵에서 오름차순 정렬된 첫번째 라인부터 꺼내면서 먼저꺼낸 y좌표보다 작으면
 * y좌표를 비교해서 y가 큰 좌표를 갱신.
 * 4. 3번을 수행하지 못하는 케이스(계산중인 y보다 x값이 큰 경우)인 경우 그 시점까지의 길이를 전역변수에 더함.
 * 5. 3~4 반복
 *
 * Scanner를 사용하면 시간 초과 발생.
 *
 *
 * 콘솔 입력
 * 4
 * 1 3
 * 2 5
 * 3 5
 * 6 7
 *
 * 결과 : 5
 * */

class Sweeping2170 { // 제출시 Main으로 변경
    private static int INVALID_POS = -1000000001;
    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        Map<Integer, Integer> linePosMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            String[] inputStrArray = line.split(" ");
            int x = Integer.parseInt(inputStrArray[0]);
            int y = Integer.parseInt(inputStrArray[1]);

            // 앞 좌표 겹치는 경우 중복 제거
            Integer prevY = linePosMap.putIfAbsent(x, y);
            if (prevY != null && prevY < y) {
                linePosMap.replace(x, y);
            }
        }

        int totalLength = 0;
        int x = INVALID_POS;
        int y = INVALID_POS;

        for (Map.Entry<Integer, Integer> entry : linePosMap.entrySet().stream().sorted((k1, k2) -> k1.getKey().compareTo(k2.getKey())).collect(Collectors.toList())) {
            Integer nowX = entry.getKey();
            Integer nowY = entry.getValue();
            if (y < nowX) { // 겹치지 않는 선인 경우
                totalLength += y - x;
                x = nowX;
                y = nowY;
            } else if (y < nowY) { // 선을 연장해야 하는 경우
                y = nowY;
            }
        }

        // 마지막 선 길이 합산
        totalLength += y - x;

        System.out.println(totalLength);
    }
}


//class Sweeping2170 { // 제출시 Main으로 변경
//    private static int INVALID_POS = -1000000001;
//    private static int n;
//
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        n = scanner.nextInt();
//
//        Map<Integer, Integer> linePosTreeMap = new HashMap<>();
//
//        for (int i = 0; i < n; i++) {
//            int x = scanner.nextInt();
//            int y = scanner.nextInt();
//
//            Integer prevY = linePosTreeMap.putIfAbsent(x, y);
//            if (prevY != null && prevY < y) {
//                linePosTreeMap.replace(x, y);
//            }
//        }
//
//        int totalLength = 0;
//        int x = INVALID_POS;
//        int y = INVALID_POS;
//
////        Map.Entry<Integer, Integer> entry;
//        for(Map.Entry<Integer, Integer> entry : linePosTreeMap.entrySet().stream().sorted((k1, k2) -> k1.getKey().compareTo(k2.getKey())).collect(Collectors.toList())) {
////        while ((entry = linePosTreeMap.pollFirstEntry()) != null) {
//            Integer nowX = entry.getKey();
//            Integer nowY = entry.getValue();
//            if (y < nowX) {
//                totalLength += y - x;
//                x = nowX;
//                y = nowY;
//            } else if (y < nowY) {
//                y = nowY;
//            }
//        }
//
//        totalLength += y - x;
//
//        System.out.println(totalLength);
//    }
//}