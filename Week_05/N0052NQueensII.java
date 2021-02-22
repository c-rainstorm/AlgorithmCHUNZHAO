package s0000;

public class N0052NQueensII {
    public int totalNQueens(int n) {
        return solve(n, 0, 0, 0, 0);
    }

    public int solve(int n, int row, int col, int pie, int na) {
        if (row >= n) return 1;

        int count = 0;
        int available = ((1 << n) - 1) & (~(col | pie | na));
        while (available != 0) {
            // 取最后一个Bit
            int pos = available & (-available);
            // 占用这个位置
            count += solve(n, row + 1, col | pos, (pie | pos) << 1, (na | pos) >> 1);
            // 移除最后一个Bit
            available -= pos;
        }
        return count;
    }
}
