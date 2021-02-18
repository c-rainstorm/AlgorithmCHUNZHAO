package s0000;

import java.util.*;

/**
 * @author traceless
 */
public class N0051NQueens {
    private List<List<String>> results = new LinkedList<>();
    private Set<Integer> cols = new HashSet<>();
    private Set<Integer> pies = new HashSet<>();
    private Set<Integer> nas = new HashSet<>();

    public List<List<String>> solveNQueens(int n) {
        if (n < 1) return Collections.emptyList();
        dfs(n, 0, new int[n]);
        return results;
    }

    private void dfs(int n, int row, int[] queues) {
        if (row >= n) {
            results.add(packageQueues(queues));
            return;
        }

        for (int col = 0; col < n; ++col) {
            int pie = row + col, na = row - col;
            if (cols.contains(col) || pies.contains(pie) || nas.contains(na))
                continue;

            cols.add(col);
            pies.add(pie);
            nas.add(na);

            queues[row] = col;
            dfs(n, row + 1, queues);

            cols.remove(col);
            pies.remove(pie);
            nas.remove(na);
        }
    }

    private List<String> packageQueues(int[] queues) {
        List<String> list = new LinkedList<>();
        for (Integer col : queues) {
            char[] row = new char[queues.length];
            Arrays.fill(row, '.');
            row[col] = 'Q';
            list.add(new String(row));
        }
        return list;
    }
}
