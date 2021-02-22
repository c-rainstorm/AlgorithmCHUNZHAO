package s0000;

/**
 * [70] 爬楼梯
 * <p>
 * https://leetcode-cn.com/problems/climbing-stairs/description/
 * <p>
 * algorithms
 * Easy (44.89%)
 * Total Accepted:    39.7K
 * Total Submissions: 88.5K
 * Testcase Example:  '2'
 * <p>
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * <p>
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * <p>
 * 注意：给定 n 是一个正整数。
 * <p>
 * 示例 1：
 * <p>
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 * <p>
 * 示例 2：
 * <p>
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 *
 * @author baochen1.zhang
 * @date 2019.04.05
 */
public class N0070ClimbStairs {

    /**
     * 递归版本
     * f(n) = f(n - 1) + f(n - 1);
     */
    public int recursion(int n) {
        if (n <= 2) return n;
        return recursion(n - 1) + recursion(n - 2);
    }

    /**
     * 递归 + 记忆化
     */
    public int recursionWithMemory(int n) {
        return recursionWithMemory(n, new int[n + 1]);
    }

    public int recursionWithMemory(int n, int[] m) {
        if (n <= 2) return n;

        if (m[n] == 0)
            m[n] = recursionWithMemory(n - 1, m) + recursionWithMemory(n - 2, m);

        return m[n];
    }

    /**
     * dp[i] = dp[i-1] + dp[i-2]
     * df[0] = dp[1] = 1;
     */
    public int dp(int n) {
        if (n <= 2) return n;
        int[] dp = new int[2];
        dp[0] = dp[1] = 1;
        for (int i = 2; i <= n; ++i) {
            int tmp = dp[0] + dp[1];
            dp[0] = dp[1];
            dp[1] = tmp;
        }
        return dp[1];
    }
}
