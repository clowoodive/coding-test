package BaekJoon; // 제출시 제외

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 백준 1753 - 최단 경로
 *
 * 그래프 이론
 * 데이크스트라
 *
 * 1. 다익스트라 등 최단 경로 알고리즘
 * 2. 예시 입력의 5와 1 정점 사이의 가중치가 1이 있음에도 결과가 INF라는 것은 입력값 u,v가 방향성이 존재함을 캐치.
 * 3. 첫번째 코드에서 같은 정점 사이의 거리가 다른 간선이 존재하면 덮어씌워지는 문제가 있어서 처리가 필요함.(콘솔 입력 2 참고)
 *    -> 정점 중복 제거를 위해 Map<k,v> []로 변경.
 * 4. 시간 단축을 위해 PriorityQueue 사용
 *
 * 콘솔 입력 1
 * 5 6
 * 1
 * 5 1 1
 * 1 2 2
 * 1 3 3
 * 2 3 4
 * 2 4 5
 * 3 4 6
 *
 * 결과 :
 * 0
 * 2
 * 3
 * 7
 * INF
 *
 * 콘솔 입력 2
 * 4 5
 * 1
 * 1 2 4
 * 2 3 3
 * 1 3 1
 * 1 3 2
 * 3 4 1
 *
 * 결과 :
 * 0
 * 4
 * 1
 * 2
 * */


// 우선순위 큐 사용(메모리는 123340KB으로 조금더 먹으나, 시간은 1344ms로 반가까이 줄어듬)
class ShortestPath1753 { // 제출시 Main으로 변경

    private static int V = 0;

    private static int E = 0;

    private static List<Map<Integer, Integer>> destDistMapList; // index : source, array[0] : dest, array[1] : dist

    private static boolean visited[];

    private static int shortestDist[];

    private static PriorityQueue<DestDist> pq = new PriorityQueue<>();


    public static void main(String[] args) throws IOException {
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();

        // BufferedReader & StringTokenizer
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 정점의 개수
        V = Integer.parseInt(st.nextToken());

        // 간선의 개수
        E = Integer.parseInt(st.nextToken());

        // 시작 정점
        int K = Integer.parseInt(br.readLine());

        destDistMapList = new ArrayList<Map<Integer, Integer>>(V + 1);
        visited = new boolean[V + 1];
        shortestDist = new int[V + 1];

        for (int i = 0; i < V + 1; i++) {
            destDistMapList.add(new HashMap<>());
            shortestDist[i] = Integer.MAX_VALUE;
        }
//        Arrays.fill(shortestDist, Integer.MAX_VALUE);
        shortestDist[0] = -1; // 출력시 무효

        int src;
        int dest;
        int dist;
        for (int i = 1; i < E + 1; i++) {
            st = new StringTokenizer(br.readLine());
            src = Integer.parseInt(st.nextToken());
            dest = Integer.parseInt(st.nextToken());
            dist = Integer.parseInt(st.nextToken());

            Integer prevVal = destDistMapList.get(src).putIfAbsent(dest, dist);
            if (prevVal != null && prevVal > dist) {
                destDistMapList.get(src).put(dest, dist); // replace랑 같은 동작
            }
        }

        shortestDist[K] = 0;
        pq.add(new DestDist(K, 0));
        dijkstra();

        Arrays.stream(shortestDist).filter(d -> d >= 0).forEach(d -> System.out.println(d == Integer.MAX_VALUE ? "INF" : d));
//        for (int n = 1; n < V + 1; n++) {
//            System.out.println(shortestDist[n] == Integer.MAX_VALUE ? "INF" : shortestDist[n]);
//        }
    }

