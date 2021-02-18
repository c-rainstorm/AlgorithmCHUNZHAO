package s0400;

import java.util.Arrays;

/**
 * @author traceless
 */
public class N0455AssignCookies {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int count = 0;
        for (int i = 0, j = 0; i < g.length && j < s.length; i++, j++) {
            while (j < s.length) {
                if (g[i] > s[j]) {
                    j++;
                    continue;
                }
                count++;
                break;
            }
        }
        return count;
    }
}
