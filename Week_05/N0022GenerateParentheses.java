package s0000;

import java.util.LinkedList;
import java.util.List;

/**
 * @author traceless
 */
public class N0022GenerateParentheses {
    public List<String> generateParenthesis(int n) {
        List<String> res = new LinkedList<>();
        generate(res, new StringBuilder(), 0, 0, n);
        return res;
    }

    public void generate(List<String> ans, StringBuilder sb, int left, int right, int n) {
        if (sb.length() == (n << 1)) {
            ans.add(sb.toString());
            return;
        }
        if (n > left) {
            sb.append('(');
            generate(ans, sb, left + 1, right, n);
            sb.setLength(sb.length() - 1);
        }
        if (left > right) {
            sb.append(')');
            generate(ans, sb, left, right + 1, n);
            sb.setLength(sb.length() - 1);
        }
    }
}