    private static void dijkstra() {
        while (pq.isEmpty() == false) {
            DestDist destDist = pq.poll();
            int nowDest = destDist.getDest();
            if (visited[nowDest] == true) {
                continue;
            }
            visited[nowDest] = true;

            Map<Integer, Integer> destDistMap = destDistMapList.get(nowDest);
            if (destDistMap == null || destDistMap.isEmpty()) {
                continue;
            }

            for (Map.Entry<Integer, Integer> entry : destDistMap.entrySet()) {
                int dest = entry.getKey();
                int dist = entry.getValue();

//                if (visited[dest] == true) {
//                    continue;
//                }

                int newDist = shortestDist[nowDest] + dist;
                if (newDist < shortestDist[dest]) {
                    shortestDist[dest] = newDist;
                }

                pq.add(new DestDist(dest, shortestDist[dest]));
            }
        }
    }

    private static class DestDist implements Comparable<DestDist> {
        private int dest;
        private int dist;

        public DestDist(int dest, int dist) {
            this.dest = dest;
            this.dist = dist;
        }

        public int getDest() {
            return dest;
        }

        public void setDest(int dest) {
            this.dest = dest;
        }

        public int getDist() {
            return dist;
        }

        public void setDist(int dist) {
            this.dist = dist;
        }

        @Override
        public int compareTo(DestDist o) {
            return dist - o.dist; // 삽입된 값이 비교대상 노드와 같거나 크면 root로의 이동 멈춤(작은 값을 root로)
        }
    }
}

//// 우선순위 큐 사용(메모리는 123340KB으로 조금더 먹으나, 시간은 1344ms로 반가까이 줄어듬)
//class ShortestPath1753 { // 제출시 Main으로 변경
//
//    private static int V = 0;
//
//    private static int E = 0;
//
//    private static List<Map<Integer, Integer>> graphMapArray; // index : source, key : dest, val : dist
//
//    private static boolean visited[];
//
//    private static int dist[];
//
//
//    public static void main(String[] args) throws IOException {
////        Scanner scanner = new Scanner(System.in);
////        int n = scanner.nextInt();
//
//        // BufferedReader & StringTokenizer
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        StringTokenizer st = new StringTokenizer(br.readLine());
//
//        // 정점의 개수
//        V = Integer.parseInt(st.nextToken());
//
//        // 간선의 개수
//        E = Integer.parseInt(st.nextToken());
//
//        // 시작 정점
//        int K = Integer.parseInt(br.readLine());
//
//        graphMapArray = new ArrayList<Map<Integer, Integer>>();
//        visited = new boolean[V + 1];
//        dist = new int[V + 1];
//
//        for (int i = 0; i < V + 1; i++) {
//            graphMapArray.add(new HashMap<>());
//        }
//
//        for (int i = 0; i < E; i++) {
//            StringTokenizer edge = new StringTokenizer(br.readLine());
//            int source = Integer.parseInt(edge.nextToken());
//            int dest = Integer.parseInt(edge.nextToken());
//            int dist = Integer.parseInt(edge.nextToken());
//
//            Integer oldDist = graphMapArray.get(source).putIfAbsent(dest, dist);
//            if (oldDist != null && oldDist > dist) {
//                graphMapArray.get(source).put(dest, dist);
//            }
//        }
//
//        dijkstra(K);
//
//        for (int n = 1; n < V + 1; n++) {
//            System.out.println(dist[n] == Integer.MAX_VALUE ? "INF" : dist[n]);
//        }
//    }
//
//    private static void dijkstra(int startNode) {
//        PriorityQueue<DestDist> pq = new PriorityQueue<>();
//        pq.add(new DestDist(startNode, 0));
//
//        Arrays.fill(dist, Integer.MAX_VALUE);
////        for (Map.Entry<Integer, Integer> entry : graphMapArray.get(startNode).entrySet()) {
////            dist[entry.getKey()] = entry.getValue();
////        }
//
////        visited[startNode] = true;
//        dist[startNode] = 0;
//
//        while (pq.isEmpty() == false) {
//            DestDist destDist = pq.poll();
////            int nowNode = getShortestUnvisitedNode();
//            if(visited[destDist.getDest()] == true) continue;
//            visited[destDist.getDest()] = true;
//
//            for (Map.Entry<Integer, Integer> entry : graphMapArray.get(destDist.getDest()).entrySet()) {
//                int dest = entry.getKey();
//                int distance = entry.getValue();
//
//                if (visited[dest] == true)
//                    continue;
//
//                int newDist = dist[destDist.getDest()] + distance;
//                dist[dest] = Math.min(dist[dest], newDist);
//
//                pq.add(new DestDist(dest, dist[dest]));
//            }
//        }
//    }
//
////    private static int getShortestUnvisitedNode() {
////        int minNode = 0;
////        int minDist = Integer.MAX_VALUE;
////
////        for (int i = 1; i < V + 1; i++) {
////            if (visited[i] == true)
////                continue;
////
////            if (minDist > dist[i]) {
////                minNode = i;
////                minDist = dist[i];
////            }
////        }
////
////        return minNode;
////    }
//
//    private static class DestDist implements Comparable<DestDist> {
//        private int dest;
//        private int dist;
//
//        public DestDist(int dest, int dist) {
//            this.dest = dest;
//            this.dist = dist;
//        }
//
//        public int getDest() {
//            return dest;
//        }
//
//        public void setDest(int dest) {
//            this.dest = dest;
//        }
//
//        public int getDist() {
//            return dist;
//        }
//
//        public void setDist(int dist) {
//            this.dist = dist;
//        }
//
//        @Override
//        public int compareTo(DestDist o) {
//            return dist - o.dist; // 삽입된 값이 비교대상 노드와 같거나 크면 root로의 이동 멈춤(작은 값을 root로)
//        }
//    }
//}


