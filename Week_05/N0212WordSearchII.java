package s0200;

import java.util.LinkedList;
import java.util.List;

/**
 * @author traceless
 */
public class N0212WordSearchII {
    List<String> result = new LinkedList<>();

    public List<String> findWords(char[][] board, String[] words) {
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];

        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(board, visited, i, j, trie.root);
            }
        }

        return result;
    }

    private void dfs(char[][] board, boolean[][] visited, int i, int j, TrieNode cur) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || visited[i][j]) {
            return;
        }

        cur = cur.children[board[i][j] - 'a'];
        if (cur == null) {
            return;
        }

        visited[i][j] = true;

        if (cur.val != null) {
            result.add(cur.val);
            cur.val = null;
        }
        dfs(board, visited, i + 1, j, cur);
        dfs(board, visited, i - 1, j, cur);
        dfs(board, visited, i, j + 1, cur);
        dfs(board, visited, i, j - 1, cur);

        visited[i][j] = false;
    }

    static class Trie {
        public TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode node = root;
            for (char ch : word.toCharArray()) {
                if (node.children[ch - 'a'] == null) {
                    node.children[ch - 'a'] = new TrieNode();
                }
                node = node.children[ch - 'a'];
            }
            node.val = word;
        }
    }

    static class TrieNode {
        public String val;
        public TrieNode[] children = new TrieNode[26];
    }
}
