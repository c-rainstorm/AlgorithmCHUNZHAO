package s0100;

import me.rainstorm.ds.TreeNode;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

public class N0105ConstructBinaryTreeFromPreorderAndInorderTraversal {

    @Test
    public void case1() {
        buildTree(new int[]{3, 9, 8, 5, 4, 10, 20, 15, 7},
                new int[]{4, 5, 8, 10, 9, 3, 15, 20, 7});
    }

    /**
     * @TimeComplexity O(n)
     * @SpaceComplexity O(n)
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) return null;

        TreeNode root = new TreeNode(preorder[0]);
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        for (int i = 1, j = 0; i < preorder.length; i++) {
            TreeNode newNode = new TreeNode(preorder[i]);
            TreeNode node = stack.peek();
            if (node.val != inorder[j]) {
                node.left = newNode;
            } else {
                while (!stack.isEmpty() && stack.peek().val == inorder[j]) {
                    node = stack.pop();
                    j++;
                }
                node.right = newNode;
            }
            stack.push(newNode);
        }
        return root;
    }
}
