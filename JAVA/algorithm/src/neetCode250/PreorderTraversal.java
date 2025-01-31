package neetCode250;

import java.util.ArrayList;
import java.util.List;

public class PreorderTraversal {

    List<Integer> res = new ArrayList<>();

    public List<Integer> preorderTraversal(TreeNode root) {
        preorder(root);
        return res;
    }

    private void preorder(TreeNode root) {
        if (root==null) {
            return;
        }

        res.add(root.val);
        preorder(root.left);
        preorder(root.right);
    }
}
