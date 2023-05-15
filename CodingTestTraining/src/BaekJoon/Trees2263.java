package BaekJoon; // 제출시 제외

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * 백준 2263 - 트리의 순회
 *
 * 트리(Trees)
 *
 * 풀이
 * post의 마지막 노드가 서브 트리의 부모 노드가 됨.
 * preOrder는 부모 노드를 먼저 탐색하는 것.
 * post의 마지막 노드를 출력하고, 그 노드 기준으로 나누어진 서브 노드 값들 중에서
 * 다시 post에서 제일 뒤에 위치하는 노드를 찾아냄 = 서브 트리의 부모 노드.
 * 단, 우측 서브트리의 위치 인덱스는 좌측 서브트리와 다름.
 *
 * 콘솔 입력
 * 7
 * 1 3 2 4 6 5 7
 * 1 2 3 6 7 5 4
 *
 * 결과 : 4 3 1 2 5 6 7
 * */
class Trees2263 { // 제출시 Main으로 변경

    private static int[] in, post;

    private static int n;

    private static List<Integer> result = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();

        in = new int[n];
        for (int i = 0; i < n; i++) {
            in[i] = scanner.nextInt();
        }

        post = new int[n];
        for (int i = 0; i < n; i++) {
            post[i] = scanner.nextInt();
        }

        preOrder(0, n - 1, 0, n - 1);

        for(var val : result) {
            System.out.print(val + " ");
        }
    }

    private static void preOrder(int inL, int inR, int postL, int postR) {
        if (inL > inR || postL > postR)
            return;

        result.add(post[postR]);

        int inRootIdx = 0;
        for (int i = inL; i <= inR; i++) {
            if (in[i] == post[postR]) {
                inRootIdx = i;
                break;
            }
        }

        int leftNodeCnt = inRootIdx - inL;
        preOrder(inL, inRootIdx - 1, postL, postL + leftNodeCnt - 1);
        preOrder(inRootIdx + 1, inR, postL + leftNodeCnt, postR - 1);
    }

//    private static void preOrder(int inL, int inR, int postL, int postR) {
//        if (inL > inR || postL > postR)
//            return;
//
//        result.add(post[postR]);
//
//        for (int i = 0; inL + i <= inR; i++) {
//            if (in[inL + i] == post[postR]) {
//                preOrder(inL, inL + i - 1, postL, postL + i - 1);
//                preOrder(inL + i + 1, inR, postL + i, postR - 1);
//                return; // 뒤에 체크 불필요
//            }
//        }
//    }
}
