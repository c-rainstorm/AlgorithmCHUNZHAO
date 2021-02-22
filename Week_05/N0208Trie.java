package me.rainstorm.ds.tree;

public class N0208Trie {
    private static class TrieNode {
        public String val;
        public TrieNode[] children = new TrieNode[26];

        public boolean contains(char ch) {
            return children[ch - 'a'] != null;
        }

        public TrieNode get(char ch) {
            return children[ch - 'a'];
        }

        public void put(char ch, TrieNode node) {
            children[ch - 'a'] = node;
        }

        public boolean isEnd() {
            return val != null;
        }
    }

    private TrieNode root;

    public N0208Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            if (!node.contains(ch)) {
                node.put(ch, new TrieNode());
            }
            node = node.get(ch);
        }
    }

    private TrieNode searchPrefix(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            if (node.contains(ch)) {
                node = node.get(ch);
            } else {
                return null;
            }
        }
        return node;
    }

    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        return node != null && node.isEnd();
    }

    public boolean startsWith(String prefix) {
        TrieNode node = searchPrefix(prefix);
        return node != null;
    }
}
