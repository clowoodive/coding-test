package BaekJoon; // 제출시 제외

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

/*
 * 백준 1759 - 암호 만들기
 *
 * Bruteforcing, Backtracking, 수학, 조합론
 *
 * 풀이 :
 * 1. 추출한 password는 정렬해야 하기에 List로 마지막에 정렬하는게 공간복잡도는 낮을것 같으나, 속도가 마지막에 정렬하지 않아도 되므로 선택.
 *
 * 콘솔 입력 :
 * 4 6
 * a t c i s w
 *
 * 결과 :
 * acis
 * acit
 * aciw
 * acst
 * acsw
 * actw
 * aist
 * aisw
 * aitw
 * astw
 * cist
 * cisw
 * citw
 * istw
 *
 * */
class BruteBacktrack1759 { // 제출시 Main으로 변경

    private static int L = 0, C = 0;

    private static char[] vowels = new char[]{'a', 'e', 'i', 'o', 'u'};

    private static int vowelCountMax = 0; // vowel, 모음

    private static int consoCountMax = 0; // consonant, 자음
    private static char[] inputChars;

    private static TreeSet<String> passwordSet = new TreeSet<>();


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        inputChars = new char[C];

        vowelCountMax = L - 2; // 최소 두개의 자음을 제외한 모음의 최대 개수
        consoCountMax = L - 1; // 최소 1개의 모음 제외한 자음의 최대 개수

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            inputChars[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(inputChars);

        StringBuilder sb = new StringBuilder();


        pickPassword(sb, 0, 0, 0, 0);
        passwordSet.stream().forEach(System.out::println);

        br.close();
    }

    private static void pickPassword(StringBuilder sb, int pickCount, int pickIndex, int vowelCnt, int consoCnt) {
        if (pickCount >= L) {
//            System.out.println(sb.toString());
            passwordSet.add(sb.toString());
            return;
        }

//        System.out.println(pickCount);
        for (int i = pickIndex; i < C; i++) {
            char c = inputChars[i];
            if (isVowel(c)) {
                if (vowelCnt >= vowelCountMax) {
                    continue;
                }
                sb.append(c);
                pickPassword(sb, pickCount + 1, i + 1, vowelCnt + 1, consoCnt);

            } else {
                if (consoCnt >= consoCountMax) {
                    continue;
                }
                sb.append(c);
                pickPassword(sb, pickCount + 1, i + 1, vowelCnt, consoCnt + 1);
            }
//            System.out.println(sb.charAt(pickCount));
            sb.deleteCharAt(pickCount);
        }
    }

    private static boolean isVowel(char c) {
        for (char v : vowels) {
            if (v == c) {
                return true;
            }
        }
        return false;
    }
}
