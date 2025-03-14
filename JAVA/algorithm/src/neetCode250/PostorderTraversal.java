package neetCode250;

import java.util.ArrayList;
import java.util.List;

public class PostorderTraversal {

    List<Integer> res = new ArrayList<>();

    public List<Integer> postorderTraversal(TreeNode root) {
        postorder(root);
        return res;
    }

    private void postorder(TreeNode root) {
        if (root==null) {
            return;
        }

        postorder(root.left);
        postorder(root.right);
        res.add(root.val);
        
    }
}
