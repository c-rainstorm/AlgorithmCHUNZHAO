package s0200;

import me.rainstorm.util.BitUtil;
import org.junit.Test;

/**
 * [231] 2的幂
 * <p>
 * https://leetcode-cn.com/problems/power-of-two/description/
 * <p>
 * algorithms
 * Easy (44.60%)
 * Total Accepted:    15.9K
 * Total Submissions: 35.6K
 * Testcase Example:  '1'
 * <p>
 * 给定一个整数，编写一个函数来判断它是否是 2 的幂次方。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1
 * 输出: true
 * 解释: 2^0 = 1
 * <p>
 * 示例 2:
 * <p>
 * 输入: 16
 * 输出: true
 * 解释: 2^4 = 16
 * <p>
 * 示例 3:
 * <p>
 * 输入: 218
 * 输出: false
 *
 * @author baochen1.zhang
 * @date 2019.04.10
 */
public class N0231IsPowerOfTwo {

    @Test
    public void case1() {
        assert isPowerOfTwo(1);
    }

    @Test
    public void case2() {
        assert isPowerOfTwo(2);
    }

    @Test
    public void case3() {
        assert !isPowerOfTwo(3);
    }

    @Test
    public void case4() {
        assert !isPowerOfTwo(0);
    }

    @Test
    public void case5() {
        assert !isPowerOfTwo(-1);
    }

    public boolean isPowerOfTwo(int n) {
        if ((n & Integer.MIN_VALUE) != 0 || n == 0) {
            return false;
        }

        return (n & (n - 1)) == 0
    }
}
