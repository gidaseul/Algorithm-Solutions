import java.io.*;
import java.util.*;

/**
 * 백준 6987번 : 월드컵 (골드 4)
 *
 */

public class Main {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
    static int N;
    static int[][] map; // 입력 값 저장 배열
    static int[][] game; // 게임 경기 저장


    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        map = new int[6][3];
        game = new int[15][2];

        // 경기의 수
        int idx = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = i + 1; j < 6; j++) {
                game[idx][0] = i;
                game[idx][1] = j;
                idx++;
            }
        }



        for (int t = 0; t < 4; t++) {
            st = new StringTokenizer(br.readLine());
            int sum = 0;
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 3; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    sum += map[i][j];
                }
            }

            if (sum != 30) {
                sb.append(0).append(" ");
                continue;
            }

            // DFS 시작
            if (dfs(0)) sb.append(1).append(" ");
            else sb.append(0).append(" ");
        }
            System.out.print(sb);
    }

    static boolean dfs(int idx){


        // 15경기 끝
        if(idx == 15){
            return true;
        }

        int a = game[idx][0];
        int b = game[idx][1];

        // a 승, b 패
        if(map[a][0] > 0 &&  map[b][2] > 0){
            map[a][0]--;
            map[b][2]--;

            if(dfs(idx+1)) return true;

            map[a][0]++;
            map[b][2]++;
        }


        // 무승부
        if(map[a][1] > 0 && map[b][1] >0){
            map[a][1]--;
            map[b][1]--;

            if(dfs(idx+1)) return true;

            map[a][1]++;
            map[b][1]++;
        }

        // a 패, b 승
        if (map[a][2] > 0 && map[b][0] > 0) {
            map[a][2]--;
            map[b][0]--;

            if (dfs(idx + 1)) return true;

            map[a][2]++;
            map[b][0]++;
        }

        // 3가지 경우가 모두 실패라면
        return false;
    }
}
