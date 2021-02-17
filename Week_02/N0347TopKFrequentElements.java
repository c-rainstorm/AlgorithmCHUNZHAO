package s0300;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class N0347TopKFrequentElements {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>(nums.length);
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        // int[] 的第一个元素代表数组的值，第二个元素代表了该值出现的次数
        PriorityQueue<int[]> queue = new PriorityQueue<>((m, n) -> m[1] - n[1]);

        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            int num = entry.getKey();
            int count = entry.getValue();
            if (queue.size() < k) {
                queue.offer(new int[]{num, count});
            } else if (queue.peek()[1] < count) {
                queue.poll();
                queue.offer(new int[]{num, count});
            }
        }
        int[] ans = new int[k];
        for (int i = 0; i < k; ++i) {
            ans[i] = queue.poll()[0];
        }
        return ans;
    }
}
