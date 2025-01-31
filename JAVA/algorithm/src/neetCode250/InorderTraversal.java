package neetCode250;

import java.util.ArrayList;
import java.util.List;

public class InorderTraversal {
    List<Integer> res = new ArrayList<>();
    
    public List<Integer> inorderTraversal(TreeNode root) {
        inorder(root);
        return res;
    }

    private void inorder(TreeNode root) {
        if (root==null) {
            return;
        }

        inorder(root.left);
        res.add(root.val);
        inorder(root.right);
    }
}

