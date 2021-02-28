package s0300;

import java.util.Arrays;

/**
 * @author traceless
 */
public class N0300LongestIncreasingSubsequence {
    /**
     * 经典DP
     * <p>
     * dp[i] = dp[j] + 1, 0 <= j < i && nums[i] > nums[j]
     * <p>
     * 看以前的，比它小的，说明可以接在后面形成一个更长的子序列
     */
    public int lengthOfLis(int[] nums) {
        if (nums.length < 2) return nums.length;

        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);

        int res = dp[0];
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }

            // 在遍历的时候同时找 dp 数组的最大值
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    /**
     * https://leetcode.com/problems/longest-increasing-subsequence/discuss/74824/JavaPython-Binary-search-O(nlogn)-time-with-explanation
     * (1) if x is larger than all tails, append it, increase the size by 1
     * (2) if tails[i-1] < x <= tails[i], update tails[i]
     */
    public int lengthOfLis_SS(int[] nums) {
        int[] tails = new int[nums.length];
        int ans = 0;
        for (int num : nums) {
            int lo = 0, hi = ans;
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                if (tails[mid] < num)
                    lo = mid + 1;
                else
                    // WARNING 注意不是 mid - 1
                    hi = mid;
            }
            tails[lo] = num;
            if (lo == ans) ++ans;
        }
        return ans;
    }
}
