package s0000;

import me.rainstorm.ds.TreeNode;

import java.util.*;

public class N0094BinaryTreeInorderTraversal {
    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) return Collections.emptyList();

        TreeNode sen = new TreeNode(-1);
        List<Integer> res = new LinkedList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node == sen) {
                res.add(stack.pop().val);
                continue;
            }

            if (node.right != null) stack.push(node.right);
            stack.push(node);
            stack.push(sen);
            if (node.left != null) stack.push(node.left);
        }
        return res;
    }
}
