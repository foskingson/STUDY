package hackerRank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class ClimbingLeaderboard {

    public static List<Integer> climbingLeaderboard(List<Integer> ranked, List<Integer> player) {
        int[] ranks = new int[ranked.size()];
        ranks[0] = 1; int rank = 1;
        for (int i = 1; i < ranked.size(); i++) {
            if (!Objects.equals(ranked.get(i - 1), ranked.get(i))) rank++;
            ranks[i] = rank;
        }

        System.out.println(Arrays.toString(ranks));
        List<Integer> answer = new ArrayList<>();
        int i = 0, j = ranks.length - 1;
        while (i < player.size()) {
            while (j >= 0 && player.get(i) > ranked.get(j)) {
                j--;
            }

            if (j == -1) {
                answer.add(1);
            } else {
                if (player.get(i) < ranked.get(j)) {
                    answer.add(ranks[j] + 1);
                } else {
                    answer.add(ranks[j]);
                }
            }
            i++;
        }
        return answer;
    }

    public static void main(String[] args) {
        List<Integer> ranked = new ArrayList<>(List.of(100, 100, 50, 40, 40, 20, 10));
        List<Integer> player = new ArrayList<>(List.of(5, 25, 50, 120));
        
        System.out.println(climbingLeaderboard(ranked, player));
    }

}
