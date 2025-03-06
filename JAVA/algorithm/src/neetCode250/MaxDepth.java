package neetCode250;

public class MaxDepth {
    public int maxDepth(TreeNode root) {
        if (root==null) {
            return 0;
        }
        int l = maxDepth(root.left)+1;
        int r = maxDepth(root.right)+1;
        return Math.max(l, r);
    }
}
