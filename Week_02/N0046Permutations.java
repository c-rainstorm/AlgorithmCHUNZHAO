package s0000;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class N0046Permutations {
    /**
     * https://leetcode-cn.com/problems/permutations/solution/quan-pai-lie-by-leetcode-solution-2/
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> output = new ArrayList<>();
        for (int num : nums) {
            output.add(num);
        }

        int n = nums.length;
        dfs(n, 0, output, res);
        return res;
    }

    public void dfs(int n, int start, List<Integer> output, List<List<Integer>> res) {
        if (start >= n) {
            res.add(new ArrayList<>(output));
            return;
        }

        for (int i = start; i < n; ++i) {
            // 动态维护数组
            Collections.swap(output, start, i);
            // 继续递归填下一个数
            dfs(n, start + 1, output, res);
            // 撤销操作
            Collections.swap(output, start, i);
        }
    }
}
