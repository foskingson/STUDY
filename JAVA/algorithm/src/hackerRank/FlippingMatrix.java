package hackerRank;

import java.util.List;

public class FlippingMatrix {
    public static int flippingMatrix(List<List<Integer>> matrix) {
        int m = matrix.size();
        int n = matrix.get(0).size();
        int sum = 0;

        int quadSize = matrix.size()/2;

        for(int r = 0; r < quadSize; r++)
        {
            for(int c = 0; c < quadSize; c++)
            {
                int p1 = matrix.get(r).get((2*quadSize) - c - 1);
                int p2 = matrix.get(r).get(c);
                int p3 = matrix.get((2*quadSize) - r - 1).get(c);
                int p4 = matrix.get((2*quadSize) - r - 1).get((2*quadSize) - c - 1);

                sum += Math.max(p1, Math.max(p2, Math.max(p3, p4)));
            }
        }

        return sum;

    }

}
