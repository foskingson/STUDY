package neetCode250;

public class IslandPerimeter {
    public int islandPerimeter(int[][] grid) {
        int res = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int k = 0; k < grid[0].length; k++) {
                if (grid[i][k]==1) {
                    int l = 4;

                    if (i!=0 && grid[i-1][k]==1) {
                        l--;
                    }
                    if (i!=grid.length-1 && grid[i+1][k]==1) {
                        l--;
                    }
                    if (k!=0 && grid[i][k-1]==1) {
                        l--;
                    }
                    if (k!=grid.length-1 && grid[i][k+1]==1) {
                        l--;
                    }
                    res+=l;
                }
            }
        }
        return res;
    }
}
