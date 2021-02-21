package s0000;

/**
 * @author traceless
 */
public class N0072EditDistance {

    /**
     * dp[i][j]表示word1的前i个字母转换成word2的前j个字母所使用的最少操作。
     * dp[i][j] =           dp[i - 1][j - 1]   字母相同
     * <p>
     * //                     dp[i][j - 1]       // 插入
     * <p>
     * //           1 + min   dp[i - 1][j]       // 删除
     * <p>
     * //                     dp[i - 1][j - 1]   // 替换
     * <p>
     * // 0 <= word1.length, word2.length <= 500
     * <p>
     * word1 -> word2 的编辑记录
     */
    public int minDistance(String word1, String word2) {
        int len1 = word1.length(), len2 = word2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 0; i <= len1; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= len2; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]);
                }
            }
        }
        return dp[len1][len2];
    }
}
