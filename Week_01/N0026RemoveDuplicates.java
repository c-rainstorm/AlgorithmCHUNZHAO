package s0000;

import org.junit.Test;

import static me.rainstorm.util.ArrayUtil.buildStr;

public class N0026RemoveDuplicates {
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int i = 1, j = 1;
        while (j < nums.length) {
            if (nums[j - 1] == nums[j]) {
                j++;
            } else {
                nums[i++] = nums[j++];
            }
        }
        return i;
    }
}
