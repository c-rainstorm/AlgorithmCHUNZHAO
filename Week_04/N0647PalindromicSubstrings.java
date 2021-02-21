package s0600;

/**
 * https://leetcode-cn.com/problems/palindromic-substrings/solution/javasan-chong-jie-fa-by-mcx-3-2nk0/
 */
public class N0647PalindromicSubstrings {
    /**
     * 我们考虑如何降低回文串的判断效率
     * <p>
     * 假设f(i,j)为当前字符是否回文串，i<=j
     * <p>
     * f(i,j)=f(i+1,j-1)&&s[i]==s[j]
     * <p>
     * 那么我们可以使用dp来降低回文串的计算效率
     * <p>
     * 需要注意的是由于dp迭代是基于左下方的点决定的，因此我们需要从下往上迭代
     */
    public int countSubstringsDp(String s) {
        if (s == null || s.length() == 0) return 0;
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        int ans = 0;
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i; j < len; j++) {
                if (s.charAt(i) == s.charAt(j) && (i + 1 > j - 1 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                    ans++;
                }
            }
        }
        return ans;
    }

    /**
     * 回文中心扩散法
     *
     * @TimeComplexity O(n ^ 2)
     * @SpaceComplexity O(1)
     */
    public int countSubstrings(String s) {
        int ans = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= 1; j++) {
                int l = i;
                int r = i + j;
                while (l >= 0 && r < n && s.charAt(l--) == s.charAt(r++)) {
                    ans++;
                }
            }
        }
        return ans;
    }
}
