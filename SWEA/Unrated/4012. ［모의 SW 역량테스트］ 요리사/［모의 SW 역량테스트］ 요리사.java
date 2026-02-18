import java.io.*;
import java.util.*;

/**
 * SW Expert Academy 4012 : [모의 SW 역량테스트] 요리사
 *
 *
 *  문제 설명:
 *  1. N개의 식재료가 주어진다. (N은 짝수)
 *  2. 식재료를 N/2개씩 나누어 A 음식과 B 음식을 만든다.
 *  3. 두 음식의 맛 차이 |A - B|의 최소값을 구한다.
 *
 *  해결 접근:
 *      - N개 중 N/2개를 선택하는 조합 문제
 *      - 선택된 재료는 A 음식, 선택되지 않은 재료는 B 음식
 *      - 조합 DFS + 백트래킹 사용
 *
 */
public class Solution {


    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;


    static int T,N,min;
    static int[][] map;
    static boolean[] selected;
    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {

            N = Integer.parseInt(br.readLine().trim());
            map = new int[N][N];
            selected = new boolean[N];
            for(int i=0; i<N; i++){
                st = new StringTokenizer(br.readLine().trim());
                for(int j=0; j<N; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            min = Integer.MAX_VALUE;
            dfs(0,0);

            sb.append("#").append(tc).append(" ").append(min).append("\n");
        }
        System.out.print(sb);

    }

    static void dfs(int idx, int count){
        if(count == N/2){
            calculate();
            return;
        }
        for(int i=idx; i<N; i++){
            selected[i] = true;
            dfs(i+1,count+1);
            selected[i] = false;
        }
    }

    static void calculate(){
        int aSum = 0;
        int bSum = 0;

        // 필요한 쌍으로
        for(int i=0; i<N; i++){
            for(int j=i+1; j<N; j++){
                if(selected[i] && selected[j]){
                    aSum += map[i][j] + map[j][i];
                }else if(!selected[i] && !selected[j]){
                    bSum += map[i][j] + map[j][i];
                }
            }
        }
        min = Math.min(min,Math.abs(aSum-bSum));
    }
}