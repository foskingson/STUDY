package hackerRank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GridChallenge {
    public static String gridChallenge(List<String> grid) {
        int n = grid.size();
        List<String> sortedGrid = new ArrayList<>();

        for (String row : grid) {
            char[] charArray = row.toCharArray();
            Arrays.sort(charArray);
            sortedGrid.add(new String(charArray));
        }

        for (int col = 0; col < sortedGrid.get(0).length(); col++) {
            for (int row = 1; row < n; row++) {
                if (sortedGrid.get(row).charAt(col) < sortedGrid.get(row - 1).charAt(col)) {
                    return "NO";
                }
            }
        }

        return "YES";
    }
}
