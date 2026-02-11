import java.io.*;
import java.util.*;

/**
 * SW Expert Academy : 2806. N-Queen
 *
 *
 *  문제 설명:
 *  1. testCase 입력받기
 *      1-1. testCase 마다 N 개의 N*N 만큼 N개의 퀸 만들기
 *      1-2. 1<= N <= 10
 *
 *
 *  해결 접근:
 *  1. 같은 열에 있는 것과, 그 다음 행의 대각선 들이 각각 이미 있는지 확인
 *  2. 열, 대각선 1, 대각선 2 구하기
 *      2-1. 열은 같은 열에 이미 퀸이 있는지 확인 col[c]
 *      2-2. diag1 대각선은 (0,2),(1,1),(2,0) 이러한 방향이면 row+col 값 동일
 *      2-3. diag2 대각선은 (2,0),(3,1),(4,2) 이러한 방향이라면 row-col 하면 음수 값으로 모두 동일함, 이때 +N을 해서 인덱스 보정함.
 *  알고리즘
 */
public class Main {


    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int N, T, count;
    static boolean[] col, diag1, diag2;

    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        col = new boolean[N];
        count =0;

        // 최대 인덱스 크기는 2N
        // 대각선의 개수는 row+col, row-col 값의 범위인데
        // 이는 각각 나올 수 있는 경우의 수 최대가 그러함.
        diag1 = new boolean[2 * N];
        diag2 = new boolean[2 * N];


        dfs(0);
        System.out.println(count);

        
    }

    static void dfs(int row) {
        if (row == N) {
            count++;
            return;
        }

        for (int c=0; c<N; c++){
            if(col[c] || diag1[row+c] || diag2[row-c+N]) continue;

            col[c] = diag1[row+c] = diag2[row -c +N] = true;

            dfs(row+1);

            col[c] = diag1[row+c] = diag2[row-c+N] = false;
        }
    }
}