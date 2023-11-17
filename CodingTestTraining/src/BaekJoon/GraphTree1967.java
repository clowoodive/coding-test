package BaekJoon; // 제출시 제외

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 백준 1967 - 트리의 지름
 *
 * Tree, DFS, Graph, Graph Search
 *
 * 풀이 :
 * 1. 서브 트리내에서 높은 가중치로 인해 지름이 될 수 있음.
 * 이때 이진 트리가 아닐수도 있으므로, 내림 차순된 서브 트리 가중치 중 제일 큰 2개를 합쳐서 지름으로 갱신.
 *
 * 콘솔 입력 :
 * 12
 * 1 2 3
 * 1 3 2
 * 2 4 5
 * 3 5 11
 * 3 6 9
 * 4 7 1
 * 4 8 7
 * 5 9 15
 * 5 10 4
 * 6 11 6
 * 6 12 10
 *
 * 결과 :
 * 45
 *
 * */
class GraphTree1967 { // 제출시 Main으로 변경

    private static int N;

    private static Map<Integer, List<int[]>> edgeMap = new HashMap<>();

    private static int maxDiameter = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int pNode = Integer.parseInt(st.nextToken());
            int cNode = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edgeMap.computeIfAbsent(pNode, cw -> new ArrayList<int[]>())
                    .add(new int[]{cNode, weight});
        }

        dfs(1);

        System.out.println(maxDiameter);

        br.close();
    }

    private static int dfs(int node) {
        List<int[]> childWeightArray = edgeMap.get(node);
        if (childWeightArray == null)
            return 0;

//        int subWeightSum = 0;
        int maxWeight = 0;
        PriorityQueue<Integer> subWeightSumQ = new PriorityQueue<>(1, Collections.reverseOrder());
        for (int[] childWeight : childWeightArray) {
            var cNode = childWeight[0];
            var weight = childWeight[1];
            int subWeight = dfs(cNode);
            int subWeightSum = subWeight + weight;
            subWeightSumQ.add(subWeightSum);
            maxWeight = Math.max(maxWeight, weight + subWeight); // 현재 노드 까지의 서브 가중치중 최고 큰값을 부모로 넘겨줌
        }

        maxDiameter = Math.max(maxDiameter, subWeightSumQ.poll() + (subWeightSumQ.size() > 0 ? subWeightSumQ.poll() : 0)); // 현재 노드 기준 서브트리가 지름이 될 수 있기에 지름 갱신

        return maxWeight;
    }
}
