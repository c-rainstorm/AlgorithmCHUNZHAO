package s0000;

import java.util.*;

public class N0077Combinations {

    /**
     * https://leetcode-cn.com/problems/combinations/solution/hui-su-suan-fa-jian-zhi-python-dai-ma-java-dai-ma-/
     */
    public List<List<Integer>> combine(int n, int k) {
        if (k <= 0 || n < k) return Collections.emptyList();
        List<List<Integer>> res = new LinkedList<>();
        Deque<Integer> path = new ArrayDeque<>();
        dfs(n, k, 1, path, res);
        return res;
    }

    private void dfs(int n, int k, int start, Deque<Integer> path, List<List<Integer>> res) {
        if (path.size() >= k) {
            res.add(new ArrayList<>(path));
            return;
        }

        // 剪枝
        for (int i = start; i <= n - (k - path.size()) + 1; ++i) {
            path.offerLast(i);
            dfs(n, k, i + 1, path, res);
            path.pollLast();
        }
    }
}
