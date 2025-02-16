package hackerRank;

class Tree {
    int data;
    Tree left;
    Tree right;
}

public class PreOrder {
    public static void preOrder(Tree root) {
        if (root==null) {
            return;
        }
        System.out.print(root.data+" ");
        preOrder(root.left);
        preOrder(root.right);
    }
}
