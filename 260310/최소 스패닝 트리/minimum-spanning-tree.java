import java.io.*;
import java.util.*;


public class Kruskal {

    static int find(int x) {
        if(x == parents[x]) {
            return x;
        }
        return parents[x] = find(parents[x]); // 결국 찾아야 하는 건 부모 노드까지 가야해서 경로 압축하는 것이 이득
        
    }
    
    static boolean union(int a, int b) {
        
        int o1 = find(a);
        int o2 = find(b);
        
        if(o1 == o2) {
            return false;
        }
        
        if(o1<o2) {
            parents[o2] = o1; 
        }
        else parents[o1] = o2; 
        
        return true;
    }
    
    static int[] parents;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        parents = new int[n+1];
        int cost = 0;
        int count = 0;
        int[][] edges = new int[m+1][3];
        for (int i = 1; i <= m; i++) {
            edges[i][0] = sc.nextInt();
            edges[i][1] = sc.nextInt();
            edges[i][2] = sc.nextInt();
        }
        
        // 1. 가중치 기준으로 정렬
        Arrays.sort(edges, (a,b)->Integer.compare(a[2],b[2]));
        
        // 2. parents 배열 초기화
        for(int i=1; i<=n;i++) {
            parents[i] = i;
        }
        
        // 3. 확인하여 간선 연결하기
        for(int i=1;i<=m;i++) {
            if(union(edges[i][0], edges[i][1])) {
                cost += edges[i][2];
                if(++count == m-1) break;
            }
        }
        System.out.println(cost);
    }
}