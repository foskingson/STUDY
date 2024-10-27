import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Backjoon1717 { 
    // 유니온파인드 : 여러 노드가 존재할 때 어떤 두 개의 노드를 같은 집합으로 묶어 주고, 어떤 두 노드가 같은 집합에 있는지 확인하는 알고리즘
    // union(1,4) : 그래프로 표현하면 1과4를 연결했다는 것과 같은 의미, 이중 작은것을 대표노드로 정한다면 1이 대표노드(idx가 4인 곳에 1을 넣어주면 됨)
    // 여기서 주의해야 할 것은 항상 대표노드끼리 연결해야 한다.
    // 이 대표노드를 찾는 것을 find 연산을 통해 한다.
    // find(4) : 4번 인덱스에 값이 4로 똑같이 들어있으면 그게 대표노드이고, 아니라면 그 값을 idx로 해서 이동해서 인덱스와 값이 똑같을때까지 찾는다.
    // find를 최적화하기 위해서는 경로압축이 필요
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 집합의 개수
        int m = Integer.parseInt(st.nextToken()); // 연산의 수
        int[] set = new int[n+1];	
        for (int i = 0; i < set.length; i++) {
            set[i]=i;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (op==0) {
                union(set,a,b);
            }else{
                if (find(set, a) == find(set, b)) {
                    System.out.println("YES");
                } else {
                    System.out.println("NO");
                }
            }
        }
    }

    private static int find(int[] set, int key) {
        if (set[key] != key) {
            set[key] = find(set, set[key]); // 경로 압축
        }
        return set[key];
    }

    private static void union(int[] set, int a, int b) {
        a = find(set, a);
        b = find(set, b);
        if (a<b) {
            set[b]=a;
        }else{
            set[a]=b;
        }
    }
}
