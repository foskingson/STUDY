package neetCode250;



public class IsSameTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p.val==q.val) {
            return true;
        }else if(p.val!=q.val){
            return false;
        }

        boolean l = isSameTree(p.left, q.left);
        boolean r = isSameTree(p.right, q.right);

        if (l && r) {
            return true;
        }else{
            return false;
        }
    }
}
