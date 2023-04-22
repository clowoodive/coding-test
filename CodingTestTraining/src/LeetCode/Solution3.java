package LeetCode;

public class Solution3 {
    public static void main(String[] args) {
        int solution = lengthOfLongestSubstring("abcabcbb");
        System.out.println(solution);
    }

    // 3. Longest Substring Without Repeating Characters

    // 타임오버된 첫번째 풀이
//    public static int lengthOfLongestSubstring(String s) {
//        // 반복된 문자 set에 캐시
//        Set<Character> checkSet = new HashSet<>();
//        int maxCount = 0;
//        int nowCount = 0;
//        boolean duplicated = false;
//
//        int length = s.length();
//
//        for (int start = 0; start < length; start++) {
//            if(length - start <= maxCount)
//                break;
//
//            for (int end = length - 1; end >= start; end--) {
//                if(end - start + 1 <= maxCount)
//                    break;
//                duplicated = false;
//                checkSet.clear();
//                for (int i = start; i <= end; i++) {
//                    char c = s.charAt(i);
//                    if (checkSet.contains(c)) {
//                        duplicated = true;
//                        break;
//                    }
//                    checkSet.add(c);
//                }
//                if(!duplicated) {
//                    nowCount = end - start + 1;
//                    maxCount = maxCount < nowCount ? nowCount : maxCount;
//                    break;
//                }
//            }
//        }
//
//        return maxCount;
//    }
}