//// 동일 노드간 중복 간선 덮어씌워지는 문제 해결해서 통과
//class ShortestPath1753 { // 제출시 Main으로 변경
//
//    private static int V = 0;
//
//    private static int E = 0;
//
//    private static List<Map<Integer, Integer>> graphMapArray; // index : source, key : dest, val : dist
//
//    private static boolean visited[];
//
//    private static int dist[];
//
//
//    public static void main(String[] args) throws IOException {
////        Scanner scanner = new Scanner(System.in);
////        int n = scanner.nextInt();
//
//        // BufferedReader & StringTokenizer
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        StringTokenizer st = new StringTokenizer(br.readLine());
//
//        // 정점의 개수
//        V = Integer.parseInt(st.nextToken());
//
//        // 간선의 개수
//        E = Integer.parseInt(st.nextToken());
//
//        // 시작 정점
//        int K = Integer.parseInt(br.readLine());
//
//        graphMapArray = new ArrayList<Map<Integer, Integer>>();
//        visited = new boolean[V + 1];
//        dist = new int[V + 1];
//
//        for (int i = 0; i < V + 1; i++) {
//            graphMapArray.add(new HashMap<>());
//        }
//
//        for (int i = 0; i < E; i++) {
//            StringTokenizer edge = new StringTokenizer(br.readLine());
//            int source = Integer.parseInt(edge.nextToken());
//            int dest = Integer.parseInt(edge.nextToken());
//            int dist = Integer.parseInt(edge.nextToken());
//
//            Integer oldDist = graphMapArray.get(source).putIfAbsent(dest, dist);
//            if (oldDist != null && oldDist > dist) {
//                graphMapArray.get(source).put(dest, dist);
//            }
//        }
//
//        dijkstra(K);
//
//        for (int n = 1; n < V + 1; n++) {
//            System.out.println(dist[n] == Integer.MAX_VALUE ? "INF" : dist[n]);
//        }
//    }
//
//    private static void dijkstra(int startNode) {
//        Arrays.fill(dist, Integer.MAX_VALUE);
//        for (Map.Entry<Integer, Integer> entry : graphMapArray.get(startNode).entrySet()) {
//            dist[entry.getKey()] = entry.getValue();
//        }
//
//        visited[startNode] = true;
//        dist[startNode] = 0;
//
//        for (int i = 0; i < V - 1; i++) {
//            int nowNode = getShortestUnvisitedNode();
//            if (nowNode == 0)
//                continue;
//
//            visited[nowNode] = true;
//
//            for (Map.Entry<Integer, Integer> entry : graphMapArray.get(nowNode).entrySet()) {
//                int dest = entry.getKey();
//                int distance = entry.getValue();
//
//                if (visited[dest] == true)
//                    continue;
//
//                int newDist = dist[nowNode] + distance;
//                dist[dest] = Math.min(dist[dest], newDist);
//            }
//        }
//    }
//
//    private static int getShortestUnvisitedNode() {
//        int minNode = 0;
//        int minDist = Integer.MAX_VALUE;
//
//        for (int i = 1; i < V + 1; i++) {
//            if (visited[i] == true)
//                continue;
//
//            if (minDist > dist[i]) {
//                minNode = i;
//                minDist = dist[i];
//            }
//        }
//
//        return minNode;
//    }
//}

