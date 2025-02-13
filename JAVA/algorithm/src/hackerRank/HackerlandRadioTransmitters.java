package hackerRank;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class HackerlandRadioTransmitters {

    public static int hackerlandRadioTransmitters(List<Integer> x, int k) {
        Set<Integer> uniqueSet = new TreeSet<>(x);
        List<Integer> sortedList = new ArrayList<>(uniqueSet);

        int counter = 0;
        int trans = 0;
        int minInRange = 0;

        for (int n : sortedList) {
            if (trans == 0) {
                trans = n;
                counter++;
            } else if (inRange(trans, n, k)) {
                if (minInRange == 0) {
                    minInRange = trans;
                    trans = n;
                } else if (inRange(minInRange, n, k)) {
                    trans = n;
                }
            } else {
                trans = n;
                minInRange = 0;
                counter++;
            }
        }

        return counter;
    }

    private static boolean inRange(int a, int b, int k) {
        return Math.abs(a - b) <= k;
    }

    public static void main(String[] args) {
        System.out.println(hackerlandRadioTransmitters(new ArrayList<>(List.of(9,5,4,2,6,15,12)),4));
    }
}
