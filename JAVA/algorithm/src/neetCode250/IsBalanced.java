package neetCode250;

public class IsBalanced {
    boolean check = true;
    public boolean isBalanced(TreeNode root) {
        findDepth(root);
        return check;
    }

    public int findDepth(TreeNode root) {
        if (root==null) {
            return 0;
        }
        int l = findDepth(root.left);
        int r = findDepth(root.right);
        if (Math.abs(l - r) > 1) {
            check = false;
        }
       return Math.max(l, r)+1;
    }
}
