package BaekJoon; // 제출시 제외

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 백준 15686 - 치킨 배달
 *
 * Bruteforce, Implementation, Backtraking
 *
 * 1. 전체 치킨집 리스트에서 최대 치킨집 개수만큼 경우의 수를 뽑아내는 것을 재귀로 처리.
 * 2. 이때 바운더리를 벗어나지 않게 idx + M - pickCnt <= chickenListAll.size() 공식을 찾아낼 때 주의.
 * 3. 집 리스트와 선택된 치킨집 리스트와의 최소 치킨 거리 계산 및 최소 도시의 치킨거리 계산은 bruteforce로 처리.
 *
 * 콘솔 입력 :
 * 5 3
 * 0 0 1 0 0
 * 0 0 2 0 1
 * 0 1 2 0 0
 * 0 0 1 0 0
 * 0 0 0 0 2
 *
 *
 * 결과 :
 * 5
 *
 * */


// 1차
class Implementation15686 { // 제출시 Main으로 변경

    private static int N = 0;
    private static int M = 0;

    private static List<int[]> homeListAll = new ArrayList<>();

    private static List<int[]> chickenListAll = new ArrayList<>();

    private static List<int[]> pickedChickenList = new ArrayList<>();

    private static int cityDist = Integer.MAX_VALUE;


    public static void main(String[] args) throws IOException {
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();

        // BufferedReader & StringTokenizer
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken()); // 최대 선택 가능한 치킨집 개수


        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= N; j++) {
                int num = Integer.parseInt(st.nextToken());

                if (num == 1) {
                    homeListAll.add(new int[]{i, j});
                } else if (num == 2) {
                    chickenListAll.add(new int[]{i, j});
                }
            }
        }

        chicken(0, 0);

        System.out.println(cityDist);

    }

    private static void chicken(int startIdx, int pickCnt) {
        if (pickCnt >= M) {
            int nowCityDist = 0;
            for (var homePos : homeListAll) {
                int chickenDist = Integer.MAX_VALUE;
                for (var chickenPos : pickedChickenList) {
                    var nowDist = Math.abs(chickenPos[0] - homePos[0]) + Math.abs(chickenPos[1] - homePos[1]);
                    chickenDist = chickenDist > nowDist ? nowDist : chickenDist;
                }
                nowCityDist += chickenDist;
            }

            cityDist = cityDist > nowCityDist ? nowCityDist : cityDist;

            return;
        }

        int idx = startIdx;
        while (idx + M - pickCnt <= chickenListAll.size()) {
            pickedChickenList.add(chickenListAll.get(idx));
            chicken(idx + 1, pickCnt + 1);
            pickedChickenList.remove(pickedChickenList.size() - 1); // 마지막에 추가한 바로위의 idx
            idx++;
        }
    }
}