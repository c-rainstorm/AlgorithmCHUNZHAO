package s0200;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author traceless
 */
public class N0200NumberOfIslands {

    static class UnionFind {
        int[] roots;

        public UnionFind(int N) {
            roots = new int[N];
            for (int i = 0; i < N; i++) {
                roots[i] = i;
            }
        }

        public int find(int x) {
            int root = x;
            while (root != roots[root])
                root = roots[root];
            while (x != roots[x]) {
                int tmp = roots[x];
                roots[x] = root;
                x = tmp;
            }
            return root;
        }

        public void union(int x, int y) {
            int xRoot = find(x);
            int yRoot = find(y);
            roots[xRoot] = yRoot;
        }

        public int count() {
            int count = 0;
            for (int i = 0; i < roots.length; ++i) {
                if (i == roots[i]) count++;
            }
            return count;
        }
    }


    public int unionFind(char[][] grid) {
        if (grid.length == 0) return 0;
        if (grid[0].length == 0) return 0;

        int rows = grid.length;
        int cols = grid[0].length;

        UnionFind unionFind = new UnionFind(rows * cols);

        int space = 0;
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (grid[i][j] == '0') {
                    space++;
                } else {
                    if (i > 0 && grid[i - 1][j] == '1') unionFind.union(toIndex(i - 1, j, cols), toIndex(i, j, cols));
                    if (j > 0 && grid[i][j - 1] == '1') unionFind.union(toIndex(i, j - 1, cols), toIndex(i, j, cols));
                }
            }
        }

        return unionFind.count() - space;
    }

    private int toIndex(int x, int y, int cols) {
        return x * cols + y;
    }

    public int dfs(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        int rows = grid.length;
        int cols = grid[0].length;
        int ans = 0;
        for (int r = 0; r < rows; ++r) {
            for (int c = 0; c < cols; ++c) {
                if (grid[r][c] == '1') {
                    ++ans;
                    dfs(grid, r, c);
                }
            }
        }

        return ans;
    }

    void dfs(char[][] grid, int row, int col) {
        int nr = grid.length;
        int nc = grid[0].length;

        if (row < 0 || col < 0 || row >= nr || col >= nc || grid[row][col] == '0') {
            return;
        }

        grid[row][col] = '0';
        dfs(grid, row - 1, col);
        dfs(grid, row + 1, col);
        dfs(grid, row, col - 1);
        dfs(grid, row, col + 1);
    }

    @Test
    public void test() {
        char[][] grid = new char[][]{
                {'1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '0', '1', '1'},
                {'0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '0'},
                {'1', '0', '1', '1', '1', '0', '0', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
                {'1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
                {'1', '0', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '0', '1', '1', '1', '0', '1', '1', '1'},
                {'0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '0', '1', '1', '1', '1'},
                {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '0', '1', '1'},
                {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
                {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
                {'0', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
                {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
                {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
                {'1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1'},
                {'1', '0', '1', '1', '1', '1', '1', '0', '1', '1', '1', '0', '1', '1', '1', '1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '0'},
                {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '0', '0'},
                {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
                {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'},
                {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1'}
        };
        assert numIslands(grid) == 1;
    }

    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int count = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == '1') {
                    count++;
                    grid[i][j] = '0';
                    bfs(grid, m, n, i, j);
                }
            }
        }

        return count;
    }

    private void bfs(char[][] grid, int m, int n, int i, int j) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(i * n + j);
        while (!queue.isEmpty()) {
            int pos = queue.poll();
            int r = pos / n;
            int c = pos % n;

            if (r - 1 >= 0 && grid[r - 1][c] == '1') {
                grid[r - 1][c] = '0';
                queue.offer(pos - n);
            }
            if (r + 1 < m && grid[r + 1][c] == '1') {
                grid[r + 1][c] = '0';
                queue.offer(pos + n);
            }
            if (c - 1 >= 0 && grid[r][c - 1] == '1') {
                grid[r][c - 1] = '0';
                queue.offer(pos - 1);
            }
            if (c + 1 < n && grid[r][c + 1] == '1') {
                grid[r][c + 1] = '0';
                queue.offer(pos + 1);
            }
        }
    }

    private void print(char[][] grid) {
        StringBuilder joiner = new StringBuilder();
        for (char[] chars : grid) {
            joiner.setLength(0);
            for (char ch : chars) {
                joiner.append(ch).append(" ");
            }
            System.out.println(joiner.toString());
        }
    }

}
