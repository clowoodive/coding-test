package LeetCode;

// 5. Longest Palindromic Substring
public class Solution5 {
    public static void main(String[] args) {
        Solution5 solution = new Solution5();
        String result = solution.longestPalindrome("a");
        System.out.println(result);
    }

    int lIdx = 0;
    int rIdx = 0;
    int maxLength = 0;

    public String longestPalindrome(String s) {
        for (int i = 0; i < s.length(); i++) {
            extendBothSide(s, i, i);
            extendBothSide(s, i, i + 1);
        }

        return s.substring(lIdx, rIdx);
    }

    private void extendBothSide(String s, int left, int right) {
        for (int l = left, r = right; l >= 0 && r < s.length(); l--, r++) {
            if (s.charAt(l) != s.charAt(r)) {
                break;
            } else {
                if (r - l + 1 > maxLength) {
                    lIdx = l;
                    rIdx = r + 1;
                    maxLength = r - l + 1;
                }
            }
        }
    }
}