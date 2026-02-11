import java.io.*;
import java.util.*;

/**
 * SW Expert Academy 1468
 * 장훈이의 높은 선반
 *
 *  문제 설명:
 *  1. testCase 입력받기
 *  2. N(N명의 점원), B(탑의 높이)
 *  3. N명의 점원들의 키를 모두 입력받는다.
 *
 *  이때 탑의 높이는 1명일 경우 그 점원의 키와 같고, 2명 이상일 경우엔 탑을 만든 모든 점원의 키의 합과 같다.
 *  탑의 높이가 B 이상인 경우 선반 위의 물건을 사용할 수 있지만 너무 높으면 위험하기 때문에 B를 넘는 것 중에 최솟값을 구하려고 함.
 *
 *  해결 접근:
 *  DFS로 탐색을 진행할 건데 이때 이진트리 처럼 사용할 것인가 아닌가와 같은 조합의 관한 문제이다.
 *  즉, 선택하는 경우와 선택하지 않는 경우로 DFS를 2개로 나누고, 그리고 이때 조건에서 어떨 때 멈출 것이고 어떨때 분기점을 나눠서 확인할 것인지
 *	
 *	1. 선택한 경우
 *		dfs(기존 인덱스 + 1, sum+배열[기존인덱스]) 이렇게 값을 추가로 더해서 넘겨줘야 함.
 * 	2. 선택하지 않을 경우
 * 		dfs(기존 인덱스 + 1, sum(선택 안 했기 때문에))
 */
public class Solution {


    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int N,T,B;
    static int[] nHeights; 
    static int minNumber;
    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            sb = new StringBuilder();
            st = new StringTokenizer(br.readLine().trim());
            N = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            nHeights = new int[N];
            minNumber =Integer.MAX_VALUE;
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<N;i++){
                nHeights[i] = Integer.parseInt(st.nextToken());
            }
            dfs(0,0);
            minNumber -= B;
            sb.append("#").append(tc).append(" ").append(minNumber);
            System.out.println(sb);
        }

    }

    static void dfs(int depth, int sum){
        // sum을 받아온 상황에서 사용하기
    	if(sum >=B) {
    		minNumber = Math.min(sum, minNumber);
    		return;
    	}
    	
    	if(depth == N) {
    		return;
    	}
    	
    	// 점원을 고른 경우라면 점원의 값도 추가로 넘겨줘야 함
    	dfs(depth+1,sum+nHeights[depth]);
    	
    	dfs(depth+1,sum);
        
    }
}