// 메모리 초과
//class ShortestPath1753 { // 제출시 Main으로 변경
//
//    private static int V = 0;
//
//    private static int E = 0;
//    private static int[][] graph;
//
//    private static boolean visited[];
//
//    private static int dist[];
//
//    public static void main(String[] args) throws IOException {
////        Scanner scanner = new Scanner(System.in);
////        int n = scanner.nextInt();
//
//        // BufferedReader & StringTokenizer
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        StringTokenizer st = new StringTokenizer(br.readLine());
//
//        // 정점의 개수
//        V = Integer.parseInt(st.nextToken());
//
//        // 간선의 개수
//        E = Integer.parseInt(st.nextToken());
//
//        // 시작 정점
//        int K = Integer.parseInt(br.readLine());
//
//        graph = new int[V + 1][V + 1];
//        for (int i = 0; i < E; i++) {
//            StringTokenizer edge = new StringTokenizer(br.readLine());
//            graph[Integer.parseInt(edge.nextToken())][Integer.parseInt(edge.nextToken())] = Integer.parseInt(edge.nextToken());
//        }
//
//        visited = new boolean[V + 1];
//        dist = new int[V + 1];
////        for (int i = 1; i < V + 1; i++) {
////            dist[i] = Integer.MAX_VALUE;
////        }
////        dist[K] = 0; // 자기자신과의 거리
//
//        dijkstra(K);
//
//        for(int n = 1; n < V + 1; n++) {
//            System.out.println(dist[n] == Integer.MAX_VALUE ? "INF" : dist[n]);
//        }
//    }
//
//    private static void dijkstra(int startNode) {
//        visited[startNode] = true;
//
//        for (int i = 1; i < V + 1; i++) {
//            if (graph[startNode][i] > 0)
//                dist[i] = graph[startNode][i];
//            else
//                dist[i] = Integer.MAX_VALUE;
//        }
//        dist[startNode] = 0;
//
//
//        for (int i = 0; i < V - 1; i++) {
//            int nowNode = getShortestUnvisitedNode();
//            visited[nowNode] = true;
////            if(visited[i] == true)
////                continue;
//            for (int j = 1; j < V + 1; j++) {
//                if (graph[nowNode][j] > 0) {
//                    int newDist = dist[nowNode] + graph[nowNode][j];
//                    dist[j] = Math.min(dist[j], newDist);
//                }
//            }
//        }
//    }
//
//    private static int getShortestUnvisitedNode() {
//        int minNode = 0;
//        int minDist = Integer.MAX_VALUE;
//        for (int i = 1; i < V + 1; i++) {
//            if (visited[i] == true)
//                continue;
//
//            if (minDist > dist[i]) {
//                minNode = i;
//                minDist = dist[i];
//            }
//        }
//
//        return minNode;
//    }
//}