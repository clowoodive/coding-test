package LeetCode;

public class Solution11 {
    public static void main(String[] args) {
        int solution = maxArea(new int[]{1, 2});
        System.out.println(solution);
    }

    // 11. Container With Most Water
    // 풀이 방식 변경 필요
    public static int maxArea(int[] height) {
        int water = 0;
        int minDis = 0;
        int rightStart = 0;

        for (int left = 0; left < height.length - 1; left++) {
            if (height[left] == 0)
                continue;
            minDis = height[left] + left;
            rightStart = height.length > minDis ? minDis : left + 1;
            for (int right = rightStart; right < height.length; right++) {
                if (water > (height.length - left) * height[left])
                    break;
                water = Math.max(water, (right - left) * Math.min(height[left], height[right]));
            }
        }

        return water;
    }
}