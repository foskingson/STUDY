package hackerRank;

public class Lca {
	public static Tree lca(Tree root, int v1, int v2) {
      	// Write your code here.
          if (root ==null) {
              return root;  
          }
          int larger = Math.max(v1, v2);
          int smaller = Math.min(v1, v2);
          if( root.data >= smaller && root.data <= larger)
            return root;
          if( root.data < smaller)
            return lca(root.right, v1, v2);
        return lca(root.left, v1, v2);
        
    }
}
