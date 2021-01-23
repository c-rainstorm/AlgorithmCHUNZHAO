package s0000;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://mp.weixin.qq.com/s/f9ebzbwymR8jQeUDxjeCDA
 */
public class N0042TrappingRainWater {

    /**
     * 暴力解法，
     * <p>
     * 第 i 个柱子的可存的水量 = min(max(height[0..i]), max(height[i..N])) - height[i];
     * 这个是解题的关键，无法抽象到这个公式那就做不成
     *
     * @TimeComplexity O(n ^ 2)
     * @SpaceComplexity O(1)
     */
    public int trap(int[] height) {
        int ans = 0;
        int len = height.length;
        for (int i = 1; i < len - 1; i++) {
            int lMax = 0, rMax = 0;
            for (int j = i; j >= 0; j--) {
                lMax = Math.max(lMax, height[j]);
            }
            for (int j = i; j < len; j++) {
                rMax = Math.max(rMax, height[j]);
            }
            ans += Math.min(lMax, rMax) - height[i];
        }
        return ans;
    }

    /**
     * DP, 消除暴力解法的重复计算
     * <p>
     * dp[i][0] = Math.max(dp[i-1][0], height[i]);  // 左侧最大高度
     * dp[i][1] = Math.max(dp[i+1][1], height[i]);  // 右侧最大高度
     *
     * @TimeComplexity O(n)
     * @SpaceComplexity O(n)
     */
    public int dp(int[] height) {
        if (height == null || height.length <= 2) return 0;
        int len = height.length;
        int[][] dp = new int[len][2];
        dp[0][0] = height[0];
        dp[len - 1][1] = height[len - 1];
        for (int i = 1, j = len - 2; i < len && j >= 0; ++i, --j) {
            dp[i][0] = Math.max(dp[i - 1][0], height[i]);
            dp[j][1] = Math.max(dp[j + 1][1], height[j]);
        }

        int ans = 0;
        for (int i = 1; i < len; ++i) {
            ans += Math.min(dp[i][0], dp[i][1]) - height[i];
        }
        return ans;
    }

    /**
     * 双指针，从两侧逼近，优化DP解法的空间复杂度
     *
     * @TimeComplexity O(n)
     * @SpaceComplexity O(1)
     */
    public int doublePointer(int[] height) {
        int ans = 0, lMax = 0, rMax = 0, left = 0, right = height.length - 1;
        while (left <= right) {
            if (lMax <= rMax) {
                lMax = Math.max(lMax, height[left]);
                ans += lMax - height[left++];
            } else {
                rMax = Math.max(rMax, height[right]);
                ans += rMax - height[right--];
            }
        }
        return ans;
    }

    /**
     * https://leetcode-cn.com/problems/trapping-rain-water/solution/jie-yu-shui-by-leetcode/
     * https://mp.weixin.qq.com/s/f9ebzbwymR8jQeUDxjeCDA
     * 单调栈
     *
     * @TimeComplexity O(n)
     * @SpaceComplexity O(n)
     */
    public int stack(int[] height) {
        int ans = 0;
        Deque<Integer> stack = new ArrayDeque<>(height.length);
        for (int i = 0; i < height.length; ++i) {
            while (!stack.isEmpty() && height[stack.peek()] < height[i]) {
                int top = stack.pop();
                if (stack.isEmpty()) break;
                int length = i - stack.peek() - 1;
                int width = Math.min(height[i], height[stack.peek()]) - height[top];
                ans += length * width;
            }
            stack.push(i);
        }
        return ans;
    }

}
