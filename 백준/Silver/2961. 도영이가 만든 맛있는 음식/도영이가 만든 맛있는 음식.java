import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
    static int[][] ingredients;
    static int N;
    static long minDiff = Long.MAX_VALUE; // 최소 차이를 저장 (초기값은 최대값)

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        N = Integer.parseInt(br.readLine().trim());
        ingredients = new int[N][2]; // [N][2] 구조로 신맛, 쓴맛 관리
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine().trim());
            ingredients[i][0] = Integer.parseInt(st.nextToken());
            ingredients[i][1] = Integer.parseInt(st.nextToken());
        }

        dfs(0,1,0,0);

        System.out.println(minDiff);
    }

    static void dfs(int index, int s, int b, int count){
        // 1. 기저 조건 : 모든 재료를 다 훑어본 상태라면
        if(index == N){
            if(count > 0){ // 무조건 한 개는 선택 해야 함.
                long diff = Math.abs(s-b);
                if(diff < minDiff){
                    minDiff = diff;
                }
            }
            return;
        }
        dfs(index+1, s*ingredients[index][0], b+ingredients[index][1],count+1);

        dfs(index+1, s,b,count);
    }
}