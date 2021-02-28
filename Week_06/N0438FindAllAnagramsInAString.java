package s0400;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author traceless
 */
public class N0438FindAllAnagramsInAString {
    public List<Integer> findAnagrams(String s, String p) {
        if (s.length() < p.length()) return Collections.emptyList();

        int[] target = new int[26];
        for (char ch : p.toCharArray()) {
            target[ch - 'a']++;
        }

        List<Integer> list = new LinkedList<>();
        int[] count = new int[26];
        for (int i = 0, index; i < s.length(); ++i) {
            index = Math.max(i - p.length() + 1, 0);
            count[s.charAt(i) - 'a']++;

            if (index == i - p.length() + 1) {
                if (Arrays.equals(count, target)) {
                    list.add(index);
                }

                count[s.charAt(index) - 'a']--;
            }
        }

        return list;
    }
}
