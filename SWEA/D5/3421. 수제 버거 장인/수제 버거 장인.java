import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1. 테스트 케이스의 수 T
 * 2. 각 테스트 케이스마다,
 *
 *
 */
public class Solution {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
    static int N, M, count;
    static boolean[][] isBad; // 인접 행렬 만들자.
    static boolean[] selected; // 현재 버거에 담긴 재료들

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= testCase; tc++) {
            st = new StringTokenizer(br.readLine().trim());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            isBad = new boolean[N + 1][N + 1];
            selected = new boolean[N + 1];
            count = 0;


            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine().trim());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                isBad[a][b] = isBad[b][a] = true; //재료가 상극이라서 같이 못 넣는 경우
            }

            dfs(1); // 1번 재료부터 결정 시작

            System.out.println("#" + tc + " " + count);
            }
        }
    static void dfs ( int idx){
        if (idx > N) {
            count++;
            return;
        }
        // 현재 재료 넣지 않는 경우
        dfs(idx + 1);

        // 현재 재료 넣는 경우 (제약 조건 확인)
        if(canAdd(idx)){
            selected[idx] = true;
            dfs(idx+1);
            selected[idx] = false;
        }
    }

    static boolean canAdd(int idx){
        for(int i =1;i<idx; i++){
            if(selected[i] && isBad[i][idx]){
                return false;
            }
        }
        return true;
    }
}