package s0100;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * [169] 求众数
 * <p>
 * https://leetcode-cn.com/problems/majority-element/description/
 * <p>
 * algorithms
 * Easy (58.42%)
 * Total Accepted:    33.3K
 * Total Submissions: 57K
 * Testcase Example:  '[3,2,3]'
 * <p>
 * 给定一个大小为 n 的数组，找到其中的众数。众数是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 * <p>
 * 你可以假设数组是非空的，并且给定的数组总是存在众数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,2,3]
 * 输出: 3
 * <p>
 * 示例 2:
 * <p>
 * 输入: [2,2,1,1,1,2,2]
 * 输出: 2
 *
 * @author baochen1.zhang
 * @date 2019.04.06
 */
public class N0169MajorityElement {

    /**
     * 哈希表解法
     *
     * @TimeComplexity O(n)
     * @SpaceComplexity O(n)
     */
    public int hash(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        int result = 0;
        int maxCount = -1;
        for (int num : nums) {
            int count = map.getOrDefault(num, 0) + 1;
            if (count > maxCount) {
                result = num;
                maxCount = count;
            }
            map.put(num, count);
        }
        return result;
    }

    /**
     * 排序解法
     *
     * @TimeComplexity O(nlog ( n))
     * @SpaceComplexity O(log ( n))
     */
    public int sort(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    /**
     * 随机化
     *
     * @TimeComplexity O(n)
     * @SpaceComplexity O(1)
     */
    public int random(int[] nums) {
        Random rand = new Random();

        while (true) {
            int guess = nums[rand.nextInt(nums.length)];
            if (count(nums, guess) > nums.length / 2) {
                return guess;
            }
        }
    }

    private int count(int[] nums, int target) {
        int count = 0;
        for (int num : nums) {
            if (num == target) {
                count++;
            }
        }
        return count;
    }

    /**
     * 分治法
     *
     * @TimeComplexity nlog(n)
     * @SpaceComplexity log(n)
     */
    public int divideAndConquer(int[] nums) {
        return majorityElement(nums, 0, nums.length - 1);
    }

    private int majorityElement(int[] nums, int lo, int hi) {
        // 终止条件
        if (lo == hi) {
            return nums[lo];
        }

        // 子问题切分
        int mid = (hi + lo) >> 1;
        int left = majorityElement(nums, lo, mid);
        int right = majorityElement(nums, mid + 1, hi);

        // 子问题结果合并
        if (left == right) {
            return left;
        }
        int leftCount = countInRange(nums, left, lo, hi);
        int rightCount = countInRange(nums, right, lo, hi);

        return leftCount > rightCount ? left : right;
    }

    private int countInRange(int[] nums, int num, int lo, int hi) {
        int count = 0;
        for (int i = lo; i <= hi; i++) {
            if (nums[i] == num) {
                count++;
            }
        }
        return count;
    }

    /**
     * 投票法
     *
     * @TimeComplexity O(n)
     * @SpaceComplexity O(1)
     */
    public int vote(int[] nums) {
        int voting = 0;

        for (int i = 0, count = 0; i < nums.length; ++i) {
            if (count == 0) {
                voting = nums[i];
            }
            count += (nums[i] == voting) ? 1 : -1;
        }

        return voting;
    }
}
