package s0000;

import java.util.*;

/**
 * @author traceless
 */
public class N0049GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>(strs.length);
        int[] counts = new int[26];
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            Arrays.fill(counts, 0);

            for (int i = 0; i < str.length(); i++) {
                counts[str.charAt(i) - 'a']++;
            }

            sb.setLength(0);
            for (int i = 0; i < counts.length; i++) {
                if (counts[i] != 0) {
                    sb.append((char) ('a' + i));
                    sb.append(counts[i]);
                }
            }

            String key = sb.toString();
            map.computeIfAbsent(key, (k) -> new LinkedList<>())
                    .add(str);
        }
        return new ArrayList<>(map.values());
    }
}
