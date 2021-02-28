package s0200;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/valid-anagram/solution/you-xiao-de-zi-mu-yi-wei-ci-by-leetcode-solution/
 */
public class N0242ValidAnagram {
    public boolean sort(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        Arrays.sort(str1);
        Arrays.sort(str2);
        return Arrays.equals(str1, str2);
    }

    public boolean hashLowerCase(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] table = new int[26];
        for (int i = 0; i < s.length(); i++) {
            table[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            table[t.charAt(i) - 'a']--;
            if (table[t.charAt(i) - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        Map<Character, Integer> table = new HashMap<>(s.length());
        for (char ch : s.toCharArray()) {
            table.put(ch, table.getOrDefault(ch, 0) + 1);
        }

        for (char ch : t.toCharArray()) {
            Integer count = table.getOrDefault(ch, 0);
            if (count == 0) {
                return false;
            } else if (count == 1) {
                table.remove(ch);
            } else {
                table.put(ch, count - 1);
            }
        }
        return table.isEmpty();
    }
}
