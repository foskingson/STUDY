import java.io.*;
import java.util.*;

/*
 * 2가지 방법 
 * 먼저 나오는 첫번째는 정렬한 후 투포인터를 사용하여 해결 O(n log n)
 * 아래에 있는 두번째는 이중반복문을 사용하여 해결 O(n^2)
 * 따라서 투포인터가 더 시간복잡도면에서 효율적
 * 일반적으로 Scanner가 BufferedReader보다 느리다.
 * Scanner는 입력을 파싱하고, 다양한 데이터 타입(int, double, string 등)을 자동으로 변환
 * BufferedReader는 단순히 문자열(String)을 읽어들이기 때문에 매우 빠르다. 다만 어느정도의 파싱 필요
 */

class Main {    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());	// 재료의 개수
        int M = Integer.parseInt(br.readLine());	// 갑옷이 완성되는 번호의 합

        // 재료들의 고유번호를 입력받아 배열 A[N]에 저장한다.
        int[] A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        // A[N]을 오름차순 정렬한다.
        Arrays.sort(A);

        int count = 0;	// 갑옷을 만들 수 있는 경우의 수
        int i = 0;		// min 값이 저장된 인덱스
        int j = N-1; 	// max 값이 저장된 인덱스

        // 투 포인터 이동 원칙을 이용해 탐색
        while(i < j) {
            if(A[i]+A[j] < M) {
                i++;
            } else if (A[i]+A[j] > M) {
                j--;
            } else { // A[i]+A[j] == M
                count++;
                i++;
                j--;
            }
        }

        // 결과 출력
        System.out.println(count);
        br.close();
    }
}

public class Backjoon1940 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n = sc.nextInt();  // 재료의 개수
        int m= sc.nextInt();  // 갑옷을 만드는데 필요한 수 

        int[] num = new int[n];
        for (int i = 0; i < n; i++) {
            num[i] = sc.nextInt();   // 재료가 가진 고유 번호
        }
        solution(n,m,num);
    }

    private static void solution(int n, int m, int[] num) {
        int st=0, end=1, count = 0;
        
        for (int i = 0; i < n; i++) {
            for (int k = 1; k < n; k++) {
                if (i==k || k<i) continue;
                if(num[i]+num[k]==m)count++;
                
            }
        }
        System.out.println(count);
    }
}


