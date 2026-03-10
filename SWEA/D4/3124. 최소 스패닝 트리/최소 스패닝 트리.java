import java.io.*;
import java.util.*;

/**
 *
 * @author gidasuel
 * SWEA 3124. 최소 스패닝 트리 (D4)
 *
 *	1. 입력받는다.
 *		1-1. test case를 입력받는다.
 *		1-2.V(정점), E(간선) 입력받는다.
 *		1-3. E개 마다,
 *			1-3-1. A,B,C (정점  A -> 정점 B , 가중치 C (음수 가능성 있음)
 *
 *	2. parents 배열을 생성한다. (V+1)만큼
 *		2-1. 배열을 모두 자기 자신으로 초기화 한다.
 *
 *	3. 저장된 가중치를 기준으로 오름차순 정렬 한다. -> 최소가 되는 것을 만들기 위해서
 *
 *	4. Union을 진행한다.
 *		4-1. Union의 반환 값은 boolean 처리하여 true로 반환할 때 간선의 가중치를 합한다.
 *		4-2. Union이 진행 될 때 인자로 받은 2개의 값은 각각 find를 진행하여 자신의 rootNode를 찾는다.
 *		4-3. 만약 간선의 개수가 V-1이 되면 break를 진행한다.
 *
 *<주의 사항> 1,000,000으로 간선의 값이 최대로 존재할 수 있어서 int로 값을 반환하면 오류가 있음</주의>
 */
public class Solution {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int T,V,E;
    static int[] parents;
    static int[][] Edges;


    static int find(int x) {
        if(parents[x]== x)return x;

        return parents[x] = find(parents[x]);
    }

    // 4-2. Union이 진행 될 때 인자로 받은 2개의 값은 각각 find를 진행하여 자신의 rootNode를 찾는다.
    static boolean union(int a, int b) {

        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot==bRoot) {
            return false;
        }
        if(aRoot < bRoot) {
            parents[bRoot] = aRoot;
        }
        else  parents[aRoot] = bRoot;
        return true;
    }


    public static void main(String[] args) throws NumberFormatException, IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        // 1. 입력받는다.
        // 1-1. test case를 입력받는다.
        T = Integer.parseInt(br.readLine().trim());
        for(int tc = 1; tc<=T; tc++) {

            // 1-2.V(정점), E(간선) 입력받는다.
            st = new StringTokenizer(br.readLine().trim());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());



            // 1-3. E개 마다,
            Edges = new int[E][3];
            // 1-3-1. A,B,C (정점  A -> 정점 B , 가중치 C (음수 가능성 있음)
            for(int e=0;e<E;e++) {
                st = new StringTokenizer(br.readLine().trim());
                Edges[e][0] = Integer.parseInt(st.nextToken());
                Edges[e][1] = Integer.parseInt(st.nextToken());
                Edges[e][2] = Integer.parseInt(st.nextToken());

            }

            // 2. parents 배열을 생성한다. (V+1)만큼
            parents = new int[V+1];
            // 2-1. 배열을 모두 자기 자신으로 초기화 한다.
            for(int v=0; v<=V;v++) {
                parents[v] = v;
            }

            // 3. 저장된 가중치를 기준으로 오름차순 정렬 한다. -> 최소가 되는 것을 만들기 위해서
            Arrays.sort(Edges, (a,b)-> Long.compare(a[2],b[2]));

            // 4. Union을 진행한다.
            int count = 0;
            long result = 0;

            for(int i=0; i<E;i++) {
                if(union(Edges[i][0],Edges[i][1])) {
                    // 4-1. Union의 반환 값은 boolean 처리하여 true로 반환할 때 간선의 가중치를 합한다.
                    result+= Edges[i][2];
                    // 4-3. 만약 간선의 개수가 V-1이 되면 brake를 진행한다.
                    if(++count == V-1) break;
                }
            }
            sb.append("#").append(tc).append(" ").append(result).append("\n");
            System.out.print(sb);
            sb.setLength(0);
        }
    }
}
