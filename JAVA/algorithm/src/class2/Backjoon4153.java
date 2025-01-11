package class2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Backjoon4153 { // 직각 삼각형
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int[] a = new int[3];

        createTri(st, a);

        while (a[0]!=0) {
            if (Math.pow(a[0], 2)+Math.pow(a[1], 2)==Math.pow(a[2], 2)) {
                System.out.println("right");
            }else{
                System.out.println("wrong");
            }
            st = new StringTokenizer(bf.readLine());
            createTri(st, a);
            Arrays.sort(a);
        }
    }

    private static void createTri(StringTokenizer st, int[] a) {
        for (int i = 0; i < 3; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
    }
}
