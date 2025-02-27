package neetCode250;

import java.util.PriorityQueue;

public class KClosest {
    class Kc{
        int x;
        int y;
        double dis;
        public Kc(int x, int y, double dis) {
            this.x = x;
            this.y = y;
            this.dis = dis;
        }

    }

    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<Kc> kc = new PriorityQueue<>((a,b)->{
            return Double.compare(a.dis, b.dis);
        });
 
        for (int[] point : points) {
            int x = point[0];
            int y = point[1];
            double dis = Math.sqrt(x*x+y*y);
            kc.add(new Kc(x,y, dis));
        }
        
        int[][] res = new int[k][2];
        for (int i = 0; i < k; i++) {
            Kc temp = kc.poll();
            res[i][0]=temp.x;
            res[i][1]=temp.y;
        }

        return res;
    }
}
