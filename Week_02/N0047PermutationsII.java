package s0000;

import java.util.*;

public class N0047PermutationsII {
    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums == null || nums.length == 0) return Collections.emptyList();

        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);

        int len = nums.length;
        boolean[] visited = new boolean[len];
        Deque<Integer> path = new ArrayDeque<>(len);
        dfs(nums, len, 0, visited, path, res);
        return res;
    }

    private void dfs(int[] nums, int len, int depth, boolean[] visited, Deque<Integer> path, List<List<Integer>> res) {
        if (depth >= len) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < len; ++i) {
            if (visited[i]) {
                continue;
            }
            // 写 !used[i - 1] 是因为 nums[i - 1] 在深度优先遍历的过程中刚刚被撤销选择
            if ((i > 0 && nums[i] == nums[i - 1] && !visited[i - 1])) {
                continue;
            }

            path.offerLast(nums[i]);
            visited[i] = true;

            dfs(nums, len, depth + 1, visited, path, res);

            visited[i] = false;
            path.pollLast();
        }
    }
}
