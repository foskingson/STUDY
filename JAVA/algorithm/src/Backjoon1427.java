import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Backjoon1427 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String temp = bf.readLine(); 
        char[] a = temp.toCharArray();
        int[] n = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            n[i] = a[i]-'0';
        }

        solution(n);
    }

    private static void solution(int[] n) {
        for (int i = 0; i < n.length; i++) {
            int maxIdx = i;
            for (int k = i+1; k < n.length; k++) {
                if(n[maxIdx]<n[k]){
                    maxIdx=k;
                }
            }
            int temp = n[i];
            n[i]=n[maxIdx];
            n[maxIdx]=temp;
        }

        for (int i : n) {
            System.out.print(i);
        }
    }
}
