package hackerRank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(); 
        int i = 0, Q = Integer.parseInt(br.readLine().trim());
        while (i++ < Q) {
            String[] input = br.readLine().split(" ");
            switch(Integer.parseInt(input[0])){
                case 1 :{
                    minHeap.offer(Integer.parseInt(input[1]));
                    break;
                }
                case 2 :{
                    minHeap.remove(Integer.parseInt(input[1]));
                    break;
                }
                case 3 :{
                    System.out.println(minHeap.peek());
                    break;
                }
                default :{
                    System.out.println("Invalid !");
                }
            }
        }
    }
}