package hackerRank;


import java.util.List;

public class TruckTour {
    public static int truckTour(List<List<Integer>> petrolpumps) {
        int sum = 0;
        int res = 0;
        for (int i = 0; i < petrolpumps.size(); i++) {
            sum += petrolpumps.get(i).get(0)-petrolpumps.get(i).get(1);
            if (sum<0){
                sum=0;
                res++;
            } 
        }
        return res;
    }
}
